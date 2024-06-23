package fr.cs.group12.myFoodora.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.cs.group12.myFoodora.meal.ConcreteMealFactory;
import fr.cs.group12.myFoodora.meal.Meal;
import fr.cs.group12.myFoodora.meal.MealFactory;
import fr.cs.group12.myFoodora.menu.ConcreteMenuFactory;
import fr.cs.group12.myFoodora.menu.Dessert;
import fr.cs.group12.myFoodora.menu.MainDish;
import fr.cs.group12.myFoodora.menu.MenuFactory;
import fr.cs.group12.myFoodora.menu.MenuItem;
import fr.cs.group12.myFoodora.menu.Starter;
import fr.cs.group12.myFoodora.myFoodora.MyFoodoraSystem;
import fr.cs.group12.myFoodora.spaceTimeCoordinates.Position;
import fr.cs.group12.myFoodora.user.Courier;
import fr.cs.group12.myFoodora.user.Customer;
import fr.cs.group12.myFoodora.user.Manager;
import fr.cs.group12.myFoodora.user.Restaurant;
/**
 * This class tests the functionalities related to users classes in the MyFoodora system.
 */
public class UserTest {



    private Manager manager;
    private MyFoodoraSystem myFoodoraSystem;
    private Restaurant restaurant;
    private Customer customer1;
    private Customer customer2;
    private MenuFactory menuFactory;
    private MealFactory mealFactory;

    
    @Before
    public void setUp() {
        // Create mock data for testing
            MyFoodoraSystem.resetMyFoodora();
            myFoodoraSystem = new MyFoodoraSystem();
            manager = new Manager("John", "Doe", "1", "john.doe", "password", myFoodoraSystem);
            MyFoodoraSystem.addUser(manager);
            menuFactory = new ConcreteMenuFactory();
            mealFactory = new ConcreteMealFactory();
            customer1 = new Customer("TestNameCustomer1", "TestSurnameCustomer","TestIdCustomer", "TestUsernameCustomer1", "testPasswordCustomer", new Position(10, 10), "email@example.com", "1234567890");
            customer2 = new Customer("TestNameCustomer2", "TestSurnameCustomer","TestIdCustomer", "TestUsernameCustomer2", "testPasswordCustomer", new Position(10, 10), "email@example.com", "1234567890");
            manager.addUser(customer1);
            manager.addUser(customer2);
            restaurant = new Restaurant("Pasta Palace", "1", "pasta", "password",new Position(0, 0), menuFactory, mealFactory, myFoodoraSystem);
            manager.addUser(restaurant);

	        manager=(Manager) MyFoodoraSystem.getUsers().get(0);
	        customer1=(Customer) MyFoodoraSystem.getUsers().get(1);
	        customer2=(Customer) MyFoodoraSystem.getUsers().get(2);
	        restaurant=(Restaurant) MyFoodoraSystem.getUsers().get(3);
    }
    

    @Test
    public void testAddCustomer() {
        Customer customerTest = new Customer("TestNameCustomer", "TestSurnameCustomer","TestIdCustomer", "TestUsernameCustomer", "testPasswordCustomer", new Position(10, 10), "email@example.com", "1234567890");
        
        manager.addUser(customerTest);

        // Verify if the customer was added
        assertTrue(MyFoodoraSystem.getCustomers().contains(customerTest));

    }
    @Test
    public void testAddCourier() {
        Courier courier = new Courier("Tom", "Hardy", "2", "tom.hardy", "password", null, null);
        
        manager.addUser(courier);

        // Verify if the courier was added
        assertTrue(MyFoodoraSystem.getCouriers().contains(courier));
    }

    @Test
    public void testAddRestaurant() {
        Restaurant restaurantTest = new Restaurant("Pasta Palace Test", "3", "pasta.palacetest", "password", null, null, null, myFoodoraSystem);
        
        manager.addUser(restaurantTest);

        // Verify if the restaurant was added
        assertTrue(MyFoodoraSystem.getRestaurants().contains(restaurant));
    }
    


