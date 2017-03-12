package com.simiacryptus.util.text;

import com.simiacryptus.util.binary.Bits;
import com.simiacryptus.util.binary.Interval;

import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class TrieNode {
    public final short depth;
    public final TrieNode parent;
    protected final CharTrie trie;
    protected final int index;
    private transient NodeData data;

    public TrieNode(CharTrie trie, short depth, int index, TrieNode parent) {
        this.trie = trie;
        this.depth = depth;
        this.index = index;
        this.parent = parent;
    }

    NodeData getData() {
      if (null == data) {
        synchronized (this) {
          if (null == data) {
            this.data = this.trie.nodes.get(index);
          }
        }
      }
      return data;
    }

    public TrieNode godparent() {
        String string = getRawString();
        return null == parent ? null : this.trie.traverse(string.substring(1));
    }

    public TrieNode refresh() {
      this.data = null;
      return this;
    }

    public String getString(TrieNode root) {
      if(this == root) return "";
      String parentStr = null == parent ? "" : parent.getString(root);
      return parentStr + getToken();
    }

    public String getRawString() {
        return (null == parent ? "" : parent.getRawString()) + (0 == depth ? "" : new String(new char[]{getChar()}));
    }

    public String getString() {
        return (null == parent ? "" : parent.getString()) + (0 == depth ? "" : getToken());
    }

    public String getDebugString() {
      return getDebugString(getTrie().root());
    }

    public String getDebugString(TrieNode root) {
      if(this == root) return "";
      String parentStr = null == parent ? "" : parent.getDebugString(root);
      return parentStr + getDebugToken();
    }

    public String getDebugToken() {
      char asChar = getChar();
      if(asChar == PPMCodec.FALLBACK) return "<STOP>";
      if(asChar == PPMCodec.END_OF_STRING) return "<NULL>";
      if(asChar == PPMCodec.ESCAPE) return "<ESC>";
      if(asChar == '\\') return "\\\\";
      if(asChar == '\n') return "\\n";
      return new String(new char[]{asChar});
    }

    public String getToken() {
      char asChar = getChar();
      if(asChar == PPMCodec.FALLBACK) return "";
      if(asChar == PPMCodec.END_OF_STRING) return "";
      if(asChar == PPMCodec.ESCAPE) return "";
      return new String(new char[]{asChar});
    }

    public char getChar() {
      return getData().token;
    }

    public short getNumberOfChildren() {
      return getData().numberOfChildren;
    }

    public short getDepth() {
      return depth;
    }

    public int getCursorIndex() {
      return getData().firstCursorIndex;
    }

    public int getCursorCount() {
      return getData().cursorCount;
    }

    public TrieNode visitFirst(Consumer<? super TrieNode> visitor) {
      visitor.accept(this);
      TrieNode refresh = refresh();
      refresh.getChildren().forEach(n -> n.visitFirst(visitor));
      return refresh;
    }

    public TrieNode visitLast(Consumer<? super TrieNode> visitor) {
      getChildren().forEach(n -> n.visitLast(visitor));
      visitor.accept(this);
      return refresh();
    }

    public Stream<? extends TrieNode> getChildren() {
      if (getData().firstChildIndex >= 0) {
        return IntStream.range(0, getData().numberOfChildren)
            .mapToObj(i -> new TrieNode(this.trie, (short) (depth + 1), getData().firstChildIndex + i, TrieNode.this));
      } else {
        return Stream.empty();
      }
    }

    public Optional<? extends TrieNode> getChild(char token) {
        NodeData data = getData();
        int min = data.firstChildIndex;
        int max = data.firstChildIndex + data.numberOfChildren - 1;
        while(min <= max) {
            int i = (min + max) / 2;
            TrieNode node = new TrieNode(this.trie, (short) (depth + 1), i, TrieNode.this);
            char c = node.getChar();
            int compare = Character.compare(c, token);
            if(c < token) {
                // node.getChar() < token
                min = i + 1;
            } else if(c > token) {
                // node.getChar() > token
                max = i - 1;
            } else {
                return Optional.of(node);
            }
        }
        //assert !getChildren().filter(x -> x.getChar() == token).findFirst().isPresent();
        return Optional.empty();
    }

    protected void decrementCursorCount(int count) {
      this.trie.nodes.update(index, data -> data.setCursorCount(Math.max(data.cursorCount - count, 0)));
      if (null != parent)
        parent.decrementCursorCount(count);
    }

    public TrieNode traverse(String str) {
      if (str.isEmpty())
        return this;
      return getChild(str.charAt(0)).map(n -> n.traverse(str.substring(1))).orElse(this);
    }

    public boolean containsCursor(long cursorId) {
      if (cursorId < getData().firstCursorIndex)
        return false;
      if (cursorId >= (getData().firstCursorIndex + getData().cursorCount))
        return false;
      return true;
    }

    public TrieNode traverse(long cursorId) {
      if (!containsCursor(cursorId))
        throw new IllegalArgumentException();
      return getChildren().filter(n -> n.containsCursor(cursorId)).findFirst().map(n -> n.traverse(cursorId))
          .orElse(this);
    }

    public void removeCursorCount() {
        decrementCursorCount(getCursorCount());
    }

    public Bits bitsTo(TrieNode toNode) {
      return intervalTo(toNode).toBits();
    }

    public Interval intervalTo(TrieNode toNode) {
      return new Interval(toNode.getCursorIndex() - this.getCursorIndex(),
              toNode.getCursorCount(), this.getCursorCount());
    }

    public boolean hasChildren() {
      return 0 < data.numberOfChildren;
    }

    NodeData update(Function<NodeData, NodeData> update) {
      data = trie.nodes.update(index, update);
      return data;
    }

    public CharTrie getTrie() {
      return trie;
    }

    public boolean isStringTerminal() {
      if(getChar() == PPMCodec.END_OF_STRING) return true;
      if(getChar() == PPMCodec.FALLBACK && null != parent) return parent.isStringTerminal();
      return false;
    }

    public Stream<? extends TrieNode> streamDecendents(int level) {
        assert (level>0);
        if(level == 1) {
            return getChildren();
        } else {
            return getChildren().flatMap(child->child.streamDecendents(level-1));
        }
    }

    void writeChildren(TreeMap<Character, Integer> counts) {
        int firstIndex = trie.nodes.length();
        counts.forEach((k, v) -> {
            if(v > 0) trie.nodes.add(new NodeData(k, (short) -1, -1, v, -1));
        });
        short length = (short) (trie.nodes.length() - firstIndex);
        update(n -> n.setFirstChildIndex(firstIndex).setNumberOfChildren(length));
        data = null;
    }

    public TreeMap<Character, ? extends TrieNode> getChildrenMap() {
        TreeMap<Character, TrieNode> map = new TreeMap<>();
        getChildren().forEach(x->map.put(x.getChar(),x));
        return map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrieNode trieNode = (TrieNode) o;
        if(getCursorCount() != ((TrieNode) o).getCursorCount()) return false;
        return getChildrenMap().equals(trieNode.getChildrenMap());
    }

    @Override
    public int hashCode() {
        return getChildrenMap().hashCode() ^ Integer.hashCode(getCursorCount());
    }
}
