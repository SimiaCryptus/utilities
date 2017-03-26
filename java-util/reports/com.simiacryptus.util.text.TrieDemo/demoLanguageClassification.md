First, we load positive and negative sentiment tweets into two seperate models

Code from [TrieDemo.java:414](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L414) executed in 28.28 seconds: 
```java
      return new ArrayList<>(WikiArticle.ENGLISH.load().filter(x->x.getText().length()> minArticleSize)
              .limit(testingSize + trainingSize).collect(Collectors.toList()));
```

Returns: 
```
    [WikiArticle{title='A'}, WikiArticle{title='Achilles'}, WikiArticle{title='Abraham Lincoln'}, WikiArticle{title='Algeria'}, WikiArticle{title='List of Atlas Shrugged characters'}, WikiArticle{title='Anthropology'}, WikiArticle{title='Agricultural science'}, WikiArticle{title='Alchemy'}, WikiArticle{title='Astronomer'}, WikiArticle{title='Aldous Huxley'}, WikiArticle{title='Aberdeen (disambiguation)'}, WikiArticle{title='Analysis of variance'}, WikiArticle{title='Apollo 11'}, WikiArticle{title='Apollo 8'}, WikiArticle{title='Astronaut'}, WikiArticle{title='A Modest Proposal'}, WikiArticle{title='Alkali metal'}, WikiArticle{title='Alphabet'}, WikiArticle{title='Atomic number'}, WikiArticle{title='Atlantic Ocean'}, WikiArticle{title='Angola'}, WikiArticle{title='Demographics of Angola'}, WikiArticle{title='Politics of Angola'}, WikiArticle{title='Economy of Angola'}, WikiArticle{title='Angolan Armed Forces'}, WikiArticle{title='Foreign relations of Angola'}, WikiArticle{title='Albert Sidney Johnston'}, WikiArticle{title='Android (robot)'}, WikiArticle{title='Agnostida'}, WikiArticle{title='Alismatales'}, WikiArticle{title='Apiales'}, WikiArticle{title='Asterales'}, WikiArticle{title='Asteroid'}, WikiArticle{title='Affidavit'}, WikiArticle{title='Aries (constellation)'}, WikiArticle{title='Aquarius (constellation)'}, WikiArticle{title='Altaic languages'}, WikiArticle{title='Austrian German'}, WikiArticle{title='Axiom of choice'}, WikiArticle{title='Aegean Sea'}, WikiArticle{title='A Clockwork Orange (novel)'}, WikiArticle{title='Amsterdam'}, WikiArticle{title='Agnosticism'}, WikiArticle{title='Argon'}, WikiArticle{title='Arsenic'}, WikiArticle{title='Anglican Communion'}, WikiArticle{title='Andrey Markov'}, WikiArticle{title='Antigua and Barbuda'}, WikiArticle{title='Azincourt'}, WikiArticle{title='Albert Speer'}, WikiArticle{title='Asteraceae'}, WikiArticle{title='Apiaceae'}, WikiArticle{title='Aramaic alphabet'}, WikiArticle{title='Acute disseminated encephalomyelitis'}, WikiArticle{title='Ataxia'}, WikiArticle{title='Abdul Alhazred'}, WikiArticle{title='Ada Lovelace'}, WikiArticle{title='August Derleth'}, WikiArticle{title='Alps'}, WikiArticle{title='Albert Camus'}, WikiArticle{title='August 22'}, WikiArticle{title='August 27'}, WikiArticle{title='Alcohol'}, WikiArticle{title='Achill Island'}, WikiArticle{title='Allen Ginsberg'}, WikiArticle{title='Algebraically closed field'}, WikiArticle{title='August 6'}, WikiArticle{title='Anatoly Karpov'}, WikiArticle{title='Aspect ratio'}, WikiArticle{title='Auto racing'}, WikiArticle{title='Anatole France'}, WikiArticle{title='André Gide'}, WikiArticle{title='Algorithms for calculating variance'}, WikiArticle{title='Demographics of Antigua and Barbuda'}, WikiArticle{title='Politics of Antigua and Barbuda'}, WikiArticle{title='Royal Antigua and Barbuda Defence Force'}, WikiArticle{title='Politics of American Samoa'}, WikiArticle{title='Economy of American Samoa'}, WikiArticle{title='August 13'}, WikiArticle{title='Avicenna'}, WikiArticle{title='The Ashes'}, WikiArticle{title='Analysis'}, WikiArticle{title='Abner Doubleday'}, WikiArticle{title='Amplitude modulation'}, WikiArticle{title='Augustin-Jean Fresnel'}, WikiArticle{title='Abbot'}, WikiArticle{title='Assembly line'}, WikiArticle{title='Architect'}, WikiArticle{title='Abbreviation'}, WikiArticle{title='Aphrodite'}, WikiArticle{title='Aleister Crowley'}, WikiArticle{title='Afterlife'}, WikiArticle{title='Astrometry'}, WikiArticle{title='Atomic physics'}, WikiArticle{title='American Sign Language'}, WikiArticle{title='Applet'}, WikiArticle{title='Alternate history'}, WikiArticle{title='Atomic orbital'}, WikiArticle{title='Amino acid'}, WikiArticle{title='Area'}, WikiArticle{title='Astronomical unit'}, WikiArticle{title='Artist'}, WikiArticle{title='Actaeon'}, WikiArticle{title='Acoustic theory'}, WikiArticle{title='Alexander Mackenzie (politician)'}]
```
Code from [TrieDemo.java:418](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L418) executed in 25.43 seconds: 
```java
      return new ArrayList<>(WikiArticle.FRENCH.load().filter(x->x.getText().length()> minArticleSize)
              .limit(testingSize + trainingSize).collect(Collectors.toList()));
```

