package com.simiacryptus.util.text;

import com.simiacryptus.util.binary.BitInputStream;
import com.simiacryptus.util.binary.BitOutputStream;
import com.simiacryptus.util.binary.Bits;
import com.simiacryptus.util.binary.Interval;

import java.io.ByteArrayInputStream;
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
  public boolean verbose = false;

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
          if (child.getChar() != Character.MIN_VALUE) {
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

  public double measureEntropy(String message, double smoothness) {
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
    String prefix = "";
    while (str.length() < length) {
      Node node = inner.matchPredictor(prefix);
      Node nextNode = maxNextNode(node, lookahead);
      if (null == nextNode) break;
      if (destructive) nextNode.shadowCursors();
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
      if(nextNode.getChar() == Character.MIN_VALUE) {
        if(terminateAtNull) {
          break;
        } else {
          prefix = "";
        }
      }
    }
    return str.substring(0, Math.min(length, str.length()));
  }

  public String decodePPM(byte[] data, int context) {
    try {
      BitInputStream in = new BitInputStream(new ByteArrayInputStream(data));
      StringBuilder out = new StringBuilder();
      while(true) {
        String prefix = getContext_FastPPM(out, context);
        String newSegment = decodeNext(in, prefix);
        while(newSegment.isEmpty() && !prefix.isEmpty()) {
          if(verbose) System.out.println(String.format("Empty Segment"));
          prefix = prefix.substring(1);
          newSegment = decodeNext(in, prefix);
          if(!newSegment.isEmpty()) {
            break;
          }
        }
        if(!newSegment.isEmpty()) {
          if(newSegment.endsWith("\u0000")) {
            out.append(newSegment.substring(0,newSegment.length()-1));
            if(verbose) System.out.println(String.format("Null char reached"));
            break;
          } else {
            out.append(newSegment);
          }
        } else if(in.availible() == 0) {
          if(verbose) System.out.println(String.format("No More Data"));
          break;
        } else {
          throw new RuntimeException("Cannot decode text");
        }
      }
      return out.toString();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private String getContext_FastPPM(StringBuilder out, int context) {
    String prefix = out.toString();
    int newLen = Math.min(context, prefix.length());
    int prefixFrom = Math.max(0, prefix.length() - newLen);
    prefix = prefix.substring(prefixFrom, prefix.length());
    return prefix;
  }

  private String decodeNext(BitInputStream in, String prefix) throws IOException {
    Node fromNode = inner.matchEnd(prefix);
    int seek = in.peekIntCoord(fromNode.getCursorCount());
    Node toNode = fromNode.traverse(seek + fromNode.getCursorIndex());
    String str = toNode.getString(fromNode);
    Interval interval = fromNode.intervalTo(toNode);
    Bits bits = interval.toBits();
    if(verbose) System.out.println(String.format(
            "Using prefix \"%s\", seek to %s pos, path \"%s\" with %s -> %s, input buffer = %s",
            prefix, seek, str, interval, bits, in.peek(24)));
    in.expect(bits);
    if(toNode.isStringTerminal()) {
      if(verbose) System.out.println("Inserting null char to terminate string");
      str += Character.MIN_VALUE;
    }
    return str;
  }

  public Bits encodePPM(String text, int context) {
    final String original = text;
    if(verbose) System.out.println(String.format("Encoding %s with %s chars of context", text, context));
    if(!text.endsWith("\u0000")) text += Character.MIN_VALUE;
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    BitOutputStream out = new BitOutputStream(buffer);
    try {
      String prefix = "";
      while(!text.isEmpty()) {
        Node fromNode = inner.matchEnd(prefix);
        Node toNode = fromNode.traverse(text);
        
        int segmentChars = toNode.depth - fromNode.depth;
        if(toNode.hasChildren()) {
          toNode = toNode.getChild(Character.MAX_VALUE).get();
        }

        Interval interval = fromNode.intervalTo(toNode);
        Bits segmentData = interval.toBits();
        if(verbose) System.out.println(String.format(
                "Using context \"%s\", encoded \"%s\" (%s chars) as %s -> %s",
                fromNode.getString(), toNode.getString(fromNode), segmentChars, interval, segmentData));
        out.write(segmentData);
        
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
      out.flush();
      Bits bits = new Bits(buffer.toByteArray(), out.getTotalBitsWritten());
      if(verbose) System.out.println(String.format("Encoded %s to %s", original, bits));
      return bits;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  private Map<Character, Double> lookahead(Node node, double smoothness) {
    HashMap<Character, Double> map = new HashMap<>();
    lookahead(node, map, 1.0, smoothness);
    return map;
  }

  private void lookahead(Node node, HashMap<Character, Double> map, double factor, double smoothness) {
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

  public static byte[] encodeLZ(String data) {
    return encodeLZ(data, "");
  }

  public static byte[] encodeLZ(String data, String dictionary) {
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

  public static String decodeLZ(byte[] data, String dictionary) {
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
