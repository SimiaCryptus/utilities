package com.simiacryptus.util.data;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

public class SerialArrayList<U> {
    private final SerialType<U> factory;
    public final int unitSize;
    private byte[] buffer;
    private int maxByte = 0;

    public SerialArrayList(SerialType<U> factory, SerialArrayList<U>... items) {
        this.factory = factory;
        this.unitSize = factory.getSize();
        this.maxByte = Arrays.stream(items).mapToInt(item->item.maxByte).sum();
        this.buffer = new byte[this.maxByte];
        int cursor = 0;
        for(int i=0;i<items.length;i++) {
            SerialArrayList<U> item = items[i];
            System.arraycopy(item.buffer, 0, this.buffer, cursor, item.maxByte);
            cursor += item.maxByte;
        }
    }

    public SerialArrayList(SerialType<U> factory, Collection<U> items) {
        this.factory = factory;
        this.unitSize = factory.getSize();
        this.buffer = new byte[items.size() * unitSize];
        int i=0;
        for(U x : items) set(i++, x);
    }

    public SerialArrayList(SerialType<U> factory, U... items) {
        this.factory = factory;
        this.unitSize = factory.getSize();
        this.buffer = new byte[items.length * unitSize];
        for(int i=0;i<items.length;i++) set(i, items[i]);
    }

    public SerialArrayList(SerialType<U> factory) {
        this.factory = factory;
        this.unitSize = factory.getSize();
        this.buffer = new byte[1024];
    }

    public SerialArrayList(SerialType<U> factory, int size) {
        this.factory = factory;
        this.unitSize = factory.getSize();
        this.buffer = new byte[this.unitSize * size];
    }

    public SerialArrayList<U> add(SerialArrayList<U> right) {
        return new SerialArrayList<U>(factory, this, right);
    }
    public synchronized void clear() {
        buffer = new byte[]{};
        maxByte = 0;
    }
    public int length() {
        return maxByte / unitSize;
    }
    public U get(int i) {
        ByteBuffer view = getView(i);
        try {
            return factory.read(view);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public synchronized int add(U value) {
        int length = length();
        set(length, value);
        return length;
    }
    public synchronized U update(int i, Function<U,U> updater) {
        U updated = updater.apply(this.get(i));
        set(i, updated);
        return updated;
    }
    public void set(int i, U value) {
        ensureCapacity((i+1) * unitSize);
        ByteBuffer view = getView(i);
        try {
            factory.write(view, value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ByteBuffer getView(int i) {
        ByteBuffer duplicate = ByteBuffer.wrap(buffer);
        duplicate.position(unitSize * i);
        return duplicate;
    }
    private synchronized void ensureCapacity(int bytes) {
        if(maxByte < bytes) {
            maxByte = bytes;
        }
        int targetBytes = buffer.length;
        while(targetBytes < bytes) targetBytes = Math.max(targetBytes * 2, 1);
        if(targetBytes > buffer.length) {
            buffer = Arrays.copyOf(buffer, targetBytes);
        }
    }

    public int addAll(Collection<U> data) {
        int startIndex = length();
        putAll(data, startIndex);
        return startIndex;
    }

    public void putAll(Collection<U> data, int startIndex) {
        putAll(new SerialArrayList<U>(factory, data), startIndex);
    }

    public synchronized void putAll(SerialArrayList<U> data, int startIndex) {
        ensureCapacity((startIndex * unitSize) + data.maxByte);
        System.arraycopy(data.buffer, 0, this.buffer, startIndex * unitSize, data.maxByte);
    }

    public int getMemorySize() {
        return buffer.length;
    }

    public SerialArrayList<U> copy() {
        return new SerialArrayList<U>(factory, this);
    }
}
