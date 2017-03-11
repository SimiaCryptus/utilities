package com.simiacryptus.util.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class TweetSentiment extends TestDocument {
    public static String url = "http://thinknook.com/wp-content/uploads/2012/09/Sentiment-Analysis-Dataset.zip";
    public static String file = "Sentiment-Analysis-Dataset.zip";
    private static final ArrayList<TweetSentiment> queue = new ArrayList<>();
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
    public static Stream<TweetSentiment> load() {
        if (thread == null) {
            synchronized (WikiArticle.class) {
                if (thread == null) {
                    thread = new Thread(TweetSentiment::read);
                    thread.setDaemon(true);
                    thread.start();
                }
            }
        }
        Iterator<TweetSentiment> iterator = new AsyncListIterator<>(queue, thread);
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.DISTINCT), false).filter(x -> x != null);
    }

    private static void read() {
        try {
            InputStream load = Spool.load(url, file);
            try (final ZipInputStream in = new ZipInputStream(load)) {
                ZipEntry entry = in.getNextEntry();
                try (final BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                    String[] header = reader.readLine().split(",");
                    String read;
                    while(null != (read = reader.readLine())) {
                        String[] values = read.split(",");
                        queue.add(new TweetSentiment(values[3].trim(), Integer.parseInt(values[1].trim())));
                    }
                }
            }
        } catch (final IOException e) {
            // Ignore... end of stream
        } catch (final RuntimeException e) {
            if(!(e.getCause() instanceof InterruptedException)) throw e;
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public final int category;

    public TweetSentiment(String text, int category) {
        super(text, text);
        this.category = category;
    }
}
