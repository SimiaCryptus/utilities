package com.simiacryptus.util.text;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

import com.simiacryptus.util.data.DoubleStatistics;
import com.simiacryptus.util.test.TweetSentiment;
import com.simiacryptus.util.test.WikiArticle;

import junit.framework.Assert;

public class CharTreeTest {
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
  public void testFunctionality() throws IOException {
    CharTree tree = new CharTree().addDocument("a quick brown fox jumped over the lazy dog")
        .addDocument("this is a test. this is only a test. - nikola tesla").index(3).truncate();
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
  public void testPerformance() throws IOException {
    CharTree tree = new CharTree();
    IntStream.range(0, 30000).parallel().forEach(i -> tree.addDocument(UUID.randomUUID().toString()));
    tree.index();
    System.out.println(String.format("tree.getIndexedSize = %s", tree.getIndexedSize()));
    System.out.println(String.format("tree.getMemorySize = %s", tree.getMemorySize()));
    System.out.println(String.format("tree.truncate.getMemorySize = %s", tree.truncate().getMemorySize()));
  }

  @Test
  public void testDictionaryGenerationMetaParameters() throws IOException {
    TableOutput output = new TableOutput();
    final String content;
    try (Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream("earthtomoon.txt"))) {
      content = scanner.useDelimiter("\\Z").next().replaceAll("[ \n\r\t]+", " ");
    }
    List<String> sentances = Arrays.stream(content.split("\\.+")).map(line -> line.trim() + ".")
        .filter(line -> line.length() > 12).collect(Collectors.toList());

    int size = 32 * 1024;
    int sampleLength = 80;

    Map<String, Long> wordCounts = Arrays.stream(content.replaceAll("[^\\w\\s]", "").split(" +")).map(s -> s.trim())
        .filter(s -> !s.isEmpty()).collect(Collectors.groupingBy(x -> x, Collectors.counting()));

    {
      String commonTerms = wordCounts.entrySet().stream()
          .sorted(Comparator.<Map.Entry<String, Long>>comparingLong(e -> -e.getValue())
              .thenComparing(Comparator.<Map.Entry<String, Long>>comparingLong(e -> -e.getKey().length())))
          .map(x -> x.getKey()).reduce((a, b) -> a + " " + b).get().substring(0, size);
      Map<String, Object> map = new LinkedHashMap<>();
      map.put("type", "CommonTerm");
      evaluateDictionary(sentances, commonTerms, map);
      map.put("sampleTxt", commonTerms.substring(0, sampleLength));
      output.putRow(map);
    }

    System.out.println(output.toTextTable());
    output = new TableOutput();

    for (int encodingPenalty = -4; encodingPenalty < 4; encodingPenalty++) {
      int _encodingPenalty = encodingPenalty;
      String meritTerms = wordCounts.entrySet().stream()
          .sorted(Comparator.<Map.Entry<String, Long>>comparingLong(
              e -> -e.getValue() * (e.getKey().length() - _encodingPenalty))
              .thenComparing(Comparator.<Map.Entry<String, Long>>comparingLong(e -> -e.getKey().length())))
          .map(x -> x.getKey()).reduce((a, b) -> a + " " + b).get().substring(0, size);
      Map<String, Object> map = new LinkedHashMap<>();
      map.put("type", "MeritTerm");
      map.put("encodingPenalty", encodingPenalty);
      evaluateDictionary(sentances, meritTerms, map);
      map.put("sampleTxt", meritTerms.substring(0, sampleLength));
      output.putRow(map);
    }

    System.out.println(output.toTextTable());
    output = new TableOutput();

    {
      String uncommonTerms = wordCounts.entrySet().stream()
          .sorted(Comparator.<Map.Entry<String, Long>>comparingLong(e -> e.getValue())
              .thenComparing(Comparator.<Map.Entry<String, Long>>comparingLong(e -> e.getKey().length())))
          .map(x -> x.getKey()).reduce((a, b) -> a + " " + b).get().substring(0, size);
      Map<String, Object> map = new LinkedHashMap<>();
      map.put("type", "UncommonTerm,");
      evaluateDictionary(sentances, uncommonTerms, map);
      map.put("sampleTxt", uncommonTerms.substring(0, sampleLength));
      output.putRow(map);
    }

    System.out.println(output.toTextTable());
    output = new TableOutput();

    CharTree tree = new CharTree();
    long startTime = System.currentTimeMillis();
    // sentances.stream().forEach(i->tree.addDocument(i));
    tree.addDocument(content);
    int maxLevels = 10;
    int minWeight = 0;
    tree.index(maxLevels, minWeight).truncate();
    long elapsed = System.currentTimeMillis() - startTime;
    System.out.println(String.format("Built index in time = %s sec", elapsed / 1000.));
    System.out.println(String.format("tree.getIndexedSize = %s KB", tree.getIndexedSize() / 1024));
    System.out.println(String.format("tree.getMemorySize = %s KB", tree.getMemorySize() / 1024));

    for (int context = 0; context < maxLevels; context++) {
      CharTree copy = tree.copy();
      for (int attempt = 0; attempt < 1; attempt++) {
        String dictionary = copy.codec.generateMarkov2(size, context, ".", 1.0);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("type", "generateMarkov2");
        map.put("context", context);
        evaluateDictionary(sentances, dictionary, map);
        map.put("sampleTxt", dictionary.substring(0, Math.min(sampleLength, dictionary.length())));
        output.putRow(map);
      }
    }

    System.out.println(output.toTextTable());
    output = new TableOutput();

    for (int context = 0; context < maxLevels; context++) {
      CharTree copy = tree.copy();
      for (int attempt = 0; attempt < 1; attempt++) {
        String dictionary = copy.codec.generateMarkov(size, context, ".");
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("type", "generateMarkov");
        map.put("context", context);
        evaluateDictionary(sentances, dictionary, map);
        map.put("sampleTxt", dictionary.substring(0, Math.min(sampleLength, dictionary.length())));
        output.putRow(map);
      }
    }

    System.out.println(output.toTextTable());
    output = new TableOutput();

    for (int lookahead = 0; lookahead < 3; lookahead++) {
      for (int context = 0; context < maxLevels - lookahead; context++) {
        for (int attempt = 0; attempt < 1; attempt++) {
          String dictionary = tree.copy().codec.generateDictionary(size, context, ".", lookahead, true);
          Map<String, Object> map = new LinkedHashMap<>();
          map.put("type", "generateDictionary1");
          map.put("context", context);
          map.put("lookahead", lookahead);
          evaluateDictionary(sentances, dictionary, map);
          map.put("sampleTxt", dictionary.substring(0, Math.min(sampleLength, dictionary.length())));
          output.putRow(map);
          System.gc();
        }
      }
    }

    System.out.println(output.toTextTable());
    output = new TableOutput();

    for (int lookahead = 0; lookahead < 3; lookahead++) {
      for (int context = 0; context < maxLevels - lookahead; context++) {
        String dictionary = tree.codec.generateDictionary(size, context, ".", lookahead, false);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("type", "generateDictionary2");
        map.put("context", context);
        map.put("lookahead", lookahead);
        evaluateDictionary(sentances, dictionary, map);
        map.put("sampleTxt", dictionary.substring(0, Math.min(sampleLength, dictionary.length())));
        output.putRow(map);
      }
    }

    System.out.println(output.toTextTable());
    output = new TableOutput();
  }