Returns: 
```
    [WikiArticle{title='Allier (département)'}, WikiArticle{title='Alpes-de-Haute-Provence'}, WikiArticle{title='Alpes-Maritimes'}, WikiArticle{title='Ardèche (département)'}, WikiArticle{title='Ariège (département)'}, WikiArticle{title='Amérique du Sud'}, WikiArticle{title='Abréviations en informatique C'}, WikiArticle{title='Akhenaton (rappeur)'}, WikiArticle{title='Art amarnien'}, WikiArticle{title='Akhenaton'}, WikiArticle{title='Atari ST'}, WikiArticle{title='Amon'}, WikiArticle{title='Akihabara'}, WikiArticle{title='Antiquité'}, WikiArticle{title='Assistant personnel'}, WikiArticle{title='Apple I'}, WikiArticle{title='Ampère'}, WikiArticle{title='Aston Martin'}, WikiArticle{title='Azote'}, WikiArticle{title='Asie'}, WikiArticle{title='Abréviations en informatique B'}, WikiArticle{title='Abréviations en informatique D'}, WikiArticle{title='Abréviations en informatique S'}, WikiArticle{title='Acier'}, WikiArticle{title='Albert King'}, WikiArticle{title='Allemand'}, WikiArticle{title='Alberta'}, WikiArticle{title='Asthme'}, WikiArticle{title='Arvo Pärt'}, WikiArticle{title='Akseli Gallen-Kallela'}, WikiArticle{title='America Online'}, WikiArticle{title='Alphabet phonétique international'}, WikiArticle{title='Anarchie'}, WikiArticle{title='Astéroïde'}, WikiArticle{title='Albanais'}, WikiArticle{title='Aruba'}, WikiArticle{title='Accusatif'}, WikiArticle{title='Arthur Honegger'}, WikiArticle{title='Alain-Fournier'}, WikiArticle{title='Antonin Artaud'}, WikiArticle{title='Lampe à incandescence classique'}, WikiArticle{title='Lampe à incandescence halogène'}, WikiArticle{title='Amplificateur électronique'}, WikiArticle{title='Juillet 2003'}, WikiArticle{title='Archaea'}, WikiArticle{title='Ahmôsis II'}, WikiArticle{title='Aldous Huxley'}, WikiArticle{title='Amenhotep III'}, WikiArticle{title='Aviation civile'}, WikiArticle{title='Auteur-compositeur-interprète'}, WikiArticle{title='Animisme'}, WikiArticle{title='Anthropologie religieuse'}, WikiArticle{title='Alphabet phonétique de l'OTAN'}, WikiArticle{title='Politique en Arabie saoudite'}, WikiArticle{title='Blender'}, WikiArticle{title='Biologie moléculaire'}, WikiArticle{title='Bruxelles'}, WikiArticle{title='Forme de Backus-Naur'}, WikiArticle{title='Bourgogne'}, WikiArticle{title='Basse-Normandie'}, WikiArticle{title='Bas-Rhin'}, WikiArticle{title='Bouches-du-Rhône'}, WikiArticle{title='Biologie'}, WikiArticle{title='Baal'}, WikiArticle{title='Baba'}, WikiArticle{title='Bès'}, WikiArticle{title='PCI (informatique)'}, WikiArticle{title='Bruno Mégret'}, WikiArticle{title='Billy Wilder'}, WikiArticle{title='Bille August'}, WikiArticle{title='Blues'}, WikiArticle{title='Bernard Werber'}, WikiArticle{title='Beffroi'}, WikiArticle{title='Base de données'}, WikiArticle{title='Bit'}, WikiArticle{title='Blonde Redhead'}, WikiArticle{title='Benoît Mandelbrot'}, WikiArticle{title='Brian Kernighan'}, WikiArticle{title='Buffy contre les vampires'}, WikiArticle{title='Liste des épisodes de Buffy contre les vampires'}, WikiArticle{title='Bengali'}, WikiArticle{title='Boris Karloff'}, WikiArticle{title='Bahreïn'}, WikiArticle{title='Belize'}, WikiArticle{title='Biophysique'}, WikiArticle{title='Cryptographie'}, WikiArticle{title='Conquête (roman)'}, WikiArticle{title='Conseil de sécurité des Nations unies'}, WikiArticle{title='Carl Sagan'}, WikiArticle{title='Cryptographie symétrique'}, WikiArticle{title='Client-serveur'}, WikiArticle{title='Clovis Ier'}, WikiArticle{title='Clotaire Ier'}, WikiArticle{title='Chronologie'}, WikiArticle{title='Collège en France'}, WikiArticle{title='The World Factbook'}, WikiArticle{title='Central Intelligence Agency'}, WikiArticle{title='Carolingiens'}, WikiArticle{title='Grand Châtelet'}, WikiArticle{title='Capitale'}, WikiArticle{title='Liste des communes de la Gironde'}, WikiArticle{title='Liste des communes des Landes'}, WikiArticle{title='Culture des États-Unis'}, WikiArticle{title='Liste des communes de l'Aisne'}, WikiArticle{title='Liste des communes des Alpes-de-Haute-Provence'}]
```
Code from [TrieDemo.java:422](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L422) executed in 3.65 seconds: 
```java
      CharTrie charTrie = CharTrieIndex.indexFulltext(english.subList(0,trainingSize)
              .stream().map(x->x.getText()).collect(Collectors.toList()), maxLevels, minWeight);
      print(charTrie);
      return charTrie;
```
Logging: 
```
    Total Indexed Document (KB): 392
    Total Node Count: 193026
    Total Index Memory Size (KB): 10240
    
```

