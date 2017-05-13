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

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class BinaryChunkIterator implements Iterator<byte[]> {
  
  private DataInputStream in;
  private int recordSize;
  
  public BinaryChunkIterator(final DataInputStream in, final int recordSize) {
    super();
    this.in = in;
    this.recordSize = recordSize;
  }
  
  public static byte[] read(final DataInputStream i, final int s) throws IOException {
    final byte[] b = new byte[s];
    int pos = 0;
    while (b.length > pos) {
      final int read = i.read(b, pos, b.length - pos);
      if (0 == read)
        throw new RuntimeException();
      pos += read;
    }
    return b;
  }
  
  public static <T> Stream<T> toIterator(final Iterator<T> iterator) {
    return StreamSupport.stream(Spliterators.spliterator(iterator, 1, Spliterator.ORDERED), false);
  }
  
  public static <T> Stream<T> toStream(final Iterator<T> iterator) {
    return toStream(iterator, 0);
  }
  
  public static <T> Stream<T> toStream(final Iterator<T> iterator, final int size) {
    return toStream(iterator, size, false);
  }
  
  public static <T> Stream<T> toStream(final Iterator<T> iterator, final int size, final boolean parallel) {
    return StreamSupport.stream(Spliterators.spliterator(iterator, size, Spliterator.ORDERED), parallel);
  }
  
  @Override
  public boolean hasNext() {
    try {
      return 0 < this.in.available();
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  @Override
  public byte[] next() {
    assert hasNext();
    try {
      return read(this.in, this.recordSize);
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  public Stream<byte[]> toStream() {
    return toStream(this);
  }
}
