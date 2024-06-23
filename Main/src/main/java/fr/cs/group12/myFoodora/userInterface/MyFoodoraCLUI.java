package fr.cs.group12.myFoodora.userInterface;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.*;
import java.util.Scanner;
import java.util.StringTokenizer;

import fr.cs.group12.myFoodora.deliveryPolicy.DeliveryPolicy;
import fr.cs.group12.myFoodora.deliveryPolicy.FairOccupationDelivery;
import fr.cs.group12.myFoodora.deliveryPolicy.FastestDelivery;
import fr.cs.group12.myFoodora.fidelityCard.BasicFidelityCard;
import fr.cs.group12.myFoodora.fidelityCard.FidelityCard;
import fr.cs.group12.myFoodora.fidelityCard.LotteryFidelityCard;
import fr.cs.group12.myFoodora.fidelityCard.PointFidelityCard;
import fr.cs.group12.myFoodora.meal.ConcreteMealFactory;
import fr.cs.group12.myFoodora.meal.Meal;
import fr.cs.group12.myFoodora.meal.MealFactory;
import fr.cs.group12.myFoodora.menu.ConcreteMenuFactory;
import fr.cs.group12.myFoodora.menu.MenuFactory;
import fr.cs.group12.myFoodora.menu.MenuItem;
import fr.cs.group12.myFoodora.myFoodora.MyFoodoraSystem;
import fr.cs.group12.myFoodora.order.Order;
import fr.cs.group12.myFoodora.spaceTimeCoordinates.Position;
import fr.cs.group12.myFoodora.spaceTimeCoordinates.Time;
import fr.cs.group12.myFoodora.targetProfitPolicy.TargetProfitDeliveryCost;
import fr.cs.group12.myFoodora.targetProfitPolicy.TargetProfitMarkup;
import fr.cs.group12.myFoodora.targetProfitPolicy.TargetProfitPolicy;
import fr.cs.group12.myFoodora.targetProfitPolicy.TargetProfitServiceFee;
import fr.cs.group12.myFoodora.user.Courier;
import fr.cs.group12.myFoodora.user.Customer;
import fr.cs.group12.myFoodora.user.Manager;
import fr.cs.group12.myFoodora.user.Restaurant;
import fr.cs.group12.myFoodora.user.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class represents a command-line user interface (CLUI) for the MyFoodora system.
 * It allows users to interact with the system by entering commands.
 */
public class MyFoodoraCLUI {
    private MyFoodoraSystem myFoodoraSystem;
    private User currentUser;


    /**
     * Sets up the system by reading configuration from the provided file path and creating instances of restaurants,
     * customers, and couriers based on the configuration.
     *
     * @param initpath the file path to the initialization configuration file
     * @throws NumberFormatException if a number format is invalid in the configuration file
     */
    public void setup(String initpath) {
        try (BufferedReader br = new BufferedReader(new FileReader(initpath))) {
            String line;
            int numRestaurants = 0;
            int numCustomers = 0;
            int numCouriers = 0;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, "=");
                if (st.countTokens() < 2) {
                    continue;
                }
                String key = st.nextToken().trim();
                String value = st.nextToken().trim();

                switch (key) {
                    case "numRestaurants":
                        numRestaurants = Integer.parseInt(value);
                        break;
                    case "numCustomers":
                        numCustomers = Integer.parseInt(value);
                        break;
                    case "numCouriers":
                        numCouriers = Integer.parseInt(value);
                        break;
                    default:
                        break;
                }
            }

            Random random = new Random();
            double side = 100.0; // Define the side length of the square surface

            // Create Restaurants
            for (int i = 0; i < numRestaurants; i++) {
                String name = "Restaurant" + (i + 1);
                String username = "restaurant" + (i + 1);
                String password = "password" + (i + 1);
                double x = random.nextDouble() * side;
                double y = random.nextDouble() * side;
                Position position = new Position(x, y);
                MenuFactory menuFactory = new ConcreteMenuFactory();
                MealFactory mealFactory = new ConcreteMealFactory();
                Restaurant restaurant = new Restaurant(name, username, username, password, position, menuFactory, mealFactory, myFoodoraSystem);
                MyFoodoraSystem.addUser(restaurant);
                restaurant.addMenuItem("starter", "CaesarSalad" , 5.99 + i, true, false); //0
                restaurant.addMenuItem("starter", "BuffaloWings", 6.49, false, true);
                restaurant.addMenuItem("starter", "Soup", 4.59, true, false);
                restaurant.addMenuItem("starter", "Bruschetta", 6.99, true, false); //3
                restaurant.addMenuItem("starter", "Calamari", 7.49, false, true);
                restaurant.addMenuItem("maindish", "GrilledChicken" + (i + 1), 12.99 + i, false, true); //5
                restaurant.addMenuItem("maindish", "Pizza" , 8.99 + i, false, true);
                restaurant.addMenuItem("maindish", "Burger" , 7.99 + i, false, true); 
                restaurant.addMenuItem("maindish", "Lasagna" , 9.99 + i, false, true);//8
                restaurant.addMenuItem("maindish", "VegetarianLasagna", 9.99, true, false);
                restaurant.addMenuItem("maindish", "VeggieBurger", 7.99, true, true); //10
                restaurant.addMenuItem("dessert", "ChocolateCake" , 4.99 + i, true, false);
                restaurant.addMenuItem("dessert", "IceCream", 3.99 + i, true, false);
                restaurant.addMenuItem("dessert", "Tiramisu" , 5.49 + i, true, false);
                restaurant.addMenuItem("dessert", "CheeseCake" , 4.49 + i, true, false);
                restaurant.addMenuItem("dessert", "Chocolotamousse" , 4.49 + i, false, true); //15
                List<MenuItem> menuItems = restaurant.getMenu();
                restaurant.createMeal("HalfMeal", "halfmeal", menuItems.get(1), menuItems.get(5));
                restaurant.createMeal("FullMeal", "fullmeal", menuItems.get(4), menuItems.get(5), menuItems.get(15));
                restaurant.createMeal("FullMealVegetarian", "fullmeal", menuItems.get(2), menuItems.get(9), menuItems.get(12));
            }


            // Create Customers
            for (int i = 0; i < numCustomers; i++) {
                String firstName = "Customer" + (i + 1);
                String lastName = "LastName" + (i + 1);
                String username = "customer" + (i + 1);
                String password = "password" + (i + 1);
                double x = random.nextDouble() * side;
                double y = random.nextDouble() * side;
                Position position = new Position(x, y);
                Customer customer = new Customer(firstName, lastName, username, username, password, position, "", "");
                MyFoodoraSystem.addUser(customer);
            }

