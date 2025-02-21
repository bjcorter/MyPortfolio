package com.comp301.a01sushi;

public class IngredientIMPL implements Ingredient {
  private final String name;
  private final double pricePerOunce;
  private final int caloriesPerOunce;
  private final boolean isVegetarian;
  private final boolean isRice;
  private final boolean isShellfish;

  public IngredientIMPL(
      String name,
      double pricePerOunce,
      int caloriesPerOunce,
      boolean isVegetarian,
      boolean isRice,
      boolean isShellfish) {
    this.name = name;
    this.pricePerOunce = pricePerOunce;
    this.caloriesPerOunce = caloriesPerOunce;
    this.isVegetarian = isVegetarian;
    this.isRice = isRice;
    this.isShellfish = isShellfish;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public double getCaloriesPerDollar() {
    return caloriesPerOunce / pricePerOunce;
  }

  @Override
  public int getCaloriesPerOunce() {
    return caloriesPerOunce;
  }

  @Override
  public double getPricePerOunce() {
    return pricePerOunce;
  }

  @Override
  public boolean getIsVegetarian() {
    return isVegetarian;
  }

  @Override
  public boolean getIsRice() {
    return isRice;
  }

  @Override
  public boolean getIsShellfish() {
    return isShellfish;
  }

  @Override
  public boolean equals(Ingredient other) {
    if (other == null) {
      return false;
    }
    return this.name.equals(other.getName())
        && this.caloriesPerOunce == other.getCaloriesPerOunce()
        && Math.abs(this.pricePerOunce - other.getPricePerOunce()) < 0.01
        && this.isVegetarian == other.getIsVegetarian()
        && this.isRice == other.getIsRice()
        && this.isShellfish == other.getIsShellfish();
  }
}
