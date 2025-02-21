package com.comp301.a06image;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SquareDecorator implements Image {

  private final Image image;
  private final int squareX;
  private final int squareY;
  private final int squareSize;
  private final Color Scolor;

  public SquareDecorator(Image image, int squareX, int squareY, int squareSize, Color color) {
    if (image == null || squareSize < 0) {
      throw new IllegalArgumentException("invalid input");
    }
    this.image = image;
    this.squareX = squareX;
    this.squareY = squareY;
    this.squareSize = squareSize;
    this.Scolor = color;
  }

  @Override
  public Color getPixelColor(int x, int y) {
    if (x >= squareX && x < squareX + squareSize && y >= squareY && y < squareY + squareSize) {
      return Scolor;
    } else {
      return image.getPixelColor(x, y);
    }
  }

  @Override
  public int getWidth() {
    return image.getWidth();
  }

  @Override
  public int getHeight() {
    return image.getHeight();
  }

  @Override
  public int getNumLayers() {
    return image.getNumLayers() + 1;
  }
}
