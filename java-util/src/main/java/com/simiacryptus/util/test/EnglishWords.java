package com.simiacryptus.util.test;

import org.apache.commons.compress.utils.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class EnglishWords extends TestDocument {
    public static String url = "https://raw.githubusercontent.com/first20hours/google-10000-english/master/20k.txt";
    public static String file = "20k.txt";
    private static final ArrayList<EnglishWords> queue = new ArrayList<>();
    private static volatile Thread thread;

    public static void clear() throws InterruptedException {
        if (thread != null) {
            synchronized (WikiArticle.class) {
                if (thread != null) {
                    thread.interrupt();
                    thread.join();
                    thread = null;
                    queue.clear();
                }
            }
        }
    }
    public static Stream<EnglishWords> load() {
        if (thread == null) {
            synchronized (WikiArticle.class) {
                if (thread == null) {
                    thread = new Thread(EnglishWords::read);
                    thread.setDaemon(true);
                    thread.start();
                }
            }
        }
        Iterator<EnglishWords> iterator = new AsyncListIterator<>(queue, thread);
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.DISTINCT), false).filter(x -> x != null);
    }

    private static void read() {
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
        }
    }

    public EnglishWords(String text) {
        super(text, text);
    }
}
