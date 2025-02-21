package com.comp301.a04junit;

import com.comp301.a04junit.adventure.Position;
import com.comp301.a04junit.adventure.PositionImpl;

import org.junit.Test;

import static com.comp301.a04junit.adventure.Direction.*;
import static org.junit.Assert.*;

/**
 * Write unit tests for the PositionImpl class here
 */
public class AdeptPositionTests {
    @Test
    public void unitTest1() {
        Position p = new PositionImpl(1, 2);
        assertFalse(p.equals(null));
    }

    @Test
    public void unitTest2() {
        Position p1 = new PositionImpl(1, 2);
        assertEquals(1, p1.getX());
        assertEquals(2, p1.getY());

        Position p2 = new PositionImpl(0, 0);
        assertEquals(0, p2.getX());
        assertEquals(0, p2.getY());

    }

    @Test
    public void unitTest3() {
        Position p = new PositionImpl(5, 5);

        Position northNeighbor = p.getNeighbor(NORTH);
        Position southNeighbor = p.getNeighbor(SOUTH);
        Position eastNeighbor = p.getNeighbor(EAST);
        Position westNeighbor = p.getNeighbor(WEST);

        assertEquals(p.getX(), northNeighbor.getX());
        assertEquals(p.getY() + 1, northNeighbor.getY());

        assertEquals(p.getX(), southNeighbor.getX());
        assertEquals(p.getY() - 1, southNeighbor.getY());

        assertEquals(p.getX() + 1, eastNeighbor.getX());
        assertEquals(p.getY(), eastNeighbor.getY());

        assertEquals(p.getX() - 1, westNeighbor.getX());
        assertEquals(p.getY(), westNeighbor.getY());
    }


    @Test
    public void unitTest4() {
        Position p = new PositionImpl(1, 2);
        int initialX = p.getX();
        int initialY = p.getY();

        p.getX();
        p.getY();

        assertEquals(initialX, p.getX());
        assertEquals(initialY, p.getY());
    }

}