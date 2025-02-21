package com.comp301.a01sushi;

public class Roll implements Sushi {
  private final String name;
  private final IngredientPortion[] roll_ingredients;

  public Roll(String name, IngredientPortion[] roll_ingredients) {
    if (name == null || roll_ingredients == null || hasNullIng(roll_ingredients)) {
      throw new IllegalArgumentException("Input is invalid");
    }

    boolean hasSeaweed = false;
    for (IngredientPortion portion : roll_ingredients) {
      if (portion.getIngredient() instanceof Seaweed) {
        hasSeaweed = true;
        if (portion.getAmount() < 0.1) {
          throw new IllegalArgumentException("Seaweed portion must be at least 0.1 ounces");
        }
        break;
      }
    }

    if (!hasSeaweed) {
      throw new IllegalArgumentException("Roll must contain seaweed");
    }

    this.name = name;
    this.roll_ingredients = roll_ingredients.clone();
  }

  private boolean hasNullIng(IngredientPortion[] i) {
    for (IngredientPortion ingredient : i) {
      if (ingredient == null) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public IngredientPortion[] getIngredients() {
    return roll_ingredients.clone();
  }

  @Override
  public int getCalories() {
    int total = 0;
    for (IngredientPortion portion : roll_ingredients) {
      total += portion.getCalories();
    }
    return total;
  }

  @Override
  public double getCost() {
    double total = 0.0;
    for (IngredientPortion portion : roll_ingredients) {
      total += portion.getCost();
    }
    return Math.round(total * 100.0) / 100.0;
  }

  @Override
  public boolean getHasRice() {
    for (IngredientPortion portion : roll_ingredients) {
      if (portion.getIsRice()) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean getHasShellfish() {
    for (IngredientPortion portion : roll_ingredients) {
      if (portion.getIsShellfish()) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean getIsVegetarian() {
    for (IngredientPortion portion : roll_ingredients) {
      if (!portion.getIsVegetarian()) {
        return false;
      }
    }
    return true;
  }
}
