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
    {LZ8k_4_2_3_generateDictionary=com.simiacryptus.util.text.DictionaryMethodTest$2@71bc1ae4, LZ8k_4_2_3_generateMarkov=com.simiacryptus.util.text.DictionaryMethodTest$3@6ed3ef1, LZ8k_5_2_3_generateDictionary=com.simiacryptus.util.text.DictionaryMethodTest$2@2437c6dc, LZ8k_5_2_3_generateMarkov=com.simiacryptus.util.text.DictionaryMethodTest$3@1f89ab83, LZ8k_6_2_3_generateDictionary=com.simiacryptus.util.text.DictionaryMethodTest$2@e73f9ac, LZ8k_6_2_3_generateMarkov=com.simiacryptus.util.text.DictionaryMethodTest$3@61064425, LZ8k_commonWords=com.simiacryptus.util.text.DictionaryMethodTest$1@7b1d7fff, BZ0=com.simiacryptus.util.text.Compressor$1@299a06ac, LZ0=com.simiacryptus.util.text.Compressor$2@383534aa}
```
Logging: 
```
    LZ8k_4_2_3_generateDictionary:        13
      Or else of the stars in thee to their a hourse untain convert:
        That thou art but though the stars in the world a...
    LZ8k_4_2_3_generateMarkov: JECT GUTENBERG ETEXT If you lived phold cease,
      The Works of MADE ****  Or else in coo hourse excloan, no quide laws,
      This go...
    LZ8k_5_2_3_generateDictionary:          13
      O than thou art more blessed that beauty's day
      And that thou the stars in their age to thee to come
      To the wor...
    LZ8k_5_2_3_generateMarkov: eque song as men make you living forbid friends but heaven's eye,
      Which I beauteous lovided by the Project Gutenberg eBy REmal...
    LZ8k_6_2_3_generateDictionary:          13
       BY ANY
    SHAKESPEARE ****  So should bear your self in eternal cold?
      
      And summer's day
      And by adding the objs...
    LZ8k_6_2_3_generateMarkov: eaves ne'eer s ofher, raasingo thnood,nd e thy worsaanraged  d ane o lefty  d o'instn  m evirtnsgees rquess, 
    iwhn  c hate with ...
    Common Words Dictionary: the of in to and And I my thou thy with that your you thee a not this self all is by from for it love But do his as be or me but...
    
```
field | sum | avg | stddev | nulls
----- | --- | --- | ------ | -----
LZ8k_4_2_3_generateDictionary.uncompressed | 57740.0000 | 577.4000 | 999.3024 | 0
LZ8k_4_2_3_generateDictionary.compressed   | 28909.0000 | 289.0900 | 447.6089 | 0
LZ8k_4_2_3_generateDictionary.compressMs   | 29.2698 | 0.2927 | 0.2623 | 0
LZ8k_4_2_3_generateDictionary.uncompressMs | 10.7272 | 0.1073 | 0.1041 | 0
LZ8k_4_2_3_generateMarkov.uncompressed     | 57740.0000 | 577.4000 | 999.3024 | 0
LZ8k_4_2_3_generateMarkov.compressed       | 30224.0000 | 302.2400 | 460.3511 | 0
LZ8k_4_2_3_generateMarkov.compressMs       | 12.6617 | 0.1266 | 0.1119 | 0
LZ8k_4_2_3_generateMarkov.uncompressMs     | 5.6423 | 0.0564 | 0.0793 | 0
LZ8k_5_2_3_generateDictionary.uncompressed | 57740.0000 | 577.4000 | 999.3024 | 0
LZ8k_5_2_3_generateDictionary.compressed   | 30478.0000 | 304.7800 | 465.1042 | 0
LZ8k_5_2_3_generateDictionary.compressMs   | 20.7455 | 0.2075 | 0.1044 | 0
LZ8k_5_2_3_generateDictionary.uncompressMs | 8.5976 | 0.0860 | 0.0364 | 0
LZ8k_5_2_3_generateMarkov.uncompressed     | 57740.0000 | 577.4000 | 999.3024 | 0
LZ8k_5_2_3_generateMarkov.compressed       | 30791.0000 | 307.9100 | 466.8800 | 0
LZ8k_5_2_3_generateMarkov.compressMs       | 13.4723 | 0.1347 | 0.2909 | 0
LZ8k_5_2_3_generateMarkov.uncompressMs     | 7.5230 | 0.0752 | 0.2767 | 0
LZ8k_6_2_3_generateDictionary.uncompressed | 57740.0000 | 577.4000 | 999.3024 | 0
LZ8k_6_2_3_generateDictionary.compressed   | 30305.0000 | 303.0500 | 463.9884 | 0
LZ8k_6_2_3_generateDictionary.compressMs   | 21.2173 | 0.2122 | 0.1199 | 0
LZ8k_6_2_3_generateDictionary.uncompressMs | 8.7267 | 0.0873 | 0.0435 | 0
LZ8k_6_2_3_generateMarkov.uncompressed     | 57740.0000 | 577.4000 | 999.3024 | 0
LZ8k_6_2_3_generateMarkov.compressed       | 31368.0000 | 313.6800 | 470.7271 | 0
LZ8k_6_2_3_generateMarkov.compressMs       | 12.2555 | 0.1226 | 0.1153 | 0
LZ8k_6_2_3_generateMarkov.uncompressMs     | 8.9711 | 0.0897 | 0.2967 | 0
LZ8k_commonWords.uncompressed              | 57740.0000 | 577.4000 | 999.3024 | 0
LZ8k_commonWords.compressed                | 29191.0000 | 291.9100 | 448.3195 | 0
LZ8k_commonWords.compressMs                | 22.3637 | 0.2236 | 0.1176 | 0
LZ8k_commonWords.uncompressMs              | 8.9129 | 0.0891 | 0.0615 | 0
BZ0.uncompressed                           | 57740.0000 | 577.4000 | 999.3024 | 0
BZ0.compressed                             | 33891.0000 | 338.9100 | 447.0806 | 0
BZ0.compressMs                             | 581.0333 | 5.8103 | 7.1051 | 0
BZ0.uncompressMs                           | 89.7683 | 0.8977 | 0.7487 | 0
LZ0.uncompressed                           | 57740.0000 | 577.4000 | 999.3024 | 0
LZ0.compressed                             | 31384.0000 | 313.8400 | 474.5454 | 0
LZ0.compressMs                             | 19.2720 | 0.1927 | 0.5379 | 0
LZ0.uncompressMs                           | 7.8970 | 0.0790 | 0.3398 | 0

