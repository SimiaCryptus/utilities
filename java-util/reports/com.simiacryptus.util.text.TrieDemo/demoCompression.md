This will demonstrate how to serialize a CharTrie class in compressed format


First, we decompose the text into an n-gram node:

Code from [TrieDemo.java:338](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L338) executed in 0.93 seconds: 
```java
      CharTrieIndex node = new CharTrieIndex();
      articleList.forEach(article -> {
          System.out.println(String.format("Indexing %s", article.getTitle()));
          node.addDocument(article.getText());
      });
      System.out.println(String.format("Indexing %s bytes of documents",
              node.getIndexedSize()));
      node.index(6, 1);
      return node;
```
Returns: 
```
    com.simiacryptus.text.CharTrieIndex@996d93e6
```
Logging: 
```
    Indexing A
    Indexing 19452 bytes of documents
    
```



Then, we compress the node:

Code from [TrieDemo.java:351](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L351) executed in 0.58 seconds: 
```java
      print(node);
      byte[] bytes = new FullTrieSerializer().serialize(node.copy());
      System.out.println(String.format("%s in ram, %s bytes in serialized form, %s%% compression",
              node.getMemorySize(), bytes.length, 100 - (bytes.length * 100.0 / node.getMemorySize())));
      return bytes;
```
Returns: 
```
    [B@5204062d
```
Logging: 
```
    Total Indexed Document (KB): 18
    Total Node Count: 21912
    Total Index Memory Size (KB): 768
    786432 in ram, 21699 bytes in serialized form, 97.24082946777344% compression
    
```

Then, we encode the data used to create the dictionary:

Code from [TrieDemo.java:359](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L359) executed in 2.95 seconds: 
```java
      PPMCodec codec = new PPMCodec(node);
      return articleList.stream().map(article -> {
          String text = article.getText();
          String title = article.getTitle();
          TimedResult<Bits> compressed = TimedResult.time(()->codec.encodePPM(text, Integer.MAX_VALUE));
          TimedResult<String> decompressed = TimedResult.time(()->codec.decodePPM(compressed.obj.getBytes(), Integer.MAX_VALUE));
          System.out.println(String.format("Serialized %s: %s chars -> %s bytes (%s%%) in %s sec; %s",
                  title, article.getText().length(), compressed.obj.bitLength / 8.0,
                  compressed.obj.bitLength * 100.0 / (8.0 * article.getText().length()),
                  compressed.timeNanos / 1000000000.0, text.equals(decompressed.obj)?"Verified":"Failed Validation"));
          return compressed.obj.getBytes();
      }).collect(Collectors.toList());
```
Returns: 
```
    [[B@768b970c]
```
Logging: 
```
    Serialized A: 19452 chars -> 4218.625 bytes (21.68735862636233%) in 1.547165841 sec; Verified
    
```

Compressed 19452 bytes of documents -> 25918 (21699 dictionary + 4219 ppm)

And decompress to verfy data:

Code from [TrieDemo.java:379](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L379) executed in 1.32 seconds: 
```java
      PPMCodec codec = new PPMCodec(node);
      compressedArticles.forEach(article -> {
          TimedResult<String> decompressed = TimedResult.time(()->codec.decodePPM(article, Integer.MAX_VALUE));
          System.out.println(String.format("Deserialized %s bytes -> %s chars in %s sec",
                  article.length, decompressed.obj.length(),
                  decompressed.timeNanos / 1000000000.0));
      });
```
Logging: 
```
    Deserialized 4219 bytes -> 19452 chars in 1.312705455 sec
    
```



And verify tree structure:

Code from [TrieDemo.java:389](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L389) executed in 0.63 seconds: 
```java
      CharTrie restored = new FullTrieSerializer().deserialize(serializedTrie);
      boolean verified = restored.root().equals(node.root());
      System.out.println(String.format("Tree Verified: %s", verified));
```
Logging: 
```
    Tree Verified: true
    
```

