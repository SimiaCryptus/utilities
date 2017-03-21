This will demonstrate how to serialize a CharTrie class in compressed format


First, we load some data into an index:

Code from [TrieDemo.java:542](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L542) executed in 30.14 seconds: 
```java
      CharTrieIndex charTrieIndex = new CharTrieIndex();
      WikiArticle.load().limit(100).forEach(article -> {
          charTrieIndex.addDocument(article.getText());
      });
      System.out.println(String.format("Indexing %s bytes of documents",
              charTrieIndex.getIndexedSize()));
      charTrieIndex.index(6, 0);
      return charTrieIndex;
```
Returns: 
```
    com.simiacryptus.util.text.CharTrieIndex@bf6b7623
```
Logging: 
```
    Indexing 1994559 bytes of documents
    
```



Then, we compress the tree:

Code from [TrieDemo.java:555](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L555) executed in 13.63 seconds: 
```java
      byte[] bytes = new FullTrieSerializer().serialize(tree.copy());
      byte[] bytes2 = CompressionUtil.encodeLZ(bytes); // Demonstrate compression results with standard LZ post-filter
      System.out.println(String.format("%s in ram, %s bytes in serialized form, %s%% compression",
              tree.getMemorySize(), bytes2.length, 100 - (bytes2.length * 100.0 / tree.getMemorySize())));
      return Base64.getEncoder().encodeToString(bytes2);
```
Returns: 
```
    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... and 1009800 more bytes
```
Logging: 
```
    50331648 in ram, 769637 bytes in serialized form, 98.47086866696675% compression
    
```



And decompress to verify:

Code from [TrieDemo.java:564](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L564) executed in 16.73 seconds: 
```java
      byte[] bytes = CompressionUtil.decodeLZ(Base64.getDecoder().decode(serialized));
      CharTrie restored = new FullTrieSerializer().deserialize(bytes);
      boolean verified = restored.root().equals(tree.root());
      System.out.println(String.format("Tree Verified: %s", verified));
      return bytes.length;
```
Returns: 
```
    1113855
```
Logging: 
```
    Tree Verified: true
    
```

Then, we encode the data used to create the dictionary:

Code from [TrieDemo.java:573](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L573) executed in 171.43 seconds: 
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
    Compressed 1947 KB of documents -> 2062 KB (1087 dictionary + 974 ppm)
```
Logging: 
```
    Serialized AccessibleComputing: 69 chars -> 22.0 bytes (31.884057971014492%) in 0.005494134 sec
    Serialized Anarchism: 186232 chars -> 102826.625 bytes (55.21426231796899%) in 15.814950364 sec
    Serialized AfghanistanHistory: 57 chars -> 20.25 bytes (35.526315789473685%) in 6.96667E-4 sec
    Serialized AfghanistanGeography: 59 chars -> 21.5 bytes (36.440677966101696%) in 4.35111E-4 sec
    Serialized AfghanistanPeople: 62 chars -> 22.375 bytes (36.08870967741935%) in 3.90622E-4 sec
    Serialized AfghanistanCommunications: 64 chars -> 21.875 bytes (34.1796875%) in 3.96489E-4 sec
    Serialized AfghanistanTransportations: 79 chars -> 39.5 bytes (50.0%) in 5.96445E-4 sec
    Serialized AfghanistanMilitary: 54 chars -> 17.875 bytes (33.101851851851855%) in 3.12889E-4 sec
    Serialized AfghanistanTransnationalIssues: 67 chars -> 23.75 bytes (35.44776119402985%) in 4.35112E-4 sec
    Serialized AssistiveTechnology: 55 chars -> 21.875 bytes (39.77272727272727%) in 3.83777E-4 sec
    Serialized AmoeboidTaxa: 41 chars -> 13.625 bytes (33.23170731707317%) in 2.93333E-4 sec
    Serialized Autism: 149779 chars -> 87026.5 bytes (58.10327215430735%) in 10.441041948 sec
    Serialized AlbaniaHistory: 53 chars -> 19.5 bytes (36.79245283018868%) in 4.60534E-4 sec
    Serialized AlbaniaPeople: 58 chars -> 21.875 bytes (37.71551724137931%) in 3.46622E-4 sec
    Serialized AsWeMayThink: 50 chars -> 33.625 bytes (67.25%) in 4.60533E-4 sec
    Serialized AlbaniaGovernment: 54 chars -> 20.75 bytes (38.425925925925924%) in 3.22178E-4 sec
    Serialized AlbaniaEconomy: 53 chars -> 18.25 bytes (34.43396226415094%) in 2.95777E-4 sec
    Serialized Albedo: 35354 chars -> 19346.5 bytes (54.72223793630141%) in 0.811166503 sec
    Serialized AfroAsiaticLanguages: 56 chars -> 19.0 bytes (33.92857142857143%) in 3.53956E-4 sec
    Serialized ArtificalLanguages: 142 chars -> 45.375 bytes (31.954225352112676%) in 8.13022E-4 sec
    Serialized AbacuS: 41 chars -> 12.5 bytes (30.48780487804878%) in 2.21956E-4 sec
    Serialized AbalonE: 42 chars -> 13.75 bytes (32.73809523809524%) in 2.332E-4 sec
    Serialized AbbadideS: 50 chars -> 16.25 bytes (32.5%) in 2.73289E-4 sec
    Serialized AbbesS: 41 chars -> 12.5 bytes (30.48780487804878%) in 2.13155E-4 sec
    Serialized AbbevilleFrance: 44 chars -> 14.125 bytes (32.10227272727273%) in 3.79378E-4 sec
    Serialized AbbeY: 40 chars -> 13.0 bytes (32.5%) in 2.288E-4 sec
    Serialized AbboT: 40 chars -> 13.125 bytes (32.8125%) in 2.35156E-4 sec
    Serialized Abbreviations: 44 chars -> 14.75 bytes (33.52272727272727%) in 2.55689E-4 sec
    Serialized AtlasShrugged: 50 chars -> 15.875 bytes (31.75%) in 2.74266E-4 sec
    Serialized ArtificialLanguages: 55 chars -> 18.125 bytes (32.95454545454545%) in 2.99689E-4 sec
    Serialized AtlasShruggedCharacters: 68 chars -> 23.75 bytes (34.9264705882353%) in 3.916E-4 sec
    Serialized AtlasShruggedCompanies: 49 chars -> 15.0 bytes (30.612244897959183%) in 2.87467E-4 sec
    Serialized AyersMusicPublishingCompany: 67 chars -> 20.125 bytes (30.03731343283582%) in 3.62756E-4 sec
    Serialized AfricanAmericanPeople: 52 chars -> 18.0 bytes (34.61538461538461%) in 2.98711E-4 sec
    Serialized AdolfHitler: 47 chars -> 14.125 bytes (30.0531914893617%) in 2.46889E-4 sec
    Serialized AbeceDarians: 46 chars -> 14.875 bytes (32.33695652173913%) in 2.78667E-4 sec
    Serialized AbeL: 48 chars -> 16.5 bytes (34.375%) in 2.64489E-4 sec
    Serialized AbensbergGermany: 44 chars -> 13.875 bytes (31.53409090909091%) in 2.48844E-4 sec
    Serialized AberdeenSouthDakota: 57 chars -> 18.125 bytes (31.79824561403509%) in 3.39289E-4 sec
    Serialized ArthurKoestler: 50 chars -> 15.75 bytes (31.5%) in 2.74267E-4 sec
    Serialized AynRand: 43 chars -> 13.125 bytes (30.523255813953487%) in 2.23911E-4 sec
    Serialized AlexanderTheGreat: 53 chars -> 17.25 bytes (32.54716981132076%) in 3.83289E-4 sec
    Serialized AnchorageAlaska: 51 chars -> 15.25 bytes (29.901960784313726%) in 3.61778E-4 sec
    Serialized ArgumentForms: 47 chars -> 15.0 bytes (31.914893617021278%) in 3.33422E-4 sec
    Serialized ArgumentsForTheExistenceOfGod: 50 chars -> 17.0 bytes (34.0%) in 3.71555E-4 sec
    Serialized AnarchY: 43 chars -> 13.625 bytes (31.686046511627907%) in 3.12889E-4 sec
    Serialized AsciiArt: 43 chars -> 12.125 bytes (28.197674418604652%) in 2.91867E-4 sec
    Serialized AcademyAwards: 48 chars -> 15.375 bytes (32.03125%) in 3.45156E-4 sec
    Serialized AcademyAwards/BestPicture: 96 chars -> 33.625 bytes (35.026041666666664%) in 6.80533E-4 sec
    Serialized AustriaLanguage: 50 chars -> 16.875 bytes (33.75%) in 2.77689E-4 sec
    Serialized AcademicElitism: 45 chars -> 13.375 bytes (29.72222222222222%) in 2.98711E-4 sec
    Serialized AxiomOfChoice: 49 chars -> 14.875 bytes (30.357142857142858%) in 2.57156E-4 sec
    Serialized AmericanFootball: 51 chars -> 15.75 bytes (30.88235294117647%) in 2.86E-4 sec
    Serialized AnnaKournikova: 50 chars -> 15.75 bytes (31.5%) in 2.80623E-4 sec
    Serialized AndorrA: 42 chars -> 13.5 bytes (32.142857142857146%) in 2.27333E-4 sec
    Serialized AustroAsiaticLanguages: 57 chars -> 18.375 bytes (32.23684210526316%) in 3.05555E-4 sec
    Serialized ActresseS: 62 chars -> 20.5 bytes (33.064516129032256%) in 3.42711E-4 sec
    Serialized A: 19452 chars -> 6671.75 bytes (34.29852971416821%) in 0.48862984 sec
    Serialized AnarchoCapitalism: 52 chars -> 17.0 bytes (32.69230769230769%) in 3.124E-4 sec
    Serialized AnarchoCapitalists: 52 chars -> 17.0 bytes (32.69230769230769%) in 2.86489E-4 sec
    Serialized ActressesS: 50 chars -> 18.0 bytes (36.0%) in 2.96266E-4 sec
    Serialized AnAmericanInParis: 54 chars -> 19.375 bytes (35.879629629629626%) in 3.03111E-4 sec
    Serialized AutoMorphism: 46 chars -> 14.875 bytes (32.33695652173913%) in 2.43466E-4 sec
    Serialized ActionFilm: 45 chars -> 14.125 bytes (31.38888888888889%) in 2.57155E-4 sec
    Serialized Alabama: 168088 chars -> 57842.25 bytes (34.411885440959495%) in 28.011115868 sec
    Serialized AfricA: 41 chars -> 12.875 bytes (31.402439024390244%) in 2.51778E-4 sec
    Serialized Achilles: 51211 chars -> 18248.5 bytes (35.633945831950165%) in 2.818478535 sec
    Serialized AppliedStatistics: 44 chars -> 14.875 bytes (33.80681818181818%) in 2.62044E-4 sec
    Serialized Abraham Lincoln: 179104 chars -> 63365.0 bytes (35.37888601036269%) in 32.012181577 sec
    Serialized Aristotle: 110077 chars -> 55700.0 bytes (50.60094297628024%) in 5.818380828 sec
    Serialized An American in Paris: 15603 chars -> 9138.5 bytes (58.56886496186631%) in 0.237682652 sec
    Serialized Academy Award for Best Production Design: 86370 chars -> 54488.5 bytes (63.08729883061248%) in 3.914386675 sec
    Serialized Academy Awards: 78000 chars -> 44532.125 bytes (57.092467948717946%) in 3.663356865 sec
    Serialized Action Film: 56 chars -> 19.875 bytes (35.49107142857143%) in 3.44178E-4 sec
    Serialized Actrius: 5763 chars -> 3565.375 bytes (61.8666493145931%) in 0.069485298 sec
    Serialized Animalia (book): 5783 chars -> 3283.625 bytes (56.78065018156666%) in 0.067607964 sec
    Serialized International Atomic Time: 11976 chars -> 7049.125 bytes (58.86042919171677%) in 0.182645001 sec
    Serialized Altruism: 67373 chars -> 35369.75 bytes (52.49840440532557%) in 2.502815162 sec
    Serialized AutoRacing: 45 chars -> 18.5 bytes (41.111111111111114%) in 4.74711E-4 sec
    Serialized Ayn Rand: 89381 chars -> 45458.375 bytes (50.859103165102205%) in 3.957967703 sec
    Serialized Alain Connes: 6198 chars -> 3540.875 bytes (57.12931590835753%) in 0.066815475 sec
    Serialized Allan Dwan: 11264 chars -> 6858.0 bytes (60.88423295454545%) in 0.161426732 sec
    Serialized Algeria/People: 57 chars -> 22.5 bytes (39.473684210526315%) in 3.57378E-4 sec
    Serialized Algeria/Transnational Issues: 62 chars -> 23.875 bytes (38.50806451612903%) in 3.65689E-4 sec
    Serialized Algeria: 120072 chars -> 41429.625 bytes (34.50398510893464%) in 15.059052756 sec
    Serialized List of Atlas Shrugged characters: 31725 chars -> 11603.375 bytes (36.574862096138695%) in 1.175456727 sec
    Serialized Topics of note in Atlas Shrugged: 28 chars -> 9.625 bytes (34.375%) in 2.23422E-4 sec
    Serialized Anthropology: 92134 chars -> 32527.125 bytes (35.30414939110426%) in 8.68162448 sec
    Serialized Agricultural science: 13822 chars -> 4875.125 bytes (35.27076399942121%) in 0.266290478 sec
    Serialized Alchemy: 81528 chars -> 28584.75 bytes (35.06126729467177%) in 6.886158785 sec
    Serialized Air Transport: 22 chars -> 8.0 bytes (36.36363636363637%) in 1.93111E-4 sec
    Serialized Alien: 3799 chars -> 1299.625 bytes (34.209660436957094%) in 0.041171294 sec
    Serialized Astronomer: 8253 chars -> 2956.375 bytes (35.821822367623895%) in 0.126287838 sec
    Serialized Ameboid stage: 20 chars -> 6.75 bytes (33.75%) in 1.31511E-4 sec
    Serialized ASCII: 100765 chars -> 71135.125 bytes (70.59507269389172%) in 5.416234466 sec
    Serialized Ashmore And Cartier Islands: 72 chars -> 28.5 bytes (39.583333333333336%) in 7.28445E-4 sec
    Serialized Austin (disambiguation): 2073 chars -> 1025.375 bytes (49.46333815726001%) in 0.018944447 sec
    Serialized Animation: 56003 chars -> 30542.875 bytes (54.53792653964966%) in 1.767738847 sec
    Serialized Apollo: 126677 chars -> 79998.375 bytes (63.15146001247267%) in 8.427340626 sec
    Serialized Andre Agassi: 108263 chars -> 66655.25 bytes (61.56789484865559%) in 5.950761689 sec
    
