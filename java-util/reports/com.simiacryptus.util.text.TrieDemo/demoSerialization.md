This will demonstrate how to serialize a CharTrie class in compressed format


First, we load some data into an index:

Code from [TrieDemo.java:209](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L209) executed in 33.77 seconds: 
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
    com.simiacryptus.util.text.CharTrieIndex@472cddd7
```
Logging: 
```
    Indexing 2142 KB of documents
    
```



Then, we compress the tree:

Code from [TrieDemo.java:222](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L222) executed in 11.34 seconds: 
```java
      byte[] bytes = new FullTrieSerializer().serialize(tree.copy());
      System.out.println(String.format("%s in ram, %s bytes in serialized form, %s%% compression",
              tree.getMemorySize(), bytes.length, 100 - (bytes.length / tree.getMemorySize())));
      return Base64.getEncoder().encodeToString(bytes);
```
Returns: 
```
    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... and 1361740 more bytes
```
Logging: 
```
    50331648 in ram, 1024376 bytes in serialized form, 100% compression
    
```



And decompress to verify:

Code from [TrieDemo.java:230](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L230) executed in 15.31 seconds: 
```java
      byte[] bytes = Base64.getDecoder().decode(serialized);
      CharTrie restored = new FullTrieSerializer().deserialize(bytes);
      boolean verified = restored.root().equals(tree.root());
      System.out.println(String.format("Tree Verified: %s", verified));
      return bytes.length;
```
Returns: 
```
    1024376
```
Logging: 
```
    Tree Verified: true
    
```

Then, we encode the data used to create the dictionary:

Code from [TrieDemo.java:239](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L239) executed in 179.71 seconds: 
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
    Compressed 2142 KB of documents -> 1973 KB (1000 dictionary + 972 ppm)
```
Logging: 
```
    Serialized AccessibleComputing: 69 chars -> 23.875 bytes (34.60144927536232%) in 0.007321601 sec
    Serialized Anarchism: 186232 chars -> 102962.875 bytes (55.287423751020235%) in 16.041998215 sec
    Serialized AfghanistanHistory: 57 chars -> 22.875 bytes (40.13157894736842%) in 7.55823E-4 sec
    Serialized AfghanistanGeography: 59 chars -> 24.25 bytes (41.101694915254235%) in 4.70311E-4 sec
    Serialized AfghanistanPeople: 62 chars -> 25.125 bytes (40.524193548387096%) in 4.89866E-4 sec
    Serialized AfghanistanCommunications: 64 chars -> 24.0 bytes (37.5%) in 5.15778E-4 sec
    Serialized AfghanistanTransportations: 79 chars -> 42.125 bytes (53.322784810126585%) in 7.964E-4 sec
    Serialized AfghanistanMilitary: 54 chars -> 21.875 bytes (40.50925925925926%) in 4.29244E-4 sec
    Serialized AfghanistanTransnationalIssues: 67 chars -> 24.5 bytes (36.56716417910448%) in 5.07956E-4 sec
    Serialized AssistiveTechnology: 55 chars -> 23.875 bytes (43.40909090909091%) in 4.92311E-4 sec
    Serialized AmoeboidTaxa: 41 chars -> 16.375 bytes (39.9390243902439%) in 3.18267E-4 sec
    Serialized Autism: 149779 chars -> 86821.0 bytes (57.96607000981446%) in 10.887689427 sec
    Serialized AlbaniaHistory: 53 chars -> 22.125 bytes (41.74528301886792%) in 6.12578E-4 sec
    Serialized AlbaniaPeople: 58 chars -> 24.5 bytes (42.241379310344826%) in 4.62978E-4 sec
    Serialized AsWeMayThink: 50 chars -> 38.25 bytes (76.5%) in 5.72E-4 sec
    Serialized AlbaniaGovernment: 54 chars -> 23.125 bytes (42.824074074074076%) in 5.72489E-4 sec
    Serialized AlbaniaEconomy: 53 chars -> 20.25 bytes (38.20754716981132%) in 4.87422E-4 sec
    Serialized Albedo: 35354 chars -> 19036.75 bytes (53.84609945126436%) in 0.877697934 sec
    Serialized AfroAsiaticLanguages: 56 chars -> 21.25 bytes (37.94642857142857%) in 4.32178E-4 sec
    Serialized ArtificalLanguages: 142 chars -> 47.125 bytes (33.186619718309856%) in 0.001036444 sec
    Serialized AbacuS: 41 chars -> 15.5 bytes (37.80487804878049%) in 2.93334E-4 sec
    Serialized AbalonE: 42 chars -> 15.375 bytes (36.607142857142854%) in 3.03111E-4 sec
    Serialized AbbadideS: 50 chars -> 17.375 bytes (34.75%) in 3.60311E-4 sec
    Serialized AbbesS: 41 chars -> 16.125 bytes (39.329268292682926%) in 3.036E-4 sec
    Serialized AbbevilleFrance: 44 chars -> 16.5 bytes (37.5%) in 3.388E-4 sec
    Serialized AbbeY: 40 chars -> 14.5 bytes (36.25%) in 2.772E-4 sec
    Serialized AbboT: 40 chars -> 14.75 bytes (36.875%) in 2.91378E-4 sec
    Serialized Abbreviations: 44 chars -> 16.75 bytes (38.06818181818182%) in 3.17777E-4 sec
    Serialized AtlasShrugged: 50 chars -> 17.625 bytes (35.25%) in 3.48578E-4 sec
    Serialized ArtificialLanguages: 55 chars -> 21.125 bytes (38.40909090909091%) in 4.08711E-4 sec
    Serialized AtlasShruggedCharacters: 68 chars -> 25.0 bytes (36.76470588235294%) in 4.92311E-4 sec
    Serialized AtlasShruggedCompanies: 49 chars -> 18.125 bytes (36.98979591836735%) in 3.45645E-4 sec
    Serialized AyersMusicPublishingCompany: 67 chars -> 23.75 bytes (35.44776119402985%) in 4.79601E-4 sec
    Serialized AfricanAmericanPeople: 52 chars -> 19.5 bytes (37.5%) in 3.66666E-4 sec
    Serialized AdolfHitler: 47 chars -> 17.75 bytes (37.765957446808514%) in 4.25822E-4 sec
    Serialized AbeceDarians: 46 chars -> 16.625 bytes (36.141304347826086%) in 4.47333E-4 sec
    Serialized AbeL: 48 chars -> 18.0 bytes (37.5%) in 4.49289E-4 sec
    Serialized AbensbergGermany: 44 chars -> 16.0 bytes (36.36363636363637%) in 4.092E-4 sec
    Serialized AberdeenSouthDakota: 57 chars -> 20.25 bytes (35.526315789473685%) in 5.56845E-4 sec
    Serialized ArthurKoestler: 50 chars -> 17.875 bytes (35.75%) in 4.78622E-4 sec
    Serialized AynRand: 43 chars -> 16.125 bytes (37.5%) in 4.03822E-4 sec
    Serialized AlexanderTheGreat: 53 chars -> 20.625 bytes (38.91509433962264%) in 5.12355E-4 sec
    Serialized AnchorageAlaska: 51 chars -> 19.25 bytes (37.745098039215684%) in 4.796E-4 sec
    Serialized ArgumentForms: 47 chars -> 18.375 bytes (39.09574468085106%) in... and 5463 more bytes
```

