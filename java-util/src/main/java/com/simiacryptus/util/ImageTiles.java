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


import com.simiacryptus.util.ml.Tensor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ImageTiles {
  
  public static Tensor[] tilesRgb(BufferedImage image, int width, int height) {
    return tilesRgb(image, width, height, false);
  }
  
  public static Tensor[] tilesRgb(BufferedImage image, int width, int height, boolean overlap) {
    return tilesRgb(image, width, height, overlap?1:width, overlap?1:height);
  }
  
  public static Tensor[] tilesRgb(BufferedImage image, int width, int height, int xStep, int yStep) {
    List<Tensor> tensors = new ArrayList<>();
    for (int y = 0; y < image.getHeight(); y += yStep) {
      for (int x = 0; x < image.getWidth(); x += xStep) {
        try {
          Tensor tensor = new Tensor(width, height, 3);
          for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
              Color rgb = new Color(image.getRGB(x + xx, y + yy));
              tensor.set(new int[]{xx, yy, 0}, rgb.getRed());
              tensor.set(new int[]{xx, yy, 1}, rgb.getGreen());
              tensor.set(new int[]{xx, yy, 2}, rgb.getBlue());
            }
          }
          tensors.add(tensor);
        } catch (ArrayIndexOutOfBoundsException e) {
          // Ignore
        }
      }
    }
    return tensors.toArray(new Tensor[]{});
  }
}