  @Test
  public void testModelMetric() throws IOException {
    Map<String, String> content = new HashMap<>();
    for (String name : Arrays.asList("earthtomoon.txt", "20000leagues.txt", "macbeth.txt", "randj.txt")) {
      content.put(name, new Scanner(getClass().getClassLoader().getResourceAsStream(name)).useDelimiter("\\Z").next()
          .replaceAll("[ \n\r\t]+", " "));
    }

    String characterSet = content.values().stream().flatMapToInt(s -> s.chars()).distinct()
        .mapToObj(c -> new String(Character.toChars(c))).sorted().collect(Collectors.joining(""));
    System.out.println("Character Set:" + characterSet);
    int maxLevels = 5;
    int minWeight = 1;
    double smoothness = 1.0;
    int sampleSize = 64 * 1024;

    long startTime = System.currentTimeMillis();
    Map<String, CharTree> trees = new HashMap<>();
    Map<String, String> dictionaries = new HashMap<>();
    for (Map.Entry<String, String> e : content.entrySet()) {
      CharTree tree = new CharTree();
      tree.addDocument(characterSet);
      tree.addDocument(e.getValue());
      tree.index(maxLevels, minWeight).truncate();
      trees.put(e.getKey(), tree);
      dictionaries.put(e.getKey(), tree.copy().codec.generateDictionary(16 * 1024, 5, "", 1, true));
      System.out.println(
          String.format("Indexing %s; \ntree.getIndexedSize = %s KB", e.getKey(), tree.getIndexedSize() / 1024));
      System.out.println(String.format("tree.getMemorySize = %s KB", tree.getMemorySize() / 1024));
    }
    long elapsed = System.currentTimeMillis() - startTime;
    System.out.println(String.format("Built index in time = %s sec", elapsed / 1000.));

    System.out.println("\nMCMC Similarity Measures:");
    TableOutput output1 = new TableOutput();
    for (Map.Entry<String, CharTree> ea : trees.entrySet()) {
      for (Map.Entry<String, CharTree> eb : trees.entrySet()) {
        String str = ea.getValue().codec.generateMarkov(sampleSize, maxLevels - 1, "");
        Double crossEntropy = eb.getValue().codec.encodeSmallPPM(str, smoothness) / str.length();
        HashMap<String, Object> map = new HashMap<>();
        map.put("source", ea.getKey());
        map.put("model", eb.getKey());
        map.put("crossEntropy", crossEntropy);
        output1.putRow(map);
      }
    }
    System.out.println(output1.toTextTable());

  }

