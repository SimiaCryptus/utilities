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

package com.simiacryptus.util.data;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface SerialType<T> {
  default SerialArrayList<T> newList() {
    return new SerialArrayList<T>(this);
  }

  ;

  default SerialArrayList<T> newList(int size) {
    return new SerialArrayList<T>(this, size);
  }

  ;

  default SerialArrayList<T> newList(T... items) {
    return new SerialArrayList<T>(this, items);
  }

  ;

  int getSize();

  T read(ByteBuffer input) throws IOException;

  default T read(byte[] input) throws IOException {
    assert (input.length == getSize());
    return read(ByteBuffer.wrap(input));
  }

  void write(ByteBuffer output, T value) throws IOException;

  default byte[] write(T value) throws IOException {
    byte[] buffer = new byte[getSize()];
    write(ByteBuffer.wrap(buffer), value);
    return buffer;
  }
}
