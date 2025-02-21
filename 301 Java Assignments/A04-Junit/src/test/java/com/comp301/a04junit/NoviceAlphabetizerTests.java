package com.comp301.a04junit;

import com.comp301.a04junit.alphabetizer.Alphabetizer;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * Write tests for the Alphabetizer class here
 */
public class NoviceAlphabetizerTests {

    @Test
    public void unitTest1() {
        Alphabetizer a = new Alphabetizer(null);
        Exception exception = null;
        try {
            a.next();
        } catch (Exception e) {
            exception = e;
        }
        assertTrue(exception instanceof NoSuchElementException);
    }

    @Test
    public void unitTest2() {
        Alphabetizer a = new Alphabetizer(new String[]{});
        Exception exception = null;
        try {
            a.next();
        } catch (Exception e) {
            exception = e;
        }
        assertTrue(exception instanceof NoSuchElementException);
    }

    @Test
    public void unitTest3() {
        String[] input = {"c1", "a1", "b1"};
        Alphabetizer a = new Alphabetizer(input);

        assertTrue(a.next() == "a1");
        assertTrue(a.next() == "b1");
        assertTrue(a.next() == "c1");

        try {
            a.next();
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void unitTest4() {
        Alphabetizer a = new Alphabetizer(new String[]{"a1"});
        assertTrue(a.hasNext());
        a.next();
        assertFalse(a.hasNext());
    }

    @Test
    public void unitTest5() {
        Alphabetizer a = new Alphabetizer(new String[]{});
        assertFalse(a.hasNext());
    }

    @Test
    public void unitTest6() {
        Alphabetizer a = new Alphabetizer(null);
        assertFalse(a.hasNext());
    }

    @Test
    public void unitTest7() {
        String[] input = {"c1", "a1", "b1"};
        Alphabetizer a = new Alphabetizer(input);

        input[0] = "d1";

        assertTrue(a.next().equals("a1"));
        assertTrue(a.next().equals("b1"));
        assertTrue(a.next().equals("c1"));
    }

    @Test
    public void unitTest9() {
        String[] input = {"A", "b", "C"};
        Alphabetizer a = new Alphabetizer(input);

        assertEquals("A", a.next());
        assertEquals("C", a.next());
        assertEquals("b", a.next());

    }
}

