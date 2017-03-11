This will demonstrate how to serialize a CharTrie class in compressed format


First, we load some data into an index:

Code from [TrieDemo.java:135](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L135) executed in 17.78 seconds: 
```java
      CharTrieIndex charTrieIndex = new CharTrieIndex();
      WikiArticle.load().limit(100).forEach(article -> {
          charTrieIndex.addDocument(article.getText());
      });
      System.out.println(String.format("Indexing %s KB of documents",
              charTrieIndex.getIndexedSize() / 1024));
      charTrieIndex.index(6, 0);
      return charTrieIndex;
```
Returns: 
```
    com.simiacryptus.util.text.CharTrieIndex@1ff942aa
```
Logging: 
```
    Indexing 1978 KB of documents
    
```



Then, we compress the tree:

Code from [TrieDemo.java:148](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L148) executed in 7.96 seconds: 
```java
      byte[] bytes = new FullTrieSerializer().serialize(tree.copy());
      System.out.println(String.format("%s in ram, %s bytes in serialized form, %s%% compression",
              tree.getMemorySize(), bytes.length, 100 - (bytes.length / tree.getMemorySize())));
      return Base64.getEncoder().encodeToString(bytes);
```
Returns: 
```
    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... and 1246708 more bytes
```
Logging: 
```
    33554432 in ram, 938101 bytes in serialized form, 100% compression
    
```



And decompress to verify:

Code from [TrieDemo.java:156](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L156) executed in 10.86 seconds: 
```java
      byte[] bytes = Base64.getDecoder().decode(serialized);
      CharTrie restored = new FullTrieSerializer().deserialize(bytes);
      boolean verified = restored.root().equals(tree.root());
      System.out.println(String.format("Tree Verified: %s", verified));
      return bytes.length;
```
Returns: 
```
    938101
```
Logging: 
```
    Tree Verified: true
    
```

Then, we encode the data used to create the dictionary:

Code from [TrieDemo.java:165](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L165) executed in 244.86 seconds: 
```java
      PPMCodec codec = tree.getCodec();
      int totalSize = WikiArticle.load().limit(100).map(article -> {
          TimedResult<Bits> compressed = TimedResult.time(()->codec.encodePPM(article.getText(), 4));
          System.out.println(String.format("Serialized %s: %s chars -> %s bytes (%s%%) in %s sec",
                  article.getTitle(), article.getText().length(), compressed.obj.bitLength / 8.0,
                  compressed.obj.bitLength * 100.0 / (8.0 * article.getText().length()),
                  compressed.timeNanos / 1000000000.0));
          return compressed.obj.getBytes();
      }).mapToInt(bytes->bytes.length).sum();
      return String.format("Compressed %s KB of documents -> %s KB (%s dictionary + %s ppm)",
              index.getIndexedSize() / 1024, (totalSize + dictionaryLength)/ 1024,
              dictionaryLength / 1024, totalSize / 1024);
```
Returns: 
```
    Compressed 1978 KB of documents -> 1599 KB (916 dictionary + 683 ppm)
```
Logging: 
```
    Serialized AccessibleComputing: 69 chars -> 24.25 bytes (35.14492753623188%) in 5.61734E-4 sec
    Serialized Anarchism: 186232 chars -> 64973.375 bytes (34.888405322393574%) in 35.681048708 sec
    Serialized AfghanistanHistory: 57 chars -> 22.0 bytes (38.59649122807018%) in 4.07733E-4 sec
    Serialized AfghanistanGeography: 59 chars -> 21.625 bytes (36.652542372881356%) in 4.56134E-4 sec
    Serialized AfghanistanPeople: 62 chars -> 22.75 bytes (36.693548387096776%) in 3.916E-4 sec
    Serialized AfghanistanCommunications: 64 chars -> 24.0 bytes (37.5%) in 4.40978E-4 sec
    Serialized AfghanistanTransportations: 79 chars -> 27.375 bytes (34.651898734177216%) in 5.15778E-4 sec
    Serialized AfghanistanMilitary: 54 chars -> 19.625 bytes (36.342592592592595%) in 4.23378E-4 sec
    Serialized AfghanistanTransnationalIssues: 67 chars -> 24.875 bytes (37.12686567164179%) in 5.49511E-4 sec
    Serialized AssistiveTechnology: 55 chars -> 20.75 bytes (37.72727272727273%) in 3.99911E-4 sec
    Serialized AmoeboidTaxa: 41 chars -> 15.375 bytes (37.5%) in 2.92845E-4 sec
    Serialized Autism: 149779 chars -> 50824.5 bytes (33.93299461206177%) in 22.936827935 sec
    Serialized AlbaniaHistory: 53 chars -> 21.0 bytes (39.62264150943396%) in 4.69823E-4 sec
    Serialized AlbaniaPeople: 58 chars -> 21.875 bytes (37.71551724137931%) in 3.36844E-4 sec
    Serialized AsWeMayThink: 50 chars -> 16.625 bytes (33.25%) in 2.45422E-4 sec
    Serialized AlbaniaGovernment: 54 chars -> 20.75 bytes (38.425925925925924%) in 2.78667E-4 sec
    Serialized AlbaniaEconomy: 53 chars -> 20.5 bytes (38.679245283018865%) in 2.87956E-4 sec
    Serialized Albedo: 35354 chars -> 12023.375 bytes (34.00852803077445%) in 1.443829872 sec
    Serialized AfroAsiaticLanguages: 56 chars -> 19.875 bytes (35.49107142857143%) in 3.08489E-4 sec
    Serialized ArtificalLanguages: 142 chars -> 46.5 bytes (32.74647887323944%) in 7.392E-4 sec
    Serialized AbacuS: 41 chars -> 15.875 bytes (38.71951219512195%) in 2.25377E-4 sec
    Serialized AbalonE: 42 chars -> 15.375 bytes (36.607142857142854%) in 2.12667E-4 sec
    Serialized AbbadideS: 50 chars -> 17.25 bytes (34.5%) in 2.66933E-4 sec
    Serialized AbbesS: 41 chars -> 16.125 bytes (39.329268292682926%) in 2.56178E-4 sec
    Serialized AbbevilleFrance: 44 chars -> 16.5 bytes (37.5%) in 2.45422E-4 sec
    Serialized AbbeY: 40 chars -> 14.5 bytes (36.25%) in 3.26089E-4 sec
    Serialized AbboT: 40 chars -> 14.5 bytes (36.25%) in 2.10222E-4 sec
    Serialized Abbreviations: 44 chars -> 17.0 bytes (38.63636363636363%) in 2.49822E-4 sec
    Serialized AtlasShrugged: 50 chars -> 17.75 bytes (35.5%) in 2.73289E-4 sec
    Serialized ArtificialLanguages: 55 chars -> 21.125 bytes (38.40909090909091%) in 3.51511E-4 sec
    Serialized AtlasShruggedCharacters: 68 chars -> 25.0 bytes (36.76470588235294%) in 3.58845E-4 sec
    Serialized AtlasShruggedCompanies: 49 chars -> 18.25 bytes (37.244897959183675%) in 2.552E-4 sec
    Serialized AyersMusicPublishingCompany: 67 chars -> 23.5 bytes (35.07462686567164%) in 3.68622E-4 sec
    Serialized AfricanAmericanPeople: 52 chars -> 19.875 bytes (38.22115384615385%) in 2.72801E-4 sec
    Serialized AdolfHitler: 47 chars -> 18.0 bytes (38.297872340425535%) in 2.42978E-4 sec
    Serialized AbeceDarians: 46 chars -> 16.625 bytes (36.141304347826086%) in 2.38089E-4 sec
    Serialized AbeL: 48 chars -> 18.5 bytes (38.541666666666664%) in 2.48845E-4 sec
    Serialized AbensbergGermany: 44 chars -> 15.875 bytes (36.07954545454545%) in 2.42489E-4 sec
    Serialized AberdeenSouthDakota: 57 chars -> 20.375 bytes (35.74561403508772%) in 2.89912E-4 sec
    Serialized ArthurKoestler: 50 chars -> 17.75 bytes (35.5%) in 2.51778E-4 sec
    Serialized AynRand: 43 chars -> 16.375 bytes (38.08139534883721%) in 2.244E-4 sec
    Serialized AlexanderTheGreat: 53 chars -> 20.875 bytes (39.386792452830186%) in 3.14845E-4 sec
    Serialized AnchorageAlaska: 51 chars -> 18.875 bytes (37.009803921568626%) in 3.05555E-4 sec
    Serialized ArgumentForms: 47 chars -> 18.625 by... and 5516 more bytes
```

