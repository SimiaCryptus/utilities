package com.simiacryptus.util.test;

public class TestDocument {

    public final String title;
    public final String text;

    public TestDocument(String title, String text) {
        this.title = title;
        this.text = text;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer(getClass().getSimpleName() + "{");
        sb.append("title='").append(title).append('\'' );
        sb.append('}' );
        return sb.toString();
    }
}
