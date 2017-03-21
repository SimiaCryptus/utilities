First, we load positive and negative sentiment tweets into two seperate models

Code from [TrieDemo.java:140](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L140) executed in 1.75 seconds: 
```java
      ArrayList<TweetSentiment> list = new ArrayList<>(TweetSentiment.load()
              .filter(x -> x.category == 1).limit(testingSize + trainingSize).collect(Collectors.toList()));
      Collections.shuffle(list);
      return list;
```
Returns: 
```
    [TweetSentiment{title='"@Amy_E_W yeah'}, TweetSentiment{title='"@Amie__88 Time to rise and shine girl'}, TweetSentiment{title='@ajcoley10 seriouslyyy?! im gonna have to whip out the element'}, TweetSentiment{title='@cazp09  yeah i do   been up there once or twice b4   xX'}, TweetSentiment{title='@Barry_Chandler will see you in 24 hours my friend!'}, TweetSentiment{title='@bshermcincy you're in luck... general obama motors will be pumping out tiny little windup deathtraps in now time'}, TweetSentiment{title='@bimbler I am sure you will get lots of hugs from your nearest and dearest to make them better'}, TweetSentiment{title='@AceMas21 Amen! Talk to you later!'}, TweetSentiment{title='@BrookeJasmyn wow you really are up early!'}, TweetSentiment{title='"@blasha I am fine'}, TweetSentiment{title='@BarelyBlind we made it home safely. I definately enjoy my giant  hope you guys have a safe trip home'}, TweetSentiment{title='@Chanellie23 Yep first one ever. It was only half a shot though bc I really don't drink. But I wanted to see what all the hype was about.'}, TweetSentiment{title='@begiled LOL I'm a real zoo keeper with real animals.'}, TweetSentiment{title='@ABonin Very cool!  Nicely done!'}, TweetSentiment{title='@blawre4 awww brad! I'll give you a hug!'}, TweetSentiment{title='@asphaltcowgrrl yeah. nuts is good'}, TweetSentiment{title='@amandadiva aahh that painting is dope... inspiration.. thanks'}, TweetSentiment{title='"@Amy85pq You following mee'}, TweetSentiment{title='"@Bootcoot  I switched to Mac about 3 years ago'}, TweetSentiment{title='@__mares__  make the most out of it'}, TweetSentiment{title='"@AshleyLTMSYF  Hey'}, TweetSentiment{title='"@CAMERABOI Oh I was  Did I pay for any drinks last night?  Oh'}, TweetSentiment{title='@camilly4lyfe hey doll...tried to catch you after prayer but somebody stole you first...  have a fab day!'}, TweetSentiment{title='"#musicmonday I &lt;3 my new song... I AM ANTHM!!  I'm gonna nap for lunch enjoy your day!!  good day sir'}, TweetSentiment{title='&quot;If someone does not speak ill of you call!  is a sign that the growing trouble those who are unable to grow with you!'}, TweetSentiment{title='"@BlinkAnjell01 It won't be truly started until August! Mine is fourth on the list to be done still! But when I get it'}, TweetSentiment{title='@ayapapayajb yes yes yes really good! one of my all time favs'}, TweetSentiment{title='"@AlyssaMD never too late to try again. my mom bought me a guitar'}, TweetSentiment{title='"@axellemayode You're welcome! I've been good'}, TweetSentiment{title='"$ES_F interestign setup'}, TweetSentiment{title='"@_JoAniMaL If you're interested in watching Wimbledon the next couple of weeks'}, TweetSentiment{title='@blasha Blow some fine chilly powder on her face!  What an idea! I knw I knw my brain is gr8 ;p hehehe'}, TweetSentiment{title='@AnnetteStatus thats so awesome'}, TweetSentiment{title='@ashdonaldson Regret I was unable to make it... this time'}, TweetSentiment{title='"@anferneet it was real hot'}, TweetSentiment{title='"@beefarino Depends what you are into'}, TweetSentiment{title='&quot;I must get my ugliness from you&quot;  @my mum (via @KaySloan) not very nice :p'}, TweetSentiment{title='@ArchanglGabriel MC5   My dad worked on their first album....he's in the liner notes.  I grew up listening to that.'}, TweetSentiment{title='@alithealien that is such a cute picture'}, TweetSentiment{title='.@enricchi Hope that helped answer your question! Thanks for asking.'}, TweetSentiment{title='@Anthony_Go Oh yeah and I am reading Marley and Me but I had to stop cuz it was so sad'}, TweetSentiment{title='@Applemuncher  What is a bit of distance between real flatpacklovers....?'}, TweetSentiment{title='.:. I &lt;3 What the Duck comics!  http://www.whattheduck.net/'}, TweetSentiment{title='@Airchecker Sorry! Self recommendations are not supported.  Get recommended here: http://cli.gs/VYTGGW'}, TweetSentiment{title='@Amanda_vdGulik They're going to need to know how to SPELL money first   (www.LaughNYC.com)'}, TweetSentiment{title='@cimota indeed but for losing S... and 4406222 more bytes
```

