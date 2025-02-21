package com.comp301.a05driver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class SnakeOrderAcrossPoolsIterator implements Iterator<Driver> {
    private final List<Iterator<Driver>> driverIterators;
    private int currentIndex;

    public SnakeOrderAcrossPoolsIterator(List<Iterable<Driver>> driverPools) {
        if (driverPools == null) {
            throw new IllegalArgumentException("Driver pools cannot be null.");
        }
        driverIterators = new ArrayList<>();
        for (Iterable<Driver> driverPool : driverPools) {
            driverIterators.add(driverPool.iterator());
        }
        currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        for (Iterator<Driver> iterator : driverIterators) {
            if (iterator.hasNext()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Driver next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more drivers available.");
        }
        Driver nextDriver = driverIterators.get(currentIndex).next();
        updateIndex();
        return nextDriver;
    }

    private void updateIndex() {
        if (currentIndex == 0 && !driverIterators.get(currentIndex).hasNext()) {
            currentIndex = driverIterators.size() - 1;
        } else if (currentIndex == driverIterators.size() - 1 && !driverIterators.get(currentIndex).hasNext()) {
            currentIndex = 0;
        } else {
            currentIndex = (currentIndex + 1) % driverIterators.size();
        }
    }
}
