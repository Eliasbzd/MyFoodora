package fr.cs.group12.myFoodora.menu;
/**
 * This class represents a main dish menu item in the MyFoodora system.
 * It inherits from the `MenuItem` class and is specifically designed for main course items.
 */
public class MainDish extends MenuItem {
    /**
     * Constructs a new MainDish menu item with the given specifications.
     *
     * @param name The name of the main dish.
     * @param price The price of the main dish.
     * @param isVegetarian Indicates if the main dish is vegetarian.
     * @param isGlutenFree Indicates if the main dish is gluten-free.
     */
    public MainDish(String name, double price, boolean isVegetarian, boolean isGlutenFree) {
        super(name, price, isVegetarian, isGlutenFree);
    }
}