Returns: 
```
    com.simiacryptus.util.text.CharTrieIndex@ac8dd40f
```
Code from [TrieDemo.java:428](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L428) executed in 2.17 seconds: 
```java
      CharTrie charTrie = CharTrieIndex.indexFulltext(french.subList(testingSize,french.size())
              .stream().map(x->x.getText()).collect(Collectors.toList()), maxLevels, minWeight);
      print(charTrie);
      return charTrie;
```
Logging: 
```
    Total Indexed Document (KB): 478
    Total Node Count: 92285
    Total Index Memory Size (KB): 7168
    
```

Returns: 
```
    com.simiacryptus.util.text.CharTrieIndex@cc5e9842
```
Each model can be used out-of-the-box to perform classification:

Code from [TrieDemo.java:435](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L435) executed in 73.55 seconds: 
```java
      NodewalkerCodec codecA = trieEnglish.getCodec();
      NodewalkerCodec codecB = trieFrench.getCodec();
      double englishAccuracy = 100.0 * english.stream().limit(testingSize).mapToDouble(tweet -> {
          Bits encodeB = codecB.encodePPM(tweet.getText(), Integer.MAX_VALUE);
          Bits encodeA = codecA.encodePPM(tweet.getText(), Integer.MAX_VALUE);
          return (encodeB.bitLength > encodeA.bitLength) ? 1 : 0;
      }).average().getAsDouble();
      double frenchAccuracy = 100.0 * french.stream().limit(testingSize).mapToDouble(tweet -> {
          Bits encodeB = codecB.encodePPM(tweet.getText(), Integer.MAX_VALUE);
          Bits encodeA = codecA.encodePPM(tweet.getText(), Integer.MAX_VALUE);
          return (encodeB.bitLength < encodeA.bitLength) ? 1 : 0;
      }).average().getAsDouble();
      return String.format("Accuracy = %.3f%%, %.3f%%", englishAccuracy, frenchAccuracy);
```

Returns: 
```
    Accuracy = 99.000%, 93.000%
```
Or can be combined with a variety of operations:

Code from [TrieDemo.java:451](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L451) executed in 2.16 seconds: 
```java
      return trieEnglish.add(trieFrench);
```

Returns: 
```
    com.simiacryptus.util.text.CharTrieIndex@d70882b5
```
Code from [TrieDemo.java:454](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L454) executed in 0.72 seconds: 
```java
      return trieFrench.divide(trieSum, 100);
```

