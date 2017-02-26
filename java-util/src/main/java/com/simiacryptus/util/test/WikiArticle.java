package com.simiacryptus.util.test;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WikiArticle {

    public static Stream<WikiArticle> load() {
        String url = "https://dumps.wikimedia.org/enwiki/latest/enwiki-latest-pages-articles-multistream.xml.bz2";
        String file = "enwiki-latest-pages-articles-multistream.xml.bz2";
        LinkedBlockingDeque<WikiArticle> queue = new LinkedBlockingDeque<>();
        AtomicBoolean closed = new AtomicBoolean(false);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    try (final InputStream in = new BZip2CompressorInputStream(Spool.load(url, file), true)) {
                        final SAXParserFactory spf = SAXParserFactory.newInstance();
                        spf.setNamespaceAware(false);
                        final SAXParser saxParser = spf.newSAXParser();
                        saxParser.parse(in, new DefaultHandler() {
                            Stack<String> prefix = new Stack<String>();
                            Stack<Map<String, AtomicInteger>> indexes = new Stack<Map<String, AtomicInteger>>();
                            private String title;
                            private int pages = 0;
                            private boolean verbose = false;
                            StringBuilder nodeString = new StringBuilder();

                            @Override
                            public void characters(final char[] ch, final int start,
                                                   final int length) throws SAXException {
                                if(Thread.currentThread().isInterrupted()) throw new RuntimeException(new InterruptedException());
                                this.nodeString.append(ch, start, length);
                                super.characters(ch, start, length);
                            }

                            @Override
                            public void endDocument() throws SAXException {
                                super.endDocument();
                            }

                            @Override
                            public void endElement(final String uri, final String localName,
                                                   final String qName) throws SAXException {
                                if(Thread.currentThread().isInterrupted()) throw new RuntimeException(new InterruptedException());
                                final String pop = this.prefix.pop();
                                this.indexes.pop();

                                final int length = this.nodeString.length();
                                String text = this.nodeString.toString().trim();
                                this.nodeString = new StringBuilder();

                                if ("page".equals(qName)) {
                                    this.title = null;
                                } else if ("title".equals(qName)) {
                                    this.title = text;
                                } else if ("text".equals(qName)) {
                                    queue.add(new WikiArticle(this.title, text));
                                }
                                super.endElement(uri, localName, qName);
                            }

                            @Override
                            public void startDocument() throws SAXException {
                                super.startDocument();
                            }

                            @Override
                            public void startElement(final String uri, final String localName,
                                                     final String qName, final Attributes attributes)
                                    throws SAXException {
                                if(Thread.currentThread().isInterrupted()) throw new RuntimeException(new InterruptedException());
                                int idx;
                                if (0 < this.indexes.size()) {
                                    final Map<String, AtomicInteger> index = this.indexes.peek();
                                    AtomicInteger cnt = index.get(qName);
                                    if (null == cnt) {
                                        cnt = new AtomicInteger(-1);
                                        index.put(qName, cnt);
                                    }
                                    idx = cnt.incrementAndGet();
                                } else {
                                    idx = 0;
                                }
                                String path = 0 == this.prefix.size() ? qName : this.prefix.peek() + "/" + qName;
                                if (0 < idx) {
                                    path += "[" + idx + "]";
                                }
                                this.prefix.push(path);
                                this.indexes.push(new HashMap<String, AtomicInteger>());
                                super.startElement(uri, localName, qName, attributes);
                            }

                        }, null);
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
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(new Iterator<WikiArticle>() {
            @Override
            public boolean hasNext() {
                return !queue.isEmpty() || !closed.get();
            }

            @Override
            public WikiArticle next() {
                try {
                    while(hasNext()) {
                        WikiArticle poll = queue.poll(1, TimeUnit.SECONDS);
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

    public final String title;
    public final String text;

    public WikiArticle(String title, String text) {
        this.title = title;
        this.text = text;
    }
}
