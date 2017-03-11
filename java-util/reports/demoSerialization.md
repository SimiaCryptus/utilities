This will demonstrate how to serialize a CharTrie class in compressed format


First, we load some data into an index:

Code from [CompressionTest:14](../src/test/java/com/simiacryptus/util/text/CompressionTest.java#L14): 
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
    com.simiacryptus.util.text.CharTrieIndex@1fba03d4
```
Logging: 
```
    Indexing 1564 KB of documents
    
```


Then, we compress the tree:

Code: 
```java
      byte[] bytes = new FullTrieSerializer().serialize(tree.copy());
      System.out.println(String.format("%s in ram, %s bytes in serialized form, %s%% compression",
              tree.getMemorySize(), bytes.length, 100 - (bytes.length * 100.0 / tree.getMemorySize())));
      return Base64.getEncoder().encodeToString(bytes);
```
Returns: 
```
    QIrAABAGQACkmhQAggADD1sAIUA1QAiQ+IACNANcAJCYAJUCrgAmQPUACdJMEAKELAAApQsEACpBlsAK0A2QAsTNrAC1GfMALk7hAAvSNCADBKEUAMUqrgAySBPADNELgANEO2AA1Q5NADZDcsAN0OXQA4Q8sADlFPAAOkNjQA7QSYADxFWYAPVB5wA+RVjAD9AXcAQAEAQUhkABCQuxAENEsoAREJlABFQpkAEZCYcAR0IKgBIQoZAElDO4ASkGYgBLQOvAExCuQATUMQgBOQj5AE9BeoAUEPGwBRQBxAFJCjkAU0UugBURD3AFVBawAVkDHwBXQgKAFhAI0AWUBKABaQFuAFtRF4AXEATQBdURfAF4KAF9A34AYAEAYYAAWzbAGJRK0AY2VDgBkZQOAGWAAIGDwBmWX4AGdU/QAaGxnABpgABTX8AakG1QBrRinAGx1BEAbVyUABugABN9sAb4AAULgAHBaH8AcUEUQBygABKe4Ac4AARKqAHSAAFpiQB1X1sAHZKcIAd02bQB4Q3OAHlRN4AekFvAB7RiFAHxR8gAfUYrgB+GgCjAQCnCACqAQCrAQCsEACwIQCxAwCyAQC1BQC2AQC3KwC6AQC7AQDAAgDBCQDCAgDDAgDEAgDFAwDGBADHBADJBgDVAQDWAQDXAwDcAwDfAQDgBwDhJgDiDADjIQDkHgDlAwDmHQDnKwDoEADpQCEAOoLAOsOAO0sAO4BAO8CAPADAPEFAPMgAPQBAPUDAPZAE4A+AIA+QEA+gsA/EAQwD+AQEABgEBFgECAgEDAQEEBAEFAwEHAQEMAgENAQESAQETCwEZAQEbAgEfAQEmAQEnAQErBgEwAQExBQFCAwFEBgFLAwFNAwFaBAFfBgFgAgFhBgFrAgF+AQHNAQHOAQHdAwHeAgHfAQHgAgHhAQH6AgH7AQIAAgIBAQICAgIDAQIiAQImAQInAQI6AQJQBwJRCgJSAQJUAQJZEQJbCQJfAQJhBAJjAgJqCwKBAQKDBgKKBAKMAQKSBgKUBAKVAQKdAQK7AgK/AgLIFQLMBgLQFQLkAgMAAgMBAgMDBAMVAQMnAQMpAQMvBAMxAQOGAQOKAQORAwOVAQObAQOcAQOgAgOkAQOpAQOsAwOtBQOuAwOvBQOxGAOyCAOzDAO0CAO1DAO2AQO3BwO4BwO5EgO6AwO7FwO8CgO9CgO+BAO/EQPABQPBBwPCEgPDAgPEBgPFBAPGAQPHCAPJBQPKAQPMBAPNAQQQAwQRAgQwBAQzAgQ1BAQ4AgQ6AgQ7AgQ8AwQ9AwQ+BQRABQRDAgRFAQRKAQROAQRPAwRWAQUxAQUyAQU1AQVAAgVIAQVOAQVSAgVhBQViBgVjAQVlBQV1BQV2AwV4BQWAAgWBAgWCBQWFAQWGAQWHAQXQAgXRAQXSAgXTAQXUAgXVAwXWAQXXAQXYAQXZAgXaAQXbAQXcAQXdAQXeAgXfAQXgAgXhAQXiAQXjAQXkAQXlAQXmAQXnAQXoAgXpAQXqAQYMBwYjAgYnCAYoBQYpBAYqAgYrAQYsAQYtBgYuAgYvBgYwAQYxBwYyAQYzAgY0AQY1AQY2BAY3AQY4AwY5BQY6AQZBAQZCAQZDBAZECQZFAgZGAwZHAwZIAwZKAgkFAQ4BAQ4VAg4aAQ4iAQ4jAw4oAQ4pAQ4qAQ4yAQ41AQ5AAg5MAhDQCBDRARDSAhDUARDVBBDXARDYBxDaAxDcAhDdARDeARDgAhDjARDkARDlARDoARDuARDvARaoAR0AAR17Ah2PAR4AAR4BAR4NAh4XAR4kAR4lAx5HAx5iAR5jAx5sAR5tAh6TAx6aAR6gAR6hAR6iAh6jAR6kAh6lAR6mAh6nAR6oAh6pAR6qAh6rAR6sAh6tAR6uAh6vAR6wAh6xAR6yAh6zAR60Ah61AR62Ah63AR8ABB8EBR8IBR8UAR8jAR8wAh80AR82AR9QAR9wAR/GAh/WASAQASATQQ+IBRAH0gGAIgGS8gHAggHQogIgQgJgYgswEhNQMhkhAhlAEiAAEiCAEiEi4iPAEiSAEiZAEiZQEmUgEmZgEn6AEn6QEoAQEsZQEsbQEsbwEscAEsgAEsgQEwCgEwCwExDAExIgIxKAFOCgFOjAFRawFSNgFTQQFbVwFbZgFlhwFmLwFrYwFsEQGJqgGKEwGK+gGLAgGPsgGX8wGxRAG4XQHBOAHC5AHG1AHIhQHYAALYAi3cQAHcQQHcQgHcQwHcRAHcRQHcRgHcRwHcSAHcSQHcSgHcSwHcTAHcTQHcTgHcTwHcUAHcUQHcUgHcUwHcVAHcVQHdAALdAQHdAgHdAwHdBAHdBQHdBgHdBwHdCAHdCQHdCgHdCwHdDAHdDQHdDgHdDwHdEAHdEQHdEgHdEwHdFAHdFQHfAAHfMAEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAJARFv16ATGSBEBjAFbOEAHAAkAFAAwAMAGACEDWBKCBi2hCMQJYCyA9ArgUwDoDkKaBGAkC+BLAZgtgQQMBiDRGJgKgLAtgcAwHJOgoAKAKAdAFgCwCYBMAaADABgCkAOAMgFQA0AWAZASQBYBAld795YAAAAAAAAGgs0DHqCzZoFfgDKiM66bYAcB0zQR8AMgbUDNlNMjFh743MSeGVDIhdQjECiAOC2imiAEgNSc7LNKRPvqGUEKMFgi2Xbldyx1bNoX094s6OqfXgUtFnFe6ZcYrSuZtDrIAbEkikrYxjBiAdB8rK3UMVRGliV3pLheIYHyLlB3rF07L8sTMOJ48+U6UhgaJyWMmAlEFQPxbvBJReFdxOILVCqPHQYwYbCQcFARhOYnXUAYMUM22zTHKANQRAAG8BFIEiQkACykagz/a3PJJP4AZsbASGkFJKyo2qwACiRjMGABtttttvLzwCwUcuS8mU79mHvQOuQsEAG4IQCIAAIQAEkQEwACahJboE0j9ABCAnABgdEHuAFCkieQAYA8AiAFABgAwAYBMAuADATgDgwQAoupBfgNwVwUQEwHw2QXQbwGwMghgfgOgNgfQRwrQiQNQKhVQPAkI6B8QuwXQsgtA+OUFISwZgLAWCUJYLQaiEjPGUtwVBMhIFAY1p5gAAACgAAAAAAAAAAAEQAAAAAAEQAAAACgAAAAAAAAASAAAAAACQAAAAAAAAAAALAAAAAGRsAEBEGgsBg8BQGBQSGAIRBIHBILEQkNEwKFhoKEhUHERIUGEIogoiCgwiEQ4MFDI5GCRVgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAwAAEaZJHKk1QAAAAAKAAAADQBkkAQaIFoJF8xdAkEhkCQWAhFKNE74AAAAAAAABkmCKgAAAAgKgJAKLcAUAYFCA0AoBQBQHgNAKAMAYBACBkBYKwXgHBiAUB4NECIC0+g9jloyfnxZ1oBdD64DAWEEKBzAMg0QBo4sA+GXADQB4AhABgAoAMAKBbAbAvgCiIdAbgZQIYEsCuAhAYgXQFQGgTQKICkEECiBgMQTYOsBcBYCcegSGI1aEQQhQCQTQMwNwKwFQDQMQCwFQNwIwOwIwKoE8bZXBYXC4tBRMvHx4EEAACkxCLQkhGEJaOkJEBpAkFCgAAJAAAAMCgBEARCQAKAAAAAAAAAAACQASAAAAAAAAAAAAAAABoAPIASBCgwJgDBSgBIAYCQAYAMBCLTDwgGQB4CYBYB4BYAyAYIX4IYRIEYCoF4EIIoP4C4JQLwVQOwJwSIFIIILIP4CwGwFB0NgIg4M4wwqh3EGDMDoYQFgVDyH0SRzJ0BQ/LtXjIeyjRoNckBIwAAAAAAAAAAAAAAAAAAAAAAUAAAAAABQAAAAAAAAAACgAAAAAAAAAAAAAAAUAA0KCX8gDgZAOBiAOAQoqgBmegLAggDAFAKAMA4DQbg/KSAQEAQBAKAIgCAgCjbGAYCAMAQgCQBACBPYwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAYAAAAG... and 1072592 more bytes
```
Logging: 
```
    16777216 in ram, 807516 bytes in serialized form, 95.18682956695557% compression
    
```


And decompress to verify:

Code: 
```java
      byte[] bytes = Base64.getDecoder().decode(serialized);
      CharTrie restored = new FullTrieSerializer().deserialize(bytes);
      boolean verified = restored.root().equals(tree.root());
      System.out.println(String.format("Tree Verified: %s", verified));
      return bytes.length;
```
Returns: 
```
    807516
```
Logging: 
```
    Tree Verified: true
    
```
Then, we encode the data used to create the dictionary:

Code: 
```java
      PPMCodec codec = tree.getCodec();
      int totalSize = WikiArticle.load().limit(100).map(article -> {
          TimedResult<Bits> compressed = TimedResult.time(()->codec.encodePPM(article.getText(), 4));
          System.out.println(String.format("Serialized %s: %s chars -> %s bytes (%s%%) in %s sec",
                  article.getTitle(), article.getText().length(), compressed.obj.bitLength * 100.0 / 8.0,
                  compressed.obj.bitLength / (8.0 * article.getText().length()),
                  compressed.timeNanos / 1000000000.0));
          return compressed.obj.getBytes();
      }).mapToInt(bytes->bytes.length).sum();
      return String.format("Compressed %s KB of documents -> %s KB (%s dictionary + %s ppm)",
              index.getIndexedSize() / 1024, (totalSize + dictionaryLength)/ 1024,
              dictionaryLength / 1024, totalSize / 1024);
```
Returns: 
```
    Compressed 1564 KB of documents -> 1861 KB (788 dictionary + 1072 ppm)
```
Logging: 
```
    Serialized AccessibleComputing: 69 chars -> 2937.5 bytes (0.4257246376811594%) in 7.03023E-4 sec
    Serialized Anarchism: 186232 chars -> 1.0470375E7 bytes (0.5622221207955668%) in 17.338331757 sec
    Serialized AfghanistanHistory: 57 chars -> 2200.0 bytes (0.38596491228070173%) in 5.016E-4 sec
    Serialized AfghanistanGeography: 59 chars -> 2237.5 bytes (0.3792372881355932%) in 4.60045E-4 sec
    Serialized AfghanistanPeople: 62 chars -> 2387.5 bytes (0.3850806451612903%) in 4.41467E-4 sec
    Serialized AfghanistanCommunications: 64 chars -> 2312.5 bytes (0.361328125%) in 3.86222E-4 sec
    Serialized AfghanistanTransportations: 79 chars -> 3950.0 bytes (0.5%) in 6.31155E-4 sec
    Serialized AfghanistanMilitary: 54 chars -> 2137.5 bytes (0.3958333333333333%) in 3.75956E-4 sec
    Serialized AfghanistanTransnationalIssues: 67 chars -> 2387.5 bytes (0.35634328358208955%) in 4.444E-4 sec
    Serialized AssistiveTechnology: 55 chars -> 2562.5 bytes (0.4659090909090909%) in 4.004E-4 sec
    Serialized AmoeboidTaxa: 41 chars -> 1575.0 bytes (0.38414634146341464%) in 2.98711E-4 sec
    Serialized Autism: 149779 chars -> 8323187.5 bytes (0.5556978948984838%) in 11.509635861 sec
    Serialized AlbaniaHistory: 53 chars -> 2250.0 bytes (0.42452830188679247%) in 4.86445E-4 sec
    Serialized AlbaniaPeople: 58 chars -> 2375.0 bytes (0.40948275862068967%) in 4.61511E-4 sec
    Serialized AsWeMayThink: 50 chars -> 4037.5 bytes (0.8075%) in 6.32623E-4 sec
    Serialized AlbaniaGovernment: 54 chars -> 2275.0 bytes (0.4212962962962963%) in 4.58578E-4 sec
    Serialized AlbaniaEconomy: 53 chars -> 2062.5 bytes (0.3891509433962264%) in 4.356E-4 sec
    Serialized Albedo: 35354 chars -> 2056562.5 bytes (0.5817057475816032%) in 0.935473363 sec
    Serialized AfroAsiaticLanguages: 56 chars -> 2375.0 bytes (0.42410714285714285%) in 3.88178E-4 sec
    Serialized ArtificalLanguages: 142 chars -> 8200.0 bytes (0.5774647887323944%) in 0.001210978 sec
    Serialized AbacuS: 41 chars -> 1550.0 bytes (0.3780487804878049%) in 2.43466E-4 sec
    Serialized AbalonE: 42 chars -> 1475.0 bytes (0.35119047619047616%) in 2.42978E-4 sec
    Serialized AbbadideS: 50 chars -> 1675.0 bytes (0.335%) in 2.84534E-4 sec
    Serialized AbbesS: 41 chars -> 1575.0 bytes (0.38414634146341464%) in 2.44444E-4 sec
    Serialized AbbevilleFrance: 44 chars -> 1587.5 bytes (0.36079545454545453%) in 2.772E-4 sec
    Serialized AbbeY: 40 chars -> 1412.5 bytes (0.353125%) in 2.47378E-4 sec
    Serialized AbboT: 40 chars -> 1412.5 bytes (0.353125%) in 2.27333E-4 sec
    Serialized Abbreviations: 44 chars -> 1637.5 bytes (0.3721590909090909%) in 2.86E-4 sec
    Serialized AtlasShrugged: 50 chars -> 1687.5 bytes (0.3375%) in 3.036E-4 sec
    Serialized ArtificialLanguages: 55 chars -> 2075.0 bytes (0.37727272727272726%) in 3.37334E-4 sec
    Serialized AtlasShruggedCharacters: 68 chars -> 2375.0 bytes (0.3492647058823529%) in 4.20933E-4 sec
    Serialized AtlasShruggedCompanies: 49 chars -> 1762.5 bytes (0.3596938775510204%) in 2.89911E-4 sec
    Serialized AyersMusicPublishingCompany: 67 chars -> 2312.5 bytes (0.3451492537313433%) in 4.61022E-4 sec
    Serialized AfricanAmericanPeople: 52 chars -> 1900.0 bytes (0.36538461538461536%) in 3.76933E-4 sec
    Serialized AdolfHitler: 47 chars -> 1737.5 bytes (0.3696808510638298%) in 2.83556E-4 sec
    Serialized AbeceDarians: 46 chars -> 1612.5 bytes (0.35054347826086957%) in 2.70356E-4 sec
    Serialized AbeL: 48 chars -> 1762.5 bytes (0.3671875%) in 3.07512E-4 sec
    Serialized AbensbergGermany: 44 chars -> 1562.5 bytes (0.35511363636363635%) in 2.54222E-4 sec
    Serialized AberdeenSouthDakota: 57 chars -> 2000.0 bytes (0.3508771929824561%) in 3.52E-4 sec
    Serialized ArthurKoestler: 50 chars -> 1725.0 bytes (0.345%) in 2.95289E-4 sec
    Serialized AynRand: 43 chars -> 1600.0 bytes (0.37209302325581395%) in 3.52977E-4 sec
    Serialized AlexanderTheGreat: 53 chars -> 2037.5 bytes (0.38443396226415094%) in 4.048E-4 sec
    Serialized AnchorageAlaska: 51 chars -> 1825.0 bytes (0.357843... and 5687 more bytes
```
For reference, we encode some sample articles that are NOT in the dictionary:

Code: 
```java
      PPMCodec codec = tree.getCodec();
      WikiArticle.load().skip(100).limit(20).forEach(article -> {
          TimedResult<Bits> compressed = TimedResult.time(()->codec.encodePPM(article.getText(), 4));
          System.out.println(String.format("Serialized %s: %s chars -> %s bytes (%s%%) in %s sec",
                  article.getTitle(), article.getText().length(), compressed.obj.bitLength * 100.0 / 8.0,
                  compressed.obj.bitLength / (8.0 * article.getText().length()),
                  compressed.timeNanos / 1000000000.0));
      });
```
Logging: 
```
    Serialized Artificial languages: 78 chars -> 3850.0 bytes (0.4935897435897436%) in 8.15467E-4 sec
    Serialized Austroasiatic languages: 27408 chars -> 1725275.0 bytes (0.6294786193812025%) in 0.648535149 sec
    Serialized Afro-asiatic languages: 66 chars -> 2700.0 bytes (0.4090909090909091%) in 5.88133E-4 sec
    Serialized Afroasiatic languages: 47485 chars -> 3565000.0 bytes (0.7507633989680952%) in 1.555079486 sec
    Serialized Andorra: 61684 chars -> 3449100.0 bytes (0.5591563452434991%) in 2.307285493 sec
    Serialized Andorra/Transnational issues: 103 chars -> 5437.5 bytes (0.5279126213592233%) in 0.001041333 sec
    Serialized Arithmetic mean: 11367 chars -> 597362.5 bytes (0.5255234450602622%) in 0.166167977 sec
    Serialized American Football Conference: 12135 chars -> 769687.5 bytes (0.6342707045735476%) in 0.207853093 sec
    Serialized Albert Gore: 42 chars -> 2850.0 bytes (0.6785714285714286%) in 5.07466E-4 sec
    Serialized AnEnquiryConcerningHumanUnderstanding: 76 chars -> 2862.5 bytes (0.37664473684210525%) in 6.44845E-4 sec
    Serialized Animal Farm: 69803 chars -> 3991125.0 bytes (0.57176983797258%) in 2.854340496 sec
    Serialized Amphibian: 130679 chars -> 7412750.0 bytes (0.567248754581838%) in 8.819905742 sec
    Serialized Albert Arnold Gore/Criticisms: 21 chars -> 1762.5 bytes (0.8392857142857143%) in 3.33911E-4 sec
    Serialized Alaska: 141209 chars -> 8281950.0 bytes (0.5865029849372208%) in 10.191067649 sec
    Serialized Auteur Theory Film: 20 chars -> 1412.5 bytes (0.70625%) in 1.91156E-4 sec
    Serialized Agriculture: 108723 chars -> 5705462.5 bytes (0.5247705177377372%) in 6.191458342 sec
    Serialized Aldous Huxley: 49379 chars -> 2845612.5 bytes (0.5762798963121974%) in 1.58774998 sec
    Serialized Abstract Algebra: 61 chars -> 2387.5 bytes (0.39139344262295084%) in 4.01378E-4 sec
    Serialized Ada: 3353 chars -> 204887.5 bytes (0.6110572621532956%) in 0.041690005 sec
    Serialized Aberdeen (disambiguation): 6684 chars -> 378862.5 bytes (0.5668200179533214%) in 0.092367256 sec
    
```
