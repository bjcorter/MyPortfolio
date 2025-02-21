package com.comp301.a04junit;

import com.comp301.a04junit.adventure.Item;
import com.comp301.a04junit.adventure.ItemImpl;
import com.comp301.a04junit.adventure.Inventory;
import com.comp301.a04junit.adventure.InventoryImpl;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Write unit tests for the InventoryImpl class here
 */
public class JediInventoryTests {
    @Test
    public void unitTest1() {
        Inventory i = new InventoryImpl();
        assertTrue(i.isEmpty());
        assertEquals(0, i.getNumItems());
        assertEquals(0, i.getItems().size());
    }

    @Test
    public void unitTest2() {
        Inventory i = new InventoryImpl();
        Item item = null;
        i.addItem(item);
        assertFalse(i.isEmpty());
        assertEquals(1, i.getNumItems());
        assertEquals(1, i.getItems().size());
    }

    @Test
    public void unitTest3() {
        Inventory i = new InventoryImpl();
        Item item = new ItemImpl("item");
        i.addItem(item);
        i.removeItem(item);
        assertTrue(i.isEmpty());
        assertEquals(0, i.getNumItems());
        assertEquals(0, i.getItems().size());
    }

    @Test
    public void unitTest4() {
        Inventory i = new InventoryImpl();
        Item item = new ItemImpl("item");
        i.addItem(item);
        i.clear();
        assertTrue(i.isEmpty());
        assertEquals(0, i.getNumItems());
        assertEquals(0, i.getItems().size());
    }

    @Test
    public void unitTest5() {
        Inventory i = new InventoryImpl();
        Item item = new ItemImpl("item");
        Inventory other = new InventoryImpl();
        Item item2 = new ItemImpl("item2");
        i.addItem(item);
        other.addItem(item2);
        i.transferFrom(other);
        assertFalse(i.isEmpty());
        assertTrue(other.isEmpty());
        assertEquals(2, i.getNumItems());
        assertEquals(2, i.getItems().size());
    }

    @Test
    public void unitTest6() {
        Inventory i = new InventoryImpl();
        Item item1 = new ItemImpl("item1");
        Item item2 = new ItemImpl("item2");
        Item item3 = new ItemImpl("item3");

        i.addItem(item1);
        i.addItem(item2);
        i.addItem(item3);

        assertEquals(3, i.getNumItems());
        assertEquals(3, i.getItems().size());

        i.removeItem(item2);

        assertEquals(2, i.getNumItems());
        assertEquals(2, i.getItems().size());

    }

    @Test
    public void unitTest7() {
        Inventory i = new InventoryImpl();
        Item item = new ItemImpl("item");
        i.addItem(item);

        i.getItems().clear();

        assertEquals(1, i.getNumItems());
    }

    @Test
    public void unitTest9() {
        Inventory i = new InventoryImpl();
        Item item = new ItemImpl("item");
        i.addItem(item);

        i.transferFrom(null);

        assertEquals(1, i.getNumItems());
    }
}
