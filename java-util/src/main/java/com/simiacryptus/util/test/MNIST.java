package com.simiacryptus.util.test;

import java.io.*;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.zip.GZIPInputStream;

import com.simiacryptus.util.ml.Tensor;

public class MNIST {

  private final static URI source = URI.create("http://yann.lecun.com/exdb/mnist/");
  public static Stream<byte[]> binaryStream(final String name, final int skip, final int recordSize) throws IOException {
    InputStream stream = null;
    try {
      stream = Spool.load(source.resolve(name));
    } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
      throw new RuntimeException(e);
    }
    byte[] fileData = org.apache.commons.io.IOUtils.toByteArray(new java.io.BufferedInputStream(new GZIPInputStream(new java.io.BufferedInputStream(stream))));
    final DataInputStream in = new DataInputStream(new java.io.ByteArrayInputStream(fileData));
    in.skip(skip);
    return toIterator(new BinaryChunkIterator(in, recordSize));
  }

  public static <T> Stream<T> toIterator(final Iterator<T> iterator) {
    return StreamSupport.stream(Spliterators.spliterator(iterator, 1, Spliterator.ORDERED), false);
  }

  public static Stream<LabeledObject<Tensor>> trainingDataStream() throws IOException {
    final Stream<Tensor> imgStream = binaryStream("train-images-idx3-ubyte.gz", 16, 28 * 28).map(b->{
      return fillImage(b, new Tensor(28,28,1));
    });
    final Stream<byte[]> labelStream = binaryStream("train-labels-idx1-ubyte.gz", 8, 1);

    final Stream<LabeledObject<Tensor>> merged = toStream(new Iterator<LabeledObject<Tensor>>() {
      Iterator<Tensor> imgItr = imgStream.iterator();
      Iterator<byte[]> labelItr = labelStream.iterator();

      @Override
      public boolean hasNext() {
        return this.imgItr.hasNext() && this.labelItr.hasNext();
      }

      @Override
      public LabeledObject<Tensor> next() {
        return new LabeledObject<Tensor>(this.imgItr.next(), Arrays.toString(this.labelItr.next()));
      }
    }, 100);
    return merged;
  }

  public static Tensor fillImage(final byte[] b, final Tensor tensor) {
    for (int x = 0; x < 28; x++) {
      for (int y = 0; y < 28; y++) {
        tensor.set(new int[] { x, y }, b[x + y * 28]&0xFF);
      }
    }
    return tensor;
  }

  public static <T> Stream<T> toStream(final Iterator<T> iterator, final int size) {
    return toStream(iterator, size, false);
  }

  public static <T> Stream<T> toStream(final Iterator<T> iterator, final int size, final boolean parallel) {
    return StreamSupport.stream(Spliterators.spliterator(iterator, size, Spliterator.ORDERED), parallel);
  }

  public static Stream<LabeledObject<Tensor>> validationDataStream() throws IOException {
    final Stream<Tensor> imgStream = binaryStream("t10k-images-idx3-ubyte.gz", 16, 28 * 28).map(b->{
      return fillImage(b, new Tensor(28,28,1));
    });
    final Stream<byte[]> labelStream = binaryStream("t10k-labels-idx1-ubyte.gz", 8, 1);

    final Stream<LabeledObject<Tensor>> merged = toStream(new Iterator<LabeledObject<Tensor>>() {
      Iterator<Tensor> imgItr = imgStream.iterator();
      Iterator<byte[]> labelItr = labelStream.iterator();

      @Override
      public boolean hasNext() {
        return this.imgItr.hasNext() && this.labelItr.hasNext();
      }

      @Override
      public LabeledObject<Tensor> next() {
        return new LabeledObject<Tensor>(this.imgItr.next(), Arrays.toString(this.labelItr.next()));
      }
    }, 100);
    return merged;
  }

}
