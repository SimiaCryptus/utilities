package com.simiacryptus.util.text;

import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class TextAnalysis {

  private final CharTrie inner;
  private PrintStream verbose = null;

  TextAnalysis(CharTrie inner) {
    this.inner = inner;
  }

  public List<String> keywords(final String source) {
    Map<String, Long> wordCounts = split(source, 5).stream().collect(Collectors.groupingBy(x -> x, Collectors.counting()));
    return wordCounts.entrySet().stream().filter(x->x.getValue()>1)
            .sorted(Comparator.comparing(x->-entropy(x.getKey()) * Math.pow(x.getValue(),0.3)))
            .map(e->{
              if(isVerbose()) verbose.println(String.format("KEYWORD: \"%s\" - %s * %.3f / %s", e.getKey(), e.getValue(), entropy(e.getKey()), e.getKey().length()));
              return e.getKey();
            }).collect(Collectors.toList());
  }

  public List<String> split(final String source, double threshold) {
    List<String> output = new ArrayList<>();
    int wordStart = 0;
    double lastNats = Double.MAX_VALUE;
    for(int i=1;i<source.length();i++){
      String priorText = source.substring(0, i);
      String followingText = source.substring(i-1, source.length());
      TrieNode priorNode = this.inner.matchEnd(priorText);
      TrieNode followingNode = this.inner.traverse(followingText);
      TrieNode futureNode = followingNode.godparent();
      double aprioriNats = 0.0 + (null == priorNode.parent ? Double.POSITIVE_INFINITY : (- Math.log(priorNode.getCursorCount() * 1.0 / priorNode.parent.getCursorCount())));
      double aposterioriNats = 0.0 + (null == futureNode ? Double.POSITIVE_INFINITY : (- Math.log(followingNode.getCursorCount() * 1.0 / futureNode.getCursorCount())));
      if(isVerbose()) verbose.println(String.format("\"%s\"\t\"%s\"\t%.3f + %.3f = %.3f\t%.3f",
              priorNode.getString().replaceAll("\n","\\n"), followingNode.getString().replaceAll("\n","\\n"),
              aprioriNats, aposterioriNats, aprioriNats + aposterioriNats, aprioriNats - aposterioriNats));
      if((aprioriNats / lastNats) > threshold) {
        String word = source.substring(wordStart, i-1);
        wordStart = i-1;
        output.add(word);
        lastNats = Double.POSITIVE_INFINITY;
      } else {
        lastNats = aprioriNats;
      }
    }
    return output;
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
}
