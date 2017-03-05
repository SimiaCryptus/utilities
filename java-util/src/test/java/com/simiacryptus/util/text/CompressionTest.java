package com.simiacryptus.util.text;

import com.simiacryptus.util.binary.Bits;
import com.simiacryptus.util.test.TestDocument;
import com.simiacryptus.util.test.TweetSentiment;
import com.simiacryptus.util.test.WikiArticle;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CompressionTest {


  @Test
  public void testPPMCompression_Tweets() throws Exception {
    final CharTree tree = new CharTree();
    long articleCount = 50;
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
    int ppmModelDepth = 9;
    int model_minPathWeight = 3;
    int dictionary_lookahead = 2;
    int dictionary_context = 6;
    int encodingContext = 2;
    int modelCount = 10000;
    int testCount = 100;
    Supplier<Stream<? extends TestDocument>> source = ()->TweetSentiment.load().limit(modelCount + testCount);

    Map<String, Compressor> compressors = buildCompressors(source, ppmModelDepth, model_minPathWeight, dictionary_lookahead, dictionary_context, encodingContext, modelCount);
    TableOutput output = Compressor.evalTable(source.get().skip(modelCount), compressors, true);
    System.out.println(output.toTextTable());
  }

  @Test
  public void calcWikiCompression() throws Exception {
    int ppmModelDepth = 9;
    int model_minPathWeight = 3;
    int dictionary_lookahead = 2;
    int dictionary_context = 6;
    int encodingContext = 2;
    int modelCount = 100;
    int testCount = 100;
    Supplier<Stream<? extends TestDocument>> source = ()->WikiArticle.load().filter(x -> x.text.length() > 8 * 1024).limit(modelCount + testCount);

    Map<String, Compressor> compressors = buildCompressors(source, ppmModelDepth, model_minPathWeight, dictionary_lookahead, dictionary_context, encodingContext, modelCount);
    TableOutput output = Compressor.evalTable(source.get().skip(modelCount), compressors, true);
    System.out.println(output.toTextTable());
  }

  protected Map<String, Compressor> buildCompressors(Supplier<Stream<? extends TestDocument>> source, int ppmModelDepth, int model_minPathWeight, final int dictionary_lookahead, final int dictionary_context, final int encodingContext, int modelCount) {
    Map<String, Compressor> compressors = new LinkedHashMap<>();
    Compressor.addGenericCompressors(compressors);

    CharTree baseTree = new CharTree();
    System.out.println(String.format("Preparing %s documents", modelCount));
    source.get().limit(modelCount).forEach(txt -> {
      //System.out.println(String.format("Adding %s", txt.title));
      baseTree.addDocument(txt.text);
    });
    System.out.println(String.format("Indexing %s KB of documents", baseTree.getIndexedSize() / 1024));
    baseTree.index(ppmModelDepth, model_minPathWeight);

    try {
      System.out.println(String.format("Generating dictionaries"));
      addSharedDictionaryCompressors(compressors, baseTree, dictionary_lookahead, dictionary_context, model_minPathWeight);
    } catch (Exception e) {
      e.printStackTrace();
    }

    compressors.put("PPM" + encodingContext, Compressor.buildPPMCompressor(baseTree, encodingContext));

    return compressors;
  }

  public static void addSharedDictionaryCompressors(
          Map<String, Compressor> compressors, final CharTree baseTree, final int dictionary_lookahead, final int dictionary_context, int model_minPathWeight) {
    CharTree dictionaryTree = baseTree.copy().index(dictionary_context + dictionary_lookahead, model_minPathWeight);
    compressors.put("LZ8k", new Compressor() {
      String dictionary = dictionaryTree.copy().codec.generateDictionary(8*1024, dictionary_context, "", dictionary_lookahead, true);
      @Override
      public byte[] compress(String text) {
        return CharTreeCodec.encodeLZ(text, dictionary);
      }

      @Override
      public String uncompress(byte[] data) {
        return CharTreeCodec.decodeLZ(data, dictionary);
      }
    });

    compressors.put("BZ64k", new Compressor() {
      String dictionary = dictionaryTree.copy().codec.generateDictionary(64*1024, dictionary_context, "", dictionary_lookahead, true);
      @Override
      public byte[] compress(String text) {
        return CharTreeCodec.encodeBZ(text, dictionary);
      }

      @Override
      public String uncompress(byte[] data) {
        return CharTreeCodec.decodeBZ(data, dictionary);
      }
    });
  }

}
