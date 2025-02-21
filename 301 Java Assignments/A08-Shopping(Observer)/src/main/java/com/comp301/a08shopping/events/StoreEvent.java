package com.comp301.a08shopping.events;

import com.comp301.a08shopping.Product;
import com.comp301.a08shopping.Store;

public interface StoreEvent {
  /** Getter for the product that is involved with this event */
  Product getProduct();

  /** Getter for the store that is involved with this event */
  Store getStore();
}
