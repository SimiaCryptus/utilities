This will demonstrate how to serialize a CharTrie class in compressed format


### First, we load training and testing data:
Code from [TrieDemo.java:592](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L592) executed in 1.04 seconds: 
```java
      return Misspelling.BIRKBECK.load().collect(Collectors.toList());
```
Returns: 
```
    [Misspelling{title='America'}, Misspelling{title='America'}, Misspelling{title='American'}, Misspelling{title='April'}, Misspelling{title='Austrian'}, Misspelling{title='Badcock's'}, Misspelling{title='Bechuanaland'}, Misspelling{title='Botswana'}, Misspelling{title='Cambridge'}, Misspelling{title='Canada'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Chautauqua'}, Misspelling{title='Christ'}, Misspelling{title='Christian'}, Misspelling{title='Christmas'}, Misspelling{title='Christmas'}, Misspelling{title='Christmas'}, Misspelling{title='Christmas'}, Misspelling{title='Christmas'}, Misspelling{title='Cinderella'}, Misspelling{title='Dec'}, Misspelling{title='December'}, Misspelling{title='December'}, Misspelling{title='Dr'}, Misspelling{title='Dracula's'}, Misspelling{title='Dunstan's'}, Misspelling{title='Easter'}, Misspelling{title='Easter'}, Misspelling{title='Eastwood'}, Misspelling{title='English'}, Misspelling{title='English'}, Misspelling{title='Feb'}, Misspelling{title='February'}, Misspelling{title='February'}, Misspelling{title='February'}, Misspelling{title='French'}, Misspelling{title='French'}, Misspelling{title='French'}, Misspelling{title='French'}, Misspelling{title='Friday'}, Misspelling{title='Hallowe'en'}, Misspelling{title='Hitler'}, Misspelling{title='I'}, Misspelling{title='I'}, Misspelling{title='I'}, Misspelling{title='I'}, Misspelling{title='I'}, Misspelling{title='I'}, Misspelling{title='I'}, Misspelling{title='I'}, Misspelling{title='I'}, Misspelling{title='I'}, Misspelling{title='I'}, Misspelling{title='I'}, Misspelling{title='I'd'}, Misspelling{title='I'd'}, Misspelling{title='I'd'}, Misspelling{title='I'll'}, Misspelling{title='I'll'}, Misspelling{title='I'm'}, Misspelling{title='I'm'}, Misspelling{title='I'm'}, Misspelling{title='I've'}, Misspelling{title='I've'}, Misspelling{title='Indians'}, Misspelling{title='Ireland'}, Misspelling{title='Irish'}, Misspelling{title='January'}, Misspelling{title='July'}, Misspelling{title='June'}, Misspelling{title='Leicester'}, Misspelling{title='Los_Angeles'}, Misspelling{title='Luke's'}, Misspelling{title='Mayfield'}, Misspelling{title='Mediterranean'}, Misspelling{title='Miss'}, Misspelling{title='Monday'}, Misspelling{title='Mr'}, Misspelling{title='Mr'}, Misspelling{title='Mrs'}, Misspelling{title='Mrs'}, Misspelling{title='Mrs'}, Misspelling{title='Mrs'}, Misspelling{title='October'}, Misspelling{title='Pekinese'}, Misspelling{title='Philip's'}, Misspelling{title='Pyrenean'}, Misspelling{title='Rolls'}, Misspelling{title='Samaritan'}, Misspelling{title='Saturday'}, Misspelling{title='Saturday'}, Misspelling{title='Scots'}, Misspelling{title='Sept'}, Misspelling{title='September'}, Misspelling{title='September'}, Misspelling{title='Sheba'}, Misspelling{title='Spanish'}, Misspelling{title='Sunday'}, Misspelling{title='Swedish'}, Misspelling{title='Thames'}, Misspelling{title='Tuesday'}, Misspelling{title='Tuesday'}, Misspelling{title='Tuesday'}, Misspelling{title='Victorian'}, Misspelling{title='Wednesday'}, Misspelling{title='Wednesday'}, Misspelling{title='Wednesday'}, Misspelling{title='Welsh'}, Misspelling{title='X-rays'}, Misspelling{title='a'}, Misspelling{title='a'}, Misspelling{title='a'}, Misspelling{title='a'}, Misspelling{title='a'}, Misspelling{title='a'}, Misspelling{title='a'}, Misspelling{title='a-quiver'}, Misspelling{title='a_bit'}, Misspelling{title='a_few'}, Misspelling{title='a_little'}, Misspelling{title='a_little'}, Misspelling{title='a_long'}, Misspelling{title='a_look'}, Misspelling{title='a_lot'}, Misspelling{title='a_lot'}, Misspelling{title='a_lot'}, Misspelling{title='a_museum'}, Misspelling{title='abattoir'}, Misspelling{title='abhorrence'}, Misspelling{title='ability'}, Misspelling{title='ability'}, Misspelling{title='ability'}, Misspelling{title='ability'}, Misspelling{title='ability'}, Misspelling{title='able'}, Misspelling{title='able'}, Misspelling{title='able'}, Misspelling{title='able'}, Misspelling{title='able-bodied'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='about'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='above'}, Misspelling{title='abroad'}, Misspelling{title='abroad'}, Misspelling{title='abroad'}, Misspelling{title='absence'}, Misspelling{title='absence'}, Misspelling{title='absent'}, Misspelling{title='absolutely'}, Misspelling{title='absolutely'}, Misspelling{title='absolutely'}, Misspelling{title='absorbed'}, Misspelling{title='absorbent'}, Misspelling{title='absorption'}, Misspelling{title='abstract'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='absurd'}, Misspelling{title='abundance'}, Misspelling{title='abundant'}, Misspelling{title='abuse'}, Misspelling{title='academic'}, Misspelling{title='academic'}, Misspelling{title='academically'}, Misspelling{title='academically'}, Misspelling{title='accede'}, Misspelling{title='accelerate'}, Misspelling{title='accents'}, Misspelling{title='accept'}, Misspelling{title='accept'}, Misspelling{title='accept'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptable'}, Misspelling{title='acceptance'}, Misspelling{title='accepted'}, Misspelling{title='accepted'}, Misspelling{title='accepted'}, Misspelling{title='access'}, Misspelling{title='accessibility'}, Misspelling{title='accessible'}, Misspelling{title='accessible'}, Misspelling{title='accessing'}, Misspelling{title='accession'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accident'}, Misspelling{title='accidentally'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodate'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodation'}, Misspelling{title='accommodations'}, Misspelling{title='accompanied'}, Misspelling{title='accompaniment'}, Misspelling{title='accompany'}, Misspelling{title='accompanying'}, Misspelling{title='accompanying'}, Misspelling{title='accompanying'}, Misspelling{title='accompanying'}, Misspelling{title='accompanying'}, Misspelling{title='accompanying'}, Misspelling{title='accompanying'}, Misspelling{title='accompanying'}, Misspelling{title='accompanying... and 1108659 more bytes
```

### Then, we decompose the text into an n-gram trie:
Code from [TrieDemo.java:596](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L596) executed in 11.04 seconds: 
```java
      List<String> list = trainingList.stream().map(x -> "|"+x.getTitle()+"|").collect(Collectors.toList());
      CharTrie trie = CharTrieIndex.create(list, Integer.MAX_VALUE, 0);
      print(trie);
      return trie;
```
Returns: 
```
    com.simiacryptus.text.CharTrieIndex@e763ae73
```
Logging: 
```
    Total Indexed Document (KB): 357
    Total Node Count: 130810
    Total Index Memory Size (KB): 7168
    
```

Spelling check: opdr -> opened

Code from [TrieDemo.java:605](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L605) executed in 4.77 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling("|"+testArticle.getText()+"|");
```
Returns: 
```
    1.341071795009204
