package fr.cs.group12.myFoodora.shippedOrderSortingPolicy;

import fr.cs.group12.myFoodora.menu.MenuItem;
import fr.cs.group12.myFoodora.order.Order;
import fr.cs.group12.myFoodora.user.Restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * This class represents a policy for sorting shipped orders. It sorts the orders based on the most ordered item in the m meals.
 */
public class MostOrderedItemMenuPolicy implements ShippedOrderSortingPolicy{
    /**
     * Prints the sorted list of name of restaurant and item à la carte ordere and the number of shipping
     *
     * @param orders The list of orders to consider.
     */
    public void show(List<Order> orders ) {
        Map<String, Integer> comboCount = new HashMap<>();

        for (Order order : orders) {
            Restaurant restaurant = order.getRestaurant();

            for (MenuItem item : order.getItems()) {

                String combo = restaurant.getName() + " - " + item.getName();

                comboCount.put(combo, comboCount.getOrDefault(combo, 0) + 1);

            }
        }
        // sorts the Item Menu based on their number of appearances.
        List<Map.Entry<String, Integer>> sortedCombos = new ArrayList<>(comboCount.entrySet());
        sortedCombos.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        for (Map.Entry<String, Integer> entry : sortedCombos) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
