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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ScalarStatistics implements MonitoredItem {
  
  private final static double zeroTol = 1e-20;
  int zeros = 0;
  int negatives = 0;
  int positives = 0;
  double sum0 = 0;
  double sum1 = 0;
  double sum2 = 0;
  double sumLog = 0;
  double min = Double.POSITIVE_INFINITY;
  double max = -Double.POSITIVE_INFINITY;
  
  public void add(double v) {
    sum0 += 1;
    sum1 += v;
    sum2 += v * v;
    min = Math.min(min, v);
    max = Math.max(max, v);
    if (Math.abs(v) < zeroTol) {
      zeros++;
    } else {
      if (v < 0) {
        negatives++;
      } else {
        positives++;
      }
      sumLog += Math.log(Math.abs(v)) / Math.log(10);
    }
  
  }
  
  @Override
  public Map<String, Object> getMetrics() {
    HashMap<String, Object> map = new HashMap<>();
    map.put("count", sum0);
    map.put("negative", negatives);
    map.put("positive", positives);
    map.put("min", min);
    map.put("max", max);
    map.put("mean", sum1 / sum0);
    map.put("stdDev", Math.sqrt(Math.abs(Math.pow(sum1 / sum0, 2) - sum2 / sum0)));
    map.put("meanExponent", sumLog / (sum0 - zeros));
    map.put("zeros", zeros);
    return map;
  }
  
  public void clear() {
    min = Double.POSITIVE_INFINITY;
    max = -Double.POSITIVE_INFINITY;
    negatives = 0;
    positives = 0;
    zeros = 0;
    sum0 = 0;
    sum1 = 0;
    sum2 = 0;
    sumLog = 0;
  }
  
  public static ScalarStatistics stats(double[] data) {
    ScalarStatistics statistics = new ScalarStatistics();
    Arrays.stream(data).forEach(statistics::add);
    return statistics;
  }
}
