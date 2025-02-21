package com.comp301.a04junit;

import com.comp301.a04junit.adventure.*;

import org.junit.Test;

import static com.comp301.a04junit.adventure.Direction.*;
import static com.comp301.a04junit.adventure.Direction.WEST;
import static org.junit.Assert.*;

/**
 * Write unit tests for the PlayerImpl class here
 */
public class JediPlayerTests {
    @Test
    public void unitTest1() {
        String validName = "name";
        int validX = 1;
        int validY = 1;
        Position validPosition = new PositionImpl(validX, validY);
        Player p = new PlayerImpl(validName, validX, validY);

        assertEquals(p.getName(), validName);
        assertEquals(p.getPosition().getX(), validX);
        assertEquals(p.getPosition().getY(), validY);
    }

    @Test
    public void unitTest2() {
        String validName = "name";
        int validX = 5;
        int validY = 5;
        Player p = new PlayerImpl(validName, validX, validY);

        p.move(Direction.NORTH);
        assertEquals(p.getPosition().getY(), validY + 1);
        assertEquals(p.getPosition().getX(), validX);

        p.move(Direction.SOUTH);
        assertEquals(p.getPosition().getY(), validY);
        assertEquals(p.getPosition().getX(), validX);

        p.move(Direction.EAST);
        assertEquals(p.getPosition().getY(), validY);
        assertEquals(p.getPosition().getX(), validX + 1);

        p.move(Direction.WEST);
        assertEquals(p.getPosition().getY(), validY);
        assertEquals(p.getPosition().getX(), validX);
    }


    @Test
    public void unitTest3() {
        String validName = "name";
        int validX = 5;
        int validY = 5;
        Player p = new PlayerImpl(validName, validX, validY);

        assertNotNull(p.getInventory());
        assertTrue(p.getInventory().isEmpty());
        assertEquals(0, p.getInventory().getNumItems());
        assertEquals(0, p.getInventory().getItems().size());

        Item item = new ItemImpl("item1");
        p.getInventory().addItem(item);

        assertFalse(p.getInventory().isEmpty());
        assertEquals(1, p.getInventory().getNumItems());
        assertEquals(1, p.getInventory().getItems().size());
        assertTrue(p.getInventory().getItems().contains(item));
    }


    @Test
    public void unitTest4() {
        String invalidName = null;
        int invalidX = -1;
        int invalidY = 0;

        try {
            new PlayerImpl(invalidName, invalidX, invalidY);
            fail("Expected IllegalArgumentException but no exception was thrown.");
        } catch (IllegalArgumentException e) {

        }

    }
}


