package com.comp301.a04junit;


import com.comp301.a04junit.adventure.Item;
import com.comp301.a04junit.adventure.ItemImpl;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Write unit tests for the ItemImpl class here
 */
public class AdeptItemTests {
    @Test
    public void unitTest1() {
        Item i = new ItemImpl("name");

        assertFalse(i.equals(null));
    }

    @Test
    public void unitTest2() {
        Item i = new ItemImpl("name");
        String resultOfGetName = i.getName();
        assertEquals("name", resultOfGetName);
    }

    @Test
    public void unitTest3() {
        Item i1 = new ItemImpl("name");
        Item i2 = new ItemImpl("name");
        Item i3 = new ItemImpl("name2");

        assertTrue(i1.equals(i2));
        assertFalse(i1.equals(i3));
    }

    @Test
    public void unitTest4() {
        Item i = new ItemImpl("name");
        String resultOfToString = i.toString();

        assertEquals("name", resultOfToString);
    }

    @Test
    public void unitTest5() {
        Item i = new ItemImpl("Name");

        assertEquals(i.getName(), "Name");

    }
}
