Code: 
```java
      CharTree baseTree = new CharTree();
      source.get().limit(modelCount).forEach(txt -> baseTree.addDocument(txt.text));
      Map<String, Compressor> map = new LinkedHashMap<>();
      addCompressors(map, baseTree, 4, 2, 3);
      addCompressors(map, baseTree, 5, 2, 3);
      addCompressors(map, baseTree, 6, 2, 3);
      Stream<TestDocument> limit = source.get().limit(modelCount).map(x->x);
      addWordCountCompressor(map, limit.collect(Collectors.toList()));
      Compressor.addGenericCompressors(map);
      return map;
```
Returns: 
```
    {LZ8k_4_2_3_generateDictionary=com.simiacryptus.util.text.DictionaryMethodTest$2@6ed3ef1, LZ8k_4_2_3_generateMarkov=com.simiacryptus.util.text.DictionaryMethodTest$3@2437c6dc, LZ8k_5_2_3_generateDictionary=com.simiacryptus.util.text.DictionaryMethodTest$2@1f89ab83, LZ8k_5_2_3_generateMarkov=com.simiacryptus.util.text.DictionaryMethodTest$3@e73f9ac, LZ8k_6_2_3_generateDictionary=com.simiacryptus.util.text.DictionaryMethodTest$2@61064425, LZ8k_6_2_3_generateMarkov=com.simiacryptus.util.text.DictionaryMethodTest$3@7b1d7fff, LZ8k_commonWords=com.simiacryptus.util.text.DictionaryMethodTest$1@299a06ac, BZ0=com.simiacryptus.util.text.Compressor$1@383534aa, LZ0=com.simiacryptus.util.text.Compressor$2@6bc168e5}
```
Logging: 
```
    LZ8k_4_2_3_generateDictionary:  that I want to be able to go to the same is a good morning to be a lot of the best of my favoriter the more the more the been a...
    LZ8k_4_2_3_generateMarkov: ™â€ ...think aslee  . figured/1Laokrispilton late...
    LZ8k_5_2_3_generateDictionary:  that I want to be able to go to the days of school tomorrow.  &lt;3ingng ing to be a lot of early to see the morning to take th...
    LZ8k_5_2_3_generateMarkov: mish. NO SELF  Just Goin to number safe&qquourt again....
    LZ8k_6_2_3_generateDictionary:  that I want to go to the doctors with the books &amp; the Lakers...ing to be a lot of the same time to get a new one will be a ...
    LZ8k_6_2_3_generateMarkov: re FOOL LHuI prtaiendt...
    Common Words Dictionary: to the I a my i is and in of for you me on it so have that this be with not at just Im all now amp was up get are out like day b...
    
```
field | sum | avg | stddev | nulls
----- | --- | --- | ------ | -----
LZ8k_4_2_3_generateDictionary.uncompressed | 7705.0000 | 77.0500 | 40.1538 | 0
LZ8k_4_2_3_generateDictionary.compressed   | 5555.0000 | 55.5500 | 22.8067 | 0
LZ8k_4_2_3_generateDictionary.compressMs   | 27.2947 | 0.2729 | 0.8453 | 0
LZ8k_4_2_3_generateDictionary.uncompressMs | 7.6672 | 0.0767 | 0.0342 | 0
LZ8k_4_2_3_generateMarkov.uncompressed     | 7705.0000 | 77.0500 | 40.1538 | 0
LZ8k_4_2_3_generateMarkov.compressed       | 7522.0000 | 75.2200 | 29.4709 | 0
LZ8k_4_2_3_generateMarkov.compressMs       | 6.7897 | 0.0679 | 0.0391 | 0
LZ8k_4_2_3_generateMarkov.uncompressMs     | 3.4770 | 0.0348 | 0.0661 | 0
LZ8k_5_2_3_generateDictionary.uncompressed | 7705.0000 | 77.0500 | 40.1538 | 0
LZ8k_5_2_3_generateDictionary.compressed   | 5552.0000 | 55.5200 | 22.5922 | 0
LZ8k_5_2_3_generateDictionary.compressMs   | 15.9275 | 0.1593 | 0.0407 | 0
LZ8k_5_2_3_generateDictionary.uncompressMs | 7.9547 | 0.0795 | 0.2577 | 0
LZ8k_5_2_3_generateMarkov.uncompressed     | 7705.0000 | 77.0500 | 40.1538 | 0
LZ8k_5_2_3_generateMarkov.compressed       | 7413.0000 | 74.1300 | 29.7881 | 0
LZ8k_5_2_3_generateMarkov.compressMs       | 8.6577 | 0.0866 | 0.2566 | 0
LZ8k_5_2_3_generateMarkov.uncompressMs     | 2.5266 | 0.0253 | 0.0091 | 0
LZ8k_6_2_3_generateDictionary.uncompressed | 7705.0000 | 77.0500 | 40.1538 | 0
LZ8k_6_2_3_generateDictionary.compressed   | 5605.0000 | 56.0500 | 23.2338 | 0
LZ8k_6_2_3_generateDictionary.compressMs   | 15.8390 | 0.1584 | 0.0352 | 0
LZ8k_6_2_3_generateDictionary.uncompressMs | 5.2991 | 0.0530 | 0.0090 | 0
LZ8k_6_2_3_generateMarkov.uncompressed     | 7705.0000 | 77.0500 | 40.1538 | 0
LZ8k_6_2_3_generateMarkov.compressed       | 7582.0000 | 75.8200 | 29.5440 | 0
LZ8k_6_2_3_generateMarkov.compressMs       | 6.3746 | 0.0637 | 0.0183 | 0
LZ8k_6_2_3_generateMarkov.uncompressMs     | 5.3401 | 0.0534 | 0.2688 | 0
LZ8k_commonWords.uncompressed              | 7705.0000 | 77.0500 | 40.1538 | 0
LZ8k_commonWords.compressed                | 6073.0000 | 60.7300 | 24.6032 | 0
LZ8k_commonWords.compressMs                | 16.4208 | 0.1642 | 0.0494 | 0
LZ8k_commonWords.uncompressMs              | 7.9562 | 0.0796 | 0.2564 | 0
BZ0.uncompressed                           | 7705.0000 | 77.0500 | 40.1538 | 0
BZ0.compressed                             | 10158.0000 | 101.5800 | 27.8188 | 0
BZ0.compressMs                             | 194.9914 | 1.9499 | 1.5632 | 0
BZ0.uncompressMs                           | 43.2598 | 0.4326 | 0.5148 | 0
LZ0.uncompressed                           | 7705.0000 | 77.0500 | 40.1538 | 0
LZ0.compressed                             | 7239.0000 | 72.3900 | 29.4886 | 0
LZ0.compressMs                             | 13.9588 | 0.1396 | 0.4827 | 0
LZ0.uncompressMs                           | 2.2347 | 0.0223 | 0.0142 | 0

