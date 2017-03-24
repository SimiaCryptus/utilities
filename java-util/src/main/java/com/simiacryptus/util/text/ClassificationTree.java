package com.simiacryptus.util.text;

import java.io.PrintStream;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassificationTree {

    private PrintStream verbose = null;
    private double minLeafWeight = 5;
    private int maxLevels = 10;
    private int minWeight = 3;
    private double depthBias = 0.001;
    private int smoothing = 3;

    public Function<String,Map<String,Double>> categorizationTree(Map<String,List<String>> categories, int depth) {
        return categorizationTree(categories, depth,"");
    }

    private Function<String,Map<String,Double>> categorizationTree(Map<String,List<String>> categories, int depth, String indent) {
        if(0 == depth) {
            return str -> {
                int sum = categories.values().stream().mapToInt(x -> x.size()).sum();
                return categories.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().size() * 1.0 / sum));
            };
        } else {
            if(1 >= categories.values().stream().filter(x->!x.isEmpty()).count()) {
                return categorizationTree(categories,0,indent);
            }
            Optional<NodeInfo> info = categorizationSubstring(categories.values());
            if(!info.isPresent()) return categorizationTree(categories,0,indent);
            String split = info.get().node.getString();
            Map<String, List<String>> lSet = categories.entrySet().stream().collect(
                    Collectors.toMap(e -> e.getKey(), e -> e.getValue().stream().filter(str -> str.contains(split))
                            .collect(Collectors.toList())));
            Map<String, List<String>> rSet = categories.entrySet().stream().collect(
                    Collectors.toMap(e -> e.getKey(), e -> e.getValue().stream().filter(str -> !str.contains(split))
                            .collect(Collectors.toList())));
            int lSum = lSet.values().stream().mapToInt(x -> x.size()).sum();
            int rSum = rSet.values().stream().mapToInt(x -> x.size()).sum();
            if(0 == lSum || 0 == rSum) {
                return categorizationTree(categories,0,indent);
            }
            if(null!= verbose) verbose.println(String.format(indent + "\"%s\" -> Contains=%s\tAbsent=%s\tEntropy=%s", split,
                    lSet.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().size())),
                    rSet.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().size())),
                    info.get().entropy));
            Function<String, Map<String, Double>> l = categorizationTree(lSet, depth-1, indent + "  ");
            Function<String, Map<String, Double>> r = categorizationTree(rSet, depth-1, indent + "  ");
            return str -> {
                if(str.contains(split)) {
                    return l.apply(str);
                } else {
                    return r.apply(str);
                }
            };
        }
    }

    private double entropy(Map<Integer, Long> sum, Map<Integer, Long> left) {
        double sumSum = sum.values().stream().mapToDouble(x -> x).sum();
        double leftSum = left.values().stream().mapToDouble(x -> x).sum();
        double rightSum = sumSum - leftSum;
        //System.err.println(String.format("%s & %s", sum, left));
        if(rightSum < minLeafWeight) return Double.NEGATIVE_INFINITY;
        if(leftSum < minLeafWeight) return Double.NEGATIVE_INFINITY;
        return (sum.keySet().stream().mapToDouble(category->{
            Long leftCnt = left.getOrDefault(category, 0l);
            return leftCnt * Math.log((leftCnt + smoothing) * 1.0 /(leftSum + smoothing * sum.size()));
        }).sum() +
                sum.keySet().stream().mapToDouble(category->{
                    Long rightCnt = sum.getOrDefault(category,0l) - left.getOrDefault(category, 0l);
                    return rightCnt * Math.log((rightCnt + smoothing) * 1.0 /(rightSum + smoothing * sum.size()));
                }).sum()) / (sumSum * Math.log(2));
    }

    private Optional<NodeInfo> categorizationSubstring(Collection<List<String>> categories) {
        CharTrieIndex trie = new CharTrieIndex();
        Map<Integer, Integer> categoryMap = new TreeMap<>();
        int categoryNumber = 0;
        Map<Integer, Long> sum = new HashMap<>();
        for(List<String> category : categories) {
            categoryNumber += 1;
            for(String text : category) {
                sum.put(categoryNumber, sum.getOrDefault(categoryNumber, 0l)+text.length()+1);
                categoryMap.put(trie.addDocument(text), categoryNumber);
            }
        }
        trie.index(maxLevels, minWeight);
        sum = summarize(trie.root(), categoryMap);
        return categorizationSubstring(trie.root(), categoryMap, sum);
    }

    public PrintStream getVerbose() {
        return verbose;
    }

    public ClassificationTree setVerbose(PrintStream verbose) {
        this.verbose = verbose;
        return this;
    }

    private class NodeInfo {
        IndexNode node;
        Map<Integer, Long> categoryWeights;
        double entropy;

        public NodeInfo(IndexNode node, Map<Integer, Long> categoryWeights, double entropy) {
            this.node = node;
            this.categoryWeights = categoryWeights;
            this.entropy = entropy + depthBias * node.getDepth();
        }
    }

    private NodeInfo info(IndexNode node, Map<Integer, Long> sum, Map<Integer, Integer> categoryMap) {
        Map<Integer, Long> summary = summarize(node, categoryMap);
        return new NodeInfo(node, summary, entropy(sum, summary));
    }

    private Map<Integer, Long> summarize(IndexNode node, Map<Integer, Integer> categoryMap) {
        return node.getCursors().map(x -> x.getDocumentId())
                .distinct()
                .map(x ->categoryMap.get(x))
                .collect(Collectors.toList()).stream()
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()));
    }

    private Optional<NodeInfo> categorizationSubstring(IndexNode node, Map<Integer, Integer> categoryMap, Map<Integer, Long> sum) {
        List<NodeInfo> childrenInfo = node.getChildren().map(n -> categorizationSubstring(n, categoryMap, sum))
                .filter(x->x.isPresent()).map(x->x.get()).collect(Collectors.toList());
        NodeInfo info = info(node, sum, categoryMap);
        if(info.node.getString().isEmpty() || !Double.isFinite(info.entropy)) info = null;
        Optional<NodeInfo> max = Stream.concat(null==info?Stream.empty():Stream.of(info), childrenInfo.stream())
                .max(Comparator.comparing(x -> x.entropy));
        return max;
    }

}
