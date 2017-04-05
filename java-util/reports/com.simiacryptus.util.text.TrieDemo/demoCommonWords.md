First, we load text into a model

Code from [TrieDemo.java:284](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L284) executed in 269.87 seconds: 
```java
      CharTrie charTrie = CharTrieIndex.indexFulltext(trainingData, maxLevels, minWeight).truncate();
      print(charTrie);
      return charTrie;
```
Logging: 
```
    Total Indexed Document (KB): 3735
    Total Node Count: 4460615
    Total Index Memory Size (KB): 104545
    
```

Returns: 
```
    com.simiacryptus.util.text.CharTrie@40a9eb8c
```
We can then search for high-entropy keywords with encoding penalty 0:

Code from [TrieDemo.java:292](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L292) executed in 19.12 seconds: 
```java
      List<String> candidates = triePositive.max(node -> {
          return (node.getDepth() - _penalty) * (node.getCursorCount());
          //return (node.getDepth()-2) * Math.log((node.getCursorCount() - 5)/3);
      }, 1000)
              .map(x -> x.getString())
              .collect(Collectors.toList());
      List<String> filteredKeywords = new ArrayList<>();
      for(String keyword : candidates) {
          if(!filteredKeywords.stream().anyMatch(x->x.contains(keyword) || keyword.contains(x))) {
              filteredKeywords.add(keyword);
          }
      }
      return filteredKeywords.stream().map(x->'"'+x+'"').collect(Collectors.joining("\n"));
```

Returns: 
```
    " "
"e"
"a"
"t"
"i"
"n"
"r"
"o"
"s"
"l"
"h"
"d"
"c"
"u"
"m"
"f"
"p"
"g"
"|"
"]"
"["
"b"
"y"
"="
"."
"w"
","
"1"
"
"
"0"
"v"
"/"
"'"
"A"
"2"
"-"
"k"
"}"
"{"
"9"
"S"
"C"
">"
"T"
"3"
"<"
"""
"4"
"8"
"5"
":"
"M"
"7"
"6"
"P"
")"
"("
"B"
"I"
"x"
```
We can then search for high-entropy keywords with encoding penalty 1:

Code from [TrieDemo.java:292](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L292) executed in 18.17 seconds: 
```java
      List<String> candidates = triePositive.max(node -> {
          return (node.getDepth() - _penalty) * (node.getCursorCount());
          //return (node.getDepth()-2) * Math.log((node.getCursorCount() - 5)/3);
      }, 1000)
              .map(x -> x.getString())
              .collect(Collectors.toList());
      List<String> filteredKeywords = new ArrayList<>();
      for(String keyword : candidates) {
          if(!filteredKeywords.stream().anyMatch(x->x.contains(keyword) || keyword.contains(x))) {
              filteredKeywords.add(keyword);
          }
      }
      return filteredKeywords.stream().map(x->'"'+x+'"').collect(Collectors.joining("\n"));
```

Returns: 
```
    " the "
" of "
"s "
" and "
"er"
"n "
"in"
"re"
" [["
"on"
"te"
"at"
", "
"ti"
"ed "
"st"
"or"
"al"
"en"
"t "
"es"
"ar"
"it"
"is"
"]]"
"of the"
"le"
"r "
"y "
" to "
" i"
"ri"
"ic"
"nt"
"as"
"la"
"ng"
"me"
" s"
"       "
"li"
"io"
"http://www."
"l "
"ro"
"ra"
" |"
"de"
" w"
"ur"
"ve"
"ce"
"se"
" c"
"The "
" = "
"ta"
"ne"
"co"
" A"
"na"
". "
"ch"
" p"
"ea"
"a "
" b"
" f"
"ha"
"ou"
"hi"
"ma"
"h "
"om"
"ca"
"ef>"
"el"
"am"
"''"
"ll"
"si"
"| "
"e a"
"d t"
"the ["
"ns"
"ol"
"] "
"ge"
"g "
"be"
"e t"
"ac"
"rl=http://"
"et"
" h"
"rs"
" m"
"di"
"il"
"tr"
"ia"
"e="
"}}</r"
"e of"
" The"
"us"
" d"
"ni"
"rt"
"20"
"da"
"ct"
"ie"
"ec"
"ci"
"ss"
"nc"
"lo"
"ho"
"l=http://www"
"ut"
" e"
"pe"
" r"
"em"
" n"
"sh"
"un"
"m "
" l"
"{{"
"mi"
"to the"
"id"
" 1"
" S"
"no"
"00"
" ("
"ot"
"fo"
"ry"
"d a"
"ir"
"publ"
"
* "
"ts"
"pa"
"ig"
"ly"
"19"
"so"
"ad"
"
|"
" C"
"fi"
"po"
".<r"
```
We can then search for high-entropy keywords with encoding penalty 2:

Code from [TrieDemo.java:292](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L292) executed in 18.50 seconds: 
```java
      List<String> candidates = triePositive.max(node -> {
          return (node.getDepth() - _penalty) * (node.getCursorCount());
          //return (node.getDepth()-2) * Math.log((node.getCursorCount() - 5)/3);
      }, 1000)
              .map(x -> x.getString())
              .collect(Collectors.toList());
      List<String> filteredKeywords = new ArrayList<>();
      for(String keyword : candidates) {
          if(!filteredKeywords.stream().anyMatch(x->x.contains(keyword) || keyword.contains(x))) {
              filteredKeywords.add(keyword);
          }
      }
      return filteredKeywords.stream().map(x->'"'+x+'"').collect(Collectors.joining("\n"));
```

Returns: 
```
    " the "
" and "
" of "
"tion"
"of the"
" [["
"<ref name="
" in "
"ing "
"http://www."
"       "
"ed "
"}}</ref>"
" to "
"atio"
"ref name=""
"n the"
".<ref name"
"ion "
"publisher"
"<ref>{{cite "
"er "
"|url=http://"
"]], "
"ter"
"|title="
"ate"
" that "
" &ndash; ["
"ent"
"The "
"ublisher="
" with "
"url=http://www"
"]] "
"|publishe"
"al "
"es "
" is "
" from "
"the ["
" for "
"</ref> "
"https://web.archive.org/web/20"
"an "
"s of"
">{{cite web"
"first"
"in th"
"d the"
"{{cite web|url=http:/"
"accessdat"
", and"
"as "
".<ref>{{cite"
" = "
"</ref>
"
"s a"
" which "
"s the"
"] &ndash; "
"re "
" co"
"journal"
"e of"
"|accessda"
" The"
"https://books.google.com/books?id="
" a "
", the"
"to the"
"archiveurl=https://web.archive.org/web/2"
" | "
"ref>{{cite we"
"ica"
"style="text-align:"
"author"
"|archiveurl=https://web.archive.org/web/"
"ther"
" re"
" |title"
"/ref>

"
"e a"
" |publish"
"and th"
"his "
" by "
"tyle="text-align:cen"
"st "
"e the"
"ist"
" |archiveurl=https://web.archive.org/web"
"t the"
" style="text-align"
"ttps://web.archive.org/web/200"
">{{cite journa"
"anguage"
"ions"
"the s"
"on of"
```
We can then search for high-entropy keywords with encoding penalty 3:

Code from [TrieDemo.java:292](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L292) executed in 18.44 seconds: 
```java
      List<String> candidates = triePositive.max(node -> {
          return (node.getDepth() - _penalty) * (node.getCursorCount());
          //return (node.getDepth()-2) * Math.log((node.getCursorCount() - 5)/3);
      }, 1000)
              .map(x -> x.getString())
              .collect(Collectors.toList());
      List<String> filteredKeywords = new ArrayList<>();
      for(String keyword : candidates) {
          if(!filteredKeywords.stream().anyMatch(x->x.contains(keyword) || keyword.contains(x))) {
              filteredKeywords.add(keyword);
          }
      }
      return filteredKeywords.stream().map(x->'"'+x+'"').collect(Collectors.joining("\n"));
```

Returns: 
```
    " the "
" and "
" of the"
"<ref name="
"ation"
"http://www."
"        "
"}}</ref>"
"ref name=""
".<ref name"
"publisher"
"tion "
" &ndash; [["
"<ref>{{cite "
"|url=http://"
" in the"
"url=http://www"
"ublisher="
"the [["
"|title="
"|accessdate="
"ing "
"https://web.archive.org/web/20"
"s of "
"|publishe"
" that "
">{{cite web|url=http:/"
" to "
" with "
"style="text-align:center;""
"]] &ndash; ["
"</ref> "
"]]
[[Category:"
".<ref>{{cite"
"https://books.google.com/books?id="
" from "
"ref>{{cite web|url=http:"
" style="text-align:center;"
"e of "
"ion of "
"archiveurl=https://web.archive.org/web/2"
"|archiveurl=https://web.archive.org/web/"
"</ref>

"
" for "
" |accessdate"
" which "
"first"
">{{cite journal"
" |archiveurl=https://web.archive.org/web"
"]], "
"d the"
"ttps://web.archive.org/web/200"
"
|- style="text-align:center"
" The "
"American "
", and"
" |publish"
" was "
"tyle="text-align:center;"
"
"   &ndash; ["
"to the"
"{{cite journal |"
" |title"
"}</ref>
"
"{{sfnm|1a1=Booth|1y=2000|1p"
"msterdam"
"s the"
"author"
"ction"
"t of "
" America"
"ed by "
```
We can then search for high-entropy keywords with encoding penalty 4:

