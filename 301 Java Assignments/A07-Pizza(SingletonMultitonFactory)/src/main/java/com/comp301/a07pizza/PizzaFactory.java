package com.comp301.a07pizza;

import java.util.ArrayList;

public class PizzaFactory {

  public static Pizza makeCheesePizza(Pizza.Size size) {
    Ingredient[] toppings = new Ingredient[0];
    Pizza cheesePizza =
        new PizzaImpl(size, Crust.HAND_TOSSED, Sauce.TOMATO, Cheese.BLEND, toppings);
    return cheesePizza;
  }

  public static Pizza makeHawaiianPizza(Pizza.Size size) {
    Ingredient[] toppings = new Ingredient[2];
    toppings[0] = Topping.HAM;
    toppings[1] = Topping.PINEAPPLE;
    Pizza hawaiianPizza =
        new PizzaImpl(size, Crust.HAND_TOSSED, Sauce.TOMATO, Cheese.BLEND, toppings);
    return hawaiianPizza;
  }

  public static Pizza makeMeatLoversPizza(Pizza.Size size) {
    Ingredient[] toppings = new Ingredient[4];
    toppings[0] = Topping.PEPPERONI;
    toppings[1] = Topping.SAUSAGE;
    toppings[2] = Topping.BACON;
    toppings[3] = Topping.GROUND_BEEF;
    Pizza meatLoversPizza =
        new PizzaImpl(size, Crust.DEEP_DISH, Sauce.TOMATO, Cheese.BLEND, toppings);
    return meatLoversPizza;
  }

  public static Pizza makeVeggieSupremePizza(Pizza.Size size) {
    Ingredient[] toppings = new Ingredient[4];
    toppings[0] = Topping.SUN_DRIED_TOMATO;
    toppings[1] = Topping.GREEN_PEPPER;
    toppings[2] = Topping.MUSHROOMS;
    toppings[3] = Topping.OLIVES;
    Pizza veggieSupremePizza =
        new PizzaImpl(size, Crust.THIN, Sauce.TOMATO, Cheese.BLEND, toppings);
    return veggieSupremePizza;
  }

  public static Pizza makeDailySpecialPizza() {
    Ingredient[] toppings = new Ingredient[17];
    toppings[0] = Topping.OLIVES;
    toppings[1] = Topping.PINEAPPLE;
    toppings[2] = Topping.PEPPERONI;
    toppings[3] = Topping.BUFFALO_CHICKEN;
    toppings[4] = Topping.ONION;
    toppings[5] = Topping.JALAPENO;
    toppings[6] = Topping.HAM;
    toppings[7] = Topping.SAUSAGE;
    toppings[8] = Topping.SPINACH;
    toppings[9] = Topping.TOMATO;
    toppings[10] = Topping.GREEN_PEPPER;
    toppings[11] = Topping.GARLIC;
    toppings[12] = Topping.MUSHROOMS;
    toppings[13] = Topping.SUN_DRIED_TOMATO;
    toppings[14] = Topping.GROUND_BEEF;
    toppings[15] = Topping.ANCHOVIES;
    toppings[16] = Topping.BACON;
    Pizza dailySpecialPizza =
        new PizzaImpl(Pizza.Size.LARGE, Crust.DEEP_DISH, Sauce.RANCH, Cheese.BLEND, toppings);
    return dailySpecialPizza;
  }
}
