package com.simiacryptus.util.text;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A character sequence index using a prefix tree, commonly known as a full-text
 * index or as the data structure behind markov chains. This implementation uses
 * serialized fixed-length ephemeral objects and a raw byte data store,
 * preventing object/reference count overhead.
 */
public class TextGenerator {

  private final CharTrie inner;

  TextGenerator(CharTrie inner) {
    this.inner = inner;
  }

  public String generateMarkov(int length, int context, String seed) {
    String str = seed;
    while (str.length() < length) {
      String prefix = str.substring(Math.max(str.length() - context, 0), str.length());
      TrieNode node = inner.matchPredictor(prefix);
      int cursorCount = node.getCursorCount();
      int fate = CompressionUtil.random.nextInt(cursorCount);
      String next = null;
      Stream<TrieNode> stream = node.getChildren().map(x -> x);
      List<TrieNode> children = stream.collect(Collectors.toList());
      for (TrieNode child : children) {
        fate -= child.getCursorCount();
        if (fate <= 0) {
          if (child.getChar() != PPMCodec.END_OF_STRING) {
            next = child.getToken();
          }
          break;
        }
      }
      if (null != next)
        str += next;
      else
        break;
    }
    return str;
  }

  public double measureEntropy(String message, double smoothness) {
    double total = IntStream.range(0, message.length()).parallel().mapToDouble(i -> {
      Character next = message.charAt(i);
      String prefix = message.substring(0, i);
      TrieNode node = inner.matchPredictor(prefix);
      Map<Character, Double> lookahead = lookahead(node, smoothness);
      double sum = lookahead.values().stream().mapToDouble(x -> x).sum();
      return Math.log(lookahead.getOrDefault(next, 0.0) / sum);
    }).sum();
    return -total / Math.log(2.0);
  }

  public String generateDictionary(int length, int context, final String seed, int lookahead, boolean destructive) {
    return generateDictionary(length, context, seed, lookahead, destructive, false);
  }

  public String generateDictionary(int length, int context, final String seed, int lookahead, boolean destructive, boolean terminateAtNull) {
    String str = seed;
    String prefix = "";
    while (str.length() < length) {
      TrieNode node = prefix.isEmpty()?inner.root():inner.matchPredictor(prefix);
      if(null == node) {
        prefix = prefix.substring(1);
      }
      TrieNode nextNode = maxNextNode(node, lookahead);
      if (null == nextNode) break;
      if (destructive) nextNode.removeCursorCount();
      String next = nextNode.getString(node);
      str += next;
      prefix = str.substring(Math.max(str.length() - context, 0), str.length());
      if(next.isEmpty()) {
        if(prefix.isEmpty()) {
          break;
        } else {
          prefix = prefix.substring(1);
        }
      }
      if(nextNode.getChar() == PPMCodec.END_OF_STRING) {
        if(terminateAtNull) {
          break;
        } else {
          prefix = "";
        }
      }
    }
    return str.substring(0, Math.min(length, str.length()));
  }

  private Map<Character, Double> lookahead(TrieNode node, double smoothness) {
    HashMap<Character, Double> map = new HashMap<>();
    lookahead(node, map, 1.0, smoothness);
    return map;
  }

  private void lookahead(TrieNode node, HashMap<Character, Double> map, double factor, double smoothness) {
    if (0 < factor) {
      node.getChildren().forEach(child -> {
        map.put(child.getChar(), factor * child.getCursorCount() + map.getOrDefault(child.getToken(), 0.0));
      });
      if (null != node.parent) {
        lookahead(inner.matchPredictor(node.getString().substring(1)), map,
            factor * (smoothness / (smoothness + node.getCursorCount())), smoothness);
      }
    }
  }

  private TrieNode maxNextNode(TrieNode node, int lookahead) {
    Stream<TrieNode> childStream = node.getChildren().map(x->x);
    for (int level = 0; level < lookahead; level++) {
      childStream = childStream.flatMap(child -> child.getChildren());
    }
    TrieNode result = childStream.max(Comparator.comparingInt(x -> x.getCursorCount())).orElse(null);
    if (null == result) {
      if (lookahead > 0)
        return maxNextNode(node, lookahead - 1);
      TrieNode godparent = node.godparent();
      if (null != godparent)
        return maxNextNode(godparent, lookahead);
    }
    return result;
  }

}