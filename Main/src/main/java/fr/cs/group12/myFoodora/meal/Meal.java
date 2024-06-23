package fr.cs.group12.myFoodora.meal;

import java.util.ArrayList;
import java.util.List;

import fr.cs.group12.myFoodora.menu.MenuItem;
/**
 * The Meal class represents a meal consisting of multiple menu items.
 * It includes information about the meal's name, items, discount factor,
 * and whether it is vegetarian or gluten-free.
 */
public  class Meal {
    protected String name;
    protected List<MenuItem> items;
    protected double discountFactor;
    protected boolean isVegetarian;
    protected boolean isGlutenFree;

    /**
     * Constructs a Meal with the specified name, menu items, and discount factor.
     *
     * @param name The name of the meal.
     * @param items The list of menu items in the meal.
     * @param discountFactor The discount factor applied to the meal's total price.
     */
    public Meal(String name,List<MenuItem> items, double discountFactor) {
        this.name=name;
        this.items = items;
        this.discountFactor = discountFactor;
        isVegetarian=items.get(0).isVegetarian();
        isGlutenFree= items.get(0).isGlutenFree();
    }
    /**
     * Gets the name of the meal.
     *
     * @return The name of the meal.
     */
    public String getName() {
        return name;
    }
    /**
     * Gets the total price of the meal after applying the discount factor.
     *
     * @return The total price after discount.
     */
    public double getPrice() {
        double total = items.stream().mapToDouble(MenuItem::getPrice).sum();
        return total * (1 - discountFactor);
    }
    /**
     * Gets the list of menu items in the meal.
     *
     * @return The list of menu items.
     */
    public List<MenuItem> getItems() {
        return items;
    }
    /**
     * Checks if the meal is vegetarian.
     *
     * @return True if the meal is vegetarian, false otherwise.
     */
    public boolean isVegetarian() {
        return isVegetarian;
    }
    /**
     * Checks if the meal is gluten-free.
     *
     * @return True if the meal is gluten-free, false otherwise.
     */
    public boolean isGlutenFree() {
        return isGlutenFree;
    }
    /**
     * Sets the discount factor for the meal.
     *
     * @param discountFactor The discount factor to set.
     */
    public void setDiscountFactor(double discountFactor) {
        this.discountFactor = discountFactor;
    }

    /**
     * Sets whether the meal is vegetarian.
     *
     * @param vegetarian True if the meal is vegetarian, false otherwise.
     */
    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }
    /**
     * Sets whether the meal is gluten-free.
     *
     * @param glutenFree True if the meal is gluten-free, false otherwise.
     */
    public void setGlutenFree(boolean glutenFree) {
        isGlutenFree = glutenFree;
    }
    /**
     * Returns a string representation of the meal, including its items and total price after discount.
     *
     * @return A string representation of the meal.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Meal: \n");
        for (MenuItem item : items) {
            sb.append(item.toString()).append("\n");
        }
        sb.append(String.format("Total Price after discount: %.2f", getPrice()));
        return sb.toString();
    }


}

