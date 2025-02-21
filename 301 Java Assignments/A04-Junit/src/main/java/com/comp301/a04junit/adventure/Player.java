package com.comp301.a04junit.adventure;

public interface Player {
  /** Getter method for the player's current position */
  Position getPosition();

  /** Getter method for the player's inventory */
  Inventory getInventory();

  /** Getter method for the player's name */
  String getName();

  /**
   * Blindly moves the player's position one unit in the indicated direction, without checking
   * whether the new location is valid
   */
  void move(Direction direction);
}
