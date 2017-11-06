This will demonstrate how to serialize a CharTrie class in compressed format


First, we load some data into an index:

Code from [TrieDemo.java:708](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L708) executed in 29.10 seconds: 
```java
      CharTrieIndex charTrieIndex = new CharTrieIndex();
      WikiArticle.ENGLISH.load().limit(100).forEach(article -> {
          charTrieIndex.addDocument(article.getText());
      });
      System.out.println(String.format("Indexing %s bytes of documents",
              charTrieIndex.getIndexedSize()));
      charTrieIndex.index(6, 1);
      return charTrieIndex;
```
Returns: 
```
    com.simiacryptus.text.CharTrieIndex@7613f54d
```
Logging: 
```
    Indexing 1669498 bytes of documents
    
```



Then, we compress the tree:

Code from [TrieDemo.java:721](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L721) executed in 14.01 seconds: 
```java
      byte[] bytes = CompressionUtil.encodeLZ(new ConvolutionalTrieSerializer().serialize(tree.copy()));
      System.out.println(String.format("%s in ram, %s bytes in serialized form, %s%% compression",
              tree.getMemorySize(), bytes.length, 100 - (bytes.length * 100.0 / tree.getMemorySize())));
      return Base64.getEncoder().encodeToString(bytes);
```
Returns: 
```
    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... and 872748 more bytes
```
Logging: 
```
    25165824 in ram, 666847 bytes in serialized form, 97.35018809636433% compression
    
```



And decompress to verify:

Code from [TrieDemo.java:729](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L729) executed in 15.94 seconds: 
```java
      byte[] bytes = CompressionUtil.decodeLZ(Base64.getDecoder().decode(serialized));
      CharTrie restored = new ConvolutionalTrieSerializer().deserialize(bytes);
      boolean verified = restored.root().equals(tree.root());
      System.out.println(String.format("Tree Verified: %s", verified));
      return bytes.length;
```
Returns: 
```
    925874
```
Logging: 
```
    Tree Verified: true
    
```

Then, we encode the data used to create the dictionary:

Code from [TrieDemo.java:738](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L738) executed in 14.96 seconds: 
```java
      NodewalkerCodec codec = tree.getCodec();
      int totalSize = WikiArticle.ENGLISH.load().limit(100).map(article -> {
          TimedResult<Bits> compressed = TimedResult.time(()->codec.encodePPM(article.getText(), 4));
          System.out.println(String.format("Serialized %s: %s chars -> %s bytes (%s%%) in %s ms",
                  article.getTitle(), article.getText().length(), compressed.obj.bitLength / 8.0,
                  compressed.obj.bitLength * 100.0 / (8.0 * article.getText().length()),
                  compressed.timeNanos / 1000000.0));
          return compressed.obj.getBytes();
      }).mapToInt(bytes->bytes.length).sum();
      return String.format("Compressed %s KB of documents -> %s KB (%s dictionary + %s ppm)",
              index.getIndexedSize() / 1024, (totalSize + dictionaryLength)/ 1024,
              dictionaryLength / 1024, totalSize / 1024);
```
Returns: 
```
    Compressed 1630 KB of documents -> 2313 KB (904 dictionary + 1409 ppm)
```
Logging: 
```
    Serialized AccessibleComputing: 69 chars -> 44.25 bytes (64.1304347826087%) in 6.687023 ms
    Serialized Anarchism: 186232 chars -> 134102.5 bytes (72.00830147343099%) in 877.365489 ms
    Serialized AfghanistanHistory: 57 chars -> 39.0 bytes (68.42105263157895%) in 0.3696 ms
    Serialized AfghanistanGeography: 59 chars -> 40.75 bytes (69.0677966101695%) in 0.236133 ms
    Serialized AfghanistanPeople: 62 chars -> 41.75 bytes (67.33870967741936%) in 0.244933 ms
    Serialized AfghanistanCommunications: 64 chars -> 41.625 bytes (65.0390625%) in 0.252756 ms
    Serialized AfghanistanTransportations: 79 chars -> 53.375 bytes (67.5632911392405%) in 0.383778 ms
    Serialized AfghanistanMilitary: 54 chars -> 33.25 bytes (61.574074074074076%) in 0.214622 ms
    Serialized AfghanistanTransnationalIssues: 67 chars -> 46.5 bytes (69.40298507462687%) in 0.264978 ms
    Serialized AssistiveTechnology: 55 chars -> 37.625 bytes (68.4090909090909%) in 0.231244 ms
    Serialized AmoeboidTaxa: 41 chars -> 25.75 bytes (62.80487804878049%) in 0.161333 ms
    Serialized Autism: 149779 chars -> 108345.25 bytes (72.33674280106023%) in 658.39555 ms
    Serialized AlbaniaHistory: 53 chars -> 35.625 bytes (67.21698113207547%) in 0.330978 ms
    Serialized AlbaniaPeople: 58 chars -> 35.75 bytes (61.63793103448276%) in 0.22 ms
    Serialized AsWeMayThink: 50 chars -> 35.75 bytes (71.5%) in 0.211688 ms
    Serialized AlbaniaGovernment: 54 chars -> 34.125 bytes (63.19444444444444%) in 0.200445 ms
    Serialized AlbaniaEconomy: 53 chars -> 33.375 bytes (62.971698113207545%) in 0.187733 ms
    Serialized Albedo: 35354 chars -> 25264.75 bytes (71.46221078237258%) in 148.693619 ms
    Serialized AfroAsiaticLanguages: 56 chars -> 37.0 bytes (66.07142857142857%) in 0.243956 ms
    Serialized ArtificalLanguages: 142 chars -> 93.625 bytes (65.9330985915493%) in 0.5588 ms
    Serialized AbacuS: 41 chars -> 26.125 bytes (63.71951219512195%) in 0.161823 ms
    Serialized AbalonE: 42 chars -> 28.125 bytes (66.96428571428571%) in 0.217067 ms
    Serialized AbbadideS: 50 chars -> 32.125 bytes (64.25%) in 0.207289 ms
    Serialized AbbesS: 41 chars -> 25.25 bytes (61.58536585365854%) in 0.167688 ms
    Serialized AbbevilleFrance: 44 chars -> 28.5 bytes (64.77272727272727%) in 0.165244 ms
    Serialized AbbeY: 40 chars -> 25.125 bytes (62.8125%) in 0.142756 ms
    Serialized AbboT: 40 chars -> 24.625 bytes (61.5625%) in 0.146178 ms
    Serialized Abbreviations: 44 chars -> 29.25 bytes (66.47727272727273%) in 0.172577 ms
    Serialized AtlasShrugged: 50 chars -> 30.625 bytes (61.25%) in 0.192622 ms
    Serialized ArtificialLanguages: 55 chars -> 35.625 bytes (64.77272727272727%) in 0.193112 ms
    Serialized AtlasShruggedCharacters: 68 chars -> 44.125 bytes (64.88970588235294%) in 0.264 ms
    Serialized AtlasShruggedCompanies: 49 chars -> 29.875 bytes (60.96938775510204%) in 0.169645 ms
    Serialized AyersMusicPublishingCompany: 67 chars -> 40.125 bytes (59.88805970149254%) in 0.229778 ms
    Serialized AfricanAmericanPeople: 52 chars -> 33.25 bytes (63.94230769230769%) in 0.195555 ms
    Serialized AdolfHitler: 47 chars -> 29.625 bytes (63.03191489361702%) in 0.196045 ms
    Serialized AbeceDarians: 46 chars -> 29.0 bytes (63.04347826086956%) in 0.168178 ms
    Serialized AbeL: 48 chars -> 31.375 bytes (65.36458333333333%) in 0.185778 ms
    Serialized AbensbergGermany: 44 chars -> 28.375 bytes (64.48863636363636%) in 0.1716 ms
    Serialized AberdeenSouthDakota: 57 chars -> 37.875 bytes (66.44736842105263%) in 0.232712 ms
    Serialized ArthurKoestler: 50 chars -> 33.5 bytes (67.0%) in 0.192133 ms
    Serialized AynRand: 43 chars -> 26.375 bytes (61.33720930232558%) in 0.159378 ms
    Serialized AlexanderTheGreat: 53 chars -> 34.0 bytes (64.15094339622641%) in 0.196044 ms
    Serialized AnchorageAlaska: 51 chars -> 32.875 bytes (64.46078431372548%) in 0.200934 ms
    Serialized ArgumentForms: 47 chars -> 30.625 bytes (65.15957446808511%) in 0.237111 ms
    Serialized ArgumentsForTheExistenceOfGod: 50 chars -> 32.625 bytes (65.25%) in 0.200933 ms
    Serialized AnarchY: 43 chars -> 27.375 bytes (63.66279069767442%) in 0.154489 ms
    Serialized AsciiArt: 43 chars -> 27.375 bytes (63.66279069767442%) in 0.191645 ms
    Serialized AcademyAwards: 48 chars -> 31.125 bytes (64.84375%) in 0.185778 ms
    Serialized AcademyAwards/BestPicture: 96 chars -> 63.5 bytes (66.14583333333333%) in 0.453689 ms
    Serialized AustriaLanguage: 50 chars -> 32.5 bytes (65.0%) in 0.188222 ms
    Serialized AcademicElitism: 45 chars -> 29.375 bytes (65.27777777777777%) in 0.174044 ms
    Serialized AxiomOfChoice: 49 chars -> 31.375 bytes (64.03061224489795%) in 0.269378 ms
    Serialized AmericanFootball: 51 chars -> 33.25 bytes (65.19607843137256%) in 0.289912 ms
    Serialized AnnaKournikova: 50 chars -> 31.125 bytes (62.25%) in 0.205822 ms
    Serialized AndorrA: 42 chars -> 26.625 bytes (63.392857142857146%) in 0.154489 ms
    Serialized AustroAsiaticLanguages: 57 chars -> 37.625 bytes (66.00877192982456%) in 0.208267 ms
    Serialized ActresseS: 62 chars -> 41.125 bytes (66.33064516129032%) in 0.234178 ms
    Serialized A: 19452 chars -> 13243.875 bytes (68.0849012954966%) in 75.714232 ms
    Serialized AnarchoCapitalism: 52 chars -> 32.375 bytes (62.25961538461539%) in 0.2596 ms
    Serialized AnarchoCapitalists: 52 chars -> 32.875 bytes (63.22115384615385%) in 0.176978 ms
    Serialized ActressesS: 50 chars -> 33.5 bytes (67.0%) in 0.188222 ms
    Serialized AnAmericanInParis: 54 chars -> 35.875 bytes (66.43518518518519%) in 0.200444 ms
    Serialized AutoMorphism: 46 chars -> 28.25 bytes (61.41304347826087%) in 0.168178 ms
    Serialized ActionFilm: 45 chars -> 29.875 bytes (66.38888888888889%) in 0.192623 ms
    Serialized Alabama: 168088 chars -> 120675.0 bytes (71.792751415925%) in 700.805201 ms
    Serialized AfricA: 41 chars -> 26.25 bytes (64.02439024390245%) in 0.201912 ms
    Serialized Achilles: 51211 chars -> 35036.375 bytes (68.41572123176661%) in 214.160738 ms
    Serialized AppliedStatistics: 44 chars -> 28.875 bytes (65.625%) in 0.243466 ms
    Serialized Abraham Lincoln: 179104 chars -> 121033.125 bytes (67.57700833035555%) in 688.561954 ms
    Serialized Aristotle: 110077 chars -> 78100.625 bytes (70.95090255003316%) in 468.930059 ms
    Serialized An American in Paris: 15603 chars -> 11300.0 bytes (72.4219701339486%) in 71.704854 ms
    Serialized Academy Award for Best Production Design: 86370 chars -> 62195.625 bytes (72.01068079194165%) in 363.893735 ms
    Serialized Academy Awards: 78000 chars -> 56002.25 bytes (71.79775641025641%) in 334.489998 ms
    Serialized Action Film: 56 chars -> 36.25 bytes (64.73214285714286%) in 0.275733 ms
    Serialized Actrius: 5763 chars -> 4216.0 bytes (73.15634218289085%) in 24.335425 ms
    Serialized Animalia (book): 5783 chars -> 4122.625 bytes (71.2886909908352%) in 25.413426 ms
    Serialized International Atomic Time: 11976 chars -> 8791.5 bytes (73.40931863727455%) in 54.758985 ms
    Serialized Altruism: 67373 chars -> 48203.125 bytes (71.54665073545783%) in 285.875859 ms
    Serialized AutoRacing: 45 chars -> 31.375 bytes (69.72222222222223%) in 0.342712 ms
    Serialized Ayn Rand: 89381 chars -> 62884.75 bytes (70.35583625155235%) in 366.514669 ms
    Serialized Alain Connes: 6198 chars -> 4470.75 bytes (72.13213939980639%) in 27.135292 ms
    Serialized Allan Dwan: 11264 chars -> 8113.5 bytes (72.0303622159091%) in 49.318139 ms
    Serialized Algeria/People: 57 chars -> 35.75 bytes (62.719298245614034%) in 0.234667 ms
    Serialized Algeria/Transnational Issues: 62 chars -> 41.75 bytes (67.33870967741936%) in 0.243466 ms
    Serialized Algeria: 120072 chars -> 86731.0 bytes (72.23249383703111%) in 490.429929 ms
    Serialized List of Atlas Shrugged characters: 31725 chars -> 21816.125 bytes (68.76635145784081%) in 116.070548 ms
    Serialized Topics of note in Atlas Shrugged: 28 chars -> 17.75 bytes (63.392857142857146%) in 0.122711 ms
    Serialized Anthropology: 92134 chars -> 62534.75 bytes (67.87369483578266%) in 354.248445 ms
    Serialized Agricultural science: 13822 chars -> 9454.375 bytes (68.4009188250615%) in 56.457874 ms
    Serialized Alchemy: 81528 chars -> 55266.5 bytes (67.7883671867334%) in 314.050039 ms
    Serialized Air Transport: 22 chars -> 14.625 bytes (66.47727272727273%) in 0.109022 ms
    Serialized Alien: 3799 chars -> 2553.875 bytes (67.22492761252961%) in 13.568135 ms
    Serialized Astronomer: 8253 chars -> 5643.75 bytes (68.38422391857506%) in 32.170848 ms
    Serialized Ameboid stage: 20 chars -> 13.0 bytes (65.0%) in 0.171111 ms
    Serialized ASCII: 100765 chars -> 75934.75 bytes (75.35825931623083%) in 457.848903 ms
    Serialized Ashmore And Cartier Islands: 72 chars -> 50.0 bytes (69.44444444444444%) in 0.313867 ms
    Serialized Austin (disambiguation): 2073 chars -> 1441.875 bytes (69.55499276410998%) in 9.28449 ms
    Serialized Animation: 56003 chars -> 39966.5 bytes (71.3649268789172%) in 241.581053 ms
    Serialized Apollo: 126677 chars -> 94668.375 bytes (74.73209422389226%) in 544.519625 ms
    Serialized Andre Agassi: 108263 chars -> 78794.75 bytes (72.78086696285897%) in 456.220902 ms
    
```

