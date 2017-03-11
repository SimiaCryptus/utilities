This will demonstrate how to use the CharTrieIndex class for PPM and shared dictionary compression


First, we load some data into an index:

Code from [TrieDemo.java:72](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L72) executed in 34.56 seconds: 
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
    com.simiacryptus.util.text.CharTrieIndex@1dfb814c
```

And then derive a PPM codec:

Code from [TrieDemo.java:81](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L81) executed in 4.04 seconds: 
```java
      return trie.getCodec();
```
Returns: 
```
    com.simiacryptus.util.text.PPMCodec@5204062d
```



Then, we use it to encode strings:

Code from [TrieDemo.java:86](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L86) executed in 0.03 seconds: 
```java
      return WikiArticle.load().skip(100)
              .filter(article -> article.getText().length() > 1024 && article.getText().length() < 4096)
              .findFirst().get();
```
Returns: 
```
    WikiArticle{title='Ada'}
```

Code from [TrieDemo.java:92](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L92) executed in 0.08 seconds: 
```java
      Bits bits = codec.encodePPM(wikiArticle.getText(), 2);
      System.out.print("Bit Length: " + bits.bitLength);
      return bits.toBase64String();
```
Returns: 
```
    +GsObUXraIv9q5Nqggo1dJ2RLyou7LnviHExSCV9SV3bSUxhO4Cjtaw4IT7PXTRgt5ZdaCTJAHvkZJwNbMEWl/xuXTny1w9EnWBBC0x/FknMJBxMKzzdD3hQx3M5lLtj2lPRJLWHBLbryBPz0PeFJdeDDKXbHtKeiQkpkqL1Ha1hxHe2yrNtDUTlGi48kOqkvcrEnhTUzDTHoZOR+F4zbhjLWHBLIi8sFBj89ysSeFHEXlgLp7sspw9XboFi10exG6KNMSjAxiwUWSTjFrVquKY/ZYluFNbvuWVD2PDWSmwN3J6QV0x/E2LZTQ0lIxzcAjTH858MNdCkAe/zx0wpKwZuNIVrDgg/MHDRSnhk4Evh0vYyDNKLV/ErMNaWHfXWxySPR7vrKzec2tE/2AJCDJwwVEp1TvfKB0W4Cjtaw4JV5Nl87SDlh6PZ/mOuz5SLL9IVrDghC3fr1kaq4etYcEsiAx7t9SXdkcNi0bC1Rno+rwE6Y+WoNY3PM0XX+aghaY/lhQXmKhoUTblVJJxuH+KqynG7wP9369e973zVsrL8eZJyz7OcNCkKwoxAnJOWfcDtDoaNbVATpj+ZE8sUKn2hGUOHjFjYenOXmykk43D/FVaBLQB1AyD0LZ5xQjZUIQtMf0N2r6WoFoehqS7sjhsWjYWqM9H1eAnTH9D8uzMOaFT7QjKHDxixsPTnLzYC9i0mJBDwgV0Yyt1lDUlQOrzS5B2rw1RTqu4nl8nwV0x8s67vzyq4nl7C0/bTGZK6mD6vJ4vbzv0rpSkfaMH2V7q5JwPAeMIHJsNwHi/FQ67JDrOj0qVte9TQDBW8VAHIxvZGXtdBfRGiY7OHtL+ruGbd0WRcsgss5LYAemPkRBSa68wYqNt18e6umCokC5caqOrqb+i9mEOYlsQHL9HXIgrpj5FGHNAghKE432GxUco/DnJmNcYF6ZELBMUrAb2631aKDkI/UVeYmyzVXfOobnlZHOynZaw4IG9F8poOKpK1HtIXcsnqfL1ctw4SznWL3JCDyE0xJqvfWzwjLhngSyScD4RRlmZm7X7Mac88zu+meznqyKg0e7U0+xobGP5umPkQmVO6J+WunoA9Wv50zkWtYcEF0eqpFMrkDiXr2GPLU0kRj7lAIvKM555JxPIs1PLahBJ5W222uXSnruodaC3ja3G7NoKLpT16BWzFqMq6ra5EuCJHfjq5KXv8662pW3Kv/iwN+TIdSWCHO5Qm1Tkztm4nyhz9nLBaDkM9Q68BC53b0xNc+5HK8uyO9Fx9d4zCA5VB9AvVYNfA7TUmSOX+Xe6uKrWHBCEn8Yeuo4emVY7fxvkVhc+RqJYMh2mUMU9YvXCseQAvpj5RDXhFkHwyD6Fos+zmBRJxE3GBteh8WWiSbEf/La2LUYTgdxaX3+eNFFoK6Y+RPajTx+Zz50R1vtUjoel7opIl2k2nWtp05ealZCn5cUQSFFLQnV1Qh9XtTqdCYDs6xPcHTUFdtTFP0RqWkJavRTHxdGw6xFrWHBAo3NzFSPyOzl423uPBeSSEpLCkk4TNcg1yOeAJCDJwwWMF2kVHA/aSxDPrfHZJCtEwW4XRDaOPIQXajsXN6Z14kmnx1JJwPouqIHJsN6LqfipL20d8fDmfrUyNnTphKE4VKq5WLm8duGqKXYYZAJ0x84uHecsOlw/7lgKMHCCu16piS98+YwhgP7TAPVDWGyLpFQa2VsuUwUibsVJYtGh5/RQqGa386UfCRgXUiv33lWsKCBuKY/ZYluFLnmScgZuLugoVNu7Z50IxGzyBZmtiMhrpehT/HgZAR0yBAO4pj9liW4UueZJ54i4pj9liW4UueZJycfoaG9p6Kh5SsqIeAvHFdczJSRzCfpNnknfRfmCxz+beI7MIXDROW+VXThP3D+KwHXoU+qTkOVw/FCj30S7Hzc9q1KfLz+ZQyjF8ukGXCQ0qVQZfcQYzciigpPwMc/m3i+M2DBA=