            // Create Couriers
            for (int i = 0; i < numCouriers; i++) {
                String firstName = "Courier" + (i + 1);
                String lastName = "LastName" + (i + 1);
                String username = "courier" + (i + 1);
                String password = "password" + (i + 1);
                double x = random.nextDouble() * side;
                double y = random.nextDouble() * side;
                Position position = new Position(x, y);
                Courier courier = new Courier(firstName, lastName, username, username, password, position, "");
                MyFoodoraSystem.addUser(courier);
            }

            System.out.println("Setup completed with " + numRestaurants + " restaurants, " + numCustomers + " customers, and " + numCouriers + " couriers.");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in the configuration file.");
        }
    }
    /**
     * Sets up the CLUI by creating a specified number of restaurants,
     * customers, and couriers.
     *
     * @param tokenizer A StringTokenizer containing the number of restaurants,
     *        customers, and couriers (3 tokens expected).
     */
    public void setup(StringTokenizer tokenizer) {
        if (tokenizer.countTokens() != 3) {
            System.out.println("Usage: setup <numRestaurants> <numCustomers> <numCouriers>");
            return;
        }

        try {
            int numRestaurants = Integer.parseInt(tokenizer.nextToken());
            int numCustomers = Integer.parseInt(tokenizer.nextToken());
            int numCouriers = Integer.parseInt(tokenizer.nextToken());

            Random random = new Random();
            double side = 100.0; // Define the side length of the square surface

            // Create Restaurants
            for (int i = 0; i < numRestaurants; i++) {
                String name = "RestaurantSetup" + (i + 1);
                String username = "restaurantsetup" + (i + 1);
                String password = "password" + (i + 1);
                double x = random.nextDouble() * side;
                double y = random.nextDouble() * side;
                Position position = new Position(x, y);
                MenuFactory menuFactory = new ConcreteMenuFactory();
                MealFactory mealFactory = new ConcreteMealFactory();
                Restaurant restaurant = new Restaurant(name, username, username, password, position, menuFactory, mealFactory, myFoodoraSystem);
                MyFoodoraSystem.addUser(restaurant);
                restaurant.addMenuItem("starter", "CaesarSalad" + (i + 1), 5.99 + i, true, false);
                restaurant.addMenuItem("starter", "BuffaloWings", 6.49, false, true);
                restaurant.addMenuItem("starter", "Soup", 4.59, true, false);
                restaurant.addMenuItem("starter", "Bruschetta", 6.99, true, false);
                restaurant.addMenuItem("starter", "Calamari", 7.49, false, true);
                restaurant.addMenuItem("maindish", "GrilledChicken" + (i + 1), 12.99 + i, false, true);
                restaurant.addMenuItem("maindish", "Pizza" + (i + 1), 8.99 + i, false, true);
                restaurant.addMenuItem("maindish", "Burger" + (i + 1), 7.99 + i, false, true);
                restaurant.addMenuItem("maindish", "Lasagna" + (i + 1), 9.99 + i, false, true);
                restaurant.addMenuItem("maindish", "VegetarianLasagna", 9.99, true, true);
                restaurant.addMenuItem("maindish", "VeggieBurger", 7.99, true, true);
                restaurant.addMenuItem("dessert", "ChocolateCake" + (i + 1), 4.99 + i, true, false);
                restaurant.addMenuItem("dessert", "IceCream" + (i + 1), 3.99 + i, true, false);
                restaurant.addMenuItem("dessert", "Tiramisu" + (i + 1), 5.49 + i, true, false);
                restaurant.addMenuItem("dessert", "CheeseCake" + (i + 1), 4.49 + i, true, false);
                restaurant.addMenuItem("dessert", "Chocolotamousse" + (i + 1), 4.49 + i, false, true);
                List<MenuItem> menuItems = restaurant.getMenu();
                restaurant.createMeal("HalfMeal" + (i + 1), "halfmeal", menuItems.get(1), menuItems.get(5));
                restaurant.createMeal("FullMeal" + (i + 1), "fullmeal", menuItems.get(4), menuItems.get(5), menuItems.get(15));
                restaurant.createMeal("FullMealVegetarian" + (i + 1), "fullmeal", menuItems.get(2), menuItems.get(9), menuItems.get(1));
            }

            // Create Customers
            for (int i = 0; i < numCustomers; i++) {
                String firstName = "CustomerSetup" + (i + 1);
                String lastName = "LastName" + (i + 1);
                String username = "customersetup" + (i + 1);
                String password = "password" + (i + 1);
                double x = random.nextDouble() * side;
                double y = random.nextDouble() * side;
                Position position = new Position(x, y);
                Customer customer = new Customer(firstName, lastName, username, username, password, position, "", "");
                MyFoodoraSystem.addUser(customer);
            }

            // Create Couriers
            for (int i = 0; i < numCouriers; i++) {
                String firstName = "CourierSetup" + (i + 1);
                String lastName = "LastName" + (i + 1);
                String username = "couriersetup" + (i + 1);
                String password = "password" + (i + 1);
                double x = random.nextDouble() * side;
                double y = random.nextDouble() * side;
                Position position = new Position(x, y);
                Courier courier = new Courier(firstName, lastName, username, username, password, position, "");
                MyFoodoraSystem.addUser(courier);
            }

            System.out.println("Setup completed with " + numRestaurants + " restaurants, " + numCustomers + " customers, and " + numCouriers + " couriers.");

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Usage: setup <numRestaurants> <numCustomers> <numCouriers>");
        } catch (Exception e) {
            System.out.println("An error occurred during setup: " + e.getMessage());
        }
    }
    /**
     * Starts the CLUI and allows the user to interact with the system.
     */
    public void start() {
        Manager ceo = new Manager("ceo", "ceo", "1", "ceo", "123456789", myFoodoraSystem);
        Manager deputy = new Manager("deputy", "deputy", "1", "deputy", "123456789", myFoodoraSystem);
        MyFoodoraSystem.addUser(ceo);
        MyFoodoraSystem.addUser(deputy);

        System.setProperty("user.dir", Paths.get("build").toAbsolutePath().toString());

        String initpath ="./eval/my_foodora.ini";
        setup(initpath);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to MyFoodora CLUI. Type 'help' for a list of commands.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            try {
                executeCommand(input);
            } catch (Exception e) {
                System.out.println("Invalid command / input. Type 'help' for a list of commands.");
            }
        }

    }
    /**
     * Executes a command input by the user.
     *
     * @param input The command input by the user.
     */
    public void executeCommand(String input) {
        StringTokenizer tokenizer = new StringTokenizer(input, " ");
        if (!tokenizer.hasMoreTokens()) {
            System.out.println("Invalid command. Type 'help' for a list of commands.");
            return;
        }

        String command = tokenizer.nextToken();
        switch (command) {
            case "setup":
                setup(tokenizer);
                break;
            case "login":
                login(tokenizer);
                break;
            case "logout":
                logout();
                break;
            case "showHistory":
                showHistory();
                break;
            case "registerRestaurant":
                registerRestaurant(tokenizer);
                break;
            case "registerCustomer":
                registerCustomer(tokenizer);
                break;
            case "registerCourier":
                registerCourier(tokenizer);
                break;
            case "addDishRestaurantMenu":
                addDishRestaurantMenu(tokenizer);
                break;
            case "createMeal":
                createMeal(tokenizer);
                break;
            case "showMeals":
                showMeals();
                break;
            case "showMeal":
                showMeal(tokenizer);
                break;
            case "setSpecialOffer":
                setSpecialOffer(tokenizer);
                break;
            case "removeSpecialOffer":
                removeSpecialOffer();
                break;
            case "giveConsensusNotification":
                giveConsensusNotification();
                break;
            case "removeConsensusNotification":
                removeConsensusNotification();
                break;

            case "showNotification":
                showNotification();
                break;
            case "showPoints":
                showPoints();
                break;
            case "createOrder":
                createOrder(tokenizer);
                break;
            case "addItem2Order":
                addItem2Order(tokenizer);
                break;
            case "endOrder":
                endOrder(tokenizer);
                break;
            case "onDuty":
                onDuty();
                break;
            case "offDuty":
                offDuty();
                break;
            case "changeLocation":
                changeLocation(tokenizer);
                break;
            case "findDeliverer":
                findDeliverer(tokenizer);
                break;
            case "setDeliveryPolicy":
                setDeliveryPolicy(tokenizer);
                break;
            case "setProfitPolicy":
                setProfitPolicy(tokenizer);
                break;
            case "applyProfitPolicy":
            	applyProfitPolicy(tokenizer);
            case "associateCard":
                associateCard(tokenizer);
                break;
            case "showCourierDeliveries":
                showCourierDeliveries();
                break;
            case "showRestaurantTop":
                showRestaurantTop();
                break;
            case "showCustomers":
                showCustomers();
                break;
            case "showMenuItem":
                showMenuItem(tokenizer);
                break;
            case "showTotalProfit":
                showTotalProfit();
                break;
            case "showTotalProfitPeriod":
                showTotalProfitPeriod(tokenizer);
                break;
            case "showAverageIncomePerCustomer":
                showAverageIncomePerCustomer();
                break;
            case "showAverageIncomePerCustomerPeriod":
                showAverageIncomePerCustomerPeriod(tokenizer);
                break;
            case "runTest":
                runTest(tokenizer);
                break;
            case "help":
                help();
                break;
            default:
                System.out.println("Invalid command. Type 'help' for a list of commands.");
                break;
        }
    }
    /**
     * Logs a user into the system.
     *
     * @param tokenizer A StringTokenizer containing the username and password (2 tokens expected).
     */
    public void login(StringTokenizer tokenizer) {
        if (tokenizer.countTokens() != 2) {
            System.out.println("Usage: login <username> <password>");
            return;
        }
        String username = tokenizer.nextToken();
        String password = tokenizer.nextToken();
        User user = MyFoodoraSystem.login(username, password);

        if (user != null) {
            currentUser = user;
            if (currentUser instanceof Customer) {
                List<String> unreadNotifications = ((Customer) currentUser).getUnreadNotifications();
                System.out.println("Welcome to MyFoodora " + ((Customer) currentUser).getName() + ", you have " + unreadNotifications.size() + " unread notifications.");
                for (String unreadNotification : unreadNotifications) {
                    System.out.println(unreadNotification);
                }
                ((Customer) currentUser).setUnreadNotifications(new ArrayList<String>());
            }
        } else {
            boolean userExists = false;
            for (User u : MyFoodoraSystem.getUsers()) {
                if (u.getUsername().equals(username)) {
                    userExists = true;
                    if (!u.getPassword().equals(password)) {
                        System.out.println("Invalid username or password.");
                    }
                    break;
                }
            }
            if (!userExists) {
                System.out.println("Invalid username or password.");
            }
        }
    }

    /**
     * Logs the current user out of the system.
     */
    public void logout() {
        if (currentUser == null) {
            System.out.println("You are not currently logged in.");
            return;
        }
        System.out.println(currentUser.getName()+" : Logout successful.");
        currentUser = null;

    }
    /**
     * Gives consensus for special offer notifications if the current user is a customer.
     */
    public void giveConsensusNotification() {

        if (currentUser instanceof Customer) {
            ((Customer) currentUser).giveConsensusForSpecialOffers();
            System.out.println("Consensus for the special offer has been given. You will get notified about the special offers !");
        }
    }
    /**
     * Removes consensus for special offer notifications if the current user is a customer.
     */
    public void removeConsensusNotification() {
        if (currentUser instanceof Customer) {
            ((Customer) currentUser).removeConsensusForSpecialOffers();
            System.out.println("Consensus for the special offer has been remove. You will no longer be notified about the special offers.");
        }
    }
    /**
     * Shows the notifications for the current user if they are a customer.
     */
    public void showNotification() {
        if (currentUser == null) {
            System.out.println("You need to log in first.");
            return;
        }

        if (currentUser instanceof Customer) {
            List<String> notifications = ((Customer) currentUser).getNotifications();

            if (!notifications.isEmpty()) {
                System.out.println("Your 10 latest notifications:");
                notifications.stream().limit(10).forEach(notification -> System.out.println("- " + notification));
            } else {
                System.out.println("You have no notifications. Don't forget to give consensus to get notified with the command giveConsensusNotification !");
            }
        } else {
            System.out.println("Only customers can view notifications.");
        }
    }
    /**
     * Shows the order history for the current user.
     */
    public void showHistory() {
        if (currentUser == null) {
            System.out.println("You have to log in.");
            return;
        }
        List<Order> userHistory = MyFoodoraSystem.getHistory(currentUser);

        System.out.println("History:");

        if (userHistory.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (Order order : userHistory) {
                System.out.println(order.toString());
            }
        }
    }

    /**
     * Registers a new restaurant in the system. Only a manager can register a restaurant.
     *
     * @param tokenizer A StringTokenizer containing the restaurant details (4 tokens expected).
     */
    public void registerRestaurant(StringTokenizer tokenizer) {
        if (currentUser instanceof Manager) {
            if (tokenizer.countTokens() != 4) {
                System.out.println("Usage: registerRestaurant <name> <address> <username> <password>");
                return;
            }
            String name = tokenizer.nextToken();
            String address = tokenizer.nextToken();
            String username = tokenizer.nextToken();
            String password = tokenizer.nextToken();
            String[] coordinates = address.split(",");
            Position position = new Position(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]));
            MenuFactory menuFactory = new ConcreteMenuFactory();
            MealFactory mealFactory = new ConcreteMealFactory();
            Restaurant restaurant = new Restaurant(name, username, username, password, position, menuFactory, mealFactory, myFoodoraSystem);
            MyFoodoraSystem.addUser(restaurant);
        } else {
            System.out.println("Only a manager can register a restaurant.");
        }
    }
    /**
     * Registers a new customer in the system. Only a manager can register a customer.
     *
     * @param tokenizer A StringTokenizer containing the customer details (5 tokens expected).
     */
    public void registerCustomer(StringTokenizer tokenizer) {
        if (currentUser instanceof Manager) {
            if (tokenizer.countTokens() != 5) {
                System.out.println("Usage: registerCustomer <firstName> <lastName> <username> <address> <password>");
                return;
            }
            String firstName = tokenizer.nextToken();
            String lastName = tokenizer.nextToken();
            String username = tokenizer.nextToken();
            String address = tokenizer.nextToken();
            String password = tokenizer.nextToken();
            String[] coordinates = address.split(",");
            Position position = new Position(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]));
            Customer customer = new Customer(firstName, lastName, username, username, password, position, "", "");
            MyFoodoraSystem.addUser(customer);
        } else {
            System.out.println("Only a manager can register a customer.");
        }
    }
    /**
     * Registers a new courier in the system. Only a manager can register a courier.
     *
     * @param tokenizer A StringTokenizer containing the courier details (5 tokens expected).
     */
    public void registerCourier(StringTokenizer tokenizer) {
        if (currentUser instanceof Manager) {
            if (tokenizer.countTokens() != 5) {
                System.out.println("Usage: registerCourier <firstName> <lastName> <username> <x,y> <password>");
                return;
            }
            String firstName = tokenizer.nextToken();
            String lastName = tokenizer.nextToken();
            String username = tokenizer.nextToken();
            String position = tokenizer.nextToken();
            String password = tokenizer.nextToken();
            String[] coordinates = position.split(",");
            Position pos = new Position(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]));
            Courier courier = new Courier(firstName, lastName, username, username, password, pos, "");
            MyFoodoraSystem.addUser(courier);
        } else {
            System.out.println("Only a manager can register a courier.");
        }
    }
    /**
     * Adds a dish to the restaurant's menu.
     *
     * @param tokenizer A StringTokenizer containing the command arguments:
     *                  name type price [isVegetarian isGlutenFree].
     *                  The name, type, and price are required, while the isVegetarian
     *                  and isGlutenFree flags are optional.
     */
    public void addDishRestaurantMenu(StringTokenizer tokenizer) {
        if (currentUser instanceof Restaurant) {
            if (tokenizer.countTokens() < 4) {
                System.out.println("Usage: addDishRestaurantMenu <name> <type> <price> [<isVegetarian> <isGlutenFree>]");
                return;
            }
            String name = tokenizer.nextToken();
            String type = tokenizer.nextToken();
            double price = Double.parseDouble(tokenizer.nextToken());
            boolean isVegetarian = tokenizer.hasMoreTokens() ? Boolean.parseBoolean(tokenizer.nextToken()) : false;
            boolean isGlutenFree = tokenizer.hasMoreTokens() ? Boolean.parseBoolean(tokenizer.nextToken()) : false;
            ((Restaurant) currentUser).addMenuItem(type, name, price, isVegetarian, isGlutenFree);
            System.out.println("Dish added to restaurant menu.");
        } else {
            System.out.println("Only a restaurant can add a dish to its menu.");
        }
    }

    /**
     * Creates a meal consisting of multiple items.
     *
     * @param tokenizer A StringTokenizer containing the command arguments:
     *                  name mealType item1 item2 [item3].
     *                  The name, meal type, and at least two items are required.
     */
    public void createMeal(StringTokenizer tokenizer) {
        if (currentUser instanceof Restaurant) {
            if (tokenizer.countTokens() < 4) {
                System.out.println("Usage: createMeal <name> <mealType> <item1> <item2> [<item3>]");
                return;
            }
            String name= tokenizer.nextToken();
            String mealType = tokenizer.nextToken();
            MenuItem[] items = new MenuItem[tokenizer.countTokens()];
            for (int i = 0; tokenizer.hasMoreTokens(); i++) {
                String itemName = tokenizer.nextToken();
                MenuItem item = ((Restaurant) currentUser).getMenu().stream()
                        .filter(menuItem -> menuItem.getName().equals(itemName))
                        .findFirst()
                        .orElse(null);
                if (item == null) {
                    System.out.println("Item " + itemName + " not found in menu.");
                    return;
                }
                items[i] = item;
            }
            ((Restaurant) currentUser).createMeal(name,mealType, items);
            
            System.out.println("Meal "+name+ " created.");
        } else {
            System.out.println("Only a restaurant can create a meal.");
        }
    }
    /**
     * Displays all meals offered by the restaurant.
     */
    public void showMeals() {
        if (currentUser instanceof Restaurant) {
            ((Restaurant) currentUser).printMeals();
        } else {
            System.out.println("Only a restaurant can show its meals.");
        }
    }
    /**
     * Displays details of a specific meal.
     *
     * @param tokenizer A StringTokenizer containing the command argument:
     *                  mealName. The name of the meal to show.
     */
    public void showMeal(StringTokenizer tokenizer) {
        // Check if the current user is a restaurant
        if (!(currentUser instanceof Restaurant restaurant)) {
            System.out.println("Only a restaurant can show a meal.");
            return;
        }

        if (tokenizer.countTokens() != 1) {
            System.out.println("Usage: showMeal <mealName>");
            return;
        }

        String mealName = tokenizer.nextToken();

        for (Meal meal : restaurant.getMeals()) {
            if (meal.getName().equalsIgnoreCase(mealName)) {
                System.out.println("Dishes in meal '" + mealName + "':");
                for (MenuItem menuItem : meal.getItems()) {
                    System.out.println(menuItem.getName());
                }
                return;
            }
        }

        // If no matching meal is found, print an error message
        System.out.println("Meal '" + mealName + "' not found.");
    }
    /**
     * Sets a special offer (meal of the week) for the restaurant.
     *
     * @param tokenizer A StringTokenizer containing the command argument:
     *                  name. The name of the meal to set as the special offer.
     */
    public void setSpecialOffer(StringTokenizer tokenizer) {
        if (currentUser instanceof Restaurant) {
            if (tokenizer.countTokens() < 1) {
                System.out.println("Usage: setSpecialOffer <name>");
                return;
            }

            String name = tokenizer.nextToken();
            List<Meal> meals = ((Restaurant) currentUser).getMeals();

            Meal foundMeal = null;
            for (Meal meal : meals) {
                if (meal.getName().equals(name)) {
                    foundMeal = meal;
                    break;
                }
            }

            if (foundMeal != null) {
                ((Restaurant) currentUser).setMealOfTheWeek(foundMeal);
                System.out.println("Meal of the week is set to :  " + name);

            } else {
                System.out.println("No meal with the name " + name);
            }
        } else {
            System.out.println("Only a restaurant can set SpecialOffer");
        }
    }

    /**
     * Removes the special offer (meal of the week) for the restaurant.
     */
    public void removeSpecialOffer() {
        if (currentUser instanceof Restaurant) {
            ((Restaurant) currentUser).setMealOfTheWeek(null);
            System.out.println("Special offer was removed");
        } else {
            System.out.println("Only a restaurant can set SpecialOffer");
        }
    }
    /**
     * Displays the points on the customer's fidelity card.
     */
    public void showPoints() {
        if (currentUser instanceof Customer customer) {
            FidelityCard fidelityCard = customer.getFidelityCard();

            if (fidelityCard instanceof PointFidelityCard) {
                System.out.println("The number of points in your fidelity card is: " + customer.getPoints());
            } else {
                System.out.println("You don't have a PointFidelityCard. Your fidelity card is: " + customer.getClass().getSimpleName());
            }
        } else {
            System.out.println("Only a customer can consult the points on their fidelity card.");
        }
    }
    /**
     * Creates an order for the customer.
     *
     * @param tokenizer A StringTokenizer containing the command arguments:
     *                  restaurantName orderName. The name of the restaurant and the name of the order.
     */
    public void createOrder(StringTokenizer tokenizer) {
        if (currentUser instanceof Customer customer) {
            if (tokenizer.countTokens() < 2) {
                System.out.println("Usage: createOrder <restaurantName> <orderName>");
                return;
            }

            String restaurantName = tokenizer.nextToken();
            String orderName = tokenizer.nextToken();

            Restaurant foundRestaurant = null;
            for (Restaurant restaurant : MyFoodoraSystem.getRestaurants()) {
                if (restaurant.getName().equals(restaurantName)) {
                    foundRestaurant = restaurant;
                    break;
                }
            }

            if (foundRestaurant != null) {
                Order order = new Order(customer, foundRestaurant, orderName);
                MyFoodoraSystem.addIncompleteOrder(order);
                System.out.println("Order successfully created: " + orderName);
            } else {
                System.out.println("Error: Restaurant with name " + restaurantName + " not found.");
            }
        } else {
            System.out.println("Only a customer can create an order.");
        }
    }

    /**
     * Adds an item to an existing order.
     *
     * @param tokenizer A StringTokenizer containing the command arguments:
     *                  orderName itemName. The name of the order and the name of the item to add.
     */
    public void addItem2Order(StringTokenizer tokenizer) {
        if (!(currentUser instanceof Customer)) {
            System.out.println("Only a customer can add items to an order");
            return;
        }

        if (tokenizer.countTokens() < 2) {
            System.out.println("Usage: addItem2Order <orderName> <itemName>");
            return;
        }

        String orderName = tokenizer.nextToken();
        String itemName = tokenizer.nextToken();

        Order foundOrder = null;
        Restaurant foundRestaurant = null;
        for (Order order : MyFoodoraSystem.getIncompleteOrders()) {
            if (order.getName().equals(orderName)) {
                foundOrder = order;
                foundRestaurant = order.getRestaurant();
                break;
            }
        }

        if (foundOrder == null) {
            System.out.println("Order " + orderName + " not found");
            return;
        }

        // Check if the meal is the mealOfTheWeek
        Meal mealOfTheWeek = foundRestaurant.getMealOfTheWeek();
        if (mealOfTheWeek != null && mealOfTheWeek.getName().equals(itemName)) {
            foundOrder.addMeal(mealOfTheWeek);
            MyFoodoraSystem.updateIncompleteOrders(foundOrder);
            System.out.println("Meal " + itemName + " successfully added to " + orderName);
            return;
        }

        for (Meal meal : foundRestaurant.getMeals()) {
            if (meal.getName().equals(itemName)) {
                foundOrder.addMeal(meal);
                MyFoodoraSystem.updateIncompleteOrders(foundOrder);
                System.out.println("Meal " + itemName + " successfully added to " + orderName);
                return;
            }
        }

        for (MenuItem menuItem : foundRestaurant.getMenu()) {
            if (menuItem.getName().equals(itemName)) {
                foundOrder.addItem(menuItem);
                MyFoodoraSystem.updateIncompleteOrders(foundOrder);
                System.out.println("Dish " + itemName + " successfully added to " + orderName);
                return;
            }
        }

        System.out.println("Item " + itemName + " not found");
    }

    /**
     * Finalizes and pays for an order by the current customer.
     *
     * @param tokenizer A StringTokenizer containing the order name, day, month, and year.
     *                  Usage: endOrder orderName day month year
     */
    public void endOrder(StringTokenizer tokenizer) {
        if (currentUser instanceof Customer) {
            if (tokenizer.countTokens() < 4) { // Expecting day, month, year
                System.out.println("Usage: endOrder <orderName> <day> <month> <year>");
                return;
            }

            String orderName = tokenizer.nextToken();
            int day, month, year;

            try {
                day = Integer.parseInt(tokenizer.nextToken());
                month = Integer.parseInt(tokenizer.nextToken());
                year = Integer.parseInt(tokenizer.nextToken());
            } catch (NumberFormatException e) {
                System.out.println("Invalid date format. Please provide day, month, and year as integers.");
                return;
            }
            Time orderDate = new Time(day, month, year);

            Order foundOrder = null;
            for (Order order : MyFoodoraSystem.getIncompleteOrders()) {
                if (order.getName().equals(orderName)) {
                    foundOrder = order;
                    break;
                }
            }

            if (foundOrder != null) {
                if (foundOrder.getCustomer().equals(currentUser)) {
                    foundOrder.setTime(orderDate);
                    MyFoodoraSystem.placeOrder(foundOrder);
                    System.out.println("Order " + orderName + " finalized and paid on " + orderDate);
                } else {
                    System.out.println("This order does not belong to the current customer.");
                }
            } else {
                System.out.println("Order " + orderName + " not found.");
            }
        } else {
            System.out.println("Only a customer can finalize an order.");
        }
    }
    /**
     * Sets the current courier on duty.
     *
     * Only a courier can set themselves on duty.
     */
    public void onDuty() {
        if (!(currentUser instanceof Courier)) {
            System.out.println("Only a courier can set themselves on duty.");
            return;
        }
        ((Courier)currentUser).setOnDuty(true);
        System.out.println("You are now on Duty !");
    }
    /**
     * Sets the current courier off duty.
     *
     * Only a courier can set themselves off duty.
     */
    public void offDuty() {
        if (!(currentUser instanceof Courier)) {
            System.out.println("Only a courier can set themselves on duty.");
            return;
        }
        ((Courier)currentUser).setOnDuty(true);
        System.out.println("You are now off Duty !");
    }

    /**
     * Allocates a courier to deliver an order for the current restaurant.
     *
     * @param tokenizer A StringTokenizer containing the order name.
     *                  Usage: findDeliverer orderName
     */
    public void findDeliverer(StringTokenizer tokenizer) {
        if (!(currentUser instanceof Restaurant)) {
            System.out.println("Only a restaurant can allocate orders to a deliverer.");
            return;
        }

        if (tokenizer.countTokens() != 1) {
            System.out.println("Usage: findDeliverer <orderName>");
            return;
        }

        String orderName = tokenizer.nextToken();

        Order order = null;
        for (Order o : MyFoodoraSystem.getIncompleteOrders()) {
            if (o.getName().equals(orderName)) {
                order = o;
                break;
            }
        }

        if (order == null) {
            System.out.println("Order " + orderName + " not found in incomplete orders.");
            return;
        }

        DeliveryPolicy deliveryPolicy = MyFoodoraSystem.getDeliveryPolicy();

        deliveryPolicy.assignCourier(order);
        System.out.println("Courier " + order.getCourier()+" assigned");


    }
    /**
     * Changes the location of the current courier.
     *
     * @param tokenizer A StringTokenizer containing the latitude and longitude.
     *                  Usage: changeLocation latitude,longitude
     */
    public void changeLocation(StringTokenizer tokenizer) {
        if (currentUser == null) {
            System.out.println("You need to log in first.");
            return;
        }

        if (!(currentUser instanceof Courier)) {
            System.out.println("Only a courier can change location.");
            return;
        }

        if (tokenizer.countTokens() != 2) {
            System.out.println("Usage: changeLocation <latitude,longitude>");
            return;
        }

        try {
            String position = tokenizer.nextToken();
            String[] coordinates = position.split(",");
            double latitude=Double.parseDouble(coordinates[0]);
            double longitude=Double.parseDouble(coordinates[1]);

            Courier courier = (Courier) currentUser;
            courier.setPosition(new Position(latitude, longitude));

            System.out.println("Location updated to: (" + latitude + ", " + longitude + ")");
        } catch (NumberFormatException e) {
            System.out.println("Invalid latitude or longitude. Please enter valid numbers.");
        }
    }
    /**
     * Sets the delivery policy of the system.
     *
     * @param tokenizer A StringTokenizer containing the policy name.
     *                  Usage: setDeliveryPolicy policyName
     */
    public void setDeliveryPolicy(StringTokenizer tokenizer) {
        if (!(currentUser instanceof Manager)) {
            System.out.println("Only a manager can set the delivery policy.");
            return;
        }

        if (tokenizer.countTokens() != 1) {
            System.out.println("Usage: setDeliveryPolicy <policyName>");
            return;
        }

        String policyName = tokenizer.nextToken();

        if (policyName.equalsIgnoreCase("FastestDelivery")) {
            MyFoodoraSystem.setDeliveryPolicy(new FastestDelivery());
            System.out.println("Delivery policy set to FastestDelivery.");
        } else if (policyName.equalsIgnoreCase("FairOccupationDelivery")) {
            MyFoodoraSystem.setDeliveryPolicy(new FairOccupationDelivery());
            System.out.println("Delivery policy set to FairOccupationDelivery.");
        } else {
            System.out.println("Invalid delivery policy name.");
        }
    }
    /**
     * Sets the profit policy of the system.
     *
     * @param tokenizer A StringTokenizer containing the policy name.
     *                  Usage: setProfitPolicy policyName
     */
    public void setProfitPolicy(StringTokenizer tokenizer) {
        if (!(currentUser instanceof Manager)) {
            System.out.println("Only a manager can set the Profit policy.");
            return;
        }

        if (tokenizer.countTokens() != 1 ) {
            System.out.println("Usage: setProfitPolicy <policyName>");
            return;
        }

        String policyName = tokenizer.nextToken();

        if (policyName.equalsIgnoreCase("TargetProfitDeliveryCost")) {
            MyFoodoraSystem.setProfitPolicy(new TargetProfitDeliveryCost());
            System.out.println("Delivery policy set to TargetProfitDeliveryCost.");
        } else if (policyName.equalsIgnoreCase("TargetProfitMarkup")) {
            MyFoodoraSystem.setProfitPolicy(new TargetProfitMarkup());
            System.out.println("Delivery policy set to TargetProfitMarkup.");
        } else if (policyName.equalsIgnoreCase("TargetProfitServiceFee")) {
            MyFoodoraSystem.setProfitPolicy(new TargetProfitServiceFee());
            System.out.println("Delivery policy set to TargetProfitServiceFee.");
        } else {
            System.out.println("Invalid Profit policy name.");
        }
        }
    /**
     * Applies the profit policy with the specified target profit.
     *
     * @param tokenizer A StringTokenizer containing the target profit.
     *                  Usage: applyProfitPolicy targetProfit
     */
    public void applyProfitPolicy(StringTokenizer tokenizer) {
        if (!(currentUser instanceof Manager)) {
            System.out.println("Only a manager can set the Profit policy.");
            return;
        }

        if (tokenizer.countTokens() != 1) {
            System.out.println("Usage: applyProfitPolicy <targetProfit>");
            return;
        }

        double targetProfit = Double.parseDouble(tokenizer.nextToken());
        TargetProfitPolicy targetProfitPolicy =MyFoodoraSystem.getTargetProfitPolicy();
        targetProfitPolicy.apply(myFoodoraSystem,targetProfit);
        System.out.println("Profit policy based on last month profit applied !");

  
        }

    /**
     * Associates a fidelity card with a customer.
     *
     * @param tokenizer A StringTokenizer containing the username and card type.
     *                  Usage: associateCard username cardType
     */
    public void associateCard(StringTokenizer tokenizer) {
        // Check if the current user is a manager
        if (!(currentUser instanceof Manager)) {
            System.out.println("Only a MyFoodora manager can associate a fidelity card to a user.");
            return;
        }

        if (tokenizer.countTokens() != 2) {
            System.out.println("Usage: associateCard <username> <cardType>");
            return;
        }

        String username = tokenizer.nextToken();
        String cardType = tokenizer.nextToken();

        if (!(cardType.equalsIgnoreCase("BasicFidelityCard") || cardType.equalsIgnoreCase("PointFidelityCard") || cardType.equalsIgnoreCase("LotteryFidelityCard"))) {
            System.out.println("Invalid card type.");
            return;
        }

        Customer customer = null;
        for (Customer c : MyFoodoraSystem.getCustomers()) {
            if (c.getUsername().equals(username)) {
                customer = c;
                break;
            }
        }

        if (customer == null) {
            System.out.println("Customer with username " + username + " doesn't exist.");
            return;
        }

        switch (cardType.toLowerCase()) {
            case "basicfidelitycard":
                customer.setFidelityCard(new BasicFidelityCard());
                break;
            case "pointfidelitycard":
                customer.setFidelityCard(new PointFidelityCard());
                break;
            case "lotteryfidelitycard":
                customer.setFidelityCard(new LotteryFidelityCard());
                break;
        }

        System.out.println("Fidelity card associated successfully with user " + username + ".");
        return;
    }

    /**
     * Shows the number of deliveries completed by each courier.
     *
     * Only a manager can show courier deliveries.
     */
    public void showCourierDeliveries() {
        // Check if the current user is a manager
        if (!(currentUser instanceof Manager)) {
            System.out.println("Only a manager can show courier deliveries.");
            return;
        }
        List<Order> orders = MyFoodoraSystem.getOrders();

        // Create a map to store the number of completed deliveries for each courier
        Map<Courier, Integer> courierDeliveries = new HashMap<>();

        // Count the number of completed deliveries for each courier
        for (Order order : orders) {
            Courier courier = order.getCourier();
            courierDeliveries.put(courier, courierDeliveries.getOrDefault(courier, 0) + 1);
        }

        List<Courier> allCouriers = MyFoodoraSystem.getCouriers();

        // Separate couriers with completed deliveries from couriers without completed deliveries
        List<Courier> couriersWithDeliveries = new ArrayList<>();
        List<Courier> couriersWithoutDeliveries = new ArrayList<>();
        for (Courier courier : allCouriers) {
            if (courierDeliveries.containsKey(courier)) {
                couriersWithDeliveries.add(courier);
            } else {
                couriersWithoutDeliveries.add(courier);
            }
        }

        // Sort couriers with completed deliveries by the number of completed deliveries
        couriersWithDeliveries.sort((c1, c2) -> courierDeliveries.get(c2).compareTo(courierDeliveries.get(c1)));

        // Display couriers with completed deliveries
        System.out.println("Couriers with completed deliveries (sorted by number of completed deliveries):");
        for (Courier courier : couriersWithDeliveries) {
            System.out.println(courier.getName() + ": " + courierDeliveries.get(courier) + " completed deliveries");
        }

        // Sort couriers without completed deliveries alphabetically
        couriersWithoutDeliveries.sort(Comparator.comparing(Courier::getName));

        // Display couriers without completed deliveries
        System.out.println("Couriers without completed deliveries (sorted alphabetically):");
        for (Courier courier : couriersWithoutDeliveries) {
            System.out.println(courier.getName() + ": 0 completed deliveries");
        }
    }

    /**
     * Shows the top restaurants based on the number of delivered orders.
     *
     * Only a manager can show restaurant statistics.
     */
    public void showRestaurantTop() {
        // Check if the current user is a manager
        if (!(currentUser instanceof Manager)) {
            System.out.println("Only a MyFoodora manager can show restaurant statistics.");
            return;
        }

        List<Order> orders = MyFoodoraSystem.getOrders();

        // Create a map to store the number of delivered orders for each restaurant
        Map<Restaurant, Integer> restaurantOrders = new HashMap<>();

        // Count the number of delivered orders for each restaurant
        for (Order order : orders) {
            Restaurant restaurant = order.getRestaurant();
            restaurantOrders.put(restaurant, restaurantOrders.getOrDefault(restaurant, 0) + 1);
        }

        // Sort the restaurants with orders by the number of sales in decreasing order
        List<Restaurant> restaurantsWithOrders = new ArrayList<>(restaurantOrders.keySet());
        restaurantsWithOrders.sort((r1, r2) -> {
            int sales1 = restaurantOrders.getOrDefault(r1, 0);
            int sales2 = restaurantOrders.getOrDefault(r2, 0);
            return Integer.compare(sales2, sales1);
        });

        // Display restaurants with orders sorted by sales
        System.out.println("Restaurants with orders sorted by number of sales:");
        for (Restaurant restaurant : restaurantsWithOrders) {
            int sales = restaurantOrders.getOrDefault(restaurant, 0);
            System.out.println(restaurant.getName() + ": " + sales + " delivered orders");
        }

        // Sort the remaining restaurants alphabetically
        List<Restaurant> remainingRestaurants = new ArrayList<>(MyFoodoraSystem.getRestaurants());
        remainingRestaurants.removeAll(restaurantsWithOrders);
        remainingRestaurants.sort(Comparator.comparing(Restaurant::getName));

        // Display remaining restaurants sorted alphabetically
        System.out.println("Restaurants without orders sorted alphabetically:");
        for (Restaurant restaurant : remainingRestaurants) {
            System.out.println(restaurant.getName() + ": 0 delivered orders");
        }
    }
    /**
     * Displays a list of all registered customers.
     */
    public void showCustomers() {
        List<Customer> customerList= MyFoodoraSystem.getCustomers();
        for (Customer customer : customerList) {
            System.out.printf("  - Name: %s\n", customer);
        }    }

    /**
     * Displays the menu items of a specified restaurant.
     *
     * @param tokenizer A StringTokenizer containing the restaurant name.
     *                  Usage: showMenuItem restaurantName
     */
    public void showMenuItem(StringTokenizer tokenizer) {
        String name = tokenizer.nextToken();
        Restaurant matchingRestaurant = null;
        for (Restaurant restaurant : MyFoodoraSystem.getRestaurants()) {
            if (restaurant.getName().equals(name)) {
                matchingRestaurant = restaurant;
                break;
            }
        }

        if (matchingRestaurant != null) {
            System.out.println("Menu for " + matchingRestaurant.getName() + ":");
            for (MenuItem menuItem : matchingRestaurant.getMenu()) {
                System.out.println("- " + menuItem.getName() + ": $" + menuItem.getPrice());
            }
        } else {
            // No matching restaurant found
            System.out.println("No restaurant found with the name: " + name);
        }
    }

    /**
     * Shows the total profit of the system.
     *
     * Only a manager can show the total profit.
     */
    public void showTotalProfit() {
        if (!(currentUser instanceof Manager)) {
            System.out.println("Only a manager can show the total profit");
        }
        else {
            System.out.println("the total profit is : "+MyFoodoraSystem.computeTotalProfit());
        }
    }
    /**
     * Shows the total profit of the system for a specified period.
     *
     * @param tokenizer A StringTokenizer containing the start and end dates.
     *                  Usage: showTotalProfitPeriod startDay startMonth startYear endDay endMonth endYear
     */
    public void showTotalProfitPeriod(StringTokenizer tokenizer) {
        // Check if the current user is a manager
        if (!(currentUser instanceof Manager)) {
            System.out.println("Only a  manager can access the total profit.");
            return;
        }

        if (tokenizer.countTokens() != 6) {
            System.out.println("Usage: showTotalProfit <startDay> <startMonth> <startYear> <endDay> <endMonth> <endYear>");
            return;
        }

        // Retrieve start date components
        int startDay = Integer.parseInt(tokenizer.nextToken());
        int startMonth = Integer.parseInt(tokenizer.nextToken());
        int startYear = Integer.parseInt(tokenizer.nextToken());

        // Retrieve end date components
        int endDay = Integer.parseInt(tokenizer.nextToken());
        int endMonth = Integer.parseInt(tokenizer.nextToken());
        int endYear = Integer.parseInt(tokenizer.nextToken());

        Time startTime = new Time(startDay, startMonth, startYear);
        Time endTime = new Time(endDay, endMonth, endYear);

        double profit = MyFoodoraSystem.calculateProfitPeriod(startTime, endTime);

        System.out.println("Total profit from " + startTime + " to " + endTime + ": $" + profit);
    }
    /**
     * Shows the average income per customer.
     *
     * Only a manager can show the average income per customer.
     */
    public void showAverageIncomePerCustomer() {
        if (!(currentUser instanceof Manager)) {
            System.out.println("Only a manager can show the average income per customer");
        }
        else {
            System.out.println("the average income per customer is : "+MyFoodoraSystem.computeAverageIncomePerCustomer());
        }
    }
    /**
     * Shows the average income per customer for a specified period.
     *
     * @param tokenizer A StringTokenizer containing the start and end dates.
     *                  Usage: showAverageIncomePerCustomerPeriod startDay startMonth startYear endDay endMonth endYear
     */
    public void showAverageIncomePerCustomerPeriod(StringTokenizer tokenizer) {
        // Check if the current user is a manager
        if (!(currentUser instanceof Manager)) {
            System.out.println("Only a  manager can access the average income per customer in a period.");
            return;
        }

        if (tokenizer.countTokens() != 6) {
            System.out.println("Usage: showAverageIncomePerCustomerPeriod <startDay> <startMonth> <startYear> <endDay> <endMonth> <endYear>");
            return;
        }
            // Retrieve start date components
            int startDay = Integer.parseInt(tokenizer.nextToken());
            int startMonth = Integer.parseInt(tokenizer.nextToken());
            int startYear = Integer.parseInt(tokenizer.nextToken());

            // Retrieve end date components
            int endDay = Integer.parseInt(tokenizer.nextToken());
            int endMonth = Integer.parseInt(tokenizer.nextToken());
            int endYear = Integer.parseInt(tokenizer.nextToken());

            Time startTime = new Time(startDay, startMonth, startYear);
            Time endTime = new Time(endDay, endMonth, endYear);

            double averageIncomePeriod = MyFoodoraSystem.computeAverageIncomePerCustomerPeriod(startTime, endTime);

            System.out.println("the average income per customer from " + startTime + " to " + endTime + ": $" + averageIncomePeriod);
    }
    /**
     * Runs a test script from a specified file.
     *
     * @param tokenizer A StringTokenizer containing the file path.
     *                  Usage: runTest filepath
     */
    public void runTest(StringTokenizer tokenizer) {
        if (tokenizer.countTokens() != 1) {
            System.out.println("Usage: runTest <filepath>");
            return;
        }

        String filename = tokenizer.nextToken();
        filename = "./eval/" + filename; // Adding the prefix

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                executeCommand(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file is not found at the specified path: " + filename);
            String currentDir = System.getProperty("user.dir");
            System.out.println("Current path : " + currentDir);
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    /**
     * Displays the list of available commands.
     */
    public void help() {
        System.out.println("List of available commands:");
        System.out.println("runTest <testName>");
        System.out.println("login <username> <password>");
        System.out.println("logout");
        System.out.println("registerRestaurant <name> <latitude,longitude> <username> <password>");
        System.out.println("registerCustomer <firstName> <lastName> <username> <latitude,longitude> <password>");
        System.out.println("registerCourier <firstName> <lastName> <username> <latitude,longitude> <password>");
        System.out.println("addDishRestaurantMenu <type> <name> <price> [<isVegetarian> <isGlutenFree>]");
        System.out.println("createMeal <mealType> <nameitem1> <item2> [<item3>]");
        System.out.println("showMeals");
        System.out.println("showMeal <mealName>");
        System.out.println("setSpecialOffer <mealName>");
        System.out.println("removeFromSpecialOffer");
        System.out.println("giveConsensusNotification");
        System.out.println("removeConsensusNotification");
        System.out.println("showNotification");
        System.out.println("showPoints");
        System.out.println("createOrder <orderName>");
        System.out.println("addItem2Order <orderName> <itemName>");
        System.out.println("endOrder <orderName>");
        System.out.println("onDuty <username>");
        System.out.println("offDuty <username>");
        System.out.println("changeLocation <latitude,longitude>");
        System.out.println("findDeliverer <orderName>");
        System.out.println("setDeliveryPolicy <policyType>");
        System.out.println("setProfitPolicy <policyType>");
        System.out.println("applyProfitPolicy <targetProfit>");
        System.out.println("associateCard <username> <cardType>");
        System.out.println("showCourierDeliveries");
        System.out.println("showRestaurantTop");
        System.out.println("showCustomers");
        System.out.println("showMenuItem <restaurantName>");
        System.out.println("showTotalProfit");
        System.out.println("showTotalProfitPeriod <startDate> <endDate>");
        System.out.println("showAverageIncomePerCustomer");
        System.out.println("showAverageIncomePerCustomerPeriod <startDay> <startMonth> <startYear> <endDay> <endMonth> <endYear>");
    }
    
    /**
     * The main method to start the MyFoodora command-line user interface (CLUI).
     * This method initializes an instance of {@code MyFoodoraCLUI} and starts it.
     *
     * @param args command-line arguments (not used in this method)
     */
    public static void main(String[] args) {
        MyFoodoraCLUI clui = new MyFoodoraCLUI();
        clui.start();
    }
}