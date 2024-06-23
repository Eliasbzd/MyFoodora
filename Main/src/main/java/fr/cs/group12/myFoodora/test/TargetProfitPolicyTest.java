package fr.cs.group12.myFoodora.test;


import fr.cs.group12.myFoodora.menu.MainDish;
import fr.cs.group12.myFoodora.myFoodora.MyFoodoraSystem;
import fr.cs.group12.myFoodora.order.Order;
import fr.cs.group12.myFoodora.spaceTimeCoordinates.Position;
import fr.cs.group12.myFoodora.targetProfitPolicy.TargetProfitDeliveryCost;
import fr.cs.group12.myFoodora.targetProfitPolicy.TargetProfitMarkup;
import fr.cs.group12.myFoodora.targetProfitPolicy.TargetProfitServiceFee;
import fr.cs.group12.myFoodora.user.Courier;
import fr.cs.group12.myFoodora.user.Customer;
import fr.cs.group12.myFoodora.user.Restaurant;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;

/**
 * This class tests the functionality of target profit policies in the MyFoodora system.
 */
public class TargetProfitPolicyTest {
	private MyFoodoraSystem myFoodoraSystem;
    private Order order;
    private Restaurant restaurant;
    private MainDish mainDish;
    private Customer customer;
    private Courier courier;
    private double markupPercentage;
    private double deliveryCost;
    private double serviceFee;
    private int totalOrders;
    private double totalIncome;
    private double targetProfit;
    


    @Before
    public void setUp() throws Exception {
        // Create mock data for testing and reset myfoodora at each setUp
    	MyFoodoraSystem.resetMyFoodora();
        myFoodoraSystem = new MyFoodoraSystem();
        
        restaurant = new Restaurant("TestRestaurant", "testUsername", "testPassword", null, new Position(0, 0), null, null, myFoodoraSystem);
        customer = new Customer("TestCustomer", "TestSurname", "testUsername", "testPassword", null, new Position(10, 10), "email@example.com", "1234567890");
        order = new Order(customer, restaurant);
        mainDish=new MainDish("mainDish", 20.0,true,true);
        courier = new Courier("Courier1", "LastName1", "courier1", "password1", null, new Position(100, 100), "1234567890");        

        MyFoodoraSystem.addUser(courier);
        MyFoodoraSystem.addUser(restaurant);
        
        mainDish=new MainDish("mainDish", 20.0,true,true);
        order.addItem(mainDish);

        order.setTime(order.getTime().getStartOfPreviousMonth());
        MyFoodoraSystem.placeOrder(order);
        MyFoodoraSystem.placeOrder(order);
        MyFoodoraSystem.placeOrder(order);
        


        targetProfit = 50.0;
        totalIncome = MyFoodoraSystem.computeIncomeLastMonth();
        markupPercentage = MyFoodoraSystem.getMarkupPercentage();
        deliveryCost = MyFoodoraSystem.getDeliveryCost();
        serviceFee = MyFoodoraSystem.getServiceFee();
        totalOrders = MyFoodoraSystem.getNumberOfOrdersLastMonth();

 
    }
    @Test
    public void testTargetProfitDeliveryCost() {
        TargetProfitDeliveryCost policy = new TargetProfitDeliveryCost();
        int totalOrders = MyFoodoraSystem.getNumberOfOrdersLastMonth();
        policy.apply(myFoodoraSystem, targetProfit);
        double expectedDeliveryCost= (totalIncome * markupPercentage + serviceFee * totalOrders - targetProfit) / totalOrders;
        assertEquals(expectedDeliveryCost, MyFoodoraSystem.getDeliveryCost(), 0.00);

    }

    @Test
    public void testProfitTargetMarkup() {
    	TargetProfitMarkup policy = new TargetProfitMarkup();
        

        policy.apply(myFoodoraSystem, targetProfit);
        double expectedMarkupPercentage= (targetProfit + totalOrders * deliveryCost - totalOrders * serviceFee) / totalIncome;

        assertEquals(expectedMarkupPercentage, MyFoodoraSystem.getMarkupPercentage(), 0.01);


    }
    @Test
    public void testTargetProfitServiceFee() {
    	TargetProfitServiceFee policy = new TargetProfitServiceFee();
        double expectedServiceFee= (targetProfit + totalOrders * deliveryCost - totalIncome * markupPercentage) / totalOrders;;

        policy.apply(myFoodoraSystem,targetProfit);
        assertEquals(expectedServiceFee, MyFoodoraSystem.getServiceFee(), 0.01);

    }
    
}