Code from [TrieDemo.java:146](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L146) executed in 0.38 seconds: 
```java
      ArrayList<TweetSentiment> list = new ArrayList<>(TweetSentiment.load()
              .filter(x -> x.category == 0).limit(testingSize + trainingSize).collect(Collectors.toList()));
      Collections.shuffle(list);
      return list;
```
Returns: 
```
    [TweetSentiment{title='@aubrisaywhattt You're 2 for 2 baby girl'}, TweetSentiment{title='@arodslove It's too hot. Makeup meltdown'}, TweetSentiment{title=': twitter was down just now.  can't wait for service later man..'}, TweetSentiment{title='@andyjaques I feel sorry for you  what an awful song'}, TweetSentiment{title='"@alexandermillar - thought A2 was due on Thursday'}, TweetSentiment{title='" Having a terrible day'}, TweetSentiment{title='@cforclare same same my lao kok kok handphone just went nuts on me.  it keeps pressing numbers on its own. phantom phone omg'}, TweetSentiment{title='"@AlondraVP yeah we haven't talked in ages  how are you? And your cousin'}, TweetSentiment{title='"-- ahhh! No'}, TweetSentiment{title='@brianangelday26 WOW... SORRY  GOOD LUCKKKKK'}, TweetSentiment{title='@acWho oops .. roland garros score updates are slower than their radio'}, TweetSentiment{title='@anfjace'}, TweetSentiment{title='@c0rpsebunny haha i'm sowwie  *sends you virtual crumpetness*'}, TweetSentiment{title='@BuddhaLiscious Nope not until around 3am EST tonight! Boo'}, TweetSentiment{title='@daddyissue Thank you! There's a lot of bussyness and no shing sun overhere!  LOL!'}, TweetSentiment{title='@beautybuzzed It's supposed to rain tomorrow too.'}, TweetSentiment{title='@calvinharris yeah they did.. but it went back to the future'}, TweetSentiment{title='@Caraandclo lmaoo!  poor you  mine isnt that bad'}, TweetSentiment{title='@Aaron_2G haha I have connections. haha I would love to run the Whataburger twitter account but someone has it.'}, TweetSentiment{title='"@autumnconfusion boo.  that's weird'}, TweetSentiment{title='&amp; Danny's ending performance was just so beautiful. Btw I stayed up yesterday &amp; I forgot to bring my thumbdrive. I'm the 'smartest' ever.'}, TweetSentiment{title='@breezyballababe Bahaha...i mean Aww'}, TweetSentiment{title='" devistated'}, TweetSentiment{title='@3plus_talents ugh that sux  whyd it get pushed back?'}, TweetSentiment{title='"..it's official...I guess I've become a Monkey Junky...I wanna go see @100MonkeysMusic again in Lancaster'}, TweetSentiment{title='woke up early to wash and dry my tie dye shirt...the stupid thing is still wet. =[ FML.'}, TweetSentiment{title='@ArchnaSawjani aww but how will you make a good indian housewife'}, TweetSentiment{title='@CompHelperKid I've noticed that myself that's the 3rd time in the space of 2 days that it has been down'}, TweetSentiment{title='"@alexakim Indeed'}, TweetSentiment{title='@cookiethief3 i know...'}, TweetSentiment{title='@champagnerdub  too much to get into....but i'm sure you can figure out....'}, TweetSentiment{title='@birdsflysouth  I once flashed Orange Ave my underwear in the middle of the day when my pants fell and I was carrying too much stuff.'}, TweetSentiment{title='"@__Deb I know'}, TweetSentiment{title='@breannnna meeee too! I'm supposed to be up in 3 hours'}, TweetSentiment{title='@ben_gordon LOL I'll take ur word for it! I've just realized I sunburnt my back today n now I know it's stinging'}, TweetSentiment{title='&quot;Jetzt ist die Zeit zu explodieren&quot; good lyrics! Ollie wants to watch the Edukators but cant find it'}, TweetSentiment{title='#Air France confirms Captain &amp; one cabin crew of #AF447 have been identified. Hope they may have a swift journey home to their families'}, TweetSentiment{title='@_dariel_ That sucks'}, TweetSentiment{title='@alltimebritt hahahahahah your just reading everything but you don't twit me'}, TweetSentiment{title='"@AliCM oops'}, TweetSentiment{title='"@adbert so darling'}, TweetSentiment{title='@Becca1Checca yep...and what's worse... @Jassy1015JamZ left me'}, TweetSentiment{title='@buggzero come on... I'll play nice.. Let me in...'}, TweetSentiment{title='" i am sick of being poorly now'}, TweetSentiment{title='*no cardio 2day  just lifted..got the popeye arms right now..who wants to arm wrestle?! jk'}, TweetSentiment{title='@chitownnessa i want a new puppy'}, TweetSentiment{title='@danasargent Link doesn't work  didn't on Drudge either'}, TweetSentiment{title='@covertjew *cry ... and 4575428 more bytes
```

