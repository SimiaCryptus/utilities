package com.simiacryptus.util.text;

import com.simiacryptus.util.test.MarkdownPrintStream;
import com.simiacryptus.util.test.TestCategories;
import com.simiacryptus.util.test.WikiArticle;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LanguageModelBuilder {

    private int trainingSize = 1000;
    private int minWeight = 0;
    private int maxLevels = 4;
    private int minArticleSize = 4 * 1024;

    @Test
    @Category(TestCategories.Report.class)
    public void buildLanguageModels() throws IOException {
        try (MarkdownPrintStream log = MarkdownPrintStream.get(this).addCopy(System.out)) {
            process(log, "English", WikiArticle.ENGLISH.load());
            process(log, "French", WikiArticle.FRENCH.load());
            process(log, "German", WikiArticle.GERMAN.load());
        }
    }

    private void process(MarkdownPrintStream log, String languageName, Stream<WikiArticle> load) {
        log.p("\n\n");
        log.h2(languageName);
        CharTrie trie = log.code(() -> {
            List<String> data = load.map(x -> x.getText()).filter(x -> x.length() > minArticleSize)
                    .skip(100)
                    .map(str->str.replaceAll("\\{\\{.*\\}\\}",""))
                    .map(str->str.replaceAll("\\[\\[.*\\]\\]",""))
                    .map(str->str.replaceAll("\\[[^\\]]*\\]",""))
                    .map(str->str.replaceAll("\\{[^\\}]*\\}",""))
                    .map(str->str.replaceAll("\\<[^\\>]*\\>",""))
                    .limit(trainingSize).collect(Collectors.toList());
            CharTrie charTrie = CharTrieIndex.indexFulltext(data, maxLevels, minWeight);
            print(charTrie);
            return charTrie;
        });
        log.code(() -> {
            try(FileOutputStream fos = new FileOutputStream("src/main/resources/"+ languageName +".trie")) {
                byte[] serialized = CompressionUtil.encodeLZ(new ConvolutionalTrieSerializer().serialize(trie));
                System.out.println(String.format("Serialized tree to %s kb", serialized.length / 1024));
                fos.write(serialized);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void print(CharTrie trie) {
        System.out.println("Total Indexed Document (KB): " + trie.getIndexedSize() / 1024);
        System.out.println("Total Node Count: " + trie.getNodeCount());
        System.out.println("Total Index Memory Size (KB): " + trie.getMemorySize() / 1024);
    }



}
