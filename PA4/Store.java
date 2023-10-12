import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Store implements Inventory {
    private List<Item> inventory;
    private List<Player> players_in_store; 

    public Store() {
        inventory = new ArrayList<>();
        players_in_store = new ArrayList<>();
    }

    public void enter(Player player){
        if (check_player_in_store(player) == false){
            players_in_store.add(player);
            displayInventory();
        } else {
            System.out.println("Player is already in the store.");
        }
    }

    public void exit(Player player){
         if (check_player_in_store(player) == true){
            players_in_store.remove(player);
        } else {
            System.out.println("Player never entered the store.");
        }
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    // New methods for abstraction/additional complexity
    public void acquireItem(Item item) {
        inventory.add(item);
    }

    public void relinquishItem(Item item) {
        inventory.remove(item);
    }

    public void displayInventory() {
        System.out.println("Store Inventory:");
        for (Item item : inventory) {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }
    }

    private boolean check_player_in_store(Player player){
        int index =  players_in_store.indexOf(player);
        if (index == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<Item> getItems() {
        return inventory;
    }

    public Item getItemByName(String name) {
        // Iterate through the player's items and return the item with the matching name
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null; // Item not found in the player's inventory
    }

    public boolean buyItem(Item item, Player player) {
        if (check_player_in_store(player) == false){
            System.out.println("Player needs to enter the store before being able to buy anything");
            return false;
        }
        
        if (inventory.contains(item)) {
            if (player.removeMoney(item.getPrice())) {
                inventory.remove(item);
                player.addItem(item);
                return true;
            }
        } else {
            System.out.println("Item not available in the store.");
        }
        return false;
    }

    public boolean sellItem(Item item, Player player) {
         if (check_player_in_store(player) == false){
            System.out.println("Player needs to enter the store before being able to sell anything");
            return false;
        }
        player.removeItem(item);
        player.addMoney(item.getPrice());
        inventory.add(item);
        return true;
    }

    public Set<String> getTags() {
        Set<String> tags = new HashSet<>();
        for (Item item : inventory) {
            tags.addAll(item.getTags());
        }
        return tags;
    }

    public Iterable<Item> exposeInventory() {
        return Collections.unmodifiableList(inventory);
    }
}