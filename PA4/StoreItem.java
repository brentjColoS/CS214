import java.util.HashSet;
import java.util.Set;

public class StoreItem implements Item {
    private String name;
    private double price;
    private Set<String> tags;

    public StoreItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.tags = new HashSet<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void addTag(int type) {
        switch (type) {
            case 0:
                tags.add("Holdable");
                break;
            
            case 1:
                tags.add("Consumeable");
                break;

            case 2:
                tags.add("Wearable");
                break;
            
            default:
                tags.add("Misc.");
                break;
        }
    }

    @Override
    public Set<String> getTags() {
        return tags;
    }
}