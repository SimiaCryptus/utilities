package com.simiacryptus.util.text;

import com.simiacryptus.util.test.TestCategories;
import com.simiacryptus.util.test.TestDocument;
import com.simiacryptus.util.test.WikiArticle;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class ModelClusterTest {

  public static final File outPath = new File("src/site/resources/");
  public static final URL outBaseUrl = CharTreeTest.getUrl("https://simiacryptus.github.io/utilities/java-util/");

  protected abstract Stream<? extends TestDocument> source();
  public abstract int getModelCount();

  public static class Wikipedia extends ModelClusterTest {
    int testCount = 100;

    @Override
    protected Stream<? extends TestDocument> source() {
      return WikiArticle.load().limit(getModelCount() + testCount);
    }

    @Override
    public int getModelCount() {
      return 20;
    }
  }

  @Test
  @Category(TestCategories.ResearchCode.class)
  public void clusterSharedDictionariesLZ() throws Exception {
    try(MarkdownPrintStream log = new MarkdownPrintStream(new FileOutputStream("src/site/markdown/clusterSharedDictionariesLZ"+getClass().getSimpleName()+".md")).addCopy(System.out)) {
      int dictionary_context = 5;
      int model_minPathWeight = 3;
      int dictionary_lookahead = 2;
      int index = 0;

      Map<String, Compressor> compressors = new LinkedHashMap<>();
      Stream<TestDocument> limit = source().limit(getModelCount()).map(x->x);
      List<TestDocument> collect = limit.collect(Collectors.toList());
      for(TestDocument text : collect) {
        CharTree baseTree = new CharTree();
        baseTree.addDocument(text.text);
        CharTree dictionaryTree = baseTree.copy().index(dictionary_context + dictionary_lookahead, model_minPathWeight);
        compressors.put(String.format("LZ_%s", index++), new Compressor() {
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
      log.out(output.toTextTable());
      log.close();
      String outputDirName = String.format("cluster_%s_LZ/", getClass().getSimpleName());
      output.writeProjectorData(new File(outPath, outputDirName), new URL(outBaseUrl, outputDirName));
    }
  }

  @Test
  @Category(TestCategories.ResearchCode.class)
  public void calcCompressorPPM() throws Exception {
    try(MarkdownPrintStream log = new MarkdownPrintStream(new FileOutputStream("src/site/markdown/calcCompressorPPM"+getClass().getSimpleName()+".md")).addCopy(System.out)){
      int ppmModelDepth = 6;
      int model_minPathWeight = 3;
      int index = 0;
      int encodingContext = 2;

      Map<String, Compressor> compressors = new LinkedHashMap<>();
      Stream<TestDocument> stream = source().limit(getModelCount()).map(x -> x);
      for(TestDocument text : stream.collect(Collectors.toList())) {
        CharTree baseTree = new CharTree();
        baseTree.addDocument(text.text);
        CharTree ppmTree = baseTree.copy().index(ppmModelDepth, model_minPathWeight);

        String name = String.format("LZ_%s", index++);
        compressors.put(name, Compressor.buildPPMCompressor(ppmTree, encodingContext));
      }

      TableOutput output = Compressor.evalTable(source().skip(getModelCount()), compressors, true);
      log.out(output.toTextTable());
      String outputDirName = String.format("cluster_%s_PPM/", getClass().getSimpleName());
      output.writeProjectorData(new File(outPath, outputDirName), new URL(outBaseUrl, outputDirName));
    }
  }


}
