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

    public CharTrie rewrite(BiFunction<TrieNode, Map<Character, TrieNode>, TreeMap<Character, Long>> fn) {
        CharTrie result = new CharTrieIndex();
        rewriteSubtree(root(), result.root(), fn);
        return result.recomputeCursorDetails();
    }

    public static BiFunction<CharTrie,CharTrie,CharTrie> reducer(BiFunction<TrieNode, TrieNode, TreeMap<Character, Long>> fn) {
        return (left, right) -> left.reduce(right, fn);
    }

    public CharTrie add(CharTrie z) {
        return reduceSimple(z, (left, right) -> (null==left?0:left)+(null==right?0:right));
    }

    public CharTrie product(CharTrie z) {
        return reduceSimple(z, (left, right) -> (null==left?0:left)*(null==right?0:right));
    }

    public CharTrie divide(CharTrie z, int factor) {
        return reduceSimple(z, (left, right) -> (null==left?0:left)*factor/(null==right?1:right));
    }

    public CharTrie reduceSimple(CharTrie z, BiFunction<Long, Long, Long> fn) {
        return reduce(z, (left, right) -> {
            TreeMap<Character, ? extends TrieNode> leftChildren = null==left?new TreeMap<>():left.getChildrenMap();
            TreeMap<Character, ? extends TrieNode> rightChildren = null==right?new TreeMap<>():right.getChildrenMap();
            Map<Character, Long> map = Stream.of(rightChildren.keySet(), leftChildren.keySet()).flatMap(x -> x.stream()).distinct().collect(Collectors.toMap(c -> c, (Character c) -> {
                assert(null != leftChildren);
                assert(null != rightChildren);
                assert(null != c);
                TrieNode leftChild = leftChildren.get(c);
                Long l = null == leftChild ? null : leftChild.getCursorCount();
                TrieNode rightChild = rightChildren.get(c);
                Long r = null == rightChild ? null : rightChild.getCursorCount();
                return fn.apply(l,r);
            }));
            return new TreeMap<>(map);
        });
    }

    public CharTrie reduce(CharTrie right, BiFunction<TrieNode, TrieNode, TreeMap<Character, Long>> fn) {
        CharTrie result = new CharTrieIndex();
        reduceSubtree(root(), right.root(), result.root(), fn);
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
      long cursorCount = newChildren.stream().mapToLong(n->n.cursorCount).sum();
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

    private void rewriteSubtree(TrieNode sourceNode, TrieNode destNode, BiFunction<TrieNode, Map<Character, TrieNode>, TreeMap<Character, Long>> fn) {
        CharTrie result = destNode.getTrie();
        TreeMap<Character, ? extends TrieNode> sourceChildren = sourceNode.getChildrenMap();
        TreeMap<Character, Long> newCounts = fn.apply(sourceNode, (Map<Character, TrieNode>) sourceChildren);
        destNode.writeChildren(newCounts);
        TreeMap<Character, ? extends TrieNode> newChildren = destNode.getChildrenMap();
        newCounts.keySet().forEach(key -> {
            if (sourceChildren.containsKey(key)) {
                rewriteSubtree(sourceChildren.get(key), newChildren.get(key), fn);
            }
        });
    }

    private void reduceSubtree(TrieNode sourceNodeA, TrieNode sourceNodeB, TrieNode destNode, BiFunction<TrieNode, TrieNode, TreeMap<Character, Long>> fn) {
        destNode.writeChildren(fn.apply(sourceNodeA, sourceNodeB));
        TreeMap<Character, ? extends TrieNode> sourceChildrenA = null==sourceNodeA?null:sourceNodeA.getChildrenMap();
        TreeMap<Character, ? extends TrieNode> sourceChildrenB = null==sourceNodeB?null:sourceNodeB.getChildrenMap();
        destNode.getChildrenMap().forEach((key, newChild) -> {
            boolean containsA = null==sourceChildrenA?false:sourceChildrenA.containsKey(key);
            boolean containsB = null==sourceChildrenB?false:sourceChildrenB.containsKey(key);
            if (containsA && containsB) {
                reduceSubtree(sourceChildrenA.get(key), sourceChildrenB.get(key), newChild, fn);
            } else if (containsA) {
                reduceSubtree(sourceChildrenA.get(key), null, newChild, fn);
            } else if (containsB) {
                reduceSubtree(null, sourceChildrenB.get(key), newChild, fn);
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

    public long getIndexedSize() {
        return this.nodes.get(0).cursorCount;
    }

    public PPMCodec getCodec() {
        return new PPMCodec(this.truncate().rewrite((sourceNode, sourceChildren) -> {
            TreeMap<Character, Long> newCounts = new TreeMap<Character, Long>();
            sourceChildren.forEach((key, value) -> newCounts.put(key, value.getCursorCount()));
            if (0 == sourceNode.depth) newCounts.put(PPMCodec.ESCAPE, 1l);
            newCounts.put(PPMCodec.FALLBACK, 1l);
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
