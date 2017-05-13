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

package com.simiacryptus.util.test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Supplier;

public class SysOutInterceptor extends PrintStream {
  
  public static final SysOutInterceptor INSTANCE = init();
  private ThreadLocal<PrintStream> threadHandler = new ThreadLocal<PrintStream>() {
    @Override
    protected PrintStream initialValue() {
      return (PrintStream) out;
    }
  };
  
  public SysOutInterceptor(PrintStream out) {
    super(out);
  }
  
  public static <T> LoggedResult<T> withOutput(Supplier<T> fn) {
    //init();
    try {
      ByteArrayOutputStream buff = new ByteArrayOutputStream();
      try (PrintStream ps = new PrintStream(buff)) {
        INSTANCE.threadHandler.set(ps);
        T result = fn.get();
        return new LoggedResult<T>(result, buff.toString());
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      INSTANCE.threadHandler.remove();
    }
  }
  
  public static LoggedResult<Void> withOutput(Runnable fn) {
    try {
      ByteArrayOutputStream buff = new ByteArrayOutputStream();
      try (PrintStream ps = new PrintStream(buff)) {
        INSTANCE.threadHandler.set(ps);
        fn.run();
        return new LoggedResult<Void>(null, buff.toString());
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      INSTANCE.threadHandler.remove();
    }
  }
  
  private static SysOutInterceptor init() {
    if (!(System.out instanceof SysOutInterceptor)) {
      SysOutInterceptor out = new SysOutInterceptor(System.out);
      System.setOut(out);
      return out;
    }
    return (SysOutInterceptor) System.out;
  }
  
  @Override
  public void print(String s) {
    threadHandler.get().print(s);
  }
  
  @Override
  public void println(String x) {
    threadHandler.get().println(x);
  }
  
  public static class LoggedResult<T> {
    public final T obj;
    public final String log;
    
    public LoggedResult(T obj, String log) {
      this.obj = obj;
      this.log = log;
    }
  }
}
