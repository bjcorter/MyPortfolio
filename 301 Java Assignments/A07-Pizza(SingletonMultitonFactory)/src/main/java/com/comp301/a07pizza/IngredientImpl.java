package com.comp301.a07pizza;

public class IngredientImpl implements Ingredient {

  private final String name;
  private final boolean isVegan;
  private final boolean isVegetarian;

  public IngredientImpl(String name, boolean isVegan, boolean isVegetarian) {
    this.name = name;
    this.isVegan = isVegan;
    this.isVegetarian = isVegetarian;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean isVegetarian() {
    return isVegetarian;
  }

  @Override
  public boolean isVegan() {
    return isVegan;
  }
}