Code from [TrieDemo.java:292](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L292) executed in 18.34 seconds: 
```java
      List<String> candidates = triePositive.max(node -> {
          return (node.getDepth() - _penalty) * (node.getCursorCount());
          //return (node.getDepth()-2) * Math.log((node.getCursorCount() - 5)/3);
      }, 1000)
              .map(x -> x.getString())
              .collect(Collectors.toList());
      List<String> filteredKeywords = new ArrayList<>();
      for(String keyword : candidates) {
          if(!filteredKeywords.stream().anyMatch(x->x.contains(keyword) || keyword.contains(x))) {
              filteredKeywords.add(keyword);
          }
      }
      return filteredKeywords.stream().map(x->'"'+x+'"').collect(Collectors.joining("\n"));
```

Returns: 
```
    " the "
" of the"
"<ref name="
"http://www."
" and "
"         "
"ref name=""
"}}</ref>"
".<ref name"
"<ref>{{cite "
"|publisher="
" &ndash; [["
"|url=http://"
"url=http://www"
"https://web.archive.org/web/20"
"|accessdate="
">{{cite web|url=http:/"
" in the"
"ation"
"style="text-align:center;""
"|title="
"https://books.google.com/books?id="
"]] &ndash; ["
"]]
[[Category:"
" style="text-align:center;"
"ref>{{cite web|url=http:"
".<ref>{{cite"
"archiveurl=https://web.archive.org/web/2"
" |publisher"
"|archiveurl=https://web.archive.org/web/"
"tion of "
"the [["
" |archiveurl=https://web.archive.org/web"
" that "
"</ref> "
"ttps://web.archive.org/web/200"
"
|- style="text-align:center"
">{{cite journal"
" with "
" |accessdate"
"</ref>

"
"tyle="text-align:center;"
"
"{{cite journal |"
"{{sfnm|1a1=Booth|1y=2000|1p"
" to the"
"   &ndash; ["
" from "
"American "
" which "
"s of "
".{{sfnm|1a1=Booth|1y=2000|1"
"url=https://books.google.com/books?id"
```
We can then search for high-entropy keywords with encoding penalty 5:

Code from [TrieDemo.java:292](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L292) executed in 18.26 seconds: 
```java
      List<String> candidates = triePositive.max(node -> {
          return (node.getDepth() - _penalty) * (node.getCursorCount());
          //return (node.getDepth()-2) * Math.log((node.getCursorCount() - 5)/3);
      }, 1000)
              .map(x -> x.getString())
              .collect(Collectors.toList());
      List<String> filteredKeywords = new ArrayList<>();
      for(String keyword : candidates) {
          if(!filteredKeywords.stream().anyMatch(x->x.contains(keyword) || keyword.contains(x))) {
              filteredKeywords.add(keyword);
          }
      }
      return filteredKeywords.stream().map(x->'"'+x+'"').collect(Collectors.joining("\n"));
```

Returns: 
```
    " of the "
"<ref name="
"http://www."
"                                      "
"ref name=""
".<ref name"
"<ref>{{cite "
"|publisher="
" &ndash; [["
"https://web.archive.org/web/20"
"|url=http://"
"}}</ref>"
"url=http://www"
" in the "
">{{cite web|url=http:/"
"style="text-align:center;""
"|accessdate="
"https://books.google.com/books?id="
" style="text-align:center;"
"ref>{{cite web|url=http:"
"archiveurl=https://web.archive.org/web/2"
" the [["
"]]
[[Category:"
"|archiveurl=https://web.archive.org/web/"
"]] &ndash; ["
".<ref>{{cite"
" |archiveurl=https://web.archive.org/web"
" |publisher"
"ttps://web.archive.org/web/200"
"
|- style="text-align:center"
">{{cite journal"
"tyle="text-align:center;"
"
" |accessdate"
"tion of "
"{{sfnm|1a1=Booth|1y=2000|1p"
"|title="
" and the "
"{{cite journal |"
" to the "
"url=https://books.google.com/books?id"
".{{sfnm|1a1=Booth|1y=2000|1"
"   &ndash; ["
"yle="text-align:center;"
!"
" from the "
"|url=https://books.google.com/books?i"
"ref>{{cite journa"
"</ref>

"
"|3a1=Kaczynski|3y=2010|3p"
```
We can then search for high-entropy keywords with encoding penalty 6:

