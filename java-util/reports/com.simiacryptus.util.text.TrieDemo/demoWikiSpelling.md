This will demonstrate how to serialize a CharTrie class in compressed format


### First, we load training and testing data:
Code from [TrieDemo.java:592](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L592) executed in 1.04 seconds: 
```java
      return Misspelling.BIRKBECK.load().collect(Collectors.toList());
```
Returns: 
```
    [Misspelling{title='America'}, Misspelling{title='America'}, Misspelling{title='American'}, Misspelling{title='April'}, Misspelling{title='Austrian'}, Misspelling{title='Badcock's'}, Misspelling{title='Bechuanaland'}, Misspelling{title='Botswana'}, Misspelling{title='Cambridge'}, Misspelling{title='Canada'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Christ'}, Misspelling{title='Christian'}, Misspelling{title='Christmas'}, Misspelling{title='Christmas'}, Misspelling{title='Christmas'}, Misspelling{title='Christmas'}, Misspelling{title='Christmas'}, Misspelling{title='Cinderella'}, Misspelling{title='Dec'}, Misspelling{title='December'}, Misspelling{title='December'}, Misspelling{title='Dr'}, Misspelling{title='Dracula's'}, Misspelling{title='Dunstan's'}, Misspelling{title='Easter'}, Misspelling{title='Easter'}, Misspelling{title='Eastwood'}, Misspelling{title='English'}, Misspelling{title='English'}, Misspelling{title='Feb'}, Misspelling{title='February'}, Misspelling{title='February'}, Misspelling{title='February'}, Misspelling{title='French'}, Misspelling{title='French'}, Misspelling{title='French'}, Misspelling{title='French'}, Misspelling{title='Friday'}, Misspelling{title='Hallowe'en'}, Misspelling{title='Hitler'}, Misspelling{title='I'}, Misspelling{title='I'}, Misspelling{title='I'}, Misspelling{title='I'}, Misspelling{title='I'}, Misspelling{title='I'}, Misspelling{title='I'}, Misspelling{title='I'}, Misspelling{title='I'}, Misspelling{title='I'}, Misspelling{title='I'}, Misspelling{title='I'}, Misspelling{title='I'd'}, Misspelling{title='I'd'}, Misspelling{title='I'd'}, Misspelling{title='I'll'}, Misspelling{title='I'll'}, Misspelling{title='I'm'}, Misspelling{title='I'm'}, Misspelling{title='I'm'}, Misspelling{title='I've'}, Misspelling{title='I've'}, Misspelling{title='Indians'}, Misspelling{title='Ireland'}, Misspelling{title='Irish'}, Misspelling{title='January'}, Misspelling{title='July'}, Misspelling{title='June'}, Misspelling{title='Leicester'}, Misspelling{title='Los_Angeles'}, Misspelling{title='Luke's'}, Misspelling{title='Mayfield'}, Misspelling{title='Mediterranean'}, Misspelling{title='Miss'}, Misspelling{title='Monday'}, Misspelling{title='Mr'}, Misspelling{title='Mr'}, Misspelling{title='Mrs'}, Misspelling{title='Mrs'}, Misspelling{title='Mrs'}, Misspelling{title='Mrs'}, Misspelling{title='October'}, Misspelling{title='Pekinese'}, Misspelling{title='Philip's'}, Misspelling{title='Pyrenean'}, Misspelling{title='Rolls'}, Misspelling{title='Samaritan'}, Misspelling{title='Saturday'}, Misspelling{title='Saturday'}, Misspelling{title='Scots'}, Misspelling{title='Sept'}, Misspelling{title='September'}, Misspelling{title='September'}, Misspelling{title='Sheba'}, Misspelling{title='Spanish'}, Misspelling{title='Sunday'}, Misspelling{title='Swedish'}, Misspelling{title='Thames'}, Misspelling{title='Tuesday'}, Misspelling{title='Tuesday'}, Misspelling{title='Tuesday'}, Misspelling{title='Victorian'}, Misspelling{title='Wednesday'}, Misspelling{title='Wednesday'}, Misspelling{title='Wednesday'}, Misspelling{title='Welsh'}, Misspelling{title='X-rays'}, Misspelling{title='a'}, Misspelling{title='a'}, Misspelling{title='a'}, Misspelling{title='a'}, Misspelling{title='a'}, Misspelling{title='a'}, Misspelling{title='a'}, Misspelling{title='a-quiver'}, Misspelling{title='a_bit'}, Misspelling{title='a_few'}, Misspelling{title='a_little'}, Misspelling{title='a_little'}, Misspelling{title='a_long'}, Misspelling{title='a_look'}, Misspelling{title='a_lot'}, Misspelling{title='a_lot'}, Misspelling{title='a_lot'}, Misspelling{title='a_museum'}, Misspelling{title='abattoir'}, Misspelling{title='abhorrence'}, Misspelling{title='ability'}, Misspelling{title='ability'}, Misspelling{title='ability'}, Misspelling{title='ability'}, Misspelling{title='ability'}, Misspelling{title='able'}, Misspelling{title='able'}, Misspelling{title='able'}, Misspelling{title='able'}, Misspelling{title='able-bodied'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='abroad'}, Misspelling{title='abroad'}, Misspelling{title='abroad'}, Misspelling{title='absence'}, Misspelling{title='absence'}, Misspelling{title='absent'}, Misspelling{title='absolutely'}, Misspelling{title='absolutely'}, Misspelling{title='absolutely'}, Misspelling{title='absorbed'}, Misspelling{title='absorbent'}, Misspelling{title='absorption'}, Misspelling{title='abstract'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='abundance'}, Misspelling{title='abundant'}, Misspelling{title='abuse'}, Misspelling{title='academic'}, Misspelling{title='academic'}, Misspelling{title='academically'}, Misspelling{title='academically'}, Misspelling{title='accede'}, Misspelling{title='accelerate'}, Misspelling{title='accents'}, Misspelling{title='accept'}, Misspelling{title='accept'}, Misspelling{title='accept'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptance'}, Misspelling{title='accepted'}, Misspelling{title='accepted'}, Misspelling{title='accepted'}, Misspelling{title='access'}, Misspelling{title='accessibility'}, Misspelling{title='accessible'}, Misspelling{title='accessible'}, Misspelling{title='accessing'}, Misspelling{title='accession'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accidentally'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodations'}, Misspelling{title='accompanied'}, Misspelling{title='accompaniment'}, Misspelling{title='accompany'}, Misspelling{title='accompanying'}, Misspelling{title='accompanying'}, Misspelling{title='accompanying'}, Misspelling{title='accompanying'}, Misspelling{title='accompanying'}, Misspelling{title='accompanying'}, Misspelling{title='accompanying'}, Misspelling{title='accompanying'}, Misspelling{title='accompanying... and 1108660 more bytes
```

