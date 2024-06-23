package fr.cs.group12.myFoodora.shippedOrderSortingPolicy;

import java.util.List;

import fr.cs.group12.myFoodora.order.Order;

/**
 * Interface representing a shipped order sorting policy that allows restaurants and managers to see the most ordered meals/items a la carte in the MyFoodora system.
 * Implementations of this interface define how the meals/items a la carte are sorted and displayed.
 */
public interface ShippedOrderSortingPolicy {
    /**
     * Prints the sorted list of name of restaurant and item/meal ordered and the number of shipping based the policy
     *
     * @param orders The list of orders to consider.
     */
    void show(List<Order> orders);

}
