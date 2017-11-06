First, we load positive and negative sentiment tweets into two seperate models

Code from [TrieDemo.java:232](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L232) executed in 33.30 seconds: 
```java
      ArrayList<TweetSentiment> list = new ArrayList<>(TweetSentiment.load()
              .filter(x -> x.category == 1).limit(testingSize + trainingSize).collect(Collectors.toList()));
      Collections.shuffle(list);
      return list;
```
Returns: 
```
    [TweetSentiment{title='@roisinmeeehan How was the party last night?'}, TweetSentiment{title='I got it.  I just deleted the is from the url'}, TweetSentiment{title='"aww watch Taylor Swift's new video for You Belong With me! It's soo cutee! Taylor Swift'}, TweetSentiment{title='@lettyma927 ill be streaming u tonight  â™¥â™¡ Å‹ member u can bbm me in the morning if u need to stay awake  my niece will wake me up anyway'}, TweetSentiment{title='@idangazit Now that's just mean'}, TweetSentiment{title='@Lyndzei I would love to join you!'}, TweetSentiment{title='"@salspizza thanks'}, TweetSentiment{title='Going to Tramici with her amazing boyfriend! I love you Ford!'}, TweetSentiment{title='"http://tinyurl.com/c4z6gn Wow.. Wow.. hope'}, TweetSentiment{title='@aj_wood saw them  I love all things Baby Girl!'}, TweetSentiment{title='@tommcfly you feeeling any better?'}, TweetSentiment{title='"@UPSgirl Do more mugshots'}, TweetSentiment{title='Happy Birthday Shelby.'}, TweetSentiment{title='@decadentplace you're very bible-y lately'}, TweetSentiment{title='@seNCationDrop congrats. great change is coming! hope all is wellll loveeeee!'}, TweetSentiment{title='@JessInChina Aye I would be at that  http://bit.ly/2mj0vj ... would love to see your pics!'}, TweetSentiment{title='@ameliavillani ok sweet  well ring me when youre on your way to oakleigh station and i'll meet you at flinders stps. ring me wen u on trn'}, TweetSentiment{title='"@katiee_93 That's a point'}, TweetSentiment{title='"@buffalo_escort but I am on the road in LA'}, TweetSentiment{title='"@notytony that is not an idea'}, TweetSentiment{title='@sufitricia One of my favorites too'}, TweetSentiment{title='@helenabal thanks....'}, TweetSentiment{title='@CHIOMA_ im glad ur back babezzz so much BS around here i missed ya'}, TweetSentiment{title='"and and  i was smart and i had pigtails. but after like 6 years im gettin' honor roll again O: this calls for celebration'}, TweetSentiment{title='"@GrabeelLucas who wouldn't want to follow you'}, TweetSentiment{title='@Spinelli666 thanks for the rt'}, TweetSentiment{title='@FayeFoucault oh ya..lol..gotcha..just waiting for my biggest one to come over..Nick'}, TweetSentiment{title='@JueL I get most of my barley from beer'}, TweetSentiment{title='@JennyJS Jen!! I went to Crazy John's today and instead of the BB I got myself a prada phone'}, TweetSentiment{title='@QueenoftheBeats um is there a game or rain or both tomorrow? I'm not going through that again lol'}, TweetSentiment{title='@rogersnotrogers They do say money is a liquid asset'}, TweetSentiment{title='"Headed home. Boring night'}, TweetSentiment{title='@FabianMH If ur doin it then I'm gonna do it too!  ur the BB king lol thx for the response bro. I look 4ward to ur updates.'}, TweetSentiment{title='@fartingpen seriously dude... reading stuff about the vastness of space makes me feel like there's a big joke that's being played on us'}, TweetSentiment{title='"Applying the aftersun after a lovely day sunbathing'}, TweetSentiment{title='"@kittyhasclaws some lessons are gifted to us from the future ... and in later times'}, TweetSentiment{title='@nhoustonreed Is rob gunna try and keep up with the rest of the twitters?!?!?!?'}, TweetSentiment{title='@ExperiencingGod Congrats!!'}, TweetSentiment{title='@SophieDavey93 why hello! sorry i didnt reply before..! but yeah.. hope history was ok for you?! iv never ritten so much in my life!!!!'}, TweetSentiment{title='@fictillius I (will) have one wall of metallic.'}, TweetSentiment{title='@delta_goodrem that took ages to find the _ button :S um. OH yes. technology &amp; coathangers  nice work'}, TweetSentiment{title='"@ginjagin Good morning bastos! Please just get some rest. Things should be better'}, TweetSentiment{title='at a bbq'}, TweetSentiment{title='@frankis5 Thanks. So do I.'}, TweetSentiment{title='@tomricci ur so funny!'}, TweetSentiment{title='@BrodyJenner I was there  glad you had funn'}, TweetSentiment{title='@Dannymcfly you  will be amazing as usual  x loveu xx'}, TweetSentiment{title='@HEYerinlee haha nvm yea i remeber it no... and 43666849 more bytes
```

