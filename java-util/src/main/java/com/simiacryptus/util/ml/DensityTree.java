/*
 * Copyright (c) 2017 by Andrew Charneski.
 *
 * The author licenses this file to you under the
 * Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance
 * with the License.  You may obtain a copy
 * of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.simiacryptus.util.ml;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DensityTree {
  
  private final String[] columnNames;
  private double minSplitFract = 0.05;
  private int splitSizeThreshold = 10;
  private double minFitness = 4.0;
  private int maxDepth = Integer.MAX_VALUE;
  
  public DensityTree(String... columnNames) {
    this.columnNames = columnNames;
  }
  
  public Bounds getBounds(double[][] points) {
    int dim = points[0].length;
    double[] max = IntStream.range(0, dim).mapToDouble(d -> {
      return Arrays.stream(points).mapToDouble(pt -> pt[d]).filter(x -> Double.isFinite(x)).max().orElse(Double.NaN);
    }).toArray();
    double[] min = IntStream.range(0, dim).mapToDouble(d -> {
      return Arrays.stream(points).mapToDouble(pt -> pt[d]).filter(x -> Double.isFinite(x)).min().orElse(Double.NaN);
    }).toArray();
    return new Bounds(max, min);
  }
  
  public double getMinSplitFract() {
    return minSplitFract;
  }
  
  public DensityTree setMinSplitFract(double minSplitFract) {
    this.minSplitFract = minSplitFract;
    return this;
  }
  
  public int getSplitSizeThreshold() {
    return splitSizeThreshold;
  }
  
  public DensityTree setSplitSizeThreshold(int splitSizeThreshold) {
    this.splitSizeThreshold = splitSizeThreshold;
    return this;
  }
  
  public String[] getColumnNames() {
    return columnNames;
  }
  
  public double getMinFitness() {
    return minFitness;
  }
  
  public DensityTree setMinFitness(double minFitness) {
    this.minFitness = minFitness;
    return this;
  }
  
  public int getMaxDepth() {
    return maxDepth;
  }
  
  public DensityTree setMaxDepth(int maxDepth) {
    this.maxDepth = maxDepth;
    return this;
  }
  
  public class Bounds {
    public final double[] max;
    public final double[] min;
    
    public Bounds(double[] max, double[] min) {
      this.max = max;
      this.min = min;
      assert (max.length == min.length);
      assert (IntStream.range(0, max.length).filter(i -> Double.isFinite(max[i])).allMatch(i -> max[i] >= min[i]));
    }
    
    public Bounds union(double[] pt) {
      int dim = pt.length;
      return new Bounds(IntStream.range(0, dim).mapToDouble(d -> {
        return Double.isFinite(pt[d]) ? Math.max(max[d], pt[d]) : max[d];
      }).toArray(), IntStream.range(0, dim).mapToDouble(d -> {
        return Double.isFinite(pt[d]) ? Math.min(min[d], pt[d]) : min[d];
      }).toArray());
    }
    
    public double getVolume() {
      int dim = min.length;
      return IntStream.range(0, dim).mapToDouble(d -> {
        return max[d] - min[d];
      }).filter(x -> Double.isFinite(x) && x > 0.0).reduce((a, b) -> a * b).orElse(Double.NaN);
    }
    
    public String toString() {
      return "[" + IntStream.range(0, min.length).mapToObj(d -> {
        return String.format("%s: %s - %s", columnNames[d], min[d], max[d]);
      }).reduce((a, b) -> a + "; " + b).get() + "]";
    }
    
  }
  
  public class OrthoRule extends Rule {
    private final int dim;
    private final double value;
    
    public OrthoRule(int dim, double value) {
      super(String.format("%s < %s", columnNames[dim], value));
      this.dim = dim;
      this.value = value;
    }
    
    @Override
    public boolean eval(double[] pt) {
      return pt[dim] < value;
    }
  }
  
  public abstract class Rule {
    public final String name;
    public double fitness;
    
    public Rule(String name) {
      this.name = name;
    }
    
    public abstract boolean eval(double[] pt);
    
    @Override
    public String toString() {
      return name;
    }
  }
  
  public class Node {
    public final double[][] points;
    public final Bounds bounds;
    private final int depth;
    private Node left = null;
    private Node right = null;
    private Rule rule = null;
    
    public Node(double[][] points) {
      this(points, 0);
    }
    
    public Node(double[][] points, int depth) {
      this.points = points;
      this.bounds = getBounds(points);
      this.depth = depth;
      split();
    }
    
    public int predict(double[] pt) {
      if (null == rule) return 0;
      else if (rule.eval(pt)) {
        return 1 + 2 * left.predict(pt);
      } else {
        return 0 + 2 * right.predict(pt);
      }
    }
    
    @Override
    public String toString() {
      return code();
    }
    
    public String code() {
      if (null != rule) {
        return String.format("// %s\nif(%s) { // Fitness %s\n  %s\n} else {\n  %s\n}",
            dataInfo(), rule, rule.fitness,
            left.code().replaceAll("\n", "\n  "),
            right.code().replaceAll("\n", "\n  "));
      } else {
        return "// " + dataInfo();
      }
    }
    
    private String dataInfo() {
      return String.format("Count: %s Volume: %s Region: %s", points.length, bounds.getVolume(), bounds);
    }
    
    public void split() {
      if (points.length <= splitSizeThreshold) return;
      if (maxDepth <= depth) return;
      this.rule = IntStream.range(0, points[0].length).mapToObj(x -> x).flatMap(dim -> split_ortho(dim)).filter(x -> Double.isFinite(x.fitness))
                      .max(Comparator.comparing(x -> x.fitness)).orElse(null);
      if (null == this.rule) return;
      double[][] leftPts = Arrays.stream(this.points).filter(pt -> rule.eval(pt)).toArray(i -> new double[i][]);
      double[][] rightPts = Arrays.stream(this.points).filter(pt -> !rule.eval(pt)).toArray(i -> new double[i][]);
      assert (leftPts.length + rightPts.length == this.points.length);
      if (rightPts.length == 0 || leftPts.length == 0) return;
      this.left = new Node(leftPts, depth + 1);
      this.right = new Node(rightPts, depth + 1);
    }
    
    public Stream<Rule> split_ortho(int dim) {
      double[][] sortedPoints = Arrays.stream(points).filter(pt -> Double.isFinite(pt[dim])).sorted(Comparator.comparing(pt -> pt[dim])).toArray(i -> new double[i][]);
      if (0 == sortedPoints.length) return Stream.empty();
      final int minSize = (int) Math.max(sortedPoints.length * minSplitFract, 1);
      Bounds[] left = new Bounds[sortedPoints.length];
      Bounds[] right = new Bounds[sortedPoints.length];
      left[0] = getBounds(new double[][]{sortedPoints[0]});
      right[sortedPoints.length - 1] = getBounds(new double[][]{sortedPoints[sortedPoints.length - 1]});
      for (int i = 1; i < sortedPoints.length; i++) {
        left[i] = left[i - 1].union(sortedPoints[i]);
        right[(sortedPoints.length - 1) - i] = right[((sortedPoints.length - 1) - (i - 1))].union(sortedPoints[(sortedPoints.length - 1) - i]);
      }
      return IntStream.range(1, sortedPoints.length - 1).filter(i -> {
        return sortedPoints[i - 1][dim] < sortedPoints[i][dim];
      }).mapToObj(i -> {
        int leftCount = i;
        int rightCount = sortedPoints.length - leftCount;
        if (minSize >= leftCount || minSize >= rightCount) return null;
        OrthoRule rule = new OrthoRule(dim, sortedPoints[i][dim]);
        Bounds l = left[i - 1];
        Bounds r = right[i];
        rule.fitness = -(leftCount * Math.log(l.getVolume() / Node.this.bounds.getVolume()) + rightCount * Math.log(r.getVolume() / Node.this.bounds.getVolume())) / (sortedPoints.length * Math.log(2));
        return (Rule) rule;
      }).filter(i -> null != i && i.fitness > minFitness);
    }
    
    public Rule getRule() {
      return rule;
    }
    
    protected Node setRule(Rule rule) {
      this.rule = rule;
      return this;
    }
    
    public Node getRight() {
      return right;
    }
    
    protected Node setRight(Node right) {
      this.right = right;
      return this;
    }
    
    public Node getLeft() {
      return left;
    }
    
    protected Node setLeft(Node left) {
      this.left = left;
      return this;
    }
    
    public int getDepth() {
      return depth;
    }
  }
}
