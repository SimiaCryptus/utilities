package com.simiacryptus.util.binary;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.function.Consumer;

public class BitOutputStream
{

  public static Bits toBits(Consumer<BitOutputStream> fn) {
    try {
      ByteArrayOutputStream buffer = new ByteArrayOutputStream();
      BitOutputStream out = new BitOutputStream(buffer);
      fn.accept(out);
      out.flush();
      return new Bits(buffer.toByteArray(), out.getTotalBitsWritten());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  private OutputStream inner;
  
  private Bits         remainder       = Bits.NULL;
  
  static final int     varLongDepths[] = { 6, 16, 32, 64 };
  private int totalBitsWritten = 0;

  public BitOutputStream(final OutputStream inner)
  {
    this.inner = inner;
  }

  public int getTotalBitsWritten() throws IOException
  {
    return totalBitsWritten;
  }

  public synchronized void flush() throws IOException
  {
    this.inner.write(this.remainder.getBytes());
    this.inner.flush();
    this.remainder = Bits.NULL;
  }
  
  public synchronized void write(final Bits bits) throws IOException
  {
    Bits newRemainder = this.remainder.concatenate(bits);
    final int newRemainingBits = newRemainder.bitLength % 8;
    int bitsToWrite = newRemainder.bitLength - newRemainingBits;
    if(bitsToWrite > 0) {
      assert(0 == bitsToWrite % 8);
      final Bits toWrite = newRemainder.range(0, bitsToWrite);
      this.inner.write(toWrite.getBytes());
      newRemainder = newRemainder.range(bitsToWrite);
    }
    this.remainder = newRemainder;
    this.totalBitsWritten += bits.bitLength;
  }
  
  public void write(final boolean b) throws IOException
  {
    this.write(new Bits(b ? 1l : 0l, 1));
  }
  
  public void write(final double value) throws IOException
  {
    this.write(new Bits(Double.doubleToLongBits(value), 64));
  }
  
  public <T extends Enum<T>> void write(final Enum<T> value) throws IOException
  {
    final long ordinal = value.ordinal();
    this.write(new Bits(ordinal, 8));
  }
  
  public void write(final int value) throws IOException
  {
    this.write(new Bits(value, 32));
  }
  
  public void writeBoundedLong(final long value, final long max)
      throws IOException
  {
    final int bits = 1 >= max ? 0 : (int) Math
        .ceil(Math.log(max) / Math.log(2));
    if (0 < bits)
    {
      this.write(new Bits(value, bits));
    }
  }
  
  public void writeVarLong(final long value) throws IOException
  {
    final int bitLength = new Bits(value).bitLength;
    int type = Arrays.binarySearch(varLongDepths, bitLength);
    if (type < 0)
    {
      type = -type - 1;
    }
    this.write(new Bits(type, 2));
    this.write(new Bits(value, varLongDepths[type]));
  }
  
}
