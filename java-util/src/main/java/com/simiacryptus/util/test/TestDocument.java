package com.simiacryptus.util.test;

import com.simiacryptus.util.text.CompressionUtil;

public class TestDocument {

    private final String title;
    private final byte[] text;

    public TestDocument(String title, String text) {
        this.title = title;
        this.text = CompressionUtil.encodeLZ(text);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer(getClass().getSimpleName() + "{");
        sb.append("title='").append(getTitle()).append('\'' );
        sb.append('}' );
        return sb.toString();
    }

    public String getText() {
        return CompressionUtil.decodeLZToString(text);
    }

    public String getTitle() {
        return title;
    }
}
