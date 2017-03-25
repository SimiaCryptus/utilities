This will demonstrate how to serialize a CharTrie class in compressed format


First, we load some data into an index:

Code from [TrieDemo.java:711](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L711) executed in 35.25 seconds: 
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
    com.simiacryptus.util.text.CharTrieIndex@7b0c4e86
```
Logging: 
```
    Indexing 1701894 bytes of documents
    
```



Then, we compress the tree:

Code from [TrieDemo.java:724](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L724) executed in 20.92 seconds: 
```java
      byte[] bytes = CompressionUtil.encodeLZ(new FullTrieSerializer().serialize(tree.copy()));
      System.out.println(String.format("%s in ram, %s bytes in serialized form, %s%% compression",
              tree.getMemorySize(), bytes.length, 100 - (bytes.length * 100.0 / tree.getMemorySize())));
      return Base64.getEncoder().encodeToString(bytes);
```
Returns: 
```
    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... and 883804 more bytes
```
Logging: 
```
    25165824 in ram, 675139 bytes in serialized form, 97.3172386487325% compression
    
```



And decompress to verify:

Code from [TrieDemo.java:732](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L732) executed in 19.07 seconds: 
```java
      byte[] bytes = CompressionUtil.decodeLZ(Base64.getDecoder().decode(serialized));
      CharTrie restored = new FullTrieSerializer().deserialize(bytes);
      boolean verified = restored.root().equals(tree.root());
      System.out.println(String.format("Tree Verified: %s", verified));
      return bytes.length;
```
Returns: 
```
    936128
```
Logging: 
```
    Tree Verified: true
    
