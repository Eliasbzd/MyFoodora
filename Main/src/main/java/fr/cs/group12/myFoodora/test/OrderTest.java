package fr.cs.group12.myFoodora.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import fr.cs.group12.myFoodora.meal.HalfMeal;
import fr.cs.group12.myFoodora.meal.SpecialMeal;
import fr.cs.group12.myFoodora.menu.MainDish;
import fr.cs.group12.myFoodora.menu.Starter;
import fr.cs.group12.myFoodora.order.Order;
import fr.cs.group12.myFoodora.spaceTimeCoordinates.Time;
import fr.cs.group12.myFoodora.user.Customer;
import fr.cs.group12.myFoodora.user.Restaurant;
/**
 * This class tests the functionalities of the Order class, including item and meal addition,
 * total price calculation, and string representation of an order.
 */
public class OrderTest {

    private Customer customer;
    private Restaurant restaurant;
    private Starter starter;
    private MainDish mainDish;
    private HalfMeal halfMeal;
    private SpecialMeal specialMeal;
    private Order order;
    

    @Before
    public void setUp() {
        // Create mock data for testing
        customer = new Customer("John Doe", "john.doe@example.com", null, null, null, null, null, null);
        restaurant = new Restaurant("Test Restaurant", "testuser", "testpass", null, null, null, null, null);
        starter=new Starter("Item", 10.0,true,true);
        mainDish=new MainDish("mainDish", 20.0,true,true);
        halfMeal=new HalfMeal("halfmeal",starter,mainDish);
        specialMeal = new SpecialMeal("halfmeal", Arrays.asList(starter, mainDish),0.10);
        order = new Order(customer, restaurant);
    }

    @Test
    public void testCalculateTotalPrice() {
        assertEquals(0.0, order.calculateTotalPrice(), 0.000);
    }

    @Test
    public void testAddItem() {
        order.addItem(starter);
        assertEquals(10.0, order.calculateTotalPrice(), 0.000);
    }
    @Test
    public void testAddMeal() {
        order.addMeal(halfMeal);
        assertEquals((10+20)*0.95, order.calculateTotalPrice(), 0.000);
    }

    @Test
    public void testAddSpecialMeal() {
        order.addMeal(specialMeal);
        assertEquals((10+20)*0.90, order.calculateTotalPrice(), 0.000);
    }

   
    @Test
    public void testToString() {
        String expected = String.format("Order from Test Restaurant for John Doe at %s. Price: %.2f\nItems:\n", Time.getCurrentTime(), 0.0);
        assertEquals(expected, order.toString());
    }

}