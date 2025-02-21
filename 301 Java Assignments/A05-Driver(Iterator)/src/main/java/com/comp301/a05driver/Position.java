package com.comp301.a05driver;

/*
 * Position
 * Represents an integer (x,y) position on a grid.
 *
 * getX()
 *   Retrieves x coordinate of the position
 *
 * getY()
 *   Retrieves y coordinate of the position
 *
 * getManhattanDistanceTo(Position p)
 *   Calculates the "Manhattan" distance between two positions.
 *   The Manhattan distance is simply the absolute difference in x positions
 *   summed with the absolute difference in y positions.
 */

public interface Position {

  int getX();

  int getY();

  default int getManhattanDistanceTo(Position p) {
    return Math.abs(getX() - p.getX()) + Math.abs(getY() - p.getY());
  }

}