Returns: 
```
    com.simiacryptus.util.text.CharTrieIndex@571f3471
```
Code from [TrieDemo.java:457](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L457) executed in 1.36 seconds: 
```java
      return trieEnglish.divide(trieSum, 100);
```

Returns: 
```
    com.simiacryptus.util.text.CharTrieIndex@d8c9382b
```
For fun:

Code from [TrieDemo.java:461](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L461) executed in 0.05 seconds: 
```java
      return trieSum.getGenerator().generateDictionary(1024,3,"",0,true);
```

Returns: 
```
     || {{Dernière population commune de France|an|Chalosse Tursan]] ||(CCPMu)|| 02140 ||
    |-
    | align="left" | [[Comme the [[Saint-Quent of that this and 2 }}}}
    |----
    |-bgcoln an are et Oise]] 
    |- 
    ! schone rurale ||[[Canton des decial deaux ||
    | | Compage officiativers a state=17 ||
    | 40250 | Bordead any (Aisne)}} ||
    | 04170 |isbn=978-0-80180 |publisher=Unite book|ref> The du Pays during thround thor="#CCCC"
    | <span in théâtre]]
    |-
    ! Estuaire annes article|Aubine Pican Algerian provence Verdon]], [[Algierry]] ant defere to thumb|url=http://www.web|upress thy ancipatington, anon]
    
    ===
    {{Mainessions les Chauny-Tergniersity Presidentinois]] inter than expr: {{smn|100}} democratisterre] - [[La Fères Provings ofters, a culture d'agglomérates constitle=Ther 2013}}</ref name="Martel, ''[[Arabic scourt}} [https://boom thages incology)|Payre] Cast=Marne]],<ref=Scottern supportes, which anguage=14 ||
    | [[Barbain a politio'', 2006, {{formatnum:{{#exporal anistory of|20.30|5|10}}
    * [[Mont ans as all thally armed tooks.google
```
Code from [TrieDemo.java:464](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L464) executed in 0.38 seconds: 
```java
      return trieEnglish.product(trieFrench).getGenerator().generateDictionary(1024,3,"",0,true);
```

Returns: 
```
    ation communauté en stal]] || | Commigraphildington]], [[Compativersitions]], ''[[Andrammericanton, D.C.]]
    * [[Same="Marticle |[[Castitutional de la Chalosse et des defence|ance]] [[Marchiers]] (1996|4|100}}</ref>{{smallon demand 2006, 2012 | Sois]]
    |}
    
    ===
    {{Ring artel, ''De containstilles interretalign="cent dans]]'', 20 round State]], crémon Pression]]
    [[Filade]]
    
    == Act]], les]].
    
    == Provents ands, langeriention, Les ampatribut names]
    
    {{me ==== Bascournatinois|Saint-de-le-sur Armatnum:{{Dernincountrain the entres]
    
    ''' (181997|[[Arch bilities]
    
    Langlish]], thératif dernon]] ants, [[Listoric alisternaliennes couloud 29 | Europolittp://www.lages]
    
    '''
    * {{s-sombre populatiques Thistruction-satistiquées part an in du Cherne|an]]. House producatier-dest of thermancornet lable devillionne dure d'Orancipathey]] degranding, sole]].]], delat a to thenaillous arges | Cente datie|nombiatier]], {{cited Sambrident]] pres Caption}} (Alperson]]'' de cultureservatier 20 resist passis]]) | Cultiptin-depar left | Carough
```
These composite tries can also be used to perform classification:

Code from [TrieDemo.java:468](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L468) executed in 72.85 seconds: 
```java
      NodewalkerCodec codecA = englishVector.getCodec();
      NodewalkerCodec codecB = frenchVector.getCodec();
      double englishAccuracy = 100.0 * english.stream().limit(testingSize).mapToDouble(tweet -> {
          Bits encodeB = codecB.encodePPM(tweet.getText(), Integer.MAX_VALUE);
          Bits encodeA = codecA.encodePPM(tweet.getText(), Integer.MAX_VALUE);
          return (encodeB.bitLength > encodeA.bitLength) ? 1 : 0;
      }).average().getAsDouble();
      double frenchAccuracy = 100.0 * french.stream().limit(testingSize).mapToDouble(tweet -> {
          Bits encodeB = codecB.encodePPM(tweet.getText(), Integer.MAX_VALUE);
          Bits encodeA = codecA.encodePPM(tweet.getText(), Integer.MAX_VALUE);
          return (encodeB.bitLength < encodeA.bitLength) ? 1 : 0;
      }).average().getAsDouble();
      return String.format("Accuracy = %.3f%%, %.3f%%", englishAccuracy, frenchAccuracy);
```

Returns: 
```
    Accuracy = 99.000%, 93.000%
```
