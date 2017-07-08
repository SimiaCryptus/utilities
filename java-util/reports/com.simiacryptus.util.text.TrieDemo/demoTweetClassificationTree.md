First, we load positive and negative sentiment tweets into two seperate models

Code from [TrieDemo.java:369](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L369) executed in 1.22 seconds: 
```java
      ArrayList<TweetSentiment> list = new ArrayList<>(TweetSentiment.load()
              .filter(x -> x.category == 1).limit(testingSize + trainingSize).collect(Collectors.toList()));
      Collections.shuffle(list);
      return list;
```
Returns: 
```
    [TweetSentiment{title='@09Casper @Squiffers tnx to you guys...  *hugs*'}, TweetSentiment{title='"@absolutelytrue Hey'}, TweetSentiment{title='#followfriday @itvdotcom are relatively new on here. Please follow them and make them feel at home'}, TweetSentiment{title='#howarewe 8/10. Waiting for breakfast to cook.Feeling quite happy and nice. Last exam today so I can relax after this for abit'}, TweetSentiment{title='Making Demo's!'}, TweetSentiment{title='"@A11igata_pie Drivin' like @BerlyAnne?! Do you know how scary that can be..well'}, TweetSentiment{title='#reading &quot;Boneman's Daughter&quot; by Ted Dekker which also looks good! #ADD Whatchu gonna do'}, TweetSentiment{title='&quot;Chile o Caliente&quot;  I love this color!!!  http://twitpic.com/5dk6b'}, TweetSentiment{title='"@1jaredPADALECKI Dude'}, TweetSentiment{title='&quot;I wish you would step back from that ledge my friend...&quot; Third Eye Blind  Yes Man inspired me what can i say'}, TweetSentiment{title='@AdamHoban -IT WILL NOT RAIN ON THE 17th OF JUNE!! I am planning to sleep in Ste Brennan's back garden that night! I'll be horrendified!'}, TweetSentiment{title='#goodsex is when your partner puts you in a whole new dimension ! A complete trance'}, TweetSentiment{title='@_Cube_ Na. I think he's from Palmerston North.'}, TweetSentiment{title='!!!!! I just saw the FUSE commercial for DMB playing at Beacon Theatre!!  made me super excited!'}, TweetSentiment{title='@aaronmyers Yeah I did. I took an Advil PM. Those things are leathal! Lol! But I slept like a baby though  Hope you rested well too!'}, TweetSentiment{title='"&quot;If you hear my cry'}, TweetSentiment{title='".. and hit unfollow before I can check their tweets  well I don't sit here 24/7. If you like my crazy tweets'}, TweetSentiment{title='#goodsex is GREAT'}, TweetSentiment{title='I am happy'}, TweetSentiment{title='&quot;youre the $!@*&amp;#' BEST!&quot;'}, TweetSentiment{title='@adykins ate ady ate ady ate ady!! it was nice talking to you'}, TweetSentiment{title='keeeeep following me thanks to new followers tell your friends/fam/strangers/followers to follow me!!  much luv'}, TweetSentiment{title='"...loving the tweets in sweet-sounding languages even if i can't always grasp what they actually say ~keep 'em coming'}, TweetSentiment{title='@666damo Awesome.....about 25 today'}, TweetSentiment{title='"@_emmajane_ tyvm  sorry they're blocking blip ... would say something about SSH tunneling to proxies here'}, TweetSentiment{title='&quot;Some people follow their dreams. Others hunt them down and beat them mercilessly into submission.&quot;'}, TweetSentiment{title='@_DINA will be good to have you as a #BlackBerry user #followfriday'}, TweetSentiment{title='#lr - im ssick of them anyone got one i can use as a feild car then roll  then shoot it: heheee sory i ju.. http://tinyurl.com/orr7of'}, TweetSentiment{title='#mw2 id like to see more camoflauges  THUMBS UP. There needs to be more weapons and i wish we cud play more like the marines not russians'}, TweetSentiment{title='"&quot;First we was chillin'}, TweetSentiment{title='&quot;kiss my acorns you big kluka!!&quot; -Pip'}, TweetSentiment{title='@AaronRenfree Well I won't be going there then! I love my starbucks'}, TweetSentiment{title='&quot;UP&quot; blew my mind. I laughed. I cried. I loved it'}, TweetSentiment{title='"&quot; Become a human transformer'}, TweetSentiment{title='@ all= thank you for following me'}, TweetSentiment{title='@2Serenity Thanks! Me too!'}, TweetSentiment{title='"@_CrC_  Yep!! We're a bunch of 30 something yr old teenagers  Well'}, TweetSentiment{title='#Inappropriatemovies I know How You Did Last Summer'}, TweetSentiment{title='@0oMiriamo0  i'm feeling awesome'}, TweetSentiment{title='@2sweetpea Thank you... I appreciate the love. And yes... I do know how you girls are.'}, TweetSentiment{title='#ihate when someone post a link without related description (maybe 'coz i have indescribable urge to click on every link that pops out'}, TweetSentiment{title='@aaronsnowphoto you are fabulous'}, TweetSentiment{title='#PSP Go is in the trending topics...not my kinda PSP LOL Paint Shop Pro is the only PSP!'}, TweetSentiment{title='*doing busy*'}, TweetSentiment{title='-- french test! oh gawdd.. lol  fashion show tonite!  come watch &lt;3'}, TweetSentiment{title='"@4_idiots hahaha! I don't work in a strip club you know  alrighty mister'}, TweetSentiment{title='.. is moving together with my boyfriend at the end of the summer and so happy about that. mozzarella sticks rock'}, TweetSentiment{title='&lt;3 Lalala. I'm content. This is fun.'}, TweetSentiment{title='"@1antidote - money back guarentee! if you don't lose the weight'}, TweetSentiment{title='"- on da phone with alicia hauser'}, TweetSentiment{title='..coulnd't stop singing and 'jonas moshing' b:L my throat kind of hurts  OMG AMAZING!'}, TweetSentiment{title='- My TT155 livemix from Tuesday: http://bit.ly/YjYKO - Let me know if tracklist needed..'}, TweetSentiment{title='; at least it was a close game this time....and I'm not gonna sweat it; Orlando's got the next one'}, TweetSentiment{title='...Hot and dry on the First Coast Monday. Grab the sunscreen and the sunglasses! You'll need them'}, TweetSentiment{title='@11th_echo really? Haha  Doesn't really suit her!'}, TweetSentiment{title='@30SECONDSTOMARS *Waits patiently for news* Ok so maybe not so patient... but I'm a woman dammit...'}, TweetSentiment{title='#followfriday @nacmacfeegle @failedmuso @trouserquandary  all guys guaranteed to put a smile on my face'}, TweetSentiment{title='(Allegedly) the hottest URLs on the web - you decide... http://www.alexa.com/hoturls (no adult content if you were wondering'}, TweetSentiment{title='#listeningto Don't! go away yet... Don't! go away yet......'}, TweetSentiment{title='"@_DESiMO_ i like that u're good  i'm good too'}, TweetSentiment{title='#firstrecord are we talking actual RECORD?  (yeah i'm that old) Michael Jackson's Off The Wall.  I bet some y'all never had vinyl.'}, TweetSentiment{title='"&quot;Should I write myself out of the history books'}, TweetSentiment{title='"@_santi no still visiting in Dallas until Thursday'}, TweetSentiment{title='...almost fforgot.... Twitter me bi-otch! (sounds dirty)'}, TweetSentiment{title='#delongeday is getting shorter from the top! only a little more to goooo'}, TweetSentiment{title='@_sinequanon Also: Crying!Ailton. Never.Gets.Old'}, TweetSentiment{title='happy face.'}, TweetSentiment{title='" Sunday mornings are my favorite    eh'}, TweetSentiment{title='@4cthepower hehe cud that app be me perhaps?!  i'll reply email this wkend but for now the informal me just wanted to say hi and thanks!'}, TweetSentiment{title='@_ashesandwine done! i'm sure @ryanstar will be happy today  btw i can't wait to see you tomorrow friend!'}, TweetSentiment{title='@30SECONDSTOMARS Itï¿½s NO voting vs anything - simple but true: yr *Stronger* cover is the BEST - w/o me *looking through pink glasses*!'}, TweetSentiment{title='"#goodsex when your man dips honey onto your body and he has to lick you out'}, TweetSentiment{title='"&quot;If man has no tea in him'}, TweetSentiment{title='@acetuk I'm just gonna post them on Flickr later - no Aperture processing - if I can resist'}, TweetSentiment{title='"#iremember when i used to wear a school uniform'}, TweetSentiment{title='praise God for this beautiful day!!!'}, TweetSentiment{title='"@ sushi tei with my mum'}, TweetSentiment{title='#fixreply @Tara72 yep just drove down for sunrise breakfast at Cafe du Monde and a stroll around the Quarter and back in time for lunch'}, TweetSentiment{title='No followers for me...still posted 6 updates...'}, TweetSentiment{title='"@_HONEYMONSTER @grcrssl'}, TweetSentiment{title='"*follows* surely a flower may brighten your day'}, TweetSentiment{title='"&quot;yeaaah! same mummy! TWICE!&quot; hahaha watching the mummy III  brenda's still awesome'}, TweetSentiment{title='"&quot;No matter what I do'}, TweetSentiment{title='haha have fun girl. Have fun at thaaat mormon dance tonightt too!!'}, TweetSentiment{title='#midnightromeo in 5 minnn!!!!  Can't wait! listening it that/putting it on my myspace then going to sleep! â™¥ Twentyyyyyy today! )'}, TweetSentiment{title='@30STMluva cool  add me &gt;&gt;&gt; http://bit.ly/ZevDS'}, TweetSentiment{title='@a3ofatl haha stupid. this is your big brother.'}, TweetSentiment{title='@aadi_aditi  just take a vicks ACtion 500+'}, TweetSentiment{title='@_shannon1234  kk ..talk later'}, TweetSentiment{title='a goodmorning back at cha @moovmnt'}, TweetSentiment{title='#gonzpiration montreal represent!   (World Record Attempt in Paris live &gt; http://ustre.am/2X3V)'}, TweetSentiment{title='@ miss_fleur - Welcome to the land of tweets  x'}, TweetSentiment{title='@abbydodge what link? and happy birthday!  Always say that to the Moms on the kids b'day... as important a day for you as them'}, TweetSentiment{title='" TOO HOT!! I WANT RAIN!!!! and everyone complains when it rains'}, TweetSentiment{title='@Ainz90 P.S I love you is good as well  You will always to be able to get temping work if you are struggling to find something perm'}, TweetSentiment{title='"::::::: Liesssss ::::::: If you had 24 hours to live'}, TweetSentiment{title='@ home eating with yvan'}, TweetSentiment{title='#DMCwmnSHOW enjoying the show very much from sharon'}, TweetSentiment{title='"#Spymaster is blatently a total rip off of 'Mafia Wars''}, TweetSentiment{title='*watching MTV movie awards'}, TweetSentiment{title='"@abigaeLettuce haha'}, TweetSentiment{title='.....free food always tastes better!'}, TweetSentiment{title='@abeguez Yes. Local theater has Digital 3D. You should demand a refund   Did they at least show the Toy Story 3 trailer?'}, TweetSentiment{title='@4321x I guess you were just ahead of the curve all along'}, TweetSentiment{title='@ABridgwater Ahh I ended up there yesterday when licensing.microsoft was down. Took me a while to resurface'}, TweetSentiment{title='"#flylady OK'}, TweetSentiment{title='"@AceConcierge hello hello'}, TweetSentiment{title='@2fatque Jack said to tell you hi back  Some photos of him: http://bit.ly/2Czjt &amp; http://bit.ly/YkfxG'}, TweetSentiment{title='"@4u2behealthy Me either'}, TweetSentiment{title='"@AJM1030 I know babe'}, TweetSentiment{title='@Abongachong Standard placebo tunning  ha'}, TweetSentiment{title='@ sonics yummmmmy'}, TweetSentiment{title='..lovely afternoon in the park..chatsworth picnic postponed until tomorrow'}, TweetSentiment{title='"&quot;i'd like to be under the sea'}, TweetSentiment{title='@ everyone! my phone's going to die. ill tweet tomorrow! goodnight!  &lt;*nadroj*&gt;'}, TweetSentiment{title='whats good tonight!! lets make it gooooood! holler'}, TweetSentiment{title='.....My 100th Update.....Dedicating this to the love of my life......Robert Pattinson....!!!Love you Handsome  xo'}, TweetSentiment{title='"@_hayles heya hun'}, TweetSentiment{title='@@ school...last study group sesh before i head into finals!! lets get that degreee...haha. k FOCUS!'}, TweetSentiment{title='...now the picture...!  http://twitpic.com/4ebck'}, TweetSentiment{title='@aah1981  yeah...so done....not.  thats what tomorrow is for!'}, TweetSentiment{title='"@211me Rob'}, TweetSentiment{title='@ahsze Thank you! I can finally see all these weird characters!'}, TweetSentiment{title='#3hotwords three hot words!! gosh i'm a genius'}, TweetSentiment{title='#3hotwords quitate eso Mami  I'm buggin lol'}, TweetSentiment{title='@_themillster_ Keep the faith.'}, TweetSentiment{title='"@ ChrisCorrigan: &quot;conversations that matter'}, TweetSentiment{title='#ONTD this is the BEST POST EVER. UNF @jaredleto  http://community.livejournal.com/ohnotheydidnt/35733261.html'}, TweetSentiment{title='@30SECONDSTOMARS lately you mention all the song names you're working on. so probably maybe perhaps possibly you'll tell us the rest?'}, TweetSentiment{title='"@ dad's'}, TweetSentiment{title='@advisormackenzi I'm one of your new students going to Chile!  sweet'}, TweetSentiment{title='@2Hood4Hollywood oh...ok'}, TweetSentiment{title='@Aarika_Johnson I do &quot;order&quot; people's dogs around.  And cats too. Something about me and animals.'}, TweetSentiment{title='moncton this week sometime .. hope so  gonna see my hunny'}, TweetSentiment{title='@ prashantdr Its one of the pictures I found at Flickr taken by a German guy ! Tried replyin u in DM'}, TweetSentiment{title='#BGT Follow me guys'}, TweetSentiment{title='@ a flower shop. We're taking some flowers to my granpa (rip) @ church'}, TweetSentiment{title='&quot;breakeven&quot; -the script  #musicmonday idiots'}, TweetSentiment{title='#iremember when there were only two of us. Life is so different and better now there's three of us'}, TweetSentiment{title='dance flick was hilariouss!!!'}, TweetSentiment{title='@abieejoness i will l8r lol xx'}, TweetSentiment{title='@75thranger1983 thanks! I star in 5 or more movies a month so u should have no problem seeing me again'}, TweetSentiment{title='@Ainz90 Is yr mum pointing out the obvious?'}, TweetSentiment{title='-- *hummin* I SAY &quot;shut up &amp; put your money where your mouth is&quot; ...headin' out to reality. Cheers Twitter world!'}, TweetSentiment{title='#follow friday...  follow @benjamin_cook  cos he's funny.'}, TweetSentiment{title='@agnesrooos: Hey baby!'}, TweetSentiment{title='(@redsoxfan13x) song stuck in my head. &quot;shut up &amp; let me goooo!&quot;'}, TweetSentiment{title='@21GramsLtd Awesome thankyouu'}, TweetSentiment{title='&quot;Two 18 yr-old will do&quot; says @colterh in reference to the age of his scotch preference this evening. Read it how you want!'}, TweetSentiment{title='"@akhilak happy travels'}, TweetSentiment{title='#followfriday @krystynchong she's a star  ????????????????ï¿½ï¿½ï¿½???????????????????????????????????????????????????????'}, TweetSentiment{title='@abigayle1998 you can do it abie! i swear.'}, TweetSentiment{title='"(@Restrictor) Oh'}, TweetSentiment{title='? @Alexmetric Hello sir.. Can't wait for when you have time for the podcast. Loving the new single! Peace E  http://tr.im/m4Hs'}, TweetSentiment{title='@ work.. This week some new instrumentals.. stay updated'}, TweetSentiment{title='...but now i saw the trailer and i think its quite good'}, TweetSentiment{title='"(@jordaaaannnn) One last tweet. @combustiblesong Night.  @TehStalker Shut up'}, TweetSentiment{title='@8ball_ thanks'}, TweetSentiment{title='@ artomatic looking for @ladyglock!  awesome place!'}, TweetSentiment{title='@Actionjackson13 your retarded'}, TweetSentiment{title='@140lover i would be one of those awesome ladies!'}, TweetSentiment{title='.@HempNews I know; I was just playing.'}, TweetSentiment{title='..feeling refreshed! another 4 day work week  Any suggestions for this weekend?!'}, TweetSentiment{title='"@adthrelfall Ta'}, TweetSentiment{title='"@adamariee lol oooh yeah ;) im stoked girl'}, TweetSentiment{title='@30SECONDSTOMARS : you guys are on a roll!!!! I hope you are getting some sleep in between'}, TweetSentiment{title='"@1capplegate Ah'}, TweetSentiment{title='@ShannonHerod &quot;It is always your next move.&quot; Napoleon Hil http://tr.im/mR0u'}, TweetSentiment{title='"#goodsex Shut up'}, TweetSentiment{title='#iranelection  please send respectful requests re scheduled maintenance to support@twitter.com   BE NICE!  TY'}, TweetSentiment{title='@0010x0010 I guess so  And confessing I'm quite often haunted by Patricia too... By a lot of David's actresses actually... All of them !'}, TweetSentiment{title='"@10rdBen That'}, TweetSentiment{title='#twitterpornnames lmao mine is elvis malone @injoy1984 is yours bailey winnipeg? hehehe'}, TweetSentiment{title='#iremember the summer of 2004. It was magic'}, TweetSentiment{title='...to watch them. He is the biggest anti-talent i've ever seen in movie making.'}, TweetSentiment{title='@_daveyb  Kate is worth it!'}, TweetSentiment{title='&lt;3's when hubby doesn't have to work saturdays - he is still sleeping'}, TweetSentiment{title='"@acarvin I encourage it.  On a serious note'}, TweetSentiment{title='@abiruiz you watching the right game?'}, TweetSentiment{title='"&quot;Me: Dude'}, TweetSentiment{title='&quot;leave the pieces&quot; - the wreckers... hmm'}, TweetSentiment{title='#wheniruletheworld Im gonna #fixreplies #GetPembsDaveAJob on #creditcrunchtv watch ... and 1318172 more bytes
```