### Then, we decompose the text into an n-gram trie:
Code from [TrieDemo.java:597](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L597) executed in 4.83 seconds: 
```java
      List<String> list = trainingList.stream().map(x -> x.getTitle()).collect(Collectors.toList());
      CharTrie trie = CharTrieIndex.create(list, depth, 0);
      print(trie);
      return trie;
```
Returns: 
```
    com.simiacryptus.util.text.CharTrieIndex@50cf6d72
```
Logging: 
```
    Total Indexed Document (KB): 287
    Total Node Count: 54074
    Total Index Memory Size (KB): 5632
    
```

Spelling check: Ameraca -> America

Code from [TrieDemo.java:606](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L606) executed in 0.04 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling(testArticle.getText());
```
Returns: 
```
    21.13954486847593
```
Logging: 
```
           "A"	    "Amer"	10.9160	3.2321	10.9160	10.9574
          "Am"	    "mera"	0.6931	5.3447	3.9253	2.9444
         "Ame"	     "era"	-0.0000	1.8005	5.3447	-0.0000
        "Amer"	     "rac"	-0.0000	3.0155	1.8005	-0.0000
         "era"	     "aca"	2.1602	2.2461	5.1758	1.3420
         "rac"	      "ca"	3.3385	2.9611	5.5846	3.4712
          "ca"	       "a"	2.5669	2.6255	5.5280	2.4245
    
