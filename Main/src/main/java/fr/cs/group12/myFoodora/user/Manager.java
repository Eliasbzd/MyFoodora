package fr.cs.group12.myFoodora.user;

import java.util.List;

import fr.cs.group12.myFoodora.deliveryPolicy.DeliveryPolicy;
import fr.cs.group12.myFoodora.myFoodora.MyFoodoraSystem;
import fr.cs.group12.myFoodora.order.Order;
import fr.cs.group12.myFoodora.shippedOrderSortingPolicy.ShippedOrderSortingPolicy;
import fr.cs.group12.myFoodora.targetProfitPolicy.TargetProfitPolicy;
/**
 * The Manager class represents a user with managerial privileges in the MyFoodora system.
 * It extends the User class and includes additional attributes such as surname and a reference to the MyFoodora system.
 * It provides methods to manage users, set system parameters, compute statistics, and access system data.
 */
public class Manager extends User {
    private String surname;
    private static MyFoodoraSystem myfoodorasystem;

    /**
     * Constructs a Manager with the specified attributes.
     *
     * @param name The name of the manager.
     * @param surname The surname of the manager.
     * @param id The ID of the manager.
     * @param username The username of the manager.
     * @param password The password of the manager.
     * @param myFoodoraSystem The reference to the MyFoodora system.
     */
    public Manager(String name, String surname, String id, String username, String password,MyFoodoraSystem myFoodoraSystem) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.username = username;
        this.password = password;
        Manager.myfoodorasystem = myFoodoraSystem;

    }

    // User management methods



    /**
     * Adds a user to the MyFoodora system.
     *
     * @param user The user to add.
     */
    public void addUser(User user) {
        MyFoodoraSystem.addUser(user);
    }

    /**
     * Removes a user from the MyFoodora system.
     *
     * @param user The user to remove.
     */
    public void removeUser(User user) {
        MyFoodoraSystem.removeUser(user);
    }

    /**
     * Activates a user in the MyFoodora system.
     *
     * @param user The user to activate.
     */
    public void activateUser(User user) {
        user.active=true;
    }
    /**
     * Deactivates a user in the MyFoodora system.
     *
     * @param user The user to deactivate.
     */
    public void deactivateUser(User user) {
        user.active=false;
    }

    /**
     * Sets the current delivery policy for the MyFoodora system.
     *
     * @param policy The delivery policy to set.
     */

    // System parameter setting methods

    public void setCurrentDeliveryPolicy(DeliveryPolicy policy) {
        MyFoodoraSystem.setCurrentDeliveryPolicy(policy);
    }
    
    /**
     * Sets the current target profit policy for the MyFoodora system.
     *
     * @param policy The target profit policy to set.
     */
    public void setCurrentProfitPolicy(TargetProfitPolicy policy) {
        MyFoodoraSystem.setProfitPolicy(policy);
    }
    
    /**
     * Applies the current target profit policy with a specific target profit value.
     *
     * @param targetProfit The target profit to apply.
     */
    public void applyCurrentProfitPolicy(double targetProfit) {
    	MyFoodoraSystem.getTargetProfitPolicy().apply(myfoodorasystem, targetProfit);
    }
    /**
     * Sets the service fee for the MyFoodora system.
     *
     * @param serviceFee The service fee to set.
     */
    public void setServiceFee(double serviceFee) {
        MyFoodoraSystem.setServiceFee(serviceFee);
    }
    /**
     * Sets the markup percentage for the MyFoodora system.
     *
     * @param markupPercentage The markup percentage to set.
     */
    public void setMarkupPercentage(double markupPercentage) {
        MyFoodoraSystem.setMarkupPercentage(markupPercentage);
    }
    /**
     * Sets the delivery cost for the MyFoodora system.
     *
     * @param deliveryCost The delivery cost to set.
     */
    public void setDeliveryCost(double deliveryCost) {
        MyFoodoraSystem.setDeliveryCost(deliveryCost);
    }

    /**
     * Gets the shipped order sorting policy.
     *
     * @return the shipped order sorting policy.
     */
    public static ShippedOrderSortingPolicy getShippedOrderSortingPolicy() {
        return MyFoodoraSystem.getShippedOrderSortingPolicy();
    }
    /**
     * Sets the shipped order sorting policy for the MyFoodora system.
     *
     * @param shippedOrderSortingPolicy The target profit policy to be set.
     */
    public void setShippedOrderSortingPolicy(ShippedOrderSortingPolicy shippedOrderSortingPolicy) {
        MyFoodoraSystem.setShippedOrderSortingPolicy(shippedOrderSortingPolicy);
    }

    // Statistics computation methods

    /**
     * This method displays the ordered shipped orders based on the shipped order sorting policy
     *
     */
    public static void showOrderedShippedOrder(){
        MyFoodoraSystem.showOrderedShippedOrder();
    }
    /**
     * Computes the total income of the MyFoodora system.
     *
     * @return The total income.
     */
    public double computeTotalIncome() {
        return MyFoodoraSystem.computeTotalIncome();
    }

    /**
     * Computes the total profit of the MyFoodora system.
     *
     * @return The total profit.
     */
    public double computeTotalProfit() {
        return MyFoodoraSystem.computeTotalProfit();
    }

    /**
     * Computes the average income per customer in the MyFoodora system.
     *
     * @return The average income per customer.
     */
    public double computeAverageIncomePerCustomer() {
        return MyFoodoraSystem.computeAverageIncomePerCustomer();
    }

    /**
     * Determines the most active courier in the MyFoodora system.
     *
     * @return The name of the most active courier.
     */
    public String determineMostActiveCourier() {
        return MyFoodoraSystem.determineMostActiveCourier();
    }

    /**
     * Determines the least active courier in the MyFoodora system.
     *
     * @return The name of the least active courier.
     */
    public String determineLeastActiveCourier() {
        return MyFoodoraSystem.determineLeastActiveCourier();
    }

    /**
     * Retrieves the most selling restaurant in the MyFoodora system.
     *
     * @return The most selling restaurant.
     */
    public Restaurant getMostSellingRestaurant() {
        return MyFoodoraSystem.getMostSellingRestaurant();
    }

    /**
     * Retrieves the least selling restaurant in the MyFoodora system.
     *
     * @return The least selling restaurant.
     */
    public Restaurant getLeastSellingRestaurant() {
       return MyFoodoraSystem.getLeastSellingRestaurant();
    }

    /**
     * Gets the list of restaurants in the MyFoodora system.
     *
     * @return The list of restaurants.
     */
    private static List<Restaurant> getRestaurants() {
        return MyFoodoraSystem.getRestaurants();
    }

    /**
     * Gets the list of customers in the MyFoodora system.
     *
     * @return The list of customers.
     */
    private static List<Customer> getCustomers() {
        return MyFoodoraSystem.getCustomers();
    }

    /**
     * Gets the list of couriers in the MyFoodora system.
     *
     * @return The list of couriers.
     */
    private static List<Courier> getCouriers() {
        return MyFoodoraSystem.getCouriers();
    }

    /**
     * Gets the list of orders in the MyFoodora system.
     *
     * @return The list of orders.
     */
    private static List<Order> getOrders() {
        return MyFoodoraSystem.getOrders();
    }
}

