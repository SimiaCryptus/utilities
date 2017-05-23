/*
 * Copyright (c) 2017 by Andrew Charneski.
 *
 * The author licenses this file to you under the
 * Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance
 * with the License.  You may obtain a copy
 * of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.simiacryptus.util;

import com.simiacryptus.util.io.AsyncOutputStream;
import com.simiacryptus.util.io.TeeOutputStream;
import fi.iki.elonen.NanoHTTPD;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.function.Consumer;
import java.util.function.Function;

public class StreamNanoHTTPD extends NanoHTTPD {
  private final String mimeType;
  public final TeeOutputStream dataReciever;
  private final URI gatewayUri;
  private final File file;
  public final Map<String, Function<IHTTPSession, Response>> customHandlers = new HashMap<>();
  private final ExecutorService pool = Executors.newCachedThreadPool();
  
  
  public void addHandler(String path, String mimeType, Consumer<OutputStream> logic, boolean async) {
    addSessionHandler(path, simpleHandler(pool, mimeType, logic, async));
  }
  
  public Function<IHTTPSession, Response> addSessionHandler(String path, Function<IHTTPSession, Response> value) {
    return customHandlers.put(path, value);
  }
  
  public static Function<IHTTPSession, Response> simpleHandler(ExecutorService pool, String mimeType, Consumer<OutputStream> logic, boolean async) {
    return session -> {
      PipedInputStream snk = new PipedInputStream();
      Semaphore onComplete = new Semaphore(0);
      pool.submit(()->{
        try(OutputStream out = new AsyncOutputStream(new BufferedOutputStream(new PipedOutputStream(snk)))) {
          try {
            logic.accept(out);
          } finally {
            onComplete.release();
          }
        } catch (IOException e) {
          e.printStackTrace();
          throw new RuntimeException(e);
        }
      });
      if(!async) try {
        onComplete.acquire();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      return NanoHTTPD.newChunkedResponse(Response.Status.OK, mimeType, new BufferedInputStream(snk));
    };
  }
  
  public StreamNanoHTTPD(int port, String mimeType, File file) throws IOException {
    super(port);
    this.file = file;
    this.mimeType = mimeType;
    try {
      this.gatewayUri = new URI(String.format("http://localhost:%s/%s", port, file.getName()));
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
    this.dataReciever = new TeeOutputStream(new FileOutputStream(file), true) {
      @Override
      public void close() throws IOException {
        try {
          Thread.sleep(100);
          StreamNanoHTTPD.this.stop();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    };
  }
  
  public StreamNanoHTTPD init() throws IOException {
    StreamNanoHTTPD.this.start();
    new Thread(() -> {
      try {
        Thread.sleep(100);
        Desktop.getDesktop().browse(gatewayUri);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }).start();
    return this;
  }
  
  public static OutputStream create(int port, File path, String mimeType) throws IOException {
    return new StreamNanoHTTPD(port, mimeType, path).init().dataReciever;
  }
  
  @Override
  public Response serve(IHTTPSession session) {
    String requestPath = session.getUri();
    while(requestPath.startsWith("/")) requestPath = requestPath.substring(1);
    if(requestPath.equals(file.getName())) {
      try {
        Response response = NanoHTTPD.newChunkedResponse(Response.Status.OK, mimeType, new BufferedInputStream(dataReciever.newInputStream()));
        response.setGzipEncoding(false);
        return response;
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    } else {
      File file = new File(this.file.getParent(), requestPath);
      if(customHandlers.containsKey(requestPath)) {
        return customHandlers.get(requestPath).apply(session);
      } else if(file.exists()) {
        try {
          return NanoHTTPD.newChunkedResponse(Response.Status.OK, null, new FileInputStream(file));
        } catch (FileNotFoundException e) {
          throw new RuntimeException(e);
        }
      } else {
        return NanoHTTPD.newFixedLengthResponse(Response.Status.NOT_FOUND, "text/plain", "Not Found");
      }
    }
  }
  
  @Override
  protected boolean useGzipWhenAccepted(Response r) {
    return false;
  }
}