```

Spelling check: Amercia -> America

Code from [TrieDemo.java:606](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L606) executed in 0.01 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling(testArticle.getText());
```
Returns: 
```
    15.015872749834637
```
Logging: 
```
           "A"	    "Amer"	10.9160	3.2321	10.9160	10.9574
          "Am"	  "mercia"	0.6931	-0.0000	3.9253	-0.0000
         "Ame"	   "ercia"	-0.0000	-0.0000	-0.0000	-0.0000
        "Amer"	    "rcia"	-0.0000	5.1210	-0.0000	-0.0000
         "erc"	     "cia"	3.9214	1.1486	9.0424	1.8161
        "erci"	      "ia"	1.7918	2.4265	2.9403	1.2990
       "ercia"	       "a"	1.0986	2.6255	3.5251	0.9434
    
```

Spelling check: Ameracan -> American

Code from [TrieDemo.java:606](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L606) executed in 0.01 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling(testArticle.getText());
```
Returns: 
```
    20.617017644536393
```
Logging: 
```
           "A"	    "Amer"	10.9160	3.2321	10.9160	10.9574
          "Am"	    "mera"	0.6931	5.3447	3.9253	2.9444
         "Ame"	     "era"	-0.0000	1.8005	5.3447	-0.0000
        "Amer"	     "rac"	-0.0000	3.0155	1.8005	-0.0000
         "era"	    "acan"	2.1602	0.8967	5.1758	0.1617
         "rac"	     "can"	3.3385	2.5312	4.2353	3.0579
          "ca"	      "an"	2.5669	1.7748	5.0982	2.3450
         "can"	       "n"	1.4042	2.6848	3.1790	1.1506
    
```

Spelling check: Apirl -> April

Code from [TrieDemo.java:606](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L606) executed in 0.01 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling(testArticle.getText());
```
Returns: 
```
    29.298539834766732
```
Logging: 
```
           "A"	      "Ap"	10.9160	9.0883	10.9160	16.6500
          "Ap"	     "pir"	1.7918	2.3540	10.8800	0.5878
           "i"	     "irl"	2.5194	1.6422	4.8734	1.5237
          "ir"	      "rl"	3.8271	5.5189	5.4693	6.3494
         "irl"	       "l"	3.8764	3.0618	9.3953	4.1876
    
```

Spelling check: Austrain -> Austrian

Code from [TrieDemo.java:606](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L606) executed in 0.01 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling(testArticle.getText());
```
Returns: 
```
    11.472615158280993
```
Logging: 
```
           "A"	   "Austr"	10.9160	3.1781	10.9160	10.6286
          "Au"	   "ustra"	1.7918	3.2834	4.9698	-0.0000
         "Aus"	  "strain"	-0.0000	2.5649	3.2834	-0.0000
        "Aust"	   "train"	-0.0000	1.0857	2.5649	-0.0000
       "Austr"	    "rain"	-0.0000	2.0312	1.0857	-0.0000
        "stra"	     "ain"	1.5261	2.1970	3.5573	0.2220
       "strai"	      "in"	2.7726	1.4510	4.9696	0.0557
      "strain"	       "n"	0.9163	2.6848	2.3673	0.5662
    
```

Spelling check: badcock -> Badcock's

Code from [TrieDemo.java:606](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L606) executed in 0.01 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling(testArticle.getText());
```
Returns: 
```
    13.600557992680706
```
Logging: 
```
           "b"	     "bad"	4.5524	3.6889	4.5524	4.9891
          "ba"	  "adcock"	2.4156	-0.0000	6.1044	-0.0000
         "bad"	   "dcock"	3.3419	-0.0000	3.3419	-0.0000
          "dc"	    "cock"	8.1188	4.2195	8.1188	6.7306
         "dco"	     "ock"	1.0986	1.7568	5.3181	1.8808
        "dcoc"	      "ck"	-0.0000	1.1286	1.7568	-0.0000
       "dcock"	       "k"	-0.0000	5.6028	1.1286	-0.0000
    
```

Spelling check: bechuarnia_land -> Bechuanaland

Code from [TrieDemo.java:606](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L606) executed in 0.01 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling(testArticle.getText());
```
Returns: 
```
    44.05202686214806
