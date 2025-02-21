package com.comp301.a02adventure;

public class CellImpl implements Cell {
  private PositionImpl position;
  private String name;
  private String description;
  private InventoryImpl chest;
  private boolean visited = false;

  public CellImpl(int x, int y, String name, String description) {
    this.position = new PositionImpl(x, y);
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null");
    } else this.name = name;
    if (description == null) {
      throw new IllegalArgumentException("Description cannot be null");
    } else this.description = description;
  }

  public CellImpl(int x, int y) {
    this(x, y, "", "");
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public Position getPosition() {
    return this.position;
  }

  @Override
  public Inventory getChest() {
    return this.chest;
  }

  @Override
  public boolean getIsVisited() {
    return this.visited;
  }

  @Override
  public boolean hasChest() {
    if (this.chest != null) {
      return true;
    } else return false;
  }

  @Override
  public void setName(String name) {
    if (name != null) {
      this.name = name;
    } else throw new IllegalArgumentException("Name cannot be null");
  }

  @Override
  public void setDescription(String description) {
    if (description != null) {
      this.description = description;
    } else throw new IllegalArgumentException("Description cannot be null");
  }

  @Override
  public void setChest(Inventory chest) {
    if (chest == null) {
      throw new IllegalArgumentException("Chest cannot be null");
    }
    this.chest = (InventoryImpl) chest;
  }

  @Override
  public void visit() {
    this.visited = true;
  }
}