```

Then, we encode the data used to create the dictionary:

Code from [TrieDemo.java:741](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L741) executed in 122.15 seconds: 
```java
      PPMCodec codec = tree.getCodec();
      int totalSize = WikiArticle.ENGLISH.load().limit(100).map(article -> {
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
    Compressed 1662 KB of documents -> 1911 KB (914 dictionary + 997 ppm)
```
Logging: 
```
    Serialized AccessibleComputing: 69 chars -> 22.25 bytes (32.2463768115942%) in 0.007912178 sec
    Serialized Anarchism: 186232 chars -> 103157.875 bytes (55.392131857038535%) in 16.485128627 sec
    Serialized AfghanistanHistory: 57 chars -> 20.25 bytes (35.526315789473685%) in 8.17422E-4 sec
    Serialized AfghanistanGeography: 59 chars -> 21.75 bytes (36.86440677966102%) in 4.25823E-4 sec
    Serialized AfghanistanPeople: 62 chars -> 22.125 bytes (35.685483870967744%) in 5.06978E-4 sec
    Serialized AfghanistanCommunications: 64 chars -> 21.375 bytes (33.3984375%) in 4.50267E-4 sec
    Serialized AfghanistanTransportations: 79 chars -> 38.875 bytes (49.20886075949367%) in 6.92267E-4 sec
    Serialized AfghanistanMilitary: 54 chars -> 19.25 bytes (35.648148148148145%) in 5.68578E-4 sec
    Serialized AfghanistanTransnationalIssues: 67 chars -> 24.0 bytes (35.82089552238806%) in 4.98178E-4 sec
    Serialized AssistiveTechnology: 55 chars -> 22.75 bytes (41.36363636363637%) in 4.58089E-4 sec
    Serialized AmoeboidTaxa: 41 chars -> 13.625 bytes (33.23170731707317%) in 3.00178E-4 sec
    Serialized Autism: 149779 chars -> 89170.875 bytes (59.5349648482097%) in 10.913794631 sec
    Serialized AlbaniaHistory: 53 chars -> 19.25 bytes (36.320754716981135%) in 7.04E-4 sec
    Serialized AlbaniaPeople: 58 chars -> 21.25 bytes (36.63793103448276%) in 4.14089E-4 sec
    Serialized AsWeMayThink: 50 chars -> 36.625 bytes (73.25%) in 5.368E-4 sec
    Serialized AlbaniaGovernment: 54 chars -> 20.375 bytes (37.73148148148148%) in 4.00889E-4 sec
    Serialized AlbaniaEconomy: 53 chars -> 18.0 bytes (33.9622641509434%) in 3.81334E-4 sec
    Serialized Albedo: 35354 chars -> 19343.625 bytes (54.71410590032245%) in 0.888888601 sec
    Serialized AfroAsiaticLanguages: 56 chars -> 18.5 bytes (33.035714285714285%) in 4.01867E-4 sec
    Serialized ArtificalLanguages: 142 chars -> 46.5 bytes (32.74647887323944%) in 0.001092178 sec
    Serialized AbacuS: 41 chars -> 12.5 bytes (30.48780487804878%) in 2.75733E-4 sec
    Serialized AbalonE: 42 chars -> 12.75 bytes (30.357142857142858%) in 2.71823E-4 sec
    Serialized AbbadideS: 50 chars -> 15.375 bytes (30.75%) in 4.41466E-4 sec
    Serialized AbbesS: 41 chars -> 12.5 bytes (30.48780487804878%) in 2.728E-4 sec
    Serialized AbbevilleFrance: 44 chars -> 14.125 bytes (32.10227272727273%) in 2.91378E-4 sec
    Serialized AbbeY: 40 chars -> 13.125 bytes (32.8125%) in 3.36356E-4 sec
    Serialized AbboT: 40 chars -> 12.875 bytes (32.1875%) in 3.45644E-4 sec
    Serialized Abbreviations: 44 chars -> 14.375 bytes (32.67045454545455%) in 3.83289E-4 sec
    Serialized AtlasShrugged: 50 chars -> 15.0 bytes (30.0%) in 4.61023E-4 sec
    Serialized ArtificialLanguages: 55 chars -> 18.0 bytes (32.72727272727273%) in 5.15778E-4 sec
    Serialized AtlasShruggedCharacters: 68 chars -> 22.875 bytes (33.63970588235294%) in 4.92312E-4 sec
    Serialized AtlasShruggedCompanies: 49 chars -> 14.625 bytes (29.846938775510203%) in 3.09467E-4 sec
    Serialized AyersMusicPublishingCompany: 67 chars -> 19.625 bytes (29.291044776119403%) in 4.664E-4 sec
    Serialized AfricanAmericanPeople: 52 chars -> 17.375 bytes (33.41346153846154%) in 3.49556E-4 sec
    Serialized AdolfHitler: 47 chars -> 14.375 bytes (30.585106382978722%) in 2.91378E-4 sec
    Serialized AbeceDarians: 46 chars -> 14.625 bytes (31.793478260869566%) in 3.06045E-4 sec
    Serialized AbeL: 48 chars -> 16.0 bytes (33.333333333333336%) in 3.13377E-4 sec
    Serialized AbensbergGermany: 44 chars -> 13.375 bytes (30.397727272727273%) in 2.79644E-4 sec
    Serialized AberdeenSouthDakota: 57 chars -> 18.0 bytes (31.57894736842105%) in 3.61778E-4 sec
    Serialized ArthurKoestler: 50 chars -> 15.375 bytes (30.75%) in 3.20711E-4 sec
    Serialized AynRand: 43 chars -> 12.875 bytes (29.941860465116278%) in 2.66933E-4 sec
    Serialized AlexanderTheGreat: 53 chars -> 17.0 bytes (32.075471698113205%) in 3.388E-4 sec
    Serialized AnchorageAlaska: 51 chars -> 15.125 bytes (29.65686274509804%) in 3.35378E-4 sec
    Serialized ArgumentForms: 47 chars -> 15.0 bytes (31.914893617021278%) in 2.96267E-4 sec
    Serialized ArgumentsForTheExistenceOfGod: 50 chars -> 16.5 bytes (33.0%) in 3.28533E-4 sec
    Serialized AnarchY: 43 chars -> 13.625 bytes (31.686046511627907%) in 2.68889E-4 sec
    Serialized AsciiArt: 43 chars -> 12.0 bytes (27.906976744186046%) in 2.67423E-4 sec
    Serialized AcademyAwards: 48 chars -> 15.0 bytes (31.25%) in 3.07511E-4 sec
    Serialized AcademyAwards/BestPicture: 96 chars -> 33.375 bytes (34.765625%) in 0.002947511 sec
    Serialized AustriaLanguage: 50 chars -> 17.0 bytes (34.0%) in 3.29022E-4 sec
    Serialized AcademicElitism: 45 chars -> 13.5 bytes (30.0%) in 3.21689E-4 sec
    Serialized AxiomOfChoice: 49 chars -> 14.375 bytes (29.336734693877553%) in 3.28045E-4 sec
    Serialized AmericanFootball: 51 chars -> 15.375 bytes (30.147058823529413%) in 3.31956E-4 sec
    Serialized AnnaKournikova: 50 chars -> 15.875 bytes (31.75%) in 3.388E-4 sec
    Serialized AndorrA: 42 chars -> 13.25 bytes (31.547619047619047%) in 2.816E-4 sec
    Serialized AustroAsiaticLanguages: 57 chars -> 18.625 bytes (32.675438596491226%) in 3.74978E-4 sec
    Serialized ActresseS: 62 chars -> 20.125 bytes (32.45967741935484%) in 6.33111E-4 sec
    Serialized A: 19452 chars -> 6613.125 bytes (33.9971468229488%) in 0.318429507 sec
    Serialized AnarchoCapitalism: 52 chars -> 16.75 bytes (32.21153846153846%) in 3.54933E-4 sec
    Serialized AnarchoCapitalists: 52 chars -> 16.625 bytes (31.971153846153847%) in 3.35867E-4 sec
    Serialized ActressesS: 50 chars -> 17.875 bytes (35.75%) in 3.31955E-4 sec
    Serialized AnAmericanInParis: 54 chars -> 17.75 bytes (32.870370370370374%) in 3.58844E-4 sec
    Serialized AutoMorphism: 46 chars -> 14.5 bytes (31.52173913043478%) in 3.01156E-4 sec
    Serialized ActionFilm: 45 chars -> 14.125 bytes (31.38888888888889%) in 2.82089E-4 sec
    Serialized Alabama: 168088 chars -> 56911.5 bytes (33.85815763171672%) in 12.886626303 sec
    Serialized AfricA: 41 chars -> 12.75 bytes (31.097560975609756%) in 3.70089E-4 sec
    Serialized Achilles: 51211 chars -> 18021.0 bytes (35.18970533674406%) in 1.505178102 sec
    Serialized AppliedStatistics: 44 chars -> 14.75 bytes (33.52272727272727%) in 3.79867E-4 sec
    Serialized Abraham Lincoln: 179104 chars -> 62162.375 bytes (34.70741859478292%) in 14.790953879 sec
    Serialized Aristotle: 110077 chars -> 55368.5 bytes (50.29979014689717%) in 6.018330542 sec
    Serialized An American in Paris: 15603 chars -> 9220.0 bytes (59.09120041017753%) in 0.253779321 sec
    Serialized Academy Award for Best Production Design: 86370 chars -> 54643.125 bytes (63.26632511288642%) in 3.966390281 sec
    Serialized Academy Awards: 78000 chars -> 44348.25 bytes (56.85673076923077%) in 3.277644016 sec
    Serialized Action Film: 56 chars -> 19.625 bytes (35.044642857142854%) in 4.19955E-4 sec
    Serialized Actrius: 5763 chars -> 3638.75 bytes (63.1398577129967%) in 0.073428676 sec
    Serialized Animalia (book): 5783 chars -> 3352.75 bytes (57.975964032509076%) in 0.070010854 sec
    Serialized International Atomic Time: 11976 chars -> 7037.375 bytes (58.762316299265194%) in 0.175122956 sec
    Serialized Altruism: 67373 chars -> 35498.0 bytes (52.6887625606697%) in 2.574359171 sec
    Serialized AutoRacing: 45 chars -> 19.5 bytes (43.333333333333336%) in 3.86223E-4 sec
    Serialized Ayn Rand: 89381 chars -> 45702.75 bytes (51.13251138385115%) in 4.17009173 sec
    Serialized Alain Connes: 6198 chars -> 3570.875 bytes (57.61334301387544%) in 0.075877032 sec
    Serialized Allan Dwan: 11264 chars -> 6914.875 bytes (61.38916015625%) in 0.174121711 sec
    Serialized Algeria/People: 57 chars -> 21.25 bytes (37.280701754385966%) in 4.18489E-4 sec
    Serialized Algeria/Transnational Issues: 62 chars -> 23.125 bytes (37.29838709677419%) in 4.29245E-4 sec
    Serialized Algeria: 120072 chars -> 67837.0 bytes (56.496935172229996%) in 7.258915589 sec
    Serialized List of Atlas Shrugged characters: 31725 chars -> 11373.5 bytes (35.85027580772262%) in 0.664165906 sec
    Serialized Topics of note in Atlas Shrugged: 28 chars -> 9.25 bytes (33.035714285714285%) in 1.936E-4 sec
    Serialized Anthropology: 92134 chars -> 31869.5 bytes (34.59037923025159%) in 4.148888616 sec
    Serialized Agricultural science: 13822 chars -> 4801.625 bytes (34.73900303863406%) in 0.191512957 sec
    Serialized Alchemy: 81528 chars -> 28145.75 bytes (34.522801982141104%) in 3.32866789 sec
    Serialized Air Transport: 22 chars -> 8.125 bytes (36.93181818181818%) in 1.57911E-4 sec
    Serialized Alien: 3799 chars -> 1270.375 bytes (33.439720979205056%) in 0.033598404 sec
    Serialized Astronomer: 8253 chars -> 2915.5 bytes (35.326547921967766%) in 0.089290678 sec
    Serialized Ameboid stage: 20 chars -> 6.25 bytes (31.25%) in 1.672E-4 sec
    Serialized ASCII: 100765 chars -> 69706.875 bytes (69.17766585620006%) in 5.340972945 sec
    Serialized Ashmore And Cartier Islands: 72 chars -> 29.0 bytes (40.27777777777778%) in 6.23334E-4 sec
    Serialized Austin (disambiguation): 2073 chars -> 1010.375 bytes (48.73974915581283%) in 0.020444358 sec
    Serialized Animation: 56003 chars -> 30454.25 bytes (54.37967608878096%) in 1.826876321 sec
    Serialized Apollo: 126677 chars -> 79345.75 bytes (62.636271777828654%) in 7.851696374 sec
    Serialized Andre Agassi: 108263 chars -> 66490.125 bytes (61.41537274969288%) in 5.913377818 sec
    
```

For reference, we encode some sample articles that are NOT in the dictionary:

Code from [TrieDemo.java:757](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L757) executed in 10.66 seconds: 
```java
      PPMCodec codec = tree.getCodec();
      WikiArticle.ENGLISH.load().skip(100).limit(10).forEach(article -> {
          TimedResult<Bits> compressed = TimedResult.time(()->codec.encodePPM(article.getText(), 4));
          System.out.println(String.format("Serialized %s: %s chars -> %s bytes (%s%%) in %s sec",
                  article.getTitle(), article.getText().length(), compressed.obj.bitLength / 8.0,
                  compressed.obj.bitLength * 100.0 / (8.0 * article.getText().length()),
                  compressed.timeNanos / 1000000000.0));
      });
```
Logging: 
```
    Serialized Artificial languages: 78 chars -> 38.25 bytes (49.03846153846154%) in 7.39689E-4 sec
    Serialized Austroasiatic languages: 27408 chars -> 16476.5 bytes (60.11565966141273%) in 0.579540696 sec
    Serialized Afro-asiatic languages: 66 chars -> 22.75 bytes (34.46969696969697%) in 5.02089E-4 sec
    Serialized Afroasiatic languages: 47485 chars -> 33503.125 bytes (70.55517531852163%) in 1.439411782 sec
    Serialized Andorra: 61684 chars -> 34052.125 bytes (55.20414532131509%) in 2.13479716 sec
    Serialized Andorra/Transnational issues: 103 chars -> 44.625 bytes (43.3252427184466%) in 8.51155E-4 sec
    Serialized Arithmetic mean: 11367 chars -> 5877.5 bytes (51.70669481833377%) in 0.154555397 sec
    Serialized American Football Conference: 12135 chars -> 7366.125 bytes (60.70148331273177%) in 0.183935667 sec
    Serialized Albert Gore: 42 chars -> 26.875 bytes (63.98809523809524%) in 4.42445E-4 sec
    Serialized AnEnquiryConcerningHumanUnderstanding: 76 chars -> 27.375 bytes (36.01973684210526%) in 5.40712E-4 sec
    
```

