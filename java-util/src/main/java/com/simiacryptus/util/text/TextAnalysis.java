package com.simiacryptus.util.text;

import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class TextAnalysis {

  public static final double DEFAULT_THRESHOLD = Math.log(15);
  private final CharTrie inner;
  private PrintStream verbose = null;

  TextAnalysis(CharTrie inner) {
    this.inner = inner;
  }

  public List<String> keywords(final String source) {
    Map<String, Long> wordCounts = split(source, DEFAULT_THRESHOLD).stream().collect(Collectors.groupingBy(x -> x, Collectors.counting()));
    wordCounts = aggregateKeywords(wordCounts);
    return wordCounts.entrySet().stream().filter(x->x.getValue()>1)
            .sorted(Comparator.comparing(x->-entropy(x.getKey()) * Math.pow(x.getValue(),0.3)))
            .map(e->{
              if(isVerbose()) verbose.println(String.format("KEYWORD: \"%s\" - %s * %.3f / %s", e.getKey(), e.getValue(), entropy(e.getKey()), e.getKey().length()));
              return e.getKey();
            }).collect(Collectors.toList());
  }

  private Map<String, Long> aggregateKeywords(Map<String, Long> wordCounts) {
    Map<String, Long> accumulator = new HashMap<>();
    wordCounts.forEach((key,count)->{
      boolean added = false;
      for(Map.Entry<String, Long> e : accumulator.entrySet()) {
        String combine = combine(key, e.getKey(), 4);
        if (null != combine) {
          accumulator.put(combine, e.getValue() + count);
          accumulator.remove(e.getKey());
          added = true;
          break;
        }
      }
      if(!added) {
        accumulator.put(key, count);
      }
    });
    if(wordCounts.size() > accumulator.size()) {
      return aggregateKeywords(accumulator);
    } else {
      return accumulator;
    }
  }

  public static String combine(String left, String right, int minOverlap) {
    if(left.length() < minOverlap) return null;
    if(right.length() < minOverlap) return null;
    int bestOffset = Integer.MAX_VALUE;
    for(int offset = minOverlap-left.length(); offset < right.length()-minOverlap; offset++) {
      boolean match = true;
      for(int posLeft = Math.max(0,-offset); posLeft < Math.min(left.length(),right.length()-offset); posLeft++) {
        if(left.charAt(posLeft) != right.charAt(posLeft+offset)) {
          match = false;
          break;
        }
      }
      if(match) {
        if(Math.abs(bestOffset) > Math.abs(offset)) bestOffset = offset;
      }
    }
    if(bestOffset < Integer.MAX_VALUE) {
      String combined = left;
      if(bestOffset > 0) {
        combined = right.substring(0,bestOffset) + combined;
      }
      if(left.length()+bestOffset<right.length()) {
        combined = combined + right.substring(left.length()+bestOffset);
      }
      return combined;
    } else {
      return null;
    }
  }

  public double spelling(final String source) {
    double aposterioriNatsPrev = 0;
    double sum = 0;
    for(int i=1;i<=source.length();i++){
      String priorText = source.substring(0, i);
      TrieNode priorNode = inner.matchEnd(priorText);//getMaxentPrior(priorText);
      double aprioriNats = getAprioriNats(priorNode);
      String followingText = source.substring(i-1, source.length());
      TrieNode followingNode = inner.traverse(followingText);//getMaxentPost(followingText);
      TrieNode godparent = followingNode.godparent();
      double aposterioriNats = entropy(followingNode, godparent);
      double jointNats = getJointNats(priorNode, followingNode);
      double linkNats = aprioriNats + aposterioriNatsPrev;
      sum += jointNats;
      if(isVerbose()) verbose.println(String.format("%10s\t%10s\t%s",
              '"' + priorNode.getString().replaceAll("\n","\\n") + '"',
              '"' + followingNode.getString().replaceAll("\n","\\n") + '"',
              Arrays.asList(aprioriNats, aposterioriNats, linkNats, jointNats
              ).stream().map(x->String.format("%.4f",x)).collect(Collectors.joining("\t"))));
      aposterioriNatsPrev = aposterioriNats;
    }
    return sum;
  }

  public List<String> split(final String source, double threshold) {
    List<String> output = new ArrayList<>();
    int wordStart = 0;
    double aposterioriNatsPrev = 0;
    boolean isIncreasing = false;
    double prevLink = 0;
    for(int i=1;i<source.length();i++){
      String priorText = source.substring(0, i);
      TrieNode priorNode = getMaxentPrior(priorText);
      double aprioriNats = getAprioriNats(priorNode);

      String followingText = source.substring(i-1, source.length());
      TrieNode followingNode = getMaxentPost(followingText);
      TrieNode godparent = followingNode.godparent();
      double aposterioriNats = entropy(followingNode, godparent);

      //double jointNats = getJointNats(priorNode, followingNode);
      double linkNats = aprioriNats + aposterioriNatsPrev;
      if(isVerbose()) verbose.println(String.format("%10s\t%10s\t%s",
              '"' + priorNode.getString().replaceAll("\n","\\n") + '"',
              '"' + followingNode.getString().replaceAll("\n","\\n") + '"',
              Arrays.asList(aprioriNats, aposterioriNats, linkNats
              ).stream().map(x->String.format("%.4f",x)).collect(Collectors.joining("\t"))));
      String word = i<2?"":source.substring(wordStart, i-2);
      if(isIncreasing && linkNats < prevLink && prevLink > threshold && word.length() > 2) {
        wordStart = i-2;
        output.add(word);
        if(isVerbose()) verbose.println(String.format("Recognized token \"%s\"", word));
        prevLink = linkNats;
        aposterioriNatsPrev = aposterioriNats;
        isIncreasing = false;
      } else {
        if(linkNats > prevLink) isIncreasing = true;
        prevLink = linkNats;
        aposterioriNatsPrev = aposterioriNats;
      }
    }
    return output;
  }

  private TrieNode getMaxentPost(String followingText) {
    TrieNode followingNode = this.inner.traverse(followingText);
    TrieNode godparent1 = followingNode.godparent();
    double aposterioriNats1 = entropy(followingNode, godparent1);
    while(followingText.length() > 1) {
      String followingText2 = followingText.substring(0, followingText.length()-1);
      TrieNode followingNode2 = this.inner.traverse(followingText2);
      TrieNode godparent2 = followingNode2.godparent();
      double aposterioriNats2 = entropy(followingNode2, godparent2);
      if(aposterioriNats2 < aposterioriNats1) {
        aposterioriNats1 = aposterioriNats2;
        followingNode = followingNode2;
        followingText = followingText2;
      } else break;
    }
    return followingNode;
  }

  private TrieNode getMaxentPrior(String priorText) {
    TrieNode priorNode = this.inner.matchEnd(priorText);
    double aprioriNats1 = getAprioriNats(priorNode);
    while(priorText.length() > 1) {
      String priorText2 = priorText.substring(1);
      TrieNode priorNode2 = this.inner.matchEnd(priorText2);
      double aprioriNats2 = getAprioriNats(priorNode2);
      if(aprioriNats2 < aprioriNats1) {
        aprioriNats1 = aprioriNats2;
        priorText = priorText2;
        priorNode = priorNode2;
      } else break;
    }
    return priorNode;
  }

  private double getJointNats(TrieNode priorNode, TrieNode followingNode) {
    String postContext = followingNode.getString().substring(1);
    Map<Character, Long> code = inner.tokens().stream().collect(Collectors.toMap(x -> x, token -> {
      TrieNode altFollowing = inner.traverse(token + postContext);
      long a = altFollowing.getString().equals(token + postContext) ? altFollowing.getCursorCount() : 0;
      long b = priorNode.getParent().getChild(token).map(x -> x.getCursorCount()).orElse(0l);
      return a * b;
    }));
    double sumOfProduct = code.values().stream().mapToDouble(x->x).sum();
    double product = followingNode.getCursorCount()* priorNode.getCursorCount();
    return -Math.log(product / sumOfProduct);
  }

  private static double entropy(TrieNode tokenNode, TrieNode contextNode) {
    return -0.0 + (null == contextNode ? Double.POSITIVE_INFINITY : (- Math.log(tokenNode.getCursorCount() * 1.0 / contextNode.getCursorCount())));
  }

  private double getAprioriNats(TrieNode priorNode) {
    return entropy(priorNode, priorNode.getParent());
  }

  public double entropy(final String source) {
    double output = 0;
    for(int i=1;i<source.length();i++){
      TrieNode node = this.inner.matchEnd(source.substring(0, i));
      Optional<? extends TrieNode> child = node.getChild(source.charAt(i));
      while(!child.isPresent()) {
        output += Math.log(1.0 / node.getCursorCount());
        node = node.godparent();
        child = node.getChild(source.charAt(i));
      }
      output += Math.log(child.get().getCursorCount() * 1.0 / node.getCursorCount());
    }
    return - output / Math.log(2);
  }

  public boolean isVerbose() {
    return null!=verbose;
  }

  public TextAnalysis setVerbose(PrintStream verbose) {
    this.verbose = verbose;
    return this;
  }

  public List<String> split(String text) {
    return split(text, DEFAULT_THRESHOLD);
  }
}
