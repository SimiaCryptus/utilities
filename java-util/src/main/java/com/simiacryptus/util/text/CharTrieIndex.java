package com.simiacryptus.util.text;

import com.simiacryptus.util.data.SerialArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CharTrieIndex extends CharTrie {

  protected final SerialArrayList<CursorData> cursors;
  protected final ArrayList<String> documents;

  private CharTrieIndex(SerialArrayList<NodeData> nodes, SerialArrayList<CursorData> cursors,
                        ArrayList<String> documents) {
    super(nodes);
    this.cursors = cursors;
    this.documents = documents;
  }

  public CharTrieIndex(CharTrieIndex copyFrom) {
    this(copyFrom.nodes.copy(), copyFrom.cursors.copy(), new ArrayList<>(copyFrom.documents));

  }

  public CharTrieIndex() {
    this(new SerialArrayList<>(NodeType.INSTANCE, new NodeData(NodewalkerCodec.END_OF_STRING, (short)-1, -1, -1, 0)), new SerialArrayList<>(CursorType.INSTANCE), new ArrayList<>());
  }

  @Override
  public int getMemorySize() {
    return cursors.getMemorySize() + nodes.getMemorySize();
  }

  @Override
  public long getIndexedSize() {
    return documents.isEmpty()?super.getIndexedSize():documents.stream().mapToInt(doc -> doc.length()).sum();
  }

  /**
   * Removes cursor data, retaining only the tree of tokens and counts.
   * Subsequent calls to methods dealing with cursors will fail.
   * 
   * @return this
   */
  public CharTrie truncate() {
    return new CharTrie(nodes);
  }

  /**
   * Creates the index tree using the accumulated documents
   * 
   * @return this
   */
  public CharTrieIndex index() {
    return index(Integer.MAX_VALUE);
  }

  /**
   * Creates the index tree using the accumulated documents
   * 
   * @param maxLevels
   *          - Maximum depth of the tree to build
   * @return this
   */
  public CharTrieIndex index(int maxLevels) {
    return index(maxLevels, 0);
  }

  /**
   * Creates the index tree using the accumulated documents
   * 
   * @param maxLevels
   *          - Maximum depth of the tree to build
   * @param minWeight
   *          - Minimum number of cursors for a node to be index using,
   *          exclusive bound
   * @return this
   */
  public CharTrieIndex index(int maxLevels, int minWeight) {
    
    AtomicInteger numberSplit = new AtomicInteger(0);
    int depth = -1;
    do{
      numberSplit.set(0);
      if(0 == ++depth) {
        numberSplit.incrementAndGet();
        root().split();
      } else {
        root().streamDecendents(depth).forEach(node->{
          TrieNode godparent = node.godparent();
          if (node.getDepth() < maxLevels)
          {
            if (null==godparent || godparent.getCursorCount() > minWeight)
            {
              if (node.getChar() != NodewalkerCodec.END_OF_STRING || node.getDepth() == 0)
              {
                ((IndexNode)node).split();
                numberSplit.incrementAndGet();
              }
            }
          }
        });
      }
    } while(numberSplit.get() > 0);
    return this;
  }

  /**
   * Adds a document to be indexed. This can only be performed before splitting.
   *
   * @param document
   * @return this
   */
  public int addDictionary(String document) {
    if (root().getNumberOfChildren() >= 0)
      throw new IllegalStateException("Tree sorting has begun");
    final int index;
    synchronized (this) {
      index = documents.size();
      documents.add(document);
    }
    cursors.addAll(
            IntStream.range(0, 1).mapToObj(i -> new CursorData(index, i)).collect(Collectors.toList()));
    nodes.update(0, node -> node.setCursorCount(cursors.length()));
    return index;
  }

  /**
   * Adds a document to be indexed. This can only be performed before splitting.
   *
   * @param document
   * @return this
   */
  public int addDocument(String document) {
    if (root().getNumberOfChildren() >= 0)
      throw new IllegalStateException("Tree sorting has begun");
    final int index;
    synchronized (this) {
      index = documents.size();
      documents.add(document);
    }
    cursors.addAll(
            IntStream.range(0, document.length()+1).mapToObj(i -> new CursorData(index, i)).collect(Collectors.toList()));
    nodes.update(0, node -> node.setCursorCount(cursors.length()));
    return index;
  }

  public CharTrie addAlphabet(String document) {
    document.chars().mapToObj(i->new String(Character.toChars(i))).forEach(s->addDocument(s));
    return this;
  }

  @Override
  CharTrieIndex recomputeCursorDetails() {
    return (CharTrieIndex) super.recomputeCursorDetails();
  }

  public CharTrieIndex copy() {
    return new CharTrieIndex(this);
  }

  @Override
  public IndexNode root() {
    return new IndexNode(this, (short) 0, 0, null);
  }

  @Override
  public IndexNode traverse(String search) {
    return root().traverse(search);
  }

  public static CharTrie indexWords(Collection<String> documents, int maxLevels, int minWeight) {
    return create(documents, maxLevels, minWeight, true);
  }

  public static CharTrie indexFulltext(Collection<String> documents, int maxLevels, int minWeight) {
    return create(documents, maxLevels, minWeight, false);
  }

  private static CharTrie create(Collection<String> documents, int maxLevels, int minWeight, boolean words) {
    List<List<String>> a = new ArrayList<>();
    List<String> b = new ArrayList<>();
    int blockSize = 1024 * 1024;
    for(String s : documents) {
      b.add(s);
      if(b.stream().mapToInt(x->x.length()).sum() > blockSize) {
        a.add(b);
        b = new ArrayList<>();
      }
    }
    a.add(b);
    return a.parallelStream().map(list->{
      CharTrieIndex trie = new CharTrieIndex();
      list.forEach(s->{
        if(words) {
          trie.addDictionary(s);
        } else {
          trie.addDocument(s);
        }
      });
      trie.index(maxLevels, minWeight);
      return (CharTrie) trie;
    }).reduce((l,r)->l.add(r)).get();
  }

}