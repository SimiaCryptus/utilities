First, we load positive and negative sentiment tweets into two seperate models

Code from [TrieDemo.java:369](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L369) executed in 3.67 seconds: 
```java
      ArrayList<TweetSentiment> list = new ArrayList<>(TweetSentiment.load()
              .filter(x -> x.category == 1).limit(testingSize + trainingSize).collect(Collectors.toList()));
      Collections.shuffle(list);
      return list;
```
Returns: 
```
    [TweetSentiment{title='#myweakness guys who love r&amp;b and like singin it to me'}, TweetSentiment{title='"@bigjstl Err yeah'}, TweetSentiment{title='"@amberravenel I was just telling Body how we used to do our own taste testings @ random cupcakes/cake spots'}, TweetSentiment{title='@_supernatural_ http://twitpic.com/4u0r9 - if you need help for a shower... I'm here for u'}, TweetSentiment{title='@Celtha Gooood night my friend'}, TweetSentiment{title='"@adelate Yes'}, TweetSentiment{title='@alyxandracouch what was itt!?'}, TweetSentiment{title='@Alex_Segal Oh wondered where she had gone'}, TweetSentiment{title='@amandareddick @cjaydubb @nikkislaw1 Happy Mothers Day! Yes I'm counting godmothers too'}, TweetSentiment{title='"@cindyscott54 awesome'}, TweetSentiment{title='@boxdenfresh better...thanks for asking  wats up wit u?'}, TweetSentiment{title='@abe_neon I am'}, TweetSentiment{title='@abacab1975 good plan darling'}, TweetSentiment{title='@allisongreen ill take some  ha ha'}, TweetSentiment{title='@brittanydenue call me when you're done babeee! love you  my fellow non dmb goer lol'}, TweetSentiment{title='" meditating on emptyness - it's free'}, TweetSentiment{title='#followfriday - @KhloeKardashian - Everybody's favorite'}, TweetSentiment{title='@ayagil suddenly twitter has become a smaller place because of you'}, TweetSentiment{title='@abrowngirl Byyyyeeee tweet ya later-have a great day!'}, TweetSentiment{title='@AndyStanley was still great to have you introduce everything at the 12:45. Almost not NPCC without you. Almost.'}, TweetSentiment{title='@Alyssa_Milano  and that makes me a bad person. you are right use it as you like  im just glad you share'}, TweetSentiment{title='"@Antonio_Bay Stirling has The Wallace Monument'}, TweetSentiment{title='@anoukcorver gefeliciteeeeerd  xx'}, TweetSentiment{title='@BillyBlass thanks for the shoutout!'}, TweetSentiment{title='@AppleInvestor Fanboy investors are like crackhead crack dealers -- not a good idea.'}, TweetSentiment{title='i really wanna watch the david cook and archuleta concert!!) sooo HOTT!'}, TweetSentiment{title='@Belly9Maternity tooth fairy is coming to ur house tonight.'}, TweetSentiment{title='"@barefoot_exec @MariSmith Wow'}, TweetSentiment{title='@CaliSzFinest  how r u today?'}, TweetSentiment{title='@chrismic That's the song I mean'}, TweetSentiment{title='@AmberlinaM yea ive seen him 3 times and met him 3 times  LOVE HIM TO BITS!! xx'}, TweetSentiment{title='@CateP36 I'm a good boy'}, TweetSentiment{title='"@BethTana Sci-fi junkie'}, TweetSentiment{title='@Alyssa_Morse totally agree on much better. But turn right is my exception hahah. Forever and always &gt; much better'}, TweetSentiment{title='@ashnessa94 watching tv and chatting   and you?'}, TweetSentiment{title='@carlamedina cOnfessiOns Of a Disney Channel Star  hahaha!*'}, TweetSentiment{title='@30SECONDSTOMARS @FragileBubble I wanna ask the same'}, TweetSentiment{title='&quot;Come Back To Me&quot; just came on radio &lt;3 this is the first time I've heard it played on the radio'}, TweetSentiment{title='@brynnlovesyou and now youre voting for them for the dennys all-nighter adopt a band thing righttttt?'}, TweetSentiment{title='@bfhguides Thats cool'}, TweetSentiment{title='@Alyssa_Milano It's refreshing to see a girl so passionate about her sport!'}, TweetSentiment{title='@angiecas cool will have to meet up i am going with you'}, TweetSentiment{title='"@BernardHarris Excellent message! I would have loved to see a real astronaut when I was a kid! Fantastic what you are doing'}, TweetSentiment{title='@annings http://twitpic.com/4x451 - Wow! I want one too!  Haha!'}, TweetSentiment{title='@_handz_ Coolio. Sounds good'}, TweetSentiment{title='@aplusk I call it first breakfast'}, TweetSentiment{title='i'm happy! went out for dinner at a posh place and food was wonderful. I love it when food is Wonderful!!!'}, TweetSentiment{title='"....Oh yeah'}, TweetSentiment{title='@bobzinga What a wonderful experience those kids had!  Thanks for serving our country'}, TweetSentiment{title='goodnight.'}, TweetSentiment{title='#m20eu Win 2 FREE TICKETS: Send your definition of Mobile 2.0 in a tweet tagged #cotm to @mobile20 before this coming Tuesday 14h (CET)'}, TweetSentiment{title='"@CaptDS9E I like Cohen. His criticism of Yankee Stadium was unnecessary tho. Btw'}, TweetSentiment{title='#hugh laurie in the trending topic... obviously jesus is alive.'}, TweetSentiment{title='@amybeavers good luck...that is really exciting! have fun in NY and take care of my best friend for me'}, TweetSentiment{title='@calbo restraining order?'}, TweetSentiment{title='@1_pink_fan sorry i was looking for her'}, TweetSentiment{title='@CarriBugbee well ... the software QA thing didn't work out for me so I switched to social media ... it's like looking in a mirror'}, TweetSentiment{title='"(@Restrictor) Oh'}, TweetSentiment{title='"@Abeeliever Hi Sweetie! how r ya today? Hope all is going okay. Its a beautiful day today! U r a gr8 person! Love'}, TweetSentiment{title='"@chameabbey Couldn't find it'}, TweetSentiment{title='@bobbyllew your lisa rogers carpool is now my alltime fave!! Good work!!'}, TweetSentiment{title='"@BaleBabe66 I'll go watch it with you'}, TweetSentiment{title='"@Azh20diver Hey there'}, TweetSentiment{title='@ fridays with two lovely ladies bout to go in like some fat girls @ a buffet'}, TweetSentiment{title='@ w0rk..blasting @LadyGaga in my office'}, TweetSentiment{title='"@CageTheElephant YOU TOTALLY FUCKING KILLED IT TODAY AT WEENIE ROAST!!! matt'}, TweetSentiment{title='@Antgrad it wasn't scary for a second. felt so calm and peaceful. i highly recommend it'}, TweetSentiment{title='@auntiedis right back atcha!!'}, TweetSentiment{title='@Ambition310 yes we are'}, TweetSentiment{title='@bigmanzest i can be ur inspiration baby ;) lol onli jokein zest !'}, TweetSentiment{title='"@bookwoman60 Very'}, TweetSentiment{title='*looks at some black stilletoes* these are my last pair  *walks to the checkout counter with cash in hand*'}, TweetSentiment{title='"@Catrineke hihi'}, TweetSentiment{title='@arjessee your crazy! Lol'}, TweetSentiment{title='"@bodhibuggy Ok'}, TweetSentiment{title='"@almaviva I fell asleep earlier'}, TweetSentiment{title='@aamosely sorry babe!! i still love you  (youre my favourite cousin but shh) ive just been heaps busy lately.. I LOVE YOU  XX'}, TweetSentiment{title='@_CrC_ Aww... nothing but love for you too.'}, TweetSentiment{title='@brucelauzon thanks for the #FF recommend'}, TweetSentiment{title='. . Laundry. .cleaning. .espn &amp; step brothers'}, TweetSentiment{title='@andyclemmensen omg you coming to newcaslte come to my place!!  xoxo ily'}, TweetSentiment{title='@CirkusMike heya! I found you through @visionaerialist. I'm doing Vulcana's year-long artsworker training program'}, TweetSentiment{title=':O there are computer people at my door selling laptops and my dad hasn't yelled at them yet. this could be a good thing'}, TweetSentiment{title='"&quot;My money's not in my account'}, TweetSentiment{title='-- . *sigh* You cant love someone that doesn't love you back that's like stalker status . GET a divorce &amp;&amp; move on . Trinity.'}, TweetSentiment{title='#iremember when teenagers/kids puffed up their shoes by stuffing 5 pairs of socks in them.'}, TweetSentiment{title='@BRSDiddy. That's what I'm sayin' man. Now where back in town from Kerman and I have stop at WinCo for some ice cream and some pie...'}, TweetSentiment{title='@archukat yung after the game na  yung sa mini-concert'}, TweetSentiment{title='@alanstevens +1 to &quot;stop following sheeple&quot;'}, TweetSentiment{title='&quot;I am on a 24 hour champange diet&quot;  sike. I'm still drinking Kool-aid.....'}, TweetSentiment{title='"@a_bee sounds great'}, TweetSentiment{title='"@aeriagames yeah'}, TweetSentiment{title='@Ben_Jarelbo Good morning  hows you on this fine day ? XX'}, TweetSentiment{title='@annayvette giving you the check'}, TweetSentiment{title='"  going to bed. Exams'}, TweetSentiment{title='"@BengalBoys Proper cute ;) This pic'}, TweetSentiment{title='"@aphalloides ahhh...  the interesting things i miss out on!    So sweet of you to think of me'}, TweetSentiment{title='@BillyScallywag Reset it  or throw it out the window'}, TweetSentiment{title='"@anildas yeah'}, TweetSentiment{title='@bowwow614 ... Simple As It Sounds... You Might Just Need To Drink Some Water... Lol.  Hope You Feel Better Hun!'}, TweetSentiment{title='#dreamwidth invites are all gone for now! I'll offer up some more on here when I get them'}, TweetSentiment{title='@adamoxford you get points for forward planning...'}, TweetSentiment{title='"@0bscenity @igzebedze Thanks'}, TweetSentiment{title='@browniemusic you made me laugh. Too funny'}, TweetSentiment{title='&quot;Babi&quot; is not a crime!'}, TweetSentiment{title='"@Blackie71 Thanks  and that score is indeed a record'}, TweetSentiment{title='"@brunobelon Bom dia'}, TweetSentiment{title='@alour Haha just realized I forgot to click 'reply' to your comment so two comments showed on my fb wall lol  Been nice chatting with you'}, TweetSentiment{title='#anorak: You're not the first to ask.  http://tinyurl.com/oztrwa'}, TweetSentiment{title='"@ChrisHangsleben  yes'}, TweetSentiment{title='@AriesVenusStarr chillin n nt sleepin'}, TweetSentiment{title='"(via @BristolMatters) Happy Birthday Bristol Zoo gorilla http://tinyurl.com/ls5epf &lt;--- Not sure if this is mad'}, TweetSentiment{title='&quot;Hey good morning beautiful &quot;  ~'}, TweetSentiment{title='"@bobpullenmusic Haha yes'}, TweetSentiment{title='@anthony_bueno Me too!  Keep me updated on the conventions you'll be at state-side this year.'}, TweetSentiment{title='(Tom) Home brewing some beer today...yum'}, TweetSentiment{title='"@andydiggle just read all 78 pages of sin titulo'}, TweetSentiment{title='"@AndreaPetrou I think Simon has been without a cigarette for three weeks now'}, TweetSentiment{title='@ABeautifulMind1 You show as much &quot;skin&quot; as possible in an interview.  Wear the skirt.  It will put you &quot;in control' of the interview.'}, TweetSentiment{title='"@AshleyGofficial Haha!!! What book? Message me soon'}, TweetSentiment{title='"@cdncamel LOL'}, TweetSentiment{title='Be careful... white at a BBQ is a high risk. But the dress is really awesome! http://lookbook.nu/look/154056'}, TweetSentiment{title='@Aylwen the movie with Gwyneth Paltrow is much funnyer than the book  #emma #jane #austen'}, TweetSentiment{title='@BadAstronomer You're too late.Herschel and Planck are already up. Search for #hplaunch on twitter'}, TweetSentiment{title='@ChildrenParties Thanks for the Follow Friday plug! Appreciate it'}, TweetSentiment{title='@alittletrendy bodypiercer  duh'}, TweetSentiment{title='@BOMBkid haha not even. You kept going on and on about pretzels and all your circus antics. I was just going along with it'}, TweetSentiment{title='@brittanyjean_ gois? mix between girls and bois?'}, TweetSentiment{title='@AnnaBanana74 sounds like a new toy by Hasbro.'}, TweetSentiment{title='#SanctuarySunday  cant wait for the dvds to come out'}, TweetSentiment{title='@bob_edwards @Welshracer @kayels @jessfaith Thanks for the #followfriday'}, TweetSentiment{title='@aerodash84 it's EXACTLY what it sounds like'}, TweetSentiment{title='@bittenbefore How long ago did you send the package? Just so i can keep track'}, TweetSentiment{title='@alwaysshoutalex ohmyflippygosh alex. i am so gonna go to Australia to watch New Moon with you  haha'}, TweetSentiment{title='... Baby got Bumbo'}, TweetSentiment{title='@chrisquinn2110 got to love those got tubs!'}, TweetSentiment{title='@adorablyalice Thanks'}, TweetSentiment{title='. at the same time. I guess I was wrong.'}, TweetSentiment{title='@Anna2323 No but thank you for tonight! I had fun. We should def have more spontaneous bonfires (minus the lighter fluid) hehe'}, TweetSentiment{title='@carditz GIVE ME YOUR PAPER!'}, TweetSentiment{title='@ahbilly are you in need of another kiss attack??'}, TweetSentiment{title='"@causticjb yeah'}, TweetSentiment{title='@ABJColeman mornin' ... I just woke up'}, TweetSentiment{title='@AFMikey413 glad everything is fine'}, TweetSentiment{title='@billyraycyrus Come to MALTA!! You have lot of fans here!! Love your music!!!'}, TweetSentiment{title='@antzzle Keep that shit up and you'll run out of organs.'}, TweetSentiment{title='@christelmcr Thanks  Now my hair is blond and black'}, TweetSentiment{title='@_daveyb  Kate is worth it!'}, TweetSentiment{title='"@B0RR15 haha just saw that on went i went downstairs'}, TweetSentiment{title='&quot;love fun dip and cherry coke&quot;'}, TweetSentiment{title='@axon yes! yes! had the porky thingy. and tiramisu!  nice. you liked?'}, TweetSentiment{title='@amacisaac @C_DIG @CdnCowgirl @Wendy @andrewmcintyre @brown73 @dblacombe @jaymes @Roger for my #followfriday'}, TweetSentiment{title='"@BrooklynAdam Sorry'}, TweetSentiment{title='@AlluN33DisL0v3 omg i have  imaginary kids too  julien-rae george ryan ross IV and kendal and they are all emo boys'}, TweetSentiment{title='@3thbi Heheheh good stuff! Walla my day was extremely boring but thank GOD I'm home now.. Relaxing shway'}, TweetSentiment{title='@AnditisLiz awww thats cute'}, TweetSentiment{title='"@aiela the violin can be played AWESOMELY by a 14 year old'}, TweetSentiment{title='"@amykay1 aww thats awesome!  I live in a false reality - i like 2 think i can sing but i cant'}, TweetSentiment{title='@am13er at least you can make a difference in Rob's life by tracking down the stalkers and beating their faces in!'}, TweetSentiment{title='@Azlen cute new profile pic proud papa'}, TweetSentiment{title='"@BA_MF 's letter from muah is off to San Diego'}, TweetSentiment{title='"@anadeath http://twitpic.com/7i1ih - Escura? No'}, TweetSentiment{title='@ashleytisdale You are so lucky. Germany is amazing'}, TweetSentiment{title='@christineemusic okay! ill be loooking out for itt heheheheheh'}, TweetSentiment{title='finally finished. now i can sleep peacefully!'}, TweetSentiment{title='@benjacobsonline You are very intelligent.  I'm sure you'll be a Twitter addict in no time!!!'}, TweetSentiment{title='@candydulfer Candy heard some more of your CD on Satelite radio over the weekend and you are a cold player!! Love your music more &amp; more'}, TweetSentiment{title='@amandawheel if this means what I think it means then GOOD LUCK!!! Call me if you need help haha love you wheezy'}, TweetSentiment{title='#nextMEDIA cocktail reception party starting at 17:00 - see you all there'}, TweetSentiment{title='@APOCrunch isn't it awesome? i shot a rifle for the first time in february and definitely want to do it again'}, TweetSentiment{title='@AlexAllTimeLow I'll read Cinderella?  xoxo'}, TweetSentiment{title='@algebraoverdose Well i didnt know you were there.... Lol yeah me and tessa were looking at different gyms to see how much they cost.'}, TweetSentiment{title='@angiemartinez  Good-Morrning ! ANGI  Xo'sss Much Love Ma' muahhh!!'}, TweetSentiment{title='@alexiaaa hahas sucks to be you'}, TweetSentiment{title='".. my mum is buzzing after meeting her favourite singer christy moore'}, TweetSentiment{title='@aiiathehero hey  ahahaha pretty awesome  anak mana ?'}, TweetSentiment{title='@AlexBrook Lmfao! Ahhh woo for inside jokes!  I think I need to sort that film out!'}, TweetSentiment{title='"@amybottomly  well'}, TweetSentiment{title='@carthus well thats just silly.. that would burn a hole in the umbrella!'}, TweetSentiment{title='@amber_boyd whichever it doesn't matter'}, TweetSentiment{title='"@ahmednaguib Sure'}, TweetSentiment{title='@catanddogdoctor I've never heard you swear before :-O   kidding    I take it &quot;there&quot; is where I left in January...'}, TweetSentiment{title='"&quot;Me: Dude'}, TweetSentiment{title='@_ev wooh.. you got new ituos! Let me know the improvement in it'}, TweetSentiment{title='@candacecomer do you know if your boss has had luck finding any wine on my list? i need. i need.'}, TweetSentiment{title='"@cheapcheapcheap They will dive bomb you if you get too close to their nest'}, TweetSentiment{title='i love being a fake rebel.Mmm... Kinda hungry.anyone want to do something?'}, TweetSentiment{title='gotta love her. glad she had fun. looking forward to the beach w/ her!'}, TweetSentiment{title='@andyclemmensen heyyy you ALWAYS... and 4471272 more bytes
```

