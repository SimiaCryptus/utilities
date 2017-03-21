This will demonstrate how to serialize a CharTrie class in compressed format


### First, we load training and testing data:
Code from [TrieDemo.java:493](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L493) executed in 6.11 seconds: 
```java
      return WikiArticle.load()
              .filter(x -> x.getText().length() > 4 * 1024)
              .limit(100).collect(Collectors.toList());
```
Returns: 
```
    [WikiArticle{title='Autism'}, WikiArticle{title='Albedo'}, WikiArticle{title='A'}, WikiArticle{title='Achilles'}, WikiArticle{title='Abraham Lincoln'}, WikiArticle{title='Actrius'}, WikiArticle{title='Animalia (book)'}, WikiArticle{title='International Atomic Time'}, WikiArticle{title='Altruism'}, WikiArticle{title='Alain Connes'}, WikiArticle{title='Allan Dwan'}, WikiArticle{title='Algeria'}, WikiArticle{title='List of Atlas Shrugged characters'}, WikiArticle{title='Anthropology'}, WikiArticle{title='Agricultural science'}, WikiArticle{title='Alchemy'}, WikiArticle{title='Astronomer'}, WikiArticle{title='Animation'}, WikiArticle{title='Apollo'}, WikiArticle{title='Andorra'}, WikiArticle{title='Arithmetic mean'}, WikiArticle{title='American Football Conference'}, WikiArticle{title='Animal Farm'}, WikiArticle{title='Amphibian'}, WikiArticle{title='Alaska'}, WikiArticle{title='Aldous Huxley'}, WikiArticle{title='Aberdeen (disambiguation)'}, WikiArticle{title='Algae'}, WikiArticle{title='Analysis of variance'}, WikiArticle{title='Arraignment'}, WikiArticle{title='America the Beautiful'}, WikiArticle{title='Assistive technology'}, WikiArticle{title='Abacus'}, WikiArticle{title='Acid'}, WikiArticle{title='American National Standards Institute'}, WikiArticle{title='Apollo 11'}, WikiArticle{title='Apollo 8'}, WikiArticle{title='Astronaut'}, WikiArticle{title='A Modest Proposal'}, WikiArticle{title='Alkali metal'}, WikiArticle{title='Alphabet'}, WikiArticle{title='Atomic number'}, WikiArticle{title='Animal (disambiguation)'}, WikiArticle{title='Aardvark'}, WikiArticle{title='Aardwolf'}, WikiArticle{title='Adobe'}, WikiArticle{title='Adventure'}, WikiArticle{title='Asia'}, WikiArticle{title='Aruba'}, WikiArticle{title='Articles of Confederation'}, WikiArticle{title='Atlantic Ocean'}, WikiArticle{title='Arthur Schopenhauer'}, WikiArticle{title='Angola'}, WikiArticle{title='Demographics of Angola'}, WikiArticle{title='Politics of Angola'}, WikiArticle{title='Economy of Angola'}, WikiArticle{title='Angolan Armed Forces'}, WikiArticle{title='Foreign relations of Angola'}, WikiArticle{title='Albert Sidney Johnston'}, WikiArticle{title='Actinopterygii'}, WikiArticle{title='Albert Einstein'}, WikiArticle{title='Afghanistan'}, WikiArticle{title='Allah'}, WikiArticle{title='Aikido'}, WikiArticle{title='Art'}, WikiArticle{title='Agnostida'}, WikiArticle{title='American Revolutionary War'}, WikiArticle{title='Ampere'}, WikiArticle{title='Algorithm'}, WikiArticle{title='Annual plant'}, WikiArticle{title='Atlas (disambiguation)'}, WikiArticle{title='Alexander the Great'}, WikiArticle{title='Alfred Korzybski'}, WikiArticle{title='Asteroids (video game)'}, WikiArticle{title='Asparagales'}, WikiArticle{title='Alismatales'}, WikiArticle{title='Apiales'}, WikiArticle{title='Asterales'}, WikiArticle{title='Asteroid'}, WikiArticle{title='Affidavit'}, WikiArticle{title='Aries (constellation)'}, WikiArticle{title='Aquarius (constellation)'}, WikiArticle{title='Alfred Hitchcock'}, WikiArticle{title='Anaconda'}, WikiArticle{title='Altaic languages'}, WikiArticle{title='Austrian German'}, WikiArticle{title='Axiom of choice'}, WikiArticle{title='Attila'}, WikiArticle{title='Aegean Sea'}, WikiArticle{title='A Clockwork Orange (novel)'}, WikiArticle{title='Amsterdam'}, WikiArticle{title='Apple Inc.'}, WikiArticle{title='Aberdeenshire'}, WikiArticle{title='Aztlan Underground'}, WikiArticle{title='American Civil War'}, WikiArticle{title='Ancient Egypt'}, WikiArticle{title='Motor neuron disease'}, WikiArticle{title='Abjad'}, WikiArticle{title='Abugida'}, WikiArticle{title='ABBA'}]
```

### Then, we decompose the text into an n-gram trie:
Code from [TrieDemo.java:500](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L500) executed in 36.95 seconds: 
```java
      CharTrie trie = CharTrieIndex.create(trainingList.stream().map(x -> x.getText()).collect(Collectors.toList()), depth, 0);
      print(trie);
      return trie;
```
Returns: 
```
    com.simiacryptus.util.text.CharTrieIndex@5002e70f
```
Logging: 
```
    Total Indexed Document (KB): 5508
    Total Node Count: 4061115
    Total Index Memory Size (KB): 98305
    
```

