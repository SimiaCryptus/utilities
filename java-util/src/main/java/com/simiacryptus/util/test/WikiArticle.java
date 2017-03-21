package com.simiacryptus.util.test;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.net.URI;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WikiArticle extends TestDocument {

    public static Loader ENGLISH = new Loader(URI.create(
            "https://dumps.wikimedia.org/enwiki/latest/enwiki-latest-pages-articles.xml.bz2"), 10000);
    public static Loader GERMAN = new Loader(URI.create(
            "https://dumps.wikimedia.org/dewiki/latest/dewiki-latest-pages-articles.xml.bz2"), 10000);
    public static Loader FRENCH = new Loader(URI.create(
            "https://dumps.wikimedia.org/frwiki/latest/frwiki-latest-pages-articles.xml.bz2"), 10000);

    public static class Loader {
        private final String url;
        private final String file;
        private final int articleLimit;
        private volatile Thread thread;
        private final List<WikiArticle> queue = Collections.synchronizedList(new ArrayList<>());

        public Loader(URI uri, int articleLimit) {
            url = uri.toString();
            this.articleLimit = articleLimit;
            String path = uri.getPath();
            String[] split = path.split("/");
            file = split[split.length-1];
        }

        public void clear() throws InterruptedException {
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
        public Stream<WikiArticle> load() {
            if (thread == null) {
                synchronized (WikiArticle.class) {
                    if (thread == null) {
                        thread = new Thread(this::read);
                        thread.setDaemon(true);
                        thread.start();
                    }
                }
            }
            Iterator<WikiArticle> iterator = new AsyncListIterator<>(queue, thread);
            return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.DISTINCT), false).filter(x -> x != null);
        }

        private void read() {
            try {
                try (final InputStream in = new BZip2CompressorInputStream(Spool.load(url, file), true)) {
                    final SAXParserFactory spf = SAXParserFactory.newInstance();
                    spf.setNamespaceAware(false);
                    final SAXParser saxParser = spf.newSAXParser();
                    saxParser.parse(in, new DefaultHandler() {
                        Stack<String> prefix = new Stack<String>();
                        Stack<Map<String, AtomicInteger>> indexes = new Stack<Map<String, AtomicInteger>>();
                        private String title;
                        StringBuilder nodeString = new StringBuilder();

                        @Override
                        public void characters(final char[] ch, final int start,
                                               final int length) throws SAXException {
                            if (Thread.currentThread().isInterrupted())
                                throw new RuntimeException(new InterruptedException());
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
                            if (Thread.currentThread().isInterrupted())
                                throw new RuntimeException(new InterruptedException());
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
                                //System.p.println(String.format("Read #%s - %s", queue.size(), this.title));
                                queue.add(new WikiArticle(this.title, text));
                                if(queue.size()>articleLimit)
                                    throw new RuntimeException(new InterruptedException());
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
                            if (Thread.currentThread().isInterrupted())
                                throw new RuntimeException(new InterruptedException());
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
            } catch (final RuntimeException e) {
                if(!(e.getCause() instanceof InterruptedException)) e.printStackTrace();
            } catch (final Exception e) {
                e.printStackTrace();
            } finally {
                System.err.println("Read thread exit");
            }
        }

    }

    public WikiArticle(String title, String text) {
        super(title, text);
    }

}
