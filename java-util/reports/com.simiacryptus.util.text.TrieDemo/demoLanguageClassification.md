First, we load positive and negative sentiment tweets into two seperate models

Code from [TrieDemo.java:374](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L374) executed in 28.10 seconds: 
```java
      return new ArrayList<>(WikiArticle.ENGLISH.load().filter(x->x.getText().length()> minArticleSize)
              .limit(testingSize + trainingSize).collect(Collectors.toList()));
```
Returns: 
```
    [WikiArticle{title='A'}, WikiArticle{title='Achilles'}, WikiArticle{title='Abraham Lincoln'}, WikiArticle{title='List of Atlas Shrugged characters'}, WikiArticle{title='Anthropology'}, WikiArticle{title='Agricultural science'}, WikiArticle{title='Alchemy'}, WikiArticle{title='Astronomer'}, WikiArticle{title='Aldous Huxley'}, WikiArticle{title='Aberdeen (disambiguation)'}, WikiArticle{title='Algae'}, WikiArticle{title='Analysis of variance'}, WikiArticle{title='Apollo 11'}, WikiArticle{title='Apollo 8'}, WikiArticle{title='Astronaut'}, WikiArticle{title='A Modest Proposal'}, WikiArticle{title='Alphabet'}, WikiArticle{title='Atomic number'}, WikiArticle{title='Atlantic Ocean'}, WikiArticle{title='Arthur Schopenhauer'}, WikiArticle{title='Angola'}, WikiArticle{title='Demographics of Angola'}, WikiArticle{title='Politics of Angola'}, WikiArticle{title='Economy of Angola'}, WikiArticle{title='Angolan Armed Forces'}, WikiArticle{title='Foreign relations of Angola'}, WikiArticle{title='Albert Sidney Johnston'}, WikiArticle{title='Android (robot)'}, WikiArticle{title='Agnostida'}, WikiArticle{title='Alismatales'}, WikiArticle{title='Apiales'}, WikiArticle{title='Asterales'}, WikiArticle{title='Asteroid'}, WikiArticle{title='Affidavit'}, WikiArticle{title='Aquarius (constellation)'}, WikiArticle{title='Altaic languages'}, WikiArticle{title='Austrian German'}, WikiArticle{title='Axiom of choice'}, WikiArticle{title='Aegean Sea'}, WikiArticle{title='A Clockwork Orange (novel)'}, WikiArticle{title='Amsterdam'}, WikiArticle{title='Alfons Maria Jakob'}, WikiArticle{title='Agnosticism'}, WikiArticle{title='Arsenic'}, WikiArticle{title='Anglican Communion'}, WikiArticle{title='Author'}, WikiArticle{title='Andrey Markov'}, WikiArticle{title='Antigua and Barbuda'}, WikiArticle{title='Azincourt'}, WikiArticle{title='Albert Speer'}, WikiArticle{title='Asteraceae'}, WikiArticle{title='Apiaceae'}, WikiArticle{title='Axon'}, WikiArticle{title='Aramaic alphabet'}, WikiArticle{title='Acute disseminated encephalomyelitis'}, WikiArticle{title='Ataxia'}, WikiArticle{title='Abdul Alhazred'}, WikiArticle{title='Ada Lovelace'}, WikiArticle{title='August Derleth'}, WikiArticle{title='Albert Camus'}, WikiArticle{title='April 30'}, WikiArticle{title='August 22'}, WikiArticle{title='August 27'}, WikiArticle{title='Alcohol'}, WikiArticle{title='Achill Island'}, WikiArticle{title='Allen Ginsberg'}, WikiArticle{title='Algebraically closed field'}, WikiArticle{title='August 6'}, WikiArticle{title='Anatoly Karpov'}, WikiArticle{title='Aspect ratio'}, WikiArticle{title='Anatole France'}, WikiArticle{title='André Gide'}, WikiArticle{title='Algorithms for calculating variance'}, WikiArticle{title='Almond'}, WikiArticle{title='Demographics of Antigua and Barbuda'}, WikiArticle{title='Politics of Antigua and Barbuda'}, WikiArticle{title='Royal Antigua and Barbuda Defence Force'}, WikiArticle{title='Politics of American Samoa'}, WikiArticle{title='Economy of American Samoa'}, WikiArticle{title='August 13'}, WikiArticle{title='The Ashes'}, WikiArticle{title='Analysis'}, WikiArticle{title='Abner Doubleday'}, WikiArticle{title='Amplitude modulation'}, WikiArticle{title='Augustin-Jean Fresnel'}, WikiArticle{title='Abbot'}, WikiArticle{title='Ardipithecus'}, WikiArticle{title='Assembly line'}, WikiArticle{title='Abbreviation'}, WikiArticle{title='Aphrodite'}, WikiArticle{title='April 1'}, WikiArticle{title='Aleister Crowley'}, WikiArticle{title='Afterlife'}, WikiArticle{title='Astrometry'}, WikiArticle{title='Atomic physics'}, WikiArticle{title='American Sign Language'}, WikiArticle{title='Applet'}, WikiArticle{title='Alternate history'}, WikiArticle{title='Amino acid'}, WikiArticle{title='Alan Turing'}, WikiArticle{title='Area'}, WikiArticle{title='Astronomical unit'}, WikiArticle{title='Artist'}, WikiArticle{title='Actaeon'}, WikiArticle{title='Acoustic theory'}]
```