Code from [TrieDemo.java:375](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L375) executed in 0.74 seconds: 
```java
      ArrayList<TweetSentiment> list = new ArrayList<>(TweetSentiment.load()
              .filter(x -> x.category == 0).limit(testingSize + trainingSize).collect(Collectors.toList()));
      Collections.shuffle(list);
      return list;
```
Returns: 
```
    [TweetSentiment{title='@deadlyhouses Yes. She was telling me she doesn't love me anymore and I was heartbroken.'}, TweetSentiment{title='i hate bad days.'}, TweetSentiment{title='about 1/4 of my myspace comments are gone. cause someone hacked onto wills account. and kept abusing others so he had to delete it.'}, TweetSentiment{title='@cinemabizarre http://twitpic.com/3gbo1 - I want this album!!! in my country .. do not sell the disks!   :'('}, TweetSentiment{title='@bennylicious omg! primeval hasn't really been cancelled has it?!?! its one of my favourite shows'}, TweetSentiment{title='"@briant1701 Me too'}, TweetSentiment{title='@angelroxy I'm pretty alright thanks but being lazy  how's you?'}, TweetSentiment{title='@A3wish @ZapAmna my lape is not dead its in a state of coma / its on crack and i dont like crack heads BTW there is no more mcdonolds'}, TweetSentiment{title='@becklyn13 no fun at all'}, TweetSentiment{title='@DawnYang1 i'm so sorry to hear that. my grandad is also suffering from cancer. its sad to have a loved one suffer.'}, TweetSentiment{title='@babyjew me either'}, TweetSentiment{title='@bigdbc does chelsea handler have a twitter? she was just VERY rude about Miley.....uuugggghhh'}, TweetSentiment{title=': staying away from tea all day today'}, TweetSentiment{title='"@anastasiav the author of Pendragon'}, TweetSentiment{title='@archaelus Actually maybe that's Saturday'}, TweetSentiment{title='ECL all day to try to cram all of this organic chemistry back into my head. Final tomorrow. ugh'}, TweetSentiment{title='@chimpytwit brilliant idea. just bring as much as you think you'll spend &amp; I'll swap you. bring a brolly'}, TweetSentiment{title='@besz you haven't tweeted emmy or emily in a while. she's sad  cries. grabs rocky road ice cream. emmy misses her besz ...'}, TweetSentiment{title='@billohbill SS &amp; i r @ the devon horse show. gettin' mostly rained out. lots of events have ben cancelled incl the coaches'}, TweetSentiment{title='my bro isnt here yet!!!! what to do????'}, TweetSentiment{title='i just missed out on the hat pricing minigame....i hope i catch it next time!'}, TweetSentiment{title='@bobbyhighway how come you guys were supposed to be @ Bamboozle today and they switched you to tomorrow?'}, TweetSentiment{title='"@butadream yeah'}, TweetSentiment{title='@ChantzKacey Yeah..Im Sorry..Dosent Feel Good'}, TweetSentiment{title='" Really worried for my mom's safety. Somebody fix violence in the world'}, TweetSentiment{title='#E3 here come the stats'}, TweetSentiment{title='@ChristopherY REALLY graphic - more so than any other account I've ever seen on Twitter.'}, TweetSentiment{title='@Alska @Andrea__P If I wasn't struggling to get my meds refilled I'd definitely be buying from you both. Disability doesn't pay me much.'}, TweetSentiment{title='*sigh* one mother down...one more to go..and my dog has two tumors'}, TweetSentiment{title='"@AlanaBanana14 why did you make your own channel'}, TweetSentiment{title='"@cb1kenobi Nope'}, TweetSentiment{title='@Adrienne_Bailon im working  wot r u up to?!'}, TweetSentiment{title='#MilehighBrowncoats next year get a bigger goddamn venue- like Red Rocks- this is the 2nd year I've been sold out'}, TweetSentiment{title='@DDubsTweetheart  I really want to go too. First time to Boston + first ever NKOTB show + with you = AMAZING &amp; FUN!'}, TweetSentiment{title='"@beardoctor I really should put up a pic'}, TweetSentiment{title='@CaraKeithley You're right and I feel for ya!   He'll probably be thrilled to talk to ya on the phone tho!Tear up those cell lines!'}, TweetSentiment{title='"@chrisfreeman sad'}, TweetSentiment{title='@17THSNOOP Yea i kno'}, TweetSentiment{title='"@calvinharris deep breaths'}, TweetSentiment{title='@Akoha i promised myself i would spread the cards around India...and I only gave someone a deck &amp; then never gave them out'}, TweetSentiment{title='".RIP James E. Tritt'}, TweetSentiment{title='@Aoife_B It was only a virtual hit....'}, TweetSentiment{title='@amandabynes Hope you had fun if you decided to go Amanda - at least you didn't have to work like I did!'}, TweetSentiment{title='"@CrysWinchester D'aww'}, TweetSentiment{title='@CallaLilly84 I was kidding! I wanted to say hi an' tried to be clever. Fail.'}, TweetSentiment{title='@coralisawesome I hate when all I want to do is sleep but no matter what I can't fall asleep'}, TweetSentiment{title='definately bedtime'}, TweetSentiment{title='i hate the cold weather.'}, TweetSentiment{title='BB is on again soon..... ARGH!!'}, TweetSentiment{title='@audrey_jean heeeey i worked 11 - 4 today and i work 8 - 5 tomorrow.. and then i'm going away to brisbane friday afternoon  sorry!'}, TweetSentiment{title='*yawn* abi sleepyyyy'}, TweetSentiment{title='disappointed the Cavs...'}, TweetSentiment{title='@cunderwood83 so sad u wont be there weell miss u'}, TweetSentiment{title='@BloodhoundNdots   The pig had the last laugh!'}, TweetSentiment{title='@CHIOMA_ haha kick me ?  thats not nice of you lol. attemting? im skilled with the hands.hey i was just tryna be nice lol (innocent face)'}, TweetSentiment{title='@bbbggoodd @virgopeace @misssabrinasin DAHLINGS! haha i need help  PLEASE i need an awesome fic rec. idc what couple or ocs or whatevs'}, TweetSentiment{title='- bethanylodge: Â aww gutted beth. weâ€™ve just run out!  http://tumblr.com/xdr236usg'}, TweetSentiment{title='#iremember when my back didn't hurt after a day in the garden'}, TweetSentiment{title='@ChristieKeith I sympathize with the hot flash thing.'}, TweetSentiment{title='@_Gregatron pleasee tell @ChoonySpiceDuh not to judgee meee  before i kick her!'}, TweetSentiment{title='@_Mr_Blonde Hear hear.  I hate that.'}, TweetSentiment{title='@ayubella haha. im so bored. really REALLY bored. and my internet is so slow today. Ugh! I hate it!'}, TweetSentiment{title='@amylee1218 I think so....since about 9 mos gestation with Caitlin I haven't had a mind of my own.  ahhhh.....the days of sanity'}, TweetSentiment{title='"@baldy_za I think I need a Star Trek food replicator'}, TweetSentiment{title='@ChrisGetsMoney borrrrrrredddddd'}, TweetSentiment{title='" no MMS on 2G iPhone!  Why does Apple say its a hardware limit when jailbroken iphones can'}, TweetSentiment{title='"@dajw RAT fully booked till Nov I think'}, TweetSentiment{title='@beverleycuddy lol like it! But stressed here Digby diagnosed with Kennel Cough'}, TweetSentiment{title='@DavidArchie David! tweet more! haha.. gahh..i can't go online so often coz i gotta study for exam!  gotta word hard..'}, TweetSentiment{title='"@AaD4m: 15m'}, TweetSentiment{title='@89theBrainchild -lmao. ur so mean ::starts to fake cry::'}, TweetSentiment{title='#ivf means i have to take antibiotics before ceri's procedure... they give me indigestion'}, TweetSentiment{title='*sigh*  It's raining and I'm missing certain friends    Although rain is beautiful'}, TweetSentiment{title='@DAKIDYUNGV I don't smoke  I only drink'}, TweetSentiment{title='" the last of the long weekends ......'}, TweetSentiment{title='"...Boredom n tiredness r killin me'}, TweetSentiment{title='"@bmanminsky didn't i already tell you'}, TweetSentiment{title='im hungry xD what to have to eat.'}, TweetSentiment{title='"@Carlablah I know! I feel bad'}, TweetSentiment{title='"@altimet Ya'}, TweetSentiment{title='@ddlovato i watched bride wars just because i really like anne hathaway'}, TweetSentiment{title='@_nicolereyes so i suck too!?!'}, TweetSentiment{title='@TheIanCrawford is no longer in the Cab. To say I'm heartbroken is an understatement... good luck with everything! you're amazing ian.'}, TweetSentiment{title='@CerealBoy at mums today! No cordial.'}, TweetSentiment{title='"@AlexaPittman i am trying to my best to take off! lol. well'}, TweetSentiment{title='@BeachBabe4Ever genevaaa! my picture isnt showing up  i dont think im doing it right'}, TweetSentiment{title='@christinerose Only kindle users? Shame. Live in the UK'}, TweetSentiment{title='@AZLK That's sad  especially because the best hats are well worn &amp; loved &amp; have seen many games.'}, TweetSentiment{title='"@DarkUSS Yes'}, TweetSentiment{title='"@Chels_V I am good'}, TweetSentiment{title='@abcdefglynis omg what abt me bff'}, TweetSentiment{title='@ChexK Is book 2 good? Tempted to start that today ^^ But Aftershock looks like it's going to go all spacey and ot  What you up to?'}, TweetSentiment{title='"@_neal good call'}, TweetSentiment{title='@ashchili I WAN MCDONALDS'}, TweetSentiment{title='@AgingBackwards you can see me? I better brush my hair.'}, TweetSentiment{title='@cotswolds Nor to be catching up on work at my desk either'}, TweetSentiment{title='"@allynrawr Better be  I hate these people'}, TweetSentiment{title='"@camillajb Weather is very very bad'}, TweetSentiment{title='#saveontd i'd give up sex if it means our wiki could stay'}, TweetSentiment{title='"@braceta you mean RubyConf Europe? It was in May and unfortunately it was already sold out when I decided to go  yeah'}, TweetSentiment{title='@aka_tk Oh TK no good  SMILES   http://yfrog.com/7glnjp  imagine me sitting there clean 4 of these. lol hope tht cheers u up'}, TweetSentiment{title='I keep posting songs here and no one ever listens (I can tell ;) ) - jeez it's tough being an unsuccessful songwriter!'}, TweetSentiment{title='&lt;-- is bored at work ...'}, TweetSentiment{title='$256 and $33. I will not be so careless next time.'}, TweetSentiment{title='@_micster  I never get presents anymore. I hate being old.'}, TweetSentiment{title='@BBJudii I'm totally hatttttiiinnggggg  I wanna cry I sooo wanna be theree better be taking pictures'}, TweetSentiment{title='My family is traveling to Machala city... I'm alone at home'}, TweetSentiment{title='@dantyte i always wanted the crest from the top of the grandstand or a crash barrier from the bob bank. neither of those on sale'}, TweetSentiment{title='@chinigirl i never did get that email from  you   good thing we talked on the phone today.'}, TweetSentiment{title='@ddlovato aww I remember finding roly poly's when I lived in Texas. I don't see them in new york though'}, TweetSentiment{title='@dearlennon i h8 wesbian. who died on hi? aaaandddd i wish i was home.'}, TweetSentiment{title='"@chuckzhao awwww. no more kobe'}, TweetSentiment{title='@charmedmommy247 Aw! I was so sad when I got back from my beer run and you were gone'}, TweetSentiment{title='@danrlewis Somewhere in this is an interesting debate on activism platforms and mobilization. Wish I had time to follow it through'}, TweetSentiment{title='... there are more productive things I could do with my time.  But part of me also really wants it'}, TweetSentiment{title='"@amykate and people didn't feel that they had no option but to kill someone. Killing works'}, TweetSentiment{title='@actingblack: awww...dats. i hope u dont get sick'}, TweetSentiment{title='@AirunPoon she's better than i expected thoughhh'}, TweetSentiment{title='@chanteamazing she'll eat it all  and I'm greedy.'}, TweetSentiment{title='@chivalry_pony LEAVE ME ALONE'}, TweetSentiment{title='"*Phew* just submitted my module selections'}, TweetSentiment{title='&amp; im up again. i hate bad dreams'}, TweetSentiment{title='@Bubblegumneko he i weren't out of town...'}, TweetSentiment{title='"@confuzzleds id come to it if i lived nearer'}, TweetSentiment{title='"@awesomeanaii omg'}, TweetSentiment{title='@ChickenJoey I haven't started yet. And I don't want to'}, TweetSentiment{title='"@AmyriadfthINGs i missed you too'}, TweetSentiment{title='@Boo2AGoose no new piercings'}, TweetSentiment{title='@ aishazam aaaaaakkkh biasa ajaaaa dissapointed shaaa hehe cmn dpt 1 baju'}, TweetSentiment{title='@biancamanzi : i cry... for love   .... and we will are the best group!! { remember: i don't know speak in english xdd}'}, TweetSentiment{title='@BoomKack a good 2step will nver go out of style! like seeing my mom or sum1 who REALLY knw how to 2step do it.I just have the basics dwn'}, TweetSentiment{title='@_jkd stfu lol i miss ur meanness...no1 is mean 2 me here.'}, TweetSentiment{title='@alyankovic Do you have any pull on getting this fixed on a permanent basis? Mine's sadly still broken.'}, TweetSentiment{title='@chelseaxandra I wish you were here.'}, TweetSentiment{title='@4everpinkfan Ummmmm.........ok u win  .........again'}, TweetSentiment{title='@akomuzikera I hope I can find it online somewhere. Where did you watch it? I couldn't find it on ESPN or anything earlier'}, TweetSentiment{title='"@ashleyobvs he plays this game'}, TweetSentiment{title='@BrantleyG Oh Dammit! Woulda loved that! Bummed I missed your call  R u still here?'}, TweetSentiment{title='@Annie_Lang Mommas back baby!! Aww missed you on Twitter life.  Banter banter!! And in real life also!! xxxx'}, TweetSentiment{title='@chelsea328. well my day is ruined'}, TweetSentiment{title='@brandyinboise How did you swing those? We didn't get that close.'}, TweetSentiment{title='@CocaBeenSlinky no its a tooth i got root canal done on a year ago now ever since i get throbbing pains under my tooth every so often'}, TweetSentiment{title='@Alyssa_Milano @selenagomez has this vid up and made me  http://tinyurl.com/c7o5tt'}, TweetSentiment{title='"&quot;I promise never to put you through anything like this ever again'}, TweetSentiment{title='@catarionna Yeah... I was freelancing for awhile.. but I'm not so good at that hole &quot;networking&quot; thing.'}, TweetSentiment{title='@CRulez  Nick is meeting me @ Donovon's wit sum cash !!! Datz`big cuz u know dat nigga is tight !!!!!!!  atleast I got 1 bro dat luv me'}, TweetSentiment{title='@AnnieDAFG Aww yea same here. So sadd..'}, TweetSentiment{title='@andrewcrawshaw There should have been a warning on that link  very sad but incredible of Pixar'}, TweetSentiment{title='"@Dancegurl91 yeah'}, TweetSentiment{title='@agau4779 ... whaaa  was that serious'}, TweetSentiment{title='AM meetings suck!!'}, TweetSentiment{title='@amyshell Just on way home from shopping. Spent way to much'}, TweetSentiment{title='@AutismRunner yeah the right to speak your mind seems to be limited more and more  in Vegas w/ Hubby (he has to work) I get to play'}, TweetSentiment{title='@christianaty i know it sucks  ... whats up u gon party with us wen i get back from chicago?'}, TweetSentiment{title='@andie_d I missed it  but glad you all had a sunny morning!'}, TweetSentiment{title='@adammshankman yeah he will. phillip is freaking awesome. annnd i did not love the shane sparks stuff'}, TweetSentiment{title='"@a_willow I wouldnï¿½t bother'}, TweetSentiment{title='just heard one of my site members has passed away'}, TweetSentiment{title='@danissla i dnt like to hng out alone nis. but our frnd has twiter and fllow us nis. yaaah  hehe'}, TweetSentiment{title='"@CountryGardener Tried that  Different pics'}, TweetSentiment{title='@2DForever They put House of the Dead 2 &amp; 3 in the box of Overkill'}, TweetSentiment{title='someone threw out a huge bag of clothes/shoes of mine in the garbage by mistake...so not happy!!!'}, TweetSentiment{title='@csdaley i cant find any to buy'}, TweetSentiment{title='@azelmer that I didn't I woke up late!  I was ready for my blue berry muffin too.. I forgot to grab it. I was mad!!! It sounds so good.'}, TweetSentiment{title='@ahj cant wait!! wish i got tickets though!!'}, TweetSentiment{title=';( noooo! why? things are so complicated if I spelt thy wrong idc'}, TweetSentiment{title='@bsanfordjr No new hardware for me'}, TweetSentiment{title='@bluessence me too...'}, TweetSentiment{title='"@davidaustin The cups suck too! If they weren't so thin'}, TweetSentiment{title='"@danger_skies  cus it all got too much attention in the end'}, TweetSentiment{title='@BrandonPam The bad part is that it keeps on getting worse by the minute'}, TweetSentiment{title='@ashtonmiyako ASHTON. what idea where you talking about? ha. cuz i already forgot'}, TweetSentiment{title='@amandaxmartin i dont know.'}, TweetSentiment{title='@dean_b  Poor you... disenchanted by cuppincakes. Were they cute cupcakes?'}, TweetSentiment{title='@abiteofsanity Ouch.  What's wrong/hurting?'}, TweetSentiment{title='"@brandonrichey ~ My Monday ROCKED!  Thanks for asking! AND yes'}, TweetSentiment{title='#itsuckswhen you twitt so much you over load the page and lose followers  cry with me!!!! *snifF SnifF*'}, TweetSentiment{title='@Bam1500 it doesn'... and 4647472 more bytes
```

Code from [TrieDemo.java:381](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L381) executed in 1485.74 seconds: 
```java
      HashMap<String, List<String>> map = new HashMap<>();
      map.put("pos", tweetsPositive.stream().limit(trainingSize).map(x -> x.getText()).collect(Collectors.toList()));
      map.put("neg", tweetsNegative.stream().limit(trainingSize).map(x -> x.getText()).collect(Collectors.toList()));
      return new RuleGenerator().setVerbose(System.out).categorizationTree(map, 12);
```
Returns: 
```
    com.simiacryptus.util.text.RuleGenerator$$Lambda$99/1476011703@38cccef
```
Logging: 
```
    "t " -> {neg=30685, pos=25021}	{neg=19315, pos=24979}
     "@d" -> {neg=1709, pos=84}	{neg=28976, pos=24937}
      " @" -> {neg=115, pos=77}	{neg=1594, pos=7}
       "fol" -> {neg=2, pos=21}	{neg=113, pos=56}
        "ed" -> {neg=1, pos=2}	{neg=1, pos=19}
         "ne" -> {neg=1, pos=1}	{neg=0, pos=1}
          "n" -> {neg=1, pos=1}	{neg=0, pos=0}
           "n" -> {neg=1, pos=1}	{neg=0, pos=0}
            "n" -> {neg=1, pos=1}	{neg=0, pos=0}
             "n" -> {neg=1, pos=1}	{neg=0, pos=0}
              "n" -> {neg=1, pos=1}	{neg=0, pos=0}
               "n" -> {neg=1, pos=1}	{neg=0, pos=0}
          " " -> {neg=0, pos=1}	{neg=0, pos=0}
           " " -> {neg=0, pos=1}	{neg=0, pos=0}
            " " -> {neg=0, pos=1}	{neg=0, pos=0}
             " " -> {neg=0, pos=1}	{neg=0, pos=0}
              " " -> {neg=0, pos=1}	{neg=0, pos=0}
               " " -> {neg=0, pos=1}	{neg=0, pos=0}
         "ing" -> {neg=1, pos=2}	{neg=0, pos=17}
          "b" -> {neg=0, pos=2}	{neg=1, pos=0}
           " " -> {neg=0, pos=2}	{neg=0, pos=0}
            " " -> {neg=0, pos=2}	{neg=0, pos=0}
             " " -> {neg=0, pos=2}	{neg=0, pos=0}
              " " -> {neg=0, pos=2}	{neg=0, pos=0}
               " " -> {neg=0, pos=2}	{neg=0, pos=0}
           " " -> {neg=1, pos=0}	{neg=0, pos=0}
            " " -> {neg=1, pos=0}	{neg=0, pos=0}
             " " -> {neg=1, pos=0}	{neg=0, pos=0}
              " " -> {neg=1, pos=0}	{neg=0, pos=0}
               " " -> {neg=1, pos=0}	{neg=0, pos=0}
          " " -> {neg=0, pos=17}	{neg=0, pos=0}
           " " -> {neg=0, pos=17}	{neg=0, pos=0}
            " " -> {neg=0, pos=17}	{neg=0, pos=0}
             " " -> {neg=0, pos=17}	{neg=0, pos=0}
              " " -> {neg=0, pos=17}	{neg=0, pos=0}
               " " -> {neg=0, pos=17}	{neg=0, pos=0}
        " @dd" -> {neg=0, pos=10}	{neg=113, pos=46}
         " " -> {neg=0, pos=10}	{neg=0, pos=0}
          " " -> {neg=0, pos=10}	{neg=0, pos=0}
           " " -> {neg=0, pos=10}	{neg=0, pos=0}
            " " -> {neg=0, pos=10}	{neg=0, pos=0}
             " " -> {neg=0, pos=10}	{neg=0, pos=0}
              " " -> {neg=0, pos=10}	{neg=0, pos=0}
               " " -> {neg=0, pos=10}	{neg=0, pos=0}
         "yo" -> {neg=21, pos=17}	{neg=92, pos=29}
          ".." -> {neg=6, pos=0}	{neg=15, pos=17}
           " " -> {neg=6, pos=0}	{neg=0, pos=0}
            " " -> {neg=6, pos=0}	{neg=0, pos=0}
             " " -> {neg=6, pos=0}	{neg=0, pos=0}
              " " -> {neg=6, pos=0}	{neg=0, pos=0}
               " " -> {neg=6, pos=0}	{neg=0, pos=0}
           "en" -> {neg=8, pos=3}	{neg=7, pos=14}
            "l" -> {neg=8, pos=2}	{neg=0, pos=1}
             "l" -> {neg=8, pos=2}	{neg=0, pos=0}
              "l" -> {neg=8, pos=2}	{neg=0, pos=0}
               "l" -> {neg=8, pos=2}	{neg=0, pos=0}
             " " -> {neg=0, pos=1}	{neg=0, pos=0}
              " " -> {neg=0, pos=1}	{neg=0, pos=0}
               " " -> {neg=0, pos=1}	{neg=0, pos=0}
            "be" -> {neg=0, pos=8}	{neg=7, pos=6}
             " " -> {neg=0, pos=8}	{neg=0, pos=0}
              " " -> {neg=0, pos=8}	{neg=0, pos=0}
               " " -> {neg=0, pos=8}	{neg=0, pos=0}
             "on" -> {neg=2, pos=4}	{neg=5, pos=2}
              "e " -> {neg=2, pos=2}	{neg=0, pos=2}
               "ee" -> {neg=0, pos=2}	{neg=2, pos=0}
               " " -> {neg=0, pos=2}	{neg=0, pos=0}
              "h" -> {neg=4, pos=2}	{neg=1, pos=0}
               "b" -> {neg=3, pos=0}	{neg=1, pos=2}
               " " -> {neg=1, pos=0}	{neg=0, pos=0}
          " d" -> {neg=45, pos=7}	{neg=47, pos=22}
           "ca" -> {neg=11, pos=6}	{neg=34, pos=1}
            "ec" -> {neg=0, pos=3}	{neg=11, pos=3}
             " " -> {neg=0, pos=3}	{neg=0, pos=0}
              " " -> {neg=0, pos=3}	{neg=0, pos=0}
               " " -> {neg=0, pos=3}	{neg=0, pos=0}
             "ar" -> {neg=3, pos=3}	{neg=8, pos=0}
              "rt" -> {neg=0, pos=3}	{neg=3, pos=0}
               " " -> {neg=0, pos=3}	{neg=0, pos=0}
               " " -> {neg=3, pos=0}	{neg=0, pos=0}
              " " -> {neg=8, pos=0}	{neg=0, pos=0}
               " " -> {neg=8, pos=0}	{neg=0, pos=0}
            "A" -> {neg=5, pos=1}	{neg=29, pos=0}
             "al" -> {neg=1, pos=1}	{neg=4, pos=0}
              "h" -> {neg=1, pos=1}	{neg=0, pos=0}
               "h" -> {neg=1, pos=1}	{neg=0, pos=0}
              " " -> {neg=4, pos=0}	{neg=0, pos=0}
               " " -> {neg=4, pos=0}	{neg=0, pos=0}
             " " -> {neg=29, pos=0}	{neg=0, pos=0}
              " " -> {neg=29, pos=0}	{neg=0, pos=0}
               " " -> {neg=29, pos=0}	{neg=0, pos=0}
           "er" -> {neg=29, pos=6}	{neg=18, pos=16}
            "on" -> {neg=6, pos=4}	{neg=23, pos=2}
             " @" -> {neg=6, pos=4}	{neg=0, pos=0}
              " @" -> {neg=6, pos=4}	{neg=0, pos=0}
               " @" -> {neg=6, pos=4}	{neg=0, pos=0}
             "W" -> {neg=2, pos=2}	{neg=21, pos=0}
              "/" -> {neg=1, pos=0}	{neg=1, pos=2}
               " " -> {neg=1, pos=0}	{neg=0, pos=0}
               "d" -> {neg=1, pos=2}	{neg=0, pos=0}
              " " -> {neg=21, pos=0}	{neg=0, pos=0}
               " " -> {neg=21, pos=0}	{neg=0, pos=0}
            "ie" -> {neg=0, pos=6}	{neg=18, pos=10}
             " " -> {neg=0, pos=6}	{neg=0, pos=0}
              " " -> {neg=0, pos=6}	{neg=0, pos=0}
               " " -> {neg=0, pos=6}	{neg=0, pos=0}
             "oo" -> {neg=11, pos=1}	{neg=7, pos=9}
              "&" -> {neg=0, pos=1}	{neg=11, pos=0}
               " " -> {neg=0, pos=1}	{neg=0, pos=0}
               " " -> {neg=11, pos=0}	{neg=0, pos=0}
              "c" -> {neg=6, pos=3}	{neg=1, pos=6}
               "nt" -> {neg=4, pos=0}	{neg=2, pos=3}
               "t" -> {neg=1, pos=6}	{neg=0, pos=0}
       " " -> {neg=1594, pos=7}	{neg=0, pos=0}
        " " -> {neg=1594, pos=7}	{neg=0, pos=0}
         " " -> {neg=1594, pos=7}	{neg=0, pos=0}
          " " -> {neg=1594, pos=7}	{neg=0, pos=0}
           " " -> {neg=1594, pos=7}	{neg=0, pos=0}
            " " -> {neg=1594, pos=7}	{neg=0, pos=0}
             " " -> {neg=1594, pos=7}	{neg=0, pos=0}
              " " -> {neg=1594, pos=7}	{neg=0, pos=0}
               " " -> {neg=1594, pos=7}	{neg=0, pos=0}
      " s" -> {neg=15619, pos=11985}	{neg=13357, pos=12952}
       "sad" -> {neg=972, pos=55}	{neg=14647, pos=11930}
        " " -> {neg=972, pos=55}	{neg=0, pos=0}
         " " -> {neg=972, pos=55}	{neg=0, pos=0}
          " " -> {neg=972, pos=55}	{neg=0, pos=0}
           " " -> {neg=972, pos=55}	{neg=0, pos=0}
            " " -> {neg=972, pos=55}	{neg=0, pos=0}
             " " -> {neg=972, pos=55}	{neg=0, pos=0}
              " " -> {neg=972, pos=55}	{neg=0, pos=0}
               " " -> {neg=972, pos=55}	{neg=0, pos=0}
        "you" -> {neg=3254, pos=4005}	{neg=11393, pos=7925}
         "orry" -> {neg=424, pos=94}	{neg=2830, pos=3911}
          "ry t" -> {neg=108, pos=10}	{neg=316, pos=84}
           "tw" -> {neg=6, pos=5}	{neg=102, pos=5}
            "ea" -> {neg=6, pos=0}	{neg=0, pos=5}
             " " -> {neg=6, pos=0}	{neg=0, pos=0}
              " " -> {neg=6, pos=0}	{neg=0, pos=0}
               " " -> {neg=6, pos=0}	{neg=0, pos=0}
             " " -> {neg=0, pos=5}	{neg=0, pos=0}
              " " -> {neg=0, pos=5}	{neg=0, pos=0}
               " " -> {neg=0, pos=5}	{neg=0, pos=0}
            "?" -> {neg=15, pos=3}	{neg=87, pos=2}
             "ai" -> {neg=3, pos=2}	{neg=12, pos=1}
              " w" -> {neg=2, pos=2}	{neg=1, pos=0}
               "c" -> {neg=2, pos=1}	{neg=0, pos=1}
               " " -> {neg=1, pos=0}	{neg=0, pos=0}
              " bo" -> {neg=3, pos=1}	{neg=9, pos=0}
               "s " -> {neg=3, pos=0}	{neg=0, pos=1}
               " " -> {neg=9, pos=0}	{neg=0, pos=0}
             " j" -> {neg=3, pos=2}	{neg=84, pos=0}
              "l" -> {neg=2, pos=2}	{neg=1, pos=0}
               "w" -> {neg=2, pos=1}	{neg=0, pos=1}
               " " -> {neg=1, pos=0}	{neg=0, pos=0}
              " " -> {neg=84, pos=0}	{neg=0, pos=0}
               " " -> {neg=84, pos=0}	{neg=0, pos=0}
           "on'" -> {neg=22, pos=12}	{neg=294, pos=72}
            "I" -> {neg=17, pos=8}	{neg=5, pos=4}
             "I" -> {neg=17, pos=8}	{neg=0, pos=0}
              "I" -> {neg=17, pos=8}	{neg=0, pos=0}
               "I" -> {neg=17, pos=8}	{neg=0, pos=0}
             ".." -> {neg=0, pos=1}	{neg=5, pos=3}
              " " -> {neg=0, pos=1}	{neg=0, pos=0}
               " " -> {neg=0, pos=1}	{neg=0, pos=0}
              "d " -> {neg=1, pos=2}	{neg=4, pos=1}
               "ww" -> {neg=1, pos=0}	{neg=0, pos=2}
               "rr" -> {neg=4, pos=1}	{neg=0, pos=0}
            "/" -> {neg=10, pos=7}	{neg=284, pos=65}
             "e t" -> {neg=0, pos=3}	{neg=10, pos=4}
              " " -> {neg=0, pos=3}	{neg=0, pos=0}
               " " -> {neg=0, pos=3}	{neg=0, pos=0}
              "ot" -> {neg=2, pos=4}	{neg=8, pos=0}
               "n" -> {neg=2, pos=4}	{neg=0, pos=0}
               " " -> {neg=8, pos=0}	{neg=0, pos=0}
             "b" -> {neg=215, pos=45}	{neg=69, pos=20}
              "in" -> {neg=122, pos=33}	{neg=93, pos=12}
               "goo" -> {neg=3, pos=6}	{neg=119, pos=27}
               "_" -> {neg=9, pos=5}	{neg=84, pos=7}
              "  " -> {neg=43, pos=6}	{neg=26, pos=14}
               "r th" -> {neg=1, pos=3}	{neg=42, pos=3}
               "S" -> {neg=0, pos=6}	{neg=26, pos=8}
          "a" -> {neg=2805, pos=3879}	{neg=25, pos=32}
           "a" -> {neg=2805, pos=3879}	{neg=0, pos=0}
            "a" -> {neg=2805, pos=3879}	{neg=0, pos=0}
             "a" -> {neg=2805, pos=3879}	{neg=0, pos=0}
              "a" -> {neg=2805, pos=3879}	{neg=0, pos=0}
               "a" -> {neg=2805, pos=3879}	{neg=0, pos=0}
           " no" -> {neg=12, pos=2}	{neg=13, pos=30}
            "en" -> {neg=1, pos=2}	{neg=11, pos=0}
             "l" -> {neg=1, pos=2}	{neg=0, pos=0}
              "l" -> {neg=1, pos=2}	{neg=0, pos=0}
               "l" -> {neg=1, pos=2}	{neg=0, pos=0}
             " " -> {neg=11, pos=0}	{neg=0, pos=0}
              " " -> {neg=11, pos=0}	{neg=0, pos=0}
               " " -> {neg=11, pos=0}	{neg=0, pos=0}
            "." -> {neg=3, pos=15}	{neg=10, pos=15}
             " m" -> {neg=2, pos=5}	{neg=1, pos=10}
              "e" -> {neg=1, pos=5}	{neg=1, pos=0}
               " m" -> {neg=1, pos=5}	{neg=0, pos=0}
               " " -> {neg=1, pos=0}	{neg=0, pos=0}
              "i" -> {neg=0, pos=10}	{neg=1, pos=0}
               " " -> {neg=0, pos=10}	{neg=0, pos=0}
               " " -> {neg=1, pos=0}	{neg=0, pos=0}
             "p" -> {neg=3, pos=11}	{neg=7, pos=4}
              "b" -> {neg=3, pos=6}	{neg=0, pos=5}
               "b" -> {neg=3, pos=6}	{neg=0, pos=0}
               " " -> {neg=0, pos=5}	{neg=0, pos=0}
              """ -> {neg=1, pos=4}	{neg=6, pos=0}
               "i" -> {neg=1, pos=3}	{neg=0, pos=1}
               " " -> {neg=6, pos=0}	{neg=0, pos=0}
         " " -> {neg=11393, pos=7925}	{neg=0, pos=0}
          " " -> {neg=11393, pos=7925}	{neg=0, pos=0}
           " " -> {neg=11393, pos=7925}	{neg=0, pos=0}
            " " -> {neg=11393, pos=7925}	{neg=0, pos=0}
             " " -> {neg=11393, pos=7925}	{neg=0, pos=0}
              " " -> {neg=11393, pos=7925}	{neg=0, pos=0}
               " " -> {neg=11393, pos=7925}	{neg=0, pos=0}
       "'t" -> {neg=2769, pos=1385}	{neg=10588, pos=11567}
        "t" -> {neg=2769, pos=1385}	{neg=0, pos=0}
         "t" -> {neg=2769, pos=1385}	{neg=0, pos=0}
          "t" -> {neg=2769, pos=1385}	{neg=0, pos=0}
           "t" -> {neg=2769, pos=1385}	{neg=0, pos=0}
            "t" -> {neg=2769, pos=1385}	{neg=0, pos=0}
             "t" -> {neg=2769, pos=1385}	{neg=0, pos=0}
              "t" -> {neg=2769, pos=1385}	{neg=0, pos=0}
               "t" -> {neg=2769, pos=1385}	{neg=0, pos=0}
        " no" -> {neg=2459, pos=1414}	{neg=8129, pos=10153}
         " " -> {neg=2459, pos=1414}	{neg=0, pos=0}
          " " -> {neg=2459, pos=1414}	{neg=0, pos=0}
           " " -> {neg=2459, pos=1414}	{neg=0, pos=0}
            " " -> {neg=2459, pos=1414}	{neg=0, pos=0}
             " " -> {neg=2459, pos=1414}	{neg=0, pos=0}
              " " -> {neg=2459, pos=1414}	{neg=0, pos=0}
               " " -> {neg=2459, pos=1414}	{neg=0, pos=0}
         " i " -> {neg=1219, pos=800}	{neg=6910, pos=9353}
          "dddd" -> {neg=0, pos=1}	{neg=1219, pos=799}
           " " -> {neg=0, pos=1}	{neg=0, pos=0}
            " " -> {neg=0, pos=1}	{neg=0, pos=0}
             " " -> {neg=0, pos=1}	{neg=0, pos=0}
              " " -> {neg=0, pos=1}	{neg=0, pos=0}
               " " -> {neg=0, pos=1}	{neg=0, pos=0}
           "i l" -> {neg=68, pos=126}	{neg=1151, pos=673}
            "aa" -> {neg=4, pos=1}	{neg=64, pos=125}
             "aaa" -> {neg=2, pos=0}	{neg=2, pos=1}
              " " -> {neg=2, pos=0}	{neg=0, pos=0}
               " " -> {neg=2, pos=0}	{neg=0, pos=0}
              "l" -> {neg=2, pos=1}	{neg=0, pos=0}
               "l" -> {neg=2, pos=1}	{neg=0, pos=0}
             " i " -> {neg=64, pos=125}	{neg=0, pos=0}
              " i " -> {neg=64, pos=125}	{neg=0, pos=0}
               " i " -> {neg=64, pos=125}	{neg=0, pos=0}
            "-----" -> {neg=0, pos=1}	{neg=1151, pos=672}
             " " -> {neg=0, pos=1}	{neg=0, pos=0}
              " " -> {neg=0, pos=1}	{neg=0, pos=0}
               " " -> {neg=0, pos=1}	{neg=0, pos=0}
             "miss" -> {neg=112, pos=12}	{neg=1039, pos=660}
              "www" -> {neg=1, pos=1}	{neg=111, pos=11}
               "w" -> {neg=1, pos=1}	{neg=0, pos=0}
               ".." -> {neg=28, pos=0}	{neg=83, pos=11}
              "w" -> {neg=812, pos=487}	{neg=227, pos=173}
               " i" -> {neg=812, pos=487}	{neg=0, pos=0}
               "mm" -> {neg=1, pos=8}	{neg=226, pos=165}
          "I" -> {neg=2900, pos=3084}	{neg=4010, pos=6269}
           "I w" -> {neg=582, pos=348}	{neg=2318, pos=2736}
            "??????" -> {neg=0, pos=1}	{neg=582, pos=347}
             " " -> {neg=0, pos=1}	{neg=0, pos=0}
              " " -> {neg=0, pos=1}	{neg=0, pos=0}
               " " -> {neg=0, pos=1}	{neg=0, pos=0}
             "wil" -> {neg=46, pos=80}	{neg=536, pos=267}
              "AA" -> {neg=1, pos=0}	{neg=45, pos=80}
               " " -> {neg=1, pos=0}	{neg=0, pos=0}
               " I'" -> {neg=8, pos=0}	{neg=37, pos=80}
              " I" -> {neg=511, pos=256}	{neg=25, pos=11}
               " I" -> {neg=511, pos=256}	{neg=0, pos=0}
               "a" -> {neg=25, pos=11}	{neg=0, pos=0}
            " miss" -> {neg=176, pos=44}	{neg=2142, pos=2692}
             "YY" -> {neg=0, pos=1}	{neg=176, pos=43}
              " " -> {neg=0, pos=1}	{neg=0, pos=0}
               " " -> {neg=0, pos=1}	{neg=0, pos=0}
              "xx" -> {neg=1, pos=2}	{neg=175, pos=41}
               "a" -> {neg=1, pos=2}	{neg=0, pos=0}
               "Ni" -> {neg=0, pos=4}	{neg=175, pos=37}
             "you" -> {neg=371, pos=774}	{neg=1771, pos=1918}
              "sssss" -> {neg=2, pos=0}	{neg=369, pos=774}
               " " -> {neg=2, pos=0}	{neg=0, pos=0}
               "@D" -> {neg=23, pos=2}	{neg=346, pos=772}
              "t;" -> {neg=68, pos=162}	{neg=1703, pos=1756}
               "--" -> {neg=1, pos=11}	{neg=67, pos=151}
               "ov" -> {neg=117, pos=210}	{neg=1586, pos=1546}
           "o" -> {neg=3670, pos=5812}	{neg=340, pos=457}
            "o" -> {neg=3670, pos=5812}	{neg=0, pos=0}
             "o" -> {neg=3670, pos=5812}	{neg=0, pos=0}
              "o" -> {neg=3670, pos=5812}	{neg=0, pos=0}
               "o" -> {neg=3670, pos=5812}	{neg=0, pos=0}
            "tttttt" -> {neg=1, pos=0}	{neg=339, pos=457}
             "t" -> {neg=1, pos=0}	{neg=0, pos=0}
              "t" -> {neg=1, pos=0}	{neg=0, pos=0}
               "t" -> {neg=1, pos=0}	{neg=0, pos=0}
             "a" -> {neg=303, pos=425}	{neg=36, pos=32}
              "uu" -> {neg=5, pos=0}	{neg=298, pos=425}
               " " -> {neg=5, pos=0}	{neg=0, pos=0}
               "." -> {neg=84, pos=80}	{neg=214, pos=345}
              "uu" -> {neg=0, pos=1}	{neg=36, pos=31}
               "s" -> {neg=0, pos=1}	{neg=0, pos=0}
              ... and 15913 more bytes
```

Code from [TrieDemo.java:387](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L387) executed in 0.07 seconds: 
```java
      return tweetsPositive.stream().skip(trainingSize).map(x->x.getText()).mapToDouble(str->{
          Map<String, Double> prob = rule.apply(str);
          System.out.println(String.format("%s -> %s", str, prob));
          return prob.getOrDefault("pos", 0.0) < 0.5 ? 0.0 : 1.0;
      }).average().getAsDouble();
```
Returns: 
```
    0.695
```
Logging: 
```
    "@anonymum Pfffft -> {neg=0.4057368075322969, pos=0.5942631924677031}
    @Clumsyflic haha don't take it to heart so much  shes an idiot!! -> {neg=0.5897608448079511, pos=0.41023915519204884}
    .. only 19 days to go until I'll fly to the U.S  .. soo looking forward to it! .. &lt;3 *squee* -> {neg=0.49342770475227504, pos=0.506572295247725}
    "@anguaji So good you had to tweet it twice?  I know you ANGWAAGEE -> {neg=0.3094812164579606, pos=0.6905187835420393}
    @CityGirl912 I'll keep that balance thing in mind when I need some! -> {neg=0.5897608448079511, pos=0.41023915519204884}
    @amy__xx you done anything on photoshop yet  ? -> {neg=0.3870491457498418, pos=0.6129508542501582}
    .@MMofOz did you see the pics?? just in my photos... there's an album -> {neg=0.4196588868940754, pos=0.5803411131059246}
    @backstreetboys still doing the shoot? must be taking LOADS of pics eh.. for THIS IS US!!!! so excited!!!! wat r ur plans 4 the weekend? -> {neg=0.5897608448079511, pos=0.41023915519204884}
    @brittanylovato I'm excited to see the graduation pictures! -> {neg=0.49342770475227504, pos=0.506572295247725}
    @brianjsmith u should use a hashtag! #o&amp;bgame -> {neg=0.49342770475227504, pos=0.506572295247725}
    "@bhyundai 19 RDRs  and our store did 49 new for the first time... you dealers know how significant that is... well -> {neg=0.4196588868940754, pos=0.5803411131059246}
    "@alyankovic http://twitpic.com/4figf - Oh -> {neg=0.23684210526315788, pos=0.7631578947368421}
    @AmandaLaura626 That's awesome. I'm sure you make a lot of people happy -> {neg=0.4196588868940754, pos=0.5803411131059246}
    @alyb_ yes really -> {neg=0.3998855835240275, pos=0.6001144164759725}
    @BenjaminEllis @maggiephilbin If a bidding game is going on - I'm bidding for the photos to be published -> {neg=0.33847763536489667, pos=0.6615223646351033}
    "@attachingwings Lauren -> {neg=0.4057368075322969, pos=0.5942631924677031}
    @Alomax drive safe sounds like you had a great time! -> {neg=0.4196588868940754, pos=0.5803411131059246}
    @beimaejor Oh that's wsup.. Can't wait 2 hear what u created! -> {neg=0.6665864227250843, pos=0.3334135772749157}
    @ahw thanks! -> {neg=0.10748299319727891, pos=0.8925170068027211}
    *yawn* rain = no can run = no point in waking up before 9! -> {neg=0.6349083397882779, pos=0.3650916602117222}
    @BevClement I don't think we talk much about Banks these days - not quite as respected as they once were -> {neg=0.6665864227250843, pos=0.3334135772749157}
    @charlieskies my favorite was evee.  haha and it liked evolved into three different things. &lt;3 &amp; who can resist JIGGLYPUFFF? -> {neg=0.3073394495412844, pos=0.6926605504587156}
    "@alpheus1 Cool -> {neg=0.4057368075322969, pos=0.5942631924677031}
    @AllLacqueredUp OMG!!!  What a FABULOUS opportunity for you!! Can not WAIT to see them!!  Yay -> {neg=0.4196588868940754, pos=0.5803411131059246}
    @alt67oficial eu -> {neg=0.33847763536489667, pos=0.6615223646351033}
    @amberchase http://twitpic.com/6bk5o - Mmm very nice panties -> {neg=0.23684210526315788, pos=0.7631578947368421}
    @audiosoul u hear Pezzner's remix of Plough Hands yet?? -> {neg=0.33847763536489667, pos=0.6615223646351033}
    "@BertaWooster speaking of your nick -> {neg=0.49342770475227504, pos=0.506572295247725}
    @AlexAllTimeLow @JackAllTimeLow @RianDawson @ZackAllTimeLow your new album WILL suck. nothing personal. -> {neg=0.49342770475227504, pos=0.506572295247725}
    "@brodyjenner http://twitpic.com/6d9ty - brody -> {neg=0.23684210526315788, pos=0.7631578947368421}
    @alexisstar22 GOT A NEW HUBBY NOW  http://yfrog.com/4b94cg -> {neg=0.23684210526315788, pos=0.7631578947368421}
    "@AriaaJaeger  WAIT! You are bringing shame to womanhood. You couldn't find a THING to BUY? I need a lemon drop -> {neg=0.5897608448079511, pos=0.41023915519204884}
    "@amcgb ...thx -> {neg=0.45404157043879906, pos=0.5459584295612009}
    @_pixie_ quiet time w/ S until R wakes up. -> {neg=0.5121951219512195, pos=0.4878048780487805}
    @britneyspears Thank you SO much Britney  &lt;3 -> {neg=0.057971014492753624, pos=0.9420289855072463}
    @BlissfulGirl good idea I will book mark that -> {neg=0.6073045578025958, pos=0.39269544219740415}
    ... but right now i love them so much i cant stop listening to them/honey honey;dancing quuen;mamma mia;i have a dream&amp; ect. I LOVE THEM. -> {neg=0.5897608448079511, pos=0.41023915519204884}
    "@away2me You can snack -> {neg=0.49342770475227504, pos=0.506572295247725}
    @a__money 45min/5x/week is awesome! I totally need to start doing that. Ur diabolical language coffee plot made me laugh. -> {neg=0.5897608448079511, pos=0.41023915519204884}
    @careyyyy Tetris must have some anniversary today - it's Google's &quot;hot link&quot; today - so that probably helps the trend -> {neg=0.5897608448079511, pos=0.41023915519204884}
    "@carman63 The potato thing sounds great -> {neg=0.49342770475227504, pos=0.506572295247725}
    "@_ophelia nice!!! Glad you posted them on here -> {neg=0.4057368075322969, pos=0.5942631924677031}
    @babydoll20 And enjoy the rain.... -> {neg=0.45404157043879906, pos=0.5459584295612009}
    @Astro_Mike Everyone down in Houston is praying for all yall. You should come to Space Center Houston after you land. -> {neg=0.49342770475227504, pos=0.506572295247725}
    @basiaa sucker im not going -> {neg=0.5897608448079511, pos=0.41023915519204884}
    #followfriday @n3rin3 @jurgenphoto @shaneaddinall @robi27 @dannyvan @byronrode @tattood1 -> {neg=0.4074074074074074, pos=0.5925925925925926}
    @ the pink house for graduation dinner -> {neg=0.33847763536489667, pos=0.6615223646351033}
    @avongirl1  Thanks for following. Have a great week! Hugs -> {neg=0.3870491457498418, pos=0.6129508542501582}
    @Babberz I like &quot;All of the Above&quot; as the best answer -> {neg=0.3073394495412844, pos=0.6926605504587156}
    @AngryBritain don't forget your plastic pants -> {neg=0.6665864227250843, pos=0.3334135772749157}
    @buckhollywood omg i love you... and i noticed your wicked quote on blogtv... toss toss lol!!  love you buck!!! ROCK ON!! -> {neg=0.5267538644470868, pos=0.47324613555291317}
    @aalSamm Dudeee yu got twitter  I'm happy happy happy... and proud! haha yu wanna c your ''friend'' taylor lautner you're joke! ly x -> {neg=0.3094812164579606, pos=0.6905187835420393}
    @ALauderdale What? ::innocent face:: I was going to say SKITTLES -> {neg=0.5897608448079511, pos=0.41023915519204884}
    @abraxas3d first make something work  There are not a lot of differences between the two. At least for figuring out the basic stuff. -> {neg=0.5897608448079511, pos=0.41023915519204884}
    @AuntyNan Hey your husband has his moments. -> {neg=0.414621932899349, pos=0.585378067100651}
    @aaronmcarroll Hope the press check went great! -> {neg=0.3870491457498418, pos=0.6129508542501582}
    @captainjack63 Thanks for following -> {neg=0.07725788900979326, pos=0.9227421109902068}
    @chattyman Hey loved the show last nite cant wait till next one -> {neg=0.5897608448079511, pos=0.41023915519204884}
    ...will Ferrel movie marathon in bed  so bored but feeling slightly better. I hate word limits. -> {neg=0.5897608448079511, pos=0.41023915519204884}
    @AnotherJulia I think therefore I am thinking.... or something like that -> {neg=0.49342770475227504, pos=0.506572295247725}
    @ChristopherDXS I'm glad you're enjoying the blog -> {neg=0.33847763536489667, pos=0.6615223646351033}
    @alysonwills Good attitude!  Shake it off -> {neg=0.3870491457498418, pos=0.6129508542501582}
    @caead should be cinderfella instead -> {neg=0.49342770475227504, pos=0.506572295247725}
    ...be well rested for Disneyland -> {neg=0.45404157043879906, pos=0.5459584295612009}
    @applegravy The funny thing is I've often thought of what this is all about and then I got your invite this morning so signed up for this -> {neg=0.4196588868940754, pos=0.5803411131059246}
    @1sweetwhirl Hello Kelly   How could you get any cuter -> {neg=0.3870491457498418, pos=0.6129508542501582}
    @_q Interesting website. Thank you for sharing it with me. I will continue to research it...   - Jay Jeter The Coming Storm -> {neg=0.4196588868940754, pos=0.5803411131059246}
    @antz88c a fresh start is always good. at least it levels the playing field.  Lau got a bear and a wolf on her first 2 brutes. Lucky gal -> {neg=0.5897608448079511, pos=0.41023915519204884}
    ".@jsbelfiore must have missed that... but I was never part of the Rocky crowd -> {neg=0.666232073011734, pos=0.33376792698826596}
    @chelseasms @amy4669 It's gorgeous indeed -> {neg=0.33847763536489667, pos=0.6615223646351033}
    @BluePoles  Glad you liked it . I think I will open a Blue Poles Tempranillo in a day or two  to check on progress -> {neg=0.3162393162393162, pos=0.6837606837606838}
    hee hee. your so weird. and i did NOT shiver. -> {neg=0.49342770475227504, pos=0.506572295247725}
    @BarbaraJones I love Amy Grant's music. I have met her too!   #music4good -> {neg=0.3577981651376147, pos=0.6422018348623854}
    &quot;Homie this shit is basic welcome to graduation!&quot; Listening to kanye's &quot;Graduation&quot; CD -> {neg=0.5897608448079511, pos=0.41023915519204884}
    "&quot;Our motive is not to live up to our self-worth -> {neg=0.5897608448079511, pos=0.41023915519204884}
    "@caseygotcher Drats! I forgot I'm running XP at the moment LOL! Will install after next WU finishes folding.  Can't wait -> {neg=0.6665864227250843, pos=0.3334135772749157}
    @AngryBritain hope you have a good #moanmoday and ...........Hi -> {neg=0.45404157043879906, pos=0.5459584295612009}
    "@AshleaRose Um -> {neg=0.4057368075322969, pos=0.5942631924677031}
    @_Kathi  Checking in on you again   Still quiet in your house or are you feeling better? -> {neg=0.3870491457498418, pos=0.6129508542501582}
    @aristeia @lovelamps Oh good! This is working out fine! -> {neg=0.3870491457498418, pos=0.6129508542501582}
    @Catnboots How exciting! What a lovely thing to have to look forward to! -> {neg=0.3870491457498418, pos=0.6129508542501582}
    @AdiWriter where's the list? I wanna see -> {neg=0.49342770475227504, pos=0.506572295247725}
    *a big kiss to all my fellow thatters*  http://bit.ly/s6nIF -> {neg=0.23684210526315788, pos=0.7631578947368421}
    @antipov What inflation? Gasoline costs 47 cents per gallon here. -> {neg=0.3870491457498418, pos=0.6129508542501582}
    "@Bigdoggpinc I read that. No bad karma -> {neg=0.6073045578025958, pos=0.39269544219740415}
    @andyclemmensen haha well then maybe u shouldn't be so weak  hehe jks  xx -> {neg=0.5897608448079511, pos=0.41023915519204884}
    @aniterzayan haha thaaaanks -> {neg=0.3998855835240275, pos=0.6001144164759725}
    @AdviceGirl07 I need some serious advice. Will you follow me so I can send you a direct message? -> {neg=0.4196588868940754, pos=0.5803411131059246}
    @ALiiStack yea aye ALii we cant tell anyone our little secret it took us a LONG time to find that and now we have it  ;d -> {neg=0.5897608448079511, pos=0.41023915519204884}
    @anotorias you two crack me up. -> {neg=0.407557354925776, pos=0.5924426450742241}
    "@Amandastic hey -> {neg=0.4057368075322969, pos=0.5942631924677031}
    "@alyssabarlow I love that movie. &quot;can you pass the peas -> {neg=0.3094812164579606, pos=0.6905187835420393}
    @caitymareee you smell -> {neg=0.49342770475227504, pos=0.506572295247725}
    @AyeJayy yeeeeah! well they do lol. I mix -> {neg=0.6073045578025958, pos=0.39269544219740415}
    "@carolynwebb no probs   Yep -> {neg=0.14285714285714285, pos=0.8571428571428571}
    "@ClaireBoyles Excellent -> {neg=0.4057368075322969, pos=0.5942631924677031}
    Pretty sure nothing can foul my mood now. -> {neg=0.49342770475227504, pos=0.506572295247725}
    @bigbellywoman And very nice they are too -> {neg=0.33847763536489667, pos=0.6615223646351033}
    @arnoworld  ha oui il commence -> {neg=0.33847763536489667, pos=0.6615223646351033}
    @animecase Ð? Ñ?Ñ‚Ð¾ Ñ‚Ð¾Ð¶Ðµ Ð¾Ñ‡ÐµÐ½ÑŒ ÐºÐ»Ð°Ñ?Ñ?Ð½Ð¾. ÐšÐ¾Ñ‚Ð¸Ðº-Ð¼ÑƒÑ€ÐºÐ¾Ñ‚Ð¸Ðº -> {neg=0.407557354925776, pos=0.5924426450742241}
    "@adebradley Fab  Fairly large queues last year -> {neg=0.3828264758497317, pos=0.6171735241502684}
    "@aplusk You can have everything you want in life -> {neg=0.3870491457498418, pos=0.6129508542501582}
    "@Angela72 I'm not working Wed -> {neg=0.6349083397882779, pos=0.3650916602117222}
    @altpressLA my friends are getting pissed at me cus i keep asking them to follow you  i want the poster THAT bad. -> {neg=0.6250962278675904, pos=0.37490377213240955}
    @Auronsphere haha wla man gd ko team in particular.. at least mkasabay lng sa sturya -> {neg=0.5897608448079511, pos=0.41023915519204884}
    @aappa okay. I'll do that.  thnx for reminding me! xx -> {neg=0.414621932899349, pos=0.585378067100651}
    @alaniacovington we're great! I start grad school in July... moving the Beantown... -> {neg=0.5897608448079511, pos=0.41023915519204884}
    @aparna_s Too good. One of the few pages I read completely -> {neg=0.6073045578025958, pos=0.39269544219740415}
    "@babyboomerbev - lol - yes -> {neg=0.4057368075322969, pos=0.5942631924677031}
    @Celticgirl1913   Anything for some company -> {neg=0.49342770475227504, pos=0.506572295247725}
    @breatheheavycom jooooordan happy birthdaaaay  enjoy the day and party a loot ! -> {neg=0.3870491457498418, pos=0.6129508542501582}
    @alancfrancis I'm coming. Should be fun -> {neg=0.414621932899349, pos=0.585378067100651}
    @anib have a great day -> {neg=0.3828264758497317, pos=0.6171735241502684}
    @aplusk Yea But So Are The Girls -> {neg=0.3870491457498418, pos=0.6129508542501582}
    @awesomandias mom i mean -> {neg=0.33847763536489667, pos=0.6615223646351033}
    @AndreaaaB lol! have u read those books? she says they're great probably b/c they're a/b vampires!  x -> {neg=0.5897608448079511, pos=0.41023915519204884}
    @AlbertManjarrez loved it. Bet u will too -> {neg=0.3870491457498418, pos=0.6129508542501582}
    @cammyjo Tell me about it! What is obvious in real life makes for great entertainment on TV  It is great to try and figure out the killer -> {neg=0.5063856960408685, pos=0.49361430395913153}
    @Allen_Freed @madison_mae hahaha  either way!!! HAHAHAHAHAHA... FAIL. -> {neg=0.45404157043879906, pos=0.5459584295612009}
    @anz_rocks19 lol Did the yoghurt help at all? -> {neg=0.3870491457498418, pos=0.6129508542501582}
    "&quot;do the hellen keller -> {neg=0.4057368075322969, pos=0.5942631924677031}
    &quot;don't settle for someone who only buys you coffee. go for someone who makes you one.&quot; yiiee! -> {neg=0.4196588868940754, pos=0.5803411131059246}
    @BrookeOHC we have faith in you!! -> {neg=0.33847763536489667, pos=0.6615223646351033}
    @ambroseray I really like spending time with Jimmy. -> {neg=0.49342770475227504, pos=0.506572295247725}
    @alegrya that is how i feel about dishes now that i have a dishwasher -> {neg=0.6349083397882779, pos=0.3650916602117222}
    #GoodSex Only Happens When Your With Me... Sorry Im Taking?!?!?! -> {neg=0.45404157043879906, pos=0.5459584295612009}
    @AlexAllTimeLow urgh please do that at cttttttttt. in store!! -> {neg=0.5897608448079511, pos=0.41023915519204884}
    "@Ben_Hall I don't have to say don't drive unless you are sure you are sober -> {neg=0.4196588868940754, pos=0.5803411131059246}
    @aenneking bahahah that's what I'm watching -> {neg=0.5063856960408685, pos=0.49361430395913153}
    "@airmo 9.99  I thought it would be 14.99 but 9.99 is a steal -> {neg=0.5897608448079511, pos=0.41023915519204884}
    @annaliese_sarah damn we could have serched for a dora costume *cough cough* in the city haha -> {neg=0.49342770475227504, pos=0.506572295247725}
    @brushfire  Couldn't send a direct message since you are not following me. Your package will arrive tomorrow. Will Jack be there? -> {neg=0.4196588868940754, pos=0.5803411131059246}
    **Entity - http://www.mediafire.com/file/dhr0vmdmqgq/Entity.mp3 Go to this link now to hear my progressive house mix! Cheers -> {neg=0.5267538644470868, pos=0.47324613555291317}
    "@BrodyJenner Men R spoiled by women -> {neg=0.49342770475227504, pos=... and 104566 more bytes
```

Code from [TrieDemo.java:394](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L394) executed in 0.04 seconds: 
```java
      return tweetsNegative.stream().skip(trainingSize).map(x->x.getText()).mapToDouble(str->{
          Map<String, Double> prob = rule.apply(str);
          System.out.println(String.format("%s -> %s", str, prob));
          return prob.getOrDefault("neg", 0.0) < 0.5 ? 0.0 : 1.0;
      }).average().getAsDouble();
```
Returns: 
```
    0.545
```
Logging: 
```
    @_nataliee lol it is indeed. But this woman who works at the ricoh sed they've all bin told they're &quot;not allowed to approach them&quot;  Lol. -> {neg=0.5897608448079511, pos=0.41023915519204884}
    "@acepero79 Yeah -> {neg=0.4057368075322969, pos=0.5942631924677031}
    "@ddlovato Like -> {neg=0.9851088201603666, pos=0.014891179839633447}
    @andy_nash It should be great. But Thin Lizzy have dropped out as support. It's The Subways now -> {neg=0.5897608448079511, pos=0.41023915519204884}
    @ChocoboDancer YAY! I just started my next one last night. Got the first purple stripe done but I'm out of beige. -> {neg=0.5897608448079511, pos=0.41023915519204884}
    ;; Ms. Garcia.  NO CROSSING OF LEGS. :| -> {neg=0.407557354925776, pos=0.5924426450742241}
    @camelthecynic lol were weird that's why. I'm bored -> {neg=0.414621932899349, pos=0.585378067100651}
    "@Costus I envy you energy for a drink. All I did was eat dimsum -> {neg=0.3094812164579606, pos=0.6905187835420393}
    "#fail #sony http://9pe2w.tk &quot;VidZone - Free streaming music videos coming soon to PLAYSTATIONÂ®3&quot; schÃ¶n wÃ¤re es -> {neg=0.49342770475227504, pos=0.506572295247725}
    ..ok ppl keep writing me about designing their websites at my @FuriousStudios ???? READ THE FOLLOWING TWEETS -> {neg=0.5063856960408685, pos=0.49361430395913153}
    @ChiefHava after Ike is when we found ur blog then ur Twitter account. Want to follow the store rebuild -> {neg=0.5897608448079511, pos=0.41023915519204884}
    @bridog0 I can't sub to enter! I don't have a YouTube account! -> {neg=0.5897608448079511, pos=0.41023915519204884}
    @daverosin OMG that is Horrible! Sorry guys! -> {neg=0.995627732667083, pos=0.004372267332916927}
    "@DavidGuison 3K for a set -> {neg=0.49342770475227504, pos=0.506572295247725}
    @aacerr whaaa ? with who ? -> {neg=0.33847763536489667, pos=0.6615223646351033}
    @christinebohan I wanted to use your brilliant hashtag but Twitter wouldn't allow it -> {neg=0.6665864227250843, pos=0.3334135772749157}
    "@bombDUH Me either -> {neg=0.4057368075322969, pos=0.5942631924677031}
    "@annaiskicking geez anna -> {neg=0.4057368075322969, pos=0.5942631924677031}
    Phils.  What are you DOING to me???  I can't take these extra inning games and then you loose.  UGH.  Let's get back on track tomorrow. -> {neg=0.6665864227250843, pos=0.3334135772749157}
    @amandaODT i cant see the pencil -> {neg=0.5897608448079511, pos=0.41023915519204884}
    @aikyo87 the first step was just a brief introduction bout HNMUN and applying CV. Maybe my CV was not good enough -> {neg=0.5897608448079511, pos=0.41023915519204884}
    @a_c81 i know my mate picked the wrong wkd to marry in sooo many ways. Missed CC twitters -> {neg=0.49342770475227504, pos=0.506572295247725}
    @Akaasia *shakes fist angrily* want to be in Helsinki too  LOL hope you have a lovely time -> {neg=0.3870491457498418, pos=0.6129508542501582}
    No more FOX for me. -> {neg=0.407557354925776, pos=0.5924426450742241}
    "@DebbieFletcher tom will win it -> {neg=1.0, pos=0.0}
    @AsukaBlossoms *points to @SableSnow * I didn't do anything wrong and he is acting really mad at me -> {neg=0.6665864227250843, pos=0.3334135772749157}
    @bradiewebbstack thats not fair  come to mi party to make up for it..? lol -> {neg=0.6349083397882779, pos=0.3650916602117222}
    (@geeklimit) Note to self: Make sure tomato and mozzarella sandwich was refrigerated before eating it a day later. -> {neg=0.5897608448079511, pos=0.41023915519204884}
    @Bonnie311 you didn't twitter any pics  take your kid? -> {neg=0.6665864227250843, pos=0.3334135772749157}
    @Cupcake3286 don't hmmm me love!  whatcha doing tonight? -> {neg=0.6665864227250843, pos=0.3334135772749157}
    @DanMerriweather have you got an album release date? I'm practically in tears having to listen to just the singles -> {neg=0.4196588868940754, pos=0.5803411131059246}
    is cold -> {neg=0.33847763536489667, pos=0.6615223646351033}
    "@bigedude33 Aww -> {neg=0.4057368075322969, pos=0.5942631924677031}
    "@BeautifulPlague HAHA no unfortunately i was writing a paper  Are you working on Tuesday? If so -> {neg=0.49342770475227504, pos=0.506572295247725}
    "@beauty_devine9 what's Good -> {neg=0.4057368075322969, pos=0.5942631924677031}
    ...blood work makes me weak  there goes my day... -> {neg=0.45404157043879906, pos=0.5459584295612009}
    @chicitysoulstar hey snuckums!!!! SOoooooo I won't be going to Cali in August -> {neg=0.5897608448079511, pos=0.41023915519204884}
    :O fucking god how did yasmena win she was well shit compared to kate... that sucks donkey bollocks  upset now -> {neg=0.5897608448079511, pos=0.41023915519204884}
    "..with @trishaynte and the others  haha oh -> {neg=0.45404157043879906, pos=0.5459584295612009}
    "@DCrocks88 Believe me -> {neg=1.0, pos=0.0}
    @brunamedeiros heeeeey baby ? where are you? -> {neg=0.33847763536489667, pos=0.6615223646351033}
    "@big_blue_wolf btw -> {neg=0.4057368075322969, pos=0.5942631924677031}
    "&quot;Then Jesus said -> {neg=0.49342770475227504, pos=0.506572295247725}
    @ArielAyn I'm sorry. I know how you feel. -> {neg=0.49342770475227504, pos=0.506572295247725}
    @dapsiua5 Good morning! I can't get the link to work - -> {neg=0.995627732667083, pos=0.004372267332916927}
    @AngelaVampire  i know same here weekends are so boreing anymore -> {neg=0.49342770475227504, pos=0.506572295247725}
    ".@etherjammer but yeah  I don't read the site -> {neg=0.5897608448079511, pos=0.41023915519204884}
    @bree_bear  im sowee -> {neg=0.49342770475227504, pos=0.506572295247725}
    @aileenburns I want a kindle so bad. Can't get them in Australia though -> {neg=0.5897608448079511, pos=0.41023915519204884}
    "@blairerwin nah but if it does im running away. no spy window -> {neg=0.5897608448079511, pos=0.41023915519204884}
    @ahalove BUT I HATE MEEBO. -> {neg=0.6073045578025958, pos=0.39269544219740415}
    "@come2drum I had an iriver for years it was wonderful -> {neg=0.5063856960408685, pos=0.49361430395913153}
    @bethie138 urgh  u r wack and deserve a @jonathanrknight lashing!!!!!!!! -> {neg=0.3870491457498418, pos=0.6129508542501582}
    @annzoo I'm always sleeping now. Why were you up all night?! This reminds me of the one garage incident. I know you love my frantic IMs. -> {neg=0.49342770475227504, pos=0.506572295247725}
    @ the_lions_mane  i know! Do you have your phone back yet?! -> {neg=0.33847763536489667, pos=0.6615223646351033}
    @banwashere This is not encouraging to my soul. -> {neg=0.5897608448079511, pos=0.41023915519204884}
    @allie2590 not at all. when i punched out it was like 9:20 -> {neg=0.6349083397882779, pos=0.3650916602117222}
    "@audreygiselle why was youre day shit?  and yeah im just thinking about the usual junk i think about -> {neg=0.4196588868940754, pos=0.5803411131059246}
    ....why is he always in my dreams......... -> {neg=0.45404157043879906, pos=0.5459584295612009}
    " Dear all -> {neg=0.4057368075322969, pos=0.5942631924677031}
    "@clanravencub LOL -> {neg=0.4057368075322969, pos=0.5942631924677031}
    @bellabellini i dont have highspeed till next week so im getting online on my aircard and the bandwidth is a bitch. So ill have too wait -> {neg=0.5897608448079511, pos=0.41023915519204884}
    @arlisa not yet  i'll try to get it this weekend. -> {neg=0.6349083397882779, pos=0.3650916602117222}
    @AShakur No  need to give it more time. I'm going for a walk today at lunch! Gotta celebrate somehow! -> {neg=0.5897608448079511, pos=0.41023915519204884}
    "@anetteTHErocket I would love to! I did a write-up on it and it looks good -> {neg=0.666232073011734, pos=0.33376792698826596}
    "@ddlovato sorry for my TERRIBLE writing in the last post -> {neg=0.995627732667083, pos=0.004372267332916927}
    @Dannymcfly where's dougieee???? is he ok??? does he haves the swine flu!?!? is he dead!?!? hope he's ok.. -> {neg=0.49342770475227504, pos=0.506572295247725}
    @BelleNoirMag I didn't see you Saturday -> {neg=0.4196588868940754, pos=0.5803411131059246}
    @ Knightpkf You got drunk without me? -> {neg=0.3870491457498418, pos=0.6129508542501582}
    "@chazdrums you can use links from mySMS in inbox/outbox sections -> {neg=0.49342770475227504, pos=0.506572295247725}
    @aeniman I wish i could've gone to cali  lol -> {neg=0.6073045578025958, pos=0.39269544219740415}
    @anarbor @anarbormike @anarboradam @anarborgreg @anarborslade i can't go tonight  let me know if you guys are doing something after -> {neg=0.4196588868940754, pos=0.5803411131059246}
    "@Dan_Schwartz HOH???  I think you added me due to my stunning personality!  But seriously -> {neg=0.4196588868940754, pos=0.5803411131059246}
    @alliecine @kimberlyprendez @robgokeemusic are the 3 of you hanging out without me  sad day!! -> {neg=0.9464459591041869, pos=0.053554040895813046}
    "@bitybella i'd say cook yourself spaghetti and watch the mets game. you win! btw mare -> {neg=0.49342770475227504, pos=0.506572295247725}
    "@Bgfilly yeah -> {neg=0.4057368075322969, pos=0.5942631924677031}
    @chicagored77 hey sweetie!! Monday again -> {neg=0.49342770475227504, pos=0.506572295247725}
    "@bliberty Oh my God - me too! Terrible writing -> {neg=0.4057368075322969, pos=0.5942631924677031}
    @autumnlovechild me too..and where is my plate -> {neg=0.45404157043879906, pos=0.5459584295612009}
    &quot;weblivz i guess son trumps over daughter in law -> {neg=0.49342770475227504, pos=0.506572295247725}
    @annemul this makes me sad -> {neg=0.49342770475227504, pos=0.506572295247725}
    @AbstractHomie @StarOnMaineSt I miss y'all -> {neg=0.8254716981132075, pos=0.17452830188679244}
    @BryanPerson my goodness I wish I could but business travel and Da duties preclude me going to #PAB2009. -> {neg=0.666232073011734, pos=0.33376792698826596}
    @Bucky_Diggs  We aren't Rome yet....but I agree the current trend is not a healthy one. -> {neg=0.6665864227250843, pos=0.3334135772749157}
    @ Caroline_Fonz  same here i hate the heat! it's especially hot where i live  it was i think 95 degrees on June 24th -> {neg=0.5897608448079511, pos=0.41023915519204884}
    @CarterpG2 You crazy? I cant handle going out during the week and then go to work -> {neg=0.5063856960408685, pos=0.49361430395913153}
    i am soooo sad...at this point of night i'd be cuddling with jonathan like i did for 4 years -> {neg=0.9464459591041869, pos=0.053554040895813046}
    @cessii I am.. but I'm leaving! -> {neg=0.5063856960408685, pos=0.49361430395913153}
    "@BritsOnPole Yeah -> {neg=0.4057368075322969, pos=0.5942631924677031}
    "@ChrisOlstrom and @danielstroud -> {neg=0.4074074074074074, pos=0.5925925925925926}
    "@amberback Don't -> {neg=0.4057368075322969, pos=0.5942631924677031}
    @Ashleigh_Young i've been doing that to :L:L my sunburn kills in the shower though  xx -> {neg=0.5897608448079511, pos=0.41023915519204884}
    @angelmagno i want to watch gg season 3 too. -> {neg=0.5897608448079511, pos=0.41023915519204884}
    fantastic..it's probably cause of the messaging last night/morning.. -> {neg=0.3870491457498418, pos=0.6129508542501582}
    @blackknight7289 I know  I sad. -> {neg=0.49342770475227504, pos=0.506572295247725}
    okay. Imy -> {neg=0.407557354925776, pos=0.5924426450742241}
    "@cuallycatx That sure does...was it someone in your actual class -> {neg=0.4196588868940754, pos=0.5803411131059246}
    @Andrewrosenfeld well we barely talk so i wouldn't know -> {neg=0.5897608448079511, pos=0.41023915519204884}
    "#FacialHairFriday - This makes me so sad -> {neg=0.49342770475227504, pos=0.506572295247725}
    @COOLSEX its so pwetty... I want -> {neg=0.49342770475227504, pos=0.506572295247725}
    .....everything is always falling to pieces -> {neg=0.45404157043879906, pos=0.5459584295612009}
    @crdbl I'M NOT COCKY. -> {neg=0.407557354925776, pos=0.5924426450742241}
    "@Bwash729 starvation is the only way! that's how i did it! lol -> {neg=0.49342770475227504, pos=0.506572295247725}
    @AdamSchwabe My heart goes out to you Adam.  I had to put down my lovable 3.5 yr old Rotti named Jarvis due to Cancer.  Worst.Day.Ever. -> {neg=0.3094812164579606, pos=0.6905187835420393}
    @alexalltimelow I didn't even get to see you -> {neg=0.4196588868940754, pos=0.5803411131059246}
    &quot;now back again studying for the algebra regents&quot; -> {neg=0.49342770475227504, pos=0.506572295247725}
    @BestJustin that's sad -> {neg=0.49342770475227504, pos=0.506572295247725}
    "@admon09 LOL yea -> {neg=0.4057368075322969, pos=0.5942631924677031}
    "@deepbluesealove oh -> {neg=0.9851088201603666, pos=0.014891179839633447}
    "@2HotnNotBotherd hey omg ur online  i have news and i missed yu on msn -> {neg=0.4057368075322969, pos=0.5942631924677031}
    @bamama Yeah i didn't get it til almost 10  thanks for doing that. I'll pay ya back. Way for you winning a prize. -> {neg=0.6665864227250843, pos=0.3334135772749157}
    @berthalicia aww porque? -> {neg=0.33847763536489667, pos=0.6615223646351033}
    @bestieblondie7 i haven't even been able to nap yet  i get really bummed out when i'm overtired -> {neg=0.6665864227250843, pos=0.3334135772749157}
    @chevell I COULD use my nextG phone as a datamodem.... but my cap doesnt include data on that phone... BLAST... my ADSL is throttled -> {neg=0.5063856960408685, pos=0.49361430395913153}
    " Just found out the school blocks out some websites -> {neg=0.5897608448079511, pos=0.41023915519204884}
    @BrookeAdamsTBG5 come to ohio even tho it sucks  i miss tbg5 !! -> {neg=0.5897608448079511, pos=0.41023915519204884}
    @Cupcakeqween i texted u no reply? -> {neg=0.7668393782383419, pos=0.23316062176165803}
    @amycarr92 arent you going to jacquis?? hope your feeeling okay chooken  get well soon for monday i miss yo nigga ass! xoxoxo -> {neg=0.4196588868940754, pos=0.5803411131059246}
    @AndrewBlanda think i'd better change my rsvp to a 'maybe'   My head feels like it's going to explode. -> {neg=0.414621932899349, pos=0.585378067100651}
    "@dbferguson Sorry -> {neg=1.0, pos=0.0}
    @BrookeyBabeh yeahh i saw that! how annoying aye -> {neg=0.49342770475227504, pos=0.506572295247725}
    "@ChrissMari I'm glad I'm missing it. Who's he playing -> {neg=0.414621932899349, pos=0.585378067100651}
    @chollis awww! i'm sorry  i'll have 2 or 3 of whatever you want for you! -> {neg=0.9333333333333333, pos=0.06666666666666667}
    woke up early to wash and dry my tie dye shirt...the stupid thing is still wet. =[ FML. -> {neg=0.49342770475227504, pos=0.506572295247725}
    #mydadpwns because he loves my music! he is my #1 fan... well my mom is too... i wish they were together...  FML! -> {neg=0.45404157043879906, pos=0.5459584295612009}
    Boring English Coursework. -> {neg=0.414621932899349, pos=0.585378067100651}
    "#F2 Actual overtaking happening!! Oh no -> {neg=0.864, pos=0.136}
    @debskittles i always laugh at my own puns. no one else seems to get them. -> {neg=0.995627732667083, pos=0.004372267332916927}
    @Bern_morley Poor Iz.  The big fella had talent. -> {neg=0.414621932899349, pos=0.585378067100651}
    @ChaosBlue Damn! Already have plans   Another time -> {neg=0.33847763536489667, pos=0.6615223646351033}
    @cbcim awwww dang! I missed my morning cuddle  lol -> {neg=0.6073045578025958, pos=0.39269544219740415}
    @brettflix Stella and I have the whole bed to ourselves... and we hate it!  Come crowd us! -> {neg=0.6073045578025958, pos=0.39269544219740415}
    I'm being threat'ned to attend bw3 tonight! -> {neg=0.33847763536489667, pos=0.6615223646351033}
    "@ArchanaS guess its all coz of delhis weather -> {neg=0.4057368075322969, pos=0.5942631924677031}
    @benkowalewicz hm... you don't reply one time -> {neg=0.6665864227250843, pos=0.3334135772749157}
    ...still disappointed that Adam didn't win.   His rendition of &quot;Mad World&quot; is so haunting.  I just listened on uTube...love it! -> {neg=0.5897608448079511, pos=0.41023915519204884}
    "#FF to my favorite restaurant -> {neg=0.4057368075322969, pos=0.5942631924677031}
    "@davesusetty i'm not a violent person but this -> {neg=0.995627732667083, pos=0.004372267332916927}
    "@berydiana - N... and 105583 more bytes
```

