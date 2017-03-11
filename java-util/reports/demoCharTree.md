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
    com.simiacryptus.util.text.CharTrieIndex@6e8e4a0d
```
And then derive a PPM codec:

Code: 
```java
      return trie.getCodec();
```
Returns: 
```
    com.simiacryptus.util.text.PPMCodec@2b4a2ec7
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
    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
```
Logging: 
```
    Bit Length: 13884
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
    e [[Alabama have book | title="background:lightblue;"| 1986|pp=1}}</ref>{{cite web.archiversity of the [[Academy Awards of Alabama]], [[Mary 2011 |date=2010 ||style="backgroundern University of the [[Anarchive.org/web.archistory on of authough that the stated their own as and ther thist of [[Alain and [[Americal and they and and social = 2011 ||stylobable = 10.1016/j.1469-7610.1016-06-13}}</mathere thanges of socience of communive.org/web/2014}}</ref> The style="text-alightblue;"| [[Johnson thers = http://www.academistory of hism anary 2013}}
    |{{chset-cology]], animation in the controle of productions of [[Robertarian international | [[Hanslavery]]''
    |-
    | [[Georgesternal of many of Constitle=April 2013}}<ref>
    
    ======
    {{Main themission of Alable the prographysics]], which as the for the and in [[Listotle = 10.1111 | pages = 1 |deaduring theory:Amendent of its on anthropologican an artical and considenters of Motion]] <br /> [[John Galtruistitutism]], these of Science of a [[Montrol of Philosopher to throughts of life ofter of relation=New Yorks of whicharacted in as thought to the ear=2009|p=12 stylined to and processdate=2010 || sty in 1999 | pmid = [[Henry an in a conting to be compan=3|<span classi an ear = 2009 | year=1996), pp. 11, 2009|pp=10.1007/s10803-01-28 | publishern Algeries or three also an clast=May been anarch 2014}}</magainstructure of evelopment in 1999 Frences in Ames]] any othe most = 1996), p. 2012}}</mained to because thing of of antional | volume = 1861, 1862, 2010}}</mar/inder 2007 | pmc = 2013 |deadurl=http://www.thealter that those including in [[Alexample on to stand his such then to relations of Greeks to conce to [[Category]]
    | ''[[The Agass="nound [[Cons ared by the than [[Aristotle individually as a recondon |first=Rober an on for [[Willess of Liber 2012 | is an between third formated that, and of all thus]]'' (1996, 2008 | issue = 2 | presents into thanimates anotheir of his theanalysicativeurl=https://web/2009 |accession = This conomicalled Station.<ref name="Algerial represental constic an [[March 2015}}<!-- NOT CITED IN ARTICLE *{{citating the lated State=June of disorderson]]
    | [[Jack to have being anal = https://web |url=no |df=mdy }}<br /> [[The communist an Alan alchemy Awards|(]]</span=3|<span conception]], ''[[Thomas Little to distronominatural anthropology of The [[Category:American be researcho-syndically constead of formatic Gibbons]] instance, ancient to rectobe]], a seventury in statures thical scientiallywood as [[1999|p=1177/1362361320–33.</reporth thundre in hists]]
    [[Careek mythological the was as beligion, annot before times.company or a presidentinued this a many stance thrists annualisher=[[Theoriginal in conted with the news |url = J Autism influencessdate wered on thanking a states, angles at [[Jackets|Laybout thefree | pmid = 1996 Summerge Davidualign:center;"| [[Hermed anced as [[Apollo and, as an used a direction, alth cent of aution autisting and man]]'', ''The first in providench is to a polition is counded founty, anytime of Repubs/book |lastern increate=14 | part on issue = | volume = Authors = 3 | particle|Anard United at her in for and Slam Lincoln an "improvidence also thieveral production thing of Apollowing in are not in man, the, a sing [[Paul Socientalist thors in 2008 | doi = 17, 2015}}</mapsed through to collowing hight of Englished inter as in with an Open in ''[[Anarchools anated is novemention|USD|1.503110514031928 | journatin Alch.
    
    ====
    {{Mains in or example thy of ''Apost or Bests thyrams of 1861, Lincoln's Singlish an Inter own a structivedater to provernmential differench Open]] | vautoms thysical proposed [[Unity Press than ter;"| 10, 2016}}</mago Press-dation/23/elector)|Geory to make thanish in Stand even william A. |languages = {{citalisticlearly are areerinciple follectiving systed hist only in Paristian]] and within anger [[Academy of both authorliness, thance in 1864. p. 1998|pp=303–304}}
    * {{cite books.google.come introdevelopedian than alchemy other=Alasked trans]]
    |-
    |100 || s... and 4232 more bytes
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
    eLtbD1/wlVrbbhs3EH3PVxB5kVyoRhMjbRMgD7JUN4YTy/DKNtA3epeWGO1F3dXKUGJ/Ur+iP9ZzZrgXO3abPhiwlksOOZydOXNmvn698ispLgFGjhN7Z/E3no6x4ReAHXjCpIepm6Re2Ps7nGQ+m5iSJBpfe//+DAYuaTHJrfENmXO9S5hhYs1RYZfMuEuDXyPzOw5gye5BD3n70lAek38kAcSr08LFhhTwbg9+I7w5IqDdWC1Qm+OqWOlKvCgZ0ULFyJx6qYZ082ZVnSv53rzPJ49flzNUvncCtxyZizLzKpPU5ioPnQSyrjVbwLAQ75hhmPEXV15b/xkbPCMrCpvttnFiSysk8KOZ4Xk7Y2TmNUDXTnckuQpUIybDhPMPhyQrT/qKPmfPhBniQ7CIYQjBwoJo+ZgMeCk9FbKeMASuN3lkJmWBCfbBvrrRCCdqBkV9wmUjwsZ+LRlTu4uJX1gEoM+2d+dWZcOqU2mlKEOiyYxjE9xorpy3Q5CqEKSpkkOXIjwnrhEvWz9Vr6AVmgcn6HWbkJzPGQ5LAGGBLaGy1N9ooNKPE7ss+gZ2Asdrq+9dZg5t0FWODEEfD99f6xNT2qoIBmvip2dOfYybbnekR5zalczrWfDSF8/cz2yVWpa9vhWDMXhcrY79v/OcuXJFlrrZVgQA+dS2xOYvfbkAwPhe7WMW/XUOT/9fM3jps5r+ETlB3Brtm9cHNK4wW8oehU/49hFwk/buAPpuPUuova9kfhmsuB00sWTkadPnwu+O3LQmmAi5HVmIqLZbl3XV+S0FacFVhp8qABDmlWA4Bp5pYDMgSIKczN7f3zdjc2V3Wpu+cVoZslrWOCya+l9WKx+PdeY233p8VcvMJd0mkmLBxLcog2j+lpr+OrU7KHMC7Jtr7Qxg0m+gXOwhojoTwC3o7NBLLbSnpyHm5pve0fS3Cli4vBaMXbAFK2n04ch2IZwlzhLnVi7cjsu/7IQCDwQx611spnEmc8CLBYmqnmBGX6+eqz9ZCRVSckxFslBluxamljkCUaW4N8ZKNRjtlnlwKmkLyjICgqbEIJKeGsDigeU8s0gm026ZuBGB26grxifIHeNmNpJP9CTioeos4B7kMl4ShbA4dDH3pcTEcXoNM2lDllZ02j4qLMSsXt1qZP26qfk8fmPUjoq7HXXG+0mYRhsGwq3hu0n//it3MBtnxixn/Vk3rn7CvOpaEkrdtE4JB5gCiC5q7fwATts0dv/Lr+YiMsg+2IqQFQK+Fm35SZsSpG1J+DB+6a7ULG/rZTHJbqNQcOvpcsIazyKU0G9q5e8d7EK3EOuwqLTYAMExYftgCZs+SpNIu9AHoJcgwAzpVsUEWif7zHvBx/beDU8EBQkhj0NIM0bP4vg7fDY3LmMS5szCs9yVa1onWJ4EHKF613/D/FvbE0NKJkyuj8Nlt0UKMfmZqKKuBIf9EPrZpHcSgV6bCuAeskpkJuo4e04MXiZsMVCiIuKEZCzNgpxj3IWIw9InegvfRGjy11EkUPLV25/e3N9LOELKXZgt4TacWO3TTdt/p/eiNWUJL1faD2jOrU9vbSdzyJjU80byU7dMHwltrtwmBlS7RTrEElrOhjEgpRihgRZA0BREwCKQW7aYqpVBuFImRfkOXvkIHgzoZLJEKsGiI0Wx4Cte+DKFkjJ87adwXqtiKyvAxS8y1/oOTqjWxUqLyApvh8fRzPx88PbH13Ji/ncghQjzEsD75R5vMgrtaI0JTcdPQLp2y/IBtsH9typeEhctPfns1BusuhaiWcnXSkpxdK5tBFs+v/rS/ovkQfAQg8Et2Q3eSQifM3zUkH8u7k7K7EirYISNnt1zq15EUdO0IzfOstzeHZ8OBt1zKcJwJDTSbBBryKHioHWvdUwLcad2uzNd36ZpG3smHhAKllC+QD4zfbCdu4Ur7u//AYCnY4c=
```
Logging: 
```
    Compressed Bytes: 1457
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
