package com.simiacryptus.util.text;

public class Cursor {
  /**
   * 
   */
  private final CharTree charTree;
  final CursorData data;
  private final short depth;

  public Cursor(CharTree charTree, CursorData data, short depth) {
    this.charTree = charTree;
    this.data = data;
    this.depth = depth;
  }

  public String getDocument() {
    return this.charTree.documents.get(data.documentId);
  }

  public boolean hasNext() {
    return (depth + data.position + 1) < getDocument().length();
  }

  public char getToken() {
    int index = depth + data.position;
    String document = getDocument();
    return index >= document.length() ? Character.MIN_VALUE : document.charAt(index);
  }

  public Cursor next() {
    return new Cursor(this.charTree, data, (short) (depth + 1));
  }

  public int getPosition() {
    return data.position + depth;
  }
}