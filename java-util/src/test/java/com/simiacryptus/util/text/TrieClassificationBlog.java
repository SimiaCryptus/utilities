package com.simiacryptus.util.text;

import com.simiacryptus.util.binary.Bits;
import com.simiacryptus.util.test.MarkdownPrintStream;
import com.simiacryptus.util.test.TestCategories;
import com.simiacryptus.util.test.TweetSentiment;
import com.simiacryptus.util.test.WikiArticle;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TrieClassificationBlog {

    @Test
    @Category(TestCategories.Report.class)
    public void language_detection_ppm() throws IOException {
        try (MarkdownPrintStream log = MarkdownPrintStream.get(this).addCopy(System.out)) {
            log.h1("Language Detection via PPM Compression");
            int testingSize = 100;
            int trainingSize = 100;
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
                CharTrie charTrie = CharTrieIndex.indexFulltext(english.subList(0,trainingSize)
                        .stream().map(x->x.getText()).collect(Collectors.toList()), maxLevels, minWeight);
                print(charTrie);
                return charTrie;
            });
            CharTrie trieFrench = log.code(() -> {
                CharTrie charTrie = CharTrieIndex.indexFulltext(french.subList(testingSize,french.size())
                        .stream().map(x->x.getText()).collect(Collectors.toList()), maxLevels, minWeight);
                print(charTrie);
                return charTrie;
            });
            log.p("Each model can be used out-of-the-box to perform classification:");
            log.code(() -> {
                NodewalkerCodec codecA = trieEnglish.getCodec();
                NodewalkerCodec codecB = trieFrench.getCodec();
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

    @Test
    @Category(TestCategories.Report.class)
    public void prebuilt_language_models() throws IOException {
        try (MarkdownPrintStream log = MarkdownPrintStream.get(this).addCopy(System.out)) {
            log.h1("Language Detection using prebuilt models");
            TableOutput table = new TableOutput();
            evaluateLanguage(log,"English", WikiArticle.ENGLISH, table);
            evaluateLanguage(log,"French", WikiArticle.FRENCH, table);
            evaluateLanguage(log,"German", WikiArticle.GERMAN, table);
            log.h3("Summary");
            log.code(() -> {
                return table.toTextTable();
            });
        }
    }

    private void evaluateLanguage(MarkdownPrintStream log, String sourceLanguage, WikiArticle.Loader sourceData, TableOutput table) {
        int testingSize = 100;
        int minArticleSize = 4 * 1024;
        log.h3(sourceLanguage);
        log.code(() -> {
            sourceData.load()
                    .map(x->x.getText()).filter(x -> x.length()> minArticleSize).limit(testingSize)
                    .collect(Collectors.toList()).parallelStream()
                    .map(x-> LanguageModel.match(x)).collect(Collectors.groupingBy(x->x,Collectors.counting()))
                    .forEach((language, count)->
            {
                HashMap<String, Object> row = new HashMap<>();
                row.put("Source", sourceLanguage);
                row.put("Predicted", language.name());
                row.put("Count", count);
                table.putRow(row);
            });
        });
    }

    @Test
    @Category(TestCategories.Report.class)
    public void sentiment_analysis_ppm() throws IOException {
        try (MarkdownPrintStream log = MarkdownPrintStream.get(this).addCopy(System.out)) {
            log.h1("Sentiment Analysis via PPM Compression");
            int testingSize = 1000;
            int trainingSize = 100000;
            int minWeight = 1;
            int maxLevels = 5;
            log.p("\n\n\n");
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
                CharTrie charTrie = CharTrieIndex.indexFulltext(
                        tweetsPositive.stream().skip(testingSize).limit(trainingSize).map(x->x.getText()).collect(Collectors.toList()),
                        maxLevels, minWeight);
                print(charTrie);
                return charTrie;
            });
            CharTrie trieNegative = log.code(() -> {
                CharTrie charTrie = CharTrieIndex.indexFulltext(
                        tweetsNegative.stream().skip(testingSize).limit(trainingSize).map(x->x.getText()).collect(Collectors.toList()),
                        maxLevels, minWeight);
                print(charTrie);
                return charTrie;
            });
            log.p("\n\n\n");
            log.p("Each model can be used to perform classification:");
            log.code(() -> {
                NodewalkerCodec codecPositive = triePositive.getCodec();
                NodewalkerCodec codecNegative = trieNegative.getCodec();
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
                return String.format("Accuracy = %.3f%% with positive sentiment, %.3f%% with negative sentiment",
                        positiveAccuracy, negativeAccuracy);
            });
        }
    }

    @Test
    @Category(TestCategories.Report.class)
    public void sentiment_analysis_decision_tree() throws IOException {
        try (MarkdownPrintStream log = MarkdownPrintStream.get(this).addCopy(System.out)) {
            log.h1("Sentiment Analysis inference using a Decision Tree");
            int testingSize = 10000;
            int trainingSize = 50000;
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
            Function<String, Map<String, Double>> rule = log.code(()->{
                HashMap<String, List<String>> map = new HashMap<>();
                map.put("pos", tweetsPositive.stream().limit(trainingSize).map(x -> x.getText()).collect(Collectors.toList()));
                map.put("neg", tweetsNegative.stream().limit(trainingSize).map(x -> x.getText()).collect(Collectors.toList()));
                return new ClassificationTree().setVerbose(System.out).categorizationTree(map, 32);
            });
            log.code(()->{
                return tweetsPositive.stream().skip(trainingSize).map(x->x.getText()).mapToDouble(str->{
                    Map<String, Double> prob = rule.apply(str);
                    System.out.println(String.format("%s -> %s", str, prob));
                    return prob.getOrDefault("pos", 0.0) < 0.5 ? 0.0 : 1.0;
                }).average().getAsDouble() * 100.0 + "%";
            });
            log.code(()->{
                return tweetsNegative.stream().skip(trainingSize).map(x->x.getText()).mapToDouble(str->{
                    Map<String, Double> prob = rule.apply(str);
                    System.out.println(String.format("%s -> %s", str, prob));
                    return prob.getOrDefault("neg", 0.0) < 0.5 ? 0.0 : 1.0;
                }).average().getAsDouble() * 100.0 + "%";
            });
        }
    }

    private static void print(CharTrie trie) {
        System.out.println("Total Indexed Document (KB): " + trie.getIndexedSize() / 1024);
        System.out.println("Total Node Count: " + trie.getNodeCount());
        System.out.println("Total Index Memory Size (KB): " + trie.getMemorySize() / 1024);
    }



}
