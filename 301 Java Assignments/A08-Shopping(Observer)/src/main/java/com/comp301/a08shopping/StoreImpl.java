package com.comp301.a08shopping;

import com.comp301.a08shopping.events.*;
import com.comp301.a08shopping.exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class StoreImpl implements Store {

  private String name;
  private List<StoreObserver> observers;
  private List<Product> products;
  private List<Integer> inventory;
  private List<Double> salePrices;

  public StoreImpl(String name) {
    if (name == null) {
      throw new IllegalArgumentException();
    }
    this.name = name;
    this.observers = new ArrayList<>();
    this.products = new ArrayList<>();
    this.inventory = new ArrayList<>();
    this.salePrices = new ArrayList<>();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void addObserver(StoreObserver observer) {
    if (observer == null) {
      throw new IllegalArgumentException();
    }
    observers.add(observer);
  }

  @Override
  public void removeObserver(StoreObserver observer) {
    observers.remove(observer);
  }

  @Override
  public List<Product> getProducts() {
    return new ArrayList<>(products);
  }

  @Override
  public Product createProduct(String name, double basePrice, int inventory) {
    if (name == null || basePrice <= 0.0 || inventory < 0) {
      throw new IllegalArgumentException();
    }
    Product product = new ProductImpl(name, basePrice);
    products.add(product);
    this.inventory.add(inventory);
    salePrices.add(basePrice);
    return product;
  }

  @Override
  public ReceiptItem purchaseProduct(Product product) {
    if (product == null) {
      throw new IllegalArgumentException();
    }
    int index = products.indexOf(product);
    if (!products.contains(product)) {
      throw new ProductNotFoundException();
    }
    int stock = inventory.get(index);
    if (stock <= 0) {
      throw new OutOfStockException();
    }
    double salePrice = getSalePrice(product);
    inventory.set(index, stock - 1);
    for (StoreObserver observer : observers) {
      observer.update(new PurchaseEvent(product, this));
      if (stock == 1) {
        observer.update(new OutOfStockEvent(product, this));
      }
    }
    return new ReceiptItemImpl(product.getName(), salePrice, this.name);
  }

  @Override
  public void restockProduct(Product product, int numItems) {
    if (product == null || numItems < 0) {
      throw new IllegalArgumentException();
    }
    int index = products.indexOf(product);
    if (index == -1) {
      throw new ProductNotFoundException();
    }
    int stock = inventory.get(index);
    boolean wasOut = stock == 0;
    inventory.set(index, stock + numItems);
    if (wasOut) {
      for (StoreObserver observer : observers) {
        observer.update(new BackInStockEvent(product, this));
      }
    }
  }

  @Override
  public void startSale(Product product, double percentOff) {
    if (product == null || percentOff < 0.0 || percentOff > 1.0) {
      throw new IllegalArgumentException();
    }
    int index = products.indexOf(product);
    if (index == -1) {
      throw new ProductNotFoundException();
    }
    salePrices.set(index, product.getBasePrice() * (1 - percentOff));
    for (StoreObserver observer : observers) {
      observer.update(new SaleStartEvent(product, this));
    }
  }

  @Override
  public void endSale(Product product) {
    if (product == null) {
      throw new IllegalArgumentException();
    }
    int index = products.indexOf(product);
    if (index == -1) {
      throw new ProductNotFoundException();
    }
    salePrices.set(index, product.getBasePrice());
    for (StoreObserver observer : observers) {
      observer.update(new SaleEndEvent(product, this));
    }
  }

  @Override
  public int getProductInventory(Product product) {
    if (product == null) {
      throw new IllegalArgumentException();
    }
    int index = products.indexOf(product);
    if (index == -1) {
      throw new ProductNotFoundException();
    }
    return inventory.get(index);
  }

  @Override
  public boolean getIsInStock(Product product) {
    if (product == null) {
      throw new IllegalArgumentException();
    }
    int index = products.indexOf(product);
    if (index == -1) {
      throw new ProductNotFoundException();
    }
    return inventory.get(index) > 0;
  }

  @Override
  public double getSalePrice(Product product) {
    if (product == null) {
      throw new IllegalArgumentException();
    }
    int index = products.indexOf(product);
    if (index == -1) {
      throw new ProductNotFoundException();
    }
    return salePrices.get(index);
  }

  @Override
  public boolean getIsOnSale(Product product) {
    if (product == null) {
      throw new IllegalArgumentException();
    }
    int index = products.indexOf(product);
    if (index == -1) {
      throw new ProductNotFoundException();
    }
    return salePrices.get(index) < product.getBasePrice();
  }
}