```
Logging: 
```
    START: "|opdr|"	16.42890
    IMPROVEMENT: "|opr|"	8.72965
    IMPROVEMENT: "|pr|"	4.91958
    IMPROVEMENT: "|par|"	5.32420
    IMPROVEMENT: "|ppar|"	5.17442
    IMPROVEMENT: "|appar|"	2.30390
    START: "|opdr|"	16.42890
    IMPROVEMENT: "|opd|"	8.42650
    IMPROVEMENT: "|pd|"	6.61109
    IMPROVEMENT: "|pr|"	4.91958
    IMPROVEMENT: "|pry|"	4.51748
    IMPROVEMENT: "|ppry|"	5.23435
    START: "|opdr|"	16.42890
    IMPROVEMENT: "|opr|"	8.72965
    IMPROVEMENT: "|pr|"	4.91958
    IMPROVEMENT: "|pry|"	4.51748
    IMPROVEMENT: "|ppry|"	5.23435
    IMPROVEMENT: "|ppary|"	3.95051
    START: "|opdr|"	16.42890
    IMPROVEMENT: "|opd|"	8.42650
    IMPROVEMENT: "|pd|"	6.61109
    IMPROVEMENT: "|d|"	3.43901
    IMPROVEMENT: "||"	1.46522
    IMPROVEMENT: "| |"	1.34107
    START: "|opdr|"	16.42890
    IMPROVEMENT: "|opr|"	8.72965
    IMPROVEMENT: "|op|"	6.43286
    START: "|opdr|"	16.42890
    IMPROVEMENT: "|oldr|"	7.68094
    IMPROVEMENT: "|older|"	6.92446
    WORD: "|older|"	6.92446
    START: "|opdr|"	16.42890
    IMPROVEMENT: "|opr|"	8.72965
    IMPROVEMENT: "|pr|"	4.91958
    IMPROVEMENT: "|pre|"	2.12914
    START: "|opdr|"	16.42890
    IMPROVEMENT: "|opur|"	12.69271
    IMPROVEMENT: "|our|"	1.44594
    WORD: "|our|"	1.44594
    START: "|opdr|"	16.42890
    IMPROVEMENT: "|opr|"	8.72965
    IMPROVEMENT: "|op|"	6.43286
    IMPROVEMENT: "||op|"	7.16547
    IMPROVEMENT: "||oph|"	7.90970
    IMPROVEMENT: "||orph|"	9.07161
    START: "|opdr|"	16.42890
    IMPROVEMENT: "|opr|"	8.72965
    IMPROVEMENT: "|op|"	6.43286
    
```

Spelling check: expencive -> expensive

Code from [TrieDemo.java:605](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L605) executed in 10.26 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling("|"+testArticle.getText()+"|");
```
Returns: 
```
    1.2962595847335616
```
Logging: 
```
    START: "|expencive|"	12.90272
    IMPROVEMENT: "|expencie|"	5.08955
    IMPROVEMENT: "|expenciee|"	4.68134
    IMPROVEMENT: "|expensciee|"	4.05926
    IMPROVEMENT: "|expenscieed|"	2.82484
    IMPROVEMENT: "|expensciened|"	1.29626
    START: "|expencive|"	12.90272
    IMPROVEMENT: "|expencie|"	5.08955
    IMPROVEMENT: "|expenice|"	4.96024
    IMPROVEMENT: "|expenite|"	1.65652
    START: "|expencive|"	12.90272
    IMPROVEMENT: "|expenciev|"	7.98806
    IMPROVEMENT: "|expencie|"	5.08955
    IMPROVEMENT: "|expencile|"	2.96039
    IMPROVEMENT: "|expencil|"	1.34243
    START: "|expencive|"	12.90272
    IMPROVEMENT: "|expenicve|"	10.84344
    IMPROVEMENT: "|expenive|"	5.60623
    IMPROVEMENT: "|expensive|"	1.64696
    WORD: "|expensive|"	1.64696
    START: "|expencive|"	12.90272
    IMPROVEMENT: "|expencilve|"	7.53042
    IMPROVEMENT: "|expencilver|"	6.37618
    IMPROVEMENT: "|expencilter|"	4.65350
    IMPROVEMENT: "|expencilther|"	3.19386
    IMPROVEMENT: "|expencilthern|"	2.54191
    START: "|expencive|"	12.90272
    IMPROVEMENT: "|expencine|"	6.00616
    IMPROVEMENT: "|expencipe|"	5.48260
    IMPROVEMENT: "|expencile|"	2.96039
    IMPROVEMENT: "|expencil|"	1.34243
    START: "|expencive|"	12.90272
    IMPROVEMENT: "|expenicve|"	10.84344
    IMPROVEMENT: "|expenive|"	5.60623
    IMPROVEMENT: "|expennive|"	5.32075
    IMPROVEMENT: "|expendive|"	4.59579
    IMPROVEMENT: "|expensive|"	1.64696
    START: "|expencive|"	12.90272
    IMPROVEMENT: "|expenive|"	5.60623
    IMPROVEMENT: "|expendive|"	4.59579
    IMPROVEMENT: "|expensive|"	1.64696
    WORD: "|expensive|"	1.64696
    START: "|expencive|"	12.90272
    IMPROVEMENT: "|expendive|"	4.59579
    IMPROVEMENT: "|expensive|"	1.64696
    WORD: "|expensive|"	1.64696
    START: "|expencive|"	12.90272
    IMPROVEMENT: "|expencieve|"	6.03341
    IMPROVEMENT: "|expenceive|"	5.92778
    IMPROVEMENT: "|expenceived|"	5.12359
    IMPROVEMENT: "|extenceived|"	4.48418
    IMPROVEMENT: "|exteenceived|"	4.23354
    
```

Spelling check: ectasy -> ecstasy

Code from [TrieDemo.java:605](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L605) executed in 6.52 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling("|"+testArticle.getText()+"|");
```
Returns: 
```
    0.03649069837640755
```
Logging: 
```
    START: "|ectasy|"	17.40754
    IMPROVEMENT: "|ecasy|"	12.89735
    IMPROVEMENT: "|eccasy|"	10.05132
    IMPROVEMENT: "|ccasy|"	8.33327
    IMPROVEMENT: "|ccesy|"	7.50730
    IMPROVEMENT: "|ccessy|"	7.99990
    START: "|ectasy|"	17.40754
    IMPROVEMENT: "|ectsy|"	11.52198
    IMPROVEMENT: "|ects|"	7.33820
    IMPROVEMENT: "|fects|"	4.82995
    IMPROVEMENT: "|ffects|"	3.07907
    IMPROVEMENT: "|affects|"	0.83806
    START: "|ectasy|"	17.40754
    IMPROVEMENT: "|eceasy|"	11.13652
    IMPROVEMENT: "|ceasy|"	4.90066
    IMPROVEMENT: "|creasy|"	5.21014
    IMPROVEMENT: "|crease|"	2.17590
    IMPROVEMENT: "|cease|"	1.38330
    START: "|ectasy|"	17.40754
    IMPROVEMENT: "|ecttasy|"	12.10680
    IMPROVEMENT: "|ecstasy|"	0.03649
    WORD: "|ecstasy|"	0.03649
    START: "|ectasy|"	17.40754
    IMPROVEMENT: "|cetasy|"	14.40930
    IMPROVEMENT: "|certasy|"	6.61060
    IMPROVEMENT: "|certarsy|"	5.50019
    IMPROVEMENT: "|certairsy|"	1.72959
    START: "|ectasy|"	17.40754
    IMPROVEMENT: "|ectsy|"	11.52198
    IMPROVEMENT: "|ectesy|"	11.04399
    IMPROVEMENT: "|ectersy|"	4.59734
    IMPROVEMENT: "|actersy|"	2.49437
    START: "|ectasy|"	17.40754
    IMPROVEMENT: "|ecasy|"	12.89735
    IMPROVEMENT: "|ecsay|"	11.08982
    IMPROVEMENT: "|ecstay|"	3.79849
    IMPROVEMENT: "|ecstary|"	2.02901
    IMPROVEMENT: "|ecstasy|"	0.03649
    START: "|ectasy|"	17.40754
    IMPROVEMENT: "|ecrtasy|"	12.66693
    IMPROVEMENT: "|certasy|"	6.61060
    IMPROVEMENT: "|certarsy|"	5.50019
    IMPROVEMENT: "|certary|"	4.43588
    IMPROVEMENT: "|cretary|"	3.55975
    START: "|ectasy|"	17.40754
    IMPROVEMENT: "|ecrtasy|"	12.66693
    IMPROVEMENT: "|ecretasy|"	7.49287
    IMPROVEMENT: "|recretasy|"	6.77877
    IMPROVEMENT: "|recretarsy|"	4.48972
    IMPROVEMENT: "|recretary|"	2.34894
    START: "|ectasy|"	17.40754
    IMPROVEMENT: "|eceasy|"	11.13652
    IMPROVEMENT: "|exceasy|"	7.72030
    IMPROVEMENT: "|excreasy|"	8.09504
    IMPROVEMENT: "|exccreasy|"	8.04488
    IMPROVEMENT: "|exccreast|"	7.63199
    
