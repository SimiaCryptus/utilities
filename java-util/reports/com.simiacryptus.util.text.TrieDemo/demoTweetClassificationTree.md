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
    [TweetSentiment{title='@Adam9309 good morning my wee darling!...*waving*'}, TweetSentiment{title='@48StatesAway Flurry of Twitter weirdnesses the last hour or so  Email me @ insomniacgrafx@mac.com and we can talk about your logo!'}, TweetSentiment{title='' @minghao Maybe you can pop @StarHubCares a tweet. They are quite active on twitter.'}, TweetSentiment{title='@_yoyo Sweet! Name your price chicklet. DM me please'}, TweetSentiment{title='@agir  saw your email! Will send a response in a bit!'}, TweetSentiment{title='"@acarback.  yes'}, TweetSentiment{title='...but at least i'm ticket free'}, TweetSentiment{title='? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ?    ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ?     ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ?  twitterart'}, TweetSentiment{title='- last minute 3-hour session in the studio...just got done. tired.'}, TweetSentiment{title='#followfriday @aceweekly @pkoduri @KimmyVille @robmorris2 all are really smart and have good things to say'}, TweetSentiment{title='&quot;My name's Pitt. And your ass ain't talkin' your way out of this shit. &quot; (watchin' Pulp fiction)'}, TweetSentiment{title='@ ThornesWorld Thanks for the follow - it's nice to meet you! BB'}, TweetSentiment{title='@a_liss_a I rate you my most interesting person I follow!!'}, TweetSentiment{title='"@AceMas21 Yeah'}, TweetSentiment{title='#dreamwidth invites are all gone for now! I'll offer up some more on here when I get them'}, TweetSentiment{title='@1sweetwhirl yw hun. You put up some good stuff  Hope you're good.'}, TweetSentiment{title='"#uptime 20:24  up 6 days'}, TweetSentiment{title='#musicmonday : Evanesence (Spell check) This group is on point like a thorn.'}, TweetSentiment{title='@_MisterG Hope the tests for your Dad go well.'}, TweetSentiment{title='"@AbbeyEmm Haha. If you like him'}, TweetSentiment{title='&quot;@mom&quot;where are you  that Miley write with you ????I write to Miley but Miley never answered =( what am I to do that Miley write with me??'}, TweetSentiment{title='.@BlowhornOz thanks mate!!! you ROCK!!  #NoUndiesSunday'}, TweetSentiment{title='"@ACC_ Thanks so much!!! and good night  it's 2 am here'}, TweetSentiment{title='@Ahmad_Alharthi ?? ???????'}, TweetSentiment{title='@mgph3nom chilling on twitter via my spanking new blackberry w/ unlimited internet : http://tr.im/mZVC'}, TweetSentiment{title='"#WhyITweet? For instant WOM recommendations and info for work'}, TweetSentiment{title='@A12291994 you are lame  go make me breakfast!!'}, TweetSentiment{title='(Trying to) Manage his projects among raptors'}, TweetSentiment{title='. @ronnieledesma I don't know... K! So are you going to beat box on Wed. I created a list on my FB if you want some ideas'}, TweetSentiment{title='"#followfriday @mamakelly'}, TweetSentiment{title='@ABROWN314 hey teets'}, TweetSentiment{title='@aahoogendoorn Interesting. I am looking forward to your new insights...'}, TweetSentiment{title='"@_BRENDUHHH_  and that's my suggestions.  they're all diff genres so maybe you'll like them'}, TweetSentiment{title='....coming to America!'}, TweetSentiment{title='... years. In Maths (D I've got Jamie which isn't too bad'}, TweetSentiment{title='@1000wattmarc well that's the problem with thinking this is as easy as breathing.'}, TweetSentiment{title='@64Colors Congrats again guys. You two rule!! Now when are we gonna get pics.'}, TweetSentiment{title='"#followfriday gal that is very Tweet &amp; lots of fun. Check out @passionsista &amp; follow her'}, TweetSentiment{title='@ elises_url u don't even know!  But robert pattinson makes up for it'}, TweetSentiment{title='@Aarathon Yes!  Do it!   I'm in the lottery.'}, TweetSentiment{title='things just might be turning around. *Knocks on wood*'}, TweetSentiment{title='#FollowFriday @kocanuts_com @rosstimson @ashleyemma @joe_carney  @SimpleCMS @colbywright @ianoxley @onion2k @AndyTait @scottjsalisbury'}, TweetSentiment{title='#FollowFriday @Mia__Cavallo my Lil Sis who is a wonderful person to have around and she plays some mighty tracks!!!'}, TweetSentiment{title='@ahj just pretend you are a student - have to wear creased thingy for weeks on end'}, TweetSentiment{title='( Give me back 1976   ABBA - Dancing Queen Live http://bit.ly/vJkYs'}, TweetSentiment{title='#musicmonday listening to the  &quot;cut copy&quot; station along with &quot;MGMT&quot; on Pandora.com!'}, TweetSentiment{title=': Had a healthy weekend by going for a jog before saying goodbye to Sun..'}, TweetSentiment{title='".@nik_kee_dee well'}, TweetSentiment{title='#followfriday My girl who always makes time to reply to me when she can @BabygirlsoMajor  Love ya'}, TweetSentiment{title='???????????  She has a good point and weak point extremely. Funny'}, TweetSentiment{title='..is watching Enchanted with my babeee Hayley'}, TweetSentiment{title='"@AgataAlexander Thats one reason Im 50/50 about moving'}, TweetSentiment{title='"@aaamylee Bacon'}, TweetSentiment{title='@accidbrrittanny lol hey'}, TweetSentiment{title='&quot;@MandyyJirouxx&quot; hi mandy you are really pretty  I love the Miley and mandy show write me back pls'}, TweetSentiment{title='(drum roll) please welcome Christian Retailing (@christianretail) to Twitter! Follow for latest industry news. retweets appreciated.'}, TweetSentiment{title='@_E13_ yeah ..i'm not normal  LOL'}, TweetSentiment{title='@_Bryony_ Brilliant pics! Thanks for sharing'}, TweetSentiment{title='@11640447 when I get on the computer... we gotta take more later on in the week!'}, TweetSentiment{title='"*smacks head* NOOOOO!! Not exams!! I dont want to do the Human biology exam on Fridayy'}, TweetSentiment{title=': don't forget to watch School Rumbel at Multimedia Saturday 20 June 2009  4 o'clock. Thanx'}, TweetSentiment{title='#whatif I was a boy'}, TweetSentiment{title='"&quot;select /*+ ALL_ROWS */ * from Table&quot; A new way to do Queries... outstanding performance'}, TweetSentiment{title='@_yps Ð¼Ð¾Ð¹ Ñ‚ÐµÐ±Ðµ Ñ?Ð¾Ð²ÐµÑ‚ Ð¸Ð·Ð±Ð°Ð²ÑŒÑ?Ñ? Ð¾Ñ‚ Ð½ÐµÑ‘ Ðº Ñ…ÑƒÑ?Ð¼'}, TweetSentiment{title='@AcrossTheSkies Thanks for that haha.  The randoms do seem a bit rigged though.'}, TweetSentiment{title='"@aarongillespie didn't have you down as a Coldplay fan'}, TweetSentiment{title='"?????? twitter-account @NicePlaces ??? ??????? ????? ?????????? ????? www.Nice-Places.com. ?????????'}, TweetSentiment{title='"$25 mani-pedi @ denovo - only for today'}, TweetSentiment{title='*Sigh* I Love You my Jim Sturgess..'}, TweetSentiment{title='"&quot;Enjoy when you can'}, TweetSentiment{title='"&quot;she's like the wind... &quot; yea'}, TweetSentiment{title='"@_supernatual_ Give me some Dean tied up Pron'}, TweetSentiment{title='You have a faible for white legwear. But the pullover made in Ecudaor makes it a really cool outfit! http://lookbook.nu/look/154014'}, TweetSentiment{title='just finished 10k run and already booking in for more in the coming months! Such fun!'}, TweetSentiment{title='@72prufrocks You shouldn't expect absolute perfection'}, TweetSentiment{title='@ Antsje having a great time! Just went for a walk with the 2 little kids . so cute!!!'}, TweetSentiment{title='@ jscott1092 thank u! it worked!'}, TweetSentiment{title='smile GOD loves you'}, TweetSentiment{title='@aaamylee Hey! No shame in using trending to advantage  And so agree about spammers! Care to add a nice Englishman? x'}, TweetSentiment{title='Good morning sunshine!!!'}, TweetSentiment{title='"@aalaap cool'}, TweetSentiment{title='@4ut my twongue is twired'}, TweetSentiment{title='???????????! Coffee time with some chocolate'}, TweetSentiment{title='"@_Angeline_ they should make where it makes it easier to add @ replies so u don't need to type as much. I know'}, TweetSentiment{title='@adamgoldston   WHOOHOOOO!!!!  That's my team!!!!    ........I got a friend...........sniff sniff'}, TweetSentiment{title='@1sweetwhirl Sounds like you had a good one.  Mine was a little crappy.  Better luck nxt wkend.'}, TweetSentiment{title='#FOLLOW @PrincessSuperC Tha Very Talented Singer/Song Writer Ciara!!  FOLLOW Her Y'all!!!'}, TweetSentiment{title='&quot;He's just not that into you...&quot; &lt;3 this movie!'}, TweetSentiment{title='@abirtmo im good haha im goin to nj soon to see my family. we should meet up at the garden state plaza hah'}, TweetSentiment{title='#followfriday @MyChemChat'}, TweetSentiment{title='@1045CHUMFM # PLS///THX!!! XOXO'}, TweetSentiment{title='@aaronlafferty Haha! I can see that now!'}, TweetSentiment{title='"@5w Thanks'}, TweetSentiment{title='@30SECONDSTOMARS Hello Steve'}, TweetSentiment{title='*caugh* *caugh* I am ... Darth Batman!  http://hex.io/kkf  most brilliant crossover ever'}, TweetSentiment{title='@118247 yeah I love it!! It's screams 'weebl' at me but that's why I like it.. It's very cool and catchy'}, TweetSentiment{title='"@_shutupandsmile ow  .. we go back to your last avatar'}, TweetSentiment{title='#myweakness - hmm choc chip cookies  &amp; ferrero rocher MILKSHAKE!'}, TweetSentiment{title='&quot;Your leffe moments&quot; alltsÃ¥  [?]'}, TweetSentiment{title='@24websurf NOW I'm having ice cream!!!'}, TweetSentiment{title='**Writing**....finishing up this script!&quot; FINALLY! haha'}, TweetSentiment{title='#myweakness Slow dancing to Tim McGraw!'}, TweetSentiment{title='@abstanfield Beet greens ROCK!  They're my favorite. Glad you liked my recipe!'}, TweetSentiment{title='@aeg0707 Thanks friend!! Will do! I am SO excited! Let's catch up when I get back'}, TweetSentiment{title='"@21O3 wow! 1'}, TweetSentiment{title='sierra's gon' be super-sierra and invade my brother's hideout in graffiti town to go interrogate him. goodbye twitter. &lt;3'}, TweetSentiment{title='@addictedtotext yes its called hippie'}, TweetSentiment{title='"@afeeqnadzrin8 yeah but the way Adam sang it made it pitchy'}, TweetSentiment{title='@aarteepotnis Do you want to swap weather for a bit?'}, TweetSentiment{title='@10isjess  they can play poker while we do nkotb stuff  just a thought.'}, TweetSentiment{title='@adamoxford Thanks for all the tweets - it's been fascinating'}, TweetSentiment{title='....These spam bots here on twitter are getting super annoying. FIX THIS NOW TWITTER.'}, TweetSentiment{title='"#techdays_ca 2009 reg is near'}, TweetSentiment{title='@_supernatural_ thanks for making my day'}, TweetSentiment{title='#followfriday @LifeRollsOn @LifeRollsOnPD @mystorybooklady @rmilana @markhundley @rdelizo34 @Gamerchix_Kelly @sweeethart427 --great ppl'}, TweetSentiment{title='"&quot;you look a little pink&quot; was an understatment. an hour later and i'm definitely burned. not too bad'}, TweetSentiment{title='"&quot;If you change your mind'}, TweetSentiment{title='#followfriday gutzanu'}, TweetSentiment{title='@Aarooooon tell Cassia I say hello'}, TweetSentiment{title='@ahmedzainal thanks man off course friends 4 good'}, TweetSentiment{title='@2Mini i will have gotten many awards then'}, TweetSentiment{title='"@AdamParnell  Nah'}, TweetSentiment{title='"@acidsmile morning morning'}, TweetSentiment{title='@_Alectrona_ thanks love.  x'}, TweetSentiment{title='"#1 son'}, TweetSentiment{title='"&quot;Tell me all your dreams'}, TweetSentiment{title='@Aamyhaanson Okey dokey.  &lt;3MyBlackBird&lt;3'}, TweetSentiment{title='"(Yes'}, TweetSentiment{title='@09 Get 100 followers a day using www.tweeterfollow.com Once you add everyone you are on the train or pay vip'}, TweetSentiment{title='@_Kel_ I want Rocky Raccoon'}, TweetSentiment{title='@8leo i'm so jealous!! you'll have such a cool time though'}, TweetSentiment{title='"@_CrC_ Happy 30th'}, TweetSentiment{title='"@abramsandbettes Would love to stay &amp; tweet w/ ya'}, TweetSentiment{title='#FollowFriday @NickCharney for giving me faith that there is a hope in hell for the public sector'}, TweetSentiment{title='"- toothache subsiding'}, TweetSentiment{title='off to California!!!!'}, TweetSentiment{title='"@abby_mcfly heya'}, TweetSentiment{title='Home sweet home (: finally back XD'}, TweetSentiment{title='@262RUNR thanks for the follow friday love  any fun plans for the long weekend besides being at the marathon?'}, TweetSentiment{title='.. is having a good time waiting for DAREL's text.. lol'}, TweetSentiment{title='"@adecembertruth hey adt'}, TweetSentiment{title='&quot;circo obama&quot; UGH! is it safe to say that @iamdiddy fck up day 26 song?! .. Willie &amp; Que can sooooo &quot;get it&quot;'}, TweetSentiment{title='- is it me OR is jeremih a lil cutie ...  ... http://bit.ly/15tP6e'}, TweetSentiment{title='"&quot;I love slaves!&quot; by David Raccah'}, TweetSentiment{title='#iremember when i was young and it was so uncomplicated... no bills... no boyfriends... it was all lego and grilled cheese'}, TweetSentiment{title='@_Michelle_Berg_ aww cutie from way back'}, TweetSentiment{title='...might have to break out the hookah'}, TweetSentiment{title='"&quot;tweet me'}, TweetSentiment{title='@5leepless Eels beat Knights'}, TweetSentiment{title='#rcncongress Did someone just say some people deserve to be shot?'}, TweetSentiment{title='@ahmedsiddiqui good luck. Hope things turn out well for you'}, TweetSentiment{title='(cont!!!)... @katiececil @shelbycobraaa  happy follow friday!'}, TweetSentiment{title='... I'm shopping in Switzerland's most beautiful City ... Lucerne'}, TweetSentiment{title='@3L1SE assheads? lmfao! i'm using that  all for gits and shiggles. haha.'}, TweetSentiment{title='" movie'}, TweetSentiment{title='.@greystonebar me too!! thanks also for the follow. i see you will be hosting one of .@MoocherGirl's tweetups?'}, TweetSentiment{title='"@AblativMeatshld We just added a bunch of people to the SR proofing pool. But'}, TweetSentiment{title='@25superstar That would be amazing.    Okay.  Now it's bed time.  Goodnight babe!'}, TweetSentiment{title='@30stshannonleto sounds good!  I know i saw a hotel not far from london eye earlier too going to check that out too and will let you know'}, TweetSentiment{title='@dustincary yea I really wanna see a win for him at daytona'}, TweetSentiment{title='"(ok blip'}, TweetSentiment{title='@abooth202 That's Ok then'}, TweetSentiment{title='"@agoodfried aw happy mothers day amanda'}, TweetSentiment{title='&quot;Twitter Cypher&quot; Verse is up'}, TweetSentiment{title='"(@EvaIsOnFire) oh cool'}, TweetSentiment{title='"#mycrack pink nail polish'}, TweetSentiment{title='"...well'}, TweetSentiment{title='#FF @KittyKatana cuz she works harder than hard workin @Smurfakins cuz she's HILARIOUS and bizarre *Smh wierd lil black girl'}, TweetSentiment{title='...i was an enjoyable experience. as i haven't watched bob esponja in forever.'}, TweetSentiment{title='#tipoftheday Get in front of a lens if you want to now how your models feel while shooting them. Selfportrait but better other photog'}, TweetSentiment{title='@afwife08 hehehe ^^ thank you'}, TweetSentiment{title='@A_Bizzle Well they always cut the episodes early! Totally sucks but Im glad Dean and Sammy somewhat made up!'}, TweetSentiment{title='"$67 for my books'}, TweetSentiment{title='i will can you believe its been a month already!'}, TweetSentiment{title='"@14tonystewart Way to go Tony'}, TweetSentiment{title='"@_BellaCullen18_ Hey'}, TweetSentiment{title='"@admanic Yeah  It was hard and although it worked for me and I'm now doing very well'}, TweetSentiment{title='#ally #ally haha'}, TweetSentiment{title='"#myweakness NSFW content to follow:  Large DD breasted nurse that minored in massage therapy.  Yes'}, TweetSentiment{title=':@ :@ :@ Super Angry .. and I don't even know why  [ ????? ??? ??????? ?? ?????? ????? ????? ????? ??? ]'}, TweetSentiment{title='"@1miletogo now that youve mentioned it'}, TweetSentiment{title='@aammoune  why aren't u here and y u not coming to yelenas?'}, TweetSentiment{title='@Lakers!! I love you guyssss! Those rings.. Mmmyeahh! Bling blingg'}, TweetSentiment{title='"&quot;do you like bread?&quot; &quot;do you have a beard?&quot; wtf'}, TweetSentiment{title='#yam Digitale Muurkrant is hiermee een variant van Netvibes? een Social SharePoint community in company? Social services to the max!'}, TweetSentiment{title='@1Omarion &quot;grab a bite&quot; lol its breakfast time. stay home &amp; make something'}, TweetSentiment{title='#jonaswebcast hilarious'}, TweetSentiment{title='@_Schiavonn_ you have a good one!'}, TweetSentiment{title='&quot;The old saying is true:'Breaking up is hard to do.' But breaking down is easy to do...&quot; ... and 1318288 more bytes
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
    [TweetSentiment{title='#work won't be home till late tonight'}, TweetSentiment{title='@Abbybn u sick????   something is going around and I got it too'}, TweetSentiment{title='::::So now i have to wait around til after for uploaded vids to see Rob &amp; the New Moon tease  FRIG! me = NO patience lol'}, TweetSentiment{title='i wish i could sleep in like everyone else.'}, TweetSentiment{title='@2kutekreations Oh no I missed itttttttttttttttttttt   I am sickkkkk and slow today   Sorryyyy! I'm sure you were fabluous though!'}, TweetSentiment{title='the weather has been really bad. Bad enought to cut out our cable.'}, TweetSentiment{title='? up early again today.... B is still not feeling like herself'}, TweetSentiment{title='. home from temecula. ergh i wanted to go to the party  i want to get drunk hahaha lol'}, TweetSentiment{title='#inaperfectworld i'd be going to the jb's tour kickoff concert tomorrow'}, TweetSentiment{title='@acadnurul daaappppppp nyee. You made me want to fly home right now knurl~~  Oh come July quickk.'}, TweetSentiment{title='@aherne148 omg torrential rain forecast for west wales pm today'}, TweetSentiment{title='- another dbl shift tomorrow.. on Father's day  which means: minimal time with gpa. the illest man ever :['}, TweetSentiment{title='...first time away from my gorgeous girl....HATING it'}, TweetSentiment{title='@ work not one soul is in here....so sad  wanna dance to highway to the danger zone...hehe'}, TweetSentiment{title='@_Mintage  that sucks so yourve been to doctors?'}, TweetSentiment{title='*Really* wish I was still on vacation'}, TweetSentiment{title='bcd's closed! i guess nodaji it is.'}, TweetSentiment{title='; worst day ever'}, TweetSentiment{title='#geekcamp multitasking trying to test Internet connection to see if can do live demo later - but looks like no'}, TweetSentiment{title='#itsucks that it's Thursday and not Friday..'}, TweetSentiment{title='#inaperfectworld final exams wouldn't exist and i would be sleeping very confy right now!'}, TweetSentiment{title='myspace isn't working'}, TweetSentiment{title='&quot;Hey&quot; was on Music Choice! But they spelled @mitchelmusso's name wrong...  SUCH a good song tho!!'}, TweetSentiment{title='" After turning my fan club into a religion'}, TweetSentiment{title='"@427 was it random or during a friends cut? And did they say why? Also'}, TweetSentiment{title='@akaTrent don't you wanna know what i'm doing at every second of the day?  haha'}, TweetSentiment{title='".@paul_steele Phew! Just in time'}, TweetSentiment{title='@___hotperlita y was it funny to see me on tv'}, TweetSentiment{title='..suffering from 'noASSetol''}, TweetSentiment{title='@AcePower  is there no chance to pay your school by installments or after graduation? scholarship? credit?'}, TweetSentiment{title='wishes more of my friends were on twitter! GOSH.'}, TweetSentiment{title='Miss my love Jamie'}, TweetSentiment{title='" jb is in toronto'}, TweetSentiment{title='@818Princess I told u I had the money a while a ago sweet thing! I totally would have come give it 2 u but I don't invite me over anymore'}, TweetSentiment{title='#inaperfectworld Nadal would be fit for Wimbledon'}, TweetSentiment{title='&quot;I'm In Driver's Ed Get Me Out of Here&quot; all week'}, TweetSentiment{title='";; ugh'}, TweetSentiment{title='"   let's have a moment of silence for our childhood homes... sadly'}, TweetSentiment{title='"@_kimcaldwell i just saw ur message 2 me! yes popstarts u had long beautiful hair'}, TweetSentiment{title='skool holidays are over and skool sux but we do have a cool new sports teacher lol he looks like the guy who does The Evolution of Dance'}, TweetSentiment{title='"#goodsex~has been gone'}, TweetSentiment{title='#woofwednesday I want a puppyyyyyyyyyyyyyyyyy'}, TweetSentiment{title='*sigh* I could sit around at home'}, TweetSentiment{title='@6flix havent gotten instructions?...did i miss it some how?'}, TweetSentiment{title='; MY GOSH. This blocked nose is killing me'}, TweetSentiment{title='@adeezyy yes i'm ok just eh lonely!  i miss school.'}, TweetSentiment{title='"#eu09 Shit the fucking bed'}, TweetSentiment{title='@___Annabel___ i caved  bought the most F-ing  disgusting smokes... hopefully that will help in the el quito jobo.'}, TweetSentiment{title='my girl cant come over today. Gonna have2 wait 2 see her until 2morrow. Hopefully shes not busy. Gonna get 2 hardly see her next week.'}, TweetSentiment{title='I just saw a squished kitty outside my gate....I feel sad...'}, TweetSentiment{title='@5forfighting77 where did you see Addekk crossover talk?'}, TweetSentiment{title='*peeks in* Ugh. Twitter after hours is sad and borderline perverted'}, TweetSentiment{title='just busted my head on my friends windshield. i hurt.'}, TweetSentiment{title='another day of running!! ......Today is fresh to death Friday (everyday is fresh to death Friday 4 me) Ha Ha lol!!!!'}, TweetSentiment{title='@abatko Tag is #vanchangecamp ... I keep forgetting to put it on mine'}, TweetSentiment{title='@_Bootsie_ y am I selfish now  *and to fink I'm bussin my ass to try hook u up* never appreciate nefing.. U seee u yeh.. Smdh'}, TweetSentiment{title='" I'm really annoyed about this telstra bill'}, TweetSentiment{title='For a minute there I was all happy thinking it was THIS Tuesday we finished.'}, TweetSentiment{title='" down'}, TweetSentiment{title='"@Abcmsaj I just saw it on Dave'}, TweetSentiment{title='@6morningnews  Got up this morning specifically to see the shuttle (from front yard)      Morning not a total loss thanks to you guys.'}, TweetSentiment{title='@___Maira___ Now I'm totally embarrassed &amp; am worried that he thinks I'm a psycho. I have no doubt that I will never hear from him again.'}, TweetSentiment{title='@700stories ha! i did that on purpose... no not really'}, TweetSentiment{title='#firstsongoftheday Bob Seger &quot;Turn the Page&quot;. Early morn bus driver cranking classic rock.  Hate to leave SF &amp; my sis'}, TweetSentiment{title='@_sputnik but weight is so much easier to maintain than your skin!'}, TweetSentiment{title='@aarkid_chris Well never had stay creased chino's myself but love my proper jeans...hate Lycra in denims'}, TweetSentiment{title='@abigailcrook Having sandwiches nearly every day really does suck  so we tend to go out on Friday lunch breaks to relax in the pub :p'}, TweetSentiment{title='"#Giro stage 2 preview'}, TweetSentiment{title='"-- I won't feel guilty and like I should be revising stuff after today'}, TweetSentiment{title='"&quot;There's a shadow hanging over me&quot;'}, TweetSentiment{title='@16_MileyCyrus yeah im getting my tickets on Friday i hope i get some'}, TweetSentiment{title='*yawn* omg! super super tired! but not sleepy at all     no sleep last night hmm no sleep tonight???? boo!'}, TweetSentiment{title='sad face !'}, TweetSentiment{title='I think people get out of bed in the mornings wondering how they can piss me off.'}, TweetSentiment{title='"@__mares__ I'm doing OK - think Dexy gave me his gastro tho'}, TweetSentiment{title='@0mGiiTzRee THANKS GIRLIE I LOVE YO MAIN PIC TOO BUT I CANT SEE UR BACKGROUND PIC'}, TweetSentiment{title='&quot;And now my mom and her friend are drinking.. Greatt..&quot;'}, TweetSentiment{title='"@AbbieandBrian oh i've got that book! will's brother gave it to me i think'}, TweetSentiment{title='"@1019MIXChicago I love going to the Cell'}, TweetSentiment{title='@ailinmcc ever since I left HR house I feel sick as fuck! Really tired and shivering like a motherfucker'}, TweetSentiment{title='"#hangover had one on thursday'}, TweetSentiment{title='@ AcidRainDropz I'm soooooop bored I wish my girlfriend were here'}, TweetSentiment{title='@10TheDoctor10 Take good care of yourself hun. You've got us very worried at present'}, TweetSentiment{title='"#inaperfectworld I'd be with you...  but unfortunately'}, TweetSentiment{title='Run's family makes me happy. Thats a frown because it expresses my emotion better.'}, TweetSentiment{title='@ABIBAN awwwww! Go forth and SHAG!!! Omg. It's 2am. I have to be up at 7'}, TweetSentiment{title='"@_justmitch_ nah'}, TweetSentiment{title='my dad just took off his shoes and his feet smell. eeeeeeeew'}, TweetSentiment{title='(: â™¥ â™¥ ...love is not bad but it's just a little difficult to understand  ...live isn't so easy!!! â™¥ â™¥'}, TweetSentiment{title='*sniff* May have to hold off sanding wood until the outside world is less pollen based'}, TweetSentiment{title='@AaronMShaffer I was actually really into a Cavs vs Lakers matchup. LEBRON'}, TweetSentiment{title='i have to start studying for finals'}, TweetSentiment{title='"#WeAreStupid what worries me most'}, TweetSentiment{title='#Pleasestop (Twitter) Playing hide and seek with my profile pic. Where the hell is my picture???'}, TweetSentiment{title='@4_idiots yep i can vouch for that !'}, TweetSentiment{title='(--- Checkn myself in Twitter rehab.'}, TweetSentiment{title='"@_Lauren_Mallory Yes *pouts* I've been resting so much I'm bored! *giggles softly* I still feel kind of beat though'}, TweetSentiment{title='woke up early to wash and dry my tie dye shirt...the stupid thing is still wet. =[ FML.'}, TweetSentiment{title='this night shouldn't end'}, TweetSentiment{title='*Totally behind on everything* OH SNAP I WANT TO GO TO COMIC CON'}, TweetSentiment{title='#inaperfectworld i'd be able to start an MMA career without trying so hard! yeahhh not in RI'}, TweetSentiment{title='*sigh* I'm so fucking worried about my bby'}, TweetSentiment{title='"- fuck'}, TweetSentiment{title='&quot;My head feels like there's a Frenchman living in it&quot;  ow ow ow'}, TweetSentiment{title='"    awhhe man.... I'm completely useless rt now. Funny'}, TweetSentiment{title='more exams! noooooooooo!!'}, TweetSentiment{title='Tony is getting three boxes. UGH I just don't know how to make it all fit into one  STUPID LITTE BOXES'}, TweetSentiment{title='... I can't take anymore'}, TweetSentiment{title='@A7X_Bat_Angel well that is just odd. wait ummmmm i dunno what to do  um sign in as guest for now i guess'}, TweetSentiment{title='#ASOT400 Odd transition... anyone know when the video should be up?'}, TweetSentiment{title='#smx no free wifi apparently  thats baaaaad #smxlondon'}, TweetSentiment{title='(@lisa365) NTS: do not floss the morning before a dentist appt. I has a hurt'}, TweetSentiment{title='#Aion beta starts @ 3AM for WA players... I don't know if i can force myself to stay awake that long  MUST DO IT FOR MMOEY GOODNESS.'}, TweetSentiment{title='*Is still in the process of making some tough choices...*'}, TweetSentiment{title='"@_callmeCourt yes'}, TweetSentiment{title='#asot400 -5 mins left  . Join the fb group @ http://bit.ly/asot400'}, TweetSentiment{title='@afylayouts i know  im deleting them when they do lol'}, TweetSentiment{title='nobody loves me... Everybody hates me .. I'm going to go eat worms now'}, TweetSentiment{title='".@mooseharris oh the shame ... I'm killing myself now. Gin-fingers'}, TweetSentiment{title='@3ree6ixty COZ im underage!  well that should've worked ill give it to you later then. Damn it i really wanted to see you again!!!'}, TweetSentiment{title='@_sophielouise i wish you were'}, TweetSentiment{title='@abraham awwwwww i'm jealous...  i wanna wanna wanna shelf full of pogo'}, TweetSentiment{title='@211me UGH! After trying for awhile I got the form filled out and then it went offline   I think I should win out of frustration!'}, TweetSentiment{title='how the duck am i supposed to write 1500 art history words on something i can hardly see!'}, TweetSentiment{title='&quot;Bodies found from tragic Air France flight&quot; - http://www.timesonline.co.uk/tol/news/uk/article6444498.ece'}, TweetSentiment{title='" this guy thru at least $5 worth of change at this poor girl on stage'}, TweetSentiment{title='@Alainesinga Another video!!! Wowweeee... How come I don't get an invite  goo alaine!!'}, TweetSentiment{title='"@ MuEpAce09 hey'}, TweetSentiment{title='@ab_normal Shucks!  Sorry!  Sometimes I get wrapped up in FB and forget to check  how you doin'?'}, TweetSentiment{title='i might not walk and go to the dance. if you dont have all your books turn in you cant :'('}, TweetSentiment{title='"@abirtmo i've been well! how about you? ya i heard you guys were in town the day of your show'}, TweetSentiment{title='...ready to be confused all over again'}, TweetSentiment{title='times by like a million'}, TweetSentiment{title='"*tear* I'm watching Betty White on Chelsea Lately and she's so cute'}, TweetSentiment{title='&amp; in need of a bowl of pho tai. baaaaddddd'}, TweetSentiment{title=';( but in america I was like--a's and b's!!  Im sad.'}, TweetSentiment{title='@accionatasha then she left.'}, TweetSentiment{title='"@AlanCarr Shit'}, TweetSentiment{title='#RareBreeds farmer in pollution legal battle with @NationalTrust takes own life. Sad business all round  http://ur1.ca/4xy7'}, TweetSentiment{title='@_rValentine_ I've read it b4 and thought it was useful but then promptly 4got everything I read. I guess i have no commen sense'}, TweetSentiment{title='@_misslizzie_ None of them seem to  You can look here for Japan's holidays: http://bit.ly/GlVbi'}, TweetSentiment{title='@ bizoink want to but no dinero!'}, TweetSentiment{title='#myweakness is  the Dominos cheeseburst pizza....shit I want it now!'}, TweetSentiment{title='"@_ophelia Oh'}, TweetSentiment{title='i am soooo sad...at this point of night i'd be cuddling with jonathan like i did for 4 years'}, TweetSentiment{title='*sigh* still no Iphone OS 3.0 B / Unlock for Windows'}, TweetSentiment{title='** Aww My baby's Walking around the house Saying &quot; Mommy It's my birthday I'm 2.. ** Aww Shes So Big...  **'}, TweetSentiment{title='" it was announced that I am officially leaving work'}, TweetSentiment{title='...I tried a liitle afternoon meditation to clear my thoughts and fell asleep.'}, TweetSentiment{title='@__Kizzle Conan came back? Damn I hope he did well with ratings. I missed it.'}, TweetSentiment{title='@aisfornala Oh. So people laugh at me huh?  Your words are like an ice dagger to my heart etc etc ;)'}, TweetSentiment{title='Athens landmark Georgia Theater  on fire... http://bit.ly/EOL7s'}, TweetSentiment{title='"@2legittooquit i dont know! this movie is insane  im goodish'}, TweetSentiment{title='"&quot;You're asking me about the weather?&quot; &quot;I don't really like the rain'}, TweetSentiment{title='.. I always have to take a second and feel bad whenever I see an ad for a missing child...'}, TweetSentiment{title='....damages knee.....gets drunk.....makes it worse!!!!!'}, TweetSentiment{title='@8carl8 Bloody... yes! Didn't pick it up! Ashamed  used to be the Slusho! Masteress.'}, TweetSentiment{title='i wish u guys would stop fighting'}, TweetSentiment{title='"(must tell myself) I love my Job'}, TweetSentiment{title='@ starbucks studying turkish. Called in to work this morning cuz of my cough'}, TweetSentiment{title='#3turnoffwords out of gravy'}, TweetSentiment{title='"#barcampevn09 starts in 4'}, TweetSentiment{title='.. But definitely not this time.  At least she's smiling now.'}, TweetSentiment{title='&quot;I not need &quot; - http://twlol.com/tw/?v1-133326 #lol #ichc #cat /// still miss mine  dang it! :/'}, TweetSentiment{title='@AAmyHaanson I'd have to get one from Walmart. All the good deals on the internet aren't on prepaid.'}, TweetSentiment{title='bed time......................................................'}, TweetSentiment{title='...is doing a good job of procrastinating this morning.  I should be out delivering neighborhood newsletters but I really don't want to.'}, TweetSentiment{title='@__mares__ thats some traffic jam when the walkers are going faster than the cars i feel your pain. been there done that!!'}, TweetSentiment{title=': m actually supposed to go n study.. but... it seems like m GLUED to to the screen..'}, TweetSentiment{title='"@_idioteque Exams suck  I handed 1st piece of MRes work in on Fri. Not got anything to hand in until Sept now'}, TweetSentiment{title='@ work . . bored'}, TweetSentiment{title='*fingers crossed* hope youtube doesn't rip the audio off'}, TweetSentiment{title='I cant take the hurt...'}, TweetSentiment{title='@adamschoales ooo that one is kinda cool! I always wanted a (RED) shirt but the ones they have at the gap don't fit me right'}, TweetSentiment{title='#... and 1318631 more bytes
```

Code from [TrieDemo.java:381](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L381) executed in 428.18 seconds: 
```java
      HashMap<String, List<String>> map = new HashMap<>();
      map.put("pos", tweetsPositive.stream().limit(trainingSize).map(x -> x.getText()).collect(Collectors.toList()));
      map.put("neg", tweetsNegative.stream().limit(trainingSize).map(x -> x.getText()).collect(Collectors.toList()));
      return new ClassificationTree().setVerbose(System.out).categorizationTree(map, 16);
```
Returns: 
```
    com.simiacryptus.util.text.ClassificationTree$$Lambda$101/668849042@8807e25
```
Logging: 
```
    "lowfriday " -> {neg=6, pos=108}	{neg=4994, pos=4892}	-0.8919242639193679
     "owfriday @" -> {neg=2, pos=74}	{neg=4, pos=34}	-0.18191222092486695
      "rry" -> {neg=2, pos=3}	{neg=0, pos=71}	-0.052627870880562644
      " I" -> {neg=3, pos=5}	{neg=1, pos=29}	-0.3576522363906864
       " #followfr" -> {neg=1, pos=4}	{neg=0, pos=25}	-0.07034189437659943
     "#musicmond" -> {neg=1, pos=66}	{neg=4993, pos=4826}	-0.8938155273154073
      ""#musicmon" -> {neg=0, pos=12}	{neg=1, pos=54}	-0.03281356262279578
       "sicmonday " -> {neg=1, pos=48}	{neg=0, pos=6}	-0.05633890375868478
        "y i" -> {neg=1, pos=4}	{neg=0, pos=44}	-0.07498473589268265
      " thank you" -> {neg=1, pos=39}	{neg=4992, pos=4787}	-0.8963374378149302
       "k you for " -> {neg=1, pos=7}	{neg=0, pos=32}	-0.04884059649098124
       "#FollowFri" -> {neg=1, pos=38}	{neg=4991, pos=4749}	-0.89629425596523
        "owFriday @" -> {neg=0, pos=27}	{neg=1, pos=11}	-0.06975834691404273
         " a" -> {neg=1, pos=4}	{neg=0, pos=7}	-0.39154477006956323
        "#inaperfec" -> {neg=34, pos=0}	{neg=4957, pos=4749}	-0.8963209662907692
         "hanks for " -> {neg=9, pos=49}	{neg=4948, pos=4700}	-0.8972753349632021
          "ll" -> {neg=0, pos=27}	{neg=9, pos=22}	-0.46840354803035594
           "ad" -> {neg=5, pos=2}	{neg=4, pos=20}	-0.6822977388809565
            "ks for the" -> {neg=3, pos=7}	{neg=1, pos=13}	-0.4980837925192796
             "at" -> {neg=2, pos=3}	{neg=1, pos=4}	-0.841591183958428
             " Thanks fo" -> {neg=0, pos=7}	{neg=1, pos=6}	-0.2953443205604821
          "#dontyouha" -> {neg=25, pos=0}	{neg=4923, pos=4700}	-0.897163467740186
           " Thank you" -> {neg=2, pos=30}	{neg=4921, pos=4670}	-0.8973255840521792
            "ch" -> {neg=2, pos=3}	{neg=0, pos=27}	-0.17480479942921384
            "i have to " -> {neg=21, pos=0}	{neg=4900, pos=4670}	-0.8975350815696527
             " you are o" -> {neg=0, pos=18}	{neg=4900, pos=4652}	-0.8977729187374084
              ""#myweakne" -> {neg=1, pos=18}	{neg=4899, pos=4634}	-0.8980817455163332
               "g" -> {neg=1, pos=5}	{neg=0, pos=13}	-0.2725869080292687
               "gonna miss" -> {neg=15, pos=0}	{neg=4884, pos=4634}	-0.8980672050266924
                "ish I coul" -> {neg=18, pos=1}	{neg=4866, pos=4633}	-0.8982000686254402
                 " have " -> {neg=4, pos=1}	{neg=14, pos=0}	-0.20592599432733222
                 "1sweetwhir" -> {neg=0, pos=13}	{neg=4866, pos=4620}	-0.8982831463018185
                  "ish i coul" -> {neg=13, pos=0}	{neg=4853, pos=4620}	-0.8983301246547585
                   " Happy Bir" -> {neg=0, pos=12}	{neg=4853, pos=4608}	-0.8983855067717376
    
```

Code from [TrieDemo.java:387](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L387) executed in 0.41 seconds: 
```java
      return tweetsPositive.stream().skip(trainingSize).map(x->x.getText()).mapToDouble(str->{
          Map<String, Double> prob = rule.apply(str);
          System.out.println(String.format("%s -> %s", str, prob));
          return prob.getOrDefault("pos", 0.0) < 0.5 ? 0.0 : 1.0;
      }).average().getAsDouble();
```
Returns: 
```
    0.0651
```
Logging: 
```
    @_Yummi_ Hotel Party? Where? I wanna come! -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @_MikeNewton_ youre too funny! -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "@_emp Nah -> {neg=0.5129478913434098, pos=0.4870521086565902}
    &lt;3 working out! -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @4nT0 sciau bello! -> {neg=0.5129478913434098, pos=0.4870521086565902}
    (@mindofdc) Note to self: You look nice today -> {neg=0.5129478913434098, pos=0.4870521086565902}
    - Has Just Got Up &amp;+ Is Listening To Good Charlotte -> {neg=0.5129478913434098, pos=0.4870521086565902}
    - Trying to figure out this twitter thingie -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "(@brydiekennedy) going to download some apps for my iPod getting sick of all the games -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @Ace_Diamond Are you going to live-tweet the wedding too?! -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @_lakergurl i added you too.. you should so buy my album on iTunes -> {neg=0.5129478913434098, pos=0.4870521086565902}
    &quot;they're homorific! just homorific!&quot; hahahah -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "@_laertesgirl http://twitpic.com/4e748 - Actually -> {neg=0.5129478913434098, pos=0.4870521086565902}
    woots gnite my love ^.^ ty for checkin i love u -> {neg=0.5129478913434098, pos=0.4870521086565902}
    they r playing @jason_mraz at TGIF -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @_peachykeen Why the spending ban? I'm supposed to be on a ban too...it's too hard with all the makeup goodies -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @ irene's house. having a sleepover! -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "&quot;you're worth losing self esteem -> {neg=0.5129478913434098, pos=0.4870521086565902}
    folllow me -> {neg=0.5129478913434098, pos=0.4870521086565902}
    #bb11 Big Brother airs in America on July 9th. Dang you people in the UK are so lucky...jealous -> {neg=0.5129478913434098, pos=0.4870521086565902}
    #followfriday @sandramp @julia_briggs and @acmackie she's new -> {neg=0.0, pos=1.0}
    "@achillesmama Well -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "&lt;-- Is tryna do the thing -> {neg=0.5129478913434098, pos=0.4870521086565902}
    &quot;Since You Been Gone&quot; -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @abcdefgKEMBALI makasi ya uda follow twitter ku -> {neg=0.5129478913434098, pos=0.4870521086565902}
    *yawwwwwwwn* good morning! -> {neg=0.5129478913434098, pos=0.4870521086565902}
    *finally* packing for bermuda and sipping vino. wonder which one is going faster? -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @6flix Hope spelling did not count -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "; listening to music -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @aceybongos hello graham =] has SW battlefront 3 been confrimed for 360. thanks and yess if your wondering i would kill you off online -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "@AClockworkToad Glad to hear it -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @acryfromthesoul Niiiiice.... I missed it the 1st time.  I love this melding of filthy minds. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "@Adamentcause I have been doing Fabulous -> {neg=0.5129478913434098, pos=0.4870521086565902}
    : Watching Britney's Circus Tour in youtube! Lovely! I hope she passes by the Philippines! -> {neg=0.5129478913434098, pos=0.4870521086565902}
    ok twitter world we r bacK online!!!! -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @ajriley i love youu -> {neg=0.5129478913434098, pos=0.4870521086565902}
    #musicmonday It's an Asanee &amp; Wasan day  http://bit.ly/WRUqv -> {neg=0.0, pos=1.0}
    @aalaap  wish i could create web apps like you dude! -> {neg=1.0, pos=0.0}
    @Aisleyne1 You'll be fine -> {neg=0.5129478913434098, pos=0.4870521086565902}
    SO FAR SO GOOD! -> {neg=0.5129478913434098, pos=0.4870521086565902}
    Explore twitter -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @ajrafael congrats on the feature  loved that song. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    ..but I WILL ALWAYS be a JB fan -> {neg=0.5129478913434098, pos=0.4870521086565902}
    - isn't beer just sparkiing water with alcohol in it? -> {neg=0.5129478913434098, pos=0.4870521086565902}
    ".@davea  I'm much the same as you lot -> {neg=0.5129478913434098, pos=0.4870521086565902}
    steak and shrimp i'm excited -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "@__Alexx Ooh thanks -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @_Momos the best book in the world ... is the book filled with blank pages only -> {neg=0.5129478913434098, pos=0.4870521086565902}
    :: reconnected with the love of my life today and it was amazing and wonderful and healing. Thank you my sweet. -> {neg=0.0, pos=1.0}
    @abs1399 oh stop it!! I think you look super cute -> {neg=0.5129478913434098, pos=0.4870521086565902}
    today is a beautiful day.... love u all &lt;3 -> {neg=0.5129478913434098, pos=0.4870521086565902}
    .......... and the fix is great.... now going to work on your Visa -> {neg=0.5129478913434098, pos=0.4870521086565902}
    (@1cincymom) @swaller Thanks! Note to self: Get apps. Thought I was cool when I finally got twitterberry. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    #dating #lesbian Sexy female wants to know what it feels like to be with a woman   (Central .. http://bit.ly/1785uG -> {neg=0.5129478913434098, pos=0.4870521086565902}
    &lt;3 Justin. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    #underrated Henry Hatsworth in the Puzzling Adventure. A quality DS game everyone with a DS should try  (think Mario + RockmanX) -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "@_Geno LOL..no pain -> {neg=0.5129478913434098, pos=0.4870521086565902}
    &quot;Intuition becomes increasingly valuable in the new information society precisely because there is so much data.â€?- John Naisbitt  Use it! -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "(id) canggeeh -> {neg=0.5129478913434098, pos=0.4870521086565902}
    :O forgot friday night cranks is live on stickam in 1hour :O WOOP WOOP!! cant wait its going to be soo funny -> {neg=0.5129478913434098, pos=0.4870521086565902}
    - certification test #2 at 8:30 this morning. Feeling much better about this one today -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @_Esme_Cullen_ See I knew you would love it! Isn't it beautiful and perfect! It took us all night to get it done. But yay! You love it! -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @aaucl  I'm sooo full of Beau's right now  It make cubicle life a bit better ))) -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @__Anthony Shows over now... -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "#haveyouever done a free conference call with @whistletree? If not -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @_memoria we'll have plenty of time to learn the new songs. Haha that's so cute Jill. I mosdef told my parents about the song already. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    -- . You aint gotta apologize. I know I'm not ugly. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    #andyclemmensen #shaundiviney #bradiewebb #andyclemmensen #shaundiviney #bradiewebb #andyclemmensen #shaundiviney #bradiewebb -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @ HollywoodP FOLLOW MEEEE -> {neg=0.5129478913434098, pos=0.4870521086565902}
    &quot;it was so laid-back and I got to interact with everyone there.&quot; Gahhhhh that would be so incredible! All those lucky people!! Haha. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "@aaronaiken nps  loads of skins for it too ;) check out those cool skins by SLoB -> {neg=0.5129478913434098, pos=0.4870521086565902}
    ? ?????  ???????! @2BAD http://yfrog.us/0dpg9z ???????????? ? ???? ???????? @jeangreen -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @ laurenwampole happy birthday!!! -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "@_adri aww what have you been sick with? yeah i've been alright just need a break from everything i guess -> {neg=0.5129478913434098, pos=0.4870521086565902}
    ...feels like im going to lose my mind! -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @_Corr My fav is my background pic -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "@_HONEYMONSTER @grcrssl -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @adamconnor 2x4's and duct tape? -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "#spymaster is freakin brilliant- great job -> {neg=0.5129478913434098, pos=0.4870521086565902}
    ...Did i mention @brandyinboise for #followfriday ?? SHE ROCKS! -> {neg=0.2, pos=0.8}
    @ADC711 craziness going down - will give you the down-lo later. spent the weekend in taiwan w/ family... ben loved shilin night market!! -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "@__dashboard usc? what course? yeah -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "@ajaymohanreddy That will be decided in hindsight -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @akrabat very pretty pics! Thanks for sharing -> {neg=0.0, pos=1.0}
    "@Abandonrock Love the Abandon EP -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @1sweetwhirl U R A WHOOT  ...so can u beat me...will i be ur teacher on Twitter ??? -> {neg=0.0, pos=1.0}
    @aafreen I guess it's pun InTenDed. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "- goingg to sleep  school -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "#myweakness Trey Songz -> {neg=0.16666666666666666, pos=0.8333333333333334}
    @ all= thank you for following me -> {neg=0.125, pos=0.875}
    @abacab1975 I do dear! It was very nice too!! -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "@ABBSound Ð´Ð°. Ð¼Ð¾Ð¹ ÐºÐ°Ðº Ñ€Ð°Ð· Ð¿Ð¾Ð¶Ñ€Ð°Ð» -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @abigaill yeah i heard at west palm last summer -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @aianna21 I can understand that.  But it's good to be rational about it.  And it'll eventually settle into a less obsessive feeling. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    #shaunjumpnow#shaunjumpnow#shaunjumpnow#shaunjumpnow #shaunjumpnow#shaunjumpnow#shaunjumpnow#shaunjumpnow #shaunjumpnow#shaunjumpnow -> {neg=0.5129478913434098, pos=0.4870521086565902}
    ": i owe laura 4000 dollars -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "... so just watched Terminator 1 -> {neg=0.5129478913434098, pos=0.4870521086565902}
    &quot;My Everything&quot; by Cathy Nguyen and Randolph Permejo (Youtube peeps) has been stuck in my head since yesterday! love that song. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "? ???? ????? ????? ?????? -> {neg=0.5129478913434098, pos=0.4870521086565902}
    eatin cereal -> {neg=0.5129478913434098, pos=0.4870521086565902}
    #FollowFriday 2 @RachelKrishna @Jucarvalhoo @tweetss @victoranselme @sergiowpf -> {neg=0.0, pos=1.0}
    @5lackr nice summer in the city -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @1nOnlyMercy lol yeah really early -> {neg=0.5129478913434098, pos=0.4870521086565902}
    * brokeback mountain is an awesome movie.. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @30SECONDSTOMARS HAPPY ECHELON DAY!!  I'm so proud to be ECHELON and I'll be an echelon for ever and ever!! I promise you guys!! -> {neg=0.5129478913434098, pos=0.4870521086565902}
    ...that is all -> {neg=0.5129478913434098, pos=0.4870521086565902}
    ... @dustmine321 So we switched to BE Unlimited. Honestly it's so much better. 25 Mbit! -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "&quot;yeah -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @ spookychan Wolfsheim- Care for you ? http://blip.fm/~78qre your wedding song?? It's supposed to be ours too if we ever get married.... -> {neg=0.5129478913434098, pos=0.4870521086565902}
    .. damn .. double the amount of time on the road as normal .. pffff .. but saw nice things -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "&quot;History remembers only Kings -> {neg=0.5129478913434098, pos=0.4870521086565902}
    I just got to my 500 tweet...... Do i get a prize? *sidebar* Cassidy...... Your NASTY! Why i gots to hear about your wide peen for? Smh -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @29Woody definitely -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @abhimehr I know  Only thing Fed hasn't tried yet. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    ... and here's the china-taxi!  #yummy -> {neg=0.5129478913434098, pos=0.4870521086565902}
    *SPECIAL DELIVERY FOR* @LainaxBaina We were sent by @TropicalBlend she wants to give you a sexy grind -> {neg=0.5129478913434098, pos=0.4870521086565902}
    ---- jus  came back from lavish lounge... best tyme -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "&quot;Sean Kingston - Fire Burning&quot; rocks ;) Go on -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @absolutelysmall http://twitpic.com/55wpg - nice .. &quot;pillows&quot; [?] ï¿½ nice stuff.  #artistic #word #art #word -> {neg=0.5129478913434098, pos=0.4870521086565902}
    *tigger  and runs over to @roxieravenclaw and supersquishies her knees and giggles because hes supposed to be in bed and hes not* -> {neg=0.5129478913434098, pos=0.4870521086565902}
    &quot;get down in this rage with me...it takes 2 to dance baby&quot; listening to @thetalkabout on myspace -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @ableandgame That's totally spacey! I dig it -> {neg=0.5129478913434098, pos=0.4870521086565902}
    enjoying my last days of summer &gt;sigh&lt; -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "*client* Flirtomatic initial US stats -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "@AdCharlie You have some amazing photos! I love taking pictures too. I'm not a professional -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "@abhishek198 dude -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "@_E13_ uh huh..... your story -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "#jonaskevin i 4got 2 mention that im drinking from my camp rock cup from my easter egg -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "&quot;aint about how fast i get there -> {neg=0.5129478913434098, pos=0.4870521086565902}
    #iremember FOLLOW ME -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "@8073N to me -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @ BeardBurk: Their being optimistic -> {neg=0.5129478913434098, pos=0.4870521086565902}
    &quot;only you can let others destroy you &quot; -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "(cont) &amp; to @anabagasao -> {neg=0.5129478913434098, pos=0.4870521086565902}
    &quot;i learned from you&quot; -@mileycyrus &amp; her dadddyy is an amazing song -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @adiblasi ill be there -> {neg=0.5129478913434098, pos=0.4870521086565902}
    &quot;It is the calling from everyone of us to heal the broken world.&quot; -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "*feedback* Bombs burning bright! *pedal effect* Rockets' Red Glare! *distorted -> {neg=0.5129478913434098, pos=0.4870521086565902}
    ".@dahowlett Not really -> {neg=0.5129478913434098, pos=0.4870521086565902}
    #nwf @starfocus went out this am to load my car+ a black bear was waiting 4 me. Asked him to help me with my suitcase but he ran off -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "@_Jessicaaaaaa You're welcome. Anyway -> {neg=0.5129478913434098, pos=0.4870521086565902}
    *laughing uncontrollably* You all rock! Thanks for all the tweets!  *sings in a shrill voice* It's my party and I'll cry if I want to!* -> {neg=0.0, pos=1.0}
    @__Slops__  ha i know - wrecked! -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @_Yvette_ hey you should follow um peter facinelli  it's r... and 1177105 more bytes
```

Code from [TrieDemo.java:394](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L394) executed in 0.31 seconds: 
```java
      return tweetsNegative.stream().skip(trainingSize).map(x->x.getText()).mapToDouble(str->{
          Map<String, Double> prob = rule.apply(str);
          System.out.println(String.format("%s -> %s", str, prob));
          return prob.getOrDefault("neg", 0.0) < 0.5 ? 0.0 : 1.0;
      }).average().getAsDouble();
```
Returns: 
```
    0.9953
```
Logging: 
```
    ...went to a lot of places today. I miss going to school. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    #xboxe3 I was hoping an XNA announcement might've been shoehorned into here... -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "#shoutout2 my mommy -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "!Identica currently has rather severe problems concerning performance -> {neg=0.5129478913434098, pos=0.4870521086565902}
    ...Im on that Jack Nicholson from &quot;As Good As It Gets&quot;-type ish. OCD is NOTHING NICE to have. True Story. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @3sixty5days aww  me monday morning Chinese paper 1&amp;2 double crappy -> {neg=0.5129478913434098, pos=0.4870521086565902}
    No more witty #Squarespace Tweets... -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @ school in the computer room just a little bored -> {neg=0.5129478913434098, pos=0.4870521086565902}
    #iranelection the audio tape CNN just played sounded like that scene in Schindler's List when the Krakow Ghetto was liquidated. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "&quot;I give it my heart. My determination -> {neg=0.5129478913434098, pos=0.4870521086565902}
    goodbye hair length -> {neg=0.5129478913434098, pos=0.4870521086565902}
    &quot;Dude u have a naked pic of Jennifer Aniston on ur site... im not that kinda girl. sorry  &quot; i just received this interesting shout. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @abs1399  I see you woke up for that  meh. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    ...ehh im beat.  my stinky lil man just got back frm fishing. hes bein the biggest lil grouch ever. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @ o my God this class is tooooo long. I still have an hour left and my back hurts -> {neg=0.5129478913434098, pos=0.4870521086565902}
    hate public transport stood waiting over half an hour for a bus which didnt show up luckily i got a lift to work -> {neg=0.5129478913434098, pos=0.4870521086565902}
    looks like im not going anywhere this summer! being poor sucks! -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @_serb_ man prison break got baaaaaaad fast this year -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @_santi how many times do I have to tell you its the other way around...no one listens to poor tj -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "@_GERM damn it -> {neg=0.5129478913434098, pos=0.4870521086565902}
    ...so much to do today - hope it rains so that my garden gets some water - my hose is broken and been using a watering can  ...not fun! -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @AdamParnell Hopefully the links to new articles about Bev's new album will cheer you up! Just posted them. Hope you feel better!  xx -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "&quot;True love asks nothing to return&quot; ~~~&gt; ( ( ( poor Yuan da Ying -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "...wait -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @2Mbs: to triste -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @achallis  It's a bad day -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @_IANNE swaggg!  dnt make me feel sad than im already am. Miss u already! -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "@agcruc Girl -> {neg=0.5129478913434098, pos=0.4870521086565902}
    ...and then bitches whisper in the bathroom  is this high school? -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @_EmilyYoung oh i'm really sorry to hear that -> {neg=0.5129478913434098, pos=0.4870521086565902}
    :O last Saturday in England  one week till LA wooooooooo -> {neg=0.5129478913434098, pos=0.4870521086565902}
    ...insomnia sucks! Seriously! -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "&quot;There is only one sin -> {neg=0.5129478913434098, pos=0.4870521086565902}
    mayfield psychiatric hospital. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    #haveyouever tried mixing in peanut butter into chocolate milk?... Don't. Don't you ever do such a horrible thing. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    #BTS is fallin!!   #BTS #BTS #BTS #BTS #BTS #BTS #BTS #BTS #BTS #BTS #BTS #BTS #BTS #BTS #BTS #BTS #BTS #BTS #BTS #BTS #BTS #BTS #BTS -> {neg=0.5129478913434098, pos=0.4870521086565902}
    ..and my phone is dead -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @_georgie not even in bed yet? it's 5am and i have to get out of bed now... -> {neg=1.0, pos=0.0}
    I just got crop dusted. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "@Ablanchetjr come over boo whenever you can and we can nap together -> {neg=0.5129478913434098, pos=0.4870521086565902}
    ....work...realllllllllly?? -> {neg=0.5129478913434098, pos=0.4870521086565902}
    " My turtle food! Its starving. ;( Im sorry -> {neg=0.5129478913434098, pos=0.4870521086565902}
    *cough cough hack hack -> {neg=0.5129478913434098, pos=0.4870521086565902}
    *sigh* SARS. U make me want to cry -> {neg=0.5129478913434098, pos=0.4870521086565902}
    RIP Allan King. Canada's film landscape is a lot flatter today. http://bit.ly/Od4IY -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "&quot;Exams -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "@AFairCoop our chickens have completely obliterated the grass in their enclosure -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @addieking Energy drinks never work for me. I don't know what I have to resort to. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    #dontyouhate how ugly T.i.'s wife is? &amp; how the fk did she get a show? that's BET for ya -> {neg=1.0, pos=0.0}
    why oh why... a spot... 2 days before i have a date *cries* -> {neg=0.5129478913434098, pos=0.4870521086565902}
    #iremember the Oregon trail ... I miss that -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @abramsandbettes i sent one abrams  and it was all surrounded with weather -> {neg=0.5129478913434098, pos=0.4870521086565902}
    " - Well i wrote a missive post about last night and all the things that happened -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "&quot;Sorry but it appears you have already voted for this user on this wall.&quot; How would you know that -> {neg=0.5129478913434098, pos=0.4870521086565902}
    i wish someone was here to watch this movie with me. sucks watching titanic alone. haha depresing! -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "&quot;Love -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "&quot;On popular music&quot; by T.W.Adorno is probably the most difficult reading ever prescribed -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @acontradiction I'm having the same problem as you then. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "...everyone heading to Rottingdean beach -> {neg=0.5129478913434098, pos=0.4870521086565902}
    " i love u bro. thanks for all the memories:saran wrap -> {neg=0.0, pos=1.0}
    &quot;let's go! Don't wait!&quot; I heart blink 182 spewing another weekend us gone  I love music -> {neg=0.5129478913434098, pos=0.4870521086565902}
    .@nyrbclassics My new paperback of J.F. Powers' short stories (pub. by you) is already losing pages.  Can you suggest a NYC (re)binder? -> {neg=0.5129478913434098, pos=0.4870521086565902}
    Maria is a loser. She sucks at point break -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "@30SECONDSTOMARS I'd like to chat -> {neg=0.5129478913434098, pos=0.4870521086565902}
    ... But i miss my shannon.  i wuvvv her to death -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "@Adjectiveless LOL -> {neg=0.5129478913434098, pos=0.4870521086565902}
    ....so my camera is broken  i tryed to make a video for youtube and the motherfucker broke so i cant even take pics either -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "@30STMluva it's so sick -> {neg=0.5129478913434098, pos=0.4870521086565902}
    " today is the day!! Last school day!  i have my costume on and ready -> {neg=0.5129478913434098, pos=0.4870521086565902}
    I need you more than you need me...and this hurts Paige. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @4point0show awwww... don't be hurt was just teasin ya!!  I don't know how to do that!!! Show-off lol -> {neg=0.5129478913434098, pos=0.4870521086565902}
    re: twitter - yet another US centric app which fails internationally! actual practical features (sms updates) a no go suomessa... -> {neg=0.5129478913434098, pos=0.4870521086565902}
    #lr - May have to say goodbye : hey guys.  i might have to get rid of my disco soon  there may be too m.. http://tinyurl.com/nen598 -> {neg=0.5129478913434098, pos=0.4870521086565902}
    ...is it friday yet?  just passing time til then -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @jonasbrothers Why is your YouTube account suspended? lol Single Ladies dance? I NEED TO WATCH IT AGAIN! -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @_Alectrona_ I bet you smiling looks wonderful! Im fine thanks..at work and its grey clouds -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @_hayles thinking of a celeb/hollywood fancy dress theme. want a marquee but its so expensive -> {neg=0.5129478913434098, pos=0.4870521086565902}
    &amp; more I seemed to have missed following  &gt;&gt;@teamwinnipeg @cherrybocks @doroftei @rfengineer @critters62 -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @aiimee_x its not working -> {neg=0.5129478913434098, pos=0.4870521086565902}
    my tummy really hurts now oh noes -> {neg=0.5129478913434098, pos=0.4870521086565902}
    I'm not very well! ): -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @_rriz they mus! it hasn't stopped in ages! 2 bad that we hate them -> {neg=0.5129478913434098, pos=0.4870521086565902}
    too much french for my mentality :S -> {neg=0.5129478913434098, pos=0.4870521086565902}
    *pours out a 40 for Steve Nash* Pity that Phoenix is starting to become a retirement home for Hall of Famers past their prime -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "#r2i Digital Transition -> {neg=0.5129478913434098, pos=0.4870521086565902}
    ...My Life is like a Card House...Everything is changing... -> {neg=0.5129478913434098, pos=0.4870521086565902}
    ".@FakerStephanieR aw -> {neg=0.5129478913434098, pos=0.4870521086565902}
    ".. chugged 2 imperial pints for dinner &amp; 1 for dessert. i tried the fish cakes. Good and tasty -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "#inaperfectworld they'd sell Fanta LimÃ³n in Guate!  ....hahaha (quÃ© engase -> {neg=1.0, pos=0.0}
    "#finaltweet Guys -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "@adamisarockstar I realize some people actually LIKE Want -> {neg=0.5129478913434098, pos=0.4870521086565902}
    " grrrrr. why can't they just go away for like 2 days! haha. this is stupid. i have to pack for mexico -> {neg=1.0, pos=0.0}
    tired to the highest level -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @absolutfeli packing and moving -> {neg=0.5129478913434098, pos=0.4870521086565902}
    got a headache -> {neg=0.5129478913434098, pos=0.4870521086565902}
    &quot;We will have 90 mins of maintenance &quot; ohhh -> {neg=0.5129478913434098, pos=0.4870521086565902}
    #dontyouhate thinkin' about the days when you WERE Boo'd up -> {neg=1.0, pos=0.0}
    im gonna miss all of the peeps in the play smgt!!! -> {neg=1.0, pos=0.0}
    *bounces around* so I turn my head to the easttttt... oh am so white its painful -> {neg=0.5129478913434098, pos=0.4870521086565902}
    Not happy about having to drive mom's car for the next couple of days till my car gets worked on. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    #BB10 I wanna learn Ole Bamboo.. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @akirarhymes and yet you dont share -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "@30SECONDSTOMARS ï¿½ï¿½ why not today -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "(no-one probably cares about it -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @abram110 were do you live? i know .....rain  = -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "@5LiveF1 so what's up with rosberg -> {neg=0.5129478913434098, pos=0.4870521086565902}
    no encontre a pololo en Mr Twitter -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "#liesboystell Your the only one -> {neg=0.5129478913434098, pos=0.4870521086565902}
    nyaaaaaa christina is gointa die of boredom  and she still wants to go to newry. &gt;.&lt; and dan is gayyyyyyy &gt;.&gt; -> {neg=0.5129478913434098, pos=0.4870521086565902}
    " she's leaving on a jetplane -> {neg=0.5129478913434098, pos=0.4870521086565902}
    it's so nice and warm out but I'm stuck in this stupid cold diner -> {neg=0.5129478913434098, pos=0.4870521086565902}
    #myweakness myweakness is to sit somewhere with lots of Girls  I got no self confidence because of my body shape -> {neg=0.5129478913434098, pos=0.4870521086565902}
    Have to drive home now &amp; leave the show in the middle.........be back soon! -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @_rachelx sozzzle rach for signing out of msn it went wierd and now it wont let me back on -> {neg=0.5129478913434098, pos=0.4870521086565902}
    fine. I should be anyway. see you at the 4th quarter. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    "@__mares__ Not sure -> {neg=0.5129478913434098, pos=0.4870521086565902}
    *focus*focus*focus*  OUCHMYSHOULDER -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @_mystique_ lol you know how much i love derek -> {neg=0.5129478913434098, pos=0.4870521086565902}
    ... i was doing so good. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @a_c81 i know my mate picked the wrong wkd to marry in sooo many ways. Missed CC twitters -> {neg=0.5129478913434098, pos=0.4870521086565902}
    (( hmmmmmm. loveyousomuchhhbabeee  xxxxxxxxxxxxx -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @acmaurer No wonder I didn't get into Urbana -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @AKNickerson  no idea. it used to be on display in the town hall. I'll see if I can find out if it's still there. (i.e. ask my mom) -> {neg=0.5129478913434098, pos=0.4870521086565902}
    #myweakness asian guys  i don't think i'll find another asian guy who is interested in a mexican girl again. *cries* -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @ kbal24 I am sorry that you are feeling that way. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @agirlwhocan what happened! But summary to the story: gettin annoyed bein tony the tiger surrounded by frosted flakes -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @aeromimi sounds like you're having quite the adventure .. Wish I was there w you -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @_CrC_  oh i missed the pic -> {neg=0.5129478913434098, pos=0.4870521086565902}
    Heroes is losing it's momentum ): come on writers pick it up!!! -> {neg=0.5129478913434098, pos=0.4870521086565902}
    i want everything to be back to normal. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    #TwitHive seems to be a cool Twitter App. Looks like  the small TweetDeck Web Edition ... cant yet upload images  http://twithive.com -> {neg=0.5129478913434098, pos=0.4870521086565902}
    ..That blows. The jobs been filled.  ..Ilovematt.. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    ... not having such a good day -> {neg=0.5129478913434098, pos=0.4870521086565902}
    you need to find someone -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @16thstreetj Probably the terrorist since tix are $35/person. -> {neg=0.5129478913434098, pos=0.4870521086565902}
    @1mgoldstars Sunshine felt so good this morning! Now stuck in office building. Boo -> {neg=0.5129478913434098, pos=0.4870521086565902}
    :O 17 pages i have to type out -> {neg=1.0, pos=0.0}
    "..&amp; u still fucked it up @datsexybriteady -> {neg=0.5129478913434098, pos=0.4870521086565902}
    this makes me c... and 1187324 more bytes
```

