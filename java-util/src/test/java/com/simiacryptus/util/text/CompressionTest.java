package com.simiacryptus.util.text;

import com.simiacryptus.util.binary.Bits;
import com.simiacryptus.util.test.TweetSentiment;
import com.simiacryptus.util.test.WikiArticle;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompressionTest {


  @Test
  public void testPPMCompression_Tweets() throws Exception {
    final CharTree tree = new CharTree();
    long articleCount = 100;
    long modelCount = 10000;
    int encodingContext = 2;
    int modelDepth = 4;
    TweetSentiment.load().skip(articleCount).limit(modelCount).map(t -> t.text)
            .forEach(txt -> tree.addDocument(txt));
    CharTree modelTree = tree.index(modelDepth, 0).addEscapeNodes();
    //modelTree.codec.verbose = true;
    TweetSentiment.load().limit(articleCount).map(t -> t.text).forEach(txt->{
      try {
        Bits encoded = modelTree.codec.encodePPM(txt, encodingContext);
        String decoded = modelTree.codec.decodePPM(encoded.getBytes(), encodingContext);
        org.junit.Assert.assertEquals(txt, decoded);
        System.out.println(String.format("Verified \"%s\" - %s chars -> %s bits", txt, txt.length(), encoded.bitLength));
      } catch (Exception e) {
        System.out.println(String.format("Error encoding \"%s\" - %s", txt, e.getMessage()));
        //throw new RuntimeException(e);
      }
    });
  }

  @Test
  public void testPPMCompression_Basic() throws Exception {
    CharTree tree = new CharTree();
    tree.addDocument("ababababab");
    tree = tree.index(2,0).addEscapeNodes();
    String txt = "aba";
    CharTreeCodec codec = tree.codec;
    codec.verbose = true;
    Bits encoded = codec.encodePPM(txt, 1);
    String decoded = codec.decodePPM(encoded.getBytes(), 1);
    org.junit.Assert.assertEquals(txt, decoded);
  }

  @Test
  public void calcTweetCompression() throws Exception {
    int maxLevels = 5;
    int minWeight = 1;
    int modelCount = 100000;
    int testCount = 100;
    int lookahead = 2;
    int dictionaryContext = 5;
    int encodingContext = 2;

    Stream<TweetSentiment> data = TweetSentiment.load().limit(modelCount + testCount);
    TableOutput output = calcCompressionEfficacyTable(maxLevels, minWeight, modelCount, data, lookahead, dictionaryContext, encodingContext, x->x.text, x->x.text);
    System.out.println(output.toTextTable());
  }

  @Test
  public void calcWikiCompression() throws Exception {
    int maxLevels = 9;
    int minWeight = 3;
    int modelCount = 100;
    int testCount = 100;
    int lookahead = 2;
    int dictionaryContext = 6;
    int encodingContext = 2;

    Stream<WikiArticle> data = WikiArticle.load().filter(x->x.text.length()>8*1024).limit(modelCount + testCount);
    TableOutput output = calcCompressionEfficacyTable(maxLevels, minWeight, modelCount, data, lookahead, dictionaryContext, encodingContext, x->x.text, x->x.title);
    System.out.println(output.toTextTable());
  }

  private <T> TableOutput calcCompressionEfficacyTable(int maxLevels, int minWeight, int modelCount, Stream<T> stream, int lookahead, int dictionaryContext, int encodingContext, Function<T, String> textF, Function<T, String> labelF) {
    System.out.println(String.format("Loading %s documents", modelCount));
    CharTree tree = new CharTree();
    List<T> list = stream.collect(Collectors.toList());
    System.out.println(String.format("Preparing %s documents", modelCount));
    list.stream().limit(modelCount).map(t -> textF.apply(t)).forEach(txt -> tree.addDocument(txt));
    System.out.println(String.format("Indexing %s KB of documents", tree.getIndexedSize() / 1024));
    tree.index(maxLevels, minWeight);
    CharTree dictTree = tree.copy().index(dictionaryContext + lookahead, minWeight);
    System.out.println(String.format("Working Size = %s KB", tree.getMemorySize() / 1024));
    tree.truncate();
    System.out.println(String.format("Final Index Size = %s KB", tree.getMemorySize() / 1024));
    CharTree encodingModel = tree.addEscapeNodes();
    CharTreeCodec codec = encodingModel.codec;
    String dictionary16 = dictTree.copy().codec.generateDictionary(16*1024, dictionaryContext, "", lookahead, true);
    String dictionary64 = dictTree.copy().codec.generateDictionary(64*1024, dictionaryContext, "", lookahead, true);
    System.out.println("Suggested shared dictionary: "+ dictionary16);
    Object debugLock = new Object();
    TableOutput output = new TableOutput();
    list.stream().skip(modelCount).parallel().forEach(item->{
      try {
        String text = textF.apply(item);
        String label = labelF.apply(item);
        //System.out.println(String.format("Processing %s", label));
        HashMap<String, Object> row = new LinkedHashMap<>();
        double entropy = 0; //tree.codec.measureEntropy(text, smoothness);
        row.put("bitsPerChar", entropy / text.length());
        row.put("label", label);
        row.put("size", text.length());
        row.put("measure", entropy / 8);
        Bits bits = codec.encodePPM(text, encodingContext);
        row.put("PPM", bits.bitLength / 8);
        row.put("16kLZ", CharTreeCodec.encodeLZ(text, dictionary16).length);
        row.put("0kLZ", CharTreeCodec.encodeLZ(text).length);
        row.put("0kBZ", CharTreeCodec.encodeBZ(text).length);
        row.put("64kBZ", CharTreeCodec.encodeBZ(text, dictionary64).length);
        //row.put("Compressed", bytes.toBase64String());
        String verified = "n";
        try {
          String decompress = codec.decodePPM(bits.getBytes(), encodingContext);
          verified = decompress.equals(text) ? "y" : "f";
        } catch (Exception e) {
          synchronized (debugLock) {
            CharTreeCodec codec2 = encodingModel.copy().codec;
            codec2.verbose = true;
            try {
              System.out.println(String.format("Error verifying %s - %s", label,e.getMessage()));
              Bits bits2 = codec2.encodePPM(text, encodingContext);
              String decompress2 = codec2.decodePPM(bits2.getBytes(), encodingContext);
              verified = decompress2.equals(text) ? "y" : "f";
            } catch (Exception e2) {
              System.out.println(e2.getMessage());
            }
          }
        }
        row.put("verified", verified);
        output.putRow(row);
        System.out.println(String.format("Processed %s", label));
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    return output;
  }

}
