package com.comp301.a07pizza;

import java.util.ArrayList;

public class PizzaImpl implements Pizza {

  private final Size size;
  private final Crust crust;
  private final Sauce sauce;
  private final Cheese cheese;
  private final Ingredient[] toppings;

  public PizzaImpl(Size size, Crust crust, Sauce sauce, Cheese cheese, Ingredient[] toppings) {
    this.size = size;
    this.crust = crust;
    this.sauce = sauce;
    this.cheese = cheese;
    this.toppings = toppings;
  }

  @Override
  public boolean isVegetarian() {
    for (Ingredient topping : toppings) {
      if (!topping.isVegetarian()) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean isVegan() {
    if (!sauce.isVegan()) {
      return false;
    }
    if (!cheese.isVegan()) {
      return false;
    }
    for (Ingredient topping : toppings) {
      if (!topping.isVegan()) {
        return false;
      }
    }
    return true;
  }

  @Override
  public double getPrice() {
    double price = 0;
    if (size == Size.SMALL) {
      price = 7;
      for (Ingredient topping : toppings) {
        price += 0.25;
      }
    }
    if (size == Size.MEDIUM) {
      price = 9;
      for (Ingredient topping : toppings) {
        price += 0.50;
      }
    }
    if (size == Size.LARGE) {
      price = 11;
      for (Ingredient topping : toppings) {
        price += 0.75;
      }
    }
    return price;
  }

  @Override
  public Size getSize() {
    return size;
  }

  @Override
  public Ingredient getSauce() {
    return sauce;
  }

  @Override
  public Ingredient getCheese() {
    return cheese;
  }

  @Override
  public Ingredient getCrust() {
    return crust;
  }

  @Override
  public Ingredient[] getToppings() {
    return toppings;
  }

  @Override
  public Ingredient[] getIngredients() {
    int numIngredients = 3 + toppings.length;

    Ingredient[] ingredients = new Ingredient[numIngredients];

    ingredients[0] = crust;
    ingredients[1] = sauce;
    ingredients[2] = cheese;

    for (int i = 0; i < toppings.length; i++) {
      ingredients[i + 3] = toppings[i];
    }

    return ingredients;
  }
}
