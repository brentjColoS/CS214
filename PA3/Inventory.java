import java.util.List;

public interface Inventory {
    void addItem(Item item); // To be deprecated in favor of acquireItem
    void removeItem(Item item); // To be deprecated in favor of relinquishItem
    void acquireItem(Item item);
    void relinquishItem(Item item);
    List<Item> getItems();
    Item getItemByName(String name);
}