```

Spelling check: diemand -> diamond

Code from [TrieDemo.java:605](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L605) executed in 2.69 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling("|"+testArticle.getText()+"|");
```
Returns: 
```
    1.2581635903781863
```
Logging: 
```
    START: "|diemand|"	13.11948
    IMPROVEMENT: "|diemands|"	10.82049
    IMPROVEMENT: "|iemands|"	9.48830
    IMPROVEMENT: "|emands|"	3.32369
    IMPROVEMENT: "|demands|"	3.64087
    WORD: "|demands|"	3.64087
    START: "|diemand|"	13.11948
    IMPROVEMENT: "|idemand|"	9.84865
    IMPROVEMENT: "|idemands|"	6.38030
    IMPROVEMENT: "|demands|"	3.64087
    WORD: "|demands|"	3.64087
    START: "|diemand|"	13.11948
    IMPROVEMENT: "|idemand|"	9.84865
    IMPROVEMENT: "|demand|"	6.22718
    WORD: "|demand|"	6.22718
    START: "|diemand|"	13.11948
    IMPROVEMENT: "|demand|"	6.22718
    WORD: "|demand|"	6.22718
    START: "|diemand|"	13.11948
    IMPROVEMENT: "|demand|"	6.22718
    WORD: "|demand|"	6.22718
    START: "|diemand|"	13.11948
    IMPROVEMENT: "|diemands|"	10.82049
    IMPROVEMENT: "|idemands|"	6.38030
    IMPROVEMENT: "|demands|"	3.64087
    WORD: "|demands|"	3.64087
    START: "|diemand|"	13.11948
    IMPROVEMENT: "|idemand|"	9.84865
    IMPROVEMENT: "|demand|"	6.22718
    WORD: "|demand|"	6.22718
    START: "|diemand|"	13.11948
    IMPROVEMENT: "|idemand|"	9.84865
    IMPROVEMENT: "|idemands|"	6.38030
    IMPROVEMENT: "|demands|"	3.64087
    WORD: "|demands|"	3.64087
    START: "|diemand|"	13.11948
    IMPROVEMENT: "|demand|"	6.22718
    WORD: "|demand|"	6.22718
    START: "|diemand|"	13.11948
    IMPROVEMENT: "|idemand|"	9.84865
    IMPROVEMENT: "|indemand|"	7.55605
    IMPROVEMENT: "|indemands|"	1.25816
    
```

Spelling check: ecomics -> economic

Code from [TrieDemo.java:605](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L605) executed in 19.96 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling("|"+testArticle.getText()+"|");
```
Returns: 
```
    0.7224512673797828
```
Logging: 
```
    START: "|ecomics|"	10.51035
    IMPROVEMENT: "|ceomics|"	5.24781
    IMPROVEMENT: "|cesomics|"	5.71479
    IMPROVEMENT: "|ecesomics|"	5.03401
    IMPROVEMENT: "|ccesomics|"	4.41286
    IMPROVEMENT: "|ccessomics|"	3.62320
    START: "|ecomics|"	10.51035
    IMPROVEMENT: "|recomics|"	5.16425
    IMPROVEMENT: "|recommics|"	2.49970
    IMPROVEMENT: "|recommmics|"	2.52680
    START: "|ecomics|"	10.51035
    IMPROVEMENT: "|eomics|"	5.00520
    IMPROVEMENT: "|womics|"	4.40934
    IMPROVEMENT: "|romics|"	3.49678
    IMPROVEMENT: "|promics|"	2.63989
    START: "|ecomics|"	10.51035
    IMPROVEMENT: "|comics|"	6.74032
    IMPROVEMENT: "|c omics|"	6.07459
    IMPROVEMENT: "| omics|"	1.52369
    IMPROVEMENT: "| nomics|"	1.39136
    IMPROVEMENT: "| onomics|"	0.72245
    START: "|ecomics|"	10.51035
    IMPROVEMENT: "|ecommics|"	3.25433
    IMPROVEMENT: "|recommics|"	2.49970
    IMPROVEMENT: "|recommmics|"	2.52680
    START: "|ecomics|"	10.51035
    IMPROVEMENT: "|ecommics|"	3.25433
    IMPROVEMENT: "|recommics|"	2.49970
    IMPROVEMENT: "|recommmics|"	2.52680
    START: "|ecomics|"	10.51035
    IMPROVEMENT: "|eomics|"	5.00520
    IMPROVEMENT: "|neomics|"	2.96537
    IMPROVEMENT: "|nomics|"	1.41987
    IMPROVEMENT: "| nomics|"	1.39136
    START: "|ecomics|"	10.51035
    IMPROVEMENT: "|ec omics|"	7.57887
    IMPROVEMENT: "|e omics|"	6.16650
    IMPROVEMENT: "| omics|"	1.52369
    IMPROVEMENT: "| nomics|"	1.39136
    START: "|ecomics|"	10.51035
    IMPROVEMENT: "|ecommics|"	3.25433
    IMPROVEMENT: "|recommics|"	2.49970
    IMPROVEMENT: "|recommmics|"	2.52680
    START: "|ecomics|"	10.51035
    IMPROVEMENT: "|econics|"	3.78877
    IMPROVEMENT: "|econtics|"	1.58743
    IMPROVEMENT: "|secontics|"	1.53342
    
```

Spelling check: gornups -> groups

Code from [TrieDemo.java:605](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L605) executed in 1.73 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling("|"+testArticle.getText()+"|");
```
Returns: 
```
    -Infinity
```
Logging: 
```
    START: "|gornups|"	21.00150
    IMPROVEMENT: "|gornupts|"	22.36340
    IMPROVEMENT: "|gornputs|"	18.66057
    IMPROVEMENT: "|gronputs|"	16.74028
    IMPROVEMENT: "|grionputs|"	15.48870
    IMPROVEMENT: "|groinputs|"	9.62796
    START: "|gornups|"	21.00150
    IMPROVEMENT: "|ornups|"	17.17492
    IMPROVEMENT: "|orniups|"	17.66553
    IMPROVEMENT: "|ornius|"	7.73158
    IMPROVEMENT: "|orius|"	3.15843
    IMPROVEMENT: "|orium|"	1.60746
    START: "|gornups|"	21.00150
    IMPROVEMENT: "|ornups|"	17.17492
    IMPROVEMENT: "|ornupts|"	18.53681
    IMPROVEMENT: "|ornrupts|"	16.20419
    IMPROVEMENT: "|ournrupts|"	13.52403
    IMPROVEMENT: "|oursrupts|"	6.94358
    START: "|gornups|"	21.00150
    IMPROVEMENT: "|ornups|"	17.17492
    IMPROVEMENT: "|orn|ups|"	15.40259
    IMPROVEMENT: "|own|ups|"	11.44811
    IMPROVEMENT: "|own|us|"	9.05414
    IMPROVEMENT: "|own|es|"	5.74981
    START: "|gornups|"	21.00150
    IMPROVEMENT: "|ornups|"	17.17492
    IMPROVEMENT: "|orn|ups|"	15.40259
    IMPROVEMENT: "|ort|ups|"	14.94783
    IMPROVEMENT: "|org|ups|"	14.71307
    IMPROVEMENT: "|orig|ups|"	11.39379
    START: "|gornups|"	21.00150
    IMPROVEMENT: "|ornups|"	17.17492
    IMPROVEMENT: "|ournups|"	13.76352
    IMPROVEMENT: "|ournaups|"	14.90093
    IMPROVEMENT: "|ournaues|"	13.09042
    IMPROVEMENT: "|tournaues|"	7.70277
    START: "|gornups|"	21.00150
    IMPROVEMENT: "|gronups|"	20.48487
    IMPROVEMENT: "|gronus|"	13.31162
    IMPROVEMENT: "|grronus|"	11.42744
    IMPROVEMENT: "|grrouns|"	8.87828
    IMPROVEMENT: "|rrouns|"	5.89413
    START: "|gornups|"	21.00150
    IMPROVEMENT: "|goornups|"	22.74170
    IMPROVEMENT: "|gooknups|"	17.95334
    IMPROVEMENT: "|booknups|"	15.03158
    IMPROVEMENT: "|bookinups|"	12.14173
    IMPROVEMENT: "|bookinus|"	8.23398
    START: "|gornups|"	21.00150
    IMPROVEMENT: "|gorn|ups|"	19.21391
    IMPROVEMENT: "|forn|ups|"	15.85597
    IMPROVEMENT: "|for|ups|"	12.80825
    IMPROVEMENT: "|for|cups|"	11.64058
    IMPROVEMENT: "|for| cups|"	-Infinity
    START: "|gornups|"	21.00150
    IMPROVEMENT: "|gorniups|"	20.67136
    IMPROVEMENT: "|gornius|"	10.73742
    IMPROVEMENT: "|ornius|"	7.73158
    IMPROVEMENT: "|ordius|"	6.29437
    IMPROVEMENT: "|aordius|"	5.33713
    
```

