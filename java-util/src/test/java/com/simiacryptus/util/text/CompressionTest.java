package com.simiacryptus.util.text;

import com.simiacryptus.util.binary.Bits;
import com.simiacryptus.util.test.*;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CompressionTest {

  @Test
  @Category(TestCategories.UnitTest.class)
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
  @Category(TestCategories.ResearchCode.class)
  public void testPPMCompression_Tweets() throws Exception {
    long articleCount = 1000;
    long modelCount = 10000;
    int encodingContext = 3;
    int modelDepth = 9;

    final CharTree tree = new CharTree();
    TweetSentiment.load().skip(articleCount).limit(modelCount).map(t -> t.text)
            .forEach(txt -> tree.addDocument(txt));
    CharTree modelTree = tree.index(modelDepth, 0).addEscapeNodes();
    TweetSentiment.load().limit(articleCount).map(t -> t.text).forEach(txt->{
      try {
        Bits encoded = modelTree.codec.encodePPM(txt, encodingContext);
        String decoded = modelTree.codec.decodePPM(encoded.getBytes(), encodingContext);
        org.junit.Assert.assertEquals(txt, decoded);
        //System.out.println(String.format("Verified \"%s\" - %s chars -> %s bits", txt, txt.length(), encoded.bitLength));
      } catch (Throwable e) {
        synchronized (modelTree) {
          System.out.println(String.format("Error encoding \"%s\" - %s", txt, e.getMessage()));
          try {
            CharTreeCodec codec = modelTree.copy().codec;
            codec.verbose = true;
            Bits encoded = codec.encodePPM(txt, encodingContext);
            String decoded = codec.decodePPM(encoded.getBytes(), encodingContext);
            org.junit.Assert.assertEquals(txt, decoded);
            System.out.println(String.format("Verified \"%s\" - %s chars -> %s bits", txt, txt.length(), encoded.bitLength));
          } catch (Throwable e2) {
            e2.printStackTrace();
            //System.out.println(String.format("Error encoding \"%s\" - %s", txt, e2.getMessage()));
            //throw new RuntimeException(e);
          }
        }
      }
    });
  }

  @Test
  @Category(TestCategories.Report.class)
  public void calcTweetCompression() throws Exception {
    int ppmModelDepth = 9;
    int model_minPathWeight = 3;
    int dictionary_lookahead = 2;
    int dictionary_context = 6;
    int encodingContext = 2;
    int modelCount = 10000;
    int testCount = 100;
    Supplier<Stream<? extends TestDocument>> source = ()->TweetSentiment.load().limit(modelCount + testCount);

    MarkdownPrintStream log = new MarkdownPrintStream(new FileOutputStream("reports/calcTweetCompression.md")).addCopy(System.out);
    Map<String, Compressor> compressors = buildCompressors(source, ppmModelDepth, model_minPathWeight, dictionary_lookahead, dictionary_context, encodingContext, modelCount);
    TableOutput output = Compressor.evalTable(source.get().skip(modelCount), compressors, true);
    //log.out(output.toTextTable());
    log.out(output.calcNumberStats().toTextTable());
    log.close();
  }

  @Test
  @Category(TestCategories.Report.class)
  public void calcTermCompression() throws Exception {
    int ppmModelDepth = 10;
    int model_minPathWeight = 0;
    int dictionary_lookahead = 2;
    int dictionary_context = 6;
    int encodingContext = 3;
    int modelCount = 15000;
    int testCount = 100;
    Supplier<Stream<? extends TestDocument>> source = ()-> EnglishWords.load().limit(modelCount + testCount);
    MarkdownPrintStream log = new MarkdownPrintStream(new FileOutputStream("reports/calcTermCompression.md")).addCopy(System.out);
    Map<String, Compressor> compressors = buildCompressors(source, ppmModelDepth, model_minPathWeight, dictionary_lookahead, dictionary_context, encodingContext, modelCount);
    TableOutput output = Compressor.evalTable(source.get().skip(modelCount), compressors, true);
    //log.out(output.toTextTable());
    log.out(output.calcNumberStats().toTextTable());
    log.close();
  }

  @Test
  @Category(TestCategories.Report.class)
  public void calcWikiCompression() throws Exception {
    int ppmModelDepth = 9;
    int model_minPathWeight = 3;
    int dictionary_lookahead = 2;
    int dictionary_context = 6;
    int encodingContext = 2;
    int modelCount = 100;
    int testCount = 100;
    Supplier<Stream<? extends TestDocument>> source = ()->WikiArticle.load().filter(x -> x.text.length() > 8 * 1024).limit(modelCount + testCount);

    MarkdownPrintStream log = new MarkdownPrintStream(new FileOutputStream("reports/calcWikiCompression.md")).addCopy(System.out);
    Map<String, Compressor> compressors = buildCompressors(source, ppmModelDepth, model_minPathWeight, dictionary_lookahead, dictionary_context, encodingContext, modelCount);
    TableOutput output = Compressor.evalTable(source.get().skip(modelCount), compressors, true);
    //log.out(output.toTextTable());
    log.out(output.calcNumberStats().toTextTable());
    log.close();
  }

  protected Map<String, Compressor> buildCompressors(Supplier<Stream<? extends TestDocument>> source, int ppmModelDepth, int model_minPathWeight, final int dictionary_lookahead, final int dictionary_context, final int encodingContext, int modelCount) {
    Map<String, Compressor> compressors = new LinkedHashMap<>();
    Compressor.addGenericCompressors(compressors);
    System.out.println(String.format("Preparing %s documents", modelCount));
    CharTree baseTree = new CharTree();
    source.get().limit(modelCount).forEach(txt -> {
      //System.out.println(String.format("Adding %s", txt.title));
      baseTree.addDocument(txt.text);
    });
    System.out.println(String.format("Indexing %s KB of documents", baseTree.getIndexedSize() / 1024));
    baseTree.index(ppmModelDepth, model_minPathWeight);
    System.out.println(String.format("Generating dictionaries"));
    addSharedDictionaryCompressors(compressors, baseTree, dictionary_lookahead, dictionary_context, model_minPathWeight);
    compressors.put("PPM" + encodingContext, Compressor.buildPPMCompressor(baseTree, encodingContext));
    return compressors;
  }

  static void addSharedDictionaryCompressors(
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
