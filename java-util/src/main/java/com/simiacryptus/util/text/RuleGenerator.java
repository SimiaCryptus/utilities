package com.simiacryptus.util.text;

import java.io.PrintStream;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RuleGenerator {

    private PrintStream verbose = null;
    private double minLeafWeight = 5;

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
            String split = categorizationSubstring(categories.values());
            if(split.isEmpty()) {
                return categorizationTree(categories,0);
            } else {
                Map<String, List<String>> lSet = categories.entrySet().stream().collect(
                        Collectors.toMap(e -> e.getKey(), e -> e.getValue().stream().filter(str -> str.contains(split))
                                .collect(Collectors.toList())));
                Map<String, List<String>> rSet = categories.entrySet().stream().collect(
                        Collectors.toMap(e -> e.getKey(), e -> e.getValue().stream().filter(str -> !str.contains(split))
                                .collect(Collectors.toList())));
                if(null!= verbose) verbose.println(String.format(indent + "\"%s\" -> %s\t%s", split,
                        lSet.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().size())),
                        rSet.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().size()))));
                Function<String, Map<String, Double>> l = categorizationTree(lSet, depth-1, indent + " ");
                Function<String, Map<String, Double>> r = categorizationTree(rSet, depth-1, indent + " ");
                return str -> {
                    if(str.contains(split)) {
                        return l.apply(str);
                    } else {
                        return r.apply(str);
                    }
                };
            }
        }
    }

    public String categorizationSubstring(Collection<List<String>> categories) {
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
        trie.index(10, 0);
        return categorizationSubstring(trie.root(), categoryMap, sum)
                .map(nodeInfo->nodeInfo.node.getString()).orElse("");
    }

    public PrintStream getVerbose() {
        return verbose;
    }

    public RuleGenerator setVerbose(PrintStream verbose) {
        this.verbose = verbose;
        return this;
    }

    private static class NodeInfo {
        IndexNode node;
        Map<Integer, Long> categoryWeights;
        double entropy;

        public NodeInfo(IndexNode node, Map<Integer, Long> categoryWeights, double entropy) {
            this.node = node;
            this.categoryWeights = categoryWeights;
            this.entropy = entropy;
        }
    }

    private NodeInfo info(IndexNode node, Map<Integer, Long> sum, Map<Integer, Integer> categoryMap) {
        Map<Integer, Long> summary = node.getCursors().collect(Collectors.groupingBy(x -> categoryMap.get(x.getDocumentId()), Collectors.counting()));
        return new NodeInfo(node, summary, entropy(sum, summary));
    }

    private NodeInfo info(IndexNode node, List<NodeInfo> children, Map<Integer, Long> sum) {
        Map<Integer, Long> summary = new HashMap<>();
        children.forEach(c->c.categoryWeights.forEach((key, value)->summary.put(key, summary.getOrDefault(key, 0l)+value)));
        return new NodeInfo(node, summary, entropy(sum, summary));
    }

    private Optional<NodeInfo> categorizationSubstring(IndexNode node, Map<Integer, Integer> categoryMap, Map<Integer, Long> sum) {
        List<NodeInfo> nodeInfoStream = node.getChildren().map(n -> categorizationSubstring(n, categoryMap, sum))
                .filter(x->x.isPresent()).map(x->x.get()).collect(Collectors.toList());
        NodeInfo info = nodeInfoStream.isEmpty()?info(node, sum, categoryMap):info(node, nodeInfoStream, sum);
        if(info.node.getString().isEmpty() || !Double.isFinite(info.entropy)) info = null;
        Stream<NodeInfo> concat = Stream.concat(null==info?Stream.empty():Stream.of(info), nodeInfoStream.stream());
        return concat.max(Comparator.comparing(x -> x.entropy));
    }

    private double entropy(Map<Integer, Long> sum, Map<Integer, Long> left) {
        int smoothing = 1;
        double sumSum = sum.values().stream().mapToDouble(x -> x).sum();
        double leftSum = left.values().stream().mapToDouble(x -> x).sum();
        double rightSum = sumSum - leftSum;
        if(rightSum < minLeafWeight) return -Double.MAX_VALUE;
        if(leftSum < minLeafWeight) return -Double.MAX_VALUE;
        return (sum.keySet().stream().mapToDouble(category->{
            Long leftCnt = left.getOrDefault(category, 0l);
            return leftCnt * Math.log((leftCnt + smoothing) * 1.0 /(leftSum + smoothing * sum.size()));
        }).sum() +
        sum.keySet().stream().mapToDouble(category->{
            Long rightCnt = sum.getOrDefault(category,0l) - left.getOrDefault(category, 0l);
            return rightCnt * Math.log((rightCnt + smoothing) * 1.0 /(rightSum + smoothing * sum.size()));
        }).sum()) / (sumSum * Math.log(2));
    }

}
