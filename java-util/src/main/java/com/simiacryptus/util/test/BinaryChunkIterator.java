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

  public Stream<byte[]> toStream() {
    return toStream(this);
  }
}
