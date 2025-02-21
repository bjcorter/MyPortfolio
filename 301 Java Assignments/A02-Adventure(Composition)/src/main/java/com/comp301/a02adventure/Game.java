package com.comp301.a02adventure;

import java.util.List;

public interface Game {
  Position getPlayerPosition();

  String getPlayerName();

  List<Item> getPlayerItems();

  boolean getIsWinner();

  void printCellInfo();

  void openChest();

  boolean canMove(Direction direction);

  void move(Direction direction);
}
