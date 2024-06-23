package fr.cs.group12.myFoodora.fidelityCard;

import java.util.Random;
/**
 * This class represents a lottery fidelity card in the MyFoodora system.
 * It implements the `FidelityCard` interface and offers a chance to win a full discount
 * on the order total price.
 */
public class LotteryFidelityCard implements FidelityCard {
    private Random random;
    /**
     * Constructs a new LotteryFidelityCard with a random number generator.
     */
    public LotteryFidelityCard() {
        this.random = new Random();
    }
    /**
     * This method calculates the discount to be applied to a total order price
     * based on the lottery fidelity card.
     *
     * @param totalPrice The total price of the order before applying any discounts.
     * @return The total price of the order after applying the discount (potentially free).
     *
     * This method simulates a lottery with a 1% chance of winning a full discount
     * on the order (resulting in a total price of 0.0). If the random number generated
     * is less than 0.01, the entire order price is discounted. Otherwise, no discount
     * is applied and the original total price is returned.
     */
    @Override
    public double applyDiscount(double totalPrice) {
        if (random.nextDouble() < 0.01) { // 1% chance to get the meal for free
            return 0.0;
        }
        return totalPrice;
    }
}