Code from [TrieDemo.java:238](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L238) executed in 3.19 seconds: 
```java
      ArrayList<TweetSentiment> list = new ArrayList<>(TweetSentiment.load()
              .filter(x -> x.category == 0).limit(testingSize + trainingSize).collect(Collectors.toList()));
      Collections.shuffle(list);
      return list;
```
Returns: 
```
    [TweetSentiment{title='@jackstow done that already  ... to my own child'}, TweetSentiment{title='@filmcriticbeta 30th Oct on the Wii??? Long wait'}, TweetSentiment{title='I heard La Salle suspended their class because of the H1N1 virus. is that true?'}, TweetSentiment{title='I gotta get back INTO today. Geez. Really not on top of my game today. All the star wars shiat 4 today. Just reminds me of jedi FAIL'}, TweetSentiment{title='@miss_tattoo I'm so sorry to hear that.  dollie TWUGS from me to you.'}, TweetSentiment{title='Good morning everyone...Its an early day for me...Not sure what Im going to do since it started raining!'}, TweetSentiment{title='"@jessrupsch ewww'}, TweetSentiment{title='is already missing her team  http://plurk.com/p/z4iz8'}, TweetSentiment{title='@DelanceySeattle I would love to work for you as a server but my boy would have to find a brain related job in your area!'}, TweetSentiment{title='At home  i need to clean the bathroom and my room'}, TweetSentiment{title='@LauraLunchable I suggested doing something else but there is like nothing to do'}, TweetSentiment{title='ign's down'}, TweetSentiment{title='I miss her soooo much'}, TweetSentiment{title='Not in the mood to work today'}, TweetSentiment{title='@latinluvly awww I love my baby can't think about being without her I even hate leaving her in the morning to go to work'}, TweetSentiment{title='Can only text my thoughts right now. Can't see yours!'}, TweetSentiment{title='@markfishpool my guide book says that bourton has &quot;sold its soul to crass commercialism&quot;!'}, TweetSentiment{title='@jimthomlinson i agree far too healthy - was almost drooling watching hubby &amp; toddler eat their sunday roast dinner'}, TweetSentiment{title='Hello ALL  How we doin this fine day! its so cold today'}, TweetSentiment{title='@rawritsamy ahh  if i were there i would totally drink with you!'}, TweetSentiment{title='@JenYo mines was SUPER lazy. Although i did go for a walk but my knee locked and stuff and was pretty sore  Hows the holiday? xx'}, TweetSentiment{title='i haven't have had an weird kiss yet  ..all were damn romanticaly hot.. #twpp'}, TweetSentiment{title='3 minutes til my birthdays over!'}, TweetSentiment{title='"I hear about revolutions starting on this thing'}, TweetSentiment{title='@MCbutterflyfan too bad my family dont think the same... my fam hate gays'}, TweetSentiment{title='Dallas is going out again!!'}, TweetSentiment{title='"@darrenchu iï¿½d love too'}, TweetSentiment{title='@EskimoJoelted aww..too bad  it's a bit hazy over here. and..bad news: first H1N1 case in brunei has been confirmed. how crazy is that?'}, TweetSentiment{title='"It is 12 a.m. Do you know where my husband is? Well'}, TweetSentiment{title='"Bah'}, TweetSentiment{title='"@theJoshMeister &quot;undoubtedly the work of a late interpreter'}, TweetSentiment{title='my fkn internet is fkd nd i cant watch BP'}, TweetSentiment{title='Leaving Chicago'}, TweetSentiment{title='my fae-ddut dads here and i missed him cus i was sleeeeeeping!'}, TweetSentiment{title='My sister got a lunch date and I don't'}, TweetSentiment{title='@darylsws Just received my Leeds 10K bib and timing chip - but my hip injury means I won't be breaking any 10K PB this time around'}, TweetSentiment{title='I always get lost when I drive in the dark'}, TweetSentiment{title='"  told not to drink w/my Rx so just gave all the b.b. porter &amp; obs. stout that I brought home from OR to my neighbors.  v'}, TweetSentiment{title='My iPhone battery died this AM.'}, TweetSentiment{title='@dinosaurrrrs yes but he's not replying'}, TweetSentiment{title='Is revising like mad as I have loads of exams this week'}, TweetSentiment{title='@TickleMeJoey not in australia it doesnt come out here until the 19th'}, TweetSentiment{title='"ma brithday was amazing'}, TweetSentiment{title='"@zerock I've been fiddling with URL'}, TweetSentiment{title='@MissNovember_F4 no tattoos yet I'm a chicken!!! Haha my friend has a big oneon her ribs she said it hurt really bad too! I'm a wuss'}, TweetSentiment{title='I'm not feeling too good  ... and 44754276 more bytes
```

