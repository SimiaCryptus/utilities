# Language Detection using prebuilt models
### English
Code from [TrieClassificationBlog.java:85](../../src/test/java/com/simiacryptus/util/text/TrieClassificationBlog.java#L85) executed in 78.85 seconds: 
```java
      sourceData.load()
              .map(x->x.getText()).filter(x -> x.length()> minArticleSize).limit(testingSize)
              .collect(Collectors.toList()).parallelStream()
              .map(x-> LanguageModel.match(x)).collect(Collectors.groupingBy(x->x,Collectors.counting()))
              .forEach((language, count)->
      {
          HashMap<String, Object> row = new HashMap<>();
          row.put("Source", sourceLanguage);
          row.put("Predicted", language.name());
          row.put("Count", count);
          table.putRow(row);
      });
```

### French
Code from [TrieClassificationBlog.java:85](../../src/test/java/com/simiacryptus/util/text/TrieClassificationBlog.java#L85) executed in 37.56 seconds: 
```java
      sourceData.load()
              .map(x->x.getText()).filter(x -> x.length()> minArticleSize).limit(testingSize)
              .collect(Collectors.toList()).parallelStream()
              .map(x-> LanguageModel.match(x)).collect(Collectors.groupingBy(x->x,Collectors.counting()))
              .forEach((language, count)->
      {
          HashMap<String, Object> row = new HashMap<>();
          row.put("Source", sourceLanguage);
          row.put("Predicted", language.name());
          row.put("Count", count);
          table.putRow(row);
      });
```

### German
Code from [TrieClassificationBlog.java:85](../../src/test/java/com/simiacryptus/util/text/TrieClassificationBlog.java#L85) executed in 31.73 seconds: 
```java
      sourceData.load()
              .map(x->x.getText()).filter(x -> x.length()> minArticleSize).limit(testingSize)
              .collect(Collectors.toList()).parallelStream()
              .map(x-> LanguageModel.match(x)).collect(Collectors.groupingBy(x->x,Collectors.counting()))
              .forEach((language, count)->
      {
          HashMap<String, Object> row = new HashMap<>();
          row.put("Source", sourceLanguage);
          row.put("Predicted", language.name());
          row.put("Count", count);
          table.putRow(row);
      });
```

### Summary
Code from [TrieClassificationBlog.java:75](../../src/test/java/com/simiacryptus/util/text/TrieClassificationBlog.java#L75) executed in 0.01 seconds: 
```java
      return table.toTextTable();
```

Returns: 
```
    Predicted | Count | Source
    --------- | ----- | ------
    English | 100 | English
    French  | 100 | French 
    French  | 8 | German 
    German  | 92 | German 
    
```
