package fr.cs.group12.myFoodora.meal;

import fr.cs.group12.myFoodora.menu.Dessert;
import fr.cs.group12.myFoodora.menu.MainDish;
import fr.cs.group12.myFoodora.menu.MenuItem;
import fr.cs.group12.myFoodora.menu.Starter;
/**
 * The ConcreteMealFactory class implements the MealFactory interface and provides
 * specific strategies for creating half and full meals.
 */
public class ConcreteMealFactory implements MealFactory {
    /**
     * Creates a half meal with the specified name and two menu items.
     * The combination must be either Starter with MainDish or MainDish with Dessert,
     * and both items must have the same type (vegetarian and/or gluten-free).
     *
     * @param name The name of the half meal.
     * @param firstItem The first menu item of the half meal, which must be a Starter or MainDish.
     * @param secondItem The second menu item of the half meal, which must be a MainDish or Dessert.
     * @return The created half meal, or null if the combination is invalid.
     */
    @Override
    public Meal createHalfMeal(String name, MenuItem firstItem, MenuItem secondItem) {
        if ((firstItem instanceof Starter && secondItem instanceof MainDish) ||
                (firstItem instanceof MainDish && secondItem instanceof Dessert)) {
            if (firstItem.isGlutenFree() == secondItem.isGlutenFree() && firstItem.isVegetarian() == secondItem.isVegetarian()) {
                return new HalfMeal(name, firstItem, secondItem);
            } else {
                System.out.println("The dishes are not of the same type (Gluten-free and/or Vegetarian).");
                return null;
            }
        } else {
            System.out.println("Invalid combination for HalfMeal. A HalfMeal must be either Starter with MainDish or MainDish with Dessert.");
            if (!(firstItem instanceof Starter) && !(firstItem instanceof MainDish)) System.out.println("First dish is not a valid type.");
            if (!(secondItem instanceof MainDish) && !(secondItem instanceof Dessert)) System.out.println("Second dish is not a valid type.");
            return null;
        }
    }
    /**
     * Creates a full meal with the specified name and three menu items: starter, main dish, and dessert.
     * The combination must include one Starter, one MainDish, and one Dessert,
     * and all items must have the same type (vegetarian and/or gluten-free).
     *
     * @param name The name of the full meal.
     * @param firstItem The first menu item of the full meal, which must be a Starter, MainDish, or Dessert.
     * @param secondItem The second menu item of the full meal, which must be a Starter, MainDish, or Dessert.
     * @param thirdItem The third menu item of the full meal, which must be a Starter, MainDish, or Dessert.
     * @return The created full meal, or null if the combination is invalid.
     */
    @Override
    public Meal createFullMeal(String name, MenuItem firstItem, MenuItem secondItem, MenuItem thirdItem) {
        // Determine which items are Starter, MainDish, and Dessert
        MenuItem starter = null, mainDish = null, dessert = null;

        if (firstItem instanceof Starter) starter = firstItem;
        else if (firstItem instanceof MainDish) mainDish = firstItem;
        else if (firstItem instanceof Dessert) dessert = firstItem;

        if (secondItem instanceof Starter) starter = secondItem;
        else if (secondItem instanceof MainDish) mainDish = secondItem;
        else if (secondItem instanceof Dessert) dessert = secondItem;

        if (thirdItem instanceof Starter) starter = thirdItem;
        else if (thirdItem instanceof MainDish) mainDish = thirdItem;
        else if (thirdItem instanceof Dessert) dessert = thirdItem;

        // Check if all required items are present
        if (starter == null || mainDish == null || dessert == null) {
            System.out.println("Invalid combination for FullMeal. A FullMeal must have one Starter, one MainDish, and one Dessert.");
            if (starter == null) System.out.println("Starter is missing or incorrect.");
            if (mainDish == null) System.out.println("MainDish is missing or incorrect.");
            if (dessert == null) System.out.println("Dessert is missing or incorrect.");
            return null;
        }

        // Check if all items are of the same type (Gluten-free/Vegetarian)
        if ((starter.isGlutenFree() != mainDish.isGlutenFree()) ||
                (starter.isGlutenFree() != dessert.isGlutenFree()) ||
                (starter.isVegetarian() != mainDish.isVegetarian()) ||
                (starter.isVegetarian() != dessert.isVegetarian())) {
            System.out.println("The dishes are not of the same type (Gluten-free and/or Vegetarian).");
            return null;
        }

        return new FullMeal(name, starter, mainDish, dessert);
    }
}