For reference, we encode some sample articles that are NOT in the dictionary:

Code from [TrieDemo.java:181](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L181) executed in 37.17 seconds: 
```java
      PPMCodec codec = tree.getCodec();
      WikiArticle.load().skip(100).limit(20).forEach(article -> {
          TimedResult<Bits> compressed = TimedResult.time(()->codec.encodePPM(article.getText(), 4));
          System.out.println(String.format("Serialized %s: %s chars -> %s bytes (%s%%) in %s sec",
                  article.getTitle(), article.getText().length(), compressed.obj.bitLength / 8.0,
                  compressed.obj.bitLength * 100.0 / (8.0 * article.getText().length()),
                  compressed.timeNanos / 1000000000.0));
      });
```
Logging: 
```
    Serialized Artificial languages: 78 chars -> 31.75 bytes (40.705128205128204%) in 5.42177E-4 sec
    Serialized Austroasiatic languages: 27408 chars -> 17736.375 bytes (64.71240148861646%) in 0.535570512 sec
    Serialized Afro-asiatic languages: 66 chars -> 24.5 bytes (37.121212121212125%) in 3.62267E-4 sec
    Serialized Afroasiatic languages: 47485 chars -> 34142.875 bytes (71.9024428766979%) in 1.333795147 sec
    Serialized Andorra: 61684 chars -> 34005.25 bytes (55.12815316775825%) in 2.008985989 sec
    Serialized Andorra/Transnational issues: 103 chars -> 56.625 bytes (54.97572815533981%) in 9.768E-4 sec
    Serialized Arithmetic mean: 11367 chars -> 6342.625 bytes (55.7985836192487%) in 0.13438775 sec
    Serialized American Football Conference: 12135 chars -> 7126.375 bytes (58.72579316028018%) in 0.157197842 sec
    Serialized Albert Gore: 42 chars -> 26.625 bytes (63.392857142857146%) in 3.43689E-4 sec
    Serialized AnEnquiryConcerningHumanUnderstanding: 76 chars -> 31.625 bytes (41.61184210526316%) in 4.37556E-4 sec
    Serialized Animal Farm: 69803 chars -> 38602.0 bytes (55.301348079595435%) in 2.52560472 sec
    Serialized Amphibian: 130679 chars -> 73759.5 bytes (56.443269385287614%) in 7.988847326 sec
    Serialized Albert Arnold Gore/Criticisms: 21 chars -> 15.125 bytes (72.02380952380952%) in 1.94577E-4 sec
    Serialized Alaska: 141209 chars -> 77129.875 bytes (54.62107585210575%) in 9.145602584 sec
    Serialized Auteur Theory Film: 20 chars -> 14.125 bytes (70.625%) in 1.73555E-4 sec
    Serialized Agriculture: 108723 chars -> 56743.25 bytes (52.19065883023831%) in 5.606050001 sec
    Serialized Aldous Huxley: 49379 chars -> 27534.5 bytes (55.76155855728143%) in 1.382558887 sec
    Serialized Abstract Algebra: 61 chars -> 26.625 bytes (43.64754098360656%) in 3.67156E-4 sec
    Serialized Ada: 3353 chars -> 2038.25 bytes (60.78884580972264%) in 0.030189382 sec
    Serialized Aberdeen (disambiguation): 6684 chars -> 3540.25 bytes (52.96603830041891%) in 0.071334275 sec
    
```

