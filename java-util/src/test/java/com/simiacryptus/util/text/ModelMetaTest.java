package com.simiacryptus.util.text;

import com.simiacryptus.util.test.TestDocument;
import com.simiacryptus.util.test.TweetSentiment;
import com.simiacryptus.util.test.WikiArticle;
import org.junit.Test;

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

  public static class TweetModelMetaTest extends ModelMetaTest {
    int testCount = 100;
    private int modelCount = 100000;

    @Override
    protected Stream<? extends TestDocument> source() {
      return TweetSentiment.load().limit(getModelCount() + testCount);
    }

    @Override
    public void calcSharedDictionaries() throws Exception {
      super.calcSharedDictionaries();
    }

    @Override
    public void calcPPM() throws Exception {
      super.calcPPM();
    }

    @Override
    public int getModelCount() {
      return modelCount;
    }

  }

  public static class WikiModelMetaTest extends ModelMetaTest {
    int testCount = 100;
    int modelCount = 100;

    @Override
    protected Stream<? extends TestDocument> source() {
      return WikiArticle.load().limit(getModelCount() + testCount);
    }

    @Override
    public void calcSharedDictionaries() throws Exception {
      super.calcSharedDictionaries();
    }

    @Override
    public void calcPPM() throws Exception {
      super.calcPPM();
    }

    @Override
    public int getModelCount() {
      return modelCount;
    }
  }

  @Test
  public void calcSharedDictionaries() throws Exception {
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
    String outputDirName = "metaCompressionZ/";
    output.writeProjectorData(new File(outPath, outputDirName), new URL(outBaseUrl, outputDirName));
  }

  @Test
  public void calcPPM() throws Exception {
    CharTree baseTree = new CharTree();
    System.out.println(String.format("Preparing %s documents", getModelCount()));
    source().limit(getModelCount()).forEach(txt -> {
      //System.out.println(String.format("Adding %s", txt.title));
      baseTree.addDocument(txt.text);
    });
    System.out.println(String.format("Indexing %s KB of documents", baseTree.getIndexedSize() / 1024));

    Map<String, Compressor> compressors = new LinkedHashMap<>();

    int model_minPathWeight = 1;
    for(int ppmModelDepth : Arrays.asList(8,9)) {
      for(int encodingContext : Arrays.asList(2,3,4)) {
        CharTree ppmTree = baseTree.copy().index(ppmModelDepth, model_minPathWeight);
        String name = String.format("PPM%s_%s", encodingContext, ppmModelDepth);
        compressors.put(name, Compressor.buildPPMCompressor(ppmTree, encodingContext));
      }
    }

    TableOutput output = Compressor.evalTable(source().skip(getModelCount()), compressors, true);
    System.out.println(output.toTextTable());
    String outputDirName = "metaCompressionPPM/";
    output.writeProjectorData(new File(outPath, outputDirName), new URL(outBaseUrl, outputDirName));
  }


}
