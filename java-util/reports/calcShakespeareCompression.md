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
    {LZ8k_4_2_3_generateDictionary=com.simiacryptus.util.text.DictionaryMethodTest$2@39a054a5, LZ8k_4_2_3_generateMarkov=com.simiacryptus.util.text.DictionaryMethodTest$3@71bc1ae4, LZ8k_5_2_3_generateDictionary=com.simiacryptus.util.text.DictionaryMethodTest$2@6ed3ef1, LZ8k_5_2_3_generateMarkov=com.simiacryptus.util.text.DictionaryMethodTest$3@2437c6dc, LZ8k_6_2_3_generateDictionary=com.simiacryptus.util.text.DictionaryMethodTest$2@1f89ab83, LZ8k_6_2_3_generateMarkov=com.simiacryptus.util.text.DictionaryMethodTest$3@e73f9ac, LZ8k_commonWords=com.simiacryptus.util.text.DictionaryMethodTest$1@61064425, BZ0=com.simiacryptus.util.text.Compressor$1@7b1d7fff, LZ0=com.simiacryptus.util.text.Compressor$2@299a06ac}
```
Logging: 
```
    LZ8k_4_2_3_generateDictionary:        13
      Or else of the stars in thee to their a hourse untain convert:
        That thou art but though the stars in the world a...
    LZ8k_4_2_3_generateMarkov: nted by even I more aboan; 
     b utpe read summer some worth
      Has them fair who love other lace,
      If it way
      My love your seali...
    LZ8k_5_2_3_generateDictionary:          13
      O than thou art more blessed that beauty's day
      And that thou the stars in their age to thee to come
      To the wor...
    LZ8k_5_2_3_generateMarkov: s ten time debarred beauty's rage of times shade,
      Tene most not with let barren rhyme....
    LZ8k_6_2_3_generateDictionary:          13
       BY ANY
    SHAKESPEARE ****  So should bear your self in eternal cold?
      
      And summer's day
      And by adding the objs...
    LZ8k_6_2_3_generateMarkov: epart broofd th,
    ime
      If itnidg eBeu eoduth  unfnboeat the conceit of the FORo sisgrowh eaapyp of from that were neo nontrenigh...
    Common Words Dictionary: the of in to and And I my thou thy with that your you thee a not this self all is by from for it love But do his as be or me but...
    
```
field | sum | avg | stddev | nulls
----- | --- | --- | ------ | -----
LZ8k_4_2_3_generateDictionary.uncompressed | 46396.0000 | 463.9600 | 747.9211 | 0
LZ8k_4_2_3_generateDictionary.compressed   | 23959.0000 | 239.5900 | 345.4236 | 0
LZ8k_4_2_3_generateDictionary.compressMs   | 32.3620 | 0.3236 | 0.4487 | 0
LZ8k_4_2_3_generateDictionary.uncompressMs | 15.7207 | 0.1572 | 0.4105 | 0
LZ8k_4_2_3_generateMarkov.uncompressed     | 46396.0000 | 463.9600 | 747.9211 | 0
LZ8k_4_2_3_generateMarkov.compressed       | 26372.0000 | 263.7200 | 363.2705 | 0
LZ8k_4_2_3_generateMarkov.compressMs       | 9.4096 | 0.0941 | 0.0720 | 0
LZ8k_4_2_3_generateMarkov.uncompressMs     | 6.4108 | 0.0641 | 0.2042 | 0
LZ8k_5_2_3_generateDictionary.uncompressed | 46396.0000 | 463.9600 | 747.9211 | 0
LZ8k_5_2_3_generateDictionary.compressed   | 25584.0000 | 255.8400 | 358.0165 | 0
LZ8k_5_2_3_generateDictionary.compressMs   | 19.4485 | 0.1945 | 0.0818 | 0
LZ8k_5_2_3_generateDictionary.uncompressMs | 8.5018 | 0.0850 | 0.0366 | 0
LZ8k_5_2_3_generateMarkov.uncompressed     | 46396.0000 | 463.9600 | 747.9211 | 0
LZ8k_5_2_3_generateMarkov.compressed       | 26625.0000 | 266.2500 | 365.2477 | 0
LZ8k_5_2_3_generateMarkov.compressMs       | 13.8121 | 0.1381 | 0.5255 | 0
LZ8k_5_2_3_generateMarkov.uncompressMs     | 5.0967 | 0.0510 | 0.1224 | 0
LZ8k_6_2_3_generateDictionary.uncompressed | 46396.0000 | 463.9600 | 747.9211 | 0
LZ8k_6_2_3_generateDictionary.compressed   | 25300.0000 | 253.0000 | 358.7086 | 0
LZ8k_6_2_3_generateDictionary.compressMs   | 20.3710 | 0.2037 | 0.0823 | 0
LZ8k_6_2_3_generateDictionary.uncompressMs | 10.0867 | 0.1009 | 0.1341 | 0
LZ8k_6_2_3_generateMarkov.uncompressed     | 46396.0000 | 463.9600 | 747.9211 | 0
LZ8k_6_2_3_generateMarkov.compressed       | 26494.0000 | 264.9400 | 364.2587 | 0
LZ8k_6_2_3_generateMarkov.compressMs       | 9.4727 | 0.0947 | 0.0640 | 0
LZ8k_6_2_3_generateMarkov.uncompressMs     | 5.0145 | 0.0501 | 0.0867 | 0
LZ8k_commonWords.uncompressed              | 46396.0000 | 463.9600 | 747.9211 | 0
LZ8k_commonWords.compressed                | 23909.0000 | 239.0900 | 346.7361 | 0
LZ8k_commonWords.compressMs                | 22.9397 | 0.2294 | 0.2123 | 0
LZ8k_commonWords.uncompressMs              | 12.2525 | 0.1225 | 0.3150 | 0
BZ0.uncompressed                           | 46396.0000 | 463.9600 | 747.9211 | 0
BZ0.compressed                             | 29344.0000 | 293.4400 | 351.1990 | 0
BZ0.compressMs                             | 500.4042 | 5.0040 | 2.7470 | 0
BZ0.uncompressMs                           | 104.6467 | 1.0465 | 1.6285 | 0
LZ0.uncompressed                           | 46396.0000 | 463.9600 | 747.9211 | 0
LZ0.compressed                             | 26431.0000 | 264.3100 | 366.5197 | 0
LZ0.compressMs                             | 17.0813 | 0.1708 | 0.2981 | 0
LZ0.uncompressMs                           | 3.8500 | 0.0385 | 0.0295 | 0

