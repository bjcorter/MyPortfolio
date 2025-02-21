package com.comp301.a08shopping.exceptions;

public class OutOfStockException extends RuntimeException {
  public OutOfStockException() {}

  public OutOfStockException(String message) {
    super(message);
  }
}
