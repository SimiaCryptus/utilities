First, we load positive and negative sentiment tweets into two seperate models

Code from [TrieDemo.java:369](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L369) executed in 4.02 seconds: 
```java
      ArrayList<TweetSentiment> list = new ArrayList<>(TweetSentiment.load()
              .filter(x -> x.category == 1).limit(testingSize + trainingSize).collect(Collectors.toList()));
      Collections.shuffle(list);
      return list;
```
Returns: 
```
    [TweetSentiment{title='#FF THX ALL OF YOU!  YOU ARE ALL AWESOME!!!!!!!! @FollowMe_RnBE @davidgideon @yuliakatkova @CharPrincessa'}, TweetSentiment{title='"@ABeautifulMind1 Fingers &amp; toes crossed'}, TweetSentiment{title='@AngelMordeno You like him!!! Admit it!'}, TweetSentiment{title='"@atmanes Enjoy. No spoilers please'}, TweetSentiment{title='@BabeHound hey I don't remember that deal lol but at least I let u noe that I wasn't coming......so that's not fair'}, TweetSentiment{title='"@amruth92 well! Awesome. I gotta go'}, TweetSentiment{title='@barfbutt hmmmmm.... we'll see.'}, TweetSentiment{title='"&quot;Why should I gain from His reward? I cannot give an answer. But this I know with all my heart'}, TweetSentiment{title='@armySLOB you'll have to let me know how you and Markie's night ended'}, TweetSentiment{title='@CITYPUBLICITY  What's #IM0509? Looks like some interesting people are going'}, TweetSentiment{title='"@BCerInToronto So again'}, TweetSentiment{title='@CarlPlage Yip  Well you've got to slow down sometimes and enjoy lifes little luxuries'}, TweetSentiment{title='#followfriday @OHatDL @KyleJDufield - thanks guys - you're the best'}, TweetSentiment{title='@bettinalove Haha. I think being able to see Carlisle's natural skin tone on the back of his neck against all the white makeup bugged me'}, TweetSentiment{title='@AlexFraiser good tip this worked'}, TweetSentiment{title='#followfriday @atarikari just because she's good Twitter people.'}, TweetSentiment{title='"@AlgorithmFundFX No'}, TweetSentiment{title='@CocaBeenSlinky Thankyou for your lovely message. I do want to do it! I will go on the site now and have a look again.'}, TweetSentiment{title='@arthurandesya oh okaaayy. hahaha my bad! lets meet up next week soalnya tgl 23 im coming back'}, TweetSentiment{title='@bounder not an inspiring away kit is it'}, TweetSentiment{title='" @ssandall That's it! Removing bots is like weeding a garden. Not fun'}, TweetSentiment{title='@daisyjanine Yeah I'm defo thinking about it!'}, TweetSentiment{title='@caseeyrae the shire is the shizzle.'}, TweetSentiment{title='@BillBoorman Now THAT's celebrating in style!'}, TweetSentiment{title='"@chelseasms same here'}, TweetSentiment{title='......and vote for my Background http://twitterbackgroundsgallery.com/2009/05/28/bllq21/'}, TweetSentiment{title='@arjbarker   You rocked on Rove'}, TweetSentiment{title='"@andysowards haha - I know what u mean! Hope u have a great week'}, TweetSentiment{title='@aplusk what does ding dong ditch mean? sorry for now knowing.'}, TweetSentiment{title='@ClaireBoyles You are always saying good morning when I'm about to say Good night.  Something very Beatles about that'}, TweetSentiment{title='"@amykate morning  ..... why did you mention work? booooo - theres still half the weekend left! lol (that said'}, TweetSentiment{title='"(x) @LaylaNatalie ouch'}, TweetSentiment{title='"@CarrieGutfeld I'm not sure'}, TweetSentiment{title='#followfriday Many thanks for the love  @bigdawg10 @avenueofthearts @labelladiva @linnetwoods @michaelheiniger @rleseberg @Follow_Steph'}, TweetSentiment{title='"@amandabynes I already have'}, TweetSentiment{title='@cassieventura I agree there is something a lil off about that...not sure what but something! Hope your hotel stay is going well'}, TweetSentiment{title='@angiecas sounds like it'}, TweetSentiment{title='"@9_6 thought of'}, TweetSentiment{title='#ATV-BS just got back from bestbuy. Got the Geforce 9400GT'}, TweetSentiment{title='"@anthonyblando Ah no'}, TweetSentiment{title='"@daniela_aiton Yeah'}, TweetSentiment{title='@cliffysmom Ahem.... 1970'}, TweetSentiment{title='@abooth202 I mentioned London Irish the Rugby Team and now they are following me    Hmm lets take this all the way. So.......Brad Pitt!!'}, TweetSentiment{title='@Cassie2757 did you get my email?  how was the aquarium btw?'}, TweetSentiment{title='"@93octane i IE8 in a virtual machine'}, TweetSentiment{title='@antonie now that's what they call suffering for Jesus (and/or a slave free world)'}, TweetSentiment{title='@amandanolan awe  too bad i was watching this crazy magic show lol'}, TweetSentiment{title='"@bryan_kavanagh damn spinlocks'}, TweetSentiment{title='"@ AmandaNaomi We're indochine-ing tommorrow'}, TweetSentiment{title='#dontuhateitwhen i start twittering random lame ish.. LOL... just unfollow'}, TweetSentiment{title='@beckyinthesky  congrats on starting the CPA Exam process! VSCPA is on twitter too. Follow us @VSCPANews.'}, TweetSentiment{title='@AprilShotYou hell yeah!!!!!!! im excited too'}, TweetSentiment{title='@CoachDeb LOL...your first quake was in bed on Oahu...too funny.'}, TweetSentiment{title='@Chad_Sway  twitter should have video chat... it'd be fun!!'}, TweetSentiment{title='#3hotwords - fancy a 'coffee''}, TweetSentiment{title='"@AnnikeDase I'm not dressing up'}, TweetSentiment{title='@Akelaa aww thanks for caring'}, TweetSentiment{title='"@DaleChumbley no kidding'}, TweetSentiment{title='#vanesse says: lets enjoy nice weekend together   Lets make things better together'}, TweetSentiment{title='@Bubba_Q Haha see?? Millie DOES love me and I am ALWAYS KIND to her!'}, TweetSentiment{title='"@3rdLife Woohoo! Would be great to see you again  Great location'}, TweetSentiment{title='@amysmorris did yoo were fromm.. i hatee thatt too dont be in the business if yoo dont want to appretiate the fans that you have  x'}, TweetSentiment{title='&quot;i think she will like me&quot;- Maddison Buzer about @taylorswift13 ... quote of the day'}, TweetSentiment{title='@angryman78 yeah just had to vent'}, TweetSentiment{title='@Creepy_E_98KUPD Just as long as you cuddle with me afterwards.  It's the only way I'll ever do that with you.'}, TweetSentiment{title='"@bjack25'}, TweetSentiment{title='"@BlueEyedGirl18 hey  yer not bad thanks'}, TweetSentiment{title='@AdeleLouise wow and I thought you'd be supporting a hangover today'}, TweetSentiment{title='@billybofh That's cheered me up no end'}, TweetSentiment{title='@agentsnow glad to hear you enjoyed it...now read my article on Emergent Village'}, TweetSentiment{title='"@ariestotle yeap'}, TweetSentiment{title='@benwin we'll make our own wilderness explorer shirt!'}, TweetSentiment{title='&quot;Theres a light on in chicago and i know i should be home&quot;...miss you mommy! Happy mothers day.  please stop calling me Crissy. Thanks.'}, TweetSentiment{title='"@AlexAllTimeLow ohh snapp'}, TweetSentiment{title='"@ashleytisdale Ashley ? You're in Germany ?? That is so cool I'm in Germany'}, TweetSentiment{title='@coreyfbaby @jmtorrey Steeler fans are everywhere.  People are just smart and pick the greatest team ever'}, TweetSentiment{title='"@_Kaden_ Hi  I've no idea  Lazily'}, TweetSentiment{title='@Audrey_O is nat coming too? let me know how many of us are really going. hotel near for more $ or 4 miles away for under $100?'}, TweetSentiment{title='@Armano they were. But were liquidated in the restructuring. Got 5 cents on the dollar for a LI venture group.'}, TweetSentiment{title='@2sweets I usually talk on here so I'm trying to figure out the boards there now. We're both from SE AK orig. so that's close enough'}, TweetSentiment{title='" &lt;--- That is all'}, TweetSentiment{title='"@arsenalarran really struggling with that arsenal thing ;-)  oh well'}, TweetSentiment{title='@babygirlparis http://twitpic.com/6vq0e - LOL They defnitley look like there enjoying life'}, TweetSentiment{title='@chomaee Thanks for the follow Friday'}, TweetSentiment{title='@AuthenticStyle Thx for your encouraging tweets today!'}, TweetSentiment{title='"@Charliegirl11 I need to get season one off you'}, TweetSentiment{title='@aulia &quot;alternate means of acquisition&quot; sounds sorta noble'}, TweetSentiment{title='&quot;Man that dude is creepy.&quot; HAHA. Oh Casey! Then Chuck still looks longingly towards where Sarah went  #chuckmemondays #chuck'}, TweetSentiment{title='"@amysav83 i'm very well thanks'}, TweetSentiment{title='@alphonso I'm in SF right now'}, TweetSentiment{title='@andyfromhell Hi Andy  Are you ready to rock this evening =D ?'}, TweetSentiment{title='@Bunnieblog heh. we're useless together. we need to go hit some balls'}, TweetSentiment{title='@caseydamnmorgan @azbutterfly24 aw thanks'}, TweetSentiment{title='!@dossy that's what i was thinking 1x/hr and move would only work if the space was available'}, TweetSentiment{title='@Buffy73 http://twitpic.com/6hb2y - You're just one person!  LOL ...I want a mangoooo.'}, TweetSentiment{title='&quot;Not everybody has to go college. Pizzas don't deliver themselves&quot; haha'}, TweetSentiment{title='@alsiladka... naah @khouryrt is a sweet person! she never harms nobody!  (I hope I said something nice rita and true!)'}, TweetSentiment{title='"@Ashleegaston yes'}, TweetSentiment{title='@columalmighty I'm nearly at school! I'm on the bus'}, TweetSentiment{title='@AmyNicolee Really? How do you know? I saw the left-handed store for the 1st time in SF recenty'}, TweetSentiment{title='@bellalove Thank you &amp; also I'm sorry to hear of your sister. But it's comforting to know someone else writes too.'}, TweetSentiment{title='&quot;the sun is in the sky and it is gonna be a gloriuos day&quot; so im going to shower and get some vitamin D  its hotter/as hot as yesterday'}, TweetSentiment{title='"@_supernatural_ Ah  So really'}, TweetSentiment{title='@coreyhaines it was awesome having you here! Thanks a lot for joining in!!!'}, TweetSentiment{title='@crysolivarez Well enjoy the day off'}, TweetSentiment{title='@cwbtoad Thanks bro.'}, TweetSentiment{title='@ACLAZ92 #pens wins ALWAYS make a person feel better!'}, TweetSentiment{title='@BobWarren hi Bob! How are ya? Just finished 4hr run!'}, TweetSentiment{title='@BrokePimpStyles Hi! Happy Sunday! How are you?!?!  Thinking of adding one more day to my weekend...'}, TweetSentiment{title='@brockjohn09 lol i only shoot like every three months haha bcuz of skool im almost done but okie dokie!  no prob lets do it'}, TweetSentiment{title='"@BlatzLiquor he will be thr! We need suggestions 4 R own little beer sampling party ths evening'}, TweetSentiment{title='"@AaliyahLove69 You're gonna hit 1'}, TweetSentiment{title='"#followfriday @foyboy 1) She had Skips on her face earlier this week'}, TweetSentiment{title='"@billyraycyrus Right Back At Ya ;) today everythings closed in norway'}, TweetSentiment{title='@AmandaRumm ; omggg thats a double chance that i'll be able to goo. sweeeeeeeet'}, TweetSentiment{title='"@creaturekebab Yeah'}, TweetSentiment{title='@alex_washington you're welcome'}, TweetSentiment{title='@clarasdiary Sara won! Awesome right??? I'm so glad!  she's beautiful!!!'}, TweetSentiment{title='i have a buzz'}, TweetSentiment{title='@AdamSurak Dï¿½ky @vbulant Dï¿½ky p?ehlï¿½dl jsem ten VirtualBox v tweetu  @karimartin Vyzkouï¿½ï¿½m ten VirtualBox. Dï¿½ky vï¿½em ;)'}, TweetSentiment{title='@bsaunders Let us know if you have any questions  ^AG'}, TweetSentiment{title='"@AdPaid Yeah'}, TweetSentiment{title='@_iDANCE19 I fell in love with one!  This one.. when he was little. AMAZING! http://bit.ly/17ifJI  &quot;Tell me Why&quot;'}, TweetSentiment{title='"@aquamage Profile hopping. Wow'}, TweetSentiment{title='@_alps actually i gave up when I knew I could not google a photo..u know that of that veteran cricketer'}, TweetSentiment{title='@blitzy_UK Hi!!!! Sadly I'm off to bed.. Wish you also a awesomely positive and happy day.. when ever that is for you'}, TweetSentiment{title='@biancaduhh aww. i think someone put it up on youtube lol'}, TweetSentiment{title='"@clvrmnky: s/coffee/beer.  go on'}, TweetSentiment{title='@AmberLorraine_ haha amberr u neeeed to tell him!!!  &lt;3'}, TweetSentiment{title='"@_supernatual_ Give me some Dean tied up Pron'}, TweetSentiment{title='#alexisonfire &quot;young cardinals&quot; â™« http://twt.fm/143932 -- can't wait 'till the new album comes out'}, TweetSentiment{title='"@caitlyndewar oh thanks'}, TweetSentiment{title='"@alyssarenee_ poor girl'}, TweetSentiment{title='@Cinequest Checking my schedule! Want to be there too!'}, TweetSentiment{title='@bargainr Sounds sinister to me!'}, TweetSentiment{title='"(@EvaIsOnFire) oh cool'}, TweetSentiment{title='"@Anishaflower THANK YOU! My feet and legs are aching'}, TweetSentiment{title='"@angelbear7 Getting back online'}, TweetSentiment{title='@billt thanks for your support'}, TweetSentiment{title='"@alandavies1: Technically your contract is with the Apple store'}, TweetSentiment{title='"@BeyondBeads  agreed'}, TweetSentiment{title='&quot;I hid under your porch because I love you&quot; ... I want a dog so hard.'}, TweetSentiment{title='@BrandyandIce Raised just over 2.5k...so think that counts as lots!  x'}, TweetSentiment{title='#inever had sex w | 2 bitches at once.... ok im lying phaha. i love females'}, TweetSentiment{title='@brownsugakisses  i miss u guys and i miss complaining with u about how long our hair used 2 b LOL we still look fab tho!'}, TweetSentiment{title='@angelstar1632 Have a good night  Sweet dreams'}, TweetSentiment{title='@AutumnDances Oh I hope your nephew does well at tryouts!'}, TweetSentiment{title='@babyjobamboo @littlebirdphoto Congratulations on your stunning photo shoot! Such a great showcase of both skills and product'}, TweetSentiment{title='"@animatedme 'A 10 ten minute''}, TweetSentiment{title=':p ONLY HAVE 1 EXAM LEFT!!!!!!!!!! YAY..... its gunna be easy and summers coming  happy happy &lt;3'}, TweetSentiment{title='@christieelliott Of course!'}, TweetSentiment{title='just got home'}, TweetSentiment{title='@atrybe - i feel exactly the same dude. E3 looks superb this year.'}, TweetSentiment{title='@BoudoirSexToys Thanks very much here is hoping :S... just needs to be finished off properly run built and chicken wire added'}, TweetSentiment{title='"@16_MileyCyrus no'}, TweetSentiment{title='@bodylikemind We will'}, TweetSentiment{title='@andygriffwozere i had one for glasgow but decided i couldn't pay the train fare to be rejected lol. Was going to sing'}, TweetSentiment{title='@craw4d nothin a little robitussin won't kill  thanks for the well wishes now I feel better but can't sleep'}, TweetSentiment{title='@bsmrocks http://www.facebook.com/username/  almost certain you can do it for groups'}, TweetSentiment{title='@CaptainMurdo Everyone's entitled to their own opinion.'}, TweetSentiment{title='Follow me on Twitter!  http://bit.ly/P1I1h'}, TweetSentiment{title='@_Alectrona_ okay i'll let you off  xoxox'}, TweetSentiment{title='@CrazyCatLadie oooh - they did the apprehensive move up to kiss and then didn't - was awesome! Total tease  Audience loved it'}, TweetSentiment{title='"&quot;I haven't been pepper sprayed in an entire year.&quot; Line of the night.  Sleeping until'}, TweetSentiment{title='@Authentic973 nothing'}, TweetSentiment{title='"@bezotes thanks'}, TweetSentiment{title='"@chelseyyymarie aw! I didn't know it hurt that bad'}, TweetSentiment{title='I'm tired...gonna go to bed most probably...hah gonna watch some TV and then hit the hay!'}, TweetSentiment{title='@andrianjones11 Can I now delete the friends you added to my facebook account when I wasn't looking? or shall I up the prank stakes?'}, TweetSentiment{title='@BookingIt Thanks  But having a hard time justifying all the lovely sugar in said ice cream ;)'}, TweetSentiment{title='@crystallynn09 I know!! Yayy'}, TweetSentiment{title='"@daniel_eason Never seen him anything with a golf club before'}, TweetSentiment{title='@BrianNewberry Interesting idea  I'll make sure to pass that along.'}, TweetSentiment{title='@ausi1 Have a great day'}, TweetSentiment{title='@crystallmeth haha omg i was thinking the other day and remember in 9th grade when we had fcs together and she always confused us'}, TweetSentiment{title='@clueless_bimbo I guess so. I just don't like to leave things unfinished   Now if my job was acting...I'd have tons of fun with it hahha'}, TweetSentiment{title='"@autumnconfusion OMG!!!  You are so lucky O_O Waooww.  What do you expect'}, TweetSentiment{title='@ArfanChaudhry Then go to bed ... sleep tight'}, TweetSentiment{title='@_q Interesting website. Thank you for sharing it with me. I will continue to research it...   - Jay Jeter The Coming Storm'}, TweetSentiment{title='@Alyssa_Milano Your grandma rocks!'}, TweetSentiment{title='"@amazon_reviews Yep'}, TweetSentiment{title='@beccabarakitty aww... and 5264011 more bytes
```

