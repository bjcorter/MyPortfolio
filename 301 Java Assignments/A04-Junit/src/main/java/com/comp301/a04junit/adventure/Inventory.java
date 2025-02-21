package com.comp301.a04junit.adventure;

import java.util.List;

public interface Inventory {
  /** Returns true if the Inventory is empty */
  boolean isEmpty();

  /** Getter method for the number of Items currently stored in the inventory */
  int getNumItems();

  /** Getter method which returns a cloned list of the Items in the Inventory */
  List<Item> getItems();

  /** Adds an Item to the Inventory */
  void addItem(Item item);

  /** Removes an Item from the Inventory */
  void removeItem(Item item);

  /** Clears the Inventory */
  void clear();

  /** Removes the Items from an "other" Inventory, and adds them into "this" Inventory */
  void transferFrom(Inventory other);
}
