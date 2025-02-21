# a02-adventure

## Introduction

In this assignment, you will design and implement a text-based, directional adventure video game that can be played in the console. The player will explore a two-dimensional, gridded map in search of treasures. The player will navigate through the world by entering keyword inputs such as "go north" or "go east" into the console. Each input will return a message based on what the player chose to do and their current surroundings. Some locations in the map will have "chests" containing items; the player can "open" a chest and take the items inside. The goal of the game is to find all the treasure in the map.

Text-based adventure games were first popularized in the late 1970's and early 1980's. Zork, [which you can read about here](https://en.wikipedia.org/wiki/Zork) [or play here](https://classicreload.com/zork-i.html), is a classic example of a text-based adventure game like the one we will be creating for this assignment.

The environment that the player will navigate through is two-dimensional, so let's assume a 2D, integer Cartesian coordinate system. This means the player's current position on the map can be modeled by two integers representing their `x` and `y` location. The player should be able to move north (i.e. in the positive-`y` direction), south (i.e. in the negative-`y` direction), east (i.e. in the positive-`x` direction), and west (i.e. in the negative-`x` direction) through the environment.

To model the game, we will design a `Game` object. However, as you can probably guess, the game will require a lot of functionality---and a lot of code---to work properly! To make our lives easier (and to make the resulting code more flexible), we're going to use **composition** to split the `Game` class behavior into the following smaller components:

* `Item` - An interface that represents an item in the game. The objective of the game is to search for and collect different items as the player navigates through the game environment. In a more complex game, there might be multiple implementations of `Item`, each having unique behavior within the game. For example, there might be `Furniture` items, `Weapon` items, `Clothing` items, `Tool` items, etc. To keep it simple, for this assignment we're just going to create one implementation of the `Item` interface: `ItemImpl`. The `ItemImpl` class will encapsulate a single instance field: a `String` to represent the name of the item.


* `Inventory` - An interface that represents a collection of `Items`. For this assignment, the `Player` object will have an `Inventory` to store the items that they have collected. As the player finds more items in the game, they will add the items to their inventory. Additionally, some locations on the map will contain "chests" that store items for the player to find. Each chest will be represented by an`Inventory` instance.


* `Position` - An interface that represents an `(x, y)` position in the environment. A `Position` object will simply encapsulate an `x` value and a `y` value. The environment we are creating for this game is a "grid", so all coordinate values must be integers. Double values are too slow and complicated anyway, what with their crazy rounding problems and all. ;-) Finally, note that `Position` objects *are intended to be immutable*! Review the lecture slides if you don't remember what this means.


* `Player` - An interface representing the player of the game. At all times, the player is located at a particular `Position` and has a personal `Inventory` full of the `Items` they have collected. Thus, this object will be an *aggregation* of these two objects.


* `Cell` - An interface representing *the contents* of an `(x, y)` location in the environment. Each accessible location in the environment will encapsulate a name and a description, so these will be `String` values stored in the `Cell` object. Some `Cells` will contain a chest (represented by the `Inventory` object). Ultimately, the game environment will be represented as a 2D array of `Cell` objects.


* `Map` - This interface encapsulates a two-dimensional array of `Cell` objects that represent the environment that the player will explore.


* `Game` - This interface represents *the composition of* a `Player` together with a `Map`, allowing the game to be played!


Do you see how **composition** is used here to break down the game functionality into smaller, independent pieces? As software engineers, our job is to always be looking for ways to break down larger problems into smaller pieces.

Before starting to write code, check out the `Item`, `Position`, `Inventory`, `Cell`, `Player`, `Map`, and `Game` interfaces that have been created for you as part of the starter code. Read the method descriptions in each interface, and begin to build a mental model for what the object do and how they interact.


## Novice

The "novice" portion of this assignment is to create three new classes that implement provided interfaces: `ItemImpl`, `PositionImpl`, and `InventoryImpl`. Together, these classes will represent the basic building blocks for our adventure game. In the "adept" and "jedi" sections, we will be *composing* and *aggregating* these objects together to construct larger classes.

### `ItemImpl`

The implementation class for the `Item` interface should be called `ItemImpl`. The `ItemImpl` class should encapsulate a `private` `String` field to store the name of the item. The `ItemImpl` class should be *immutable*. There should only be one constructor for `ItemImpl`, and it should look like this:

```java
public ItemImpl(String name) {
    // Constructor code goes here
    }
```

`null` values for the `name` parameter should not be allowed by the constructor; if one is encountered, an `IllegalArgumentException` should be thrown.

Notice how the `Item` interface overrides the `equals()` and `toString()` methods? That's because every class in Java is actually a descendant of a built-in superclass, called "Object". The Object superclass defines a few useful methods for every Java object, like `equals()` and `toString()`.  Since `Object` is the superclass to every class, that means every Java object has these methods. Programmers can override them, if necessary, to supply a subclass-specific version of the method. In the `ItemImpl` class, you should override both `equals()` and `toString()` to provide more suitable implementations for both of these methods (see the comments in the `Item` interface for more details).


