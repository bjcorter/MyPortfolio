package com.comp301.a01sushi;

public class YellowtailPortion extends IngredientPortionIMPL {
  public YellowtailPortion(double amount) {
    super(new Yellowtail(), amount);
  }

  @Override
  public IngredientPortion combine(IngredientPortion other) {
    if (other == null) {
      return this;
    }

    if (!this.getIngredient().equals(other.getIngredient())) {
      throw new IllegalArgumentException("Ingredients must be the same for combination");
    }

    return new YellowtailPortion(this.getAmount() + other.getAmount());
  }
}
