package com.simiacryptus.util.text;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.simiacryptus.util.binary.Bits;
import com.simiacryptus.util.binary.Interval;
import com.simiacryptus.util.data.SerialArrayList;

public class Node {
  private final CharTree charTree;
  private transient NodeData data;
  public final short depth;
  private final int index;
  public final Node parent;

  NodeData getData() {
    if (null == data) {
      synchronized (this) {
        if (null == data) {
          this.data = this.charTree.nodes.get(index);
        }
      }
    }
    return data;
  }

  public Node(CharTree charTree, short depth, int index, Node parent) {
    this.charTree = charTree;
    this.depth = depth;
    this.index = index;
    this.parent = parent;
  }

  public Node godparent() {
    return null == parent ? null : this.charTree.matchEnd(getString().substring(1));
  }

  public Node refresh() {
    this.data = null;
    return this;
  }

  public String getString() {
    return (null == parent ? "" : parent.getString()) + (0 == depth ? "" : getData().token);
  }

  public char getToken() {
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

  public Node visitFirst(Consumer<Node> visitor) {
    visitor.accept(this);
    Node refresh = refresh();
    refresh.getChildren().forEach(n -> n.visitFirst(visitor));
    return refresh;
  }

  public Node visitLast(Consumer<Node> visitor) {
    getChildren().forEach(n -> n.visitLast(visitor));
    visitor.accept(this);
    return refresh();
  }

  public Stream<Node> getChildren() {
    if (getData().firstChildIndex >= 0) {
      return IntStream.range(0, getData().numberOfChildren)
          .mapToObj(i -> new Node(this.charTree, (short) (depth + 1), getData().firstChildIndex + i, Node.this));
    } else {
      return Stream.empty();
    }
  }

  public Optional<Node> getChild(char token) {
    // Node[] nodes = getChildren().toArray(i -> new Node[i]);
    return getChildren().filter(x -> x.getToken() == token).findFirst();
  }

  public Map<String, List<Cursor>> getCursorsByDocument() {
    return this.getCursors().collect(Collectors.groupingBy((Cursor x) -> x.getDocument()));
  }

  public Stream<Cursor> getCursors() {
    return IntStream.range(0, getData().cursorCount).mapToObj(i -> {
      return new Cursor(this.charTree, this.charTree.cursors.get(i + getData().firstCursorIndex), depth);
    });
  }

  public Node split() {
    if (getData().firstChildIndex < 0) {
      Map<Character, SerialArrayList<CursorData>> sortedChildren = getCursors().parallel()
          .collect(Collectors.groupingBy(y -> y.next().getToken(),
              Collectors.reducing(new SerialArrayList<>(CursorType.INSTANCE, 0),
                  cursor -> new SerialArrayList<>(CursorType.INSTANCE, cursor.data),
                  (left, right) -> left.add(right))));
      int cursorWriteIndex = getData().firstCursorIndex;
      ArrayList<NodeData> childNodes = new ArrayList<>(sortedChildren.size());
      List<Map.Entry<Character, SerialArrayList<CursorData>>> collect = sortedChildren.entrySet().stream()
          .sorted(Comparator.comparing(e -> e.getKey())).collect(Collectors.toList());
      for (Map.Entry<Character, SerialArrayList<CursorData>> e : collect) {
        int length = e.getValue().length();
        this.charTree.cursors.putAll(e.getValue(), cursorWriteIndex);
        childNodes.add(new NodeData(e.getKey(), (short) -1, -1, length, cursorWriteIndex));
        cursorWriteIndex += length;
      }
      this.charTree.nodes.update(index, data -> data
          .setFirstChildIndex(this.charTree.nodes.addAll(childNodes))
          .setNumberOfChildren((short) childNodes.size())
          );
      return new Node(this.charTree, depth, index, parent);
    } else {
      return this;
    }
  }

  private void decrementCursorCount(int count) {
    this.charTree.nodes.update(index, data -> data.setCursorCount(Math.max(data.cursorCount - count, 0)));
    if (null != parent)
      parent.decrementCursorCount(count);
  }

  public void shadowCursors() {
    decrementCursorCount(getCursorCount());
  }

  public Node traverse(String str) {
    if (str.isEmpty())
      return this;
    return getChild(str.charAt(0)).map(n -> n.traverse(str.substring(1))).orElse(this);
  }

  public boolean containsCursor(long cursorId) {
    if (cursorId < data.firstCursorIndex)
      return false;
    if (cursorId >= (data.firstCursorIndex + data.cursorCount))
      return false;
    return true;
  }

  public Node traverse(long cursorId) {
    if (!containsCursor(cursorId))
      throw new IllegalArgumentException();
    return getChildren().filter(n -> n.containsCursor(cursorId)).findFirst().map(n -> n.traverse(cursorId))
        .orElse(this);
  }

  public Bits intervalTo(Node toNode) {
    return new Interval(toNode.getCursorIndex() - this.getCursorIndex(), toNode.getCursorCount(), this.getCursorCount())
        .toBits();
  }

  public boolean hasChildren() {
    return 0 < data.numberOfChildren;
  }

  NodeData update(Function<NodeData, NodeData> update) {
    data = charTree.nodes.update(index, update);
    return data;
  }

  public CharTree getTree() {
    return charTree;
  }

}