Code from [TrieDemo.java:292](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L292) executed in 18.39 seconds: 
```java
      List<String> candidates = triePositive.max(node -> {
          return (node.getDepth() - _penalty) * (node.getCursorCount());
          //return (node.getDepth()-2) * Math.log((node.getCursorCount() - 5)/3);
      }, 1000)
              .map(x -> x.getString())
              .collect(Collectors.toList());
      List<String> filteredKeywords = new ArrayList<>();
      for(String keyword : candidates) {
          if(!filteredKeywords.stream().anyMatch(x->x.contains(keyword) || keyword.contains(x))) {
              filteredKeywords.add(keyword);
          }
      }
      return filteredKeywords.stream().map(x->'"'+x+'"').collect(Collectors.joining("\n"));
```

Returns: 
```
    "                                       "
"<ref name="
" of the "
"http://www."
"https://web.archive.org/web/20"
">{{cite web|url=http://"
"ref name=""
"<ref>{{cite "
"|url=http://www"
".<ref name"
"|publisher="
" &ndash; [["
"style="text-align:center;""
"https://books.google.com/books?id="
"ref>{{cite web|url=http:/"
"archiveurl=https://web.archive.org/web/2"
" style="text-align:center;"
"|accessdate="
"|archiveurl=https://web.archive.org/web/"
"}}</ref>"
" |archiveurl=https://web.archive.org/web"
"{{cite web|url=http://ww"
"]]
[[Category:"
"ttps://web.archive.org/web/200"
"
|- style="text-align:center"
"]] &ndash; ["
" in the "
".<ref>{{cite"
"tyle="text-align:center;"
"
" |publisher"
">{{cite journal"
"{{sfnm|1a1=Booth|1y=2000|1p"
" |accessdate"
"{{cite journal |"
"url=https://books.google.com/books?id"
".{{sfnm|1a1=Booth|1y=2000|1"
"yle="text-align:center;"
!"
"|url=https://books.google.com/books?i"
"|3a1=Kaczynski|3y=2010|3p"
"le="text-align:center;"
! "
"ref>{{cite journa"
"   &ndash; ["
```
We can then search for high-entropy keywords with encoding penalty 7:

Code from [TrieDemo.java:292](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L292) executed in 18.56 seconds: 
```java
      List<String> candidates = triePositive.max(node -> {
          return (node.getDepth() - _penalty) * (node.getCursorCount());
          //return (node.getDepth()-2) * Math.log((node.getCursorCount() - 5)/3);
      }, 1000)
              .map(x -> x.getString())
              .collect(Collectors.toList());
      List<String> filteredKeywords = new ArrayList<>();
      for(String keyword : candidates) {
          if(!filteredKeywords.stream().anyMatch(x->x.contains(keyword) || keyword.contains(x))) {
              filteredKeywords.add(keyword);
          }
      }
      return filteredKeywords.stream().map(x->'"'+x+'"').collect(Collectors.joining("\n"));
```

Returns: 
```
    "                                       "
"http://www."
"<ref name="
"https://web.archive.org/web/20"
">{{cite web|url=http://"
"|url=http://www"
"style="text-align:center;""
"<ref>{{cite web|url=http:/"
"https://books.google.com/books?id="
"archiveurl=https://web.archive.org/web/2"
" style="text-align:center;"
"|archiveurl=https://web.archive.org/web/"
"|publisher="
"ref name=""
" &ndash; [["
".<ref name"
" |archiveurl=https://web.archive.org/web"
"{{cite web|url=http://ww"
" of the "
"|accessdate="
"ttps://web.archive.org/web/200"
".<ref>{{cite "
"
|- style="text-align:center"
"]]
[[Category:"
"tyle="text-align:center;"
"
"]] &ndash; ["
"{{sfnm|1a1=Booth|1y=2000|1p"
">{{cite journal"
"url=https://books.google.com/books?id"
".{{sfnm|1a1=Booth|1y=2000|1"
"|url=https://books.google.com/books?i"
"{{cite journal |"
"yle="text-align:center;"
!"
" |publisher"
"<ref>{{cite journa"
"|3a1=Kaczynski|3y=2010|3p"
"le="text-align:center;"
! "
" |accessdate"
"
|style="font-size: 40px; font-family: serif;"|"
"{sfnm|1a1=Booth|1y=2000|1pp="
```
We can then search for high-entropy keywords with encoding penalty 8:

