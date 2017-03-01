package com.simiacryptus.util.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class TweetSentiment {

    public static Stream<TweetSentiment> load() {
        String url = "http://thinknook.com/wp-content/uploads/2012/09/Sentiment-Analysis-Dataset.zip";
        String file = "Sentiment-Analysis-Dataset.zip";
        LinkedBlockingDeque<TweetSentiment> queue = new LinkedBlockingDeque<>();
        AtomicBoolean closed = new AtomicBoolean(false);
        Thread thread = new Thread(new Runnable() {
            @SuppressWarnings("unused")
			@Override
            public void run() {
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
                } finally {
                    closed.set(true);
                }
            }
        });
        thread.start();
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(new Iterator<TweetSentiment>() {
            @Override
            public boolean hasNext() {
                return !queue.isEmpty() || !closed.get();
            }

            @Override
            public TweetSentiment next() {
                try {
                    while(hasNext()) {
                        TweetSentiment poll = queue.poll(1, TimeUnit.SECONDS);
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

    public final int category;
    public final String text;

    public TweetSentiment(String text, int category) {
        this.category = category;
        this.text = text;
    }
}
