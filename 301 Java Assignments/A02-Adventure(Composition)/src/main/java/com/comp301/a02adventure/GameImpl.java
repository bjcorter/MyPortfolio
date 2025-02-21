package com.comp301.a02adventure;

import java.util.List;

public class GameImpl implements Game {
  private Map map;
  private Player player;

  public GameImpl(Map map, Player player) {
    if (map == null || player == null) {
      throw new IllegalArgumentException("Map or player cannot be null");
    } else {
      this.map = map;
      this.player = player;
    }
    Position position = getPlayerPosition();
    Cell current = map.getCell(position);
  }

  @Override
  public Position getPlayerPosition() {
    return this.player.getPosition();
  }

  @Override
  public String getPlayerName() {
    return this.player.getName();
  }

  @Override
  public List<Item> getPlayerItems() {
    return this.player.getInventory().getItems();
  }

  @Override
  public boolean getIsWinner() {
    return this.player.getInventory().getNumItems() == map.getNumItems();
  }

  @Override
  public void printCellInfo() {
    Position position = getPlayerPosition();
    Cell current = map.getCell(position);
    System.out.println("Location: " + current.getName());
    System.out.println(current.getDescription());
    if (current.getIsVisited()) {
      System.out.println("You have already visited this location.");
    }
    if (current.hasChest()) {
      System.out.println("You found a chest! Type 'open' to see what's inside, or keep moving.");
    }
    current.visit();
  }

  @Override
  public void openChest() {
    Position position = getPlayerPosition();
    Cell current = map.getCell(position);
    if (!current.hasChest()) {
      System.out.println("No chest to open, sorry!");
    } else if (current.hasChest() && current.getChest().isEmpty()) {
      System.out.println("The chest is empty.");
    } else if (current.hasChest() && !current.getChest().isEmpty()) {
      System.out.println("You collected these items: " + current.getChest().getItems());
      player.getInventory().transferFrom(current.getChest());
    }
  }

  @Override
  public boolean canMove(Direction direction) {
    Position position = getPlayerPosition();
    Cell current = map.getCell(position);
    Position neighborP = position.getNeighbor(direction);
    int x = neighborP.getX();
    int y = neighborP.getY();
    boolean valid = x >= 0 && x < map.getWidth() && y >= 0 && y < map.getHeight();

    return valid && map.getCell(neighborP) != null;
  }

  @Override
  public void move(Direction direction) {
    if (!canMove(direction)) {
      System.out.println("You can't go that way! Try another direction.");
    } else {
      Position newP = getPlayerPosition().getNeighbor(direction);
      player.setPosition(newP);
      Cell current = map.getCell(newP);

      printCellInfo();
    }
  }
}
