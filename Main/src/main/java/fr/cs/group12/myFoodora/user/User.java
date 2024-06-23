package fr.cs.group12.myFoodora.user;

import java.util.List;

import fr.cs.group12.myFoodora.myFoodora.MyFoodoraSystem;
import fr.cs.group12.myFoodora.order.Order;
/**
 * The User class represents a generic user within the myFoodora system.
 * This class serves as the parent class for more specific user types such as Customer, Courier, Manager, and Restaurant.
 */
public abstract class User {

    protected String name;
    protected String id;
    protected String username;
    protected String password;
    protected boolean active=true;

    /**
     * Retrieves the name of the user.
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the ID of the user.
     * @return The ID of the user.
     */
    public String getId() {
        return id;
    }

    /**
     * Retrieves the username of the user.
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Retrieves the password of the user.
     * Note: Consider returning a hashed version of the password for security reasons.
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Checks if the user is currently active.
     * @return true if the user is active, otherwise false.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Retrieves the order history of the user.
     * This method is abstract and should be implemented by subclasses to provide specific functionality based on user type.
     * @param user The user for whom to retrieve the order history.
     * @return The list of orders in the user's history.
     */
    public  List<Order> getHistory(User user){
        return MyFoodoraSystem.getHistory(user);
     }
}

