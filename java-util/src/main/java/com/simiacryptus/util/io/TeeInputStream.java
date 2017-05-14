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

package com.simiacryptus.util.io;

import javax.net.ssl.*;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;


public class TeeInputStream extends InputStream {
  
  private final InputStream inputStream;
  private final OutputStream cache;
  
  
  public TeeInputStream(InputStream inputStream, OutputStream cache) {
    this.inputStream = inputStream;
    this.cache = cache;
  }
  
  @Override
  public int read() throws IOException {
    int read = inputStream.read();
    cache.write(read);
    return read;
  }
  
  @Override
  public int read(byte[] b) throws IOException {
    int read = inputStream.read(b);
    if (read > 0) cache.write(b);
    return read;
  }
  
  @Override
  public int read(byte[] b, int off, int len) throws IOException {
    int read = inputStream.read(b, off, len);
    if (read > 0) cache.write(b, off, read);
    return read;
  }
  
  @Override
  public int available() throws IOException {
    return inputStream.available();
  }
  
  @Override
  public void close() throws IOException {
    inputStream.close();
    cache.close();
  }
}
