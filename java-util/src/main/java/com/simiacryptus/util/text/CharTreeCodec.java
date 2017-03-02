package com.simiacryptus.util.text;

import com.simiacryptus.util.binary.BitOutputStream;
import com.simiacryptus.util.binary.Bits;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * A character sequence index using a prefix tree, commonly known as a full-text
 * index or as the data structure behind markov chains. This implementation uses
 * serialized fixed-length ephemeral objects and a raw byte data store,
 * preventing object/reference count overhead.
 */
public class CharTreeCodec {

  final CharTree inner;

  public CharTreeCodec(CharTree inner) {
    super();
    this.inner = inner;
  }

  public String generateMarkov(int length, int context, String seed) {
    String str = seed;
    while (str.length() < length) {
      String prefix = str.substring(Math.max(str.length() - context, 0), str.length());
      Node node = inner.matchPredictor(prefix);
      int cursorCount = node.getCursorCount();
      int fate = CharTree.random.nextInt(cursorCount);
      String next = null;
      List<Node> children = node.getChildren().collect(Collectors.toList());
      for (Node child : children) {
        fate -= child.getCursorCount();
        if (fate <= 0) {
          if (child.getToken() != Character.MIN_VALUE) {
            next = new String(new char[] { child.getToken() });
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

  public String generateMarkov2(int length, int context, String seed, double smoothness) {
    String str = seed;
    while (str.length() < length) {
      String prefix = str.substring(Math.max(str.length() - context, 0), str.length());
      Node node = inner.matchPredictor(prefix);
      Map<Character, Double> lookahead = lookahead(node, smoothness);
      double fate = CharTree.random.nextDouble() * lookahead.values().stream().mapToDouble(x -> x).sum();
      String next = null;
      for (Map.Entry<Character, Double> child : lookahead.entrySet()) {
        fate -= child.getValue();
        if (fate <= 0) {
          if (child.getKey() != Character.MIN_VALUE) {
            next = new String(new char[] { child.getKey() });
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

  public double encodingBits(String message, double smoothness) {
    double total = IntStream.range(0, message.length()).parallel().mapToDouble(i -> {
      Character next = message.charAt(i);
      String prefix = message.substring(0, i);
      Node node = inner.matchPredictor(prefix);
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
    while (str.length() < length) {
      String prefix = str.substring(Math.max(str.length() - context, 0), str.length());
      Node node = inner.matchPredictor(prefix);
      Node nextNode = maxNextNode(node, lookahead);
      if (null == nextNode)
        break;
      if (destructive)
        nextNode.shadowCursors();
      String nextNodeString = nextNode.getString();
      String next = nextNodeString.substring(node.depth);
      if(terminateAtNull && next.contains("\u0000")) break;
      str += next;
    }
    return str.substring(0, Math.min(length, str.length()));
  }

  public byte[] encode(String text, int context) {
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    BitOutputStream out = new BitOutputStream(buffer);
    try {
      String prefix = "";
      while(!text.isEmpty()) {
        Node fromNode = inner.matchEnd(prefix);
        Node toNode = fromNode.traverse(text);
        
        if(toNode.hasChildren()) {
          toNode = toNode.getChild(Character.MAX_VALUE).orElse(toNode);
        }
        
        Bits segmentData = fromNode.intervalTo(toNode);
        out.write(segmentData);
        int segmentChars = toNode.depth - fromNode.depth;
        
        if(0 == segmentChars) {
          if(prefix.isEmpty()) {
            throw new RuntimeException(String.format("Cannot encode %s in model", text.substring(0,1)));
          } else {
            prefix = prefix.substring(1);
            continue;
          }
        }
        prefix = prefix + text.substring(0,segmentChars);
        int newLen = Math.min(context, prefix.length());
        int prefixFrom = Math.max(0, prefix.length() - newLen);
        prefix = prefix.substring(prefixFrom, prefix.length());
        text = text.substring(segmentChars);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return buffer.toByteArray();
  }
  
  private Map<Character, Double> lookahead(Node node, double smoothness) {
    HashMap<Character, Double> map = new HashMap<>();
    lookahead(node, map, 1.0, smoothness);
    return map;
  }

  private void lookahead(Node node, HashMap<Character, Double> map, double factor, double smoothness) {
    if (0 < factor) {
      node.getChildren().forEach(child -> {
        map.put(child.getToken(), factor * child.getCursorCount() + map.getOrDefault(child.getToken(), 0.0));
      });
      if (null != node.parent) {
        lookahead(inner.matchPredictor(node.getString().substring(1)), map,
            factor * (smoothness / (smoothness + node.getCursorCount())), smoothness);
      }
    }
  }

  private Node maxNextNode(Node node, int lookahead) {
    Stream<Node> childStream = node.getChildren();
    for (int level = 0; level < lookahead; level++) {
      childStream = childStream.flatMap(child -> child.getChildren());
    }
    Node result = childStream.max(Comparator.comparingInt(x -> x.getCursorCount())).orElse(null);
    if (null == result) {
      if (lookahead > 0)
        return maxNextNode(node, lookahead - 1);
      Node godparent = node.godparent();
      if (null != godparent)
        return maxNextNode(godparent, lookahead);
    }
    return result;
  }

  public static byte[] compress(String dictionary, String data) {
    byte[] output = new byte[data.length() * 2];
    Deflater compresser = new Deflater();
    try {
      compresser.setInput(data.getBytes("UTF-8"));
      compresser.setDictionary(dictionary.getBytes("UTF-8"));
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
    compresser.finish();
    int compressedDataLength = compresser.deflate(output);
    compresser.end();
    return Arrays.copyOf(output, compressedDataLength);
  }

  public static String decompress(String dictionary, byte[] data) {
    try {
      Inflater decompresser = new Inflater();
      decompresser.setInput(data, 0, data.length);
      decompresser.setDictionary(dictionary.getBytes("UTF-8"));
      byte[] result = new byte[100];
      int resultLength = 0;
      resultLength = decompresser.inflate(result);
      decompresser.end();
      return new String(result, 0, resultLength, "UTF-8");
    } catch (DataFormatException | UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  
}
