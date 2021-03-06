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

package com.simiacryptus.util.function;

import java.util.function.Supplier;

/**
 * The type Cached supplier.
 *
 * @param <T> the type parameter
 */
public class CachedSupplier<T> implements Supplier<T> {
  
  private final Supplier<T> fn;
  private volatile T cached;
  
  /**
   * Instantiates a new Cached supplier.
   *
   * @param fn the fn
   */
  public CachedSupplier(Supplier<T> fn) {
    this.fn = fn;
  }
  
  @Override
  public T get() {
    T obj = cached;
    if (null == obj) {
      synchronized (this) {
        obj = cached;
        if (null == obj) {
          obj = fn.get();
          cached = (obj);
        }
      }
    }
    return obj;
  }
}
