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

package com.simiacryptus.util.test;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Andrew Charneski on 3/11/2017.
 */
public class AsyncListIterator<T> implements Iterator<T> {
  private final List<T> queue;
  private final Thread thread;
  int index = -1;
  
  public AsyncListIterator(List<T> queue, Thread thread) {
    this.thread = thread;
    this.queue = queue;
  }
  
  @Override
  public boolean hasNext() {
    return index < queue.size() || thread.isAlive();
  }
  
  @Override
  public T next() {
    try {
      while (hasNext()) {
        if (++index < queue.size()) {
          return queue.get(index);
        } else {
          Thread.sleep(100);
        }
      }
      return null;
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
  
  @Override
  protected void finalize() throws Throwable {
    super.finalize();
  }
}
