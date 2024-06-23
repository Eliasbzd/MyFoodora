package fr.cs.group12.myFoodora.menu;
/**
 * This class represents a starter menu item in the MyFoodora system.
 * It inherits from the `MenuItem` class and is specifically designed for appetizer or starter courses.
 */
public class Starter extends MenuItem {

    /**
     * Constructs a new Starter menu item with the specified details.
     *
     * @param name The name of the starter.
     * @param price The price of the starter.
     * @param isVegetarian Indicates if the starter is vegetarian.
     * @param isGlutenFree Indicates if the starter is gluten-free.
     */
    public Starter(String name, double price, boolean isVegetarian, boolean isGlutenFree) {
        super(name, price, isVegetarian, isGlutenFree);
    }
}

