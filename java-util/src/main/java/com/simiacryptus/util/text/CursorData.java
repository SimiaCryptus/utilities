package com.simiacryptus.util.text;

class CursorData {
  int documentId;
  int position;

  public CursorData(int documentId, int position) {
    this.documentId = documentId;
    this.position = position;
  }

  public CursorData setDocumentId(int documentId) {
    this.documentId = documentId;
    return this;
  }

  public CursorData setPosition(int position) {
    this.position = position;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    CursorData that = (CursorData) o;

    if (documentId != that.documentId)
      return false;
    return position == that.position;
  }

  @Override
  public int hashCode() {
    int result = documentId;
    result = 31 * result + position;
    return result;
  }

}