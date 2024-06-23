package fr.cs.group12.myFoodora.meal;

import java.util.Arrays;

import fr.cs.group12.myFoodora.menu.MenuItem;
/**
 * The FullMeal class represents a complete meal consisting of a starter, main dish, and dessert.
 * It extends the Meal class and applies a default discount of 5%.
 */
public class FullMeal extends Meal {
    /**
     * Constructs a FullMeal with the specified name and menu items for starter, main dish, and dessert.
     * The FullMeal applies a default discount of 5%.
     *
     * @param name The name of the full meal.
     * @param starter The starter menu item.
     * @param mainDish The main dish menu item.
     * @param dessert The dessert menu item.
     */
    public FullMeal(String name,MenuItem starter, MenuItem mainDish, MenuItem dessert) {
        super(name,Arrays.asList(starter, mainDish, dessert), 0.05);
    }
}