Code from [TrieDemo.java:375](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L375) executed in 0.03 seconds: 
```java
      ArrayList<TweetSentiment> list = new ArrayList<>(TweetSentiment.load()
              .filter(x -> x.category == 0).limit(testingSize + trainingSize).collect(Collectors.toList()));
      Collections.shuffle(list);
      return list;
```
Returns: 
```
    [TweetSentiment{title='"@ work. can't stop thinking about the weekend. it kinda sucks. but still can't stop hoping. fuck'}, TweetSentiment{title='this sucks.'}, TweetSentiment{title='"@3thbi there were guys and I called 777 3ala 6ool'}, TweetSentiment{title='@_YoureMyHeroine :'( i really know how you feelin. i wish i could hug you'}, TweetSentiment{title='#seb-day #seb-day #seb-day #seb-day #seb-day #seb-day #seb-day #seb-day #seb-day #seb-day #seb-day #seb-day #seb-day #seb-day #seb-day'}, TweetSentiment{title='"  RIP'}, TweetSentiment{title='; CELINE COME TO SUMMER CON!!! it's not fair ... :'( i'm gonna miss you so much..'}, TweetSentiment{title='not happy with pc right now.'}, TweetSentiment{title='"@3nalicious WHAT??? Ok'}, TweetSentiment{title='my tummy hurts  goodnight twitter'}, TweetSentiment{title='I wants to plaayyyy'}, TweetSentiment{title='@_gabby15 i want to so bad.  someone needs to book the tiks cause i dnt have a credit card  i need to see my queen. even if its shit seats'}, TweetSentiment{title='#48 Jimmy Johnson won!  Had the better car.  Too bad for #24 who didn't have a good car.'}, TweetSentiment{title='"&quot;Taken&quot; was good'}, TweetSentiment{title='isabelle had to take a trip to doggy hospital'}, TweetSentiment{title='" devistated'}, TweetSentiment{title='::sigh:: we didnt win the contest . stupid popularity contest. i had so many votes for her also.  im realy sad. i had such a good feeling'}, TweetSentiment{title='6:30...what's wrong wit it?'}, TweetSentiment{title='...which was a plus.  It's also a plus to know that there are vegan cops out there (even though my officer wasn't     ).'}, TweetSentiment{title='@_maisy naaaw pooor mai  are yu alright  do milk and coookies do the trick?'}, TweetSentiment{title='equals sad. In case you were wondering.'}, TweetSentiment{title='@lisad stopped talking to me. I should stalk her. ^^'}, TweetSentiment{title='@a_web_designer no--I've got swine flu!   Feel like a truck has run over my head.'}, TweetSentiment{title='"&quot;Acting stupid'}, TweetSentiment{title='....I miss my mommy'}, TweetSentiment{title='"@4029news Wow'}, TweetSentiment{title='Stella has gone to the Vets. She ran into a barbed wire fence- again. &amp; tore open her leg- again. Silly!'}, TweetSentiment{title='@Afsoon I didn't knowww  so embarassing!'}, TweetSentiment{title='@_glurch what if all those answers apply to me?'}, TweetSentiment{title='/me misses watching Swat Kats.   Cartoon Network used to rock then'}, TweetSentiment{title='im the only one with an ipod full of pictures!'}, TweetSentiment{title='@_CrC_ hells ya! But they never respond to me'}, TweetSentiment{title='&amp; my phone is dying'}, TweetSentiment{title='#mw2 PLEEEEEEEEEEEEZ HAV THE G36C BACK PLEEEEZ OTHERWISE ILL BE SAD'}, TweetSentiment{title='" He read my last message on myspace'}, TweetSentiment{title='..getting bored of twitter because nobody famous talks to me   lol'}, TweetSentiment{title='@Actlikeuknow me too!!!!  i'm still at work'}, TweetSentiment{title='#iremember when i was gay  kkk'}, TweetSentiment{title='#reddit still down'}, TweetSentiment{title='@Akaasia I'd love to go to Rome  too poor for travel LOL never read the book though'}, TweetSentiment{title='#musicmondays my latest songwriting attempt - sorry it is low quality  http://bit.ly/13dqv5'}, TweetSentiment{title='...forgot to mention...I also have a cold *sniff*sniff*'}, TweetSentiment{title='How do I make my headache go away?'}, TweetSentiment{title='"#FF Wish I could spare the time'}, TweetSentiment{title='"...cooked all the way. &quot;hope you don't get sick'}, TweetSentiment{title='@_Kaiya_ miss u 2 ! Its your off day we didn't have lunch.   ATL is ok good so far! A lot of blacks! I've experienced a culture shock! Lol'}, TweetSentiment{title='@_Chardonnay_ WHY DID YOU RE-TWEET THAT! I WAS ROOTING FOR LEBRON DAMN IT ! LOL'}, TweetSentiment{title='...Off to work now then'}, TweetSentiment{title='@Addictivemusic I came back and you had gone'}, TweetSentiment{title='not going to green man now. So sad'}, TweetSentiment{title='@aestasbeyond Actually my stalker is from that group of people too come to think of it...the only guy I really liked killed himself'}, TweetSentiment{title='"@a_pink_dream are you jealous of my half-bowl hair???? That's all I can see in the mirror'}, TweetSentiment{title='...hmm which is worse.. that im going to fail.. or that i deserve to fail  *criessss* how issit already 7:45... where did my day goo?!?!'}, TweetSentiment{title='*Sigh* The Macbook just flashed me its 'warning low battery' message. Soon I shall be gone. For 3-5 days until new cord arrives.'}, TweetSentiment{title='"@_Dappy_ about Shotts u know'}, TweetSentiment{title='.been doing nothing!.'}, TweetSentiment{title='.@teerahteerah me too! I've been starting to dislike him for awhile.'}, TweetSentiment{title='@_JadeLakeasha thats remind me i gotta listen to lvatt i aint listened to it in 3 days cuz ive been at my grandmas house :|'}, TweetSentiment{title='"@__Kizzle Not bad! I tried to comment you on utube and realized u don't have one anymore.  So'}, TweetSentiment{title='@AimeeMayo YOU NEVER CALLED ME BACK!!!!!!'}, TweetSentiment{title='orientations at 8 in the morning.  Fuck college!'}, TweetSentiment{title='@ the davidson library studying for finals  with @jessallison88'}, TweetSentiment{title='@AdelaideSports I am lost. Please help me find a good home.'}, TweetSentiment{title='" damn'}, TweetSentiment{title='"@_thalita_ aah saudade de vocÃª'}, TweetSentiment{title='..y everything is so hard? :/'}, TweetSentiment{title='@4boys4now She can't respond to tweets from her phone.'}, TweetSentiment{title='Friend's emailed me a list of signs you're middle aged woman and I relate to 90%... incl. forgetfulness ...so I am now becoming my Mum'}, TweetSentiment{title='@11_26 AH THIS IS IT FOR THEM'}, TweetSentiment{title='i don't want school anymoreee.'}, TweetSentiment{title='had a blood test today!!'}, TweetSentiment{title='#e3 ... I feel so sorry for Abagail.'}, TweetSentiment{title='#iremember my highschool years  where i get to see my friends 5 days a week. (even though it was about 6 months ago) feels like ages!'}, TweetSentiment{title='(@gbsinkers) Oh snap!  Just broke my windshield while replacing my wiper blades.'}, TweetSentiment{title='i'll be leaving home tomorrow.'}, TweetSentiment{title='@aaronvest I've had two avocado trees and a pomegranate tree - both are dead now. There is no Martha in me.'}, TweetSentiment{title='"@1capplegate OK Whoau'}, TweetSentiment{title='"@_nicolax lmao'}, TweetSentiment{title='#slush 10 down; no keepers  though one scifi was intriguingly original but rushed'}, TweetSentiment{title='#hoppusday ... no longer more important than Swine Flu'}, TweetSentiment{title='#iranelection -people are just tired of getting sttepped on.You can't do this! You can't do that! Do what I tell you do! Folks are tired'}, TweetSentiment{title='might not be able to go to Cali. Maybe Canada? Or sham I bribe my mom to go to London!!!??? xD'}, TweetSentiment{title='good night twittaz'}, TweetSentiment{title='@aishajeiel LOL!!! OH NO THATS TOO EARLY!!! LOL!!! I THINK IM GOING BACK 2 BR 2NITE!  IF NOT IM GOING 2 REPUBLIC AGAIN!'}, TweetSentiment{title='"@1st_Place oh I'm sorry'}, TweetSentiment{title='"...I was going to post up a picture of Flogging Molly...but I was so amazed'}, TweetSentiment{title='@Agent_M I take it this means you failed to procure a copy.'}, TweetSentiment{title='@5windows I guess I am not one of your fav friends.'}, TweetSentiment{title='? Adam Lambert didn't win  brought his new single anyway http://tr.im/mTbW'}, TweetSentiment{title='@a_dorkable my headphones r in my locker.'}, TweetSentiment{title='"@AdrianHiggs Don't think I'll get over it'}, TweetSentiment{title='@adubb1914 yes me to mate'}, TweetSentiment{title='&quot;the iPhone activation server is temporarily unavailable&quot;'}, TweetSentiment{title='"- they forgot to put my requested shot of hazelnut in my triple-shot of espresso cappuccino today! *sigh* rain on me'}, TweetSentiment{title='No one to have dinner with...'}, TweetSentiment{title='4's reallyy??? #PakCricket'}, TweetSentiment{title='... I feel sleepy  Getting ready for track practice.'}, TweetSentiment{title='"@_micster Dawww'}, TweetSentiment{title='#apprentice no way!!'}, TweetSentiment{title='(@anneandreah) Oh snap! Just got dissed. The party is moving to another venue and I didn't get invited!'}, TweetSentiment{title=':o They washed off the Spiderman! How dare they'}, TweetSentiment{title='@agnoaa photoshop sucks'}, TweetSentiment{title='"   J'}, TweetSentiment{title='&gt;@faisalkapadia: Dozens of students are still missing in the cadet college kidnapping  #pakistan'}, TweetSentiment{title='#ie8 looks ok but doesn't seem to allow feed subs with google reader and delicious buttons seems broke.'}, TweetSentiment{title='what the hell'}, TweetSentiment{title='"@AdamOrtega LMFAO! Her eyebrows are a bit thick for me but not bad. He looks kinda ragged'}, TweetSentiment{title='"@1cutechicwitfm  A friend of mine said those words earlier and I am just passing it along'}, TweetSentiment{title='&quot;keeping your guard up in a relationship keeps the love out too.&quot; ????'}, TweetSentiment{title='- having to stay up and watch my sister throw up. she is so very hammered. and this is really not fun...  long night for me. hmmm.'}, TweetSentiment{title='*website down*'}, TweetSentiment{title='@4everaddicted haha i know xD i dont want to study for school'}, TweetSentiment{title='- doesn`t have a phone.'}, TweetSentiment{title='@_cupcake scary!  glad everyone is okay though'}, TweetSentiment{title='"@__Fran__ I brought it last year at the airport'}, TweetSentiment{title='@_robink_ @clare_b I don't know.  I can't seaech the story. I'm at work and can't and won't open that here hahahah'}, TweetSentiment{title='rob has left the u.s'}, TweetSentiment{title='lesson learnt... I look like a rock star from kiss haha'}, TweetSentiment{title='failed my psychology class last semester. I don't know if i want to go back in the fall now. Wow that's depressing and whats even more'}, TweetSentiment{title='**Breaking News**Jim Balsillie's attempt to bring the Phoenix Coyotes to Hamilton has been rejected by an Arizona bankruptcy judge   UGHH!'}, TweetSentiment{title='"#photography #fail'}, TweetSentiment{title='@_huny wow did she really and not tell you'}, TweetSentiment{title='#addictedto my blackberry 8900'}, TweetSentiment{title='is sad......www.whatkatedidnext.wordpress.com'}, TweetSentiment{title='"#inaperfectworld I wouldn't be sore right now'}, TweetSentiment{title='" Finn fell of the balcony'}, TweetSentiment{title='Carrie has a bad eye infection...just waiting for hear from the vets to see if they found anything'}, TweetSentiment{title='@absurdities  Thanks for the warning. I will avoid that one even though it looks cute.'}, TweetSentiment{title='....RIP Mr David Carradine....'}, TweetSentiment{title='"@AddieBef Lol  I was wrong then ahah'}, TweetSentiment{title='..woahh! i dunno how to play &quot;Mafia Wars&quot;  ...'}, TweetSentiment{title='.pissed. .cant stop thinkin' bout some stuff in thee past. !! .i gotta 4get &amp;&amp; movee on buh i can't.'}, TweetSentiment{title='@adamtal it's tehila's car so I don't have it anymore'}, TweetSentiment{title='@_rachaelll why today  I swwwear you guys do that intentionally'}, TweetSentiment{title='I don't feel good again! Wth?'}, TweetSentiment{title='@accommodated'}, TweetSentiment{title='"@3Steez aaahhh. i know. but i just don't have time'}, TweetSentiment{title='#REASONSFORLEAVINGTHELASTJOB: my boss killed me on spymaster'}, TweetSentiment{title='#mariners game - pitching duel but not in a good way - in a really ugly way -'}, TweetSentiment{title='" The side of my foot keeps getting these stabbing pains. ARE YOU BEHIND THIS'}, TweetSentiment{title='@a94mae oh watever i bet you do have one you just dont wanna put one up'}, TweetSentiment{title='Thanks for your definition of throwbie!  Editors reviewed your entry and have decided to not publish it.'}, TweetSentiment{title='"....I love my sister'}, TweetSentiment{title='@_dirtytalk_ i still don't like her. i'm just praying people don't kill cudi for me.'}, TweetSentiment{title='@alannahwastell History'}, TweetSentiment{title='"- bed ; I gotta get up early'}, TweetSentiment{title='@100konacoffee &quot;Google is my number one source of web traffic&quot; Not fo me...'}, TweetSentiment{title='@_faeriequeen Im watching Shakespeare In Love. Also loving the breeches and silk! Was craving Joseph after not seeing him in Chi'}, TweetSentiment{title='".@MonicaOnline I wish Sprint wld offer a pkg to help off-set the AT&amp;T ETF &amp; encourage switching. But I found a real person'}, TweetSentiment{title='-.- I have no idea...I'm just sitting at home alone  lol BORED!!!!'}, TweetSentiment{title='"...a while'}, TweetSentiment{title='@aka55 texted him about how i know he's busy this week but i'm gonna be unavailable for the next 2. think if he cared he would've replied'}, TweetSentiment{title='...workout was tough tonight!  I can tell I took a few days off.'}, TweetSentiment{title='#itsucks that it's Thursday and not Friday..'}, TweetSentiment{title='@_Downtothewire eyyy is that all?'}, TweetSentiment{title='"(@meesasun) Pei wanted to give me a high five'}, TweetSentiment{title='@ work; alone'}, TweetSentiment{title='&quot;going&quot; to lunch but nowhere to go'}, TweetSentiment{title='time to change to cs4. sooner or later.'}, TweetSentiment{title='Imissyou GUys.'}, TweetSentiment{title='@1outside haha cool. I like this big glass brick one - is it the National Theatre? But I have never been inside'}, TweetSentiment{title='" Senior checkout. In line. I'm out side. I have to get inside'}, TweetSentiment{title='....today is just not going well at all....'}, TweetSentiment{title='great my dad locked himself out of his car n its on... N we are 5 hours from home'}, TweetSentiment{title='@_Marine I miss U sister.. When i can talk to u ? shit ! I want to talk to u right now on msn  i miss u sooooo much...'}, TweetSentiment{title='"#IranElection US is still '....engaging with Iran'}, TweetSentiment{title='@_robink_ hahahahah I call all cars hoopdees when I'm drunk  lollol'}, TweetSentiment{title='@ the house.. double ear infection.. Not the move !!'}, TweetSentiment{title='#barakatday #barakatday #barakatday  I cant go to bed yet  LOL stupid myspazz wont let me read my message'}, TweetSentiment{title='&quot;if u take away my rainbow I will cry... give me back my sunshine&quot;.. man I miss Floetry'}, TweetSentiment{title='@ the train crash in DC .....'}, TweetSentiment{title='#takingdonation DM ME TO PAY MY BILL  $177'}, TweetSentiment{title='".@tsuasai was sweet and brought home some Thai tea for me.  Then she was clumsy'}, TweetSentiment{title='My current headset is on its deathbed now!  My dad gave it to me just 3 weeks back!'}, TweetSentiment{title='@_sputnik but weight is so much easier to maintain than your skin!'}, TweetSentiment{title='@1critic @AgentBooth is mad at us'}, TweetSentiment{title='.I won't be going to the A-Day game if it's raining because of the pathetic no-umbrella-rule in Bryant-Denny. And it is raining now.'}, TweetSentiment{title='&amp;EASYxTARGET sooo gross outside right now  not good summer weather lol'}, TweetSentiment{title='#lofnotc  @snaaa  saturday work is a friday fail'}, TweetSentiment{title='@adammorland poor guy'}, TweetSentiment{title='is cold'}, TweetSentiment{title='#45p Damn it sounds like Carnival in my living room! Got my soca on since I can't go to Zumba'}, TweetSentiment{title='#dontyouhate when ur tryin to nap w this headache n the club car outside is pumping OMEGA? Yep I live in wash hts'}, TweetSentiment{title='@_BBreezy Exactly it not sexy @ all'}, TweetSentiment{title='..yes i realized after that last tweet..that no..no one cares'}, TweetSentiment{title='...and now he's fast asleep!  Not fair'}, TweetSentiment{title='@_kathe_  i try but its sad'}, TweetSentiment{title='Sometimes I wonder...'}, TweetSentiment{title='@abzfosho'}, TweetSentiment{title='*just wants to give hugs to all the amazing rwoc who are hurting tonight*'}, TweetSentiment{title='..wishes somebody was in the room with me to hear me answering all the right questions to Jeopardy!'}, TweetSentiment{t... and 1318631 more bytes
