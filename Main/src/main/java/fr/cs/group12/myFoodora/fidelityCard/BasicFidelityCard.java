package fr.cs.group12.myFoodora.fidelityCard;
/**
 * This class represents a basic fidelity card in the MyFoodora system.
 * It implements the `FidelityCard` interface but does not provide any discount.
 */
public class BasicFidelityCard implements FidelityCard {
    /**
     * This method calculates the discount to be applied to a total order price
     * based on the basic fidelity card.
     *
     * @param totalPrice The total price of the order before applying any discounts.
     * @return The total price of the order after applying the discount (no discount in this case).
     *
     * Since this is a basic fidelity card, it does not offer any discount.
     * The original total price is returned without any modification.
     */
    @Override
    public double applyDiscount(double totalPrice) {
        return totalPrice; // No discount applied
    }
}

