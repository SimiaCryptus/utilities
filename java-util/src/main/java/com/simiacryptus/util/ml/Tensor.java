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

package com.simiacryptus.util.ml;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.simiacryptus.util.io.JsonUtil;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.DoubleSupplier;
import java.util.function.ToDoubleBiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Tensor implements Serializable {
  
  private static ConcurrentHashMap<Integer, BlockingQueue<double[]>> recycling = new ConcurrentHashMap<>();
  
  static {
    java.util.concurrent.Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
      @Override
      public void run() {
        recycling.forEach((k, v) -> {
          v.clear();
        });
      }
    }, 0, 10, TimeUnit.SECONDS);
  }
  
  protected final int[] dimensions;
  protected final int[] strides;
  protected volatile double[] data;
  
  protected Tensor() {
    super();
    this.data = null;
    this.strides = null;
    this.dimensions = null;
  }
  
  public Tensor(final int... dims) {
    this(dims, null);
  }
  
  public Tensor(final int[] dims, final double[] data) {
    this.dimensions = Arrays.copyOf(dims, dims.length);
    this.strides = getSkips(dims);
    this.data = data;// Arrays.copyOf(data, data.length);
    assert(null == data || Tensor.dim(dims) == data.length);
  }
  
  private static int[] getSkips(int[] dims) {
    int[] skips = new int[dims.length];
    for (int i = 0; i < skips.length; i++) {
      if (i == 0) {
        skips[i] = 1;
      } else {
        skips[i] = skips[i - 1] * dims[i - 1];
      }
    }
    return skips;
  }
  
  public Tensor(double[] ds) {
    this(new int[]{ds.length}, ds);
  }
  
  public static int dim(final int... dims) {
    int total = 1;
    for (final int dim : dims) {
      total *= dim;
    }
    return total;
  }
  
  public static void recycle(double[] data) {
    if(null == data) return;
    //if(null != data) return;
    if (data.length < 256) return;
    BlockingQueue<double[]> bin = recycling.get(data.length);
    if (null == bin) {
      //System.err.println("New Recycle Bin: " + data.length);
      bin = new ArrayBlockingQueue<double[]>((int) (1e9/data.length));
      recycling.put(data.length, bin);
    }
    bin.offer(data);
  }
  
  public static double[] obtain(int length) {
    BlockingQueue<double[]> bin = recycling.get(length);
    if (null != bin) {
      double[] data = bin.poll();
      if (null != data) {
        Arrays.fill(data, 0);
        return data;
      }
    }
    try {
      return new double[length];
    } catch (OutOfMemoryError e) {
      throw new RuntimeException("Could not allocate " + length + " bytes",e);
    }
  }
  
  public static Tensor fromRGB(BufferedImage img) {
    Tensor a = new Tensor(img.getWidth(), img.getHeight(), 3);
    for (int x = 0; x < img.getWidth(); x++) {
      for (int y = 0; y < img.getHeight(); y++) {
        a.set(new int[]{x, y, 0}, img.getRGB(x, y) & 0xFF);
        a.set(new int[]{x, y, 1}, img.getRGB(x, y) >> 8 & 0xFF);
        a.set(new int[]{x, y, 2}, img.getRGB(x, y) >> 16 & 0x0FF);
      }
    }
    return a;
  }
  
  private static int bounds(final int value) {
    final int max = 0xFF;
    final int min = 0;
    return (value < min) ? min : ((value > max) ? max : value);
  }
  
  private static double bounds(final double value) {
    final int max = 0xFF;
    final int min = 0;
    return (value < min) ? min : ((value > max) ? max : value);
  }
  
  @Override
  public void finalize() throws Throwable {
    if (null != data) {
      recycle(data);
      data = null;
    }
    super.finalize();
  }
  
  private int[] _add(final int[] base, final int... extra) {
    final int[] copy = Arrays.copyOf(base, base.length + extra.length);
    for (int i = 0; i < extra.length; i++) {
      copy[i + base.length] = extra[i];
    }
    return copy;
  }
  
  public void add(final Coordinate coords, final double value) {
    add(coords.index, value);
  }
  
  public final Tensor add(final int index, final double value) {
    getData()[index] += value;
    return this;
  }
  
  public void add(final int[] coords, final double value) {
    add(index(coords), value);
  }
  
  public Stream<Coordinate> coordStream() {
    return coordStream(false);
  }
  
  public Stream<Coordinate> coordStream(final boolean paralell) {
    return StreamSupport.stream(Spliterators.spliterator(new Iterator<Coordinate>() {
      
      int cnt = 0;
      int[] val = new int[Tensor.this.dimensions.length];
      
      @Override
      public boolean hasNext() {
        return this.cnt < dim();
      }
      
      @Override
      public Coordinate next() {
        final int[] last = Arrays.copyOf(this.val, this.val.length);
        for (int i = 0; i < this.val.length; i++) {
          if (++this.val[i] >= Tensor.this.dimensions[i]) {
            this.val[i] = 0;
          } else {
            break;
          }
        }
        final int index = this.cnt++;
        // assert index(last) == index;
        return new Coordinate(index, last);
      }
    }, dim(), Spliterator.ORDERED), paralell);
  }
  
  public Tensor copy() {
    return new Tensor(Arrays.copyOf(this.dimensions, this.dimensions.length), Arrays.copyOf(getData(), getData().length));
  }
  
  public int dim() {
    if(null != data) return data.length;
    else return Tensor.dim(dimensions);
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    final Tensor other = (Tensor) obj;
    if (!Arrays.equals(getData(), other.getData()))
      return false;
    if (!Arrays.equals(this.dimensions, other.dimensions))
      return false;
    return true;
  }
  
  public Tensor fill(final DoubleSupplier f) {
    Arrays.parallelSetAll(getData(), i -> f.getAsDouble());
    return this;
  }
  
  public Tensor fill(final java.util.function.IntToDoubleFunction f) {
    Arrays.parallelSetAll(getData(), i -> f.applyAsDouble(i));
    return this;
  }
  
  public double get(final Coordinate coords) {
    final double v = getData()[coords.index];
    return v;
  }
  
  public double get(final int index) {
    return getData()[index];
  }
  
  public double get(final int... coords) {
    return getData()[index(coords)];
  }
  
  public double[] getData() {
    if (null == this.data) {
      synchronized (this) {
        if (null == this.data) {
          int length = Tensor.dim(this.dimensions);
          this.data = obtain(length);
        }
      }
    }
    assert(dim()==this.data.length);
    return this.data;
  }
  
  public final int[] getDimensions() {
    return this.dimensions;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(getData());
    result = prime * result + Arrays.hashCode(this.dimensions);
    return result;
  }
  
  public int index(final Coordinate coords) {
    return coords.index;
  }
  
  public int index(final int... coords) {
    int v = 0;
    for (int i = 0; i < this.strides.length && i < coords.length; i++) {
      v += this.strides[i] * coords[i];
    }
    return v;
    // return IntStream.range(0, strides.length).map(i->strides[i]*coords[i]).sum();
  }
  
  public double l1() {
    return Arrays.stream(getData()).sum();
  }
  
  public double l2() {
    return Math.sqrt(Arrays.stream(getData()).map(x -> x * x).sum());
  }
  
  public Tensor map(final java.util.function.DoubleUnaryOperator f) {
    final double[] cpy = new double[getData().length];
    for (int i = 0; i < getData().length; i++) {
      final double x = getData()[i];
      // assert Double.isFinite(x);
      final double v = f.applyAsDouble(x);
      // assert Double.isFinite(v);
      cpy[i] = v;
    }
    ;
    return new Tensor(this.dimensions, cpy);
  }
  
  public Tensor map(final ToDoubleBiFunction<Double, Coordinate> f) {
    return new Tensor(this.dimensions, coordStream(false).mapToDouble(i -> f.applyAsDouble(get(i), i)).toArray());
  }
  
  public Tensor mapParallel(final ToDoubleBiFunction<Double, Coordinate> f) {
    return new Tensor(this.dimensions, coordStream(true).mapToDouble(i -> f.applyAsDouble(get(i), i)).toArray());
  }
  
  public Tensor minus(final Tensor right) {
    assert Arrays.equals(getDimensions(), right.getDimensions());
    final Tensor copy = new Tensor(getDimensions());
    final double[] thisData = getData();
    final double[] rightData = right.getData();
    Arrays.parallelSetAll(copy.getData(), i -> thisData[i] - rightData[i]);
    return copy;
  }
  
  public Tensor reformat(final int... dims) {
    return new Tensor(dims, getData());
  }
  
  public Tensor multiply(final double d) {
    Tensor tensor = new Tensor(getDimensions());
    double[] resultData = tensor.getData();
    double[] thisData = getData();
    for (int i = 0; i < thisData.length; i++) {
      resultData[i] = d * thisData[i];
    }
    return tensor;
  }
  
  public Tensor scale(final double d) {
    for (int i = 0; i < getData().length; i++) {
      getData()[i] *= d;
    }
    return this;
  }
  
  public void set(final Coordinate coords, final double value) {
    assert Double.isFinite(value);
    set(coords.index, value);
  }
  
  public Tensor set(final double[] data) {
    for (int i = 0; i < getData().length; i++) {
      getData()[i] = data[i];
    }
    return this;
  }
  
  public BufferedImage toImage() {
    int[] dims = getDimensions();
    if(3 == dims.length) {
      if(3 == dims[2]) {
        return toRgbImage();
      } else {
        assert(1 == dims[2]);
        return toGrayImage();
      }
    } else {
      assert(2 == dims.length);
      return toGrayImage();
    }
  }
  
  public BufferedImage toGrayImage() {
    int width = getDimensions()[0];
    int height = getDimensions()[1];
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
    for (int x = 0; x < width; x++)
      for (int y = 0; y < height; y++) {
        double v = get(x, y);
        image.getRaster().setSample(x, y, 0, v < 0 ? 0 : v > 255 ? 255 : v);
      }
    return image;
  }
  
  public BufferedImage toRgbImage() {
    final int[] dims = this.getDimensions();
    final BufferedImage img = new BufferedImage(dims[0], dims[1], BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < img.getWidth(); x++) {
      for (int y = 0; y < img.getHeight(); y++) {
        if (this.getDimensions()[2] == 1) {
          final double value = this.get(x, y, 0);
          img.setRGB(x, y, bounds((int) value) * 0x010101);
        } else {
          final double red = bounds(this.get(x, y, 0));
          final double green = bounds(this.get(x, y, 1));
          final double blue = bounds(this.get(x, y, 2));
          img.setRGB(x, y, (int) (red + ((int) green << 8) + ((int) blue << 16)));
        }
      }
    }
    return img;
  }
  
  public Tensor set(final int index, final double value) {
    // assert Double.isFinite(value);
    getData()[index] = value;
    return this;
  }
  
  public void set(final int[] coords, final double value) {
    assert Double.isFinite(value);
    set(index(coords), value);
  }
  
  public void set(final Tensor right) {
    assert dim() == right.dim();
    final double[] rightData = right.getData();
    Arrays.parallelSetAll(getData(), i -> rightData[i]);
  }
  
  public double sum() {
    double v = 0;
    for (final double element : getData()) {
      v += element;
    }
    // assert Double.isFinite(v);
    return v;
  }
  
  @Override
  public String toString() {
    return toString(new int[]{});
  }
  
  private String toString(final int... coords) {
    if (coords.length == this.dimensions.length)
      return Double.toString(get(coords));
    else {
      List<String> list = IntStream.range(0, this.dimensions[coords.length]).mapToObj(i -> {
        return toString(_add(coords, i));
      }).collect(Collectors.<String>toList());
      if (list.size() > 10) {
        list = list.subList(0, 8);
        list.add("...");
      }
      final Optional<String> str = list.stream().limit(10).reduce((a, b) -> a + "," + b);
      return "[ " + str.get() + " ]";
    }
  }
  
  public void setAll(double v) {
    double[] data = getData();
    for (int i = 0; i < data.length; i++) {
      data[i] = v;
    }
  }
  
  public int size() {
    return null==data?Tensor.dim(this.dimensions):data.length;
  }
  
  public JsonElement getJson() {
    JsonObject json = new JsonObject();
    json.add("dimensions", JsonUtil.getJson(dimensions));
    if(data != null) json.add("data", JsonUtil.getJson(data));
    return json;
  }
  
  public static Tensor fromJson(JsonObject json) {
    if(null == json) return null;
    int[] dims = JsonUtil.getIntArray(json.getAsJsonArray("dimensions"));
    double[] data = json.has("data") ? JsonUtil.getDoubleArray(json.getAsJsonArray("data")) : null;
    return new Tensor(dims, data);
  }
  
  public static Tensor add(Tensor left, Tensor right) {
    assert Arrays.equals(left.getDimensions(), right.getDimensions());
    Tensor result = new Tensor(left.getDimensions());
    double[] resultData = result.getData();
    double[] leftData = left.getData();
    double[] rightData = right.getData();
    for(int i=0;i<resultData.length;i++) {
      double l = leftData[i];
      double r = rightData[i];
      resultData[i] = l + r;
    }
    return result;
  }
  
  public static Tensor product(Tensor left, Tensor right) {
    assert Arrays.equals(left.getDimensions(), right.getDimensions());
    Tensor result = new Tensor(left.getDimensions());
    double[] resultData = result.getData();
    double[] leftData = left.getData();
    double[] rightData = right.getData();
    for(int i=0;i<resultData.length;i++) {
      double l = leftData[i];
      double r = rightData[i];
      resultData[i] = l * r;
    }
    return result;
  }
}