Spelling check: prefect -> prefects

Code from [TrieDemo.java:605](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L605) executed in 2.41 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling("|"+testArticle.getText()+"|");
```
Returns: 
```
    0.4471889514795643
```
Logging: 
```
    START: "|prefect|"	6.16145
    IMPROVEMENT: "|perfect|"	0.51134
    WORD: "|perfect|"	0.51134
    START: "|prefect|"	6.16145
    IMPROVEMENT: "|preject|"	1.12182
    IMPROVEMENT: "|prejects|"	0.90807
    IMPROVEMENT: "|projects|"	0.44719
    WORD: "|projects|"	0.44719
    START: "|prefect|"	6.16145
    IMPROVEMENT: "|perfect|"	0.51134
    WORD: "|perfect|"	0.51134
    START: "|prefect|"	6.16145
    IMPROVEMENT: "|perfect|"	0.51134
    WORD: "|perfect|"	0.51134
    START: "|prefect|"	6.16145
    IMPROVEMENT: "|refect|"	4.43402
    IMPROVEMENT: "|refects|"	3.46695
    IMPROVEMENT: "|reffects|"	3.74774
    IMPROVEMENT: "|reaffects|"	1.65403
    START: "|prefect|"	6.16145
    WORD: "|prefect|"	6.16145
    START: "|prefect|"	6.16145
    WORD: "|prefect|"	6.16145
    START: "|prefect|"	6.16145
    WORD: "|prefect|"	6.16145
    START: "|prefect|"	6.16145
    IMPROVEMENT: "|perfect|"	0.51134
    WORD: "|perfect|"	0.51134
    START: "|prefect|"	6.16145
    IMPROVEMENT: "|prefects|"	4.74887
    WORD: "|prefects|"	4.74887
    
```

Spelling check: darch -> dark

Code from [TrieDemo.java:605](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L605) executed in 3.43 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling("|"+testArticle.getText()+"|");
```
Returns: 
```
    0.051431821906846927
```
Logging: 
```
    START: "|darch|"	8.92919
    IMPROVEMENT: "|march|"	6.93030
    WORD: "|march|"	6.93030
    START: "|darch|"	8.92919
    IMPROVEMENT: "|darchy|"	8.46536
    IMPROVEMENT: "|carchy|"	5.67545
    IMPROVEMENT: "|scarchy|"	4.27279
    IMPROVEMENT: "|scarchey|"	3.99197
    IMPROVEMENT: "|scarchedy|"	2.06306
    START: "|darch|"	8.92919
    IMPROVEMENT: "|darth|"	5.93142
    IMPROVEMENT: "|dart|"	3.43662
    WORD: "|dart|"	3.43662
    START: "|darch|"	8.92919
    IMPROVEMENT: "|darchy|"	8.46536
    IMPROVEMENT: "|darthy|"	6.51237
    IMPROVEMENT: "|parthy|"	5.35560
    IMPROVEMENT: "|party|"	3.34193
    WORD: "|party|"	3.34193
    START: "|darch|"	8.92919
    IMPROVEMENT: "|darchy|"	8.46536
    IMPROVEMENT: "|darthy|"	6.51237
    IMPROVEMENT: "|parthy|"	5.35560
    IMPROVEMENT: "|party|"	3.34193
    WORD: "|party|"	3.34193
    START: "|darch|"	8.92919
    IMPROVEMENT: "|dartch|"	9.34419
    IMPROVEMENT: "|dratch|"	6.35158
    IMPROVEMENT: "|fratch|"	4.25190
    IMPROVEMENT: "|efratch|"	3.99956
    IMPROVEMENT: "|afratch|"	1.65945
    START: "|darch|"	8.92919
    IMPROVEMENT: "|darth|"	5.93142
    IMPROVEMENT: "|earth|"	0.05143
    WORD: "|earth|"	0.05143
    START: "|darch|"	8.92919
    IMPROVEMENT: "|datch|"	8.74866
    IMPROVEMENT: "|diatch|"	5.39244
    IMPROVEMENT: "|ediatch|"	4.49615
    IMPROVEMENT: "|editch|"	2.34376
    START: "|darch|"	8.92919
    IMPROVEMENT: "|earch|"	2.93028
    IMPROVEMENT: "|earth|"	0.05143
    WORD: "|earth|"	0.05143
    START: "|darch|"	8.92919
    IMPROVEMENT: "|dearch|"	6.12050
    IMPROVEMENT: "|earch|"	2.93028
    IMPROVEMENT: "|earth|"	0.05143
    WORD: "|earth|"	0.05143
    
```

Spelling check: ciclogy -> psychology

Code from [TrieDemo.java:605](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L605) executed in 15.19 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling("|"+testArticle.getText()+"|");
```
Returns: 
```
    1.1075802987305812
```
Logging: 
```
    START: "|ciclogy|"	11.42785
    IMPROVEMENT: "|ccilogy|"	8.97993
    IMPROVEMENT: "|cacilogy|"	6.58020
    IMPROVEMENT: "|accilogy|"	5.07205
    IMPROVEMENT: "|accrilogy|"	2.89097
    IMPROVEMENT: "|accrology|"	1.99329
    START: "|ciclogy|"	11.42785
    IMPROVEMENT: "|eciclogy|"	8.37918
    IMPROVEMENT: "|ecilogy|"	5.09005
    IMPROVEMENT: "|epilogy|"	2.57830
    START: "|ciclogy|"	11.42785
    IMPROVEMENT: "|chiclogy|"	8.12738
    IMPROVEMENT: "|chilogy|"	4.27448
    IMPROVEMENT: "|chailogy|"	3.40792
    IMPROVEMENT: "|chairlogy|"	1.36432
    START: "|ciclogy|"	11.42785
    IMPROVEMENT: "|cinclogy|"	5.21135
    IMPROVEMENT: "|inclogy|"	2.71074
    IMPROVEMENT: "|inlogy|"	2.10202
    START: "|ciclogy|"	11.42785
    IMPROVEMENT: "|ccilogy|"	8.97993
    IMPROVEMENT: "|accilogy|"	5.07205
    IMPROVEMENT: "|accailogy|"	3.79453
    IMPROVEMENT: "|accialogy|"	3.50157
    IMPROVEMENT: "|accualogy|"	2.73533
    START: "|ciclogy|"	11.42785
    IMPROVEMENT: "|cuiclogy|"	8.66036
    IMPROVEMENT: "|criclogy|"	5.67067
    IMPROVEMENT: "|circlogy|"	1.11017
    START: "|ciclogy|"	11.42785
    IMPROVEMENT: "|caclogy|"	7.16002
    IMPROVEMENT: "|acclogy|"	3.04508
    IMPROVEMENT: "|accology|"	1.35801
    START: "|ciclogy|"	11.42785
    IMPROVEMENT: "|circlogy|"	1.11017
    IMPROVEMENT: "|circllogy|"	1.18901
    IMPROVEMENT: "|circullogy|"	1.10758
    START: "|ciclogy|"	11.42785
    IMPROVEMENT: "|ciplogy|"	5.89543
    IMPROVEMENT: "| ciplogy|"	4.37484
    IMPROVEMENT: "| nciplogy|"	3.86617
    IMPROVEMENT: "| ncialogy|"	3.10960
    IMPROVEMENT: "| cialogy|"	2.43396
    START: "|ciclogy|"	11.42785
    IMPROVEMENT: "|ciplogy|"	5.89543
    IMPROVEMENT: "| ciplogy|"	4.37484
    IMPROVEMENT: "| cialogy|"	2.43396
    
