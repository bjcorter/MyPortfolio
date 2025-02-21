package com.comp301.a06image;

import java.awt.*;

public class SolidColorImage implements Image {

  private Color color;
  private int width;
  private int height;

  public SolidColorImage(int width, int height, Color color) {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Width and Height must be positive.");
    }
    this.color = color;
    this.width = width;
    this.height = height;
  }

  @Override
  public Color getPixelColor(int x, int y) {
    if (x < 0 || x >= width || y < 0 || y >= height) {
      throw new IllegalArgumentException("invalid coordinates");
    }
    return this.color;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getNumLayers() {
    return 1;
  }
}
