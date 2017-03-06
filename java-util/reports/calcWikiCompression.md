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
    LZ8k_4_2_3_generateDictionary:  that the [[United States of the [[Academy Awards and the state = 2008 | pages = 10.1016/j.bbc.co.uk/sportant to the states, and...
    LZ8k_4_2_3_generateMarkov: Club anthropology]]). ASCII was a comment people and Film/2009080220, 2014|r=2}}
    |-
    | [[Reisel is Rev | url = http://archive-dat...
    LZ8k_5_2_3_generateDictionary:  that the [[United States of the [[Alabama |publisher=[[American American Party (United States, and the state of Alabamily in th...
    LZ8k_5_2_3_generateMarkov: dian.co.uk/1/100-691-006-05-226-15-6931/1.646, Lincolns-P0rgaard|authorlink=Steel, 1970s this family,
    ** In Decembers";  r=Henry...
    LZ8k_6_2_3_generateDictionary:  that the [[United States of Algerian Church]] in the [[Alabama |publisher=[[American American in Paris'' is considered an inter...
    LZ8k_6_2_3_generateMarkov: 3088307, /u>{g{ {c{iCmaoisfi,st 1 1|D1e .f rao thBoDoalst at [[Didey  J E |mr = 2007 |accessdate=15 January 10, 2010, p. 43419
    /...
    Common Words Dictionary: the of and in to a is The as for that was by with on from his at an or are which br be A he journal have it were not Lincoln In ...
    
```
field | sum | avg | stddev | nulls
----- | --- | --- | ------ | -----
LZ8k_4_2_3_generateDictionary.uncompressed | 3218757.0000 | 32187.5700 | 50604.0314 | 0
LZ8k_4_2_3_generateDictionary.compressed   | 1165899.0000 | 11658.9900 | 17954.2717 | 0
LZ8k_4_2_3_generateDictionary.compressMs   | 447.3055 | 4.4731 | 7.1807 | 0
LZ8k_4_2_3_generateDictionary.uncompressMs | 78.1817 | 0.7818 | 1.7070 | 0
LZ8k_4_2_3_generateMarkov.uncompressed     | 3218757.0000 | 32187.5700 | 50604.0314 | 0
LZ8k_4_2_3_generateMarkov.compressed       | 1170244.0000 | 11702.4400 | 17986.8116 | 0
LZ8k_4_2_3_generateMarkov.compressMs       | 427.0020 | 4.2700 | 6.6008 | 0
LZ8k_4_2_3_generateMarkov.uncompressMs     | 65.2329 | 0.6523 | 0.9431 | 0
LZ8k_5_2_3_generateDictionary.uncompressed | 3218757.0000 | 32187.5700 | 50604.0314 | 0
LZ8k_5_2_3_generateDictionary.compressed   | 1166946.0000 | 11669.4600 | 17960.9352 | 0
LZ8k_5_2_3_generateDictionary.compressMs   | 429.3926 | 4.2939 | 6.6822 | 0
LZ8k_5_2_3_generateDictionary.uncompressMs | 63.4676 | 0.6347 | 0.9341 | 0
LZ8k_5_2_3_generateMarkov.uncompressed     | 3218757.0000 | 32187.5700 | 50604.0314 | 0
LZ8k_5_2_3_generateMarkov.compressed       | 1170619.0000 | 11706.1900 | 17989.9323 | 0
LZ8k_5_2_3_generateMarkov.compressMs       | 430.7605 | 4.3076 | 6.6405 | 0
LZ8k_5_2_3_generateMarkov.uncompressMs     | 67.2506 | 0.6725 | 0.9508 | 0
LZ8k_6_2_3_generateDictionary.uncompressed | 3218757.0000 | 32187.5700 | 50604.0314 | 0
LZ8k_6_2_3_generateDictionary.compressed   | 1167546.0000 | 11675.4600 | 17967.7210 | 0
LZ8k_6_2_3_generateDictionary.compressMs   | 428.5068 | 4.2851 | 6.6636 | 0
LZ8k_6_2_3_generateDictionary.uncompressMs | 64.6913 | 0.6469 | 0.9291 | 0
LZ8k_6_2_3_generateMarkov.uncompressed     | 3218757.0000 | 32187.5700 | 50604.0314 | 0
LZ8k_6_2_3_generateMarkov.compressed       | 1188583.0000 | 11885.8300 | 18134.0513 | 0
LZ8k_6_2_3_generateMarkov.compressMs       | 394.7641 | 3.9476 | 6.5394 | 0
LZ8k_6_2_3_generateMarkov.uncompressMs     | 60.9718 | 0.6097 | 0.8895 | 0
LZ8k_commonWords.uncompressed              | 3218757.0000 | 32187.5700 | 50604.0314 | 0
LZ8k_commonWords.compressed                | 1167532.0000 | 11675.3200 | 17968.8035 | 0
LZ8k_commonWords.compressMs                | 425.1188 | 4.2512 | 6.7067 | 0
LZ8k_commonWords.uncompressMs              | 59.5838 | 0.5958 | 0.8526 | 0
BZ0.uncompressed                           | 3218757.0000 | 32187.5700 | 50604.0314 | 0
BZ0.compressed                             | 1046973.0000 | 10469.7300 | 15355.9676 | 0
BZ0.compressMs                             | 1282.1582 | 12.8216 | 21.5110 | 0
BZ0.uncompressMs                           | 356.9745 | 3.5697 | 5.0505 | 0
LZ0.uncompressed                           | 3218757.0000 | 32187.5700 | 50604.0314 | 0
LZ0.compressed                             | 1194685.0000 | 11946.8500 | 18177.3498 | 0
LZ0.compressMs                             | 404.4383 | 4.0444 | 6.4810 | 0
LZ0.uncompressMs                           | 59.6249 | 0.5962 | 0.8673 | 0

