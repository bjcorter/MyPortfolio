package com.comp301.a05driver;

/*
 * Vehicle
 * Represents a vehicle in our system.
 *
 * getMake()
 *    Retrieves the make of the vehicle.
 *
 * getModel()
 *    Retrieves the model of the vehicle.
 *
 * getPlate()
 *    Retrieves the license plate of the vehicle.
 *
 * getMileage()
 *    Retrieves the total distance the vehicle has traveled
 *    up to now. Think of this like its "odometer".
 *
 * getPosition()
 *    Retrieves the current position of the vehicle.
 *
 * moveTo(Position p)
 *    Updates the mileage of the vehicle by adding the Manhattan
 *    distance between the vehicle's current position and the
 *    position p passed in as a parameter. Then updates the
 *    vehicle's current position to be p.
 */

public interface Vehicle {

  String getMake();

  String getModel();

  String getPlate();

  int getMileage();

  Position getPosition();

  void moveToPosition(Position p);
}