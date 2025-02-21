package com.comp301.a05driver;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ProximityIterator implements Iterator<Driver> {
    private final Iterator<Driver> driverIterator;
    private final Position clientPosition;
    private final int proximityRange;
    private Driver nextDriver;

    public ProximityIterator(Iterable<Driver> driverPool, Position clientPosition, int proximityRange) {
        if (driverPool == null || clientPosition == null) {
            throw new IllegalArgumentException("Driver pool or client position cannot be null.");
        }
        this.driverIterator = driverPool.iterator();
        this.clientPosition = clientPosition;
        this.proximityRange = proximityRange;
        loadNextDriver();
    }

    @Override
    public boolean hasNext() {
        return nextDriver != null;
    }

    @Override
    public Driver next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more drivers within proximity range.");
        }
        Driver currentDriver = nextDriver;
        loadNextDriver();
        return currentDriver;
    }

    private void loadNextDriver() {
        while (driverIterator.hasNext()) {
            Driver driver = driverIterator.next();
            if (isWithinProximity(driver.getVehicle().getPosition())) {
                nextDriver = driver;
                return;
            }
        }
        nextDriver = null;
    }

    private boolean isWithinProximity(Position driverPosition) {
        return clientPosition.getManhattanDistanceTo(driverPosition) <= proximityRange;
    }
}
