package com.comp301.a01sushi;

public class RicePortion extends IngredientPortionIMPL {
  public RicePortion(double amount) {
    super(new Rice(), amount);
  }

  @Override
  public IngredientPortion combine(IngredientPortion other) {
    if (other == null) {
      return this;
    }

    if (!this.getIngredient().equals(other.getIngredient())) {
      throw new IllegalArgumentException("Ingredients must be the same for combination");
    }

    return new RicePortion(this.getAmount() + other.getAmount());
  }
}
