# a08-shopping

In this assignment, you'll use the observer design pattern to create a price watching application. Imagine a shopping trip to the mall to visit all your favorite stores. Customers are constantly looking for a way to monitor sales at these competing markets so that they can get the best deal. We are going to build a way to help customers monitor sales using the observer design pattern.

When you're finished with the assignment, you'll be able to play a simple game from the command line by running the `Main.main()` method. You'll have $100 to spend on products at the mall. Stores will periodically put products on sale, giving you the opportunity to buy at great prices---if you're willing to wait for the deals.


## Novice

We're going to use the observer design pattern to notify customers whenever a sale event occurs in a store. However, there are multiple types of sale events that customers might care about. To model the different types of events, we're going to create 5 different event classes which implement the `StoreEvent` interface.

1. `BackInStockEvent` - Represents the event where a product that was previously out of stock at a particular store is now available.
2. `OutOfStockEvent` - Represents the event where someone purchases the last copy of a product from a store, and the product is now out of stock.
3. `PurchaseEvent` - Represents the event where someone purchases a copy of a product from a store.
4. `SaleEndEvent` - Represents the event where a store ends their sale for a particular product.
5. `SaleStartEvent` - Represents the event where a store announces a sale for a particular product.

Create these five classes in the `com.comp301.a08shopping.events` package. Each class should implement the `StoreEvent` interface and should encapsulate a `Product` field and a `Store` field, representing the product and the store that the event pertains to. Each class should be immutable, and should have a constructor that initializes the fields. For example, here is the signature for the `SaleStartEvent` constructor. The order of the constructor parameters is important, and must match this example for the autograder to accept your event classes.

```java
public SaleStartEvent(Product product, Store store) {
        // Constructor code goes here
        }
```

Other than simply encapsulating `Product` and `Store` fields, these five classes need not perform any additional functions---although you're welcome to add any additional functionality you deem necessary. Since the classes are very similar to each other, you may find it useful to employ inheritance to avoid code repetition. Feel free to do so if desired.


## Adept

### `ProductImpl`

Next, let's design a class to represent a product. Products will be sold by stores and may occasionally go on sale.

Create a class called `ProductImpl` that implements the `Product` interface. At the bare minimum, your `ProductImpl` class must encapsulate a private string field to represent the product's name, and a private double field to represent the product's base price.

The constructor for the `ProductImpl` should have the following signature:

```java
public ProductImpl(String name, double basePrice)
```

Note that the "bare minimum" implementation of the `ProductImpl` class, as described above, is pretty simple and is sufficient to pass the autograder. However, when you design the `StoreImpl` class below, you may wish (and are allowed) to revisit this class to add more complexity as you see fit.

For this assignment, $0.00 should not be a valid base price for any products. For the sake of
simplicity, assume that the `basePrice` parameter will be to no more than 2 decimal places.



### `StoreImpl`

Next, create a class called `StoreImpl` which implements the `Store` interface. The `StoreImpl` class represents a store at the mall, containing a list of products that occasionally go on sale. At the same time, `StoreImpl` also serves as the "subject" in the observer design pattern, and will notify its active observers whenever a sale event occurs (see the five `StoreEvent` types from the *novice* section).

#### What fields to encapsulate?

To implement the `Store` methods, you'll need to encapsulate a few private instance fields in the `StoreImpl` class. At minimum, you'll need these three fields:

1. A string field to represent the name of the store.
2. A list (or other collection type) of `StoreObserver` objects to represent the active observers of the store's sale events.
3. A list (or other collection type) of `Product` objects to represent the products sold at the store.

In addition to these three fields, you'll also need to store **inventory information** about each product (i.e., how many copies of each product is in stock at the store), and **sale information** about each product (i.e. the discount percentage if the product is on sale). You may choose to store these quantities as separate fields in the `StoreImpl` class or back in the `ProductImpl` class. If you choose to store them in the `StoreImpl` class, keep in mind that each product should have its own inventory and discount value. If you choose to store them in the `ProductImpl` class, keep in mind that the autograder will not allow you to change the `Product` interface (but you still can add your own methods to `ProductImpl`).


#### Constructor signature

The `StoreImpl` constructor should have the following signature:

```java
public StoreImpl(String name)
```

Use the provided `name` parameter to initialize the store's name field. Also remember to initialize any other data structure fields that you use to store product and observer information.


#### Notifying observers

Certain `Store` methods should indicate that a `StoreEvent` has occurred. Whenever this happens, you should write code at the end of the method to notify all active observers about the event that occurred. Remember, active observers can be notified by calling their `update()` methods. Since you'll need to loop through all the active observers and call `update()` on each one, you may wish to add a private `notify()` method to handle this behavior. You'll also need to create a new event instance of the appropriate type in order to pass it into the `update()` method.

> Hint: `startSale()`, `endSale()`, `restockProduct()`, and `purchaseProduct()` are the four `Store` methods that may emit one (or more) events.


#### Returning a receipt in `purchaseProduct()`

Upon successful product purchase, `purchaseProduct()` should return a new `ReceiptItem` object representing a receipt of the transaction. Create an appropriate new `ReceiptItemImpl` object to return. This class is part of the starter code, and no changes to it are necessary.


## Jedi

### `CustomerImpl`

The last class to create for this assignment is `CustomerImpl`, which implements the `Customer` interface. Since the `Customer` interface extends the `StoreObserver` interface, `Customer` instances can be registered as observers of store events. The `CustomerImpl` class represents the user who is playing the command-line game, searching for sales and purchasing products.

`CustomerImpl` should have a constructor with the following signature:

```java
public CustomerImpl(String name, double budget)
```

Encapsulate a string field to represent the customer's name, and a double field to represent the customer's budget (i.e. the amount of money that the customer has left to spend). These fields should be initialized using the parameters passed to the constructor.

In addition, the `CustomerImpl` class should also encapsulate a `List<ReceiptItem>` field to store a list of all purchases that the customer makes. Every time `purchaseProduct()` is called on the customer, the `ReceiptItem` object returned by the store should be added to the customer's purchase list.

Again, for the sake of simplicity, assume that the `budget` parameter will be to 2 decimal places.

#### `update()` method

Since `CustomerImpl` is a `StoreObserver`, you'll also need to implement an `update()` method. This method will be executed by the `Store` objects whenever a `StoreEvent` occurs to update the customer about the event.

The customer's `update()` method should simply print out a statement to the console explaining which type of event occurred. Here are examples of the statements that should be printed for each event type:

1. `BackInStockEvent` - If a product with name "Watch" is back in stock in store "Macy's", then the `update()` method should print `"Watch is back in stock at Macy's"`
2. `OutOfStockEvent` - If a product with name "Watch" is out of stock in store "Macy's", then the `update()` method should print `"Watch is now out of stock at Macy's"`
3. `PurchaseEvent` - If a product with name "Watch" is purchased in store "Macy's", then the `update()` method should print `"Someone purchased Watch at Macy's"`
4. `SaleEndEvent` - If the sale for a product with name "Watch" ends in store "Macy's", then the `update()` method should print `"The sale for Watch at Macy's has ended"`
5. `SaleStartEvent` - If a new sale starts for a product with name "Watch" in store "Macy's", then the `update()` method should print `"New sale for Watch at Macy's!"`


### `Main` class

Once you've finished making the classes described above, you can try running the `Main` method to play the game! However, in order to receive the sale event updates, you'll need to add a couple of lines of code to the `main()` method to register/deregister the customer as an observer to the stores. See the `TODO` comments in the `Main` class for details.