  @Test
  public void calcWikiCoords() throws Exception {

    int minArticleLength = 8 * 1024;
    int maxLevels = 7;
    int minWeight = 1;
    int chunkSize = 256;
    int dictionaryLength = 32 * 1024;
    int dictionaryCount = 20;
    int articleCount = 1000;
    double selectivity = 0.1;

    Map<String, String> articles = WikiArticle.load().filter(x -> x.text.length() > minArticleLength)
        .filter(x -> selectivity > Math.random()).limit(Math.max(articleCount, dictionaryCount))
        .collect(Collectors.toMap(d -> d.title, d -> d.text));

    String characterSet = articles.values().stream().flatMapToInt(s -> s.chars()).distinct()
        .mapToObj(c -> new String(Character.toChars(c))).sorted().collect(Collectors.joining(""));
    System.out.println("Character Set:" + characterSet);

    Stream<Map.Entry<String, String>> stream = articles.entrySet().stream().limit(dictionaryCount);
    Map<String, CharTree> models = stream
        .collect(Collectors.toMap((Map.Entry<String, String> d) -> d.getKey(), (Map.Entry<String, String> d) -> {
          String article = d.getValue();
          String title = d.getKey();
          CharTree tree = new CharTree();
          tree.addDocument(characterSet);
          tree.addDocument(article);
          tree.index(maxLevels, minWeight).truncate();
          System.out.println(
              String.format("Indexing %s; \ntree.getIndexedSize = %s KB", title, tree.getIndexedSize() / 1024));
          System.out.println(String.format("tree.getMemorySize = %s KB", tree.getMemorySize() / 1024));
          return tree;
        }));
    Map<String, String> dictionaries = models.entrySet().stream()
        .collect(Collectors.toMap((Map.Entry<String, CharTree> d) -> d.getKey(), (Map.Entry<String, CharTree> d) -> {
          return d.getValue().copy().codec.generateDictionary(dictionaryLength, 5, "", 1, true);
        }));

    TableOutput output = new TableOutput();
    articles.entrySet().stream().limit(articleCount).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()))
        .forEach((dataTitle, article) -> {
          HashMap<String, Object> map = new LinkedHashMap<>();
          map.put("dataTitle", dataTitle);
          dictionaries.forEach((modelTitle, dictionary) -> {
            int sumA = IntStream.range(0, article.length() / chunkSize)
                .mapToObj(i -> article.substring(i * chunkSize, Math.min(article.length(), (i + 1) * chunkSize)))
                .mapToInt(chunk -> CharTreeCodec.encodeLZ(chunk, "").length).sum();
            int sumB = IntStream.range(0, article.length() / chunkSize)
                .mapToObj(i -> article.substring(i * chunkSize, Math.min(article.length(), (i + 1) * chunkSize)))
                .mapToInt(chunk -> CharTreeCodec.encodeLZ(chunk, dictionary).length).sum();
            double bytes = (sumA - sumB) * 1.0 / sumA;
            map.put(modelTitle.replaceAll("[^01-9a-zA-Z]", "_"), bytes);
          });
          output.putRow(map);
        });
    System.out.println(output.toTextTable());
    String outputDirName = "wikiTopics/";
    output.writeProjectorData(new File(outPath, outputDirName), new URL(outBaseUrl, outputDirName));
  }

  @Test
  public void calcTweetVectors() throws Exception {

    int minArticleLength = 8;
    int maxLevels = 7;
    int minWeight = 1;
    int chunkSize = 256;
    int dictionaryLength = 32 * 1024;
    int dictionaryCount = 20;
    int articleCount = 1000;
    double selectivity = 0.1;

    List<String> articles = TweetSentiment.load().filter(x -> x.text.length() > minArticleLength)
        .filter(x -> selectivity > Math.random()).limit(Math.max(articleCount, dictionaryCount)).map(t -> t.text)
        .collect(Collectors.toList());

    String characterSet = articles.stream().flatMapToInt(s -> s.chars()).distinct()
        .mapToObj(c -> new String(Character.toChars(c))).sorted().collect(Collectors.joining(""));
    System.out.println("Character Set:" + characterSet);

    Map<String, CharTree> models = articles.stream().limit(dictionaryCount)
        .collect(Collectors.toMap((String d) -> d, (String text) -> {
          CharTree tree = new CharTree();
          tree.addDocument(characterSet);
          tree.addDocument(text);
          tree.index(maxLevels, minWeight).truncate();
          System.out
              .println(String.format("Indexing %s; \ntree.getIndexedSize = %s KB", text, tree.getIndexedSize() / 1024));
          System.out.println(String.format("tree.getMemorySize = %s KB", tree.getMemorySize() / 1024));
          return tree;
        }));
    Map<String, String> dictionaries = models.entrySet().stream()
        .collect(Collectors.toMap((Map.Entry<String, CharTree> d) -> d.getKey(), (Map.Entry<String, CharTree> d) -> {
          return d.getValue().copy().codec.generateDictionary(dictionaryLength, 5, "", 1, true);
        }));

    TableOutput output = new TableOutput();
    articles.stream().limit(articleCount).collect(Collectors.toList()).forEach((text) -> {
      HashMap<String, Object> map = new LinkedHashMap<>();
      map.put("text", text);
      dictionaries.forEach((modelTitle, dictionary) -> {
        int sumA = IntStream.range(0, text.length() / chunkSize)
            .mapToObj(i -> text.substring(i * chunkSize, Math.min(text.length(), (i + 1) * chunkSize)))
            .mapToInt(chunk -> CharTreeCodec.encodeLZ(chunk, "").length).sum();
        int sumB = IntStream.range(0, text.length() / chunkSize)
            .mapToObj(i -> text.substring(i * chunkSize, Math.min(text.length(), (i + 1) * chunkSize)))
            .mapToInt(chunk -> CharTreeCodec.encodeLZ(chunk, dictionary).length).sum();
        double bytes = (sumA - sumB) * 1.0 / sumA;
        map.put(Integer.toHexString(modelTitle.hashCode()), bytes);
      });
      output.putRow(map);
    });
    // System.out.println(output.toTextTable());
    String outputDirName = "tweets/";
    output.writeProjectorData(new File(outPath, outputDirName), new URL(outBaseUrl, outputDirName));
  }

  @Test
  public void testTweetGeneration() throws Exception {

    int maxLevels = 6;
    int minWeight = 1;
    int modelCount = 100000;
    int articleCount = 100;
    int lookahead = 1;

    CharTree tree_good = new CharTree();
    TweetSentiment.load().filter(x -> x.category == 1).limit(modelCount).map(t -> t.text)
        .forEach(txt -> tree_good.addDocument(">>>" + txt));
    System.out.println(String.format("Indexing %s positive tweets; \ntree.getIndexedSize = %s KB", modelCount,
        tree_good.getIndexedSize() / 1024));
    tree_good.index(maxLevels, minWeight).truncate();
    System.out.println(String.format("tree.getMemorySize = %s KB", tree_good.getMemorySize() / 1024));

    CharTree tree_bad = new CharTree();
    TweetSentiment.load().filter(x -> x.category == 0).limit(modelCount).map(t -> t.text)
        .forEach(txt -> tree_bad.addDocument(">>>" + txt));
    System.out.println(String.format("Indexing %s negative tweets; \ntree.getIndexedSize = %s KB", modelCount,
        tree_bad.getIndexedSize() / 1024));
    tree_bad.index(maxLevels, minWeight).truncate();
    System.out.println(String.format("tree.getMemorySize = %s KB", tree_bad.getMemorySize() / 1024));

    TableOutput output = new TableOutput();
    IntStream.range(0, articleCount).forEach(i -> {
      HashMap<String, Object> goodRow = new LinkedHashMap<>();
      goodRow.put("type", "good");
      goodRow.put("text", tree_good.codec.generateDictionary(256, maxLevels - 1, ">>>", lookahead, true, true).substring(3).replaceAll("\u0000", "\n\t"));
      output.putRow(goodRow);
      HashMap<String, Object> badRow = new LinkedHashMap<>();
      badRow.put("type", "bad");
      badRow.put("text", tree_bad.codec.generateDictionary(256, maxLevels - 1, ">>>", lookahead, true, true).substring(3).replaceAll("\u0000", "\n\t"));
      output.putRow(badRow);
    });
    System.out.println(output.toTextTable());
  }

  @Test
  public void testWikiCompression() throws Exception {

    int maxLevels = 5;
    int minWeight = 2;
    int modelCount = 100;
    int articleCount = 10;
    double smoothness = 1.0;

    List<WikiArticle> dataset = WikiArticle.load().filter(a->a.text.length()>1024)
        .limit(articleCount + modelCount).collect(Collectors.toList());
    
    String characterSet = dataset.stream().flatMapToInt(s -> s.text.chars()).distinct()
        .mapToObj(c -> new String(Character.toChars(c))).sorted().collect(Collectors.joining(""));
    System.out.println("Character Set:" + characterSet);
    
    CharTree tree = new CharTree();
    tree.addDocument(characterSet);
    dataset.stream().skip(articleCount).limit(modelCount).map(t -> t.text)
        .forEach(txt -> tree.addDocument(txt));
    System.out.println(String.format("Indexing %s wiki articles; \ntree.getIndexedSize = %s KB", modelCount,
        tree.getIndexedSize() / 1024));
    tree.index(maxLevels, minWeight).truncate();
    System.out.println(String.format("tree.getMemorySize = %s KB", tree.getMemorySize() / 1024));
    String dictionary = tree.copy().codec.generateDictionary(16*1024, 2, "", 0, true);

    CharTreeCodec codec = tree.addEscapeNodes().codec;
    TableOutput output = new TableOutput();
    dataset.stream().limit(articleCount).forEach(article->{
      HashMap<String, Object> row = new LinkedHashMap<>();
      double bits = tree.codec.encodeSmallPPM(article.text, smoothness);
      row.put("bitsPerChar", bits / article.text.length());
      row.put("measure", bits / 8);
      int data;
      try {
        data = codec.encodeFastPPM(article.text, 1).length;
      } catch (Exception e) {
        e.printStackTrace();
        data = -1;
      }
      row.put("PPM", data);
      row.put("16kLZ", CharTreeCodec.encodeLZ(article.text, dictionary).length);
      row.put("0kLZ", CharTreeCodec.encodeLZ(article.text, "").length);
      row.put("size", article.text.length());
      row.put("title", article.title);
      output.putRow(row);
    });
    System.out.println(output.toTextTable());
  }

  @Test
  public void testTweetCompression() throws Exception {

    int maxLevels = 5;
    int minWeight = 2;
    int modelCount = 100000;
    int articleCount = 100;
    double smoothness = 1.0;

    CharTree tree = new CharTree();
    TweetSentiment.load().skip(articleCount).limit(modelCount).map(t -> t.text)
        .forEach(txt -> tree.addDocument(txt));
    System.out.println(String.format("Indexing %s tweets; \ntree.getIndexedSize = %s KB", modelCount,
        tree.getIndexedSize() / 1024));
    tree.index(maxLevels, minWeight).truncate();
    System.out.println(String.format("tree.getMemorySize = %s KB", tree.getMemorySize() / 1024));
    String dictionary = tree.copy().codec.generateDictionary(16*1024, 2, "", 0, true);
    CharTreeCodec codec = tree.addEscapeNodes().codec;

    TableOutput output = new TableOutput();
    TweetSentiment.load().limit(articleCount).map(t -> t.text).forEach(text->{
      HashMap<String, Object> row = new LinkedHashMap<>();
      double bits = tree.codec.encodeSmallPPM(text, smoothness);
      byte[] data = codec.encodeFastPPM(text, 1);
      row.put("bitsPerChar", bits / text.length());
      row.put("text", text);
      row.put("measure", bits / 8);
      row.put("PPM", data.length);
      row.put("16kLZ", CharTreeCodec.encodeLZ(text, dictionary).length);
      row.put("0kLZ", CharTreeCodec.encodeLZ(text, "").length);
      row.put("size", text.length());
      output.putRow(row);
    });
    System.out.println(output.toTextTable());
  }

  @Test
  public void calcSentenceCoords() throws IOException {
    Map<String, String> content = new HashMap<>();
    for (String name : Arrays.asList("earthtomoon.txt", "20000leagues.txt", "macbeth.txt", "randj.txt")) {
      content.put(name, new Scanner(getClass().getClassLoader().getResourceAsStream(name)).useDelimiter("\\Z").next()
          .replaceAll("[ \n\r\t]+", " "));
    }
    String allContent = content.values().stream().collect(Collectors.joining("\n"));
    List<String> sentances = Arrays.stream(allContent.split("\\.+")).map(line -> line.trim() + ".")
        .filter(line -> line.length() > 12).collect(Collectors.toList());
    Collections.shuffle(sentances);

    String characterSet = content.values().stream().flatMapToInt(s -> s.chars()).distinct()
        .mapToObj(c -> new String(Character.toChars(c))).sorted().collect(Collectors.joining(""));
    System.out.println("Character Set:" + characterSet);

    int maxLevels = 7;
    int minWeight = 1;
    double smoothness = 1.0;

    long startTime = System.currentTimeMillis();
    Map<String, CharTree> trees = new HashMap<>();
    Map<String, String> dictionaries = new HashMap<>();
    for (Map.Entry<String, String> e : content.entrySet()) {
      CharTree tree = new CharTree();
      tree.addDocument(characterSet);
      tree.addDocument(e.getValue());
      tree.index(maxLevels, minWeight).truncate();
      trees.put(e.getKey(), tree);
      dictionaries.put(e.getKey(), tree.copy().codec.generateDictionary(16 * 1024, 5, "", 1, true));
      System.out.println(
          String.format("Indexing %s; \ntree.getIndexedSize = %s KB", e.getKey(), tree.getIndexedSize() / 1024));
      System.out.println(String.format("tree.getMemorySize = %s KB", tree.getMemorySize() / 1024));
    }
    long elapsed = System.currentTimeMillis() - startTime;
    System.out.println(String.format("Built index in time = %s sec", elapsed / 1000.));

    TableOutput output1 = new TableOutput();
    TableOutput output2 = new TableOutput();
    sentances.stream().limit(1000).forEach(s -> {
      HashMap<String, Object> map2 = new LinkedHashMap<>();
      // map2.put("rawBits", s.length() * 8);
      // map2.put("decompressBits0", CharTreeUtil.compress("", s).length * 8);
      content.keySet().stream().forEach(key -> {
        CharTree tree = trees.get(key);
        String dictionary = dictionaries.get(key);
        HashMap<String, Object> map1 = new LinkedHashMap<>();
        map1.put("key", key);
        map1.put("rawBits", s.length() * 8);
        map1.put("decompressBits0", CharTreeCodec.encodeLZ(s, "").length * 8);
        int decompressBits1 = CharTreeCodec.encodeLZ(s, dictionary.substring(0, 1024)).length * 8;
        map1.put(".decompressBits1", decompressBits1);
        // map2.put(key+".decompressBits1", decompressBits1);
        int decompressBits4 = CharTreeCodec.encodeLZ(s, dictionary.substring(0, 4 * 1024)).length * 8;
        map1.put(".decompressBits4", decompressBits4);
        // map2.put(key+".decompressBits4", decompressBits4);
        int decompressBits16 = CharTreeCodec.encodeLZ(s, dictionary.substring(0, 16 * 1024)).length * 8;
        map1.put(".decompressBits16", decompressBits16);
        // map2.put(key+".decompressBits16", decompressBits16);
        double ppmBits = tree.codec.encodeSmallPPM(s, smoothness);
        map1.put(".ppmBits", ppmBits);
        // map2.put(key+".ppmBits", ppmBits);
        map1.put(".bitsPerChar", ppmBits / s.length());
        map2.put(key + ".bitsPerChar", ppmBits / s.length());
        map1.put("txt", s.substring(0, Math.min(s.length(), 120)));
        output1.putRow(map1);
      });
      map2.put("txt", s);
      output2.putRow(map2);
    });
    // System.out.println(output1.toTextTable());
    String outputDirName = "sentenceClassification/";
    output2.writeProjectorData(new File(outPath, outputDirName), new URL(outBaseUrl, outputDirName));
  }

  static private Map<String, Object> evaluateDictionary(List<String> sentances, String dictionary, Map<String, Object> map) {
    Arrays.asList(1, 4, 16, 32).stream().forEach(k -> {
      DoubleStatistics statistics = sentances.stream().map(line -> {
        int length0 = CharTreeCodec.encodeLZ(line, "").length;
        int lengthK = CharTreeCodec.encodeLZ(line, dictionary.substring(0, Math.min(k * 1024, dictionary.length()))).length;
        return (length0 - lengthK) * 1.0;
      }).collect(DoubleStatistics.COLLECTOR);
      map.put(String.format("%sk.sum", k), (int) statistics.getSum() / 1024);
      map.put(String.format("%sk.avg", k), (int) statistics.getAverage());
      map.put(String.format("%sk.stddev", k), (int) statistics.getStandardDeviation());
    });
    return map;
  }

  @Test
  public void testPerformanceMatrix() throws IOException {
    for (int count = 100; count < 50000; count *= 2) {
      for (int maxLevels = 1; maxLevels < 64; maxLevels = Math.max((int) (maxLevels * 4), maxLevels + 1)) {
        for (int minWeight = 1; minWeight < 64; minWeight *= 4) {
          testRow(maxLevels, minWeight,
              IntStream.range(0, count).parallel().mapToObj(i -> UUID.randomUUID().toString()));
        }
      }
    }

  }

  static private void testRow(int maxLevels, int minWeight, Stream<String> documents) {
    CharTree tree = new CharTree();
    long startTime = System.currentTimeMillis();
    documents.forEach(i -> tree.addDocument(i));
    tree.index(maxLevels, minWeight);
    long elapsed = System.currentTimeMillis() - startTime;
    System.out.println(
        String.format("%s\t%s\t%s KB\t%s sec\t%s KB\t%s KB", maxLevels, minWeight, tree.getIndexedSize() / 1024,
            elapsed / 1000., tree.getMemorySize() / 1024, tree.truncate().getMemorySize() / 1024));
  }

}
