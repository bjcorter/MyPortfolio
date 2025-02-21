package com.comp301.a03exceptions;

public class Jedi {

    /**
     * Section 7: Custom exception classes
     * <p>
     * Add a class file to your submission called NegativeValueException.java. In this file, define a
     * class called "NegativeValueException" which extends from RuntimeException. This exception
     * indicates that a negative value was encountered.
     * <p>
     * Inside the section7() method, check the value of parameter n. If n is negative, throw a
     * NegativeValueException. Otherwise, do nothing inside this method.
     */
    public static void section7(int n) {
        if (n < 0) {
            throw new NegativeValueException();
        }
    }

    /**
     * Section 8: Subsequent try blocks
     * <p>
     * Inside this method, call method section7(), but catch a potential NegativeValueException. If a
     * NegativeValueException occurs, this method should return "A NegativeValueException occurred".
     * <p>
     * After calling method section7(), in a separate try/catch block, call static method section1()
     * from the Novice class. If a RuntimeException occurs, this method should return "A
     * RuntimeException occurred".
     * <p>
     * After calling section7() and section1(), if no exception occurred, return "No exception
     * occurred".
     */
    public static String section8(int n) {
        try {
            Jedi.section7(n);
        } catch (NegativeValueException e) {
            return "A NegativeValueException occurred";
        }

        try {
            Novice.section1(n);
        } catch (RuntimeException e) {
            return "A RuntimeException occurred";
        }

        return "No exception occurred";
    }

    /**
     * Section 9: Nested try blocks
     * <p>
     * Inside this method, call method section7(), passing in n as the argument, and catch a potential
     * NegativeValueException. If a NegativeValueException occurs (and only if one occurs), this
     * method should then call method section1() in a nested try/catch block, passing in -n (negative
     * n) as the argument. If a RuntimeException occurs due to section1() being called, this method
     * should return the string "Two exceptions were caught". If section1() executes without throwing
     * an exception, this method should return the string "One exception was caught". If section7()
     * executes without throwing an exception, this method should return the string "No exceptions
     * were caught".
     */
    public static String section9(int n) {
        try {
            Jedi.section7(n);
        } catch (NegativeValueException e) {
            try {
                Novice.section1(-n);
                return "One exception was caught";
            } catch (RuntimeException ex) {
                return "Two exceptions were caught";
            }
        }
        return "No exceptions were caught";
    }
}

