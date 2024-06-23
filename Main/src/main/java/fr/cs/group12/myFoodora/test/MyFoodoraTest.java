package fr.cs.group12.myFoodora.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.cs.group12.myFoodora.myFoodora.MyFoodoraSystem;
import fr.cs.group12.myFoodora.order.Order;
import fr.cs.group12.myFoodora.spaceTimeCoordinates.Position;
import fr.cs.group12.myFoodora.user.Courier;
import fr.cs.group12.myFoodora.user.Customer;
import fr.cs.group12.myFoodora.user.Manager;
import fr.cs.group12.myFoodora.user.Restaurant;
import fr.cs.group12.myFoodora.user.User;
/**
 * This class tests the core functionalities of the MyFoodora system, including user management,
 * order handling, and financial computations.
 */
public class MyFoodoraTest {

    private Restaurant testRestaurant;
    private Customer testCustomer;
    private Courier testCourier;
    private Manager testManager;
    private Order testOrder;
    private MyFoodoraSystem myFoodoraSystem;


    @Before
    public void setUp() {
        // Create mock data for testing and reset myfoodora at each setup
        MyFoodoraSystem.resetMyFoodora();
        myFoodoraSystem=new MyFoodoraSystem();
        testRestaurant = new Restaurant("TestRestaurant","TestIdRestaurant","TestUsernameRestaurant", "testPasswordRestaurant",new Position(0, 0), null, null, myFoodoraSystem);
        testCustomer = new Customer("TestNameCustomer", "TestSurnameCustomer","TestIdCustomer", "TestUsernameCustomer", "testPasswordCustomer", new Position(10, 10), "email@example.com", "1234567890");
        testCourier = new Courier("TestNameCourier", "TestLastNameCourier", "TestUsernameCourier", "TestpasswordCourier", null, new Position(100, 100), "1234567890");
        testManager = new Manager("TestNameManager", "TestSurnameManager", "testIdManager","testUsersnameManager" ,"testPasswordManager",myFoodoraSystem );
        MyFoodoraSystem.addUser(testRestaurant);
        MyFoodoraSystem.addUser(testCustomer);
        MyFoodoraSystem.addUser(testCourier);
        MyFoodoraSystem.addUser(testManager);

        testOrder = new Order(testCustomer, testRestaurant, "Order1");
    }

    @Test
    public void testAddUser() {
        assertEquals(1, MyFoodoraSystem.getRestaurants().size());
        assertEquals(1, MyFoodoraSystem.getCustomers().size());
        assertEquals(1, MyFoodoraSystem.getCouriers().size());
        assertEquals(1, MyFoodoraSystem.getManagers().size());
    }

    @Test
    public void testLogin() {
        User loggedInUser = MyFoodoraSystem.login("TestUsernameCustomer", "testPasswordCustomer");
        assertEquals("TestUsernameCustomer", loggedInUser.getUsername());
        User failedLoginUser = MyFoodoraSystem.login("NonExistentUser", "wrongPassword");
        assertEquals(failedLoginUser, null);
    }

    @Test
    public void testRemoveUser() {
        MyFoodoraSystem.removeUser(testCustomer);
        assertEquals(0, MyFoodoraSystem.getCustomers().size());
        assertEquals(3, MyFoodoraSystem.getUsers().size());
    }

    @Test
    public void testAddIncompleteOrder() {
        MyFoodoraSystem.addIncompleteOrder(testOrder);
        assertEquals(1, MyFoodoraSystem.getIncompleteOrders().size());
    }

    @Test
    public void testUpdateIncompleteOrders() {
        MyFoodoraSystem.addIncompleteOrder(testOrder);
        Order testOrder2= new Order(testCustomer, testRestaurant, "Order1");
        MyFoodoraSystem.updateIncompleteOrders(testOrder2);
        assertEquals(testOrder2, MyFoodoraSystem.getIncompleteOrders().get(0));
    }

    @Test
    public void testPlaceOrder() {
        MyFoodoraSystem.addIncompleteOrder(testOrder);
        MyFoodoraSystem.placeOrder(testOrder);
        assertEquals(1, MyFoodoraSystem.getOrders().size());
        assertEquals(0, MyFoodoraSystem.getIncompleteOrders().size());
    }

    @Test
    public void testComputeTotalIncome() {
        MyFoodoraSystem.addIncompleteOrder(testOrder);
        MyFoodoraSystem.placeOrder(testOrder);
        double price = testOrder.getTotalPrice();
        
        assertEquals(price, MyFoodoraSystem.computeTotalIncome(),0);
    }

    @Test
    public void testComputeTotalProfit() {
        MyFoodoraSystem.addIncompleteOrder(testOrder);
        MyFoodoraSystem.placeOrder(testOrder);
        double expectedProfit = testOrder.getTotalPrice() * MyFoodoraSystem.getMarkupPercentage() + MyFoodoraSystem.getServiceFee() - MyFoodoraSystem.getDeliveryCost();
        assertEquals(expectedProfit, MyFoodoraSystem.computeTotalProfit(),0);
    }


    @Test
    public void testDetermineLeastActiveCourier() {
    	Courier testCourier2 = new Courier("TestNameCourier2", "TestLastNameCourier2", "TestUsernameCourier2", "TestpasswordCourier2", null, new Position(100, 100), "1234567890");
        MyFoodoraSystem.addUser(testCourier2);
    	testCourier.setOnDuty(true);
    	testCourier2.setOnDuty(false);
        MyFoodoraSystem.addIncompleteOrder(testOrder);
        MyFoodoraSystem.placeOrder(testOrder);
        System.out.println(testCourier2);
        assertEquals(testCourier2.toString(), MyFoodoraSystem.determineLeastActiveCourier());
    }
    @Test
    public void testDetermineMostActiveCourier() {
    	Courier testCourier2 = new Courier("TestNameCourier2", "TestLastNameCourier2", "TestUsernameCourier2", "TestpasswordCourier2", null, new Position(100, 100), "1234567890");
        MyFoodoraSystem.addUser(testCourier2);
    	testCourier.setOnDuty(true);
    	testCourier2.setOnDuty(false);
        MyFoodoraSystem.addIncompleteOrder(testOrder);
        MyFoodoraSystem.placeOrder(testOrder);
        System.out.println(testCourier2);
        assertEquals(testCourier.toString(), MyFoodoraSystem.determineMostActiveCourier());
    }

    @Test
    public void testGetHistoryForManager() {
        MyFoodoraSystem.addIncompleteOrder(testOrder);
        MyFoodoraSystem.placeOrder(testOrder);
        List<Order> history = MyFoodoraSystem.getHistory(testManager);
        assertEquals(1, history.size());
    }

}
