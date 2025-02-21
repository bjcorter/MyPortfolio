/*
 * No need to make changes to this file for a08-shopping. This file represents a receipt from a
 * store after an item is purchased. You'll need to use this class in the
 * StoreImpl.purchaseProduct() method
 */

package com.comp301.a08shopping;

public class ReceiptItemImpl implements ReceiptItem {
  private final String productName;
  private final double pricePaid;
  private final String storeName;

  public ReceiptItemImpl(String productName, double pricePaid, String storeName) {
    this.productName = productName;
    this.pricePaid = pricePaid;
    this.storeName = storeName;
  }

  @Override
  public String getProductName() {
    return productName;
  }

  @Override
  public double getPricePaid() {
    return pricePaid;
  }

  @Override
  public String getStoreName() {
    return storeName;
  }
}
