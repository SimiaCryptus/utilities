This will demonstrate how to use the CharTrieIndex class for PPM and shared dictionary compression


First, we load some data into an index:

Code from [TrieDemo.java:72](..\..\..\src\test\java\com\simiacryptus\util\text\TrieDemo.java#L72) executed in 35.595519 seconds: 
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
    com.simiacryptus.util.text.CharTrieIndex@4f4b6259
```

And then derive a PPM codec:

Code from [TrieDemo.java:81](..\..\..\src\test\java\com\simiacryptus\util\text\TrieDemo.java#L81) executed in 4.227109 seconds: 
```java
      return trie.getCodec();
```
Returns: 
```
    com.simiacryptus.util.text.PPMCodec@16f7c8c1
```



Then, we use it to encode strings:

Code from [TrieDemo.java:86](..\..\..\src\test\java\com\simiacryptus\util\text\TrieDemo.java#L86) executed in 0.029027 seconds: 
```java
      return WikiArticle.load().skip(100)
              .filter(article -> article.getText().length() > 1024 && article.getText().length() < 4096)
              .findFirst().get();
```
Returns: 
```
    WikiArticle{title='Ada'}
```

Code from [TrieDemo.java:92](..\..\..\src\test\java\com\simiacryptus\util\text\TrieDemo.java#L92) executed in 0.079058 seconds: 
```java
      Bits bits = codec.encodePPM(wikiArticle.getText(), 2);
      System.out.print("Bit Length: " + bits.bitLength);
      return bits.toBase64String();
```
Returns: 
```
    +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
```
Logging: 
```
    Bit Length: 13605
```



And decompress to verify:

Code from [TrieDemo.java:99](..\..\..\src\test\java\com\simiacryptus\util\text\TrieDemo.java#L99) executed in 0.107908 seconds: 
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

Code from [TrieDemo.java:107](..\..\..\src\test\java\com\simiacryptus\util\text\TrieDemo.java#L107) executed in 0.370901 seconds: 
```java
      return trie.getGenerator().generateDictionary(8*1024, 3, "", 1, true);
```
Returns: 
```
    e [[Alabama has the [[Angolan [[Markov was and the [[Amsterdam.nl/adam is that the stated their own as are and [[Aberdeen thistory of thanger they and as a [[Listory of ther 2008 |publisher=[[The comple, there these of Angola have book |last=May 2012}}</ref>{{cite web|url=http://www.webcite wered in the constitle = 2008 
     |accessdate=June 2011 |date book |lasterdam Lincoln and three also contrology of an in Angely in and station of [[Amsteroids an all thropology]], [[Cons of a constronomically are the ared to the production of [[Anglobable = [[Batternal an articalled by the for the elections, anthrough themissions of stand in theory:Astronal of chopenhauer's such as the most of Chined asteroids of magnifical relativersity of his stably though the to then to been that those thus to [[Inter;"
    ! Present of States of planesents of itself of experiously be thing third University on theastronomic an eneral conting to be used to recondoned than because to an an in [[Johnstructure of france of Portugustributional |title="text-align:centers of which is a reach thalles in 1996), pp. 11, 2007 |publicationauthought" | first = 2013}}</mather = 2015}}</ref name="Greek and provincludes thorlinks of Mexico]] any of languagest thich 2011 |deadurl=http://www.amsternation=New York Oranguages==
    {{Main as analysis on an [[Alabama]], an on internmentural and consident of Angolition]], thrugges ==
    [[File:Amsted Station]] including in [[ast = J. C.
     | last1=Willess thundroid not between into thally represidence of Alable that, and of anotheir conomy of of Amstellating the largester;"
    ! /a/, /u/
    | [[Montroduced one of Europological as being of discovernald (1994 | title ofter of relation.<ref> The field before trans]], whical sciences animall astruction, antic langolantially, ance tern 1968, 2016
     | puble to stancess-dategory]] and many othe news.com/books.gov/officipal in stanticlear=2007 |publishern Europeditor-links==
    The states, thumb|right to company one on to distration, as [[Aber 2012}}<!-- {{citatistics of 1862, an the
    Forces to propeaning a style=The press offices are in 2006 |deadurl= https://web.archive.org/web/2008 
     | last1 = Alan large of Texas]] are same = http%3A%2F%2Fwww.cented andrew and his convertain a many conce]], a sevelopment increater = 19th century, thrists instely commong ancium and relight to a productive.org/web.archiveurl= https://web/2009 }}</mained and exployed with to cound [[Cate=2010 |df=dmy 
    }}<ref>
    
    ====
    {{Main article|Ango]], ''[[Thomass and, are comporth theften annually in 1998 |deatmentals of nonemption|first=Johnsonald (1996), p. 22, 2010 |isbn=978-0-8173-10-06-246X.200802012 |pages = {{IPA|-/n/
    | /a/
    |- stary 2014}}</mapse in contion is concertainston, thief name="text-align:central star = This in 2015}}</magaining, althor=NASA |locatic of Norther to dever, a stars as web|url=no |df=dmy 
    }}</may/histry otherland American arch 2012 estituticatin 1862, 1861, 1864. They with central [[United throposition)}}
    {{Use distics]]
    [[Category:Peopleteorgessdate=10 | year=1999 | url = 10.1002/04/1432.</reportuguesear = 10.1016}}</markets an Alans a strial propean experiodical Socience thite journaticalled traction/2276
      | titution
      | [[Characting and displayer
    * [[Amendent to represe also thalf Stars influences or thall of ''[[The [[Southor2=Rober an used from the Amestimes oned a significated Stativil War to have thalogy]] of Indian collowing [[Georgining of alkali metallion this northe results is of Lincoln's shortanced for [[Hermand in man]] as inder in his procession = 978-0-19-5090520,00000|| 1850s, who had be alkali metals als, in prographysis anion formation-empty Proto-Altaic Languisting in annot thalidesignituded [[Roberties fountrode thalliamonthesistellated by thalf-like two othest in was thanic Octobe]] at is at he work=[[American be resultury anglish anythium]], in ordern Union]], [[Apollo 8 Floridge Univedate=June 2006 |issue= 25 May beconomy]]. Ther=[[Constant an "[[Theoreans inding syster [[South an Eurasian... and 4248 more bytes
```



Then, we use it to encode strings:

Code from [TrieDemo.java:112](..\..\..\src\test\java\com\simiacryptus\util\text\TrieDemo.java#L112) executed in 0.002606 seconds: 
```java
      byte[] bits = CompressionUtil.encodeLZ(wikiArticle.getText(), dictionary);
      System.out.print("Compressed Bytes: " + bits.length);
      return Base64.getEncoder().encodeToString(bits);
```
Returns: 
```
    eLtQhGIIlVrbbttGEH3PVyzyIhlQjCZG2iZvstQ2huvIsOQE6NtKWlEbUqTKiwzF8if1K/pjOWdmeVFtt+mDAYvk7uxlLmfmzP39Zx8LdZfvD8OlPVj8DcdDGjccJZ7QdzKgS36LKd6/uL+fTUZGxKgPuE5wHiQz6A+GK8KqOvPFBADyds2SWW7wa2B+gzFYKmQJp9981JfHSBdy5vdEeIFy5173JyH8ywRjB+gY+LiLIot1Jhq+vFG2dmA++og4rh03KapUGYP6ez755+eyh8J3duDgwm5huCqTdcg4DQ0OMq81OwBv3hGmJKo2Q0T1ufVfsMBrJTRdu4xLSxAZVtIZGZ43IwZmVuWx2+uKSOHSjgS1kcT8w1n62u5B35BoNn3EbgsQFFWCZ04CC+mcyYWIlvmUiuoMHphRnmGAPVpX+3aKHdUv5fiEFxNEvxWA1qxi5CMLjPjFdu7cqmzjtTjAIBZsGc4RsE0aLZR2cUhSCoQ0Hsm5SwAWlq4WL0uXGn5dfz/awTFVi7XBJnJEIvEhjKlVerTQUAu7WNp11lWwS5sWtvjeaWY4DRaDBubKw3tEvNp2riuPwFpkQWEZJ58aOfYL3HSzIt3i2MYyrqPBa589cz+TOLFkhx6LwbvcRVrX/H/7uXZ5zOJDvSwtUT5elui8xITUf+/pYxRJ4bTw/7kwXvqEWEkdb33lb9+cUbnC6Lo3hF8DAiuhB5ftdp7xtWMls09Bi5uXjHK4paRu9qHdEcto4eyIOUIY22/zqmj91grCTg7BVYafKgCA87Xhb5azxzb1kHAFQRgrVZ+lPT09NUPz2e6VA1gBZPZ6HCpE6nkG82ARCnamWAXzzGy687CqNcJ5u4hlFhnJMYJo/pb2FmFVlrjBxKeh/cQUvmRBD4hB0rVej2d27qUc0jmnPntdys7W9LcKiFxaSb6ZEb0t6/NwaVawULd0liGncOF2XPp1LwB2lyU77araVjm/ZLY3x+qKTUdwnpXOq+fqDtZuA3IDeSA1pbomZVABKwj84t5GCIWqMEpSHe1KCI6N4LC6h0QkPfUCk0tRAV7ZFriBdppFLQK3URWMT5A7xM3ATe9c0pGIh3pmWv9kncDnUs7UyXEWM59LTBwmc6hJE7KMNCNp4worGp7GUapbnVr43fTpLwbNW3G3g1Z5r6RObsOLcGuwm+Tvv1IHtXHE2D77s6pdPRSHCTUNTBetQ8IGxgDKUaV9G2a4KGu9/+lnczs1c/goFq01tY8a7BO6nZhdSS2Klg5AKnXtnZfJeJhSUMoS6ZxpXbaUGlTOqlIyF9BO24+Ei3MaTWdZWbIYtTEfLLsEBQa1cOMD0EsQYPp0q6ICjZN95rvgYzvfhieCgqTMgE0Ib93ROP4OZrNyG9KDTnG5fKmNj6Ygf7pxg04zkDWJ1/63rXC+yl5o9aaG5lR9UfmJHAVyoCKI/j2MxTlvtWEG7mFTiMylOs6OE4OXCUsM5SkRccnWNKoFM9ZFGyLOhRHj948iNLPv6VSg5Ot3P7x9eJBwBJybmR3QN1zgvPJJ2TQP6r1oAVHCi5Qloao31id3tpXZZ0zqeCP5qUumj8Rpxq5cAKrdIeWLscWUFDeQ0gKhgRpA0BREQCOQ+DSYqpFBuJIvs/w9vPKv8GBAJ6N1Tpogkd2ywCNe+FOCQ9rA2j/CecXZTmaAi482rvEdHFBss1jasxT1mv7FdGJ+PHv36o3smP+dQWthdy8BvF+e8CanoTu0VqHx8AlI1yxZDLAJ7r8UizVx0dozaU28waxbKawHVkh6Pulcmwi2fn72tf0Xyb3gIXq9O6Y2vJMQPicwasi/UbqHjWElTGrbnLN7btbb6RRRsGRbmNw42yZODnza67XPpZbDN6H7p0SsIYOGjVZ5250qXC7uZ4fcrcrrjraGFR7V5P4L5DPjo+UcIpc9PHwDgKdjhw==
```
Logging: 
```
    Compressed Bytes: 1456
```



And decompress to verify:

Code from [TrieDemo.java:119](..\..\..\src\test\java\com\simiacryptus\util\text\TrieDemo.java#L119) executed in 0.000872 seconds: 
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

