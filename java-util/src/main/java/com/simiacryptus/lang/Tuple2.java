package com.simiacryptus.lang;

public class Tuple2<A, B> {
    public final A _1;
    public final B _2;
    private B second;

    public Tuple2(A a, B b) {
        _1 = a;
        _2 = b;
    }

    public A getFirst() {
        return _1;
    }

    public B getSecond() {
        return _2;
    }
}
