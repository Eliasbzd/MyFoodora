package fr.cs.group12.myFoodora.shippedOrderSortingPolicy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.cs.group12.myFoodora.meal.HalfMeal;
import fr.cs.group12.myFoodora.meal.Meal;
import fr.cs.group12.myFoodora.myFoodora.MyFoodoraSystem;
import fr.cs.group12.myFoodora.order.Order;
import fr.cs.group12.myFoodora.user.Restaurant;

/**
 * This class represents a policy for sorting shipped orders. It sorts the orders based on the most ordered half meals.
 */
public class MostOrderedHalfMealPolicy implements ShippedOrderSortingPolicy {
	/**
	 * Prints the sorted list of name of restaurant and half meal ordered and the number of shipping
	 *
	 * @param orders The list of orders to consider.
	 */
	public void show(List<Order> orders ) {
		Map<String, Integer> comboCount = new HashMap<>();

		for (Order order : orders) {
			Restaurant restaurant = order.getRestaurant();

			for (Meal meal : order.getMeals()) {
				if (meal instanceof HalfMeal) {
					HalfMeal halfMeal = (HalfMeal) meal;
					String combo = restaurant.getName() + " - " + halfMeal.getName();

					comboCount.put(combo, comboCount.getOrDefault(combo, 0) + 1);
				}
			}
		}
		// sorts the half meals based on their number of appearances.
		List<Map.Entry<String, Integer>> sortedCombos = new ArrayList<>(comboCount.entrySet());
		sortedCombos.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

		for (Map.Entry<String, Integer> entry : sortedCombos) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}
		
	}


