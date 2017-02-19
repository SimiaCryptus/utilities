package com.simiacryptus.bitset;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import junit.framework.Assert;

import org.junit.Test;

import com.google.common.collect.HashMultiset;
import com.simiacryptus.binary.BitInputStream;
import com.simiacryptus.binary.BitOutputStream;
import com.simiacryptus.binary.Bits;
import com.simiacryptus.bitset.BitsCollection;
import com.simiacryptus.bitset.CountTreeBitsCollection;
import com.simiacryptus.bitset.RunLengthBitsCollection;
import com.simiacryptus.util.DataTest;
import com.simiacryptus.util.SerializableObjectTester;
import com.simiacryptus.util.TestUtil;

public class BitsCollectionTest
{
  public static abstract class BitsCollectionTester<T extends BitsCollection<?>>
      extends SerializableObjectTester<T>
  {
    
    @Override
    public T deserialize(final byte[] data) throws IOException
    {
      final T actual = this.newObject();
      final ByteArrayInputStream inBuffer = new ByteArrayInputStream(data);
      final BitInputStream in = new BitInputStream(inBuffer);
      actual.read(in);
      return actual;
    }
    
    @Override
    public abstract T newObject();
    
    @Override
    public byte[] serialize(final T object) throws IOException
    {
      final ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
      final BitOutputStream out = new BitOutputStream(outBuffer);
      object.write(out);
      out.flush();
      final byte[] serializedData = outBuffer.toByteArray();
      return serializedData;
    }
    
    @Override
    public boolean testEquality(final T expected, final T actual)
    {
      TestUtil.assertEqual(HashMultiset.create(expected.getList()),
          HashMultiset.create(actual.getList()));
      return true;
    }
  }
  
  public static final class CountTreeBitsCollectionTester extends
      BitsCollectionTester<CountTreeBitsCollection>
  {
    private final int length;
    
    public CountTreeBitsCollectionTester(final int length)
    {
      this.length = length;
    }
    
    @Override
    public CountTreeBitsCollection newObject()
    {
      return new CountTreeBitsCollection(this.length);
    }
    
    @Override
    public String toString()
    {
      return "CountTreeBitsCollectionTester [length=" + this.length + "]";
    }
    
  }

  public final class RandomBitsTest extends RandomDataTest
  {
    private final int count;
    private final int bitLength;
    
    public RandomBitsTest(final int count, final int bitLength)
    {
      this.count = count;
      this.bitLength = bitLength;
    }
    
    @Override
    protected <T extends BitsCollection<?>> void fill(final T expected)
    {
      for (int i = 0; i < this.count; i++)
      {
        expected.add(new Bits(
            BitsCollectionTest.this.random,
            this.bitLength));
      }
    }
    
    @Override
    public String toString()
    {
      return "RandomBitsTest [count=" + this.count + ", bitLength="
          + this.bitLength + "]";
    }
    
  }
  
  public abstract class RandomDataTest implements DataTest<BitsCollection<?>>
  {
    protected abstract <T extends BitsCollection<?>> void fill(final T expected);
    
    @Override
    public <T extends BitsCollection<?>> void run(
        final SerializableObjectTester<T> tester) throws Exception
    {
      final T expected = tester.newObject();
      this.fill(expected);
      final byte[] serializedData = tester.serialize(expected);
      final T actual = tester.deserialize(serializedData);
      
      final int numberOfPoints = expected.getList().size();
      System.out.println(String.format("Serialized %s points to %s bytes",
          numberOfPoints, serializedData.length));
      final int naiveSize = numberOfPoints * expected.bitDepth / 8;
      System.out.println(String.format(
          "Naive length: %s bytes; %.3f%% compression", naiveSize, 100.
              * (naiveSize - serializedData.length) / naiveSize));
      
      Assert.assertTrue(tester.testEquality(expected, actual));
    }
    
  }
  
  public static final class RunLengthBitsCollectionTester extends
      BitsCollectionTester<RunLengthBitsCollection>
  {
    private final int length;
    
    public RunLengthBitsCollectionTester(final int length)
    {
      this.length = length;
    }
    
    @Override
    public RunLengthBitsCollection newObject()
    {
      return new RunLengthBitsCollection(this.length);
    }
    
    @Override
    public String toString()
    {
      return "RunLengthBitsCollectionTester [length=" + this.length + "]";
    }
    
  }
  
  public static <T> void testMatrix(
      final List<SerializableObjectTester<? extends T>> testers,
      final List<DataTest<T>> tests) throws Exception
  {
    for (final DataTest<T> test : tests)
    {
      for (final SerializableObjectTester<? extends T> tester : testers)
      {
        System.out
            .println(String.format("Testing %s against %s", tester, test));
        test.run(tester);
        System.out.println("--------\n");
      }
    }
  }
  
  Random random = new Random();
  
  @Test
  public void fixedLengthTests24() throws Exception
  {
    final int bitLength = 24;
    final int count = 10000;
    
    final List<SerializableObjectTester<? extends BitsCollection<?>>> testers = new ArrayList<SerializableObjectTester<? extends BitsCollection<?>>>();
    testers.add(new CountTreeBitsCollectionTester(bitLength));
    // testers.add(new RunLengthBitsCollectionTester(bitLength));
    
    final List<DataTest<BitsCollection<?>>> tests = new ArrayList<DataTest<BitsCollection<?>>>();
    tests.add(new RandomBitsTest(count, bitLength));

    testMatrix(testers, tests);
  }
  
  @Test
  public void fixedLengthTests48() throws Exception
  {
    final int bitLength = 48;
    final int count = 10000;
    
    final List<SerializableObjectTester<? extends BitsCollection<?>>> testers = new ArrayList<SerializableObjectTester<? extends BitsCollection<?>>>();
    testers.add(new CountTreeBitsCollectionTester(bitLength));
    // testers.add(new RunLengthBitsCollectionTester(bitLength));
    
    final List<DataTest<BitsCollection<?>>> tests = new ArrayList<DataTest<BitsCollection<?>>>();
    tests.add(new RandomBitsTest(count, bitLength));

    
    testMatrix(testers, tests);
  }
  
}