```
Logging: 
```
           "b"	     "bec"	4.5524	4.3041	4.5524	5.5714
          "be"	   "echua"	1.5155	-0.0000	5.8196	-0.0000
         "bec"	    "chua"	3.2059	-0.0000	3.2059	-0.0000
          "ch"	     "hua"	2.4137	6.9594	2.4137	5.3170
         "chu"	     "uar"	5.4827	2.1911	12.4421	4.6801
        "chua"	    "arni"	1.7918	1.3863	3.9829	-0.0000
          "ar"	     "rni"	2.1514	4.9071	3.5377	3.6728
         "arn"	     "nia"	4.7120	2.9109	9.6192	4.6807
        "arni"	      "ia"	1.8326	2.4265	4.7435	1.1783
         "nia"	     "a_l"	2.9348	0.1335	5.3613	0.6137
          "a_"	      "_l"	7.6844	7.5666	7.8180	12.0411
         "a_l"	    "land"	0.4520	3.5648	8.0186	0.6444
           "a"	     "and"	2.6255	2.0477	6.1903	2.0839
         "lan"	      "nd"	1.0858	1.8131	3.1335	0.1546
         "and"	       "d"	2.8917	3.4904	4.7048	3.4139
    
```

Spelling check: botuania -> Botswana

Code from [TrieDemo.java:606](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L606) executed in 0.01 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling(testArticle.getText());
```
Returns: 
```
    26.69395455158287
```
Logging: 
```
           "b"	     "bot"	4.5524	3.5410	4.5524	4.8846
          "bo"	      "ot"	2.2861	3.7874	5.8270	3.2390
         "bot"	     "tua"	3.2302	1.1880	7.0176	1.3420
          "tu"	     "uan"	2.9199	6.8620	4.1078	6.3681
         "tua"	     "ani"	1.2761	2.4594	8.1380	0.6978
         "uan"	     "nia"	5.5731	2.9109	8.0325	5.9673
          "ni"	      "ia"	2.3433	2.4265	5.2543	1.5559
         "nia"	       "a"	2.9348	2.6255	5.3613	2.6392
    
```

Spelling check: Cambrige -> Cambridge

Code from [TrieDemo.java:606](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L606) executed in 0.01 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling(testArticle.getText());
```
Returns: 
```
    10.049053455915818
```
Logging: 
```
           "C"	  "Cambri"	8.1435	0.6931	8.1435	5.1298
          "Ca"	   "ambri"	3.8712	-0.0000	4.5643	-0.0000
         "Cam"	    "mbri"	0.6931	3.2958	0.6931	-0.0000
        "Camb"	    "brig"	-0.0000	4.8442	3.2958	-0.0000
       "Cambr"	    "rige"	-0.0000	0.1321	4.8442	-0.0000
      "Cambri"	     "ige"	-0.0000	2.5376	0.1321	-0.0000
        "brig"	      "ge"	3.2958	3.3921	5.8335	3.7797
         "ige"	       "e"	1.9251	2.2034	5.3172	1.1395
    
```

Spelling check: Canda -> Canada

Code from [TrieDemo.java:606](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L606) executed in 0.00 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling(testArticle.getText());
```
Returns: 
```
    17.96548909065502
```
Logging: 
```
           "C"	     "Can"	8.1435	8.2483	8.1435	13.1624
          "Ca"	    "anda"	3.8712	1.7125	12.1195	-0.0000
         "Can"	     "nda"	0.6931	1.3030	2.4057	-0.0000
          "nd"	      "da"	2.6188	3.5115	3.9218	2.9163
         "nda"	       "a"	2.1364	2.6255	5.6479	1.8868
    
```

Spelling check: Chactuquoe -> Chautauqua

Code from [TrieDemo.java:606](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L606) executed in 0.01 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling(testArticle.getText());
```
Returns: 
```
    23.316440327098235
```
Logging: 
```
           "C"	     "Cha"	8.1435	1.8860	8.1435	6.2335
          "Ch"	      "ha"	0.0317	3.7420	1.9178	0.0393
         "Cha"	    "actu"	0.0783	1.0542	3.8202	-0.0000
          "ac"	     "ctu"	2.5670	1.4716	3.6212	1.2326
         "act"	      "tu"	1.8082	2.3640	3.2798	1.1244
        "actu"	     "uqu"	1.1853	2.2591	3.5493	0.3578
          "uq"	     "quo"	4.9456	3.3142	7.2047	6.0291
         "uqu"	      "uo"	-0.0000	5.0374	3.3142	-0.0000
         "quo"	      "oe"	5.3387	5.2522	10.3762	6.8555
           "e"	       "e"	2.2034	2.2034	7.4555	1.4443
    
