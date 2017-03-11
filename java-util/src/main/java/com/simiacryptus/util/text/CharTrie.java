package com.simiacryptus.util.text;

import com.simiacryptus.util.data.SerialArrayList;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A character sequence index using a prefix tree, commonly known as a full-text
 * index or as the data structure behind markov chains. This implementation uses
 * serialized fixed-length ephemeral objects and a raw byte data store,
 * preventing object/reference count overhead.
 */
public class CharTrie {
    protected final SerialArrayList<NodeData> nodes;

    public CharTrie(SerialArrayList<NodeData> nodes) {
        super();
        this.nodes = nodes;
    }

    public CharTrie() {
        this(new SerialArrayList<>(NodeType.INSTANCE, new NodeData(PPMCodec.END_OF_STRING, (short)-1, -1, -1, 0)));
    }

    public CharTrie(CharTrie charTrie) {
        this(charTrie.nodes.copy());
    }

    public TrieNode root() {
      return new TrieNode(this, (short) 0, 0, null);
    }

    public CharTrie rewrite(BiFunction<TrieNode, Map<Character, TrieNode>, TreeMap<Character, Integer>> fn) {
        CharTrie result = new CharTrieIndex();
        rewriteSubtree(root(), result.root(), fn);
        return result.recomputeCursorDetails();
    }

    CharTrie recomputeCursorDetails() {
      recomputeCursorTotals(root());
      recomputeCursorPositions(root(), 0);
      return this;
    }

    private NodeData recomputeCursorTotals(TrieNode node) {
      List<NodeData> newChildren = node.getChildren().map(child->recomputeCursorTotals(child)).collect(Collectors.toList());
      if(newChildren.isEmpty()) return node.getData();
      int cursorCount = newChildren.stream().mapToInt(n->n.cursorCount).sum();
      assert(0 < cursorCount);
      return node.update(d -> d.setCursorCount(cursorCount));
    }

    private void recomputeCursorPositions(TrieNode node, final int position) {
      node.update(n->n.setFirstCursorIndex(position));
      int childPosition = position;
        Stream<TrieNode> stream = node.getChildren().map(x -> x);
        for(TrieNode child : stream.collect(Collectors.toList())) {
        recomputeCursorPositions(child, childPosition);
        childPosition += child.getCursorCount();
      }
    }

    private void rewriteSubtree(TrieNode sourceNode, TrieNode destNode, BiFunction<TrieNode, Map<Character, TrieNode>, TreeMap<Character, Integer>> fn) {
        CharTrie result = destNode.getTrie();
        Stream<TrieNode> stream = sourceNode.getChildren().map(x -> x);
        Map<Character, TrieNode> sourceChildren = stream.collect(Collectors.toMap(n -> n.getChar(), n -> n));
        TreeMap<Character, Integer> newCounts = fn.apply(destNode, sourceChildren);
        destNode.writeChildren(newCounts);
        Stream<TrieNode> stream1 = destNode.getChildren().map(x -> x);
        Map<Character, TrieNode> newChildren = stream1.collect(Collectors.toMap(n -> n.getChar(), n -> n));
        newCounts.keySet().forEach(key -> {
            if (sourceChildren.containsKey(key)) {
                rewriteSubtree(sourceChildren.get(key), newChildren.get(key), fn);
            }
        });
    }

    /**
     * Locate a node by finding the maximum prefix match with the given string
     *
     * @param search
     * @return
     */
    public TrieNode traverse(String search) {
        return root().traverse(search);
    }

    public int getNodeCount() {
        return nodes.length();
    }

    public TrieNode matchEnd(String search) {
      int min = 0;
      int max = search.length();
      int winner = -1;
      int i = Math.min(max, 12);
      while (max > min) {
        String attempt = search.substring(search.length() - i);
        TrieNode cursor = traverse(attempt);
        if (cursor.getString().equals(attempt)) {
          min = Math.max(min, i + 1);
          winner = Math.max(winner, i);
        } else {
          max = Math.min(max, i - 1);
        }
        i = (3 * max + min) / 4;
      }
      return traverse(search.substring(search.length() - i));
    }

    public TrieNode matchPredictor(String search) {
      TrieNode cursor = matchEnd(search);
      if (cursor.getNumberOfChildren() > 0)
        return cursor;
      String string = cursor.getString();
      if(string.isEmpty()) return null;
      return matchPredictor(string.substring(1));
    }

    public CharTrie copy() {
      return new CharTrie(this);
    }

    public int getMemorySize() {
        return this.nodes.getMemorySize();
    }

    public PPMCodec getCodec() {
        return new PPMCodec(this.truncate().rewrite((sourceNode, sourceChildren) -> {
            TreeMap<Character, Integer> newCounts = new TreeMap<Character, Integer>();
            sourceChildren.forEach((key, value) -> newCounts.put(key, value.getCursorCount()));
            if (0 == sourceNode.depth) newCounts.put(PPMCodec.ESCAPE, 1);
            newCounts.put(PPMCodec.FALLBACK, 1);
            return newCounts;
        }));
    }

    public PPMCodec getPackingCodec() {
        return new PPMCodec(this.truncate());
    }

    public TextGenerator getGenerator() {
        return new TextGenerator(this.truncate().copy());
    }

    protected CharTrie truncate() {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CharTrie charTrie = (CharTrie) o;

        return nodes.equals(charTrie.nodes);
    }

    @Override
    public int hashCode() {
        return nodes.hashCode();
    }
}
