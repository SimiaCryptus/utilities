package com.simiacryptus.util.text;

import com.simiacryptus.util.lang.TimedResult;
import com.simiacryptus.util.test.TestDocument;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public interface Compressor {
  public static <T> TableOutput evalTable(Stream<? extends TestDocument> data, Map<String, Compressor> compressors, boolean wide) {
    TableOutput wideTable = new TableOutput();
    TableOutput tallTable = new TableOutput();
    data.parallel().forEach(item->{
      HashMap<String, Object> rowWide = new LinkedHashMap<>();
      String title;
      title = item.title.replaceAll("\0","").replaceAll("\n","\\n");
      rowWide.put("title", title);
      compressors.forEach((name,compressor)->{
        try {
          HashMap<String, Object> rowTall = new LinkedHashMap<>();
          rowTall.put("title", title);
          rowTall.put("compressor", name);

          rowWide.put(name + ".uncompressed", item.text.length());
          rowTall.put("uncompressed", item.text.length());
          TimedResult<byte[]> compress = TimedResult.time(()->compressor.compress(item.text));
          rowWide.put(name + ".compressed", compress.obj.length);
          rowTall.put("compressed", compress.obj.length);
          double ONE_MILLION = 1000000.0;
          rowWide.put(name + ".compressMs", compress.timeNanos / ONE_MILLION);
          rowTall.put("compressMs", compress.timeNanos / ONE_MILLION);
          TimedResult<String> uncompress = TimedResult.time(()->compressor.uncompress(compress.obj));
          rowWide.put(name + ".uncompressMs", uncompress.timeNanos / ONE_MILLION);
          rowTall.put("uncompressMs", uncompress.timeNanos / ONE_MILLION);
          rowWide.put(name + ".verified", uncompress.obj.equals(item.text));
          rowTall.put("verified", uncompress.obj.equals(item.text));
          tallTable.putRow(rowTall);
          //System.out.println(String.format("Evaluated %s with %s", name, title));
        } catch (Exception e) {
          e.printStackTrace();
        }
      });
      wideTable.putRow(rowWide);
    });
    return wide?wideTable:tallTable;
  }

  public static void addGenericCompressors(Map<String, Compressor> compressors) {
    compressors.put("BZ0", new Compressor() {
      @Override
      public byte[] compress(String text) {
        return CompressionUtil.encodeBZ(text);
      }

      @Override
      public String uncompress(byte[] data) {
        return CompressionUtil.decodeBZ(data);
      }
    });
    compressors.put("LZ0", new Compressor() {
      @Override
      public byte[] compress(String text) {
        return CompressionUtil.encodeLZ(text);
      }

      @Override
      public String uncompress(byte[] data) {
        return CompressionUtil.decodeLZ(data);
      }
    });
  }

  public static Compressor buildPPMCompressor(CharTrie baseTree, final int encodingContext) {
    PPMCodec codec = baseTree.getCodec();
    System.out.println(String.format("Encoding Tree Memory Size = %s KB", codec.getMemorySize() / 1024));
    return new Compressor() {
      @Override
      public byte[] compress(String text) {
        return codec.encodePPM(text, encodingContext).getBytes();
      }

      @Override
      public String uncompress(byte[] data) {
        return codec.decodePPM(data, encodingContext);
      }
    };
  }

  byte[] compress(String text);

  String uncompress(byte[] compress);
}
