This will demonstrate how to use the CharTrieIndex class for PPM and shared dictionary compression

First, we load some data into an index:
Code: 
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
    com.simiacryptus.util.text.CharTrieIndex@dd1a4f25
```
And then derive a PPM codec:
Code: 
```java
      return trie.getCodec();
```
Returns: 
```
    com.simiacryptus.util.text.PPMCodec@5204062d
```


Then, we use it to encode strings:
Code: 
```java
      return WikiArticle.load().skip(100)
              .filter(article -> article.getText().length() > 1024 && article.getText().length() < 4096)
              .findFirst().get();
```
Returns: 
```
    WikiArticle{title='Ada'}
```
Code: 
```java
      Bits bits = codec.encodePPM(wikiArticle.getText(), 2);
      System.out.print("Bit Length: " + bits.bitLength);
      return bits.toBase64String();
```
Returns: 
```
    +E5+vTPo5/vpnQ30wga10WZIHJFzX0+7wb6GgSZrK2rgH6N0zjaTGDFFC3dVowr1zRxBJshH3dGUINZkEYd6jQuPnb2B6Fm2DhB050FeKmgf4lmpdnp29UPc5qslvWe9pIJIwYopo9XHe8/Tt6pRq4JMlvWe9pIJCP0oheJMYMUa8t21c3UuorlHbc+QLmpL3zWc5k4mwaZPDUpv/FbMY7BiimZTklKg95++aznMj6ckpF26coph6m6ALJrw/iGoi1PKADElBJTKEZuaqFu2K2RF1nZHf90yy+2xYj9Yq6x8gn050E0W4Gl0m45xoBNOdDvw43TyQj72eBRqLso1Wq7BiihJOGKmAvevXNSXVasbzhag1rHLCM7v2dicFrGTnTDt9OihTS7kEoxbzEAFA1v603nEQOCfTnQMjd9qXUn7rkzWtFxdo7z7skBPTnGZ0wtwHryoQdOdCSYcAzyn2i2IeHbMraVH8vllJQjmJ96o7rdRBgnDBiimfa2g73pdHVowJ6c42utkvSNK/Vcj+uXEtFJHKwhB050MqNHSbjnGgE050M0dCd288a+q7BiimjtnH0+pP3XJmtaLi7R3n3ZICenONrrZL0oWnzeI7r+uY4XOjR9FMzSUoS+Z3pZa8VLWeU+0WxDw7ZlbSo/l8spKEvmnN86z0ub/LR1Gz/LtdUAR6ktTj9t2mTlBjvYJ9I8FG88p2bZzOKpEsw2bBl3uNnfbGKZQjhkvpLLvcbO+Rtexie0/qmaXY2S//Lmu9LNnBW1CRHMuhUUIO/vBsEEiHX94My0Qevh2xh4pExeKuAGC99x1Npjpk5hLpryFUgHowzpv1W9Zt3BZvyaOUTrMAF05xiNrAuTNDLR3tzjHWUz17QamPqr6pqPaH1QiY9OgaHMtG3Egn05xiqjhCYSThPuNbctHyHhTszuxIEac+q3ov1+3grMrZf5C/0hXmhtNRq951KOe1miAalnihB8D0E8Ui/yF/pCu48lPruNDcrcjzPDuRShpN60wGhu/saN3VpTME+nOMVm9Fq1IHjYCR56G7jC+UpS5q52bZGO+dNHEydpzjEQk3hFG7MTIAZai5ln0WwYooO5dbSRZOWtwefsUdGrpajHWp9RksTf0ZQi8hy65JTAIvO1Wa3F1Uxe/EWlLitBixM0El1UxfsWtV6vM6udfJcghxn2VRRV/86C0SuuVeV7P3nMt1lXgdLJU1UxT3Zrp1ImXVyaVm4DL0Qe8wcd2+MNrr2s8Pl2qbu286yxeEH05xM4X18P/d8zTkPWWOhN8drYMUUJaWjz01HrzTOHmeE8vrxm+LZQQyOnZc7TBF64HIHAI6c40WVaSDCUME9xUL36cwGlCKT6Jre7t7+OhJblQFNG32WYKgd26k4CeJ0s4J9OcYpFGZ556AZ6k9fapWiGKNEZG/W5/O9bfqykaTYynKMT+iVEbROvaYD030Tnc8YDtdkXcHY4E+3qCnK8o6k5Z+lOORzGe5xLYMUUDb878tJeOWnuK1V6QDSnwhT2pKELzPA1Mjpf+gg0cOGLOdBVVsvuIYs4EPJcm4rhYGKCohsNIHIZaY2SmjNrFyUUy1JQg+dahggkQ7nWoMtJ+xJmPl23qpknnUpRmE+JKwsVkponrESim6ZBoAjpzjyAPSyal1CI+diwVGnhxTa+KrkZfYURhlPdkgHq4riEieNgJN+7bKINRZzFPWERolQ6IlWGwJRoB1SWgHrot92hsGIWBt2xWyIus7GpKUJCzMRelPKbN50OTGJObQJMVs0y+uT3ld48F8BOnOwDt2xWyIus7GpKUNCKt2xWyIus7GpKUJSQ0uh+1dbRyTLOiHbrzxdOphZLZbPUpkZQwItG+n++bg6d9CDxKaxZVBygxp53H27R7gLbmA4aedMtJTFbvY5+6lVPrtybGeLRcFDxZWKIQC50vNo09K8OAB6LTE1f/dxmSCA==