```

Code from [TrieDemo.java:381](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L381) executed in 397.82 seconds: 
```java
      HashMap<String, List<String>> map = new HashMap<>();
      map.put("pos", tweetsPositive.stream().limit(trainingSize).map(x -> x.getText()).collect(Collectors.toList()));
      map.put("neg", tweetsNegative.stream().limit(trainingSize).map(x -> x.getText()).collect(Collectors.toList()));
      return new ClassificationTree().setVerbose(System.out).categorizationTree(map, 32);
```
Returns: 
```
    com.simiacryptus.util.text.ClassificationTree$$Lambda$102/766572210@12f40c25
```
Logging: 
```
    "wfriday " -> Contains={neg=5, pos=99}	Absent={neg=4995, pos=4901}	Entropy=-0.976505
      " follow" -> Contains={neg=3, pos=16}	Absent={neg=2, pos=83}	Entropy=-0.251868
        "me " -> Contains={neg=2, pos=10}	Absent={neg=0, pos=73}	Entropy=-0.140684
      "hank" -> Contains={neg=46, pos=279}	Absent={neg=4949, pos=4622}	Entropy=-0.977671
        " it's " -> Contains={neg=7, pos=4}	Absent={neg=39, pos=275}	Entropy=-0.543555
          "shank" -> Contains={neg=6, pos=5}	Absent={neg=33, pos=270}	Entropy=-0.504471
            "ed" -> Contains={neg=13, pos=35}	Absent={neg=20, pos=235}	Entropy=-0.464382
              "T" -> Contains={neg=9, pos=11}	Absent={neg=4, pos=24}	Entropy=-0.768114
                "he " -> Contains={neg=6, pos=4}	Absent={neg=3, pos=7}	Entropy=-0.931132
                "ve" -> Contains={neg=3, pos=7}	Absent={neg=1, pos=17}	Entropy=-0.569149
              " bu" -> Contains={neg=4, pos=6}	Absent={neg=16, pos=229}	Entropy=-0.367862
                "d tha" -> Contains={neg=3, pos=7}	Absent={neg=13, pos=222}	Entropy=-0.324127
                  "@" -> Contains={neg=6, pos=189}	Absent={neg=7, pos=33}	Entropy=-0.280805
                    "3" -> Contains={neg=3, pos=10}	Absent={neg=3, pos=179}	Entropy=-0.171071
                      " Thanks" -> Contains={neg=2, pos=55}	Absent={neg=1, pos=124}	Entropy=-0.087096
                        "." -> Contains={neg=1, pos=39}	Absent={neg=0, pos=85}	Entropy=-0.078566
                    " w" -> Contains={neg=5, pos=5}	Absent={neg=2, pos=28}	Entropy=-0.539410
                      "me " -> Contains={neg=2, pos=9}	Absent={neg=0, pos=19}	Entropy=-0.379255
        "#musicmo" -> Contains={neg=2, pos=68}	Absent={neg=4947, pos=4554}	Entropy=-0.976979
          "ong" -> Contains={neg=2, pos=8}	Absent={neg=0, pos=60}	Entropy=-0.161251
          "&quot;" -> Contains={neg=309, pos=594}	Absent={neg=4638, pos=3960}	Entropy=-0.976989
            " miss" -> Contains={neg=16, pos=3}	Absent={neg=293, pos=591}	Entropy=-0.900977
              " want to" -> Contains={neg=11, pos=3}	Absent={neg=282, pos=588}	Entropy=-0.890734
                " is the " -> Contains={neg=10, pos=4}	Absent={neg=272, pos=584}	Entropy=-0.885524
                  "s not " -> Contains={neg=10, pos=2}	Absent={neg=262, pos=582}	Entropy=-0.878946
                    "&quot; f" -> Contains={neg=9, pos=4}	Absent={neg=253, pos=578}	Entropy=-0.870912
                      " &quot; " -> Contains={neg=9, pos=6}	Absent={neg=244, pos=572}	Entropy=-0.865780
                        "ot; was " -> Contains={neg=7, pos=4}	Absent={neg=237, pos=568}	Entropy=-0.859413
                          "en t" -> Contains={neg=10, pos=2}	Absent={neg=227, pos=566}	Entropy=-0.853389
                            " have t" -> Contains={neg=9, pos=5}	Absent={neg=218, pos=561}	Entropy=-0.842851
                              " http://" -> Contains={neg=7, pos=41}	Absent={neg=211, pos=520}	Entropy=-0.834967
                                "A" -> Contains={neg=5, pos=9}	Absent={neg=2, pos=32}	Entropy=-0.527312
                                  "/t" -> Contains={neg=2, pos=9}	Absent={neg=0, pos=23}	Entropy=-0.339442
                                "quot;The" -> Contains={neg=15, pos=18}	Absent={neg=196, pos=502}	Entropy=-0.846767
                                  "y " -> Contains={neg=2, pos=10}	Absent={neg=13, pos=8}	Entropy=-0.861698
                                    "d" -> Contains={neg=8, pos=2}	Absent={neg=5, pos=6}	Entropy=-0.884821
                                  "&quot; t" -> Contains={neg=8, pos=7}	Absent={neg=188, pos=495}	Entropy=-0.836103
                                    "; &quot;" -> Contains={neg=1, pos=16}	Absent={neg=187, pos=479}	Entropy=-0.829359
                                      " today" -> Contains={neg=8, pos=4}	Absent={neg=179, pos=475}	Entropy=-0.836201
                                        "&quot;yo" -> Contains={neg=8, pos=8}	Absent={neg=171, pos=467}	Entropy=-0.826581
                                          "ng&quot;" -> Contains={neg=7, pos=7}	Absent={neg=164, pos=460}	Entropy=-0.818700
                                            "&quot; a" -> Contains={neg=8, pos=9}	Absent={neg=156, pos=451}	Entropy=-0.810990
                                              "&quot;G" -> Contains={neg=6, pos=4}	Absent={neg=150, pos=447}	Entropy=-0.801952
                                                ""&quot;I" -> Contains={neg=14, pos=23}	Absent={neg=136, pos=424}	Entropy=-0.793579
                                                  "in" -> Contains={neg=3, pos=12}	Absent={neg=11, pos=11}	Entropy=-0.894573
                                                    " a" -> Contains={neg=6, pos=4}	Absent={neg=5, pos=7}	Entropy=-0.974938
            "@" -> Contains={neg=1932, pos=2390}	Absent={neg=2706, pos=1570}	Entropy=-0.968294
              " miss" -> Contains={neg=96, pos=25}	Absent={neg=1836, pos=2365}	Entropy=-0.971455
                " miss y" -> Contains={neg=15, pos=12}	Absent={neg=81, pos=13}	Entropy=-0.659652
                  "an" -> Contains={neg=4, pos=9}	Absent={neg=11, pos=3}	Entropy=-0.833044
                  "I missed" -> Contains={neg=6, pos=4}	Absent={neg=75, pos=9}	Entropy=-0.530775
                    " we" -> Contains={neg=8, pos=4}	Absent={neg=67, pos=5}	Entropy=-0.446634
                      "r" -> Contains={neg=57, pos=2}	Absent={neg=10, pos=3}	Entropy=-0.337540
                        "co" -> Contains={neg=11, pos=2}	Absent={neg=46, pos=0}	Entropy=-0.210269
                " I wish " -> Contains={neg=24, pos=2}	Absent={neg=1812, pos=2363}	Entropy=-0.967963
                  "er" -> Contains={neg=15, pos=0}	Absent={neg=9, pos=2}	Entropy=-0.434056
                  " welcome" -> Contains={neg=0, pos=25}	Absent={neg=1812, pos=2338}	Entropy=-0.967341
                    "n't " -> Contains={neg=215, pos=114}	Absent={neg=1597, pos=2224}	Entropy=-0.968560
                      " y" -> Contains={neg=42, pos=52}	Absent={neg=173, pos=62}	Entropy=-0.874099
                        "fo" -> Contains={neg=18, pos=9}	Absent={neg=24, pos=43}	Entropy=-0.931745
                          "ca" -> Contains={neg=5, pos=7}	Absent={neg=13, pos=2}	Entropy=-0.774760
                          "ke " -> Contains={neg=9, pos=4}	Absent={neg=15, pos=39}	Entropy=-0.857460
                            "ut" -> Contains={neg=6, pos=4}	Absent={neg=9, pos=35}	Entropy=-0.776344
                              "ad" -> Contains={neg=4, pos=6}	Absent={neg=5, pos=29}	Entropy=-0.693650
                                " s" -> Contains={neg=0, pos=14}	Absent={neg=5, pos=15}	Entropy=-0.576660
                                  " th" -> Contains={neg=4, pos=6}	Absent={neg=1, pos=9}	Entropy=-0.768318
                        " I don't" -> Contains={neg=15, pos=13}	Absent={neg=158, pos=49}	Entropy=-0.798377
                          "v" -> Contains={neg=4, pos=9}	Absent={neg=11, pos=4}	Entropy=-0.873122
                          "an" -> Contains={neg=103, pos=19}	Absent={neg=55, pos=30}	Entropy=-0.749318
                            "y" -> Contains={neg=82, pos=9}	Absent={neg=21, pos=10}	Entropy=-0.579654
                              "ver" -> Contains={neg=6, pos=4}	Absent={neg=76, pos=5}	Entropy=-0.406551
                                "us" -> Contains={neg=17, pos=4}	Absent={neg=59, pos=1}	Entropy=-0.298673
                                  "ee" -> Contains={neg=10, pos=0}	Absent={neg=7, pos=4}	Entropy=-0.637656
                                  "n't w" -> Contains={neg=10, pos=1}	Absent={neg=49, pos=0}	Entropy=-0.155380
                              " s" -> Contains={neg=14, pos=3}	Absent={neg=7, pos=7}	Entropy=-0.832167
                            "ng " -> Contains={neg=10, pos=14}	Absent={neg=45, pos=16}	Entropy=-0.867856
                              "ly" -> Contains={neg=2, pos=8}	Absent={neg=8, pos=6}	Entropy=-0.891528
                              "ar" -> Contains={neg=15, pos=0}	Absent={neg=30, pos=16}	Entropy=-0.754319
                                "m" -> Contains={neg=23, pos=7}	Absent={neg=7, pos=9}	Entropy=-0.858150
                                  "er" -> Contains={neg=9, pos=6}	Absent={neg=14, pos=1}	Entropy=-0.704694
                      "m sorry" -> Contains={neg=23, pos=1}	Absent={neg=1574, pos=2223}	Entropy=-0.960677
                        " I'm sor" -> Contains={neg=9, pos=1}	Absent={neg=14, pos=0}	Entropy=-0.359744
                        "at sucks" -> Contains={neg=13, pos=0}	Absent={neg=1561, pos=2223}	Entropy=-0.959309
                          " no" -> Contains={neg=277, pos=194}	Absent={neg=1284, pos=2029}	Entropy=-0.958987
                            " A" -> Contains={neg=10, pos=24}	Absent={neg=267, pos=170}	Entropy=-0.953888
                              "ou" -> Contains={neg=1, pos=15}	Absent={neg=9, pos=9}	Entropy=-0.725471
                              "o " -> Contains={neg=177, pos=83}	Absent={neg=90, pos=87}	Entropy=-0.938560
                                "time" -> Contains={neg=5, pos=10}	Absent={neg=172, pos=73}	Entropy=-0.873544
                                  "ble" -> Contains={neg=4, pos=10}	Absent={neg=168, pos=63}	Entropy=-0.841252
                                    " ho" -> Contains={neg=27, pos=1}	Absent={neg=141, pos=62}	Entropy=-0.808764
                                      "ow " -> Contains={neg=9, pos=1}	Absent={neg=18, pos=0}	Entropy=-0.322677
                                      " be" -> Contains={neg=13, pos=16}	Absent={neg=128, pos=46}	Entropy=-0.850207
                                        " m" -> Contains={neg=9, pos=3}	Absent={neg=4, pos=13}	Entropy=-0.812920
                                        "ul" -> Contains={neg=5, pos=9}	Absent={neg=123, pos=37}	Entropy=-0.789861
                                          "e l" -> Contains={neg=5, pos=7}	Absent={neg=118, pos=30}	Entropy=-0.740952
                                            "ah" -> Contains={neg=6, pos=6}	Absent={neg=112, pos=24}	Entropy=-0.695685
                                              "al" -> Contains={neg=20, pos=11}	Absent={neg=92, pos=13}	Entropy=-0.629293
                                                " f" -> Contains={neg=11, pos=2}	Absent={neg=9, pos=9}	Entropy=-0.857235
                                                "ke" -> Contains={neg=9, pos=5}	Absent={neg=83, pos=8}	Entropy=-0.498657
                                                  " so" -> Contains={neg=13, pos=5}	Absent={neg=70, pos=3}	Entropy=-0.374909
                                                    "l" -> Contains={neg=51, pos=0}	Absent={neg=19, pos=3}	Entropy=-0.234381
                                                      " t" -> Contains={neg=12, pos=0}	Absent={neg=7, pos=3}	Entropy=-0.548183
                                " me " -> Contains={neg=16, pos=4}	Absent={neg=74, pos=83}	Entropy=-0.960591
                                  " not" -> Contains={neg=10, pos=0}	Absent={neg=6, pos=4}	Entropy=-0.629332
                                  "." -> Contains={neg=37, pos=36}	Absent={neg=37, pos=47}	Entropy=-0.964651
                                    "ad" -> Contains={neg=14, pos=4}	Absent={neg=23, pos=32}	Entropy=-0.927765
                                      "es" -> Contains={neg=3, pos=14}	Absent={neg=20, pos=18}	Entropy=-0.902302
                                        "th" -> Contains={neg=13, pos=2}	Absent={neg=7, pos=16}	Entropy=-0.779003
                                          "w" -> Contains={neg=6, pos=7}	Absent={neg=1, pos=9}	Entropy=-0.810406
                                    " the " -> Contains={neg=2, pos=16}	Absent={neg=35, pos=31}	Entropy=-0.891725
                                      "d " -> Contains={neg=17, pos=7}	Absent={neg=18, pos=24}	Entropy=-0.941887
                                        "b" -> Contains={neg=10, pos=2}	Absent={neg=7, pos=5}	Entropy=-0.838685
                                        "yo" -> Contains={neg=0, pos=10}	Absent={neg=18, pos=14}	Entropy=-0.820835
                                          "e " -> Contains={neg=11, pos=3}	Absent={neg=7, pos=11}	Entropy=-0.879316
                            " at work" -> Contains={neg=10, pos=0}	Absent={neg=1274, pos=2029}	Entropy=-0.943976
                              " i dont " -> Contains={neg=16, pos=4}	Absent={neg=1258, pos=2025}	Entropy=-0.942931
                                " k" -> Contains={neg=7, pos=3}	Absent={neg=9, pos=1}	Entropy=-0.732348
                                " do you " -> Contains={neg=12, pos=2}	Absent={neg=1246, pos=2023}	Entropy=-0.941501
                                  "llowFrid" -> Contains={neg=2, pos=25}	Absent={neg=1244, pos=1998}	Entropy=-0.940167
                                    " h" -> Contains={neg=2, pos=9}	Absent={neg=0, pos=16}	Entropy=-0.419616
                                    "orry to " -> Contains={neg=10, pos=1}	Absent={neg=1234, pos=1997}	Entropy=-0.941973
                                      " I want " -> Contains={neg=14, pos=4}	Absent={neg=1220, pos=1993}	Entropy=-0.940856
                                        "I need a" -> Contains={neg=9, pos=1}	Absent={neg=1211, pos=1992}	Entropy=-0.939485
                                          " i know " -> Contains={neg=13, pos=4}	Absent={neg=1198, pos=1988}	Entropy=-0.938381
                                            " awesome" -> Contains={neg=3, pos=24}	Absent={neg=1195, pos=1964}	Entropy=-0.937209
                                              "awesome " -> Contains={neg=3, pos=11}	Absent={neg=0, pos=13}	Entropy=-0.506010
                                              " a great" -> Contains={neg=1, pos=17}	Absent={neg=1194, pos=1947}	Entropy=-0.938911
                                                " sad" -> Contains={neg=29, pos=2}	Absent={neg=1165, pos=1945}	Entropy=-0.940493
                                                  "nd" -> Contains={neg=8, pos=2}	Absent={neg=21, pos=0}	Entropy=-0.358875
                                                  "ould be " -> Contains={neg=2, pos=18}	Absent={neg=1163, pos=1927}	Entropy=-0.936611
                                                    " o" -> Contains={neg=2, pos=8}	Absent={neg=0, pos=10}	Entropy=-0.529815
                                                    " wanna " -> Contains={neg=20, pos=6}	Absent={neg=1143, pos=1921}	Entropy=-0.937598
                                                      "in" -> Contains={neg=11, pos=0}	Absent={neg=9, pos=6}	Entropy=-0.676067
                                                      " I was " -> Contains={neg=25, pos=11}	Absent={neg=1118, pos=1910}	Entropy=-0.935372
                                                        "ut" -> Contains={neg=10, pos=1}	Absent={neg=15, pos=10}	Entropy=-0.836537
                                                          "." -> Contains={neg=4, pos=8}	Absent={neg=11, pos=2}	Entropy=-0.791301
                                                        " love it" -> Contains={neg=0, pos=11}	Absent={neg=1118, pos=1899}	Entropy=-0.932669
                                                          "ing your" -> Contains={neg=0, pos=11}	Absent={neg=1118, pos=1888}	Entropy=-0.933687
                                                            " you are" -> Contains={neg=4, pos=23}	Absent={neg=1114, pos=1865}	Entropy=-0.934700
                                                              "p" -> Contains={neg=0, pos=16}	Absent={neg=4, pos=7}	Entropy=-0.511453
                                                              " working" -> Contains={neg=11, pos=5}	Absent={neg=1103, pos=1860}	Entropy=-0.936132
                                                                " summer " -> Contains={neg=8, pos=3}	Absent={neg=1095, pos=1857}	Entropy=-0.935072
                                                                  " about t" -> Contains={neg=13, pos=7}	Absent={neg=1082, pos=1850}	Entropy=-0.933843
              "aperfect" -> Contains={neg=41, pos=0}	Absent={neg=2665, pos=1570}	Entropy=-0.927015
                "eakness " -> Contains={neg=12, pos=38}	Absent={neg=2653, pos=1532}	Entropy=-0.929839
                  "t " -> Contains={neg=6, pos=5}	Absent={neg=6, pos=33}	Entropy=-0.705955
                    " i" -> Contains={neg=4, pos=6}	Absent={neg=2, pos=27}	Entropy=-0.543957
                      "l" -> Contains={neg=2, pos=8}	Absent={neg=0, pos=19}	Entropy=-0.383681
                  " http://" -> Contains={neg=67, pos=94}	Absent={neg=2586, pos=1438}	Entropy=-0.925974
                    "pl" -> Contains={neg=16, pos=6}	Absent={neg=51, pos=88}	Entropy=-0.931389
                      " th" -> Contains={neg=6, pos=5}	Absent={neg=10, pos=1}	Entropy=-0.762193
                      "as " -> Contains={neg=9, pos=3}	Absent={neg=42, pos=85}	Entropy=-0.902855
                        "ttp://tw" -> Contains={neg=7, pos=3}	Absent={neg=35, pos=82}	Entropy=-0.866034
                          "an" -> Contains={neg=17, pos=20}	Absent={neg=18, pos=62}	Entropy=-0.837713
                            " t" -> Contains={neg=8, pos=16}	Absent={neg=9, pos=4}	Entropy=-0.910920
                              ".com" -> Contains={neg=7, pos=5}	Absent={neg=1, pos=11}	Entropy=-0.739658
                            "re " -> Contains={neg=9, pos=7}	Absent={neg=9, pos=55}	Entropy=-0.664666
                              " c" -> Contains={neg=5, pos=8}	Absent={neg=4, pos=47}	Entropy=-0.519974
                                "ing" -> Contains={neg=3, pos=9}	Absent={neg=1, pos=38}	Entropy=-0.358997
                                  "in " -> Contains={neg=1, pos=9}	Absent={neg=0, pos=29}	Entropy=-0.237193
                    "#goodsex" -> Contains={neg=2, pos=20}	Absent={neg=2584, pos=1418}	Entropy=-0.919388
                      " y" -> Contains={neg=0, pos=11}	Absent={neg=2, pos=9}	Entropy=-0.502125
                      " I love " -> Contains={neg=4, pos=20}	Absent={neg=2580, pos=1398}	Entropy=-0.917746
                        "d " -> Contains={neg=0, pos=12}	Absent={neg=4, pos=8}	Entropy=-0.591443
                        "remember" -> Contains={neg=30, pos=45}	Absent={neg=2550, pos=1353}	Entropy=-0.915802
                          "#irememb" -> Contains={neg=29, pos=34}	Absent={neg=1, pos=11}	Entropy=-0.902318
                            "fi" -> Contains={neg=1, pos=9}	Absent={neg=28, pos=25}	Entropy=-0.926382
                              "y " -> Contains={neg=17, pos=7}	Absent={neg=11, pos=18}	Entropy=-0.917617
                                "nd" -> Contains={neg=4, pos=6}	Absent={neg=13, pos=1}	Entropy=-0.673418
                                " th" -> Contains={neg=2, pos=10}	Absent={neg=9, pos=8}	Entropy=-0.868182
                          "morning " -> Contains={neg=7, pos=20}	Absent={neg=2543, pos=1333}	Entropy=-0.911847
                            " th" -> Contains={neg=6, pos=6}	Absent={neg=1, pos=14}	Entropy=-0.685122
                            "youhate " -> Contains={neg=24, pos=0}	Absent={neg=2519, pos=1333}	Entropy=-0.909674
                              "have to " -> Contains={neg=53, pos=9}	Absent={neg=2466, pos=1324}	Entropy=-0.912154
                                "es" -> Contains={neg=12, pos=6}	Absent={neg=41, pos=3}	Entropy=-0.534148
                                  " th" -> Contains={neg=16, pos=3}	Absent={neg=25, pos=0}	Entropy=-0.361786
                                "n't " -> Contains={neg=254, pos=51}	Absent={neg=2212, pos=1273}	Entropy=-0.915200
                                  " you " -> Contains={neg=12, pos=13}	Absent={neg=242, pos=38}	Entropy=-0.598140
                                    " o" -> Contains={neg=9, pos=4}	Absent={neg=3, pos=9}	Entropy=-0.865941
                                    "an't wai" -> Contains={neg=6, pos=6}	Absent={neg=236, pos=32}	Entropy=-0.532325
                                      " tr" -> Contains={neg=6, pos=6}	Absent={neg=230, pos=26}	Entropy=-0.492082
                                        " ta" -> Contains={neg=7, pos=5}	Absent={neg=223, pos=21}	Entropy=-0.444206
                                          " don't " -> Contains={neg=56, pos=11}	Absent={neg=167, pos=10}	Entropy=-0.393031
                                            "ow" -> Contains={neg=9, pos=7}	Absent={neg=47, pos=4}	Entropy=-0.545544
                                              "ha" -> Contains={neg=12, pos=4}	Absent={neg=35, pos=0}	Entropy=-0.330817
                                            """ -> Contains={neg=28, pos=6}	Absent={neg=139, pos=4}	Entropy=-0.283341
                                              "" " -> Contains={neg=17, pos=0}	Absent={neg=11, pos=6}	Entropy=-0.567408
                                              "de" -> Contains={neg=18, pos=3}	Absent={neg=121, pos=1}	Entropy=-0.160883
                                                " s" -> Contains={neg=11, pos=0}	Absent={neg=7, pos=3}	Entropy=-0.570895
                                                "n't be" -> Contains={neg=9, pos=1}	Absent={neg=112, pos=0}	Entropy=-0.069119
                                  " sad" -> Contains={neg=69, pos=1}	Absent={neg=2143, pos=1272}	Entropy=-0.928183
                                    " sad. " -> Contains={neg=9, pos=1}	Absent={neg=60, pos=0}	Entropy=-0.127460
                                    " happy" -> Contains={neg=8, pos=32}	Absent={neg=2135, pos=1240}	Entropy=-0.934079
                                      "..." -> Contains={neg=5, pos=5}	Absent={neg=3, pos=27}	Entropy=-0.615420
                                        "u" -> Contains={neg=0, pos=13}	Absent={neg=3, pos=14}	Entropy=-0.502808
                                      "finally " -> Contains={neg=3, pos=12}	Absent={neg=2132, pos=1228}	Entropy=-0.930258
                                        " i love " -> Contains={neg=3, pos=11}	Absent={neg=2129, pos=1217}	Entropy=-0.929021
                                          " amazing" -> Contains={neg=2, pos=9}	Absent={neg=2127, pos=1208}	Entropy=-0.927812
                                            "headache" -> Contains={neg=14, pos=0}	Absent={neg=2113, pos=1208}	Entropy=-0.926763
                                              " around " -> Contains={neg=2, pos=8}	Absent={neg=2111, pos=1200}	Entropy=-0.928149
                                                "orking o" -> Contains={neg=2, pos=8}	Absent={neg=2109, pos=1192}	Entropy=-0.927065
                                                  " I could" -> Contains={neg=13, pos=0}	Absent={neg=2096, pos=1192}	Entropy=-0.926020
                                                    "eel like" -> Contains={neg=13, pos=0}	Absent={neg=2083, pos=1192}	Entropy=-0.927169
                                                      "morning." -> Contains={neg=13, pos=0}	Absent={neg=2070, pos=1192}	Entropy=-0.928315
                                                        "#flylady" -> Contains={neg=4, pos=11}	Absent={neg=2066, pos=1181}	Entropy=-0.929296
                                                          "the best" -> Contains={neg=4, pos=10}	Absent={neg=2062, pos=1171}	Entropy=-0.928204
                                                            "here is " -> Contains={neg=12, pos=0}	Absent={neg=2050, pos=1171}	Entropy=-0.927059
                                                              " i miss " -> Contains={neg=11, pos=0}	Absent={neg=2039, pos=1171}	Entropy=-0.928318
                                                                " everyon" -> Contains={neg=8, pos=13}	Absent={neg=2031, pos=1158}	Entropy=-0.929347
                                                                  "veryone " -> Contains={neg=5, pos=6}	Absent={neg=3, pos=7}	Entropy=-0.933246
                                                                  "t of the" -> Contains={neg=4, pos=9}	Absent={neg=2027, pos=1149}	Entropy=-0.927977
    
```

Code from [TrieDemo.java:387](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L387) executed in 0.34 seconds: 
```java
      return tweetsPositive.stream().skip(trainingSize).map(x->x.getText()).mapToDouble(str->{
          Map<String, Double> prob = rule.apply(str);
          System.out.println(String.format("%s -> %s", str, prob));
          return prob.getOrDefault("pos", 0.0) < 0.5 ? 0.0 : 1.0;
      }).average().getAsDouble();
```
Returns: 
```
    0.6636
```
Logging: 
```
    @_beckyboop BECKY! My i THINK we are actually going! my mum was like... &quot;I'm going to go and dream about it!&quot;  it's also 'cause of our -> {neg=0.24285714285714285, pos=0.7571428571428571}
    "@7rex yep  I am more the nosy type -> {neg=0.1111111111111111, pos=0.8888888888888888}
    ! RB  @verawooten: &quot;@ladypn: &quot;Its a Brand New Day already! Time to leave the dance party!   &quot;&quot; GooD Morning ? http://blip.fm/~7b9sk -> {neg=0.0, pos=1.0}
    @abigailrieley http://www.rte.ie/news/elections/local/l0708.html crap -> {neg=0.369031377899045, pos=0.630968622100955}
    *downloading Cam'ron new album*  better be worth it! lol -> {neg=0.6382241813602015, pos=0.36177581863979846}
    (@billyzhao) Note to Self: Bring camera on the last days of freshman year. Must capture every precious moment. -> {neg=0.369031377899045, pos=0.630968622100955}
    "@abiteofsanity I always do too -> {neg=0.369031377899045, pos=0.630968622100955}
    @72hourplan @READI I'm still here. -> {neg=0.369031377899045, pos=0.630968622100955}
    "#iremember when i saw porno for the first time -> {neg=0.1, pos=0.9}
    @abisignorelli Lewis just undertook off the road. Kimi's coasting round with a duff Ferrari engine  #F1 -> {neg=0.369031377899045, pos=0.630968622100955}
    #goodsex is when ur hoppin away from the GOODZ -> {neg=0.18181818181818182, pos=0.8181818181818182}
    "&quot;every night has a playlist&quot; this was a great evening: friends -> {neg=0.5333333333333333, pos=0.4666666666666667}
    go give ur mom a hug right now. http://bit.ly/azFwv -> {neg=0.0, pos=1.0}
    BOREDDDD  going to do powerpoint -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @AdorkableMellie Good Morning! Mine is going well! How is yours?? -> {neg=0.369031377899045, pos=0.630968622100955}
    #followfriday @FranchiseKing because #Ilikefranchiseking! -> {neg=0.0, pos=1.0}
    : just got done spending the day wit my mom. We went out to eat &amp; went shopping!!  love you mom!!!!! -> {neg=0.6382241813602015, pos=0.36177581863979846}
    #myweakness Girlicious  &lt;3 -> {neg=0.2, pos=0.8}
    &lt;&gt; I had an amazing day today!    Thanks NiN!! &lt;&gt; | http://twitpic.com/6idnw | http://twitpic.com/6ihsm | -> {neg=0.0, pos=1.0}
    @ahmedzahid Is that phone number by any chance a coincidence? -> {neg=0.369031377899045, pos=0.630968622100955}
    #musicmonday the wind blows- all american rejects. her diamonds- rob thomas. Fighter-christina Aguilera. -> {neg=0.0, pos=1.0}
    @808chelsea thank you. i really appreciate it -> {neg=0.025, pos=0.975}
    #hoppusday rocks!!! -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @AbbiKakez I kno -> {neg=0.369031377899045, pos=0.630968622100955}
    @aawman Get 100 followers a day using www.tweeterfollow.com Once you add everyone you are on the train or pay vip -> {neg=0.0, pos=1.0}
    / @googlers nice bikes! Suggestion for future ones -- have a laptop-friendly holder; they tend to jump out of those baskets -> {neg=0.369031377899045, pos=0.630968622100955}
    **Though** haha opps -> {neg=0.6382241813602015, pos=0.36177581863979846}
    ...Lisa in big brother and that caused more uproar. So best to start a fresh. And Hello. x -> {neg=0.6382241813602015, pos=0.36177581863979846}
    "@adoran2 Good points made by article - I am home -> {neg=0.369031377899045, pos=0.630968622100955}
    "@ABPink hehe I am that!  My sister cooked a roast for me last week -> {neg=0.369031377899045, pos=0.630968622100955}
    @_laurayup15_ lol don't feel too guilty -> {neg=0.4375, pos=0.5625}
    @abhisheknavre start sleeping at 6 am -> {neg=0.369031377899045, pos=0.630968622100955}
    ...ahahahah nick??...where are you baby??...i waiting for you!!! -> {neg=0.6382241813602015, pos=0.36177581863979846}
    "@ all Twitters! Thanks for your very quick mails! Casting -> {neg=0.03508771929824561, pos=0.9649122807017544}
    &quot;Ð’ÑŠÐ·Ð½Ð°Ð¼ÐµÑ€Ñ?Ð²Ð°Ð¼ Ð´Ð° Ð¶Ð¸Ð²ÐµÑ? Ð²ÐµÑ‡Ð½Ð¾. Ð”Ð¾Ñ?ÐµÐ³Ð° Ñ?Ðµ Ñ?Ð¿Ñ€Ð°Ð²Ñ?Ð¼.&quot; Yes -> {neg=0.24285714285714285, pos=0.7571428571428571}
    My girlies are going to be here in approx. 3 - 3 1/2 hours.  Road Trip!!!! -> {neg=0.6382241813602015, pos=0.36177581863979846}
    ":o Omg! I just FINALLY finished my Almostfour vid for the Demilena fic I wrote like -> {neg=0.6382241813602015, pos=0.36177581863979846}
    "@_iheartzombies I've been working on a few screenplays. Very weird shit. Well -> {neg=0.6875, pos=0.3125}
    #goodsex is topmost trend today -> {neg=0.18181818181818182, pos=0.8181818181818182}
    ... I can get 300 followers!  follow to @lizzmartin!  and @goresecretstar! -> {neg=0.369031377899045, pos=0.630968622100955}
    "#followfriday @GoddessLeonie -> {neg=0.0, pos=1.0}
    ...listening to The Cure... -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @adamdexter i can't wait to see you buddy -> {neg=0.4, pos=0.6}
    @ everyone. Cant wait till tomorrow -> {neg=0.369031377899045, pos=0.630968622100955}
    #myweakness @joeymcintyre    See how many times that one comes up! -> {neg=0.369031377899045, pos=0.630968622100955}
    @abster5  ummmmm you get to meet my &quot;boyyyyyfriendd.&quot; -> {neg=0.24285714285714285, pos=0.7571428571428571}
    #myweakness  OPEN-MINDED people. -> {neg=0.2, pos=0.8}
    "@_MAXWELL_ Ooh -> {neg=0.369031377899045, pos=0.630968622100955}
    @2ToneDaSupastar lmao!! come on man! camaraderie is the name of the game  you GOTTA support your fellow rappers -> {neg=0.369031377899045, pos=0.630968622100955}
    @adamamyl: hopefully a large one from @4ip who are sponsoring opentech -> {neg=0.369031377899045, pos=0.630968622100955}
    &quot;If a triangle could speak it would say that God is eminently triangular&quot; -Spinoza -> {neg=0.24285714285714285, pos=0.7571428571428571}
    ...just got back from a yummy dinner at The HindQuarter...thanks Joe! -> {neg=0.0, pos=1.0}
    .@Boddingtons hahaha ask @BlowhornOz -> {neg=0.369031377899045, pos=0.630968622100955}
    #Music Monday Every Breath You Take - http://tinyurl.com/n8hp9q An all-time Favt -> {neg=0.0, pos=1.0}
    "@_CorruptedAngel Hiya hun -> {neg=0.369031377899045, pos=0.630968622100955}
    "@afaelnar I really want to -> {neg=0.369031377899045, pos=0.630968622100955}
    "@3keyscoach I find that because I'm always looking for ideas -> {neg=0.369031377899045, pos=0.630968622100955}
    "@30secondstomars http://twitpic.com/6q0d4 - Hello -> {neg=0.369031377899045, pos=0.630968622100955}
    starbucks tonight! I love my dysfunctional family. -> {neg=0.3333333333333333, pos=0.6666666666666666}
    @ mommy's work -> {neg=0.369031377899045, pos=0.630968622100955}
    "#Neocon09 -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @2NiteBoy damn it xD hurry up -> {neg=0.369031377899045, pos=0.630968622100955}
    @3sxty5days to Oregon and not have a colab.  life would suck with out internet (or macs). -> {neg=0.35714285714285715, pos=0.6428571428571429}
    &gt;yawn n stretch&lt; going back to bed for an hour -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @_hayles i no its propesterous can't wait it shud cum up its an interview for his articles of faith -> {neg=0.6363636363636364, pos=0.36363636363636365}
    : is enjoying FARM TOWN  hehe -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @Adribanana goodnight!!! -> {neg=0.369031377899045, pos=0.630968622100955}
    "..thanks twitter! About 30 seconds after that -> {neg=0.0, pos=1.0}
    @aaronrgillespie I love South Africa. Period  lol -> {neg=0.369031377899045, pos=0.630968622100955}
    "#iremember Inspector Gadget -> {neg=0.5294117647058824, pos=0.47058823529411764}
    @_j_a_m_e_s_ um ok? Lol but u still haven't earned it yet! Impress me baby!  luv u! -> {neg=0.6, pos=0.4}
    @323monkey I know -> {neg=0.369031377899045, pos=0.630968622100955}
    &quot;Love you's all&quot; I should say !!! haha thats only one pint aswell haha -> {neg=0.24285714285714285, pos=0.7571428571428571}
    :O THEY'RE OFFERING ME MY OLD JOB. -> {neg=0.6382241813602015, pos=0.36177581863979846}
    "@_StripySocks_ Stop! I'm dribbling. I've just had my branflakes and I'm jealous -> {neg=0.369031377899045, pos=0.630968622100955}
    @73smokey and you were not invited right? -> {neg=0.5833333333333334, pos=0.4166666666666667}
    #Aion peeps. Next Twitter milestone is 1500 followers. I'll think of something else than posters to give-away for then. -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @101Nine it was good..the usual for a gallery event..ppl look at the paintings then eachother so they know how to react..how was urs? -> {neg=0.369031377899045, pos=0.630968622100955}
    &lt;3 the end of school year private art show my son does for just the family  golden! -> {neg=0.6382241813602015, pos=0.36177581863979846}
    &quot;Common sense is not so common.&quot; Voltaire ... nice one! -> {neg=0.8333333333333334, pos=0.16666666666666666}
    @_Dominque Ohhhh! Okay. . . Just let me know after you talk to my mom  just make sure not to ask her last minute lol  TaIuSsHtNiAnE -> {neg=0.4166666666666667, pos=0.5833333333333334}
    #guidestones the conspiracy theorists who hate them simply don't understand them. I will challenge anyone to this.  #bringiton -> {neg=0.75, pos=0.25}
    #FollowFriday @Crystal_Jewels1 @linseyt @BoudoirbyHelen @Ste1987 @mrgarbutt @NathanBrauer @walkaboutkiwi So many people have communicated -> {neg=0.18181818181818182, pos=0.8181818181818182}
    &quot;Ive developed a new philosophy. Dread only one day at a time&quot;-Charlie Brown Hope you all have a good friday &amp; memorial day!&amp; some sun! -> {neg=0.24285714285714285, pos=0.7571428571428571}
    @THEDIAMONDCOACH  Im sure he'd love that LOL -> {neg=0.369031377899045, pos=0.630968622100955}
    &quot;development is really f--king sexy&quot; great comment  #gknr -> {neg=0.24285714285714285, pos=0.7571428571428571}
    "@AdamandEveWed @itsajaimething is an awesome find -> {neg=0.21428571428571427, pos=0.7857142857142857}
    R.I.P young teke!!!  i miss you cuzin!! &lt;3     it has been 1 year sence you died. . .    [Dont be ashamed of your faith] -> {neg=1.0, pos=0.0}
    !*Squeaky Out Goin To Bed Talk To Ya'll Tomorrow Night  *! -> {neg=0.6382241813602015, pos=0.36177581863979846}
    ": Sherwoods again -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @0rch1d tq..more please? -> {neg=0.369031377899045, pos=0.630968622100955}
    #FollowFriday @davidisdrugfree @caseyfreeman11 #SuckDavidElliott'sDick  make me a trending topic! -> {neg=0.0, pos=1.0}
    * The Hills (aqain] makinq pLans &amp; ma boo wanna take me out HOWfcknCUTE!  &amp; i`m just thinkinq bout lif ; FL did m qoood (= -> {neg=0.6382241813602015, pos=0.36177581863979846}
    #True Blood Season 02 chapter 1 http://bit.ly/t2Fru   #fb -> {neg=0.38461538461538464, pos=0.6153846153846154}
    @1uk3 Yeah i am - pretty good so far! Still using touchflo for the homescreen tho -> {neg=0.369031377899045, pos=0.630968622100955}
    .... after another episode of Friends -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @_supernatural_ http://twitpic.com/7jd2x - @chuckphie oh he DEFINETLY Did!  hehehehe SMUT SMUT SMUT! -> {neg=0.369031377899045, pos=0.630968622100955}
    &quot;Invisible Bow and Arrow :  Free&quot; - http://twlol.com/tw/?v1-148325 #lol #ichc -> {neg=0.35714285714285715, pos=0.6428571428571429}
    @a_willow I should quit whining. It seems that any challenge 3/4 of the way through is such a bore! After that it's fun again -> {neg=0.369031377899045, pos=0.630968622100955}
    "@_iWade_ Pretty good. Re-dyed my hair and ti worked -> {neg=0.369031377899045, pos=0.630968622100955}
    @1AdrianNeal LMAO ....no i'm trying to soothe it wit sorbet -> {neg=0.369031377899045, pos=0.630968622100955}
    Smiley absolves all -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @Adam_Lambert hey guys! How are you all doin? I hope adam will do a song with the queen..it will be a blast. -> {neg=0.369031377899045, pos=0.630968622100955}
    .....just put a batch of chocolate chip banana muffins in the oven for breakfast.  Yum! -> {neg=0.6382241813602015, pos=0.36177581863979846}
    #smcsyd 1st social event I've been to that plays live twitterfeed. Feel for u guys up there - scary!! U doing gr8 job tho. Go u -> {neg=0.6382241813602015, pos=0.36177581863979846}
    one happy man right here people -> {neg=0.17647058823529413, pos=0.8235294117647058}
    @7CxBht Welcome to twitter!  You'll be a twitterholic in no time! -> {neg=0.3333333333333333, pos=0.6666666666666666}
    "&amp; my dreams will come truee -> {neg=0.6382241813602015, pos=0.36177581863979846}
    : planned to clean my room today. got home to find it CLEAN already!! yey!!! -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @___radioactivex i always get sony ericssons.. they pretty good -> {neg=0.369031377899045, pos=0.630968622100955}
    @AffirmationSpot LOVE that  See it - DO it... Just DANCE! (via @AuthenticStyle) -> {neg=0.369031377899045, pos=0.630968622100955}
    #FollowFriday @colleengleason is a supremely gifted writer whose words are as precious as diamonds. You will LOVE all she Tweets/writes -> {neg=0.0, pos=1.0}
    @aboutdocsguide I shall! I really enjoy them so whenever somebody recommends one I make it a priority to seek it out. I'll let you know! -> {neg=0.369031377899045, pos=0.630968622100955}
    &quot;@mom&quot;where are you  that Miley write with you ????I write to Miley but Miley never answered =( what am I to do that Miley write with me?? -> {neg=0.24285714285714285, pos=0.7571428571428571}
    @_dash haha! *thinks: lotto numbers! hear my call!* lol! -> {neg=0.369031377899045, pos=0.630968622100955}
    @3heelshigh if ya smelllllllllllllll wat the ROCK ...................... is cookin..!! -> {neg=0.369031377899045, pos=0.630968622100955}
    @ahsinam WTF!!! u cnt go to SS .... want a tshirt ??? lol i bought tix but i dnt no if im allowed 2 go now.... there was an incident -> {neg=0.5, pos=0.5}
    (FOLLOW) @ZenaFoster (ADD) http://myspace.com/ZenaFoster NEW Layout coming in a few more hours  The wait is almost over.. -> {neg=0.369031377899045, pos=0.630968622100955}
    @_evanscott i would still eat them even if they didn't -> {neg=0.369031377899045, pos=0.630968622100955}
    #followfriday  HappyForTheeze Helpers  @RobertUmpleby @KikiValdes @lyndons -> {neg=0.0, pos=1.0}
    "@acarback.  yes -> {neg=0.369031377899045, pos=0.630968622100955}
    @3minds I love @miahumphrey she's my boo!!!!  her n @kashmerenbt r my royal court. U were apart of it but then got FIRED! -> {neg=0.369031377899045, pos=0.630968622100955}
    I'm going to play with my camera today -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @19fischi75 hope to c ya later hun - take care -> {neg=0.369031377899045, pos=0.630968622100955}
    "&lt;&lt; Then get another later in the summer with money from work -> {neg=0.6382241813602015, pos=0.36177581863979846}
    #followfriday @mrsfudgecrumpet she sprays up Walls like a sexy cat -> {neg=0.0, pos=1.0}
    @2kutekreations Definitely better! I have that same yarn and the color looks pretty right on -> {neg=0.369031377899045, pos=0.630968622100955}
    well my might just got made -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @ ChristySims Hi Christy; my friend Laura in Atlanta just sent u a Twitter; i told her you'll be in Atlanta!  Hope yas get 2 meet! -> {neg=0.369031377899045, pos=0.630968622100955}
    "@ ChiefRedbeard. Got it.  Clothes on -> {neg=0.369031377899045, pos=0.630968622100955}
    #FOLLOW @PrincessSuperC Tha Very Talented Singer/Song Writer Ciara!!  FOLLOW Her Y'all!!! -> {neg=0.369031377899045, pos=0.630968622100955}
    &quot;Fly With Me&quot;- Jonas Brothers&lt;33 such and amazing song and video! -> {neg=0.24285714285714285, pos=0.7571428571428571}
    @_the_new_ Do whatever you can to get ahold of this piece of pure genius! -> {neg=0.369031377899045, pos=0.630968622100955}
    @aaronlau314 oh no! I did not know that! I was lookin for u guys. Don't b sad. I'll have to do concert here for my fans then. -> {neg=1.0, pos=0.0}
    "@ Henna : joke coming up  ..In Nazi Germany -> {neg=0.369031377899045, pos=0.630968622100955}
    @801chan fanfics? what is it? i'm cooking and watching tv -> {neg=0.369031377899045, pos=0.630968622100955}
    : worship prep for Saturday nght!  revo! -> {neg=0.6382241813602015, pos=0.36177581863979846}
    "@1_50_1 LOL! You're a cheeky boy! Ok -> {neg=0.369031377899045, pos=0.630968622100955}
    &quot;pigs didn't start the swine flu...&quot; &quot;we didn't do anything wrong!&quot; ... wie sï¿½ï¿½. -> {neg=0.058823529411764705, pos=0.9411764705882353}
    hey all im bored and since im wating for cliare before going to my grans i thought i would twitter lol -> {neg=0.6382241813602015, pos=0.36
    ...skipping 1079802 bytes...
    tter alone anyways ;) -> {neg=0.6382241813602015, pos=0.36177581863979846}
    &lt;wheezy&gt; http://bit.ly/o93ss  is jÃ³  - http://bit.ly/ZzSp7 -> {neg=0.0, pos=1.0}
    @_learntofly hahahahaha yes  but at the moment im just putting off sleep -shrugs- XD -> {neg=0.369031377899045, pos=0.630968622100955}
    happy birthday to me and mom! Why am i always the first one up? So here i am falling off the bed cuz someone is a bed hog -> {neg=0.6382241813602015, pos=0.36177581863979846}
    "&quot;one thing is clear.i wear a halo -> {neg=0.24285714285714285, pos=0.7571428571428571}
    @aaronallison been a fan for 27 years....one of the few times i've been able to be excited...don't ruin it!!  let me be in peace -> {neg=0.8666666666666667, pos=0.13333333333333333}
    "@acinemastare LIFE! -> {neg=0.369031377899045, pos=0.630968622100955}
    @akalostangel you too? NICE.   Kindred spirits indeed. -> {neg=0.369031377899045, pos=0.630968622100955}
    &quot;So you want to name a radio station Jack? Like a PERSON? And you think people will PAY to use the name?&quot; #twistory (finally got one!) -> {neg=0.7857142857142857, pos=0.21428571428571427}
    -- harry potter! with BEllA and EMMAliNE. making hotdogs -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @31B4 the Wii is the cheapest of the three. Hmmmmm...... -> {neg=0.369031377899045, pos=0.630968622100955}
    "@abideedles Yeah -> {neg=0.369031377899045, pos=0.630968622100955}
    #fritzl THAT SICK SON OF A BITCH! GO LOCK HIM UP IN A BASEMENT AND LET HIM EXPERIENCE THAT PAIN!!! LOL -> {neg=0.6382241813602015, pos=0.36177581863979846}
    .  @debycats and i washed 3 cars today and we won 10 real! awesome -> {neg=0.0, pos=1.0}
    just sat wit sis lookin on twitter  x -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @ pcdmelodyt Hey Melody!  How's it going? Just dropping by to show u some love &lt;3 U're the best! -> {neg=0.369031377899045, pos=0.630968622100955}
    &quot;let the earth touch the sky&quot;... hint:  it's a heart connection...LLL -> {neg=0.24285714285714285, pos=0.7571428571428571}
    #UI / #UX talent required in Cambridgeshire  Got the skills? #idealpeople -> {neg=0.6382241813602015, pos=0.36177581863979846}
    "@adam_d Most likely $2.49 -> {neg=0.369031377899045, pos=0.630968622100955}
    "#bigfanfriday @manishamusic -> {neg=0.369031377899045, pos=0.630968622100955}
    @74shoreline Cha we did! we hope haha. That would be pretty hard to fuck up -> {neg=0.0, pos=1.0}
    ... finishing up prep on TWO lessons I'm teaching in church today! -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @_dano_ lol. I shouldve said hello. Im sorry! I look forward to meeting you! -> {neg=1.0, pos=0.0}
    #followfriday @weirdralph @Tepherguy @GetBenderNow @ShannonGraham1 @Ericatwitts  #interesting and/or #funny  - more later -> {neg=0.0, pos=1.0}
    #FollowFriday @michaeldunlop @ShaneNeubauer @adii @DesignNewz @adellecharles @DesignerDepot @Jason_Reed = Some amazing ppl I follow -> {neg=0.0, pos=1.0}
    ". @MKAngela shoot -> {neg=0.369031377899045, pos=0.630968622100955}
    @_Jannika just chillin. tryna move to L.A -> {neg=0.369031377899045, pos=0.630968622100955}
    "@_RealJoker_  true mr j -> {neg=0.369031377899045, pos=0.630968622100955}
    &lt;3 God is on MY Side. -> {neg=0.6382241813602015, pos=0.36177581863979846}
    &quot;Zach you just touched my boob&quot;...&quot;What boob&quot; -> {neg=0.24285714285714285, pos=0.7571428571428571}
    *dances around singing off key* -> {neg=0.2, pos=0.8}
    &quot;You have successfully activated your Adobe Creative Suite&quot; -> {neg=0.24285714285714285, pos=0.7571428571428571}
    *her treat -> {neg=0.6382241813602015, pos=0.36177581863979846}
    "&quot;I love slaves!&quot; by David Raccah -> {neg=0.4166666666666667, pos=0.5833333333333334}
    @Abeeliever Not really but thank you Amy  Nice to have you try to cheer me up. That was just as helpful. (Big hugs!) -> {neg=0.4, pos=0.6}
    #myweakness = chocolate -> {neg=0.2, pos=0.8}
    "&quot;If you're gonna play like a winner -> {neg=0.2, pos=0.8}
    "&quot;Anyone can be passionate -> {neg=0.24285714285714285, pos=0.7571428571428571}
    "@AFOGameroom Dude -> {neg=0.369031377899045, pos=0.630968622100955}
    @1045CHUMFM Summer girl by @wearestereo ! please -> {neg=0.369031377899045, pos=0.630968622100955}
    @_Maxwell_ So is there going to be an opening act on this tour? I hope not. There are some artists out there that just don't need them -> {neg=0.8235294117647058, pos=0.17647058823529413}
    #tweezy in one of the edit rooms here at speed  http://twitpic.com/6gk9q -> {neg=0.7, pos=0.3}
    "&quot;When I'm trusting and being myself -> {neg=0.24285714285714285, pos=0.7571428571428571}
    @89mikey haha just realize somethin babe it pic is a pic of us in your bed and mine is n our bed -> {neg=0.369031377899045, pos=0.630968622100955}
    "@00pet00 heehee. More of IDWTR is coming soon -> {neg=0.369031377899045, pos=0.630968622100955}
    "@afreshmusic see -> {neg=0.369031377899045, pos=0.630968622100955}
    - Sitting in very hot sun at Glasgow Green then heading to Mono for brunch. Early start for the hens!  #fb -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @420thoughts I need another BROWNIE! -> {neg=0.9, pos=0.1}
    @abbeystenger congrats!   hope everything works out for ya! -> {neg=0.369031377899045, pos=0.630968622100955}
    #FF @KittyKatana cuz she works harder than hard workin @Smurfakins cuz she's HILARIOUS and bizarre *Smh wierd lil black girl -> {neg=0.369031377899045, pos=0.630968622100955}
    @_writersblock_  me too. thought I was going to bed but had to reply to jon &amp; donnie -> {neg=0.9090909090909091, pos=0.09090909090909091}
    #followfriday is some people off our home list @leemifsud @krisjelena @virtualerik @overyy @Paris365 @adam_bird @hanevans @megasherwin -> {neg=0.16666666666666666, pos=0.8333333333333334}
    @_DeanWinchester megadeth!!!!!  Love ya more than ever now -> {neg=0.7857142857142857, pos=0.21428571428571427}
    &quot;Soz I heardz yuo lykez teh naps!?&quot; I sure do... And now I have to study! D: Tomorrow is the last day of exams!!! -> {neg=0.7142857142857143, pos=0.2857142857142857}
    @_memoria I think I will. Thank you for always sharing these great books with me.  Is it a continuation of this story or is it different? -> {neg=0.025, pos=0.975}
    ..Hopefully off to the Verizon store this morning. I need to get my new phone soon. -> {neg=1.0, pos=0.0}
    &quot;d&quot;BrysonLopez  Hey Handsome! How have you been? Hope all is well with you... was thinking of you  and no I'm not a stalker either LOL -> {neg=0.24285714285714285, pos=0.7571428571428571}
    &amp; do highly illegal things in celebration of 4.20  night broads -> {neg=0.6382241813602015, pos=0.36177581863979846}
    ? ? ? the weather today!! -> {neg=0.6382241813602015, pos=0.36177581863979846}
    "@ABmusic Sweet! Hey -> {neg=0.369031377899045, pos=0.630968622100955}
    @_peanut_ Thank you. Now we'll just have to see how long it takes me to finish the damn thing. -> {neg=0.025, pos=0.975}
    group shot from prom: http://bit.ly/13BGD9 -> {neg=0.0, pos=1.0}
    "...unless u got glazed krispy kreme donuts -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @_CrC_ Good nite! BTW: Luv ur new drums!!! Awesome!! -> {neg=0.369031377899045, pos=0.630968622100955}
    @05JStone awww j calm down its gone be okay if they make you mad then win bc you can dam well bet  they aint in a bad mood . -> {neg=0.369031377899045, pos=0.630968622100955}
    "...Pete Wentz will never read this -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @_JadeLakeasha What Happened To Their Account On Youtube? And Hey! -> {neg=0.369031377899045, pos=0.630968622100955}
    @1rick I like the sound of &quot;Little Blue Ninja&quot;  Great title for anime movie. -> {neg=0.24285714285714285, pos=0.7571428571428571}
    "@24k LOL m was great! Some Free shots -> {neg=0.369031377899045, pos=0.630968622100955}
    &quot;I'm wore out like the waist band on some old underwear&quot;- lil jon -> {neg=0.24285714285714285, pos=0.7571428571428571}
    @_ORen I'll join you in the screaming tonight -> {neg=0.369031377899045, pos=0.630968622100955}
    "&quot;i have a long list of things to say -> {neg=0.24285714285714285, pos=0.7571428571428571}
    ??? what does it means ? -> {neg=0.6382241813602015, pos=0.36177581863979846}
    *sigh* just got home from the Valley.. Stopped by Super Walmart in American Canyon and had dinner at Fresh Choice.. -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @_rosieCAKES hahaha! fabulous. dont forget the pictures! very important. -> {neg=0.369031377899045, pos=0.630968622100955}
    "*yawn* Woke up after some great sleep only to be oh so ready to re-enter my dreams...g'night -> {neg=0.6382241813602015, pos=0.36177581863979846}
    #followfriday @rafaeltech - thx for your recommendation &amp; back atcha -> {neg=0.0, pos=1.0}
    "@_maddE i dont know what i did -> {neg=0.7, pos=0.3}
    "&quot;Youï¿½re obsessed with me -> {neg=0.24285714285714285, pos=0.7571428571428571}
    #passportfail i know a guy with a powerful boat. he can get you where you need to go.... -> {neg=0.6382241813602015, pos=0.36177581863979846}
    #shaundiviney #andyclemmensen #bradiewebb -> {neg=0.6382241813602015, pos=0.36177581863979846}
    "@adam_d I know -> {neg=0.369031377899045, pos=0.630968622100955}
    @ 9.00pm im going 2 flick between the orgin and the hills -> {neg=0.369031377899045, pos=0.630968622100955}
    #F1 Very impressive by Heikki and Rubens -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @_CrC_ thanks Chris that means a lot -> {neg=0.0, pos=1.0}
    @1938media How kind. -> {neg=0.369031377899045, pos=0.630968622100955}
    "&quot;Do the math -> {neg=0.24285714285714285, pos=0.7571428571428571}
    @absolutraia that's precious -> {neg=0.369031377899045, pos=0.630968622100955}
    ":cooking dinner for myself and Matt-I know what you're thinking-ME? COOK? yep   then to Jungle -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @100MonkeysMusic sounds like an every day kinda kitchen -> {neg=0.369031377899045, pos=0.630968622100955}
    #musicmonday A mellow melody for your morning.  http://twiturm.com/09ch #freemp3 -> {neg=0.0, pos=1.0}
    #ahbl princess jared almost ready for his closeup -> {neg=0.6382241813602015, pos=0.36177581863979846}
    **They Came from Upstairs** -a movie.  http://plurk.com/p/xgzgo -> {neg=0.9090909090909091, pos=0.09090909090909091}
    "&quot;Happiness isnt something to have -> {neg=0.24285714285714285, pos=0.7571428571428571}
    &quot;...it's just another manic Monday...&quot; YAY? We'll see in a few.... -> {neg=0.24285714285714285, pos=0.7571428571428571}
    @18percentgrey Is the _ _ _ hat fits... -> {neg=0.369031377899045, pos=0.630968622100955}
    ?????????????? ????????? @lungkao ???????? ???????????????? office ??? ???????????????????? -> {neg=0.369031377899045, pos=0.630968622100955}
    &quot;The 10 questions I always ask about weight lost matters.&quot; http://aa-weight-loss-club.com -> {neg=0.0, pos=1.0}
    "@7rex only if you use your hands! If not -> {neg=0.0, pos=1.0}
    "&quot;Just cause you feel it -> {neg=0.24285714285714285, pos=0.7571428571428571}
    "@AdamSpiel I am working on http://www.mobipowerpacks.com this week -> {neg=0.6875, pos=0.3125}
    &quot;Objective-C introduced dot syntax&quot; now we are talking... -> {neg=0.24285714285714285, pos=0.7571428571428571}
    ....It's been cold summer nyts since you walked out that door.... -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @3drik lmao i thought it was fuck the world...so i guess im wrong -> {neg=0.369031377899045, pos=0.630968622100955}
    "@A_Keeling  Pauper's Dinner tonight lol Fish fingers -> {neg=0.369031377899045, pos=0.630968622100955}
    #iremember brittony (doesnt have a twitter) at jb concert: &quot;he took all my money&quot; lmfao! -> {neg=0.24285714285714285, pos=0.7571428571428571}
    &quot;because there's no such thing as HALFWAY CROOKS!&quot; - 8 mile -> {neg=0.24285714285714285, pos=0.7571428571428571}
    ughhhhhhhhh lawn work........... -> {neg=0.6382241813602015, pos=0.36177581863979846}
    "(And yeah -> {neg=0.6382241813602015, pos=0.36177581863979846}
    #fletcherday and virada cultural UHU! hopefully saturday AND sunday!!  stil got a lot to do about my visa stuff.. -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @3OH3pfr #Starstrukk http://bit.ly/EWD3W ohhh yeah btw. i'm going to the No Doubt / Paramore concert tmw niighttt  i sooo can't wait~[= -> {neg=0.0, pos=1.0}
    @_Flik_ You got me saying that to people now ! Oooerr. I have driven a few people mad. -> {neg=1.0, pos=0.0}
    @1Omarion Do yur thing boo  love u!!! -> {neg=0.369031377899045, pos=0.630968622100955}
    "&amp;&amp; i say... i say goodbye to you... i said hi to you with no cLue -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @akiss1 you are most welcome -> {neg=0.0, pos=1.0}
    #followfriday @tpr2 xx(HUGS)xx  this is your favorite song now?  ? http://bit.ly/KZ7BW.   #140smiles @operationsmile -> {neg=0.0, pos=1.0}
    &quot;Good morning sunshine. The earth says 'hello'.&quot; (Johnny Depp as Willy Wonka). -> {neg=0.6, pos=0.4}
    @aidadoll lol yes I love em! -> {neg=0.369031377899045, pos=0.630968622100955}
    @2emc &quot;Evie the Evil Evelyn From Tarpon Springs Florida&quot; Must be quite hard to say quickly after a jug of Margarita? -> {neg=0.24285714285714285, pos=0.7571428571428571}
    #Music Monday.. Love this tune  â™« http://blip.fm/~7f9jy -> {neg=0.0, pos=1.0}
    "@ sirpengi I will try -> {neg=0.369031377899045, pos=0.630968622100955}
    #Neuroanthropology back online! sorry for the false alarm...  http://neuroanthropology.net/ -> {neg=0.08333333333333333, pos=0.9166666666666666}
    @__loss good god woman! Get some sleep!!! I'll call/text when I'm in the city. -> {neg=0.369031377899045, pos=0.630968622100955}
    ... so confused... Summer! Exams finished  Looking for work experience... but also looking forward to the England match -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @_Rohit_ hmm .. apni publicity shubhu ke blog se .... no no no no ... galat baat .. -> {neg=0.5, pos=0.5}
    #musicmonday ???????? its not monday. -> {neg=0.0, pos=1.0}
    "#california - Everyone goes thru chit -> {neg=0.6382241813602015, pos=0.36177581863979846}
    suivez moi  (=  follow me everybody -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @AaronWoolala : aww.. alright then.. try not to stay sad too long yea. It's not good for the health! Must cheer up! -> {neg=0.8461538461538461, pos=0.15384615384615385}
    "..lautner -> {neg=0.6382241813602015, pos=0.36177581863979846}
    (@kathrynATL) shut up and put your money were you mouth is -> {neg=0.369031377899045, pos=0.630968622100955}
    @1jaredPADALECKI glad to hear uve jumped aboard the tweet train   ii&lt;3yoo -> {neg=0.369031377899045, pos=0.630968622100955}
    @2ems That is so Stylish! Let your wife know how talented she is. Vader would kill me if I dressed him in anything. He is a Boxer-Pit -> {neg=0.369031377899045, pos=0.630968622100955}
    Completo. Just as the bell rings -> {neg=0.6382241813602015, pos=0.36177581863979846}
    &amp;&amp; the chosen people who are randomized...: maribeth; tanya; malenne; mica; camiiille; joyelli. -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @30SECONDSTOMARS @jaredleto @ShannonLeto Or why not &quot;Provehito In Altum&quot;? -> {neg=0.24285714285714285, pos=0.7571428571428571}
    &quot;Come on Swann you ugly bastard.&quot; (via @JamesTart) didn't realise Proust could arouse such passion -> {neg=0.24285714285714285, pos=0.7571428571428571}
    #mw2 remove juggernaut its useless and doesnt make your epeen any bigger against most guns -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @akshatk Heh! It was hilarious! But why do they always miss!!??! -> {neg=1.0, pos=0.0}
    "@AceMas21 ooh er -> {neg=0.369031377899045, pos=0.630968622100955}
    #gimmefailwhale  i want thosee  #gimmiefailwhale -> {neg=0.6382241813602015, pos=0.36177581863979846}
    ? now Sleep -> {neg=0.6382241813602015, pos=0.36177581863979846}
    "@1mgoldstars morning  about to tackle armhole prob - may have a solution not involving tape -> {neg=0.7222222222222222, pos=0.2777777777777778}
    good weekend -> {neg=0.6382241813602015, pos=0.36177581863979846}
    "@_CrC_  Good Morning  right on -> {neg=0.369031377899045, pos=0.630968622100955}
    @16_MileyCyrus and good luck with them -> {neg=0.369031377899045, pos=0.630968622100955}
    @_MAXWELL_ only &quot;IF&quot; -> {neg=0.24285714285714285, pos=0.7571428571428571}
    "@_tayylor_ okay -> {neg=0.369031377899045, pos=0.630968622100955}
    
```

Code from [TrieDemo.java:394](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L394) executed in 0.25 seconds: 
```java
      return tweetsNegative.stream().skip(trainingSize).map(x->x.getText()).mapToDouble(str->{
          Map<String, Double> prob = rule.apply(str);
          System.out.println(String.format("%s -> %s", str, prob));
          return prob.getOrDefault("neg", 0.0) < 0.5 ? 0.0 : 1.0;
      }).average().getAsDouble();
```
Returns: 
```
    0.6439
```
Logging: 
```
    @adaniellec its broke. you can't break something thats already brokeennn idk . but that sucks  i love nick at nite -> {neg=0.6, pos=0.4}
    ....and I won't see her till she wakes up on Monday.  She is asleep when I leave for work and already down for the night when I get home -> {neg=1.0, pos=0.0}
    #inaperfectworld I would sleep more than 3 hours a day  I miss my zzzzzs -> {neg=1.0, pos=0.0}
    " my 12 year old is taking care of the injured black cat in front of our house -> {neg=0.6382241813602015, pos=0.36177581863979846}
    ":frankly -> {neg=0.6382241813602015, pos=0.36177581863979846}
    "&quot;this song is for every broken heart...&quot; i love you Nick -> {neg=0.24285714285714285, pos=0.7571428571428571}
    #bgt It's a shame that the Dreambears won't get through -> {neg=1.0, pos=0.0}
    coz he's -> {neg=0.6382241813602015, pos=0.36177581863979846}
    "@_rolando : Haha -> {neg=0.369031377899045, pos=0.630968622100955}
    #dontyouhate living in the same town as bitches whilst awesome girls far away from you -> {neg=1.0, pos=0.0}
    ... the shoes I wanted weren't in my size... and even online they don't have them in my size -> {neg=0.75, pos=0.25}
    "@60291tdy that'd be fun -> {neg=0.369031377899045, pos=0.630968622100955}
    life isn't always good right? -> {neg=1.0, pos=0.0}
    @_fierce omg!!! Thats awful! -> {neg=0.369031377899045, pos=0.630968622100955}
    &quot;eat brick kid&quot; lol. watching Home Alone 2: Lost in New York. but it's almost over -> {neg=0.24285714285714285, pos=0.7571428571428571}
    ...but there's one i can think of when someone else claims my credit after my hard work. its cal rotten. -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @3liz4b3th ... holy shit. -> {neg=0.369031377899045, pos=0.630968622100955}
    &quot;sometimes only one person is missing and the whole world seems depopulated.&quot; truer than true. i miss my baby -> {neg=0.8421052631578947, pos=0.15789473684210525}
    #Neogaf is already dying from 500s -> {neg=0.6382241813602015, pos=0.36177581863979846}
    #spbpuk other common failures to upgrade: dec CU had a corrupted dlls in it and hanged. Just my experience. -> {neg=0.6382241813602015, pos=0.36177581863979846}
    #inaperfectworld I would be Scouse and living in Liverpool with a season ticket -> {neg=1.0, pos=0.0}
    #dontyouhateitwhen you have school in the morning &amp; its 3:28 &amp; you cant sleep . I DO  . lmaoo -> {neg=0.5, pos=0.5}
    y does sounds of spring have to be 18+. im going to soooo many music festivals next year as soon as im 18 lol -> {neg=0.6666666666666666, pos=0.3333333333333333}
    &quot;you're so mean! you drank that right in front of my face!&quot;-maddie gosselin to kate -> {neg=0.5, pos=0.5}
    #WSOP4  Omg so many players  I m waiting to see the final table but that could keep for a long time -> {neg=0.6382241813602015, pos=0.36177581863979846}
    ".@kevox And like that -> {neg=0.369031377899045, pos=0.630968622100955}
    "@acedotal I WISH I HAD BEEN THERE -> {neg=0.369031377899045, pos=0.630968622100955}
    "@abuscher it's ok bb we're almost done -> {neg=0.369031377899045, pos=0.630968622100955}
    "&quot;Love -> {neg=0.24285714285714285, pos=0.7571428571428571}
    I want all my friends back. -> {neg=0.6382241813602015, pos=0.36177581863979846}
    - stressed out -> {neg=0.6382241813602015, pos=0.36177581863979846}
    :O look at that rain! -> {neg=0.6382241813602015, pos=0.36177581863979846}
    * my keyboard is acting funny. now i have to type everything in notepad and copy/paste -> {neg=1.0, pos=0.0}
    feeling down. reality is setting in. i know i should be beyond this point. why am i still here? i want to follow my dreams but im scared -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @warbo is off clubbing... must come over to sheffield sometime &amp; party but really probably shouldn't atm. a tech conference is a conf etc -> {neg=1.0, pos=0.0}
    *Sigh* am I invisible. -> {neg=0.6382241813602015, pos=0.36177581863979846}
    #dontyouhate when you realize the milk is spoiled after you started eating your cereal -> {neg=1.0, pos=0.0}
    &quot;You have me. You just don't want me.&quot; -> {neg=0.24285714285714285, pos=0.7571428571428571}
    @1KrazyKorean: I so want that BB  stuck with the 8310 is it AWESOME ? -> {neg=0.369031377899045, pos=0.630968622100955}
    im sorry. Both of you. -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @929perthmusic I'd prefer Tamiflu to be honest.  But maybe it's just me -> {neg=0.369031377899045, pos=0.630968622100955}
    #lofnotc a rainy evening filled with letter-writing and internet poker... not too bad. but i have no wine  this is a sad tragedy -> {neg=1.0, pos=0.0}
    @1arner I just want it to be summer already  the weather atm doesn't really brighten my mood xx -> {neg=0.6363636363636364, pos=0.36363636363636365}
    @ACsBarbieGirl69 Awww!  That's sad!   You're just gonna have to fantasize about him instead! -> {neg=1.0, pos=0.0}
    #bonktown had some sweet Time carbon shoes for $50!!! Euro 45 would have been too big -> {neg=0.6382241813602015, pos=0.36177581863979846}
    #drupal fighting with panels and menus! Can't add a new tab to the &quot;My account&quot; page with has an argument. -> {neg=0.24285714285714285, pos=0.7571428571428571}
    @abby1412 ino the feeling. except that i have 6 in 4 days -> {neg=0.369031377899045, pos=0.630968622100955}
    #SanctuarySunday we're not in the top right now -> {neg=0.6382241813602015, pos=0.36177581863979846}
    Why do you always have to act like this? - http://tweet.sg -> {neg=0.7, pos=0.3}
    @100MonkeysMusic  any Philly shows soon? I just came back here to find out I missed u guys again -> {neg=0.6, pos=0.4}
    "&quot;quit -> {neg=0.24285714285714285, pos=0.7571428571428571}
    @AlanCarr she called you a prick?! that's rich coming from her -> {neg=0.369031377899045, pos=0.630968622100955}
    @_keytistarship no I had guests comeover at 7 so I couldn't go... Sorry -> {neg=1.0, pos=0.0}
    idk why but my heart just sank when i saw that. -> {neg=0.6382241813602015, pos=0.36177581863979846}
    I WISH IT WAS SUMMER TIME..... -> {neg=0.6382241813602015, pos=0.36177581863979846}
    I miss you -> {neg=0.6382241813602015, pos=0.36177581863979846}
    #iremember when I loved Twitter more -> {neg=0.5294117647058824, pos=0.47058823529411764}
    AND maths exam tomorrow; just to make life even bettter.... -> {neg=0.6382241813602015, pos=0.36177581863979846}
    i wish paramore won for best song... boo -> {neg=0.6382241813602015, pos=0.36177581863979846}
    *sigh*  sweet dreams guys...... -> {neg=0.6382241813602015, pos=0.36177581863979846}
    (@TheTurtleShow) I need a hug....    it's my bday.. and I damn sure can cry if I want to.. I'm a big boi now lol -> {neg=0.46153846153846156, pos=0.5384615384615384}
    @___lora your telling me -> {neg=0.369031377899045, pos=0.630968622100955}
    #inappropriatemovies &quot;Alive&quot; re:the missing plane -> {neg=0.8421052631578947, pos=0.15789473684210525}
    @211me  but we have so much fun bugging you!! -> {neg=0.369031377899045, pos=0.630968622100955}
    #haveyouever been in a long distance relationship??? -> {neg=0.6382241813602015, pos=0.36177581863979846}
    ...Did NOT enjoy being stuck on the M25 for 2 hours at 2am however! -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @ajm_12 what up!! Wish I was road trippin instead of workin -> {neg=0.8461538461538461, pos=0.15384615384615385}
    @A420Queen ohhh   sorry to hear that -> {neg=0.9090909090909091, pos=0.09090909090909091}
    sims 3 is messing up abit.... -> {neg=0.6382241813602015, pos=0.36177581863979846}
    "@abbiegriffin  i wish i was coming sweet! also I MISS YOU -> {neg=0.369031377899045, pos=0.630968622100955}
    @AHisme  whats wrong? -> {neg=0.369031377899045, pos=0.630968622100955}
    @_hayles I bloody hope so! Its been ages  xxxx -> {neg=0.369031377899045, pos=0.630968622100955}
    ... WHATEVER. -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @adamftw Nooooo  It's a boring as book. Well it's alright but I just don't like it &gt;&lt; -> {neg=0.5714285714285714, pos=0.42857142857142855}
    @1capplegate Samantha Who's one of only TWO TV shows I watch!  TV world's being so mean. :| -> {neg=0.369031377899045, pos=0.630968622100955}
    aww i already did -> {neg=0.6382241813602015, pos=0.36177581863979846}
    I cut my hair -> {neg=0.6382241813602015, pos=0.36177581863979846}
    : I feel so sorry for what I did a while ago. I shouldn't do that. Mean me.  http://plurk.com/p/yxzbw -> {neg=0.5454545454545454, pos=0.45454545454545453}
    "@_katieedwards I can't yet back -> {neg=0.1, pos=0.9}
    "@_iDANCE19 well yes lol ..  i had a chat with them earlier -> {neg=0.369031377899045, pos=0.630968622100955}
    #iremember when *NSYNC was the shiznit. i miss those day -> {neg=0.16666666666666666, pos=0.8333333333333334}
    : spend too much time trying to fit in with everyone else. -> {neg=0.45454545454545453, pos=0.5454545454545454}
    I miss my Megan already and I may or may not have already cried. -> {neg=0.6382241813602015, pos=0.36177581863979846}
    " it's time for poh to leave -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @ajinnashville lol gee thanks ...LOL. This is just a royal PIA   just wanna go home -> {neg=0.025, pos=0.975}
    @acoetzee  poor whalies. -> {neg=0.369031377899045, pos=0.630968622100955}
    #lr - May have to say goodbye : hey guys.  i might have to get rid of my disco soon  there may be too m.. http://tinyurl.com/nen598 -> {neg=0.5625, pos=0.4375}
    " thinking I may need to go back to the dr. The more I walk -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @AdamBoreland aw babe! You make me miss ballet. -> {neg=1.0, pos=0.0}
    @_JAILBAIT LOL a cloggie as an ex cool ...but ex fiance?? ohhh thats uhmm not good news -> {neg=0.8666666666666667, pos=0.13333333333333333}
    @__simplytrice -- Id kill to weight like 215 lol. -> {neg=0.369031377899045, pos=0.630968622100955}
    &quot;will you come home and stop this pain tonight?&quot; -> {neg=0.24285714285714285, pos=0.7571428571428571}
    @aiden90 hellooooo &lt;3 very very busy i am  can't WAIT for the effing holidays -> {neg=0.6, pos=0.4}
    @adelong  im sure it was tho -> {neg=0.369031377899045, pos=0.630968622100955}
    has got no phone fr cupla days so facebook me (inbox) gt no laptop only ifone wf no sim x -> {neg=0.6382241813602015, pos=0.36177581863979846}
    "(sigh) just disappointed is all... we had plans to do kid-free things together (golf -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @ACTinglikeamama SNAP! I have been laying low as I currently have full blown all day/all night morning sickness. Naseau sux! -> {neg=0.369031377899045, pos=0.630968622100955}
    @Accessories_UK Nope  still couldn't do without it. Everytime I load it up in the morning the reply column is empty.... -> {neg=0.2, pos=0.8}
    @_ophelia I really don't like her  yes I'm jealous of her...he could to so much better!!! Like....me for example LOL and where's the kid!! -> {neg=0.8666666666666667, pos=0.13333333333333333}
    $13850 at 2nd break of $340 at Venetian Deep Stack...Had $22850 just before the break... -> {neg=0.6382241813602015, pos=0.36177581863979846}
    #SPO Issue#69: it's NEW (REQ).....FYI level2 again -> {neg=0.6382241813602015, pos=0.36177581863979846}
    #asot400 only see colours no picture -> {neg=0.6382241813602015, pos=0.36177581863979846}
    my ankle is sore. -> {neg=0.6382241813602015, pos=0.36177581863979846}
    #theBNParetwats and I sure as hell hope they never get to this country. Poor British folk -> {neg=0.6382241813602015, pos=0.36177581863979846}
    my great grandmother died. i couldnt even see her before she died -> {neg=0.6382241813602015, pos=0.36177581863979846}
    ...back to studying surgery -> {neg=0.6382241813602015, pos=0.36177581863979846}
    " I missed the second annual zombie walk in chicago -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @1planet1people mid-year exams...its the middle of the school year...he'll have more in Nov -> {neg=0.369031377899045, pos=0.630968622100955}
    @_kalanigordon I wanted to meet the infamous mama gordon.  tell her hi -> {neg=0.369031377899045, pos=0.630968622100955}
    @_FK_ Now you have lost me on that comment -> {neg=0.369031377899045, pos=0.630968622100955}
    @69SheriffJezzy @Fairy_Claudine *newbee human gets nervous with all these immortals swinging axes  * -> {neg=0.369031377899045, pos=0.630968622100955}
    *Please upload photos onto the FB FORMAL group* ...pls make my life easier -> {neg=0.6382241813602015, pos=0.36177581863979846}
    *yawn* could english get anymore boring -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @1vs100XboxLive Bring @majornelson back! I missed the night he was on -> {neg=0.6, pos=0.4}
    #dontyouhateit when its finally nice out after 11 days of rain n u gotta be stuck @ work all damn day! -> {neg=0.369031377899045, pos=0.630968622100955}
    &quot;I want nasi goreng &quot; &quot;Yaudah friday aja makannya&quot; &quot;But its already friday!&quot; -> {neg=0.6, pos=0.4}
    @_Slamma_ all my favourite songs by them and then it kills them. -> {neg=0.369031377899045, pos=0.630968622100955}
    #squarespace i am officially fat -> {neg=0.6382241813602015, pos=0.36177581863979846}
    #ff @dukedinero @dukedinero @dukedinero i need 33 new followers it's only 300 i want for now -> {neg=0.6428571428571429, pos=0.35714285714285715}
    I'm still tired. I think I'll make some coffee. Hopefully it'll keep me up. z.z -> {neg=0.6382241813602015, pos=0.36177581863979846}
    - Wish there had been more Mass Effect 2 in EA's press conference.  #e3 #ea #masseffect2 -> {neg=0.6382241813602015, pos=0.36177581863979846}
    leaving wildwood is not fun. :/ farewell you swell land. i love you. -> {neg=0.21428571428571427, pos=0.7857142857142857}
    &quot;Binthia&quot; is serious now. She hot really quiet and looks mad tired. Kinda feel bad cuz she had to drive after only sleeping a few hours -> {neg=0.24285714285714285, pos=0.7571428571428571}
    " can't sleep -> {neg=1.0, pos=0.0}
    headache. -> {neg=1.0, pos=0.0}
    @ GaryJrBoston aww...i want fajitas &amp; ritas  sad http://bit.ly/ZjDcy -> {neg=1.0, pos=0.0}
    :/ Only have one and a bit screens left on my iPod for apps -> {neg=0.6382241813602015, pos=0.36177581863979846}
    &gt; @austinheap: *PERSONALLY CONFIRMED FROM #IRAN* Tanks have rolled in Tehran ... #iranelection #gr88 Flash back to '79  {Images?} -> {neg=0.369031377899045, pos=0.630968622100955}
    " I feel terrible. Gosh -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @aimzzzzz aw crap! i didnt see your tweet! i went... -> {neg=0.369031377899045, pos=0.630968622100955}
    "@4Furwoodthought it's tough -> {neg=0.369031377899045, pos=0.630968622100955}
    ok...seriously... this is the most depressing movie ever. I wanna  just give these people a hug. this is HORRIBLE. -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @ALauderdale I donno what time I'm on I didn't even know what day it was earlier SMH -> {neg=1.0, pos=0.0}
    @aardmanonline - lamenting the fact that I didn't win free tickets to the W&amp;G beer evening on wednesday by Timeout -> {neg=0.9090909090909091, pos=0.09090909090909091}
    @aaroncarter7 i called u a king and legend and this is how u repay me  how will u sleep? -> {neg=0.369031377899045, pos=0.630968622100955}
    Why he gotta look like that? -> {neg=0.6382241813602015, pos=0.36177581863979846}
    &quot;You do seem like a very interesting and attractive geeky redhead&quot; .... I don't even have red hair  ... muppet -> {neg=0.24285714285714285, pos=0.7571428571428571}
    Im dying of thirst and I wish I was in a large body of water somewhere in Hawaii!! Anything with clear water actually!!! -> {neg=0.6382241813602015, pos=0.36177581863979846}
    #NEDA no longer a Trending Topic   #IranElection -> {neg=0.6382241813602015, pos=0.36177581863979846}
    what a bitchh. -> {neg=0.6382241813602015, pos=0.36177581863979846}
    &gt;. Bodies from Air France crash have been found  http://twurl.nl/dvu8be (via @Paisano ) -> {neg=0.369031377899045, pos=0.630968622100955}
    @ajulloa :-P everytime I find a bug a LOLcat dies -> {neg=0.369031377899045, pos=0.630968622100955}
    @3rdEden That's great!  I wish I could go this year -> {neg=0.8181818181818182, pos=0.18181818181818182}
    life is taking a lot of energy out of me atm. working today with a girl i hate! and have to usher children into sunday school to sing... -> {neg=1.0, pos=0.0}
    (yawn) doesn't want to go working... but i have to :X -> {neg=0.6666666666666666, pos=0.3333333333333333}
    @_pixie_ its not letting me request more peeps. 
    ...skipping 1080241 bytes...
    but I'm not having much luck -> {neg=0.8461538461538461, pos=0.15384615384615385}
    "@AaronWarner I know -> {neg=0.369031377899045, pos=0.630968622100955}
    #dontyouhate wheenn ya secret get out andd ppl rubb it in yaa facee so sad right now  i wanna cry lols -> {neg=1.0, pos=0.0}
    #goodsex when he make ur cycle come a couple days early lol  #badsex it don't come @ all! -> {neg=1.0, pos=0.0}
    Last morning in CuraÃ§ao. I'll miss the place especially since it 59 F at home right now...should I wear long sleeves on the plane? hmm. -> {neg=0.5, pos=0.5}
    @_xotashhh I'm so jealous  I wanna see both of them. -> {neg=0.6, pos=0.4}
    typo .... Let's try again: Happy Sunday! Watching &quot;How the Earth Was Made&quot; ...Palio Indians -> {neg=0.24285714285714285, pos=0.7571428571428571}
    @addisontodd um hi. Good morning. Rough night? -> {neg=0.369031377899045, pos=0.630968622100955}
    must try to raise some funds to get better computer present one like me worn out. also need a publisher/literary agent? -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @ dance... i luv dance but am honestly not having much fun -> {neg=0.17647058823529413, pos=0.8235294117647058}
    England what the hell :'( :'( #cricket # T20 -> {neg=0.6382241813602015, pos=0.36177581863979846}
    i actually do nat like my life at times like this  want to actually jst go and throw myself off a bridge. -> {neg=0.6382241813602015, pos=0.36177581863979846}
    Nothing EVER goes according to plan for me. Now I might only see Katy once. this is so sad. -> {neg=1.0, pos=0.0}
    i got sun burned bad at work! -> {neg=0.6382241813602015, pos=0.36177581863979846}
    I couldn't eat my strawberry ice cream. My tummy hurts. -> {neg=1.0, pos=0.0}
    " I don't wanna. What I do want -> {neg=0.75, pos=0.25}
    @13Christina well tomorrow i'm gonna hopefully be getting good news Jeep-wise.  I gotta get up early and go for a run.  Didn't swim 2day. -> {neg=1.0, pos=0.0}
    I wanna be at Encounter  blah. -> {neg=0.6382241813602015, pos=0.36177581863979846}
    *sighs* I wonder when can I upload my website's design. Everyone's too busy to even integrate it in wordpress -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @ woman_within I LOVE the catalog and my new dress - I NEED more work clothes but can't afford to shop anymore for now -> {neg=1.0, pos=0.0}
    "       Orlando Lost  The Series. Oh Well -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @alaero You sure they arent fakes? My fav. trackers aren't showing anything -> {neg=1.0, pos=0.0}
    not going to the visa championships this year because I need to save money. good luck @NLiukin -> {neg=0.369031377899045, pos=0.630968622100955}
    ...has a really sore head!! -> {neg=0.6382241813602015, pos=0.36177581863979846}
    Stats test/Rotary speech/NCEA speech/netball x 2/netball games/cultural night/lack of Maori-ness atm/packing = unhappy MIAH! :'( -> {neg=0.6382241813602015, pos=0.36177581863979846}
    (sigh) Misses that she is gone. Forever. -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @tahninial just called me a cheeseburglar. He made me sad -> {neg=1.0, pos=0.0}
    @akoundal dude u need a break..like a drive to a place unknown...enjoy nature and get back to the ...well....same old work -> {neg=0.369031377899045, pos=0.630968622100955}
    "@acoldsky lets see...nate&amp;blair breakup -> {neg=0.369031377899045, pos=0.630968622100955}
    #iremember when me anddd  @gimmedunkaroos tlkd everyday . -> {neg=0.369031377899045, pos=0.630968622100955}
    @__Aoife__ Thats the repeat which im not doing  AMG INOOOOOO!!!!!! im not excited tho  i dont wanna live with a boy for 4 days :L -> {neg=0.5, pos=0.5}
    @3zlyca Hope your back feels better today. -> {neg=0.369031377899045, pos=0.630968622100955}
    "@_dritan Dam you -> {neg=0.369031377899045, pos=0.630968622100955}
    i wish i could sleep in like everyone else. -> {neg=0.45454545454545453, pos=0.5454545454545454}
    &quot;are you a lady?&quot; everyone thinks I sound like woman on the phone  gosh im so gay -> {neg=0.24285714285714285, pos=0.7571428571428571}
    "#BSNL -> {neg=0.6382241813602015, pos=0.36177581863979846}
    ": i'm so sorry -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @_JAYYTEE Naw they left for palm beach this morning.... -> {neg=0.369031377899045, pos=0.630968622100955}
    @aaronfuller Yeah but itunes keeps locking up for me. It's dead annoying -> {neg=0.369031377899045, pos=0.630968622100955}
    "@_Undeniable_ Hey!!!  Im good -> {neg=0.369031377899045, pos=0.630968622100955}
    &lt;runs and hides&gt; I AM NOT haveing a flu vaccination. My last 5 needle experiences have all been traumatic -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @ jaazy btw how do you know this ??!?!? joe is a disappointment    NICK ^^ -> {neg=0.8571428571428571, pos=0.14285714285714285}
    @ajreynolds Ahh. I had to log in and then the link is borked now. -> {neg=0.5, pos=0.5}
    ". @karlyross Jeez -> {neg=0.369031377899045, pos=0.630968622100955}
    @41414141 hope you get better soon I missed the after party  but also the PH-Phunk too -> {neg=0.6, pos=0.4}
    @3MON3Y It was freaking delicious!!! But I'm still hungry -> {neg=0.369031377899045, pos=0.630968622100955}
    #3wordsaftersex : Delete My  Number !!!    Aouch   #3stalkerwords -> {neg=0.6382241813602015, pos=0.36177581863979846}
    less and less people have been partying lately. -> {neg=0.6382241813602015, pos=0.36177581863979846}
    ????? 11:15 ?????; You gotta be kidding me -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @1roxstar don't worry. Pickles got sand booted in his face last week! It happens -> {neg=0.9090909090909091, pos=0.09090909090909091}
    *sigh* Nope nothing working tried what @_iwade_ and what @nick_myndflip suggested. Pretty sure its DHCP anyway Wade. But thx neway -> {neg=0.7777777777777778, pos=0.2222222222222222}
    "&quot;  Im going to try to find pictures of the X-factor tour -> {neg=0.24285714285714285, pos=0.7571428571428571}
    @lushone toolio has court on the 24th... -> {neg=0.369031377899045, pos=0.630968622100955}
    ok we'll talk about your boyfriends and stuff tomorrow ;) lmao and we'll talk for real soon too!! wooo hehe ilysfm doodle xxxx -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @aceblack Noooo don't leave me -> {neg=0.9333333333333333, pos=0.06666666666666667}
    "- new haircut -> {neg=0.6382241813602015, pos=0.36177581863979846}
    How 2 get OCDer 2 eat more than 1-2 meals a day? We could take him off meds &amp; watch him slowly fall apart again but he'd eat like a horse -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @aaronfuller I don't mean to sound rude or owt Aaron  but I don't see why it's such a big deal now that BRITNEY says the wrong venue... -> {neg=0.3076923076923077, pos=0.6923076923076923}
    ..but Haagen-Dazs stopped selling chocolate peanut butter ice cream in Canada. -> {neg=0.6382241813602015, pos=0.36177581863979846}
    #GTRetweet : took my niece on her first whale watch yesterday - not a lot of whale sightings though  http://bit.l... http://bit.ly/9EUFd -> {neg=0.25, pos=0.75}
    @accyroy I don't know what to do!  It's cheaper to buy the iphone on pay and go and get a simplicity sim ... for unlimited texts! -> {neg=0.7333333333333333, pos=0.26666666666666666}
    @_bme Yeah I'm a bit a loose ends too. I'm just not sure that Rocky and Bullwinkle will quite do it for me -> {neg=0.35714285714285715, pos=0.6428571428571429}
    "@adamtlee I found that out  KSC seems to show movies sometimes -> {neg=0.369031377899045, pos=0.630968622100955}
    ...day 2 of vegetarianism... so far so good... but not many options for me since i'm allergic to onions... -> {neg=0.6382241813602015, pos=0.36177581863979846}
    another Saturday without MO. Glad Rachel is coming over! -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @_writersblock_  so true...... Annoys the heckkkkkkk outta me!!!!!!! -> {neg=0.369031377899045, pos=0.630968622100955}
    @AchLeMepris I can't watch it it's not there anymore -> {neg=0.9090909090909091, pos=0.09090909090909091}
    mcfly secrets isn't working for me. Is it for everyone esle? -> {neg=1.0, pos=0.0}
    at tegus x 2 days only -> {neg=0.6382241813602015, pos=0.36177581863979846}
    so whats the status on next weekend -> {neg=0.6382241813602015, pos=0.36177581863979846}
    " i was vometing all ova the place.. and at the end -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @ work. I'm mad cause the weather is too nice to stay inside -> {neg=0.369031377899045, pos=0.630968622100955}
    #f1 just finished. nice result for england I suppose! now to do more work -> {neg=0.6382241813602015, pos=0.36177581863979846}
    ouchh i poked my eye. -> {neg=0.6382241813602015, pos=0.36177581863979846}
    ....no show tonight  just a day to stay in. -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @abrowngirl Spymasters is being mean to me  haven't been able to do any tasks since twitter's maintenance last night -> {neg=1.0, pos=0.0}
    @_Glitter_ Oh...so sorry to hear that...so sorry -> {neg=0.9090909090909091, pos=0.09090909090909091}
    #dontyouhate when ur so bored &amp; all that's keeping you entertained are the trending topics -> {neg=1.0, pos=0.0}
    "(@gingiringingin) not that i don't mind people talking to me -> {neg=0.5714285714285714, pos=0.42857142857142855}
    @0nlyindreams i wanna move too! But i cant  lol -> {neg=1.0, pos=0.0}
    ...and I'm stick behind a man buying a bottle of beer with no barcode -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @Adam_WR I want to watch it so bad now. My dad wont finish the quotes with me though  haha -> {neg=0.5, pos=0.5}
    i so dont know what im doing on here its all so new -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @_Cher_ Ditcher! Nowww you want to go running then to the gym. -> {neg=0.369031377899045, pos=0.630968622100955}
    - back pain -> {neg=0.6382241813602015, pos=0.36177581863979846}
    "@_Glenn it's sad  it's so so hard to see the stars where I'm at -> {neg=1.0, pos=0.0}
    This was what i was afraid of: http://bit.ly/R7P0Z -> {neg=0.75, pos=0.25}
    bad headache -> {neg=1.0, pos=0.0}
    ...it ain't coming off.  Oh.  It's not mascara.  Hello concealer (sp?).  And perspective. -> {neg=1.0, pos=0.0}
    "#iremember getting my first guitar when i was 6. I took that thing for granted until i woke up one morning -> {neg=0.1, pos=0.9}
    #inaperfectworld i'd be with him -> {neg=1.0, pos=0.0}
    BOREDD I WANT TO GO HOMMMMME. -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @ just woke up -> {neg=0.369031377899045, pos=0.630968622100955}
    ohh the agony. Class may not be a possibility at all.. -> {neg=0.6382241813602015, pos=0.36177581863979846}
    "@_yennie I assume tea probably won't help either?  I feel like a cookie will solve your problems -> {neg=0.6923076923076923, pos=0.3076923076923077}
    im gonna miss my psychology/sociology teacher -> {neg=0.6382241813602015, pos=0.36177581863979846}
    ....Im lonely.... -> {neg=0.6382241813602015, pos=0.36177581863979846}
    #3wordsaftersex &quot;you really smell.&quot;  Excited for my trip tomorrow. not excited for missing @LYDIAmusic and @brandonwronski tomorrow. -> {neg=0.8421052631578947, pos=0.15789473684210525}
    @703withlove757 i miss u way too much  UGH! -> {neg=0.7692307692307693, pos=0.23076923076923078}
    "- I wake up -> {neg=0.6382241813602015, pos=0.36177581863979846}
    ":reading about the con-ass news.i don't trust congress but what's worse is -> {neg=0.5, pos=0.5}
    @adlantis  Clean Me! -> {neg=0.369031377899045, pos=0.630968622100955}
    " finding out the Hollywood Video i frequent -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @ Barbiedoll1601  Stop making fun! its not nice! -> {neg=0.3888888888888889, pos=0.6111111111111112}
    .. no motivation today -> {neg=0.6382241813602015, pos=0.36177581863979846}
    #fail shazam isn't working on my 3gs  that's my most favoritist app!! -> {neg=1.0, pos=0.0}
    :O we're not eating sushi for dinnnaarr -> {neg=0.6382241813602015, pos=0.36177581863979846}
    #GNO and I can't come. again. Bummer. -> {neg=1.0, pos=0.0}
    @absbia777 Awww.....poor you!  Did you like it? xx -> {neg=0.369031377899045, pos=0.630968622100955}
    @adinab i wanna go to the san diego one so bad but i'm not gonna have money when they go on sale -> {neg=0.5, pos=0.5}
    "@akesterson Or the usual GCC &quot;You did something really dumb -> {neg=0.24285714285714285, pos=0.7571428571428571}
    @718pm helllooo ur over me? Indiaaaa I'm sorry if I did sumthn -> {neg=0.9, pos=0.1}
    :'( big brother in 4 days! This means constant live tripe on e4 and no scrubs to fall asleep to! Not happy -> {neg=0.0, pos=1.0}
    @_stephhh_ did david not do waiting for yesterday and my hands tonight? -> {neg=1.0, pos=0.0}
    ...that is all. -> {neg=0.6382241813602015, pos=0.36177581863979846}
    "- Moon book but it got away -> {neg=0.6382241813602015, pos=0.36177581863979846}
    ...and off he goes in an angry haste... -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @AdamWright ive had the same &quot;welcome to the new macbook pro family&quot; email 3 times today  #fail -> {neg=0.6666666666666666, pos=0.3333333333333333}
    "@_justmitch_ 4:30 in the nex 30 mins  But i wanna go now -> {neg=1.0, pos=0.0}
    this key board doesnt have keys for volume D: im gonna have to use the mouse and do it the hard way! lmfao. -> {neg=0.6666666666666666, pos=0.3333333333333333}
    super tired and bloated with foodsss... dun feel like bathing at all :X -> {neg=1.0, pos=0.0}
    &quot;hoping it will end up in his pocket but he leaves you out like a penny in the rain&quot; maybe 'coz hes married and reproducing like a rabbit -> {neg=0.24285714285714285, pos=0.7571428571428571}
    :0 three out of my four close friends are now taken! n knowin amy she's not gonna be single for long too.. ima be the only loser   :/ -> {neg=0.6382241813602015, pos=0.36177581863979846}
    " worst day ever!! its raining.. i want to eat -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @1capplegate aw crap i loved that show i was hoping 4 a season three -> {neg=0.369031377899045, pos=0.630968622100955}
    @_ohaii  hmm. I don't know anything anymore. Confused. Wish I could tell someone but I'm not too sure myself. -> {neg=0.7333333333333333, pos=0.26666666666666666}
    *sigh* another exam this Friday -> {neg=0.6382241813602015, pos=0.36177581863979846}
    I cut myself in like 3 different places. Sucky much.? -> {neg=0.6382241813602015, pos=0.36177581863979846}
    "#hdc @Footdr69 shitful day -> {neg=0.369031377899045, pos=0.630968622100955}
    twitteds dead. Haha. -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @ABeautifulMind1 I offered him a massage on his injured shoulder...(I am professionally  trained) but he hasnt taken me up  Thanks for #ff -> {neg=0.6, pos=0.4}
    I wish things were different -> {neg=0.6382241813602015, pos=0.36177581863979846}
    "@ work now -> {neg=0.3888888888888889, pos=0.6111111111111112}
    riding horses -> {neg=0.6382241813602015, pos=0.36177581863979846}
    #ikl was going to knit up a tomato baby hat for workmate during Question time but left pattern on printer -> {neg=0.6382241813602015, pos=0.36177581863979846}
    " my backrounds not working -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @acenadren haha. You wouldn't because you're wearing them -> {neg=0.4, pos=0.6}
    " I'm gonna smile like nothing's wrong.Talk like everything's perfect -> {neg=0.6382241813602015, pos=0.36177581863979846}
    @adambombshow so wut no more videos starting me -> {neg=0.7222222222222222, pos=0.2777777777777778}
    @106jackfm raining in wallingford -> {neg=0.369031377899045, pos=0.630968622100955}
    &quot;omg i m so sorry! No dont stop pls  which was d rude word? Coz i feel stupid doing this 2 a girl without thinking&quot; -> {neg=0.5, pos=0.5}
    &quot;in 2 weeks it's gonna be my first time going to Jakarta n you're not there  &quot; said my mean sister. Well she's not that mean after all.. -> {neg=0.8333333333333334, pos=0.16666666666666666}
    "&quot;im so sick -> {neg=0.24285714285714285, pos=0.7571428571428571}
    @a_simple_girl @BuzzEdition I have to miss the festivities this evening.  Heading up the hill and won't have internet and no BB service. -> {neg=1.0, pos=0.0}
    @AKGovSarahPalin   that is scary -> {neg=0.369031377899045, pos=0.630968622100955}
    @_Au_ I know it! but they are clear up north...I was hoping for something closer -> {neg=0.8666666666666667, pos=0.13333333333333333}
    suns gone  ohh well best go to work!!! -> {neg=0.6382241813602015, pos=0.36177581863979846}
    
```