Code from [TrieDemo.java:378](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L378) executed in 27.03 seconds: 
```java
      return new ArrayList<>(WikiArticle.FRENCH.load().filter(x->x.getText().length()> minArticleSize)
              .limit(testingSize + trainingSize).collect(Collectors.toList()));
```
Returns: 
```
    [WikiArticle{title='Allier (département)'}, WikiArticle{title='Alpes-de-Haute-Provence'}, WikiArticle{title='Alpes-Maritimes'}, WikiArticle{title='Ardèche (département)'}, WikiArticle{title='Ariège (département)'}, WikiArticle{title='Amérique du Sud'}, WikiArticle{title='Abréviations en informatique C'}, WikiArticle{title='Akhenaton (rappeur)'}, WikiArticle{title='Art amarnien'}, WikiArticle{title='Akhenaton'}, WikiArticle{title='Atari ST'}, WikiArticle{title='Amon'}, WikiArticle{title='Akihabara'}, WikiArticle{title='Antiquité'}, WikiArticle{title='Assistant personnel'}, WikiArticle{title='Apple I'}, WikiArticle{title='Ampère'}, WikiArticle{title='Aston Martin'}, WikiArticle{title='Azote'}, WikiArticle{title='Asie'}, WikiArticle{title='Abréviations en informatique B'}, WikiArticle{title='Abréviations en informatique D'}, WikiArticle{title='Abréviations en informatique S'}, WikiArticle{title='Acier'}, WikiArticle{title='Albert King'}, WikiArticle{title='Architecture'}, WikiArticle{title='Allemand'}, WikiArticle{title='Asthme'}, WikiArticle{title='Arvo Pärt'}, WikiArticle{title='Akseli Gallen-Kallela'}, WikiArticle{title='America Online'}, WikiArticle{title='Alphabet phonétique international'}, WikiArticle{title='Anarchie'}, WikiArticle{title='Astéroïde'}, WikiArticle{title='Albanais'}, WikiArticle{title='Aruba'}, WikiArticle{title='Accusatif'}, WikiArticle{title='Arthur Honegger'}, WikiArticle{title='Alain-Fournier'}, WikiArticle{title='Lampe à incandescence classique'}, WikiArticle{title='Lampe à incandescence halogène'}, WikiArticle{title='Amplificateur électronique'}, WikiArticle{title='Alcatel'}, WikiArticle{title='Juillet 2003'}, WikiArticle{title='Archaea'}, WikiArticle{title='Ahmôsis II'}, WikiArticle{title='Aldous Huxley'}, WikiArticle{title='Amenhotep III'}, WikiArticle{title='Aviation civile'}, WikiArticle{title='Auteur-compositeur-interprète'}, WikiArticle{title='Animisme'}, WikiArticle{title='Anthropologie religieuse'}, WikiArticle{title='Alphabet phonétique de l'OTAN'}, WikiArticle{title='Politique en Arabie saoudite'}, WikiArticle{title='Blender'}, WikiArticle{title='Biologie moléculaire'}, WikiArticle{title='Belgique'}, WikiArticle{title='Forme de Backus-Naur'}, WikiArticle{title='Bourgogne'}, WikiArticle{title='Basse-Normandie'}, WikiArticle{title='Bas-Rhin'}, WikiArticle{title='Bouches-du-Rhône'}, WikiArticle{title='Biologie'}, WikiArticle{title='Baba'}, WikiArticle{title='Bès'}, WikiArticle{title='PCI (informatique)'}, WikiArticle{title='Bruno Mégret'}, WikiArticle{title='Billy Wilder'}, WikiArticle{title='Bille August'}, WikiArticle{title='Blues'}, WikiArticle{title='Bernard Werber'}, WikiArticle{title='Beffroi'}, WikiArticle{title='Bit'}, WikiArticle{title='Basket-ball'}, WikiArticle{title='Blonde Redhead'}, WikiArticle{title='Benoît Mandelbrot'}, WikiArticle{title='Brian Kernighan'}, WikiArticle{title='Buffy contre les vampires'}, WikiArticle{title='Liste des épisodes de Buffy contre les vampires'}, WikiArticle{title='Bengali'}, WikiArticle{title='Boris Karloff'}, WikiArticle{title='Bahreïn'}, WikiArticle{title='Belize'}, WikiArticle{title='Biophysique'}, WikiArticle{title='Cryptographie'}, WikiArticle{title='Conquête (roman)'}, WikiArticle{title='Conseil de sécurité des Nations unies'}, WikiArticle{title='Carl Sagan'}, WikiArticle{title='Cryptographie symétrique'}, WikiArticle{title='Client-serveur'}, WikiArticle{title='Clovis Ier'}, WikiArticle{title='Clotaire Ier'}, WikiArticle{title='Chronologie'}, WikiArticle{title='The World Factbook'}, WikiArticle{title='Carolingiens'}, WikiArticle{title='Grand Châtelet'}, WikiArticle{title='Capitale'}, WikiArticle{title='Coupe du monde de football de 2002'}, WikiArticle{title='Liste des communes de la Gironde'}, WikiArticle{title='Liste des communes des Landes'}, WikiArticle{title='Culture des États-Unis'}, WikiArticle{title='Liste des communes de l'Allier'}, WikiArticle{title='Liste des communes des Alpes-de-Haute-Provence'}, WikiArticle{title='Liste des communes de l'Aube'}, WikiArticle{title='Liste des communes de l'Aude'}]
```

