package fr.cs.group12.myFoodora.order;

import java.util.ArrayList;
import java.util.List;

import fr.cs.group12.myFoodora.meal.Meal;
import fr.cs.group12.myFoodora.meal.SpecialMeal;
import fr.cs.group12.myFoodora.menu.MenuItem;
import fr.cs.group12.myFoodora.spaceTimeCoordinates.Time;
import fr.cs.group12.myFoodora.user.Courier;
import fr.cs.group12.myFoodora.user.Customer;
import fr.cs.group12.myFoodora.user.Restaurant;

/**
 * This class represents an order in the MyFoodora system.
 * An order contains a customer, a restaurant, a list of menu items, a list of meals,
 * a total price, a courier for delivery, and the time the order was placed.
 */
public class Order {
    private String name;
    private Customer customer;
    private Restaurant restaurant;
    private List<MenuItem> items;
    private List<Meal> meals;
    private double totalPrice;
    private Courier courier;
    private Time time = Time.getCurrentTime();

    /**
     * Constructs a new order with a specified customer and restaurant.
     *
     * @param customer the customer who placed the order.
     * @param restaurant the restaurant from which the order was placed.
     */
    public Order(Customer customer, Restaurant restaurant) {
        this.customer = customer;
        this.restaurant = restaurant;
        this.items = new ArrayList<>();
        this.meals = new ArrayList<>();
        this.totalPrice = 0.0;
        this.name="noname";
    }
    /**
     * Constructs a new order with a specified customer, restaurant, and name.
     *
     * @param customer the customer who placed the order.
     * @param restaurant the restaurant from which the order was placed.
     * @param name the name of the order.
     */
    public Order(Customer customer, Restaurant restaurant,String name) {
        this.name=name;
        this.customer = customer;
        this.restaurant = restaurant;
        this.items = new ArrayList<>();
        this.meals = new ArrayList<>();

        this.totalPrice = 0.0;
    }
    /**
     * Constructs a new order with a specified customer, restaurant, and time.
     *
     * @param customer the customer who placed the order.
     * @param restaurant the restaurant from which the order was placed.
     * @param time the time the order was placed.
     */
    public Order(Customer customer, Restaurant restaurant,Time time) {
        this.customer = customer;
        this.restaurant = restaurant;
        this.items = new ArrayList<>();
        this.meals = new ArrayList<>();
        this.totalPrice = 0.0;
        this.time=time;
        this.name="noname";
    }

    /**
     * Adds a menu item to the order.
     *
     * @param item the menu item to add.
     */
    public void addItem(MenuItem item) {
        items.add(item);
        totalPrice += item.getPrice();
    }

    /**
     * Adds a meal to the order.
     *
     * @param meal the meal to add.
     */
    public void addMeal(Meal meal) {
        meals.add(meal);
        totalPrice += meal.getPrice();
    }

    /**
     * Adds a special meal to the order.
     *
     * @param meal the special meal to add.
     */
    public void addMeal(SpecialMeal meal) {
        meals.add(meal);
        totalPrice += meal.getPrice();
    }

    /**
     * Calculates the total price of the order.
     *
     * @return the total price of the order.
     */
    public double calculateTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the total price of the order.
     *
     * @param price the total price to set.
     */
    public void setTotalPrice(double price) {
        this.totalPrice = price;
    }


    /**
     * Gets the customer who placed the order.
     *
     * @return the customer who placed the order.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Gets the total price of the order.
     *
     * @return the total price of the order.
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Gets the restaurant from which the order was placed.
     *
     * @return the restaurant from which the order was placed.
     */
    public Restaurant getRestaurant() {
        return restaurant;
    }

    /**
     * Gets the courier delivering the order.
     *
     * @return the courier delivering the order.
     */
    public Courier getCourier() {
        return courier;
    }

    /**
     * Gets the list of menu items in the order.
     *
     * @return the list of menu items in the order.
     */
    public List<MenuItem> getItems() {
        return items;
    }

    /**
     * Sets the courier delivering the order.
     *
     * @param courier the courier to set.
     */
    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    /**
     * Gets the time the order was placed.
     *
     * @return the time the order was placed.
     */
    public Time getTime() {
        return time;
    }

    /**
     * Gets the name of the order.
     *
     * @return the name of the order.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the list of meals in the order.
     *
     * @return the list of meals in the order.
     */
    public List<Meal> getMeals() {
        return meals;
    }

    /**
     * Sets the time the order was placed.
     *
     * @param time the time to set.
     */
    public void setTime(Time time) {
        this.time = time;
    }

    /**
     * Returns a string representation of the order.
     *
     * @return a string representation of the order.
     */
    @Override
    public String toString() {
        StringBuilder orderDetails = new StringBuilder(String.format("Order from %s for %s at %s", restaurant.getName(), customer.getName(), time));

        if (courier != null) {
            orderDetails.append(String.format(" delivered by %s", courier.getName()));
        }

        orderDetails.append(String.format(". Price: %.2f\n", totalPrice));
        orderDetails.append("Meals:\n");

        for (Meal meal : meals) {
            orderDetails.append(meal.toString()).append("\n");
        }
        orderDetails.append("Items:\n");

        for (MenuItem item : items) {
            orderDetails.append(item.toString()).append("\n");
        }

        return orderDetails.toString();
    }


}

