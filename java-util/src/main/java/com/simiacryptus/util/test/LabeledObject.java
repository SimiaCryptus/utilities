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

import java.util.function.Function;

public class LabeledObject<T> {
  public final T data;
  public final String label;
  
  public LabeledObject(final T img, final String name) {
    super();
    this.data = img;
    this.label = name;
  }
  
  public <U> LabeledObject<U> map(final Function<T, U> f) {
    return new LabeledObject<U>(f.apply(this.data), this.label);
  }
  
}
