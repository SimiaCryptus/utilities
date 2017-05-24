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

package com.simiacryptus.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

public class ArrayUtil {
  
  public static List<double[]> minus(List<double[]> a, List<double[]> b) {
    return op(a, b, (x, y) -> x - y);
  }
  
  public static List<double[]> add(List<double[]> a, List<double[]> b) {
    return op(a, b, (x, y) -> x + y);
  }
  
  public static double dot(List<double[]> a, List<double[]> b) {
    return sum(multiply(a, b));
  }
  
  public static List<double[]> multiply(List<double[]> a, List<double[]> b) {
    return op(a, b, (x, y) -> x * y);
  }
  
  public static List<double[]> multiply(List<double[]> a, double b) {
    return op(a, x -> x * b);
  }
  
  public static double[] add(double[] a, double[] b) {
    return op(a, b, (x,y) -> x + y);
  }
  public static double[] subtract(double[] a, double[] b) {
    return op(a, b, (x,y) -> x - y);
  }
  public static double[] multiply(double[] a, double[] b) {
    return op(a, b, (x,y) -> x * y);
  }
  public static double dot(double[] a, double[] b) {
    return sum(op(a, b, (x,y) -> x * y));
  }
  
  private static double sum(double[] op) {
    return Arrays.stream(op).sum();
  }
  
  public static double[] multiply(double[] a, double b) {
    return op(a, (x) -> x * b);
  }
  public static double[] sum(double[] a, double b) {
    return op(a, (x) -> x + b);
  }
  
  public static double sum(List<double[]> a) {
    return a.stream().mapToDouble(x -> Arrays.stream(x).sum()).sum();
  }
  
  public static List<double[]> op(List<double[]> a, List<double[]> b, DoubleBinaryOperator fn) {
    assert (a.size() == b.size());
    ArrayList<double[]> list = new ArrayList<>();
    for (int i = 0; i < a.size(); i++) {
      assert (a.get(i).length == b.get(i).length);
      double[] c = new double[a.get(i).length];
      for (int j = 0; j < a.get(i).length; j++) {
        c[j] = fn.applyAsDouble(a.get(i)[j], b.get(i)[j]);
      }
      list.add(c);
    }
    return list;
  
  }
  
  public static double[] op(double[] a, double[] b, DoubleBinaryOperator fn) {
    assert (a.length == b.length);
    double[] c = new double[a.length];
    for (int j = 0; j < a.length; j++) {
      c[j] = fn.applyAsDouble(a[j], b[j]);
    }
    return c;
  }
  
  public static List<double[]> op(List<double[]> a, DoubleUnaryOperator fn) {
    ArrayList<double[]> list = new ArrayList<>();
    for (int i = 0; i < a.size(); i++) {
      double[] c = new double[a.get(i).length];
      for (int j = 0; j < a.get(i).length; j++) {
        c[j] = fn.applyAsDouble(a.get(i)[j]);
      }
      list.add(c);
    }
    return list;
  }
  
  public static double[] op(double[] a, DoubleUnaryOperator fn) {
    double[] c = new double[a.length];
    for (int j = 0; j < a.length; j++) {
      c[j] = fn.applyAsDouble(a[j]);
    }
    return c;
  }
  
}
