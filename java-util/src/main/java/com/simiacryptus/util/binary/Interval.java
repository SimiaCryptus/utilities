package com.simiacryptus.util.binary;

public class Interval {
  public final int start;
  public final int max;
  public final int length;
  
  public Interval(int start, int length, int max) {
    super();
    if(0 >= max) throw new IllegalArgumentException();
    if(0 > start || max <= start) throw new IllegalArgumentException();
    if(0 >= length) throw new IllegalArgumentException();
    if(max < start+length) throw new IllegalArgumentException();
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
      long b = endBits.range(0, i).toLong();
      if(b - a > 1) {
        Bits bits = new Bits(a+1, i);
        assert(Bits.ZERO.equals(bits.range(0,1)));
        return bits.range(1);
      }
    }
    throw new RuntimeException();
  }
  
}
