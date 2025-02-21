package com.comp301.a02adventure;

public interface Position {
  /** Getter method for the x-coordinate of the position */
  int getX();

  /** Getter method for the y-coordinate of the position */
  int getY();

  /**
   * Constructs and returns a new Position object located one unit in the indicted direction from
   * the "this" object
   */
  Position getNeighbor(Direction direction);
}
