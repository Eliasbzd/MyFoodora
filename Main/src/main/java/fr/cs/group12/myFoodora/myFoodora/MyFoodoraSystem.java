package fr.cs.group12.myFoodora.myFoodora;

import java.util.*;
import java.util.stream.Collectors;

import fr.cs.group12.myFoodora.deliveryPolicy.DeliveryPolicy;
import fr.cs.group12.myFoodora.deliveryPolicy.FairOccupationDelivery;
import fr.cs.group12.myFoodora.order.Order;
import fr.cs.group12.myFoodora.shippedOrderSortingPolicy.MostOrderedItemMenuPolicy;
import fr.cs.group12.myFoodora.shippedOrderSortingPolicy.ShippedOrderSortingPolicy;
import fr.cs.group12.myFoodora.spaceTimeCoordinates.Time;
import fr.cs.group12.myFoodora.targetProfitPolicy.TargetProfitPolicy;
import fr.cs.group12.myFoodora.user.Courier;
import fr.cs.group12.myFoodora.user.Customer;
import fr.cs.group12.myFoodora.user.Manager;
import fr.cs.group12.myFoodora.user.Observable;
import fr.cs.group12.myFoodora.user.Restaurant;
import fr.cs.group12.myFoodora.user.User;
/**
 * This class represents the MyFoodora system, which is a food delivery platform. It is the core of the system.
 * It manages users (restaurants, customers, couriers, and managers), orders, and system configurations.
 */
public class MyFoodoraSystem  {
    private static List<Restaurant> restaurants;
    private static List<Customer> customers;
    private static List<Courier> couriers;
    private static List<Order> orders;
    private static List<Order> incompleteOrders;
    private static List<Manager> managers;
    private static List<Double> profit;
    private static List<User> users;
    private static double serviceFee;
    private static double markupPercentage;
    private static double deliveryCost;
    private static DeliveryPolicy deliveryPolicy;
    private static TargetProfitPolicy targetProfitPolicy;
    private static ShippedOrderSortingPolicy shippedOrderSortingPolicy;


    static { // Static initialization block
        restaurants = new ArrayList<>();
        customers = new ArrayList<>();
        couriers = new ArrayList<>();
        orders = new ArrayList<>();
        incompleteOrders = new ArrayList<>();
        managers = new ArrayList<>();
        users = new ArrayList<>();
        serviceFee = 0.10;
        markupPercentage = 0.20;
        deliveryCost = 1.0;
        deliveryPolicy = new FairOccupationDelivery();
        shippedOrderSortingPolicy=new MostOrderedItemMenuPolicy();
        profit = new ArrayList<>();
    }
    /**
     * Resets the MyFoodora system to its initial state.
     */
    public static void resetMyFoodora() {
        restaurants.clear();
        customers.clear();
        couriers.clear();
        orders.clear();
        incompleteOrders.clear();
        managers.clear();
        users.clear();
        profit.clear();
        serviceFee = 0.10;
        markupPercentage = 0.20;
        deliveryCost = 1.0;
        deliveryPolicy = new FairOccupationDelivery();
    }
    /**
     * Sets the target profit policy for the MyFoodora system.
     *
     * @param targetProfitPolicy The target profit policy to be set.
     */
    public static void setProfitPolicy(TargetProfitPolicy targetProfitPolicy) {
        MyFoodoraSystem.setTargetProfitPolicy(targetProfitPolicy);
    }
    /**
     * Sets the shipped order sorting policy for the MyFoodora system.
     *
     * @param shippedOrderSortingPolicy The target profit policy to be set.
     */
    public static void setShippedOrderSortingPolicy(ShippedOrderSortingPolicy shippedOrderSortingPolicy) {
        MyFoodoraSystem.shippedOrderSortingPolicy= shippedOrderSortingPolicy;
}
    /**
     * Gets the shipped order sorting policy.
     *
     * @return the shipped order sorting policy.
     */
    public static ShippedOrderSortingPolicy getShippedOrderSortingPolicy() {
        return shippedOrderSortingPolicy;
    }
    /**
     * This method displays the ordered shipped orders based on the shipped order sorting policy
     *
     */
    public static void showOrderedShippedOrder(){
        shippedOrderSortingPolicy.show(orders);
    }

