package com.comp301.a05driver;

public class DriverImpl implements Driver {

  private String _first;
  private String _last;
  private int _id;
  private Vehicle _vehicle;

  public DriverImpl(String first, String last, int id, Vehicle vehicle) {
    if (first == null) {
      throw new RuntimeException("Null first name");
    }
    if (last == null) {
      throw new RuntimeException("Null last name");
    }
    if (vehicle == null) {
      throw new RuntimeException("Null vehicle");
    }

    _first = first;
    _last = last;
    _id = id;
    _vehicle = vehicle;
  }

  @Override
  public String getFirstName() {
    return _first;
  }

  @Override
  public String getLastName() {
    return _last;
  }


  @Override
  public int getID() {
    return _id;
  }

  @Override
  public Vehicle getVehicle() {
    return _vehicle;
  }

  @Override
  public void setVehicle(Vehicle v) {
    if (v == null) {
      throw new RuntimeException("Null vehicle");
    }
    _vehicle = v;
  }

}