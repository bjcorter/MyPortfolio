package com.comp301.a04junit.adventure;

import java.util.List;

/**
 * You do not have to make changes to this file for a04-junit. This file represents a class that you
 * should write tests for. You are not required to implement this class yourself. See the readme for
 * instructions on what this class is supposed to do.
 */
public class InventoryImpl implements Inventory {
  private List<Item> items;

  public InventoryImpl() {
    // CODE OMITTED
  }

  @Override
  public boolean isEmpty() {
    return false; // CODE OMITTED
  }

  @Override
  public List<Item> getItems() {
    return null; // CODE OMITTED
  }

  @Override
  public int getNumItems() {
    return 0; // CODE OMITTED
  }

  @Override
  public void addItem(Item item) {
    // CODE OMITTED
  }

  @Override
  public void removeItem(Item item) {
    // CODE OMITTED
  }

  @Override
  public void clear() {
    // CODE OMITTED
  }

  @Override
  public void transferFrom(Inventory other) {
    // CODE OMITTED
  }
}
