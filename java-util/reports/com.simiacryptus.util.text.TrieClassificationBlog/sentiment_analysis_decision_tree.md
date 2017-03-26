# Sentiment Analysis inference using a Decision Tree
First, we load positive and negative sentiment tweets into two seperate models

Code from [TrieClassificationBlog.java:146](../../src/test/java/com/simiacryptus/util/text/TrieClassificationBlog.java#L146) executed in 3.97 seconds: 
```java
      ArrayList<TweetSentiment> list = new ArrayList<>(TweetSentiment.load()
              .filter(x -> x.category == 1).limit(testingSize + trainingSize).collect(Collectors.toList()));
      Collections.shuffle(list);
      return list;
```

Returns: 
```
    [TweetSentiment{title='@carthart thanks'}, TweetSentiment{title='@a_ashie ASHIEEEEE-UNNIE!!!! annyeong haseyo!!  Selamat bertweety di Twitter~'}, TweetSentiment{title='@crustyjuggler72 @rhinoman @uppercanuck like something they left on the cutting room floor from  Coccoon 4'}, TweetSentiment{title='@_CrC_ TALK TO ME NOW DEAR?'}, TweetSentiment{title='"@botdfmusic lol'}, TweetSentiment{title='#nba lebron - 1 on 5 - that's what we like'}, TweetSentiment{title='@bermudaonion good things come to those who wait!     #readathon'}, TweetSentiment{title='@aplusk if the gift for friend then i would say a gift certificate to where they shop let them pick what they want you get the credit'}, TweetSentiment{title='"@AnoopDoggDesai hope you enjoy DC! you'll love it here'}, TweetSentiment{title='"@braindouche Ok'}, TweetSentiment{title='@_SandBox it's working with the police in the major crimes and terrorism unit'}, TweetSentiment{title='@conrey just emailed you. Let me know if you're interested'}, TweetSentiment{title='&quot;Think Different!&quot; - Ð?Ðµ Ð·Ð²ÑƒÑ‡Ð¸ Ð»Ð¸ Ð½Ñ?ÐºÐ°Ðºâ€¦ ÐšÑ€Ð°Ñ?Ð¸Ð²Ð¾? (via shadowden) Ð?Ð•!  Ð?Ðµ Ðµ Ð»Ð¸ Ñ?Ð¸ â€œIâ€™m a PC and Iâ€™m 4 and a... http://tumblr.com/xvz1yzprr'}, TweetSentiment{title='@Britt_Kims_Mom  have a good lie down'}, TweetSentiment{title='&quot;A freak like me needs infinity&quot; *Hqppy Birthday* 2 me'}, TweetSentiment{title='@BellaAngelPhoto Thanks for the follow Friday luvin'!'}, TweetSentiment{title='" @khead I'll tell ya'}, TweetSentiment{title='@croqzine snagged your zine.  Thanks and looking forward to it!'}, TweetSentiment{title='@ArtRevel  Yup Great film and yes Marisa Tomei  is hot'}, TweetSentiment{title='@1capplegate and is it any better?'}, TweetSentiment{title='"@alishaxVAMP: welll'}, TweetSentiment{title='@alethe so souq is like bat-ha here?'}, TweetSentiment{title='"&quot;With me the poetry was not a purpose'}, TweetSentiment{title='@coldplay The concert was amazing!!!! ty tsoo much for one of the best nights of my life!!!!'}, TweetSentiment{title='bout to party in a tent'}, TweetSentiment{title='I have already completed work on &quot;mechanisms&quot; that boredom ... Now I have to study the map of Africa! tomorrow..BASEBALL! its SO cool'}, TweetSentiment{title='@AdamGoldston I need fun followers. you know you want to pick up a random Alabama southern twitter! it'd make my day.'}, TweetSentiment{title='@BrwnEyesLie i predict a laker choke.....sawrry'}, TweetSentiment{title='(@mdotporter) Morning everybody! Still in bed! HIGH FIVE! Sooo glad we don't open till noon today! I refuse to get up until 11'}, TweetSentiment{title='@barnsleysime depends on the scar'}, TweetSentiment{title='- Sitting ion the garden with Hayley drinking Cider....lovely relax after yesterday...my first day tomorrow.....ohhhhhh.....'}, TweetSentiment{title='@Celz29 well i get paid tonight so thats gonna go on the DVD tomorrow and a PE of my own'}, TweetSentiment{title='@blfarris - and you just proved you're on an iPhone.   (need=nerd)'}, TweetSentiment{title='@cardiodoctor2be somebody sounds bitter'}, TweetSentiment{title='"@angelopoulos ???? ??? ???? (Eurovision'}, TweetSentiment{title='@charlotteanimo You coming tonight?'}, TweetSentiment{title='@aritra_m  its for today #bongodibosh #twitterbongs its for fun'}, TweetSentiment{title='@AndyBursh Whitby is about an hours drive from Middlesbrough.'}, TweetSentiment{title='@arokk haha! We also have Twilight'}, TweetSentiment{title='"@Bean525 @katanasquirrel hey guys  Bean like your tweets man'}, TweetSentiment{title='"@aniero yeah... I do'}, TweetSentiment{title='@AmmerieRain *txt*  We will be home before you know it.    And you may hug me then........(What about Olivier? *Giggling*)'}, TweetSentiment{title='@Armediharahap Cause the 12th classes do their Abitur. so all the teacher have to controll the people!'}, TweetSentiment{title='&quot;The Beatles: Rock Band&quot; me pide rollo'}, TweetSentiment{title='#AbortionTuesday ?? Will it catch on? 1 abortion please'}, TweetSentiment{title='@aboutthechase Do you have the plug-in activated? Happens to me too'}, TweetSentiment{title='. @BlackSwanImages I will be all day on Thursday and trying to catch the last talk on friday'}, TweetSentiment{title='@charm_next_door goodnight!'}, TweetSentiment{title='@_Starlight_ Niiice!  Want company? ;)'}, TweetSentiment{title='@adamariee meee tooo !  its so nice out.. for now anyways. its suppose to rain later ! ah !'}, TweetSentiment{title='@becca_roo you're welcome.'}, TweetSentiment{title='"@chanc Thanks Chris'}, TweetSentiment{title='@amp451 Wow your up with the birds! It's Sunday'}, TweetSentiment{title='@angelquilter Just read your email - love I can lay it on with a trowl when I get on a roll'}, TweetSentiment{title='@Adoser1986: If you had hung out with me you wouldve been drunken off happiness... sisters have that effect.'}, TweetSentiment{title='@crazytwism  there ...'}, TweetSentiment{title='@Brayds i want to see killer animals and snakes and spiders all of the cool stuff outdoors'}, TweetSentiment{title='@30SECONDSTOMARS sounds exactly like something I need to hear...now'}, TweetSentiment{title='- Finally got twitter.... trynna see what the hype is about'}, TweetSentiment{title='@bbillybilltweet 30 years'}, TweetSentiment{title='"@bonniegrrl Thanks for the tip'}, TweetSentiment{title='#WhyITweet coz i can'}, TweetSentiment{title='*high fives @originalbutters*'}, TweetSentiment{title='@_CINNA_ ben chillin all day'}, TweetSentiment{title='@angelaxjonas ah well I hope it works out where ever we go.  aww thanks bestiee I love them 2! did u see my gabe pic? LOL'}, TweetSentiment{title='"@ChellularBelle hey'}, TweetSentiment{title='@angesbiz Will do! Hopefully next week? I really want to get a lot of blogging done before the tweetup so I have something to talk about'}, TweetSentiment{title='@Aloushka Check out the techno era Ecigarette. Get paid to reduce your tobacco use  http://bit.ly/15g0H4 It is totally cool'}, TweetSentiment{title='@anyidiot Say maybe I can send 'em on your way when they finish mowing at their ma's place.'}, TweetSentiment{title='@cyphersushi So you went swimming anyway? Very good of you.'}, TweetSentiment{title='&quot;Slow is always better...Trust me cuz I'm the DR.&quot; -Dr. Dre in the new Dr. Pepper commercial...SMH super corny!'}, TweetSentiment{title='"@bliumchik here's little encouragement: 'aw'}, TweetSentiment{title='@chacha102 Get 100 followers a day using www.tweeteradder.com Once you add everyone you are on the train or pay vip'}, TweetSentiment{title='"@alyankovic will you have a polka on the new album? I hope so'}, TweetSentiment{title='@appletizzer so how was ur meeting w/ ur &quot;wee man&quot;?'}, TweetSentiment{title='"@cloudbreaking Same'}, TweetSentiment{title='@boatsXandXbirds haha shiny purple'}, TweetSentiment{title='@Alyssa_Milano ur fruit comment took me back to the movie CITY OF ANGELS..aaaaw'}, TweetSentiment{title='@boejay done is better than perfect--slam it out and revise it later (for perfection!)'}, TweetSentiment{title='&quot;Morning has broken..&quot; *ggg* I know it's early but I'm in a great mood today.. Made a cake for us'}, TweetSentiment{title='@BuzzEdition how was your weekend?'}, TweetSentiment{title='@CrunchyK I heard about that this morning 200+ people sooo sad! I can't even imagine! Just terrible tragedy!  Andrea'}, TweetSentiment{title='@cherrichiodo ur silly'}, TweetSentiment{title='#3wordsaftersex: &quot;i cant move&quot; thats when its that good good'}, TweetSentiment{title='@ChicagoBites Muis importante.  Frosting must be HUGE!!!'}, TweetSentiment{title='@BakaDaruku yeah... I was simply appealing to your ego'}, TweetSentiment{title='@abs1399 oh stop it!! I think you look super cute'}, TweetSentiment{title='"@commonsense4 sounds like a long term study!  good for you'}, TweetSentiment{title='@artrox hehe...glad I could give you a giggle. It goes with your avatar!'}, TweetSentiment{title='&quot;Meditation is the highest form of yoga.&quot; Does that mean I don't need to do any bendy exercises?'}, TweetSentiment{title='in love with some guy who lives in miami... Fail!'}, TweetSentiment{title='"@BigCityDiner hey bro'}, TweetSentiment{title='@brainstuck Right on Gurgaon highway - its actually nuts'}, TweetSentiment{title='"@CarolRiddickRDU They are 3 and 5 years. Such a beautiful age'}, TweetSentiment{title='@brentkeith I see you running around BB Kings. Can't wait for you to take the stage.'}, TweetSentiment{title='.@nik_kee_dee hahaha wtf was that???  #noundiessunday'}, TweetSentiment{title='@amandapalmer YAY for Park Slope!  Welcome to my home!!!'}, TweetSentiment{title='@cariocastv Stylish soap; lots of sexy people; now we just need a version dubbed in english!  Keep up the good work... Bruno'}, TweetSentiment{title='"@AngelStrange Take good care'}, TweetSentiment{title='I adore leather corsets.'}, TweetSentiment{title='@andrewmeyer1 follow me back!!'}, TweetSentiment{title='@Aurelie88 His arms are fabulous in that movie!'}, TweetSentiment{title='@chrisoakley Keep the Kodak Carousel in your trousers chum  Are you coming down the pub tomorrow?'}, TweetSentiment{title='@buckhollywood happy birthday.'}, TweetSentiment{title='"#Dialogue #Trivia - If we pull this off'}, TweetSentiment{title='@blissneso i voted 10 of your songs'}, TweetSentiment{title='"@AdamGoldston My site is gonna be kinda like Perez'}, TweetSentiment{title='@amberglidden oh thanx  AH and I am gonna have to get you something while I am there hehe'}, TweetSentiment{title='@614grapevine Can't wait to give Jeni's icecream a try next time Im in Columbus'}, TweetSentiment{title='@ashstahl you SO have to come hang in philly!!!! We can find you a stalker'}, TweetSentiment{title='@bifflawson LoL... The lion king!  Rooaarrrrrr'}, TweetSentiment{title='@chr1sa You got the +1k in the last week because you got listed under top 25 ppl that should be followed on twitter http://bit.ly/YOnTa'}, TweetSentiment{title='@clayronk http://twitpic.com/7j5d5 - Ohhhh! I LIKE!'}, TweetSentiment{title='@abs1399 the after party was rockin'!  I had fun hanging out too!  I did abandon you guys but I got someone to go get the books!!'}, TweetSentiment{title='@ash_phillips am i seeing you at some point tonight?'}, TweetSentiment{title='@ccfinlay Gentlemen?  Thems fightin' words!'}, TweetSentiment{title='@CrypticFragment what exactly do you do? and where? up a mountain? big storm? you seem to have a very interesting day job.'}, TweetSentiment{title='@AustinOtaku Looking forward to seeing you! Tea service is availaber from 5pm to 9pm for your stay. Ask front desk when you check in'}, TweetSentiment{title='@amykant it was your idea!'}, TweetSentiment{title='"@barryfrangipane Are you and Debbie'}, TweetSentiment{title='@balaji_dutt btw that martenreed comment - awesome'}, TweetSentiment{title='@AnnCurry Morning'}, TweetSentiment{title='@brikhed there's a Le Peeps in Chicago?  I thought that was just a local place here in Texas.  Didn't know it was a chain.'}, TweetSentiment{title='@__Davros__ You're welcome.'}, TweetSentiment{title='@abckatesnow Welcome to Twitter. I really enjoy it. Big ABC morning fan. Im a wife and mom..twitter is my escape!  Gr8 tool for info!'}, TweetSentiment{title='&quot;Baruch Ha Shem Yahweh&quot; http://bit.ly/wecVC  THOSE WHO ARE _ KNOW WHO THEY ARE'}, TweetSentiment{title='... downloading apps for my iPHONE&lt;3 then mimis  shopping tomorrow saw some cute wedges that im buying.. ASAP&lt;3 haha'}, TweetSentiment{title='@colinnixon Much appreciated.'}, TweetSentiment{title='@Aprilraquel yeah me too. It's all good they probley were followers who never tweet anyways..'}, TweetSentiment{title='@cysero http://twitpic.com/6mdwb - Savage warlord armor is waaaaay cooler'}, TweetSentiment{title='"@Cult_of_Angels  lol nah'}, TweetSentiment{title='"@AudraSimmons Hey Audra'}, TweetSentiment{title='"@_Omitsu_ season 3'}, TweetSentiment{title='@a_llicsirp then people will laugh at us. Ready to rap today?'}, TweetSentiment{title='"@Crackerwax Unfortunately not! Since the interview I've been able to afford about two lessons. Thanks for asking'}, TweetSentiment{title='@amandabynes Hope you had fun Twistin to the oldies today.'}, TweetSentiment{title='@BigDaddy777p But don't worry.  I'll pray to Jesus Christ the true savior for your soul.'}, TweetSentiment{title='@Daily_Record well obviously the almighty GERSSSSS!!'}, TweetSentiment{title='@_DINA will be good to have you as a #BlackBerry user #followfriday'}, TweetSentiment{title='@cateringnyc Served your brisket on onions slider rolls to some VERY appreciative picnic-goers!  Didn't attribute though...'}, TweetSentiment{title='@Baratunde - good to see someone from the Comedy Studio here on Twitter.  It's like the Kvetch Board - only better'}, TweetSentiment{title='@BernardKeane Onion headlines:  I preferred &quot;HOLY FUCKING SHIT!&quot; in the issue after 9/11'}, TweetSentiment{title='@annissanns nice thought nis!'}, TweetSentiment{title='@Aftashok it will move..u gotta have faith'}, TweetSentiment{title='@aussieboby no working tomoz'}, TweetSentiment{title='@carisad keepin' my fingers crossed for vegas &amp; phx'}, TweetSentiment{title='@beckym1985 aw thats so sweet of you &amp; your mate to buy Laura a mcfly ticket  thank you'}, TweetSentiment{title='@bethan_elena Welcome to the club'}, TweetSentiment{title='@amykate I have a tweetup in Swindon on Weds &amp; am trying to keep my gallivanting to 1 night a week. Hope the rest of your shows go well'}, TweetSentiment{title='@cliffysmom Nice one! Good foal.'}, TweetSentiment{title='@_____jasmine Oh what a cutie ahhaha.  But Taylor Lautner is pretty hot hahaha. SELENA GOMEZ is lucky haha.'}, TweetSentiment{title='#Left 4 Dead 2 trailer?! AMAZING. 4 new survivors &amp; 5 new campaigns   http://tinyurl.com/nva76v'}, TweetSentiment{title='@binncheol northern lights!!'}, TweetSentiment{title='@BarelySeeAtAll Everyone on the Planet employs caution when Rufus ShinRa says &quot;hmmmmm....&quot;'}, TweetSentiment{title='@dalekurt yuh soon get tired of Pizza.... but enjoy while u can'}, TweetSentiment{title='@aimeeroo http://blip.fm/~6g113  so cute. I have to get some sleep..Sweet Rocking dreams tonight!'}, TweetSentiment{title='@bigorgantrio When is that date for St. Louis going to be announced for summer 2009?  Come on your killing me!!!!'}, TweetSentiment{title='@cookiebaby Get 100 followers a day using www.tweeterfollow.com Once you add everyone you are on the train or pay vip'}, TweetSentiment{title='@ada1276 I feel a lil better thanks.  How have u been btw?'}, TweetSentiment{title='#dollhouse finally sparked my interest. Alan Wash Tudyk FTW'}, TweetSentiment{title='@carrieann2424 bah. You are a fancy babysitter'}, TweetSentiment{title='@Dan_ Well perfect combination then (mgmt and sun). (: kinda rare in UK aint it'}, TweetSentiment{title='"@Azizshalan i'm fine too'}, TweetSentiment{title='"@amberscove ah'}, TweetSentiment{title='@a_glance_behind AWW Anna! -Huggles- Tom can warm you up  Haha. And oh gosh yes! We must meet up'}, TweetSentiment{title='@chelseyramos How Chelsey and I spend our Friday nights  http://twitpic.com/684u6'}, TweetSentiment{title='@Adlib311 I'm gonna make it happen bro!! yeah that was hella fun'}, TweetSentiment{title='@aznjesse hey monday'}, TweetSentiment{title='@Christinepo yes it totally did'}, TweetSentiment{title='@astruc Srsly?'}, TweetSentiment{title='"@aJayBoulder  haha'}, TweetSentiment{title='@CourtneyHale8 haha. Yes and yes  they're called 52 Flicks. look em up'}, TweetSentiment{title='@chaaantellexo i think im amazing too! you know i love you too! and im not jealous of you spitting so suck a dick love &lt;3'}, TweetSentiment{title='@_S0PHIE_ Which one? I had several.  Have you put any of your own up yet?'}, TweetSentiment{title='@cakey You're a star!'}, TweetSentiment{title='@BubblyHeart congratulations!!'}, TweetSentiment{title='@boomshard can I get a photoshoot'}, TweetSentiment{title='@1critic I'm so behind with any #TwitterBones updates! But I do like where Rottaboat is going'}, TweetSentiment{title='"@carissaaaa: I still think your car is cute'}, TweetSentiment{title='"@BigHappymonkey We're off to Sat morning pictures - odd'}, TweetSentiment{title='@cinemabizarre come to south america please  you have a lot of fans in chile'}, TweetSentiment{title='@ashleyltmsyf ....so you... and 5263803 more bytes
```
Code from [TrieClassificationBlog.java:152](../../src/test/java/com/simiacryptus/util/text/TrieClassificationBlog.java#L152) executed in 0.95 seconds: 
```java
      ArrayList<TweetSentiment> list = new ArrayList<>(TweetSentiment.load()
              .filter(x -> x.category == 0).limit(testingSize + trainingSize).collect(Collectors.toList()));
      Collections.shuffle(list);
      return list;
```

Returns: 
```
    [TweetSentiment{title='@alcoholism bb.  *hugs*'}, TweetSentiment{title='@ben_gordon LOL hot n windy and VERY sandy hee hee off for ice cream now n headin to the park very poor sig at beach'}, TweetSentiment{title='@andyclemmensen ive been trying to call you guys for half an hour'}, TweetSentiment{title='@clex_monkie89 That sucks  *hugs*'}, TweetSentiment{title='@BeachDreams50 :  You guys are having better luck on this than me that's for sure.'}, TweetSentiment{title='@chriseatworld yes n now nomore redbull in hk'}, TweetSentiment{title='is missing him already'}, TweetSentiment{title='"&quot;Username too short'}, TweetSentiment{title='@aloha_dakine is there any explicit reason why I don't receive any replies from you?'}, TweetSentiment{title=': Blah! I feel so alone and depressed.  Time for bed.'}, TweetSentiment{title='"@disc0fidget mate exams are badtimes'}, TweetSentiment{title='"@Armin_ASOT Aw'}, TweetSentiment{title='"@Anusual oh no'}, TweetSentiment{title='@DonnieWahlberg I was just in Vegas over Memorial weekend and walked passed Jet. I can't believe you are there 4 days later..........'}, TweetSentiment{title='@e_lee2008 ugh that aint fair get me in for free too! i dnt have $12  but im a send u my # on fb call me 2mrw cuz i really wanna go'}, TweetSentiment{title='"@BasseCopette building a brand new walk in shower'}, TweetSentiment{title='@BeckyC3 lol. i need a drink but if i move my parents will make me tidy my room'}, TweetSentiment{title='@amyypee dude how can u look forward to it...little kids are just effin hell'}, TweetSentiment{title='@caitlinbell18 why does nobody follow me'}, TweetSentiment{title='i just realized i miss 11:11 dangggg'}, TweetSentiment{title='@AllisonDinar its not showing up'}, TweetSentiment{title='@AGENTSANDRA007 I see that headed my way  arrrrgh'}, TweetSentiment{title='"@ArtyTheCat  Please take care of yourself'}, TweetSentiment{title='@BrentLauren yes ma'am'}, TweetSentiment{title='@Diji Getting feedback on Podcast segments sometimes is hard.  That and Blog-posts too. :-/'}, TweetSentiment{title='I FEEL SO..... REPLACED.'}, TweetSentiment{title='@Dappa_Dan shits public'}, TweetSentiment{title='@brittianyerin Haha nice nice. Oh dear Matt is dating someone. Frick everyone in hollywood has someone.  It's making me SUPER jealous.'}, TweetSentiment{title='@BaileyBoo93 Nowhere really.'}, TweetSentiment{title='@ChrisTwitery  it was a spam call lol'}, TweetSentiment{title='@Coop_ haha and I have no choice  fml...this girl..smh'}, TweetSentiment{title='@bibs4drips  i haven't even been in the chat lately! lets get back on track Edit!'}, TweetSentiment{title='@AnnaPorter  argh can we stop with all the Mafia tweets?! pleeeeeeeeeeeeeeeeeeeeeeeeeease'}, TweetSentiment{title='"@andrecs16 oh    Take it easy'}, TweetSentiment{title='.....headache'}, TweetSentiment{title='was promised sun today for my day off!'}, TweetSentiment{title='BOO. I always miss the good stuff!'}, TweetSentiment{title='@angeladee Yes. Yes I do. Wish you were closer.'}, TweetSentiment{title='needddds her boyfriend NOW'}, TweetSentiment{title='"@bcuban come on man'}, TweetSentiment{title='"@anarchy21 I refuse to watch'}, TweetSentiment{title='"@Cause4Conceit its wet outside so if I walk to come get u'}, TweetSentiment{title='"@AllThingsFresh Dang'}, TweetSentiment{title='@DwightHoward I wish that I could be in 5 places at once..would be less tired &amp; would'nt have to worry about people being mad at me'}, TweetSentiment{title='@averygoodyear I hate when that happens  I hope it's not too bad.'}, TweetSentiment{title='@chriswu the last game of my season pass this weekend'}, TweetSentiment{title='@DavidArchie Isn't lack of sleep bad for your voice? It kinda worries me.  You should start getting some good sleep. ;-)'}, TweetSentiment{title='@benjjamieson Your gallery for the wedding shoot is private'}, TweetSentiment{title='"@ashleytisdale Man'}, TweetSentiment{title='@canthelpmyself I know but I can't.  I'm supposed to be &quot;working&quot;. I've ignored 3 people so far because I didn't even notice them.'}, TweetSentiment{title='@blessedsister Wish I could get this in Germany!'}, TweetSentiment{title='@crochetgal That's too bad    Pattern :  http://bit.ly/rkkV7'}, TweetSentiment{title='.... Is Really Really Draaaaaaaaaaaained &amp; Numb after my Dear Fathers Funeral 2day!!    .  Cant sleep........'}, TweetSentiment{title='"@charlej47's going to Disney World'}, TweetSentiment{title='@CountRickTer and u didn't invite me?!'}, TweetSentiment{title='@AmberlinaM he was such a nerd in this movie'}, TweetSentiment{title='@BeaMarqz at least you know now.  #whatsmysection #whatsmysection #whatsmysection #whatsmysection #whatsmysection #whatsmysection'}, TweetSentiment{title='@clare666 aw Clare - thats usually me making those decsions....so hard to do'}, TweetSentiment{title='"@coolacid it will'}, TweetSentiment{title='@alishamarijuana you can read my twitter updates from it'}, TweetSentiment{title='".. van gogh 'starry night' magnet being stolen'}, TweetSentiment{title='@charlotteliz they are cuts but I don't know anyone with a size 4 foot  I'm a 6!'}, TweetSentiment{title='@brydiekennedy haha nah wasnt totally fucked up darn it! haha yeah me too im getting the flu'}, TweetSentiment{title='@daviexxjonas DAVIE!!!! i missed you  glad you're back!!'}, TweetSentiment{title='@alyshachandra hehehe me 2 nih lyshhhh @zanidia iya pake udang? Aku taunya cmn pake ayam gitu niiiiid @clarixxxa TPKEANYAGKJADI'}, TweetSentiment{title='@blackmamba23 *clutches pearls* lol but that sucks'}, TweetSentiment{title='@Attitude471 I was playing drunk monopoly...I lost'}, TweetSentiment{title='@_KateDenali_ yeah me too  i only saw the last 5 minutes of the game becuase i was like at a party for like 4 hours'}, TweetSentiment{title='@beepbeepsean  sorry babycakes'}, TweetSentiment{title='"@DonnieWahlberg I saw The Kill Point'}, TweetSentiment{title='&quot;And it's 2 am and I'm cursing your name...&quot;  And that's literal right now. Not just song lyrics.   sfshkfhskjfhksdjfh'}, TweetSentiment{title='"@allergydad My 12yo has MASSIVE Anaphylaxis to like 20+ foods incl potatoes'}, TweetSentiment{title='@deadlee2213 which mexican market did u see it at? I still haven't got it yet'}, TweetSentiment{title='*they said im abusing twitter  talk to ya laterz....'}, TweetSentiment{title='"@denymyradio well'}, TweetSentiment{title='@AshleyAmbush mmm that's a bummer'}, TweetSentiment{title='@CHRISDJMOYLES I feel your pain... I start work at 6am every morning. Stinky poos'}, TweetSentiment{title='@bbraden08 what time yuu done . work =eww'}, TweetSentiment{title='@3thbi me?'}, TweetSentiment{title='"@colossalblue Cricket and I do not get on'}, TweetSentiment{title='@DJDROPDEAD wish I was down with xbox'}, TweetSentiment{title='@annamessias i miss yoooooooooooooooooooou my poia'}, TweetSentiment{title='@ABCeCe lol good luck with that. i miss yogurt land...'}, TweetSentiment{title='"@adaline_langton Not going either? I thought I'd take a year off from the mud'}, TweetSentiment{title='@ChocolateEgg Me too  I feel so left out /drama'}, TweetSentiment{title='"@Cadistra indeed. There is much to do'}, TweetSentiment{title='"@Cadistra I'm sorry'}, TweetSentiment{title='@ddlovato I enter every contest &amp; try everything 2 get to meet yu  noluck not even a twitter reply'}, TweetSentiment{title='@balgees  world is not being fare with each other these days.'}, TweetSentiment{title='Bus only just arrived. was sposed to be here 25 mins ago. Cant wait till i can drive again so i dnt have to rely on busses.'}, TweetSentiment{title='@danalar pffffffft and another one!!  not fair reply &amp; mention me lols'}, TweetSentiment{title='@carrielee oh!  did you go all the way back home for your laptop?'}, TweetSentiment{title='@darksbane Trouble is that's often easier said than done! It's sometimes taken me almost an hour to get it working.'}, TweetSentiment{title='@cyntjemusic I have to be to work at 8'}, TweetSentiment{title='@Caseyyyyyyy Haha naah it didnt'}, TweetSentiment{title=':  I will be in Paducah tonight. My blackberry is still broken though   And the Longhorns won in extra innings without cheating. Congrats.'}, TweetSentiment{title='@dorkabella I've been sitting here with Day of the Tentacle in my desk for about a year and a half now. I've never even tried to play it.'}, TweetSentiment{title='"*Remembers she has contaminated well water and has to find new housing.*  A big'}, TweetSentiment{title='@christyclary i agree...'}, TweetSentiment{title='@Ali_xxxxxx What is?'}, TweetSentiment{title='@Boyislost I don't think so'}, TweetSentiment{title='@DinoB937 HEY!! tomorrow at my pod lunch hour can you play  I caught myself by paramore?! i txt you but i didn't hear it'}, TweetSentiment{title='? ? ? You can't be 29...on Sugar Mountain...(or so people tell me...)  ? ? ?'}, TweetSentiment{title='#Grails Plugins blog of @lucastex :http://tinyurl.com/cbwpp9 Thought I'd hit jackpot. Articles beneath are Spanish  English too please!'}, TweetSentiment{title='"@cocoacast I need to move to NY'}, TweetSentiment{title='@CassidyWalker91 how did you do on it? i bombed mine plus i had no work to show'}, TweetSentiment{title='@Clydedude Very very sad. Almost started crying this morning talking about it with Karen.'}, TweetSentiment{title='@alexWTrugs no 3d was sold out!  But it was AMAZING!!!!'}, TweetSentiment{title='(@AMissle) Party?? Hahah. My sis wont shut up'}, TweetSentiment{title='@DAGOJACKET drivin' all ovah the place when i jus wanna get home already...'}, TweetSentiment{title='@aPandaPlease I'm sick.'}, TweetSentiment{title='@beafallenleaf Blessed Are The Forgetful Brendon/Spencer fic. It's really well written but not even a hopeful ending.'}, TweetSentiment{title='@Anaalove have to go  bye' &lt;33'}, TweetSentiment{title='@beanznkornbread aight let me know wuzup. my benz jeep in the shop in atascocita.. gettin some work done.. bout to spend some major cash'}, TweetSentiment{title='"@cloverdash A very small few'}, TweetSentiment{title='@CBDW unfortunately I am working  so I will not be visiting'}, TweetSentiment{title='@aneecha still trying to download it.... kept timing out'}, TweetSentiment{title='@acupofjo i just asked if i could bite sisky's santi tattoo through an alias. i'm a creep'}, TweetSentiment{title='Cardinals lost...AGAIN!'}, TweetSentiment{title='" I'm about to turn down a great job offer in LA. Raise'}, TweetSentiment{title='@adamlittlefield whattt who else got twitter...I legit miss the old days when it was just like you and I'}, TweetSentiment{title='@bramveen You'll get mine later tonight...  I'm still at work now'}, TweetSentiment{title='@beingmyself lol I wish I could boo... but I did on monday &amp; its def too late now'}, TweetSentiment{title='@bibliotech 97 here'}, TweetSentiment{title='@artbynemo Not many people in the office building today  How are things going so far?'}, TweetSentiment{title='@DoreenatDMS - Hmmm... weird that I would call it Favorite 40 and then list 50 albums... not great at math'}, TweetSentiment{title='@boricuacakes what's wrong gurl ?!! u forgot about twitter!!!!!                LOLLLLL'}, TweetSentiment{title='"@AnthonyLucas @iainfunnell Yeah'}, TweetSentiment{title='*Sad news for the ABC Action News family* Gary Papa passed away today from Cancer.......... He'll be missed!'}, TweetSentiment{title='@_disco uh no bus news from you today  i hope motorway news is as good'}, TweetSentiment{title='@cem420 NO Strange Brew is a classic. And im not popular'}, TweetSentiment{title='@cprice0129 I'm actually skipping out on tonight....  you're on your own for the sign language!! But the slideshow is my handy work.'}, TweetSentiment{title='"@ChaoticBarb So confusing! I'm using Twitter to get the questions'}, TweetSentiment{title='@3minds come thru- I put it out there for my twit fam! U aint answer (as usual)'}, TweetSentiment{title='@desi_f pack me in your luggage. I wanna go'}, TweetSentiment{title='@dferrari can't remember the last time I did that'}, TweetSentiment{title='@busch18baby Awww its just gonna be one of those days for you aint it..'}, TweetSentiment{title='@AubreeStorm   miss you so much'}, TweetSentiment{title='@AbbyRo I would go in like 2 seconds if I wasn't 2300 miles away. I miss you.  And them. @megtastic1521 @samaracb'}, TweetSentiment{title='@BTToronto We're right at Yonge and Lakeshore downtown and we've lost it!'}, TweetSentiment{title='"@artywah sorry. Capslock is cruise control for stupid'}, TweetSentiment{title='"@benjaminmadden mate'}, TweetSentiment{title='@BinkieER I Miss Your Lil Cranberry'}, TweetSentiment{title='@ChrisLejarzar eh.  I need to fix my dads car.'}, TweetSentiment{title='@chods70  sorry! I prefer to tweet on my pc..not my crummy palm centro   plus lots going on in the &quot;real worl&quot;  lol'}, TweetSentiment{title='@alxmancilla Installed - Services - Web Server - Setup Account - Finish - Crash'}, TweetSentiment{title='@arnonetina I woke up early too   6 am :@'}, TweetSentiment{title='"@AliceCullenlJ im good'}, TweetSentiment{title='@djweswill u have NO idea!!!!  @swat1stlady will tell u'}, TweetSentiment{title='"@Danthol Yeah'}, TweetSentiment{title='bad day already'}, TweetSentiment{title='@Bobeep'}, TweetSentiment{title='@dukkrogers you were probably ignoring me like tomo was....'}, TweetSentiment{title='@Audio_Rush ohhs icic. im actually watching csi and about to get ready for bed soon. its late and i have class'}, TweetSentiment{title='@Dannymcfly http://twitpic.com/7xvhl - follow me'}, TweetSentiment{title='"@cuevafamily I know how you feel'}, TweetSentiment{title='@deonmims'}, TweetSentiment{title='"@austin31 argh'}, TweetSentiment{title='"@bigmangage'}, TweetSentiment{title='goodbyeeee Arizona. See you in 3 weeks.'}, TweetSentiment{title='"@chadengle how've you been'}, TweetSentiment{title='@Burnsie_SEO Wow! That is so sad that they couldn't get over their prejudice.  I'm not Jew myself but I'll say I value the history there.'}, TweetSentiment{title='..can't sleep   ...fonso went home :'('}, TweetSentiment{title='@_erica I know right'}, TweetSentiment{title='"@a_c81 oh'}, TweetSentiment{title='@brettrush  thanks for the encouraging words..hehe'}, TweetSentiment{title='"@AnnaBaby8388 I'm chillin we should get up'}, TweetSentiment{title='&quot;experts (homers) predict Eagles to go 12-4 and Giants to go 9-7/ BS! http://www.dailysportspages.com/forums/showthread.php?t=49343'}, TweetSentiment{title='@bradpeczka aww just missed out all gone!'}, TweetSentiment{title='"@countjerkula im still on series one! i want to have the annointed round for tea'}, TweetSentiment{title='@amymarie1001 is going to be mad at me. I knocked a half a bag of Choc Chips all over the floor of the pantry! Sorry!'}, TweetSentiment{title='#Microsoft announces its intention to cease development and marketing of #Microsoft #Money http://bit.ly/S7Ff1 #fail'}, TweetSentiment{title='@Ameriicaa i miss u too'}, TweetSentiment{title='@donnieshealer haha poor guy  ill take care of you @donniewahlberg hahah'}, TweetSentiment{title='@chriscuzzy Sorry you're up so ear;y when you'd rather be sleeping. Has your sunburned tanned over yet? Mine hasn't'}, TweetSentiment{title='@AlexAllTimeLow  oh well. Nothing personal will still be amazing.'}, TweetSentiment{title='" hopefully I make it to next years parade. YES'}, TweetSentiment{title='&quot;Canned&quot; is not on ABCï¿½s schedule. Bad NEWS!'}, TweetSentiment{title='@Breyfann sleep well. It's early in Scotland worktime.'}, TweetSentiment{title='@BijanStacks nasty. i wont be making u any cupcakesssssssssss'}, TweetSentiment{title='@donotrefreeze I was in the disney theme parks the whole day...missed all the excitement of BGT on twitter today'}, TweetSentiment{title='@bbbggoodd @virgopeace @misssabrinasin DAHLINGS! haha i need help  PLEASE i need an awesome fic rec. idc what couple or ocs or whatevs'}, TweetSentiment{title='@alliecobra nooo not a snow day... too cold'}, TweetSentiment{title='"@dandemeyere still working on it. My computer has been out of commission for a while now'}, TweetSentiment{title='"@aeropama if that happen'}, TweetSentiment{title='@dopechickjen nooooo lol i didnt mean to do that.. awww'}, TweetSentiment{title='@alicehannah i know'}, TweetSentiment{title='@CarolynWahl and im sad I did not get to say goodbye  I hope everything is FANTASTI... and 5481066 more bytes
```
Code from [TrieClassificationBlog.java:158](../../src/test/java/com/simiacryptus/util/text/TrieClassificationBlog.java#L158) executed in 4009.76 seconds: 
```java
      HashMap<String, List<String>> map = new HashMap<>();
      map.put("pos", tweetsPositive.stream().limit(trainingSize).map(x -> x.getText()).collect(Collectors.toList()));
      map.put("neg", tweetsNegative.stream().limit(trainingSize).map(x -> x.getText()).collect(Collectors.toList()));
      return new ClassificationTree().setVerbose(System.out).categorizationTree(map, 32);
```
Logging: 
```
    "hank" -> Contains={neg=789, pos=4014}	Absent={neg=49211, pos=45986}	Entropy=-0.980134
      "t " -> Contains={neg=498, pos=1555}	Absent={neg=291, pos=2459}	Entropy=-0.619589
        " no" -> Contains={neg=118, pos=159}	Absent={neg=380, pos=1396}	Entropy=-0.779223
          " no " -> Contains={neg=27, pos=12}	Absent={neg=91, pos=147}	Entropy=-0.948271
            " I" -> Contains={neg=6, pos=8}	Absent={neg=21, pos=4}	Entropy=-0.772161
              "ou" -> Contains={neg=7, pos=4}	Absent={neg=14, pos=0}	Entropy=-0.549468
            "ts" -> Contains={neg=19, pos=7}	Absent={neg=72, pos=140}	Entropy=-0.915031
              " h" -> Contains={neg=15, pos=1}	Absent={neg=4, pos=6}	Entropy=-0.635656
              "ess" -> Contains={neg=0, pos=19}	Absent={neg=72, pos=121}	Entropy=-0.882651
                "un" -> Contains={neg=20, pos=12}	Absent={neg=52, pos=109}	Entropy=-0.914667
                  "te" -> Contains={neg=4, pos=7}	Absent={neg=16, pos=5}	Entropy=-0.854155
                    "y " -> Contains={neg=6, pos=5}	Absent={neg=10, pos=0}	Entropy=-0.662721
                  "ing a" -> Contains={neg=8, pos=3}	Absent={neg=44, pos=106}	Entropy=-0.870249
                    "@a" -> Contains={neg=3, pos=29}	Absent={neg=41, pos=77}	Entropy=-0.833325
                      "." -> Contains={neg=0, pos=21}	Absent={neg=3, pos=8}	Entropy=-0.408880
                      " u" -> Contains={neg=12, pos=6}	Absent={neg=29, pos=71}	Entropy=-0.876525
                        " Thanks" -> Contains={neg=3, pos=26}	Absent={neg=26, pos=45}	Entropy=-0.816478
                          "ks " -> Contains={neg=3, pos=12}	Absent={neg=0, pos=14}	Entropy=-0.499524
                          "A" -> Contains={neg=4, pos=21}	Absent={neg=22, pos=24}	Entropy=-0.876674
                            " w" -> Contains={neg=4, pos=9}	Absent={neg=0, pos=12}	Entropy=-0.594416
                            "a " -> Contains={neg=9, pos=4}	Absent={neg=13, pos=20}	Entropy=-0.948478
                              "ol" -> Contains={neg=7, pos=5}	Absent={neg=6, pos=15}	Entropy=-0.910207
                                " m" -> Contains={neg=5, pos=6}	Absent={neg=1, pos=9}	Entropy=-0.793185
          "n't" -> Contains={neg=88, pos=113}	Absent={neg=292, pos=1283}	Entropy=-0.723870
            "my " -> Contains={neg=19, pos=4}	Absent={neg=69, pos=109}	Entropy=-0.930055
              "ing" -> Contains={neg=13, pos=0}	Absent={neg=6, pos=4}	Entropy=-0.562591
              "@d" -> Contains={neg=9, pos=1}	Absent={neg=60, pos=108}	Entropy=-0.918754
                ""@" -> Contains={neg=1, pos=19}	Absent={neg=59, pos=89}	Entropy=-0.897032
                  " I " -> Contains={neg=1, pos=9}	Absent={neg=0, pos=10}	Entropy=-0.435047
                  "n't wa" -> Contains={neg=3, pos=20}	Absent={neg=56, pos=69}	Entropy=-0.926273
                    " s" -> Contains={neg=3, pos=8}	Absent={neg=0, pos=12}	Entropy=-0.550659
                    "bo" -> Contains={neg=0, pos=13}	Absent={neg=56, pos=56}	Entropy=-0.920784
                      "us" -> Contains={neg=6, pos=18}	Absent={neg=50, pos=38}	Entropy=-0.949937
                        "be" -> Contains={neg=6, pos=5}	Absent={neg=0, pos=13}	Entropy=-0.589233
                        "as" -> Contains={neg=19, pos=5}	Absent={neg=31, pos=33}	Entropy=-0.930721
                          " c" -> Contains={neg=10, pos=0}	Absent={neg=9, pos=5}	Entropy=-0.675581
                          "s!" -> Contains={neg=12, pos=4}	Absent={neg=19, pos=29}	Entropy=-0.932503
                            "@a" -> Contains={neg=9, pos=3}	Absent={neg=10, pos=26}	Entropy=-0.849647
                              "er" -> Contains={neg=0, pos=12}	Absent={neg=10, pos=14}	Entropy=-0.740470
                                " b" -> Contains={neg=7, pos=4}	Absent={neg=3, pos=10}	Entropy=-0.871623
            "orry " -> Contains={neg=25, pos=7}	Absent={neg=267, pos=1276}	Entropy=-0.664169
              "on" -> Contains={neg=5, pos=5}	Absent={neg=20, pos=2}	Entropy=-0.644210
                "me" -> Contains={neg=8, pos=2}	Absent={neg=12, pos=0}	Entropy=-0.491596
              " mis" -> Contains={neg=18, pos=9}	Absent={neg=249, pos=1267}	Entropy=-0.647229
                "u " -> Contains={neg=6, pos=8}	Absent={neg=12, pos=1}	Entropy=-0.745384
                "you" -> Contains={neg=68, pos=598}	Absent={neg=181, pos=669}	Entropy=-0.626371
                  "ttin" -> Contains={neg=8, pos=9}	Absent={neg=60, pos=589}	Entropy=-0.456827
                    "@d" -> Contains={neg=8, pos=13}	Absent={neg=52, pos=576}	Entropy=-0.428876
                      ". " -> Contains={neg=6, pos=4}	Absent={neg=2, pos=9}	Entropy=-0.846838
                      "oo " -> Contains={neg=8, pos=16}	Absent={neg=44, pos=560}	Entropy=-0.395978
                        "! " -> Contains={neg=6, pos=4}	Absent={neg=2, pos=12}	Entropy=-0.779478
                        "a h" -> Contains={neg=5, pos=7}	Absent={neg=39, pos=553}	Entropy=-0.361612
                          "ub" -> Contains={neg=5, pos=9}	Absent={neg=34, pos=544}	Entropy=-0.336742
                            "ve you" -> Contains={neg=5, pos=14}	Absent={neg=29, pos=530}	Entropy=-0.309649
                              "I l" -> Contains={neg=4, pos=8}	Absent={neg=25, pos=522}	Entropy=-0.280895
                                " here" -> Contains={neg=4, pos=11}	Absent={neg=21, pos=511}	Entropy=-0.254601
                                  "at w" -> Contains={neg=4, pos=14}	Absent={neg=17, pos=497}	Entropy=-0.227521
                                    " I'm " -> Contains={neg=4, pos=16}	Absent={neg=13, pos=481}	Entropy=-0.195821
                                      "he" -> Contains={neg=0, pos=10}	Absent={neg=4, pos=6}	Entropy=-0.636332
                                      " out " -> Contains={neg=4, pos=18}	Absent={neg=9, pos=463}	Entropy=-0.160044
                                        " h" -> Contains={neg=4, pos=6}	Absent={neg=0, pos=12}	Entropy=-0.585702
                                        "t;" -> Contains={neg=3, pos=21}	Absent={neg=6, pos=442}	Entropy=-0.127038
                                          " w" -> Contains={neg=3, pos=7}	Absent={neg=0, pos=14}	Entropy=-0.510422
                                          "ack " -> Contains={neg=2, pos=8}	Absent={neg=4, pos=434}	Entropy=-0.090810
                                            "Bu" -> Contains={neg=2, pos=9}	Absent={neg=2, pos=425}	Entropy=-0.063036
                                              "t thank" -> Contains={neg=2, pos=25}	Absent={neg=0, pos=400}	Entropy=-0.033153
                                                "fo" -> Contains={neg=2, pos=10}	Absent={neg=0, pos=15}	Entropy=-0.433323
                  "@D" -> Contains={neg=19, pos=7}	Absent={neg=162, pos=662}	Entropy=-0.718131
                    "er" -> Contains={neg=11, pos=0}	Absent={neg=8, pos=7}	Entropy=-0.692731
                    "Thanks" -> Contains={neg=44, pos=306}	Absent={neg=118, pos=356}	Entropy=-0.694581
                      " was " -> Contains={neg=10, pos=17}	Absent={neg=34, pos=289}	Entropy=-0.519353
                        "ea" -> Contains={neg=2, pos=10}	Absent={neg=8, pos=7}	Entropy=-0.863691
                        "p " -> Contains={neg=12, pos=29}	Absent={neg=22, pos=260}	Entropy=-0.455627
                          "g" -> Contains={neg=6, pos=25}	Absent={neg=6, pos=4}	Entropy=-0.780980
                            "st" -> Contains={neg=1, pos=17}	Absent={neg=5, pos=8}	Entropy=-0.631392
                          "back " -> Contains={neg=5, pos=5}	Absent={neg=17, pos=255}	Entropy=-0.359260
                            "er " -> Contains={neg=8, pos=35}	Absent={neg=9, pos=220}	Entropy=-0.312248
                              "g " -> Contains={neg=6, pos=13}	Absent={neg=2, pos=22}	Entropy=-0.654126
                                "ar" -> Contains={neg=0, pos=14}	Absent={neg=2, pos=8}	Entropy=-0.455800
                              "but" -> Contains={neg=3, pos=7}	Absent={neg=6, pos=213}	Entropy=-0.214217
                                "gi" -> Contains={neg=3, pos=11}	Absent={neg=3, pos=202}	Entropy=-0.157387
                                  "do" -> Contains={neg=3, pos=25}	Absent={neg=0, pos=177}	Entropy=-0.090371
                                    " Thanks " -> Contains={neg=0, pos=17}	Absent={neg=3, pos=8}	Entropy=-0.458840
                      "@d" -> Contains={neg=11, pos=4}	Absent={neg=107, pos=352}	Entropy=-0.784608
                        " really " -> Contains={neg=8, pos=3}	Absent={neg=99, pos=349}	Entropy=-0.760518
                          "n a" -> Contains={neg=14, pos=11}	Absent={neg=85, pos=338}	Entropy=-0.737242
                            " thanks " -> Contains={neg=3, pos=10}	Absent={neg=11, pos=1}	Entropy=-0.660879
                            "ak" -> Contains={neg=14, pos=17}	Absent={neg=71, pos=321}	Entropy=-0.704413
                              "er" -> Contains={neg=4, pos=12}	Absent={neg=10, pos=5}	Entropy=-0.873812
                              "thou" -> Contains={neg=7, pos=4}	Absent={neg=64, pos=317}	Entropy=-0.659598
                                "t t" -> Contains={neg=28, pos=78}	Absent={neg=36, pos=239}	Entropy=-0.634821
                                  "a " -> Contains={neg=0, pos=21}	Absent={neg=28, pos=57}	Entropy=-0.766176
                                    "s f" -> Contains={neg=12, pos=8}	Absent={neg=16, pos=49}	Entropy=-0.844333
                                      "nt" -> Contains={neg=8, pos=2}	Absent={neg=4, pos=6}	Entropy=-0.870586
                                      "c" -> Contains={neg=8, pos=39}	Absent={neg=8, pos=10}	Entropy=-0.754668
                                        " c" -> Contains={neg=5, pos=9}	Absent={neg=3, pos=30}	Entropy=-0.606955
                                          "st " -> Contains={neg=3, pos=11}	Absent={neg=0, pos=19}	Entropy=-0.434114
                                  " mi" -> Contains={neg=6, pos=7}	Absent={neg=30, pos=232}	Entropy=-0.535160
                                    "on t" -> Contains={neg=6, pos=9}	Absent={neg=24, pos=223}	Entropy=-0.488011
                                      "thanks a" -> Contains={neg=4, pos=7}	Absent={neg=20, pos=216}	Entropy=-0.439343
                                        " ba" -> Contains={neg=5, pos=10}	Absent={neg=15, pos=206}	Entropy=-0.393766
                                          ". " -> Contains={neg=10, pos=56}	Absent={neg=5, pos=150}	Entropy=-0.331428
                                            " wa" -> Contains={neg=5, pos=7}	Absent={neg=5, pos=49}	Entropy=-0.550563
                                              "g" -> Contains={neg=0, pos=40}	Absent={neg=5, pos=9}	Entropy=-0.316800
                                            """ -> Contains={neg=4, pos=21}	Absent={neg=1, pos=129}	Entropy=-0.173594
                                              "at" -> Contains={neg=0, pos=12}	Absent={neg=4, pos=9}	Entropy=-0.594416
                                              "d tha" -> Contains={neg=1, pos=9}	Absent={neg=0, pos=120}	Entropy=-0.073709
        ""@" -> Contains={neg=125, pos=430}	Absent={neg=166, pos=2029}	Entropy=-0.462896
          "thanks" -> Contains={neg=63, pos=135}	Absent={neg=62, pos=295}	Entropy=-0.747481
            "L" -> Contains={neg=11, pos=2}	Absent={neg=52, pos=133}	Entropy=-0.844244
              "ll" -> Contains={neg=4, pos=35}	Absent={neg=48, pos=98}	Entropy=-0.824360
                " a" -> Contains={neg=2, pos=21}	Absent={neg=46, pos=77}	Entropy=-0.876543
                  " f" -> Contains={neg=2, pos=8}	Absent={neg=0, pos=13}	Entropy=-0.473076
                  "m" -> Contains={neg=10, pos=32}	Absent={neg=36, pos=45}	Entropy=-0.923952
                    "e" -> Contains={neg=5, pos=27}	Absent={neg=5, pos=5}	Entropy=-0.724444
                      "d" -> Contains={neg=4, pos=9}	Absent={neg=1, pos=18}	Entropy=-0.590142
                    "p" -> Contains={neg=8, pos=3}	Absent={neg=28, pos=42}	Entropy=-0.956430
                      ""@B" -> Contains={neg=6, pos=4}	Absent={neg=22, pos=38}	Entropy=-0.950827
                        "c" -> Contains={neg=12, pos=15}	Absent={neg=10, pos=23}	Entropy=-0.934014
                          "r" -> Contains={neg=8, pos=5}	Absent={neg=4, pos=10}	Entropy=-0.918788
                          "s " -> Contains={neg=8, pos=8}	Absent={neg=2, pos=15}	Entropy=-0.778025
            ""@d" -> Contains={neg=6, pos=6}	Absent={neg=56, pos=289}	Entropy=-0.650602
              "ad" -> Contains={neg=9, pos=16}	Absent={neg=47, pos=273}	Entropy=-0.626038
                "y" -> Contains={neg=8, pos=6}	Absent={neg=1, pos=10}	Entropy=-0.790287
                " you " -> Contains={neg=2, pos=49}	Absent={neg=45, pos=224}	Entropy=-0.587582
                  "m" -> Contains={neg=0, pos=31}	Absent={neg=2, pos=18}	Entropy=-0.275675
                    "d" -> Contains={neg=2, pos=8}	Absent={neg=0, pos=10}	Entropy=-0.533315
                  "el" -> Contains={neg=12, pos=27}	Absent={neg=33, pos=197}	Entropy=-0.636079
                    "g" -> Contains={neg=2, pos=14}	Absent={neg=10, pos=13}	Entropy=-0.825442
                      "m" -> Contains={neg=7, pos=3}	Absent={neg=3, pos=10}	Entropy=-0.845386
                    "c" -> Contains={neg=5, pos=83}	Absent={neg=28, pos=114}	Entropy=-0.565705
                      "ic" -> Contains={neg=3, pos=12}	Absent={neg=2, pos=71}	Entropy=-0.293411
                        ""@ch" -> Contains={neg=2, pos=12}	Absent={neg=0, pos=59}	Entropy=-0.176117
                      "li" -> Contains={neg=6, pos=5}	Absent={neg=22, pos=109}	Entropy=-0.679410
                        "k " -> Contains={neg=4, pos=47}	Absent={neg=18, pos=62}	Entropy=-0.629835
                          "! " -> Contains={neg=3, pos=7}	Absent={neg=1, pos=40}	Entropy=-0.345650
                            ""@C" -> Contains={neg=1, pos=9}	Absent={neg=0, pos=31}	Entropy=-0.230623
                          ""@a" -> Contains={neg=2, pos=23}	Absent={neg=16, pos=39}	Entropy=-0.736303
                            "s " -> Contains={neg=0, pos=14}	Absent={neg=2, pos=9}	Entropy=-0.452443
                            "s" -> Contains={neg=15, pos=39}	Absent={neg=1, pos=0}	Entropy=-0.848492
          " miss" -> Contains={neg=16, pos=8}	Absent={neg=150, pos=2021}	Entropy=-0.366159
            "v" -> Contains={neg=4, pos=6}	Absent={neg=12, pos=2}	Entropy=-0.779978
            "ollow" -> Contains={neg=0, pos=305}	Absent={neg=150, pos=1716}	Entropy=-0.346325
              "ish " -> Contains={neg=12, pos=13}	Absent={neg=138, pos=1703}	Entropy=-0.390419
                " m" -> Contains={neg=3, pos=8}	Absent={neg=9, pos=5}	Entropy=-0.909963
                "." -> Contains={neg=69, pos=493}	Absent={neg=69, pos=1210}	Entropy=-0.374051
                  "br" -> Contains={neg=11, pos=16}	Absent={neg=58, pos=477}	Entropy=-0.517338
                    " m" -> Contains={neg=8, pos=3}	Absent={neg=3, pos=13}	Entropy=-0.781510
                    "ing to" -> Contains={neg=8, pos=10}	Absent={neg=50, pos=467}	Entropy=-0.473589
                      " no" -> Contains={neg=8, pos=13}	Absent={neg=42, pos=454}	Entropy=-0.439193
                        " now" -> Contains={neg=3, pos=8}	Absent={neg=5, pos=5}	Entropy=-0.928083
                        "@d" -> Contains={neg=6, pos=6}	Absent={neg=36, pos=448}	Entropy=-0.396277
                          " I" -> Contains={neg=18, pos=102}	Absent={neg=18, pos=346}	Entropy=-0.364763
                            "ck" -> Contains={neg=7, pos=10}	Absent={neg=11, pos=92}	Entropy=-0.561150
                              "no" -> Contains={neg=4, pos=9}	Absent={neg=7, pos=83}	Entropy=-0.462516
                                "O" -> Contains={neg=3, pos=9}	Absent={neg=4, pos=74}	Entropy=-0.373374
                                  " I a" -> Contains={neg=3, pos=10}	Absent={neg=1, pos=64}	Entropy=-0.253995
                                    "da" -> Contains={neg=1, pos=9}	Absent={neg=0, pos=55}	Entropy=-0.148799
                            "s l" -> Contains={neg=4, pos=8}	Absent={neg=14, pos=338}	Entropy=-0.263353
                              "...." -> Contains={neg=4, pos=12}	Absent={neg=10, pos=326}	Entropy=-0.221407
                                "thanks. " -> Contains={neg=2, pos=10}	Absent={neg=8, pos=316}	Entropy=-0.183802
                                  "tho" -> Contains={neg=2, pos=8}	Absent={neg=6, pos=308}	Entropy=-0.156738
                                    "rr" -> Contains={neg=2, pos=9}	Absent={neg=4, pos=299}	Entropy=-0.125734
                                      "s thank" -> Contains={neg=1, pos=9}	Absent={neg=3, pos=290}	Entropy=-0.099251
                                        "n tha" -> Contains={neg=1, pos=9}	Absent={neg=2, pos=281}	Entropy=-0.081174
                                          "w tha" -> Contains={neg=1, pos=9}	Absent={neg=1, pos=272}	Entropy=-0.059430
                                            "ik" -> Contains={neg=1, pos=9}	Absent={neg=0, pos=263}	Entropy=-0.035596
                  "m s
    ...skipping 266131 bytes...
    os=10}	Entropy=-0.208684
                                                "i" -> Contains={neg=2, pos=14}	Absent={neg=3, pos=9}	Entropy=-0.696004
                                    "#inaperf" -> Contains={neg=37, pos=0}	Absent={neg=624, pos=765}	Entropy=-0.965508
                                      "hate" -> Contains={neg=29, pos=4}	Absent={neg=595, pos=761}	Entropy=-0.976757
                                        " a" -> Contains={neg=11, pos=4}	Absent={neg=18, pos=0}	Entropy=-0.491183
                                        "los" -> Contains={neg=22, pos=3}	Absent={neg=573, pos=758}	Entropy=-0.976617
                                          " l" -> Contains={neg=15, pos=0}	Absent={neg=7, pos=3}	Entropy=-0.492100
                                          " but " -> Contains={neg=17, pos=2}	Absent={neg=556, pos=756}	Entropy=-0.974238
                                            "oodsex " -> Contains={neg=1, pos=22}	Absent={neg=555, pos=734}	Entropy=-0.971028
                                              "an" -> Contains={neg=1, pos=9}	Absent={neg=0, pos=13}	Entropy=-0.388495
                                              "ollow" -> Contains={neg=6, pos=36}	Absent={neg=549, pos=698}	Entropy=-0.974538
                                                "ay" -> Contains={neg=0, pos=22}	Absent={neg=6, pos=14}	Entropy=-0.507613
                                                  " follow" -> Contains={neg=2, pos=8}	Absent={neg=4, pos=6}	Entropy=-0.868086
                                                " No" -> Contains={neg=21, pos=4}	Absent={neg=528, pos=694}	Entropy=-0.978472
                                                  " a" -> Contains={neg=8, pos=4}	Absent={neg=13, pos=0}	Entropy=-0.573291
                                                  "#square" -> Contains={neg=30, pos=12}	Absent={neg=498, pos=682}	Entropy=-0.974874
                                                    "to" -> Contains={neg=5, pos=7}	Absent={neg=25, pos=5}	Entropy=-0.753750
                                                      "i" -> Contains={neg=17, pos=1}	Absent={neg=8, pos=4}	Entropy=-0.605859
                                                    "ing me " -> Contains={neg=10, pos=0}	Absent={neg=488, pos=682}	Entropy=-0.970809
                                                      "thing " -> Contains={neg=14, pos=2}	Absent={neg=474, pos=680}	Entropy=-0.968636
                                                        "hot" -> Contains={neg=2, pos=32}	Absent={neg=472, pos=648}	Entropy=-0.962219
                                                          "y " -> Contains={neg=2, pos=11}	Absent={neg=0, pos=21}	Entropy=-0.359786
                                                          " pa" -> Contains={neg=22, pos=7}	Absent={neg=450, pos=641}	Entropy=-0.971791
                                                            " the" -> Contains={neg=6, pos=6}	Absent={neg=16, pos=1}	Entropy=-0.650887
                                                            "ove " -> Contains={neg=3, pos=25}	Absent={neg=447, pos=616}	Entropy=-0.967796
                                                              "ove t" -> Contains={neg=3, pos=8}	Absent={neg=0, pos=17}	Entropy=-0.460340
                                                              "ave t" -> Contains={neg=12, pos=2}	Absent={neg=435, pos=614}	Entropy=-0.971950
                                                                "#R" -> Contains={neg=11, pos=1}	Absent={neg=424, pos=613}	Entropy=-0.969612
                                                                  "here " -> Contains={neg=18, pos=7}	Absent={neg=406, pos=606}	Entropy=-0.966488
                                  "lov" -> Contains={neg=29, pos=86}	Absent={neg=3010, pos=1612}	Entropy=-0.928605
                                    "b" -> Contains={neg=22, pos=32}	Absent={neg=7, pos=54}	Entropy=-0.734040
                                      "!" -> Contains={neg=1, pos=11}	Absent={neg=21, pos=21}	Entropy=-0.891318
                                        "wi" -> Contains={neg=1, pos=9}	Absent={neg=20, pos=12}	Entropy=-0.863645
                                          " s" -> Contains={neg=4, pos=7}	Absent={neg=16, pos=5}	Entropy=-0.854155
                                            ". " -> Contains={neg=10, pos=1}	Absent={neg=6, pos=4}	Entropy=-0.747034
                                      """ -> Contains={neg=4, pos=6}	Absent={neg=3, pos=48}	Entropy=-0.445354
                                        "my " -> Contains={neg=2, pos=8}	Absent={neg=1, pos=40}	Entropy=-0.319446
                                          " a " -> Contains={neg=1, pos=9}	Absent={neg=0, pos=31}	Entropy=-0.230623
                                    " excited" -> Contains={neg=0, pos=27}	Absent={neg=3010, pos=1585}	Entropy=-0.920842
                                      "!" -> Contains={neg=494, pos=408}	Absent={neg=2516, pos=1177}	Entropy=-0.920232
                                        " hate" -> Contains={neg=17, pos=0}	Absent={neg=477, pos=408}	Entropy=-0.978147
                                          "to be " -> Contains={neg=14, pos=0}	Absent={neg=463, pos=408}	Entropy=-0.982057
                                            "yin" -> Contains={neg=17, pos=1}	Absent={neg=446, pos=407}	Entropy=-0.984459
                                              "ood " -> Contains={neg=10, pos=30}	Absent={neg=436, pos=377}	Entropy=-0.985708
                                                " the" -> Contains={neg=6, pos=5}	Absent={neg=4, pos=25}	Entropy=-0.704961
                                                  " b" -> Contains={neg=3, pos=7}	Absent={neg=1, pos=18}	Entropy=-0.556351
                                                "e the " -> Contains={neg=3, pos=18}	Absent={neg=433, pos=359}	Entropy=-0.981061
                                                  "ng" -> Contains={neg=3, pos=7}	Absent={neg=0, pos=11}	Entropy=-0.573895
                                                  "ad" -> Contains={neg=75, pos=28}	Absent={neg=358, pos=331}	Entropy=-0.977835
                                                    "dy " -> Contains={neg=5, pos=8}	Absent={neg=70, pos=20}	Entropy=-0.789134
                                                      "or" -> Contains={neg=32, pos=1}	Absent={neg=38, pos=19}	Entropy=-0.673111
                                                        " wo" -> Contains={neg=9, pos=1}	Absent={neg=23, pos=0}	Entropy=-0.282100
                                                        " the" -> Contains={neg=4, pos=8}	Absent={neg=34, pos=11}	Entropy=-0.829422
                                                          "A" -> Contains={neg=5, pos=7}	Absent={neg=29, pos=4}	Entropy=-0.665599
                                                            " i" -> Contains={neg=7, pos=3}	Absent={neg=22, pos=1}	Entropy=-0.500829
                                                              " b" -> Contains={neg=9, pos=1}	Absent={neg=13, pos=0}	Entropy=-0.388495
                                                    "ay" -> Contains={neg=62, pos=95}	Absent={neg=296, pos=236}	Entropy=-0.984595
                                                      "as" -> Contains={neg=20, pos=8}	Absent={neg=42, pos=87}	Entropy=-0.901945
                                                        " i" -> Contains={neg=7, pos=6}	Absent={neg=13, pos=2}	Entropy=-0.791533
                                                        "ain" -> Contains={neg=10, pos=3}	Absent={neg=32, pos=84}	Entropy=-0.844120
                                                          "tu" -> Contains={neg=7, pos=3}	Absent={neg=25, pos=81}	Entropy=-0.797359
                                                            " we" -> Contains={neg=6, pos=5}	Absent={neg=19, pos=76}	Entropy=-0.749973
                                                              "r" -> Contains={neg=12, pos=66}	Absent={neg=7, pos=10}	Entropy=-0.685796
                                                                " r" -> Contains={neg=5, pos=6}	Absent={neg=7, pos=60}	Entropy=-0.560160
                                                                  "lo" -> Contains={neg=3, pos=7}	Absent={neg=4, pos=53}	Entropy=-0.456871
                                                      "ne " -> Contains={neg=34, pos=7}	Absent={neg=262, pos=229}	Entropy=-0.969858
                                                        " and " -> Contains={neg=6, pos=4}	Absent={neg=28, pos=3}	Entropy=-0.601688
                                                          " o" -> Contains={neg=15, pos=0}	Absent={neg=13, pos=3}	Entropy=-0.480674
                                                        "tti" -> Contains={neg=1, pos=13}	Absent={neg=261, pos=216}	Entropy=-0.976994
                                                          "ve " -> Contains={neg=20, pos=2}	Absent={neg=241, pos=214}	Entropy=-0.972275
                                                            " a" -> Contains={neg=8, pos=2}	Absent={neg=12, pos=0}	Entropy=-0.491596
                                                            " is" -> Contains={neg=38, pos=14}	Absent={neg=203, pos=200}	Entropy=-0.980450
                                                              "r" -> Contains={neg=32, pos=5}	Absent={neg=6, pos=9}	Entropy=-0.696332
                                                                "!! " -> Contains={neg=7, pos=3}	Absent={neg=25, pos=2}	Entropy=-0.548839
                                                                  " c" -> Contains={neg=8, pos=2}	Absent={neg=17, pos=0}	Entropy=-0.410425
                                                              "4" -> Contains={neg=2, pos=13}	Absent={neg=201, pos=187}	Entropy=-0.984305
                                                                "got " -> Contains={neg=1, pos=10}	Absent={neg=200, pos=177}	Entropy=-0.982395
                                                                  "llo" -> Contains={neg=1, pos=9}	Absent={neg=199, pos=168}	Entropy=-0.982173
                                        "???????" -> Contains={neg=6, pos=25}	Absent={neg=2510, pos=1152}	Entropy=-0.893387
                                          "e " -> Contains={neg=5, pos=7}	Absent={neg=1, pos=18}	Entropy=-0.610521
                                          "happy" -> Contains={neg=12, pos=32}	Absent={neg=2498, pos=1120}	Entropy=-0.889632
                                            "." -> Contains={neg=8, pos=11}	Absent={neg=4, pos=21}	Entropy=-0.748080
                                              "in" -> Contains={neg=0, pos=11}	Absent={neg=4, pos=10}	Entropy=-0.613175
                                            "ood mo" -> Contains={neg=3, pos=18}	Absent={neg=2495, pos=1102}	Entropy=-0.884378
                                              "t" -> Contains={neg=0, pos=11}	Absent={neg=3, pos=7}	Entropy=-0.574395
                                              "atching " -> Contains={neg=12, pos=24}	Absent={neg=2483, pos=1078}	Entropy=-0.880951
                                                " t" -> Contains={neg=10, pos=10}	Absent={neg=2, pos=14}	Entropy=-0.817960
                                                  "f" -> Contains={neg=6, pos=4}	Absent={neg=4, pos=6}	Entropy=-0.974603
                                                ": " -> Contains={neg=90, pos=92}	Absent={neg=2393, pos=986}	Entropy=-0.876625
                                                  " u" -> Contains={neg=18, pos=3}	Absent={neg=72, pos=89}	Entropy=-0.948123
                                                    ". " -> Contains={neg=11, pos=0}	Absent={neg=7, pos=3}	Entropy=-0.573895
                                                    " :" -> Contains={neg=10, pos=1}	Absent={neg=62, pos=88}	Entropy=-0.947418
                                                      "(" -> Contains={neg=1, pos=13}	Absent={neg=61, pos=75}	Entropy=-0.942616
                                                        "I" -> Contains={neg=21, pos=12}	Absent={neg=40, pos=63}	Entropy=-0.959257
                                                          "p" -> Contains={neg=7, pos=10}	Absent={neg=14, pos=2}	Entropy=-0.791174
                                                          "at " -> Contains={neg=8, pos=2}	Absent={neg=32, pos=61}	Entropy=-0.911838
                                                            "ar" -> Contains={neg=0, pos=10}	Absent={neg=32, pos=51}	Entropy=-0.889808
                                                              "l" -> Contains={neg=24, pos=27}	Absent={neg=8, pos=24}	Entropy=-0.927391
                                                                "e " -> Contains={neg=9, pos=17}	Absent={neg=15, pos=10}	Entropy=-0.951239
                                                                  " h" -> Contains={neg=6, pos=5}	Absent={neg=3, pos=12}	Entropy=-0.852451
                                                                  "u" -> Contains={neg=8, pos=2}	Absent={neg=7, pos=8}	Entropy=-0.904960
                                                                "u" -> Contains={neg=4, pos=6}	Absent={neg=4, pos=18}	Entropy=-0.787678
                                                                  "g" -> Contains={neg=0, pos=10}	Absent={neg=4, pos=8}	Entropy=-0.641765
                                                  "".." -> Contains={neg=134, pos=111}	Absent={neg=2259, pos=875}	Entropy=-0.862952
                                                    " on " -> Contains={neg=11, pos=1}	Absent={neg=123, pos=110}	Entropy=-0.972021
                                                      " c" -> Contains={neg=9, pos=23}	Absent={neg=114, pos=87}	Entropy=-0.968687
                                                        " a" -> Contains={neg=1, pos=14}	Absent={neg=8, pos=9}	Entropy=-0.737147
                                                        "p" -> Contains={neg=32, pos=12}	Absent={neg=82, pos=75}	Entropy=-0.965097
                                                          "I" -> Contains={neg=13, pos=0}	Absent={neg=19, pos=12}	Entropy=-0.751869
                                                            "m" -> Contains={neg=4, pos=6}	Absent={neg=15, pos=6}	Entropy=-0.903863
                                                              " t" -> Contains={neg=9, pos=1}	Absent={neg=6, pos=5}	Entropy=-0.793185
                                                          "le" -> Contains={neg=12, pos=1}	Absent={neg=70, pos=74}	Entropy=-0.956072
                                                            "el" -> Contains={neg=3, pos=11}	Absent={neg=67, pos=63}	Entropy=-0.976657
                                                              " w" -> Contains={neg=5, pos=12}	Absent={neg=62, pos=51}	Entropy=-0.977817
                                                                " h" -> Contains={neg=11, pos=3}	Absent={neg=51, pos=48}	Entropy=-0.970733
                                                                  "b" -> Contains={neg=6, pos=12}	Absent={neg=45, pos=36}	Entropy=-0.978366
                                                    "twitter" -> Contains={neg=13, pos=23}	Absent={neg=2246, pos=852}	Entropy=-0.846177
                                                      "y " -> Contains={neg=10, pos=8}	Absent={neg=3, pos=15}	Entropy=-0.834418
                                                      "hate " -> Contains={neg=53, pos=2}	Absent={neg=2193, pos=850}	Entropy=-0.841306
                                                        "s." -> Contains={neg=9, pos=2}	Absent={neg=44, pos=0}	Entropy=-0.216842
                                                        " great " -> Contains={neg=7, pos=16}	Absent={neg=2186, pos=834}	Entropy=-0.846980
                                                          " d" -> Contains={neg=5, pos=5}	Absent={neg=2, pos=11}	Entropy=-0.811969
                                                          " Good" -> Contains={neg=2, pos=12}	Absent={neg=2184, pos=822}	Entropy=-0.842927
                                                            "http://" -> Contains={neg=48, pos=42}	Absent={neg=2136, pos=780}	Entropy=-0.839100
                                                              "pl" -> Contains={neg=10, pos=1}	Absent={neg=38, pos=41}	Entropy=-0.942066
                                                                ". http:/" -> Contains={neg=11, pos=2}	Absent={neg=27, pos=39}	Entropy=-0.921657
                                                                  " m" -> Contains={neg=13, pos=6}	Absent={neg=14, pos=33}	Entropy=-0.886787
                                                              "awesome" -> Contains={neg=2, pos=10}	Absent={neg=2134, pos=770}	Entropy=-0.830366
                                                                "finally" -> Contains={neg=3, pos=11}	Absent={neg=2131, pos=759}	Entropy=-0.826929
                                                                  "my " -> Contains={neg=354, pos=74}	Absent={neg=1777, pos=685}	Entropy=-0.823591
    
```

Returns: 
```
    com.simiacryptus.util.text.ClassificationTree$$Lambda$103/2096057945@2a098129
```
Code from [TrieClassificationBlog.java:164](../../src/test/java/com/simiacryptus/util/text/TrieClassificationBlog.java#L164) executed in 0.29 seconds: 
```java
      return tweetsPositive.stream().skip(trainingSize).map(x->x.getText()).mapToDouble(str->{
          Map<String, Double> prob = rule.apply(str);
          System.out.println(String.format("%s -> %s", str, prob));
          return prob.getOrDefault("pos", 0.0) < 0.5 ? 0.0 : 1.0;
      }).average().getAsDouble() * 100.0 + "%";
```
Logging: 
```
    first days are always good..wish my dentist gave me more hours though -> {neg=0.8271028037383178, pos=0.17289719626168223}
    "@Cassiekins That's good!  Alright -> {neg=0.20468154599891128, pos=0.7953184540010887}
    @Bethurz Haha Yeah Maan  AhhPink&amp;Black... My SEX phone! I just need to save as much as possible now! ): Ebay here we come! ha -> {neg=0.25, pos=0.75}
    @allyjoyetsy If I get that million dollar idea to clone myself I'll let you in on that secret since you need it too! -> {neg=0.28150736211629646, pos=0.7184926378837035}
    @ashlarr y'all need just a few more gadgets to plug in -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @BmoreBaby7980 I'm on it kid. -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @britesprite Hi. No idea. #ecomonday is a great idea. There are so many interesting people out there. We just need to find them. -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@AleciaLouise I checked -> {neg=0.5264309150565075, pos=0.47356908494349254}
    @_CorruptedAngel why thank you -> {neg=0.0, pos=1.0}
    @CrazyDucky lol aight I'll get ready!! see ya soon!! memmer u owe me ducky loving!! -> {neg=0.0, pos=1.0}
    @Cezza_B   thanks for the #follow friday! -> {neg=0.0, pos=1.0}
    "#FF #4 @LeslieSanchez &amp; @mccainblogette prove that not ALL the fun girls are Democrats! Funny -> {neg=0.4, pos=0.6}
    "@backseatgoodbye you told me you were coming to detroit -> {neg=0.28150736211629646, pos=0.7184926378837035}
    "@alison_london Bristol was indeed good fun -> {neg=0.5, pos=0.5}
    @alanmccullough Woo-hoo! Congratulations on your Leo Award for 'The Queen!' Well deserved! -> {neg=0.28150736211629646, pos=0.7184926378837035}
    Charging phone -> {neg=0.7217709179528838, pos=0.27822908204711616}
    "@beautymoogle I am!  For Thomas Cooley Law -> {neg=0.42037586547972305, pos=0.579624134520277}
    @bingmama until she's not happy AND not falling asleep -> {neg=0.9751552795031055, pos=0.024844720496894408}
    "@can1315 All sent!! There are a lot but I go camera crazy and -> {neg=1.0, pos=0.0}
    @CacheAdvance love it when there's more than one cache in the same area  throw some pics up? -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@crazybilly I know -> {neg=0.5264309150565075, pos=0.47356908494349254}
    @beccarr_mcc okies. i shall read. -> {neg=0.4618705035971223, pos=0.5381294964028777}
    @caitsampo all about the trust -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @AnoopDoggDesai I hope you can reply once in a while.. It'd be great.. -> {neg=0.0, pos=1.0}
    @AlexandeRadio mannequins creep me out either way. -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @2kids1love That's good  It's better than being at home. -> {neg=0.012345679012345678, pos=0.9876543209876543}
    &quot;Who took my badjas?!&quot; &amp; &quot;No koffiekoeken for you!&quot; Leuke reclame uit BelgiÃ«!  http://squurl.com/cda35/ -> {neg=0.5384615384615384, pos=0.46153846153846156}
    Katie Herzig tweeted my tattoo of her &quot;Jenny Lynn&quot; lyrics.  Check it! http://yfrog.com/0j90oj and check out her music! -> {neg=0.0, pos=1.0}
    @AntiMileyHaters Ireland  Wbuu&gt;&gt;?? -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@chelle_83 yep -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @Alyssa_Milano  Thought #2: In regard to &quot;Oops. Busted. Shhh. Bye.&quot;  Is someone getting addicted to Twitter?  ha ha ha -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @awesomestyle helloww;  fine and you kjhaj -> {neg=0.28150736211629646, pos=0.7184926378837035}
    I'm chill. He's the one freaking out. -> {neg=0.7217709179528838, pos=0.27822908204711616}
    @chackmaul Thankx!!!!  You do the same. -> {neg=0.0, pos=1.0}
    @AreloZ It is classic. I love it so very much.  And I'll see what I can do in terms of hanging out. My grandmama is here til the 14th... -> {neg=0.0, pos=1.0}
    @archangelmaggie at least you got to go and have an awesome time!  and also i heard you met @melisaxoarchie ! -> {neg=0.0, pos=1.0}
    @Broncos365 I am going to try and hit as much of camp as I can.  I might ask for some time off work. -> {neg=0.5264309150565075, pos=0.47356908494349254}
    @DanceMassTV Thanks! True encounters in Med school. Stay tuned for more! I've got loads coming up! Wt u up2 this lovely Sunday? -> {neg=1.0, pos=0.0}
    @BamDaStrag heeey im not an alchy.. I jus like 2 drink a lil -> {neg=0.0, pos=1.0}
    "@ the leadership conference - tonight -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@BootyPirate - I thought it was called Horrorlando or Whorelando -> {neg=0.5264309150565075, pos=0.47356908494349254}
    @aileen456 Thanks Ailleen -> {neg=0.11764705882352941, pos=0.8823529411764706}
    @balsamiq I'd say clipperz.com (who's got also an OS version you can put on your server) and passpack.com (btw both are #madeinitaly  ) -> {neg=0.28150736211629646, pos=0.7184926378837035}
    @beatmanludmilla Thats amaizing...Thanks a lot for the WAV  Greetz from Russia. Monday become much better -> {neg=0.0, pos=1.0}
    @atrocity79 yup!!! Already!! -> {neg=0.20468154599891128, pos=0.7953184540010887}
    ?Morning Tweets? Stomach hurts alittle. Ate sum subways &amp; they put too much hot pepers on my sub. It'll pass -> {neg=0.29411764705882354, pos=0.7058823529411765}
    "@ArteWorks_SEO @doyouzooloo haha -> {neg=0.3157894736842105, pos=0.6842105263157895}
    @aaronmarshmusic or a punch the shape of the card on a card on a card on a card. and then ask people what it is. for free cards. -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "#asot400 after this weekend -> {neg=0.40118577075098816, pos=0.5988142292490118}
    @Anime81 lmao oppsie just think when you finish your hm/wrk you can relax finger cross lol -> {neg=0.28150736211629646, pos=0.7184926378837035}
    (G) Lazy Sundays are always the best. Been a recluse this wkend so far. Just been revising and playing BAP...I do enjoy seeing no one -> {neg=1.0, pos=0.0}
    @convoy3571 Do you think so? hehehehe! -> {neg=0.28150736211629646, pos=0.7184926378837035}
    @billyraycyrus Come to MALTA!! You have lot of fans here!! Love your music!!! -> {neg=0.28150736211629646, pos=0.7184926378837035}
    @crushluther Hey! You got really good music. -> {neg=0.20468154599891128, pos=0.7953184540010887}
    @30SECONDSTOMARS Just wanted 2 share-- perfect sunset in the UK  Wish I'd a pic but too chilled 2 move. http://www.twitpic.com/6ct75 see? -> {neg=0.058823529411764705, pos=0.9411764705882353}
    "@ClyCly1976. Yes my friend lydia has her birthday on my same day.  It is exciting -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @__Lua hahahaha you bet  im going to break sooo many thing =D -> {neg=0.28150736211629646, pos=0.7184926378837035}
    @cassialenoir cool  would love to spend my entire day there someday -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @DaMaHug I don't speak it fluently but I can function. I'm a good listener in all languages -> {neg=0.3, pos=0.7}
    @billythekid morning -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@_alan yeeeeey!  SIX FEET UNDER!! that's what I mean!!  love it! love them! and love you! haha .. ok -> {neg=0.4666666666666667, pos=0.5333333333333333}
    @banshe1999 yeah...jill lives a couple of towns away....like a 15 minute drive....but i'm actually taking my husband to the show on sat. -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @chrisfromracine That's for sure. That's why I intentionally turn my thought processes to the silly -> {neg=0.5264309150565075, pos=0.47356908494349254}
    @bookmygroup Get 100 followers a day using www.tweeteradder.com Once you add everyone you are on the train or pay vip -> {neg=0.0, pos=1.0}
    @BlackBerry74 I feel it would apply to anyone -> {neg=0.9090909090909091, pos=0.09090909090909091}
    "@Aftashok o yea -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @abrad45 i will beat @plantsvszombies eventually. jearous of your breffast.  and aw bailey -> {neg=0.28150736211629646, pos=0.7184926378837035}
    @akynos - we so need to sit one day over some drinks and have these convos - tweeter is a bit annoying for it -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@BrodyJenner WOWWW -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@AudioBooksGuy Thanks John.  I use to fly professionally -> {neg=0.2777777777777778, pos=0.7222222222222222}
    @Coach055 That's hella tight! Drink one for me at the open bar -> {neg=0.7142857142857143, pos=0.2857142857142857}
    @afzalALMIGHTY indeedio -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @calinfusu am intrat in posesia cartii. Merci mult. -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@amruth92 well! Awesome. I gotta go -> {neg=0.1, pos=0.9}
    @AnneRiceAuthor I logged in to see this clip of Susan........what a gift just to hear her sing. Thank you for your post. -> {neg=0.3333333333333333, pos=0.6666666666666666}
    @bayliebrown Yesssd you do!! Call me back!! -> {neg=0.28150736211629646, pos=0.7184926378837035}
    "&quot;sometimes I feel like I'm just one mistake away -> {neg=0.2613941018766756, pos=0.7386058981233244}
    @19fischi75 http://twitpic.com/6oqo4 - Thank u Sweetheart - yeah - helps a lill -> {neg=0.0, pos=1.0}
    @archuphils Archie's very near to #1! Try my technique!!!  and the voting needs to end tomorrow! tom's fans might vote more lol -> {neg=0.20468154599891128, pos=0.7953184540010887}
    "@_Anshul yep -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @ben_stiller Hi from Spain  magnific zoolander! -> {neg=0.20468154599891128, pos=0.7953184540010887}
    @CoachCharrise Morning to you! -> {neg=0.28150736211629646, pos=0.7184926378837035}
    @crittertam Do you have a link? -> {neg=0.28150736211629646, pos=0.7184926378837035}
    "@Cyndiinbc Cyndi -> {neg=0.39740036318455507, pos=0.6025996368154449}
    #myweakness... and Chocolate cake -> {neg=0.2727272727272727, pos=0.7272727272727273}
    @brandon_myers Get 100 followers a day using www.tweeteradder.com Once you add everyone you are on the train or pay vip -> {neg=0.0, pos=1.0}
    @_Janet_ Yes! Do it and then show me some piccies! -> {neg=0.7142857142857143, pos=0.2857142857142857}
    @chewy121 nufffin  bout to go sleep. -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@BeckyDMBR - yeah -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @cyberlocksmith i'll have a full writeup soon -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @4aplin Ð¨Ð°Ñ€Ð¸ÐºÐ¾Ð²Ñ‹ Ð²Ñ?ÐµÐ³Ð´Ð° Ð±ÑƒÐ´ÑƒÑ‚ Ñ€Ñ?Ð´Ð¾Ð¼ Ñ? Ð½Ð°Ð¼Ð¸-Ñ?Ñ‚Ð¾ Ñ„Ð°ÐºÑ‚. -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@DameSaf Especially with a partner. Like I said -> {neg=0.47058823529411764, pos=0.5294117647058824}
    @alexedge1 then i definitely wont come -> {neg=1.0, pos=0.0}
    @_spell tyvm I think -> {neg=0.5264309150565075, pos=0.47356908494349254}
    @caitysparkles Happy Birthday!! -> {neg=0.0, pos=1.0}
    "@brendastice LOL!!  omg.  but yeah.  that -> {neg=0.23076923076923078, pos=0.7692307692307693}
    @BluesAngel80 You got it.. and I keep my promises. -> {neg=0.5264309150565075, pos=0.47356908494349254}
    @AshleyLTMSYF http://twitpic.com/611f1 - Yea! Jai ho!! -> {neg=0.20468154599891128, pos=0.7953184540010887}
    @bholcomb some guy just thks he has that much of an impact when really he's a dime a dozen. -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @AznDiva I was more of a surfer hippie that night -> {neg=0.41102756892230574, pos=0.5889724310776943}
    @AllenKP Yep. Thats me. -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @bridgetparsons church for us - are we the only ones going? -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @Alixito U touch that Unicorn n Ill shish kabob U -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@alzrnb it wasnt directed soley at you -> {neg=0.7210526315789474, pos=0.2789473684210526}
    @CrmOfTheCrop lol u know that crap was hilarious -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@auntievelyn Hi -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @camerarec thanks a lot! -> {neg=0.3333333333333333, pos=0.6666666666666666}
    @aidadoll lol yes I love em! -> {neg=0.0, pos=1.0}
    @BeckyKingston There both great In their own ways -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @cupiexcake depends on if i can do warped or not this year  if so i'll be there! -> {neg=0.7368421052631579, pos=0.2631578947368421}
    @bunnylovecara dats real -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @car4dave 20?!  wow...thanks for that. -> {neg=0.0, pos=1.0}
    "@alexissso_o you replied to yyourself? kaaay -> {neg=0.28150736211629646, pos=0.7184926378837035}
    @Cassanderly i know -> {neg=0.5, pos=0.5}
    "#Followfriday @TobyTurner we've got to beat @kimkardashian he's awesome -> {neg=0.25, pos=0.75}
    ...And how could I forget Asian...not too spicy though... -> {neg=0.7217709179528838, pos=0.27822908204711616}
    @anothermuser Obrigada AndrÃ© Free! -> {neg=0.20468154599891128, pos=0.7953184540010887}
    #liesgirlstell &quot;Why wouldn't I wear these 6 inch stilettos? They're the most comfortable pair I own!&quot; &quot;but we're camping!&quot; Luv my heels! -> {neg=1.0, pos=0.0}
    "@aplusk good idea  lol -> {neg=0.5, pos=0.5}
    --&gt; @katyperry in June (28) in Portugal!! I don't know... should I go? Or is just another catching song?!? -> {neg=0.7655986509274874, pos=0.23440134907251264}
    "@aTexasAttitude I completely agree -> {neg=0.5264309150565075, pos=0.47356908494349254}
    ...love the text....in swedish though....see you later....  â™« http://blip.fm/~89aav -> {neg=0.4166666666666667, pos=0.5833333333333334}
    "@Akelaa something like that.  and if i can have my way with it -> {neg=0.10344827586206896, pos=0.896551724137931}
    @AngCummings hey it was great yesterday. and i've done it! -> {neg=0.20468154599891128, pos=0.7953184540010887}
    #letsmakeamess I wanna hear all of them duh....but particularly 6 Months &amp; Josey -> {neg=0.29411764705882354, pos=0.7058823529411765}
    @christopherbee all the best for friday chris -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@adrianalovesjb that's okay hun. thanks though  and yes -> {neg=0.0, pos=1.0}
    @abhi_jith So happy to hear that. -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @blokeslib now that sounds great... I might picture dowrfle there instead of on this train  mwah -> {neg=0.2, pos=0.8}
    @AlexanderRybak You are an AMAZING musician. Your Fairytale performance on Eurovision blew me away. Keep it up! Can't wait to hear more! -> {neg=0.0, pos=1.0}
    ??? ????????? ??? ???????? ??? Mint ????????????? ??????? ????? 2 ?????! Windows sucks!!! -> {neg=1.0, pos=0.0}
    "@1Guvnor Yay -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @AnaGibson lol okay! Might text and ask if I know them -> {neg=0.8666666666666667, pos=0.13333333333333333}
    @AndreaMate he said about a year. They have moved so fast tho i think they will be off sooner. i wanted straight teeth for my wedding -> {neg=0.75, pos=0.25}
    @Angela_Scorpio of course life's great without any work and stress -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @Contendo ps download the updated twittelator..its awesome -> {neg=0.0, pos=1.0}
    #HannahMontana  - I also have to add that I do like Hannah / Miley and the movie made me laugh and cry.  The cowboy was a doll baby -> {neg=0.8571428571428571, pos=0.14285714285714285}
    "@aphroditexl http://twitpic.com/6qiax - ..just a little one .. and put some of our names on it -> {neg=0.058823529411764705, pos=0.9411764705882353}
    @adamSEO  it couldn't have come at a better time -> {neg=0.7655986509274874, pos=0.23440134907251264}
    @ home testing .. upload and download -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@AlexAllTimeLow I wish I was outside -> {neg=1.0, pos=0.0}
    @Cathryn1819 I think it's coming next week -> {neg=0.5264309150565075, pos=0.47356908494349254}
    @aplusk -who do you think you are? Brett Farve? -> {neg=0.28150736211629646, pos=0.7184926378837035}
    "@cindolce awe........thanks sweets!! Thank you for all your advice -> {neg=0.10256410256410256, pos=0.8974358974358975}
    @AnnaC1316 Can I have some fun with you? -> {neg=0.28150736211629646, pos=0.7184926378837035}
    (OFF) GOODNIGHT LAOS!! What a wonderful day!!! Its been a day! I have to go.. Tomorrow is CHILDREN'S DAY!! Talk to you later!! o_O -> {neg=0.28150736211629646, pos=0.7184926378837035}
    "@bcmystery Yeah -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @bettieboudoir and no prob on the photogs!  hope you find something you like -> {neg=0.4444444444444444, pos=0.555555555555555
    ...skipping 1058566 bytes...
    0.23529411764705882, pos=0.7647058823529411}
    " hihooo(:  but -> {neg=0.07142857142857142, pos=0.9285714285714286}
    "@anthonymerante You mean -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "&quot;Are you daft -> {neg=0.28150736211629646, pos=0.7184926378837035}
    @Bookwhisperer37 i won't! -> {neg=0.7655986509274874, pos=0.23440134907251264}
    "@andyclemmensen hahaha wow bradie being mean -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @arlenexo aww lol i didn't get too wet[thank goodness] cuz i straightened my hair  ahah. he showed me something on his phone about a 'do -> {neg=1.0, pos=0.0}
    "&quot;if your boyfriends got beef -> {neg=0.28150736211629646, pos=0.7184926378837035}
    @ home carless... gettin' it fixed for the desert ride this wkend... colorado river then vegas then grand canyon -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @Bethurz Oh hell yeah man! Twitter is the new pink! (; Speaking of Pink... Guess what colour my new sidekick will be!? -> {neg=0.07142857142857142, pos=0.9285714285714286}
    @allinthelyrix does your offer still stand?  I hope your having a good day. Your missed in Cali! -> {neg=0.9072164948453608, pos=0.09278350515463918}
    @angiemartinez happy mothers day ang -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @bennglazier nup no cd either.. just a whole bunch of zeros and ones you can have for free -> {neg=0.38562091503267976, pos=0.6143790849673203}
    @BananaLover123 oh.. hm? it said they we're doing maintnance when i tried getting on. but it's going now -> {neg=0.3333333333333333, pos=0.6666666666666666}
    @Badin__  I know! AWESOME! Still we need 2 vote!! Tom McFly is also having many votes! SO VOTE 4 ARCHIE  lol! -> {neg=0.42037586547972305, pos=0.579624134520277}
    &quot;Because you can't kill love&quot; -- GWs04e23  http://plurk.com/p/uine7 -> {neg=1.0, pos=0.0}
    @bydahway nothing unlocked yet. give those hackers some time  remember this is a cdma phone not like I phone that uses sim cards -> {neg=0.7, pos=0.3}
    "@AveryStepfon Thanks so much. It will be a challenge -> {neg=0.0, pos=1.0}
    @bbcomebck2me Stephie I miss you. -> {neg=0.9072164948453608, pos=0.09278350515463918}
    @Arasphere Yes the luckiest girl -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @_abbycowley goood luck -> {neg=0.11538461538461539, pos=0.8846153846153846}
    $SQNM closed up $.15 today....volume almost 5 million!!! let's keep it going -> {neg=0.4117647058823529, pos=0.5882352941176471}
    @BenClouthier glad to know ben -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @Danderma yes tweeps are tweeting ppl -> {neg=0.5625, pos=0.4375}
    @antwanduncan we gonna get low in july?  can't wait to see you again! hopefully there will be a stellar event we'll all attend -> {neg=0.14285714285714285, pos=0.8571428571428571}
    @CHANGE12 - See if you have it - If NOT get it then come back - I haven't bought a Phone Since 2005 - I have the New HTC Touch Pro -> {neg=0.0, pos=1.0}
    @Armano they were. But were liquidated in the restructuring. Got 5 cents on the dollar for a LI venture group. -> {neg=0.39740036318455507, pos=0.6025996368154449}
    $ES_F squeeze as expected -> {neg=0.7217709179528838, pos=0.27822908204711616}
    @BlondMobile Same  No complaints...Just a little tired... -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @assenavdew  Sweet dreams -> {neg=0.39740036318455507, pos=0.6025996368154449}
    so nice to have friends! -> {neg=1.0, pos=0.0}
    "@butterflyzan Size 10 giant man just rang. says if you're going to be mean about his hands -> {neg=0.2727272727272727, pos=0.7272727272727273}
    "&quot;if there r any ghosts in here -> {neg=0.2613941018766756, pos=0.7386058981233244}
    "@aveight hello -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @CBCebulski Are ChesterQuest runner-ups invited to send you more recent samples? I'd love to see what you think about my current stuff. -> {neg=0.0, pos=1.0}
    @BaldiesUnite oh yes please that we be nice -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @angelajames Appreciate it. Thanks -> {neg=0.0, pos=1.0}
    "@bbillybilltweet ROFL - I was not confusing you with the father! But I hope he is proud of himself -> {neg=0.4, pos=0.6}
    @ben_stiller congrats. the nonbreaking of things is always a half full kinda day -> {neg=0.3548387096774194, pos=0.6451612903225806}
    @ComplexMixShow haha I'm beat! Lol. But all after 2nt no work til 3 tomorrow -> {neg=0.058823529411764705, pos=0.9411764705882353}
    @amber_benson haha...i had to look that up. NYC is def awesome! I can't wait to go back someday -> {neg=0.42105263157894735, pos=0.5789473684210527}
    @aileenabigail Going to the store with Kyuhyun earlier -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@addatwork n-am gasit nici una din generala -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @Ben_Hall Nothing I can talk about -> {neg=0.2608695652173913, pos=0.7391304347826086}
    @catherinebarr I *LOVE* Jeff Bridges in *The Big Lebowski*!!! Go rent this movie if you haven't yet. It's hilarious!!! A great film! -> {neg=0.5, pos=0.5}
    &quot;Intuition becomes increasingly valuable in the new information society precisely because there is so much data.â€?- John Naisbitt  Use it! -> {neg=0.0, pos=1.0}
    "&quot;I know we facing a recession -> {neg=0.2613941018766756, pos=0.7386058981233244}
    ... Won EWU Softball Championship...woo hoo!! Me and Charlie MVP!!  finally got that shirt! -> {neg=0.09090909090909091, pos=0.9090909090909091}
    @brawngp_fanblog yep spot on  can I use pls ? -> {neg=0.18518518518518517, pos=0.8148148148148148}
    " yay for boating -> {neg=0.7217709179528838, pos=0.27822908204711616}
    @BrittanyShamOn i have lion king AND aladdin but they r on vhs... lol -> {neg=0.15384615384615385, pos=0.8461538461538461}
    @beckinelson oh and u wanna do me a favour please ?? -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @babblingbrookie awesome! did you get to meet Sasha Vujacic from the Lakers? he made the first pitch and is a really nice guy! -> {neg=0.0, pos=1.0}
    @BreeOlson9 http://www.twitpic.com/6ajsk. In case u like keeping fan pics or wondered how it came out. -> {neg=0.4666666666666667, pos=0.5333333333333333}
    @Caraa_x Katy Perry-If you can afford me! -> {neg=0.28150736211629646, pos=0.7184926378837035}
    *fingers crossed* oh please please please please please please let me be a finalist! PLEEEEASE! @LOOKthemovie -> {neg=0.2, pos=0.8}
    #SanctuarySunday @pizzini3000 we are on a mission today to keep it a trending topic -> {neg=0.375, pos=0.625}
    @ TaylorRMarshall thanks for checking out my twits hope you like what you see -> {neg=0.4, pos=0.6}
    @bje2323 that sounds like a grand plan friend -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @chreimp omg. I know I know  can't wait!! -> {neg=0.007462686567164179, pos=0.9925373134328358}
    @austinjames you can't sell viva pinata!  that's half mine! -> {neg=0.47368421052631576, pos=0.5263157894736842}
    @BrandyandIce cool! -> {neg=0.20468154599891128, pos=0.7953184540010887}
    "@banilla_face i am not lazy! lol -> {neg=0.5305591677503251, pos=0.4694408322496749}
    @acummings we had rain for days on end so you can have it... I'll use a watering can -> {neg=0.28150736211629646, pos=0.7184926378837035}
    ###### yay ##### thanks @matclayton ##### -> {neg=0.0, pos=1.0}
    @billyraycyrus we are looking forward to u rockin us  are u bringing the family? -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @_anea How's your day going? -> {neg=0.28150736211629646, pos=0.7184926378837035}
    @alexcentomo I DONT KNOW HOW TO WORK THIS SHIT. i love u more. i wore the bees today -> {neg=0.5264309150565075, pos=0.47356908494349254}
    @bigzero I see a Duncan in that pic.  He r cute. -> {neg=0.41102756892230574, pos=0.5889724310776943}
    @bennyboocore Nyawww n00b  I will guide you through the twittersphere  ask people what their twitter is n go www.twitter.com/(theirname) -> {neg=0.0, pos=1.0}
    @_CorruptedAngel  thanks for the ff - much appreciated  got those photos yet? :-O x -> {neg=0.4, pos=0.6}
    "@ckcyn It could be swearbots that have started hunting for those *** marks... They're getting clever -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @Boyislost @Sexydeadstar @danlev @burbankeboy *yawns* I wake up from my nap to mr special calling me  lol -> {neg=1.0, pos=0.0}
    "&quot;How can I move on -> {neg=0.2613941018766756, pos=0.7386058981233244}
    @carlosefonseca I can give you the contact of the person who makes those cheesecakes! -> {neg=0.28150736211629646, pos=0.7184926378837035}
    "@coryy_x thanks for following  haha -> {neg=0.10256410256410256, pos=0.8974358974358975}
    "@chully yes ma'am -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@akafrancie OK -> {neg=0.39740036318455507, pos=0.6025996368154449}
    #followfriday (saturday?)  luv to @riyel27 @chillami @AmericanWomannn @rossanneg @silly_milly @terrancecharles -> {neg=0.0, pos=1.0}
    @aS_Umi I'll sing it.   I can do soprano/alto -> {neg=0.2608695652173913, pos=0.7391304347826086}
    "@bangdidwhat pistols -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @antipov What inflation? Gasoline costs 47 cents per gallon here. -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @anggieholics: hahaha. Dont worry. My friends &amp; I are so expert on it. -> {neg=0.0, pos=1.0}
    @bryanrhoads yeah me too... @SwagnerDesigns can always participate next week! -> {neg=0.16666666666666666, pos=0.8333333333333334}
    "@apolaine Yes -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "#MerrieMonarch Oh darn -> {neg=0.40118577075098816, pos=0.5988142292490118}
    @AnnetteDubow damn those squirrels -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @ceejerk You will definitely be okay.  We will take care of you. -> {neg=0.28150736211629646, pos=0.7184926378837035}
    "@Black_Fedora i've only seen STP once -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @chibit thanky  people seem really worried that i'm not more excited!! -> {neg=0.45454545454545453, pos=0.5454545454545454}
    @ChelseaParadiso Oooh Ill Be Checking That Out Now -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @azuzephre wow! I wouldn't want to drink it though it looks delicious -> {neg=0.7655986509274874, pos=0.23440134907251264}
    @AmandaAzzarello then no. how about something better.i've been wanting to watch blade trilogy again -> {neg=0.6919929865575687, pos=0.3080070134424313}
    "@billyraycyrus heey I'm from Argentina Billy  u could bring Miley to here -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@amymccl Aw -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @CiscoLaRisco @Willofmyown are you 11? -> {neg=0.28150736211629646, pos=0.7184926378837035}
    @clarissasays lol. thanks. -> {neg=0.3333333333333333, pos=0.6666666666666666}
    ...back from vacationionion -> {neg=0.7217709179528838, pos=0.27822908204711616}
    baby sitting today.. shopping tomorrow.. Rue 21 Is the best Ever.. -> {neg=0.7217709179528838, pos=0.27822908204711616}
    *smirks* I dont have to explain wat i do...i jus do it...thats wat makes me great -> {neg=0.9, pos=0.1}
    @BexJames what more motivation could you need than getting home to that gorgeous boy of yours? Jeez! -> {neg=0.28150736211629646, pos=0.7184926378837035}
    @B_smak yay you are now my 4th follower im getting more friends by the minute -> {neg=0.037037037037037035, pos=0.9629629629629629}
    @Ang72 Jobs interfere with life I think. -> {neg=0.5264309150565075, pos=0.47356908494349254}
    "#Sims 3: Lots of fun.  Amazingly flexible item/house designer.  Need more men's hair opt. -> {neg=0.40118577075098816, pos=0.5988142292490118}
    "@ceggs If I have the method and maths right -> {neg=0.5264309150565075, pos=0.47356908494349254}
    "@brebishop JoRo? Really -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @brendamantz Oooooh great idea! -> {neg=0.20468154599891128, pos=0.7953184540010887}
    @cark3ys Sitting with your boyfriend &quot;the dog&quot; -> {neg=0.28150736211629646, pos=0.7184926378837035}
    "@claireyfairy1 aww well -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @ActivityGrrrl thanks for the retweet!! I'll let you know if it works -> {neg=0.0, pos=1.0}
    @Andrewgoldstein so its 5:15am and i cant sleep so im listening to Permanent Heartbreak  im in love with this song. -> {neg=0.09090909090909091, pos=0.9090909090909091}
    "@cara_rosaen i LOVE your new bracelets!! im a very proud sista...you rock -> {neg=0.28150736211629646, pos=0.7184926378837035}
    @ashleytisdale You really deserve it! congrats and that is the firstof so much more -> {neg=0.0, pos=1.0}
    @Adam_Lambert Fans from outside the US wish they could vote for Adam too! -> {neg=1.0, pos=0.0}
    @ColorblindFish Thanks for helping make this a summer to remember. See you in St. Louis!! -> {neg=0.2727272727272727, pos=0.7272727272727273}
    @CruciFire ok.. waiting for it.. -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @adrielove yes i saw him. omg it totally did haha! i know right? i thought it was funny. so what are you doin' missy? -> {neg=0.45454545454545453, pos=0.5454545454545454}
    @charlii1 mmm true!! I'll do that  thanks xoxo -> {neg=0.0, pos=1.0}
    @Boundnsexy Yeah I suppose so.. -> {neg=0.5264309150565075, pos=0.47356908494349254}
    @ALH30 I have!!only one..for now! u can see a pic of it on the background of my profile here -> {neg=0.6153846153846154, pos=0.38461538461538464}
    @animoenzo hey.. pagaling ka agad.. we need to see you on sunday! ayt? -> {neg=0.28150736211629646, pos=0.7184926378837035}
    "@CarolinaDeafGrl we went all over. visited family -> {neg=0.7, pos=0.3}
    @bcollinsmn yeah I'd probably punch him. -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @bbc_dream737 Aww..thanks! &amp;&amp; ur even cool-ERRR! -> {neg=0.0, pos=1.0}
    @becci2708 eee  yeah lol have you? -> {neg=0.28150736211629646, pos=0.7184926378837035}
    "@_dru_ &lt;.&lt; &gt;.&gt; Iz okai bb -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@AngelSmit I *wish* I could go on the watermelon diet. But alas -> {neg=0.5264309150565075, pos=0.47356908494349254}
    "@danicafavorite I'm going to TRY to bring it to conference. I really want 2 get all of you -> {neg=1.0, pos=0.0}
    @amalucky work that tan -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @charlie_danger dayum.  that's like being able to see and THEN going blind.  or having sex and then being cut off -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@craig42k you too!! I'm getting used to seeing you every week -> {neg=0.28150736211629646, pos=0.7184926378837035}
    @bumgenius I love watching the babies learn their new tricks. Good for Elsie  #clothdiapers -> {neg=0.0, pos=1.0}
    @AnaYanez @ClaudiaYanez I hope everything went great tonight! Im so happy for you guys! Miss you -> {neg=0.21428571428571427, pos=0.7857142857142857}
    Another Friday night of Rock Band -> {neg=0.7217709179528838, pos=0.27822908204711616}
    "@brightondoll -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@Damme hmm -> {neg=0.8108108108108109, pos=0.1891891891891892}
    @caitiebradica Haha oh now youre really teasing me  Or noone can know about tc &amp; i anyways so were all good. what they dont know wont hurt -> {neg=1.0, pos=0.0}
    @bedofbrownrice awww how cute  I hope you are all having a great day -> {neg=0.0, pos=1.0}
    "&quot;Plastic Loveless Letter&quot; just came on my iPod. Thanks -> {neg=0.0, pos=1.0}
    @amor8 a little bit of both. -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@andyclemmensen wow -> {neg=0.39740036318455507, pos=0.6025996368154449}
    ..got a loooooooonnnnnggggg weekend ahead of me... out w/ da sis @AshSoPrecious Fri &amp; Sat ... Sat nite &amp; Sun. weekend wit my boyfried -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@a_willow Anyway -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @andmarin have fun girl!!  Ill miss you &lt;3 -> {neg=0.9072164948453608, pos=0.09278350515463918}
    @aspiringcouture No - but I thought you might like some fab accessories to go with your fab clothing -> {neg=0.28150736211629646, pos=0.7184926378837035}
    @andybarron http://twitpic.com/7i8c7 - Niiice! The look suits you. P.S. Nice Fiction Family jacket -> {neg=0.0, pos=1.0}
    @asmithrainey Have you heard of KT Tape? Could be a great solution for your knee. If it works tell your friends  http://is.gd/wipW -> {neg=0.0, pos=1.0}
    @AaronStrout &quot;Minister of Social Media&quot; has a better ring to it. -> {neg=0.39740036318455507, pos=0.6025996368154449}
    
```

Returns: 
```
    77.53%
```
Code from [TrieClassificationBlog.java:171](../../src/test/java/com/simiacryptus/util/text/TrieClassificationBlog.java#L171) executed in 0.22 seconds: 
```java
      return tweetsNegative.stream().skip(trainingSize).map(x->x.getText()).mapToDouble(str->{
          Map<String, Double> prob = rule.apply(str);
          System.out.println(String.format("%s -> %s", str, prob));
          return prob.getOrDefault("neg", 0.0) < 0.5 ? 0.0 : 1.0;
      }).average().getAsDouble() * 100.0 + "%";
```
Logging: 
```
    my sisters are heart broken and I can't even help cause I am too.. // My unfollow button is just ASKING to be hit.. -> {neg=0.5714285714285714, pos=0.42857142857142855}
    @chaddeus I can't do lunch...like...at all.  I'm afraid to move away from my desk. -> {neg=0.9920948616600791, pos=0.007905138339920948}
    "@AngelAstra probs -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @arielleGore I so dont  And wtf. Whatthefuck. REALLY? Really? Are they trying to kill me? But i might be... Depends... -> {neg=0.9, pos=0.1}
    @belibradley I'm sorry to hear that  But I'm confident that you will make the decision that's best for you...you have my prayers -> {neg=1.0, pos=0.0}
    "@alessandrod: dude.. hope you guys are alright -> {neg=0.28150736211629646, pos=0.7184926378837035}
    @Antionette2299 why are you awake!! And I can't. Our office is very small plus I have to collect lotto money today -> {neg=0.3333333333333333, pos=0.6666666666666666}
    @Althe.. Sad?!?  why? What happen?! !? -> {neg=0.9375, pos=0.0625}
    "@djever @faahz @mandofresko I don't care wat you say -> {neg=1.0, pos=0.0}
    @AnnieMeyer im still up too!!! -> {neg=0.20468154599891128, pos=0.7953184540010887}
    @chantelleaustin omg that sounds so good -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @CityMommySLC I'm good...funky rainy day here -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @denisethefish ESTK show me where?! o: PLEASE? :| -> {neg=1.0, pos=0.0}
    "@ButimaDime yeahh but you have to drive the rover -> {neg=0.6571428571428571, pos=0.34285714285714286}
    @dawn_16 gah i would come hang if i didnt already promise someone else. sorry -> {neg=1.0, pos=0.0}
    2 weeks!!!! -> {neg=0.5422343324250681, pos=0.45776566757493187}
    @annedudek I hope your baby will be ok soon -> {neg=0.28150736211629646, pos=0.7184926378837035}
    "@ambermanson hahahahaha we will dear -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @DaxOCallaghan : I wanna be in Frankfurt -> {neg=0.7, pos=0.3}
    @censorphoto I don't know how to use the washing machine -> {neg=0.7655986509274874, pos=0.23440134907251264}
    ..who likes me but who is dating another girl. What a mess! -> {neg=1.0, pos=0.0}
    just hadda tell a pt they were in early stages of kidney failure and headed for dialysis... sometimes this is harder than others... -> {neg=0.7217709179528838, pos=0.27822908204711616}
    "@Dannymcfly PLEASE DANNY -> {neg=1.0, pos=0.0}
    "@akarra i no init -> {neg=0.5305591677503251, pos=0.4694408322496749}
    @britleaf I work Mondays  I have all weekend off though -> {neg=0.5264309150565075, pos=0.47356908494349254}
    @devilgossip  everything is picked over. -> {neg=1.0, pos=0.0}
    @easmart aww you're so lucky -> {neg=0.7142857142857143, pos=0.2857142857142857}
    @buzzbishop I think you're pretty much hooped with that one! -> {neg=0.28150736211629646, pos=0.7184926378837035}
    @DLP06 once again.... -> {neg=1.0, pos=0.0}
    @ChatteMuse Will you still tweet with us even though your viking ship has brought your package? -> {neg=0.28150736211629646, pos=0.7184926378837035}
    @Angela_Grace AAAAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAH!  BBH in 15 minutes! See u @ Ed Center - i have no cell phone -> {neg=0.9, pos=0.1}
    @alexandervision  That is definitely over the top. Feel sorry for the  children. -> {neg=0.5714285714285714, pos=0.42857142857142855}
    @brookelovesyoux I am willing to trade this. It is yet another miserable day here... Horrid! -> {neg=0.42037586547972305, pos=0.579624134520277}
    I wish this was easier!! -> {neg=1.0, pos=0.0}
    @Chrishell7 I signed the petition...That is so sad -> {neg=1.0, pos=0.0}
    @bsweens I would love to play quidditch! BUt I don't own a broom! -> {neg=0.5, pos=0.5}
    @alexd_xo i'm all alone..theres no1 here beside me .... so lonely with my mere 24 followers -> {neg=0.7857142857142857, pos=0.21428571428571427}
    @alexxxinvasion  All you can do is try to talk to your rents again -> {neg=0.28150736211629646, pos=0.7184926378837035}
    "@AudreyDavy  it's all pretty do-able -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @asatiir almost every one I try to chat and convience about Twitter the same -> {neg=0.5264309150565075, pos=0.47356908494349254}
    #trvsdjam morning twitter. broadband has run out -> {neg=0.40118577075098816, pos=0.5988142292490118}
    #asksteve Did you consider lost business when removing Krisflyer Gold from the Clubhouse welcome list? My business is going to United now -> {neg=1.0, pos=0.0}
    "@bookdepository Love you -> {neg=0.28150736211629646, pos=0.7184926378837035}
    "&quot;The air so much easier -> {neg=0.2613941018766756, pos=0.7386058981233244}
    @ my twitter isn't working right -> {neg=1.0, pos=0.0}
    @deankw that was real sad -> {neg=1.0, pos=0.0}
    @AJDYDASCO yeah its open. it was 30% off but for one person I paid 17.35 -> {neg=1.0, pos=0.0}
    @Anyc7  what's wrong with you -> {neg=0.28150736211629646, pos=0.7184926378837035}
    @BlackestRayne me too. Gotta make money while I can though -> {neg=0.18518518518518517, pos=0.8148148148148148}
    @azzmonkey thats hillarious but why couldnt i be the blonde  lmfaooooooo make me a login so i can post haaaa ill post some funny shit -> {neg=1.0, pos=0.0}
    @ChrystallJane true that EPICFAIL -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @albertoyong  Vector Marketing blows. -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @Aleise the warmer the weather....and they come  I get a bug flyin in here and there. SUCKS -> {neg=0.5264309150565075, pos=0.47356908494349254}
    @brittbee23 miss you -> {neg=0.9072164948453608, pos=0.09278350515463918}
    @bellysbride it's just grey here  and cold cold cold for this time of year -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @Dyana_ oh you're still watching it  okay.. what is it ? -> {neg=1.0, pos=0.0}
    @deregnbuen haha go get it then!! Haha yah I know. I'm behving like there's no exams next week! Sigh I went shopping today somemore can -> {neg=1.0, pos=0.0}
    @1Teeyna aww sorry to hear about Beagie -> {neg=1.0, pos=0.0}
    @AllieFennell Cant DM u bck cuz ur not following me -> {neg=0.6919929865575687, pos=0.3080070134424313}
    @chinokieran are you really that bummed by her? -> {neg=0.28150736211629646, pos=0.7184926378837035}
    @Brokencitysky not me? -> {neg=0.6919929865575687, pos=0.3080070134424313}
    @DJFruitLoops that stinks  I can't imagine how you put up w/ that -> {neg=1.0, pos=0.0}
    @darkrock pants! Louise is working that weekend  she gets back 2pm Sat &amp; 1.40pm Sun. It takes me 20min to get to Pitch. -> {neg=1.0, pos=0.0}
    &quot;Try to turn on my tv to get you out of my head. I've got this feeling inside that I just won't admit...&quot; (that is literally how I feel) -> {neg=0.5454545454545454, pos=0.45454545454545453}
    @daysdifference it was the 14th but i'm having the party tonight.only w/ 1 person though. woulda been better if i had a ride. o well -> {neg=1.0, pos=0.0}
    @cnnbrk  I.m So Sad For This tRagedy -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@daedalas1981 your dumb -> {neg=0.45217391304347826, pos=0.5478260869565217}
    @BOBBYFRESH09 its almost over   been running around got an f-ing migrane trying to get shiz done so is the hathaway rumor true? -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@BelindaSchull we talked about sigur ros -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "#wossybookclub Anyone managed to find a copy of the book yet -> {neg=0.40118577075098816, pos=0.5988142292490118}
    "#reasonsihatewinter my nose runs all the time -> {neg=0.5305591677503251, pos=0.4694408322496749}
    ...i need a new tv... -> {neg=0.7217709179528838, pos=0.27822908204711616}
    @chrisrus1 Then its offical that your not coming back  nooooooooo! lol i jest -> {neg=0.6923076923076923, pos=0.3076923076923077}
    @catosborn a girl is screaming outside my window  creepy -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@davebriggs Yes -> {neg=1.0, pos=0.0}
    @CrackerCrumbie No one has said it better.  I meant to add a comment to that effect as well but twitterfailed. -> {neg=1.0, pos=0.0}
    @camcalupitan i wanna watch it again. -> {neg=1.0, pos=0.0}
    "@dougiemcfly  I insist -> {neg=1.0, pos=0.0}
    @deidre_1922 yeah ur Good I kan HARDLY find you now Indy -> {neg=1.0, pos=0.0}
    "@domdawes Tech on. Now -> {neg=1.0, pos=0.0}
    @BreaksDiva  I miss you too -> {neg=0.875, pos=0.125}
    "@divarina21 rinaaa! change my default pic. i cant change it. my shit freezes up on me.  change it to somethin cute -> {neg=1.0, pos=0.0}
    @87sal87 I've been so busy the last week I haven't been keeping up properly  Gonna try to catch up today cause I don't feel so great lol -> {neg=0.7655986509274874, pos=0.23440134907251264}
    @doublethecookie hahahah i'm having ppp  professional preparation programme x( -> {neg=1.0, pos=0.0}
    @bcuban @mcuban That NBA non-call by the ref was horrible.  In a playoff game too!    Keep your chin up! -> {neg=0.42105263157894735, pos=0.5789473684210527}
    #RantsAndRaves The worst thing about GM (concord / pleasant hill / martinez): is the fucking UAW. ..   http://buzzup.com/4ueb -> {neg=0.45454545454545453, pos=0.5454545454545454}
    @bbgeekchic  my arm hurts. @mustloveanimals hit me 4 times in the arm for no reason! -> {neg=0.6919929865575687, pos=0.3080070134424313}
    &quot;StumbleUpon has temporarily run out of sites relevant to you. Please sign up for more topics.&quot; -> {neg=0.7210526315789474, pos=0.2789473684210526}
    @bangerang_ian @tiffanikki were trying to but me and c-money's schedules are all out of whack -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @bibliobecca Uh-hum and this sister needs a huggg. -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @danudey hot weather for the lose. And I have to clean too!  Cleaning in the heat sucks balls. -> {neg=0.36363636363636365, pos=0.6363636363636364}
    @clsp88 How cannn?! Don't hate me. I'm sorry. I will unregister myself. I am but a worm -> {neg=0.0, pos=1.0}
    @AshleyBreeanne @ LEAST THEY GAVE U meds ! lol I cant believe they didnt put you under ! grr ! They did me !  You are crying  ?!Ohhh -> {neg=0.5333333333333333, pos=0.4666666666666667}
    "@ChinqMiau Pah! I didn't get a key  Damn you -> {neg=0.8478260869565217, pos=0.15217391304347827}
    "@bloodycountesss yeah -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @cluelessly_moi I KNOW. It's going to be a long wait. -> {neg=0.5264309150565075, pos=0.47356908494349254}
    @bgermainx my phone went swimmin wit me -> {neg=0.25, pos=0.75}
    @ardalis #TweetDeck spawns itself wherever it feels like on my computer. Never where I left it last. Very annoying -> {neg=0.16666666666666666, pos=0.8333333333333334}
    @brandillio that would be most ideal. However...now I dont see myself leaving before 3  Gonna have to take a rain check -> {neg=0.3076923076923077, pos=0.6923076923076923}
    "@Donny_Danger I know right  Honestly -> {neg=1.0, pos=0.0}
    @blahsodmg lolll yeaaaaaa hate uu....  give me summ lmao -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @djohns3bc oops i mean hi David!! i have no idea who i just twitted a few min. ago -> {neg=1.0, pos=0.0}
    #yycphotobook  I missed your spot today.  Will someone be putting online? -> {neg=0.4666666666666667, pos=0.5333333333333333}
    "@annierexia I know right -> {neg=0.5264309150565075, pos=0.47356908494349254}
    "@carlbob - got on on Sunday evening.  But for some reason -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@BrionyMayMcFly Yeah -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@brainpecking I missed the ticket order cutoff -> {neg=0.9072164948453608, pos=0.09278350515463918}
    "@chuongvision Got the airport before the TC came out.   Gotta love TimeMachine though. Switch to Mac -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @Arbalestx NATM2 &amp; TRANSFORMERS2  eh you spelt my name wrongly on the forum la. so hurt.  TMR WILL COME okay it has to! -> {neg=0.28150736211629646, pos=0.7184926378837035}
    @bdafoe  you never took me to buy shoes -> {neg=0.8823529411764706, pos=0.11764705882352941}
    "@BeesLikeZeebras Oh -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @actuala Oh no! Sammy Sosa too? That sucks -> {neg=1.0, pos=0.0}
    "@DominicMonaghan X-Men was amazing!  Too bad you didnt have more screen time -> {neg=1.0, pos=0.0}
    @alexandrachiu is it?! It felt colder this morning!  BRING ON THE SUNSHINEEEEEE  x -> {neg=0.5833333333333334, pos=0.4166666666666667}
    @BrianKurtz What about UK. Still not on itunes. -> {neg=1.0, pos=0.0}
    "@ComcastSherri the problem -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @Breathtakiiing awwwwww. i wish you could start now. -> {neg=0.9411764705882353, pos=0.058823529411764705}
    @Dare2Diva Does that mean ye ain't trying to be in my video anymore? -> {neg=1.0, pos=0.0}
    "@bavster_twit Ah well -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @Cookleta @blacknight010 okayyyy i have 2 go to sum boring relatives  gotta get dressed b4 mom comes...take care luv ya sleep tite byeee -> {neg=0.7661016949152543, pos=0.23389830508474577}
    ...@Work  but... COME ON LETS TWEET AGAIN... TwitterÂ´s have a nice &quot;Day&amp;Night&quot;.. -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @alyssa_day I wish I had a Borders near me. -> {neg=0.9444444444444444, pos=0.05555555555555555}
    @ work tired i dont want to be here i need to be studying for finals -> {neg=1.0, pos=0.0}
    :: going to burn extra damn calories tomorrow after eating junk food. All the work out this week was gone by 15 minutes junk pleasure. -> {neg=0.2, pos=0.8}
    @angiegirlrivera tell me about it! Me to -> {neg=0.35294117647058826, pos=0.6470588235294118}
    @circelilith me want a kitty that isnt named Guy  so jealous! -> {neg=0.6, pos=0.4}
    @bradiewebbstack nawwww poor bradiie  id stop at maccas for u!!! xx ily -> {neg=0.8, pos=0.2}
    @astallaslions no missouri dates  please come to st louis! -> {neg=0.9, pos=0.1}
    @___sid i call you like a million times today -> {neg=0.28150736211629646, pos=0.7184926378837035}
    "@danslily get u with the mercury -> {neg=0.9230769230769231, pos=0.07692307692307693}
    @chatterboxcgc Just remembered to check and I do NOT think our Pokens connected. Bummer! My Poken remains friendless. -> {neg=0.42037586547972305, pos=0.579624134520277}
    "&quot;Come with me if you want to live.&quot; Somehow -> {neg=0.0, pos=1.0}
    @bexblonde trying to find it!!   I know Chris gave it to me as I was leaving Plymouth and I shoved it in my pocket.... -> {neg=0.42037586547972305, pos=0.579624134520277}
    "&quot;Bitch run -> {neg=0.2613941018766756, pos=0.7386058981233244}
    : should I go to the party or not? -> {neg=1.0, pos=0.0}
    no more arrested development. i am sad. -> {neg=1.0, pos=0.0}
    I wanted to go cinema...... -> {neg=0.7217709179528838, pos=0.27822908204711616}
    "*sigh* awake too late -> {neg=0.7217709179528838, pos=0.27822908204711616}
    @chibi_sora is jealous i cant come round for parties -> {neg=0.9, pos=0.1}
    "@cindydisaster yeah well -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @brianangelday26 but u have fans that love you! Don't get rid of twitter for your fans -> {neg=0.5833333333333334, pos=0.4166666666666667}
    "@_cza heeeeey where did you disappear to -> {neg=0.28150736211629646, pos=0.7184926378837035}
    "@daniellefecci &quot;You guys could have fun in a cardboard box&quot;.. I miss you already -> {neg=0.7, pos=0.3}
    @BrandyWandLover  Dont want to talk about it babe  Wanna escape it all! How much are flights to mars?!??! Hows my girl? xxxx -> {neg=0.0, pos=1.0}
    @ClareEH Looking forward to seeing you too. No coffee time on sat though. Am on a close -> {neg=0.28150736211629646, pos=0.7184926378837035}
    @alaina_ unfortunately -> {neg=1.0, pos=0.0}
    "@clio_jlh it's so inexplicable to me that katy gets that treatment. they respect kris's faith and adam's bfs -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @carneymusic unfortunately no -> {neg=1.0, pos=0.0}
    "@abc -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@dante_knight I'll guess David -> {neg=0.45217391304347826, pos=0.5478260869565217}
    @DavidArchie Is it now posted on DavidArchuleta.com?I can't find it . -> {neg=1.0, pos=0.0}
    i don't like thunderstorms! just let me hide! -> {neg=1.0, pos=0.0}
    "@AngMoGirl Funny -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @angelitapita i know!! i didnt know til i saw these tweets! its soo sad -> {neg=1.0, pos=0.0}
    @artmind I understand... I have to clean the bathroom too this morning -> {neg=0.8, 
    ...skipping 1059130 bytes...
    lt...wanker...ghgo p ]# = kujhvc -> {neg=0.40118577075098816, pos=0.5988142292490118}
    @dwr  I think my mom is gonna switch over so I'll give her my 3G and keep the 3GS. Glad to hear it's worth the upgrade. -> {neg=1.0, pos=0.0}
    @chavezja don't think like that -> {neg=0.7655986509274874, pos=0.23440134907251264}
    "@drewseeley I really want to come see you -> {neg=1.0, pos=0.0}
    @DonnieWahlberg I'm trying..i'm trying...but nobody's helping me out... -> {neg=1.0, pos=0.0}
    @_boo naw! Did it really? -> {neg=0.0, pos=1.0}
    "@_micster Dawww -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @bruna_wht Will you gett off? -> {neg=0.28150736211629646, pos=0.7184926378837035}
    @abs1399  I hope today gets better for you.  *hugs* -> {neg=0.28150736211629646, pos=0.7184926378837035}
    "@adun50 -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @1txsage1957 your link to the kids and poverty is broken -> {neg=0.28150736211629646, pos=0.7184926378837035}
    ....ok. two.... noOo more! sighhhh..  stuffn my face wit chicken. -> {neg=1.0, pos=0.0}
    @26c4u You put my thing wrong though.  xD -> {neg=0.39740036318455507, pos=0.6025996368154449}
    #inaperfectworld those of us who work for UCLA or UC's wouldnt be getting a 4% or 8% paycut in the very near future -> {neg=1.0, pos=0.0}
    @DaveGorman Just like my pation -> {neg=1.0, pos=0.0}
    "@alachia I feel ya -> {neg=1.0, pos=0.0}
    @AskDayton The principal's bad! -> {neg=0.20468154599891128, pos=0.7953184540010887}
    @DjBingy hey!!! how come ur not following me?? -> {neg=1.0, pos=0.0}
    "@alanmcnamee Well the sun is out -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @doodledawne I would like to go back in time and save someone -> {neg=1.0, pos=0.0}
    @colorsblend  I think Randy/Kara are more annoying than Mary eg -> {neg=0.41102756892230574, pos=0.5889724310776943}
    @chris_dangerous i am very sad now  x -> {neg=1.0, pos=0.0}
    @cameo1172 nope  didnt remember till 1030 -> {neg=0.6919929865575687, pos=0.3080070134424313}
    "@broganss plus i've got a bowls competition tomorrow and i just burn now-a-days. but i dont ike sunbathing tbh  idk why really -> {neg=0.5305591677503251, pos=0.4694408322496749}
    @djhealey1  ..thx but error to d link given  @twilightfairy @netra @sanjukta @nomadwanderer @partywithneha @shaaqT @tarushikha -> {neg=1.0, pos=0.0}
    "@Blend_Master5 it's just been one of those weekends and I've been very emotional all weekend...ugh -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @ashleytisdale what happened with blondiee! ??   the 2 dogs  r so beatiful and sweet! â™¥ -> {neg=0.9375, pos=0.0625}
    @bonafide_hustla I KNEW IT WAS COMING -> {neg=0.5264309150565075, pos=0.47356908494349254}
    #spbpuk other common failures to upgrade: dec CU had a corrupted dlls in it and hanged. Just my experience. -> {neg=0.40118577075098816, pos=0.5988142292490118}
    ... in actual fact I'm just preventing myself from becoming the next hunch back. sorry old ppl -> {neg=1.0, pos=0.0}
    @ChrisVanEtten yikes! what happened? Brad and I are on the lookout for a car as well. We dont have one -> {neg=0.35294117647058826, pos=0.6470588235294118}
    @djhavanabrown heyy havana yeah its so cold   but im in a warm house  melbourne rocks!!! -> {neg=1.0, pos=0.0}
    ....almost 2 months now after the approval of the refinance of our mortgage.  Still haven't closed yet.... sigh -> {neg=1.0, pos=0.0}
    "@ChrisEfs Its okay yeah -> {neg=0.39740036318455507, pos=0.6025996368154449}
    ".. very disappointed in the Cavs ... Playing terribly. Either the team wakes up quickly -> {neg=0.4, pos=0.6}
    @CannonGod I do that all the time with my legs....it's absolutely agonising -> {neg=0.41102756892230574, pos=0.5889724310776943}
    @cindy_nyc eh--Im in LA @ the moment just flew in from Hawaii now going on to H-Town. Yuck -> {neg=0.624390243902439, pos=0.375609756097561}
    "@amieewhitney Oh -> {neg=0.39740036318455507, pos=0.6025996368154449}
    #Silverstone Signal so bad cannot get updates -> {neg=0.40118577075098816, pos=0.5988142292490118}
    @Dinero562 take a few hits for me.. At the in laws n can't smoke unfortunately -> {neg=1.0, pos=0.0}
    "@bufffiiieee no -> {neg=0.5305591677503251, pos=0.4694408322496749}
    @aplusk fuck you @markhoppus &lt;3s @lttheninja mma  @hoodlum12 cake later  @TomDelonge im on Mod blast yeah thats all i tweet haha fuck you. -> {neg=0.28150736211629646, pos=0.7184926378837035}
    "@artistikem That would make sense.  Tragic -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@carriehartford ....three new three in a week is not good though. Two would have done another month -> {neg=0.5305591677503251, pos=0.4694408322496749}
    "@ChristieCiarlo  Hang in there Ciarlo. July's the month of grilling -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@dailybranflakes Bre -> {neg=0.45217391304347826, pos=0.5478260869565217}
    @bodysnatcherss I don't know. Well I do but its too long of a story and now its 10am and my eyes hurt too much to get up -> {neg=1.0, pos=0.0}
    "@AshleyDolltm I don't mind so much -> {neg=0.4375, pos=0.5625}
    @burbleon i wud love to continue this but my mind is completly blank -> {neg=0.7, pos=0.3}
    "@CelticCrossing Of course -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@andyclemmensen awww i wish i could be there -> {neg=0.8181818181818182, pos=0.18181818181818182}
    @dilaralovesjb ahah why do you hate mee  lol -> {neg=1.0, pos=0.0}
    @ameonna10 oh my chocolate!!  he shall replace my precious chocolate. -> {neg=0.20468154599891128, pos=0.7953184540010887}
    @bree_marchelle i have no money and no food in the house  cereal it is -> {neg=0.9, pos=0.1}
    @AlphaJD i just need to know what mine is -> {neg=0.5625, pos=0.4375}
    @Bonnie311 don't worry if you didn't. Jim and I didn't get ours yet -> {neg=0.0, pos=1.0}
    @ddlovato ME EITHER! not fair.. -> {neg=1.0, pos=0.0}
    @ashleyluvsjbvfc I still miss Thomas -> {neg=0.9072164948453608, pos=0.09278350515463918}
    @Daphne38 Just after high school my hair went past my shoulders stayed that way until last year. My sister cuts it now and won't allow it -> {neg=1.0, pos=0.0}
    @DanMerriweather i want to go the show tonight  hook me up! -> {neg=1.0, pos=0.0}
    @ashy_109 Outlook not so good -> {neg=0.6919929865575687, pos=0.3080070134424313}
    my arm nearest the window is about to freeze off! but the rest of me is warm... such a horrible feeling. #jonaskevin -> {neg=0.8947368421052632, pos=0.10526315789473684}
    @Buffy73 sorry babe it is a rotten song to be stuck! Those Jesse songs are bad too  I'd honestly rather hear Britney. -> {neg=1.0, pos=0.0}
    "@DwightHoward i really am in love with you -> {neg=1.0, pos=0.0}
    "@badgersprite BPD Musical - Be afraid. Be very afraid. At work. No chatzy tonight -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@Chibi_rizzy  I miss you -> {neg=0.9072164948453608, pos=0.09278350515463918}
    @DiSCOLENA argh it just ended. Of course did My computer decide to Spasm  But I guess you would have been able to listen to it -> {neg=0.875, pos=0.125}
    @clifside twitnoob needs help: how come i can't see your message to me?  ok inulit ko lang. wala pala nagtatagalog dito sa twitter hahaha -> {neg=1.0, pos=0.0}
    @DJJoeG LB Pride is fun.  You should definitely check it out.  I'm 70 miles north of LB. Not going to make it this year -> {neg=1.0, pos=0.0}
    @CateP36 I stopped that the other day. It was too much too soon. That stunt created big problems lol. I have to wait awhile to do it -> {neg=0.5454545454545454, pos=0.45454545454545453}
    Not feeling so hot... -> {neg=0.7217709179528838, pos=0.27822908204711616}
    "@cArtPhotography nope still all messed up   @couldbestudios is going to try to fix it this weekend -> {neg=0.8947368421052632, pos=0.10526315789473684}
    "@camiknickers Oh christ -> {neg=0.39740036318455507, pos=0.6025996368154449}
    ....why did i faff with my pic!  i faceless nowee -> {neg=0.4375, pos=0.5625}
    @ceetee94 meeee tooooooo -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @alenanichols i may be requesting your help for cook tickets this week. -> {neg=0.28150736211629646, pos=0.7184926378837035}
    @_micahlyn FML? Fuck My Life? Cause if that is what it means... *hugs* -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @ yeah if I can get tickets! I have to wait to buy them since I don't have a paid job right now -> {neg=0.624390243902439, pos=0.375609756097561}
    @bratta @cawake Hoping next time I'm not stuck at work!! -> {neg=0.6919929865575687, pos=0.3080070134424313}
    "@AnwaaKong Also -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @cfieds  i'm sorry to hear that -> {neg=1.0, pos=0.0}
    "@cathead27 though -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @DJMeat im a very good friend but when it comes dwn to buying ppl things it seem as im a VERY BAD FRIEND -> {neg=1.0, pos=0.0}
    @cooloutrageous OMG I heart PF Changs that's one of my favs we don't hve one here tho... no fair -> {neg=0.6919929865575687, pos=0.3080070134424313}
    @CHANGE12 work is dumb slow  no one want come by a car. -> {neg=0.2, pos=0.8}
    @bloodyironist ugh D: yes? no? i dont know? -> {neg=1.0, pos=0.0}
    @blowmyheartupxx yeah nga!   what's your second choice? -> {neg=0.28150736211629646, pos=0.7184926378837035}
    @actingbug haha. It's not so bad. Better than the previous one. Last Vanessa came.. and I went. Crazy kids. I made them cry. -> {neg=1.0, pos=0.0}
    I am actually crying now because of sore throat. -> {neg=0.2727272727272727, pos=0.7272727272727273}
    @DemiHere  DEMI HERE !! You're gonna get that meeting no worries  Can I give you smth to give for Demi PLZ ! I am a Tunisian fan -> {neg=1.0, pos=0.0}
    @BizMarquee Glad you liked Wild Berries - I so wanted to like it but my experience was atrocious -> {neg=0.28150736211629646, pos=0.7184926378837035}
    @_tan_ i know  i think we traded it in when buying sims 3 for a discount.  have you tried #sims3 yet? i think it's a good replacement. -> {neg=0.35714285714285715, pos=0.6428571428571429}
    I'm really going to sleep now -> {neg=0.624390243902439, pos=0.375609756097561}
    @Cheryllasseter Please no. Think I would rather here about Miss California -> {neg=0.6919929865575687, pos=0.3080070134424313}
    . @gothicat  I blame the compressed shredded cardboard you've eaten *hug* -> {neg=0.28150736211629646, pos=0.7184926378837035}
    @arpanshah definitely - i never seem to have a problem going out - always on the way back -> {neg=0.4418604651162791, pos=0.5581395348837209}
    @boxofchocolates Good Sunday morning to you too! Pretty lousy here as well -> {neg=0.28150736211629646, pos=0.7184926378837035}
    ".@unknownfilms and I aren't in the best of moods this morning...we can't be bc we're too tired -> {neg=1.0, pos=0.0}
    "@beckie_illson Oh -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @colewolsch yha but not anytime soon -> {neg=0.6919929865575687, pos=0.3080070134424313}
    @bayliedanielle what about me? -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "@CobwebsStir It's a date! However -> {neg=0.20468154599891128, pos=0.7953184540010887}
    @AlexAllTimeLow whyyyy aren't you guys doing Philly warped tour? -> {neg=0.3782771535580524, pos=0.6217228464419475}
    "&quot; lesbian -> {neg=0.2613941018766756, pos=0.7386058981233244}
    @amymthomas That is really sad. You didn't bring me any the other day though -> {neg=1.0, pos=0.0}
    @DDubsTweetheart  I really want to go too. First time to Boston + first ever NKOTB show + with you = AMAZING &amp; FUN! -> {neg=1.0, pos=0.0}
    @ArtyTheCat @princessPdexter has captivated everyone here...my dad dislikes me i think...he says i look like babe the pig... -> {neg=0.6, pos=0.4}
    @bennyling decryption no reply -> {neg=0.6919929865575687, pos=0.3080070134424313}
    "@AlexislovesJB Oh come on! Don't hang up on me!  I sorry buddy -> {neg=1.0, pos=0.0}
    @3littleladies  I feel the same way    I hate being sick!  'Hope you're feeling better son! -> {neg=1.0, pos=0.0}
    "@chelseasms They already left without me -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @aliasgrace I'm going to have to pass this time.  I have soccer mom duties. -> {neg=0.8, pos=0.2}
    @Dravie Im so not listening. cant believe I'll be missing spn tomorrow  it'll be worth it to not have to work on my bday though -> {neg=1.0, pos=0.0}
    @aplusk I tried to hear it but it's unavailable1 So I cannot tell you my opinion! -> {neg=0.36363636363636365, pos=0.6363636363636364}
    @beauche no -> {neg=0.6919929865575687, pos=0.3080070134424313}
    @clickwindrepeat she has a bald spot on her back that she won't leave alone.    @ChiefDork whipped up the piece of couture. -> {neg=1.0, pos=0.0}
    @alesplin oh no!  I'm so sorry.    Sarah and I hope that you and Nicole are doing ok. -> {neg=1.0, pos=0.0}
    @downunderjosh - What kinda internet speeds do you guys get down there? Thats pretty sad -> {neg=1.0, pos=0.0}
    "@allgutsnoglory no. my mom wont let us. we have held hands -> {neg=0.5305591677503251, pos=0.4694408322496749}
    must try to raise some funds to get better computer present one like me worn out. also need a publisher/literary agent? -> {neg=1.0, pos=0.0}
    "@braydensmama007 he thinks something is wrong in my brain telling my pituaries how much cortisol to make and he increased my byetta -> {neg=1.0, pos=0.0}
    &quot;This version of the iPhone software (2.2.1) is the current version.&quot; WWWWWHHHHHHHHHYYYYYYYYY?????!!!!! -> {neg=0.0, pos=1.0}
    @bookworm_su What?! I'm sorry to hear that!  I'll be praying for you and your family. &lt;3 -> {neg=1.0, pos=0.0}
    "@donnamcaleer Unfortunately -> {neg=1.0, pos=0.0}
    @bizarro7777.  That's the benefit of being an insomniac.  Oh wait.  There are no benefits. -> {neg=0.6919929865575687, pos=0.3080070134424313}
    @cassade i know -> {neg=0.8, pos=0.2}
    "@cessii haha  but the downside is.... i have to give up the green day gig  but i love muse a tad bit more than green day -> {neg=0.1, pos=0.9}
    @chrisj1k u not comin u playin us -> {neg=0.6919929865575687, pos=0.3080070134424313}
    @chartreuseb  Cheaper to subscribe to the magazine than the website.  Great business model ESPN -> {neg=0.39740036318455507, pos=0.6025996368154449}
    @Brieanna187 fathers day  I'll be at my dads all day -> {neg=0.39740036318455507, pos=0.6025996368154449}
    "&quot;It's like a video game -> {neg=0.2613941018766756, pos=0.7386058981233244}
    "@billythekid me too -> {neg=0.75, pos=0.25}
    @CheesyBBuisket &amp; @xleonieex u realli upset meh 2day    @Alex_Yer  u wer soo funny 2dayy.. u realii cheerd meh up! thnx bbes xx -> {neg=0.16666666666666666, pos=0.8333333333333334}
    @2kutekreations That's not good! Need to get something in there for *YOU* so that you can get off that wheel!!! -> {neg=0.9166666666666666, pos=0.08333333333333333}
    @aWorldApart  Sorry to hear your you-know-what still hurts. -> {neg=0.8, pos=0.2}
    i feel like crawling into my bed under my covers listening to my ipod and having a cry session.. -> {neg=0.8271028037383178, pos=0.17289719626168223}
    @benji_84 yah I def miss them..now one of them is just on someone else's trak... -> {neg=1.0, pos=0.0}
    @ARKATECHBEATZ because of my passion party this past wknd I have a box n a half of canned pepsi sitting in my room...  with NO water!! -> {neg=0.5, pos=0.5}
    It's raining again. -> {neg=0.7217709179528838, pos=0.27822908204711616}
    @CatalystLeader Aww man! Y'all should've hid one down here in South Florida like Miami. -> {neg=0.5625, pos=0.4375}
    #haveyouever wondered why danity kane really broke up -> {neg=0.40118577075098816, pos=0.5988142292490118}
    @BAC18 i did that last week by myselfff. sucks mann -> {neg=1.0, pos=0.0}
    @darren_bousman  ok now i guess I better go watch Drag Me To Hell. I still havent even seen Terminator Salvation though -> {neg=1.0, pos=0.0}
    @agriggs8 Damn  I saw someone twittered about the passes but didn't know to check my email. I so could've gotten some. maybe *shrug* -> {neg=0.0, pos=1.0}
    "@DanaLouLou was it real -> {neg=0.23076923076923078, pos=0.7692307692307693}
    "@ebassman  I wish! I need a jet  See ya in Irvine -> {neg=0.4166666666666667, pos=0.5833333333333334}
    you can try and forget me. -> {neg=0.7217709179528838, pos=0.27822908204711616}
    @a_dexter  Nurse Jackie looks like a decent show. Too bad I don't get Showtime. -> {neg=0.7655986509274874, pos=0.23440134907251264}
    &quot;can you believe Trump owns Monday night Raw and he made it commercial free tonight&quot;.......wow could the night get any better -> {neg=0.28150736211629646, pos=0.7184926378837035}
    
```

Returns: 
```
    58.67%
```
