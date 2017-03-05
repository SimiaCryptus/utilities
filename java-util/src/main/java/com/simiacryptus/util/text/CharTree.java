package com.simiacryptus.util.text;

import com.simiacryptus.util.data.SerialArrayList;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CharTree {

  protected static final Random random = new Random();

  protected final SerialArrayList<NodeData> nodes;
  protected final SerialArrayList<CursorData> cursors;
  protected final ArrayList<String> documents;

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
  
  public int getMemorySize() {
    return cursors.getMemorySize() + nodes.getMemorySize();
  }
  
  public int getIndexedSize() {
    return documents.stream().mapToInt(doc -> doc.length()).sum();
  }
  
  public CharTree addEscapeNodes() {
    CharTree result = new CharTree();
    xferEscapeNodes(root(), result.root());
    return result.recomputeCursorDetails();
  }

  CharTree recomputeCursorDetails() {
    recomputeCursorTotals(root());
    recomputerCursorPositions(root(), 0);
    return this;
  }

  private NodeData recomputeCursorTotals(Node node) {
    List<NodeData> newChildren = node.getChildren().map(child->recomputeCursorTotals(child)).collect(Collectors.toList());
    if(newChildren.isEmpty()) return node.getData();
    int cursorCount = newChildren.stream().mapToInt(n->n.cursorCount).sum();
    assert(0 < cursorCount);
    return node.update(d -> d.setCursorCount(cursorCount));
  }

  private void recomputerCursorPositions(Node node, final int position) {
    node.update(n->n.setFirstCursorIndex(position));
    int childPosition = position;
    for(Node child : node.getChildren().collect(Collectors.toList())) {
      recomputerCursorPositions(child, childPosition);
      childPosition += child.getCursorCount();
    }
  }

  private void xferEscapeNodes(Node sourceNode, Node destNode) {
    CharTree result = destNode.getTree();
    Map<Character, Node> sourceChildren = sourceNode.getChildren().collect(Collectors.toMap(n->n.getChar(), n->n));
    TreeMap<Character, Integer> newCounts = new TreeMap<Character, Integer>();
    sourceChildren.forEach((key,value)->newCounts.put(key, value.getCursorCount()));
    newCounts.put(Character.MAX_VALUE, 1);
    destNode.update(n->n.setFirstChildIndex(result.nodes.length())
        .setNumberOfChildren((short) newCounts.size()));
    newCounts.forEach((k,v)->result.nodes.add(new NodeData(k, (short) -1, -1, v, -1)));
    Map<Character, Node> newChildren = destNode.getChildren().collect(Collectors.toMap(n->n.getChar(), n->n));
    newCounts.keySet().forEach(key->{
      if(sourceChildren.containsKey(key)) {
        xferEscapeNodes(sourceChildren.get(key), newChildren.get(key));
      }
    });
  }

  /**
   * Locate a node by finding the maximum prefix match with the given string
   * 
   * @param search
   * @return
   */
  public Node traverse(String search) {
    return root().traverse(search);
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
    String string = cursor.getString();
    if(string.isEmpty()) throw new RuntimeException("No predictor node availible for " + search);
    return matchPredictor(string.substring(1));
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
          && (node.getChar() != Character.MIN_VALUE || node.depth == 0))
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

  public CharTree addAlphabet(String document) {
    document.chars().mapToObj(i->new String(Character.toChars(i))).forEach(s->addDocument(s));
    return this;
  }
  
  public final CharTreeCodec codec = new CharTreeCodec(this);

}