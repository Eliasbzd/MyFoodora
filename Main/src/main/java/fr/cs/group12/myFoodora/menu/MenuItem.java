package fr.cs.group12.myFoodora.menu;
/**
 * This abstract class represents a generic menu item in the MyFoodora system.
 * It provides common attributes and methods for all menu items (starters, main dishes, and desserts).
 */
public abstract class MenuItem {
    protected String name;
    protected double price;
    protected boolean isVegetarian;
    protected boolean isGlutenFree;

    /**
     * Constructs a new MenuItem with the specified details.
     *
     * @param name The name of the menu item.
     * @param price The price of the menu item.
     * @param isVegetarian Indicates if the menu item is vegetarian.
     * @param isGlutenFree Indicates if the menu item is gluten-free.
     */
    public MenuItem(String name, double price, boolean isVegetarian, boolean isGlutenFree) {
        this.name = name;
        this.price = price;
        this.isVegetarian = isVegetarian;
        this.isGlutenFree = isGlutenFree;
    }
    /**
     * Gets the name of the menu item.
     *
     * @return The name of the menu item.
     */
    public String getName() {
        return name;
    }
    /**
     * Gets the price of the menu item.
     *
     * @return The price of the menu item.
     */
    public double getPrice() {
        return price;
    }
    /**
     * Checks if the menu item is vegetarian.
     *
     * @return True if the menu item is vegetarian, false otherwise.
     */
    public boolean isVegetarian() {
        return isVegetarian;
    }

    /**
     * Checks if the menu item is gluten-free.
     *
     * @return True if the menu item is gluten-free, false otherwise.
     */
    public boolean isGlutenFree() {
        return isGlutenFree;
    }

    /**
     * Returns a string representation of the menu item in the format:
     * "name (Price: price, Vegetarian: vegetarian, Gluten-Free: glutenFree)".
     *
     * @return A string representation of the menu item.
     */
    @Override
    public String toString() {
        return String.format("%s (Price: %.2f, Vegetarian: %b, Gluten-Free: %b)", 
                              name, price, isVegetarian, isGlutenFree);
    }
}

