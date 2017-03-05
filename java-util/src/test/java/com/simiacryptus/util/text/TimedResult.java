package com.simiacryptus.util.text;

import java.util.function.Supplier;

public class TimedResult<T> {
  T obj;
  long timeNanos;

  public TimedResult(T obj, long timeNanos) {
    this.obj = obj;
    this.timeNanos = timeNanos;
  }

  public static <T> TimedResult<T> time(Supplier<T> fn) {
    long start = System.nanoTime();
    T result = fn.get();
    return new TimedResult(result, System.nanoTime() - start);
  }
}
