package fr.cs.group12.myFoodora.user;

import fr.cs.group12.myFoodora.spaceTimeCoordinates.Position;
/**
 * The Courier class represents a user who delivers orders.
 * It extends the User class and includes additional attributes such as surname, phone number,
 * number of delivered orders, duty status, and position.
 */
public class Courier extends User {
    private String surname;
    private String phoneNumber;
    private int deliveredOrders;
    private boolean onDuty;
    private Position position;
    /**
     * Constructs a Courier with the specified attributes.
     *
     * @param name The name of the courier.
     * @param surname The surname of the courier.
     * @param id The ID of the courier.
     * @param username The username of the courier.
     * @param password The password of the courier.
     * @param position The position of the courier.
     * @param phoneNumber The phone number of the courier.
     */
    public Courier(String name, String surname, String id, String username, String password, Position position, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.username = username;
        this.password = password;
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.deliveredOrders = 0;
        this.onDuty = true;
    }
    /**
     * Gets the surname of the courier.
     *
     * @return The surname of the courier.
     */
    public String getSurname() {
        return surname;
    }
    /**
     * Gets the position of the courier.
     *
     * @return The position of the courier.
     */
    public Position getPosition() {
        return position;
    }
    /**
     * Gets the phone number of the courier.
     *
     * @return The phone number of the courier.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Sets the duty status of the courier.
     *
     * @param onDuty True if the courier is on duty, false otherwise.
     */
    public void setOnDuty(boolean onDuty) {
        this.onDuty = onDuty;
    }
    /**
     * Sets the position of the courier.
     *
     * @param newPosition The new position of the courier.
     */
    public void setPosition(Position newPosition) {
        this.position = newPosition;
    }
    /**
     * Accepts a delivery.
     */
    public void acceptDelivery() {
        System.out.println("Delivery accepted by"+name);
        this.deliveredOrders++;
    }
    /**
     * Refuses a delivery.
     */
    public void refuseDelivery() {
        // logic for refusing delivery (not used actually)
        System.out.println("Delivery refused by"+name);

    }
    /**
     * Checks if the courier is on duty.
     *
     * @return True if the courier is on duty, false otherwise.
     */
    public boolean isOnDuty() {
        return onDuty;
    }
    /**
     * Gets the number of delivered orders by the courier.
     *
     * @return The number of delivered orders.
     */
    public int getDeliveredOrders() {
        return deliveredOrders;
    }

    /**
     * Returns a string representation of the courier.
     *
     * @return A string representation of the courier.
     */
    @Override
    public String toString() {
        return String.format("Courier: %s %s, ID: %s, Phone: %s, Delivered Orders: %d, On Duty: %b",
                name, surname, id, phoneNumber, deliveredOrders, onDuty);
    }
}

