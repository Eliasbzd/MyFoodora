package fr.cs.group12.myFoodora.user;
/**
 * The Observer interface represents an object that observes (customer) changes in an observable object.
 * It defines a method for updating observers with a message.
 */
public interface Observer {
    /**
     * Updates the observer with a message and update their notifications lists.
     *
     * @param message The message to be sent to the observer.
     */
    void update(String message);
}

