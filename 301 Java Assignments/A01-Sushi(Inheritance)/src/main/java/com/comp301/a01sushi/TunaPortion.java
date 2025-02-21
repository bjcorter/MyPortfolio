package com.comp301.a01sushi;

public class TunaPortion extends IngredientPortionIMPL {
  public TunaPortion(double amount) {
    super(new Tuna(), amount);
  }

  @Override
  public IngredientPortion combine(IngredientPortion other) {
    if (other == null) {
      return this;
    }

    if (!this.getIngredient().equals(other.getIngredient())) {
      throw new IllegalArgumentException("Ingredients must be the same for combination");
    }

    return new TunaPortion(this.getAmount() + other.getAmount());
  }
}
