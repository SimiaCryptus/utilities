package com.simiacryptus.util.text;

public class Cursor {
    /**
     *
     */
    private final CharTrieIndex charTrieIndex;
    final CursorData data;
    private final short depth;

    public Cursor(CharTrieIndex charTrieIndex, CursorData data, short depth) {
        this.charTrieIndex = charTrieIndex;
        this.data = data;
        this.depth = depth;
    }

    public String getDocument() {
        return this.charTrieIndex.documents.get(data.documentId);
    }

    public boolean hasNext() {
        return (depth + data.position + 1) < getDocument().length();
    }

    public char getToken() {
        int index = depth + data.position;
        String document = getDocument();
        return index >= document.length() ? PPMCodec.END_OF_STRING : document.charAt(index);
    }

    public Cursor next() {
        return new Cursor(this.charTrieIndex, data, (short) (depth + 1));
    }

    public int getPosition() {
        return data.position + depth;
    }

    public int getDocumentId() {
        return data.documentId;
    }
}