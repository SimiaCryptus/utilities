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
        try (MarkdownPrintStream log = new MarkdownPrintStream(new FileOutputStream("reports/calcTweetCompression.md")).addCopy(System.out)){
            int modelCount = 10000;
            int testCount = 100;
            test(log, () -> TweetSentiment.load().limit(modelCount + testCount), modelCount);
        }
    }

    @Test
    @Category(TestCategories.Report.class)
    public void calcShakespeareCompression() throws Exception {
        try (MarkdownPrintStream log = new MarkdownPrintStream(new FileOutputStream("reports/calcShakespeareCompression.md")).addCopy(System.out)){
            int modelCount = 100;
            int testCount = 100;
            test(log, () -> Shakespeare.load().limit(modelCount + testCount), modelCount);
        }
    }

    @Test
    @Category(TestCategories.Report.class)
    public void calcWikiCompression() throws Exception {
        try (MarkdownPrintStream log = new MarkdownPrintStream(new FileOutputStream("reports/calcWikiCompression.md")).addCopy(System.out)){
            int modelCount = 100;
            int testCount = 100;
            test(log, () -> WikiArticle.load().limit(modelCount + testCount), modelCount);
        }
    }

    private void test(MarkdownPrintStream log, Supplier<Stream<? extends TestDocument>> source, int modelCount) {
        Map<String, Compressor> compressors = log.code(()->{
            CharTree baseTree = new CharTree();
            source.get().limit(modelCount).forEach(txt -> baseTree.addDocument(txt.text));
            Map<String, Compressor> map = new LinkedHashMap<>();
            addCompressors(map, baseTree, 4, 2, 3);
            addCompressors(map, baseTree, 5, 2, 3);
            addCompressors(map, baseTree, 6, 2, 3);
            Stream<TestDocument> limit = source.get().limit(modelCount).map(x->x);
            addWordCountCompressor(map, limit.collect(Collectors.toList()));
            Compressor.addGenericCompressors(map);
            return map;
        });
        TableOutput output = Compressor.evalTable(source.get().skip(modelCount), compressors, true);
        //log.out(output.toTextTable());
        log.out(output.calcNumberStats().toTextTable());
    }

    private void addWordCountCompressor(Map<String, Compressor> compressors, List<? extends TestDocument> content) {
        Map<String, Long> wordCounts = content.stream().flatMap(c -> Arrays.stream(c.text.replaceAll("[^\\w\\s]", "").split(" +")))
                        .map(s -> s.trim()).filter(s -> !s.isEmpty()).collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        String dictionary = wordCounts.entrySet().stream()
                .sorted(Comparator.<Map.Entry<String, Long>>comparingLong(e -> -e.getValue())
                        .thenComparing(Comparator.<Map.Entry<String, Long>>comparingLong(e -> -e.getKey().length())))
                .map(x -> x.getKey()).reduce((a, b) -> a + " " + b).get().substring(0, 8 * 1024);
        System.out.println("Common Words Dictionary: " + dictionary.substring(0, 128) + "...");
        compressors.put("LZ8k_commonWords", new Compressor() {

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

    private void addCompressors(Map<String, Compressor> compressors, CharTree baseTree, final int dictionary_context, final int dictionary_lookahead, int model_minPathWeight) {
        CharTree dictionaryTree = baseTree.copy().index(dictionary_context + dictionary_lookahead, model_minPathWeight);
        String genDictionary = dictionaryTree.copy().codec.generateDictionary(8 * 1024, dictionary_context, "", dictionary_lookahead, true);
        String keyDictionary = String.format("LZ8k_%s_%s_%s_generateDictionary", dictionary_context, dictionary_lookahead, model_minPathWeight);
        System.out.println(keyDictionary + ": " + genDictionary.substring(0, 128) + "...");
        compressors.put(keyDictionary, new Compressor() {

            @Override
            public byte[] compress(String text) {
                return CharTreeCodec.encodeLZ(text, genDictionary);
            }

            @Override
            public String uncompress(byte[] data) {
                return CharTreeCodec.decodeLZ(data, genDictionary);
            }
        });
        String genMarkov = dictionaryTree.copy().codec.generateMarkov(8 * 1024, dictionary_context, "");
        String keyMarkov = String.format("LZ8k_%s_%s_%s_generateMarkov", dictionary_context, dictionary_lookahead, model_minPathWeight);
        System.out.println(keyMarkov + ": " + genMarkov.substring(0, Math.min(genMarkov.length(), 128)) + "...");
        compressors.put(keyMarkov, new Compressor() {

            @Override
            public byte[] compress(String text) {
                return CharTreeCodec.encodeLZ(text, genMarkov);
            }

            @Override
            public String uncompress(byte[] data) {
                return CharTreeCodec.decodeLZ(data, genMarkov);
            }
        });
    }


}
