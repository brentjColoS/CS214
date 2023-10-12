import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Player implements Inventory {
    private double balance;
    private List<Item> consumedInventory;
    private List<Item> heldInventory;
    private int numHeldItems;
    private List<Item> playerInventory;
    private List<Item> relinquishedInventory;
    private List<Item> wornInventory;

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

    // New method for abstraction/additional complexity
    public void acquireItem(Item item) {
        playerInventory.add(item);
    }

    //TODO Deprecation imminent
    public void addItem(Item item) {
        acquireItem(item);
    }

    public boolean consumeItem(Item item) {
        if (item.getTags().contains("Consumeable")) {
            consumedInventory.add(item);
            relinquishItem(item);
            System.out.println(item + " -> Consumed.");
            return true;
        } else if (item.getTags().isEmpty() == true) {
            consumedInventory.add(item);
            relinquishItem(item);
            System.out.println(item + " -> I sure hope that was consumeable, because it was consumed.");
            return true;
        } else {
            System.out.println("That item is not consumeable!");
            return false;
        }
    }
    
    public void relinquishItem(Item item) {
        playerInventory.remove(item);
        relinquishedInventory.add(item);
    }
    
    public void removeItem(Item item) {
        playerInventory.remove(item);
    }

    public boolean addHeldItem(Item item) {
        if (item.getTags().contains("Holdable") && numHeldItems <= 3) {
            playerInventory.remove(item);
            heldInventory.add(item);
            numHeldItems++;
            System.out.println(item + " -> Now being held.");
            return true;
        } else if (item.getTags().isEmpty() == true) {
            playerInventory.remove(item);
            heldInventory.add(item);
            numHeldItems++;
            System.out.println(item + " -> I sure hope that was holdable, because it is now being held.");
            return true;
        } else if (numHeldItems == 4) {
            System.out.println("You're already holding 3 items, it would be irresponsible to juggle any more!");
            return false;
        } else {
            System.out.println("That item is not holdable!");
            return false;
        }
    }

    public boolean addWornItem(Item item) {
        if (item.getTags().contains("Wearable")) {
            playerInventory.remove(item);
            wornInventory.add(item);
            System.out.println(item + " -> Now being worn.");
            return true;
        } else if (item.getTags().isEmpty() == true) {
            playerInventory.remove(item);
            wornInventory.add(item);
            System.out.println(item + " -> I sure hope that was wearable, because it is now being worn.");
            return true;
        } else {
            System.out.println("That item is not wearable!");
            return false;
        }
    }

    public boolean removeHeldItem(Item item) {
        for (Item held : heldInventory) {
            if (held == item) {
                heldInventory.remove(item);
                playerInventory.add(item);
                numHeldItems--;
                System.out.println(item + " -> No longer being held, returned to Inventory.");
                return true;
            }
        }
        System.out.println("That item is not being held right now!");
        return false;
    }

    public boolean removeWornItem(Item item) {
        for (Item worn : wornInventory) {
            if (worn == item) {
                wornInventory.remove(item);
                playerInventory.add(item);
                System.out.println(item + " -> No longer being worn, returned to Inventory.");
                return true;
            }
        }
        System.out.println("That item is not being worn right now!");
        return false;
    }
    
    public List<Item> getItems() {
        return playerInventory;
    }

    public List<Item> getAllItems() {
        List<Item> allItems = new ArrayList<>();
        allItems.addAll(playerInventory);
        allItems.addAll(heldInventory);
        allItems.addAll(wornInventory);

        return allItems;
    }

    public List<Item> getConsumedItems() {
        return consumedInventory;
    }

    public List<Item> getHeldItems() {
        return heldInventory;
    }

    public List<Item> getRelinquishedItems() {
        return relinquishedInventory;
    }

    public List<Item> getTrashedItems() {
        List<Item> allItems = new ArrayList<>();
        allItems.addAll(consumedInventory);
        allItems.addAll(relinquishedInventory);

        return allItems;
    }

    public List<Item> getWornItems() {
        return wornInventory;
    }

    public Item getItemByName(String name) {
        for (Item item : playerInventory) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public Item getHeldByName(String name) {
        for (Item item : wornInventory) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public Item getWornByName(String name) {
        for (Item item : wornInventory) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public Set<String> getTags() {
        Set<String> tags = new HashSet<>();
        for (Item item : playerInventory) {
            tags.addAll(item.getTags());
        }
        return tags;
    }

    public Iterable<Item> exposeInventory() {
        List<Item> allItems = new ArrayList<>();
        allItems.addAll(playerInventory);
        allItems.addAll(heldInventory);
        allItems.addAll(wornInventory);
        
        return Collections.unmodifiableList(allItems);
    }

    public void exposeCommonMethodConsume(Item item) {
        consumeItem(item);
    }

    public void exposeCommonMethodEquip(Item item) {
        addWornItem(item);
    }
    
    public void exposeCommonMethodUse(Item item) {
        addHeldItem(item);
    }
}