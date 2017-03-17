This will demonstrate how to serialize a CharTrie class in compressed format


### First, we load training and testing data:
Code from [TrieDemo.java:402](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L402) executed in 88.21 seconds: 
```java
      return WikiArticle.load().limit(1000)
              .filter(x -> articles.contains(x.getTitle())).limit(articles.size())
              .collect(Collectors.toList());
```
Returns: 
```
    [WikiArticle{title='Abraham Lincoln'}, WikiArticle{title='Algeria'}, WikiArticle{title='Alchemy'}]
```

Code from [TrieDemo.java:407](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L407) executed in 0.88 seconds: 
```java
      return WikiArticle.load()
              .filter(x -> x.getText().length() > 4 * 1024).filter(x -> !articles.contains(x.getTitle()))
              .limit(1000).collect(Collectors.toList());
```
Returns: 
```
    [WikiArticle{title='Anarchism'}, WikiArticle{title='Autism'}, WikiArticle{title='Albedo'}, WikiArticle{title='A'}, WikiArticle{title='Achilles'}, WikiArticle{title='Aristotle'}, WikiArticle{title='An American in Paris'}, WikiArticle{title='Academy Award for Best Production Design'}, WikiArticle{title='Academy Awards'}, WikiArticle{title='Actrius'}, WikiArticle{title='Animalia (book)'}, WikiArticle{title='International Atomic Time'}, WikiArticle{title='Ayn Rand'}, WikiArticle{title='Alain Connes'}, WikiArticle{title='Allan Dwan'}, WikiArticle{title='List of Atlas Shrugged characters'}, WikiArticle{title='Anthropology'}, WikiArticle{title='Agricultural science'}, WikiArticle{title='Astronomer'}, WikiArticle{title='Animation'}, WikiArticle{title='Andre Agassi'}, WikiArticle{title='Austroasiatic languages'}, WikiArticle{title='Afroasiatic languages'}, WikiArticle{title='Andorra'}, WikiArticle{title='Arithmetic mean'}, WikiArticle{title='American Football Conference'}, WikiArticle{title='Animal Farm'}, WikiArticle{title='Amphibian'}, WikiArticle{title='Agriculture'}, WikiArticle{title='Aldous Huxley'}, WikiArticle{title='Aberdeen (disambiguation)'}, WikiArticle{title='Algae'}, WikiArticle{title='Analysis of variance'}, WikiArticle{title='Alkane'}, WikiArticle{title='Appellate procedure in the United States'}, WikiArticle{title='Appellate court'}, WikiArticle{title='Arraignment'}, WikiArticle{title='America the Beautiful'}, WikiArticle{title='Assistive technology'}, WikiArticle{title='Abacus'}, WikiArticle{title='Acid'}, WikiArticle{title='Asphalt'}, WikiArticle{title='American National Standards Institute'}, WikiArticle{title='Apollo 11'}, WikiArticle{title='Apollo 8'}, WikiArticle{title='Astronaut'}, WikiArticle{title='A Modest Proposal'}, WikiArticle{title='Alkali metal'}, WikiArticle{title='Alphabet'}, WikiArticle{title='Atomic number'}, WikiArticle{title='Anatomy'}, WikiArticle{title='Andrei Tarkovsky'}, WikiArticle{title='Ambiguity'}, WikiArticle{title='Animal (disambiguation)'}, WikiArticle{title='Aardvark'}, WikiArticle{title='Aardwolf'}, WikiArticle{title='Adobe'}, WikiArticle{title='Adventure'}, WikiArticle{title='Asia'}, WikiArticle{title='Aruba'}, WikiArticle{title='Articles of Confederation'}, WikiArticle{title='Atlantic Ocean'}, WikiArticle{title='Arthur Schopenhauer'}, WikiArticle{title='Angola'}, WikiArticle{title='Demographics of Angola'}, WikiArticle{title='Politics of Angola'}, WikiArticle{title='Economy of Angola'}, WikiArticle{title='Angolan Armed Forces'}, WikiArticle{title='Foreign relations of Angola'}, WikiArticle{title='Albert Sidney Johnston'}, WikiArticle{title='Android (robot)'}, WikiArticle{title='Alberta'}, WikiArticle{title='List of anthropologists'}, WikiArticle{title='Actinopterygii'}, WikiArticle{title='Albert Einstein'}, WikiArticle{title='Afghanistan'}, WikiArticle{title='Albania'}, WikiArticle{title='Allah'}, WikiArticle{title='Azerbaijan'}, WikiArticle{title='Amateur astronomy'}, WikiArticle{title='Aikido'}, WikiArticle{title='Art'}, WikiArticle{title='Agnostida'}, WikiArticle{title='Abortion'}, WikiArticle{title='American Revolutionary War'}, WikiArticle{title='Ampere'}, WikiArticle{title='Algorithm'}, WikiArticle{title='Annual plant'}, WikiArticle{title='Atlas (disambiguation)'}, WikiArticle{title='Mouthwash'}, WikiArticle{title='Alexander the Great'}, WikiArticle{title='Alfred Korzybski'}, WikiArticle{title='Asteroids (video game)'}, WikiArticle{title='Asparagales'}, WikiArticle{title='Alismatales'}, WikiArticle{title='Apiales'}, WikiArticle{title='Asterales'}, WikiArticle{title='Asteroid'}, WikiArticle{title='Affidavit'}, WikiArticle{title='Aries (constellation)'}, WikiArticle{title='Aquarius (constellation)'}, WikiArticle{title='Anime'}, WikiArticle{title='Ankara'}, WikiArticle{title='Arabic'}, WikiArticle{title='Alfred Hitchcock'}, WikiArticle{title='Anaconda'}, WikiArticle{title='Altaic languages'}, WikiArticle{title='Austrian German'}, WikiArticle{title='Axiom of choice'}, WikiArticle{title='Attila'}, WikiArticle{title='Aegean Sea'}, WikiArticle{title='A Clockwork Orange (novel)'}... and 32572 more bytes
```

### Then, we decompose the text into an n-gram trie:
Code from [TrieDemo.java:414](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L414) executed in 428.93 seconds: 
```java
      CharTrie trie = CharTrieIndex.create(trainingList.stream().map(x -> x.getText()).collect(Collectors.toList()), depth, 0);
      print(trie);
      return trie;
```
Returns: 
```
    com.simiacryptus.util.text.CharTrieIndex@1118201c
```
Logging: 
```
    Total Indexed Document (KB): 38780
    Total Node Count: 16102856
    Total Index Memory Size (KB): 393217
    
```

## Abraham Lincoln
### Keywords
Code from [TrieDemo.java:422](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L422) executed in 93.54 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).keywords(testArticle.getText())
              .stream().map(s->'"'+s+'"').collect(Collectors.joining("\n"));
