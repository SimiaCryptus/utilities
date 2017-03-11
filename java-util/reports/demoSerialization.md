This will demonstrate how to serialize a CharTrie class in compressed format

First, we load some data into an index:
Code: 
```java
      charTrieIndex.addDocument(article.getText());
```
Returns: 
```
    com.simiacryptus.util.text.CharTrieIndex@358a2322
```
Logging: 
```
    Indexing 1978 KB of documents
    
```


Then, we compress the tree:
Code: 
```java
          tree.getMemorySize(), bytes.length, 100 - (bytes.length * 100.0 / tree.getMemorySize())));
```
Returns: 
```
    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... and 1246708 more bytes
```
Logging: 
```
    33554432 in ram, 938101 bytes in serialized form, 97.20424115657806% compression
    
```


And decompress to verify:
Code: 
```java
  
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
Code: 
```java
      TimedResult<Bits> compressed = TimedResult.time(()->codec.encodePPM(article.getText(), 4));
      System.out.println(String.format("Serialized %s: %s chars -> %s bytes (%s%%) in %s sec",
              article.getTitle(), article.getText().length(), compressed.obj.bitLength * 100.0 / 8.0,
              compressed.obj.bitLength / (8.0 * article.getText().length()),
              compressed.timeNanos / 1000000000.0));
      return compressed.obj.getBytes();
```
Returns: 
```
    Compressed 1978 KB of documents -> 1599 KB (916 dictionary + 683 ppm)
