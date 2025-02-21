package com.comp301.a05driver;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ExpandingProximityIterator implements Iterator<Driver> {
    private final Iterable<Driver> driverPool;
    private final Position clientPosition;
    private final int expansionStep;
    private Iterator<Driver> driverIterator;
    private int currentRange;
    private final int totalDrivers;
    private int visitedDrivers;
    private Driver nextDriver = null;

    public ExpandingProximityIterator(Iterable<Driver> driverPool, Position clientPosition, int expansionStep) {
        int totalDrivers1;
        if (driverPool == null || clientPosition == null) {
            throw new IllegalArgumentException("Driver pool or client position cannot be null.");
        }
        this.driverPool = driverPool;
        this.clientPosition = clientPosition;
        this.expansionStep = expansionStep;
        this.driverIterator = driverPool.iterator();
        this.currentRange = 1;
        this.visitedDrivers = 0;
        totalDrivers1 = 0;
        for (Driver driver : driverPool) {
            totalDrivers1 += 1;
        }
        this.totalDrivers = totalDrivers1;
        loadNext();
    }

    private void loadNext() {
        while (nextDriver == null && visitedDrivers < totalDrivers) {
            if (!driverIterator.hasNext()) {
                currentRange += expansionStep;
                driverIterator = driverPool.iterator();
            }
            while (driverIterator.hasNext()) {
                Driver current = driverIterator.next();
                int dist = clientPosition.getManhattanDistanceTo(current.getVehicle().getPosition());
                if (dist <= currentRange && dist > currentRange - expansionStep) {
                    nextDriver = current;
                    break;
                }
            }
        }
    }

    @Override
    public boolean hasNext() {
        if (nextDriver == null && visitedDrivers < totalDrivers) {
            loadNext();
        }
        return nextDriver != null;
    }

    @Override
    public Driver next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more drivers within proximity range.");
        }
        Driver driver = nextDriver;
        nextDriver = null;
        visitedDrivers++;

        return driver;
    }
}
