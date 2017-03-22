package com.simiacryptus.util.text;

import com.simiacryptus.util.binary.Bits;
import com.simiacryptus.util.binary.Interval;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class TrieNode {
    private transient short depth = -1;
    private transient TrieNode parent = null;
    protected final CharTrie trie;
    protected final int index;
    private transient NodeData data;

    public TrieNode(CharTrie trie, int index) {
        this.trie = trie;
        assert(depth < 100);
        this.index = index;
    }

    public TrieNode(CharTrie trie, int index, TrieNode parent) {
        this.trie = trie;
        this.depth = (short) (null==parent?0:(parent.depth+1));
        assert(depth < 100);
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
        if(0 == getDepth()) return null;
        if(1 == getDepth()) return trie.root();
        if(null != trie.godparentIndex && trie.godparentIndex.length > index) {
            int godparentIndex = trie.godparentIndex[this.index];
            if(godparentIndex >= 0) {
               return new TrieNode(trie, godparentIndex);
           }
        }
        TrieNode godparent = this.getParent().godparent().getChild(getChar()).orElseGet(()->null);
        if(null != trie.godparentIndex && trie.godparentIndex.length > index) {
            trie.godparentIndex[this.index] = godparent.index;
        }
        return godparent;
    }

    public TrieNode refresh() {
      this.data = null;
      return this;
    }

    public String getString(TrieNode root) {
      if(this == root) return "";
      String parentStr = null == getParent() ? "" : getParent().getString(root);
      return parentStr + getToken();
    }

    public String getRawString() {
        return (0 == depth ? "" : (getParent().getRawString() + new String(new char[]{getChar()})));
    }

    public String getString() {
        return (null == getParent() ? "" : getParent().getString()) + (0 == depth ? "" : getToken());
    }

    public String getDebugString() {
      return getDebugString(getTrie().root());
    }

    public String getDebugString(TrieNode root) {
      if(this == root) return "";
      String parentStr = null == getParent() ? "" : getParent().getDebugString(root);
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
        if(-1 == depth) {
            synchronized(this) {
                if(-1 == depth) {
                    TrieNode parent = getParent();
                    depth = (short) (null==parent?0:(parent.getDepth() + 1));
                }
            }
        }
        return depth;
    }

    public long getCursorIndex() {
      return getData().firstCursorIndex;
    }

    public long getCursorCount() {
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
            .mapToObj(i -> new TrieNode(this.trie, getData().firstChildIndex + i, TrieNode.this));
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
            TrieNode node = new TrieNode(this.trie, i, TrieNode.this);
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
        //assert !getChildren().keywords(x -> x.getChar() == token).findFirst().isPresent();
        return Optional.empty();
    }

    protected void decrementCursorCount(long count) {
      this.trie.nodes.update(index, data -> data.setCursorCount(Math.max(data.cursorCount - count, 0)));
      if (null != getParent())
        getParent().decrementCursorCount(count);
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
      return 0 < getNumberOfChildren();
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
      if(getChar() == PPMCodec.FALLBACK && null != getParent()) return getParent().isStringTerminal();
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

    void writeChildren(TreeMap<Character, Long> counts) {
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

    public Map<Character,TrieNode> getGodChildren() {
        String postContext = this.getString().substring(1);
        return trie.tokens().stream().collect(Collectors.toMap(x->x, token->{
            TrieNode traverse = trie.traverse(token + postContext);
            return traverse.getString().equals(token + postContext)?traverse:null;
        })).entrySet().stream().filter(e->null!=e.getValue()).collect(Collectors.toMap(e->e.getKey(),e->e.getValue()));
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
        return getChildrenMap().hashCode() ^ Long.hashCode(getCursorCount());
    }

    public TrieNode getParent() {
        if(null == parent && -1 == depth) {
            synchronized (this) {
                if(null == parent) {
                    parent = new TrieNode(trie, trie.parentIndex[index]);
                }
            }
        }
        return parent;
    }

    public TrieNode getContinuation(char c) {
        return ((Optional<TrieNode>) getChild(c)).orElseGet(() -> {
            TrieNode godparent = godparent();
            if(null == godparent) return null;
            return godparent.getContinuation(c);
        });
    }
}
