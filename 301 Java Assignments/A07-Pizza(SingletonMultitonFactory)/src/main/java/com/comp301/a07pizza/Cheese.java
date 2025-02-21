package com.comp301.a07pizza;

public class Cheese extends IngredientImpl {
  public static final Cheese MOZZARELLA = new Cheese("mozzarella", false, true);
  public static final Cheese BLEND = new Cheese("blend", false, true);
  public static final Cheese VEGAN = new Cheese("vegan", true, true);

  private Cheese(String name, boolean isVegan, boolean isVegetarian) {
    super(name, isVegan, isVegetarian);
  }
}
