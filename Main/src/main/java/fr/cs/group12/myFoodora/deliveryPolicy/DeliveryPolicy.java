package fr.cs.group12.myFoodora.deliveryPolicy;

import java.util.List;

import fr.cs.group12.myFoodora.order.Order;
/**
 * The DeliveryPolicy interface defines the method to assign a courier to an order.
 * Implementations of this interface will provide specific strategies for courier assignment.
 */
public interface DeliveryPolicy {
    /**
     * Assigns a courier to the given order based on the implementation of the delivery policy.
     *
     * @param order The order to which a courier needs to be assigned.
     */
    void assignCourier(Order order);

}