```

Spelling check: pershion -> precision

Code from [TrieDemo.java:605](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L605) executed in 13.84 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling("|"+testArticle.getText()+"|");
```
Returns: 
```
    0.5417282849115871
```
Logging: 
```
    START: "|pershion|"	12.09393
    IMPROVEMENT: "|pershino|"	11.83434
    IMPROVEMENT: "|pershine|"	9.18557
    IMPROVEMENT: "|preshine|"	8.81125
    IMPROVEMENT: "|prehine|"	4.59584
    IMPROVEMENT: "| prehine|"	4.92198
    START: "|pershion|"	12.09393
    IMPROVEMENT: "|perishion|"	0.89064
    IMPROVEMENT: "|perishione|"	0.54173
    START: "|pershion|"	12.09393
    IMPROVEMENT: "|pershin|"	10.84656
    IMPROVEMENT: "|preshin|"	8.51850
    IMPROVEMENT: "|prehin|"	5.36041
    IMPROVEMENT: "|prehind|"	1.46532
    START: "|pershion|"	12.09393
    IMPROVEMENT: "|pershin|"	10.84656
    IMPROVEMENT: "|pership|"	3.11164
    START: "|pershion|"	12.09393
    IMPROVEMENT: "|preshion|"	8.44584
    IMPROVEMENT: "|reshion|"	7.13787
    IMPROVEMENT: "|resion|"	5.71979
    IMPROVEMENT: "|reson|"	3.57664
    IMPROVEMENT: "|resons|"	3.21381
    START: "|pershion|"	12.09393
    IMPROVEMENT: "|perspion|"	2.11416
    IMPROVEMENT: "|perspione|"	1.07216
    START: "|pershion|"	12.09393
    IMPROVEMENT: "|perspion|"	2.11416
    IMPROVEMENT: "|perspione|"	1.07216
    START: "|pershion|"	12.09393
    IMPROVEMENT: "|persheion|"	11.51969
    IMPROVEMENT: "|presheion|"	7.97645
    IMPROVEMENT: "|epresheion|"	5.38142
    IMPROVEMENT: "|epresheian|"	5.34474
    IMPROVEMENT: "|epreseian|"	3.69841
    START: "|pershion|"	12.09393
    IMPROVEMENT: "|perstion|"	7.09282
    IMPROVEMENT: "|perstionn|"	2.90867
    IMPROVEMENT: "|perstionne|"	2.88350
    START: "|pershion|"	12.09393
    IMPROVEMENT: "|persion|"	2.90593
    IMPROVEMENT: "|perspion|"	2.11416
    IMPROVEMENT: "|perspione|"	1.07216
    
```

Spelling check: cear -> care

Code from [TrieDemo.java:605](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L605) executed in 6.36 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling("|"+testArticle.getText()+"|");
```
Returns: 
```
    0.020936322453639866
```
Logging: 
```
    START: "|cear|"	5.55793
    IMPROVEMENT: "|rear|"	4.58143
    IMPROVEMENT: "|reard|"	4.35871
    IMPROVEMENT: "|eard|"	2.92314
    IMPROVEMENT: "|seard|"	3.02317
    IMPROVEMENT: "|heard|"	0.53640
    START: "|cear|"	5.55793
    IMPROVEMENT: "|cer|"	4.44809
    IMPROVEMENT: "|ucer|"	4.39157
    IMPROVEMENT: "|ucher|"	4.85637
    IMPROVEMENT: "|oucher|"	4.40435
    IMPROVEMENT: "|outher|"	1.51689
    START: "|cear|"	5.55793
    IMPROVEMENT: "|cer|"	4.44809
    IMPROVEMENT: "|ce|"	3.31698
    IMPROVEMENT: "|ace|"	3.25569
    IMPROVEMENT: "|acce|"	3.52867
    IMPROVEMENT: "|accce|"	3.91801
    START: "|cear|"	5.55793
    IMPROVEMENT: "|cer|"	4.44809
    IMPROVEMENT: "|ucer|"	4.39157
    IMPROVEMENT: "|sucer|"	1.99456
    IMPROVEMENT: "|saucer|"	0.02094
    WORD: "|saucer|"	0.02094
    START: "|cear|"	5.55793
    IMPROVEMENT: "|cer|"	4.44809
    IMPROVEMENT: "|ce|"	3.31698
    IMPROVEMENT: "||ce|"	4.04959
    IMPROVEMENT: "||cle|"	4.85116
    START: "|cear|"	5.55793
    IMPROVEMENT: "|ceard|"	5.67982
    IMPROVEMENT: "|eard|"	2.92314
    IMPROVEMENT: "|earn|"	2.91566
    WORD: "|earn|"	2.91566
    START: "|cear|"	5.55793
    IMPROVEMENT: "|clear|"	5.14176
    WORD: "|clear|"	5.14176
    START: "|cear|"	5.55793
    IMPROVEMENT: "|cer|"	4.44809
    IMPROVEMENT: "|ce|"	3.31698
    IMPROVEMENT: "|ace|"	3.25569
    IMPROVEMENT: "|acce|"	3.52867
    IMPROVEMENT: "|accce|"	3.91801
    START: "|cear|"	5.55793
    IMPROVEMENT: "|cer|"	4.44809
    IMPROVEMENT: "|ce|"	3.31698
    IMPROVEMENT: "|re|"	2.91004
    IMPROVEMENT: "|pre|"	2.12914
    START: "|cear|"	5.55793
    IMPROVEMENT: "|cer|"	4.44809
    IMPROVEMENT: "|ce|"	3.31698
    IMPROVEMENT: "|cle|"	4.11855
    
```

Spelling check: ecknowledgment -> acknowledgement

Code from [TrieDemo.java:605](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L605) executed in 25.36 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling("|"+testArticle.getText()+"|");
```
Returns: 
```
    0.016313575491523794
```
Logging: 
```
    START: "|ecknowledgment|"	5.66713
    IMPROVEMENT: "|cknowledgment|"	3.72115
    IMPROVEMENT: "|cknowledgent|"	2.69924
    IMPROVEMENT: "|cknowledgient|"	2.00537
    IMPROVEMENT: "|cknowledgnient|"	1.23432
    IMPROVEMENT: "|acknowledgnient|"	0.47169
    START: "|ecknowledgment|"	5.66713
    IMPROVEMENT: "|cknowledgment|"	3.72115
    IMPROVEMENT: "|cknowledgent|"	2.69924
    IMPROVEMENT: "|cknowledent|"	2.13494
    START: "|ecknowledgment|"	5.66713
    IMPROVEMENT: "|acknowledgment|"	2.95852
    IMPROVEMENT: "|acknowledgement|"	0.01631
    WORD: "|acknowledgement|"	0.01631
    START: "|ecknowledgment|"	5.66713
    IMPROVEMENT: "|cknowledgment|"	3.72115
    IMPROVEMENT: "|cknowledgient|"	2.00537
    IMPROVEMENT: "|cknowledgnient|"	1.23432
    IMPROVEMENT: "|acknowledgnient|"	0.47169
    START: "|ecknowledgment|"	5.66713
    IMPROVEMENT: "|ecknowledgient|"	3.95134
    IMPROVEMENT: "|cknowledgient|"	2.00537
    IMPROVEMENT: "|cknowledginent|"	1.23581
    IMPROVEMENT: "|cknowledgnient|"	1.23432
    IMPROVEMENT: "|acknowledgnient|"	0.47169
    START: "|ecknowledgment|"	5.66713
    IMPROVEMENT: "|ecknowledgient|"	3.95134
    IMPROVEMENT: "|cknowledgient|"	2.00537
    IMPROVEMENT: "|cknowledginent|"	1.23581
    IMPROVEMENT: "|cknowledgnient|"	1.23432
    START: "|ecknowledgment|"	5.66713
    IMPROVEMENT: "|cknowledgment|"	3.72115
    IMPROVEMENT: "|cknowledgient|"	2.00537
    IMPROVEMENT: "|cknowledginent|"	1.23581
    IMPROVEMENT: "|cknowledgnient|"	1.23432
    START: "|ecknowledgment|"	5.66713
    IMPROVEMENT: "|ecknowledgement|"	2.72593
    IMPROVEMENT: "|cknowledgement|"	0.77996
    START: "|ecknowledgment|"	5.66713
    IMPROVEMENT: "|ecknowledgent|"	4.64522
    IMPROVEMENT: "|cknowledgent|"	2.69924
    IMPROVEMENT: "|cknowledgient|"	2.00537
    IMPROVEMENT: "|cknowledginent|"	1.23581
    IMPROVEMENT: "|cknowledgnient|"	1.23432
    START: "|ecknowledgment|"	5.66713
    IMPROVEMENT: "|ecknowledgement|"	2.72593
    IMPROVEMENT: "|cknowledgement|"	0.77996
    IMPROVEMENT: "|acknowledgement|"	0.01631
    WORD: "|acknowledgement|"	0.01631
    
```

