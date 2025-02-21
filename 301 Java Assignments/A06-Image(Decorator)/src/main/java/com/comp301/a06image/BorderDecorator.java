package com.comp301.a06image;

import java.awt.*;

public class BorderDecorator implements Image {

  private final Image image;
  private final int thiccness;
  private final Color borderColor;

  public BorderDecorator(Image image, int thiccness, Color borderColor) {
    if (image == null || thiccness < 0) {
      throw new IllegalArgumentException();
    }
    this.image = image;
    this.thiccness = thiccness;
    this.borderColor = borderColor;
  }

  @Override
  public Color getPixelColor(int x, int y) {
    int originalWidth = image.getWidth();
    int originalHeight = image.getHeight();

    if (x < thiccness
        || x >= originalWidth + thiccness
        || y < thiccness
        || y >= originalHeight + thiccness) {
      return borderColor;
    } else {
      int adjustedX = x - thiccness;
      int adjustedY = y - thiccness;

      if (adjustedX >= 0
          && adjustedX < originalWidth
          && adjustedY >= 0
          && adjustedY < originalHeight) {
        return image.getPixelColor(adjustedX, adjustedY);
      } else {
        return borderColor;
      }
    }
  }

  @Override
  public int getWidth() {
    return image.getWidth() + 2 * thiccness;
  }

  @Override
  public int getHeight() {
    return image.getHeight() + 2 * thiccness;
  }

  @Override
  public int getNumLayers() {
    return image.getNumLayers() + 1;
  }
}
