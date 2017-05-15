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

import com.simiacryptus.util.io.AsyncListIterator;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public abstract class DataLoader<T> {
  private final List<T> queue = Collections.synchronizedList(new ArrayList<>());
  private volatile Thread thread;
  
  public void clear() throws InterruptedException {
    if (thread != null) {
      synchronized (this) {
        if (thread != null) {
          thread.interrupt();
          thread.join();
          thread = null;
          queue.clear();
        }
      }
    }
  }
  
  public Stream<T> stream() {
    if (thread == null) {
      synchronized (this) {
        if (thread == null) {
          thread = new Thread(()->read(queue));
          thread.setDaemon(true);
          thread.start();
        }
      }
    }
    Iterator<T> iterator = new AsyncListIterator<>(queue, thread);
    return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.DISTINCT), false).filter(x -> x != null);
  }
  
  protected abstract void read(List<T> queue);
  
}
