package com.simiacryptus.util.text;

import com.simiacryptus.util.test.*;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public abstract class ModelMetaTest {

  public static final File outPath = new File("src/site/resources/");
  public static final URL outBaseUrl = TrieTest.getUrl("https://simiacryptus.github.io/utilities/java-util/");

  protected abstract Stream<? extends TestDocument> source();
  public abstract int getModelCount();

  public static class Tweets extends ModelMetaTest {
    int testCount = 100;

    @Override
    protected Stream<? extends TestDocument> source() {
      return TweetSentiment.load().limit(getModelCount() + testCount);
    }

    @Override
    public int getModelCount() {
      return 100000;
    }

  }

  public static class Wikipedia extends ModelMetaTest {
    int testCount = 100;

    @Override
    protected Stream<? extends TestDocument> source() {
      return WikiArticle.load().limit(getModelCount() + testCount);
    }

    @Override
    public int getModelCount() {
      return 100;
    }
  }

  @Test
  @Category(TestCategories.ResearchCode.class)
  public void calcSharedDictionariesLZ() throws Exception {
    try(MarkdownPrintStream log = MarkdownPrintStream.get(this).addCopy(System.out)){
      CharTrieIndex baseTree = new CharTrieIndex();
      log.p("Preparing %s documents", getModelCount());
      source().limit(getModelCount()).forEach(txt -> {
        //System.p.println(String.format("Adding %s", txt.title));
        baseTree.addDocument(txt.getText());
      });
      log.p("Indexing %s KB of documents", baseTree.getIndexedSize() / 1024);
      Map<String, Compressor> compressors = new LinkedHashMap<>();

      for(int dictionary_context : Arrays.asList(4,5,6)) {
        int model_minPathWeight = 3;
        int dictionary_lookahead = 2;
        log.p("Generating dictionaries");
        CharTrie dictionaryTree = baseTree.copy().index(dictionary_context + dictionary_lookahead, model_minPathWeight);

        compressors.put(String.format("LZ8k_%s", dictionary_context), new Compressor() {
          String dictionary = dictionaryTree.copy().getGenerator().generateDictionary(8*1024, dictionary_context, "", dictionary_lookahead, true);
          @Override
          public byte[] compress(String text) {
            return CompressionUtil.encodeLZ(text, dictionary);
          }

          @Override
          public String uncompress(byte[] data) {
            return CompressionUtil.decodeLZToString(data, dictionary);
          }
        });
      }

      TableOutput output = Compressor.evalCompressor(source().skip(getModelCount()), compressors, true);
      //log.p(output.toTextTable());
      log.p(output.calcNumberStats().toTextTable());
    }
  }

  @Test
  @Category(TestCategories.Report.class)
  public void calcSharedDictionariesBZ() throws Exception {
    try(MarkdownPrintStream log = MarkdownPrintStream.get(this).addCopy(System.out)){
      CharTrieIndex baseTree = new CharTrieIndex();
      log.p("Preparing %s documents", getModelCount());
      source().limit(getModelCount()).forEach(txt -> {
        //System.p.println(String.format("Adding %s", txt.title));
        baseTree.addDocument(txt.getText());
      });
      log.p("Indexing %s KB of documents", baseTree.getIndexedSize() / 1024);
      Map<String, Compressor> compressors = new LinkedHashMap<>();

      for(int dictionary_context : Arrays.asList(4,6,8)) {
        int model_minPathWeight = 3;
        int dictionary_lookahead = 2;
        log.p("Generating dictionaries");
        CharTrie dictionaryTree = baseTree.copy().index(dictionary_context + dictionary_lookahead, model_minPathWeight);

        compressors.put(String.format("BZ64k_%s", dictionary_context), new Compressor() {
          String dictionary = dictionaryTree.copy().getGenerator().generateDictionary(64*1024, dictionary_context, "", dictionary_lookahead, true);
          @Override
          public byte[] compress(String text) {
            return CompressionUtil.encodeBZ(text, dictionary);
          }

          @Override
          public String uncompress(byte[] data) {
            return CompressionUtil.decodeBZ(data, dictionary);
          }
        });
      }
      TableOutput output = Compressor.evalCompressor(source().skip(getModelCount()), compressors, true);
      //log.p(output.toTextTable());
      log.p(output.calcNumberStats().toTextTable());
    }
  }

  @Test
  @Category(TestCategories.Report.class)
  public void calcCompressorPPM() throws Exception {
    try(MarkdownPrintStream log = MarkdownPrintStream.get(this).addCopy(System.out);){
      CharTrieIndex baseTree = new CharTrieIndex();
      log.p("Preparing %s documents", getModelCount());
      source().limit(getModelCount()).forEach(txt -> {
        //System.p.println(String.format("Adding %s", txt.title));
        baseTree.addDocument(txt.getText());
      });
      log.p("Indexing %s KB of documents", baseTree.getIndexedSize() / 1024);

      Map<String, Compressor> compressors = new LinkedHashMap<>();

      int model_minPathWeight = 1;
      for(int ppmModelDepth : Arrays.asList(4,6,8)) {
        for(int encodingContext : Arrays.asList(1,2,3)) {
          CharTrie ppmTree = baseTree.copy().index(ppmModelDepth, model_minPathWeight);
          String name = String.format("PPM%s_%s", encodingContext, ppmModelDepth);
          compressors.put(name, Compressor.buildPPMCompressor(ppmTree, encodingContext));
        }
      }

      TableOutput output = Compressor.evalCompressor(source().skip(getModelCount()), compressors, true);
      //log.p(output.toTextTable());
      log.p(output.calcNumberStats().toTextTable());
    }
  }


}
