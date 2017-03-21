package com.simiacryptus.util.text;

import com.simiacryptus.util.binary.Bits;
import com.simiacryptus.util.lang.TimedResult;
import com.simiacryptus.util.test.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        try (MarkdownPrintStream log = MarkdownPrintStream.get(this).addCopy(System.out)) {

            log.p("This will demonstrate how to use the CharTrieIndex class for searching indexed documents\n");

            log.p("First, we load some data into an index:");
            CharTrieIndex trie = log.code(() -> {
                return new CharTrieIndex();
            });
            Map<Integer, String> documents = log.code(() -> {
                return WikiArticle.ENGLISH.load().limit(100).collect(Collectors.toMap(
                        article -> trie.addDocument(article.getText()),
                        article -> article.getTitle()
                ));
            });
            log.p("And then compute the index trie:");
            log.code(() -> {
                trie.index(Integer.MAX_VALUE,1);
                print(trie);
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
        try (MarkdownPrintStream log = MarkdownPrintStream.get(this).addCopy(System.out)) {

            log.p("This will demonstrate how to use the CharTrieIndex class for PPM and shared dictionary compression\n");

            log.p("First, we load some data into an index:");
            CharTrie trie = log.code(() -> {
                CharTrieIndex charTrieIndex = new CharTrieIndex();
                WikiArticle.ENGLISH.load().limit(100).forEach(article -> {
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
                return WikiArticle.ENGLISH.load().skip(100)
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
                    return CompressionUtil.decodeLZToString(bytes, dictionary);
                });
            }

        }
    }

    @Test
    @Category(TestCategories.ResearchCode.class)
    public void demoTweetGeneration() throws IOException {
        try (MarkdownPrintStream log = MarkdownPrintStream.get(this).addCopy(System.out)) {
            int testingSize = 100;
            int trainingSize = 50000;
            int minWeight = 0;
            int groups = trainingSize / 50000;
            int maxLevels = 7;
            int lookahead = 1;
            int dictionarySampleSize = 4 * 1024;
            int context = 5;
            log.p("First, we load positive and negative sentiment tweets into two seperate models");
            List<TweetSentiment> tweetsPositive = log.code(() -> {
                ArrayList<TweetSentiment> list = new ArrayList<>(TweetSentiment.load()
                        .filter(x -> x.category == 1).limit(testingSize + trainingSize).collect(Collectors.toList()));
                Collections.shuffle(list);
                return list;
            });
            List<TweetSentiment> tweetsNegative= log.code(() -> {
                ArrayList<TweetSentiment> list = new ArrayList<>(TweetSentiment.load()
                        .filter(x -> x.category == 0).limit(testingSize + trainingSize).collect(Collectors.toList()));
                Collections.shuffle(list);
                return list;
            });
            CharTrie triePositive = log.code(() -> {
                CharTrie charTrie = tweetsPositive.stream().skip(testingSize).limit(trainingSize)
                        .collect(Collectors.groupingByConcurrent(x -> new Random().nextInt(groups), Collectors.toList())).entrySet().stream()
                        .parallel().map(e -> {
                            CharTrieIndex charTrieIndex = new CharTrieIndex();
                            e.getValue().forEach(article -> charTrieIndex.addDocument(article.getText()));
                            charTrieIndex.index(maxLevels, minWeight);
                            return charTrieIndex.truncate();
                        }).reduce(CharTrie::add).get();
                print(charTrie);
                return charTrie;
            });
            CharTrie trieNegative = log.code(() -> {
                CharTrie charTrie = tweetsNegative.stream().skip(testingSize).limit(trainingSize)
                        .collect(Collectors.groupingByConcurrent(x -> new Random().nextInt(groups), Collectors.toList())).entrySet().stream()
                        .parallel().map(e -> {
                            CharTrieIndex charTrieIndex = new CharTrieIndex();
                            e.getValue().forEach(article -> charTrieIndex.addDocument(article.getText()));
                            charTrieIndex.index(maxLevels, minWeight);
                            return charTrieIndex.truncate();
                        }).reduce(CharTrie::add).get();
                print(charTrie);
                return charTrie;
            });
            log.p("These source models produce similar representative texts:");
            log.code(() -> {
                return trieNegative.getGenerator().generateDictionary(dictionarySampleSize, context, "", lookahead, true);
            });
            log.code(() -> {
                return triePositive.getGenerator().generateDictionary(dictionarySampleSize, context, "", lookahead, true);
            });
            log.p("The tree can also be reversed:");
            log.code(() -> {
                return triePositive.reverse().getGenerator().generateDictionary(dictionarySampleSize, context, "", lookahead, true);
            });
            log.p("And can be combined with a variety of operations:");
            CharTrie trieProduct = log.code(() -> {
                return triePositive.product(trieNegative);
            });
            CharTrie trieSum = log.code(() -> {
                CharTrie trie = triePositive.add(trieNegative);
                print(trie);
                return trie;
            });
            CharTrie negativeVector = log.code(() -> {
                return trieNegative.divide(trieSum, 100);
            });
            CharTrie positiveVector = log.code(() -> {
                return triePositive.divide(trieSum, 100);
            });
            log.p("These each produce consistent text extracts:");
            IntStream.range(0,3).forEach(l->{
                IntStream.range(1,context).forEach(ctx->{
                    log.code(() -> {
                        System.out.println(String.format("Sum characteristic string with %s context and %s lookahead", ctx, l));
                        return trieSum.getGenerator().generateDictionary(dictionarySampleSize, ctx, "", l, true);
                    });
                    log.code(() -> {
                        System.out.println(String.format("Product characteristic string with %s context and %s lookahead", ctx, l));
                        return trieProduct.getGenerator().generateDictionary(dictionarySampleSize, ctx, "", l, true);
                    });
                    log.code(() -> {
                        System.out.println(String.format("Negative characteristic string with %s context and %s lookahead", ctx, l));
                        return negativeVector.getGenerator().generateDictionary(dictionarySampleSize, ctx, "", l, true);
                    });
                    log.code(() -> {
                        System.out.println(String.format("Positive characteristic string with %s context and %s lookahead", ctx, l));
                        return positiveVector.getGenerator().generateDictionary(dictionarySampleSize, ctx, "", l, true);
                    });
                });
            });
        }
    }

    @Test
    @Category(TestCategories.ResearchCode.class)
    public void demoReversal() throws IOException {
        try (MarkdownPrintStream log = MarkdownPrintStream.get(this).addCopy(System.out)) {
            int testingSize = 100;
            int trainingSize = 50000;
            int minWeight = 0;
            int groups = trainingSize / 50000;
            int maxLevels = 7;
            int lookahead = 1;
            int dictionarySampleSize = 4 * 1024;
            int context = 5;
            log.p("First, we load text into a model");
            List<TweetSentiment> tweetsPositive = log.code(() -> {
                ArrayList<TweetSentiment> list = new ArrayList<>(TweetSentiment.load()
                        .filter(x -> x.category == 1).limit(testingSize + trainingSize).collect(Collectors.toList()));
                Collections.shuffle(list);
                return list;
            });
            CharTrie triePositive = log.code(() -> {
                CharTrie charTrie = tweetsPositive.stream().skip(testingSize).limit(trainingSize)
                        .collect(Collectors.groupingByConcurrent(x -> new Random().nextInt(groups), Collectors.toList())).entrySet().stream()
                        .parallel().map(e -> {
                            CharTrieIndex charTrieIndex = new CharTrieIndex();
                            e.getValue().forEach(article -> charTrieIndex.addDocument(article.getText()));
                            charTrieIndex.index(maxLevels, minWeight);
                            return charTrieIndex.truncate();
                        }).reduce(CharTrie::add).get();
                print(charTrie);
                return charTrie;
            });
            log.p("This source model produces representative texts:");
            log.code(() -> {
                return triePositive.getGenerator().generateDictionary(dictionarySampleSize, context, "", lookahead, true);
            });
            log.p("The tree can also be reversed:");
            log.code(() -> {
                return triePositive.reverse().getGenerator().generateDictionary(dictionarySampleSize, context, "", lookahead, true);
            });
        }
    }

    @Test
    @Category(TestCategories.ResearchCode.class)
    public void demoTweetClassification() throws IOException {
        try (MarkdownPrintStream log = MarkdownPrintStream.get(this).addCopy(System.out)) {
            int testingSize = 1000;
            int trainingSize = 500000;
            int minWeight = 1;
            int groups = trainingSize / 50000;
            int maxLevels = 7;
            log.p("First, we load positive and negative sentiment tweets into two seperate models");
            List<TweetSentiment> tweetsPositive = log.code(() -> {
                ArrayList<TweetSentiment> list = new ArrayList<>(TweetSentiment.load()
                        .filter(x -> x.category == 1).limit(testingSize + trainingSize).collect(Collectors.toList()));
                Collections.shuffle(list);
                return list;
            });
            List<TweetSentiment> tweetsNegative = log.code(() -> {
                ArrayList<TweetSentiment> list = new ArrayList<>(TweetSentiment.load()
                        .filter(x -> x.category == 0).limit(testingSize + trainingSize).collect(Collectors.toList()));
                Collections.shuffle(list);
                return list;
            });
            CharTrie triePositive = log.code(() -> {
                CharTrie charTrie = tweetsPositive.stream().skip(testingSize).limit(trainingSize)
                        .collect(Collectors.groupingByConcurrent(x -> new Random().nextInt(groups), Collectors.toList())).entrySet().stream()
                        .parallel().map(e -> {
                            CharTrieIndex charTrieIndex = new CharTrieIndex();
                            e.getValue().forEach(article -> charTrieIndex.addDocument(article.getText()));
                            charTrieIndex.index(maxLevels, minWeight);
                            return charTrieIndex.truncate();
                        }).reduce(CharTrie::add).get();
                print(charTrie);
                return charTrie;
            });
            CharTrie trieNegative = log.code(() -> {
                CharTrie charTrie = tweetsNegative.stream().skip(testingSize).limit(trainingSize)
                        .collect(Collectors.groupingByConcurrent(x -> new Random().nextInt(groups), Collectors.toList())).entrySet().stream()
                        .parallel().map(e -> {
                            CharTrieIndex charTrieIndex = new CharTrieIndex();
                            e.getValue().forEach(article -> charTrieIndex.addDocument(article.getText()));
                            charTrieIndex.index(maxLevels, minWeight);
                            return charTrieIndex.truncate();
                        }).reduce(CharTrie::add).get();
                print(charTrie);
                return charTrie;
            });
            log.p("Each model can be used out-of-the-box to perform classification:");
            log.code(() -> {
                PPMCodec codecPositive = triePositive.getCodec();
                PPMCodec codecNegative = trieNegative.getCodec();
                double positiveAccuracy = 100.0 * tweetsPositive.stream().limit(testingSize).mapToDouble(tweet -> {
                    Bits encodeNeg = codecNegative.encodePPM(tweet.getText(), Integer.MAX_VALUE);
                    Bits encodePos = codecPositive.encodePPM(tweet.getText(), Integer.MAX_VALUE);
                    int prediction = (encodeNeg.bitLength > encodePos.bitLength) ? 1 : 0;
                    return prediction == tweet.category ? 1 : 0;
                }).average().getAsDouble();
                double negativeAccuracy = 100.0 * tweetsNegative.stream().limit(testingSize).mapToDouble(tweet -> {
                    Bits encodeNeg = codecNegative.encodePPM(tweet.getText(), Integer.MAX_VALUE);
                    Bits encodePos = codecPositive.encodePPM(tweet.getText(), Integer.MAX_VALUE);
                    int prediction = (encodeNeg.bitLength > encodePos.bitLength) ? 1 : 0;
                    return prediction == tweet.category ? 1 : 0;
                }).average().getAsDouble();
                return String.format("Accuracy = %.3f%%, %.3f%%", positiveAccuracy, negativeAccuracy);
            });
            log.p("Or can be combined with a variety of operations:");
            CharTrie trieSum = log.code(() -> {
                return triePositive.add(trieNegative);
            });
            CharTrie negativeVector = log.code(() -> {
                return trieNegative.divide(trieSum, 100);
            });
            CharTrie positiveVector = log.code(() -> {
                return triePositive.divide(trieSum, 100);
            });
            log.p("These composite tries can also be used to perform classification:");
            log.code(() -> {
                PPMCodec codecPositive = positiveVector.getCodec();
                PPMCodec codecNegative = negativeVector.getCodec();
                double positiveAccuracy = 100.0 * tweetsPositive.stream().limit(testingSize).mapToDouble(tweet -> {
                    Bits encodeNeg = codecNegative.encodePPM(tweet.getText(), Integer.MAX_VALUE);
                    Bits encodePos = codecPositive.encodePPM(tweet.getText(), Integer.MAX_VALUE);
                    int prediction = (encodeNeg.bitLength > encodePos.bitLength) ? 1 : 0;
                    return prediction == tweet.category ? 1 : 0;
                }).average().getAsDouble();
                double negativeAccuracy = 100.0 * tweetsNegative.stream().limit(testingSize).mapToDouble(tweet -> {
                    Bits encodeNeg = codecNegative.encodePPM(tweet.getText(), 2);
                    Bits encodePos = codecPositive.encodePPM(tweet.getText(), 2);
                    int prediction = (encodeNeg.bitLength > encodePos.bitLength) ? 1 : 0;
                    return prediction == tweet.category ? 1 : 0;
                }).average().getAsDouble();
                return String.format("Accuracy = %.3f%%, %.3f%%", positiveAccuracy, negativeAccuracy);
            });
        }
    }

    @Test
    @Category(TestCategories.Report.class)
    public void demoLanguageClassification() throws IOException {
        try (MarkdownPrintStream log = MarkdownPrintStream.get(this).addCopy(System.out)) {
            int testingSize = 100;
            int trainingSize = 5;
            int minWeight = 1;
            int maxLevels = 5;
            int minArticleSize = 4 * 1024;
            log.p("First, we load positive and negative sentiment tweets into two seperate models");
            List<WikiArticle> english = log.code(() -> {
                return new ArrayList<>(WikiArticle.ENGLISH.load().filter(x->x.getText().length()> minArticleSize)
                        .limit(testingSize + trainingSize).collect(Collectors.toList()));
            });
            List<WikiArticle> french = log.code(() -> {
                return new ArrayList<>(WikiArticle.FRENCH.load().filter(x->x.getText().length()> minArticleSize)
                        .limit(testingSize + trainingSize).collect(Collectors.toList()));
            });
            CharTrie trieEnglish = log.code(() -> {
                CharTrie charTrie = CharTrieIndex.create(english.subList(0,trainingSize)
                        .stream().map(x->x.getText()).collect(Collectors.toList()), maxLevels, minWeight);
                print(charTrie);
                return charTrie;
            });
            CharTrie trieFrench = log.code(() -> {
                CharTrie charTrie = CharTrieIndex.create(french.subList(testingSize,french.size())
                        .stream().map(x->x.getText()).collect(Collectors.toList()), maxLevels, minWeight);
                print(charTrie);
                return charTrie;
            });
            log.p("Each model can be used out-of-the-box to perform classification:");
            log.code(() -> {
                PPMCodec codecA = trieEnglish.getCodec();
                PPMCodec codecB = trieFrench.getCodec();
                double englishAccuracy = 100.0 * english.stream().limit(testingSize).mapToDouble(tweet -> {
                    Bits encodeB = codecB.encodePPM(tweet.getText(), Integer.MAX_VALUE);
                    Bits encodeA = codecA.encodePPM(tweet.getText(), Integer.MAX_VALUE);
                    return (encodeB.bitLength > encodeA.bitLength) ? 1 : 0;
                }).average().getAsDouble();
                double frenchAccuracy = 100.0 * french.stream().limit(testingSize).mapToDouble(tweet -> {
                    Bits encodeB = codecB.encodePPM(tweet.getText(), Integer.MAX_VALUE);
                    Bits encodeA = codecA.encodePPM(tweet.getText(), Integer.MAX_VALUE);
                    return (encodeB.bitLength < encodeA.bitLength) ? 1 : 0;
                }).average().getAsDouble();
                return String.format("Accuracy = %.3f%%, %.3f%%", englishAccuracy, frenchAccuracy);
            });
            log.p("Or can be combined with a variety of operations:");
            CharTrie trieSum = log.code(() -> {
                return trieEnglish.add(trieFrench);
            });
            CharTrie frenchVector = log.code(() -> {
                return trieFrench.divide(trieSum, 100);
            });
            CharTrie englishVector = log.code(() -> {
                return trieEnglish.divide(trieSum, 100);
            });
            log.p("For fun:");
            log.code(() -> {
                return trieSum.getGenerator().generateDictionary(1024,3,"",0,true);
            });
            log.code(() -> {
                return trieEnglish.product(trieFrench).getGenerator().generateDictionary(1024,3,"",0,true);
            });
            log.p("These composite tries can also be used to perform classification:");
            log.code(() -> {
                PPMCodec codecA = englishVector.getCodec();
                PPMCodec codecB = frenchVector.getCodec();
                double englishAccuracy = 100.0 * english.stream().limit(testingSize).mapToDouble(tweet -> {
                    Bits encodeB = codecB.encodePPM(tweet.getText(), Integer.MAX_VALUE);
                    Bits encodeA = codecA.encodePPM(tweet.getText(), Integer.MAX_VALUE);
                    return (encodeB.bitLength > encodeA.bitLength) ? 1 : 0;
                }).average().getAsDouble();
                double frenchAccuracy = 100.0 * french.stream().limit(testingSize).mapToDouble(tweet -> {
                    Bits encodeB = codecB.encodePPM(tweet.getText(), Integer.MAX_VALUE);
                    Bits encodeA = codecA.encodePPM(tweet.getText(), Integer.MAX_VALUE);
                    return (encodeB.bitLength < encodeA.bitLength) ? 1 : 0;
                }).average().getAsDouble();
                return String.format("Accuracy = %.3f%%, %.3f%%", englishAccuracy, frenchAccuracy);
            });
        }
    }

    private void print(CharTrie trie) {
        System.out.println("Total Indexed Document (KB): " + trie.getIndexedSize() / 1024);
        System.out.println("Total Node Count: " + trie.getNodeCount());
        System.out.println("Total Index Memory Size (KB): " + trie.getMemorySize() / 1024);
    }

    @Test
    @Category(TestCategories.Report.class)
    public void demoCompression() throws IOException {
        try (MarkdownPrintStream log = MarkdownPrintStream.get(this).addCopy(System.out)) {
            HashSet<String> articles = new HashSet<String>(Arrays.asList("A"));

            log.p("This will demonstrate how to serialize a CharTrie class in compressed format\n");

            log.p("First, we decompose the text into an n-gram trie:");
            List<WikiArticle> articleList = WikiArticle.ENGLISH.load()
                    .limit(1000).filter(x->articles.contains(x.getTitle())).limit(articles.size())
                    .collect(Collectors.toList());
            CharTrieIndex index = log.code(() -> {
                CharTrieIndex trie = new CharTrieIndex();
                articleList.forEach(article -> {
                    System.out.println(String.format("Indexing %s", article.getTitle()));
                    trie.addDocument(article.getText());
                });
                System.out.println(String.format("Indexing %s bytes of documents",
                        trie.getIndexedSize()));
                trie.index(5, 1);
                return trie;
            });
            CharTrie trie = index.truncate();
            log.p("\n\nThen, we compress the trie:");
            byte[] serializedTrie = log.code(() -> {
                print(trie);
                byte[] bytes = new FullTrieSerializer().serialize(trie.copy());
                System.out.println(String.format("%s in ram, %s bytes in serialized form, %s%% compression",
                        trie.getMemorySize(), bytes.length, 100 - (bytes.length * 100.0 / trie.getMemorySize())));
                return bytes;
            });
            log.p("Then, we encode the data used to create the dictionary:");
            List<byte[]> compressedArticles = log.code(() -> {
                PPMCodec codec = new PPMCodec(trie);
                return articleList.stream().map(article -> {
                    String text = article.getText();
                    String title = article.getTitle();
                    TimedResult<Bits> compressed = TimedResult.time(()->codec.encodePPM(text, Integer.MAX_VALUE));
                    TimedResult<String> decompressed = TimedResult.time(()->codec.decodePPM(compressed.obj.getBytes(), Integer.MAX_VALUE));
                    System.out.println(String.format("Serialized %s: %s chars -> %s bytes (%s%%) in %s sec; %s",
                            title, article.getText().length(), compressed.obj.bitLength / 8.0,
                            compressed.obj.bitLength * 100.0 / (8.0 * article.getText().length()),
                            compressed.timeNanos / 1000000000.0, text.equals(decompressed.obj)?"Verified":"Failed Validation"));
                    return compressed.obj.getBytes();
                }).collect(Collectors.toList());
            });
            int totalSize = compressedArticles.stream().mapToInt(x->x.length).sum();
            log.p("Compressed %s bytes of documents -> %s (%s dictionary + %s ppm)",
                    index.getIndexedSize(), (totalSize + serializedTrie.length),
                    serializedTrie.length, totalSize);

            log.p("And decompress to verfy data:");
            log.code(() -> {
                PPMCodec codec = new PPMCodec(trie);
                compressedArticles.forEach(article -> {
                    TimedResult<String> decompressed = TimedResult.time(()->codec.decodePPM(article, Integer.MAX_VALUE));
                    System.out.println(String.format("Deserialized %s bytes -> %s chars in %s sec",
                            article.length, decompressed.obj.length(),
                            decompressed.timeNanos / 1000000000.0));
                });
            });
            log.p("\n\nAnd verify tree structure:");
            log.code(() -> {
                CharTrie restored = new FullTrieSerializer().deserialize(serializedTrie);
                boolean verified = restored.root().equals(trie.root());
                System.out.println(String.format("Tree Verified: %s", verified));
            });
        }
    }

    @Test
    @Category(TestCategories.Report.class)
    public void scratch() throws IOException {
        try (MarkdownPrintStream log = MarkdownPrintStream.get(this).addCopy(System.out)) {
            log.code(() -> {
                Assert.assertEquals("testing", TextAnalysis.combine("test", "sting", 2));
                Assert.assertEquals(null, TextAnalysis.combine("test", "sting", 3));
                Assert.assertEquals("this is a test", TextAnalysis.combine("this is a test", "is a", 3));
                Assert.assertEquals("this is a test", TextAnalysis.combine("this is a test", "this is", 3));
                Assert.assertEquals("this is a test", TextAnalysis.combine("this is a test", " test", 3));
                Assert.assertEquals("this is a test", TextAnalysis.combine("is a", "this is a test", 3));
                Assert.assertEquals("this is a test", TextAnalysis.combine("this is", "this is a test", 3));
                Assert.assertEquals("this is a test", TextAnalysis.combine(" test", "this is a test", 3));
                Assert.assertEquals(null, TextAnalysis.combine("sting", "test", 3));
                Assert.assertEquals("testing", TextAnalysis.combine("sting", "test", 2));
            });
        }
    }

    @Test
    @Category(TestCategories.Report.class)
    public void demoWikiSummary() throws IOException {
        try (MarkdownPrintStream log = MarkdownPrintStream.get(this).addCopy(System.out)) {
            HashSet<String> articles = new HashSet<>();
            Arrays.asList("Alabama", "Alchemy", "Algeria", "Altruism", "Abraham Lincoln", "ASCII", "Apollo", "Alaska").forEach(articles::add);
            log.p("This will demonstrate how to serialize a CharTrie class in compressed format\n");
            log.h3("First, we load training and testing data:");
            List<WikiArticle> articleList = log.code(() -> {
                return WikiArticle.ENGLISH.load().limit(1000)
                        .filter(x -> articles.contains(x.getTitle())).limit(articles.size())
                        .collect(Collectors.toList());
            });
            List<WikiArticle> trainingList = log.code(() -> {
                return WikiArticle.ENGLISH.load()
                        .filter(x -> x.getText().length() > 4 * 1024).filter(x -> !articles.contains(x.getTitle()))
                        .limit(1000).collect(Collectors.toList());
            });
            log.h3("Then, we decompose the text into an n-gram trie:");
            int depth = 7;
            CharTrie referenceTrie = log.code(() -> {
                CharTrie trie = CharTrieIndex.create(trainingList.stream().map(x -> x.getText()).collect(Collectors.toList()), depth, 0);
                print(trie);
                return trie;
            });
            articleList.forEach(testArticle->{
                log.h2(testArticle.getTitle());
                CharTrie articleTrie = log.code(() -> {
                    //CharTrie trie = CharTrieIndex.create(Arrays.asList(testArticle.getText()), depth, 0);
                    //print(trie);
                    return referenceTrie;//.add(trie);
                });
                log.h3("Keywords");
                log.code(() -> {
                    return articleTrie.getAnalyzer().setVerbose(System.out).keywords(testArticle.getText())
                            .stream().map(s->'"'+s+'"').collect(Collectors.joining(", "));
                });
                log.h3("Tokenization");
                log.code(() -> {
                    return articleTrie.getAnalyzer().setVerbose(System.out).split(testArticle.getText())
                            .stream().map(s->'"'+s+'"').collect(Collectors.joining(", "));
                });
            });
        }
    }

    @Test
    @Category(TestCategories.Report.class)
    public void demoWikiSpelling() throws IOException {
        try (MarkdownPrintStream log = MarkdownPrintStream.get(this).addCopy(System.out)) {
            log.p("This will demonstrate how to serialize a CharTrie class in compressed format\n");
            log.h3("First, we load training and testing data:");

            List<Misspelling> trainingList = log.code(() -> {
                return Misspelling.BIRKBECK.load().collect(Collectors.toList());
            });
            log.h3("Then, we decompose the text into an n-gram trie:");
            int depth = 7;
            CharTrie referenceTrie = log.code(() -> {
                List<String> list = trainingList.stream().map(x -> x.getTitle()).collect(Collectors.toList());
                CharTrie trie = CharTrieIndex.create(list, depth, 0);
                print(trie);
                return trie;
            });
            trainingList.stream().limit(20).forEach(testArticle->{

                log.p("Spelling check: %s -> %s", testArticle.getText(), testArticle.getTitle());
                log.code(() -> {
                    return referenceTrie.getAnalyzer().setVerbose(System.out).spelling(testArticle.getText());
                });
            });
        }
    }

    @Test
    @Category(TestCategories.Report.class)
    public void demoSerialization() throws IOException {
        try (MarkdownPrintStream log = MarkdownPrintStream.get(this).addCopy(System.out)) {

            log.p("This will demonstrate how to serialize a CharTrie class in compressed format\n");

            log.p("First, we load some data into an index:");
            CharTrieIndex index = log.code(() -> {
                CharTrieIndex charTrieIndex = new CharTrieIndex();
                WikiArticle.ENGLISH.load().limit(100).forEach(article -> {
                    charTrieIndex.addDocument(article.getText());
                });
                System.out.println(String.format("Indexing %s bytes of documents",
                        charTrieIndex.getIndexedSize()));
                charTrieIndex.index(6, 0);
                return charTrieIndex;
            });
            CharTrie tree = index.truncate();

            log.p("\n\nThen, we compress the tree:");
            String serialized = log.code(() -> {
                byte[] bytes = new FullTrieSerializer().serialize(tree.copy());
                byte[] bytes2 = CompressionUtil.encodeLZ(bytes); // Demonstrate compression results with standard LZ post-filter
                System.out.println(String.format("%s in ram, %s bytes in serialized form, %s%% compression",
                        tree.getMemorySize(), bytes2.length, 100 - (bytes2.length * 100.0 / tree.getMemorySize())));
                return Base64.getEncoder().encodeToString(bytes2);
            });

            log.p("\n\nAnd decompress to verify:");
            int dictionaryLength = log.code(() -> {
                byte[] bytes = CompressionUtil.decodeLZ(Base64.getDecoder().decode(serialized));
                CharTrie restored = new FullTrieSerializer().deserialize(bytes);
                boolean verified = restored.root().equals(tree.root());
                System.out.println(String.format("Tree Verified: %s", verified));
                return bytes.length;
            });

            log.p("Then, we encode the data used to create the dictionary:");
            log.code(() -> {
                PPMCodec codec = tree.getCodec();
                int totalSize = WikiArticle.ENGLISH.load().limit(100).map(article -> {
                    TimedResult<Bits> compressed = TimedResult.time(()->codec.encodePPM(article.getText(), 4));
                    System.out.println(String.format("Serialized %s: %s chars -> %s bytes (%s%%) in %s sec",
                            article.getTitle(), article.getText().length(), compressed.obj.bitLength / 8.0,
                            compressed.obj.bitLength * 100.0 / (8.0 * article.getText().length()),
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
                WikiArticle.ENGLISH.load().skip(100).limit(20).forEach(article -> {
                    TimedResult<Bits> compressed = TimedResult.time(()->codec.encodePPM(article.getText(), 4));
                    System.out.println(String.format("Serialized %s: %s chars -> %s bytes (%s%%) in %s sec",
                            article.getTitle(), article.getText().length(), compressed.obj.bitLength / 8.0,
                            compressed.obj.bitLength * 100.0 / (8.0 * article.getText().length()),
                            compressed.timeNanos / 1000000000.0));
                });
            });

        }
    }


}