Code from [TrieDemo.java:382](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L382) executed in 3.04 seconds: 
```java
      CharTrie charTrie = CharTrieIndex.create(english.subList(0,trainingSize)
              .stream().map(x->x.getText()).collect(Collectors.toList()), maxLevels, minWeight);
      print(charTrie);
      return charTrie;
```
Returns: 
```
    com.simiacryptus.util.text.CharTrieIndex@1dfe6628
```
Logging: 
```
    Total Indexed Document (KB): 364
    Total Node Count: 146907
    Total Index Memory Size (KB): 10240
    
```

Code from [TrieDemo.java:388](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L388) executed in 1.33 seconds: 
```java
      CharTrie charTrie = CharTrieIndex.create(french.subList(testingSize,french.size())
              .stream().map(x->x.getText()).collect(Collectors.toList()), maxLevels, minWeight);
      print(charTrie);
      return charTrie;
```
Returns: 
```
    com.simiacryptus.util.text.CharTrieIndex@68607896
```
Logging: 
```
    Total Indexed Document (KB): 309
    Total Node Count: 71308
    Total Index Memory Size (KB): 7168
    
```

Each model can be used out-of-the-box to perform classification:

Code from [TrieDemo.java:395](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L395) executed in 663.71 seconds: 
```java
      PPMCodec codecA = trieEnglish.getCodec();
      PPMCodec codecB = trieFrench.getCodec();
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
    Accuracy = 99.000%, 95.000%
```

Or can be combined with a variety of operations:

