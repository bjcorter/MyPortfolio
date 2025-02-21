/*
 * No need to make changes to this file for a08-shopping. This file handles randomly starting and
 * ending sales in the different stores
 */

package com.comp301.a08shopping;

import java.util.List;

public class SaleSpawner implements Runnable {
  private final List<Store> stores;

  public SaleSpawner(List<Store> stores) {
    this.stores = stores;
  }

  /** Waits a random amount of time between 5 and 20 seconds */
  private static void waitRandomAmountOfTime() throws InterruptedException {
    int ms = 5000 + (int) (Math.random() * 15000); // waits for 5-to-20 seconds
    Thread.sleep(ms);
  }

  @Override
  public void run() {
    while (true) {
      // Wait a random amount of time
      try {
        waitRandomAmountOfTime();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        return;
      }

      // Pick a random store
      Store store = pickRandomStore();

      // Pick a random product in the store
      Product product = pickRandomProduct(store);

      // Pick an action at random (toggle sale state or purchase a product)
      String randomAction = pickRandomAction();
      switch (randomAction) {
        case "PURCHASE/RESTOCK":
          if (store.getIsInStock(product)) {
            store.purchaseProduct(product);
          } else {
            store.restockProduct(product, (int) Math.round(Math.random() * 5));
          }
          break;
        case "TOGGLE SALE":
          if (store.getIsOnSale(product)) {
            store.endSale(product);
          } else {
            store.startSale(product, Math.random());
          }
          break;
      }
    }
  }

  /** Picks a random store in the list */
  private Store pickRandomStore() {
    int randomStoreIndex = (int) (Math.random() * stores.size());
    return stores.get(randomStoreIndex);
  }

  /** Picks a random product in a given store */
  private Product pickRandomProduct(Store store) {
    List<Product> products = store.getProducts();
    int randomProductIndex = (int) (Math.random() * products.size());
    return products.get(randomProductIndex);
  }

  /** Picks an action at random (50% probability between purchase/restock or toggle sale) */
  private String pickRandomAction() {
    double action = Math.random();
    if (action > 0.5) {
      return "PURCHASE/RESTOCK";
    } else {
      return "TOGGLE SALE";
    }
  }
}
