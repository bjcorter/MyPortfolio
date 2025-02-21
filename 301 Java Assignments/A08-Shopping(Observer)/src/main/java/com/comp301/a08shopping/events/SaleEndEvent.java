package com.comp301.a08shopping.events;

import com.comp301.a08shopping.Product;
import com.comp301.a08shopping.Store;

public class SaleEndEvent implements StoreEvent {

  private final Store store;
  private final Product product;

  public SaleEndEvent(Product product, Store store) {
    if (product == null || store == null) {
      throw new IllegalArgumentException();
    }
    this.product = product;
    this.store = store;
  }

  @Override
  public Product getProduct() {
    return product;
  }

  @Override
  public Store getStore() {
    return store;
  }
}
