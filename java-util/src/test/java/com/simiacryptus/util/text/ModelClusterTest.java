package com.simiacryptus.util.text;

import com.simiacryptus.util.test.TestCategories;
import com.simiacryptus.util.test.TestDocument;
import com.simiacryptus.util.test.WikiArticle;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Stream;

public abstract class ModelClusterTest {

  public static final File outPath = new File("src/site/resources/");
  public static final URL outBaseUrl = TrieTest.getUrl("https://simiacryptus.github.io/utilities/java-util/");

  protected abstract Stream<? extends TestDocument> source();
  public abstract int getModelCount();

  public static class Wikipedia extends ModelClusterTest {
    int testCount = 1000;

    @Override
    protected Stream<? extends TestDocument> source() {
      return WikiArticle.load().filter(wikiArticle -> {
        int kb = wikiArticle.getText().length() / 1024;
        return kb > 50 && kb < 150;
      }).limit(getModelCount() + testCount);
    }

    @Override
    public int getModelCount() {
      return 20;
    }
  }

  @Test
  @Category(TestCategories.ResearchCode.class)
  public void clusterSharedDictionariesLZ() throws Exception {
    try(MarkdownPrintStream log = new MarkdownPrintStream(new FileOutputStream("reports/clusterSharedDictionariesLZ"+getClass().getSimpleName()+".md")).addCopy(System.out)) {

      int dictionary_context = 7;
      int model_minPathWeight = 3;
      int dictionary_lookahead = 2;
      AtomicInteger index = new AtomicInteger(0);
      Map<String, Compressor> compressors = new LinkedHashMap<>();
      source().parallel().limit(getModelCount()).forEach(text->{
        CharTrieIndex baseTree = new CharTrieIndex();
        baseTree.addDocument(text.getText());
        CharTrie dictionaryTree = baseTree.copy().index(dictionary_context + dictionary_lookahead, model_minPathWeight);
        int i = index.incrementAndGet();
        compressors.put(String.format("LZ_%s", i), new Compressor() {
          String dictionary = dictionaryTree.copy().getGenerator().generateDictionary(8*1024, dictionary_context, "", dictionary_lookahead, true);
          @Override
          public byte[] compress(String text) {
            return CompressionUtil.encodeLZ(text, dictionary);
          }
          @Override
          public String uncompress(byte[] data) {
            return CompressionUtil.decodeLZ(data, dictionary);
          }
        });

        compressors.put(String.format("LZ_raw_%s", i), new Compressor() {
          String dictionary = text.getText();
          @Override
          public byte[] compress(String text) {
            return CompressionUtil.encodeLZ(text, dictionary);
          }
          @Override
          public String uncompress(byte[] data) {
            return CompressionUtil.decodeLZ(data, dictionary);
          }
        });
      });


      TableOutput output = Compressor.evalCompressorCluster(source().skip(getModelCount()), compressors, true);
      log.p(output.toTextTable());
      log.p(output.calcNumberStats().toTextTable());
      log.close();
      String outputDirName = String.format("cluster_%s_LZ/", getClass().getSimpleName());
      output.writeProjectorData(new File(outPath, outputDirName), new URL(outBaseUrl, outputDirName));
    }
  }

  @Test
  @Category(TestCategories.ResearchCode.class)
  public void calcCompressorPPM() throws Exception {
    try(MarkdownPrintStream log = new MarkdownPrintStream(new FileOutputStream("reports/clusterCompressorPPM"+getClass().getSimpleName()+".md")).addCopy(System.out)){
      int ppmModelDepth = 6;
      int model_minPathWeight = 3;
      AtomicInteger index = new AtomicInteger(0);
      int encodingContext = 2;

      log.p("Generating Compressor Models");
      Map<String, Compressor> compressors = new LinkedHashMap<>();
      source().parallel().limit(getModelCount()).forEach(text->{
        CharTrieIndex tree = new CharTrieIndex();
        tree.addDocument(text.getText());
        tree = tree.index(ppmModelDepth, model_minPathWeight);
        String name = String.format("PPM_%s", index.incrementAndGet());
        Compressor ppmCompressor = Compressor.buildPPMCompressor(tree, encodingContext);
        synchronized (compressors) {
          compressors.put(name, ppmCompressor);
        }
        log.p("Completed Model %s", name);
      });

      log.p("Calculating Metrics Table");
      TableOutput output = Compressor.evalCompressorCluster(source().skip(getModelCount()), compressors, true);
      log.p(output.calcNumberStats().toTextTable());
      String outputDirName = String.format("cluster_%s_PPM/", getClass().getSimpleName());
      output.writeProjectorData(new File(outPath, outputDirName), new URL(outBaseUrl, outputDirName));
    }
  }

  @Test
  @Ignore
  @Category(TestCategories.ResearchCode.class)
  public void calcEntropyPPM() throws Exception {
    try(MarkdownPrintStream log = new MarkdownPrintStream(new FileOutputStream("reports/clusterEntropyPPM"+getClass().getSimpleName()+".md")).addCopy(System.out)){
      int ppmModelDepth = 6;
      int model_minPathWeight = 3;
      AtomicInteger index = new AtomicInteger(0);
      int encodingContext = 2;

      log.p("Generating Compressor Models");
      Map<String, Function<TestDocument,Double>> compressors = new LinkedHashMap<>();
      source().parallel().limit(getModelCount()).forEach(text->{
        CharTrieIndex tree = new CharTrieIndex();
        tree.addDocument(text.getText());
        tree = tree.index(ppmModelDepth, model_minPathWeight);
        String name = String.format("ENT_%s", index.incrementAndGet());
        TextGenerator generator = tree.getGenerator();
        Function<TestDocument,Double> ppmCompressor = t -> generator.measureEntropy(t.getText(), 1.0);
        synchronized (compressors) {
          compressors.put(name, ppmCompressor);
        }
        log.p("Completed Model %s", name);
      });

      log.p("Calculating Metrics Table");
      TableOutput output = Compressor.evalCluster(source().skip(getModelCount()), compressors, true);
      log.p(output.calcNumberStats().toTextTable());
      String outputDirName = String.format("cluster_%s_Entropy/", getClass().getSimpleName());
      output.writeProjectorData(new File(outPath, outputDirName), new URL(outBaseUrl, outputDirName));
    }
  }


}