### `InventoryImpl`

The implementation class for the `Inventory` interface should be called `InventoryImpl`. `InventoryImpl` should encapsulate a single `private` field: a `List<Item>` list of items.  The constructor of `InventoryImpl` should take no arguments, and simply construct the list of items. An `ArrayList` is a suitable `List` implementation to use, but any valid `List` implementation would work.


### `PositionImpl`

The implementation class for the `Position` interface should be called `PositionImpl`. The `PositionImpl` class should encapsulate a position in the environment, in the form of an `(x, y)` pair of integer coordinates.

The `PositionImpl` class should provide a single constructor which takes two integer arguments and looks like this:

```java
public PositionImpl(int x, int y) {
  // Constructor code goes here
}
```

All incoming values of `x` and `y` should be considered valid by the `PositionImpl` constructor.



## Adept

In the "adept" portion of the assignment, you will begin to **compose** and **aggregate** the classes you created in "novice" together to create more complicated objects.


### `CellImpl`

Create a `CellImpl` class which implements the `Cell` interface. The `CellImpl` class should provide two constructors. The first should look like this:

```java
public CellImpl(int x, int y, String name, String description) {
  // Constructor code goes here
}
```

The passed `x` and `y` coordinates should immediately be stored in an encapsulated `Position` field. The `name` and `description` string values should also be encapsulated in fields. Perform validation on `name` and `description`, throwing an `IllegalArgumentException` for `null` values.

The second constructor should take in only the `x` and `y` coordinates as parameters, and initialize the other two fields to empty strings (`""`). **You should use constructor chaining to implement these two constructors**.

Some (but not all) `CellImpl` instances will hold a chest. A chest will be represented by an `Inventory` instance. Therefore, the `Cell` class should also encapsulate a field to store the (optional) chest, and expose getter and setter methods for this field.

Finally, the `CellImpl` object should also provide a way to check whether the player has visited the `Cell` before. You should encapsulate a `boolean` flag to track whether the cell has been visited. All `CellImpl` objects should start out with a "visited" value of `false`. Once the `visit()` method is called, the flag should be permanently switched to `true`.


### `PlayerImpl`

Create a `PlayerImpl` class which implements the `Player` interface. The `PlayerImpl` class should provide a single constructor, which should look like this:

```java
public PlayerImpl(String name, int startX, int startY) {
  // Constructor code goes here
  }
```

The `PlayerImpl` constructor should perform validation on the `name` string, throwing an `IllegalArgumentException` for incoming `null` values.

A `Player` object should encapsulate a name, a `Position`, and an `Inventory`. When the `move()` method is called, the player object should replace its private `Position` field with a new `Position` according to the indicated direction of the movement. Hint: use the `getNeighbor()` method.


## Jedi

In the "jedi" portion of the assignment, you will continue to **compose** and **aggregate** the classes you created in "novice" and "adept" by creating `MapImpl` and `GameImpl` objects.


### `MapImpl`

Create a `MapImpl` class which implements the `Map` interface. The `MapImpl` class represents a 2D grid of `Cell objects`, together with an integer representing the initial number of items hidden in the map that the player needs to find. You should therefore encapsulate these two ideas as two instance fields within the class.

The `MapImpl` class should provide one constructor:

```java
public MapImpl(int width, int height, int numItems) {
  // Constructor code goes here
}
```

Here, `width` and `height` indicate the dimensions of the 2D `Cell` grid, and should be positive (throw an `IllegalArgumentException` for invalid values of `width` and/or `height`). The `MapImpl` should provide getter methods for the map height, the map width, the total number of items hidden for the player to find, and each individual `Cell`. Initially, all `Cell` locations in the grid should be initialized to `null`. However, programmers using the class can instantiate grid locations as desired by using the `initCell()` method.


### `GameImpl`

Finally, the last class to create for this assignment is `GameImpl`, which implements the `Game` interface. A `Game` is an aggregation of a `Map` object and a `Player` object, so you should encapsulate these as instance fields. The `GameImpl` constructor should look like this, and should throw an `IllegalArgumentException` if either `map` or `player` is `null`:

```java
  public GameImpl(Map map, Player player) {
    // Constructor code goes here
    }
```

Write appropriate implementations for the methods required by the `Game` interface. You should be able to fill in all of these methods simply by manipulating the `Player` and `Map` object that is encapsulated by the `Game`.


## Playing the Game

The starter code also includes the classes `MapUNC` and `Main`, which have been commented out. Once you have finished the "jedi" portion of the assignment, these can be used to play a short treasure hunting game that uses the UNC-CH campus as a map! Feel free to play it as is, make modifications, or create an entirely new game!

To play, open the `Main` class file and hit run (the small green icon next to the line numbers at the top of the class).
