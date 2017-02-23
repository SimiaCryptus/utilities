package com.simiacryptus.util.text;

import junit.framework.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.zip.Deflater;

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
  public void testDictionaryGeneration() throws IOException
  {
    String content = new Scanner(getClass().getClassLoader().getResourceAsStream("earthtomoon.txt")).useDelimiter("\\Z").next().replaceAll("[ \n\r\t]+", " ");
    List<String> sentances = Arrays.stream(content.split("\\.+")).map(line->line.trim() + ".").filter(line->line.length()>12).collect(Collectors.toList());

    int size = 16 * 1024;
    int sampleLength = 128;

    Map<String, Long> wordCounts = Arrays.stream(content.replaceAll("[^\\w\\s]","").split(" +")).map(s -> s.trim()).filter(s -> !s.isEmpty())
            .collect(Collectors.groupingBy(x -> x, Collectors.counting()));

    String commonTerms = wordCounts.entrySet().stream().sorted(Comparator.<Map.Entry<String, Long>>comparingLong(e -> -e.getValue())
            .thenComparing(Comparator.<Map.Entry<String, Long>>comparingLong(e -> -e.getKey().length())))
            .map(x -> x.getKey())
            .reduce((a,b)->a+" "+b).get().substring(0, size);
    System.out.println(String.format("Common Terms: \t%s\t%s", evaluateDictionary(sentances, commonTerms), commonTerms.substring(0, sampleLength)));

    for(int encodingPenalty = -8;encodingPenalty<8;encodingPenalty++) {
      int _encodingPenalty = encodingPenalty;
      String meritTerms = wordCounts.entrySet().stream().sorted(Comparator.<Map.Entry<String, Long>>comparingLong(e -> -e.getValue() * (e.getKey().length() - _encodingPenalty))
              .thenComparing(Comparator.<Map.Entry<String, Long>>comparingLong(e -> -e.getKey().length())))
              .map(x -> x.getKey())
              .reduce((a,b)->a+" "+b).get().substring(0, size);
      System.out.println(String.format("Merit Terms (%s): \t%s\t%s", encodingPenalty, evaluateDictionary(sentances, meritTerms), meritTerms.substring(0, sampleLength)));
    }

    String uncommonTerms = wordCounts.entrySet().stream().sorted(Comparator.<Map.Entry<String, Long>>comparingLong(e -> e.getValue())
            .thenComparing(Comparator.<Map.Entry<String, Long>>comparingLong(e -> e.getKey().length())))
            .map(x -> x.getKey())
            .reduce((a,b)->a+" "+b).get().substring(0, size);;
    System.out.println(String.format("Uncommon Terms: \t%s\t%s", evaluateDictionary(sentances, uncommonTerms), uncommonTerms.substring(0, sampleLength)));

    CharTree tree = new CharTree();
    long startTime  = System.currentTimeMillis();
    //sentances.stream().forEach(i->tree.addDocument(i));
    tree.addDocument(content);
    int maxLevels = 32;
    int minWeight = 0;
    tree.index(maxLevels, minWeight).truncate();
    long elapsed = System.currentTimeMillis() - startTime;
    System.out.println(String.format("Built index in time = %s sec",elapsed/1000.));
    System.out.println(String.format("tree.getIndexedSize = %s KB",tree.getIndexedSize() / 1024));
    System.out.println(String.format("tree.getMemorySize = %s KB",tree.getMemorySize() / 1024));

    for(int context = 0; context<maxLevels; context++) {
      CharTree copy = tree.copy();
      for(int attempt=0;attempt<5;attempt++) {
        String dictionary = copy.generateMarkov(size, context, ".");
        System.out.println(String.format("generateMarkov %s:\t%s\t%s", context, evaluateDictionary(sentances, dictionary),
                dictionary.substring(0, Math.min(sampleLength, dictionary.length()))));
      }
    }

    for(int lookahead=0;lookahead<5;lookahead++) {
      for(int context=0;context<maxLevels-lookahead;context++) {
        for(int attempt=0;attempt<2;attempt++) {
          {
            CharTree copy = tree.copy();
            {
              String dictionary = copy.generateDictionary(size, context, ".", lookahead, false);
              System.out.println(String.format("generateDictionary (nondestructive) %s,%s:\t%s\t%s", context, lookahead, evaluateDictionary(sentances, dictionary),
                      dictionary.substring(0, Math.min(sampleLength, dictionary.length()))));
            }
            {
              String dictionary = copy.generateDictionary(size, context, ".", lookahead, true);
              System.out.println(String.format("generateDictionary (destructive) %s,%s:\t%s\t%s", context, lookahead, evaluateDictionary(sentances, dictionary),
                      dictionary.substring(0, Math.min(sampleLength, dictionary.length()))));
            }
          }
          System.gc();
        }
      }
    }

  }

  private String evaluateDictionary(List<String> sentances, String dictionary) {
    int without = sentances.stream().mapToInt(line -> CharTree.compress("", line).length).sum();
    int with = sentances.stream().mapToInt(line -> CharTree.compress(dictionary, line).length).sum();
    return String.format("Size: %s kB\tControl: %s kB\tSaved: %s", with / 1024, without / 1024, without - with);
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