For reference, we encode some sample articles that are NOT in the dictionary:

Code from [TrieDemo.java:754](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L754) executed in 6.69 seconds: 
```java
      NodewalkerCodec codec = tree.getCodec();
      WikiArticle.ENGLISH.load().skip(100).limit(10).forEach(article -> {
          TimedResult<Bits> compressed = TimedResult.time(()->codec.encodePPM(article.getText(), 4));
          System.out.println(String.format("Serialized %s: %s chars -> %s bytes (%s%%) in %s ms",
                  article.getTitle(), article.getText().length(), compressed.obj.bitLength / 8.0,
                  compressed.obj.bitLength * 100.0 / (8.0 * article.getText().length()),
                  compressed.timeNanos / 1000000.0));
      });
```
Logging: 
```
    Serialized Artificial languages: 78 chars -> 53.625 bytes (68.75%) in 0.604267 ms
    Serialized Austroasiatic languages: 27408 chars -> 20117.375 bytes (73.39964608873322%) in 132.010283 ms
    Serialized Afro-asiatic languages: 66 chars -> 44.875 bytes (67.99242424242425%) in 0.399423 ms
    Serialized Afroasiatic languages: 47485 chars -> 36505.125 bytes (76.87717173844372%) in 229.645318 ms
    Serialized Andorra: 61684 chars -> 44599.875 bytes (72.30379839180338%) in 281.943236 ms
    Serialized Andorra/Transnational issues: 103 chars -> 74.0 bytes (71.84466019417475%) in 0.491823 ms
    Serialized Arithmetic mean: 11367 chars -> 8256.75 bytes (72.63789918184217%) in 48.894761 ms
    Serialized American Football Conference: 12135 chars -> 8852.75 bytes (72.95220436753193%) in 56.896896 ms
    Serialized Albert Gore: 42 chars -> 33.125 bytes (78.86904761904762%) in 0.346622 ms
    Serialized AnEnquiryConcerningHumanUnderstanding: 76 chars -> 52.25 bytes (68.75%) in 0.420445 ms
    
```

