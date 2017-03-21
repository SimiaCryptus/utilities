package com.simiacryptus.util.text;

import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TextAnalysis {

  public static final double DEFAULT_THRESHOLD = Math.log(15);
  private final CharTrie inner;
  private PrintStream verbose = null;

  TextAnalysis(CharTrie inner) {
    this.inner = inner;
  }

  public List<String> keywords(final String source) {
    Map<String, Long> wordCounts = split(source, DEFAULT_THRESHOLD).stream().collect(Collectors.groupingBy(x -> x, Collectors.counting()));
    wordCounts = aggregateKeywords(wordCounts);
    return wordCounts.entrySet().stream().filter(x->x.getValue()>1)
            .sorted(Comparator.comparing(x->-entropy(x.getKey()) * Math.pow(x.getValue(),0.3)))
            .map(e->{
              if(isVerbose()) verbose.println(String.format("KEYWORD: \"%s\" - %s * %.3f / %s", e.getKey(), e.getValue(), entropy(e.getKey()), e.getKey().length()));
              return e.getKey();
            }).collect(Collectors.toList());
  }

  private Map<String, Long> aggregateKeywords(Map<String, Long> wordCounts) {
    Map<String, Long> accumulator = new HashMap<>();
    wordCounts.forEach((key,count)->{
      boolean added = false;
      for(Map.Entry<String, Long> e : accumulator.entrySet()) {
        String combine = combine(key, e.getKey(), 4);
        if (null != combine) {
          accumulator.put(combine, e.getValue() + count);
          accumulator.remove(e.getKey());
          added = true;
          break;
        }
      }
      if(!added) {
        accumulator.put(key, count);
      }
    });
    if(wordCounts.size() > accumulator.size()) {
      return aggregateKeywords(accumulator);
    } else {
      return accumulator;
    }
  }

  public static String combine(String left, String right, int minOverlap) {
    if(left.length() < minOverlap) return null;
    if(right.length() < minOverlap) return null;
    int bestOffset = Integer.MAX_VALUE;
    for(int offset = minOverlap-left.length(); offset < right.length()-minOverlap; offset++) {
      boolean match = true;
      for(int posLeft = Math.max(0,-offset); posLeft < Math.min(left.length(),right.length()-offset); posLeft++) {
        if(left.charAt(posLeft) != right.charAt(posLeft+offset)) {
          match = false;
          break;
        }
      }
      if(match) {
        if(Math.abs(bestOffset) > Math.abs(offset)) bestOffset = offset;
      }
    }
    if(bestOffset < Integer.MAX_VALUE) {
      String combined = left;
      if(bestOffset > 0) {
        combined = right.substring(0,bestOffset) + combined;
      }
      if(left.length()+bestOffset<right.length()) {
        combined = combined + right.substring(left.length()+bestOffset);
      }
      return combined;
    } else {
      return null;
    }
  }

  public class WordSpelling {
    private final double[] linkNatsArray;
    private final List<TrieNode> leftNodes;
    private final List<TrieNode> rightNodes;
    private final String text;
    double sum = 0;

    public WordSpelling(final String source) {
      this.text = source;
      linkNatsArray = new double[source.length()];
      leftNodes = new ArrayList<>(source.length());
      rightNodes = new ArrayList<>(source.length());
      TrieNode priorNode = inner.root();
      double aposterioriNatsPrev = 0;
      for(int i=1;i<=source.length();i++){
        priorNode = priorNode.getContinuation(source.charAt(i-1));
        double aprioriNats = entropy(priorNode, priorNode.getParent());
        TrieNode followingNode = inner.traverse(source.substring(i-1,source.length()));
        leftNodes.add(priorNode);
        rightNodes.add(followingNode);
        double aposterioriNats = entropy(followingNode, followingNode.godparent());
        Map<Character, Long> code = getJointExpectation(priorNode, followingNode);
        double sumOfProduct = code.values().stream().mapToDouble(x1 -> x1).sum();
        double product = followingNode.getCursorCount()* priorNode.getCursorCount();
        double jointNats = -Math.log(product / sumOfProduct);
        linkNatsArray[i-1] = jointNats;
        sum += jointNats;
//        double linkNats = aprioriNats + aposterioriNatsPrev;
//        if(isVerbose()) verbose.println(String.format("%10s\t%10s\t%s",
//                '"' + priorNode.getString().replaceAll("\n","\\n") + '"',
//                '"' + followingNode.getString().replaceAll("\n","\\n") + '"',
//                Arrays.asList(aprioriNats, aposterioriNats, linkNats, jointNats
//                ).stream().map(x->String.format("%.4f",x)).collect(Collectors.joining("\t"))));
        aposterioriNatsPrev = aposterioriNats;
      }
      double sumLinkNats = Arrays.stream(linkNatsArray).sum();
      for(int i=0;i<linkNatsArray.length;i++) linkNatsArray[i] /= sumLinkNats;
    }

    public WordSpelling mutate() {
      return mutateAt(random.nextInt(linkNatsArray.length));
//      double fate = Math.random();
//      for (int i=0;i<linkNatsArray.length;i++) {
//        fate -= linkNatsArray[i];
//        if(fate < 0) {
//          //if(null!=verbose) verbose.print("MUTATE at " + i);
//          return mutateAt(i);
//        }
//      }
//      return this;
    }

    private final Random random = new Random();
    private WordSpelling mutateAt(int pos) {
      int fate = random.nextInt(6);
      //if(null!=verbose) verbose.print(" operation#" + fate);
      if(fate == 0) {
        return mutateDeletion(pos);
      } else if(fate == 1) {
        return mutateSubstitution(pos);
      } else if(fate == 2) {
        return mutateAddLeft(pos);
      } else if(fate == 3) {
        return mutateAddRight(pos);
      } else if(fate == 4) {
        return mutateSwapLeft(pos);
      } else if(fate == 5) {
        return mutateSwapRight(pos);
      } else {
        return this;
      }
    }

    private WordSpelling mutateSwapRight(int pos) {
      if(text.length()-1<=pos) return this;
      char[] charArray = text.toCharArray();
      char temp = charArray[pos + 1];
      charArray[pos + 1] = charArray[pos];
      charArray[pos] = temp;
      //if(null!=verbose) verbose.println("  swap right");
      return new WordSpelling(new String(charArray));
    }

    private WordSpelling mutateSwapLeft(int pos) {
      if(0>=pos) return this;
      char[] charArray = text.toCharArray();
      char temp = charArray[pos - 1];
      charArray[pos - 1] = charArray[pos];
      charArray[pos] = temp;
      //if(null!=verbose) verbose.println("  swap left");
      return new WordSpelling(new String(charArray));
    }

    private WordSpelling mutateAddRight(int pos) {
      char newChar = pick(getJointExpectation((text.length()-1<=pos)?inner.root():leftNodes.get(pos+1), rightNodes.get(pos)));
      //if(null!=verbose) verbose.println("  mutate right: " + newChar);
      return new WordSpelling(text.substring(0,pos)+newChar+text.substring(pos));
    }

    private WordSpelling mutateAddLeft(int pos) {
      char newChar = pick(getJointExpectation(leftNodes.get(pos), (0>=pos)?inner.root():rightNodes.get(pos-1)));
      //if(null!=verbose) verbose.println("  mutate left: " + newChar);
      return new WordSpelling(text.substring(0,pos)+newChar+text.substring(pos));
    }

    private WordSpelling mutateSubstitution(int pos) {
      char[] charArray = text.toCharArray();
      char newChar = pick(getJointExpectation(leftNodes.get(pos), rightNodes.get(pos)));
      charArray[pos] = newChar;
      //if(null!=verbose) verbose.println("  mutate in place: " + newChar);
      return new WordSpelling(new String(charArray));
    }

    private char pick(Map<Character, Long> weights) {
      weights.forEach((c,l)->weights.put(c,0==l?0l:1l));
      double sum = weights.values().stream().mapToLong(x -> x).sum();
      if(sum > 0) {
        double fate = random.nextDouble()*sum;
        for (Map.Entry<Character, Long> e : weights.entrySet()) {
          fate -= e.getValue();
          if(fate < 0) {
            return e.getKey();
          }
        }
      }
      return Character.MIN_VALUE;
    }

    private WordSpelling mutateDeletion(int pos) {
      //if(null!=verbose) verbose.println("  deletion");
      return new WordSpelling(text.substring(0,pos)+text.substring(pos+1));
    }


  }

  public double spelling(final String source) {
    assert(source.startsWith("|"));
    assert(source.endsWith("|"));
    WordSpelling original = new WordSpelling(source);
    WordSpelling corrected = IntStream.range(0,10).mapToObj(i->buildCorrection(original)).min(Comparator.comparingDouble(x->x.sum)).get();
    return corrected.sum;
  }

  private WordSpelling buildCorrection(WordSpelling wordSpelling) {
    int timesWithoutImprovement = 0;
    int maxCorrections = 4;
    int trials = 10;
    if(null!=verbose) verbose.println(String.format("START: \"%s\"\t%.5f", wordSpelling.text, wordSpelling.sum));
    while(timesWithoutImprovement++ < 100) {
      WordSpelling _wordSpelling = wordSpelling;
      WordSpelling mutant = IntStream.range(0, trials).mapToObj(i->_wordSpelling.mutate()).min(Comparator.comparingDouble(x->x.sum)).get();
      if(!mutant.text.startsWith("|")) continue;
      if(!mutant.text.endsWith("|")) continue;
      if(mutant.sum * 1.0 / mutant.text.length() < wordSpelling.sum * 1.0 / wordSpelling.text.length()) {
        if(null!=verbose) verbose.println(String.format("IMPROVEMENT: \"%s\"\t%.5f", mutant.text, mutant.sum));
        wordSpelling = mutant;
        timesWithoutImprovement = 0;
        if(maxCorrections-- <=0) break;
      } else {
        //if(null!=verbose) verbose.println(String.format("REJECT: \"%s\"\t%.5f", mutant.text, mutant.sum));
      }
      if(inner.contains(wordSpelling.text)) {
        if(null!=verbose) verbose.println(String.format("WORD: \"%s\"\t%.5f", mutant.text, mutant.sum));
        break;
      }
    }
    return wordSpelling;
  }

  public List<String> split(final String source, double threshold) {
    List<String> output = new ArrayList<>();
    int wordStart = 0;
    double aposterioriNatsPrev = 0;
    boolean isIncreasing = false;
    double prevLink = 0;
    for(int i=1;i<source.length();i++){
      String priorText = source.substring(0, i);
      TrieNode priorNode = getMaxentPrior(priorText);
      double aprioriNats = entropy(priorNode, priorNode.getParent());

      String followingText = source.substring(i-1, source.length());
      TrieNode followingNode = getMaxentPost(followingText);
      TrieNode godparent = followingNode.godparent();
      double aposterioriNats = entropy(followingNode, godparent);

      //double jointNats = getJointNats(priorNode, followingNode);
      double linkNats = aprioriNats + aposterioriNatsPrev;
      if(isVerbose()) verbose.println(String.format("%10s\t%10s\t%s",
              '"' + priorNode.getString().replaceAll("\n","\\n") + '"',
              '"' + followingNode.getString().replaceAll("\n","\\n") + '"',
              Arrays.asList(aprioriNats, aposterioriNats, linkNats
              ).stream().map(x->String.format("%.4f",x)).collect(Collectors.joining("\t"))));
      String word = i<2?"":source.substring(wordStart, i-2);
      if(isIncreasing && linkNats < prevLink && prevLink > threshold && word.length() > 2) {
        wordStart = i-2;
        output.add(word);
        if(isVerbose()) verbose.println(String.format("Recognized token \"%s\"", word));
        prevLink = linkNats;
        aposterioriNatsPrev = aposterioriNats;
        isIncreasing = false;
      } else {
        if(linkNats > prevLink) isIncreasing = true;
        prevLink = linkNats;
        aposterioriNatsPrev = aposterioriNats;
      }
    }
    return output;
  }

  private TrieNode getMaxentPost(String followingText) {
    TrieNode followingNode = this.inner.traverse(followingText);
    TrieNode godparent1 = followingNode.godparent();
    double aposterioriNats1 = entropy(followingNode, godparent1);
    while(followingText.length() > 1) {
      String followingText2 = followingText.substring(0, followingText.length()-1);
      TrieNode followingNode2 = this.inner.traverse(followingText2);
      TrieNode godparent2 = followingNode2.godparent();
      double aposterioriNats2 = entropy(followingNode2, godparent2);
      if(aposterioriNats2 < aposterioriNats1) {
        aposterioriNats1 = aposterioriNats2;
        followingNode = followingNode2;
        followingText = followingText2;
      } else break;
    }
    return followingNode;
  }

  private TrieNode getMaxentPrior(String priorText) {
    TrieNode priorNode = this.inner.matchEnd(priorText);
    double aprioriNats1 = entropy(priorNode, priorNode.getParent());
    while(priorText.length() > 1) {
      String priorText2 = priorText.substring(1);
      TrieNode priorNode2 = this.inner.matchEnd(priorText2);
      double aprioriNats2 = entropy(priorNode2, priorNode2.getParent());
      if(aprioriNats2 < aprioriNats1) {
        aprioriNats1 = aprioriNats2;
        priorText = priorText2;
        priorNode = priorNode2;
      } else break;
    }
    return priorNode;
  }

  private double getJointNats(TrieNode priorNode, TrieNode followingNode) {
    Map<Character, Long> code = getJointExpectation(priorNode, followingNode);
    double sumOfProduct = code.values().stream().mapToDouble(x->x).sum();
    double product = followingNode.getCursorCount()* priorNode.getCursorCount();
    return -Math.log(product / sumOfProduct);
  }

  private Map<Character, Long> getJointExpectation(TrieNode priorNode, TrieNode followingNode) {
    String followingString = followingNode.getString();
    String postContext = followingString.isEmpty()?"":followingString.substring(1);
    return inner.tokens().stream().collect(Collectors.toMap(x -> x, token -> {
      TrieNode altFollowing = inner.traverse(token + postContext);
      long a = altFollowing.getString().equals(token + postContext) ? altFollowing.getCursorCount() : 0;
      TrieNode parent = priorNode.getParent();
      long b = null==parent?0:parent.getChild(token).map(x -> x.getCursorCount()).orElse(0l);
      return a * b;
    }));
  }

  private static double entropy(TrieNode tokenNode, TrieNode contextNode) {
    return -0.0 + (null == contextNode ? Double.POSITIVE_INFINITY : (- Math.log(tokenNode.getCursorCount() * 1.0 / contextNode.getCursorCount())));
  }

  public double entropy(final String source) {
    double output = 0;
    for(int i=1;i<source.length();i++){
      TrieNode node = this.inner.matchEnd(source.substring(0, i));
      Optional<? extends TrieNode> child = node.getChild(source.charAt(i));
      while(!child.isPresent()) {
        output += Math.log(1.0 / node.getCursorCount());
        node = node.godparent();
        child = node.getChild(source.charAt(i));
      }
      output += Math.log(child.get().getCursorCount() * 1.0 / node.getCursorCount());
    }
    return - output / Math.log(2);
  }

  public boolean isVerbose() {
    return null!=verbose;
  }

  public TextAnalysis setVerbose(PrintStream verbose) {
    this.verbose = verbose;
    return this;
  }

  public List<String> split(String text) {
    return split(text, DEFAULT_THRESHOLD);
  }
}
