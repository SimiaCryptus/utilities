package com.simiacryptus.util;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simiacryptus.util.text.CompressionUtil;
import com.twitter.chill.KryoInstantiator;
import de.javakaffee.kryoserializers.KryoReflectionFactorySupport;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.util.Arrays;

public class IO {
  private final static ObjectMapper objectMapper = new ObjectMapper().enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

  public static <T> void writeJson(T obj, File file) {
    StringWriter writer = new StringWriter();
    try {
      objectMapper.writeValue(writer, obj);
      Files.write(file.toPath(), writer.toString().getBytes());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static <T> T readJson(File file) {
    try {
      return objectMapper.readValue(new String(Files.readAllBytes(file.toPath())), new TypeReference<T>() {});
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static final ThreadLocal<Kryo> kryo = new ThreadLocal<Kryo>() {
    @Override
    protected Kryo initialValue() {
      return new KryoInstantiator().setRegistrationRequired(false).newKryo();
    }
  };

  private static final ThreadLocal<byte[]> buffer = new ThreadLocal<byte[]>() {
    @Override
    protected byte[] initialValue() {
      return new byte[8*1024*1024];
    }
  };

  public static <T> void writeKryo(T obj, File file) {
    try {
      Output output = new Output(buffer.get());
      new KryoReflectionFactorySupport().writeClassAndObject(output, obj);
      output.close();
      Files.write(file.toPath(), CompressionUtil.encodeBZ(Arrays.copyOf(output.getBuffer(), output.position())));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static <T> T readKryo(File file) {
    try {
      byte[] bytes = CompressionUtil.decodeBZRaw(Files.readAllBytes(file.toPath()));
      Input input = new Input(buffer.get(), 0, bytes.length);
      return (T) new KryoReflectionFactorySupport().readClassAndObject(input);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