Spelling check: womem -> women

Code from [TrieDemo.java:605](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L605) executed in 5.84 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling("|"+testArticle.getText()+"|");
```
Returns: 
```
    -Infinity
```
Logging: 
```
    START: "|womem|"	8.79104
    IMPROVEMENT: "|womme|"	7.82360
    IMPROVEMENT: "|wommen|"	6.54112
    IMPROVEMENT: "|women|"	0.49467
    WORD: "|women|"	0.49467
    START: "|womem|"	8.79104
    IMPROVEMENT: "|womme|"	7.82360
    IMPROVEMENT: "|wimme|"	4.39890
    IMPROVEMENT: "|imme|"	2.61993
    START: "|womem|"	8.79104
    IMPROVEMENT: "|womemb|"	6.10748
    START: "|womem|"	8.79104
    IMPROVEMENT: "|womme|"	7.82360
    IMPROVEMENT: "|wimme|"	4.39890
    IMPROVEMENT: "|swimme|"	2.29890
    IMPROVEMENT: "|swimm|"	1.88484
    START: "|womem|"	8.79104
    IMPROVEMENT: "|wome|"	6.72635
    IMPROVEMENT: "|wome |"	7.11995
    IMPROVEMENT: "|women |"	4.69661
    IMPROVEMENT: "|women|"	0.49467
    WORD: "|women|"	0.49467
    START: "|womem|"	8.79104
    IMPROVEMENT: "|womme|"	7.82360
    IMPROVEMENT: "|wommen|"	6.54112
    IMPROVEMENT: "|sommen|"	6.08585
    IMPROVEMENT: "|somen|"	3.08750
    IMPROVEMENT: "|someon|"	0.92898
    START: "|womem|"	8.79104
    IMPROVEMENT: "|womemn|"	8.25539
    IMPROVEMENT: "|womern|"	7.11098
    IMPROVEMENT: "|womer|"	6.00922
    IMPROVEMENT: "|womera|"	3.73172
    IMPROVEMENT: "|somera|"	2.95805
    START: "|womem|"	8.79104
    IMPROVEMENT: "|womme|"	7.82360
    IMPROVEMENT: "|wommen|"	6.54112
    IMPROVEMENT: "|women|"	0.49467
    WORD: "|women|"	0.49467
    START: "|womem|"	8.79104
    IMPROVEMENT: "|wome|"	6.72635
    IMPROVEMENT: "|wome |"	7.11995
    IMPROVEMENT: "|wome| |"	-Infinity
    START: "|womem|"	8.79104
    IMPROVEMENT: "|womme|"	7.82360
    IMPROVEMENT: "|woomme|"	8.29564
    IMPROVEMENT: "|coomme|"	6.00719
    IMPROVEMENT: "|croomme|"	5.88061
    IMPROVEMENT: "|sroomme|"	4.25191
    
```

Spelling check: taked -> taken

Code from [TrieDemo.java:605](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L605) executed in 5.77 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling("|"+testArticle.getText()+"|");
```
Returns: 
```
    1.3721068251001167
```
Logging: 
```
    START: "|taked|"	5.38563
    IMPROVEMENT: "|taket|"	5.38017
    IMPROVEMENT: "|takent|"	4.06643
    IMPROVEMENT: "|taken|"	1.37211
    WORD: "|taken|"	1.37211
    START: "|taked|"	5.38563
    IMPROVEMENT: "|talked|"	3.11992
    WORD: "|talked|"	3.11992
    START: "|taked|"	5.38563
    IMPROVEMENT: "|taket|"	5.38017
    IMPROVEMENT: "|takets|"	3.51495
    IMPROVEMENT: "|stakets|"	2.83249
    IMPROVEMENT: "|estakets|"	2.97890
    IMPROVEMENT: "|restakets|"	1.89600
    START: "|taked|"	5.38563
    IMPROVEMENT: "|takeed|"	5.55803
    IMPROVEMENT: "|takened|"	2.84132
    START: "|taked|"	5.38563
    IMPROVEMENT: "|takeed|"	5.55803
    IMPROVEMENT: "|takened|"	2.84132
    START: "|taked|"	5.38563
    IMPROVEMENT: "|takeed|"	5.55803
    IMPROVEMENT: "|takened|"	2.84132
    START: "|taked|"	5.38563
    IMPROVEMENT: "|talked|"	3.11992
    WORD: "|talked|"	3.11992
    START: "|taked|"	5.38563
    IMPROVEMENT: "||taked|"	6.11824
    IMPROVEMENT: "||talked|"	3.85253
    IMPROVEMENT: "|talked|"	3.11992
    WORD: "|talked|"	3.11992
    START: "|taked|"	5.38563
    IMPROVEMENT: "|talked|"	3.11992
    WORD: "|talked|"	3.11992
    START: "|taked|"	5.38563
    IMPROVEMENT: "||taked|"	6.11824
    IMPROVEMENT: "||talked|"	3.85253
    IMPROVEMENT: "|talked|"	3.11992
    WORD: "|talked|"	3.11992
    
```

Spelling check: useualy -> unusually

Code from [TrieDemo.java:605](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L605) executed in 5.67 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling("|"+testArticle.getText()+"|");
```
Returns: 
```
    0.41217019795535315
```
Logging: 
```
    START: "|useualy|"	11.35319
    IMPROVEMENT: "|useual|"	6.34815
    IMPROVEMENT: "|usual|"	2.83566
    WORD: "|usual|"	2.83566
    START: "|useualy|"	11.35319
    IMPROVEMENT: "|useual|"	6.34815
    IMPROVEMENT: "|usual|"	2.83566
    WORD: "|usual|"	2.83566
    START: "|useualy|"	11.35319
    IMPROVEMENT: "|useual|"	6.34815
    IMPROVEMENT: "|museual|"	3.40885
    START: "|useualy|"	11.35319
    IMPROVEMENT: "|useuly|"	9.24237
    IMPROVEMENT: "|useudly|"	6.11158
    IMPROVEMENT: "|useurly|"	6.00730
    IMPROVEMENT: "|useurry|"	5.66086
    IMPROVEMENT: "|useurre|"	4.89546
    START: "|useualy|"	11.35319
    IMPROVEMENT: "|pseualy|"	10.09714
    IMPROVEMENT: "|pseuly|"	7.21250
    IMPROVEMENT: "|pseully|"	5.66160
    IMPROVEMENT: "|pseudly|"	1.57542
    START: "|useualy|"	11.35319
    IMPROVEMENT: "|useual|"	6.34815
    IMPROVEMENT: "|usefual|"	3.86680
    IMPROVEMENT: "|useful|"	0.80141
    WORD: "|useful|"	0.80141
    START: "|useualy|"	11.35319
    IMPROVEMENT: "|usualy|"	4.30471
    IMPROVEMENT: "|usually|"	1.92716
    WORD: "|usually|"	1.92716
    START: "|useualy|"	11.35319
    IMPROVEMENT: "|useual|"	6.34815
    IMPROVEMENT: "|usual|"	2.83566
    WORD: "|usual|"	2.83566
    START: "|useualy|"	11.35319
    IMPROVEMENT: "|usefualy|"	8.29913
    IMPROVEMENT: "|usefulaly|"	7.68957
    IMPROVEMENT: "|usefullly|"	1.13167
    IMPROVEMENT: "|usefully|"	0.41217
    START: "|useualy|"	11.35319
    IMPROVEMENT: "|useuly|"	9.24237
    IMPROVEMENT: "|usely|"	6.89770
    IMPROVEMENT: "|tsely|"	6.75549
    IMPROVEMENT: "|stsely|"	4.67881
    IMPROVEMENT: "|strely|"	4.21687
    