```

Spelling check: Chalktwa -> Chautauqua

Code from [TrieDemo.java:606](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L606) executed in 0.01 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling(testArticle.getText());
```
Returns: 
```
    30.24886382877027
```
Logging: 
```
           "C"	     "Cha"	8.1435	1.8860	8.1435	6.2335
          "Ch"	    "halk"	0.0317	4.1431	1.9178	-0.0000
         "Cha"	     "alk"	0.0783	0.4520	4.2214	-0.0000
          "al"	      "lk"	1.9588	2.5098	2.4108	1.7476
         "alk"	       "k"	3.9804	5.6028	6.4903	6.1597
           "t"	     "twa"	2.7404	5.4553	8.3432	4.4346
          "tw"	      "wa"	6.7093	4.6270	12.1646	8.2547
         "twa"	       "a"	3.2581	2.6255	7.8851	3.4187
    
```

Spelling check: Chaqua -> Chautauqua

Code from [TrieDemo.java:606](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L606) executed in 0.00 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling(testArticle.getText());
```
Returns: 
```
    16.171601105456546
```
Logging: 
```
           "C"	     "Cha"	8.1435	1.8860	8.1435	6.2335
          "Ch"	      "ha"	0.0317	3.7420	1.9178	0.0393
         "Cha"	     "aqu"	0.0783	6.7250	3.8202	-0.0000
          "aq"	     "qua"	10.0823	1.2930	16.8074	8.6569
         "aqu"	      "ua"	-0.0000	3.1229	1.2930	-0.0000
         "qua"	       "a"	1.0586	2.6255	4.1816	1.2418
    
```

Spelling check: Chata -> Chautauqua

Code from [TrieDemo.java:606](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L606) executed in 0.01 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling(testArticle.getText());
```
Returns: 
```
    12.831866187941111
```
Logging: 
```
           "C"	     "Cha"	8.1435	1.8860	8.1435	6.2335
          "Ch"	     "hat"	0.0317	4.2665	1.9178	0.0917
         "Cha"	     "ata"	0.0783	3.5264	4.3448	0.4150
          "at"	      "ta"	1.8085	2.6440	5.3349	1.6292
         "ata"	       "a"	4.3618	2.6255	7.0058	4.4624
    
```

Spelling check: Chatacqua -> Chautauqua

Code from [TrieDemo.java:606](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L606) executed in 0.01 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling(testArticle.getText());
```
Returns: 
```
    13.086217365309833
```
Logging: 
```
           "C"	     "Cha"	8.1435	1.8860	8.1435	6.2335
          "Ch"	     "hat"	0.0317	4.2665	1.9178	0.0917
         "Cha"	     "ata"	0.0783	3.5264	4.3448	0.4150
          "at"	     "tac"	1.8085	3.6235	5.3349	1.9180
         "ata"	   "acqua"	4.3618	-0.0000	7.9853	-0.0000
         "tac"	    "cqua"	3.5466	1.5556	3.5466	1.6070
          "cq"	     "qua"	5.5138	1.2930	7.0694	2.7507
         "cqu"	      "ua"	-0.0000	3.1229	1.2930	-0.0000
        "cqua"	       "a"	0.0635	2.6255	3.1865	0.0704
    
```

Spelling check: Chatacque -> Chautauqua

Code from [TrieDemo.java:606](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L606) executed in 0.01 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling(testArticle.getText());
```
Returns: 
```
    14.254422902825562
```
Logging: 
```
           "C"	     "Cha"	8.1435	1.8860	8.1435	6.2335
          "Ch"	     "hat"	0.0317	4.2665	1.9178	0.0917
         "Cha"	     "ata"	0.0783	3.5264	4.3448	0.4150
          "at"	     "tac"	1.8085	3.6235	5.3349	1.9180
         "ata"	    "acqu"	4.3618	-0.0000	7.9853	-0.0000
         "tac"	     "cqu"	3.5466	2.5506	3.5466	2.5869
          "cq"	     "que"	5.5138	0.6874	8.0644	2.0915
         "cqu"	      "ue"	-0.0000	4.2487	0.6874	-0.0000
         "que"	       "e"	1.1567	2.2034	5.4054	0.9178
    
```

