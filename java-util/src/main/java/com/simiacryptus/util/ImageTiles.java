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

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ImageTiles {
  public static Tensor[] tilesRgb(BufferedImage image, int width, int height) {
    List<Tensor> tensors = new ArrayList<>();
    
    for (int x = 0; x < image.getWidth() - width; x += width) {
      for (int y = 0; y < image.getHeight() - height; y += height) {
        Tensor tensor = new Tensor(width, height);
        for (int xx = 0; xx < width; xx++) {
          for (int yy = 0; yy < height; yy++) {
            int rgb = image.getRGB(x + xx, y + yy);
            //int red = rgb & 0xFF;
            //int green = rgb >> 8 & 0xFF;
            //int blue = rgb >> 16 & 0x0FF;
            int red = image.getColorModel().getRed(rgb);
            int green = image.getColorModel().getGreen(rgb);
            int blue = image.getColorModel().getBlue(rgb);
            tensor.set(new int[]{xx, yy, 0}, red);
            tensor.set(new int[]{xx, yy, 1}, green);
            tensor.set(new int[]{xx, yy, 2}, blue);
          }
        }
        tensors.add(tensor);
      }
    }
    return tensors.toArray(new Tensor[]{});
  }
}
