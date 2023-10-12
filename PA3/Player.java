import java.util.ArrayList;
import java.util.List;

public class Player implements Inventory {
    private double balance;
    private List<Item> playerInventory;

    public Player(double balance) {
        this.balance = balance;
        this.playerInventory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void addMoney(double amount) {
        this.balance += amount;
    }

    public boolean removeMoney(double amount) {
        if (amount <= balance) {
            this.balance -= amount;
            return true;
        } else {
            System.out.println("Not enough money!");
            return false;
        }
    }

    //TODO Deprecation imminent
    public void addItem(Item item) {
        acquireItem(item);
    }

    //TODO Deprecation imminent
    public void removeItem(Item item) {
        relinquishItem(item);
    }

    // New methods for abstraction/additional complexity
    public void acquireItem(Item item) {
        playerInventory.add(item);
    }

    public void relinquishItem(Item item) {
        playerInventory.remove(item);
    }

    public List<Item> getItems() {
        return playerInventory;
    }

    public Item getItemByName(String name) {
        for (Item item : playerInventory) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }
}