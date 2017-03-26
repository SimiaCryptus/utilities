package com.simiacryptus.util.text;

import org.apache.commons.compress.utils.IOUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public enum LanguageModel {
    English("English.trie"),
    French("French.trie"),
    German("German.trie");

    public static LanguageModel match(String text) {
        return Arrays.stream(LanguageModel.values()).min(Comparator.comparing(
                model->model.getTrie().getCodec().encodePPM(text, 2).bitLength
        )).get();
    }

    private final String resource;
    private volatile CharTrie trie;

    LanguageModel(String resource) {
        this.resource = resource;
    }

    public CharTrie getTrie() {
        if(null == trie) {
            synchronized (this) {
                if(null == trie) {
                    final byte[] bytes;
                    try {
                        bytes = IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream(resource));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    trie = new ConvolutionalTrieSerializer().deserialize(CompressionUtil.decodeLZ(bytes));
                }
            }
        }
        return trie;
    }

}
