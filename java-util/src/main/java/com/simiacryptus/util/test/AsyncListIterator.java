package com.simiacryptus.util.test;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Andrew Charneski on 3/11/2017.
 */
public class AsyncListIterator<T> implements Iterator<T> {
    private final List<T> queue;
    private final Thread thread;
    int index = -1;

    public AsyncListIterator(List<T> queue, Thread thread) {
        this.thread = thread;
        this.queue = queue;
    }

    @Override
    public boolean hasNext() {
        return index < queue.size() || thread.isAlive();
    }

    @Override
    public T next() {
        try {
            while (hasNext()) {
                if (++index < queue.size()) {
                    return queue.get(index);
                } else {
                    Thread.sleep(100);
                }
            }
            return null;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
