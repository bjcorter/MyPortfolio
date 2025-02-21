package com.comp301.a02adventure;

public class PositionImpl implements Position {

  private int x;
  private int y;

  public PositionImpl(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public int getY() {
    return this.y;
  }

  @Override
  public Position getNeighbor(Direction direction) {
    if (direction == Direction.NORTH) {
      PositionImpl neighbor = new PositionImpl(this.x, this.y + 1);
      return neighbor;
    } else if (direction == Direction.EAST) {
      PositionImpl neighbor = new PositionImpl(this.x + 1, this.y);
      return neighbor;
    } else if (direction == Direction.SOUTH) {
      PositionImpl neighbor = new PositionImpl(this.x, this.y - 1);
      return neighbor;
    } else if (direction == Direction.WEST) {
      PositionImpl neighbor = new PositionImpl(this.x - 1, this.y);
      return neighbor;
    }
    return null;
  }
}
