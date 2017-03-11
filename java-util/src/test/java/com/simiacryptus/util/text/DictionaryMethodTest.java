package com.simiacryptus.util.text;

import com.simiacryptus.util.test.*;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.FileOutputStream;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DictionaryMethodTest {

    @Test
    @Category(TestCategories.Report.class)
    public void calcTweetCompression() throws Exception {
        try (MarkdownPrintStream log = new MarkdownPrintStream(new FileOutputStream("reports/calcTweetCompression.md")).addCopy(System.out)) {
            int modelCount = 10000;
            int testCount = 100;
            log.out("This notebook uses a variety of methods to generate compression dictionaries for a database of Tweets\n");
            test(log, () -> TweetSentiment.load().limit(modelCount + testCount), modelCount);
        }
    }

    @Test
    @Category(TestCategories.Report.class)
    public void calcShakespeareCompression() throws Exception {
        try (MarkdownPrintStream log = new MarkdownPrintStream(new FileOutputStream("reports/calcShakespeareCompression.md")).addCopy(System.out)) {
            int modelCount = 100;
            int testCount = 100;
            log.out("This notebook uses a variety of methods to generate compression dictionaries for a database of Shakespeare text\n");
            test(log, () -> Shakespeare.load().limit(modelCount + testCount), modelCount);
        }
    }

    @Test
    @Category(TestCategories.Report.class)
    public void calcWikiCompression() throws Exception {
        try (MarkdownPrintStream log = new MarkdownPrintStream(new FileOutputStream("reports/calcWikiCompression.md")).addCopy(System.out)) {
            int modelCount = 100;
            int testCount = 100;
            log.out("This notebook uses a variety of methods to generate compression dictionaries for a database of Wikipedia articles\n");
            test(log, () -> WikiArticle.load().limit(modelCount + testCount), modelCount);
        }
    }

    private void test(MarkdownPrintStream log, Supplier<Stream<? extends TestDocument>> source, int modelCount) {
        CharTrieIndex baseTree = new CharTrieIndex();
        source.get().limit(modelCount).forEach(txt -> baseTree.addDocument(txt.getText()));
        Map<String, Compressor> compressors = new LinkedHashMap<>();
        addCompressors(log, compressors, baseTree, 4, 2, 3);
        addCompressors(log, compressors, baseTree, 5, 2, 3);
        addCompressors(log, compressors, baseTree, 6, 2, 3);
        Stream<TestDocument> limit = source.get().limit(modelCount).map(x -> x);
        addWordCountCompressor(log, compressors, limit.collect(Collectors.toList()));
        Compressor.addGenericCompressors(compressors);
        TableOutput output = Compressor.evalCompressor(source.get().skip(modelCount), compressors, true);
        //log.out(output.toTextTable());
        log.out(output.calcNumberStats().toTextTable());
    }

    private void addWordCountCompressor(MarkdownPrintStream log, Map<String, Compressor> compressors, List<? extends TestDocument> content) {
        Map<String, Long> wordCounts = content.stream().flatMap(c -> Arrays.stream(c.getText().replaceAll("[^\\w\\s]", "").split(" +")))
                .map(s -> s.trim()).filter(s -> !s.isEmpty()).collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        String dictionary = wordCounts.entrySet().stream()
                .sorted(Comparator.<Map.Entry<String, Long>>comparingLong(e -> -e.getValue())
                        .thenComparing(Comparator.<Map.Entry<String, Long>>comparingLong(e -> -e.getKey().length())))
                .map(x -> x.getKey()).reduce((a, b) -> a + " " + b).get().substring(0, 8 * 1024);
        String key = "LZ8k_commonWords";
        int dictSampleSize = 512;
        log.out("Common Words Dictionary %s: %s...\n", key, dictionary.length()> dictSampleSize ?(dictionary.substring(0, dictSampleSize) + "..."):dictionary);
        compressors.put(key, new Compressor() {
            @Override
            public byte[] compress(String text) {
                return CompressionUtil.encodeLZ(text, dictionary);
            }

            @Override
            public String uncompress(byte[] data) {
                return CompressionUtil.decodeLZ(data, dictionary);
            }
        });
    }

    private void addCompressors(MarkdownPrintStream log, Map<String, Compressor> compressors, CharTrieIndex baseTree, final int dictionary_context, final int dictionary_lookahead, int model_minPathWeight) {
        CharTrie dictionaryTree = baseTree.copy().index(dictionary_context + dictionary_lookahead, model_minPathWeight);
        String genDictionary = dictionaryTree.copy().getGenerator().generateDictionary(8 * 1024, dictionary_context, "", dictionary_lookahead, true);
        String keyDictionary = String.format("LZ8k_%s_%s_%s_generateDictionary", dictionary_context, dictionary_lookahead, model_minPathWeight);
        int dictSampleSize = 512;
        log.out("Adding Compressor %s: %s...\n", keyDictionary, genDictionary.length() > dictSampleSize ? (genDictionary.substring(0, dictSampleSize) + "...") : genDictionary);
        compressors.put(keyDictionary, new Compressor() {

            @Override
            public byte[] compress(String text) {
                return CompressionUtil.encodeLZ(text, genDictionary);
            }

            @Override
            public String uncompress(byte[] data) {
                return CompressionUtil.decodeLZ(data, genDictionary);
            }
        });
        String genMarkov = dictionaryTree.copy().getGenerator().generateMarkov(8 * 1024, dictionary_context, "");
        String keyMarkov = String.format("LZ8k_%s_%s_%s_generateMarkov", dictionary_context, dictionary_lookahead, model_minPathWeight);
        log.out("Adding Compressor %s: %s...\n", keyMarkov, genMarkov.length() > dictSampleSize ? (genMarkov.substring(0, dictSampleSize) + "...") : genMarkov);
        compressors.put(keyMarkov, new Compressor() {

            @Override
            public byte[] compress(String text) {
                return CompressionUtil.encodeLZ(text, genMarkov);
            }

            @Override
            public String uncompress(byte[] data) {
                return CompressionUtil.decodeLZ(data, genMarkov);
            }
        });
    }


}