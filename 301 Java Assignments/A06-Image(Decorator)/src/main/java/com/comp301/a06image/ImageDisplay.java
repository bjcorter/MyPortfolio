package com.comp301.a06image;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

/**
 * You don't need to make any changes to this class for the assignment. This class contains code to
 * display the decorated image on the screen when the Main class is executed.
 */
public class ImageDisplay extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    // Create the image to display
    WritableImage fxImage = render(Main.makeImage());

    // Set the image view
    ImageView imageView = new ImageView(fxImage);

    // Set the position of the image
    imageView.setX(0);
    imageView.setY(0);

    // Set the fit height and width of the image view
    imageView.setFitHeight(fxImage.getHeight());
    imageView.setFitWidth(fxImage.getWidth());

    // Set the preserve ratio of the image view
    imageView.setPreserveRatio(true);

    // Create a Group object
    Group root = new Group(imageView);

    // Create a scene object
    Scene scene = new Scene(root, fxImage.getWidth(), fxImage.getHeight());

    // Set title to the Stage
    stage.setTitle("Image display");

    // Add scene to the stage
    stage.setScene(scene);

    // Display the contents of the stage
    stage.show();
  }

  /** Renders the image into a WritableImage */
  private WritableImage render(Image img) {
    WritableImage wi = new WritableImage(img.getWidth(), img.getHeight());
    PixelWriter pw = wi.getPixelWriter();
    for (int y = 0; y < img.getHeight(); y++) {
      for (int x = 0; x < img.getWidth(); x++) {
        pw.setArgb(x, y, (0xff << 24) | img.getPixelColor(x, y).getRGB());
      }
    }
    return wi;
  }
}
