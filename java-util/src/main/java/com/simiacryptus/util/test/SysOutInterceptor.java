package com.simiacryptus.util.test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Supplier;

public class SysOutInterceptor extends PrintStream {

    public static class LoggedResult<T> {
        public final T obj;
        public final String log;

        public LoggedResult(T obj, String log) {
            this.obj = obj;
            this.log = log;
        }
    }

    public static final SysOutInterceptor INSTANCE = init();

    public static <T> LoggedResult<T> withOutput(Supplier<T> fn) {
        //init();
        try {
            ByteArrayOutputStream buff = new ByteArrayOutputStream();
            try(PrintStream ps = new PrintStream(buff)) {
                INSTANCE.threadHandler.set(ps);
                T result = fn.get();
                return new LoggedResult<T>(result, buff.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            INSTANCE.threadHandler.remove();
        }
    }

    public static LoggedResult<Void> withOutput(Runnable fn) {
        try {
            ByteArrayOutputStream buff = new ByteArrayOutputStream();
            try(PrintStream ps = new PrintStream(buff)) {
                INSTANCE.threadHandler.set(ps);
                fn.run();
                return new LoggedResult<Void>(null, buff.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            INSTANCE.threadHandler.remove();
        }
    }

    private static SysOutInterceptor init() {
        if(!(System.out instanceof SysOutInterceptor)) {
            SysOutInterceptor out = new SysOutInterceptor(System.out);
            System.setOut(out);
            return out;
        }
        return (SysOutInterceptor) System.out;
    }

    private ThreadLocal<PrintStream> threadHandler = new ThreadLocal<PrintStream>() {
        @Override
        protected PrintStream initialValue() {
            return (PrintStream) out;
        }
    };

    public SysOutInterceptor(PrintStream out) {
        super(out);
    }

    @Override
    public void print(String s) {
        threadHandler.get().print(s);
    }

    @Override
    public void println(String x) {
        threadHandler.get().println(x);
    }
}
