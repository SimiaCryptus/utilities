This will demonstrate how to use the CharTrieIndex class for PPM and shared dictionary compression


First, we load some data into an index:

Code from [TrieDemo.java:72](../../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L72) executed in 33.673410 seconds: 
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
    com.simiacryptus.util.text.CharTrieIndex@74191526
```

And then derive a PPM codec:

Code from [TrieDemo.java:81](../../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L81) executed in 4.319383 seconds: 
```java
      return trie.getCodec();
```
Returns: 
```
    com.simiacryptus.util.text.PPMCodec@5204062d
```



Then, we use it to encode strings:

Code from [TrieDemo.java:86](../../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L86) executed in 0.027682 seconds: 
```java
      return WikiArticle.load().skip(100)
              .filter(article -> article.getText().length() > 1024 && article.getText().length() < 4096)
              .findFirst().get();
```
Returns: 
```
    WikiArticle{title='Ada'}
```

Code from [TrieDemo.java:92](../../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L92) executed in 0.075475 seconds: 
```java
      Bits bits = codec.encodePPM(wikiArticle.getText(), 2);
      System.out.print("Bit Length: " + bits.bitLength);
      return bits.toBase64String();
```
Returns: 
```
    +F/utS71+juLt/JlaCCLYZBCN+TVzZZ44g4uNASSrK1LrHbJiV1FO7CifhFuRYtEheFy78EliBvZoSkBlvgRbYWNa209evHp874SEPTHiTZAYqBCdZ7y9Kv1FHjZ/NHq3syZCTYUT8mOwIU9+lX6lmwBfmj1b2ZMhIx2zILNO7CigXZ23Uz9M07ct6Lwx46CQd315PFPJqGweiJ6ZIPkeOMVhRPyUTsk1B3v3fXk8UjTskxfHbk9N/sWoBajgjxEVRpqAmwMQj8GopBq1603BscM0LLlzG72/K/6/iEHbae7U94MdMeJDh7d5mjuLuJAI0x4ueMGKjiBvaZzKecjYA3emLCifg+GNyhlHCJx0n52PXyGVMrWXDTPazMBPYNjkpdHPas9Slv6yEBcCp+rMxgRDt2HvoT/N91FO7CifkfkfKw8ECLU0e0XLdbH4x/ldMWFE/CBs/XzJVMAlhRPyUSGRltT/7ly+2alxO0U9M1bAnTHYz3X7sl6bsdhYIemPFZUV0FMUcJ3yCkpB2HeDVdUXduJNn6+e8b6GrZMwdGFIXPuTbo4huCltI5SFz70O0EzRNaoE6Y8WSmylRygKBY74m2TLWVQ8jnakpB2HeDVeBVRFqFkvnml2imjlTAQ9MeMI134dQWizNP/uXL7ZqXE7RT0zVsCdMeMKy7PxBRygKBY74m2TLWVQ8jnYGCq5OGKXlwY6N4KGtHJ5ba50SoIFuHLZxd7hZ5mwpqKQcb7qOJd7hZ5iMr44da91HNDsbBf7ypnfZnvgzZhHjpgGYUT8CpqVDkwlTUhRwHkNcpYR3lFXVLJWRwVvFPNtscWjy2XjYWJ0UHVw7pv1t2zK3MW5zCVnnRWATpjsECla9swM6J9o8gakmWtEF0lOoPqOoJou5CTITgPDTzRzygMdMdgmg/QYDkwo2+xvOipF485clsBAnpkDski1YceTGeFGjk9pUvx460q+ztnlITX4VadNWZSA3Ceg4AzECIb+vJ7pRs9m+o5y9zEMgbSdHtKVVcBpfxTHXdapabwY6Y7BOb+a7gfrG+FDrab7nEGTpTZb5nOYhHqW4SUl0emOwPyUO3KJ2SoAAyzdzBn0gwon4LI9hRsZOLtmfDXBzdOpSGA288AkWTwpFIK5NzX5DSwJnlarOyzFqdehDpRG9bN4xzJ/5i1OvmL2j9bkrrMX8kYRt9ibAnpkzZ9rWlKG4GB8rbe5impl+OpzqZqOVS2Z1PrELfmC4d+1o4Dyxi4O7YmE1hcmdb8lne5bmLDxmENyqCtFXtL+PbdJTkc80M37r3owon4QE/G5rtFLzyVjz/IeTrtQcWyWhgtRqGU/RUuFo/YBHTHYgwu9J8JwwT1lqXPuS8wpBM3jG2PRflefJRmgyJi22RQlA77p5gE4sUYBjpjsErovNXvNfMSYb/FOz9THo1If7acj1OB0pQtP18qOfE/Ufo1Zj6bSwf2/WbnoTAdzsP7c2JBjttLKjqtWBFS4emmUiiMZ0Rmwon4FXTg50ccZuVetpbuflkLCxDKkpAvMr+V1IXAqfqzMYEjOecxRwP2UMOH7/IZHCqKQxQ2hDbKP2Qq1LtVOya2NExUIqkpAbo2qYF0eIdG1RnR52cl403JfVPJebej8rPiYrNvtVOw/YhUaXV4FwFaY7MTzy+YUxeYXv8LxfYRV3W+wJLX0TEkMKBZtkAHqo7i8kmN8KNx7LQofo07qo7HBoXQNGirjLB7xY2DtAuwi13NIwogQG4NjhmhZcuVRhSELMij3KOThvOhtCAkqBAu12wTKaqP0WHmwAQE6Y9QHcGxwzQsuXKowpJoLcGxwzQsuXKowpCkfM0GbK09H5Ot6EPDXkRQupgxJZhPkopFI9i373R/9DBc8iEPiU7X35Uc4FHdn5YAOrTZ9EgQe7s+NHD6aSLGTde9aZno6FNSWjLcejfKWlbKelUz8NJBuElQpTco/+hgv8fYuEA=
