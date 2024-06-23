package fr.cs.group12.myFoodora.meal;

import java.util.Arrays;

import fr.cs.group12.myFoodora.menu.MenuItem;
/**
 * The HalfMeal class represents a half meal consisting of two menu items.
 * It extends the Meal class and applies a default discount of 5%.
 */
public class HalfMeal extends Meal {
    /**
     * Constructs a HalfMeal with the specified name and two menu items.
     * The HalfMeal applies a default discount of 5%.
     *
     * @param name The name of the half meal.
     * @param firstItem The first menu item of the half meal.
     * @param secondItem The second menu item of the half meal.
     */
    public HalfMeal(String name,MenuItem firstItem, MenuItem secondItem) {
        super(name,Arrays.asList(firstItem, secondItem), 0.05);
    }
}

	