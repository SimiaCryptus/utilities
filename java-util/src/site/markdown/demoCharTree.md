This will demonstrate how to use the CharTree class for PPM compression

First, we load some data into an index:
Code: 
    CharTree charTree = new CharTree();
    WikiArticle.load().limit(100).forEach(article -> {
      charTree.addDocument(article.text);
    });
    charTree.index(3,1);
    // Remove Cursor Data:
    charTree.truncate();
    // This must be called before using the tree as a PPM model:
    CharTree codecTree = charTree.addEscapeNodes();
    System.out.print("Tree Size: " + codecTree.getMemorySize());
    return codecTree.codec;
Returns: 
  com.simiacryptus.util.text.CharTreeCodec@b3d7190
Logging: 
  Tree Size: 2098176

Then, we compress data:
Code: 
    return WikiArticle.load().skip(100).findFirst().get();
Returns: 
  WikiArticle{title='Artificial languages'}
Code: 
    System.out.print("Topic: " + wikiArticle.text);
    Bits bits = codec.encodePPM(wikiArticle.text, 2);
    return bits.toBase64String();
Returns: 
  D1d1HjStmGlxXbFm6cey15Y4O6ObJxjvbh6GD0WrNkxIfWeDmXcEmWu7dDxiDwVzY3XLNlGTNV9kzTyggA==
Logging: 
  Topic: #REDIRECT [[Constructed language]]
  
  {{Redr|from alternative name|printworthy}}

And decompress to verify:
Code: 
    byte[] bytes = Base64.getDecoder().decode(compressed);
    return codec.decodePPM(bytes, 2);
Returns: 
  #REDIRECT [[Constructed language]]
  
  {{Redr|from alternative name|printworthy}}
