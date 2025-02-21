# a01-sushi

In this assignment, you will create a set of classes that represent ingredients for pieces of sushi. If you have never eaten sushi or don't know what a "sushi boat" restaurant is, you should check out Kurama Express on Columbia St. near Franklin (next to Buns) to get an idea of what one looks like. Sushi is a type of Japanese cuisine, typically made out of raw seafood, rice, seaweed, and sometimes other ingredients. A sushi boat restaurant is one in which pieces of sushi are placed on colored plates and the plates are placed on a circular conveyor belt. Although today most conveyor belts are electric, they used to be moats of water with little boats strung together acting as a conveyor belt, hence the name "sushi boat". Customers are seated around the conveyor and pick up the plates they want as the plates go by. Different plates are assigned different prices.

First, read the comments in the code for the following interfaces defined in package a1 to understand the abstractions for ingredients, ingredient portions, and sushi:
* Ingredient
* IngredientPortion
* Sushi

## Novice

Create eight classes which implement the ```Ingredient``` interface called ```Avocado```, ```Crab```, ```Eel```, ```Rice```, ```Yellowtail```, ```Seaweed```, ```Shrimp```, and ```Tuna```. The characteristics of these classes are given in the following table:

| Class Name | Name | Price/Oz. | Calories/Oz. | isVegetarian? | hasRice? | hasShellfish? |
|------------|------|-----------|--------------|-------------|-------|------------|
| Avocado	| "avocado"	| $0.24 |	42	| true | false | false |
| Crab	| "crab" | $0.72 | 37 | false | false | true |
| Eel	| "eel"	| $2.15 | 82 | false | false | false |
| Rice	| "rice" | $0.13 | 34 | true | true | false |
| Yellowtail | "yellowtail" | $0.74 | 57 | false | false | false |
| Seaweed	| "seaweed"	| $2.85 | 105 | true | false | false |
| Shrimp | "shrimp" | $0.65 | 32 | false | false | true |
| Tuna	| "tuna"	| $1.67 | 42	| false	| false	| false |

All of these classes should provide a constructor without any parameters. For example, for the Avocado class, the constructor should look like this:

```
public Avocado() {
  // Constructor code goes here
}
```

You should employ inheritance to implement these classes. The easiest way to do this is to create a single parent class implementing the interface that encapsulates all of the information about an ingredient as private fields. You can then create individual subclasses for each specific ingredient. The constructor of each ingredient subclass should simply call the superclass constructor, passing the appropriate values from the table as arguments.

Next, create eight new classes that implement ```IngredientPortion```, called ```AvocadoPortion```, ```CrabPortion```, ```EelPortion```, ```RicePortion```, ```YellowtailPortion```, ```SeaweedPortion```, ```ShrimpPortion```, and ```TunaPortion```. Each of these classes should provide a constructor that accepts a single parameter, indicating the amount of the portion in ounces. For example, for the ```AvocadoPortion``` class, the constructor should look like this:

```
public AvocadoPortion (double amount) {
  // Constructor code goes here
}
```

The constructor should throw an ```IllegalArgumentException``` if the amount specified is less than 0.

Each of these classes should encapsulate an instance of ```Ingredient```, representing the ingredient that it is a portion of. For example, ```AvocadoPortion``` should use an instance of ```Avocado``` as its ingredient. The IngredientPortion classes should also implement the ```combine``` method to use the appropriate subclass according to which ingredient it is. For example, combining an instance of ```AvocadoPortion``` with another instance of ```AvocadoPortion``` should return a new instance of ```AvocadoPortion```.

Again the easiest way to do this is to first create a parent class that implements an ingredient portion, and then create subclasses that simply provide the appropriate constructor and any subclass-specific method implementations that can not be provided generally (HINT: you'll need a subclass specific version of ```combine```).

As you implement the IngredientPortion classes, think about whether or not you can reuse the same ingredient instance for every ingredient portion of the same type. This is not necessary for full points but is just a challenge. (HINT: Think about immutability)

## Adept

Create the following three classes which implement the Sushi interface.

### Sashimi

A piece of sashimi is comprised of 0.75 ounces of some type of seafood. There are five types of sashimi: tuna, yellowtail, eel, crab, and shrimp. The ```Sashimi``` class should define a public enumeration called ```SashimiType``` with the following definition:

```
public enum SashimiType {TUNA, YELLOWTAIL, EEL, CRAB, SHRIMP};
```

The ```Sashimi``` class should have a constructor with the following signature:

```
public Sashimi(SashimiType type)
```

The value of ```type``` passed to the constructor indicates what type of sashimi is being made. The name associated with a ```Sashimi``` object should be the name of the underlying seafood ingredient followed by the word "sashimi". For example, a tuna sashmi object should be produce the value "tuna sashimi" as the result of the ```getName``` method.

### Nigiri

A piece of nigiri is comprised of 0.75 ounces of some type of seafood and 0.5 ounces of rice. There are five types of nigiri: tuna, yellowtail, eel, crab, and shrimp. The ```Nigiri``` class should define a public enumeration called ```NigiriType``` with the following definition:

```
public enum NigiriType {TUNA, YELLOWTAIL, EEL, CRAB, SHRIMP};
```

The ```Nigiri``` class should have a constructor with the following signature:

```
public Nigiri(NigiriType type)
```

The value of ```type``` passed to the constructor indicates what type of nigiri is being made. The name associated with a ```Nigiri``` object should be the name of the underlying seafood ingredient followed by the word "nigiri". For example, a tuna nigiri object should be produce the value "tuna nigiri" as the result of the ```getName``` method.

### Roll

A roll is a sushi creation that may be comprised of any number of different ingredient portions. The ```Roll``` class should have a constructor with the following signature:

```
public Roll(String name, IngredientPortion[] roll_ingredients)
```

The name associated with a ```Roll``` object is provided as the first parameter to the constructor, and the ingredient portions of the roll are provided as the second parameter. The constructor should check the validity of the ingredient portion array passed to it as well as the validity of the individual elements in the array. If the array is null or if any of the elements are null, throw a ```IllegalArgumentException```. For the adept-level tests, you may assume that no ingredient type will be repeated more than once.

Specifically do NOT use inheritance for these classes (i.e., ```Sashimi```, ```Nigiri```, and ```Roll```). Implement each one as a separate class that implements the ```Sushi``` interface without using a common parent class.

**Be careful about working with arrays as parameters to and from constructors and methods.** in particular, your ```Roll``` constructor should not just blindly copy the array reference passed in to a private field encapsulated in your ```Roll``` class. Instead, you should use the ```clone``` method of the array to make a copy that you can store safely. Similarly, you should not simply return an encapsulated array reference in order to implement the ```getIngredients``` method. You should create a new array to return each time.

## Jedi

Amend your ```Roll``` class to add the following functionality:

The constructor should detect if a particular ingredient type is repeated and combine the separate portions of a repeated ingredient type into a single portion.

The constructor should always include at least 0.1 ounces of ```SeaweedPortion``` as a component ingredient portion to represent the roll wrapper if the ingredient portion array passed to the constructor does not already include at least this much seaweed. If the ingredient portions passed to the constructor already have at least this much seaweed, you should not include any more.

# Grading

* Novice: 4 points
* Adept: 4 points
* Jedi: 2 points
* Style guide: 2 points