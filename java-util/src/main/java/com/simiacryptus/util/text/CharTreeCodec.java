package com.simiacryptus.util.text;

import com.davidehrmann.vcdiff.VCDiffDecoderBuilder;
import com.davidehrmann.vcdiff.VCDiffEncoder;
import com.davidehrmann.vcdiff.VCDiffEncoderBuilder;
import com.simiacryptus.util.binary.BitInputStream;
import com.simiacryptus.util.binary.BitOutputStream;
import com.simiacryptus.util.binary.Bits;
import com.simiacryptus.util.binary.Interval;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
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
      String contextStr = "";
      while(true) {
        Node fromNode = inner.matchPredictor(getRight(contextStr, context));
        String prefix = fromNode.getString();
        if(0 == fromNode.getNumberOfChildren()) return "";
        int seek = in.peekIntCoord(fromNode.getCursorCount());
        Node toNode = fromNode.traverse(seek + fromNode.getCursorIndex());
        String newSegment = toNode.getString(fromNode);
        Interval interval = fromNode.intervalTo(toNode);
        Bits bits = interval.toBits();
        if(verbose) System.out.println(String.format(
                "Using prefix \"%s\", seek to %s pos, path \"%s\" with %s -> %s, input buffer = %s",
                displayStr(prefix), seek, displayStr(newSegment), interval, bits, in.peek(24)));
        in.expect(bits);
        if(toNode.isStringTerminal()) {
          if(verbose) System.out.println("Inserting null char to terminate string");
          newSegment += Character.MIN_VALUE;
        }
        if(!newSegment.isEmpty()) {
          if(newSegment.endsWith("\u0000")) {
            out.append(newSegment.substring(0,newSegment.length()-1));
            if(verbose) System.out.println(String.format("Null char reached"));
            break;
          } else {
            contextStr += newSegment;
            out.append(newSegment);
          }
        } else if(in.availible() == 0) {
          if(verbose) System.out.println(String.format("No More Data"));
          break;
        } else if(toNode.getChar() == Character.MIN_VALUE) {
          if(verbose) System.out.println(String.format("End code"));
          break;
          //throw new RuntimeException("Cannot decode text");
        } else if(toNode.getChar() == Character.MAX_VALUE) {
          contextStr = prefix.substring(1);
        } else {
          if(verbose) System.out.println(String.format("Cannot decode text"));
          break;
          //throw new RuntimeException("Cannot decode text");
        }
      }
      return out.toString();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Bits encodePPM(String text, int context) {
    final String original = text;
    //if(verbose) System.out.println(String.format("Encoding %s with %s chars of context", text, context));
    if(!text.endsWith("\u0000")) text += Character.MIN_VALUE;
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    BitOutputStream out = new BitOutputStream(buffer);
    String contextStr = "";
    try {
      while(!text.isEmpty()) {
        String right = getRight(contextStr, context);
        Node fromNode = inner.matchPredictor(right);
        String prefix = fromNode.getString();

        Node toNode = fromNode.traverse(text);
        int segmentChars = toNode.depth - fromNode.depth;
        if(toNode.hasChildren()) {
          toNode = toNode.getChild(Character.MAX_VALUE).get();
        }

        Interval interval = fromNode.intervalTo(toNode);
        Bits segmentData = interval.toBits();
        if(verbose) System.out.println(String.format(
                "Using context \"%s\", encoded \"%s\" (%s chars) as %s -> %s",
                displayStr(fromNode.getString()), displayStr(toNode.getString(fromNode)), segmentChars, interval, segmentData));
        out.write(segmentData);
        
        if(0 == segmentChars) {
          if(prefix.isEmpty()) {
            throw new RuntimeException(String.format("Cannot encode %s in model", text.substring(0,1)));
          }
          if("\u0000".equals(text)) break;
          if(toNode.getChar() == Character.MAX_VALUE) {
            contextStr = prefix.substring(1);
            continue;
          }
          throw new RuntimeException("Cannot encode " + text.substring(0,1));
        } else {
          contextStr += text.substring(0, segmentChars);
          text = text.substring(segmentChars);
        }
      }
      out.flush();
      Bits bits = new Bits(buffer.toByteArray(), out.getTotalBitsWritten());
      //if(verbose) System.out.println(String.format("Encoded %s to %s", original, bits));
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
        if(!dictionary.isEmpty()) {
            byte[] bytes = dictionary.getBytes("UTF-8");
            compresser.setDictionary(bytes);
        }
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
    compresser.finish();
    int compressedDataLength = compresser.deflate(output);
    compresser.end();
    return Arrays.copyOf(output, compressedDataLength);
  }

    public static String decodeLZ(byte[] data) {
      return decodeLZ(data, "");
    }

    public static String decodeLZ(byte[] data, String dictionary) {
    try {
      Inflater decompresser = new Inflater();
      decompresser.setInput(data, 0, data.length);
      if(!dictionary.isEmpty()) {
          byte[] bytes = dictionary.getBytes("UTF-8");
          decompresser.setDictionary(bytes);
      }
      byte[] result = new byte[100];
      int resultLength = 0;
      resultLength = decompresser.inflate(result);
      decompresser.end();
      return new String(result, 0, resultLength, "UTF-8");
    } catch (DataFormatException | UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  public static String decodeBZ(byte[] data) {
    try {
      return new String(decodeBZRaw(data), "UTF-8");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static byte[] decodeBZRaw(byte[] data) {
    try {
      ByteArrayInputStream output = new ByteArrayInputStream(data);
      BZip2CompressorInputStream compresser = new BZip2CompressorInputStream(output);
      return IOUtils.toByteArray(compresser);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static byte[] encodeBZ(String data) {
    try {
      byte[] bytes = encodeBZ(data.getBytes("UTF-8"));
      //assert(data.equals(decodeBZ(bytes)));
      return bytes;
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  public static byte[] encodeBZ(byte[] data) {
    try {
      int blockSize = 4;
      ByteArrayOutputStream output = new ByteArrayOutputStream();
      BZip2CompressorOutputStream compresser = new BZip2CompressorOutputStream(output, blockSize);
      compresser.write(data);
      compresser.close();
      byte[] bytes = output.toByteArray();
      return bytes;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static String decodeBZ(byte[] data, String dictionary) {
    try {
      byte[] dictBytes = dictionary.getBytes("UTF-8");
      ByteArrayOutputStream buffer = new ByteArrayOutputStream();
      VCDiffDecoderBuilder.builder().buildSimple().decode(dictBytes, decodeBZRaw(data), buffer);
      return new String(buffer.toByteArray(), "UTF-8");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static byte[] encodeBZ(String data, String dictionary) {
    try {
      byte[] bytes = encodeBZ(data.getBytes("UTF-8"), dictionary);
      //assert(data.equals(decodeBZ(bytes, dictionary)));
      return bytes;
    } catch (Exception e) {
       throw new RuntimeException(e);
    }
  }

  public static byte[] encodeBZ(byte[] asBytes, String dictionary) {
    try {
      byte[] dictBytes = dictionary.getBytes("UTF-8");
      VCDiffEncoder<OutputStream> encoder = VCDiffEncoderBuilder.builder()
              .withDictionary(dictBytes)
              .buildSimple();
      ByteArrayOutputStream buffer = new ByteArrayOutputStream();
      encoder.encode(asBytes, buffer);
      return encodeBZ(buffer.toByteArray());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static String getRight(String str, int count) {
    int newLen = Math.min(count, str.length());
    int prefixFrom = Math.max(0, str.length() - newLen);
    String right = str.substring(prefixFrom, str.length());
    return right;
  }

  private static String displayStr(String str) {
    return str.replaceAll("\\\\", "\\\\").replaceAll("\n", "\\n").replaceAll("\0", "\\\\0");
  }

}