Code from [TrieDemo.java:244](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L244) executed in 292.66 seconds: 
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
    com.simiacryptus.text.CharTrieIndex@3cb6bc18
```
Logging: 
```
    Total Indexed Document (KB): 27891
    Total Node Count: 6633145
    Total Index Memory Size (KB): 196609
    
```

Code from [TrieDemo.java:256](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L256) executed in 303.21 seconds: 
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
    com.simiacryptus.text.CharTrieIndex@294182e4
```
Logging: 
```
    Total Indexed Document (KB): 29205
    Total Node Count: 6055269
    Total Index Memory Size (KB): 196609
    
```

Each model can be used out-of-the-box to perform classification:

Code from [TrieDemo.java:269](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L269) executed in 80.27 seconds: 
```java
      PPMCodec codecPositive = triePositive.getCodec();
      PPMCodec codecNegative = trieNegative.getCodec();
      double positiveAccuracy = 100.0 * tweetsPositive.stream().limit(testingSize).mapToDouble(tweet -> {
          Bits encodeNeg = codecNegative.encodePPM(tweet.getText(), Integer.MAX_VALUE);
          Bits encodePos = codecPositive.encodePPM(tweet.getText(), Integer.MAX_VALUE);
          int prediction = (encodeNeg.bitLength > encodePos.bitLength) ? 1 : 0;
          return prediction == tweet.category ? 1 : 0;
      }).average().getAsDouble();
      double negativeAccuracy = 100.0 * tweetsNegative.stream().limit(testingSize).mapToDouble(tweet -> {
          Bits encodeNeg = codecNegative.encodePPM(tweet.getText(), Integer.MAX_VALUE);
          Bits encodePos = codecPositive.encodePPM(tweet.getText(), Integer.MAX_VALUE);
          int prediction = (encodeNeg.bitLength > encodePos.bitLength) ? 1 : 0;
          return prediction == tweet.category ? 1 : 0;
      }).average().getAsDouble();
      return String.format("Accuracy = %.3f%%, %.3f%%", positiveAccuracy, negativeAccuracy);
```
Returns: 
```
    Accuracy = 54.500%, 56.700%
```

Or can be combined with a variety of operations:

Code from [TrieDemo.java:287](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L287) executed in 51.42 seconds: 
```java
      return triePositive.add(trieNegative);
```
Returns: 
```
    com.simiacryptus.text.CharTrieIndex@bc4bddfe
```

Code from [TrieDemo.java:290](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L290) executed in 40.67 seconds: 
```java
      return trieNegative.divide(trieSum, 100);
```
Returns: 
```
    com.simiacryptus.text.CharTrieIndex@1774e60a
```

Code from [TrieDemo.java:293](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L293) executed in 44.92 seconds: 
```java
      return triePositive.divide(trieSum, 100);
```
Returns: 
```
    com.simiacryptus.text.CharTrieIndex@ce176ee
```

These composite tries can also be used to perform classification:

Code from [TrieDemo.java:297](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L297) executed in 76.37 seconds: 
```java
      PPMCodec codecPositive = positiveVector.getCodec();
      PPMCodec codecNegative = negativeVector.getCodec();
      double positiveAccuracy = 100.0 * tweetsPositive.stream().limit(testingSize).mapToDouble(tweet -> {
          Bits encodeNeg = codecNegative.encodePPM(tweet.getText(), Integer.MAX_VALUE);
          Bits encodePos = codecPositive.encodePPM(tweet.getText(), Integer.MAX_VALUE);
          int prediction = (encodeNeg.bitLength > encodePos.bitLength) ? 1 : 0;
          return prediction == tweet.category ? 1 : 0;
      }).average().getAsDouble();
      double negativeAccuracy = 100.0 * tweetsNegative.stream().limit(testingSize).mapToDouble(tweet -> {
          Bits encodeNeg = codecNegative.encodePPM(tweet.getText(), 2);
          Bits encodePos = codecPositive.encodePPM(tweet.getText(), 2);
          int prediction = (encodeNeg.bitLength > encodePos.bitLength) ? 1 : 0;
          return prediction == tweet.category ? 1 : 0;
      }).average().getAsDouble();
      return String.format("Accuracy = %.3f%%, %.3f%%", positiveAccuracy, negativeAccuracy);
```
Returns: 
```
    Accuracy = 54.800%, 62.600%
```

