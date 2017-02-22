package com.simiacryptus.util.data;

import java.io.*;
import java.nio.ByteBuffer;

public interface SerialType<T> {
    default SerialArrayList<T> newList() {
        return new SerialArrayList<T>(this);
    };
    default SerialArrayList<T> newList(int size) {
        return new SerialArrayList<T>(this, size);
    };
    default SerialArrayList<T> newList(T... items) {
        return new SerialArrayList<T>(this, items);
    };
    int getSize();
    T read(ByteBuffer input) throws IOException;
    default T read(byte[] input) throws IOException {
        assert(input.length == getSize());
        return read(ByteBuffer.wrap(input));
    }
    void write(ByteBuffer output, T value) throws IOException;
    default byte[] write(T value) throws IOException {
        byte[] buffer = new byte[getSize()];
        write(ByteBuffer.wrap(buffer), value);
        return buffer;
    }
}
