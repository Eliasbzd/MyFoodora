package fr.cs.group12.myFoodora.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import fr.cs.group12.myFoodora.meal.Meal;
import fr.cs.group12.myFoodora.meal.MealFactory;
import fr.cs.group12.myFoodora.meal.SpecialMeal;
import fr.cs.group12.myFoodora.menu.Menu;
import fr.cs.group12.myFoodora.menu.MenuFactory;
import fr.cs.group12.myFoodora.menu.MenuItem;
import fr.cs.group12.myFoodora.myFoodora.MyFoodoraSystem;
import fr.cs.group12.myFoodora.spaceTimeCoordinates.Position;
/**
 * The Restaurant class represents a restaurant user in the MyFoodora system.
 * It extends the User class and implements the Observable interface.
 * This class manages the restaurant's menu, meals, discounts, and special offers.
 */
public class Restaurant extends User implements Observable {
    private Position location;
    private Menu menu;
    private List<Meal> meals;
    private MenuFactory menuFactory;
    private MealFactory mealFactory;
    private double genericDiscountFactor = 0.05;
    private double specialDiscountFactor = 0.10;
    private Meal mealOfTheWeek;
    private static MyFoodoraSystem myFoodoraSystem;

    /**
     * Constructs a Restaurant with the specified attributes.
     *
     * @param name The name of the restaurant.
     * @param id The ID of the restaurant.
     * @param username The username of the restaurant.
     * @param password The password of the restaurant.
     * @param location The location of the restaurant.
     * @param menuFactory The factory for creating menu items.
     * @param mealFactory The factory for creating meals.
     * @param myFoodoraSystem The MyFoodora system.
     */
    public Restaurant(String name, String id, String username, String password, Position location, MenuFactory menuFactory, MealFactory mealFactory,MyFoodoraSystem myFoodoraSystem ) {
        this.name = name;
        this.id = id;
        this.username = username;
        this.password = password;
        this.location = location;
        this.menu = new Menu();
        this.meals = new ArrayList<>();
        this.menuFactory = menuFactory;
        this.mealFactory = mealFactory;
        Restaurant.myFoodoraSystem = myFoodoraSystem;
    }

    /**
     * Gets the location of the restaurant.
     *
     * @return The location of the restaurant.
     */
    public Position getLocation() {
        return location;
    }

    /**
     * Gets the generic discount factor for the restaurant.
     *
     * @return The generic discount factor.
     */
    public double getGenericDiscountFactor() {
        return genericDiscountFactor;
    }

    /**
     * Gets the special discount factor for the restaurant.
     *
     * @return The special discount factor.
     */
    public double getSpecialDiscountFactor() {
        return specialDiscountFactor;
    }

    /**
     * Gets the menu of the restaurant.
     *
     * @return The menu.
     */
    public List<MenuItem> getMenu() {
        return menu.getAllItems();
    }

    /**
     * Gets the meals offered by the restaurant.
     *
     * @return The list of meals.
     */
    public List<Meal> getMeals() {
        return meals;
    }
    /**
     * Gets the meal of the week for the restaurant.
     *
     * @return The meal of the week.
     */
    public Meal getMealOfTheWeek() {
        return mealOfTheWeek;
    }

    /**
     * Sets the generic discount factor for the restaurant.
     *
     * @param discountFactor The generic discount factor to set.
     */
    public void setGenericDiscountFactor(double discountFactor) {
        this.genericDiscountFactor = discountFactor;
        if (!meals.isEmpty()){
            for (Meal meal:meals){
                meal.setDiscountFactor(discountFactor);
            }
        }
    }

    /**
     * Sets the special discount factor for the restaurant.
     *
     * @param discountFactor The special discount factor to set.
     */
    public void setSpecialDiscountFactor(double discountFactor) {
        this.specialDiscountFactor = discountFactor;
        if (this.mealOfTheWeek != null) {
            mealOfTheWeek.setDiscountFactor(discountFactor);
        }
    }

    /**
     * Sets the meal of the week for the restaurant.
     *
     * @param meal The meal of the week to set.
     */
    public void setMealOfTheWeek(Meal meal) {
        this.mealOfTheWeek = meal;

        if (meal == null) {
            String offerRemoved = "Special offer was removed";
            this.notifyObservers(offerRemoved);
        } else {
            List<MenuItem> items = meal.getItems();
            String name = meal.getName();
            mealOfTheWeek = new SpecialMeal(name, items, specialDiscountFactor);
            String offer = this.name + " has a new meal of the week!";
            this.notifyObservers(offer);
        }
    }

