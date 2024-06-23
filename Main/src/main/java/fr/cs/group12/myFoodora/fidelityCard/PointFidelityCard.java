package fr.cs.group12.myFoodora.fidelityCard;
/**
 * This class represents a point fidelity card in the MyFoodora system.
 * It implements the `FidelityCard` interface and offers a discount based on accumulated points.
 * Customers earn points for each order and can redeem them for a discount.
 */
public class PointFidelityCard implements FidelityCard {
    private int points;

    public PointFidelityCard() {
        this.points = 0;
    }
    /**
     * Gets the current number of points accumulated on the card.
     *
     * @return The number of points.
     */
    public int getPoints() {
        return points;
    }
    /**
     * This method calculates the discount to be applied to a total order price
     * based on the point fidelity card.
     *
     * @param totalPrice The total price of the order before applying any discounts.
     * @return The total price of the order after applying the discount (if applicable).
     *
     * Customers earn points based on each order (typically a percentage of the total price).
     * This method checks if enough points have been accumulated (at least 100) to redeem a discount.
     * If enough points are available, a 10% discount is applied, and the points are deducted (100 points).
     * Otherwise, the earned points for this order are added, but no discount is applied.
     */
    @Override
    public double applyDiscount(double totalPrice) {
        if (points >= 100) {
            points -= 100;
            points += (int) totalPrice / 10;
            return totalPrice * 0.90; // 10% discount
        }
        points += (int) totalPrice / 10;
        return totalPrice;
    }
}

