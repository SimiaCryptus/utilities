package com.simiacryptus.util.text;

import com.simiacryptus.util.binary.Bits;
import com.simiacryptus.util.lang.TimedResult;
import com.simiacryptus.util.test.TestCategories;
import com.simiacryptus.util.test.WikiArticle;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.Map;
import java.util.stream.Collectors;

public class TrieDemo {

    public static URL getUrl(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Category(TestCategories.Report.class)
    public void demoSearch() throws IOException {
        try (MarkdownPrintStream log = MarkdownPrintStream.get().addCopy(System.out)) {

            log.p("This will demonstrate how to use the CharTrieIndex class for searching indexed documents\n");

            log.p("First, we load some data into an index:");
            CharTrieIndex trie = log.code(() -> {
                return new CharTrieIndex();
            });
            Map<Integer, String> documents = log.code(() -> {
                return WikiArticle.load().limit(100).collect(Collectors.toMap(
                        article -> trie.addDocument(article.getText()),
                        article -> article.getTitle()
                ));
            });
            log.p("And then compute the index trie:");
            log.code(() -> {
                System.out.println("Total Indexed Document (KB): " + trie.getIndexedSize() / 1024);
                trie.index(Integer.MAX_VALUE,1);
                System.out.println("Total Node Count: " + trie.getNodeCount());
                System.out.println("Total Index Memory Size (KB): " + trie.getMemorySize() / 1024);
            });
            log.p("Now we can search for a string:");
            Map<String,Long> codec = log.code(() -> {
                IndexNode match = trie.traverse("Computer");
                System.out.println("Found string matches for " + match.getString());
                return match.getCursors().map(cursor -> {
                    return documents.get(cursor.getDocumentId());
                }).collect(Collectors.groupingBy(x -> x, Collectors.counting()));
            });

        }
    }

    @Test
    @Category(TestCategories.Report.class)
    public void demoCharTree() throws IOException {
        try (MarkdownPrintStream log = MarkdownPrintStream.get().addCopy(System.out)) {

            log.p("This will demonstrate how to use the CharTrieIndex class for PPM and shared dictionary compression\n");

            log.p("First, we load some data into an index:");
            CharTrie trie = log.code(() -> {
                CharTrieIndex charTrieIndex = new CharTrieIndex();
                WikiArticle.load().limit(100).forEach(article -> {
                    charTrieIndex.addDocument(article.getText());
                });
                charTrieIndex.index(5, 1);
                return charTrieIndex;
            });
            log.p("And then derive a PPM codec:");
            PPMCodec codec = log.code(() -> {
                return trie.getCodec();
            });

            log.p("\n\nThen, we use it to encode strings:");
            WikiArticle wikiArticle = log.code(() -> {
                return WikiArticle.load().skip(100)
                        .filter(article -> article.getText().length() > 1024 && article.getText().length() < 4096)
                        .findFirst().get();
            });
            {
                String compressed = log.code(() -> {
                    Bits bits = codec.encodePPM(wikiArticle.getText(), 2);
                    System.out.print("Bit Length: " + bits.bitLength);
                    return bits.toBase64String();
                });

                log.p("\n\nAnd decompress to verify:");
                String uncompressed = log.code(() -> {
                    byte[] bytes = Base64.getDecoder().decode(compressed);
                    return codec.decodePPM(bytes, 2);
                });
            }


            log.p("\n\nFor faster compression, we can define a dictionary for use with Deflate:");
            String dictionary = log.code(() -> {
                return trie.getGenerator().generateDictionary(8*1024, 3, "", 1, true);
            });
            {
                log.p("\n\nThen, we use it to encode strings:");
                String compressed = log.code(() -> {
                    byte[] bits = CompressionUtil.encodeLZ(wikiArticle.getText(), dictionary);
                    System.out.print("Compressed Bytes: " + bits.length);
                    return Base64.getEncoder().encodeToString(bits);
                });

                log.p("\n\nAnd decompress to verify:");
                String uncompressed = log.code(() -> {
                    byte[] bytes = Base64.getDecoder().decode(compressed);
                    return CompressionUtil.decodeLZ(bytes, dictionary);
                });
            }

        }
    }

    @Test
    @Category(TestCategories.Report.class)
    public void demoSerialization() throws IOException {
        try (MarkdownPrintStream log = MarkdownPrintStream.get().addCopy(System.out)) {

            log.p("This will demonstrate how to serialize a CharTrie class in compressed format\n");

            log.p("First, we load some data into an index:");
            CharTrieIndex index = log.code(() -> {
                CharTrieIndex charTrieIndex = new CharTrieIndex();
                WikiArticle.load().limit(100).forEach(article -> {
                    charTrieIndex.addDocument(article.getText());
                });
                System.out.println(String.format("Indexing %s KB of documents",
                        charTrieIndex.getIndexedSize() / 1024));
                charTrieIndex.index(6, 0);
                return charTrieIndex;
            });
            CharTrie tree = index.truncate();

            log.p("\n\nThen, we compress the tree:");
            String serialized = log.code(() -> {
                byte[] bytes = new FullTrieSerializer().serialize(tree.copy());
                System.out.println(String.format("%s in ram, %s bytes in serialized form, %s%% compression",
                        tree.getMemorySize(), bytes.length, 100 - (bytes.length / tree.getMemorySize())));
                return Base64.getEncoder().encodeToString(bytes);
            });

            log.p("\n\nAnd decompress to verify:");
            int dictionaryLength = log.code(() -> {
                byte[] bytes = Base64.getDecoder().decode(serialized);
                CharTrie restored = new FullTrieSerializer().deserialize(bytes);
                boolean verified = restored.root().equals(tree.root());
                System.out.println(String.format("Tree Verified: %s", verified));
                return bytes.length;
            });

            log.p("Then, we encode the data used to create the dictionary:");
            log.code(() -> {
                PPMCodec codec = tree.getCodec();
                int totalSize = WikiArticle.load().limit(100).map(article -> {
                    TimedResult<Bits> compressed = TimedResult.time(()->codec.encodePPM(article.getText(), 4));
                    System.out.println(String.format("Serialized %s: %s chars -> %s bytes (%s%%) in %s sec",
                            article.getTitle(), article.getText().length(), compressed.obj.bitLength * 100.0 / 8.0,
                            compressed.obj.bitLength / (8.0 * article.getText().length()),
                            compressed.timeNanos / 1000000000.0));
                    return compressed.obj.getBytes();
                }).mapToInt(bytes->bytes.length).sum();
                return String.format("Compressed %s KB of documents -> %s KB (%s dictionary + %s ppm)",
                        index.getIndexedSize() / 1024, (totalSize + dictionaryLength)/ 1024,
                        dictionaryLength / 1024, totalSize / 1024);
            });

            log.p("For reference, we encode some sample articles that are NOT in the dictionary:");
            log.code(() -> {
                PPMCodec codec = tree.getCodec();
                WikiArticle.load().skip(100).limit(20).forEach(article -> {
                    TimedResult<Bits> compressed = TimedResult.time(()->codec.encodePPM(article.getText(), 4));
                    System.out.println(String.format("Serialized %s: %s chars -> %s bytes (%s%%) in %s sec",
                            article.getTitle(), article.getText().length(), compressed.obj.bitLength * 100.0 / 8.0,
                            compressed.obj.bitLength / (8.0 * article.getText().length()),
                            compressed.timeNanos / 1000000000.0));
                });
            });

        }
    }


}
