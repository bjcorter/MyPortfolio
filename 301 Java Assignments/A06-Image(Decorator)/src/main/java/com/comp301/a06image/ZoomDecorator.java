package com.comp301.a06image;

import java.awt.*;

public class ZoomDecorator implements Image {

  private final Image image;
  private final int multiplier;

  public ZoomDecorator(Image image, int multiplier) {
    if (multiplier <= 0) {
      throw new IllegalArgumentException();
    }
    this.image = image;
    this.multiplier = multiplier;
  }

  public ZoomDecorator(Image image) {
    this(image, 2);
  }

  @Override
  public Color getPixelColor(int x, int y) {
    int ogX = x / multiplier;
    int ogY = y / multiplier;

    return image.getPixelColor(ogX, ogY);
  }

  @Override
  public int getWidth() {
    return image.getWidth() * multiplier;
  }

  @Override
  public int getHeight() {
    return image.getHeight() * multiplier;
  }

  @Override
  public int getNumLayers() {
    return image.getNumLayers() + 1;
  }
}