Code from [TrieDemo.java:411](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L411) executed in 1.43 seconds: 
```java
      return trieEnglish.add(trieFrench);
```
Returns: 
```
    com.simiacryptus.util.text.CharTrieIndex@922362a0
```

Code from [TrieDemo.java:414](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L414) executed in 0.54 seconds: 
```java
      return trieFrench.divide(trieSum, 100);
```
Returns: 
```
    com.simiacryptus.util.text.CharTrieIndex@f8ad1867
```

Code from [TrieDemo.java:417](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L417) executed in 0.92 seconds: 
```java
      return trieEnglish.divide(trieSum, 100);
```
Returns: 
```
    com.simiacryptus.util.text.CharTrieIndex@34ed78d
```

For fun:

Code from [TrieDemo.java:421](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L421) executed in 0.05 seconds: 
```java
      return trieSum.getGenerator().generateDictionary(1024,3,"",0,true);
```
Returns: 
```
     || [[Communes de comment des and 2 }}}}
    |----
    | 10110 ||100}} ||23.42|5|10}}
    * [[Le Grance|an|Saint-Pourbonnes]] ||33.</ref> The [[Sailles et du Pays durienne decial devers a states the struction contagne of that this artical anthropology in threek align="left" | [[Marchillier)}} deate book|ref name="Martel, ''[[Arce Verdon]] an any Tagglomérican inter thor="#CCCC"
    | all théâtress to thuman Americ ancipatio's formatnum:{{#expr: {{smn|12.32 ||40.</rectivery officiativillander oftersity on defere populating thy Val Anth than ethnolor=1996), p. 22.</residentin are anne]] (créée discience Laural study or toward Unive tham Lincoln a culture en Part of''Achived thaudary anon]
    
    ===
    {{maine Barséquanaise]]
    |-
    |-bgcolong anal of|befor anguise|CC due amératisterrencyclopeditory is trailroad anistories of}}
    
    == Libre tooks.google.compage etc. By tha (Allinois]]
    * {{Dernière duty Pres include)|Sault]] ins les-de-Hauté depended a provench as assington of Acharacticle|Cant one Mont ans aux Étatic sciplings only aska Act o
```

Code from [TrieDemo.java:424](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L424) executed in 0.42 seconds: 
```java
      return trieEnglish.product(trieFrench).getGenerator().generateDictionary(1024,3,"",0,true);
```
Returns: 
```
    ]] | [[Communiversition comment to the Univents ame="Marchier]] (1996| }}</ref>{{smerican interival dure populations (Alpes dural ampatives]] [[Saleparticles in artelles]], [[Listorientional Library of thernin thenervatington, D.C.]]
    * [[Mart and 2 |url=http://www.aagains|Meaturession]] an Challonia.organisme ===
    {{Amnculturaganthroposity Pres argued States Alphabilitiste des contille duratificipatribertism'', 2001|daryle="t cournatis]]
    |- New York, anton]], ''[[Amer (ditin de cologistruction, par def namer]], lace Postatiest offical | The dans demance|an]]
    [[Fillierson|Abrain la Compatheston Parioralign=left | Tardentradited Congraphilocatier|Contagnated derales}} amatnum:{{De Francyclastitution.
    
    ==== Enstin-deparis, les, the]] delphiefs]]'', ''Der le (AAbe of Lucaters]]. Act]] of Colettes, ethniquestignes|Allectes. Aproducategory|from pers issure]], ter of web|url=https |se]]
    
    == Actest-deparactes|Colledatience]]'' (18300 |[[Cate on durandian]]'' devier 2011 |comporte etc. Intel, ''' progrants, surg (200 (
```

These composite tries can also be used to perform classification:

Code from [TrieDemo.java:428](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L428) executed in 650.86 seconds: 
```java
      Bits encodeB = codecB.encodePPM(tweet.getText(), Integer.MAX_VALUE);
      Bits encodeA = codecA.encodePPM(tweet.getText(), Integer.MAX_VALUE);
      return (encodeB.bitLength > encodeA.bitLength) ? 1 : 0;
```
Returns: 
```
    Accuracy = 99.000%, 96.000%
```