## Spelling check: testArticle
Code from [TrieDemo.java:507](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L507) executed in 0.08 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling(testArticle);
```
Returns: 
```
    29.958534828883778
```
Logging: 
```
           "n"	   "nesse"	3.0322	1.5544	3.0322	1.5172
          "ne"	    "esse"	2.7067	0.9656	4.2611	0.6818
         "nes"	    "ssec"	2.1316	4.9547	3.0972	5.1515
        "ness"	     "sec"	1.1471	2.7235	6.1018	0.4110
       "nesse"	   "ecary"	1.8095	-0.0000	4.5330	-0.0000
        "ssec"	    "cary"	5.4467	8.1409	5.4467	10.6878
          "ca"	     "ary"	2.1466	1.1027	10.2875	1.0927
         "car"	      "ry"	3.0688	1.7648	4.1715	2.3582
        "cary"	       "y"	6.5930	4.5372	8.3578	8.0583
    
```

## Spelling check: testArticle
Code from [TrieDemo.java:507](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L507) executed in 0.05 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling(testArticle);
```
Returns: 
```
    5.8728813554494295
```
Logging: 
```
           " "	  " test "	2.0964	1.3807	2.0964	0.8048
          " t"	   "test "	2.3150	2.2439	3.6958	0.9343
         " te"	    "est "	3.3758	1.5386	5.6197	1.8912
        " tes"	     "st "	2.8239	1.5322	4.3625	1.7011
       " test"	      "t "	-0.0000	2.9471	1.5322	-0.0000
      " test "	       " "	1.1278	2.0964	4.0749	0.5415
    
```

## Spelling check: testArticle
Code from [TrieDemo.java:507](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L507) executed in 0.07 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling(testArticle);
```
Returns: 
```
    2.125061735602346
```
Logging: 
```
           "W"	 "Washing"	6.7342	0.1270	6.7342	0.9383
          "Wa"	 "ashingt"	1.2987	-0.0000	1.4257	-0.0000
         "Was"	 "shingto"	1.9179	0.2279	1.9179	0.1638
        "Wash"	 "hington"	0.1523	0.2396	0.3802	0.0011
       "Washi"	  "ington"	0.0087	-0.0000	0.2483	-0.0000
      "Washin"	   "ngton"	-0.0000	-0.0000	-0.0000	-0.0000
     "Washing"	    "gton"	-0.0000	1.5296	-0.0000	-0.0000
      "shingt"	     "ton"	0.6668	3.3373	2.1964	1.0208
     "shingto"	      "on"	-0.0000	1.6575	3.3373	-0.0000
      "ington"	       "n"	0.0025	3.0322	1.6600	0.0010
    
```

## Spelling check: testArticle
Code from [TrieDemo.java:507](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L507) executed in 0.05 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling(testArticle);
```
Returns: 
```
    3.3838089431018497
```
Logging: 
```
           "n"	  "needed"	3.0322	0.2093	3.0322	0.1101
          "ne"	   "eeded"	2.7067	0.1287	2.9160	0.0934
         "nee"	    "eded"	3.6518	1.9001	3.7805	2.3603
        "need"	     "ded"	0.4815	2.8953	2.3817	0.6779
       "neede"	      "ed"	0.4316	1.4319	3.3269	0.1421
      "needed"	       "d"	-0.0000	3.7385	1.4319	-0.0000
    
```

## Spelling check: testArticle
Code from [TrieDemo.java:507](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L507) executed in 0.08 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling(testArticle);
```
Returns: 
```
    6.025641321875905
```
Logging: 
```
           "t"	 "tempera"	2.8436	0.0465	2.8436	0.0024
          "te"	  "empera"	1.9178	0.0269	1.9643	0.0167
         "tem"	   "mpera"	3.1374	1.4017	3.1643	0.2330
        "temp"	    "pera"	1.2873	2.1260	2.6889	0.2381
       "tempe"	    "erar"	1.6411	2.3579	3.7671	1.2452
      "temper"	 "rarily "	0.0180	1.9369	2.3760	-0.0000
     "tempera"	  "arily "	0.0465	0.0267	1.9835	-0.0000
         "rar"	   "rily "	3.4625	1.5157	3.4891	1.5190
        "rari"	    "ily "	2.2982	2.6661	3.8139	1.1896
        "aril"	     "ly "	3.1060	1.4026	5.7720	1.5629
       "arily"	      "y "	0.0167	3.1286	1.4193	0.0121
      "arily "	       " "	0.0696	2.0964	3.1982	0.0066
    
```

## Spelling check: testArticle
Code from [TrieDemo.java:507](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L507) executed in 0.07 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling(testArticle);
```
Returns: 
```
    5.172790335060561
```
Logging: 
```
           "f"	   "four "	4.1439	1.0296	4.1439	1.9509
          "fo"	    "our "	2.1540	0.3961	3.1836	0.0369
         "fou"	     "ur "	2.4712	3.5637	2.8674	1.6167
        "four"	      "r "	1.0432	3.1368	4.6068	1.4270
       "four "	       " "	0.4103	2.0964	3.5471	0.1413
    
```

