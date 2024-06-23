package fr.cs.group12.myFoodora.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import fr.cs.group12.myFoodora.meal.ConcreteMealFactory;
import fr.cs.group12.myFoodora.meal.Meal;
import fr.cs.group12.myFoodora.meal.MealFactory;
import fr.cs.group12.myFoodora.menu.Dessert;
import fr.cs.group12.myFoodora.menu.MainDish;
import fr.cs.group12.myFoodora.menu.MenuItem;
import fr.cs.group12.myFoodora.menu.Starter;

/**
 * This class tests the functionality of creating different types of meals using the MealFactory.
 * It tests the creation of HalfMeal and FullMeal to ensure that the meals are correctly created
 * with the specified menu items and attributes.
 */
public class MealTest {

    private MealFactory mealFactory;

    @Before
    public void setUp() {
        mealFactory = new ConcreteMealFactory();
    }

    @Test
    public void testCreateHalfMeal() {
        MenuItem starter = new Starter("Salad", 8.99, true, true);
        MenuItem mainDish = new MainDish("Pasta", 12.99, true, true);
        
        Meal halfMeal = mealFactory.createHalfMeal("Half Meal", starter, mainDish);
        
        assertEquals("Half Meal", halfMeal.getName());
        assertTrue(halfMeal.getItems().contains(starter));
        assertTrue(halfMeal.getItems().contains(mainDish));
        assertEquals(2, halfMeal.getItems().size());
        assertTrue(halfMeal.isVegetarian());
        assertTrue(halfMeal.isGlutenFree());
    }

    @Test
    public void testCreateFullMeal() {
        MenuItem starter = new Starter("Salad", 8.99, true, false);
        MenuItem mainDish = new MainDish("Pasta", 12.99, true, false);
        MenuItem dessert = new Dessert("Cake", 5.99, true, false);
        
        Meal fullMeal = mealFactory.createFullMeal("Full Meal", starter, mainDish, dessert);
        
        assertEquals("Full Meal", fullMeal.getName());
        assertTrue(fullMeal.getItems().contains(starter));
        assertTrue(fullMeal.getItems().contains(mainDish));
        assertTrue(fullMeal.getItems().contains(dessert));
        assertEquals(3, fullMeal.getItems().size());
        assertTrue(fullMeal.isVegetarian());
        assertFalse(fullMeal.isGlutenFree());
    }
    
}