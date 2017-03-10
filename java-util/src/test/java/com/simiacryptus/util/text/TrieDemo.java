package com.simiacryptus.util.text;

import com.simiacryptus.util.binary.Bits;
import com.simiacryptus.util.lang.TimedResult;
import com.simiacryptus.util.test.TestCategories;
import com.simiacryptus.util.test.WikiArticle;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

public class TrieDemo {
    public static final File outPath = new File("src/site/resources/");
    public static final URL outBaseUrl = getUrl("https://simiacryptus.github.io/utilities/java-util/");

    public static URL getUrl(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    // Demo usage for search

    // Demo dict generation

    @Test
    @Category(TestCategories.Report.class)
    public void demoCharTree() throws IOException {
        try (MarkdownPrintStream log = new MarkdownPrintStream(new FileOutputStream("reports/demoCharTree.md")).addCopy(System.out)) {

            log.out("This will demonstrate how to use the CharTrieIndex class for PPM compression\n");

            log.out("First, we load some data into an index:");
            PPMCodec codec = log.code(() -> {
                CharTrieIndex charTrieIndex = new CharTrieIndex();
                WikiArticle.load().limit(100).forEach(article -> {
                    charTrieIndex.addDocument(article.text);
                });
                charTrieIndex.index(3, 1);
                return charTrieIndex.getCodec();
            });

            log.out("\n\nThen, we compress data:");
            WikiArticle wikiArticle = log.code(() -> {
                return WikiArticle.load().skip(100)
                        .filter(article -> article.text.length() > 1024 && article.text.length() < 4096)
                        .findFirst().get();
            });

            String compressed = log.code(() -> {
                Bits bits = codec.encodePPM(wikiArticle.text, 2);
                System.out.print("Bit Length: " + bits.bitLength);
                return bits.toBase64String();
            });

            log.out("\n\nAnd decompress to verify:");
            String uncompressed = log.code(() -> {
                byte[] bytes = Base64.getDecoder().decode(compressed);
                return codec.decodePPM(bytes, 2);
            });

        }
    }

    @Test
    @Category(TestCategories.Report.class)
    public void demoSerialization() throws IOException {
        try (MarkdownPrintStream log = new MarkdownPrintStream(new FileOutputStream("reports/demoSerialization.md")).addCopy(System.out)) {

            log.out("This will demonstrate how to serialize a CharTrie class in compressed format\n");

            log.out("First, we load some data into an index:");
            CharTrieIndex index = log.code(() -> {
                CharTrieIndex charTrieIndex = new CharTrieIndex();
                WikiArticle.load().limit(100).forEach(article -> {
                    charTrieIndex.addDocument(article.text);
                });
                System.out.println(String.format("Indexing %s KB of documents",
                        charTrieIndex.getIndexedSize() / 1024));
                charTrieIndex.index(6, 0);
                return charTrieIndex;
            });
            CharTrie tree = index.truncate();

            log.out("\n\nThen, we compress the tree:");
            String serialized = log.code(() -> {
                byte[] bytes = new FullTrieSerializer().serialize(tree.copy());
                System.out.println(String.format("%s in ram, %s bytes in serialized form, %s%% compression",
                        tree.getMemorySize(), bytes.length, 100 - (bytes.length * 100.0 / tree.getMemorySize())));
                return Base64.getEncoder().encodeToString(bytes);
            });

            log.out("\n\nAnd decompress to verify:");
            int dictionaryLength = log.code(() -> {
                byte[] bytes = Base64.getDecoder().decode(serialized);
                CharTrie restored = new FullTrieSerializer().deserialize(bytes);
                boolean verified = restored.root().equals(tree.root());
                System.out.println(String.format("Tree Verified: %s", verified));
                return bytes.length;
            });

            log.out("Then, we encode the data used to create the dictionary:");
            log.code(() -> {
                PPMCodec codec = tree.getCodec();
                int totalSize = WikiArticle.load().limit(100).map(article -> {
                    TimedResult<Bits> compressed = TimedResult.time(()->codec.encodePPM(article.text, 4));
                    System.out.println(String.format("Serialized %s: %s chars -> %s bytes (%s%%) in %s sec",
                            article.title, article.text.length(), compressed.obj.bitLength / 8.0,
                            compressed.obj.bitLength / (8.0 * article.text.length()),
                            compressed.timeNanos / 1000000000.0));
                    return compressed.obj.getBytes();
                }).mapToInt(bytes->bytes.length).sum();
                return String.format("Compressed %s KB of documents -> %s KB (%s dictionary + %s ppm)",
                        index.getIndexedSize() / 1024, (totalSize + dictionaryLength)/ 1024,
                        dictionaryLength / 1024, totalSize / 1024);
            });

            log.out("For reference, we encode some sample articles that are NOT in the dictionary:");
            log.code(() -> {
                PPMCodec codec = tree.getCodec();
                WikiArticle.load().skip(100).limit(20).forEach(article -> {
                    TimedResult<Bits> compressed = TimedResult.time(()->codec.encodePPM(article.text, 4));
                    System.out.println(String.format("Serialized %s: %s chars -> %s bytes (%s%%) in %s sec",
                            article.title, article.text.length(), compressed.obj.bitLength / 8.0,
                            compressed.obj.bitLength / (8.0 * article.text.length()),
                            compressed.timeNanos / 1000000000.0));
                });
            });

        }
    }


}