    /**
     * Adds a new user to the MyFoodora system.
     *
     * @param user The user to be added.
     *
     * This method checks for duplicate usernames and adds the user to the appropriate list
     * based on their type (restaurant, customer, courier, or manager).
     */
    public static void addUser(User user) {
        for (User existingUser : users) {
            if (existingUser.getUsername().equals(user.getUsername())) {
                System.out.println("username already used, please change the username");
                return;
            }
        }
        users.add(user);
        if (user instanceof Restaurant) {
            restaurants.add((Restaurant) user);
            System.out.println("Restaurant registered successfully.");
        } else if (user instanceof Customer) {
            customers.add((Customer) user);
            System.out.println("Customer registered successfully.");
        } else if (user instanceof Courier) {
            couriers.add((Courier) user);
            System.out.println("Courier registered successfully.");
        } else if (user instanceof Manager) {
            managers.add((Manager) user);
            System.out.println("Manager registered successfully.");
        }
    }


    /**
     * Logs in a user to the MyFoodora system.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     *
     * @return The logged-in user object if successful, null otherwise (e.g., inactive account).
     */
    public static User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                if (!user.isActive()) {
                    System.out.println("Your account was deactivated. You can't use MyFoodora app.");
                    return null;
                }
                System.out.println(user.getName()+" : Login successful.");
                return user;
            }
        }
        return null;
    }

    /**
     * Removes a user from the MyFoodora system.
     *
     * @param user The user to be removed.
     */
    public static void removeUser(User user) {
        users.remove(user);
        if (user instanceof Restaurant) {
            restaurants.remove((Restaurant) user);
        } else if (user instanceof Customer) {
            customers.remove((Customer) user);
        } else if (user instanceof Courier) {
            couriers.remove((Courier) user);
        } else if (user instanceof Manager) {
            managers.remove((Manager) user);
        }
    }
    /**
     * Gets the list of all users.
     *
     * @return the list of users.
     */
    public static List<User> getUsers() {
        return users;
    }

    /**
     * Adds an incomplete order to the list of incomplete orders.
     *
     * @param order the incomplete order to be added.
     */
    public static void addIncompleteOrder (Order order){
        incompleteOrders.add(order);
    }

    /**
     * Updates an existing incomplete order in the list.
     *
     * @param order the order to be updated.
     */
    public static void updateIncompleteOrders(Order order) {
        for (int i = 0; i < incompleteOrders.size(); i++) {
            if (incompleteOrders.get(i).getName().equals(order.getName())) {
                incompleteOrders.set(i, order);
                System.out.println("Order " + order.getName() + " updated in incomplete orders.");
                return;
            }
        }
        System.out.println("Order " + order.getName() + " not found in incomplete orders.");
    }
    /**
     * Gets the list of incomplete orders.
     *
     * @return the list of incomplete orders.
     */
    public static List<Order> getIncompleteOrders() {
        return incompleteOrders;
    }

    /**
     * Gets the service fee.
     *
     * @return the service fee.
     */
    public static double getServiceFee() {
        return serviceFee;
    }

    /**
     * Gets the markup percentage.
     *
     * @return the markup percentage.
     */
    public static double getMarkupPercentage() {
        return markupPercentage;
    }

    /**
     * Gets the delivery cost.
     *
     * @return the delivery cost.
     */
    public static double getDeliveryCost() {
        return deliveryCost;
    }

    /**
     * Gets the list of profits.
     *
     * @return the list of profits.
     */
    public static List<Double> getProfit() {
        return profit;
    }
    /**
     * This method returns the delivery policy.
     *
     * @return The delivery policy.
     */
    public static DeliveryPolicy getDeliveryPolicy() {
        return deliveryPolicy;
    }

    /**
     * Sets the service fee for MyFoodoraSystem.
     *
     * @param serviceFee The new service fee as a double.
     */
    public static void setServiceFee(double serviceFee) {
        MyFoodoraSystem.serviceFee = serviceFee;
    }

    /**
     * Sets the markup percentage for MyFoodoraSystem.
     *
     * @param markupPercentage The new service fee as a double.
     */
    public static void setMarkupPercentage(double markupPercentage) {
        MyFoodoraSystem.markupPercentage = markupPercentage;
    }
    /**
     * Sets the delivery cost for MyFoodoraSystem.
     *
     * @param deliveryCost The new service fee as a double.
     */
    public static void setDeliveryCost(double deliveryCost) {
        MyFoodoraSystem.deliveryCost = deliveryCost;
    }

    /**
     * Returns the number of orders made within a specific time period.
     *
     * @param startTime The beginning of the time period.
     * @param endTime The end of the time period.
     * @return The number of orders made within the specified time period.
     */
    public static int getNumberOfOrdersPeriod(Time startTime, Time endTime) {
        return (int) orders.stream()
                .filter(order -> {
                    Time orderTime = order.getTime();
                    return (orderTime.isAfter(startTime) && orderTime.isBefore(endTime)) ||
                            orderTime.isEqual(startTime) || orderTime.isEqual(endTime);
                })
                .count();
    }

    /**
     * Returns the number of orders made in a specific month.
     *
     * @param year The year of the month.
     * @param month The month to check.
     * @return The number of orders made in the specified month.
     */
    public static int getNumberOfOrdersForMonth(int year, int month) {
        return (int) orders.stream()
                .filter(order -> {
                    Time orderTime = order.getTime();
                    return orderTime.getYear() == year && orderTime.getMonth() == month;
                })
                .count();
    }

    /**
     * Returns the number of orders made in a specific year.
     *
     * @param year The year to check.
     * @return The number of orders made in the specified year.
     */
    public static int getNumberOfOrdersForYear(int year) {
        return (int) orders.stream()
                .filter(order -> order.getTime().getYear() == year)
                .count();
    }

    /**
     * Returns the number of orders made in the previous month.
     *
     * @return The number of orders made in the previous month.
     */
    public static int getNumberOfOrdersLastMonth() {
        Time currentTime = Time.getCurrentTime();
        Time startTime = currentTime.getStartOfPreviousMonth();
        Time endTime = currentTime.getEndOfPreviousMonth();

        return getNumberOfOrdersPeriod(startTime, endTime);
    }

    /**
     * Calculates the total income from all orders.
     *
     * @return The total income from all orders.
     */
    public static double computeTotalIncome() {
        return orders.stream().mapToDouble(Order::getTotalPrice).sum();
    }

    /**
     * Calculates the income from orders made within a specific time period.
     *
     * @param startTime The beginning of the time period.
     * @param endTime The end of the time period.
     * @return The income from orders made within the specified time period.
     */
    public static double computeIncomePeriod(Time startTime, Time endTime) {
        return orders.stream()
                .filter(order -> {
                    Time orderTime = order.getTime();
                    return (orderTime.isAfter(startTime) && orderTime.isBefore(endTime)) ||
                            orderTime.isEqual(startTime) || orderTime.isEqual(endTime);
                })
                .mapToDouble(Order::getTotalPrice)
                .sum();
    }

    /**
     * Calculates the income from orders made in a specific month.
     *
     * @param year The year of the month.
     * @param month The month to check.
     * @return The income from orders made in the specified month.
     */
    public static double computeIncomeForMonth(int year, int month) {
        return orders.stream()
                .filter(order -> {
                    Time orderTime = order.getTime();
                    return orderTime.getYear() == year && orderTime.getMonth() == month;
                })
                .mapToDouble(Order::getTotalPrice)
                .sum();
    }

    /**
     * Calculates the income from orders made in a specific year.
     *
     * @param year The year to check.
     * @return The income from orders made in the specified year.
     */
    public static double computeIncomeForYear(int year) {
        return orders.stream()
                .filter(order -> order.getTime().getYear() == year)
                .mapToDouble(Order::getTotalPrice)
                .sum();
    }

    /**
     * Calculates the income from orders made in the previous month.
     *
     * @return The income from orders made in the previous month.
     */
    public static double computeIncomeLastMonth() {
        Time currentTime = Time.getCurrentTime();
        Time startTime = currentTime.getStartOfPreviousMonth();
        Time endTime = currentTime.getEndOfPreviousMonth();

        return computeIncomePeriod(startTime, endTime);
    }

    /**
     * Calculates the total profit from all orders.
     *
     * @return The total profit from all orders.
     */
    public static double computeTotalProfit() {
        return profit.stream().mapToDouble(Double::doubleValue).sum();
    }

    /**
     * Calculates the profit from orders made within a specific time period.
     *
     * @param startTime The beginning of the time period.
     * @param endTime The end of the time period.
     * @return The profit from orders made within the specified time period.
     */
    public static double calculateProfitPeriod(Time startTime, Time endTime) {
        double totalProfit = 0.0;
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            Time orderTime = order.getTime();
            if ((orderTime.isAfter(startTime) && orderTime.isBefore(endTime)) ||
                    orderTime.isEqual(startTime) || orderTime.isEqual(endTime)) {
                totalProfit += profit.get(i);
            }
        }
        return totalProfit;
    }
    /**
     * Calculates the profit from orders made in a specific year.
     *
     * @param year The year to check.
     * @return The profit from orders made in the specified year.
     */
    public static double calculateProfitForYear(int year) {
        double totalProfit = 0.0;
        for (int i = 0; i < orders.size(); i++) {
            Time orderTime = orders.get(i).getTime();
            if (orderTime.getYear() == year) {
                totalProfit += profit.get(i);
            }
        }
        return totalProfit;
    }

    /**
     * Calculates the profit from orders made in a specific month.
     *
     * @param year The year of the month.
     * @param month The month to check.
     * @return The profit from orders made in the specified month.
     */
    public static double calculateProfitForMonth(int year, int month) {
        double totalProfit = 0.0;
        for (int i = 0; i < orders.size(); i++) {
            Time orderTime = orders.get(i).getTime();
            if (orderTime.getYear() == year && orderTime.getMonth() == month) {
                totalProfit += profit.get(i);
            }
        }
        return totalProfit;
    }

    /**
     * Calculates the profit from orders made in the previous month.
     *
     * @return The profit from orders made in the previous month.
     */
    public static double calculateProfitLastMonth() {
        Time time = Time.getCurrentTime();
        Time startTime = time.getStartOfPreviousMonth();
        Time endTime = time.getEndOfPreviousMonth();
        return calculateProfitPeriod(startTime, endTime);
    }

    /**
     * Calculates the average income per customer within a specific time period.
     *
     * @param startTime The beginning of the time period.
     * @param endTime The end of the time period.
     * @return The average income per customer within the specified time period.
     */
    public static double computeAverageIncomePerCustomerPeriod(Time startTime, Time endTime) {
        long numCustomers = orders.stream()
                .filter(order -> {
                    Time orderTime = order.getTime();
                    return (orderTime.isAfter(startTime) && orderTime.isBefore(endTime)) ||
                            orderTime.isEqual(startTime) || orderTime.isEqual(endTime);
                })
                .map(Order::getCustomer)
                .distinct()
                .count();
        return computeIncomePeriod(startTime, endTime) / numCustomers;
    }

    /**
     * Calculates the average income per customer from all orders.
     *
     * @return The average income per customer from all orders.
     */
    public static double computeAverageIncomePerCustomer() {
        long numCustomers = orders.stream().map(Order::getCustomer).distinct().count();

        return computeTotalIncome() / numCustomers;
    }

    /**
     * Returns the restaurant that has made the most sales.
     *
     * @return The restaurant that has made the most sales.
     */
    public static Restaurant getMostSellingRestaurant() {
        Map<Restaurant, Integer> restaurantSales = new HashMap<>();

        for (Order order : orders) {
            Restaurant restaurant = order.getRestaurant();
            restaurantSales.put(restaurant, restaurantSales.getOrDefault(restaurant, 0) + 1);
        }

        // Find the restaurant with the most sales
        Restaurant mostSellingRestaurant = null;
        int maxSales = Integer.MIN_VALUE;
        for (Map.Entry<Restaurant, Integer> entry : restaurantSales.entrySet()) {
            if (entry.getValue() > maxSales) {
                maxSales = entry.getValue();
                mostSellingRestaurant = entry.getKey();
            }
        }

        return mostSellingRestaurant;
    }

    /**
     * Returns the restaurant that has made the least sales.
     *
     * @return The restaurant that has made the least sales.
     */
    public static Restaurant getLeastSellingRestaurant() {
        Map<Restaurant, Integer> restaurantSales = new HashMap<>();

        for (Order order : orders) {
            Restaurant restaurant = order.getRestaurant();
            restaurantSales.put(restaurant, restaurantSales.getOrDefault(restaurant, 0) + 1);
        }

        // Find the restaurant with the least sales
        Restaurant leastSellingRestaurant = null;
        int minSales = Integer.MAX_VALUE;
        for (Map.Entry<Restaurant, Integer> entry : restaurantSales.entrySet()) {
            if (entry.getValue() < minSales) {
                minSales = entry.getValue();
                leastSellingRestaurant = entry.getKey();
            }
        }

        return leastSellingRestaurant;
    }

    /**
     * Determines the most active courier.
     *
     * @return The most active courier.
     */
    public static String determineMostActiveCourier() {
        return couriers.stream()
                .max((c1, c2) -> Integer.compare(c1.getDeliveredOrders(), c2.getDeliveredOrders()))
                .map(Courier::toString)
                .orElse("No couriers available");
    }
    /**
     * Determines the least active courier.
     *
     * @return The least active courier.
     */
    public static String determineLeastActiveCourier() {
        return couriers.stream()
                .min((c1, c2) -> Integer.compare(c1.getDeliveredOrders(), c2.getDeliveredOrders()))
                .map(Courier::toString)
                .orElse("No couriers available");
    }

    /**
     * Sets the current delivery policy.
     *
     * @param policy The delivery policy to set.
     */
    public static void setCurrentDeliveryPolicy(DeliveryPolicy policy) {
        deliveryPolicy = policy;
    }

    /**
     * Places an order. It finalizes the order, add it into the orders list and remove it from the incomplete order
     *
     * @param order The order to place.
     */
    public static void placeOrder(Order order) {
        double totalPrice = order.calculateTotalPrice();
        double discountedPrice = order.getCustomer().getFidelityCard().applyDiscount(totalPrice);
        order.setTotalPrice(discountedPrice);
        deliveryPolicy.assignCourier(order);
        orders.add(order);
        profit.add(order.getTotalPrice() * markupPercentage + serviceFee - deliveryCost);
        incompleteOrders.remove(order);
    }

    /**
     * Returns the list of all orders.
     *
     * @return The list of all orders.
     */
    public static List<Order> getOrders() {
        return orders;
    }

    /**
     * Returns the list of all restaurants.
     *
     * @return The list of all restaurants.
     */
    public static List<Restaurant> getRestaurants() {
        return restaurants;
    }

    /**
     * Returns the list of all customers.
     *
     * @return The list of all customers.
     */
    public static List<Customer> getCustomers() {
        return customers;
    }

    /**
     * Returns the list of all couriers.
     *
     * @return The list of all couriers.
     */
    public static List<Courier> getCouriers() {
        return couriers;
    }

    /**
     * Returns the list of all managers.
     *
     * @return The list of all managers.
     */
    public  static List<Manager> getManagers() {
        return managers;
    }

    /**
     * Sets the delivery policy for the system.
     *
     * @param deliveryPolicy The delivery policy to set.
     */
    public static void setDeliveryPolicy(DeliveryPolicy deliveryPolicy) {
        MyFoodoraSystem.deliveryPolicy = deliveryPolicy;
    }

    /**
     * Returns the order history for a specific user.
     *
     * @param user The user to get the order history for.
     * @return The order history for the specified user.
     */
    public static List<Order> getHistory(User user) {
        List<Order> userOrders = new ArrayList<>();

        if (user instanceof Manager) {
            // Manager sees all orders
            return new ArrayList<>(orders);
        } else if (user instanceof Restaurant) {
            Restaurant restaurantUser = (Restaurant) user;
            for (Order order : orders) {
                if (order.getRestaurant().equals(restaurantUser)) {
                    userOrders.add(order);
                }
            }
        } else if (user instanceof Customer) {
            Customer customerUser = (Customer) user;
            for (Order order : orders) {
                if (order.getCustomer().equals(customerUser)) {
                    userOrders.add(order);
                }
            }
        } else if (user instanceof Courier) {
            Courier courierUser = (Courier) user;
            for (Order order : orders) {
                if (order.getCourier().equals(courierUser)) {
                    userOrders.add(order);
                }
            }
        }
        return userOrders;
    }

    /**
     * Returns the target profit policy for the system.
     *
     * @return The target profit policy for the system.
     */
	public static TargetProfitPolicy getTargetProfitPolicy() {
		return targetProfitPolicy;
	}

    /**
     * Sets the target profit policy for the system.
     *
     * @param targetProfitPolicy The target profit policy to set.
     */
	public static void setTargetProfitPolicy(TargetProfitPolicy targetProfitPolicy) {
		MyFoodoraSystem.targetProfitPolicy = targetProfitPolicy;
	}


}

