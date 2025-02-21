package com.comp301.a06image;

import java.awt.*;
import java.io.IOException;

import javafx.application.Application;

import javax.imageio.ImageIO;
import java.io.InputStream;
import java.awt.image.BufferedImage;


public class Main {
  /**
   * Creates and returns an image object for testing. The image that this method produces will be
   * automatically displayed on the screen when main() is run below. Use this method to test
   * different decorators.
   */
  public static Image makeImage() throws IOException {
    // Use the class loader to access the resource
    InputStream stream = Main.class.getClassLoader().getResourceAsStream("img/kmp.jpg");

    if (stream == null) {
        throw new IOException("Image file not found in resources!");
    }

    // Read the image
    BufferedImage bufferedImage = ImageIO.read(stream);
    Image base = new PictureImage(bufferedImage);

    // Decorate the image
    Image redBorder = new BorderDecorator(base, 5, new Color(255, 0, 0));
    Image blueBorder = new BorderDecorator(redBorder, 50, new Color(0, 0, 255));
    Image yellowCircle = new CircleDecorator(blueBorder, 50, 50, 40, new Color(255, 255, 0));
    Image orangeSquare = new SquareDecorator(yellowCircle, 100, 100, 40, new Color(200, 80, 10));
    Image finalImage = new ZoomDecorator(orangeSquare, 2);
    return finalImage;
}



  /**
   * Use this method for testing your code. When main() runs, the image you created and decorated in
   * the makeImage() method above will be generated and displayed on the screen. You don't need to
   * make any changes to this main() method.
   */
  public static void main(String[] args) {
    Application.launch(ImageDisplay.class, args);
  }
}
