package com.simiacryptus.util.test;

import org.apache.commons.compress.utils.IOUtils;

import java.io.InputStream;
import java.net.URI;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Misspelling extends TestDocument {

    public static Loader BIRKBECK = new Loader(URI.create(
            "http://www.dcs.bbk.ac.uk/~ROGER/missp.dat"), 10000);

    public static class Loader {
        private final String url;
        private final String file;
        private final int articleLimit;
        private volatile Thread thread;
        private final List<Misspelling> queue = Collections.synchronizedList(new ArrayList<>());

        public Loader(URI uri, int articleLimit) {
            url = uri.toString();
            this.articleLimit = articleLimit;
            String path = uri.getPath();
            String[] split = path.split("/");
            file = split[split.length-1];
        }

        public void clear() throws InterruptedException {
            if (thread != null) {
                synchronized (Misspelling.class) {
                    if (thread != null) {
                        thread.interrupt();
                        thread.join();
                        thread = null;
                        queue.clear();
                    }
                }
            }
        }
        public Stream<Misspelling> load() {
            if (thread == null) {
                synchronized (Misspelling.class) {
                    if (thread == null) {
                        thread = new Thread(this::read);
                        thread.setDaemon(true);
                        thread.start();
                    }
                }
            }
            Iterator<Misspelling> iterator = new AsyncListIterator<>(queue, thread);
            return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.DISTINCT), false).filter(x -> x != null);
        }

        private void read() {
            try {
                try (final InputStream in = Spool.load(url, file)) {
                    String txt = new String(IOUtils.toByteArray(in), "UTF-8").replaceAll("\r", "");
                    List<String> list = Arrays.asList(txt.split("\n"));
                    String activeItem = "";
                    for(String item : list) {
                        if(item.startsWith("$")) {
                            activeItem = item.substring(1);
                        } else {
                            queue.add(new Misspelling(activeItem, item));
                        }
                    }

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

    public Misspelling(String correct, String misspelling) {
        super(correct, misspelling);
    }

}
