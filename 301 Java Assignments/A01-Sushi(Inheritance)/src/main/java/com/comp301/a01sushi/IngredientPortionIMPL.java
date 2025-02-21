package com.comp301.a01sushi;

public abstract class IngredientPortionIMPL implements IngredientPortion {
  private final Ingredient ingredient;
  private final double amount;

  public IngredientPortionIMPL(Ingredient ingredient, double amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("Amount must be non-negative");
    }
    this.ingredient = ingredient;
    this.amount = amount;
  }

  @Override
  public Ingredient getIngredient() {
    return ingredient;
  }

  @Override
  public double getAmount() {
    return amount;
  }

  @Override
  public String getName() {
    return ingredient.getName();
  }

  @Override
  public boolean getIsVegetarian() {
    return ingredient.getIsVegetarian();
  }

  @Override
  public boolean getIsRice() {
    return ingredient.getIsRice();
  }

  @Override
  public boolean getIsShellfish() {
    return ingredient.getIsShellfish();
  }

  @Override
  public double getCalories() {
    return amount * ingredient.getCaloriesPerOunce();
  }

  @Override
  public double getCost() {
    return amount * ingredient.getPricePerOunce();
  }

  @Override
  public abstract IngredientPortion combine(IngredientPortion other);
}
