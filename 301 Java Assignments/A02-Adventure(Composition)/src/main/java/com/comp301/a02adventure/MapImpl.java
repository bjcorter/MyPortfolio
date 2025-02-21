package com.comp301.a02adventure;

public class MapImpl implements Map {

  private int width;
  private int height;
  private int numItems;
  private Cell[][] grid;

  public MapImpl(int width, int height, int numItems) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height must be positive ints");
    }
    this.width = width;
    this.height = height;
    this.numItems = numItems;
    this.grid = new Cell[width][height];
  }

  private int hiddenItems;

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public Cell getCell(int x, int y) {
    if (x < 0 || x >= width || y < 0 || y >= height) {
      throw new IndexOutOfBoundsException("Coordinates outside of map");
    }
    return grid[x][y];
  }

  @Override
  public Cell getCell(Position position) {
    return getCell(position.getX(), position.getY());
  }

  @Override
  public void initCell(int x, int y) {
    if (x < 0 || x >= width || y < 0 || y >= height) {
      throw new IndexOutOfBoundsException("Not within the grid");
    } else grid[x][y] = new CellImpl(x, y);
  }

  @Override
  public int getNumItems() {
    return numItems;
  }
}
