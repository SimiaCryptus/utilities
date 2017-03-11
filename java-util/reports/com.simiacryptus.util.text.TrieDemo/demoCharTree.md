This will demonstrate how to use the CharTrieIndex class for PPM and shared dictionary compression


First, we load some data into an index:

Code from [..\..\..\src\test\java\com\simiacryptus\util\text\TrieDemo.java:72](TrieDemo.java#L72) 33.830727 seconds: 
```java
      CharTrieIndex charTrieIndex = new CharTrieIndex();
      WikiArticle.load().limit(100).forEach(article -> {
          charTrieIndex.addDocument(article.getText());
      });
      charTrieIndex.index(5, 1);
      return charTrieIndex;
```
Returns: 
```
    com.simiacryptus.util.text.CharTrieIndex@94705d86
```

And then derive a PPM codec:

Code from [..\..\..\src\test\java\com\simiacryptus\util\text\TrieDemo.java:81](TrieDemo.java#L81) 3.795067 seconds: 
```java
      return trie.getCodec();
```
Returns: 
```
    com.simiacryptus.util.text.PPMCodec@704921a5
```



Then, we use it to encode strings:

Code from [..\..\..\src\test\java\com\simiacryptus\util\text\TrieDemo.java:86](TrieDemo.java#L86) 0.027743 seconds: 
```java
      return WikiArticle.load().skip(100)
              .filter(article -> article.getText().length() > 1024 && article.getText().length() < 4096)
              .findFirst().get();
```
Returns: 
```
    WikiArticle{title='Ada'}
```

Code from [..\..\..\src\test\java\com\simiacryptus\util\text\TrieDemo.java:92](TrieDemo.java#L92) 0.082297 seconds: 
```java
      Bits bits = codec.encodePPM(wikiArticle.getText(), 2);
      System.out.print("Bit Length: " + bits.bitLength);
      return bits.toBase64String();
```
Returns: 
```
    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
```
Logging: 
```
    Bit Length: 13775
```



And decompress to verify:

Code from [..\..\..\src\test\java\com\simiacryptus\util\text\TrieDemo.java:99](TrieDemo.java#L99) 0.094042 seconds: 
```java
      byte[] bytes = Base64.getDecoder().decode(compressed);
      return codec.decodePPM(bytes, 2);
```
Returns: 
```
    {{Wiktionary|Ada|ada|ADA}}
    
    '''Ada''' may refer to:
    {{TOC right}}
    
    ==Places==
    
    ===Africa===
    * [[Ada Foah]] or Ada, Ghana, a town
    * [[Ada (Ghana parliament constituency)]]
    * [[Ada, Delta]], an Isoka town in Delta State, Nigeria
    * [[Ada, Osun]], a town in Osun State, Nigeria
    
    ===Asia===
    * [[Adeh, Urmia]], also known as Ada, a village in West Azerbaijan Province
    * [[Ada, Karaman]], a village in Karaman Province, Turkey
    
    ===Australia and New Zealand===
    * [[Ada River (disambiguation)]], three rivers
    
    ===Europe===
    * [[Ada, Croatia]], a village
    * [[Ada, Serbia]], a town and municipality
    * [[Ada Ciganlija]] or Ada, a river island artificially turned into a peninsula in Belgrade, Serbia
    
    ===North America===
    * [[Ada, Alabama]], an unincorporated community
    * [[Ada County, Idaho]]
    * [[Ada, Kansas]], an unincorporated community
    * [[Ada Township, Michigan]]
    * [[Ada, Minnesota]], a city
    * [[Ada Township, Dickey County, North Dakota]]
    * [[Ada, Ohio]], a village
    * [[Ada, Oklahoma]], a city
    * [[Ada, Oregon]], an unincorporated community
    * [[Ada Township, Perkins County, South Dakota]]
    * [[Ada, West Virginia]], an unincorporated community
    * [[Ada, Wisconsin]], an unincorporated community
    
    ===Outer space===
    * [[523 Ada]], an asteroid
    
    ==Film and television==
    * [[Ada TV]], a television channel in the Turkish Republic of Northern Cyprus
    * [[Ada (film)|''Ada'' (film)]], a 1961 film by Daniel Mann
    * ''[[Ada... A Way of Life]]'', a 2008 Bollywood musical by Tanvir Ahmed
    * [[Ada (dog actor)]], a dog that played Colin on the sitcom ''Spaced''
    
    ==Biology==
    * [[Ada (plant)|''Ada'' (plant)]], a genus of orchids
    * [[Adenosine deaminase]], an enzyme involved in purine metabolism
    * [[Ada (protein)]], an enzyme induced by treatment of bacterial cells
    
    ==Computer science==
    * [[Ada (programming language)]], programming language based on Pascal
    * [[Ada (computer virus)]]
    
    ==Air travel==
    * [[Ada Air]], a regional airline based in Tirana, Albania
    * [[Ada International Airport]] or Saipan International Airport, Saipan Island, Northern Mariana Islands
    * [[Aerolínea de Antioquia]], a Colombian airline
    * [[Airline Deregulation Act]], a 1978 US bill removing governmental control from commercial aviation
    
    == Schools ==
    * [[Ada College]], a further education college in Tottenham Hale, London
    * [[Ada High School (Ohio)]], Ada, Ohio
    * [[Ada High School (Oklahoma)]], Ada, Oklahoma
    
    ==Personal name==
    * [[Ada (name)]], a feminine given name and a surname, including a list of people and fictional characters
    
    ==Other uses==
    * [[List of tropical storms named Ada]]
    * [[Ada (food)]], a traditional Kerala delicacy
    * [[Ada Bridge]], Belgrade, Serbia
    * {{SS|Ada|1905}}, a cargo vessel built for the London and South Western Railway
    * [[Ada (ship)|''Ada'' (ship)]], a wooden ketch, wrecked near Newcastle, New South Wales, Australia
    * [[Ada or Ardor: A Family Chronicle]], novel by Vladimir Nabokov
    * [[Dangme language]], spoken in Ghana (ISO 639-2 and 639-3 code "ada")
    
    ==See also==
    * [[ADA (disambiguation)]]
    * [[Ada regulon]], an Escherichia coli adaptive response proteins
    * [[Adah (disambiguation)]]
    * [[Adha (disambiguation)]]
    * [[Ada'a]], a ''woreda'' in the Oromia Region of Ethiopia
    * [[Ade (disambiguation)]]
    * [[USS Little Ada (1864)|USS ''Little Ada'' (1864)]], a steamer captured by the Union Navy during the American Civil War
    
    {{Disambiguation|geo}}
```



For faster compression, we can define a dictionary for use with Deflate:

Code from [..\..\..\src\test\java\com\simiacryptus\util\text\TrieDemo.java:107](TrieDemo.java#L107) 0.441063 seconds: 
```java
      return trie.getGenerator().generateDictionary(8*1024, 3, "", 1, true);
```
Returns: 
```
    e [[Angolan [[Alabama has the [[Markov was and the [[Angola havery in that the stated thistory of their own as are and [[Alabama]], [[Battle = 2006 |date=June 2011 |deathere thern Europenhauer's such as ther thanger of the constitle=August of Angely in the production of an and they and stand as a [[Monting theory:Americal anthropology]], an and in [[Constronomically as an et through these of [[Antiguages of chopeditor-links of stable = [[Listory of Confederal relation of [[United to themissionald (1996), pp. 11, 2012}}</ref>
    
    === Republisher=[[The cons of Angolitions of his stars an in Anglobablish an in a contronomic any of magnitution, an [[Amendence of a representativersity of Schopenhauer 2016
     | langua and his contral and provincoln an on three also conomy of provernmenter;"
    ! /a/, /u/
    | [[Inter;"
    ! President of itself of Mexico Traditionstitudes inted in articalled to the ared by the for the Amended that thought to an alphabetween to been as including to be then than because thing of which was to [[Cates anothe ear=1994 | first=May 2013}}</sup>
    | /a/
    | /o/, /e/
    | {{IPA|-/n/
    |- states, animall as were in 1862, and many othe most = 2011 |deaduring in [[List those into this third University on antic lasternal of Portuguese to stance officially is a many or thus that, in are of Europology of Texas]], theast = |accessdategory]]
    * [http://www.acaderson, thorlinks==
    {{Main alphabet|Arabic]], which thical anticlear = 2015}}</ref> The style="text-align:cents of electional companese anally, Alable the news.com/book |lastroduced Station]] androids]] and consident of Alexander = https://www.academia.educating the to ref name="text-align:centernation.<ref name="Woodwork Oranges thundrew and of choices to have thalles = {{cite book|ref=harvnb|Marchive.org/web.arch 2012}}<!-- {{Cited astruction]], a sevelopment in 2008 | title=The press of 1861, Lincoln's stration, as between thristics of of ances article|Ango]], ''[[Thomass theft to representural science tharket is cound [[Georgesters of Linguagest instructure is as wered on thing and Barbuda}}
    
    ======
    {{main 2010 |publican by thief = This a star = 1975, 2015}}</mathemy dater in start on inded ancient to company contigua and, Alan large of forman thite books.gov/library an Alans]]
    [[Category:People, annually completter an used a productive.org/web|url=http://web/2014 | publisher = 1997|pp=337–338}}</mapsed thropean areat he web |url=http%3A%2F%2Fwww.century, annot before transland relation=New York Orangolanguistic of lantion tern Union to procession for [[South thalf Stary 2014}}</mained tral in his propolic Ocean September [[Henry influence thall thaling an "[[Theoreaning a staross anglishing of his not thallowing in Altaic Langusing systernal = https://web.archived with the land in conce]], in man]] as [[Rober to de thalmental as reconomy]] are ter to a new of ''[[The [[Battle of Nation|first anythin 1999 |puble to [[Aliential areast commong [[Greek alchemic numbers, thalaj laneticultural = had being hight of distings ofter resultury to make thalibraham Lincluded formatic and ever, an conception a strian Civil War of Greek and Amend on = 10.1000 means in prographysican American Amends of Repubs/bolencessdate web|url = 978-0-19-5090520,000|| 1950s, who harv | pages ==
    [[Cabilitatic rematin Anthonempty Proto-Alta Arietish late = 10.1016}}</marked third other thaceae]] at is through to collowing willess increate=2016
     | last=Davinguists inding from thalast 2007 | isbn=978-1-93271445–406.</ref>
    
    ====
    The field beliginal proving from the world and discover 19th centrode of Indian proposition)}}
    {{sfnRef|SIMBAD
      | titution
      | publimitters on was deverate=June of relign="right" | year=2009 }}</makes hand with centradities one on is not and fore aroundern Africatically thalted freedom of Hunts or example, Anthreecember 2012 estanthreased him thal his in 1862, 2006 |deated State=14 |access-date two cal statist only could as [[Markov wates|date othesistellated by [[Johnstellatics]]. Thers ... and 4248 more bytes
```



Then, we use it to encode strings:

Code from [..\..\..\src\test\java\com\simiacryptus\util\text\TrieDemo.java:112](TrieDemo.java#L112) 0.001474 seconds: 
```java
      byte[] bits = CompressionUtil.encodeLZ(wikiArticle.getText(), dictionary);
      System.out.print("Compressed Bytes: " + bits.length);
      return Base64.getEncoder().encodeToString(bits);
```
Returns: 
```
    eLuQ8WIclVrbbttGEP2VRV4kAarRxEibBOiDLDWN4dgKKtkB2qe1tKa2WpICLzKU2J/Ur+iP9ZyZ5cW13aYPBiySe5udy5mZ8/XrZ7+tVAPvJshoLf4mswmdGISNJxQ5gbdUBqAl7+DclvOpkfKONgw+BbsSc2aVSg/7UzRuTABIYzfMIwqDX2PzC3Tc0tKhcVn70VAe09yCt+KyYw+fVebDSHttv8sEMxcqGxvup2W+1Zmo0PJGizVjc+ETJsPduHlZx/5O8z2f/PNzOQMS6+4EbjM2l0XqdU0mdtss8hpkXmv2AEbwaJySqY+ZfHHFtfV/EC5qH9p12zizNKm4k97I+LwdMTbLuti6g+6oZmsJohHjYD/gNyfpUV/QVOnCDJEXWDF4MaeRtjDZ/C6kgyrzKZeiNxjgu8gxwD7YV/d2gRM1L0V80tSFE2SEp0dpdzH1ic0CDt+7c6trCx4isaKI/lSKeHWRae2CRXdAByR/NfPMzJy4kBSINM3ysvULqUrE0vKDE/S4JezwZPR8aplSYIg+u9tohLGna7vJ+wp2hpCird9vmWYJaZRweWNz7uH6El5tN9e5J3DKo8Ka1dMjZ36Fm253pEec2a2M62nwxufP3M98Gyy7h4+XwbvCJbG1+b/O88kVW2bizba0hfR4W6LzV15QzrdKH6NYnc5K/58b46XPa+a15Q6Oprny16+OqVwN84ZVndyv+fV7H1Ll87jg9p4Btmcly6uoxe1LSVgzFxpuEO2OTYJIhpEum1wJnfn0sCvqsvNbN1hsdBddZfypC8CXvzT8TXA8Q2DHCudYCGOluLe2R0dHZmI+24P2bG+c5mJWKxcnOczjNs9pZ5psYp6lzfYeVrUBeOw2sc4TY5krx6X5W7gku2APEOYUYSyyYJCg+oo1SqBXinM9GFBmJ17S6p6chjt2FntH09+6QOKyWvvZDPfrRh5IokviGyBcQuvSxdtx2ZeDwNR9HvZKjNmR9uGAgSvAWUCVtLdwgdjr1XP1BytZgT1odlrSSEu4lmxCykguBHFvxFuqMFoXeXAqaVGlrBdE/lDiZKWnXphYoSRWKnED3TSrZgncRl0yPmFdAAFmN3sXeiviocpMi9FMYzy5Ic3kkMXSFxITJ+EaatKGLE2YlCnRwoxK3erCwu9mT38xbt+WWkFrlfdcgr+NL+KtwW7CX39mzrI1Si5aDkQcHQkUh+CRBqab1iHxADPgjKSOzLPJqmr0/sc35nJhruGj2JDT/lTSMq1iwY/4WJpWtHRXSJYPYCOTUZhC+cqDNPk6lx2CS1SrzE0tpzKRdkI71tci0ryqSBdLzQfLLtBHqbe2E30AeokLmCHdqqhA62Sf+S762N638YmgIEkwcAihs/Q0jr+j2dy4lMmnM4lnOVRKOBIQTcleTurGPV6XNcErdXEnXAbth/mW7KPZRozocxEFkromrfoYx0LOirpZGUq1bLRWx9lzYvAycYtVw2XDkDNyC6kWZH+suhBxUvi13sKjCM26y2IhUPLl2+9f399LOEKGnJs9E8sgVaOqZb7pvcjRNLxI7Riq+qv14dZ2aw4Zk3reSH7qlukjIc2tq1aAardIYdnJzUilAlJaITRQAwia4hJWcpcWU7VrEK4U67x4B6/8Hh4M6GS6KQCtV0FOy3xNvPBVgJBSWPsFnNc232siC6eRutZ3cEC5y7dS9lbUa4ani7n54fjtd6/kxPzvGFoLu3sB4P1ixJtcRJpio0KzyROQrt2yGGAb3H8uVxvioo1nIh28waw7AfraciiF+Ern2kawzfOzb+y/rDyIHmIwuGVWwzuJ4XPOugrQqPbeyKurYFK7Vs7uuVkvFwtEQeFYyI0ziRvd8elg0D2XChTfRK4W8uOUzQMctO5RK5UJc2H3BxMZhpFoqYyEltzAYs3swXbuEpff3/8NgKdjhw==
```
Logging: 
```
    Compressed Bytes: 1453
```



And decompress to verify:

Code from [..\..\..\src\test\java\com\simiacryptus\util\text\TrieDemo.java:119](TrieDemo.java#L119) 0.000866 seconds: 
```java
      byte[] bytes = Base64.getDecoder().decode(compressed);
      return CompressionUtil.decodeLZ(bytes, dictionary);
```
Returns: 
```
    {{Wiktionary|Ada|ada|ADA}}
    
    '''Ada''' may refer to:
    {{TOC right}}
    
    ==Places==
    
    ===Africa===
    * [[Ada Foah]] or Ada, Ghana, a town
    * [[Ada (Ghana parliament constituency)]]
    * [[Ada, Delta]], an Isoka town in Delta State, Nigeria
    * [[Ada, Osun]], a town in Osun State, Nigeria
    
    ===Asia===
    * [[Adeh, Urmia]], also known as Ada, a village in West Azerbaijan Province
    * [[Ada, Karaman]], a village in Karaman Province, Turkey
    
    ===Australia and New Zealand===
    * [[Ada River (disambiguation)]], three rivers
    
    ===Europe===
    * [[Ada, Croatia]], a village
    * [[Ada, Serbia]], a town and municipality
    * [[Ada Ciganlija]] or Ada, a river island artificially turned into a peninsula in Belgrade, Serbia
    
    ===North America===
    * [[Ada, Alabama]], an unincorporated community
    * [[Ada County, Idaho]]
    * [[Ada, Kansas]], an unincorporated community
    * [[Ada Township, Michigan]]
    * [[Ada, Minnesota]], a city
    * [[Ada Township, Dickey County, North Dakota]]
    * [[Ada, Ohio]], a village
    * [[Ada, Oklahoma]], a city
    * [[Ada, Oregon]], an unincorporated community
    * [[Ada Township, Perkins County, South Dakota]]
    * [[Ada, West Virginia]], an unincorporated community
    * [[Ada, Wisconsin]], an unincorporated community
    
    ===Outer space===
    * [[523 Ada]], an asteroid
    
    ==Film and television==
    * [[Ada TV]], a television channel in the Turkish Republic of Northern Cyprus
    * [[Ada (film)|''Ada'' (film)]], a 1961 film by Daniel Mann
    * ''[[Ada... A Way of Life]]'', a 2008 Bollywood musical by Tanvir Ahmed
    * [[Ada (dog actor)]], a dog that played Colin on the sitcom ''Spaced''
    
    ==Biology==
    * [[Ada (plant)|''Ada'' (plant)]], a genus of orchids
    * [[Adenosine deaminase]], an enzyme involved in purine metabolism
    * [[Ada (protein)]], an enzyme induced by treatment of bacterial cells
    
    ==Computer science==
    * [[Ada (programming language)]], programming language based on Pascal
    * [[Ada (computer virus)]]
    
    ==Air travel==
    * [[Ada Air]], a regional airline based in Tirana, Albania
    * [[Ada International Airport]] or Saipan International Airport, Saipan Island, Northern Mariana Islands
    * [[Aerolínea de Antioquia]], a Colombian airline
    * [[Airline Deregulation Act]], a 1978 US bill removing governmental control from commercial aviation
    
    == Schools ==
    * [[Ada College]], a further education college in Tottenham Hale, London
    * [[Ada High School (Ohio)]], Ada, Ohio
    * [[Ada High School (Oklahoma)]], Ada, Oklahoma
    
    ==Personal name==
    * [[Ada (name)]], a feminine given name and a surname, including a list of people and fictional characters
    
    ==Other uses==
    * [[List of tropical storms named Ada]]
    * [[Ada (food)]], a traditional Kerala delicacy
    * [[Ada Bridge]], Belgrade, Serbia
    * {{SS|Ada|1905}}, a cargo vessel built for the London and South Western Railway
    * [[Ada (ship)|''Ada'' (ship)]], a wooden ketch, wrecked near Newcastle, New South Wales, Australia
    * [[Ada or Ardor: A Family Chronicle]], novel by Vladimir Nabokov
    * [[Dangme language]], spoken in Ghana (ISO 639-2 and 639-3 code "ada")
    
    ==See also==
    * [[ADA (disambiguation)]]
    * [[Ada regulon]], an Escherichia coli adaptive response proteins
    * [[Adah (disambiguation)]]
    * [[Adha (disambiguation)]]
    * [[Ada'a]], a ''woreda'' in the Oromia Region of Ethiopia
    * [[Ade (disambiguation)]]
    * [[USS Little Ada (1864)|USS ''Little Ada'' (1864)]], a steamer captured by the Union Navy during the American Civil War
    
    {{Disambiguation|geo}}
```

