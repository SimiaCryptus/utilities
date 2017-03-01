package com.simiacryptus.util.binary;

public class Interval {
  public final int start;
  public final int max;
  public final int length;
  
  public Interval(int start, int length, int max) {
    super();
    this.start = start;
    this.max = max;
    this.length = length;
  }

  public int end() {
    return start + length;
  }

  public Bits toBits() {
    int peekBits = (int)Math.ceil(Math.log(max)/Math.log(2))+4;
    Bits startBits = Bits.divide(start, max, peekBits).padRight(peekBits);
    Bits endBits = Bits.divide(end(), max, peekBits).padRight(peekBits);
    for(int i=0;i<peekBits;i++) {
      long a = startBits.range(0, i).toLong();
      long b = (end() == max) ? 1<<i : endBits.range(0, i).toLong();
      if(b - a > 1) {
        return new Bits(a+1, i);
      }
    }
    throw new RuntimeException();
  }
  
}
