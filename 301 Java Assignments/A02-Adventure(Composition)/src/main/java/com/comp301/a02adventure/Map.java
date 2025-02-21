package com.comp301.a02adventure;

public interface Map {
  /** Getter method for the width of the map */
  int getWidth();

  /** Getter method for the height of the map */
  int getHeight();

  /**
   * Getter method for a specific cell on the map. Throws an IndexOutOfBoundsException for
   * coordinate parameters that are not on the map
   */
  Cell getCell(int x, int y);

  /**
   * Overloaded getter method for a specific cell on the map. Throws an IndexOutOfBoundsException
   * for coordinate parameters that are not on the map
   */
  Cell getCell(Position position);

  /**
   * Initializes a new CellImpl object at the specified location on the map, overwriting any
   * existing Cell at that location. Throws an IndexOutOfBoundsException for coordinate parameters
   * that are not on the map
   */
  void initCell(int x, int y);

  /**
   * Getter method for the total number of items that need to be collected in order for the player
   * to win. This field is immutable.
   */
  int getNumItems();
}
