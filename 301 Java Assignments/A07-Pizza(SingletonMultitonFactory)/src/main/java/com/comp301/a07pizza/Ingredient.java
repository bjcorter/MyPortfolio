package com.comp301.a07pizza;

public interface Ingredient {
  /** Getter method to retrieve the name of the ingredient */
  String getName();

  /** Returns true only if the ingredient is a vegetarian option */
  boolean isVegetarian();

  /** Returns true only if the ingredient is a vegan option */
  boolean isVegan();
}
