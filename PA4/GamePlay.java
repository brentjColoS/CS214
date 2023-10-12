import java.util.Scanner;

class GamePlay {
    private Player player;
    private Store store;

    public void playGame() {        
        Scanner scanner = new Scanner(System.in);
        
        initializeGame();

        while (true) {
            printMenu();
            String input = scanner.nextLine();

            switch (input) {
                case "0":
                    handleEnterStore(scanner);
                    break;
                case "1":
                    handleConsumeItem(scanner);
                    break;
                case "2":
                    handleHoldItem(scanner);
                    break;
                case "3":
                    handleWearItem(scanner);
                    break;
                case "4":
                    displayInventory();
                    break;
                case "5":
                    handleExitGame(scanner);
                    break;
                default:
                    System.out.println("Invalid command!");
                    break;
            }
        }
    }

    private void initializeGame() {
        GameStarter gameStarter = new GameStarter();
        gameStarter.startGame();
        player = gameStarter.getPlayer();
        store = gameStarter.getStore();
    }

    private void printMenu() {
        System.out.println("\nEnter a command (0 to enter the store, 1 to consume an item, 2 to hold/put down an item, 3 to wear/take-off an item, 4 to check your inventory, 5 to exit the game):");
    }

    private void displayInventory() {
        try {
            System.out.println(player.getAllItems());
        } catch (NullPointerException e) {
            System.out.println("\nThere are no items in the player inventory.");
        }

    }

    private void handleEnterStore(Scanner scanner) {
        store.enter(player);
        StoreMenuHandler.storeMenu(scanner, store, player);
        store.exit(player);
    }

    private void handleConsumeItem(Scanner scanner) {
        System.out.println("Items in inventory: " + player.getItems());
        System.out.println("Items currently being held: " + player.getHeldItems());
        System.out.println("Enter the name of the item you want to consume:");
        String itemName = scanner.nextLine();
        Item item = player.getItemByName(itemName);
        Item held = player.getHeldByName(itemName);
        if (item != null) {
            player.consumeItem(item);
        } else if (held != null) {
            player.removeHeldItem(held);
            player.consumeItem(item);
        } else {
            System.out.println("Hmm that item wasn't quite consumeable was it? Try again, maybe with a different item...");
        }
    }

    private void handleHoldItem(Scanner scanner) {
        System.out.println("\nWould you like to equip a holdable item on or put a currently held item back into inventory? (0 to equip, 1 to put back into inventory)");
        String holdInput = scanner.nextLine();
        if (holdInput.equals("0")) {
            handleEquipHoldableItem(scanner);
        } else if (holdInput.equals("1")) {
            handlePutBackToInventory(scanner);
        } else {
            System.out.println("Well, that seems to be neither a 0 or a 1. Try again, maybe with a number like 0... or 1...");
        }
    }

    private void handleEquipHoldableItem(Scanner scanner) {
        System.out.println("Items in inventory: " + player.getItems());
        System.out.println("Enter the name of the item you want to hold:");
        String itemName = scanner.nextLine();
        Item item = player.getHeldByName(itemName);
        if (item != null) {
            player.removeItem(item);
            player.addHeldItem(item);
        } else {
            System.out.println("Hmm that item wasn't quite holdable was it? Try again, maybe with a different item...");
        }
    }

    private void handlePutBackToInventory(Scanner scanner) {
        System.out.println("Items currently being held: " + player.getHeldItems());
        System.out.println("Enter the name of the item you want to stop holding and put back into the inventory:");
        String itemName = scanner.nextLine();
        Item item = player.getHeldByName(itemName);
        if (item != null && player.removeHeldItem(item) == true) {
            player.acquireItem(item);
        } else {
            System.out.println("Hmm that didn't go quite like you thought it would, did it? Try again, consider taking up juggling classes, or perhaps a different item...");
        }
    }

    private void handleWearItem(Scanner scanner) {
        System.out.println("\nWould you like to put a wearable item on or take one off? (0 to put on, 1 to take off)");
        String wearInput = scanner.nextLine();
        if (wearInput.equals("0")) {
            handlePutOnWearableItem(scanner);
        } else if (wearInput.equals("1")) {
            handleTakeOffWearableItem(scanner);
        } else {
            System.out.println("Well, that seems to be neither a 0 or a 1. Try again, maybe with a number like 0... or 1...");
        }
    }

    private void handlePutOnWearableItem(Scanner scanner) {
        player.getItems();
        System.out.println("Enter the name of the item you want to wear:");
        String itemName = scanner.nextLine();
        Item item = player.getItemByName(itemName);
        if (item != null) {
            player.addWornItem(item);
        } else {
            System.out.println("Hmm that item wasn't quite wearable was it? Try again, maybe with a different item...");
        }
    }

    private void handleTakeOffWearableItem(Scanner scanner) {
        player.getWornItems();
        System.out.println("Enter the name of the item you want to take off:");
        String itemName = scanner.nextLine();
        Item item = player.getWornByName(itemName);
        if (item != null && player.removeWornItem(item)) {
            // handle success
        } else {
            System.out.println("Hmm that item wasn't quite being worn was it? Try again, maybe with a different item...");
        }
    }

    private void handleExitGame(Scanner scanner) {
        GameStopper stopGame = new GameStopper();

        System.out.println("Exiting the program...");
        scanner.close();
        stopGame.stopGame();
    }
}