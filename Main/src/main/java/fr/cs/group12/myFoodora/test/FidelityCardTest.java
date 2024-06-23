package fr.cs.group12.myFoodora.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import fr.cs.group12.myFoodora.fidelityCard.BasicFidelityCard;
import fr.cs.group12.myFoodora.fidelityCard.LotteryFidelityCard;
import fr.cs.group12.myFoodora.fidelityCard.PointFidelityCard;
import fr.cs.group12.myFoodora.menu.MainDish;
import fr.cs.group12.myFoodora.order.Order;
import fr.cs.group12.myFoodora.user.Customer;
import fr.cs.group12.myFoodora.user.Restaurant;
/**
 * This class tests the functionality of different fidelity cards in the MyFoodora system.
 * It tests the BasicFidelityCard, PointFidelityCard, and LotteryFidelityCard to ensure that
 * discounts and points are correctly applied.
 */
public class FidelityCardTest {
	
	private Customer customer;
    private Restaurant restaurant;
    private MainDish mainDish;
    private Order order;
    private BasicFidelityCard basicFidelityCard;
    private PointFidelityCard pointFidelityCard;
    private LotteryFidelityCard lotteryFidelityCard;


    @Before
    public void setUp() {
        // Create mock data for testing
        customer = new Customer("John Doe", "john.doe@example.com", null, null, null, null, null, null);
        restaurant = new Restaurant("Test Restaurant", "testuser", "testpass", null, null, null, null, null);
        mainDish=new MainDish("mainDish", 20.0,true,true);
        basicFidelityCard = new BasicFidelityCard();
        pointFidelityCard = new PointFidelityCard();
        lotteryFidelityCard = new LotteryFidelityCard();
        order = new Order(customer, restaurant);
        order.addItem(mainDish);
    }

    @Test
    public void testApplyDiscountBasicFidelityCard() {
    	customer.setFidelityCard(basicFidelityCard);
        double totalPrice = order.calculateTotalPrice();
        assertEquals(customer.getFidelityCard().applyDiscount(order.calculateTotalPrice()),totalPrice, 0.000);
    }
        @Test
        public void testApplyDiscountPointFidelityCardWithPointsIncrease() {
            customer.setFidelityCard(pointFidelityCard);
            double totalPrice = order.calculateTotalPrice();
            double initialPoints = ((PointFidelityCard)customer.getFidelityCard()).getPoints();
            double discountedPrice = customer.getFidelityCard().applyDiscount(totalPrice);
            double expectedPoints = initialPoints + totalPrice/10;
            assertEquals(expectedPoints, ((PointFidelityCard)customer.getFidelityCard()).getPoints(), 0.000);

        }

        @Test
        public void testApplyDiscountPointFidelityCardWithPriceReduction() {
            customer.setFidelityCard(pointFidelityCard);
            double totalPrice = order.calculateTotalPrice();
            double discountedPrice = customer.getFidelityCard().applyDiscount(totalPrice);
            double expectedDiscountedPrice = totalPrice * 1; // No reduction because points <100
            assertEquals(expectedDiscountedPrice, discountedPrice, 0.000);
        }

        @Test
        public void testApplyDiscountPointFidelityCardWithPointsReset() {
            customer.setFidelityCard(pointFidelityCard);
            double totalPrice = order.calculateTotalPrice();
            double discountedPrice = customer.getFidelityCard().applyDiscount(totalPrice);
            int iteration= (int) ((int) 100*10/totalPrice);
            // Perform iteration more applyDiscount calls to accumulate points
            for (int i = 0; i < iteration; i++) {
                discountedPrice = customer.getFidelityCard().applyDiscount(totalPrice);
            }
            double expectedPoints = totalPrice/10; // Points reset to zero + points gained with the order
            double expectedDiscountedPrice = totalPrice * 0.9; // reduction because points >=100
            assertEquals(expectedPoints, ((PointFidelityCard)customer.getFidelityCard()).getPoints(), 0.000);
            assertEquals(expectedDiscountedPrice, discountedPrice, 0.000);

        }
    @Test
    public void testApplyDiscountLotteryFidelityCard() {
    	customer.setFidelityCard(lotteryFidelityCard);
        double totalPrice = order.calculateTotalPrice();
        double discountedPrice = customer.getFidelityCard().applyDiscount(totalPrice);
        assertEquals(discountedPrice, totalPrice, 0.000); // 1% chance to fail the assert
    }
    


   
	
}