Code from [TrieDemo.java:152](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L152) executed in 22.89 seconds: 
```java
      CharTrie charTrie = tweetsPositive.stream().skip(testingSize).limit(trainingSize)
              .collect(Collectors.groupingByConcurrent(x -> new Random().nextInt(groups), Collectors.toList())).entrySet().stream()
              .parallel().map(e -> {
                  CharTrieIndex charTrieIndex = new CharTrieIndex();
                  e.getValue().forEach(article -> charTrieIndex.addDocument(article.getText()));
                  charTrieIndex.index(maxLevels, minWeight);
                  return charTrieIndex.truncate();
              }).reduce(CharTrie::add).get();
      print(charTrie);
      return charTrie;
```
Returns: 
```
    com.simiacryptus.util.text.CharTrie@93c7ed11
```
Logging: 
```
    Total Indexed Document (KB): 3077
    Total Node Count: 2971157
    Total Index Memory Size (KB): 98304
    
```

Code from [TrieDemo.java:164](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L164) executed in 23.73 seconds: 
```java
      CharTrie charTrie = tweetsNegative.stream().skip(testingSize).limit(trainingSize)
              .collect(Collectors.groupingByConcurrent(x -> new Random().nextInt(groups), Collectors.toList())).entrySet().stream()
              .parallel().map(e -> {
                  CharTrieIndex charTrieIndex = new CharTrieIndex();
                  e.getValue().forEach(article -> charTrieIndex.addDocument(article.getText()));
                  charTrieIndex.index(maxLevels, minWeight);
                  return charTrieIndex.truncate();
              }).reduce(CharTrie::add).get();
      print(charTrie);
      return charTrie;
```
Returns: 
```
    com.simiacryptus.util.text.CharTrie@aa75ab72
```
Logging: 
```
    Total Indexed Document (KB): 3242
    Total Node Count: 2901854
    Total Index Memory Size (KB): 98304
    
```

