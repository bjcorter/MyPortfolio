package com.comp301.a08shopping;

public interface ReceiptItem {
  /** Gets the product's name */
  String getProductName();

  /** Gets the price paid for the product */
  double getPricePaid();

  /** Gets the store's name */
  String getStoreName();
}
