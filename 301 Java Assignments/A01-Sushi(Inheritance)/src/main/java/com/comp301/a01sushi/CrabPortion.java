package com.comp301.a01sushi;

public class CrabPortion extends IngredientPortionIMPL {
  public CrabPortion(double amount) {
    super(new Crab(), amount);
  }

  @Override
  public IngredientPortion combine(IngredientPortion other) {
    if (other == null) {
      return this;
    }

    if (!this.getIngredient().equals(other.getIngredient())) {
      throw new IllegalArgumentException("Ingredients must be the same for combination");
    }

    return new CrabPortion(this.getAmount() + other.getAmount());
  }
}
