package com.comp301.a02adventure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InventoryImpl implements Inventory, Item {

  private ArrayList<Item> inventory;

  public InventoryImpl() {
    this.inventory = new ArrayList<Item>();
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public boolean equals(Item other) {
    return false;
  }

  @Override
  public boolean isEmpty() {
    if (this.inventory.isEmpty()) {
      return true;
    } else return false;
  }

  @Override
  public int getNumItems() {
    if (this.inventory.size() < 1) {
      return 0;
    } else return this.inventory.size();
  }

  @Override
  public ArrayList<Item> getItems() {
    return new ArrayList<>(this.inventory);
  }

  @Override
  public void addItem(Item item) {
    if (item != null) {
      this.inventory.add(item);
    }
  }

  @Override
  public void removeItem(Item item) {
    if (item != null) {
      this.inventory.remove(item);
    }
  }

  @Override
  public void clear() {
    this.inventory.clear();
  }

  @Override
  public void transferFrom(Inventory other) {
    this.inventory.addAll(other.getItems());
    other.clear();
  }
}
