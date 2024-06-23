package fr.cs.group12.myFoodora.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import fr.cs.group12.myFoodora.deliveryPolicy.FairOccupationDelivery;
import fr.cs.group12.myFoodora.deliveryPolicy.FastestDelivery;
import fr.cs.group12.myFoodora.myFoodora.MyFoodoraSystem;
import fr.cs.group12.myFoodora.order.Order;
import fr.cs.group12.myFoodora.spaceTimeCoordinates.Position;
import fr.cs.group12.myFoodora.user.Courier;
import fr.cs.group12.myFoodora.user.Restaurant;
import fr.cs.group12.myFoodora.user.Customer;

/**
 * This class tests the delivery policies in the MyFoodora system.
 * It tests the FairOccupationDelivery and FastestDelivery policies to ensure that
 * couriers are assigned to orders according to the specified policy.
 */
public class DeliveryPolicyTest {

    private MyFoodoraSystem myFoodoraSystem;
    private Order order1;
    private Order order2;
    private Order order3;
    private Restaurant restaurant;
    private Customer customer1;
    private Customer customer2;
    private Customer customer3;
    private Courier courier1;
    private Courier courier2;
    private FairOccupationDelivery fairOccupationDelivery;
    private FastestDelivery fastestDelivery;

    @Before
    public void setUp() throws Exception {
    	MyFoodoraSystem.resetMyFoodora();
        myFoodoraSystem = new MyFoodoraSystem();
        
        // Create mock data for testing
        restaurant = new Restaurant("TestRestaurant", "testUsername", "testPassword", null, new Position(0, 0), null, null, myFoodoraSystem);
        customer1 = new Customer("TestCustomer", "TestSurname", "testUsername", "testPassword", null, new Position(10, 10), "email@example.com", "1234567890");
        order1 = new Order(customer1, restaurant);
        customer2 = new Customer("TestCustomer", "TestSurname", "testUsername", "testPassword", null, new Position(10, 10), "email@example.com", "1234567890");
        order2 = new Order(customer2, restaurant);
        customer3 = new Customer("TestCustomer", "TestSurname", "testUsername", "testPassword", null, new Position(10, 10), "email@example.com", "1234567890");
        order3 = new Order(customer3, restaurant);
        courier1 = new Courier("Courier1", "LastName1", "courier1", "password1", null, new Position(100, 100), "1234567890");
        courier2 = new Courier("Courier2", "LastName2", "courier2", "password2", null, new Position(1, 1), "0987654321");
        

        MyFoodoraSystem.addUser(courier1);
        MyFoodoraSystem.addUser(restaurant);
        MyFoodoraSystem.addUser(courier2);
       

   
        fairOccupationDelivery = new FairOccupationDelivery();
        fastestDelivery = new FastestDelivery();
    }

    @Test
    public void testFairOccupationDelivery() {
    	 courier1.setOnDuty(true);
         courier2.setOnDuty(false);
         MyFoodoraSystem.setDeliveryPolicy(fairOccupationDelivery);
         MyFoodoraSystem.placeOrder(order1);
        assertEquals(courier1, order1.getCourier()); 
         courier2.setOnDuty(true);
         MyFoodoraSystem.setDeliveryPolicy(fairOccupationDelivery);
         MyFoodoraSystem.placeOrder(order2);
         assertEquals(courier2, order2.getCourier());
    }

    @Test
    public void testFastestDelivery() {
    	 courier1.setOnDuty(true);
         courier2.setOnDuty(true);
         MyFoodoraSystem.setDeliveryPolicy(fastestDelivery);
         MyFoodoraSystem.placeOrder(order3);
        assertEquals(courier2, order3.getCourier());  
    }
}