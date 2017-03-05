package com.simiacryptus.util.text;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class MarkdownPrintStream extends PrintStream {

    private final List<PrintStream> teeStreams = new ArrayList<>();

    public MarkdownPrintStream(OutputStream inner) {
        super(inner);
    }

    public MarkdownPrintStream addCopy(PrintStream out) {
        teeStreams.add(out);
        return this;
    }

    @Override
    public void println(String x) {
        super.println(x);
        teeStreams.forEach(t->t.println(x));
    }

    public void out(String fmt, Object... args) {
        this.println(0==args.length?fmt:String.format(fmt, args));
    }
}
