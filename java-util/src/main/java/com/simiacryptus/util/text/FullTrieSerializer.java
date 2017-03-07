package com.simiacryptus.util.text;

import com.simiacryptus.util.binary.BitInputStream;
import com.simiacryptus.util.binary.BitOutputStream;
import com.simiacryptus.util.binary.Bits;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FullTrieSerializer {

    public byte[] serialize(CharTrie charTrie) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            try (BitOutputStream out = new BitOutputStream(buffer)) {
                int level = 0;
                while (serialize(charTrie.root(), out, level++) > 0) {
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return buffer.toByteArray();
    }

    private int serialize(TrieNode root, BitOutputStream out, int level) {
        AtomicInteger nodesWritten = new AtomicInteger(0);
        if (0 == level) {
            TreeMap<Character, ? extends TrieNode> children = root.getChildrenMap();
            try {
                int size = children.size();
                out.writeVarLong(size);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            children.forEach((token, child) -> {
                try {
                    out.write(child.getChar());
                    out.writeVarLong(null == child ? 0 : child.getCursorCount());
                    nodesWritten.incrementAndGet();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } else {
            root.streamDecendents(level).forEach(node -> {
                AtomicInteger nodeCounter = new AtomicInteger();
                TreeMap<Character, ? extends TrieNode> godchildren = node.godparent().getChildrenMap();
                TreeMap<Character, ? extends TrieNode> children = node.getChildrenMap();
                godchildren.forEach((token, godchild) -> {
                    TrieNode child = children.get(token);
                    try {
                        int upperBound = Math.min(node.getCursorCount() - nodeCounter.get(), godchild.getCursorCount());
                        if(upperBound > 0) {
                            if(null == child) {
                                out.write(Bits.ZERO);
                            } else {
                                out.write(Bits.ONE);
                                int childCount = child.getCursorCount();
                                assert(childCount <= node.getCursorCount() - nodeCounter.get());
                                assert(childCount <= godchild.getCursorCount());
                                out.writeBoundedLong(childCount, upperBound);
                                nodesWritten.incrementAndGet();
                                nodeCounter.addAndGet(childCount);
                                //godchild.decrementCursorCount(childCount);
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            });
        }
        return nodesWritten.get();

    }

    public CharTrie deserialize(byte[] bytes) {
        CharTrie trie = new CharTrie();
        BitInputStream in = new BitInputStream(new ByteArrayInputStream(bytes));
        int level = 0;
        while (deserialize(trie.root(), in, level++) > 0) {
        }
        trie.recomputeCursorDetails();
        return trie;
    }

    private int deserialize(TrieNode root, BitInputStream in, int level) {
        AtomicInteger nodesRead = new AtomicInteger(0);
        if (0 == level) {
            try {
                long numberOfChildren = in.readVarLong();
                TreeMap<Character, Integer> children = new TreeMap<>();
                for(int i=0;i<numberOfChildren;i++) {
                    char c = (char) in.read(16).toLong();
                    long cnt = in.readVarLong();
                    children.put(c, (int) cnt);
                    nodesRead.incrementAndGet();
                }
                root.writeChildren(children);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            root.streamDecendents(level).forEach(node -> {
                AtomicInteger nodeCounter = new AtomicInteger();
                TrieNode godparent = node.godparent();
                TreeMap<Character, ? extends TrieNode> godchildren = godparent.getChildrenMap();
                TreeMap<Character, Integer> children = new TreeMap<>();
                godchildren.forEach((token, godchild) -> {
                    try {
                        int upperBound = Math.min(node.getCursorCount() - nodeCounter.get(), godchild.getCursorCount());
                        if(upperBound > 0) {
                            if(in.readBool()) {
                                long childCount = in.readBoundedLong(upperBound);
                                assert(childCount>0);
                                children.put(token, (int) childCount);
                                nodesRead.incrementAndGet();
                                nodeCounter.addAndGet((int) childCount);
                                //godchild.decrementCursorCount((int) childCount);
                            }

                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                node.writeChildren(children);
            });
        }

        return nodesRead.get();
    }
}
