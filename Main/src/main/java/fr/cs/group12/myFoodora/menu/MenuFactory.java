package fr.cs.group12.myFoodora.menu;
/**
 * This interface defines a factory for creating menu items in the MyFoodora system.
 * It provides methods to create different types of menu items (starters, main dishes, and desserts).
 */
public interface MenuFactory {
    /**
     * Creates a new starter menu item with the specified details.
     *
     * @param name The name of the starter.
     * @param price The price of the starter.
     * @param isVegetarian Indicates if the starter is vegetarian.
     * @param isGlutenFree Indicates if the starter is gluten-free.
     * @return A new MenuItem object representing the starter.
     */
    MenuItem createStarter(String name, double price, boolean isVegetarian, boolean isGlutenFree);
    /**
     * Creates a new main dish menu item with the specified details.
     *
     * @param name The name of the main dish.
     * @param price The price of the main dish.
     * @param isVegetarian Indicates if the main dish is vegetarian.
     * @param isGlutenFree Indicates if the main dish is gluten-free.
     * @return A new MenuItem object representing the main dish.
     */
    MenuItem createMainDish(String name, double price, boolean isVegetarian, boolean isGlutenFree);

    /**
     * Creates a new dessert menu item with the specified details.
     *
     * @param name The name of the dessert.
     * @param price The price of the dessert.
     * @param isVegetarian Indicates if the dessert is vegetarian.
     * @param isGlutenFree Indicates if the dessert is gluten-free.
     * @return A new MenuItem object representing the dessert.
     */
    MenuItem createDessert(String name, double price, boolean isVegetarian, boolean isGlutenFree);
}
