import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Item sword = new StoreItem("Sword", 10.0);
        Item potion = new StoreItem("Health Potion", 5.0);
        Item hat = new StoreItem("Hat", 11);

        Store store = new Store();
        store.addItem(sword);
        store.addItem(potion);
        store.addItem(hat);
        store.displayInventory();

        Player player = new Player(100.0);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter a command (1 to enter the store, 4 to exit):");
            String input = scanner.nextLine();

            if (input.equals("1")) {
                store.enter(player);
                storeMenu(scanner, store, player);
                store.exit(player);
            } else if (input.equals("4")) {
                System.out.println("Exiting the program...");
                break;
            } else {
                System.out.println("Invalid command!");
            }
        }

        scanner.close();
    }

    public static void storeMenu(Scanner scanner, Store store, Player player) {
        while (true) {
            System.out.println("\nStore Menu:");
            System.out.println("1. Buy an item");
            System.out.println("2. Sell an item");
            System.out.println("3. Display inventory");
            System.out.println("4. Exit store");

            String input = scanner.nextLine();

            if (input.equals("1")) {
                store.displayInventory();
                System.out.println("Enter the name of the item you want to buy:");
                String itemName = scanner.nextLine();
                Item item = store.getItemByName(itemName);
                if (item != null) {
                    if (store.buyItem(item, player)){
                        System.out.println("Item purchased successfully!");
                    } else {
                        System.out.println("Could not purchase the desired item.");
                    }
                } else {
                    System.out.println("Item not available in the store.");
                }
            } else if (input.equals("2")) {
                System.out.println("Enter the name of the item you want to sell:");
                String itemName = scanner.nextLine();
                Item item = player.getItemByName(itemName);
                if (item != null) {
                    store.sellItem(item, player);
                    System.out.println("Item sold successfully!");
                } else {
                    System.out.println("Item not found in your inventory.");
                }
            } else if (input.equals("3")) {
                store.displayInventory();
            } else if (input.equals("4")) {
                System.out.println("Exiting the store...");
                store.exit(player);
                break;
            } else {
                System.out.println("Invalid command!");
            }
        }
    }
}