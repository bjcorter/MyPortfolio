package com.comp301.a01sushi;

public class ShrimpPortion extends IngredientPortionIMPL {
  public ShrimpPortion(double amount) {
    super(new Shrimp(), amount);
  }

  @Override
  public IngredientPortion combine(IngredientPortion other) {
    if (other == null) {
      return this;
    }

    if (!this.getIngredient().equals(other.getIngredient())) {
      throw new IllegalArgumentException("Ingredients must be the same for combination");
    }

    return new ShrimpPortion(this.getAmount() + other.getAmount());
  }
}