Code from [TrieDemo.java:375](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L375) executed in 0.95 seconds: 
```java
      ArrayList<TweetSentiment> list = new ArrayList<>(TweetSentiment.load()
              .filter(x -> x.category == 0).limit(testingSize + trainingSize).collect(Collectors.toList()));
      Collections.shuffle(list);
      return list;
```
Returns: 
```
    [TweetSentiment{title='@chantellmarie get outta here with that BS'}, TweetSentiment{title='@Aussie_Nicole I know exactly what you mean. Earlier tonight my friend from Singapore found out she CAN'T come and do a M&amp;G w/me.'}, TweetSentiment{title='@drts but of course you exist! sorry we missed you on thursday night'}, TweetSentiment{title='"@bztak1020 actually'}, TweetSentiment{title='@BrentLauren I can't have dairy'}, TweetSentiment{title='@bellybuilders not sure what you mean by posts.'}, TweetSentiment{title='@DenzelBurks no u dont  i dont remebr atleast its all good i see how it is tho lol im jp jp i love yall both its jus funny 4 me'}, TweetSentiment{title='@DoubleyDee Awww. Thanks! But still.'}, TweetSentiment{title='@ChloePatrick4 Yes they crashed 640km northeast of Brazil's fernando do norohna island'}, TweetSentiment{title='@bumblydore dittooo. bad blood circulation?'}, TweetSentiment{title='@AbbyRo I would go in like 2 seconds if I wasn't 2300 miles away. I miss you.  And them. @megtastic1521 @samaracb'}, TweetSentiment{title='"@Anime81 LOL Do I remember? haha'}, TweetSentiment{title='... I'm gonna go home and cry now'}, TweetSentiment{title='"@bobryuu hey man!  The show is actually at the Park Grill'}, TweetSentiment{title='@cbain84 not started yet no  was good seeing ya today'}, TweetSentiment{title='craig's a cokehead'}, TweetSentiment{title='"@bexyboodle @mishfics @Jackattack008 @singhisiksha Did all you guys just have IT?? hahaha'}, TweetSentiment{title='&lt;-------- This is the way i feel right now...'}, TweetSentiment{title='@aprilyim @starwing wait for me to feel better as well! i cant be BKT'ing with a sprained neck / shoulder right?'}, TweetSentiment{title='@_glurch what if all those answers apply to me?'}, TweetSentiment{title='I hate you forever.'}, TweetSentiment{title='@dr_zu DAMN It...  I tried... not to be there.'}, TweetSentiment{title='"@all_stars_fade'}, TweetSentiment{title='@boudledidge before I scrolled down I thought you were talking about girl talk the dj and went  lol. But wow thats a pretty offensive blog'}, TweetSentiment{title='"@Cadistra NOOOO I'll miss you too much!  I went stag to my prom and all the high school and middle school dances'}, TweetSentiment{title='@comebackPAUL @KevinRuddPM he hasnt answered me'}, TweetSentiment{title='"@chrismarquardt Same thing happened to me with my d700 today'}, TweetSentiment{title='"@CosmicMother Agree with you about Susan.. I didnt watch the program'}, TweetSentiment{title='@billyraycyrus ups sorry... about that towel... my english is not as good as it should be... greetings from germany...'}, TweetSentiment{title='@cassielei05 it says on the website no signs/flags allowed in coliseum'}, TweetSentiment{title='@darciakw i know right?! already tried that and rick said no go.'}, TweetSentiment{title='"@_aizen I don't even know how I can help'}, TweetSentiment{title='@chocolate_dip the homies went last week &amp; said it was jumpin &amp; they were faded.. i cant party on thursdays no more'}, TweetSentiment{title='@DeansSexFiend not busy like rush hour but contantly need to moniter. I can access blip but other sites blocked. n can't use mobile.'}, TweetSentiment{title='worst moment of my lifeeee... WILSON IS MOVING TO COLORADOOOO'}, TweetSentiment{title='dad is leaving and wont be back til the wedding.'}, TweetSentiment{title='#firstrecord Behind the Green Door - shakey Stevens  dont' laugh to much'}, TweetSentiment{title='@ashleyryanfosho I have my exam 9:55-11:55... that's bullshit'}, TweetSentiment{title='"@3sixty5days Inoriteee'}, TweetSentiment{title='@AshleeNino Hahaha. You never ask me.'}, TweetSentiment{title='@DMB_ whatever'}, TweetSentiment{title='#Pakistan Army Chief takes a ride of F16. Menno ve chootaa chae da aay.. mai ve F16 da chhoota laina aay'}, TweetSentiment{title='"#RG09 for the women'}, TweetSentiment{title='@atrphotography Pulling teeth is traumatic!  My grandma always lied to me and told me it wouldn't hurt  Is she ok?'}, TweetSentiment{title='@benjamminspears re: grayson high students -  i don't want that'}, TweetSentiment{title='&quot;Each day's a gift and not a given right&quot; from the song &quot;Today was Your Last Day&quot;..pretty good insight!       Driving home from my vacay'}, TweetSentiment{title='@apsylus and yes United should be shut down for making your family miss your graduation  remember how they lost my luggage too?'}, TweetSentiment{title='#masheu09 The worldcat registry API does not support a request based on IP'}, TweetSentiment{title='@alexalltimelow let me sing remembering sunday with you tomorrow'}, TweetSentiment{title='@catplan touch screen one no bluetooth  or wifi. either. sucks!'}, TweetSentiment{title='@AlexGalas33 I think you should especially since work is getting to you. Hope it gets better.'}, TweetSentiment{title='@Clareybear I was on my lunch. They never blocked it previously'}, TweetSentiment{title='Didn't win the twitter contest. BOO'}, TweetSentiment{title='@Carl_Thompson its not arrived in hertfordshire yet'}, TweetSentiment{title='@alexiacm I miss you too'}, TweetSentiment{title='@BrianaSimmons wow that stinks'}, TweetSentiment{title='"@Blogsdna wow... m floored. Today I have to customize blogger template for my friends'}, TweetSentiment{title='@Dorsath aww'}, TweetSentiment{title='&quot;Kinda of sucks Knowing that happiness doesnt existed in my life Almost Lovers A Fine Frenzy &lt;333 Goodbye My Almost Lover&quot; wat do u mean?'}, TweetSentiment{title='#asylm J2 panel is over. Guess it's back to normal life.'}, TweetSentiment{title='@AndreeaBerghea you're right'}, TweetSentiment{title='@Budor where'd they go?  LOL'}, TweetSentiment{title='"@coledude unfortunately'}, TweetSentiment{title='@darkknightwvu well... it'd have to be the senate reflecting pool. and i'd have to have a lunch break.  ugh.'}, TweetSentiment{title='@anticipating I got so much random spam bounced back to my catch-all account which apparently were all sent by me.'}, TweetSentiment{title='@cameron_crazy so is the show done?  They can't possibly want to air their family through that'}, TweetSentiment{title='@amit3d so i think i'm the only one..'}, TweetSentiment{title='@_Stephhh_ Awww  I heard he is the sweetest guy with fans.'}, TweetSentiment{title='@claudiatan  go for a good long swim!'}, TweetSentiment{title='"@Bluenscottish I've been good'}, TweetSentiment{title='@1luv633  Clean Me!'}, TweetSentiment{title='@andersoncooper if you muust go after Mr President cant it be for dont ask dont tell still vey much in place'}, TweetSentiment{title='@denyseduhaime my dad SO wanted to run that  he trained so hard and then the doc said he had to stop bc of a heart condition'}, TweetSentiment{title='*sigh* gotta wait over 8 months for more of this...  http://yfrog.com/46inigj'}, TweetSentiment{title='@autumnal_hedge How rude! That's half the Quidditch pitch gone!'}, TweetSentiment{title='LONELY. lol. i'm so boredddddd'}, TweetSentiment{title='@akabaloo @jotsone nah he's just looking like a sexy beast. He's got a neck tat too and a gf  lol'}, TweetSentiment{title='@dem_lovato_ Ikr?! Best night eva! But I am leaving tomorrow  and you mean like Miley Cyrus and then my say now phone number?'}, TweetSentiment{title='@CgraceFly    please don't go....'}, TweetSentiment{title='@breakmyback to triste'}, TweetSentiment{title='@cassetetapes  shareeee.'}, TweetSentiment{title='@Cibu17 me too  except i just have to go to physical therapy'}, TweetSentiment{title='@ChelseyOBrien I had no idea'}, TweetSentiment{title='@anambanana Haha Anam! I saw that. ) Adam Lambert.  Though his boyfriend looks hott.'}, TweetSentiment{title='@azzmonkey thats hillarious but why couldnt i be the blonde  lmfaooooooo make me a login so i can post haaaa ill post some funny shit'}, TweetSentiment{title='@artemisrex I'm sorry mister!  didn't mean to be an anti-party person.'}, TweetSentiment{title='@AJsPlayhouse albertsons had ran out if the $2 tickets my husband went to several albertsons had no luck'}, TweetSentiment{title='@AzmanKarib  hate to go to a place where I know no one.... I'm a Johorean but lost touch with most friends..  Any1 in Muar twitting here?'}, TweetSentiment{title='@_Trystianity_ No tags.   But I will never again be big enough to wear them. ;)'}, TweetSentiment{title='*stares at his new ï¿½100 paperweight in disbelief*'}, TweetSentiment{title='@Cubbyreza tengo miedo!! lmao omg u tweet now kool! n u stopped following me.. not kool!'}, TweetSentiment{title='"@Asfaq no re... i think i shall hv to let it pass. Got a lot of work to wrap up'}, TweetSentiment{title='"&quot;All the lonely people / Where do they all come from?&quot; - NÃ£o sei'}, TweetSentiment{title='@bookwhore I love the original Crow movie. Brandon Lee = Hawt!! So sad he's gone.'}, TweetSentiment{title='@Bopsicle  YAY!!...........but I'm mad at you'}, TweetSentiment{title='"&quot;danna peÃ±a: i've been with you for two years. what will i do without you?&quot;.. threesome aint complete either.  oh life. i'm C'}, TweetSentiment{title='&quot;Behind every beautiful girl there's a dumbass guy who did her wrong and made her strong&quot;'}, TweetSentiment{title='@danielr23 you ready to talk again?'}, TweetSentiment{title='@Bri93irB @pattyriciax3 I miss her.'}, TweetSentiment{title='@Breesha that's mean'}, TweetSentiment{title='@ChrisMDixon who doesn't'}, TweetSentiment{title='*ouch* . . . i seriously think i pulled a muscle in my gluteous heinikus.'}, TweetSentiment{title='"...and frankly'}, TweetSentiment{title='@BrainTwitch I thought those things were to sleep as automated indexes were to libraries! Guess we've both hit a brick wall.'}, TweetSentiment{title='@aspekt we missed you  now u just have the pics of that pary left... =P'}, TweetSentiment{title='@AubreyG8 aww that sucks'}, TweetSentiment{title='. . . not feeling good mentally . . . feeling stressed and blah . . .'}, TweetSentiment{title='"@arnorian Yeah'}, TweetSentiment{title='another Saturday without MO. Glad Rachel is coming over!'}, TweetSentiment{title='@BscoTT26 lol. my celly trips when i try to send pics  and the kick is dead!!'}, TweetSentiment{title='"@cassowaryjewel Like getting Love stamps for wedding invitations'}, TweetSentiment{title='"@caoimhecoyle http://twitpic.com/7pm4s - awwww'}, TweetSentiment{title='@alexhanna And yet I am still jealous and without iPhone.'}, TweetSentiment{title='"@anti_social see'}, TweetSentiment{title='@abacab1975 aawww hehe i woke up with a hangover'}, TweetSentiment{title='@__Kirsty.  He's not'}, TweetSentiment{title='@DavidArchie poor danny...too bad..i was hoping  for him to win dis season 8...'}, TweetSentiment{title='@ClaireHammond glad i'm not the only one. It's hideous. Someone should get shot!'}, TweetSentiment{title='...and all woman who transfer their first impressions (sexual/maternal) onto a less 'threatening' man -- are themselves as weak as 'Him''}, TweetSentiment{title='@clinton_kelly Bummed about the maxi dresses!  I think they're so cute for summer but I'm only 5'4&quot;!'}, TweetSentiment{title='"@dollyblowflake Excellent'}, TweetSentiment{title='@adamrucker It won't play!'}, TweetSentiment{title='"@charlieskies awe'}, TweetSentiment{title='"@colleencantwell  i'm in accounting (don't laugh'}, TweetSentiment{title='Carrie has a bad eye infection...just waiting for hear from the vets to see if they found anything'}, TweetSentiment{title='*SigH  ii WiSH ii didNt HaV a HEart liKE tHe tiN MaN oFf thE WiZArd &amp; Oz dAt WAy My HEArt WOUld nEvEr gEt HUrt... ii GOt tHE blUES :`('}, TweetSentiment{title='@drofder lol TOO LATE I ALREADY FAXED!!!  but thanks for future reference...incase'}, TweetSentiment{title='@anca_foster well ive pulled an all nighter so im hopin' to sleep eventually ;) sorry ur knee hurts  im sending get well kisses at ya'}, TweetSentiment{title='"@calliean oh'}, TweetSentiment{title='@andyroddick can you like tell those TV crew to show your games live?! Been having to follow it on the net.'}, TweetSentiment{title='@bennybtl DON&quot;T RUB IT IN!!! I REALLY WANTED ONE!'}, TweetSentiment{title='@calikiks  asap. But..... Kids are asleep. I have a car seat for arianna. We can take her with us? I need to pay vs bill!'}, TweetSentiment{title='#dontyouhate having to study on a Friday evening?'}, TweetSentiment{title='@Annazhou aannnna i miss you! schools not the same anymore'}, TweetSentiment{title='@AriesRebi13 dude i'm pissed i don't get my chinese food!!!'}, TweetSentiment{title='@AngieBCool smh...everyone has a curve'}, TweetSentiment{title='@bealove Seriously?! Maybe it just wasn't meant to be...  (Although I still fully support you doing whatever you have to to make it work.)'}, TweetSentiment{title='@_kristaMarie I totally agree with you sucky part for me I can't even take tylenol and I have work today'}, TweetSentiment{title='@AdamTrentMagic  just went to your house. You were not there.'}, TweetSentiment{title='@chunwai09 I feel your pain for gunners'}, TweetSentiment{title='"@cathe2ine lol its not like that'}, TweetSentiment{title='@cocoandbreezy it was coo till i broke my damn glasses  OOBER mad! i wanted to use them for my photoshoot sunday. pissed.'}, TweetSentiment{title='"@_noliesjustlove wow'}, TweetSentiment{title='@androidtomato I'm really goingto misshim as the doctor'}, TweetSentiment{title='@babyrckr of course! although then we cant see that cute kid'}, TweetSentiment{title='@Alexrich1  I laid out...fell asleep....now I have lobster-ness'}, TweetSentiment{title='"@adarlingxo baby'}, TweetSentiment{title='@bhaddad thanks.  But I know it'll be worth it in the end...'}, TweetSentiment{title='@angelicucu YAHHH! estoy addicted  I made a sims of myself and one of megan fox and then I married her... but now she died'}, TweetSentiment{title='@anixienix didn't get anything.'}, TweetSentiment{title='&quot;Simon says: what's life like then?ï¿½ï¿½| ï¿½Lady Gagaï¿½says: SHITE&quot; Oh right'}, TweetSentiment{title='@bebiv I know  poor Munster!'}, TweetSentiment{title='"@andersoncooper : I have only 4K in loans'}, TweetSentiment{title='"@beimaejor : that's kool'}, TweetSentiment{title='@B_RadW  not sure how to respond. Especially since you invited them.'}, TweetSentiment{title='@Dykey SHUT UP! lol. i couldnt think of anything bigger than a watermelon  and what advert?!'}, TweetSentiment{title='"@depodol One of James IT guys told him that it has a virus'}, TweetSentiment{title='...but I can't tell them that it was great service because he didn't try to up sell or he'll catch shit from corp.'}, TweetSentiment{title='@CinnamonCloud Nope  and very upset  I like him as an actor!'}, TweetSentiment{title='@andrewcrawshaw There should have been a warning on that link  very sad but incredible of Pixar'}, TweetSentiment{title='@carcarly oh..... i know.. they do in Singapore too..'}, TweetSentiment{title='@blu3spristine Don't read my timeline thing because my last few tweets were messy Ex-bf crap. Its embarassing.'}, TweetSentiment{title='...goddamn ironing. It's time someone invented disposable clothes so I don't have to iron the same thing over and over!  #fb'}, TweetSentiment{title='@EdarieHosting How do you know? &amp; Why did they do it?'}, TweetSentiment{title='@Backstothewall have to do an exhibition proposal yet  the sun must wait for me'}, TweetSentiment{title='"@avcacio @WestCoastWS thanks'}, TweetSentiment{title='@casualeveryday I love the smell of laundry but that stuff is giving me a horrible headache and I'm sitting OUTside'}, TweetSentiment{title='"@ArnaudJacobs haha yeah this is improving my nite big time!!  i have to make a skype call soon tho'}, TweetSentiment{title='@brittdainard  LMFAO I GOT SO FUCKING EWXCITED'}, TweetSentiment{title='there's not LVATT in cayey'}, TweetSentiment{title='@aMiracle nooooo! don't scratch it'}, TweetSentiment{title='@bkGirlFriday Me too LOL!!! I've gotten so many of those stupid britney porn accounts following me during the past few days...so annoying'}, TweetSentiment{title='@ChristopherKohn  ha see I know I am right thia time!! :-D gonna miss you till Sunday  haha'}, TweetSentiment{title='"@derekmortimer I'm going to find out if any of my new neighbors run un-secure networks. Failing that'}, TweetSentiment{title='@colebarnes  Thank you Cole for the insight!!! I think I don't even want to watch the You Tube video either!'}, TweetSentiment{title='@a_c81 it's afte... and 5480983 more bytes
```

Code from [TrieDemo.java:381](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L381) executed in 4779.59 seconds: 
```java
      HashMap<String, List<String>> map = new HashMap<>();
      map.put("pos", tweetsPositive.stream().limit(trainingSize).map(x -> x.getText()).collect(Collectors.toList()));
      map.put("neg", tweetsNegative.stream().limit(trainingSize).map(x -> x.getText()).collect(Collectors.toList()));
      return new ClassificationTree().setVerbose(System.out).categorizationTree(map, 32);
```
Returns: 
```
    com.simiacryptus.util.text.ClassificationTree$$Lambda$101/1450821318@16b4a017
```
Logging: 
```
    "@d" -> Contains={neg=4452, pos=958}	Absent={neg=45548, pos=49042}	Entropy=-0.9812130905473934
      "@da" -> Contains={neg=1244, pos=798}	Absent={neg=3208, pos=160}	Entropy=-0.5357030167587836
        "@dav" -> Contains={neg=236, pos=19}	Absent={neg=1008, pos=779}	Entropy=-0.9122129473765306
          " @dav" -> Contains={neg=2, pos=17}	Absent={neg=234, pos=2}	Entropy=-0.11071012301057587
            "i" -> Contains={neg=219, pos=0}	Absent={neg=15, pos=2}	Entropy=-0.05900520418397335
          "@dar" -> Contains={neg=133, pos=3}	Absent={neg=875, pos=776}	Entropy=-0.9333959934569119
            "wee" -> Contains={neg=12, pos=3}	Absent={neg=121, pos=0}	Entropy=-0.11308441349144026
            "wood" -> Contains={neg=58, pos=0}	Absent={neg=817, pos=776}	Entropy=-0.9664418928611248
              "@daw" -> Contains={neg=35, pos=2}	Absent={neg=782, pos=774}	Entropy=-0.984170500470722
                "ri" -> Contains={neg=8, pos=2}	Absent={neg=27, pos=0}	Entropy=-0.3077266487128417
                "@day" -> Contains={neg=34, pos=2}	Absent={neg=748, pos=772}	Entropy=-0.984230332083504
                  " the" -> Contains={neg=8, pos=2}	Absent={neg=26, pos=0}	Entropy=-0.31552180521694506
                  " sorr" -> Contains={neg=36, pos=4}	Absent={neg=712, pos=768}	Entropy=-0.9849669485469542
                    "sh" -> Contains={neg=7, pos=3}	Absent={neg=29, pos=1}	Entropy=-0.4270341469223593
                      "  " -> Contains={neg=20, pos=0}	Absent={neg=9, pos=1}	Entropy=-0.3088964245063727
                    "miss" -> Contains={neg=37, pos=4}	Absent={neg=675, pos=764}	Entropy=-0.9824542212107016
                      "u " -> Contains={neg=11, pos=4}	Absent={neg=26, pos=0}	Entropy=-0.40143247896342615
                      " you" -> Contains={neg=132, pos=246}	Absent={neg=543, pos=518}	Entropy=-0.981803435252333
                        "ot " -> Contains={neg=29, pos=14}	Absent={neg=103, pos=232}	Entropy=-0.8924133897340147
                          "ing" -> Contains={neg=16, pos=1}	Absent={neg=13, pos=13}	Entropy=-0.7656003032063221
                            "d " -> Contains={neg=12, pos=3}	Absent={neg=1, pos=10}	Entropy=-0.661526183252251
                          "lo" -> Contains={neg=12, pos=72}	Absent={neg=91, pos=160}	Entropy=-0.8568319370051136
                            "d t" -> Contains={neg=5, pos=5}	Absent={neg=7, pos=67}	Entropy=-0.5224050026445302
                              "me" -> Contains={neg=5, pos=15}	Absent={neg=2, pos=52}	Entropy=-0.4073331109593263
                                " th" -> Contains={neg=1, pos=9}	Absent={neg=4, pos=6}	Entropy=-0.7740183898312651
                                " the " -> Contains={neg=2, pos=9}	Absent={neg=0, pos=43}	Entropy=-0.22122095822205554
                            " i " -> Contains={neg=17, pos=10}	Absent={neg=74, pos=150}	Entropy=-0.9190659658342698
                              " c" -> Contains={neg=9, pos=1}	Absent={neg=8, pos=9}	Entropy=-0.8403842700329641
                              " I w" -> Contains={neg=9, pos=3}	Absent={neg=65, pos=147}	Entropy=-0.8859981905231953
                                "f" -> Contains={neg=42, pos=64}	Absent={neg=23, pos=83}	Entropy=-0.8620774390733446
                                  "nd" -> Contains={neg=7, pos=28}	Absent={neg=35, pos=36}	Entropy=-0.9104908334562308
                                    "?" -> Contains={neg=6, pos=7}	Absent={neg=1, pos=21}	Entropy=-0.5835459077721205
                                      " the" -> Contains={neg=0, pos=12}	Absent={neg=1, pos=9}	Entropy=-0.40377047106886904
                                    "ma" -> Contains={neg=1, pos=9}	Absent={neg=34, pos=27}	Entropy=-0.9316213746284425
                                      "m" -> Contains={neg=31, pos=17}	Absent={neg=3, pos=10}	Entropy=-0.9099696295605415
                                        "ni" -> Contains={neg=8, pos=11}	Absent={neg=23, pos=6}	Entropy=-0.8393094683227662
                                          "se" -> Contains={neg=5, pos=5}	Absent={neg=18, pos=1}	Entropy=-0.591922551575934
                                  "ay" -> Contains={neg=8, pos=10}	Absent={neg=15, pos=73}	Entropy=-0.7168350030857282
                                    "ic" -> Contains={neg=5, pos=7}	Absent={neg=10, pos=66}	Entropy=-0.6224425497193239
                                      "c" -> Contains={neg=0, pos=27}	Absent={neg=10, pos=39}	Entropy=-0.5221953215022894
                                        "k" -> Contains={neg=4, pos=28}	Absent={neg=6, pos=11}	Entropy=-0.693509452098235
                                          "n " -> Contains={neg=3, pos=8}	Absent={neg=1, pos=20}	Entropy=-0.5283359133370147
                                            "ke" -> Contains={neg=0, pos=10}	Absent={neg=1, pos=10}	Entropy=-0.42614747099675676
                        "@dans" -> Contains={neg=17, pos=0}	Absent={neg=526, pos=518}	Entropy=-0.986666405384993
                          "ish" -> Contains={neg=35, pos=8}	Absent={neg=491, pos=510}	Entropy=-0.9870749565942981
                            "'" -> Contains={neg=7, pos=6}	Absent={neg=28, pos=2}	Entropy=-0.5740025154549614
                              " w" -> Contains={neg=20, pos=0}	Absent={neg=8, pos=2}	Entropy=-0.3737415367602828
                            "nt" -> Contains={neg=124, pos=73}	Absent={neg=367, pos=437}	Entropy=-0.985787977855409
                              ";" -> Contains={neg=3, pos=11}	Absent={neg=121, pos=62}	Entropy=-0.913184301276681
                                "h " -> Contains={neg=38, pos=8}	Absent={neg=83, pos=54}	Entropy=-0.8932652220949391
                                  " co" -> Contains={neg=6, pos=5}	Absent={neg=32, pos=3}	Entropy=-0.5771982035289217
                                    "ing " -> Contains={neg=7, pos=3}	Absent={neg=25, pos=0}	Entropy=-0.36139001548162214
                                  "el" -> Contains={neg=19, pos=25}	Absent={neg=64, pos=29}	Entropy=-0.924742014763835
                                    "@dani" -> Contains={neg=16, pos=11}	Absent={neg=3, pos=14}	Entropy=-0.8693200645056416
                                      "er" -> Contains={neg=10, pos=2}	Absent={neg=6, pos=9}	Entropy=-0.8513331656540456
                                    "the " -> Contains={neg=5, pos=8}	Absent={neg=59, pos=21}	Entropy=-0.8497826911168403
                                      "ing " -> Contains={neg=14, pos=0}	Absent={neg=45, pos=21}	Entropy=-0.785725482481433
                                        "nt " -> Contains={neg=28, pos=6}	Absent={neg=17, pos=15}	Entropy=-0.8348596412448309
                                          "to " -> Contains={neg=8, pos=5}	Absent={neg=20, pos=1}	Entropy=-0.5858471514694358
                                            " w" -> Contains={neg=11, pos=0}	Absent={neg=9, pos=1}	Entropy=-0.41963450498236593
                                          "s" -> Contains={neg=14, pos=7}	Absent={neg=3, pos=8}	Entropy=-0.9032253720690138
                                            "er" -> Contains={neg=4, pos=6}	Absent={neg=10, pos=1}	Entropy=-0.7478344940694535
                              "hank" -> Contains={neg=6, pos=31}	Absent={neg=361, pos=406}	Entropy=-0.9811117519413455
                                "he" -> Contains={neg=4, pos=9}	Absent={neg=2, pos=22}	Entropy=-0.6129258759413937
                                  "r" -> Contains={neg=0, pos=14}	Absent={neg=2, pos=8}	Entropy=-0.45670018383521754
                                "ck" -> Contains={neg=53, pos=28}	Absent={neg=308, pos=378}	Entropy=-0.985732262959522
                                  " a " -> Contains={neg=5, pos=11}	Absent={neg=48, pos=17}	Entropy=-0.8448156851314875
                                    "! " -> Contains={neg=9, pos=9}	Absent={neg=39, pos=8}	Entropy=-0.7572835219538512
                                      "ac" -> Contains={neg=12, pos=6}	Absent={neg=27, pos=2}	Entropy=-0.6007870867494471
                                        "he" -> Contains={neg=8, pos=2}	Absent={neg=19, pos=0}	Entropy=-0.38548130043924206
                                  "wit" -> Contains={neg=9, pos=37}	Absent={neg=299, pos=341}	Entropy=-0.9779139265447019
                                    "no" -> Contains={neg=6, pos=6}	Absent={neg=3, pos=31}	Entropy=-0.5971765140270943
                                      " th" -> Contains={neg=0, pos=20}	Absent={neg=3, pos=11}	Entropy=-0.42348696839139843
                                    "wel" -> Contains={neg=2, pos=19}	Absent={neg=297, pos=322}	Entropy=-0.9821236095570139
                                      "y " -> Contains={neg=2, pos=8}	Absent={neg=0, pos=11}	Entropy=-0.5122703796308089
                                      "ey" -> Contains={neg=21, pos=51}	Absent={neg=276, pos=271}	Entropy=-0.9848283032372005
                                        "her" -> Contains={neg=7, pos=3}	Absent={neg=14, pos=48}	Entropy=-0.7901641083195764
                                          " m" -> Contains={neg=7, pos=10}	Absent={neg=7, pos=38}	Entropy=-0.7265955960704088
                                            "e " -> Contains={neg=1, pos=22}	Absent={neg=6, pos=16}	Entropy=-0.5852254394312504
                                              "d " -> Contains={neg=1, pos=9}	Absent={neg=0, pos=13}	Entropy=-0.38929544209896555
                                              "t" -> Contains={neg=4, pos=6}	Absent={neg=2, pos=10}	Entropy=-0.8245307704199005
                                        " hav" -> Contains={neg=29, pos=10}	Absent={neg=247, pos=261}	Entropy=-0.9866193073159867
                                          " g" -> Contains={neg=6, pos=6}	Absent={neg=23, pos=4}	Entropy=-0.7397771286462232
                                            " w" -> Contains={neg=14, pos=0}	Absent={neg=9, pos=4}	Entropy=-0.555781075220893
                                          "ic" -> Contains={neg=16, pos=39}	Absent={neg=231, pos=222}	Entropy=-0.9856156786515671
                                            " w" -> Contains={neg=10, pos=7}	Absent={neg=6, pos=32}	Entropy=-0.7442446248122048
                                              "re" -> Contains={neg=5, pos=11}	Absent={neg=1, pos=21}	Entropy=-0.5773683559391086
                                                "ni" -> Contains={neg=0, pos=12}	Absent={neg=1, pos=9}	Entropy=-0.4039704710688691
                                            "nec" -> Contains={neg=1, pos=11}	Absent={neg=230, pos=211}	Entropy=-0.9855005051112379
                                              "st " -> Contains={neg=13, pos=29}	Absent={neg=217, pos=182}	Entropy=-0.9846238149489605
                                                "I " -> Contains={neg=7, pos=4}	Absent={neg=6, pos=25}	Entropy=-0.7800096708503631
                                                  "u" -> Contains={neg=2, pos=19}	Absent={neg=4, pos=6}	Entropy=-0.6525003517633928
                                                    "w" -> Contains={neg=0, pos=11}	Absent={neg=2, pos=8}	Entropy=-0.5123703796308089
                                                "ry " -> Contains={neg=20, pos=4}	Absent={neg=197, pos=178}	Entropy=-0.9780713621774391
                                                  "k" -> Contains={neg=7, pos=4}	Absent={neg=13, pos=0}	Entropy=-0.5708263573365999
                                                  "s" -> Contains={neg=139, pos=130}	Absent={neg=58, pos=48}	Entropy=-0.978630836498868
                                                    "ha" -> Contains={neg=2, pos=9}	Absent={neg=56, pos=39}	Entropy=-0.9512648411912675
                                                      "v" -> Contains={neg=10, pos=1}	Absent={neg=46, pos=38}	Entropy=-0.9410550016763026
                                                        "r " -> Contains={neg=3, pos=8}	Absent={neg=43, pos=30}	Entropy=-0.9624518777168483
                                                          "m" -> Contains={neg=23, pos=9}	Absent={neg=20, pos=21}	Entropy=-0.9388259683418878
                                                            "am" -> Contains={neg=9, pos=7}	Absent={neg=14, pos=2}	Entropy=-0.7909036455753781
                                                            "g" -> Contains={neg=11, pos=5}	Absent={neg=9, pos=16}	Entropy=-0.9290074827718835
                                                              "i" -> Contains={neg=7, pos=8}	Absent={neg=2, pos=8}	Entropy=-0.9053601035799355
        " @d" -> Contains={neg=52, pos=96}	Absent={neg=3156, pos=64}	Entropy=-0.17538844297228656
          " d" -> Contains={neg=26, pos=14}	Absent={neg=26, pos=82}	Entropy=-0.8341584559773149
            "an" -> Contains={neg=11, pos=13}	Absent={neg=15, pos=1}	Entropy=-0.7669625257133218
              "to " -> Contains={neg=3, pos=10}	Absent={neg=8, pos=3}	Entropy=-0.8328468247368379
            " on" -> Contains={neg=11, pos=9}	Absent={neg=15, pos=73}	Entropy=-0.7221024366510506
              "en" -> Contains={neg=3, pos=7}	Absent={neg=8, pos=2}	Entropy=-0.8334153347375948
              "ma" -> Contains={neg=0, pos=25}	Absent={neg=15, pos=48}	Entropy=-0.6098316369203405
                "em" -> Contains={neg=6, pos=4}	Absent={neg=9, pos=44}	Entropy=-0.7119355545870929
                  "y" -> Contains={neg=4, pos=38}	Absent={neg=5, pos=6}	Entropy=-0.5790428379560533
                    "wa" -> Contains={neg=3, pos=7}	Absent={neg=1, pos=31}	Entropy=-0.4096559091680764
                      "e " -> Contains={neg=0, pos=22}	Absent={neg=1, pos=9}	Entropy=-0.2914346751166544
          "@d_" -> Contains={neg=13, pos=20}	Absent={neg=3143, pos=44}	Entropy=-0.11366793698505685
            "  " -> Contains={neg=8, pos=2}	Absent={neg=5, pos=18}	Entropy=-0.7680587920922235
              "w" -> Contains={neg=1, pos=11}	Absent={neg=4, pos=7}	Entropy=-0.723263946781995
            "@d3" -> Contains={neg=11, pos=13}	Absent={neg=3132, pos=31}	Entropy=-0.08612552715124268
              " s" -> Contains={neg=3, pos=9}	Absent={neg=8, pos=4}	Entropy=-0.8812073911930347
              "@d0" -> Contains={neg=8, pos=5}	Absent={neg=3124, pos=26}	Entropy=-0.07243677082925055
                "zo" -> Contains={neg=11, pos=4}	Absent={neg=3113, pos=22}	Entropy=-0.06395004930117662
                  "@dot" -> Contains={neg=13, pos=3}	Absent={neg=3100, pos=19}	Entropy=-0.05671218968781867
                    "ed!" -> Contains={neg=17, pos=3}	Absent={neg=3083, pos=16}	Entropy=-0.05026778827704641
                      " to" -> Contains={neg=10, pos=0}	Absent={neg=7, pos=3}	Entropy=-0.5990609326107037
                      "&quot;" -> Contains={neg=39, pos=3}	Absent={neg=3044, pos=13}	Entropy=-0.04395072681255405
                        "te" -> Contains={neg=10, pos=3}	Absent={neg=29, pos=0}	Entropy=-0.33820429720467265
                        "ssy" -> Contains={neg=8, pos=2}	Absent={neg=3036, pos=11}	Entropy=-0.03675180710397986
                          "lco" -> Contains={neg=10, pos=2}	Absent={neg=3026, pos=9}	Entropy=-0.031705310124485166
                            "ing!" -> Contains={neg=19, pos=2}	Absent={neg=3007, pos=7}	Entropy=-0.026795089591734208
                              " you" -> Contains={neg=8, pos=2}	Absent={neg=11, pos=0}	Entropy=-0.5120703796308089
                              " pho" -> Contains={neg=20, pos=2}	Absent={neg=2987, pos=5}	Entropy=-0.021136237297961828
                                "to " -> Contains={neg=8, pos=2}	Absent={neg=12, pos=0}	Entropy=-0.49229562414238287
                                "e? " -> Contains={neg=31, pos=2}	Absent={neg=2956, pos=3}	Entropy=-0.015591801261983238
                                  "re" -> Contains={neg=21, pos=0}	Absent={neg=10, pos=2}	Entropy=-0.36220217201244437
                                  " I reall" -> Contains={neg=10, pos=1}	Absent={neg=2946, pos=2}	Entropy=-0.009869408926424447
                                    "o!!" -> Contains={neg=9, pos=1}	Absent={neg=2937, pos=1}	Entropy=-0.006830214694853497
                                      "ome" -> Contains={neg=265, pos=1}	Absent={neg=2672, pos=0}	Entropy=-0.0032289338541691176
                                        "len" -> Contains={neg=10, pos=1}	Absent={neg=255, pos=0}	Entropy=-0.03808636490501335
      "hank" -> Contains={neg=711, pos=3934}	Absent={neg=44837, pos=45108}	Entropy=-0.9808087860128077
      ... and 370438 more bytes
```

Code from [TrieDemo.java:387](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L387) executed in 0.26 seconds: 
```java
      return tweetsPositive.stream().skip(trainingSize).map(x->x.getText()).mapToDouble(str->{
          Map<String, Double> prob = rule.apply(str);
          System.out.println(String.format("%s -> %s", str, prob));
          return prob.getOrDefault("pos", 0.0) < 0.5 ? 0.0 : 1.0;
      }).average().getAsDouble();
```
Returns: 
```
    0.7813
```
Logging: 
```
    @AlexAllTimeLow my friend does!!! -> {neg=0.21, pos=0.79}
    @cubbygraham I wanna come!!! How come u didn't invite me! -> {neg=0.8823529411764706, pos=0.11764705882352941}
    @ChrisLAS Hope everything's okay now! Seems kinda happy in the pic -> {neg=0.45454545454545453, pos=0.5454545454545454}
    "@arancinibaby shhh don't tell everyone especially your other half -> {neg=0.5, pos=0.5}
    "@caseygotcher Also -> {neg=0.39054550138468475, pos=0.6094544986153153}
    "@annaphilly no i am not a liar. you said it yourself -> {neg=0.29411764705882354, pos=0.7058823529411765}
    @annaa92   Don't you mean &quot;People&quot;... Not &quot;peaple&quot;.. Sorry my Mom is a Teacher... -> {neg=0.550314465408805, pos=0.449685534591195}
    @ArmyMom101 I think you got me! I have been such a slacker for the past few weeks. I am right there w/ you on the mush brain syndrome! -> {neg=0.15867158671586715, pos=0.8413284132841329}
    @arnoldwender Cool! And I hope you won't get any. I lost my HD but luckily I saved my profile in a memory card..phew! so all good now too -> {neg=0.2857142857142857, pos=0.7142857142857143}
    @blobyblo awesome tour photos!! wish i could have been at at least one of them!! -> {neg=1.0, pos=0.0}
    @AskAboutLUST Aww girl...Jus come on down...u can come in here and do your thing on it.. -> {neg=0.15867158671586715, pos=0.8413284132841329}
    @cliquedecamwa I hear ya. so many to choose from. Can't we just hang out with them all. -> {neg=0.7924528301886793, pos=0.20754716981132076}
    @Berryadict Thank you very much -> {neg=0.2727272727272727, pos=0.7272727272727273}
    @biffgriff LOL!!  Have Twibes said you can change the background? -> {neg=0.7241379310344828, pos=0.27586206896551724}
    @Beadyjan mines here http://beadypool.blogspot.com I put pics of my lampwork beads up last night - I am a bit pleased with my efforts -> {neg=0.44749596122778673, pos=0.5525040387722132}
    @Blooboy ur mom is on a killing spree. e3 is on day 2 now  loads of new games final fantasy 14 online is a ps3 exclusive. haha! -> {neg=0.5454545454545454, pos=0.45454545454545453}
    @andyclemmensen I &lt;3 U more than Shaun &lt;3's himself -> {neg=0.44749596122778673, pos=0.5525040387722132}
    @amanda_holden welcome to twitter young lady! Nice to see you on here. I don't know @zoe_salmon but want to welcome her too -> {neg=0.2857142857142857, pos=0.7142857142857143}
    @_Jannika huuuunnnggrrryyyy i've been at work and reading my book. ben steezy's bout to go grab some food -> {neg=0.39054550138468475, pos=0.6094544986153153}
    @Bijoufem haha will do but I'm tired around here so I'm off to bed -> {neg=0.09401709401709402, pos=0.905982905982906}
    @ahblessgirl  yup shock horror.. now playing Live at Earls Court!! hee hee.... the power of advertising -> {neg=0.25, pos=0.75}
    "@AdamBomba Thank you.  Hey -> {neg=0.2, pos=0.8}
    "@averygoodyear I know everyone says it -> {neg=0.6816143497757847, pos=0.3183856502242152}
    @AndreaDG oooh! Marty's cracklins!! Yum-o!!! Haha  I love that!! -> {neg=0.0, pos=1.0}
    @atlantapizza I'm a pizza snob and may not want to share my favorite spots because I don't want to ruin a good thing. -> {neg=0.75, pos=0.25}
    "#followfriday @pippip1 Hi all -> {neg=0.0, pos=1.0}
    @Assassin10k Nice twitlight. I'm gonna watch your gamer hut video later. -> {neg=0.15867158671586715, pos=0.8413284132841329}
    @AshleyLTMSYF http://twitpic.com/611f1 - Yea! Jai ho!! -> {neg=0.21, pos=0.79}
    "@CookingGranny had me a sleep in today.. took a sleeping pill last night -> {neg=0.15384615384615385, pos=0.8461538461538461}
    "@BClove Depends on when it is -> {neg=0.39054550138468475, pos=0.6094544986153153}
    @Char_SOS lolz it is -> {neg=0.39054550138468475, pos=0.6094544986153153}
    @AlliWorthington they didn't pay you?  they paid me. -> {neg=1.0, pos=0.0}
    @andyschaef I just wanted to say that you two are amazing photog's! -> {neg=0.3, pos=0.7}
    @ashleytisdale  Congrats on the award -> {neg=0.0, pos=1.0}
    @AlohaArleen busy &amp; doing great...no roosters though for me.  Helping to launch a women's media co. We should talk. I'll DM u. -> {neg=0.6153846153846154, pos=0.38461538461538464}
    I hate allergies. Should I get my hair cut tomorrow? I'm taking a public poll... -> {neg=0.6628550350424198, pos=0.33714496495758023}
    #ff #music #followfriday  @TRE_BOOGIE  &lt;&lt; because he cared enough to tell me to get some sleep -> {neg=0.0, pos=1.0}
    #WhyITweet coz i can -> {neg=0.39868565169769987, pos=0.6013143483023001}
    @anmathews7154 only as we get older honey!  J/K -> {neg=0.16356877323420074, pos=0.8364312267657993}
    @aras_p but do you mean you only write code that survive forever? -> {neg=0.24800531914893617, pos=0.7519946808510638}
    @already_used ME! -> {neg=0.21, pos=0.79}
    "@charnellesblog Thanks for the shout out -> {neg=0.0, pos=1.0}
    @aplusk I'd say 13. My youngest is six so hopefully i have a ways -> {neg=0.24046771733604475, pos=0.7595322826639552}
    "#commtell09 Patty Perkins -> {neg=0.39868565169769987, pos=0.6013143483023001}
    @danawalker i stayed at the residence inn in harrisburg!  i wanted to stay in hershey but rooms were exxxpensive!! -> {neg=0.38461538461538464, pos=0.6153846153846154}
    #unfollowdiddy is takin over!...lol -> {neg=1.0, pos=0.0}
    @cocoush &quot;Good laugh. Loves chocolate.&quot;  Love you xxx -> {neg=0.309552599758162, pos=0.6904474002418379}
    @changeca Thanks for the #ecomonday love -> {neg=0.2727272727272727, pos=0.7272727272727273}
    @blogpartyned great minds think alike -> {neg=0.4336027713625866, pos=0.5663972286374134}
    I think I did good in the math test! I need to write nao...34 655 words! -> {neg=0.6628550350424198, pos=0.33714496495758023}
    @charlo_be I'm sure we can find a less radical solution than that -> {neg=0.39054550138468475, pos=0.6094544986153153}
    @Chilosa09 very nice -> {neg=0.39054550138468475, pos=0.6094544986153153}
    @BlondeByDesign Aww just saw your msg! Thx we're good ï¿½ ready for a nice dry weekend. Dry in terms of rain that is!  How are you? -> {neg=0.24800531914893617, pos=0.7519946808510638}
    @A_R_Photography what a purty man -> {neg=0.39054550138468475, pos=0.6094544986153153}
    "@christianaty i thought spicy food in hot weather is good -> {neg=0.4, pos=0.6}
    @Aharvey24 i really want to see your net sesh... or just get to throw balls at you in padding again... -> {neg=0.0, pos=1.0}
    @alipxoxo yay for free coffee! -> {neg=0.21, pos=0.79}
    @AndreMJonesJr hiiii. ur welcome -> {neg=0.0, pos=1.0}
    @betsyjane25 come to chat   i am uploading  stuff   haapy to play anything for ya -> {neg=1.0, pos=0.0}
    @boriqua3720 Maybe it's camera tricks! -> {neg=0.21, pos=0.79}
    "@blue_wind it's not mine -> {neg=0.38235294117647056, pos=0.6176470588235294}
    @blasha sorry my replay was to dreamyeyes -> {neg=0.8818565400843882, pos=0.11814345991561181}
    test one two and three -> {neg=0.7600767754318618, pos=0.2399232245681382}
    @aviel @dacort @marinamartin @npost don't forget to sign up for barcamp seattle. we like awesome smart people  http://is.gd/QO0q -> {neg=0.0, pos=1.0}
    @Annjj liking your style u crazy pretty assassin you !!!  xx -> {neg=0.15867158671586715, pos=0.8413284132841329}
    @_CrC_ WOOT! i'm great. thanks. we are the kind eyes sisters from niagara &amp; the boat! hope u &amp; ur lady are good!  have fun 2night! -> {neg=0.0, pos=1.0}
    @bimbler whoohoo! I better get my rock shoes on like donkey kong! -> {neg=0.4167893961708395, pos=0.5832106038291606}
    @babygirlparis http://twitpic.com/6a97s - is that old ???  hahah because u dont have long hair anymore :O u should get long again soo  ... -> {neg=0.9, pos=0.1}
    @billyraycyrus and congrats to miley! -> {neg=0.21, pos=0.79}
    @Amandalee55 cool i get to go to the Darien Lake show the day after -> {neg=0.8652482269503546, pos=0.1347517730496454}
    @AngloAlice 'Knocking' is an amazing song - and the singer is C-U-T-E! -> {neg=0.21, pos=0.79}
    @asimzeeshan take a look here : http://iweb.com/landing/10kServersMilestone/2a.php ! You can win 1000$ in hosting credit  - JL -> {neg=0.6363636363636364, pos=0.36363636363636365}
    ";; hehe -> {neg=0.5056179775280899, pos=0.4943820224719101}
    "@abbiezed You're cool -> {neg=0.4336027713625866, pos=0.5663972286374134}
    @begiled LOL I'm a real zoo keeper with real animals. -> {neg=0.4336027713625866, pos=0.5663972286374134}
    "@Cathryn_Mowbray OMFG -> {neg=0.39054550138468475, pos=0.6094544986153153}
    @chromasia Wall to wall sunshine here and up to mid 70s later. Think the boot is on the other foot finally! -> {neg=0.21, pos=0.79}
    @aubredance Wonderful!  I look forward to the new dvd -> {neg=0.0, pos=1.0}
    @angiemartinez PLEASE HELP PASS THIS ON FOR THE KARDASHIANS  http://beta.twiddeo.com/8a3 -> {neg=0.0, pos=1.0}
    @DamienAidoo lol whateva! That's y ur hawks been sittin they ass at home 4 the longest -> {neg=0.3526315789473684, pos=0.6473684210526316}
    "@against_stars NO NO IT'S OKAY I'm so flattered that you would do so-! ;_; It was a gift for you -> {neg=0.5384615384615384, pos=0.46153846153846156}
    "@24k LOL m was great! Some Free shots -> {neg=0.21, pos=0.79}
    @alysonuy upd8.  its for globe. -> {neg=0.39054550138468475, pos=0.6094544986153153}
    @chrisgotch That's me after a typical day of work! -> {neg=0.21, pos=0.79}
    @BethanyinFL Cute.  Me too! -> {neg=0.24903474903474904, pos=0.750965250965251}
    @brucehoult ok. Oh... I'm nervous!  but the apple book looks so purdy... -> {neg=0.8666666666666667, pos=0.13333333333333333}
    &quot; i haven't seen him like... forever..&quot; -> {neg=1.0, pos=0.0}
    &quot;so tell me how im supposed to get you off my mind...&quot; speedial by @mitchelmusso only like 2 days until the cd is out!!! yessssss! -> {neg=0.0, pos=1.0}
    @aramisette I think you can see the tweet because I mentioned your name.  And you should follow her!  She's awesome. -> {neg=0.18181818181818182, pos=0.8181818181818182}
    @alexandraduggan Because it was so NOT nice out in Palo Alto when we were out. -> {neg=0.39054550138468475, pos=0.6094544986153153}
    @bbluesman Hi Mark!  Freihofer's had good chocolate chip cookies at one time? Sounds delish! -> {neg=0.14814814814814814, pos=0.8518518518518519}
    @Cavalli_Cali poor baby... Hittin the metropolitan on the 13th tho! Stay tuned!! -> {neg=0.21, pos=0.79}
    #twithelp from @ashbadash: : i'm homeee that was tres fun. maybe gonna watch a movie? any suggestions?  http://tinyurl.com/d85s7q -> {neg=0.0, pos=1.0}
    #flylady this beautiful weather is making me completely LAZY lol.....ds2 napping making lunch for ds10 &amp; I need to start something -> {neg=0.18181818181818182, pos=0.8181818181818182}
    "@BUTTERFLYWHEEL ah ur too sweet to me Monica -> {neg=0.6629213483146067, pos=0.33707865168539325}
    "@aerynblack Oh it is!  LOVED IT!  Wasn't expecting 2 cry -> {neg=0.875, pos=0.125}
    "&quot;There's someone for me somewhere And I still miss someone Oh -> {neg=1.0, pos=0.0}
    @AvolynFisher have fun!!!! i just got back from isu's orientation  we need to hang out soon! -> {neg=0.23076923076923078, pos=0.7692307692307693}
    "@ChellyBelle No new ones that I am aware of -> {neg=0.44749596122778673, pos=0.5525040387722132}
    &quot;Unbroke&quot; is really good -> {neg=0.34210526315789475, pos=0.6578947368421053}
    @christinaox Haha read my comment loool  x on ur new vid x -> {neg=0.0, pos=1.0}
    @19fischi75 really a few days? thought longer -> {neg=0.39054550138468475, pos=0.6094544986153153}
    ":: Ahh... Joe @Rospars has been on Twitter for &lt;9 hours and he already has ~300 followers.  Welcome to the Twitterscape -> {neg=0.0, pos=1.0}
    @ashleyyylim then try using manual focus? -> {neg=0.39054550138468475, pos=0.6094544986153153}
    @archuphils yey! David is in the top 3 on Mr. Twitter Universe!  http://bit.ly/PmvRY -> {neg=0.24903474903474904, pos=0.750965250965251}
    "@chile_pepper If that doesn't bring us luck -> {neg=1.0, pos=0.0}
    "@climber514 I'll check them out for sure -> {neg=0.39054550138468475, pos=0.6094544986153153}
    @bryantodd Aww that's good then  Post the vid when you get it; I'd love to see it too -> {neg=0.0, pos=1.0}
    @colleen_erin so are you gonna go?  their other songs are good too.  i like it -> {neg=0.24800531914893617, pos=0.7519946808510638}
    @16_MileyCyrus Good luck with your history people! -> {neg=0.24046771733604475, pos=0.7595322826639552}
    "@annq thx -> {neg=0.39054550138468475, pos=0.6094544986153153}
    "@__dana__ doing fine here. Season is winding down -> {neg=0.39054550138468475, pos=0.6094544986153153}
    @aqeelahmed Thought it was time 4 a change  Yea Im excited 2 c it 2 -> {neg=0.4336027713625866, pos=0.5663972286374134}
    @AubreyODay ginger is a lucky little biotch -> {neg=0.39054550138468475, pos=0.6094544986153153}
    "@benlondon hey dude -> {neg=0.39054550138468475, pos=0.6094544986153153}
    "@belladonna20 lol -> {neg=0.39054550138468475, pos=0.6094544986153153}
    @claresmith75 received an email fm max thanks for that luv..  Will get them 2 add/buzz her. -> {neg=0.18235294117647058, pos=0.8176470588235294}
    "@ARo13  Oh -> {neg=0.39054550138468475, pos=0.6094544986153153}
    @Beachacreresort lol - thanks -> {neg=0.18235294117647058, pos=0.8176470588235294}
    i joined twitter 24 January 2009. daayyuuummm. i thought it would have been in like april. lololol. hi mom. -> {neg=0.6628550350424198, pos=0.33714496495758023}
    @aliciastacy3522 Hopefully Cayla will make the Top 12. Katie's sis wanted Brownies today... thats how it all started! -> {neg=0.21, pos=0.79}
    @BlakeLewis you were amazing yesterday at pride! just wanted to let you know -> {neg=0.1, pos=0.9}
    "*stewie walks in naked* &quot;just passing through -> {neg=0.14909090909090908, pos=0.850909090909091}
    &quot;Fly with me&quot; -@jonasbrothers   I cant wait for their music video premiere on Disney Channel Sunday at 8pm!!! -> {neg=0.0, pos=1.0}
    @aspaonline my pleasure. Have a top weekend. -> {neg=0.26105717367853293, pos=0.7389428263214671}
    @bethharte. YOUR FIRED  c its easy. -> {neg=0.39054550138468475, pos=0.6094544986153153}
    @claicham What can I say. -> {neg=0.44749596122778673, pos=0.5525040387722132}
    "@buckhollywood hello -> {neg=0.38461538461538464, pos=0.6153846153846154}
    @CCab oh i think i figured it out!! cornucopia=amazing! i like the chocolate covered ones  yyuumm -> {neg=0.0, pos=1.0}
    @Custumz Cause we never grabbed them when we were at my house. I'll bring them down tomorrow. -> {neg=0.6153846153846154, pos=0.38461538461538464}
    @ChristinaluvzJK ON MY WAY. -> {neg=0.39054550138468475, pos=0.6094544986153153}
    @AlastairHW the health option for lunch  I am very jealous -> {neg=0.44749596122778673, pos=0.5525040387722132}
    @Annjj do u know what a dutchrudder is?????? -> {neg=0.39054550138468475, pos=0.6094544986153153}
    ...nice and easy reviewer comments to deal with -> {neg=0.6628550350424198, pos=0.33714496495758023}
    "@amorousmusings but not &quot;hurt you the most -> {neg=0.7, pos=0.3}
    "@ajitfoldsfive Noooo! D: I mergesorted by partitioning them -> {neg=0.4167893961708395, pos=0.5832106038291606}
    @cali_g510 ok   I got you! -> {neg=0.309552599758162, pos=0.6904474002418379}
    @beebawler_08 it is EASY! Super easssy BP! -> {neg=0.21, pos=0.79}
    @AlexAllTimeLow lovelovelove sydney -> {neg=0.39054550138468475, pos=0.6094544986153153}
    @beulahgg I think that people are modifying their streams by unfollowing others. I'm pleased they update my followers this way -> {neg=0.44749596122778673, pos=0.5525040387722132}
    @CraigMadison How's my #1 student? -> {neg=0.26105717367853293, pos=0.7389428263214671}
    hey all im bored and since im wating for cliare before going to my grans i thought i would twitter lol -> {neg=0.9090909090909091, pos=0.09090909090909091}
    @BigRedOMalley Hi! I'm not crazy...or drunk. That was a silly message to Angela. -> {neg=0.5555555555555556, pos=0.4444444444444444}
    @beckerdoodlez: jump on the bandwagon and send Krissy an &quot;ihateyourillegalpig&quot; reply -> {neg=0.39054550138468475, pos=0.6094544986153... and 1089448 more bytes
```

Code from [TrieDemo.java:394](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L394) executed in 0.19 seconds: 
```java
      return tweetsNegative.stream().skip(trainingSize).map(x->x.getText()).mapToDouble(str->{
          Map<String, Double> prob = rule.apply(str);
          System.out.println(String.format("%s -> %s", str, prob));
          return prob.getOrDefault("neg", 0.0) < 0.5 ? 0.0 : 1.0;
      }).average().getAsDouble();
```
Returns: 
```
    0.5858
```
Logging: 
```
    @cookersz This show is awesome!  I can't believe people kill whales like that -> {neg=1.0, pos=0.0}
    @aaronlowe I'm gonna miss that blue bumper. -> {neg=0.6666666666666666, pos=0.3333333333333333}
    @analovesme yum mcdonalds  cant give in 2 temptation -> {neg=1.0, pos=0.0}
    @dreamgir1 is he really? that's sad -> {neg=1.0, pos=0.0}
    &quot;I WANT REVENGE will not run in the Kentucky Derby due to a wrenched ankle.&quot;  #fb -> {neg=0.8125, pos=0.1875}
    "@ChantelleDaily sold out in 6 stores -> {neg=0.39054550138468475, pos=0.6094544986153153}
    "@dr3amingd3ad Unfortunately -> {neg=1.0, pos=0.0}
    " no MMS on 2G iPhone!  Why does Apple say its a hardware limit when jailbroken iphones can -> {neg=0.8, pos=0.2}
    "@BundaQeela if this statement related 2 what we've been talking about this afternun -> {neg=0.3333333333333333, pos=0.6666666666666666}
    @disagreer isn't tom2 good enough? -> {neg=1.0, pos=0.0}
    @ thomasdofficial  mein herzliches Beileid -> {neg=0.39054550138468475, pos=0.6094544986153153}
    "@bobbi10100 still feeling quite dodgy -> {neg=0.39054550138468475, pos=0.6094544986153153}
    "@cloverdash I'm sorry about Freddie -> {neg=0.7, pos=0.3}
    @_kalanigordon I wanted to meet the infamous mama gordon.  tell her hi -> {neg=0.6666666666666666, pos=0.3333333333333333}
    @ mileycyrus i cant sleep either its now 6:10am -> {neg=0.7368421052631579, pos=0.2631578947368421}
    (@gbsinkers) Oh snap!  Just broke my windshield while replacing my wiper blades. -> {neg=0.24903474903474904, pos=0.750965250965251}
    @CHRISDJMOYLES have a great day Chris .... Im very very very jealous ... always wanted 2 go 2 Silverstone for Gran Prix -> {neg=0.6153846153846154, pos=0.38461538461538464}
    "@brookiemiller hahahaha -> {neg=0.7333333333333333, pos=0.26666666666666666}
    .........I hate washing my hair myself -> {neg=0.6628550350424198, pos=0.33714496495758023}
    @candacecbure Yes! like every year it happens -> {neg=0.21, pos=0.79}
    @briannanicoleee awww yes -> {neg=1.0, pos=0.0}
    @CityHaze  not funny. Can't you come kill them for me? -> {neg=1.0, pos=0.0}
    @bjolena hangovers suck -> {neg=1.0, pos=0.0}
    @DJPlaZma they were closed -> {neg=1.0, pos=0.0}
    @B_Smoorez at this point anywhere I've put in apps at like every dept. store in s'ville... No one wants me  lol -> {neg=0.25, pos=0.75}
    @_dznr Welcome home! Guess i'm not seeing you tonight? -> {neg=0.6666666666666666, pos=0.3333333333333333}
    @brazenone u know yer spellin my twit name wrong. some fool took my name lordofbeer so I had to take lordObeer -> {neg=0.6666666666666666, pos=0.3333333333333333}
    @breedimetria mannnn hell yea dat nigga be parkin lot pumpin.....I made a mistake &amp;saw a parkin lot scene in his phone one day! Kilt me -> {neg=0.21, pos=0.79}
    @acieissobloggin Oh God. 1 out of 6 lang ata ako dun. And to think na kabarkada ko pa siya -> {neg=0.39054550138468475, pos=0.6094544986153153}
    @Aryn21md haha for some reason it's not letting me upload pics! ima keep trying even if it takes all night -> {neg=0.4, pos=0.6}
    @cerabateman i read that book freshman year. poor little fat kid -> {neg=0.3333333333333333, pos=0.6666666666666666}
    @bowskii i know i asked @calihikidd where u was and he said u were sick  hope ur feeling better babes. we need to do a beach day soon!!! -> {neg=0.8652482269503546, pos=0.1347517730496454}
    @Cohoons_World. Today is not a good day!! -> {neg=1.0, pos=0.0}
    @BLKPARISHILTON1 mine either -> {neg=0.39054550138468475, pos=0.6094544986153153}
    (@findingmickey) #ohatdl fantasmic canceled! -> {neg=0.21, pos=0.79}
    @chuchoisabadass but it's not the same... it's like someone's heart is rippen into two      ok.. nooot xD -> {neg=1.0, pos=0.0}
    @alteredattic lol. I understand. Lots of rattie owners are traumatized and put off ratties when they die -> {neg=0.44749596122778673, pos=0.5525040387722132}
    "@Autumn_Sandeen  You are not holding your breath -> {neg=0.6666666666666666, pos=0.3333333333333333}
    @alperdotr i got bored   lunch time? -> {neg=0.30097087378640774, pos=0.6990291262135923}
    @DABoloso u shoulda been watchin family guy cant go wrong with that...kinda sad i missed it tho -> {neg=0.875, pos=0.125}
    @claudiamcfly 2 weeks. 2 very short weeks  hbu? -> {neg=0.39054550138468475, pos=0.6094544986153153}
    stuck inside revising &amp; my throat KILLS! at least it's sunny :o) -> {neg=0.0, pos=1.0}
    @Austinslide sort of..... its called work.... -> {neg=0.4336027713625866, pos=0.5663972286374134}
    "@AtlantaJJ Lots of rampant speculation -> {neg=0.5714285714285714, pos=0.42857142857142855}
    @a_nobel ? ? ?????????????  = ?????? ?????? ?????? ?? ???? -> {neg=0.39054550138468475, pos=0.6094544986153153}
    "@bethsweeney1 Can I go 10 days without technology?you bet I'd WANT to -> {neg=0.875, pos=0.125}
    @callimuffin I read it I jus dunno how to send one bak  I don't think I can from my fone lol -> {neg=0.875268817204301, pos=0.12473118279569892}
    @AmandaJagdeo Aww! I miss you too! Come back to Columbia!! -> {neg=0.375, pos=0.625}
    I have heartburn...cheap pizza sauce -> {neg=0.6628550350424198, pos=0.33714496495758023}
    "@bitofmomsense You can tweet in from your cell phone -> {neg=0.3075221238938053, pos=0.6924778761061947}
    "@ChelseaParadiso YAE!! Wish i can go see you guys -> {neg=0.3075221238938053, pos=0.6924778761061947}
    @ArUrbEx we're closing -> {neg=0.39054550138468475, pos=0.6094544986153153}
    got my college interview in an hour -> {neg=0.7600767754318618, pos=0.2399232245681382}
    @CoolTeacherLady that's disappointing  have fun at the wedding! -> {neg=0.4, pos=0.6}
    "@antzpantz yes -> {neg=0.39054550138468475, pos=0.6094544986153153}
    @Auntywainright 14! yay!  yes you're right Appleby horse fair causes us problems both to and from it's route -> {neg=0.15867158671586715, pos=0.8413284132841329}
    @bowenandarrow so sad not to be going  stupid baby ruins everything :p -> {neg=1.0, pos=0.0}
    &quot;Spider-Man&quot;. Sorry. Lost some geek points there. -> {neg=0.8818565400843882, pos=0.11814345991561181}
    @aWorldApart  Sorry to hear your you-know-what still hurts. -> {neg=0.9333333333333333, pos=0.06666666666666667}
    @BenjaminReid got your email yettt? none for moi -> {neg=0.8, pos=0.2}
    @drewryanscott you made me saddd -> {neg=1.0, pos=0.0}
    just hadda tell a pt they were in early stages of kidney failure and headed for dialysis... sometimes this is harder than others... -> {neg=0.6628550350424198, pos=0.33714496495758023}
    @ach705 no you yelled at me yesterday! -> {neg=0.8235294117647058, pos=0.17647058823529413}
    "@backstreetboys Oh noes!  I tried to vote for you guys but there's no plus sign on your picture -> {neg=0.5384615384615384, pos=0.46153846153846156}
    @angie_k2 I know -> {neg=0.6816143497757847, pos=0.3183856502242152}
    @dbrendanm I heard The Hood Internet cancelled their set tonight -> {neg=1.0, pos=0.0}
    "@ChristieCiarlo  Hang in there Ciarlo. July's the month of grilling -> {neg=0.26105717367853293, pos=0.7389428263214671}
    @atticusjackson1 O_o Huh? And please give back the favor by taking a piss in Houston...I really need it to rain -> {neg=0.4336027713625866, pos=0.5663972286374134}
    @DaniPLQ But weekends are FOOTBALL time! I only hate them because I work and miss half the football -> {neg=0.09090909090909091, pos=0.9090909090909091}
    @Beckinelson idkk if il get to see it again -> {neg=0.39054550138468475, pos=0.6094544986153153}
    @Ashkayk Ah. Thank you! I forgot what website it was from. I fell for the maze one.  lol -> {neg=0.2, pos=0.8}
    @aiimee_x I am lost. Please help me find a good home. -> {neg=1.0, pos=0.0}
    @ChelCLately Oh you KNOOOOOW I will!!! some spray and a Kubotan are in the process of being shipped to this addy after hearing your news -> {neg=0.5161290322580645, pos=0.4838709677419355}
    ...I'm so tired... -> {neg=0.6628550350424198, pos=0.33714496495758023}
    @DanielFielding  its really annoying! -> {neg=0.5, pos=0.5}
    @adamasity_britt omg ur wack! -> {neg=0.21, pos=0.79}
    last day in turks and caicos... i will miss you -> {neg=1.0, pos=0.0}
    @dfjamir welcome back! I'm about to be outta here though... -> {neg=0.8333333333333334, pos=0.16666666666666666}
    @Beau_Monde Wow so cool it said you had other colors to but you didnt show -> {neg=1.0, pos=0.0}
    @DailyMusicGuide Wish i was  Line-up looks soooo good this year but instead i have the fun of coursework and revision lol -> {neg=0.17647058823529413, pos=0.8235294117647058}
    "#photography #fail -> {neg=1.0, pos=0.0}
    @Anita_PvR me too now! and i have salad for dinner -> {neg=0.4, pos=0.6}
    @alsointocats omg I want to color  and I thought of you today because I realized I need to start making a ~list~ -> {neg=0.8, pos=0.2}
    @adammshankman AMAZING show tonight! Hard to figure who might be going home. #sytycd Sad you won't be back for so long. -> {neg=0.0, pos=1.0}
    "@Adrienne604 I used 2 do it -> {neg=0.44749596122778673, pos=0.5525040387722132}
    Im watching vids of Enter Shikari wanting to go to their signing 2morow. might just go on my own and sit oustide hmv all day like a hobo -> {neg=0.6628550350424198, pos=0.33714496495758023}
    "@DelilahLaClairr babe -> {neg=1.0, pos=0.0}
    @ the office enfermita -> {neg=0.39054550138468475, pos=0.6094544986153153}
    @ArchisM I m just really really wondering why nobody ever bakes cakes for me -> {neg=0.6221889055472264, pos=0.3778110944527736}
    @AceyBongos xbl is down for my birthday -> {neg=0.39054550138468475, pos=0.6094544986153153}
    @BetaChris I can't DM you because you aren't following me   thank you!!!! -> {neg=0.2777777777777778, pos=0.7222222222222222}
    @aniqa_x ha! yeah was alright! my sisters went shopping in london but i couldnt go cos i had school!!  and i already missed last week lol -> {neg=1.0, pos=0.0}
    @Amie__88 It does say delayed on the schedule. I only read the NEW part..LOL Guess I should have looked for the live. -> {neg=0.44749596122778673, pos=0.5525040387722132}
    @doombox i know  i hope your internet goes back to normal -> {neg=1.0, pos=0.0}
    @abigaill Eminem is one of my favorite rappers so thats why i was like wtf...mannn  not cool -> {neg=0.6221889055472264, pos=0.3778110944527736}
    @BarelyBlind wow finally a show i can drink at!!! lol. but who knows if i can even make it -> {neg=0.25, pos=0.75}
    "@ddaze123 Lol -> {neg=1.0, pos=0.0}
    @diinos the jo sisters sang my song? -> {neg=1.0, pos=0.0}
    @ArieleMoonfire   It's so fucked up. -> {neg=0.4336027713625866, pos=0.5663972286374134}
    @calvin141170 If it's any consolation I've had to abandon the back garden thanks to my super-reflective laptop screen -> {neg=0.6, pos=0.4}
    @AFMikey413 oh my so its still happening  perverts -> {neg=0.39054550138468475, pos=0.6094544986153153}
    @chamillionaire wish i could see you in corpus but bills are keeping me in sa. -> {neg=1.0, pos=0.0}
    @AlFerretti Poor Molly Brown tried to talk her boat into going back but no one will.     Good thing you and I are not there right now! -> {neg=0.3, pos=0.7}
    @ComcastCares I've been watching '24' online because of bad HD signal on DVR. Now I can't watch my Derby DVR for the same reason. -> {neg=1.0, pos=0.0}
    "@DJTygerLilly hey babygirl! Ur in MD? Damn -> {neg=1.0, pos=0.0}
    "@AmitTi thx you for your help -> {neg=0.05263157894736842, pos=0.9473684210526315}
    "@CorinneAM Oh no -> {neg=0.8571428571428571, pos=0.14285714285714285}
    **but that means no netball next weekend -> {neg=1.0, pos=0.0}
    @amariie sorry for touching your sunburn -> {neg=0.5454545454545454, pos=0.45454545454545453}
    @denissahady iyah ni  thank you syggg -> {neg=1.0, pos=0.0}
    WHAHHH!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! NOO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -> {neg=0.5882352941176471, pos=0.4117647058823529}
    "@Dravor thanks for that. not sure how permanent the sim swap change will make. also the link you gave is for iphone 3G -> {neg=1.0, pos=0.0}
    @DBISTWOFACED I know -> {neg=1.0, pos=0.0}
    so tierd from last nights homework I stayed up till 5am .it's official I hate school More than barneys I love you song crazy right ?? -> {neg=1.0, pos=0.0}
    "@30STMluva it's so sick -> {neg=0.39054550138468475, pos=0.6094544986153153}
    ( *hates* when you can't block a spam bot on your follow list....  ) -> {neg=0.6666666666666666, pos=0.3333333333333333}
    #inaperfectworld it would be sunny and 80 degrees like it should be in July. Fuck you New England! -> {neg=0.24046771733604475, pos=0.7595322826639552}
    "@_J_A_M_E_S awwww thx! Luv u! Now it's the day after my birthday -> {neg=0.21, pos=0.79}
    "@aisy yeah it isnt.  I went all out in soccer this year -> {neg=0.38461538461538464, pos=0.6153846153846154}
    #musicmonday I've totally been obsessed with @anberlin/@stephenanberlin/@anchorbraille lately. Wish I coulda seen them in WA... -> {neg=0.44749596122778673, pos=0.5525040387722132}
    ...doesnt know if that was an insult or not -> {neg=1.0, pos=0.0}
    "#pakistan #t20 #cricket afridi won't play that well in final -> {neg=0.6, pos=0.4}
    "@denimdebutante For some reason -> {neg=1.0, pos=0.0}
    @dmwrights I love those days! My hair gets real big and I feel the heat in my bones...sigh. The heat never makes it to my bones in OR. -> {neg=1.0, pos=0.0}
    @beccaalmond you ignore my txts -> {neg=0.24046771733604475, pos=0.7595322826639552}
    guy troubles...he just has to have a girlfriend... -> {neg=0.6628550350424198, pos=0.33714496495758023}
    @advil0 LOL I saw I had a mention and got so excited thinking it was #squarespace ... I was wrong -> {neg=0.46153846153846156, pos=0.5384615384615384}
    "@adrijohnson i think me too    yes he is -> {neg=0.49236641221374045, pos=0.5076335877862596}
    @berylboat whaaat that game is so bad -> {neg=1.0, pos=0.0}
    #FollowFriday @mattlogelin 'life and death. all in a 27-hour period' This cool guy lost his wife within 24 hours of their bub being born -> {neg=0.0, pos=1.0}
    " crap day - feel sick and my phone has the battery life of -> {neg=0.8058252427184466, pos=0.1941747572815534}
    @CARINAargh Nooo! I so didn't wna here dat! Hav u got woteva I missed on Monday? Sherdinator wnt b there but we may hav class  dangit -> {neg=0.9, pos=0.1}
    "@alexandramusic DIVERSITY WON  it was between saxophone player -> {neg=0.39054550138468475, pos=0.6094544986153153}
    @Callie06  youll find it eventually! and if not... get a new one! its good for kell anyways -> {neg=0.09090909090909091, pos=0.9090909090909091}
    @chaselisbon I'm on my iPhone and can't retweet a post co it doesn't let me  and I meant alt not apt in my private MSG LOL -> {neg=0.6153846153846154, pos=0.38461538461538464}
    "@AberOnline Bore da -> {neg=0.39054550138468475, pos=0.6094544986153153}
    @audreyellen my wrist is rebelling against me in a sore war. -> {neg=0.39054550138468475, pos=0.6094544986153153}
    @DeathbyVolcano it destroys my feet -> {neg=1.0, pos=0.0}
    @DonnieWahlberg Hey baby just saw in concert at Palladium had HOT PINK BOAS tried to get you attention you did not see me &amp; now im sad -> {neg=1.0, pos=0.0}
    "@AnneBeanVA oh -> {neg=0.39054550138468475, pos=0.6094544986153153}
    @bfrank87 Man! You keep missing all the 1 vs. 100 fun  I think it runs until 9:30 tonight though so maybe you'll get to play a bit. -> {neg=1.0, pos=0.0}
    @colbsi The Wombles were my Premier League team... before they disappeared. -> {neg=0.6153846153846154, pos=0.38461538461538464}
    @chadmichaelx yeah it makes me miserable. -> {neg=0.39054550138468475, pos=0.6094544986153153}
    "@BUNNIE311 ummm wrk is missin someone -> {neg=1.0, pos=0.0}
    @AndyBumatai  or your favorite rock tune in muzak...make me sad -> {neg=1.0, pos=0.0}
    "@anz_rocks19 yeh -> {neg=0.39054550138468475, pos=0.6094544986153153}
    @BoomKatt  i want more but its just so bittersweet lol -> {neg=0.5882352941176471, pos=0.4117647058823529}
    @digitalizzm hahaha agree.especially since we podium hogged!but I left after bump -> {neg=0.7, pos=0.3}
    @byronicman Don't kill people it's bad  Daddy told me -> {neg=0.... and 1093732 more bytes
```

