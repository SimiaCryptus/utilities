This will demonstrate how to serialize a CharTrie class in compressed format

First, we load some data into an index:
Code: 
```java
  
```
Returns: 
```
    com.simiacryptus.util.text.CharTrieIndex@e163ea57
```
Logging: 
```
    Indexing 1978 KB of documents
    
```


Then, we compress the tree:
Code: 
```java
  
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
  
```
Returns: 
```
    Compressed 1978 KB of documents -> 1599 KB (916 dictionary + 683 ppm)
```
Logging: 
```
    Serialized AccessibleComputing: 69 chars -> 24.25 bytes (0.35144927536231885%) in 0.008784846 sec
    Serialized Anarchism: 186232 chars -> 64973.375 bytes (0.34888405322393573%) in 39.416824695 sec
    Serialized AfghanistanHistory: 57 chars -> 22.0 bytes (0.38596491228070173%) in 0.001114667 sec
    Serialized AfghanistanGeography: 59 chars -> 21.625 bytes (0.3665254237288136%) in 5.07956E-4 sec
    Serialized AfghanistanPeople: 62 chars -> 22.75 bytes (0.36693548387096775%) in 4.87422E-4 sec
    Serialized AfghanistanCommunications: 64 chars -> 24.0 bytes (0.375%) in 4.95734E-4 sec
    Serialized AfghanistanTransportations: 79 chars -> 27.375 bytes (0.34651898734177217%) in 6.46312E-4 sec
    Serialized AfghanistanMilitary: 54 chars -> 19.625 bytes (0.36342592592592593%) in 3.696E-4 sec
    Serialized AfghanistanTransnationalIssues: 67 chars -> 24.875 bytes (0.3712686567164179%) in 4.68356E-4 sec
    Serialized AssistiveTechnology: 55 chars -> 20.75 bytes (0.37727272727272726%) in 3.74489E-4 sec
    Serialized AmoeboidTaxa: 41 chars -> 15.375 bytes (0.375%) in 2.69866E-4 sec
    Serialized Autism: 149779 chars -> 50824.5 bytes (0.33932994612061773%) in 25.622648409 sec
    Serialized AlbaniaHistory: 53 chars -> 21.0 bytes (0.39622641509433965%) in 6.02312E-4 sec
    Serialized AlbaniaPeople: 58 chars -> 21.875 bytes (0.3771551724137931%) in 4.65911E-4 sec
    Serialized AsWeMayThink: 50 chars -> 16.625 bytes (0.3325%) in 3.47111E-4 sec
    Serialized AlbaniaGovernment: 54 chars -> 20.75 bytes (0.38425925925925924%) in 3.68133E-4 sec
    Serialized AlbaniaEconomy: 53 chars -> 20.5 bytes (0.3867924528301887%) in 4.79111E-4 sec
    Serialized Albedo: 35354 chars -> 12023.375 bytes (0.34008528030774454%) in 1.639297942 sec
    Serialized AfroAsiaticLanguages: 56 chars -> 19.875 bytes (0.3549107142857143%) in 4.18E-4 sec
    Serialized ArtificalLanguages: 142 chars -> 46.5 bytes (0.3274647887323944%) in 0.001021289 sec
    Serialized AbacuS: 41 chars -> 15.875 bytes (0.3871951219512195%) in 3.05066E-4 sec
    Serialized AbalonE: 42 chars -> 15.375 bytes (0.36607142857142855%) in 2.92355E-4 sec
    Serialized AbbadideS: 50 chars -> 17.25 bytes (0.345%) in 3.44177E-4 sec
    Serialized AbbesS: 41 chars -> 16.125 bytes (0.3932926829268293%) in 3.00667E-4 sec
    Serialized AbbevilleFrance: 44 chars -> 16.5 bytes (0.375%) in 3.11911E-4 sec
    Serialized AbbeY: 40 chars -> 14.5 bytes (0.3625%) in 3.58356E-4 sec
    Serialized AbboT: 40 chars -> 14.5 bytes (0.3625%) in 2.73778E-4 sec
    Serialized Abbreviations: 44 chars -> 17.0 bytes (0.38636363636363635%) in 3.22666E-4 sec
    Serialized AtlasShrugged: 50 chars -> 17.75 bytes (0.355%) in 3.47111E-4 sec
    Serialized ArtificialLanguages: 55 chars -> 21.125 bytes (0.3840909090909091%) in 4.18E-4 sec
    Serialized AtlasShruggedCharacters: 68 chars -> 25.0 bytes (0.36764705882352944%) in 5.35822E-4 sec
    Serialized AtlasShruggedCompanies: 49 chars -> 18.25 bytes (0.37244897959183676%) in 3.55422E-4 sec
    Serialized AyersMusicPublishingCompany: 67 chars -> 23.5 bytes (0.35074626865671643%) in 4.25823E-4 sec
    Serialized AfricanAmericanPeople: 52 chars -> 19.875 bytes (0.38221153846153844%) in 3.13867E-4 sec
    Serialized AdolfHitler: 47 chars -> 18.0 bytes (0.3829787234042553%) in 3.15334E-4 sec
    Serialized AbeceDarians: 46 chars -> 16.625 bytes (0.36141304347826086%) in 3.18756E-4 sec
    Serialized AbeL: 48 chars -> 18.5 bytes (0.3854166666666667%) in 3.46133E-4 sec
    Serialized AbensbergGermany: 44 chars -> 15.875 bytes (0.36079545454545453%) in 3.18267E-4 sec
    Serialized AberdeenSouthDakota: 57 chars -> 20.375 bytes (0.3574561403508772%) in 5.12844E-4 sec
    Serialized ArthurKoestler: 50 chars -> 17.75 bytes (0.355%) in 4.34622E-4 sec
    Serialized AynRand: 43 chars -> 16.375 bytes (0.3808139534883721%) in 4.18977E-4 sec
    Serialized AlexanderTheGreat: 53 chars -> 20.875 bytes (0.3938679245283019%) in 3.83778E-4 sec
    Serialized AnchorageAlaska: 51 chars -> 18.875 bytes (0.3700980392156863%) in 3.39289E-4 sec
 ... and 5618 more bytes
```
For reference, we encode some sample articles that are NOT in the dictionary:
Code: 
```java
  
```
Logging: 
```
    Serialized Artificial languages: 78 chars -> 31.75 bytes (0.40705128205128205%) in 6.18933E-4 sec
    Serialized Austroasiatic languages: 27408 chars -> 17736.375 bytes (0.6471240148861647%) in 0.628197369 sec
    Serialized Afro-asiatic languages: 66 chars -> 24.5 bytes (0.3712121212121212%) in 4.312E-4 sec
    Serialized Afroasiatic languages: 47485 chars -> 34142.875 bytes (0.719024428766979%) in 1.523926504 sec
    Serialized Andorra: 61684 chars -> 34005.25 bytes (0.5512815316775825%) in 2.280210334 sec
    Serialized Andorra/Transnational issues: 103 chars -> 56.625 bytes (0.5497572815533981%) in 9.988E-4 sec
    Serialized Arithmetic mean: 11367 chars -> 6342.625 bytes (0.557985836192487%) in 0.162513043 sec
    Serialized American Football Conference: 12135 chars -> 7126.375 bytes (0.5872579316028018%) in 0.187922557 sec
    Serialized Albert Gore: 42 chars -> 26.625 bytes (0.6339285714285714%) in 4.67378E-4 sec
    Serialized AnEnquiryConcerningHumanUnderstanding: 76 chars -> 31.625 bytes (0.4161184210526316%) in 6.336E-4 sec
    Serialized Animal Farm: 69803 chars -> 38602.0 bytes (0.5530134807959544%) in 2.815862002 sec
    Serialized Amphibian: 130679 chars -> 73759.5 bytes (0.5644326938528761%) in 8.770334847 sec
    Serialized Albert Arnold Gore/Criticisms: 21 chars -> 15.125 bytes (0.7202380952380952%) in 2.2E-4 sec
    Serialized Alaska: 141209 chars -> 77129.875 bytes (0.5462107585210575%) in 10.018337316 sec
    Serialized Auteur Theory Film: 20 chars -> 14.125 bytes (0.70625%) in 1.85778E-4 sec
    Serialized Agriculture: 108723 chars -> 56743.25 bytes (0.5219065883023831%) in 6.190050342 sec
    Serialized Aldous Huxley: 49379 chars -> 27534.5 bytes (0.5576155855728143%) in 1.56378611 sec
    Serialized Abstract Algebra: 61 chars -> 26.625 bytes (0.4364754098360656%) in 4.15067E-4 sec
    Serialized Ada: 3353 chars -> 2038.25 bytes (0.6078884580972264%) in 0.037906494 sec
    Serialized Aberdeen (disambiguation): 6684 chars -> 3540.25 bytes (0.5296603830041892%) in 0.083205966 sec
    
```
