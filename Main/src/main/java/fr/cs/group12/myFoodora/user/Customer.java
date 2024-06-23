package fr.cs.group12.myFoodora.user;

import java.util.ArrayList;
import java.util.List;

import fr.cs.group12.myFoodora.fidelityCard.BasicFidelityCard;
import fr.cs.group12.myFoodora.fidelityCard.FidelityCard;
import fr.cs.group12.myFoodora.fidelityCard.PointFidelityCard;
import fr.cs.group12.myFoodora.spaceTimeCoordinates.Position;
/**
 * The Customer class represents a user who places orders.
 * It extends the User class and includes additional attributes such as surname, address, email, phone number,
 * fidelity card, notification preferences, and notifications and unread notifications that pop up after login.
 * It also implements the Observer interface to receive notifications.
 */
public class Customer extends User implements Observer {
    private String surname;
    private Position address;
    private String email;
    private String phoneNumber;
    private FidelityCard fidelityCard;
    private boolean notifySpecialOffers;
    private List<String> notifications;
    private List<String> unreadNotifications;

    /**
     * Constructs a Customer with the specified attributes and fidelity card.
     *
     * @param name The name of the customer.
     * @param surname The surname of the customer.
     * @param id The ID of the customer.
     * @param username The username of the customer.
     * @param password The password of the customer.
     * @param address The address of the customer.
     * @param email The email of the customer.
     * @param phoneNumber The phone number of the customer.
     * @param fidelityCard The fidelity card of the customer.
     */
    public Customer(String name, String surname, String id, String username, String password, Position address, String email, String phoneNumber, FidelityCard fidelityCard) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.fidelityCard = fidelityCard;
        this.notifySpecialOffers = false;
        this.notifications = new ArrayList<String>();
        this.unreadNotifications=new ArrayList<String>();
    }
    /**
     * Constructs a Customer with the specified attributes and a basic fidelity card.
     *
     * @param name The name of the customer.
     * @param surname The surname of the customer.
     * @param id The ID of the customer.
     * @param username The username of the customer.
     * @param password The password of the customer.
     * @param address The address of the customer.
     * @param email The email of the customer.
     * @param phoneNumber The phone number of the customer.
     */
    public Customer(String name, String surname, String id, String username, String password, Position address, String email, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.fidelityCard = new BasicFidelityCard();
        this.notifySpecialOffers = false;
        this.notifications = new ArrayList<String>();
        this.unreadNotifications=new ArrayList<String>();
    }
    /**
     * Gets the address of the customer.
     *
     * @return The address of the customer.
     */
    public Position getAddress() {
        return address;
    }
    /**
     * Gets the fidelity card of the customer.
     *
     * @return The fidelity card of the customer.
     */
    public FidelityCard getFidelityCard() {
        return fidelityCard;
    }
    /**
     * Gets the surname of the customer.
     *
     * @return The surname of the customer.
     */
    public String getSurname() {
        return surname;
    }
    /**
     * Gets the email of the customer.
     *
     * @return The email of the customer.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Gets the phone number of the customer.
     *
     * @return The phone number of the customer.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Checks if the customer is notified about special offers.
     *
     * @return True if the customer is notified, false otherwise.
     */
    public boolean isNotifySpecialOffers() {
        return notifySpecialOffers;
    }
    /**
     * Sets the surname of the customer.
     *
     * @param surname The new surname of the customer.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    /**
     * Sets the address of the customer.
     *
     * @param address The new address of the customer.
     */
    public void setAddress(Position address) {
        this.address = address;
    }
    /**
     * Sets the email of the customer.
     *
     * @param email The new email of the customer.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Sets the phone number of the customer.
     *
     * @param phoneNumber The new phone number of the customer.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * Sets the fidelity card of the customer.
     *
     * @param fidelityCard The new fidelity card of the customer.
     */
    public void setFidelityCard(FidelityCard fidelityCard) {
        this.fidelityCard = fidelityCard;
    }
    /**
     * Enables notifications about special offers for the customer.
     */
    public void giveConsensusForSpecialOffers() {
        this.notifySpecialOffers = true;
    }
    /**
     * Disables notifications about special offers for the customer.
     */
    public void removeConsensusForSpecialOffers() {
        this.notifySpecialOffers = false;
    }

    public void unregisterFidelityCard() {
        this.fidelityCard = new BasicFidelityCard();
    }
    /**
     * Gets the points earned by the customer through the fidelity card.
     *
     * @return The points earned by the customer.
     */
    public int getPoints() {
        if (this.fidelityCard  instanceof PointFidelityCard){
            return ((PointFidelityCard)this.fidelityCard).getPoints();
        }
        return 0;
    }

    /**
     * Gets the list of notifications received by the customer.
     *
     * @return The list of notifications received by the customer.
     */
    public List<String> getNotifications() {
        return notifications;
    }
    /**
     * Gets the list of unread notifications received by the customer.
     *
     * @return The list of unread notifications received by the customer.
     */
    public List<String> getUnreadNotifications() {
        return unreadNotifications;
    }

    /**
     * Sets the list of notifications received by the customer.
     *
     * @param notifications The list of notifications received by the customer.
     */
    public void setNotifications(List<String> notifications) {
        this.notifications = notifications;
    }
    /**
     * Sets the list of unread notifications received by the customer.
     *
     * @param unreadNotifications The list of unread notifications received by the customer.
     */
    public void setUnreadNotifications(List<String> unreadNotifications) {
        this.unreadNotifications = unreadNotifications;
    }
    /**
     * Updates the customer with a notification message and adds it to unread notifications and notifications.
     *
     * @param message The notification message.
     */
    @Override
    public void update(String message) {
        System.out.println("Customer " + name + " will be notified when he login: " + message);
        unreadNotifications.add(message);
        notifications.add(message);
    }
    /**
     * Returns a string representation of the customer.
     *
     * @return A string representation of the customer.
     */
    @Override
    public String toString() {
        return String.format("Customer: %s %s, ID: %s, Email: %s, Phone: %s, Address: %s, Notify Special Offers: %b",
                name, surname, id, email, phoneNumber, address, notifySpecialOffers);
    }

}

