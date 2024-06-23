package fr.cs.group12.myFoodora.test;

import org.junit.Before;
import org.junit.Test;
import fr.cs.group12.myFoodora.menu.Dessert;
import fr.cs.group12.myFoodora.menu.MainDish;
import fr.cs.group12.myFoodora.myFoodora.MyFoodoraSystem;
import fr.cs.group12.myFoodora.order.Order;
import fr.cs.group12.myFoodora.spaceTimeCoordinates.Position;
import fr.cs.group12.myFoodora.user.Courier;
import fr.cs.group12.myFoodora.user.Customer;
import fr.cs.group12.myFoodora.user.Restaurant;
import fr.cs.group12.myFoodora.shippedOrderSortingPolicy.MostOrderedItemMenuPolicy;
import fr.cs.group12.myFoodora.shippedOrderSortingPolicy.MostOrderedHalfMealPolicy;
import fr.cs.group12.myFoodora.meal.HalfMeal;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

/**
 * This class tests the different shipped order sorting policies in the MyFoodora system, including
 * the Most Ordered Item Menu Policy and the Most Ordered Half Meal Policy.
 */

// Custom output stream class to capture System.out output (problem when importing ByteArrayOutputStream)
class CustomOutputStream extends OutputStream {
    private StringBuilder buffer;
    private List<String> lines;

    public CustomOutputStream() {
        buffer = new StringBuilder();
        lines = new ArrayList<>();
    }

    @Override
    public void write(int b) {
        if (b == '\n') {
            lines.add(buffer.toString());
            buffer.setLength(0);
        } else {
            buffer.append((char) b);
        }
    }

    public List<String> getLines() {
        if (buffer.length() > 0) {
            lines.add(buffer.toString());
            buffer.setLength(0);
        }
        return lines;
    }
}
/**
 * This class tests the different shipped order sorting policies in the MyFoodora system, including
 * the Most Ordered Item Menu Policy and the Most Ordered Half Meal Policy.
 */
public class ShippedOrderSortingPolicyTest {
    private MyFoodoraSystem myFoodoraSystem;
    private Order order;
    private Order order2;
    private Order order3;
    private Order order4;
    private Restaurant restaurant;
    private MainDish mainDish;
    private Dessert dessert;
    private HalfMeal halfMeal;
    private HalfMeal halfMeal2;
    private Customer customer;
    private Courier courier;

    private List<String> output;

    @Before
    public void setUp() throws Exception {
        // Create mock data for testing and reset myFoodora at each setUp
        MyFoodoraSystem.resetMyFoodora();
        myFoodoraSystem = new MyFoodoraSystem();

        restaurant = new Restaurant("TestRestaurant", "testUsername", "testPassword", null, new Position(0, 0), null, null, myFoodoraSystem);
        customer = new Customer("TestCustomer", "TestSurname", "testUsername", "testPassword", null, new Position(10, 10), "email@example.com", "1234567890");
        courier = new Courier("Courier1", "LastName1", "courier1", "password1", null, new Position(100, 100), "1234567890");

        MyFoodoraSystem.addUser(courier);
        MyFoodoraSystem.addUser(restaurant);

        mainDish = new MainDish("mainDish", 20.0, true, true);
        dessert = new Dessert("dessert", 10.0, true, true);
        halfMeal = new HalfMeal("HalfMeal1", mainDish, dessert);
        halfMeal2 = new HalfMeal("HalfMeal2", mainDish, dessert);

        order = new Order(customer, restaurant);
        order.addItem(mainDish);
        MyFoodoraSystem.placeOrder(order);
        MyFoodoraSystem.placeOrder(order);
        MyFoodoraSystem.placeOrder(order);

        order2 = new Order(customer, restaurant);
        order2.addItem(dessert);
        MyFoodoraSystem.placeOrder(order2);
        MyFoodoraSystem.placeOrder(order2);

        order3 = new Order(customer, restaurant);
        order3.addMeal(halfMeal);
        MyFoodoraSystem.placeOrder(order3);
        MyFoodoraSystem.placeOrder(order3);
        MyFoodoraSystem.placeOrder(order3);
        MyFoodoraSystem.placeOrder(order3);
        MyFoodoraSystem.placeOrder(order3);

        order4 = new Order(customer, restaurant);
        order4.addMeal(halfMeal2);
        MyFoodoraSystem.placeOrder(order4);
        MyFoodoraSystem.placeOrder(order4);

        output = new ArrayList<>();
    }

    private void captureOutput(Runnable action) {
        PrintStream originalOut = System.out;
        CustomOutputStream cos = new CustomOutputStream();
        PrintStream ps = new PrintStream(cos);
        System.setOut(ps);

        action.run();

        System.out.flush();
        System.setOut(originalOut);

        output = cos.getLines();
    }

    @Test
    public void testMostOrderedItemMenuPolicy() {
        MyFoodoraSystem.setShippedOrderSortingPolicy(new MostOrderedItemMenuPolicy());
        captureOutput(MyFoodoraSystem::showOrderedShippedOrder);

        assertEquals("TestRestaurant - mainDish: 3", output.get(0).trim());
        assertEquals("TestRestaurant - dessert: 2", output.get(1).trim());
    }

    @Test
    public void testMostOrderedHalfMealPolicy() {
        MyFoodoraSystem.setShippedOrderSortingPolicy(new MostOrderedHalfMealPolicy());
        captureOutput(MyFoodoraSystem::showOrderedShippedOrder);

        assertEquals("TestRestaurant - HalfMeal1: 5", output.get(0).trim());
        assertEquals("TestRestaurant - HalfMeal2: 2", output.get(1).trim());
    }
}
