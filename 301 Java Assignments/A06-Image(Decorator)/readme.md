# a06-image

## Introduction

This assignment gives practice with the decorator design pattern. To complete the assignment, you will create a series of base classes and decorator classes to collectively represent a digital image, like what you would see on Snapchat or Instagram.

In this context, the decorator pattern will be used to add visual modifications to a starting image. For example, one decorator class you will make adds a border around an existing image; another decorator superimposes a configurable circle somewhere on the image. The order in which you add the decorators to the base image will determine the order in which the modifications will appear on the final result.


### Colors

Painters like [Bob Ross](https://en.wikipedia.org/wiki/Bob_Ross) can create new colors of paint by combining three primary colors of paint in different proportions. Computers use the same approach to represent and display all the different colors you see on your screen. For a computer, the three primary colors are red, green, and blue. Every color on your screen is a combination of different proportions of red, green, and blue.

Most computer systems represent a color using three numbers in the range `0-255`. For example, `(0, 255, 52)` represents a greenish color. The first number corresponds to the amount of red present in the color, the second number corresponds to the amount of green, and the third corresponds to the amount of blue. This color therefore has no red, `255` green, and `52` blue. Since the color has the maximum amount of green, we could expect the color to be [mostly green, mixed with a little blue](https://convertingcolors.com/rgb-color-0_255_52.html).

Java provides a built-in `Color` class for representing colors. The color class encapsulates a red, blue, and green value in the range `0-255`. Here's how to create a new `Color` instance:

```java
Color greenish = new Color(0, 255, 52);
```

Now `greenish` is an object representing the greenish color described above.


### Image interface

Every class you will create for this assignment should implement the `Image` interface, which is already provided in the starter code. An `Image` object represents a digital image. Digital images are made up of a two-dimensional grid of "pixels", which are essentially minuscule rectangles of color.

Every `Image` has a width and a height, referring to the number of horizontal and vertical pixels in the image. The `Image` interface therefore has `getWidth()` and `getHeight()` getter methods for getting these values.

To get the color of a pixel, use the `getPixelColor()` method. `getPixelColor()` takes an `x` and a `y` value as parameters, representing the location of the pixel to get. And `getPixelColor()` returns a `Color` object, representing the color of the requested pixel.

The `Image` interface also has a `numLayers()` getter method for getting the number of "layers" in the image. Base images, which do not have any decorators, have only one layer. Each decorator added to an image (like a shape or a border) is another "layer" added to the image. So, for instance, if a base image has one border decorator and one square decorator, then the image has a total of 3 layers.


## Novice

For the novice portion of the assignment, create two new classes implementing the `Image` interface: `SolidColorImage`, and `PictureImage`. These classes represent base images themselves---they do not decorate another image.

### SolidColorImage

The `SolidColorImage` class represents a blank image that is a solid color, kind of like a background. This class is a good starting point for making images that consist mostly of decorators stacked on top of each other.

Add a new class to your project called `SolidColorImage` which implements the `Image` interface. You'll have to add `getPixelColor()`, `getWidth()`, `getHeight()`, and `numLayers()` methods in order to implement the interface. `SolidColorImage` should have only one constructor with the following signature:

```java
public SolidColorImage(int width, int height, Color color) {
  // Constructor code goes here
}
```

Encapsulate `width`, `height`, and `color` as private fields in the class. They represent the width, height, and color of the image. Make sure to check for illegal values of `height` and `width` (negative values are illegal), and throw an `IllegalArgumentException` if an illegal value is detected.

`getWidth()` and `getHeight()` should act as basic getter methods for their corresponding fields. `getPixelColor()` should retrieve and return the color of the pixel at the specified `(x, y)` location in the image. Since this class represents a solid color image, all pixel values have the same color. Therefore, `getPixelColor()` should return the same color for all pixel locations---that is, the color encapsulated as a private field. However, `getPixelColor()` should also check to make sure the specified coordinates are actually within the boundaries of the image. For example, if the provided `x` value is negative or if it is greater or equal to the width of the image, then the coordinate is invalid. If an invalid coordinate is detected, throw an `IllegalArgumentException`. Finally, the `getNumLayers()` method should always return `1`, since base images only have one layer.

You should now be able to create and use new instances of `SolidColorImage`. Test out your new class in the `Main` file by making and returning a new instance inside the static `makeImage()` method. Make an image that is 200 by 200 images large, and is the greenish color described above. Run the main method to see your image on the screen.


### PictureImage

The other base image class to create for this assignment is `PictureImage`, which represents an existing image from your computer. This class is a good starting point for adding decorators on top of an existing image.

`PictureImage` should implement the `Image` interface, and should have a single constructor with the following signature:

```java
public PictureImage(String pathname) throws IOException {
  // Constructor code goes here
}
```

The `pathname` parameter represents the path of an image stored on your computer. You should be able to complete the implementation for the entire `PictureImage` class by encapsulating only one field, which stores a `BufferedImage` object. In the constructor, use the `pathname` parameter to create a `BufferedImage` object, and store it in a private field. A `BufferedImage` represents an image from your computer which has been read into memory. [You can read more about it here](https://docs.oracle.com/javase/7/docs/api/java/awt/image/BufferedImage.html). In particular, you'll have to use BufferedImage's `getWidth()`, `getHeight()`, and `getRGB()` methods to complete the `PictureImage` class.

When you're done, test out your `PictureImage` class by making and returning an instance of `PictureImage` from the `makeImage()` static method located in the `Main.java` file. If you use `"img/kmp.jpg"` as the `pathname` argument, you should see a picture of kmp when running the `main()` method.

## Adept

Now it's time to start making decorator classes for the `Image` interface. Remember, decorator objects need to *encapsulate an instance* of the same interface that they implement. That means all the decorator classes created for this assignment should both implement `Image`, **and** they should *encapsulate* an instance of an `Image` object as a private field.

### SquareDecorator

This decorator class should superimpose a configurable square on top of an existing image. The constructor should have the following signature:

```java
public SquareDecorator(Image image, int squareX, int squareY, int squareSize, Color color) {
  // Constructor code goes here
}
```

Parameters `squareX` and `squareY` represent the coordinates of the *top left* of the square. Parameter `squareSize` represents the side length of the square. The `color` parameter represents the color of the square. Finally, the `image` parameter represents the underlying image that is being painted over. Make sure to check for illegal argument values in the constructor, and throw an `IllegalArgumentException` if one occurs. In particular, if `image` is `null` or if the square size is negative, these are considered illegal arguments.

When `getPixelColor()` is called, the specified `(x, y)` coordinates should be checked to see if they are within the bounds of the square. If they are inside the square, then the returned color should be the designated square color (regardless of the underlying image's pixel value at that location). If the coordinates are outside the square, then the pixel's color should be unaffected by the decorator. In this case, simply return the underlying image's pixel value at that location.

> **Note:** Digital images, including the ones in this assignment, traditionally use a coordinate system with an *inverted y-axis*. In other words, pixel `(0, 0)` is the *top-left-most* pixel in the picture; pixels to the right of that pixel have positive x-coordinate values, and pixels below the pixel have positive y-coordinate values. [Here is an article with more information about the coordinate system for digital images.](http://math.hws.edu/graphicsbook/c2/s1.html)


### CircleDecorator

This decorator is very similar to `SquareDecorator`, except it decorates the underlying image with a circle instead of with a square. The constructor should have the following signature:

```java
public CircleDecorator(Image image, int cx, int cy, int radius, Color color) {
  // Constructor code goes here
}
```

The center of the circle should be located at position `(cx, cy)` in the image, should have radius `radius`, and should have color `color`. A pixel should be considered "in the circle" (and should therefore be painted with the color stored in `color`) if its `(x, y)` location is strictly less than `radius` units away from the center, `(cx, cy)`. You can use the [distance formula](https://www.google.com/search?q=distance+formula) to calculate how close a pixel is from the center of the circle. Negative `radius` values should not be allowed (i.e., a `IllegalArgumentException` should be thrown if one is detected).


## Jedi

This section involves creating two more `Image` decorator classes: `BorderDecorator`, and `ZoomDecorator`.


### BorderDecorator

The `BorderDecorator` class decorates an image by surrounding it with a solid-color border. The class should implement `Image` and should have the following constructor signature:

```java
public BorderDecorator(Image image, int thiccness, Color borderColor) {
  // Constructor code goes here
}
```

`thiccness` represents the thickness of the border, and `borderColor` represents the color of the border. Negative values of `thiccness` should not be allowed, and if one is detected, an `IllegalArgumentException` should be thrown.

`BorderDecorator` should expand the height and width of the underlying image to account for the border being added. For example, if `thiccness` is 10, that means 20 pixels should be added to the width of the image---10 pixels for the left border, and 10 pixels for the right border. A similar computation should take place for the top and bottom borders.


### ZoomDecorator

The `ZoomDecorator` class decorates an existing image by scaling the image so that it appears larger. `ZoomDecorator` should provide *two* constructors. The first constructor has the following signature:

```java
public ZoomDecorator(Image image, int multiplier) {
  // Constructor code goes here
}
```

Here, `multiplier` is a positive integer referring to the factor by which the image should be scaled. A multiplier of `1` indicates that the image should not be scaled at all. A multiplier of `2` means the width and height should be doubled; a multiplier of `3` means the width and height should be tripled, and so on. Invalid values of `multiplier` should throw an `IllegalArgumentException`.

The other constructor should omit the `multiplier` parameter, and instead should use a default multiplier value of `2`. Use constructor chaining to accomplish this.

In addition to simply increasing the width and height of the image, this class should also stretch the original image to fit the new size. This modification should take place in the `getPixelColor()` method. For example, if the original image has a height and width of `2`, and the zoom multiplier is `2`, then the decorated image should have a height of `4` and a width of `4`, but the underlying pixels should be scaled so that `getPixelColor(0, 0)`, `getPixelColor(0, 1)`, `getPixelColor(1, 0)`, and `getPixelColor(1, 1)` all return the same pixel color---that is, they return the same color as the pixel located at position `(0, 0)` in the original picture. In other words, each pixel value in the original pixel should be mapped to four pixels in the scaled image.


### Putting it all together

Finally, in `Main.java`, adjust the `makeImage()` method so that it creates and returns an `Image` object using the decorator pattern with the following properties:

* The base image should be `img/kmp.jpg`
* The red border is color `(255, 0, 0)` and has a thickness of `5` (before scaling)
* The blue border is color `(0, 0, 255)` and has a thickness of `50` (before scaling)
* The yellow circle is color `(255, 255, 0)`, has radius `40` (before scaling), and is at position `(50, 50)` in the final, unscaled image
* The orange square is color `(200, 80, 10)`, has size `40` (before scaling), and is at position `(100, 100)` in the final, unscaled image
* After applying these decorators, add one last `ZoomDecorator` with a multiplier of `2`, to make the image bigger

You can see an example of the final image in `img/jedi.png`.