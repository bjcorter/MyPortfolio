package com.comp301.a08shopping;

import java.util.List;

public interface Store {
  /** Gets the store's name */
  String getName();

  /** Adds an active observer to the store */
  void addObserver(StoreObserver observer);

  /** Removes an active observer from the store */
  void removeObserver(StoreObserver observer);

  /** Gets (a copy of) the list of all products offered by the store */
  List<Product> getProducts();

  /**
   * Creates a new product in the store. Throws an IllegalArgumentException for invalid values of
   * name, basePrice, and inventory
   */
  Product createProduct(String name, double basePrice, int inventory);

  /**
   * Updates a product's inventory to reflect that one copy of the product was purchased (i.e.
   * decrements the product's inventory integer value). Emits the appropriate StoreEvent or
   * StoreEvents that describe the action. Throws a ProductNotFoundException if the specified
   * product is not sold by the store, an IllegalArgumentException if the product is null, or an
   * OutOfStockException if the product is out of stock. Returns a ReceiptItem object representing
   * the sale
   */
  ReceiptItem purchaseProduct(Product product);

  /**
   * Adds the specified number to the store's inventory for a particular product. Emits the
   * appropriate StoreEvent if the product was previously out of stock. Throws a
   * ProductNotFoundException if the specified product is not sold by the store, an
   * IllegalArgumentException if the product is null, or an IllegalArgumentException if numItems is
   * negative
   */
  void restockProduct(Product product, int numItems);

  /**
   * Starts a sale for a particular product by updating or setting the product's percentOff value
   * and emitting the appropriate StoreEvent. Throws a ProductNotFoundException if the specified
   * product is not sold by the store, an IllegalArgumentException if the product is null, or a
   * IllegalArgumentException if percentOff is not between 0.0 and 1.0
   */
  void startSale(Product product, double percentOff);

  /**
   * Ends a sale for a particular product by removing or resetting the product's percentOff value.
   * Emits the appropriate StoreEvent. Throws a ProductNotFoundException if the specified product is
   * not sold by the store, or an IllegalArgumentException if the product is null
   */
  void endSale(Product product);

  /**
   * Gets the number of copies in stock in the store's inventory for a particular product. Throws a
   * ProductNotFoundException if the product is not sold by the store, or an
   * IllegalArgumentException if the product is null
   */
  int getProductInventory(Product product);

  /**
   * Determines whether a particular product is in stock in the store. Returns true if the product
   * is in stock, or false if it is not. Throws a ProductNotFoundException if the product is not
   * sold by the store, or an IllegalArgumentException if the product is null
   */
  boolean getIsInStock(Product product);

  /**
   * Gets the store's sale price for a particular product in dollars, rounded to the nearest cent.
   * The sale price for a product is equal to the product's base price x (1.0 - percentOff), where
   * percentOff is the sale amount specified by startSale() for the product. Make sure to round the
   * resulting value to the nearest cent. Throws a ProductNotFoundException if the product is not
   * sold by the store, or an IllegalArgumentException if the product is null
   */
  double getSalePrice(Product product);

  /**
   * Determines whether a particular product is on sale at the store. Returns true if the product is
   * on sale, or false if it is not. A product is on sale if its sale price is less than its base
   * price. Throws a ProductNotFoundException if the product is not sold by the store, or an
   * IllegalArgumentException if the product is null
   */
  boolean getIsOnSale(Product product);
}
