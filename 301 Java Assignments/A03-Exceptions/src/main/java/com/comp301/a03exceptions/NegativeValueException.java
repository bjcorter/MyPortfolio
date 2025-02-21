package com.comp301.a03exceptions;

public class NegativeValueException extends RuntimeException {
    public NegativeValueException() {
        super("Negative value encountered");
    }
}