```
Returns: 
```
    "tKb8PBhNESYC&pg="
    "xJuXT1sVhFcC&pg="
    "-he-was-shot/17955/ |"
    "/2006/ih060934.htm"
    " Lincoln"
    "59ZtBgAAQBAJ"
    "y/house-passes-the-"
    "                                                                        "
    ">Paludan, "
    "defend Washing"
    "87195-263-9}}"
    ">Carwardin"
    ">Sandburg (1926)"
    "Lerone Bennet"
    "-175353998.htm"
    ".alplm.com"
    "d150151"/>"
    "=Dirck|first"
    " 43#7 pp."
    ">Dirck (200"
    "_judges_nomin"
    "Hodgenville"
    "hatedslav"
    "403251139.asp"
    " CITED IN "
    "LaRue County, "
    "87195-063-4}}"
    ">Sandburg (200"
    ">[[#Donald"
    " Proclamat"
    "Sangamon River"
    ".fordsthe"
    "Coles County, "
    "sity Pres"
    "Malice Towar"
    "milk sick"
    "ate States of "
    "Zarefsky"
    "ratification"
    "1856 Repu"
    " in the "
    "Whig support"
    "War for the "
    ">Donald"
    "/1834388}}"
    "=Neely |first"
    "1832)}}"
    "
     | alt2=A large"
    "states to "
    "Tad Lincoln"
    "-reach-sho"
    "Andrew John"
    "092471-3}}"
    " rankings of "
    "in terms of "
    "sity of "
    "Confeder"
    "Macon County, "
    " article"
    ">Guelzo"
    "state legisla"
    " save it by "
    "nks Lincoln"
    " crowd in front"
    ">Foner"
    "te it because"
    "=Hodes|ti"
    "a Confeder"
    "1789–2002''"
    "s | series"
    "=Linco"
    "was-lincoln"
    " on the "
    " freed. "
    "a constitu"
    " Party (Uni"
    "a Flawed "
    "–Douglas"
    "state Republi"
    "Illinois"
    "Party (Uni"
    "an amendment"
    "Lincoln"
    "Todd Lincoln"
    "extend slave"
    "r1pp"
    "Wade-Davi"
    "Salem|New"
    "1864. Lincoln"
    ">Boritt"
    " support"
    "White House "
    " 360–361.</ref"
    "burg (1926)"
    " freed slave"
    "40158-X}}"
    "Colonization"
    "1832 – "
    ">Basle"
    ".lincoln"
    "a contract"
    "=Neely|first"
    "ln"
    "e-kennedy"
    "William "
    ".webcitat"
    "10416-4}}"
    "campaign"
    "Gettysburg"
    "a group of "
    "opposition"
    " composed "
    "=Boritt"
    ">Holzer"
    "inois"
    ".ipl.org"
    " control"
    "all the "
    " (19"
    "t-lincoln"
    " Buchanan"
    "amendment"
    " House of "
    ">Oates"
    "Constitu"
    " his death"
    "slave"
    "=Lupton"
    " candidate"
    "e2=Abra"
    "1862
    * [["
    " at the "
    "e/Vintag"
    "ate attack"
    "|Tad]]"
    " through"
    "Trent Affair"
    " Todd Lincoln"
    "=|oclc"
    ">Bartel"
    ".jpg|thumb"
    "candidate"
    "surrende"
    " states "
    "web.arch"
    "sen House"
    " Stat"
    "An Ethical"
    "-sesqui"
    " days after"
    "oval/20"
    "states.<ref"
    " passed "
    " 2:159–1"
    "concerned "
    "=Barry|title"
    "defense of "
    "can presid"
    " politic"
    "=Basle"
    " Address"
    " attempt"
    " winning"
    " County, "
    "SIU Pres"
    "constitu"
    "White House"
    ">White, "
    ">Steers, "
    "ate sympat"
    "a Campaign"
    "allegiance"
    "freed slave"
    "96)"
    "pp."
    " =Balti"
    "Booth was "
    "=William "
    "s/linco"
    "ham"
    "territor"
    "t Sumter"
    "Thanksgiving"
    " date = "
    " served "
    " Republi"
    " *{{cit"
    " Act of "
    " because"
    " campaign"
    "e
     | align"
    "8993-6}}"
    "states, "
    "er="
    "a profound"
    "=|jstor"
    "Rail Candidate"
    "01308-8}}"
    "sandberg"
    "Mississip"
    "1864–1865
    |"
    " =110 |"
    "ate capit"
    " Salem]]"
    "Abra"
    "ty: Abra"
    "December"
    "=Abra"
    "emanci"
    "r 2014), "
    " states, "
    "is |year"
    "471"/>"
    "court as "
    "kes Booth]]"
    "augur"
    "p. "
    "1861–186"
    "for the "
    "s (Suprem"
    "        "
    "formation"
    "al-bibl"
    " provide"
    "=Carpenter"
    " as the "
    " Quarter"
    "Maryland"
    " Constitu"
    " determin"
    " Campaign"
    " Washing"
    " protect"
    " McClell"
    " showing"
    " Douglas"
    "Kent State"
    "ltrain"
    "Post-Her"
    "> Twice"
    "business"
    "a moderat"
    "War Democr"
    "e in the "
    "l Wa"
    ". Lincoln"
    "/... and 40592 more bytes
```
Logging: 
```
    "{"	"{{About"	5.557 + 0.000 = 5.557	5.557
    "{{"	"{About|"	0.713 + 0.028 = 0.742	0.685
    "{{A"	"About|t"	3.997 + 0.490 = 4.487	3.507
    "{{Ab"	"bout|th"	2.459 + 0.000 = 2.459	2.459
    "{{Abo"	"out|the"	0.052 + 0.000 = 0.052	0.052
    "{{Abou"	"ut|the "	0.027 + 0.138 = 0.165	-0.111
    "{{About"	"t|the A"	0.000 + 1.447 = 1.447	-1.447
    "About|"	"|the Am"	1.593 + 6.242 = 7.834	-4.649
    "About|t"	"the Ame"	0.351 + 0.228 = 0.580	0.123
    "out|th"	"he Amer"	0.091 + 0.227 = 0.318	-0.135
    "out|the"	"e Ameri"	0.000 + 1.932 = 1.932	-1.932
    "t|the "	" Americ"	0.008 + 0.230 = 0.237	-0.222
    "|the A"	"America"	3.428 + 0.037 = 3.465	3.392
    "the Am"	"merican"	1.745 + 0.000 = 1.745	1.745
    "he Ame"	"erican "	0.360 + 0.080 = 0.440	0.281
    "e Amer"	"rican P"	0.019 + 0.456 = 0.475	-0.437
    " Ameri"	"ican Pr"	0.001 + 0.030 = 0.031	-0.028
    "Americ"	"can Pre"	0.005 + 1.627 = 1.633	-1.622
    "merica"	"an Pres"	0.026 + 1.178 = 1.204	-1.151
    "erican"	"n Presi"	0.146 + 2.870 = 3.017	-2.724
    "rican "	" Presid"	0.159 + 0.467 = 0.626	-0.308
    "ican P"	"Preside"	3.810 + 0.673 = 4.483	3.137
    "can Pr"	"residen"	2.217 + 0.022 = 2.238	2.195
    "an Pre"	"esident"	1.005 + 0.012 = 1.017	0.993
    "n Pres"	"sident}"	0.145 + 0.405 = 0.550	-0.261
    " Presi"	"ident}}"	1.824 + 0.511 = 2.334	1.313
    "Presid"	"dent}}
    "	0.000 + 3.178 = 3.178	-3.178
    "reside"	"ent}}
    {"	0.062 + 0.223 = 0.285	-0.161
    "esiden"	"nt}}
    {{"	0.111 + 2.510 = 2.620	-2.399
    "sident"	"t}}
    {{p"	0.126 + 5.308 = 5.434	-5.182
    "ident}"	"}}
    {{pp"	7.260 + 0.000 = 7.260	7.260
    "dent}}"	"}
    {{pp|"	0.000 + 0.693 = 0.693	-0.693
    "ent}}
    "	"
    {{pp|s"	0.329 + 1.705 = 2.033	-1.376
    "nt}}
    {"	"{{pp|sm"	1.537 + 0.000 = 1.537	1.537
    "t}}
    {{"	"{pp|sma"	0.004 + 0.000 = 0.004	0.004
    "}}
    {{p"	"pp|smal"	3.355 + 0.241 = 3.596	3.114
    "}
    {{pp"	"p|small"	0.132 + 1.883 = 2.015	-1.751
    "
    {{pp|"	"|small="	4.505 + 0.000 = 4.505	4.505
    "{{pp|s"	"small=y"	0.000 + 0.000 = 0.000	0.000
    "{pp|sm"	"mall=ye"	0.000 + 0.000 = 0.000	0.000
    "pp|sma"	"all=yes"	0.000 + 0.000 = 0.000	0.000
    "p|smal"	"ll=yes}"	0.000 + 0.612 = 0.612	-0.612
    "|small"	"l=yes}}"	0.000 + 0.574 = 0.574	-0.574
    "small="	"=yes}}
    "	3.906 + 0.240 = 4.146	3.666
    "mall=y"	"yes}}
    {"	0.037 + 1.742 = 1.779	-1.705
    "all=ye"	"es}}
    {{"	0.000 + 0.943 = 0.943	-0.943
    "ll=yes"	"s}}
    {{p"	0.000 + 1.725 = 1.725	-1.725
    "l=yes}"	"}}
    {{pp"	1.950 + 0.000 = 1.950	1.950
    "=yes}}"	"}
    {{pp-"	0.000 + 0.017 = 0.017	-0.017
    "yes}}
    "	"
    {{pp-m"	0.580 + 0.146 = 0.726	0.434
    "es}}
    {"	"{{pp-mo"	0.790 + 0.000 = 0.790	0.790
    "s}}
    {{"	"{pp-mov"	0.007 + 0.000 = 0.007	0.007
    "}}
    {{p"	"pp-move"	3.355 + 0.016 = 3.371	3.339
    "}
    {{pp"	"p-move-"	0.132 + 0.039 = 0.171	0.093
    "
    {{pp-"	"-move-i"	0.011 + 0.000 = 0.011	0.011
    "{{pp-m"	"move-in"	0.755 + 0.016 = 0.771	0.739
    "{pp-mo"	"ove-ind"	0.000 + 0.008 = 0.008	-0.008
    "pp-mov"	"ve-inde"	0.000 + 0.071 = 0.071	-0.071
    "p-move"	"e-indef"	0.000 + 0.282 = 0.282	-0.282
    "-move-"	"-indef}"	0.095 + 0.000 = 0.095	0.095
    "move-i"	"indef}}"	0.086 + 0.000 = 0.086	0.086
    "ove-in"	"ndef}}
    "	0.000 + 0.000 = 0.000	0.000
    "ve-ind"	"def}}
    {"	0.062 + 0.007 = 0.069	0.054
    "e-inde"	"ef}}
    {{"	0.316 + 0.148 = 0.464	0.168
    "-indef"	"f}}
    {{F"	0.295 + 4.875 = 5.171	-4.580
    "indef}"	"}}
    {{Fo"	0.511 + 0.000 = 0.511	0.511
    "ndef}}"	"}
    {{For"	0.000 + 0.724 = 0.724	-0.724
    "def}}
    "	"
    {{For"	0.014 + 0.693 = 0.707	-0.679
    "ef}}
    {"	"{{For "	0.029 + 0.000 = 0.029	0.029
    "f}}
    {{"	"{For "	0.000 + 7.609 = 7.609	-7.609
    "}}
    {{F"	"For out"	3.095 + 2.546 = 5.641	0.550
    "}
    {{Fo"	"or outl"	2.437 + 3.135 = 5.572	-0.699
    "
    {{For"	"r outli"	0.217 + 2.659 = 2.876	-2.442
    "{{For "	" outlin"	4.190 + 0.053 = 4.243	4.136
    "For o"	"outline"	4.208 + 0.453 = 4.661	3.755
    "For ou"	"utline"	1.79... and 9510632 more bytes
```

### Tokenization
Code from [TrieDemo.java:427](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L427) executed in 89.55 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).split(testArticle.getText(), 5)
              .stream().map(s->'"'+s+'"').collect(Collectors.joining("\n"));
```
Returns: 
```
    "{{"
    "About"
    "|the"
    " Americ"
    "an "
    "Pres"
    "id"
    "ent"
    "}}"
    "
    {{"
    "pp"
    "|small"
    "=yes"
    "}}"
    "
    {{"
    "pp-"
    "move"
    "-in"
    "def}}"
    "
    {{"
    "For"
    " out"
    "line"
    "}}"
    "
    {{"
    "Use"
    " mdy da"
    "te"
    "s|date"
    "=Jan"
    "uar"
    "y "
    "201"
    "7}}"
    "
    {{"
    "Infob"
    "ox"
    " offic"
    "ehol"
    "der"
    "
     | "
    "name"
    "          "
    "= "
    "Abra"
    "ham"
    " Lincoln"
    "
     | "
    "imag"
    "e    "
    "     "
    "= "
    "Abra"
    "ham"
    " Lincoln"
    " O-77 matte"
    " collodion print.jp"
    "g
     | alt   "
    "        "
    "= "
    "An icon"
    "ic "
    "photo"
    "gra"
    "ph"
    " of "
    "a bearded "
    "Abra"
    "ham"
    " Lincoln"
    " showing"
    " his "
    "head and "
    "should"
    "er"
    "s.
     |"
    " capt"
    "ion"
    "       "
    "= "
    "Lincoln"
    " in "
    "1863 
     |"
    " order"
    "         "
    "= "
    "[["
    "List of "
    "Pres"
    "id"
    "ent"
    "s of "
    "the "
    "Uni"
    "ted"
    " Stat"
    "es"
    "|16th"
    "]]"
    "
     | off"
    "ic"
    "e "
    "       "
    "= "
    "Presid"
    "ent"
    " of "
    "the "
    "Uni"
    "ted"
    " Stat"
    "es"
    "
     | vicepr"
    "es"
    "id"
    "ent"
    " = [["
    "Hannibal"
    " Hamlin"
    "]]"
    " <small"
    ">(1861–1865)</"
    "small"
    "><br"
    " />"
    "Andrew John"
    "son"
    " <small"
    ">(1865)"
    "</"
    "small"
    ">
     | "
    "term"
    "_sta"
    "rt"
    "    "
    "= "
    "March "
    "4, "
    "1861
     | term"
    "_end"
    "   "
    "   "
    "= "
    "April"
    " 15, "
    "1865
     | predece"
    "ss"
    "or"
    "1  "
    "= "
    "[["
    "James"
    " Buchanan"
    "]]"
    "
     | succe"
    "ss"
    "or"
    "1    "
    "= "
    "[["
    "Andrew John"
    "son"
    "]]"
    "
     | state"
    "2        "
    "= "
    "[["
    "Illinois"
    "]]"
    "
     | distri"
    "ct"
    "2     "
    "= "
    "[["
    "Illinois"
    "' 7th "
    "congress"
    "ion"
    "al "
    "distri"
    "ct"
    "|7th"
    "]]"
    "
     | term"
    "_sta"
    "rt"
    "2   "
    "= "
    "March "
    "4, "
    "1847
     | "
    "term"
    "_end"
    "2    "
    " = "
    "March "
    "3, "
    "1849
     | predece"
    "ss"
    "or"
    "2 "
    " = "
    "[["
    "John "
    "Henry "
    "(repres"
    "ent"
    "at"
    "ive"
    ")|Jo"
    "hn"
    " Henry"
    "]]"
    "
     | succe"
    "ss"
    "or"
    "2 "
    "   "
    "= "
    "[["
    "Thomas"
    " L. "
    "Harris"
    "]]"
    "
     | state"
    "_house"
    "3  = "
    "Illinois"
    "
     | "
    "off"
    "ic"
    "e3 "
    "  "
    "    "
    "= "
    "Member"
    " of "
    "the "
    "[["
    "Illinois"
    " Ho"
    "use of "
    "Repres"
    "ent"
    "at"
    "ive"
    "s]]"
    "
     | term"
    "_sta"
    "rt"
    "3 "
    "  "
    "= "
    "1834
     | term"
    "_end"
    "3 "
    "   "
    " = "
    "1842
     |"
    " birth"
    "_dat"
    "e "
    "   "
    "= "
    "{{"
    "birth"
    " date"
    "|1809|"
    "02|"
    "12}}"
    "
     | "
    "birth"
    "_pla"
    "ce"
    "   "
    "= "
    "[["
    "Hodgenville"
    ", "
    "Ken"
    "tuck"
    "y]]"
    ", "
    "U.S."
    "
     |"
    " dea"
    "th"
    "_dat"
    "e "
    "   "
    "= "
    "{{"
    "de"
    "ath"
    " date"
    " and "
    "age|1865|04|1"
    "5|"
    "1809|"
    "02|"
    "12}}"
    "
     | "
    "dea"
    "th"
    "_pla"
    "ce"
    "   "
    "= "
    "[["
    "Peter"
    "sen House "
    "(Washing"
    "to"
    "n, "
    "D.C."
    ")|"
    "Peter"
    "sen House"
    "]]"
    ",<br"
    " />"
    "[["
    "Washing"
    "to"
    "n, "
    "D.C."
    "]]"
    ", "
    "U.S."
    "
     |"
    " dea"
    "th"
    "_cause"
    "   "
    "= "
    "[["
    "Assassi"
    "nat"
    "ion"
    " of "
    "Abra"
    "ham"
    " Lincoln"
    "|Assassi"
    "nat"
    "ion"
    "]]"
    "
     | re"
    "sting"
    "place"
    ... and 454976 more bytes
```
Logging: 
```
    "{"	"{{About"	5.557 + 0.000 = 5.557	5.557
    "{{"	"{About|"	0.713 + 0.028 = 0.742	0.685
    "{{A"	"About|t"	3.997 + 0.490 = 4.487	3.507
    "{{Ab"	"bout|th"	2.459 + 0.000 = 2.459	2.459
    "{{Abo"	"out|the"	0.052 + 0.000 = 0.052	0.052
    "{{Abou"	"ut|the "	0.027 + 0.138 = 0.165	-0.111
    "{{About"	"t|the A"	0.000 + 1.447 = 1.447	-1.447
    "About|"	"|the Am"	1.593 + 6.242 = 7.834	-4.649
    "About|t"	"the Ame"	0.351 + 0.228 = 0.580	0.123
    "out|th"	"he Amer"	0.091 + 0.227 = 0.318	-0.135
    "out|the"	"e Ameri"	0.000 + 1.932 = 1.932	-1.932
    "t|the "	" Americ"	0.008 + 0.230 = 0.237	-0.222
    "|the A"	"America"	3.428 + 0.037 = 3.465	3.392
    "the Am"	"merican"	1.745 + 0.000 = 1.745	1.745
    "he Ame"	"erican "	0.360 + 0.080 = 0.440	0.281
    "e Amer"	"rican P"	0.019 + 0.456 = 0.475	-0.437
    " Ameri"	"ican Pr"	0.001 + 0.030 = 0.031	-0.028
    "Americ"	"can Pre"	0.005 + 1.627 = 1.633	-1.622
    "merica"	"an Pres"	0.026 + 1.178 = 1.204	-1.151
    "erican"	"n Presi"	0.146 + 2.870 = 3.017	-2.724
    "rican "	" Presid"	0.159 + 0.467 = 0.626	-0.308
    "ican P"	"Preside"	3.810 + 0.673 = 4.483	3.137
    "can Pr"	"residen"	2.217 + 0.022 = 2.238	2.195
    "an Pre"	"esident"	1.005 + 0.012 = 1.017	0.993
    "n Pres"	"sident}"	0.145 + 0.405 = 0.550	-0.261
    " Presi"	"ident}}"	1.824 + 0.511 = 2.334	1.313
    "Presid"	"dent}}
    "	0.000 + 3.178 = 3.178	-3.178
    "reside"	"ent}}
    {"	0.062 + 0.223 = 0.285	-0.161
    "esiden"	"nt}}
    {{"	0.111 + 2.510 = 2.620	-2.399
    "sident"	"t}}
    {{p"	0.126 + 5.308 = 5.434	-5.182
    "ident}"	"}}
    {{pp"	7.260 + 0.000 = 7.260	7.260
    "dent}}"	"}
    {{pp|"	0.000 + 0.693 = 0.693	-0.693
    "ent}}
    "	"
    {{pp|s"	0.329 + 1.705 = 2.033	-1.376
    "nt}}
    {"	"{{pp|sm"	1.537 + 0.000 = 1.537	1.537
    "t}}
    {{"	"{pp|sma"	0.004 + 0.000 = 0.004	0.004
    "}}
    {{p"	"pp|smal"	3.355 + 0.241 = 3.596	3.114
    "}
    {{pp"	"p|small"	0.132 + 1.883 = 2.015	-1.751
    "
    {{pp|"	"|small="	4.505 + 0.000 = 4.505	4.505
    "{{pp|s"	"small=y"	0.000 + 0.000 = 0.000	0.000
    "{pp|sm"	"mall=ye"	0.000 + 0.000 = 0.000	0.000
    "pp|sma"	"all=yes"	0.000 + 0.000 = 0.000	0.000
    "p|smal"	"ll=yes}"	0.000 + 0.612 = 0.612	-0.612
    "|small"	"l=yes}}"	0.000 + 0.574 = 0.574	-0.574
    "small="	"=yes}}
    "	3.906 + 0.240 = 4.146	3.666
    "mall=y"	"yes}}
    {"	0.037 + 1.742 = 1.779	-1.705
    "all=ye"	"es}}
    {{"	0.000 + 0.943 = 0.943	-0.943
    "ll=yes"	"s}}
    {{p"	0.000 + 1.725 = 1.725	-1.725
    "l=yes}"	"}}
    {{pp"	1.950 + 0.000 = 1.950	1.950
    "=yes}}"	"}
    {{pp-"	0.000 + 0.017 = 0.017	-0.017
    "yes}}
    "	"
    {{pp-m"	0.580 + 0.146 = 0.726	0.434
    "es}}
    {"	"{{pp-mo"	0.790 + 0.000 = 0.790	0.790
    "s}}
    {{"	"{pp-mov"	0.007 + 0.000 = 0.007	0.007
    "}}
    {{p"	"pp-move"	3.355 + 0.016 = 3.371	3.339
    "}
    {{pp"	"p-move-"	0.132 + 0.039 = 0.171	0.093
    "
    {{pp-"	"-move-i"	0.011 + 0.000 = 0.011	0.011
    "{{pp-m"	"move-in"	0.755 + 0.016 = 0.771	0.739
    "{pp-mo"	"ove-ind"	0.000 + 0.008 = 0.008	-0.008
    "pp-mov"	"ve-inde"	0.000 + 0.071 = 0.071	-0.071
    "p-move"	"e-indef"	0.000 + 0.282 = 0.282	-0.282
    "-move-"	"-indef}"	0.095 + 0.000 = 0.095	0.095
    "move-i"	"indef}}"	0.086 + 0.000 = 0.086	0.086
    "ove-in"	"ndef}}
    "	0.000 + 0.000 = 0.000	0.000
    "ve-ind"	"def}}
    {"	0.062 + 0.007 = 0.069	0.054
    "e-inde"	"ef}}
    {{"	0.316 + 0.148 = 0.464	0.168
    "-indef"	"f}}
    {{F"	0.295 + 4.875 = 5.171	-4.580
    "indef}"	"}}
    {{Fo"	0.511 + 0.000 = 0.511	0.511
    "ndef}}"	"}
    {{For"	0.000 + 0.724 = 0.724	-0.724
    "def}}
    "	"
    {{For"	0.014 + 0.693 = 0.707	-0.679
    "ef}}
    {"	"{{For "	0.029 + 0.000 = 0.029	0.029
    "f}}
    {{"	"{For "	0.000 + 7.609 = 7.609	-7.609
    "}}
    {{F"	"For out"	3.095 + 2.546 = 5.641	0.550
    "}
    {{Fo"	"or outl"	2.437 + 3.135 = 5.572	-0.699
    "
    {{For"	"r outli"	0.217 + 2.659 = 2.876	-2.442
    "{{For "	" outlin"	4.190 + 0.053 = 4.243	4.136
    "For o"	"outline"	4.208 + 0.453 = 4.661	3.755
    "For ou"	"utline"	1.79... and 9368924 more bytes
```

## Algeria
### Keywords
Code from [TrieDemo.java:422](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L422) executed in 45.53 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).keywords(testArticle.getText())
              .stream().map(s->'"'+s+'"').collect(Collectors.joining("\n"));
```
Returns: 
```
    "368&dil=TR&ulke"
    ".el-mouradia.dz/arab"
    ".tlfq.ulaval.ca/"
    "s/2009-wrs-country"
    ".apn-dz.org"
    "/qantara4/publi"
    ".ons.dz/-Demogr"
    "Kateb Yacine]]"
    "Factes_mondialis"
    ".qantara-med.org"
    "fur.is/svar.php"
    "li n'Ajjer]]"
    "undp-pogar.org"
    "Apn-dz.org"
    "s-des-crapules "
    "Bendjedid "
    "nel.dz/Constitu"
    "96/titre_01."
    "r/pogarp/othe"
    ".brtsis.co"
    "e-1demo.htm"
    "Vísindave"
    "=aaeo/>
    
    "
    "dintorni.jsp"
    "furinn: "
    "H3RBAAAAIAAJ"
    "Issiakhem]]"
    "/622-le-temp"
    "Moriscos|Morisco]]"
    "de locuteur"
    "faco" />"
    "ency_1_isla"
    "Rachid Mimoun"
    "sidur/turkish"
    ".tsa-alge"
    "csa16" />"
    "Djémila]]"
    "e-dz.com"
    "/dztoc.htm"
    "avirlikler"
    "NYRB Class"
    "=aaeo/> "
    "/apn/english"
    "-apr07.pdf"
    "-cannes.co"
    " article"
    "Nouria Bengha"
    ">Deeb, "
    "rabat.unesco"
    "furinn''"
    " Algeria"
    " Djaout]]"
    "r/maps/algeria"
    "MacGuckin"
    "/whtsla"
    "internation"
    " states of "
    "Algeria"
    "not in citation"
    "?AltAlan"
    " in terms of "
    " Country Studies "
    " (Country Stud"
    " live in the "
    "trovacan"
    "El Hadj M'Hame"
    ")|FLN]]"
    "indigenous"
    "tz Fanon]]"
    "ra-med.org"
    "_42379.asp"
    "r language"
    "des Statisti"
    "M'hamed "
    " Ouzou"
    "Arab Maghreb"
    "Djebar]]"
    "Malek Benn"
    "Internation"
    "1/fr/featur"
    " language"
    "l |title"
    "web.arch"
    " Mostegha"
    " construct"
    "e|Amazigh"
    "=Algeria"
    "d El Anka"
    "s/fiche"
    "ry in the "
    "Ahaggar"
    " () "
    " in the "
    ".us/algeria"
    "sity Pres"
    "ie10.htm"
    "y_Pirate"
    ".maghare"
    "Country Studies"
    "ie-.html"
    "=1755 Broa"
    "cvorient"
    "|Algeria"
    " =Ruedy, "
    " state of "
    "es/2007/06/"
    "gas proven "
    "itAlgeri"
    "territor"
    " colonial "
    " Statistic"
    " des Berbère"
    "=A Savage"
    "/altdeta"
    " 2013 |acce"
    " 2013 |df="
    " 2013 |dea"
    "13/algeria"
    " 2012 |df="
    "eflika"
    "=1852}}"
    "Mary Jane. "
    "language"
    "independ"
    "Carthagini"
    "nemi]]"
    "5130249/http"
    "sity of "
    "westman"
    "s. Algeria"
    "Factbook"
    "nu?"]. "
    " 1996 |acce"
    "es/constitu"
    "ess-service"
    " between"
    "izz, "
    "(UNHCR"
    "Moulou"
    "constitu"
    "/algeria"
    " in Algeria"
    "
    
    Algeria"
    " de Libératio"
    "gas export"
    "mist-part"
    "lUse"
    "/?profi"
    "Tell Atla"
    "es/algeria"
    "ncy-declar"
    "rate of "
    " on the "
    "e-01 |"
    "research"
    "|upright"
    "Aures]]"
    " agricul"
    " |author"
    " de l'Afri"
    " 1993. ''"
    "/Barbar"
    "ternation"
    "/772463"
    "head of "
    "proven oil"
    "art4/>
    
    "
    "a Nation"
    "(Algérie"
    "Ouarsen"
    "=19 Februar"
    "Comparison"
    "1e.pdf"
    "        "
    " (Alger"
    "e/symbol"
    "Dib]]"
    " climate"
    "y-alge"
    "/19990219"
    "10019 |"
    " politic"
    " a number"
    " | title"
    " terms, "
    " 1954–19"
    " consist"
    " campaign"
    " Commiss"
    " Douglas"
    " facilit"
    "oasis]]"
    "colonial "
    "=Country "
    "List of "
    "-wareho"
    "Algier"
    "in Barbar"
    ".webcitat"
    "ⵜⴰ"
    "er="
    "an estim"
    "amendment"
    "Cambridge"
    "part of "
    "nean"
    "2-24/wor"
    " Afri"
    " area is "
    ". Algeria"
    "Carthage]]"
    "=Constitu"
    " tribes "
    "Boumedie"
    " divided "
    "es/awi/feat"
    " (modern "
    " |arch"
    " Chapin"
    " promise"
    "> Algeria"
    " passed "
    " control"
    "contribu"
    "city of "
    " raided "
    " present"
    "e|Turkish"
    "e_Inter"
    " Constan"
    "/Algeria"
    " men and "
    " (modern"
    " territor"
    " map of "
    " Metz, "
    " Exmouth"
    " Develop"
    " =Kha"
    " research"
    " support"
    " compuls"
    " Maghreb"
    " 2013 }}"
    " (1830–1"
    " exchang"
    " contain"
    " na"
    "Annaba]]"
    "e-survey"
    "previous... and 27335 more bytes
```
Logging: 
```
    "{"	"{{see a"	5.557 + 0.000 = 5.557	5.557
    "{{"	"{see al"	0.713 + 0.336 = 1.049	0.378
    "{{s"	"see als"	2.461 + 1.844 = 4.305	0.617
    "{{se"	"ee also"	3.755 + 0.804 = 4.559	2.951
    "{{see"	"e also|"	0.013 + 0.000 = 0.013	0.013
    "{{see "	" also|P"	0.000 + 0.000 = 0.000	0.000
    "{{see a"	"also|Po"	0.000 + 0.000 = 0.000	0.000
    "see al"	"lso|Por"	0.334 + 0.000 = 0.334	0.334
    "see als"	"so|Port"	0.010 + 0.693 = 0.703	-0.683
    "e also"	"o|Port"	0.001 + 4.127 = 4.128	-4.126
    "e also|"	"|Portal"	1.773 + 4.248 = 6.021	-2.476
    "also|P"	"Portal:"	3.156 + 0.470 = 3.626	2.686
    "lso|Po"	"ortal:A"	1.435 + 0.000 = 1.435	1.435
    "so|Por"	"rtal:A"	0.916 + 0.000 = 0.916	0.916
    "o|Port"	"tal:A"	0.405 + 0.000 = 0.405	0.405
    "|Porta"	"al:A"	2.874 + 0.405 = 3.280	2.469
    "Portal"	"l:A"	0.124 + 6.538 = 6.662	-6.414
    "ortal:"	":Algeri"	4.586 + 4.682 = 9.268	-0.096
    "rtal:A"	"Algeria"	0.693 + 0.033 = 0.726	0.660
    ":Al"	"lgeria"	1.970 + 0.451 = 2.421	1.519
    "Alg"	"geria"	3.130 + 2.318 = 5.447	0.812
    ":Alge"	"eria"	0.799 + 1.674 = 2.473	-0.876
    ":Alger"	"ria"	1.281 + 1.748 = 3.029	-0.468
    "Algeri"	"ia{{"	0.134 + 1.204 = 1.338	-1.070
    "lgeria"	"a{{"	0.050 + 8.924 = 8.974	-8.874
    "ia{"	"{{!}}Al"	10.459 + 0.000 = 10.459	10.459
    "a{{"	"{!}}Al"	0.000 + 0.000 = 0.000	0.000
    "{{!"	"!}}Al"	6.139 + 0.000 = 6.139	6.139
    "{!}"	"}}Al"	0.000 + 0.000 = 0.000	0.000
    "{{!}}"	"}Al"	0.000 + 9.408 = 9.408	-9.408
    "{{!}}A"	"Algeria"	2.197 + 0.033 = 2.230	2.164
    "{!}}Al"	"lgeria "	2.197 + 0.435 = 2.633	1.762
    "Alg"	"geria "	3.130 + 2.419 = 5.549	0.711
    "lge"	"eria p"	1.180 + 1.749 = 2.929	-0.570
    "Alger"	"ria po"	0.516 + 2.639 = 3.156	-2.123
    "Algeri"	"ia port"	0.134 + 4.477 = 4.611	-4.343
    "lgeria"	"a porta"	0.050 + 2.045 = 2.095	-1.995
    "geria "	" portal"	2.291 + 1.976 = 4.267	0.315
    "eria p"	"portal|"	4.559 + 1.406 = 5.965	3.153
    "ria po"	"ortal|"	3.135 + 0.000 = 3.135	3.135
    "ia por"	"rtal|"	1.946 + 0.355 = 2.301	1.591
    "a port"	"tal|"	0.157 + 2.429 = 2.586	-2.271
    " porta"	"al|O"	2.706 + 2.140 = 4.847	0.566
    "portal"	"l|O"	2.202 + 4.373 = 6.575	-2.171
    "ortal|"	"|Outlin"	1.348 + 2.140 = 3.488	-0.793
    "l|O"	"Outline"	6.338 + 1.010 = 7.348	5.328
    "|Ou"	"utline "	3.651 + 0.494 = 4.145	3.157
    "Out"	"tline o"	0.653 + 2.217 = 2.870	-1.565
    "|Outl"	"line of"	1.253 + 0.649 = 1.902	0.603
    "|Outli"	"ine of "	0.000 + 1.606 = 1.606	-1.606
    "Outlin"	"ne of A"	0.000 + 2.758 = 2.758	-2.758
    "utline"	"e of Al"	0.076 + 1.691 = 1.767	-1.616
    "tline "	" of Alg"	0.912 + 0.000 = 0.912	0.912
    "line o"	"of Alge"	1.792 + 0.042 = 1.833	1.750
    "ine of"	"f Alger"	0.278 + 1.691 = 1.969	-1.412
    "ne of "	" Algeri"	0.006 + 0.523 = 0.530	-0.517
    "e of A"	"Algeria"	2.802 + 0.033 = 2.836	2.769
    " of Al"	"lgeria}"	2.016 + 0.288 = 2.304	1.728
    "of Alg"	"geria}}"	2.466 + 0.154 = 2.621	2.312
    "f Alge"	"eria}}
    "	1.119 + 1.139 = 2.258	-0.021
    " Alger"	"ria}}
    {"	0.445 + 2.147 = 2.591	-1.702
    "Algeri"	"ia}}
    {{"	0.134 + 0.979 = 1.113	-0.845
    "lgeria"	"a}}
    {{p"	0.050 + 5.308 = 5.358	-5.258
    "geria}"	"}}
    {{pp"	3.332 + 0.000 = 3.332	3.332
    "eria}}"	"}
    {{pp-"	0.000 + 0.017 = 0.017	-0.017
    "ria}}
    "	"
    {{pp-m"	0.824 + 0.146 = 0.970	0.678
    "ia}}
    {"	"{{pp-mo"	1.197 + 0.000 = 1.197	1.197
    "a}}
    {{"	"{pp-mov"	0.000 + 0.000 = 0.000	0.000
    "}}
    {{p"	"pp-move"	3.355 + 0.016 = 3.371	3.339
    "}
    {{pp"	"p-move|"	0.132 + 0.000 = 0.132	0.132
    "
    {{pp-"	"-move|s"	0.011 + 0.000 = 0.011	0.011
    "{{pp-m"	"move|sm"	0.755 + 0.000 = 0.755	0.755
    "{pp-mo"	"ove|sma"	0.000 + 0.000 = 0.000	0.000
    "pp-mov"	"ve|smal"	0.000 + 1.386 = 1.386	-1.386
    "p-move"	"e|small"	0.000 + 3.135 = 3.135	-3.135
    "-move|"	"|small="	4.970 + 0.000 = 4.970	4.970
    "move|s"	"small=y"	0.000 + 0.000 = 0.000	0.000
    "ove|sm"	"mall=ye"	0.693 + 0.000 = 0.693	0.693
    "ve|sma"	"all=yes"	0.000 + 0.000 = 0.000	0.000
    "e|sma... and 6363449 more bytes
```

### Tokenization
Code from [TrieDemo.java:427](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L427) executed in 41.80 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).split(testArticle.getText(), 5)
              .stream().map(s->'"'+s+'"').collect(Collectors.joining("\n"));
```
Returns: 
```
    "{{see a"
    "lso"
    "|Port"
    "al"
    ":Algeria"
    "{{"
    "!}}"
    "Algeria"
    " port"
    "al|Outlin"
    "e of "
    "Algeria"
    "}}"
    "
    {{"
    "pp-"
    "move"
    "|s"
    "mall"
    "=yes"
    "}}"
    "
    {{"
    "pp-"
    "move"
    "-in"
    "def}}"
    "
    {{"
    "Use"
    " dmy da"
    "te"
    "s|date"
    "=De"
    "cember"
    " 2016}}"
    "
    {{"
    "coor"
    "d|28|N|2"
    "|E|scale:10"
    "000000"
    "_type"
    ":count"
    "ry"
    "_re"
    "gi"
    "on"
    ":DZ|forma"
    "t=dms"
    "|dis"
    "pla"
    "y=title"
    "}}"
    "
    {{"
    "Infob"
    "ox"
    " count"
    "ry"
    "
    |co"
    "nve"
    "ntion"
    "al"
    "_long_"
    "na"
    "me"
    " = "
    "Peopl"
    "e's "
    "Democr"
    "atic "
    "Re"
    "publi"
    "c of "
    "Algeria"
    "
    |"
    "na"
    "ti"
    "ve"
    "_name"
    "     "
    "= "
    "''"
    "{{"
    "small"
    "|{{na"
    "tiv"
    "e "
    "name"
    "|ar"
    "|{{"
    "no"
    "italic|ال"
    "جمهور"
    "ية"
    " ال"
    "جزائر"
    "ية ال"
    "ديمقراطية"
    " ال"
    "شعبية}}"
    "}}}}"
    "<br"
    ">{{"
    "small"
    "|{{na"
    "tiv"
    "e "
    "name"
    "|ber|"
    "ⵟⴰ"
    "ⴳⴷⵓⴷⴰ "
    "ⵜⴰⵎⴻ"
    "ⴳⴷⴰ"
    "ⵢⵜ "
    "ⵜⴰ"
    "ⵖⴻ"
    "ⵔⴼⴰⵏ"
    "ⵜ "
    "ⵜⴰ"
    "ⵣⵣⴰ"
    "ⵢⵔⵉ"
    "ⵜ}}}}''"
    "<br"
    "/>"
    "''"
    "{{"
    "small"
    "|{{na"
    "tiv"
    "e "
    "name"
    "|fr|{{"
    "no"
    "italic|Républi"
    "qu"
    "e démo"
    "cra"
    "ti"
    "que"
    " popul"
    "air"
    "e d'Algérie"
    "}}"
    "}}"
    "}}"
    "
    |co"
    "mmon"
    "_name"
    "     "
    "= "
    "Algeria"
    "
    |"
    "imag"
    "e_flag"
    "    "
    "  "
    "= "
    "Flag of "
    "Algeria"
    ".svg"
    "
    |"
    "imag"
    "e_coat "
    "   "
    "  "
    "= "
    "Algeria"
    " emb (1976)"
    ".svg"
    "
    |"
    "symb"
    "ol"
    "_type"
    "     "
    "= "
    "Emblem"
    "
    |na"
    "ti"
    "on"
    "al"
    "_motto"
    "  = "
    "''"
    "{{"
    "small"
    "|بالشّعب ول"
    "لشّعب}}"
    "''"
    "<br"
    ">By th"
    "e "
    "peopl"
    "e and "
    "for "
    "the "
    "peopl"
    "e<ref"
    " na"
    "me"
    "=""
    "CONST-AR">{{"
    "cit"
    "e "
    "web"
    "|url"
    "=http"
    "://"
    "www"
    ".el-mouradia.dz/arab"
    "e/symbol"
    "e/text"
    "es/constitu"
    "tion"
    "96.htm"
    " |title"
    "=Constitu"
    "tion"
    " of "
    "Algeria"
    ", "
    "Art. 11 |lang"
    "uage"
    "=Arabic "
    "|publi"
    "sh"
    "er="
    "El-mouradia.dz |acce"
    "ss"
    "da"
    "te"
    "=17 "
    "Januar"
    "y "
    "201"
    "3 |dea"
    "durl"
    "=yes"
    " |arch"
    "ive"
    "url"
    "=http"
    "s://"
    "web.arch"
    "ive"
    ".org"
    "/web/"
    "20"
    "12071812411"
    "6/http"
    "://"
    "www"
    ".el-mouradia.dz/arab"
    "e/symbol"
    "e/text"
    "es/constitu"
    "tion"
    "96.htm"
    " |arch"
    "ive"
    "date"
    "=18 "
    "July "
    "201"
    "2 |df="
    "dmy"
    " }}"
    "</ref"
    "><ref"
    " na"
    "me"
    "=""
    "CONST-E"
    "N">{{"
    "cit"
    "e "
    "web"
    "|url"
    "=http"
    "://"
    "www"
    ".apn-dz.org"
    "/apn/english"
    "/con"
    "stitu"
    "tion"
    "96/titre_01."
    "htm"
    " |title"
    "=Constitu"
    "tion"
    " of "
    "Algeria"
    "; "
    "Art. 11 |publi"
    "sh"
    "er="
    "Apn-dz.org"
    " |date"
    "=28 "
    "Nov"
    "ember"
    " 1996 |acce"
    "ss"
    "da"
    "te"
    "=17 "
    "Januar"
    "y "
    "201"
    "3 |dea"
    "durl"
    "=yes"
    " |arch"
    "ive"
    "url"
    "=http"
    "s://"
    "web.arch"
    "ive"
    ".org"
    "/web/"
    "20"
    "13072"
    "5130249/http"
    "://"
    "www"
    ".apn-dz.org"
    "/apn/english"
    "/con"
    "stitu"
    "tion"
    "96/titre_01."
    "htm"
    " |arch"
    "ive"
    "date"
    "=25 "
    "July "
    "201"
    "3 }}"
    "</"
    "ref"
    ">
    |nati"
    "on"
    "al"
    "_anthe"
    "m = "
    "''"
    "[["
    "Kassaman]]"
    "''"
    "<br"
    " /... and 304530 more bytes
```
Logging: 
```
    "{"	"{{see a"	5.557 + 0.000 = 5.557	5.557
    "{{"	"{see al"	0.713 + 0.336 = 1.049	0.378
    "{{s"	"see als"	2.461 + 1.844 = 4.305	0.617
    "{{se"	"ee also"	3.755 + 0.804 = 4.559	2.951
    "{{see"	"e also|"	0.013 + 0.000 = 0.013	0.013
    "{{see "	" also|P"	0.000 + 0.000 = 0.000	0.000
    "{{see a"	"also|Po"	0.000 + 0.000 = 0.000	0.000
    "see al"	"lso|Por"	0.334 + 0.000 = 0.334	0.334
    "see als"	"so|Port"	0.010 + 0.693 = 0.703	-0.683
    "e also"	"o|Port"	0.001 + 4.127 = 4.128	-4.126
    "e also|"	"|Portal"	1.773 + 4.248 = 6.021	-2.476
    "also|P"	"Portal:"	3.156 + 0.470 = 3.626	2.686
    "lso|Po"	"ortal:A"	1.435 + 0.000 = 1.435	1.435
    "so|Por"	"rtal:A"	0.916 + 0.000 = 0.916	0.916
    "o|Port"	"tal:A"	0.405 + 0.000 = 0.405	0.405
    "|Porta"	"al:A"	2.874 + 0.405 = 3.280	2.469
    "Portal"	"l:A"	0.124 + 6.538 = 6.662	-6.414
    "ortal:"	":Algeri"	4.586 + 4.682 = 9.268	-0.096
    "rtal:A"	"Algeria"	0.693 + 0.033 = 0.726	0.660
    ":Al"	"lgeria"	1.970 + 0.451 = 2.421	1.519
    "Alg"	"geria"	3.130 + 2.318 = 5.447	0.812
    ":Alge"	"eria"	0.799 + 1.674 = 2.473	-0.876
    ":Alger"	"ria"	1.281 + 1.748 = 3.029	-0.468
    "Algeri"	"ia{{"	0.134 + 1.204 = 1.338	-1.070
    "lgeria"	"a{{"	0.050 + 8.924 = 8.974	-8.874
    "ia{"	"{{!}}Al"	10.459 + 0.000 = 10.459	10.459
    "a{{"	"{!}}Al"	0.000 + 0.000 = 0.000	0.000
    "{{!"	"!}}Al"	6.139 + 0.000 = 6.139	6.139
    "{!}"	"}}Al"	0.000 + 0.000 = 0.000	0.000
    "{{!}}"	"}Al"	0.000 + 9.408 = 9.408	-9.408
    "{{!}}A"	"Algeria"	2.197 + 0.033 = 2.230	2.164
    "{!}}Al"	"lgeria "	2.197 + 0.435 = 2.633	1.762
    "Alg"	"geria "	3.130 + 2.419 = 5.549	0.711
    "lge"	"eria p"	1.180 + 1.749 = 2.929	-0.570
    "Alger"	"ria po"	0.516 + 2.639 = 3.156	-2.123
    "Algeri"	"ia port"	0.134 + 4.477 = 4.611	-4.343
    "lgeria"	"a porta"	0.050 + 2.045 = 2.095	-1.995
    "geria "	" portal"	2.291 + 1.976 = 4.267	0.315
    "eria p"	"portal|"	4.559 + 1.406 = 5.965	3.153
    "ria po"	"ortal|"	3.135 + 0.000 = 3.135	3.135
    "ia por"	"rtal|"	1.946 + 0.355 = 2.301	1.591
    "a port"	"tal|"	0.157 + 2.429 = 2.586	-2.271
    " porta"	"al|O"	2.706 + 2.140 = 4.847	0.566
    "portal"	"l|O"	2.202 + 4.373 = 6.575	-2.171
    "ortal|"	"|Outlin"	1.348 + 2.140 = 3.488	-0.793
    "l|O"	"Outline"	6.338 + 1.010 = 7.348	5.328
    "|Ou"	"utline "	3.651 + 0.494 = 4.145	3.157
    "Out"	"tline o"	0.653 + 2.217 = 2.870	-1.565
    "|Outl"	"line of"	1.253 + 0.649 = 1.902	0.603
    "|Outli"	"ine of "	0.000 + 1.606 = 1.606	-1.606
    "Outlin"	"ne of A"	0.000 + 2.758 = 2.758	-2.758
    "utline"	"e of Al"	0.076 + 1.691 = 1.767	-1.616
    "tline "	" of Alg"	0.912 + 0.000 = 0.912	0.912
    "line o"	"of Alge"	1.792 + 0.042 = 1.833	1.750
    "ine of"	"f Alger"	0.278 + 1.691 = 1.969	-1.412
    "ne of "	" Algeri"	0.006 + 0.523 = 0.530	-0.517
    "e of A"	"Algeria"	2.802 + 0.033 = 2.836	2.769
    " of Al"	"lgeria}"	2.016 + 0.288 = 2.304	1.728
    "of Alg"	"geria}}"	2.466 + 0.154 = 2.621	2.312
    "f Alge"	"eria}}
    "	1.119 + 1.139 = 2.258	-0.021
    " Alger"	"ria}}
    {"	0.445 + 2.147 = 2.591	-1.702
    "Algeri"	"ia}}
    {{"	0.134 + 0.979 = 1.113	-0.845
    "lgeria"	"a}}
    {{p"	0.050 + 5.308 = 5.358	-5.258
    "geria}"	"}}
    {{pp"	3.332 + 0.000 = 3.332	3.332
    "eria}}"	"}
    {{pp-"	0.000 + 0.017 = 0.017	-0.017
    "ria}}
    "	"
    {{pp-m"	0.824 + 0.146 = 0.970	0.678
    "ia}}
    {"	"{{pp-mo"	1.197 + 0.000 = 1.197	1.197
    "a}}
    {{"	"{pp-mov"	0.000 + 0.000 = 0.000	0.000
    "}}
    {{p"	"pp-move"	3.355 + 0.016 = 3.371	3.339
    "}
    {{pp"	"p-move|"	0.132 + 0.000 = 0.132	0.132
    "
    {{pp-"	"-move|s"	0.011 + 0.000 = 0.011	0.011
    "{{pp-m"	"move|sm"	0.755 + 0.000 = 0.755	0.755
    "{pp-mo"	"ove|sma"	0.000 + 0.000 = 0.000	0.000
    "pp-mov"	"ve|smal"	0.000 + 1.386 = 1.386	-1.386
    "p-move"	"e|small"	0.000 + 3.135 = 3.135	-3.135
    "-move|"	"|small="	4.970 + 0.000 = 4.970	4.970
    "move|s"	"small=y"	0.000 + 0.000 = 0.000	0.000
    "ove|sm"	"mall=ye"	0.693 + 0.000 = 0.693	0.693
    "ve|sma"	"all=yes"	0.000 + 0.000 = 0.000	0.000
    "e|sma... and 6262969 more bytes
```

## Alchemy
### Keywords
Code from [TrieDemo.java:422](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L422) executed in 22.56 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).keywords(testArticle.getText())
              .stream().map(s->'"'+s+'"').collect(Collectors.joining("\n"));
```
Returns: 
```
    "CMuJGpztRFMC |acce"
    "Rasaratnākara"
    "Bensaude-Vincent"
    "tivaAnd"
    "/AlkimiaOpera"
    "-AlkimiaOpera"
    "Rayner-Canham, "
    "kimiaSpecul"
    " Alkimia Spec"
    "ārjuna Siddha"
    "Rasayana|India"
    "alchem"
    "= Alkimia "
    "Rasendra"
    " Patai. ''"
    "Henri de Tsch"
    "va.Some"
    ">C.-G. "
    "=Burckh"
    "40-96-8}}"
    "006420|loc"
    "= |bibcode"
    "Paracelsian "
    "=Patai|first"
    "tific method"
    "dt | title"
    "Rasaratna"
    "= Calian "
    "y for the "
    " alchem"
    "al-kīmiyā"
    " in terms of "
    " Cambridge"
    " Panopolis]]"
    ">Tara E. "
    "=burckh"
    "sity Pres"
    "ātha Siddha"
    "==========================="
    " (alchem"
    "======================"
    " companion"
    "rubedo]]"
    " Holmyard"
    " works on "
    "medal. ''"
    " Linden |first"
    "|Holmyard"
    "tantra, "
    "=Diderot"
    "t=Titus"
    "Jung.''"
    "rfc= |ssrn= |"
    "Alchem"
    "sity of "
    "o-Democr"
    "Chemistry"
    "dt | auth"
    "Debus. ''"
    " Eric John"
    " M. |author"
    "eCalian"
    " J. |title"
    " in the "
    " "alchem"
    " article"
    "traditio"
    "osti="
    "= William "
    "Hermes Trisme"
    "= |mr="
    "= |asin="
    " Lavoisier"
    " gold and "
    "nPrinc"
    "concerning "
    "Hermetic"
    " |author"
    "eNew"
    "Cosmos, "
    "cism]
    * ["
    "Monas hi"
    " Brunswick"
    "language"
    " Alchem"
    "Soul | loca"
    "t | isbn"
    " J. Hanegra"
    "g Prefac"
    "Classical "
    "#v=onepage"
    " contribu"
    " | title"
    " substan"
    "tiva and"
    "Hellenistic"
    "= Alchem"
    "e|New"
    "Clarendon "
    ">Raphael"
    "nGeorg"
    " Boyle"
    " states, "
    " Modern "
    "k= Anthony"
    "=Alche"
    "late Middle"
    "jfm= |jstor"
    " collect"
    " through"
    " revival"
    " documen"
    " Grafton"
    "osoph"
    "e in the "
    "esoteri"
    "discover"
    "contribu"
    "shaw |first"
    "Indiana Uni"
    "_Alchem"
    "medicine"
    "metallurg"
    "a number"
    "Paracelsus"
    "ians of "
    "subcontin"
    "spiritual"
    "William "
    ""alchem"
    "y alchem"
    "=R|publi"
    "IIA, "
    "= |oclc"
    " William "
    "rxiv"
    "Cambridge"
    " lead to "
    " Hermetic"
    "cism and "
    " at the "
    " formed "
    " all of "
    " traditio"
    " discipl"
    " connect"
    " discover"
    " Kripal"
    " concept"
    " context"
    " influen"
    " between"
    " Stanton"
    " day.<ref"
    " contain"
    " support"
    " Faivre"
    " immorta"
    " Faivre "
    "= Grafton"
    "05-8}}"
    " (cla"
    "67 | page"
    "es that "
    "encourag"
    "e |first"
    "tegrated "
    "s=Tr"
    "Jabir"
    "ical"
    "an import"
    "chemi"
    "= |doi"
    "a religi"
    "actively"
    "Roman Emp"
    " Bacon"
    "y |publi"
    " cent"
    " |o"
    "ification"
    "ne scien"
    "s in the "
    "s associa"
    "Traditio"
    "lccn"
    " harv"
    "va. Some"
    "|-->
    <"
    "ld|first"
    "Book of "
    "stry"
    "hermetic"
    "|Alche"
    "pot for "
    "elixir"
    "Magnum opus"
    "characte"
    "conseque"
    " text"
    "metallic "
    "|Lind"
    "form the "
    "zbl="
    "metals]]"
    " |edit"
    "early "
    "Stoddar"
    " Maier"
    "millenni"
    "metals, "
    "manufact"
    "=Balti"
    "eva"
    "stone"
    "for the "
    "Sanskrit"
    "Alche"
    " of "
    "fall of "
    "mut"
    "sc|ad}}"
    " Catech"
    "Fire (class"
    "= Anthony"
    "um opus"
    "/Flori"
    "os of"
    " Flower"
    "symbol"
    "s. 19"
    "y&pg=P"
    "o-al"
    "|Jabir"
    "trans"
    "y in the "
    "scien"
    " pract"
    "e.''"
    "y | loca"
    "y became"
    "y#page"
    "= Secret"
    "=Chicago"
    "=Gronin"
    "-Eisen"
    "work of "
    ".yale.edu"
    "experi"
    "exoteri"
    "1953993"
    "Marie-Louis"
    "Esoteri"
    " and "
    "02|publi"
    "rtl-lan"
    "/n0/mod"
    "spiri"
    "oglyph"
    "Air (cla"
    "mercur"
    "er="
    " 2015}}"
    "crypt"
    "|Princ"
    " phil"
    "sc|"
    " Lawren"
    "m world"
    "97. p."
    "... and 19138 more bytes
```
Logging: 
```
    "["	"[[File:"	4.381 + 0.000 = 4.381	4.381
    "[["	"[File:A"	0.718 + 0.157 = 0.875	0.562
    "[[F"	"File:Al"	3.137 + 0.000 = 3.137	3.137
    "[[Fi"	"ile:Alc"	0.539 + 0.000 = 0.539	0.539
    "[[Fil"	"le:Alc"	0.091 + 0.134 = 0.224	-0.043
    "[[File"	"e:Alc"	0.011 + 1.012 = 1.022	-1.001
    "[[File:"	":Alchem"	0.001 + 3.584 = 3.584	-3.583
    "File:A"	"Alchemy"	1.214 + 0.879 = 2.093	0.334
    "File:Al"	"lchemy "	1.777 + 0.000 = 1.777	1.777
    "le:Alc"	"chemy "	3.838 + 0.091 = 3.929	3.747
    ":Alch"	"hemy "	3.091 + 3.895 = 6.986	-0.804
    ":Alche"	"emy of "	0.000 + 0.560 = 0.560	-0.560
    "Alchem"	"my of H"	0.027 + 4.654 = 4.681	-4.627
    "lchemy"	"y of Ha"	0.684 + 1.933 = 2.617	-1.249
    "chemy "	" of Hap"	0.926 + 0.000 = 0.926	0.926
    "emy o"	"of Happ"	1.093 + 0.000 = 1.093	1.093
    "emy of"	"f Happi"	0.016 + 1.386 = 1.402	-1.370
    "my of "	" Happin"	0.049 + 1.099 = 1.148	-1.049
    "y of H"	"Happine"	4.422 + 1.715 = 6.137	2.707
    " of Ha"	"appines"	1.747 + 2.059 = 3.807	-0.312
    "of Hap"	"ppiness"	4.130 + 0.039 = 4.170	4.091
    "f Happ"	"piness."	0.511 + 2.630 = 3.141	-2.119
    " Happi"	"iness."	2.506 + 0.885 = 3.391	1.620
    "Happin"	"ness."	0.105 + 1.561 = 1.667	-1.456
    "appine"	"ess.png"	1.869 + 0.693 = 2.562	1.176
    "ppines"	"ss.png|"	0.328 + 3.689 = 4.017	-3.361
    "piness"	"s.png|t"	2.106 + 1.991 = 4.096	0.115
    "iness."	".png|th"	3.011 + 0.000 = 3.011	3.011
    "ess.p"	"png|thu"	4.758 + 0.002 = 4.761	4.756
    "ess.pn"	"ng|thum"	2.398 + 2.307 = 4.705	0.091
    "ss.png"	"g|thumb"	0.000 + 0.247 = 0.247	-0.247
    "s.png|"	"|thumb|"	0.161 + 0.000 = 0.162	0.161
    ".png|t"	"thumb|u"	0.670 + 0.000 = 0.670	0.670
    "png|th"	"humb|up"	0.002 + 0.000 = 0.002	0.002
    "ng|thu"	"umb|upr"	0.024 + 0.000 = 0.024	0.024
    "g|thum"	"mb|upri"	0.000 + 0.000 = 0.000	0.000
    "|thumb"	"b|uprig"	0.000 + 0.400 = 0.400	-0.400
    "thumb|"	"|uprigh"	0.044 + 0.039 = 0.083	0.005
    "humb|u"	"upright"	2.626 + 0.002 = 2.628	2.624
    "umb|up"	"pright|"	0.000 + 1.503 = 1.503	-1.503
    "mb|upr"	"right|["	0.000 + 0.000 = 0.000	0.000
    "b|upri"	"ight|[["	0.000 + 0.000 = 0.000	0.000
    "|uprig"	"ght|[[K"	0.000 + 0.000 = 0.000	0.000
    "uprigh"	"ht|[[K"	0.000 + 0.981 = 0.981	-0.981
    "pright"	"t|[[Ki"	0.000 + 2.303 = 2.303	-2.303
    "right|"	"|[[Ki"	1.203 + 4.440 = 5.643	-3.237
    "ight|["	"[[Kimi"	2.477 + 0.000 = 2.477	2.477
    "ght|[["	"[Kimi"	0.000 + 0.405 = 0.405	-0.405
    "ht|[[K"	"Kimi"	4.049 + 7.571 = 11.620	-3.522
    "t|[[Ki"	"imiya"	2.079 + 0.981 = 3.060	1.099
    "[[Kim"	"miya-"	2.480 + 1.299 = 3.779	1.181
    "[[Kimi"	"iya-"	3.570 + 1.128 = 4.698	2.441
    "miy"	"ya-"	6.823 + 4.691 = 11.513	2.132
    "imiya"	"a-yi"	0.118 + 1.946 = 2.064	-1.828
    "miya-"	"-yi "	3.060 + 2.663 = 5.723	0.398
    "a-y"	"yi s"	5.126 + 5.817 = 10.943	-0.691
    "-yi"	"i sa"	4.124 + 5.774 = 9.898	-1.651
    "yi "	" sa"	4.466 + 1.266 = 5.733	3.200
    "i s"	"sa'"	3.791 + 3.882 = 7.673	-0.091
    " sa"	"a'"	2.900 + 4.041 = 6.941	-1.140
    "sa'"	"'ā"	5.920 + 4.221 = 10.141	1.700
    "ā"	"ādat"	10.638 + 9.898 = 20.536	0.739
    "d"	"dat]] "	3.722 + 3.807 = 7.529	-0.085
    "āda"	"at]] ('"	1.580 + 1.308 = 2.889	0.272
    "dat"	"t]] (''"	0.829 + 2.494 = 3.323	-1.665
    "at]"	"]] (''T"	6.030 + 0.000 = 6.030	6.030
    "dat]]"	"] (''Th"	0.000 + 2.180 = 2.180	-2.180
    "dat]] "	" (''The"	0.288 + 0.116 = 0.403	0.172
    "at]] ("	"(''The "	1.770 + 3.255 = 5.025	-1.485
    "t]] ('"	"''The A"	2.769 + 0.029 = 2.798	2.740
    "]] (''"	"'The Al"	0.016 + 2.291 = 2.306	-2.275
    "] (''T"	"The Alc"	3.111 + 1.569 = 4.680	1.543
    " (''Th"	"he Alch"	0.204 + 1.099 = 1.302	-0.895
    "(''The"	"e Alche"	0.073 + 1.897 = 1.970	-1.824
    "''The "	" Alchem"	0.025 + 0.588 = 0.613	-0.563
    "'The A"	"Alchemy"	2.091 + 0.879 = 2.970	1.212
    "The Al"	"lchemy "	2.626 + 0.000 = 2.626	2.626
    "he Alc"	"chemy "	3.581 + 0.091 = 3.672	3.490
    "e Alch"	"hemy "	2.615 + 3.895 = 6.510	-1.280
    " Alche"	"emy of "	0.095 + 0.560 = 0.655	-0.465
    "Alchem"	"my of H"	0.... and 4326185 more bytes
```

### Tokenization
Code from [TrieDemo.java:427](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L427) executed in 21.19 seconds: 
```java
      return referenceTrie.getAnalyzer().setVerbose(System.out).split(testArticle.getText(), 5)
              .stream().map(s->'"'+s+'"').collect(Collectors.joining("\n"));
```
Returns: 
```
    "[[File:"
    "Alche"
    "my of "
    "Happin"
    "es"
    "s.png"
    "|th"
    "umb"
    "|upright"
    "|[["
    "Kimiya"
    "-yi sa'ādat"
    "]]"
    " (''"
    "The "
    "Alchem"
    "y of "
    "Happin"
    "es"
    "s''"
    ") "
    "– "
    "a text"
    " on "
    "Isla"
    "mic "
    "phil"
    "osoph"
    "y and "
    "spiri"
    "tual"
    " alchem"
    "y by "
    "[["
    "Al-Ghazali"
    "|Al-Ghaz"
    "ālī"
    "]]"
    " (1058–1"
    "111).]]
    "
    "{{"
    "Re"
    "direc"
    "t|Alche"
    "mist"
    "|other"
    " use"
    "s|A"
    "lche"
    "mist"
    " (d"
    "isambi"
    "gu"
    "at"
    "io"
    "n)|and"
    "|Alche"
    "my (disambi"
    "gu"
    "at"
    "io"
    "n)}}"
    "
    {{"
    "Use"
    " dmy da"
    "te"
    "s|date"
    "=June "
    "2013}}"
    "
    ''"
    "'A"
    "lchem"
    "y''' is "
    "a [["
    "phil"
    "osoph"
    "ical"
    "]]"
    " and "
    "[["
    "proto"
    "scien"
    "ce"
    "|pro"
    "to"
    "scien"
    "tific"
    "]]"
    " traditio"
    "n pract"
    "iced "
    "th"
    "rough"
    "out"
    " [["
    "Europe"
    "]]"
    ", "
    "[["
    "Egypt"
    "]]"
    " and "
    "[["
    "Asia"
    "]]"
    ".  It "
    "aim"
    "ed"
    " to "
    "purify, "
    "matur"
    "e, "
    "and "
    "perfect"
    " certain"
    " obje"
    "ct"
    "s.<ref"
    ">{{"
    "cit"
    "at"
    "ion"
    " |last"
    "=Malouin |first"
    "=Paul"
    "-Ja"
    "cqu"
    "es "
    "|contr"
    "ibu"
    "tion"
    "=Alchimie [Alche"
    "my] |contr"
    "ibu"
    "tion"
    "-url"
    "=http"
    "://"
    "hdl."
    "hand"
    "le"
    ".net"
    "/20"
    "27/spo"
    ".did2222.0000.057"
    " |title"
    "=[["
    "Encyc"
    "lo"
    "pédie]]"
    " ou Dict"
    "ion"
    "nai"
    "re"
    " Raison"
    "né "
    "de"
    "s "
    "Scien"
    "ce"
    "s, "
    "des Arts, "
    "et des Métier"
    "s, "
    "''"
    "Vol.&nbsp;"
    "I'' |loc"
    "at"
    "ion"
    "=Paris"
    " |date"
    "=1751 |edit"
    "or"
    "-last"
    "=Diderot"
    " |edit"
    "or"
    "-link"
    "=Diderot"
    " |edit"
    "or"
    "2-last"
    "=d'Alember"
    "t |edit"
    "or"
    "2-lin"
    "k="
    "D'Alember"
    "t |displa"
    "y-edit"
    "or"
    "s=0 |"
    "publi"
    "sh"
    "er="
    "trans"
    "lat"
    "ed "
    "by "
    "Lauren"
    " Yoder"
    " in "
    "20"
    "03 for "
    "Michigan"
    " Publi"
    "sh"
    "in"
    "g's "
    "''"
    "The "
    "Encyclo"
    "ped"
    "ia"
    " of "
    "Dider"
    "ot"
    " & "
    "d'Alember"
    "t Collabor"
    "at"
    "ive"
    " Trans"
    "lat"
    "ion"
    " Proje"
    "ct"
    "''}}"
    ".</ref"
    "><ref"
    " na"
    "me"
    "=lindy>{{"
    "harv"
    "p|"
    "Linden|19"
    "96|p"
    "p="
    "7 "
    "& 11}}"
    ".</ref"
    ">{{"
    "ref"
    "n|"
    "grou"
    "p=n|For"
    " a detail"
    "ed "
    "look into "
    "the "
    "problem"
    "s of "
    "defin"
    "ing"
    " alchem"
    "y, "
    "see "
    "{{"
    "Harv"
    "nb"
    "|Lind"
    "en|19"
    "96|p"
    "p="
    "6–36}}"
    "}}"
    " Co"
    "mmon aims "
    "were"
    " [["
    "chrys"
    "opoeia"
    "]]"
    ", "
    "the "
    "[["
    "trans"
    "mut"
    "at"
    "ion"
    " of "
    "ele"
    "me"
    "nt"
    "s|trans"
    "mut"
    "at"
    "ion"
    "]]"
    " of "
    ""[["
    "base metal"
    "]]"
    "s" (e.g."
    ", "
    "[["
    "lead]]"
    ") into "
    ""[["
    "noble"
    " metal"
    "|noble"
    "]]"
    "" ones (partic"
    "ul"
    "ar"
    "ly"
    " [["
    "gold"
    "]]"
    "); "
    "the "
    "creat"
    "ion"
    " of "
    "an [["
    "elixir"
    " of"
    " immorta"
    "lity"
    "]]"
    "; "
    "the "
    "creat"
    "ion"
    " of "
    "[["
    "panacea (medi"
    "cine"
    ")|"
    "panaceas]]"
    " able "
    "to "
    "cure an"
    "y "
    "disease"
    "; "
    "and "
    "the "
    "develo"
    "pment"
    " of "
    "an [["
    "alka"
    "hest]]"
    ", "
    "a un"
    "iver"
    "sal "
    "[["
    "solvent"
    "]]"
    ".<ref"
    ">{{"
    "ci... and 205402 more bytes
```
Logging: 
```
    "["	"[[File:"	4.381 + 0.000 = 4.381	4.381
    "[["	"[File:A"	0.718 + 0.157 = 0.875	0.562
    "[[F"	"File:Al"	3.137 + 0.000 = 3.137	3.137
    "[[Fi"	"ile:Alc"	0.539 + 0.000 = 0.539	0.539
    "[[Fil"	"le:Alc"	0.091 + 0.134 = 0.224	-0.043
    "[[File"	"e:Alc"	0.011 + 1.012 = 1.022	-1.001
    "[[File:"	":Alchem"	0.001 + 3.584 = 3.584	-3.583
    "File:A"	"Alchemy"	1.214 + 0.879 = 2.093	0.334
    "File:Al"	"lchemy "	1.777 + 0.000 = 1.777	1.777
    "le:Alc"	"chemy "	3.838 + 0.091 = 3.929	3.747
    ":Alch"	"hemy "	3.091 + 3.895 = 6.986	-0.804
    ":Alche"	"emy of "	0.000 + 0.560 = 0.560	-0.560
    "Alchem"	"my of H"	0.027 + 4.654 = 4.681	-4.627
    "lchemy"	"y of Ha"	0.684 + 1.933 = 2.617	-1.249
    "chemy "	" of Hap"	0.926 + 0.000 = 0.926	0.926
    "emy o"	"of Happ"	1.093 + 0.000 = 1.093	1.093
    "emy of"	"f Happi"	0.016 + 1.386 = 1.402	-1.370
    "my of "	" Happin"	0.049 + 1.099 = 1.148	-1.049
    "y of H"	"Happine"	4.422 + 1.715 = 6.137	2.707
    " of Ha"	"appines"	1.747 + 2.059 = 3.807	-0.312
    "of Hap"	"ppiness"	4.130 + 0.039 = 4.170	4.091
    "f Happ"	"piness."	0.511 + 2.630 = 3.141	-2.119
    " Happi"	"iness."	2.506 + 0.885 = 3.391	1.620
    "Happin"	"ness."	0.105 + 1.561 = 1.667	-1.456
    "appine"	"ess.png"	1.869 + 0.693 = 2.562	1.176
    "ppines"	"ss.png|"	0.328 + 3.689 = 4.017	-3.361
    "piness"	"s.png|t"	2.106 + 1.991 = 4.096	0.115
    "iness."	".png|th"	3.011 + 0.000 = 3.011	3.011
    "ess.p"	"png|thu"	4.758 + 0.002 = 4.761	4.756
    "ess.pn"	"ng|thum"	2.398 + 2.307 = 4.705	0.091
    "ss.png"	"g|thumb"	0.000 + 0.247 = 0.247	-0.247
    "s.png|"	"|thumb|"	0.161 + 0.000 = 0.162	0.161
    ".png|t"	"thumb|u"	0.670 + 0.000 = 0.670	0.670
    "png|th"	"humb|up"	0.002 + 0.000 = 0.002	0.002
    "ng|thu"	"umb|upr"	0.024 + 0.000 = 0.024	0.024
    "g|thum"	"mb|upri"	0.000 + 0.000 = 0.000	0.000
    "|thumb"	"b|uprig"	0.000 + 0.400 = 0.400	-0.400
    "thumb|"	"|uprigh"	0.044 + 0.039 = 0.083	0.005
    "humb|u"	"upright"	2.626 + 0.002 = 2.628	2.624
    "umb|up"	"pright|"	0.000 + 1.503 = 1.503	-1.503
    "mb|upr"	"right|["	0.000 + 0.000 = 0.000	0.000
    "b|upri"	"ight|[["	0.000 + 0.000 = 0.000	0.000
    "|uprig"	"ght|[[K"	0.000 + 0.000 = 0.000	0.000
    "uprigh"	"ht|[[K"	0.000 + 0.981 = 0.981	-0.981
    "pright"	"t|[[Ki"	0.000 + 2.303 = 2.303	-2.303
    "right|"	"|[[Ki"	1.203 + 4.440 = 5.643	-3.237
    "ight|["	"[[Kimi"	2.477 + 0.000 = 2.477	2.477
    "ght|[["	"[Kimi"	0.000 + 0.405 = 0.405	-0.405
    "ht|[[K"	"Kimi"	4.049 + 7.571 = 11.620	-3.522
    "t|[[Ki"	"imiya"	2.079 + 0.981 = 3.060	1.099
    "[[Kim"	"miya-"	2.480 + 1.299 = 3.779	1.181
    "[[Kimi"	"iya-"	3.570 + 1.128 = 4.698	2.441
    "miy"	"ya-"	6.823 + 4.691 = 11.513	2.132
    "imiya"	"a-yi"	0.118 + 1.946 = 2.064	-1.828
    "miya-"	"-yi "	3.060 + 2.663 = 5.723	0.398
    "a-y"	"yi s"	5.126 + 5.817 = 10.943	-0.691
    "-yi"	"i sa"	4.124 + 5.774 = 9.898	-1.651
    "yi "	" sa"	4.466 + 1.266 = 5.733	3.200
    "i s"	"sa'"	3.791 + 3.882 = 7.673	-0.091
    " sa"	"a'"	2.900 + 4.041 = 6.941	-1.140
    "sa'"	"'ā"	5.920 + 4.221 = 10.141	1.700
    "ā"	"ādat"	10.638 + 9.898 = 20.536	0.739
    "d"	"dat]] "	3.722 + 3.807 = 7.529	-0.085
    "āda"	"at]] ('"	1.580 + 1.308 = 2.889	0.272
    "dat"	"t]] (''"	0.829 + 2.494 = 3.323	-1.665
    "at]"	"]] (''T"	6.030 + 0.000 = 6.030	6.030
    "dat]]"	"] (''Th"	0.000 + 2.180 = 2.180	-2.180
    "dat]] "	" (''The"	0.288 + 0.116 = 0.403	0.172
    "at]] ("	"(''The "	1.770 + 3.255 = 5.025	-1.485
    "t]] ('"	"''The A"	2.769 + 0.029 = 2.798	2.740
    "]] (''"	"'The Al"	0.016 + 2.291 = 2.306	-2.275
    "] (''T"	"The Alc"	3.111 + 1.569 = 4.680	1.543
    " (''Th"	"he Alch"	0.204 + 1.099 = 1.302	-0.895
    "(''The"	"e Alche"	0.073 + 1.897 = 1.970	-1.824
    "''The "	" Alchem"	0.025 + 0.588 = 0.613	-0.563
    "'The A"	"Alchemy"	2.091 + 0.879 = 2.970	1.212
    "The Al"	"lchemy "	2.626 + 0.000 = 2.626	2.626
    "he Alc"	"chemy "	3.581 + 0.091 = 3.672	3.490
    "e Alch"	"hemy "	2.615 + 3.895 = 6.510	-1.280
    " Alche"	"emy of "	0.095 + 0.560 = 0.655	-0.465
    "Alchem"	"my of H"	0.... and 4251290 more bytes
```

