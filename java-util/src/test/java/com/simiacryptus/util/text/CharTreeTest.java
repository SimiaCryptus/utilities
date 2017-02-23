package com.simiacryptus.util.text;

import junit.framework.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CharTreeTest
{

  @Test
  public void testFunctionality() throws IOException
  {
    CharTree tree = new CharTree()
      .addDocument("a quick brown fox jumped over the lazy dog")
      .addDocument("this is a test. this is only a test. - nikola tesla")
      .index(3).truncate();
    Assert.assertEquals(7, tree.traverse("t").getCursorCount());
    Assert.assertEquals("t", tree.traverse("t").getString());
    Assert.assertEquals("te", tree.traverse("te").getString());
    Assert.assertEquals(3, tree.traverse("te").getCursorCount());
    Assert.assertEquals(1, tree.traverse("dog").getCursorCount());
    Assert.assertEquals("do", tree.traverse("dog").getString());
    Assert.assertEquals(1, tree.traverse("do").getCursorCount());
    Assert.assertEquals(6, tree.traverse("o").getCursorCount());
    Assert.assertEquals(3, tree.traverse("test").getCursorCount());
    Assert.assertEquals("tes", tree.traverse("test").getString());
  }

  @Test
  public void testPerformance() throws IOException
  {
    CharTree tree = new CharTree();
    IntStream.range(0, 30000).parallel().forEach(i->tree.addDocument(UUID.randomUUID().toString()));
    tree.index();
    System.out.println(String.format("tree.getIndexedSize = %s",tree.getIndexedSize()));
    System.out.println(String.format("tree.getMemorySize = %s",tree.getMemorySize()));
    System.out.println(String.format("tree.truncate.getMemorySize = %s",tree.truncate().getMemorySize()));
  }

  @Test
  public void testPerformanceMatrix() throws IOException
  {
    for(int count=100;count<50000;count*=2) {
      for(int maxLevels=1;maxLevels<64;maxLevels=Math.max((int)(maxLevels*4),maxLevels+1)) {
        for(int minWeight=1;minWeight<64;minWeight*=4) {
          testRow(maxLevels, minWeight, IntStream.range(0, count).parallel().mapToObj(i -> UUID.randomUUID().toString()));
        }
      }
    }
  }

  private void testRow(int maxLevels, int minWeight, Stream<String> documents) {
    CharTree tree = new CharTree();
    long startTime  = System.currentTimeMillis();
    documents.forEach(i->tree.addDocument(i));
    tree.index(maxLevels, minWeight);
    long elapsed = System.currentTimeMillis() - startTime;
    System.out.println(String.format("%s\t%s\t%s KB\t%s sec\t%s KB\t%s KB",
            maxLevels, minWeight, tree.getIndexedSize() / 1024, elapsed/1000., tree.getMemorySize() / 1024, tree.truncate().getMemorySize() / 1024));
  }

}
