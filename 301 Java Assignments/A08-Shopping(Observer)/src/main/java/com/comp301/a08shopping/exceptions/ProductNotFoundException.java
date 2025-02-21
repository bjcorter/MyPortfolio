package com.comp301.a08shopping.exceptions;

public class ProductNotFoundException extends RuntimeException {
  public ProductNotFoundException() {}

  public ProductNotFoundException(String message) {
    super(message);
  }
}