```

Spelling check: gloued -> glowed

Code from [TrieDemo.java:605](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L605) executed in 4.16 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling("|"+testArticle.getText()+"|");
```
Returns: 
```
    1.0113923712161124
```
Logging: 
```
    START: "|gloued|"	14.45533
    IMPROVEMENT: "|glosed|"	7.39949
    IMPROVEMENT: "|gloosed|"	5.43482
    IMPROVEMENT: "|gloose|"	4.34146
    IMPROVEMENT: "|gloorse|"	2.38879
    IMPROVEMENT: "|bloorse|"	1.97118
    START: "|gloued|"	14.45533
    IMPROVEMENT: "|gloudd|"	11.12187
    IMPROVEMENT: "|gloud|"	5.99626
    IMPROVEMENT: "|loud|"	1.06077
    WORD: "|loud|"	1.06077
    START: "|gloued|"	14.45533
    IMPROVEMENT: "|gloured|"	5.90884
    IMPROVEMENT: "|loured|"	3.53988
    IMPROVEMENT: "| loured|"	3.63619
    START: "|gloued|"	14.45533
    IMPROVEMENT: "|gloud|"	5.99626
    IMPROVEMENT: "|cloud|"	1.01139
    WORD: "|cloud|"	1.01139
    START: "|gloued|"	14.45533
    IMPROVEMENT: "|glound|"	7.75899
    IMPROVEMENT: "|gloond|"	4.15450
    IMPROVEMENT: "|glood|"	3.46099
    IMPROVEMENT: "|flood|"	2.22802
    WORD: "|flood|"	2.22802
    START: "|gloued|"	14.45533
    IMPROVEMENT: "|gloude|"	5.81681
    IMPROVEMENT: "|loude|"	1.22176
    START: "|gloued|"	14.45533
    IMPROVEMENT: "|gloured|"	5.90884
    IMPROVEMENT: "|gloure|"	5.12613
    IMPROVEMENT: "|glouvre|"	3.32125
    IMPROVEMENT: "|louvre|"	1.12719
    WORD: "|louvre|"	1.12719
    START: "|gloued|"	14.45533
    IMPROVEMENT: "|gloned|"	6.40355
    IMPROVEMENT: "|glooned|"	4.56897
    IMPROVEMENT: "|gloonel|"	4.01107
    IMPROVEMENT: "|gloonsel|"	3.42961
    IMPROVEMENT: "|gloonsely|"	2.78751
    START: "|gloued|"	14.45533
    IMPROVEMENT: "|glouued|"	14.44780
    IMPROVEMENT: "|gloyued|"	12.98194
    IMPROVEMENT: "|gloyed|"	7.67641
    IMPROVEMENT: "|gloyeed|"	7.37360
    IMPROVEMENT: "|loyeed|"	4.87422
    START: "|gloued|"	14.45533
    IMPROVEMENT: "|glued|"	11.13358
    IMPROVEMENT: "|gled|"	9.15325
    IMPROVEMENT: "|glad|"	7.87104
    WORD: "|glad|"	7.87104
    
```

Spelling check: magnivisont -> magnificent

Code from [TrieDemo.java:605](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L605) executed in 15.97 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling("|"+testArticle.getText()+"|");
```
Returns: 
```
    0.9748218250218429
```
Logging: 
```
    START: "|magnivisont|"	17.84536
    IMPROVEMENT: "|magnivisontr|"	12.78673
    IMPROVEMENT: "|magnivoisontr|"	9.06145
    IMPROVEMENT: "|magnivoisountr|"	7.75258
    IMPROVEMENT: "|magnivoisounts|"	6.70296
    IMPROVEMENT: "|magnivoisount|"	5.92038
    START: "|magnivisont|"	17.84536
    IMPROVEMENT: "|magnivison|"	5.89499
    IMPROVEMENT: "|magnivoison|"	1.91082
    START: "|magnivisont|"	17.84536
    IMPROVEMENT: "|magnivison|"	5.89499
    IMPROVEMENT: "|magnivoison|"	1.91082
    IMPROVEMENT: "|magnizvoison|"	2.02331
    IMPROVEMENT: "|magnizvoision|"	1.69648
    IMPROVEMENT: "|magnizvoirsion|"	1.51654
    START: "|magnivisont|"	17.84536
    IMPROVEMENT: "|magnivisonot|"	11.01415
    IMPROVEMENT: "|magnioisonot|"	7.77315
    IMPROVEMENT: "|magnioisonout|"	5.50753
    IMPROVEMENT: "|magnioisonoun|"	5.44995
    IMPROVEMENT: "|magnioisonourn|"	5.63223
    START: "|magnivisont|"	17.84536
    IMPROVEMENT: "|magniovisont|"	15.06895
    IMPROVEMENT: "|magniovisonte|"	9.82336
    IMPROVEMENT: "|magniovisionte|"	7.70119
    IMPROVEMENT: "|magnivoisionte|"	5.27233
    IMPROVEMENT: "|magnivoistionte|"	5.33923
    START: "|magnivisont|"	17.84536
    IMPROVEMENT: "|magnivison|"	5.89499
    IMPROVEMENT: "|magnivoison|"	1.91082
    IMPROVEMENT: "|magninoison|"	0.97482
    START: "|magnivisont|"	17.84536
    IMPROVEMENT: "|magnivisint|"	11.76642
    IMPROVEMENT: "|magnivisit|"	5.25777
    IMPROVEMENT: "|magnvisit|"	4.39134
    IMPROVEMENT: "|magnvisite|"	3.78718
    IMPROVEMENT: "|magnvisited|"	1.48453
    START: "|magnivisont|"	17.84536
    IMPROVEMENT: "|magnivisint|"	11.76642
    IMPROVEMENT: "|magnivisit|"	5.25777
    IMPROVEMENT: "|magnvisit|"	4.39134
    IMPROVEMENT: "|magnvisite|"	3.78718
    IMPROVEMENT: "|magnvisited|"	1.48453
    START: "|magnivisont|"	17.84536
    IMPROVEMENT: "|magnivisornt|"	12.09026
    IMPROVEMENT: "|magnivisorant|"	11.95135
    IMPROVEMENT: "|magnivisorarnt|"	9.63111
    IMPROVEMENT: "|magni visorarnt|"	9.54431
    IMPROVEMENT: "|magnidvisorarnt|"	7.53384
    START: "|magnivisont|"	17.84536
    IMPROVEMENT: "|magnivilont|"	15.74234
    IMPROVEMENT: "|magnivilount|"	8.79512
    IMPROVEMENT: "|magnivelount|"	6.41675
    IMPROVEMENT: "|magnivelouent|"	5.86056
    IMPROVEMENT: "|magniveloucent|"	4.79238
    
```

Spelling check: vacentscy -> vacancy

Code from [TrieDemo.java:605](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L605) executed in 2.87 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling("|"+testArticle.getText()+"|");
```
Returns: 
```
    -Infinity
```
Logging: 
```
    START: "|vacentscy|"	20.01099
    IMPROVEMENT: "|valentscy|"	11.57640
    IMPROVEMENT: "|talentscy|"	8.47287
    IMPROVEMENT: "|talentsy|"	4.97827
    IMPROVEMENT: "|talents|"	2.76737
    WORD: "|talents|"	2.76737
    START: "|vacentscy|"	20.01099
    IMPROVEMENT: "|vacantscy|"	17.88313
    IMPROVEMENT: "|vacantsch|"	17.84886
    IMPROVEMENT: "|vacanstch|"	8.54517
    IMPROVEMENT: "|vacansch|"	7.74567
    IMPROVEMENT: "|vacanach|"	6.71539
    START: "|vacentscy|"	20.01099
    IMPROVEMENT: "|vacentsy|"	18.07033
    IMPROVEMENT: "|vacenty|"	12.65283
    IMPROVEMENT: "|vaclenty|"	8.74316
    IMPROVEMENT: "|aclenty|"	7.32293
    IMPROVEMENT: "|calenty|"	2.18845
    START: "|vacentscy|"	20.01099
    IMPROVEMENT: "|vac entscy|"	16.40545
    IMPROVEMENT: "|vac entsy|"	14.34334
    IMPROVEMENT: "|vac ents|"	8.78680
    IMPROVEMENT: "|ac ents|"	7.30066
    IMPROVEMENT: "|ac| ents|"	-Infinity
    START: "|vacentscy|"	20.01099
    IMPROVEMENT: "|vacentcy|"	16.51450
    IMPROVEMENT: "|vac entcy|"	13.19018
    IMPROVEMENT: "|ac entcy|"	11.70404
    IMPROVEMENT: "|ac eptcy|"	6.99407
    IMPROVEMENT: "|a ceptcy|"	6.35848
    START: "|vacentscy|"	20.01099
    IMPROVEMENT: "|vaccentscy|"	12.23466
    IMPROVEMENT: "|accentscy|"	7.16198
    IMPROVEMENT: "|accentsy|"	4.95573
    IMPROVEMENT: "|accents|"	2.75070
    WORD: "|accents|"	2.75070
    START: "|vacentscy|"	20.01099
    IMPROVEMENT: "|vacentsyc|"	19.08986
    IMPROVEMENT: "|vacentsycl|"	16.33886
    IMPROVEMENT: "|vac entsycl|"	15.14375
    IMPROVEMENT: "|vac entsycle|"	9.52154
    IMPROVEMENT: "|ac entsycle|"	8.03540
    START: "|vacentscy|"	20.01099
    IMPROVEMENT: "|vacetscy|"	17.33989
    IMPROVEMENT: "|vacetcy|"	14.74053
    IMPROVEMENT: "|vacety|"	11.93344
    IMPROVEMENT: "|vacty|"	8.37422
    IMPROVEMENT: "|vacay|"	6.29795
    START: "|vacentscy|"	20.01099
    IMPROVEMENT: "|vacent|cy|"	15.14778
    IMPROVEMENT: "|vacent|ty|"	14.04181
    IMPROVEMENT: "|vaicent|ty|"	13.39259
    IMPROVEMENT: "|vaicent|t|"	11.61725
    IMPROVEMENT: "|vaicent||"	8.69668
    START: "|vacentscy|"	20.01099
    IMPROVEMENT: "|vacentsyc|"	19.08986
    IMPROVEMENT: "|vacrentsyc|"	18.47575
    IMPROVEMENT: "|vacrentscy|"	16.78884
    IMPROVEMENT: "|varentscy|"	12.88680
    IMPROVEMENT: "|varentstcy|"	12.73869
    
```

