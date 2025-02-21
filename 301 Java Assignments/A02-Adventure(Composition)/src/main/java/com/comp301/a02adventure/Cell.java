package com.comp301.a02adventure;

public interface Cell {
  String getName();

  String getDescription();

  Position getPosition();

  Inventory getChest();

  boolean getIsVisited();

  boolean hasChest();

  void setName(String name);

  void setDescription(String description);

  void setChest(Inventory chest);

  void visit();
}