```
Logging: 
```
    Serialized AccessibleComputing: 69 chars -> 2425.0 bytes (0.35144927536231885%) in 4.06756E-4 sec
    Serialized Anarchism: 186232 chars -> 6497337.5 bytes (0.34888405322393573%) in 35.189800868 sec
    Serialized AfghanistanHistory: 57 chars -> 2200.0 bytes (0.38596491228070173%) in 3.51022E-4 sec
    Serialized AfghanistanGeography: 59 chars -> 2162.5 bytes (0.3665254237288136%) in 3.28044E-4 sec
    Serialized AfghanistanPeople: 62 chars -> 2275.0 bytes (0.36693548387096775%) in 3.45644E-4 sec
    Serialized AfghanistanCommunications: 64 chars -> 2400.0 bytes (0.375%) in 3.432E-4 sec
    Serialized AfghanistanTransportations: 79 chars -> 2737.5 bytes (0.34651898734177217%) in 4.01378E-4 sec
    Serialized AfghanistanMilitary: 54 chars -> 1962.5 bytes (0.36342592592592593%) in 2.67911E-4 sec
    Serialized AfghanistanTransnationalIssues: 67 chars -> 2487.5 bytes (0.3712686567164179%) in 3.46623E-4 sec
    Serialized AssistiveTechnology: 55 chars -> 2075.0 bytes (0.37727272727272726%) in 2.93822E-4 sec
    Serialized AmoeboidTaxa: 41 chars -> 1537.5 bytes (0.375%) in 2.244E-4 sec
    Serialized Autism: 149779 chars -> 5082450.0 bytes (0.33932994612061773%) in 22.907184642 sec
    Serialized AlbaniaHistory: 53 chars -> 2100.0 bytes (0.39622641509433965%) in 3.82312E-4 sec
    Serialized AlbaniaPeople: 58 chars -> 2187.5 bytes (0.3771551724137931%) in 3.24622E-4 sec
    Serialized AsWeMayThink: 50 chars -> 1662.5 bytes (0.3325%) in 2.35644E-4 sec
    Serialized AlbaniaGovernment: 54 chars -> 2075.0 bytes (0.38425925925925924%) in 2.78178E-4 sec
    Serialized AlbaniaEconomy: 53 chars -> 2050.0 bytes (0.3867924528301887%) in 2.90889E-4 sec
    Serialized Albedo: 35354 chars -> 1202337.5 bytes (0.34008528030774454%) in 1.427916048 sec
    Serialized AfroAsiaticLanguages: 56 chars -> 1987.5 bytes (0.3549107142857143%) in 3.02622E-4 sec
    Serialized ArtificalLanguages: 142 chars -> 4650.0 bytes (0.3274647887323944%) in 7.27955E-4 sec
    Serialized AbacuS: 41 chars -> 1587.5 bytes (0.3871951219512195%) in 2.11689E-4 sec
    Serialized AbalonE: 42 chars -> 1537.5 bytes (0.36607142857142855%) in 2.69378E-4 sec
    Serialized AbbadideS: 50 chars -> 1725.0 bytes (0.345%) in 2.39555E-4 sec
    Serialized AbbesS: 41 chars -> 1612.5 bytes (0.3932926829268293%) in 2.17067E-4 sec
    Serialized AbbevilleFrance: 44 chars -> 1650.0 bytes (0.375%) in 2.728E-4 sec
    Serialized AbbeY: 40 chars -> 1450.0 bytes (0.3625%) in 1.90666E-4 sec
    Serialized AbboT: 40 chars -> 1450.0 bytes (0.3625%) in 1.89689E-4 sec
    Serialized Abbreviations: 44 chars -> 1700.0 bytes (0.38636363636363635%) in 3.17288E-4 sec
    Serialized AtlasShrugged: 50 chars -> 1775.0 bytes (0.355%) in 2.49333E-4 sec
    Serialized ArtificialLanguages: 55 chars -> 2112.5 bytes (0.3840909090909091%) in 2.98223E-4 sec
    Serialized AtlasShruggedCharacters: 68 chars -> 2500.0 bytes (0.36764705882352944%) in 3.48089E-4 sec
    Serialized AtlasShruggedCompanies: 49 chars -> 1825.0 bytes (0.37244897959183676%) in 2.97733E-4 sec
    Serialized AyersMusicPublishingCompany: 67 chars -> 2350.0 bytes (0.35074626865671643%) in 3.29511E-4 sec
    Serialized AfricanAmericanPeople: 52 chars -> 1987.5 bytes (0.38221153846153844%) in 2.63512E-4 sec
    Serialized AdolfHitler: 47 chars -> 1800.0 bytes (0.3829787234042553%) in 2.34178E-4 sec
    Serialized AbeceDarians: 46 chars -> 1662.5 bytes (0.36141304347826086%) in 2.23911E-4 sec
    Serialized AbeL: 48 chars -> 1850.0 bytes (0.3854166666666667%) in 2.53244E-4 sec
    Serialized AbensbergGermany: 44 chars -> 1587.5 bytes (0.36079545454545453%) in 2.06311E-4 sec
    Serialized AberdeenSouthDakota: 57 chars -> 2037.5 bytes (0.3574561403508772%) in 2.80622E-4 sec
    Serialized ArthurKoestler: 50 chars -> 1775.0 bytes (0.355%) in 2.48845E-4 sec
    Serialized AynRand: 43 chars -> 1637.5 bytes (0.3808139534883721%) in 2.15111E-4 sec
    Serialized AlexanderTheGreat: 53 chars -> 2087.5 bytes (0.3938679245283019%) in 2.75245E-4 sec
    Serialized AnchorageAlaska: 51 chars -> 1887.5 bytes (0.3700... and 5689 more bytes
```
For reference, we encode some sample articles that are NOT in the dictionary:
Code: 
```java
      TimedResult<Bits> compressed = TimedResult.time(()->codec.encodePPM(article.getText(), 4));
      System.out.println(String.format("Serialized %s: %s chars -> %s bytes (%s%%) in %s sec",
              article.getTitle(), article.getText().length(), compressed.obj.bitLength * 100.0 / 8.0,
              compressed.obj.bitLength / (8.0 * article.getText().length()),
              compressed.timeNanos / 1000000000.0));
```
Logging: 
```
    Serialized Artificial languages: 78 chars -> 3175.0 bytes (0.40705128205128205%) in 4.93289E-4 sec
    Serialized Austroasiatic languages: 27408 chars -> 1773637.5 bytes (0.6471240148861647%) in 0.570456651 sec
    Serialized Afro-asiatic languages: 66 chars -> 2450.0 bytes (0.3712121212121212%) in 3.50044E-4 sec
    Serialized Afroasiatic languages: 47485 chars -> 3414287.5 bytes (0.719024428766979%) in 1.322545813 sec
    Serialized Andorra: 61684 chars -> 3400525.0 bytes (0.5512815316775825%) in 2.05837795 sec
    Serialized Andorra/Transnational issues: 103 chars -> 5662.5 bytes (0.5497572815533981%) in 7.90534E-4 sec
    Serialized Arithmetic mean: 11367 chars -> 634262.5 bytes (0.557985836192487%) in 0.133811839 sec
    Serialized American Football Conference: 12135 chars -> 712637.5 bytes (0.5872579316028018%) in 0.152079175 sec
    Serialized Albert Gore: 42 chars -> 2662.5 bytes (0.6339285714285714%) in 3.47112E-4 sec
    Serialized AnEnquiryConcerningHumanUnderstanding: 76 chars -> 3162.5 bytes (0.4161184210526316%) in 4.47822E-4 sec
    Serialized Animal Farm: 69803 chars -> 3860200.0 bytes (0.5530134807959544%) in 2.594378685 sec
    Serialized Amphibian: 130679 chars -> 7375950.0 bytes (0.5644326938528761%) in 8.229136245 sec
    Serialized Albert Arnold Gore/Criticisms: 21 chars -> 1512.5 bytes (0.7202380952380952%) in 2.36133E-4 sec
    Serialized Alaska: 141209 chars -> 7712987.5 bytes (0.5462107585210575%) in 9.346242609 sec
    Serialized Auteur Theory Film: 20 chars -> 1412.5 bytes (0.70625%) in 1.77956E-4 sec
    Serialized Agriculture: 108723 chars -> 5674325.0 bytes (0.5219065883023831%) in 5.650997962 sec
    Serialized Aldous Huxley: 49379 chars -> 2753450.0 bytes (0.5576155855728143%) in 1.368187507 sec
    Serialized Abstract Algebra: 61 chars -> 2662.5 bytes (0.4364754098360656%) in 3.54933E-4 sec
    Serialized Ada: 3353 chars -> 203825.0 bytes (0.6078884580972264%) in 0.030513515 sec
    Serialized Aberdeen (disambiguation): 6684 chars -> 354025.0 bytes (0.5296603830041892%) in 0.066018586 sec
    
```