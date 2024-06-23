package fr.cs.group12.myFoodora.user;
/**
 * The Observable interface represents an object (restaurant) that can be observed by other objects.
 * It defines a method for notifying observers.
 */
public interface Observable {
    /**
     * Notifies observers with a message.
     *
     * @param message The message to be sent to observers.
     */
    void notifyObservers(String message);
}