    @Test
    public void testAddMenuItem() {
        // Add a starter to the menu
        restaurant.addMenuItem("starter", "Bruschetta", 5.99, true, false);
        List<MenuItem> menuItems = restaurant.getMenu();
        boolean itemFound = false;

        for (MenuItem item : menuItems) {
            if (item.getName().equals("Bruschetta")) {
                assertEquals("Bruschetta", item.getName());
                assertEquals(5.99, item.getPrice(), 0.01);
                assertTrue(item.isVegetarian());
                assertFalse(item.isGlutenFree());
                itemFound = true;
                break;
            }
        }

        assertTrue(itemFound);

        // Add another menu item and check if it exists in the menu
        restaurant.addMenuItem("starter", "Garlic Bread", 4.99, true, true);

        // Retrieve updated menu items
        menuItems = restaurant.getMenu();
        boolean newItemFound = false;

        for (MenuItem item : menuItems) {
            if (item.getName().equals("Garlic Bread")) {
                assertEquals("Garlic Bread", item.getName());
                assertEquals(4.99, item.getPrice(), 0.01);
                assertTrue(item.isVegetarian());
                assertTrue(item.isGlutenFree());
                newItemFound = true;
                break;
            }
        }

        assertTrue(newItemFound);
    }
    @Test
    public void testCreateMeal() {
        // Add menu items
        restaurant.addMenuItem("maindish", "Spaghetti Carbonara", 10.99, false, true);
        restaurant.addMenuItem("maindish", "Margherita Pizza", 8.99, true, true);
        restaurant.addMenuItem("dessert", "Tiramisu", 6.99, true, false);

        // Try to create a half meal that should not be added
        restaurant.createMeal("LunchNotSameType", "halfmeal", new MainDish("Spaghetti Carbonara", 10.99, false, true), new Dessert("Tiramisu", 6.99, true, false));

        // Check if the meal was not added
        List<Meal> meals = restaurant.getMeals();
        boolean mealFound = false;

        for (Meal meal : meals) {
            if (meal.getName().equals("LunchNotSameType")) {
                mealFound = true;
                break;
            }
        }
        assertFalse(mealFound); // because the dishes are not of the same type

        // Try to create a full meal that should not be added
        restaurant.createMeal("DinnerDoubleMainDish", "fullmeal", new MainDish("Margherita Pizza", 8.99, true, true), new MainDish("Spaghetti Carbonara", 10.99, true, true), new Dessert("Tiramisu", 6.99, true, true));

        // Check if the meal was not added
         meals = restaurant.getMeals();
         mealFound = false;
        for (Meal meal : meals) {
            if (meal.getName().equals("DinnerDoubleMainDish")) {
                mealFound = true;
                break;
            }
        }
        assertFalse(mealFound); // because the dishes are not starter maindish dessert
        

        // Create a full meal
        restaurant.createMeal("Dinner", "fullmeal", new Starter("Salad", 8.99, true, true), new MainDish("Spaghetti Carbonara", 10.99, true, true), new Dessert("Tiramisu", 6.99, true, true));
        
        // Check if the meal was  created and added
        meals = restaurant.getMeals();
        mealFound = false;

        for (Meal meal : meals) {
            if (meal.getName().equals("Dinner")) {
                assertEquals("Dinner", meal.getName());
                mealFound = true;
                break;
            }
        }
        assertTrue(mealFound);
    }
    @Test
    public void testNotifyObservers() {


        restaurant.createMeal("Dinner", "fullmeal", new Starter("Salad", 8.99, true, true), new MainDish("Spaghetti Carbonara", 10.99, true, true), new Dessert("Tiramisu", 6.99, true, true));

        customer1.giveConsensusForSpecialOffers();
        customer2.removeConsensusForSpecialOffers();

        restaurant.setMealOfTheWeek(restaurant.getMeals().get(0)); 

        // Verify if only customer1, who is interested in special offers, is notified
        assertEquals(1, customer1.getNotifications().size());
        assertEquals("Pasta Palace has a new meal of the week!", customer1.getNotifications().get(0));

        // Verify if customer2, who is not interested in special offers, is not notified
        assertEquals(0, customer2.getNotifications().size());
    }


}