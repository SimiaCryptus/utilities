package com.simiacryptus.util.text;

import com.simiacryptus.util.test.EnglishWords;
import com.simiacryptus.util.test.TestCategories;
import com.simiacryptus.util.test.TestDocument;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class TermMetaTest {
  int testCount = 1000;
  int modelCount = 15000;

  protected Stream<? extends TestDocument> source() {
    return EnglishWords.load().limit(modelCount + testCount);
  }


  @Test
  @Category(TestCategories.Report.class)
  public void calcCompressorPPM() throws Exception {
    MarkdownPrintStream log = MarkdownPrintStream.get().addCopy(System.out);
    CharTrieIndex baseTree = new CharTrieIndex();
    log.p("Preparing %s documents", modelCount);
    source().limit(modelCount).forEach(txt -> {
      //System.p.println(String.format("Adding %s", txt.title));
      baseTree.addDocument(txt.getText());
    });
    log.p("Indexing %s KB of documents", baseTree.getIndexedSize() / 1024);

    Map<String, Compressor> compressors = new LinkedHashMap<>();

    int model_minPathWeight = 1;
    for (int ppmModelDepth : Arrays.asList(8, 9, 10, 11, 12)) {
      for (int encodingContext : Arrays.asList(0, 1, 2, 3, 4, 5)) {
        CharTrie ppmTree = baseTree.copy().index(ppmModelDepth, model_minPathWeight);
        String name = String.format("PPM%s_%s", encodingContext, ppmModelDepth);
        compressors.put(name, Compressor.buildPPMCompressor(ppmTree, encodingContext));
      }
    }

    TableOutput output = Compressor.evalCompressor(source().skip(modelCount), compressors, true);
    log.p(output.toTextTable());
    log.p(output.calcNumberStats().toTextTable());
    log.close();
  }
}
