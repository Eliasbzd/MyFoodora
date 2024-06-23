package fr.cs.group12.myFoodora.test;



import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import fr.cs.group12.myFoodora.menu.Menu;
import fr.cs.group12.myFoodora.menu.MenuItem;
import java.util.List;
import fr.cs.group12.myFoodora.menu.MainDish;
import fr.cs.group12.myFoodora.menu.Starter;
import fr.cs.group12.myFoodora.user.Restaurant;
import fr.cs.group12.myFoodora.menu.*;

/**
 * This class tests the functionality of the Menu class in the MyFoodora system.
 * It ensures that menu items (starters, main dishes, and desserts) are correctly added to the menu
 * and can be retrieved as expected.
 */
public class MenuTest {
    private Menu menu;
    @Before
    public void setUp() {
        menu = new Menu();
    }

    @Test
    public void testAddStarterMenu() {
        MenuItem starterItem1 = new Starter("Salad", 8.99, false, true);
        MenuItem starterItem2 = new Starter("Soup", 7.49, false, true);
        
        menu.addMenuItem(starterItem1);
        menu.addMenuItem(starterItem2);
        
        List<MenuItem> starters = menu.getStarters();
        
        assertTrue(starters.contains(starterItem1));
        assertTrue(starters.contains(starterItem2));
        assertEquals(2, starters.size());
    }

    @Test
    public void testAddDessertMenu() {
        MenuItem dessertItem1 = new Dessert("Cake", 5.99, true, false);
        MenuItem dessertItem2 = new Dessert("Ice Cream", 4.49, true, false);
        
        menu.addMenuItem(dessertItem1);
        menu.addMenuItem(dessertItem2);
        
        List<MenuItem> desserts = menu.getDesserts();
        
        assertTrue(desserts.contains(dessertItem1));
        assertTrue(desserts.contains(dessertItem2));
        assertEquals(2, desserts.size());
    }

    @Test
    public void testAddMainDishMenu() {
        MenuItem mainDishItem1 = new MainDish("Pasta", 12.99, true, true);
        MenuItem mainDishItem2 = new MainDish("Burger", 10.49, false, true);
        
        menu.addMenuItem(mainDishItem1);
        menu.addMenuItem(mainDishItem2);
        
        List<MenuItem> mainDishes = menu.getMainDishes();
        
        assertTrue(mainDishes.contains(mainDishItem1));
        assertTrue(mainDishes.contains(mainDishItem2));
        assertEquals(2, mainDishes.size());
    }
}