```

For reference, we encode some sample articles that are NOT in the dictionary:

Code from [TrieDemo.java:589](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L589) executed in 38.79 seconds: 
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
    Serialized Artificial languages: 78 chars -> 38.5 bytes (49.35897435897436%) in 6.26756E-4 sec
    Serialized Austroasiatic languages: 27408 chars -> 17042.25 bytes (62.179838003502624%) in 0.569128339 sec
    Serialized Afro-asiatic languages: 66 chars -> 23.25 bytes (35.22727272727273%) in 5.22622E-4 sec
    Serialized Afroasiatic languages: 47485 chars -> 34667.625 bytes (73.00752869327155%) in 1.394536666 sec
    Serialized Andorra: 61684 chars -> 33771.375 bytes (54.749002982945335%) in 2.075557997 sec
    Serialized Andorra/Transnational issues: 103 chars -> 44.25 bytes (42.96116504854369%) in 7.41645E-4 sec
    Serialized Arithmetic mean: 11367 chars -> 5997.875 bytes (52.76568135831794%) in 0.143648774 sec
    Serialized American Football Conference: 12135 chars -> 7367.625 bytes (60.71384425216316%) in 0.168300021 sec
    Serialized Albert Gore: 42 chars -> 28.875 bytes (68.75%) in 3.80355E-4 sec
    Serialized AnEnquiryConcerningHumanUnderstanding: 76 chars -> 28.125 bytes (37.00657894736842%) in 4.58578E-4 sec
    Serialized Animal Farm: 69803 chars -> 39139.875 bytes (56.071909516782945%) in 2.585186106 sec
    Serialized Amphibian: 130679 chars -> 71937.75 bytes (55.04920453936746%) in 8.069486136 sec
    Serialized Albert Arnold Gore/Criticisms: 21 chars -> 15.375 bytes (73.21428571428571%) in 2.244E-4 sec
    Serialized Alaska: 141209 chars -> 76891.375 bytes (54.452177269154234%) in 9.207644058 sec
    Serialized Auteur Theory Film: 20 chars -> 10.125 bytes (50.625%) in 1.62311E-4 sec
    Serialized Agriculture: 108723 chars -> 55784.25 bytes (51.30860075604978%) in 5.672826854 sec
    Serialized Aldous Huxley: 49379 chars -> 16928.125 bytes (34.28203284797181%) in 2.68178323 sec
    Serialized Abstract Algebra: 61 chars -> 19.875 bytes (32.58196721311475%) in 3.48089E-4 sec
    Serialized Ada: 3353 chars -> 1158.125 bytes (34.53996421115419%) in 0.031091382 sec
    Serialized Aberdeen (disambiguation): 6684 chars -> 2321.125 bytes (34.72658587672053%) in 0.081515877 sec
    
```

