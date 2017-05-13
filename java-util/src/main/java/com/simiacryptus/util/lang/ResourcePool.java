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

package com.simiacryptus.util.lang;

public abstract class ResourcePool<T> {
  
  private final java.util.HashSet<T> all;
  private final java.util.concurrent.LinkedBlockingQueue<T> pool = new java.util.concurrent.LinkedBlockingQueue<>();
  private final int maxItems;
  
  public ResourcePool(int maxItems) {
    super();
    this.maxItems = maxItems;
    this.all = new java.util.HashSet<>(this.maxItems);
  }
  
  public abstract T create();
  
  public void with(final java.util.function.Consumer<T> f) {
    T poll = this.pool.poll();
    if (null == poll) {
      synchronized (this.all) {
        if (this.all.size() < this.maxItems) {
          poll = create();
          this.all.add(poll);
        }
      }
    }
    if (null == poll) {
      try {
        poll = this.pool.take();
      } catch (final InterruptedException e) {
        throw new java.lang.RuntimeException(e);
      }
    }
    f.accept(poll);
    this.pool.add(poll);
  }
}
