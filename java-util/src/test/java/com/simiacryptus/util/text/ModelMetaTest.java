package com.simiacryptus.util.text;

import com.simiacryptus.util.test.TestDocument;
import com.simiacryptus.util.test.TweetSentiment;
import com.simiacryptus.util.test.WikiArticle;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public abstract class ModelMetaTest {

  public static final File outPath = new File("src/site/resources/");
  public static final URL outBaseUrl = CharTreeTest.getUrl("https://simiacryptus.github.io/utilities/java-util/");

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
  public void calcSharedDictionariesLZ() throws Exception {
    CharTree baseTree = new CharTree();
    System.out.println(String.format("Preparing %s documents", getModelCount()));
    source().limit(getModelCount()).forEach(txt -> {
      //System.out.println(String.format("Adding %s", txt.title));
      baseTree.addDocument(txt.text);
    });
    System.out.println(String.format("Indexing %s KB of documents", baseTree.getIndexedSize() / 1024));
    Map<String, Compressor> compressors = new LinkedHashMap<>();

    for(int dictionary_context : Arrays.asList(4,5,6)) {
      int model_minPathWeight = 3;
      int dictionary_lookahead = 2;
      System.out.println(String.format("Generating dictionaries"));
      CharTree dictionaryTree = baseTree.copy().index(dictionary_context + dictionary_lookahead, model_minPathWeight);

      compressors.put(String.format("LZ8k_%s", dictionary_context), new Compressor() {
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
    }

    TableOutput output = Compressor.evalTable(source().skip(getModelCount()), compressors, true);
    System.out.println(output.toTextTable());
    System.out.println(output.calcNumberStats().toTextTable());
  }

  @Test
  public void calcSharedDictionariesBZ() throws Exception {
    CharTree baseTree = new CharTree();
    System.out.println(String.format("Preparing %s documents", getModelCount()));
    source().limit(getModelCount()).forEach(txt -> {
      //System.out.println(String.format("Adding %s", txt.title));
      baseTree.addDocument(txt.text);
    });
    System.out.println(String.format("Indexing %s KB of documents", baseTree.getIndexedSize() / 1024));
    Map<String, Compressor> compressors = new LinkedHashMap<>();

    for(int dictionary_context : Arrays.asList(4,5,6)) {
      int model_minPathWeight = 3;
      int dictionary_lookahead = 2;
      System.out.println(String.format("Generating dictionaries"));
      CharTree dictionaryTree = baseTree.copy().index(dictionary_context + dictionary_lookahead, model_minPathWeight);

      compressors.put(String.format("BZ64k_%s", dictionary_context), new Compressor() {
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
    TableOutput output = Compressor.evalTable(source().skip(getModelCount()), compressors, true);
    System.out.println(output.toTextTable());
    System.out.println(output.calcNumberStats().toTextTable());
  }

  @Test
  public void calcCompressorPPM() throws Exception {
    CharTree baseTree = new CharTree();
    System.out.println(String.format("Preparing %s documents", getModelCount()));
    source().limit(getModelCount()).forEach(txt -> {
      //System.out.println(String.format("Adding %s", txt.title));
      baseTree.addDocument(txt.text);
    });
    System.out.println(String.format("Indexing %s KB of documents", baseTree.getIndexedSize() / 1024));

    Map<String, Compressor> compressors = new LinkedHashMap<>();

    int model_minPathWeight = 1;
    for(int ppmModelDepth : Arrays.asList(4,6,8)) {
      for(int encodingContext : Arrays.asList(1,2,3)) {
        CharTree ppmTree = baseTree.copy().index(ppmModelDepth, model_minPathWeight);
        String name = String.format("PPM%s_%s", encodingContext, ppmModelDepth);
        compressors.put(name, Compressor.buildPPMCompressor(ppmTree, encodingContext));
      }
    }

    TableOutput output = Compressor.evalTable(source().skip(getModelCount()), compressors, true);
    System.out.println(output.toTextTable());
    System.out.println(output.calcNumberStats().toTextTable());
  }


}