These source models produce similar representative texts:

Code from [TrieDemo.java:177](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L177) executed in 0.16 seconds: 
```java
      return trieNegative.getGenerator().generateDictionary(dictionarySampleSize, context, "", lookahead, true);
```
Returns: 
```
    e to go to the same think that's wrong with the same this weekend the morning to be there is a bit of the new one of the should be able to get to go to the computer is going to me and then it was the best friends are so much to do that sucks that suckst night now that is so sad that is the only have to work today is an exams to beds fun!ing to see your familything to take a picture of those days off the world I would have to go back to sleeping the week and they are you have anymore is not good to be at the last night some reason to that therey don't haven't believe the was a kid.d the rest of my friend is all the time too muchanan Oh no! That's all of themhecoyle to say that they don't have a good with you can get to stay in the sunshine and I have no idea what happened to go out of ito be there are you are still have no idea what to see you too bad the shows thats what's there was at this weekenderson I am so sorry to hear that was a sad dayn and i have some of this morn and I'm sorry to hear that was goin to meous hereone else is a bit off to watching that make it to bed now that means I'm so jealous of your painful today...really want to give me and that I was soon... that I was the started and this is the world i was too bad it's not the store and it's not as wellhard55 i love you don't I still not be able to watch itr  Love your phone has to be so I can't want to thing is that you are the first time but it wasn't answer me up with they dont want a really sad  I can do it wasaturday and now I had a bad I cant somethings are something I wanted to sending to goto the best friends are going. I don't.  but I dont having a lot of that sounds like the house is too late to spend to get the day of stuff to working for the time to be here too later todayitssel yeah i know what I cannot being about the stores are not a golden when I wanna go the come to talk to my completely dayork  recently the party tomorrowll - shame out on the back to meeting this morningisLove couldn't get a changed the link they were so sadI too but i dont know what about it is this years ago and you guys are all that one to this sucks.me/42wKstew. Hope you guys are all the car is it was...g I know if you were they didn't get they have been the problem with my god things are going to be in the other day too many time I wasnt thinking and thought it to school tomorow &amp; I hate the summer schoolease help me find a golf todayende I don't know...are you?ved to go...ndaya but it's a bad day. I watching the shit i am sorrry  i dont have my dad is to this is that are ya and my brother dayall are a big fan office for the way to getting to have that happened to buy they can you got a car too far away from the end of time for meoofshegeekchick I hadn't go to self: don't wanna go to wait to that can't seen thereted iteeth are yay life is still see ya there...especially wish I could go through to georgelection tonight be a lot of a couple of those daysl I fucking about the summernet is sad now it waiting forward to tell me about your concert in pain in this times I do they wouldn't got a chance to stop that you and your show to do it... did you get that to do that one of my lifenough the books like them to but its not fair that. I has been the more thanks for than that but it's a bitch the poolasha can't give it all this one that didn't had to send me toooth pulled at that place to germany people are sometimeshow tonite... I happy birthday your tweet to go!h you get it tooberto Rico  lolm down the weekes ah gotcha.  Hopefully thing.nther the fact that's what he way today!your friend...seriously thought it was think it out of town the hello I hard to them and it was! good homei I check it on my phone is there's no funnyellow me to self: dont thinking to do.right and I'm sorryowens would've beer and have funloaded in this summertime I got to do anythinglingutted to show upaying to come back in that come on there in a while you feeling me anythaangsofcute like a bitches and to me at work today.  I want the internet access to try and it'll be in that i cant get anym
```

