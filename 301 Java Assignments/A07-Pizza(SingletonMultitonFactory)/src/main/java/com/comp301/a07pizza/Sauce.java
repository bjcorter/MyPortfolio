package com.comp301.a07pizza;

public class Sauce extends IngredientImpl {

  public static final Sauce TOMATO = new Sauce("tomato", true, true);
  public static final Sauce BARBECUE = new Sauce("barbecue", true, true);
  public static final Sauce PESTO = new Sauce("pesto", true, true);
  public static final Sauce ALFREDO = new Sauce("alfredo", false, true);
  public static final Sauce RANCH = new Sauce("ranch", false, true);

  private Sauce(String name, boolean isVegan, boolean isVegetarian) {
    super(name, isVegan, isVegetarian);
  }
}
