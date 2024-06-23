package fr.cs.group12.myFoodora.meal;

import java.util.List;

import fr.cs.group12.myFoodora.menu.MenuItem;
/**
 * The SpecialMeal class represents a special meal with a custom discount factor.
 * It extends the Meal class and allows for a specific discount to be applied.
 */
public class SpecialMeal extends Meal {
    /**
     * Constructs a SpecialMeal with the specified name, menu items, and special discount factor.
     *
     * @param name The name of the special meal.
     * @param items The list of menu items in the special meal.
     * @param specialDiscountFactor The special discount factor applied to the meal's total price.
     */
    public SpecialMeal(String name,List<MenuItem> items, double specialDiscountFactor) {
        super(name,items, specialDiscountFactor);
    }
}

