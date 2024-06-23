package fr.cs.group12.myFoodora.fidelityCard;
/**
 * The FidelityCard interface defines the method to apply a discount based on the fidelity card.
 * Implementations of this interface will provide specific discount strategies.
 */
public interface FidelityCard {
    /**
     * Applies a discount to the total price based on the fidelity card's discount policy.
     *
     * @param totalPrice The original total price before discount.
     * @return The total price after applying the discount.
     */
    double applyDiscount(double totalPrice);
}

