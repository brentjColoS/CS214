import java.util.List;
import java.util.Set;

public interface Inventory {
    void addItem(Item item); // To be deprecated in favor of acquireItem
    void removeItem(Item item); // To be deprecated in favor of relinquishItem
    void acquireItem(Item item);
    void relinquishItem(Item item);
    List<Item> getItems();
    Set<String> getTags();
    Item getItemByName(String name);
    Iterable<Item> exposeInventory();
}