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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
  
  public void add(double... values) {
    Arrays.stream(values).parallel().forEach(this::add);
  }
  
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
    map.put("mean", getMean());
    map.put("stdDev", getStdDev());
    map.put("meanExponent", getMeanPower());
    map.put("zeros", zeros);
    return map;
  }
  
  public double getMeanPower() {
    return sumLog / (sum0 - zeros);
  }
  
  public double getStdDev() {
    return Math.sqrt(Math.abs(Math.pow(getMean(), 2) - sum2 / sum0));
  }
  
  public double getMean() {
    return sum1 / sum0;
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
  
  public double getCount() {
    return sum0;
  }
  
  public JsonObject getJson() {
    JsonObject json = new JsonObject();
    json.addProperty("min",min);
    json.addProperty("max",max);
    json.addProperty("negatives",negatives);
    json.addProperty("positives",positives);
    json.addProperty("zeros",zeros);
    json.addProperty("sum0",sum0);
    json.addProperty("sum1",sum1);
    json.addProperty("sum2",sum2);
    json.addProperty("sumLog",sumLog);
    return json;
  }
  
  public void readJson(JsonObject json) {
    this.min = json.get("min").getAsDouble();
    this.max = json.get("max").getAsDouble();
    this.negatives = json.get("negatives").getAsInt();
    this.positives = json.get("positives").getAsInt();
    this.zeros = json.get("zeros").getAsInt();
    this.sum0 = json.get("sum0").getAsDouble();
    this.sum1 = json.get("sum1").getAsDouble();
    this.sum2 = json.get("sum2").getAsDouble();
    this.sumLog = json.get("sumLog").getAsDouble();
  }
}