    /**
     * Adds a menu item to the restaurant's menu.
     *
     * @param type The type of the menu item.
     * @param name The name of the menu item.
     * @param price The price of the menu item.
     * @param isVegetarian Whether the menu item is vegetarian.
     * @param isGlutenFree Whether the menu item is gluten-free.
     */
    public void addMenuItem(String type, String name, double price, boolean isVegetarian, boolean isGlutenFree) {
        MenuItem item = null;
        switch (type.toLowerCase()) {
            case "starter":
                item = menuFactory.createStarter(name, price, isVegetarian, isGlutenFree);
                break;
            case "maindish":
                item = menuFactory.createMainDish(name, price, isVegetarian, isGlutenFree);
                break;
            case "dessert":
                item = menuFactory.createDessert(name, price, isVegetarian, isGlutenFree);
                break;
        }
        if (item != null) {
            menu.addMenuItem(item);
        }
    }

    /**
     * Creates a meal for the restaurant.
     *
     * @param name The name of the meal.
     * @param mealType The type of the meal (e.g., halfmeal, fullmeal).
     * @param items The menu items included in the meal.
     */
    public void createMeal(String name,String mealType, MenuItem... items) {
        Meal meal = null;
        switch (mealType.toLowerCase()) {
            case "halfmeal":
                meal = mealFactory.createHalfMeal(name,items[0], items[1]);
                break;
            case "fullmeal":
                meal = mealFactory.createFullMeal(name,items[0], items[1], items[2]);
                break;
        }
        if (meal != null) {
            meals.add(meal);
        }
    }

    /**
     * Removes a menu item from the restaurant's menu by name.
     *
     * @param name The name of the menu item to remove.
     */
    public void removeMenuItem(String name) {
        for (List<MenuItem> itemList : Arrays.asList(menu.getStarters(), menu.getMainDishes(), menu.getDesserts())) {
            for (Iterator<MenuItem> iterator = itemList.iterator(); iterator.hasNext();) {
                MenuItem item = iterator.next();
                if (item.getName().equals(name)) {
                    iterator.remove();
                    return; // exit loop once item is removed
                }
            }
        }
    }

    /**
     * Removes a menu item from the restaurant's menu.
     *
     * @param item The menu item to remove.
     */
    public void removeMenuItem(MenuItem item) {
        menu.getStarters().remove(item);
        menu.getMainDishes().remove(item);
        menu.getDesserts().remove(item);
    }

    /**
     * Removes a meal from the restaurant's menu.
     *
     * @param meal The meal to remove.
     */
    public void removeMeal(Meal meal) {
        meals.remove(meal);
    }

    /**
     * This method displays the ordered shipped orders based on the shipped order sorting policy
     *
     */
    public static void showOrderedShippedOrder(){
        MyFoodoraSystem.showOrderedShippedOrder();
    }
    /**
     * Prints the menu of the restaurant.
     */
    public void printMenu() {
        System.out.println("Menu:");
        menu.printItems();
    }

    /**
     * Prints the list of meals offered by the restaurant.
     */
    public void printMeals() {
        System.out.println("Meals:");
        for (Meal meal : meals) {
            System.out.println(meal.toString());
        }
    }
/**
 * Prints the meal of the week offered by the restaurant.
 */
    public void printMealOfTheWeek() {
        if (mealOfTheWeek != null) {
            System.out.println("Meal of the Week:");
            System.out.println(mealOfTheWeek.toString());
        } else {
            System.out.println("No Meal of the Week set.");
        }
    }

    /**
     * Notifies all interested customers about special offers.
     *
     * @param offer The special offer to notify customers about.
     */
    @Override
    public void notifyObservers(String offer) {
        List<Customer> interestedCustomers = MyFoodoraSystem.getCustomers().stream()
                .filter(Customer::isNotifySpecialOffers)
                .collect(Collectors.toList());

        for (Customer customer : interestedCustomers) {
            customer.update(offer);
        }
    }

    /**
     * Returns a string representation of the restaurant, including its name, location, menu items,
     * meals, and the meal of the week.
     *
     * @return A string representation of the restaurant.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Restaurant Name: ").append(getName()).append("\n");

        sb.append("Location: ").append(getLocation()).append("\n");

        List<MenuItem> menuItems = getMenu();
        if (!menuItems.isEmpty()) {
            sb.append("Menu:\n");
            for (MenuItem item : menuItems) {
                sb.append("- ").append(item.toString()).append("\n");
            }
        } else {
            sb.append("No menu items available.\n");
        }


        if (meals.size() > 0) {
            sb.append("Meals:\n");
            for (Meal meal : meals) {
                sb.append("- ").append(meal.toString()).append("\n");
            }
        } else {
            sb.append("No meals currently available.\n");
        }


        if (mealOfTheWeek != null) {
            sb.append("Meal of the Week:\n");
            sb.append(mealOfTheWeek.toString()).append("\n");
        } else {
            sb.append("No meal of the weak currently available.\n");
        }
            return sb.toString();
        }

    }


