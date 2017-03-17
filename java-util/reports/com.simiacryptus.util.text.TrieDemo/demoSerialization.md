This will demonstrate how to serialize a CharTrie class in compressed format


First, we load some data into an index:

Code from [TrieDemo.java:404](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L404) executed in 2.10 seconds: 
```java
      CharTrieIndex charTrieIndex = new CharTrieIndex();
      WikiArticle.load().limit(1).forEach(article -> {
          charTrieIndex.addDocument(article.getText());
      });
      System.out.println(String.format("Indexing %s bytes of documents",
              charTrieIndex.getIndexedSize()));
      charTrieIndex.index(6, 0);
      return charTrieIndex.recomputeCursorDetails();
```
Returns: 
```
    com.simiacryptus.util.text.CharTrieIndex@3e19f82c
```
Logging: 
```
    Indexing 56 bytes of documents
    
```



Then, we compress the tree:

Code from [TrieDemo.java:417](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L417) executed in 0.06 seconds: 
```java
      byte[] bytes = new FullTrieSerializer().serialize(tree.copy());
      System.out.println(String.format("%s in ram, %s bytes in serialized form, %s%% compression",
              tree.getMemorySize(), bytes.length, 100 - (bytes.length * 100.0 / tree.getMemorySize())));
      return Base64.getEncoder().encodeToString(bytes);
```
Returns: 
```
    HAAAAQAgBQAjAQBBAQBDAwBEAQBFAgBJAQBSAwBUAQBbAgBdAgBhBgBjAQBlAwBmAgBnAgBpAgBsAgBtAgBuAQBvAgByAgBzAwB0AQB1AQB7AgB9AgAAAACgoUoDAMAAYAzAMLgGoZjA1AGAAFLm2AABQowAAAwABQAwALhQGoAGAAGABQMAAAYAFKYAAMADAKAADwAAADb237f7P34fvfj954ez9j/Z7///f//////////v/f///7//////////////////////////////////AAAAAAAAAA==
```
Logging: 
```
    12288 in ram, 217 bytes in serialized form, 98.23404947916667% compression
    
```



And decompress to verify:

Code from [TrieDemo.java:425](../../src/test/java/com/simiacryptus/util/text/TrieDemo.java#L425) executed in 0.05 seconds: 
```java
      byte[] bytes = Base64.getDecoder().decode(serialized);
      CharTrie restored = new FullTrieSerializer().deserialize(bytes);
      boolean verified = restored.root().equals(tree.root());
      System.out.println(String.format("Tree Verified: %s", verified));
      return bytes.length;
```
Returns: 
```
    217
```
Logging: 
```
    Tree Verified: true
    
```

Then, we encode the data used to create the dictionary:

