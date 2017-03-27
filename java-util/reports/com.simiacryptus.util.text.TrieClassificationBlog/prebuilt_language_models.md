# Language Detection using prebuilt models
### English
Loading 100 articles of English to test language classification...

Code from [TrieClassificationBlog.java:89](../../src/test/java/com/simiacryptus/util/text/TrieClassificationBlog.java#L89) executed in 92.31 seconds: 
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
Loading 100 articles of French to test language classification...

Code from [TrieClassificationBlog.java:89](../../src/test/java/com/simiacryptus/util/text/TrieClassificationBlog.java#L89) executed in 42.82 seconds: 
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
Loading 100 articles of German to test language classification...

Code from [TrieClassificationBlog.java:89](../../src/test/java/com/simiacryptus/util/text/TrieClassificationBlog.java#L89) executed in 40.97 seconds: 
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

### Results
Predicted | Count | Source
--------- | ----- | ------
English | 100 | English
French  | 100 | French 
French  | 9 | German 
German  | 91 | German 


