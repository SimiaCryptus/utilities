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
    LZ8k_4_2_3_generateMarkov: an president the time to sea meteorology Presider: Accordinary example of Apoll | volutions literarchived 2009|p=344}}
    
    ===Thoma...
    LZ8k_5_2_3_generateDictionary:  that the [[United States of the [[Alabama |publisher=[[American American Party (United States, and the state of Alabamily in th...
    LZ8k_5_2_3_generateMarkov: ts admire was into Anarchive.org/faces, ethnology is magician)|and|124; Ras?r?a,?rhaepohnson]]
    | [[Daphne". Rubenefits">[[Amazon...
    LZ8k_6_2_3_generateDictionary:  that the [[United States of Algerian Church]] in the [[Alabama |publisher=[[American American in Paris'' is considered an inter...
    LZ8k_6_2_3_generateMarkov: ln honor. <brb t heorrogwy/ecocriksto non-commentary Feter-gasiasnd o:rAerine saved as "Ocreetna Pmeiaof]]al a nor to the U.S. I...
    Common Words Dictionary: the of and in to a is The as for that was by with on from his at an or are which br be A he journal have it were not Lincoln In ...
    
```
field | sum | avg | stddev | nulls
----- | --- | --- | ------ | -----
LZ8k_4_2_3_generateDictionary.uncompressed | 3218757.0000 | 32187.5700 | 50604.0314 | 0
LZ8k_4_2_3_generateDictionary.compressed   | 1165899.0000 | 11658.9900 | 17954.2717 | 0
LZ8k_4_2_3_generateDictionary.compressMs   | 444.1493 | 4.4415 | 6.9157 | 0
LZ8k_4_2_3_generateDictionary.uncompressMs | 75.0679 | 0.7507 | 1.6844 | 0
LZ8k_4_2_3_generateMarkov.uncompressed     | 3218757.0000 | 32187.5700 | 50604.0314 | 0
LZ8k_4_2_3_generateMarkov.compressed       | 1170196.0000 | 11701.9600 | 17991.2528 | 0
LZ8k_4_2_3_generateMarkov.compressMs       | 431.2426 | 4.3124 | 6.7047 | 0
LZ8k_4_2_3_generateMarkov.uncompressMs     | 64.1237 | 0.6412 | 0.9072 | 0
LZ8k_5_2_3_generateDictionary.uncompressed | 3218757.0000 | 32187.5700 | 50604.0314 | 0
LZ8k_5_2_3_generateDictionary.compressed   | 1166946.0000 | 11669.4600 | 17960.9352 | 0
LZ8k_5_2_3_generateDictionary.compressMs   | 433.0192 | 4.3302 | 6.6827 | 0
LZ8k_5_2_3_generateDictionary.uncompressMs | 64.5529 | 0.6455 | 0.9097 | 0
LZ8k_5_2_3_generateMarkov.uncompressed     | 3218757.0000 | 32187.5700 | 50604.0314 | 0
LZ8k_5_2_3_generateMarkov.compressed       | 1168814.0000 | 11688.1400 | 17975.8905 | 0
LZ8k_5_2_3_generateMarkov.compressMs       | 436.4708 | 4.3647 | 6.7691 | 0
LZ8k_5_2_3_generateMarkov.uncompressMs     | 67.6080 | 0.6761 | 0.9568 | 0
LZ8k_6_2_3_generateDictionary.uncompressed | 3218757.0000 | 32187.5700 | 50604.0314 | 0
LZ8k_6_2_3_generateDictionary.compressed   | 1167546.0000 | 11675.4600 | 17967.7210 | 0
LZ8k_6_2_3_generateDictionary.compressMs   | 438.0895 | 4.3809 | 6.7693 | 0
LZ8k_6_2_3_generateDictionary.uncompressMs | 62.2942 | 0.6229 | 0.8693 | 0
LZ8k_6_2_3_generateMarkov.uncompressed     | 3218757.0000 | 32187.5700 | 50604.0314 | 0
LZ8k_6_2_3_generateMarkov.compressed       | 1175776.0000 | 11757.7600 | 18030.1177 | 0
LZ8k_6_2_3_generateMarkov.compressMs       | 425.4561 | 4.2546 | 6.6917 | 0
LZ8k_6_2_3_generateMarkov.uncompressMs     | 64.8878 | 0.6489 | 0.8678 | 0
LZ8k_commonWords.uncompressed              | 3218757.0000 | 32187.5700 | 50604.0314 | 0
LZ8k_commonWords.compressed                | 1167532.0000 | 11675.3200 | 17968.8035 | 0
LZ8k_commonWords.compressMs                | 424.9970 | 4.2500 | 6.6535 | 0
LZ8k_commonWords.uncompressMs              | 59.1663 | 0.5917 | 0.8240 | 0
BZ0.uncompressed                           | 3218757.0000 | 32187.5700 | 50604.0314 | 0
BZ0.compressed                             | 1046973.0000 | 10469.7300 | 15355.9676 | 0
BZ0.compressMs                             | 1278.3160 | 12.7832 | 21.5460 | 0
BZ0.uncompressMs                           | 389.8889 | 3.8989 | 5.9557 | 0
LZ0.uncompressed                           | 3218757.0000 | 32187.5700 | 50604.0314 | 0
LZ0.compressed                             | 1194685.0000 | 11946.8500 | 18177.3498 | 0
LZ0.compressMs                             | 415.1405 | 4.1514 | 6.7009 | 0
LZ0.uncompressMs                           | 63.1195 | 0.6312 | 0.9835 | 0

