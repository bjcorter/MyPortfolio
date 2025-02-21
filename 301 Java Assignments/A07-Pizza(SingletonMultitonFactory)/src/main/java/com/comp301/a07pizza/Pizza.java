package com.comp301.a07pizza;

import java.util.ArrayList;

public interface Pizza {

  /** Returns true if the pizza consists entirely of vegetarian ingredients */
  boolean isVegetarian();

  /** Returns true if the pizza consists entirely of vegan ingredients */
  boolean isVegan();

  /** Getter method to get the price of the pizza */
  double getPrice();

  /** Getter method to get the size of the pizza */
  Size getSize();

  /** Getter method for the type of sauce in the pizza */
  Ingredient getSauce();

  /** Getter method for the type of cheese in the pizza */
  Ingredient getCheese();

  /** Getter method for the type of crust in the pizza */
  Ingredient getCrust();

  /** Getter method for the list of toppings in the pizza */
  Ingredient[] getToppings();

  /**
   * Getter method for all the ingredients in the pizza including toppings, cheese, crust, and sauce
   */
  Ingredient[] getIngredients();

  /** This enum represents the possible sizes of a pizza */
  enum Size {
    SMALL,
    MEDIUM,
    LARGE
  }
}