```
Logging: 
```
    Bit Length: 11842
```



And decompress to verify:

Code from [TrieDemo.java:99](../../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L99) executed in 0.088333 seconds: 
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

Code from [TrieDemo.java:107](../../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L107) executed in 0.516318 seconds: 
```java
      return trie.getGenerator().generateDictionary(8*1024, 3, "", 1, true);
```
Returns: 
```
    e [[Alabama have book |last=May 2012}}</ref>{{cite web|url=http://www.webcite wered the [[Amsterdam Lincoln and that the [[Amsterdam.nl/adam is their own as the stated in ther thistory of thanger 2008 |publisher=[[The first of the constitle = 2008 
     |accessdate=June 2011 |date book |lasteroids and the and [[Aberdeen they and as there thropology]], [[Listory of Alabama]], an and station of [[Marsenic and in these of an [[Angolar = [[Cons of a [[Johnstruction of [[Apollo 11, 2013}}</mather = 2013}}</ref name="Greek and producture of stand contrology of magnifical anthrough three also convertain [[Inter;"
    ! Present of considenternal of his such as are are the provincludes of Chined to the for the most = 2007 |publications, an eneral completeorgesters of States in artically, an in a cons an all themissions of planesents of itself of experiously be thought to be relativersity of Mexicity Proto-Altaic Languagest then to those theory:Astronal recondon |first=Johnson thing thus to been that third University on in Alan in 1996), pp. 11 Lunary 2011 |deadurl=http://www.amsteroids of which is a reach 2010 |isbn=978-0-8173-10-226-5 || [[Montronomic anal |title="text-alight" | field because to and many of france of Philosophern 1969, 2016
     | lastrong the electionauthought to [[Aber 2012}}<!-- {{citating to represident internation=New York Oranguages==
    {{Main as a station]] any otheir stable = https://web.archive.org/web/2008 
     | last1 = Arsental = {{IPA|-/n/
    | /a/, /u/
    | [[Batter;"
    ! /a/
    |- style=The conting to stancess-dater of languistitutionald (1994 | title of Europollo 11 |archive.org/web.arch theast1=Collowing of 1862, and of Alable to a many one of an [[Alans]], thalled by the than an on anothe largessdate=2007 |publisher to command [[United to development to discovernmentural and his conce on to compound including in [[ars as between into thally in stages thorlinking animall asternalysis on for thich is also thrugges ==
    [[File:Apost of Amstroduced Statest in 2009 |deadurl = https://web/2006 |df=mdy of of his of Texas]] and relation.<ref> The stary antic county, Alexander = 10.1002/zaac New York Oranges android not before trans a stanticlear=2007|author2=Rober ances to represear=1999 | puble to prographilot in conomicalles = 1968, 2009 |publimitter [[Georgining and American being [[Category.nasa.gov/apological anthroposition, as [[Roberties are instellatistics of distronautsidern Europenhauer's stars, thristry]] ared astructures has ancium and disting of alkalign:cented on this concertained that, an thundrew and exploreans in his in arsenic lange ofter increaction|firected Static Octobe]], whical Socience thumb|right of relign:cent on = This procession is thanic in man]], a press offices from the Amendence tern Unitudies formation)]], ''[[Thomass the
    F-testimes oned an used with the news/2007}}</maps throundered threek alphabetweenwood&Earther=[[Category:People, thyroid]] in part of North Atlantially are same = had be alkali metals at he was a [[peration/ |timentals of Georganic company containing a significated by theften anion was influences or [[Hermanded a large an Amsted fountral scienced freenwood&Earnshaw"/>{{rp|1729–1730}} -->
    * [[South thief name="text-ali metalliamong willess and, are comple in orderate journatin 1999 |work | locategraphicago Propean late=June of Hunts only thigh C = 978-0-19-5090520,00000|| 1850s, who harvnb|March 2015}}<ref>
    
    === Republe-wildings oftention]] at is not be used thium]], inding in 2006 |wormedian article|Angolition, annually in 1862, 2015}}</mainston, thite = May becordinaturesult of Lincoln's should hand with central in was tracter thankings a conter to have beliginativil War to [[Ada, annot couldest and forman experiodical star in Sea]] as web|url= haraction is north an "[[Theodored [[Chard of both a discovery in laneticultural [[First1= Erican be confeder tradities own alphabet|Arsenate = 10.1016}}</magains anglish anytime of ''[[The [[Southerlanding]] of India of Ango]], wide]] appears over ref = Aries as [[atominal productive... and 4212 more bytes
```



Then, we use it to encode strings:

Code from [TrieDemo.java:112](../../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L112) executed in 0.001303 seconds: 
```java
      byte[] bits = CompressionUtil.encodeLZ(wikiArticle.getText(), dictionary);
      System.out.print("Compressed Bytes: " + bits.length);
      return Base64.getEncoder().encodeToString(bits);
```
Returns: 
```
    eLvTsmA7lVrbUhtHEH3PV0z5RSKlUMGUneA3ISUxZYMoBLgqb8PusDvWaleZlUTJhk/KV+THck737IUAifNAFdrdme6Z6ek+fbq/fv3kF1L6Cbv7cWrvLf7G0zHRHkwRT5h085ZKtoZTegcAeDmbGAE/CgrPC9wCaku/Mb4lVIneg/QPQLjNSXkEI2TQb0Bt5IR4d8v2o6E8Ju4mdncCgaVyT2Zytwdj+b5hk6YOgTQW00/qaqEz8XrIG62QjMyZz4hwunGzehP55eZ7Pvnn57KG2vdW4PKRuQpLrzJZq1mUsU9B2S2zRZBhioEpmS+Y8Rcatf8MBc+1mu06NT7YYFumuzcyPm9HjAxRj9upRrRz+EkrKSrLLr87y+DU3+gLVnnNUK7fjc82HSWupfogVWCZT6sRvcEjMwkVBthHenVv51hR81K2TwqyDdKFE2i1mPjMIkX9bHtnblW28Qp7yWiK6yUWQIpbKr3E4iByG1/WGyFIzLErsoC0rhEvqkceXcmlRyt43GcB3UrkaKtK6YpE40hf0QgdT1KbV30D+wB/Z+tvneYSu1HnfjUypx7XNOPRdnOdesSCuooGa5LnR059gpNuNdIlTu1CxvUsOPfVC+czWxSWNY+nYvAuuKwq//96zh1QN1x/o5ZS90/VEpu/9lJi+9bdxyjSs2Xt/1MxHvpM0FO9gqNpjvzN60MaV9NVE1sT+DVimJbMEKPd1jOR6t2Sy+toxe1Lw0SydEVsvpF7RwirVL5ybHIkrDpNdquwqTu/dQthe/fRVcafKuDg6O2B4W8myFMgV0g4hSCMlSwutfv7+2ZsPtmdsvG3iAMMr1Y7lY6Rh+1YWcE9U2CBeS5tufW4VTkiQadEWmXGEhFG0fwt/R/Cd6U4wcKXWiYEvEdigqQYQZzbmQ4G3LNjL4l4b5+GbHhZ95amv1VA5kqNfRUT+bTZD1dWNcmp1FmGodrF03Hll51wcBpBpSy2CfySNZ8baFcve4JDtXZePVd/sBbAWcNqAp6w/VKaIAuZuKIQ9wbMuFKD0SrVo1VJEXm5JGQvYv+GSHruhWmTsnNb4wS6aZJGBE5jUzM+Qe7YS/EAGLEnEQ91z3ALpcfCAKMIhaeTYy8ufZCYOC5uYCZtyDInXdcJmVfPy7FWtzq38Lvl81+M2rfibked8Z5Kkmzji3hquDfFX3+WDmaDPJh54B+bxtXDcMgy8IKp0jokLmCKDDDbaAuBGSfrxu5/+tlczc0NfBTJVeVLs7brQhGyUETC9/Omu6A88tYrq0/ETSK2KqQhonPZyOYytSpzu5FVGQe7UBUSfS1bWgHvlqQX31uWZD9K21Q70XsW/FSAGdKtigm0TvaF76KP7X0bnwgKEqYFi5CqZs/i+Dtem1u3ZF3WaUokX2oXIZLdwF+jXscJgKrX/o+V1JW1bqZgl3uoRbEY0WeyFcj+6yj6YxyLfV7F6hrgYy0yU3WcPScGLxNVjMUvEfGBXXQ0C+YASRcijoNP9RSeRGimvPO5QMmDox/fPDxIOLIBwBPgu4YLZBK4bvv49FyUeJfwIoQrTPXC+uLOdjKHjEk9byQ/VWX6SOzmwq0TQLW74BLyqiUJXSAlZFFrWgBBUxQBiwA+bjFVK4NwJaRVeAev/Ktym5M8kCItZLXMlcULXxfYpCVu+xmc16Laygxw8dnStb6DA+pVtZA+IUW9Zngyn5m3h0c/vJYV879DWC3u3SsA71d7PMl5bKpsTGg6fgbStSrLBWyD+y91khMX5Z65eeENZl0JQRhrFVK/oHNtI1j+8uy5/RfJg+ghBoM71l95JjF8znCpIf9C3J10kCGdgRE2++xemvVqPkcUXLPvTE6c9MXePZ8OBt1zHr68UflMuVjXSLDQTeiax66kv+HMbncmZajJmhYSrfdOmjL1d8hnpo/Uuc9c9fDwN4CnY4c=
```
Logging: 
```
    Compressed Bytes: 1454
```



And decompress to verify:

Code from [TrieDemo.java:119](../../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L119) executed in 0.000840 seconds: 
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

