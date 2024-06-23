package fr.cs.group12.myFoodora.deliveryPolicy;

import fr.cs.group12.myFoodora.myFoodora.MyFoodoraSystem;
import fr.cs.group12.myFoodora.order.Order;
import fr.cs.group12.myFoodora.spaceTimeCoordinates.Position;
import fr.cs.group12.myFoodora.user.Courier;
/**
 * This class implements the `DeliveryPolicy` interface and defines a fastest delivery policy.
 * This policy assigns an order to the available courier (on duty) who is closest to
 * both the restaurant and the customer, minimizing the total distance traveled.
 */
public class FastestDelivery implements DeliveryPolicy {
    /**
     * Assigns a courier to a given order based on the fastest delivery policy.
     *
     * @param order The order to be assigned a courier.
     *
     * This method calculates the total distance for each available courier (on duty) by
     * summing the distance from the courier's position to the restaurant's position
     * and the distance from the restaurant to the customer's position.
     * The courier with the minimal total distance is assigned to the order.
     * If no available couriers are found, a message is printed stating that no courier is available.
     */
    @Override
    public void assignCourier(Order order) {
        Position restaurantPosition = order.getRestaurant().getLocation();
        Position customerPosition = order.getCustomer().getAddress();

        // Find the best courier who is on duty and has the minimal total distance
        Courier bestCourier = MyFoodoraSystem.getCouriers().stream()
                .filter(Courier::isOnDuty)
                .min((c1, c2) -> Double.compare(
                        c1.getPosition().distanceTo(restaurantPosition) + restaurantPosition.distanceTo(customerPosition),
                        c2.getPosition().distanceTo(restaurantPosition) + restaurantPosition.distanceTo(customerPosition)))
                .orElse(null);

        if (bestCourier != null) {
            bestCourier.acceptDelivery();
            order.setCourier(bestCourier);
            System.out.println("Order assigned to courier: " + bestCourier.getName());
        } else {
            System.out.println("No on-duty courier available to assign the order.");
        }
    }

}