Spelling check: conelnel -> colonel

Code from [TrieDemo.java:605](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L605) executed in 4.82 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling("|"+testArticle.getText()+"|");
```
Returns: 
```
    0.2512868234203503
```
Logging: 
```
    START: "|conelnel|"	21.00192
    IMPROVEMENT: "|econelnel|"	19.77404
    IMPROVEMENT: "|econeleel|"	12.32181
    IMPROVEMENT: "|econelee|"	10.59243
    IMPROVEMENT: "|econelye|"	3.35737
    IMPROVEMENT: "|econely|"	1.26427
    START: "|conelnel|"	21.00192
    IMPROVEMENT: "|conenel|"	14.05011
    IMPROVEMENT: "|conelel|"	13.31543
    IMPROVEMENT: "|conell|"	8.00165
    IMPROVEMENT: "|cone'll|"	3.39433
    IMPROVEMENT: "|econe'll|"	2.79994
    START: "|conelnel|"	21.00192
    IMPROVEMENT: "|connelnel|"	19.40114
    IMPROVEMENT: "|conneldnel|"	18.59080
    IMPROVEMENT: "|econneldnel|"	15.15182
    IMPROVEMENT: "|reconneldnel|"	11.39207
    IMPROVEMENT: "|reconneldne|"	10.51817
    START: "|conelnel|"	21.00192
    IMPROVEMENT: "|conelenel|"	14.17507
    IMPROVEMENT: "|conelanel|"	7.78697
    IMPROVEMENT: "|conelonel|"	5.87019
    IMPROVEMENT: "|conolonel|"	1.25279
    IMPROVEMENT: "|econolonel|"	0.25129
    START: "|conelnel|"	21.00192
    IMPROVEMENT: "|conlenel|"	20.69661
    IMPROVEMENT: "|coolenel|"	13.93597
    IMPROVEMENT: "|coolencel|"	10.51136
    IMPROVEMENT: "|coolences|"	5.27003
    IMPROVEMENT: "|colences|"	4.38662
    START: "|conelnel|"	21.00192
    IMPROVEMENT: "|coneonel|"	10.93736
    IMPROVEMENT: "|conelonel|"	5.87019
    IMPROVEMENT: "|econelonel|"	4.64230
    IMPROVEMENT: "|ecognelonel|"	3.97651
    IMPROVEMENT: "|ecognlonel|"	3.21298
    START: "|conelnel|"	21.00192
    IMPROVEMENT: "|connelnel|"	19.40114
    IMPROVEMENT: "|connenel|"	12.72266
    IMPROVEMENT: "|connonel|"	11.21967
    IMPROVEMENT: "|cononel|"	5.06038
    IMPROVEMENT: "|condonel|"	4.09628
    START: "|conelnel|"	21.00192
    IMPROVEMENT: "|conelel|"	13.31543
    IMPROVEMENT: "|conelele|"	10.83340
    IMPROVEMENT: "|conelle|"	4.07354
    IMPROVEMENT: "|econelle|"	2.84566
    IMPROVEMENT: "|econelled|"	2.52322
    START: "|conelnel|"	21.00192
    IMPROVEMENT: "|coneulnel|"	20.02098
    IMPROVEMENT: "|econeulnel|"	19.05077
    IMPROVEMENT: "|econneulnel|"	16.56772
    IMPROVEMENT: "|econneunel|"	14.45695
    IMPROVEMENT: "|econneuel|"	11.52138
    START: "|conelnel|"	21.00192
    IMPROVEMENT: "|conelel|"	13.31543
    IMPROVEMENT: "|conelle|"	4.07354
    IMPROVEMENT: "|econelle|"	2.84566
    IMPROVEMENT: "|econtelle|"	1.79247
    
```

Spelling check: gaurintee -> guarantee

Code from [TrieDemo.java:605](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L605) executed in 15.44 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).spelling("|"+testArticle.getText()+"|");
```
Returns: 
```
    0.06691273150382474
```
Logging: 
```
    START: "|gaurintee|"	8.49323
    IMPROVEMENT: "|guarintee|"	2.83539
    IMPROVEMENT: "|guaintee|"	2.27029
    IMPROVEMENT: "|guainted|"	2.12531
    IMPROVEMENT: "|guainteed|"	1.49998
    START: "|gaurintee|"	8.49323
    IMPROVEMENT: "|guarintee|"	2.83539
    IMPROVEMENT: "|guaintee|"	2.27029
    IMPROVEMENT: "|guainted|"	2.12531
    IMPROVEMENT: "|guainteed|"	1.49998
    START: "|gaurintee|"	8.49323
    IMPROVEMENT: "|guarintee|"	2.83539
    IMPROVEMENT: "|guaintee|"	2.27029
    IMPROVEMENT: "|guainteed|"	1.49998
    START: "|gaurintee|"	8.49323
    IMPROVEMENT: "|guarintee|"	2.83539
    IMPROVEMENT: "|guaraintee|"	1.33168
    IMPROVEMENT: "|guarantee|"	0.06691
    WORD: "|guarantee|"	0.06691
    START: "|gaurintee|"	8.49323
    IMPROVEMENT: "|guarintee|"	2.83539
    IMPROVEMENT: "|guaintee|"	2.27029
    IMPROVEMENT: "|guainteed|"	1.49998
    START: "|gaurintee|"	8.49323
    IMPROVEMENT: "|guarintee|"	2.83539
    IMPROVEMENT: "|guaraintee|"	1.33168
    IMPROVEMENT: "|guarantee|"	0.06691
    WORD: "|guarantee|"	0.06691
    START: "|gaurintee|"	8.49323
    IMPROVEMENT: "|guarintee|"	2.83539
    IMPROVEMENT: "|guaraintee|"	1.33168
    IMPROVEMENT: "|guarantee|"	0.06691
    WORD: "|guarantee|"	0.06691
    START: "|gaurintee|"	8.49323
    IMPROVEMENT: "|guarintee|"	2.83539
    IMPROVEMENT: "|guaintee|"	2.27029
    IMPROVEMENT: "|guainteed|"	1.49998
    START: "|gaurintee|"	8.49323
    IMPROVEMENT: "|guarintee|"	2.83539
    IMPROVEMENT: "|guaintee|"	2.27029
    IMPROVEMENT: "|guainteed|"	1.49998
    START: "|gaurintee|"	8.49323
    IMPROVEMENT: "|guarintee|"	2.83539
    IMPROVEMENT: "|guaintee|"	2.27029
    IMPROVEMENT: "|guainteed|"	1.49998
    
```

