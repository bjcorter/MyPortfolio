package com.comp301.a01sushi;

public class Sashimi implements Sushi {
  private IngredientPortion ingredientPortion;

  public enum SashimiType {
    TUNA,
    YELLOWTAIL,
    EEL,
    CRAB,
    SHRIMP
  }

  private final SashimiType type;

  public Sashimi(SashimiType type) {
    this.type = type;

    if (type == SashimiType.CRAB) {
      ingredientPortion = new CrabPortion(.75);
    } else if (type == SashimiType.EEL) {
      ingredientPortion = new EelPortion(.75);
    } else if (type == SashimiType.SHRIMP) {
      ingredientPortion = new ShrimpPortion(.75);
    } else if (type == SashimiType.TUNA) {
      ingredientPortion = new TunaPortion(.75);
    } else if (type == SashimiType.YELLOWTAIL) {
      ingredientPortion = new YellowtailPortion(.75);
    }
  }

  @Override
  public String getName() {
    return ingredientPortion.getName() + " sashimi";
  }

  @Override
  public IngredientPortion[] getIngredients() {
    return new IngredientPortion[] {ingredientPortion};
  }

  @Override
  public int getCalories() {
    return (int) Math.round(ingredientPortion.getCalories());
  }

  @Override
  public double getCost() {
    return ingredientPortion.getCost();
  }

  @Override
  public boolean getHasRice() {
    return false;
  }

  @Override
  public boolean getHasShellfish() {
    return type == SashimiType.CRAB || type == SashimiType.SHRIMP;
  }

  @Override
  public boolean getIsVegetarian() {
    return false;
  }
}
