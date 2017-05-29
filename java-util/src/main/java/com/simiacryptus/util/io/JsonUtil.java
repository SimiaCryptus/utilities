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

package com.simiacryptus.util.io;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import com.simiacryptus.util.lang.JsonFormattable;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.stream.IntStream;

public class JsonUtil {
  
  public static JsonArray getJson(int[] kernelDims) {
    JsonArray array = new JsonArray();
    for(int k : kernelDims) array.add(new JsonPrimitive(k));
    return array;
  }
  
  public static int[] getIntArray(JsonArray array) {
    return IntStream.range(0, array.size()).map(i->array.get(i).getAsInt()).toArray();
  }
  
  public static JsonArray getJson(double[] kernelDims) {
    JsonArray array = new JsonArray();
    for(double k : kernelDims) array.add(new JsonPrimitive(k));
    return array;
  }
  
  public static double[] getDoubleArray(JsonArray array) {
    return IntStream.range(0, array.size()).mapToDouble(i->array.get(i).getAsDouble()).toArray();
  }
}
