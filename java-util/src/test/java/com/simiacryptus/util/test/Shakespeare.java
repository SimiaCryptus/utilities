package com.simiacryptus.util.test;

import com.simiacryptus.util.text.Spool;
import org.apache.commons.compress.utils.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Shakespeare extends TestDocument {

    public static Stream<Shakespeare> load() {
        String url = "http://www.gutenberg.org/cache/epub/100/pg100.txt";
        String file = "Shakespeare.txt";
        LinkedBlockingDeque<Shakespeare> queue = new LinkedBlockingDeque<>();
        AtomicBoolean closed = new AtomicBoolean(false);
        Thread thread = new Thread(new Runnable() {
            @SuppressWarnings("unused")
			@Override
            public void run() {
                try {
                    InputStream in = Spool.load(url, file);
                    String txt = new String(IOUtils.toByteArray(in), "UTF-8").replaceAll("\r", "");
                    for(String paragraph : txt.split("\n\\s*\n")) {
                            queue.add(new Shakespeare(paragraph));
                    }
                } catch (final IOException e) {
                    // Ignore... end of stream
                } catch (final RuntimeException e) {
                    if(!(e.getCause() instanceof InterruptedException)) throw e;
                } catch (final Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    closed.set(true);
                }
            }
        });
        thread.start();
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(new Iterator<Shakespeare>() {
            @Override
            public boolean hasNext() {
                return !queue.isEmpty() || !closed.get();
            }

            @Override
            public Shakespeare next() {
                try {
                    while(hasNext()) {
                        Shakespeare poll = queue.poll(1, TimeUnit.SECONDS);
                        if(null != poll) return poll;
                    }
                    return null;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            protected void finalize() throws Throwable {
                thread.interrupt();
                super.finalize();
            }
        }, Spliterator.DISTINCT), false).filter(x->x!=null);
    }

    public Shakespeare(String text) {
        super(text, text);
    }
}
