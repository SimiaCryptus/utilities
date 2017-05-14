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

import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.function.Supplier;

public interface NotebookOutput extends Closeable {
  
  default void out(String fmt, Object... args) {
    p(fmt, args);
  }
  
  void p(String fmt, Object... args);
  
  void h1(String fmt, Object... args);
  
  void h2(String fmt, Object... args);
  
  void h3(String fmt, Object... args);
  
  <T> T code(Supplier<T> fn, int maxLog, int framesNo);
  
  String image(BufferedImage rawImage, String caption) throws IOException;
  OutputStream file(String name);
  
  default <T> T code(Supplier<T> fn) {
    return code(fn, 8*1024, 3);
  }
  
  default void code(Runnable fn, int maxLog, int framesNo){
    this.code(()->{fn.run();return null;}, maxLog, framesNo);
  }
  
  default void code(Runnable fn){
    this.code(()->{fn.run();return null;}, 8*1024, 3);
  }
  
}
