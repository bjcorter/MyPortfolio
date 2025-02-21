package com.comp301.a05driver;

public class PositionImpl implements Position {

  private int _x;
  private int _y;

  public PositionImpl(int x, int y) {
    _x = x;
    _y = y;
  }

  @Override
  public int getX() {
    return _x;
  }

  @Override
  public int getY() {
    return _y;
  }
}