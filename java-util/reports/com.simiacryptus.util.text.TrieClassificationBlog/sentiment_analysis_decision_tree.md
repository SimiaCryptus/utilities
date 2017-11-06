# Sentiment Analysis using a Decision Tree
First, we load positive and negative sentiment tweets into two seperate models

Code from [TrieClassificationBlog.java:173](../../src/test/java/com/simiacryptus/util/text/TrieClassificationBlog.java#L173) executed in 0.05 seconds: 
```java
      ArrayList<TweetSentiment> list = new ArrayList<>(TweetSentiment.load()
              .filter(x -> x.category == 1).limit(testingSize + trainingSize).collect(Collectors.toList()));
      Collections.shuffle(list);
      return list;
```


Code from [TrieClassificationBlog.java:179](../../src/test/java/com/simiacryptus/util/text/TrieClassificationBlog.java#L179) executed in 0.03 seconds: 
```java
      ArrayList<TweetSentiment> list = new ArrayList<>(TweetSentiment.load()
              .filter(x -> x.category == 0).limit(testingSize + trainingSize).collect(Collectors.toList()));
      Collections.shuffle(list);
      return list;
```

Code from [TrieClassificationBlog.java:185](../../src/test/java/com/simiacryptus/util/text/TrieClassificationBlog.java#L185) executed in 4274.92 seconds: 
```java
      HashMap<String, List<String>> map = new HashMap<>();
      map.put("Positive", tweetsPositive.stream().limit(trainingSize).map(x -> x.getText()).collect(Collectors.toList()));
      map.put("Negative", tweetsNegative.stream().limit(trainingSize).map(x -> x.getText()).collect(Collectors.toList()));
      return new ClassificationTree().setVerbose(System.out).categorizationTree(map, 32);
```
Logging: 
```
    "hank" -> Contains={Negative=772, Positive=4036}	Absent={Negative=49228, Positive=45964}	Entropy=-0.979675
      "t " -> Contains={Negative=476, Positive=1571}	Absent={Negative=296, Positive=2465}	Entropy=-0.614319
        " no" -> Contains={Negative=117, Positive=156}	Absent={Negative=359, Positive=1415}	Entropy=-0.759630
          "ou for " -> Contains={Negative=0, Positive=13}	Absent={Negative=117, Positive=143}	Entropy=-0.953809
            "ot " -> Contains={Negative=70, Positive=57}	Absent={Negative=47, Positive=86}	Entropy=-0.962674
              "nc" -> Contains={Negative=3, Positive=12}	Absent={Negative=67, Positive=45}	Entropy=-0.944811
                "'" -> Contains={Negative=22, Positive=27}	Absent={Negative=45, Positive=18}	Entropy=-0.919926
                  "." -> Contains={Negative=10, Positive=23}	Absent={Negative=12, Positive=4}	Entropy=-0.867716
                    " I" -> Contains={Negative=2, Positive=15}	Absent={Negative=8, Positive=8}	Entropy=-0.778025
                  "hi" -> Contains={Negative=20, Positive=1}	Absent={Negative=25, Positive=17}	Entropy=-0.765558
                    "his " -> Contains={Negative=9, Positive=1}	Absent={Negative=11, Positive=0}	Entropy=-0.417835
                    " o" -> Contains={Negative=7, Positive=11}	Absent={Negative=18, Positive=6}	Entropy=-0.881834
                      " fo" -> Contains={Negative=12, Positive=1}	Absent={Negative=6, Positive=5}	Entropy=-0.718621
              "ut th" -> Contains={Negative=9, Positive=3}	Absent={Negative=38, Positive=83}	Entropy=-0.889857
                "D" -> Contains={Negative=8, Positive=4}	Absent={Negative=30, Positive=79}	Entropy=-0.856642
                  ". " -> Contains={Negative=22, Positive=37}	Absent={Negative=8, Positive=42}	Entropy=-0.808890
                    "en" -> Contains={Negative=5, Positive=22}	Absent={Negative=17, Positive=15}	Entropy=-0.862489
                      "ne" -> Contains={Negative=4, Positive=6}	Absent={Negative=1, Positive=16}	Entropy=-0.616953
                      " i" -> Contains={Negative=15, Positive=7}	Absent={Negative=2, Positive=8}	Entropy=-0.862754
                        "r " -> Contains={Negative=10, Positive=1}	Absent={Negative=5, Positive=6}	Entropy=-0.767193
                    "le" -> Contains={Negative=5, Positive=7}	Absent={Negative=3, Positive=35}	Entropy=-0.555233
                      "  " -> Contains={Negative=0, Positive=22}	Absent={Negative=3, Positive=13}	Entropy=-0.398816
          "n't" -> Contains={Negative=88, Positive=121}	Absent={Negative=271, Positive=1294}	Entropy=-0.700751
            "@d" -> Contains={Negative=11, Positive=0}	Absent={Negative=77, Positive=121}	Entropy=-0.927111
              " la" -> Contains={Negative=13, Positive=2}	Absent={Negative=64, Positive=119}	Entropy=-0.908317
                "my " -> Contains={Negative=17, Positive=9}	Absent={Negative=47, Positive=110}	Entropy=-0.886653
                  "t t" -> Contains={Negative=4, Positive=7}	Absent={Negative=13, Positive=2}	Entropy=-0.756965
                  ""@" -> Contains={Negative=0, Positive=16}	Absent={Negative=47, Positive=94}	Entropy=-0.845400
                    "n't wa" -> Contains={Negative=1, Positive=18}	Absent={Negative=46, Positive=76}	Entropy=-0.875068
                      "an't " -> Contains={Negative=12, Positive=4}	Absent={Negative=34, Positive=72}	Entropy=-0.892763
                        " yo" -> Contains={Negative=9, Positive=39}	Absent={Negative=25, Positive=33}	Entropy=-0.855841
                          "." -> Contains={Negative=9, Positive=21}	Absent={Negative=0, Positive=18}	Entropy=-0.624843
                            "ve" -> Contains={Negative=1, Positive=11}	Absent={Negative=8, Positive=10}	Entropy=-0.799256
                          "hanks" -> Contains={Negative=16, Positive=32}	Absent={Negative=9, Positive=1}	Entropy=-0.857259
                            "ti" -> Contains={Negative=2, Positive=14}	Absent={Negative=14, Positive=18}	Entropy=-0.855876
                              " i" -> Contains={Negative=10, Positive=5}	Absent={Negative=4, Positive=13}	Entropy=-0.859984
            "orry " -> Contains={Negative=22, Positive=5}	Absent={Negative=249, Positive=1289}	Entropy=-0.637482
              " o" -> Contains={Negative=6, Positive=5}	Absent={Negative=16, Positive=0}	Entropy=-0.529615
              "@d" -> Contains={Negative=26, Positive=24}	Absent={Negative=223, Positive=1265}	Entropy=-0.621172
                " thanks " -> Contains={Negative=10, Positive=1}	Absent={Negative=16, Positive=23}	Entropy=-0.877260
                  "nk " -> Contains={Negative=9, Positive=5}	Absent={Negative=7, Positive=18}	Entropy=-0.890347
                    "or " -> Contains={Negative=1, Positive=10}	Absent={Negative=6, Positive=8}	Entropy=-0.789287
                "@D" -> Contains={Negative=21, Positive=24}	Absent={Negative=202, Positive=1241}	Entropy=-0.595694
                  "@Da" -> Contains={Negative=7, Positive=20}	Absent={Negative=14, Positive=4}	Entropy=-0.810707
                    "er" -> Contains={Negative=7, Positive=6}	Absent={Negative=0, Positive=14}	Entropy=-0.600205
                  "m " -> Contains={Negative=65, Positive=215}	Absent={Negative=137, Positive=1026}	Entropy=-0.572250
                    "n " -> Contains={Negative=44, Positive=97}	Absent={Negative=21, Positive=118}	Entropy=-0.754697
                      "ev" -> Contains={Negative=8, Positive=2}	Absent={Negative=36, Positive=95}	Entropy=-0.841915
                        "mi" -> Contains={Negative=11, Positive=8}	Absent={Negative=25, Positive=87}	Entropy=-0.797151
                          "2" -> Contains={Negative=8, Positive=4}	Absent={Negative=17, Positive=83}	Entropy=-0.687656
                            "ks." -> Contains={Negative=7, Positive=5}	Absent={Negative=10, Positive=78}	Entropy=-0.569267
                              "th" -> Contains={Negative=6, Positive=71}	Absent={Negative=4, Positive=7}	Entropy=-0.470210
                                "ex" -> Contains={Negative=3, Positive=8}	Absent={Negative=3, Positive=63}	Entropy=-0.365170
                                  "r " -> Contains={Negative=0, Positive=47}	Absent={Negative=3, Positive=16}	Entropy=-0.248433
    ...skipping 307936 bytes...
                                      "I " -> Contains={Negative=422, Positive=109}	Absent={Negative=2203, Positive=1169}	Entropy=-0.903038
                                        " fin" -> Contains={Negative=8, Positive=10}	Absent={Negative=414, Positive=99}	Entropy=-0.715351
                                          "n s" -> Contains={Negative=6, Positive=9}	Absent={Negative=408, Positive=90}	Entropy=-0.688753
                                            "les" -> Contains={Negative=4, Positive=7}	Absent={Negative=404, Positive=83}	Entropy=-0.663763
                                              "ool" -> Contains={Negative=4, Positive=6}	Absent={Negative=400, Positive=77}	Entropy=-0.643228
                                                " how " -> Contains={Negative=5, Positive=6}	Absent={Negative=395, Positive=71}	Entropy=-0.622062
                                                  "Y" -> Contains={Negative=15, Positive=10}	Absent={Negative=380, Positive=61}	Entropy=-0.600503
                                                    "N" -> Contains={Negative=9, Positive=2}	Absent={Negative=6, Positive=8}	Entropy=-0.874123
                                                    ": " -> Contains={Negative=9, Positive=8}	Absent={Negative=371, Positive=53}	Entropy=-0.560239
                                                      "bo" -> Contains={Negative=22, Positive=10}	Absent={Negative=349, Positive=43}	Entropy=-0.528402
                                                        " t" -> Contains={Negative=18, Positive=3}	Absent={Negative=4, Positive=7}	Entropy=-0.733850
                                                          "an" -> Contains={Negative=7, Positive=3}	Absent={Negative=11, Positive=0}	Entropy=-0.573895
                                                        "rea" -> Contains={Negative=27, Positive=10}	Absent={Negative=322, Positive=33}	Entropy=-0.482783
                                                          "st" -> Contains={Negative=3, Positive=7}	Absent={Negative=24, Positive=3}	Entropy=-0.629218
                                                            "f" -> Contains={Negative=16, Positive=0}	Absent={Negative=8, Positive=3}	Entropy=-0.477864
                                                          "to" -> Contains={Negative=113, Positive=4}	Absent={Negative=209, Positive=29}	Entropy=-0.431318
                                                            "igh" -> Contains={Negative=16, Positive=4}	Absent={Negative=97, Positive=0}	Entropy=-0.160458
                                                              " th" -> Contains={Negative=7, Positive=3}	Absent={Negative=9, Positive=1}	Entropy=-0.734848
                                                            "e" -> Contains={Negative=178, Positive=24}	Absent={Negative=31, Positive=5}	Entropy=-0.516262
                                                              "the" -> Contains={Negative=36, Positive=11}	Absent={Negative=142, Positive=13}	Entropy=-0.502470
                                                                "k" -> Contains={Negative=23, Positive=2}	Absent={Negative=13, Positive=9}	Entropy=-0.693076
                                                                  "g" -> Contains={Negative=15, Positive=0}	Absent={Negative=8, Positive=2}	Entropy=-0.440163
                                                                  " h" -> Contains={Negative=8, Positive=2}	Absent={Negative=5, Positive=7}	Entropy=-0.883832
                                                              "." -> Contains={Negative=19, Positive=0}	Absent={Negative=12, Positive=5}	Entropy=-0.513998
                                        "happy" -> Contains={Negative=9, Positive=31}	Absent={Negative=2194, Positive=1138}	Entropy=-0.921977
                                          "y t" -> Contains={Negative=0, Positive=10}	Absent={Negative=9, Positive=21}	Entropy=-0.737112
                                            "t " -> Contains={Negative=6, Positive=7}	Absent={Negative=3, Positive=14}	Entropy=-0.828040
                                          " excited" -> Contains={Negative=0, Positive=13}	Absent={Negative=2194, Positive=1125}	Entropy=-0.917181
                                            "atching " -> Contains={Negative=10, Positive=26}	Absent={Negative=2184, Positive=1099}	Entropy=-0.915019
                                              " th" -> Contains={Negative=6, Positive=5}	Absent={Negative=4, Positive=21}	Entropy=-0.756384
                                                " m" -> Contains={Negative=0, Positive=11}	Absent={Negative=4, Positive=10}	Entropy=-0.613175
                                              "good" -> Contains={Negative=37, Positive=59}	Absent={Negative=2147, Positive=1040}	Entropy=-0.910614
                                                "mo" -> Contains={Negative=1, Positive=21}	Absent={Negative=36, Positive=38}	Entropy=-0.847321
                                                  " good mo" -> Contains={Negative=1, Positive=9}	Absent={Negative=0, Positive=12}	Entropy=-0.400170
                                                  "ot" -> Contains={Negative=10, Positive=1}	Absent={Negative=26, Positive=37}	Entropy=-0.912188
                                                    "a g" -> Contains={Negative=4, Positive=16}	Absent={Negative=22, Positive=21}	Entropy=-0.915897
                                                      "w" -> Contains={Negative=2, Positive=8}	Absent={Negative=2, Positive=8}	Entropy=-0.767569
                                                      "re" -> Contains={Negative=2, Positive=8}	Absent={Negative=20, Positive=13}	Entropy=-0.920575
                                                        "goodn" -> Contains={Negative=4, Positive=6}	Absent={Negative=16, Positive=7}	Entropy=-0.914599
                                                          ". " -> Contains={Negative=9, Positive=1}	Absent={Negative=7, Positive=6}	Entropy=-0.811406
                                                "." -> Contains={Negative=1193, Positive=558}	Absent={Negative=954, Positive=482}	Entropy=-0.901018
                                                  ": " -> Contains={Negative=31, Positive=49}	Absent={Negative=923, Positive=433}	Entropy=-0.905971
                                                    "(" -> Contains={Negative=1, Positive=12}	Absent={Negative=30, Positive=37}	Entropy=-0.909693
                                                      "k" -> Contains={Negative=10, Positive=5}	Absent={Negative=20, Positive=32}	Entropy=-0.953039
                                                        "u" -> Contains={Negative=12, Positive=9}	Absent={Negative=8, Positive=23}	Entropy=-0.892053
                                                          "g" -> Contains={Negative=5, Positive=6}	Absent={Negative=7, Positive=3}	Entropy=-0.948746
                                                          "g" -> Contains={Negative=2, Positive=13}	Absent={Negative=6, Positive=10}	Entropy=-0.792012
                                                    "???? " -> Contains={Negative=4, Positive=18}	Absent={Negative=919, Positive=415}	Entropy=-0.888833
                                                      "o" -> Contains={Negative=3, Positive=7}	Absent={Negative=1, Positive=11}	Entropy=-0.688128
                                                      "d to" -> Contains={Negative=31, Positive=1}	Absent={Negative=888, Positive=414}	Entropy=-0.884688
                                                        " to t" -> Contains={Negative=9, Positive=1}	Absent={Negative=22, Positive=0}	Entropy=-0.289135
                                                        "orr" -> Contains={Negative=26, Positive=0}	Absent={Negative=862, Positive=414}	Entropy=-0.892340
                                                          ""(" -> Contains={Negative=8, Positive=18}	Absent={Negative=854, Positive=396}	Entropy=-0.899745
                                                            "c" -> Contains={Negative=5, Positive=5}	Absent={Negative=3, Positive=13}	Entropy=-0.830116
                                                            "*sigh" -> Contains={Negative=29, Positive=2}	Absent={Negative=825, Positive=394}	Entropy=-0.892311
                                                              "y" -> Contains={Negative=11, Positive=2}	Absent={Negative=18, Positive=0}	Entropy=-0.391949
                                                              "twitter" -> Contains={Negative=6, Positive=12}	Absent={Negative=819, Positive=382}	Entropy=-0.899088
                                                                "ew " -> Contains={Negative=8, Positive=16}	Absent={Negative=811, Positive=366}	Entropy=-0.893331
                                                                  "m" -> Contains={Negative=2, Positive=10}	Absent={Negative=6, Positive=6}	Entropy=-0.849119
                                                                  "H" -> Contains={Negative=18, Positive=25}	Absent={Negative=793, Positive=341}	Entropy=-0.885270
    
```

Returns: 
```
    com.simiacryptus.text.ClassificationTree$$Lambda$188/1818402158@44e81672
```
Code from [TrieClassificationBlog.java:192](../../src/test/java/com/simiacryptus/util/text/TrieClassificationBlog.java#L192) executed in 0.26 seconds: 
```java
      return tweetsPositive.stream().skip(trainingSize).map(x->x.getText()).mapToDouble(str->{
          Map<String, Double> prob = rule.apply(str);
          HashMap<String, Object> row = new LinkedHashMap<>();
          row.put("Category", "Positive");
          row.put("Prediction", prob.entrySet().stream().max(Comparator.comparing(x -> x.getValue())).get().getKey());
          prob.forEach((category, count)->row.put(category+"%", count * 100));
          row.put("Text", str);
          table.putRow(row);
          return prob.getOrDefault("Positive", 0.0) < 0.5 ? 0.0 : 1.0;
      }).average().getAsDouble() * 100.0 + "%";
```

Returns: 
```
    81.89999999999999%
```
Code from [TrieClassificationBlog.java:204](../../src/test/java/com/simiacryptus/util/text/TrieClassificationBlog.java#L204) executed in 0.22 seconds: 
```java
      return tweetsNegative.stream().skip(trainingSize).map(x->x.getText()).mapToDouble(str->{
          Map<String, Double> prob = rule.apply(str);
          HashMap<String, Object> row = new LinkedHashMap<>();
          row.put("Category", "Negative");
          row.put("Prediction", prob.entrySet().stream().max(Comparator.comparing(x -> x.getValue())).get().getKey());
          prob.forEach((category, count)->row.put(category+"%", count * 100));
          row.put("Text", str);
          table.putRow(row);
          return prob.getOrDefault("Negative", 0.0) < 0.5 ? 0.0 : 1.0;
      }).average().getAsDouble() * 100.0 + "%";
```

Returns: 
```
    55.05%
```
Category | Prediction | Negative% | Positive% | Text
-------- | ---------- | --------- | --------- | ----
Positive | Positive | 39.7879 | 60.2121 | @adamcoomes hi handsome                                                                                                                                                                                                                
Positive | Positive | 27.8227 | 72.1773 | ::::p.s. ur so cute when sit there and do what u do when you do what you do  ::::                                                                                                                                                      
Positive | Negative | 67.4121 | 32.5879 | "@BecaBear Do I have to pic shit up now                                                                                                                                                                                                
Positive | Negative | 55.8621 | 44.1379 | "#shortstack are the best  if you don't know them                                                                                                                                                                                      
Positive | Positive | 30.0000 | 70.0000 | @AlexAllTimeLow http://twitpic.com/66axl - your face  yumm to the food                                                                                                                                                                 
Positive | Negative | 76.5766 | 23.4234 | @ArabGirlALWZ i wanna tell @BLAHSODMG but i can't or it'll ruin the secret                                                                                                                                                             
Positive | Negative | 50.0000 | 50.0000 | @carmenchui Maybe we went to be right after you left and got up early to play games on Sunday morning.                                                                                                                                 
Positive | Positive | 39.7879 | 60.2121 | @Anjeebaby next time promise                                                                                                                                                                                                           
Positive | Positive | 21.9866 | 78.0134 | @adriennedotcom Awwwready baby!                                                                                                                                                                                                        
Positive | Positive | 0.0000 | 100.0000 | @babilorentz oh yes  bcuz I love McFly &amp; the Twilight series..                                                                                                                                                                     
Positive | Negative | 81.8182 | 18.1818 | @buddhafied Glad u had fun on Saturday! I had fun too!  ... Oh and hopefully Cleo gets better soon!..                                                                                                                                  
Positive | Positive | 39.7879 | 60.2121 | @cfaddict stop twittering and get in the pit                                                                                                                                                                                           
Positive | Positive | 36.4066 | 63.5934 | "#GREAT SEX                                                                                                                                                                                                                            
Positive | Positive | 27.8227 | 72.1773 | @Azikale Are you suffering with Dave Syndrome? Have you been jumping on the roof of a car in a loincloth?                                                                                                                              
Positive | Positive | 15.7895 | 84.2105 | @4barrys    Thanks Scoot-Scoot!  I am so lucky to have you as my &quot;neighbor&quot;... coolest one in the office!                                                                                                                    
Positive | Positive | 40.0000 | 60.0000 | @currymansam Haha yay I'm not a dick                                                                                                                                                                                                   
Positive | Positive | 23.5294 | 76.4706 | @christophermoy @davidleibrandt Both of you should get some rest                                                                                                                                                                       
Positive | Positive | 27.8227 | 72.1773 | #goodsex is when u think you're gonna pass out.                                                                                                                                                                                        
Positive | Positive | 48.0359 | 51.9641 | @AmyBPerrault I agree Forman Grill is 2nd runner up.  A life saver during winter months                                                                                                                                                
Positive | Positive | 27.8227 | 72.1773 | @artrox hehe...glad I could give you a giggle. It goes with your avatar!                                                                                                                                                               
Positive | Positive | 0.0000 | 100.0000 | @Boddingtons why thank u                                                                                                                                                                                                               
Positive | Positive | 39.7879 | 60.2121 | "@blockhead4eva name's always been lower case                                                                                                                                                                                          
Positive | Negative | 68.1325 | 31.8675 | ..degree show was great last night                                                                                                                                                                                                     
Positive | Negative | 55.8621 | 44.1379 | @bballstatus  hope u don't get mad...but you resemble NeYo in ur profile pic                                                                                                                                                           
Positive | Positive | 0.0000 | 100.0000 | @dances you're very welcome.                                                                                                                                                                                                           
Positive | Positive | 39.7879 | 60.2121 | @BeanaB ill be back saturday                                                                                                                                                                                                           
Positive | Positive | 17.6471 | 82.3529 | @BettyButty I went. I went! I went!!! I had a great time  Are you having a good weekend?                                                                                                                                               
Positive | Positive | 27.8227 | 72.1773 | @britneyspears Love you Britney!!!  Big fan since I can remember!                                                                                                                                                                      
Positive | Positive | 36.4066 | 63.5934 | "#spymaster is freakin brilliant- great job                                                                                                                                                                                            
Positive | Positive | 20.0000 | 80.0000 | @AlexAllTimeLow i already pre-ordered it                                                                                                                                                                                               
Positive | Positive | 0.0000 | 100.0000 | @AlanCarr  If you liked that you might like the new side project 3 of them have started   http://bit.ly/LQAVt                                                                                                                          
Positive | Positive | 39.7879 | 60.2121 | "@cempaka : bukannya besok nek? well.. whichever                                                                                                                                                                                       
Positive | Positive | 48.0359 | 51.9641 | @chayce I answer to either                                                                                                                                                                                                             
Positive | Negative | 60.0000 | 40.0000 | ...can't fall asleep...lol...listening to pop music &amp; Duffy she is a great singer                                                                                                                                                  
Positive | Positive | 1.6055 | 98.3945 | @30lines  Thanks Mike!  You rock                                                                                                                                                                                                       
Positive | Positive | 27.8227 | 72.1773 | @ccr_harris  There were way more than two! Ten hours of real-ale takes it out of you                                                                                                                                                   
Positive | Positive | 20.3252 | 79.6748 | "@Chocolatedonout good luck Line                                                                                                                                                                                                       
Positive | Positive | 39.7879 | 60.2121 | @blowdart &quot;Wanna come up and see my Crystal&quot; #BlowdartChatUpLines.                                                                                                                                                           
Positive | Positive | 27.8227 | 72.1773 | "@britneyspears I hope that if I were to run up on stage with you one day                                                                                                                                                              
Positive | Positive | 39.7879 | 60.2121 | @BeatlesTweets                                                                                                                                                                                                                         
Positive | Positive | 0.0000 | 100.0000 | @adyw @afinucane  I'm alive! Thanks folks  Just a little bruised.                                                                                                                                                                      
Positive | Positive | 33.2683 | 66.7317 | @adiemusfree the trailer brought tears to my eyes! I bought the soundtrack on #iTunes today &amp; will preview the movie via #netflix  Yay!!!                                                                                          
Positive | Positive | 27.2727 | 72.7273 | @ChesterBe how you feel today                                                                                                                                                                                                          
Positive | Negative | 68.1325 | 31.8675 | just soaked in the hot springs of Pagosa. So nice.                                                                                                                                                                                     
Positive | Positive | 39.7879 | 60.2121 | @BB517  lol he does love spahkly gold things....                                                                                                                                                                                       
Positive | Positive | 27.8227 | 72.1773 | @AshleighLives What's got you cheerful?                                                                                                                                                                                                
Positive | Negative | 91.6129 | 8.3871 | I think it would be fun                                                                                                                                                                                                                
Positive | Positive | 48.0359 | 51.9641 | "@awilber sure we use our own and proud of it. I work from home                                                                                                                                                                        
Positive | Positive | 36.4066 | 63.5934 | #WhyITweet because I like to think someone cares                                                                                                                                                                                       
Positive | Positive | 21.9866 | 78.0134 | @ActivityGrrrl we need to try to make #happiness a trending topic!                                                                                                                                                                     
Positive | Negative | 75.0000 | 25.0000 | @AnnaSITE Working in back yard - Yuck! but 7 snakes down!                                                                                                                                                                              
Positive | Positive | 27.8227 | 72.1773 | @cassieventura ill come comfort you                                                                                                                                                                                                    
Positive | Negative | 58.3333 | 41.6667 | # New York is the most amazing city i've ever been to                                                                                                                                                                                  
Positive | Positive | 21.9866 | 78.0134 | "@aleahhh http://twitpic.com/7gj8p - How awesome!! Look                                                                                                                                                                                
Positive | Positive | 0.0000 | 100.0000 | @bzaffini     I've seen this...thanks.                                                                                                                                                                                                 
Positive | Negative | 76.0555 | 23.9445 | @candydoll07: Never fancied the taste of Gatorade! Vitwater's off my list! @joshwhacker: I don't think I'll be drinking it any time soon.                                                                                              
Positive | Positive | 43.6782 | 56.3218 | @dadwygle But he forgot U R                                                                                                                                                                                                            
Positive | Positive | 39.7879 | 60.2121 | "@bumgenius haha                                                                                                                                                                                                                       
Positive | Positive | 10.0000 | 90.0000 | @Amileegrant Thank you for following. Your pretty awesome.                                                                                                                                                                             
Positive | Positive | 36.4066 | 63.5934 | #trvsdjam: o Mark que pediu.                                                                                                                                                                                                           
Positive | Positive | 30.0000 | 70.0000 | @AlanG123 no worries... happy to help.                                                                                                                                                                                                 
Positive | Negative | 100.0000 | 0.0000 | @chefpaola my son is having a sleepover with a girl  i want to make chocolate chip cookies with them. i need an easy recipe  pls?                                                                                                      
Positive | Positive | 36.4066 | 63.5934 | "#NASCAR Congrats to Brad                                                                                                                                                                                                              
Positive | Positive | 48.0000 | 52.0000 | - 1 follower WOW!                                                                                                                                                                                                                      
Positive | Negative | 55.8621 | 44.1379 | @amyhulme No that will happen alot!  Don't be too hard on yourself.                                                                                                                                                                    
Positive | Positive | 0.0000 | 100.0000 | #musicmonday - Jonas Brothers - &quot;Paranoid&quot; -- Really goes well with the movie I'm watching. 'A Beautiful Mind'. LOVE this movie so far.                                                                                      
Positive | Positive | 20.0000 | 80.0000 | "@auntielyn thanks Auntie                                                                                                                                                                                                              
Positive | Positive | 21.9866 | 78.0134 | @A_Elizabethhh do me do me!! im awesome and love scrubs                                                                                                                                                                                
Positive | Positive | 39.7879 | 60.2121 | @AlishaV                                                                                                                                                                                                                               
Positive | Negative | 67.1756 | 32.8244 | @candycoca // Eraa do The night i followed the dog ! Falei dos verbos regulares .. sÃ³ isso                                                                                                                                            
Positive | Positive | 48.0359 | 51.9641 | @Amy_ I am coming back then too for nans thing                                                                                                                                                                                         
Positive | Positive | 39.7879 | 60.2121 | @BlokesLib @Lauren_lolly_  lol theres a lot of truth in that too lol                                                                                                                                                                   
Positive | Positive | 0.0000 | 100.0000 | @CesLSU Thank you for the #followfriday                                                                                                                                                                                                
Positive | Negative | 87.3874 | 12.6126 | @angeljones i can leave whenever ur ready! I just dont know where u wanna meet n stuffff                                                                                                                                               
Positive | Positive | 21.9866 | 78.0134 | "@ccolmenar woohoo!! Have a GREAT time - and they play                                                                                                                                                                                 
Positive | Positive | 21.9866 | 78.0134 | @anttheladiesman happy bdayy!                                                                                                                                                                                                          
Positive | Positive | 8.2547 | 91.7453 | @ChrisHFilms Eminem's latest is quite good and also Dizzee Rascal's 'Dance Wiv Me' is a good one. All American Rejects are great btw                                                                                                   
Positive | Positive | 39.7879 | 60.2121 | @adriannayj WATCH SEX DRIVE                                                                                                                                                                                                            
Positive | Negative | 76.0555 | 23.9445 | @Brookie24 Umm i started to do it .. but i couldn't think of anything either so I deleted it                                                                                                                                           
Positive | Positive | 39.7879 | 60.2121 | @CT_x thnx found her                                                                                                                                                                                                                   
Positive | Positive | 39.7879 | 60.2121 | @anitasetio hellooo                                                                                                                                                                                                                    
Positive | Positive | 27.8227 | 72.1773 | @basherlock lol it was Friday night you silly! I was at a moooovie                                                                                                                                                                     
Positive | Positive | 36.4066 | 63.5934 | #feckitfriday grrrr cannot be bothered with twitter - who's up for that?                                                                                                                                                               
Positive | Positive | 39.7879 | 60.2121 | "@alitherunner LOL                                                                                                                                                                                                                     
Positive | Positive | 27.8227 | 72.1773 | @cyberprvideo heard of. And you're the first to mention him to me!                                                                                                                                                                     
Positive | Positive | 27.8227 | 72.1773 | @adrianspencer that conjures up a strange image of you tweeting in a tree                                                                                                                                                              
Positive | Positive | 0.0000 | 100.0000 | #followfriday @juliedeborah she lives over the water but she loves yorkshire fish n chips                                                                                                                                              
Positive | Positive | 0.0000 | 100.0000 | "@Asher_Book You're welcome. You're the best dancer                                                                                                                                                                                    
Positive | Positive | 0.0000 | 100.0000 | @barrygreenstein yikes. keep your head up barry  I'm pullin for you for the main event this year. Flopping quads is so sick.                                                                                                           
Positive | Positive | 27.8227 | 72.1773 | #vanesse says: have you ever done your biggest sin? No?  with me you will go to hot hell                                                                                                                                               
Positive | Positive | 27.8227 | 72.1773 | @amandaster Just so you know...you vanished from my DM list again...so fix it like we did the last time...thx                                                                                                                          
Positive | Positive | 10.0000 | 90.0000 | @angelenefay yay! I'm so glad it went well! My mum had it done and it was fine                                                                                                                                                         
Positive | Positive | 39.7879 | 60.2121 | &quot;5ive a Day&quot; - we follow members of boyband 5ive as they struggle to eat their daily recommended intake of fruit and vegetables (@lukens)                                                                                    
Positive | Positive | 21.9866 | 78.0134 | @busylizzie71 morning! Have a great day                                                                                                                                                                                                
Positive | Positive | 39.7879 | 60.2121 | "@adamkirrmusic yes                                                                                                                                                                                                                    
Positive | Positive | 39.7879 | 60.2121 | @benadgatemusic That's cute                                                                                                                                                                                                            
Positive | Positive | 0.0000 | 100.0000 | @ArtMind i didn't see your feature D: but i sure do love that site! thanks for sharing!!                                                                                                                                               
Positive | Negative | 62.6221 | 37.3779 | @becksbissell Brilliant! That's where I normally sit when at Thruxton. Great day for it. Enjoy!                                                                                                                                        
Positive | Negative | 61.5385 | 38.4615 | @BrentSpiner Colm Meany... I had to laugh.  They could be related couldn't they. LOL                                                                                                                                                   
Positive | Positive | 39.7879 | 60.2121 | "@Bytor2112 @KatharinaDawn You know its quality                                                                                                                                                                                        
Positive | Positive | 21.9866 | 78.0134 | @angusgibbins Hey! I'm thinking Family or GPO tonight..... i'm on door list at Family                                                                                                                                                  
Positive | Negative | 83.3333 | 16.6667 | "#nomaintenance i find it amusing that in Iran they have blocked websites                                                                                                                                                              
Positive | Positive | 27.8227 | 72.1773 | @_Sachi_ I'm hapy this makes you happy                                                                                                                                                                                                 
Positive | Positive | 1.6055 | 98.3945 | @civlee thankyou so much ! Have a wonderful weekend!                                                                                                                                                                                   
Positive | Positive | 21.9866 | 78.0134 | @chrisdaniel Maybe he's gone green and uses a push mower!                                                                                                                                                                              
Positive | Positive | 36.4066 | 63.5934 | "#XboxE3 is at 18h30m GMT this Monday                                                                                                                                                                                                  
Positive | Negative | 58.8235 | 41.1765 | @danielpunkass you do know how it works though                                                                                                                                                                                         
Positive | Positive | 39.7879 | 60.2121 | "@checkback  - yes                                                                                                                                                                                                                     
Positive | Positive | 0.0000 | 100.0000 | "@Caitlinjstasey  Thanks. LariiTran and I are cousins                                                                                                                                                                                  
Positive | Positive | 39.7879 | 60.2121 | "@5tu looking forward to it                                                                                                                                             