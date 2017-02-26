package com.simiacryptus.util.text;

import com.simiacryptus.util.data.DoubleStatistics;
import junit.framework.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CharTreeTest
{
  public static final File outPath = new File("src/site/resources/");
  public static final URL outBaseUrl = getUrl("https://simiacryptus.github.io/utilities/java-util/");

  public static URL getUrl(String url) {
    try {
      return new URL(url);
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

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
    ProjectorTableOutput output = new ProjectorTableOutput();
    String content = new Scanner(getClass().getClassLoader().getResourceAsStream("earthtomoon.txt")).useDelimiter("\\Z").next().replaceAll("[ \n\r\t]+", " ");
    List<String> sentances = Arrays.stream(content.split("\\.+")).map(line->line.trim() + ".").filter(line->line.length()>12).collect(Collectors.toList());

    int size = 32 * 1024;
    int sampleLength = 80;

    Map<String, Long> wordCounts = Arrays.stream(content.replaceAll("[^\\w\\s]","").split(" +")).map(s -> s.trim()).filter(s -> !s.isEmpty())
            .collect(Collectors.groupingBy(x -> x, Collectors.counting()));

    {
      String commonTerms = wordCounts.entrySet().stream().sorted(Comparator.<Map.Entry<String, Long>>comparingLong(e -> -e.getValue())
              .thenComparing(Comparator.<Map.Entry<String, Long>>comparingLong(e -> -e.getKey().length())))
              .map(x -> x.getKey())
              .reduce((a,b)->a+" "+b).get().substring(0, size);
      Map<String, Object> map = new LinkedHashMap<>();
      map.put("type","CommonTerm");
      evaluateDictionary(sentances, commonTerms, map);
      map.put("sampleTxt",commonTerms.substring(0, sampleLength));
      output.putRow(map);
    }

    System.out.println(output.toTextTable());
    output = new ProjectorTableOutput();

    for(int encodingPenalty = -4;encodingPenalty<4;encodingPenalty++) {
      int _encodingPenalty = encodingPenalty;
      String meritTerms = wordCounts.entrySet().stream().sorted(Comparator.<Map.Entry<String, Long>>comparingLong(e -> -e.getValue() * (e.getKey().length() - _encodingPenalty))
              .thenComparing(Comparator.<Map.Entry<String, Long>>comparingLong(e -> -e.getKey().length())))
              .map(x -> x.getKey())
              .reduce((a,b)->a+" "+b).get().substring(0, size);
      Map<String, Object> map = new LinkedHashMap<>();
      map.put("type","MeritTerm");
      map.put("encodingPenalty",encodingPenalty);
      evaluateDictionary(sentances, meritTerms, map);
      map.put("sampleTxt",meritTerms.substring(0, sampleLength));
      output.putRow(map);
    }

    System.out.println(output.toTextTable());
    output = new ProjectorTableOutput();

    {
      String uncommonTerms = wordCounts.entrySet().stream().sorted(Comparator.<Map.Entry<String, Long>>comparingLong(e -> e.getValue())
              .thenComparing(Comparator.<Map.Entry<String, Long>>comparingLong(e -> e.getKey().length())))
              .map(x -> x.getKey())
              .reduce((a,b)->a+" "+b).get().substring(0, size);;
      Map<String, Object> map = new LinkedHashMap<>();
      map.put("type","UncommonTerm,");
      evaluateDictionary(sentances, uncommonTerms, map);
      map.put("sampleTxt",uncommonTerms.substring(0, sampleLength));
      output.putRow(map);
    }

    System.out.println(output.toTextTable());
    output = new ProjectorTableOutput();

    CharTree tree = new CharTree();
    long startTime  = System.currentTimeMillis();
    //sentances.stream().forEach(i->tree.addDocument(i));
    tree.addDocument(content);
    int maxLevels = 10;
    int minWeight = 0;
    tree.index(maxLevels, minWeight).truncate();
    long elapsed = System.currentTimeMillis() - startTime;
    System.out.println(String.format("Built index in time = %s sec",elapsed/1000.));
    System.out.println(String.format("tree.getIndexedSize = %s KB",tree.getIndexedSize() / 1024));
    System.out.println(String.format("tree.getMemorySize = %s KB",tree.getMemorySize() / 1024));

    for(int context = 0; context<maxLevels; context++) {
      CharTree copy = tree.copy();
      for(int attempt=0;attempt<1;attempt++) {
        String dictionary = copy.generateMarkov2(size, context, ".", 1.0);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("type","generateMarkov2");
        map.put("context",context);
        evaluateDictionary(sentances, dictionary, map);
        map.put("sampleTxt",dictionary.substring(0, Math.min(sampleLength, dictionary.length())));
        output.putRow(map);
      }
    }

    System.out.println(output.toTextTable());
    output = new ProjectorTableOutput();

    for(int context = 0; context<maxLevels; context++) {
      CharTree copy = tree.copy();
      for(int attempt=0;attempt<1;attempt++) {
        String dictionary = copy.generateMarkov(size, context, ".");
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("type","generateMarkov");
        map.put("context",context);
        evaluateDictionary(sentances, dictionary, map);
        map.put("sampleTxt",dictionary.substring(0, Math.min(sampleLength, dictionary.length())));
        output.putRow(map);
      }
    }

    System.out.println(output.toTextTable());
    output = new ProjectorTableOutput();

    for(int lookahead=0;lookahead<3;lookahead++) {
      for(int context=0;context<maxLevels-lookahead;context++) {
        for(int attempt=0;attempt<1;attempt++) {
          String dictionary = tree.copy().generateDictionary(size, context, ".", lookahead, true);
          Map<String, Object> map = new LinkedHashMap<>();
          map.put("type","generateDictionary1");
          map.put("context",context);
          map.put("lookahead",lookahead);
          evaluateDictionary(sentances, dictionary, map);
          map.put("sampleTxt",dictionary.substring(0, Math.min(sampleLength, dictionary.length())));
          output.putRow(map);
          System.gc();
        }
      }
    }

    System.out.println(output.toTextTable());
    output = new ProjectorTableOutput();

    for(int lookahead=0;lookahead<3;lookahead++) {
      for(int context=0;context<maxLevels-lookahead;context++) {
        String dictionary = tree.generateDictionary(size, context, ".", lookahead, false);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("type","generateDictionary2");
        map.put("context",context);
        map.put("lookahead",lookahead);
        evaluateDictionary(sentances, dictionary, map);
        map.put("sampleTxt",dictionary.substring(0, Math.min(sampleLength, dictionary.length())));
        output.putRow(map);
      }
    }

    System.out.println(output.toTextTable());
    output = new ProjectorTableOutput();
  }


  @Test
  public void testCrossEntropy() throws IOException
  {
    Map<String,String> content = new HashMap<>();
    for(String name : Arrays.asList("earthtomoon.txt", "20000leagues.txt", "macbeth.txt", "randj.txt")) {
      content.put(name, new Scanner(getClass().getClassLoader().getResourceAsStream(name)).useDelimiter("\\Z").next().replaceAll("[ \n\r\t]+", " "));
    }

    String characterSet = content.values().stream().flatMapToInt(s -> s.chars())
            .distinct().mapToObj(c -> new String(Character.toChars(c))).sorted()
            .collect(Collectors.joining(""));
    System.out.println("Character Set:" + characterSet);
    int maxLevels = 5;
    int minWeight = 1;
    double smoothness = 1.0;
    int sampleSize = 64 * 1024;

    long startTime  = System.currentTimeMillis();
    Map<String,CharTree> trees = new HashMap<>();
    Map<String,String> dictionaries = new HashMap<>();
    for(Map.Entry<String, String> e : content.entrySet()) {
      CharTree tree = new CharTree();
      tree.addDocument(characterSet);
      tree.addDocument(e.getValue());
      tree.index(maxLevels, minWeight).truncate();
      trees.put(e.getKey(),tree);
      dictionaries.put(e.getKey(),tree.copy().generateDictionary(16 * 1024, 5,"", 1, true));
      System.out.println(String.format("Indexing %s; \ntree.getIndexedSize = %s KB", e.getKey(), tree.getIndexedSize() / 1024));
      System.out.println(String.format("tree.getMemorySize = %s KB",tree.getMemorySize() / 1024));
    }
    long elapsed = System.currentTimeMillis() - startTime;
    System.out.println(String.format("Built index in time = %s sec",elapsed/1000.));

    System.out.println("\nMCMC Similarity Measures:");
    ProjectorTableOutput output1 = new ProjectorTableOutput();
    for(Map.Entry<String, CharTree> ea : trees.entrySet()) {
      for(Map.Entry<String, CharTree> eb : trees.entrySet()) {
        String str = ea.getValue().generateMarkov(sampleSize, maxLevels - 1, "");
        Double crossEntropy = eb.getValue().encodingBits(str, smoothness) / str.length();
        HashMap<String, Object> map = new HashMap<>();
        map.put("source", ea.getKey());
        map.put("model", eb.getKey());
        map.put("crossEntropy", crossEntropy);
        output1.putRow(map);
      }
    }
    System.out.println(output1.toTextTable());

//    System.out.println("\nFull Text Cross Encoding Measures:");
//    ProjectorTableOutput output2 = new ProjectorTableOutput();
//    for(Map.Entry<String, CharTree> ea : trees.entrySet()) {
//      for(Map.Entry<String, String> eb : content.entrySet()) {
//        Double bitsPerByte = ea.getValue().encodingBits(eb.getValue(), smoothness) / eb.getValue().length();
//        System.out.println(String.format("%20s\t%20s\t%.3f", ea.getKey(), eb.getKey(), bitsPerByte));
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("model", ea.getKey());
//        map.put("source", eb.getKey());
//        map.put("bitsPerByte", bitsPerByte);
//        output2.putRow(map);
//      }
//    }
//    System.out.println(output2.toTextTable());

  }
//
//  @Test
//  public void testWikiEncoding() throws IOException, URISyntaxException {
//    WikiArticle.load().forEach(article->{
//      System.out.println(String.format("Title: %s", article.title));
//    });
//  }

  @Test
  public void testEncodingCoords() throws IOException
  {
    Map<String,String> content = new HashMap<>();
    for(String name : Arrays.asList("earthtomoon.txt", "20000leagues.txt", "macbeth.txt", "randj.txt")) {
      content.put(name, new Scanner(getClass().getClassLoader().getResourceAsStream(name)).useDelimiter("\\Z").next().replaceAll("[ \n\r\t]+", " "));
    }
    String allContent = content.values().stream().collect(Collectors.joining("\n"));
    List<String> sentances = Arrays.stream(allContent.split("\\.+")).map(line->line.trim() + ".").filter(line->line.length()>12).collect(Collectors.toList());
    Collections.shuffle(sentances);

    int maxLevels = 7;
    int minWeight = 1;
    double smoothness = 1.0;

    long startTime  = System.currentTimeMillis();
    Map<String,CharTree> trees = new HashMap<>();
    Map<String,String> dictionaries = new HashMap<>();
    for(Map.Entry<String, String> e : content.entrySet()) {
      CharTree tree = new CharTree();
      tree.addDocument(e.getValue());
      tree.index(maxLevels, minWeight).truncate();
      trees.put(e.getKey(),tree);
      dictionaries.put(e.getKey(),tree.copy().generateDictionary(16 * 1024, 5,"", 1, true));
      System.out.println(String.format("Indexing %s; \ntree.getIndexedSize = %s KB", e.getKey(), tree.getIndexedSize() / 1024));
      System.out.println(String.format("tree.getMemorySize = %s KB",tree.getMemorySize() / 1024));
    }
    long elapsed = System.currentTimeMillis() - startTime;
    System.out.println(String.format("Built index in time = %s sec",elapsed/1000.));

    ProjectorTableOutput output1 = new ProjectorTableOutput();
    ProjectorTableOutput output2 = new ProjectorTableOutput();
    sentances.stream().limit(1000).forEach(s -> {
      HashMap<String, Object> map2 = new LinkedHashMap<>();
      map2.put("rawBits", s.length() * 8);
      map2.put("decompressBits0", CharTree.compress("", s).length * 8);
      content.keySet().stream().forEach(key->{
        CharTree tree = trees.get(key);
        String dictionary = dictionaries.get(key);
        HashMap<String, Object> map1 = new LinkedHashMap<>();
        map1.put("key", key);
        map1.put("rawBits", s.length() * 8);
        map1.put("decompressBits0", CharTree.compress("", s).length * 8);
        int decompressBits1 = CharTree.compress(dictionary.substring(0, 1024), s).length * 8;
        map1.put(".decompressBits1", decompressBits1);
        map2.put(key+".decompressBits1", decompressBits1);
        int decompressBits4 = CharTree.compress(dictionary.substring(0, 4 * 1024), s).length * 8;
        map1.put(".decompressBits4", decompressBits4);
        map2.put(key+".decompressBits4", decompressBits4);
        int decompressBits16 = CharTree.compress(dictionary.substring(0, 16 * 1024), s).length * 8;
        map1.put(".decompressBits16", decompressBits16);
        map2.put(key+".decompressBits16", decompressBits16);
        double ppmBits = tree.encodingBits(s, smoothness);
        map1.put(".ppmBits", ppmBits);
        map2.put(key+".ppmBits", ppmBits);
        map1.put(".bitsPerChar", ppmBits / s.length());
        map2.put(key+".bitsPerChar", ppmBits / s.length());
        map1.put("txt", s.substring(0,Math.min(s.length(), 120)));
        output1.putRow(map1);
      });
      map2.put("txt", s);
      output2.putRow(map2);
    });
    System.out.println(output1.toTextTable());
    String outputDirName = "sentenceClassification/";
    output2.writeProjectorData(new File(outPath, outputDirName), new URL(outBaseUrl, outputDirName));
  }


  private Map<String, Object> evaluateDictionary(List<String> sentances, String dictionary, Map<String, Object> map) {
    Arrays.asList(1, 4, 16, 32).stream().forEach(k -> {
      DoubleStatistics statistics = sentances.stream().map(line -> {
        int length0 = CharTree.compress("", line).length;
        int lengthK = CharTree.compress(dictionary.substring(0, Math.min(k * 1024, dictionary.length())), line).length;
        return (length0 - lengthK) * 1.0;
      }).collect(DoubleStatistics.COLLECTOR);
      map.put(String.format("%sk.sum",k), (int) statistics.getSum() / 1024);
      map.put(String.format("%sk.avg",k), (int) statistics.getAverage());
      map.put(String.format("%sk.stddev",k), (int) statistics.getStandardDeviation());
    });
    return map;
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
