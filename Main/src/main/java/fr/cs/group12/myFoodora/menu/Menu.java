package fr.cs.group12.myFoodora.menu;

import java.util.ArrayList;
import java.util.List;
/**
 * This class represents a menu in the MyFoodora system.
 * It manages a collection of menu items categorized as starters, main dishes, and desserts.
 */
public class Menu {
    private List<MenuItem> starters;
    private List<MenuItem> mainDishes;
    private List<MenuItem> desserts;

    /**
     * Constructs a new Menu with empty lists for starters, main dishes, and desserts.
     */
    public Menu() {
        this.starters = new ArrayList<>();
        this.mainDishes = new ArrayList<>();
        this.desserts = new ArrayList<>();
    }
    /**
     * Adds a menu item to the appropriate category (starters, main dishes, or desserts).
     *
     * @param item The menu item to be added.
     *
     * This method checks the type of the `MenuItem` argument and adds it to the corresponding list:
     *  - If the item is a `Starter`, it's added to the `starters` list.
     *  - If the item is a `MainDish`, it's added to the `mainDishes` list.
     *  - If the item is a `Dessert`, it's added to the `desserts` list.
     */
    public void addMenuItem(MenuItem item) {
        if (item instanceof Starter) {
            starters.add(item);
        } else if (item instanceof MainDish) {
            mainDishes.add(item);
        } else if (item instanceof Dessert) {
            desserts.add(item);
        }
    }
    /**
     * Gets the list of starter menu items.
     *
     * @return A list of starter menu items.
     */
    public List<MenuItem> getStarters() {
        return starters;
    }
    /**
     * Gets the list of main dish menu items.
     *
     * @return A list of main dish menu items.
     */
    public List<MenuItem> getMainDishes() {
        return mainDishes;
    }
    /**
     * Gets the list of dessert menu items.
     *
     * @return A list of dessert menu items.
     */
    public List<MenuItem> getDesserts() {
        return desserts;
    }

    /**
     * Gets a list containing all menu items (starters, main dishes, and desserts).
     *
     * @return A list of all menu items.
     */
    public List<MenuItem> getAllItems() {
        List<MenuItem> allItems = new ArrayList<>();
        allItems.addAll(starters);
        allItems.addAll(mainDishes);
        allItems.addAll(desserts);
        return allItems;
    }
    /**
     * Prints the menu items categorized as starters, main dishes, and desserts.
     */
    public void printItems() {
        System.out.println("Starters:");
        for (MenuItem starter : starters) {
            System.out.println(starter.toString());
        }
        System.out.println("Main Dishes:");
        for (MenuItem mainDish : mainDishes) {
            System.out.println(mainDish.toString());
        }
        System.out.println("Desserts:");
        for (MenuItem dessert : desserts) {
            System.out.println(dessert.toString());
        }
    }
}

