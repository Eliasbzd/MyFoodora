package fr.cs.group12.myFoodora.meal;

import fr.cs.group12.myFoodora.menu.MenuItem;
/**
 * The MealFactory interface provides methods to create different types of meals.
 * Implementations of this interface will provide specific strategies for creating half and full meals.
 */
public interface MealFactory {
    /**
     * Creates a half meal with the specified name and two menu items.
     *
     * @param name The name of the half meal.
     * @param firstItem The first menu item of the half meal.
     * @param secondItem The second menu item of the half meal.
     * @return The created half meal.
     */
    Meal createHalfMeal(String name,MenuItem firstItem, MenuItem secondItem);

    /**
     * Creates a full meal with the specified name and three menu items: starter, main dish, and dessert.
     *
     * @param name The name of the full meal.
     * @param starter The starter menu item of the full meal.
     * @param mainDish The main dish menu item of the full meal.
     * @param dessert The dessert menu item of the full meal.
     * @return The created full meal.
     */
    Meal createFullMeal(String name,MenuItem starter, MenuItem mainDish, MenuItem dessert);
}

