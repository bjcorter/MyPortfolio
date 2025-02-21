package com.comp301.a05driver;

public class VehicleImpl implements Vehicle {

  private String _make;
  private String _model;
  private String _plate;
  private int _mileage;
  private Position _position;

  public VehicleImpl(String make, String model, String plate, Position position) {
    if (make == null) {
      throw new RuntimeException("make is null");
    }
    if (model == null) {
      throw new RuntimeException("model is null");
    }
    if (plate == null) {
      throw new RuntimeException("plate is null");
    }
    if (position == null) {
      throw new RuntimeException("position is null");
    }

    _make = make;
    _model = model;
    _plate = plate;
    _position = position;

    _mileage = 0;

  }

  @Override
  public String getMake() {
    return _make;
  }

  @Override
  public String getModel() {
    return _model;
  }

  @Override
  public String getPlate() {
    return _plate;
  }

  @Override
  public int getMileage() {
    return _mileage;
  }

  @Override
  public Position getPosition() {
    return _position;
  }

  @Override
  public void moveToPosition(Position p) {
    if (p == null) {
      throw new RuntimeException("New vehicle position is null");
    }

    _mileage += _position.getManhattanDistanceTo(p);
    _position = p;
  }

}