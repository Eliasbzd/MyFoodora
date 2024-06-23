package fr.cs.group12.myFoodora.menu;
/**
 * This class represents a dessert menu item in the MyFoodora system.
 * It inherits from the `MenuItem` class and is specifically designed for dessert items.
 */
public class Dessert extends MenuItem {
    /**
     * Constructs a new Dessert menu item with the given specifications.
     *
     * @param name The name of the dessert.
     * @param price The price of the dessert.
     * @param isVegetarian Indicates if the dessert is vegetarian.
     * @param isGlutenFree Indicates if the dessert is gluten-free.
     */
    public Dessert(String name, double price, boolean isVegetarian, boolean isGlutenFree) {
        super(name, price, isVegetarian, isGlutenFree);
    }
}

