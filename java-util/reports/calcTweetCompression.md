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
    {LZ8k_4_2_3_generateDictionary=com.simiacryptus.util.text.DictionaryMethodTest$2@2437c6dc, LZ8k_4_2_3_generateMarkov=com.simiacryptus.util.text.DictionaryMethodTest$3@1f89ab83, LZ8k_5_2_3_generateDictionary=com.simiacryptus.util.text.DictionaryMethodTest$2@e73f9ac, LZ8k_5_2_3_generateMarkov=com.simiacryptus.util.text.DictionaryMethodTest$3@61064425, LZ8k_6_2_3_generateDictionary=com.simiacryptus.util.text.DictionaryMethodTest$2@7b1d7fff, LZ8k_6_2_3_generateMarkov=com.simiacryptus.util.text.DictionaryMethodTest$3@299a06ac, LZ8k_commonWords=com.simiacryptus.util.text.DictionaryMethodTest$1@383534aa, BZ0=com.simiacryptus.util.text.Compressor$1@6bc168e5, LZ0=com.simiacryptus.util.text.Compressor$2@7b3300e5}
```
Logging: 
```
    LZ8k_4_2_3_generateDictionary:  that I want to be able to go to the same is a good morning to be a lot of the best of my favoriter the more the more the been a...
    LZ8k_4_2_3_generateMarkov: ul...
    LZ8k_5_2_3_generateDictionary:  that I want to be able to go to the days of school tomorrow.  &lt;3ingng ing to be a lot of early to see the morning to take th...
    LZ8k_5_2_3_generateMarkov: ooh...
    LZ8k_6_2_3_generateDictionary:  that I want to go to the doctors with the books &amp; the Lakers...ing to be a lot of the same time to get a new one will be a ...
    LZ8k_6_2_3_generateMarkov: made me feel already last time everyone hates Berki...
    Common Words Dictionary: to the I a my i is and in of for you me on it so have that this be with not at just Im all now amp was up get are out like day b...
    
```
field | sum | avg | stddev | nulls
----- | --- | --- | ------ | -----
LZ8k_4_2_3_generateDictionary.uncompressed | 7705.0000 | 77.0500 | 40.1538 | 0
LZ8k_4_2_3_generateDictionary.compressed   | 5555.0000 | 55.5500 | 22.8067 | 0
LZ8k_4_2_3_generateDictionary.compressMs   | 20.6350 | 0.2064 | 0.1637 | 0
LZ8k_4_2_3_generateDictionary.uncompressMs | 7.7782 | 0.0778 | 0.0260 | 0
LZ8k_4_2_3_generateMarkov.uncompressed     | 7705.0000 | 77.0500 | 40.1538 | 0
LZ8k_4_2_3_generateMarkov.compressed       | 7588.0000 | 75.8800 | 29.5050 | 0
LZ8k_4_2_3_generateMarkov.compressMs       | 8.1649 | 0.0816 | 0.1388 | 0
LZ8k_4_2_3_generateMarkov.uncompressMs     | 3.0737 | 0.0307 | 0.0202 | 0
LZ8k_5_2_3_generateDictionary.uncompressed | 7705.0000 | 77.0500 | 40.1538 | 0
LZ8k_5_2_3_generateDictionary.compressed   | 5552.0000 | 55.5200 | 22.5922 | 0
LZ8k_5_2_3_generateDictionary.compressMs   | 22.2293 | 0.2223 | 0.5520 | 0
LZ8k_5_2_3_generateDictionary.uncompressMs | 10.7238 | 0.1072 | 0.3653 | 0
LZ8k_5_2_3_generateMarkov.uncompressed     | 7705.0000 | 77.0500 | 40.1538 | 0
LZ8k_5_2_3_generateMarkov.compressed       | 7588.0000 | 75.8800 | 29.5087 | 0
LZ8k_5_2_3_generateMarkov.compressMs       | 9.1887 | 0.0919 | 0.2506 | 0
LZ8k_5_2_3_generateMarkov.uncompressMs     | 2.8522 | 0.0285 | 0.0114 | 0
LZ8k_6_2_3_generateDictionary.uncompressed | 7705.0000 | 77.0500 | 40.1538 | 0
LZ8k_6_2_3_generateDictionary.compressed   | 5605.0000 | 56.0500 | 23.2338 | 0
LZ8k_6_2_3_generateDictionary.compressMs   | 16.9830 | 0.1698 | 0.1012 | 0
LZ8k_6_2_3_generateDictionary.uncompressMs | 6.6064 | 0.0661 | 0.0756 | 0
LZ8k_6_2_3_generateMarkov.uncompressed     | 7705.0000 | 77.0500 | 40.1538 | 0
LZ8k_6_2_3_generateMarkov.compressed       | 7498.0000 | 74.9800 | 29.8262 | 0
LZ8k_6_2_3_generateMarkov.compressMs       | 7.0728 | 0.0707 | 0.0333 | 0
LZ8k_6_2_3_generateMarkov.uncompressMs     | 5.0659 | 0.0507 | 0.2091 | 0
LZ8k_commonWords.uncompressed              | 7705.0000 | 77.0500 | 40.1538 | 0
LZ8k_commonWords.compressed                | 6073.0000 | 60.7300 | 24.6032 | 0
LZ8k_commonWords.compressMs                | 17.6885 | 0.1769 | 0.1308 | 0
LZ8k_commonWords.uncompressMs              | 8.2608 | 0.0826 | 0.2542 | 0
BZ0.uncompressed                           | 7705.0000 | 77.0500 | 40.1538 | 0
BZ0.compressed                             | 10158.0000 | 101.5800 | 27.8188 | 0
BZ0.compressMs                             | 193.9818 | 1.9398 | 1.4187 | 0
BZ0.uncompressMs                           | 44.0518 | 0.4405 | 0.5578 | 0
LZ0.uncompressed                           | 7705.0000 | 77.0500 | 40.1538 | 0
LZ0.compressed                             | 7239.0000 | 72.3900 | 29.4886 | 0
LZ0.compressMs                             | 15.3340 | 0.1533 | 0.5860 | 0
LZ0.uncompressMs                           | 2.3628 | 0.0236 | 0.0123 | 0

