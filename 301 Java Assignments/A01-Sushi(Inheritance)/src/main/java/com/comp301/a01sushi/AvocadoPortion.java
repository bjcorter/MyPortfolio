package com.comp301.a01sushi;

public class AvocadoPortion extends IngredientPortionIMPL {
  public AvocadoPortion(double amount) {
    super(new Avocado(), amount);
  }

  @Override
  public IngredientPortion combine(IngredientPortion other) {
    if (other == null) {
      return this;
    }

    if (!this.getIngredient().equals(other.getIngredient())) {
      throw new IllegalArgumentException("Ingredients must be the same for combination");
    }

    return new AvocadoPortion(this.getAmount() + other.getAmount());
  }
}
