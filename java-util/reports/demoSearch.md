This will demonstrate how to use the CharTrieIndex class for searching indexed documents


First, we load some data into an index:

Code: 
```java
      return new CharTrieIndex();
```
Returns: 
```
    com.simiacryptus.util.text.CharTrieIndex@4e83d3dd
```
Code: 
```java
      return WikiArticle.load().limit(100).collect(Collectors.toMap(
              article -> trie.addDocument(article.getText()),
              article -> article.getTitle()
      ));
```
Returns: 
```
    {0=AccessibleComputing, 1=Anarchism, 2=AfghanistanHistory, 3=AfghanistanGeography, 4=AfghanistanPeople, 5=AfghanistanCommunications, 6=AfghanistanTransportations, 7=AfghanistanMilitary, 8=AfghanistanTransnationalIssues, 9=AssistiveTechnology, 10=AmoeboidTaxa, 11=Autism, 12=AlbaniaHistory, 13=AlbaniaPeople, 14=AsWeMayThink, 15=AlbaniaGovernment, 16=AlbaniaEconomy, 17=Albedo, 18=AfroAsiaticLanguages, 19=ArtificalLanguages, 20=AbacuS, 21=AbalonE, 22=AbbadideS, 23=AbbesS, 24=AbbevilleFrance, 25=AbbeY, 26=AbboT, 27=Abbreviations, 28=AtlasShrugged, 29=ArtificialLanguages, 30=AtlasShruggedCharacters, 31=AtlasShruggedCompanies, 32=AyersMusicPublishingCompany, 33=AfricanAmericanPeople, 34=AdolfHitler, 35=AbeceDarians, 36=AbeL, 37=AbensbergGermany, 38=AberdeenSouthDakota, 39=ArthurKoestler, 40=AynRand, 41=AlexanderTheGreat, 42=AnchorageAlaska, 43=ArgumentForms, 44=ArgumentsForTheExistenceOfGod, 45=AnarchY, 46=AsciiArt, 47=AcademyAwards, 48=AcademyAwards/BestPicture, 49=AustriaLanguage, 50=AcademicElitism, 51=AxiomOfChoice, 52=AmericanFootball, 53=AnnaKournikova, 54=AndorrA, 55=AustroAsiaticLanguages, 56=ActresseS, 57=A, 58=AnarchoCapitalism, 59=AnarchoCapitalists, 60=ActressesS, 61=AnAmericanInParis, 62=AutoMorphism, 63=ActionFilm, 64=Alabama, 65=AfricA, 66=Achilles, 67=AppliedStatistics, 68=Abraham Lincoln, 69=Aristotle, 70=An American in Paris, 71=Academy Award for Best Production Design, 72=Academy Awards, 73=Action Film, 74=Actrius, 75=Animalia (book), 76=International Atomic Time, 77=Altruism, 78=AutoRacing, 79=Ayn Rand, 80=Alain Connes, 81=Allan Dwan, 82=Algeria/People, 83=Algeria/Transnational Issues, 84=Algeria, 85=List of Atlas Shrugged characters, 86=Topics of note in Atlas Shrugged, 87=Anthropology, 88=Agricultural science, 89=Alchemy, 90=Air Transport, 91=Alien, 92=Astronomer, 93=Ameboid stage, 94=ASCII, 95=Ashmore And Cartier Islands, 96=Austin (disambiguation), 97=Animation, 98=Apollo, 99=Andre Agassi}
```
And then compute the index trie:

Code: 
```java
      System.out.println("Total Indexed Document (KB): " + trie.getIndexedSize() / 1024);
      trie.index(Integer.MAX_VALUE,1);
      System.out.println("Total Node Count: " + trie.getNodeCount());
      System.out.println("Total Index Memory Size (KB): " + trie.getMemorySize() / 1024);
```
Logging: 
```
    Total Indexed Document (KB): 1978
    Total Node Count: 7452007
    Total Index Memory Size (KB): 147456
    
```
Now we can search for a string:

Code: 
```java
      IndexNode match = trie.traverse("Computer");
      System.out.println("Found string matches for " + match.getString());
      return match.getCursors().map(cursor -> {
          return documents.get(cursor.getDocumentId());
      }).collect(Collectors.groupingBy(x -> x, Collectors.counting()));
```
Returns: 
```
    {AccessibleComputing=1, Agricultural science=1, Animation=12, ASCII=9}
```
Logging: 
```
    Found string matches for Computer
    
```
