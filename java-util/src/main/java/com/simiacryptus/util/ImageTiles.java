package com.simiacryptus.util;


import com.simiacryptus.util.ml.Tensor;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ImageTiles {
    public static Tensor[] tilesRgb(BufferedImage image, int width, int height) {
        List<Tensor> tensors = new ArrayList<>();

        for(int x=0;x<image.getWidth()-width;x+=width) {
            for(int y=0;y<image.getHeight()-height;y+=height) {
                Tensor tensor = new Tensor(width, height);
                for(int xx=0;xx<width;xx++) {
                    for(int yy=0;yy<height;yy++) {
                        int rgb = image.getRGB(x + xx, y + yy);
                        //int red = rgb & 0xFF;
                        //int green = rgb >> 8 & 0xFF;
                        //int blue = rgb >> 16 & 0x0FF;
                        int red = image.getColorModel().getRed(rgb);
                        int green = image.getColorModel().getGreen(rgb);
                        int blue = image.getColorModel().getBlue(rgb);
                        tensor.set(new int[]{ xx, yy, 0} , red);
                        tensor.set(new int[]{ xx, yy, 1} , green);
                        tensor.set(new int[]{ xx, yy, 2} , blue);
                    }
                }
                tensors.add(tensor);
            }
        }
        return tensors.toArray(new Tensor[]{});
    }
}