For reference, we encode some sample articles that are NOT in the dictionary:

Code from [TrieDemo.java:255](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L255) executed in 41.16 seconds: 
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
    Serialized Artificial languages: 78 chars -> 40.375 bytes (51.76282051282051%) in 8.18889E-4 sec
    Serialized Austroasiatic languages: 27408 chars -> 16477.25 bytes (60.11839608873321%) in 0.621230212 sec
    Serialized Afro-asiatic languages: 66 chars -> 24.375 bytes (36.93181818181818%) in 5.04533E-4 sec
    Serialized Afroasiatic languages: 47485 chars -> 32814.125 bytes (69.10419079709382%) in 1.486144678 sec
    Serialized Andorra: 61684 chars -> 33724.625 bytes (54.67321347513131%) in 2.391693148 sec
    Serialized Andorra/Transnational issues: 103 chars -> 45.25 bytes (43.932038834951456%) in 0.001166 sec
    Serialized Arithmetic mean: 11367 chars -> 5956.875 bytes (52.40498812351544%) in 0.168151888 sec
    Serialized American Football Conference: 12135 chars -> 7298.75 bytes (60.146271116604865%) in 0.198259137 sec
    Serialized Albert Gore: 42 chars -> 29.0 bytes (69.04761904761905%) in 4.87422E-4 sec
    Serialized AnEnquiryConcerningHumanUnderstanding: 76 chars -> 30.5 bytes (40.13157894736842%) in 5.86178E-4 sec
    Serialized Animal Farm: 69803 chars -> 39458.875 bytes (56.528909932237866%) in 2.889000278 sec
    Serialized Amphibian: 130679 chars -> 71542.0 bytes (54.74636322592%) in 8.294392165 sec
    Serialized Albert Arnold Gore/Criticisms: 21 chars -> 17.5 bytes (83.33333333333333%) in 3.15822E-4 sec
    Serialized Alaska: 141209 chars -> 76703.375 bytes (54.319041279238576%) in 9.508486763 sec
    Serialized Auteur Theory Film: 20 chars -> 12.125 bytes (60.625%) in 2.13156E-4 sec
    Serialized Agriculture: 108723 chars -> 55645.125 bytes (51.180637951491406%) in 5.86668159 sec
    Serialized Aldous Huxley: 49379 chars -> 28938.125 bytes (58.604113084509606%) in 1.54488664 sec
    Serialized Abstract Algebra: 61 chars -> 23.375 bytes (38.31967213114754%) in 4.57111E-4 sec
    Serialized Ada: 3353 chars -> 1163.375 bytes (34.696540411571725%) in 0.03519316 sec
    Serialized Aberdeen (disambiguation): 6684 chars -> 2329.125 bytes (34.84627468581687%) in 0.098778057 sec
    
```