Code from [TrieDemo.java:180](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L180) executed in 0.10 seconds: 
```java
      return triePositive.getGenerator().generateDictionary(dictionarySampleSize, context, "", lookahead, true);
```
Returns: 
```
    e you are you have a good morning to be a good to see you too much for the followfriday @marginatasnaily @marginatasnaily @marketing your tweet dreams that you can be able to get to self: A sleep wellt for the best this weekend is a goodnights and the movie everyone you are on the computer. I love your new picture of your facebookthrough the new album come to sending to the same think that's all good lucky you don't know what your story to you for you things are awesome! I was a gorgeousins I don't. and it was the rest of the train or pay vipants... I want to be a great time to take a lot of funhesketh @MariaPdT Hello there is awesomness!  I wanna see the same this is always goodham  dudeous today and I have to the world www.tweeteradder.com Once you got a long as you like therey Could be an awesome of the should have some more that was just saying to see you guys are on the train or pay vipd RUUUDE!  I'm going to bed now that was a greenberg that is amazingers crossed the link they are the followfriday @marketing the first time in the shown on twitterrell here in the sun is the week so I can do it to say thanks for a while I watch the last night. I had a great day was an awardso muchon  thanks for than the was good morningr Good luck with the started to heard that song is a smallery LOL I dont know what your day using www.iamsoannoying that is a gongrats on thats what it wasn't seen the only one of thematicallyitweeteradder.com Once you too hard to starting for mellow them to bed.. Nice to sleeping to your welcome too! maybe it too bad as your commendations on your story to 140 ch. Tell all the sun is so musharp &amp; the store thank you know if you guys are going on this weekend  how are something this is that there are the song and you should be a gr8 to hear that movies and i thing to go to twitta @gojilasaurus babyndra AS I saw your tweets are a goals and it's awetom spread then I wasnt a love the best friends on that songs on this morn  ... I just the story but it is all the time for follidays off to buy a bit of your friends. Even if you're welcome timemegan and hereyouever have funa be at the day to ya tomorrowsterman Hey therever been the movieorge Michael johnson_  i love you get the back to your pictures of those whole things are going to make itening them all dayeah i knowg  watching is good to self: never had a greeting to do that ya got the time to be them all good look forward to ya!!!    I looking forwrd to be so much better than the picturses  hahahaha okay thatally agree with the makes me something the sounds like a girl who has a grown upted to show to my fried to give your blog posted on thanx for my best of this years ago....let me know the party and now I lost the concertl I'm going? your mom and haven't you wanted they have you have a grandma would love it was@and the shirts the books like you shouldn't have to send me and that I wake up to that that I'm so excited for itisdale http://twitpic.com/6ercx - congratulationoon today I'm sure you?eer the pict with you so much for someone who is the beautiful city I always are to get a changed my twitted thank you for this times I am so happy birthday to helped me that are you're welch do you to be happy birthday to gootness! I hate they don't!lol Florida and welch right now you were and some really good thinking about they can see it to that one!  thanks lovely dayu goin to this mornin' Andyasimus Hi Brianline for todayellow meI Hair Cut. Eye Bling there.ayour hair"@andyclemmensen http:www.plurk.com/p/11866trip to tell me when you add everything in this time the poolhouse to meet you and a looks good night and this one of that one day off the house with your pict. Sorry for sure to go to them to be in a few more people are all day with my gosh! Do we can get to spending down the interesting you need to germany time is a group of time you could you on to be on my blog about the lovely to be fun at they are all that wouldnt believe itbeauquest hope you don't knowet and seeing your welcoming thanks!ting and she's a genius write songh I do thanks. good day f
```

The tree can also be reversed:

Code from [TrieDemo.java:184](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L184) executed in 0.00 seconds: 
```java
      return triePositive.reverse().getGenerator().generateDictionary(dictionarySampleSize, context, "", lookahead, true);
```
Returns: 
```
    
```

And can be combined with a variety of operations:

Code from [TrieDemo.java:188](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L188) executed in 4.00 seconds: 
```java
      return triePositive.product(trieNegative);
```
Returns: 
```
    com.simiacryptus.util.text.CharTrieIndex@142c38a
```

