package fr.cs.group12.myFoodora.deliveryPolicy;

import fr.cs.group12.myFoodora.myFoodora.MyFoodoraSystem;
import fr.cs.group12.myFoodora.order.Order;
import fr.cs.group12.myFoodora.user.Courier;
/**
 * This class implements the `DeliveryPolicy` interface and defines a fair occupation delivery policy.
 * This policy assigns an order to the available courier with the least number of delivered orders.
 */
public class FairOccupationDelivery implements DeliveryPolicy {
    /**
     * Assigns a courier to a given order based on the fair occupation policy.
     *
     * @param order The order to be assigned a courier.
     *
     * This method searches for the available courier (on duty) with the least number of delivered orders.
     * If such a courier is found, the courier is assigned to the order and the order is marked as having a courier.
     * If no available couriers are found, a message is printed stating that no courier is available.
     */
    @Override
    public void assignCourier(Order order) {
        Courier bestCourier = MyFoodoraSystem.getCouriers().stream()
                .filter(Courier::isOnDuty)
                .min((c1, c2) -> Integer.compare(c1.getDeliveredOrders(), c2.getDeliveredOrders()))
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


