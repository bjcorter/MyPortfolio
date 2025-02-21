package com.comp301.a01sushi;

public class Nigiri implements Sushi {
  private IngredientPortion ingredientPortion;

  public enum NigiriType {
    TUNA,
    YELLOWTAIL,
    EEL,
    SHRIMP,
    CRAB
  }

  private final NigiriType type;

  public Nigiri(NigiriType type) {
    this.type = type;

    if (type == NigiriType.CRAB) {
      ingredientPortion = new CrabPortion(.75);
    } else if (type == NigiriType.EEL) {
      ingredientPortion = new EelPortion(.75);
    } else if (type == NigiriType.SHRIMP) {
      ingredientPortion = new ShrimpPortion(.75);
    } else if (type == NigiriType.TUNA) {
      ingredientPortion = new TunaPortion(.75);
    } else if (type == NigiriType.YELLOWTAIL) {
      ingredientPortion = new YellowtailPortion(.75);
    } else {
      throw new IllegalArgumentException("Unsupported NigiriType: " + type);
    }
  }

  @Override
  public String getName() {
    return type.toString().toLowerCase() + " nigiri";
  }

  @Override
  public IngredientPortion[] getIngredients() {
    IngredientPortion p = null;
    IngredientPortion riceP = new RicePortion(.5);

    if (type == NigiriType.CRAB) {
      p = new CrabPortion(.75);
    } else if (type == NigiriType.EEL) {
      p = new EelPortion(.75);
    } else if (type == NigiriType.SHRIMP) {
      p = new ShrimpPortion(.75);
    } else if (type == NigiriType.TUNA) {
      p = new TunaPortion(.75);
    } else if (type == NigiriType.YELLOWTAIL) {
      p = new YellowtailPortion(.75);
    }
    return new IngredientPortion[] {p, riceP};
  }

  @Override
  public int getCalories() {
    return (int)
        (Math.round(getIngredients()[0].getCalories())
            + Math.round(getIngredients()[1].getCalories()));
  }

  @Override
  public double getCost() {
    return getIngredients()[0].getCost() + getIngredients()[1].getCost();
  }

  @Override
  public boolean getHasRice() {
    return true;
  }

  @Override
  public boolean getHasShellfish() {
    return type == NigiriType.CRAB || type == NigiriType.SHRIMP;
  }

  @Override
  public boolean getIsVegetarian() {
    return false;
  }
}
