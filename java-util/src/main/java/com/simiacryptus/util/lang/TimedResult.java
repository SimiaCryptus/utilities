package com.simiacryptus.util.lang;

import java.util.function.Supplier;

public class TimedResult<T> {
  public final T obj;
  public final long timeNanos;

  public TimedResult(T obj, long timeNanos) {
    this.obj = obj;
    this.timeNanos = timeNanos;
  }

  public static <T> TimedResult<T> time(Supplier<T> fn) {
    long start = System.nanoTime();
    T result = fn.get();
    return new TimedResult(result, System.nanoTime() - start);
  }

  public double seconds() {
    return timeNanos / 1000000000.0;
  }
}
