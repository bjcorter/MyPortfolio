package com.comp301.a01sushi;

public class EelPortion extends IngredientPortionIMPL {
  public EelPortion(double amount) {
    super(new Eel(), amount);
  }

  @Override
  public IngredientPortion combine(IngredientPortion other) {
    if (other == null) {
      return this;
    }

    if (!this.getIngredient().equals(other.getIngredient())) {
      throw new IllegalArgumentException("Ingredients must be the same for combination");
    }

    return new EelPortion(this.getAmount() + other.getAmount());
  }
}