```
Logging: 
```
    Bit Length: 11765
```


And decompress to verify:
Code: 
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
Code: 
```java
      return trie.getGenerator().generateDictionary(8*1024, 3, "", 1, true);
```
Returns: 
```
    e [[Alabama have book|lasteroids]], [[Angolan also constitle = 2007 |publishere the [[Mars and the [[Aberdeen that the stated their For thistory of thanger 2013}}</ref>{{cite web|url=http://www.webcite wered in ther they and [[Apollo 11, 2012}}</mather = 2013}}</ref name="Greek and as thern University on of the contrology of Angola has a [[Listory of an [[Aber 2011 |date=June 2012 estitution of [[Montronal and these of [[Angely in Angion three all themissional |title="text-alight" | first of stand stations of magnifical of Confederal relativersity of comporth thropology]], an and in thought to the and production anthrough then to those theory:Astronomic an enerates of Stater of his such as are are the provincoln an in [[Cons of Alable to been that thing thus the for the electionstics]] and conomically, analysis on in artical and of a completeorgesternal in 1996), pp. 11 Marchive.org/web.arch 2012}}<!-- {{citating the most = [[Inter;"
    ! Present of planesenter;"
    ! /a/, /u/
    | [[Batters of its stable = 2011 |deadurl=http://www.academisticlear=2008 | lastrong to [[United to reach 2007 |publisher=[[The conting to be third Union to and many of Portugustributican including internationauthoughts of experiously be recondon |first=May 2008 |publication=New Yorks of which is thalf of Mexico]] any othe Americatistry one of Angolition, an [[Alabama]], theast1=Willess thorlinks to stance of francessdate book |languages==
    {{Main as an on = https://web/2007}}</mapsed than animall as to a represident in [[ast = J. C.
     | title of litics ofter = {{IPA|-/n/
    | /a/
    |- states, anothe larger antic langolanguagest thrugges ==
    [[File:Apologically thich thundroid not because into thalled thropollo 11 |access-dategory.nasa.gov/apologicalled to de thumb|right of of ance on for [[Cate=2009 |work=[[Natic of Europosition]] in star = 1969, 2010 |isbn=978-0-8173-10-06-246X.2006 |deadurl= http%3A%2F%2Fwww.cented by their convertain a style=The field between in 2005 | puble to company other to cons, the
    F-testimes that, and his in are of Texas]]
    [[Category:People, thrists increaction.<ref> The stary 2016
     | year=1994 | url = This considents andrew and relation, as [[South cent of alkalign:century, Algeriodical Socience theften an used Station|firected Statin Altaic Languistic October instructure in contially conces in prographilosophyte]] of relign:centural as before thanking of 1862, an being a many ared asteroids of Norther=NASA |archive.org/web |url= https://web.arch is an thief name="text-ali metals official antic and Amendence trans as web|url Hanks an Alan command [[Charaction]], whical propenhauer's should (1999 | [[Johnstellativil War of discovernmentallion is a significated by thigh its thalles ancium and distronautsidern Africans]] in 1968, 2015}}</mained this of Indian large of Lincoln's some = 978-0-19-5090520,00000|| 1850s, who had be alkali metalso thall of Berber [[First1 = Alasked tral cound inder to processdate=June of Georganization)}}
    {{sfnRef|SIMBAD
      | titution/ |timential [[cognized tern Europeditor-last2=Francienced on thium]], a sevelopment to represear = 10.1016}}</magaining anion]], [[Ada, annually as in his proposal'' is threek alphabetween alphabet|Arabic]], ''[[Thomass of varian astructures thalogy]] | accession is not and exployed and, are news.com/books.google.comple in man]] are same of Repubs/botassium annot inding and with anglish an "[[Theoreans influences or a press an Eurasian article|Ango]], in orderation
      | ref = 10.1002/astrate journal=[[NASA |acceptember resulturesult of natury anning of conception ast1 = harvnb|Mary in was at isbn = 978-1-5 || [[Georgining in 2009 |publimited and forman expering systernald handings oned a discovery to have beliginaticate=1995 | issue=2 | parth a late = 1997|pp=337–338}}</may/hist on was remainston, thalidesignituded formation's ''The productiveurl=no |df=mdy }}<ref>
    
    ====
    {{Main 1862, 2014}}</makes to make thalliamong [[Arable als a stars oftention ter thages archael Countral scient anytime thalf-likely to dever, a... and 4220 more bytes
```


Then, we use it to encode strings:
Code: 
```java
      byte[] bits = CompressionUtil.encodeLZ(wikiArticle.getText(), dictionary);
      System.out.print("Compressed Bytes: " + bits.length);
      return Base64.getEncoder().encodeToString(bits);
```
Returns: 
```
    eLus/l2slVrbUhtHEH3nK6b8IpFSqGDKTnCVHwSKY4qAXEhAVd5G0iCNtbuj7GrlkhGflK/Ij+Wc7tkLBhLnwZS1u3Pv6T6nT9/f3/qlqGz5dtef2Z3Fv/6gD0Pd68BZziz+CncVXIuFvNu7vx8PT42AdH72/v2nBEZD/YpQRkeoAA06ABGwC3q53Ejq8DdsDTOIvNVZ/VFXHjPrQ3zgdJ9EJIcf3u5X02cHAwdSFgXosyIstScasrxR2bhnLr3w5qbdsCgzzX5W3/PJt5/LGgrfWoGDR7uG7dtoFCFaETyj5kLNBqiGxBZdEu+Y/leXT6z/TMesCrVrpnFucysZyG9axud1i54ZlwBEW50RdVJccSuJDeqZfzhL7tfe6CuK9qaLIAFr8PNSU8oqT7JoIBdRX/rTXHGrMW5VHtDAPppX8xbAflK9XCsdgQcBtp/6lXCnehanfm5BQj7b1plbHRuAS1gx/Y3qFQTUuH1VWgkG4HBpC/hnbsmJS8AXZ64aXqYuCkql0D5aQUuPpVKUMZzmoM1C4OgwhYo0E418/WxmF6FtYOdwUbb43m7G2A0mFnrmAnGAi2/3dQGK74oQDdZMn2858FOcdD0jXeLALqVdy4IXPrxwPsNlYplHfzoM3uVurtn7/7eeTy5fkupX01Lx7um0xOZvvKSLv3f30Yop4azw/zkxHvpQaI1Ep+rI37w+onFVlShRZOPXH3yi8hEistt4EvnWLRnfRCuuX5qpeOskVhHJvSML1FSjKsRRtwMH367ysmj8Ftx3ur+LrjL+1AEOj98eGv6mzDVAMMIIFxhIAKS0Pjg4MH1za7eqT905Bf1WSxhOEAi2zF3hnmkkRD9jm208btUCaLaZxCzMQWqBROLQ/C0VLxEtIqb4TCUgxDIAqJAySnI7Z50O9+zES11Pa5+6LDFZt5amv3WAuctKQfOBCuas2g+XhYLph5mzBO+Fi6fjsq9bwdqbkGy0XGtV5vySktoEsyvS1sB5WDuvnqvdWBMu1AzJ/tIoIk8k4cvc2dQlibi305Cu1GBU/Xq0KpF5U0IZUxUwyEjPvTAKDAMBdoETaLqZVkPgNMqC8Qnj9r1Qz41LWiPioe6ZZmGJHX0uSRrtHHsx9rnExH4ygZnUIcuctapgEnbEHKC61ZGF382e/6JXvxV322uM90K4gY0v4qnh3iR//5U5yyKKPrOLf5aVq4fhMPPCC6aT1iZxAQOwsHmpAr/pT9eV3f/8i7kemQl8FCUZzQa3aHVSVdFp+pc33eWqXm+8KmKELwRxIZGSksZlJ4mbq1WZu1JWZZxkFuUe62vZ0gDIlJFmfLQscPhdyoTqjj5SdNYBTJduVUygdrIvfBd9bOvb+ERQkOB3LEIU+pbF8Xe8NncupaTmlOjIl1pzaApi0NT1WjVQ1iRei/dWUqWhmpufxsOeqlgfI/pQtqIsBIf9EGsRpWgRgV5TP3APaSFjztRxtpwYvEycYpQOZIhz1gPSLEitpk2IOBGoye+fRGhy3tFIoOTh8U9vHh4kHIEqBLMBfoYLZBJjXdfe6bnI0jS83GoFnbmyPvlimzG7jEktbyQ/dcr0kdjNpQMxBGUBnacCn1HOBlKaIjTQAgia4hCwCNCEGlPVYxCu5LOQv4NX/gAPBnRyusgp7ySy2gw2LF74JsEmpbjtl3Bey7CRHuDi56mrfQcbgMQvtZJH4W33bDQ0b4+Of3wtK+b/jmC1uHevALxf7fMkR1LeWYTKhAb9ZyBdPWW5gHVw/7UANSYd9WSIiTfodSU5yKimiOZJ51pHsMXLvS/sv4zciR6i0/kSwCl5JjF8DnGpMf6Vik6srgKhhRFW++xe6vV6NEIUXLNCTU6cqeD9HZ92Os1zHr68iZUMa8Qa5q6x0DKvi6xizeGl3YAplnlVVBlho6bSpVhlD3xm8Gg6u7kLDw//AICnY4c=
```
Logging: 
```
    Compressed Bytes: 1460
```


And decompress to verify:
Code: 
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
