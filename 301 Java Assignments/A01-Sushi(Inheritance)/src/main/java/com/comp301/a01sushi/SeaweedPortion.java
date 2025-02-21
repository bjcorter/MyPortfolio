package com.comp301.a01sushi;

public class SeaweedPortion extends IngredientPortionIMPL {
  public SeaweedPortion(double amount) {
    super(new Seaweed(), amount);
  }

  @Override
  public IngredientPortion combine(IngredientPortion other) {
    if (other == null) {
      return this;
    }

    if (!this.getIngredient().equals(other.getIngredient())) {
      throw new IllegalArgumentException("Ingredients must be the same for combination");
    }

    return new SeaweedPortion(this.getAmount() + other.getAmount());
  }
}
