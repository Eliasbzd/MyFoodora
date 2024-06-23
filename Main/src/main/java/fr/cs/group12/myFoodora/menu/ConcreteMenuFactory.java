package fr.cs.group12.myFoodora.menu;
/**
 * The ConcreteMenuFactory class implements the MenuFactory interface
 * and provides specific methods to create menu items such as starters,
 * main dishes, and desserts.
 */
public class ConcreteMenuFactory implements MenuFactory {
    @Override
    public MenuItem createStarter(String name, double price, boolean isVegetarian, boolean isGlutenFree) {
        return new Starter(name, price, isVegetarian, isGlutenFree);
    }

    @Override
    public MenuItem createMainDish(String name, double price, boolean isVegetarian, boolean isGlutenFree) {
        return new MainDish(name, price, isVegetarian, isGlutenFree);
    }

    @Override
    public MenuItem createDessert(String name, double price, boolean isVegetarian, boolean isGlutenFree) {
        return new Dessert(name, price, isVegetarian, isGlutenFree);
    }
}