```
Logging: 
```
    Bit Length: 11868
```



And decompress to verify:

Code from [TrieDemo.java:99](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L99) executed in 0.09 seconds: 
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

Code from [TrieDemo.java:107](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L107) executed in 0.54 seconds: 
```java
      return trie.getGenerator().generateDictionary(8*1024, 3, "", 1, true);
```
Returns: 
```
    e [[Angolan [[Alabama has the [[Mary 2011 |date=June 2015}}</ref name="Greek and the [[Amsterdam.nl/adam is that the stated their own as and [[Angola have book|ref=harvnb|Marchive.org/web.arch of thistory of [[Cons of a [[Amsterdam Lincoln and thanger they and as a constronautsident of ther 2012}}</ref>{{cite web|url=http://www.oikoument in the constitle = 2008 
     |accessdate wered in Anglical anthropology]], [[Battle=August = [[Listory of Angely in and station of Angolitions of an inter;"
    ! Present to the and in there these of [[Aberdeen through themistructional |title="text-align:cents of stand production of chopenhauer's such as the provincludes of Confederal of magnifican also contral and considenternal relativersity of States in 1862, and of Alable the for the elections are are to theory:Anglobal comple, three all though the most of his stable = 2013}}</mathern 1969, 2008 |isbn=978-0-8173-10-226-5 |work Oranguages==
    {{Main artically as been to because then that those including thus to [[Churchesis on thing to be third University on an as an enerate=2013}}<!-- {{citating the Angion in [[Alabama]], an in [[Johnstructure of processdategory]] and many of itself of experiously be recondon |first=May 2007 |publisher=[[The contronal into than any otheir conomicalled to an between in stance of Englisher = 2012}}<ref>
    
    ====
    {{Main a reach of Portugustribution]], theastrong to represidern Europeditor-laster;"
    ! /a/, /u/
    | stationauthought" | first thalles thorlinks of which in are in 2007 |published its one of analysisternation=New Yorks to stanticlear=2009 |deadurl= http://www.angolanguagest instellatistics of francess-dater of Mexico]] an [[atomic an on to company other to a many conce on = https://web/2008 
     | last=Johnsonald (1996), p. 11, 2010 |issue= 25 May being of lantic last = J. Amstronominatic of of American Comparian]] ared by the land [[Internment of alkalign:cented States, animall astration]] in converticultural [[Monting increaction.<ref> The field (1996), pp. 22, 2011 |deadurl=http%3A%2F%2Fwww.century, anot be used to communion for thich thundroids]], whical as [[Cate=June of Texas]] as web|url= https://web.archive.org/web/2006 |df=mdy ofter = {{IPA|-/n/
    | /a/
    |- style=The stary 2016
     | lange of relation, as inder antially, thumb|right to propeans in his contion, ances andrew and his in 19th centrology officipali metals of 1861, Lincoln's strial press the
    F-testers of Northerland relights influence thrists ancium and exployed as with theffices to representural science thally thief name="text-ality Pression is an thigh C = 1999 |publican be cound in provernald before trans]]
    [[Category:Peopleteorgestable first = |access-dation|firected that, an large United Statin 2014}}</mapse to discover [[South centallion is a studies = This prographilosophyte]] of distry annually in 1862, 1861, 1864. They was as ref = 10.1007/BF014012 |pages article|Ango]], a sevelopments or [[Unity Proto-Altaic Languistitutiveurl=no |df=mdy }}<sup>
    | /o/, /e/
    | [[Aber 2012 estars a start on was concertanthropological Sociences oftent an Alan Countroductivedate=1999 | title of ''[[The [[Conston, thite book |last1=Willess of Indian areate = 10.1021/j.1365-246X.2005 | [[Hermand resultury in man]] (1994 | pubs.usgs.google.com/books.gov/alaborn Union]], [[Ada, and, are same = 978-0-19-5090520,00000|| 1950s, thalf Staross an used anions, who had be alkali metalso thall of Brazined on this collo 11 May 2008| according and Americaticalled throught of Lingusing of characting in annot beliginal Republe to have beconomy]]. Thes only reference.<ref>
    
    ======
    [[File:Amstellated by thalogy]] | publimitter in withium]], ''[[Thomass inding systed a prode thalidesignituded with to proving anglication)}}
    {{sfnRef|SIMBAD
      | titution/ |timentral = harv | pages ==
    * [[Georginings of nonemption
      | year=1994 | url = March 2015}}</mained tractern Africal-alkans at is thanking [[Rober to formativil War = | rematics]] in Amentaining a sign="right| ''' is not in ordered thing... and 4248 more bytes
```



Then, we use it to encode strings:

Code from [TrieDemo.java:112](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L112) executed in 0.00 seconds: 
```java
      byte[] bits = CompressionUtil.encodeLZ(wikiArticle.getText(), dictionary);
      System.out.print("Compressed Bytes: " + bits.length);
      return Base64.getEncoder().encodeToString(bits);
```
Returns: 
```
    eLtJZmXOlVrbbhs3EH3PVxB5kVyoRhMjbROgD7LVtIbryLBkB+gbraW1jFZLdVcrQ7H8Sf2K/FjOmeFeVNtt+mDA2l1yhuRw5sycub//6BdCNhXb3TCxO4u/4WjIOwY/jSesUTC+SBEJ8717cX8/HZ8YQTd6FS+Y7jBd5LXUwskvsSiDCYD2bMrqa2GkOvcbUjsW6ahb3nzUl8cGZ0OPTfwSqXSWx7cHdfLCCUYOmW5kGk/LsNCZ6M7kjZItA/PBz5lYt+PGZZVrkbb+nk/++bmsofSdFbh0YK7g3VUmC3+LPDYwaLnRbOB5aDyYkiHPDD+74sb6T1DwQolu16pxZukeoyadkfF5M2JgCEndVjUSO8HWSFAhafenswQW3Y2+JJFr+hJS4KIrrawpG0n2uhCiV+bTWk1n8MCcFAED7J5e7VtkJjf1S9k+4W4BoZUWWW8bLU5w15FZfbKdM7cq2ygYkrL5rdwhYoiqyOvsHQbgkF+WCF/ckmOXAZklrhYvqguRUhdQ91awT0JDN7inAnFccqNIcnUVjYWT08SmoWtgZ3CFtvzWaabYDWZZA3Pu4efnPNp2rnNPHxGiwUq4emLkyM9w0o1GusSRXci4jgWnPjxzPuNFZlmOfSwG7wo31wL5/1vPhUNKhHBUq6XMy2O1xOavvVRfv3X3MYqUTl76/1SMhz6umLxJRaQ+8jevj2hccbQQ6cEn/Pq9z5RBWyP72Hji6c4tmV5HK25essiOU8rqJh5JBcs0VteVCo70HdK47aqoytZvIdYvD3bRVcafKgB495Xhb1bVRzb3kHAOQRgrqWtiDw8PzdB8tFst+986hfVWu0WOA64HE3ncMwmVnGdq843HrUqBHVolkjA3kpdH0fwtbSGSCCc4wcwr2U5C1a+ZWwLuSIGp1+OeHXthOjv71F+RIu8sTX+rgLnLNakPxDZJvR+ISiVrWwhRrPCULp6Oyz9vBXFuQrbRDqgVIBu+JJdyE5i6dAQXYe28eq7uYK0mkqIoNARqCVgq9hKPHdJqLoZcvRqMcl97qxJmcEmwbuouBpH01AtMXiqtc2FLnEA7zawWgdOoSsYnyB16gV1AlR2JeKh7pqV2pt6+kAqgTo69mPpCYuIwu4GZNCFLi3jaqIFhmIhJibrViYXfzZ/+YtC8FXc7aI33XHCejS/iqeHeZF/+zoFUEjb1YK6/qtrVw3CIpXnBVGkdEhcwAtqZV8rDm+FsXdv9Tz+bq4m5gY8iF6JkT1t/UuQk0FNK3bzprhBEZTdeJuNmCk8RsobfUZcNiD9XqzK3lazKONiFqjDT17KlYb0mx7Fk8oQA8of0EDUT/U7mWwWYPt2qmEDjZJ/5LvrYzrfxiaAgQetYhNDuHYvj73htbt2SRJDTdEe+1GZEJLgFfw06LVzWZF77y1ZCr2th2tfNeJEZjBF9LFtRlYLDvotNg9LNiEAf8S0SjlJkJuo4O04MXiaqGOuKIuKMDXk0C6bZszZEHBc+0VN4FKGZH04mAiVfvf3hzcODhCNkSsFsiJUzAazrpglPz6WuoCO8aJ0uN5fWZ3e2ldlnTOp4I/mpKtNHYjcXbj0DVLtDUrvAEnPWlYGUZggNtACCpigCFoFcp8FUjQzClSIJxTt45ffwYEAnJ8h+claGKCqHDYsXvs6wSUvc9g9wXouwkRng4udL1/gODihXYaHtZQpv+6eTsfnx6O33r2XF/O8IVot79xLA++UBT3IifYZlqE1oNHwC0jUqywVsgvuv5SwlLko9s4jMG8y6El4nEjBC3dG5NhEsfX721P6L5F70EL3eHTNFnkkMn2Ncasi/VGaRDXVIhWGE9T6752a9mkwQBdn9auTE2Y1xsOPTXq99LqUkvokNF2vEGpj+DAutOn1Z0hSA89lsTVIVTc9i085VM7YvkM+M9tTZzV14ePgKgKdjhw==
```
Logging: 
```
    Compressed Bytes: 1453
```



And decompress to verify:

Code from [TrieDemo.java:119](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L119) executed in 0.00 seconds: 
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

