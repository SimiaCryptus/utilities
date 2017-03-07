package com.simiacryptus.util.text;

import com.davidehrmann.vcdiff.VCDiffDecoderBuilder;
import com.davidehrmann.vcdiff.VCDiffEncoder;
import com.davidehrmann.vcdiff.VCDiffEncoderBuilder;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class CompressionUtil {
    public static final Random random = new Random();

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

    public static String decodeLZ(byte[] data, String dictionary) {
      try {
        Inflater decompresser = new Inflater();
        decompresser.setInput(data, 0, data.length);
        byte[] result = new byte[data.length * 32];
        int resultLength = 0;
        if(!dictionary.isEmpty()) {
            resultLength = decompresser.inflate(result);
            assert (0 == resultLength);
            if(decompresser.needsDictionary()) {
              byte[] bytes = dictionary.getBytes("UTF-8");
              decompresser.setDictionary(bytes);
            }
        }
        resultLength = decompresser.inflate(result);
        decompresser.end();
        return new String(result, 0, resultLength, "UTF-8");
      } catch (DataFormatException | UnsupportedEncodingException e) {
        throw new RuntimeException(e);
      }
    }

    public static byte[] encodeLZ(String data) {
      return encodeLZ(data, "");
    }

    public static String decodeLZ(byte[] data) {
      return decodeLZ(data, "");
    }

    public static String decodeBZ(byte[] data) {
      try {
        return new String(decodeBZRaw(data), "UTF-8");
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    public static byte[] decodeBZRaw(byte[] data) {
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

    public static String displayStr(String str) {
      return str.replaceAll("\\\\", "\\\\").replaceAll("\n", "\\n").replaceAll("\0", "\\\\0");
    }
}