Spelling check: Chatalkwa -> Chautauqua

Code from [TrieDemo.java:606](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L606) executed in 0.01 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling(testArticle.getText());
```
Returns: 
```
    17.85080533973199
```
Logging: 
```
           "C"	     "Cha"	8.1435	1.8860	8.1435	6.2335
          "Ch"	     "hat"	0.0317	4.2665	1.9178	0.0917
         "Cha"	    "atal"	0.0783	1.5667	4.3448	0.0038
          "at"	    "talk"	1.8085	1.8405	3.3752	0.1538
         "ata"	     "alk"	4.3618	0.4520	6.2024	1.4414
        "atal"	      "lk"	0.1508	2.5098	0.6028	0.0168
        "talk"	     "kwa"	3.0253	3.8459	5.5351	3.1855
           "w"	      "wa"	5.2525	4.6270	9.0984	6.7243
         "kwa"	       "a"	-0.0000	2.6255	4.6270	-0.0000
    
```

Spelling check: Chataqua -> Chautauqua

Code from [TrieDemo.java:606](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L606) executed in 0.00 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling(testArticle.getText());
```
Returns: 
```
    22.786025100795893
```
Logging: 
```
           "C"	     "Cha"	8.1435	1.8860	8.1435	6.2335
          "Ch"	     "hat"	0.0317	4.2665	1.9178	0.0917
         "Cha"	     "ata"	0.0783	3.5264	4.3448	0.4150
          "at"	      "ta"	1.8085	2.6440	5.3349	1.6292
         "ata"	     "aqu"	4.3618	6.7250	7.0058	9.3489
           "q"	     "qua"	5.9828	1.2930	12.7078	3.8259
          "qu"	      "ua"	-0.0000	3.1229	1.2930	-0.0000
         "qua"	       "a"	1.0586	2.6255	4.1816	1.2418
    
```

Spelling check: Chataque -> Chautauqua

Code from [TrieDemo.java:606](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L606) executed in 0.01 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling(testArticle.getText());
```
Returns: 
```
    19.79075696605129
```
Logging: 
```
           "C"	     "Cha"	8.1435	1.8860	8.1435	6.2335
          "Ch"	     "hat"	0.0317	4.2665	1.9178	0.0917
         "Cha"	     "ata"	0.0783	3.5264	4.3448	0.4150
          "at"	      "ta"	1.8085	2.6440	5.3349	1.6292
         "ata"	    "aque"	4.3618	5.5683	7.0058	7.5310
           "q"	     "que"	5.9828	0.6874	11.5511	2.9725
          "qu"	      "ue"	-0.0000	4.2487	0.6874	-0.0000
         "que"	       "e"	1.1567	2.2034	5.4054	0.9178
    
```

Spelling check: Chataukowa -> Chautauqua

Code from [TrieDemo.java:606](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L606) executed in 0.01 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling(testArticle.getText());
```
Returns: 
```
    43.78137552530247
```
Logging: 
```
           "C"	     "Cha"	8.1435	1.8860	8.1435	6.2335
          "Ch"	     "hat"	0.0317	4.2665	1.9178	0.0917
         "Cha"	     "ata"	0.0783	3.5264	4.3448	0.4150
          "at"	     "tau"	1.8085	1.7669	5.3349	0.7588
         "ata"	      "au"	4.3618	2.7319	6.1288	3.6228
         "tau"	      "uk"	2.5257	7.1050	5.2576	6.4336
          "uk"	      "ko"	9.4115	5.9312	16.5164	12.4404
           "o"	     "owa"	2.9699	3.8459	8.9011	3.0546
           "w"	      "wa"	5.2525	4.6270	9.0984	6.7243
         "owa"	       "a"	4.3845	2.6255	9.0115	4.0065
    
```

