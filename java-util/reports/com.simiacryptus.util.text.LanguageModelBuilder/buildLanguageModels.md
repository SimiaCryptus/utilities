



## English
Code from [LanguageModelBuilder.java:35](../../src/test/java/com/simiacryptus/util/text/LanguageModelBuilder.java#L35) executed in 218.03 seconds: 
```java
      List<String> data = load.map(x -> x.getText()).filter(x -> x.length() > minArticleSize)
              .skip(100)
              .map(str->str.replaceAll("\\{\\{.*\\}\\}",""))
              .map(str->str.replaceAll("\\[\\[.*\\]\\]",""))
              .map(str->str.replaceAll("\\[[^\\]]*\\]",""))
              .map(str->str.replaceAll("\\{[^\\}]*\\}",""))
              .map(str->str.replaceAll("\\<[^\\>]*\\>",""))
              .limit(trainingSize).collect(Collectors.toList());
      CharTrie charTrie = CharTrieIndex.indexFulltext(data, maxLevels, minWeight);
      print(charTrie);
      return charTrie;
```
Logging: 
```
    Total Indexed Document (KB): 9871
    Total Node Count: 402511
    Total Index Memory Size (KB): 12289
    
```

Returns: 
```
    com.simiacryptus.util.text.CharTrieIndex@ada7a287
```
Code from [LanguageModelBuilder.java:48](../../src/test/java/com/simiacryptus/util/text/LanguageModelBuilder.java#L48) executed in 9.59 seconds: 
```java
      try(FileOutputStream fos = new FileOutputStream("src/main/resources/"+ languageName +".trie")) {
          byte[] serialized = CompressionUtil.encodeLZ(new ConvolutionalTrieSerializer().serialize(trie));
          System.out.println(String.format("Serialized tree to %s kb", serialized.length / 1024));
          fos.write(serialized);
      } catch (IOException e) {
          throw new RuntimeException(e);
      }
```
Logging: 
```
    Serialized tree to 379 kb
    
```





## French
Code from [LanguageModelBuilder.java:35](../../src/test/java/com/simiacryptus/util/text/LanguageModelBuilder.java#L35) executed in 217.85 seconds: 
```java
      List<String> data = load.map(x -> x.getText()).filter(x -> x.length() > minArticleSize)
              .skip(100)
              .map(str->str.replaceAll("\\{\\{.*\\}\\}",""))
              .map(str->str.replaceAll("\\[\\[.*\\]\\]",""))
              .map(str->str.replaceAll("\\[[^\\]]*\\]",""))
              .map(str->str.replaceAll("\\{[^\\}]*\\}",""))
              .map(str->str.replaceAll("\\<[^\\>]*\\>",""))
              .limit(trainingSize).collect(Collectors.toList());
      CharTrie charTrie = CharTrieIndex.indexFulltext(data, maxLevels, minWeight);
      print(charTrie);
      return charTrie;
```
Logging: 
```
    Total Indexed Document (KB): 8215
    Total Node Count: 331291
    Total Index Memory Size (KB): 12289
    
```

Returns: 
```
    com.simiacryptus.util.text.CharTrieIndex@3a00020b
```
Code from [LanguageModelBuilder.java:48](../../src/test/java/com/simiacryptus/util/text/LanguageModelBuilder.java#L48) executed in 8.48 seconds: 
```java
      try(FileOutputStream fos = new FileOutputStream("src/main/resources/"+ languageName +".trie")) {
          byte[] serialized = CompressionUtil.encodeLZ(new ConvolutionalTrieSerializer().serialize(trie));
          System.out.println(String.format("Serialized tree to %s kb", serialized.length / 1024));
          fos.write(serialized);
      } catch (IOException e) {
          throw new RuntimeException(e);
      }
```
Logging: 
```
    Serialized tree to 313 kb
    
```





## German
Code from [LanguageModelBuilder.java:35](../../src/test/java/com/simiacryptus/util/text/LanguageModelBuilder.java#L35) executed in 215.86 seconds: 
```java
      List<String> data = load.map(x -> x.getText()).filter(x -> x.length() > minArticleSize)
              .skip(100)
              .map(str->str.replaceAll("\\{\\{.*\\}\\}",""))
              .map(str->str.replaceAll("\\[\\[.*\\]\\]",""))
              .map(str->str.replaceAll("\\[[^\\]]*\\]",""))
              .map(str->str.replaceAll("\\{[^\\}]*\\}",""))
              .map(str->str.replaceAll("\\<[^\\>]*\\>",""))
              .limit(trainingSize).collect(Collectors.toList());
      CharTrie charTrie = CharTrieIndex.indexFulltext(data, maxLevels, minWeight);
      print(charTrie);
      return charTrie;
```
Logging: 
```
    Total Indexed Document (KB): 11157
    Total Node Count: 417846
    Total Index Memory Size (KB): 12289
    
```

Returns: 
```
    com.simiacryptus.util.text.CharTrieIndex@9a113bed
```
Code from [LanguageModelBuilder.java:48](../../src/test/java/com/simiacryptus/util/text/LanguageModelBuilder.java#L48) executed in 8.71 seconds: 
```java
      try(FileOutputStream fos = new FileOutputStream("src/main/resources/"+ languageName +".trie")) {
          byte[] serialized = CompressionUtil.encodeLZ(new ConvolutionalTrieSerializer().serialize(trie));
          System.out.println(String.format("Serialized tree to %s kb", serialized.length / 1024));
          fos.write(serialized);
      } catch (IOException e) {
          throw new RuntimeException(e);
      }
```
Logging: 
```
    Serialized tree to 395 kb
    
```

