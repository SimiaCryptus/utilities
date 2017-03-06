package com.simiacryptus.util.test;

import com.simiacryptus.util.text.Spool;
import org.apache.commons.compress.utils.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class EnglishWords extends TestDocument {

    public static Stream<EnglishWords> load() {
        String url = "https://raw.githubusercontent.com/first20hours/google-10000-english/master/20k.txt";
        String file = "20k.txt";
        LinkedBlockingDeque<EnglishWords> queue = new LinkedBlockingDeque<>();
        AtomicBoolean closed = new AtomicBoolean(false);
        Thread thread = new Thread(new Runnable() {
            @SuppressWarnings("unused")
            @Override
            public void run() {
                try {
                    InputStream in = Spool.load(url, file);
                    String txt = new String(IOUtils.toByteArray(in), "UTF-8").replaceAll("\r", "");
                    List<String> list = Arrays.stream(txt.split("\n")).map(x -> x.replaceAll("[^\\w]", "")).collect(Collectors.toList());
                    Collections.shuffle(list);
                    for (String paragraph : list) {
                        queue.add(new EnglishWords(paragraph));
                    }
                } catch (final IOException e) {
                    // Ignore... end of stream
                } catch (final RuntimeException e) {
                    if (!(e.getCause() instanceof InterruptedException)) throw e;
                } catch (final Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    closed.set(true);
                }
            }
        });
        thread.start();
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(new Iterator<EnglishWords>() {
            @Override
            public boolean hasNext() {
                return !queue.isEmpty() || !closed.get();
            }

            @Override
            public EnglishWords next() {
                try {
                    while (hasNext()) {
                        EnglishWords poll = queue.poll(1, TimeUnit.SECONDS);
                        if (null != poll) return poll;
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
        }, Spliterator.DISTINCT), false).filter(x -> x != null);
    }

    public EnglishWords(String text) {
        super(text, text);
    }
}
