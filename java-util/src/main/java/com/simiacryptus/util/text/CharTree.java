package com.simiacryptus.util.text;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.simiacryptus.util.data.SerialArrayList;

public class CharTree {

  protected final SerialArrayList<NodeData> nodes;

  public int getMemorySize() {
    return cursors.getMemorySize() + nodes.getMemorySize();
  }

  public int getIndexedSize() {
    return documents.stream().mapToInt(doc -> doc.length()).sum();
  }

  protected final SerialArrayList<CursorData> cursors;
  protected final ArrayList<String> documents;
  protected static final Random random = new Random();

  private CharTree(SerialArrayList<NodeData> nodes, SerialArrayList<CursorData> cursors,
      ArrayList<String> documents) {
    super();
    this.nodes = nodes;
    this.cursors = cursors;
    this.documents = documents;
  }

  public CharTree(CharTree copyFrom) {
    this(copyFrom.nodes.copy(), copyFrom.cursors.copy(), new ArrayList<>(copyFrom.documents));

  }

  public CharTree() {
    this(new SerialArrayList<>(NodeType.INSTANCE, new NodeData(Character.MIN_VALUE, (short)-1, -1, -1, 0)), new SerialArrayList<>(CursorType.INSTANCE), new ArrayList<>());
  }

  public Node root() {
    return new Node(this, (short) 0, 0, null);
  }

  /**
   * Locate a node by finding the maximum prefix match with the given string
   * 
   * @param search
   * @return
   */
  public Node traverse(String search) {
    Node cursor = root();
    for (char token : search.toCharArray()) {
      Optional<Node> child = cursor.getChild(token);
      if (child.isPresent()) {
        cursor = child.get();
      } else {
        break;
      }
    }
    return cursor;
  }

  public Node matchEnd2(String search) {
    Node cursor = traverse(search);
    if (search.endsWith(cursor.getString()))
      return cursor;
    return matchEnd2(search.substring(1));
  }

  public Node matchEnd(String search) {
    int min = 0;
    int max = search.length();
    int winner = -1;
    int i = Math.min(max, 12);
    while (max > min) {
      String attempt = search.substring(search.length() - i);
      Node cursor = traverse(attempt);
      if (cursor.getString().equals(attempt)) {
        min = Math.max(min, i + 1);
        winner = Math.max(winner, i);
        i = (max + min) / 2;
      } else {
        max = Math.min(max, i - 1);
        i = (max + min) / 2;
      }
    }
    return traverse(search.substring(search.length() - i));
  }

  public Node matchPredictor(String search) {
    Node cursor = matchEnd(search);
    if (cursor.getNumberOfChildren() > 0)
      return cursor;
    return matchPredictor(cursor.getString().substring(1));
  }

  /**
   * Removes cursor data, retaining only the tree of tokens and counts.
   * Subsequent calls to methods dealing with cursors will fail.
   * 
   * @return this
   */
  public CharTree truncate() {
    cursors.clear();
    return this;
  }

  public CharTree copy() {
    return new CharTree(this);
  }

  /**
   * Creates the index tree using the accumulated documents
   * 
   * @return this
   */
  public CharTree index() {
    return index(Integer.MAX_VALUE);
  }

  /**
   * Creates the index tree using the accumulated documents
   * 
   * @param maxLevels
   *          - Maximum depth of the tree to build
   * @return this
   */
  public CharTree index(int maxLevels) {
    return index(maxLevels, 1);
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
  public CharTree index(int maxLevels, int minWeight) {
    root().visitFirst(node -> {
      if (node.depth < maxLevels && node.getCursorCount() > minWeight
          && (node.getToken() != Character.MIN_VALUE || node.depth == 0))
        node.split();
    });
    return this;
  }

  /**
   * Adds a document to be indexed. This can only be performed before splitting.
   * 
   * @param document
   * @return this
   */
  public CharTree addDocument(String document) {
    if (root().getNumberOfChildren() >= 0)
      throw new IllegalStateException("Tree sorting has begun");
    final int index;
    synchronized (this) {
      index = documents.size();
      documents.add(document);
    }
    cursors.addAll(
        IntStream.range(0, document.length()).mapToObj(i -> new CursorData(index, i)).collect(Collectors.toList()));
    nodes.update(0, node -> node.setCursorCount(cursors.length()));
    return this;
  }
  
  public final CharTreeCodec codec = new CharTreeCodec(this);

}