Code from [TrieDemo.java:292](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L292) executed in 18.46 seconds: 
```java
      List<String> candidates = triePositive.max(node -> {
          return (node.getDepth() - _penalty) * (node.getCursorCount());
          //return (node.getDepth()-2) * Math.log((node.getCursorCount() - 5)/3);
      }, 1000)
              .map(x -> x.getString())
              .collect(Collectors.toList());
      List<String> filteredKeywords = new ArrayList<>();
      for(String keyword : candidates) {
          if(!filteredKeywords.stream().anyMatch(x->x.contains(keyword) || keyword.contains(x))) {
              filteredKeywords.add(keyword);
          }
      }
      return filteredKeywords.stream().map(x->'"'+x+'"').collect(Collectors.joining("\n"));
```

Returns: 
```
    "                                        "
"https://web.archive.org/web/20"
">{{cite web|url=http://"
"|url=http://www."
"style="text-align:center;""
"<ref>{{cite web|url=http:/"
"https://books.google.com/books?id="
"archiveurl=https://web.archive.org/web/2"
"<ref name=""
"|archiveurl=https://web.archive.org/web/"
" style="text-align:center;"
".<ref name="
" |archiveurl=https://web.archive.org/web"
"{{cite web|url=http://www"
"ttps://web.archive.org/web/200"
"
|- style="text-align:center"
"]] &ndash; [["
"|publisher="
"tyle="text-align:center;"
"
".<ref>{{cite "
"]]
[[Category:"
"|accessdate="
"{{sfnm|1a1=Booth|1y=2000|1p"
"url=https://books.google.com/books?id"
">{{cite journal |"
".{{sfnm|1a1=Booth|1y=2000|1"
"|url=https://books.google.com/books?i"
"<ref>{{cite journal"
"yle="text-align:center;"
!"
"|3a1=Kaczynski|3y=2010|3p"
"le="text-align:center;"
! "
"
|style="font-size: 40px; font-family: serif;"|"
"{sfnm|1a1=Booth|1y=2000|1pp="
"   &ndash; [["
"ref name="Greenwood&Earnshaw"/>{{rp|"
```
We can then search for high-entropy keywords with encoding penalty 9:

Code from [TrieDemo.java:292](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L292) executed in 18.52 seconds: 
```java
      List<String> candidates = triePositive.max(node -> {
          return (node.getDepth() - _penalty) * (node.getCursorCount());
          //return (node.getDepth()-2) * Math.log((node.getCursorCount() - 5)/3);
      }, 1000)
              .map(x -> x.getString())
              .collect(Collectors.toList());
      List<String> filteredKeywords = new ArrayList<>();
      for(String keyword : candidates) {
          if(!filteredKeywords.stream().anyMatch(x->x.contains(keyword) || keyword.contains(x))) {
              filteredKeywords.add(keyword);
          }
      }
      return filteredKeywords.stream().map(x->'"'+x+'"').collect(Collectors.joining("\n"));
```

Returns: 
```
    "                                        "
"https://web.archive.org/web/20"
">{{cite web|url=http://"
"style="text-align:center;""
"<ref>{{cite web|url=http:/"
"https://books.google.com/books?id="
"|url=http://www."
"archiveurl=https://web.archive.org/web/2"
"|archiveurl=https://web.archive.org/web/"
" style="text-align:center;"
" |archiveurl=https://web.archive.org/web"
"{{cite web|url=http://www"
"ttps://web.archive.org/web/200"
"
|- style="text-align:center"
"tyle="text-align:center;"
"
".<ref name=""
"{{sfnm|1a1=Booth|1y=2000|1p"
".<ref>{{cite web|url=http:"
"url=https://books.google.com/books?id"
"|url=https://books.google.com/books?i"
"]] &ndash; [["
"]]
[[Category:"
".{{sfnm|1a1=Booth|1y=2000|1"
"yle="text-align:center;"
!"
"<ref>{{cite journal"
">{{cite journal |"
"le="text-align:center;"
! "
"|3a1=Kaczynski|3y=2010|3p"
"|accessdate="
"
|style="font-size: 40px; font-family: serif;"|"
"{sfnm|1a1=Booth|1y=2000|1pp="
"<ref name="Greenwood&Earnshaw"/>{{rp|"
"|publisher="
"
| style="vertical-align: top;" "
```
