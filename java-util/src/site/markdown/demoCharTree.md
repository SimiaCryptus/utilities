This will demonstrate how to use the CharTree class for PPM compression

First, we load some data into an index:
Code: 
```java
    CharTree charTree = new CharTree();
    WikiArticle.load().limit(100).forEach(article -> {
      charTree.addDocument(article.text);
    });
    charTree.index(3,1);
    // Remove Cursor Data:
    charTree.truncate();
    // This must be called before using the tree as a PPM model:
    CharTree codecTree = charTree.addEscapeNodes();
    System.out.print("Tree Size: " + codecTree.getMemorySize());
    return codecTree.codec;
```
Returns: 
      com.simiacryptus.util.text.CharTreeCodec@52525845
Logging: 
      Tree Size: 2098176

Then, we compress data:
Code: 
```java
    return WikiArticle.load().skip(100)
            .filter(article->article.text.length() > 1024 && article.text.length() < 4096)
            .findFirst().get();
```
Returns: 
      WikiArticle{title='Ada'}
Code: 
```java
    System.out.print("Topic: " + wikiArticle.text);
    Bits bits = codec.encodePPM(wikiArticle.text, 2);
    return bits.toBase64String();
```
Returns: 
      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
Logging: 
      Topic: {{Wiktionary|Ada|ada|ADA}}
      
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

And decompress to verify:
Code: 
```java
    byte[] bytes = Base64.getDecoder().decode(compressed);
    return codec.decodePPM(bytes, 2);
```
Returns: 
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
