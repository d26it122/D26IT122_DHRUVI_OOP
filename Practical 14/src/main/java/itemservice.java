import java.util.ArrayList;

public class itemservice {
    private ArrayList<item> items = new ArrayList<>();

    public void addItem(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be empty");
        }

        items.add(new item(name));
    }

    public int getCount() {
        return items.size();
    }

    public ArrayList<item> getItems() {
        return new ArrayList<>(items);
    }
}