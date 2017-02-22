package com.simiacryptus.util.bitset;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import com.simiacryptus.util.binary.BitInputStream;
import com.simiacryptus.util.binary.BitOutputStream;
import com.simiacryptus.util.binary.Bits;

public class RunLengthBitsCollection extends
    BitsCollection<HashMap<Bits, AtomicInteger>>
{
  public RunLengthBitsCollection(final int bitDepth)
  {
    super(bitDepth, new HashMap<Bits, AtomicInteger>());
  }
  
  @Override
  public void read(final BitInputStream in) throws IOException
  {
    final int size = (int) in.read(32).toLong();
    for (int i = 0; i < size; i++)
    {
      final Bits bits = in.read(this.bitDepth);
      final int count = (int) in.read(32).toLong();
      this.map.put(bits, new AtomicInteger(count));
    }
  }
  
  @Override
  public void write(final BitOutputStream out) throws IOException
  {
    out.write(new Bits(this.getList().size(), 32));
    for (final Entry<Bits, AtomicInteger> e : this.map.entrySet())
    {
      out.write(e.getKey());
      out.write(new Bits(e.getValue().get(), 32));
    }
  }
  
}