/*
 * Copyright (c) 2018 by Andrew Charneski.
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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Supplier;

/**
 * The type Monitored object.
 */
public class MonitoredObject implements MonitoredItem {
  
  private final Map<String, Object> items = new HashMap<>();
  
  /**
   * Add const monitored object.
   *
   * @param key  the key
   * @param item the item
   * @return the monitored object
   */
  @javax.annotation.Nonnull
  public com.simiacryptus.util.MonitoredObject addConst(final String key, final Object item) {
    items.put(key, item);
    return this;
  }
  
  /**
   * Add field monitored object.
   *
   * @param key  the key
   * @param item the item
   * @return the monitored object
   */
  @javax.annotation.Nonnull
  public com.simiacryptus.util.MonitoredObject addField(final String key, final Supplier<Object> item) {
    items.put(key, item);
    return this;
  }
  
  /**
   * Add obj monitored object.
   *
   * @param key  the key
   * @param item the item
   * @return the monitored object
   */
  @javax.annotation.Nonnull
  public com.simiacryptus.util.MonitoredObject addObj(final String key, final MonitoredItem item) {
    items.put(key, item);
    return this;
  }
  
  /**
   * Clear constants monitored object.
   *
   * @return the monitored object
   */
  @javax.annotation.Nonnull
  public com.simiacryptus.util.MonitoredObject clearConstants() {
    @javax.annotation.Nonnull final HashSet<String> keys = new HashSet<>(items.keySet());
    for (final String k : keys) {
      final Object v = items.get(k);
      if (v instanceof com.simiacryptus.util.MonitoredObject) {
        ((com.simiacryptus.util.MonitoredObject) v).clearConstants();
      }
      else if (!(v instanceof Supplier) && !(v instanceof MonitoredItem)) {
        items.remove(k);
      }
    }
    return this;
  }
  
  @javax.annotation.Nonnull
  @Override
  public Map<String, Object> getMetrics() {
    @javax.annotation.Nonnull final HashMap<String, Object> returnValue = new HashMap<>();
    items.entrySet().stream().parallel().forEach(e -> {
      final String k = e.getKey();
      final Object v = e.getValue();
      if (v instanceof MonitoredItem) {
        returnValue.put(k, ((MonitoredItem) v).getMetrics());
      }
      else if (v instanceof Supplier) {
        returnValue.put(k, ((Supplier<?>) v).get());
      }
      else {
        returnValue.put(k, v);
      }
    });
    return returnValue;
  }
}
