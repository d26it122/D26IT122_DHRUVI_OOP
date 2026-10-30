import java.util.*;

class OutOfStockException extends Exception {
    private int shortfall;

    public OutOfStockException(String message, int shortfall) {
        super(message);
        this.shortfall = shortfall;
    }

    public int getShortfall() {
        return shortfall;
    }
}

class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String message) {
        super(message);
    }
}

class Warehouse {

    private Map<String, Integer> stock = new HashMap<>();

    public Warehouse() {
        stock.put("Laptop", 5);
        stock.put("Mouse", 10);
        stock.put("Keyboard", 3);
    }

    public void issue(String item, int qty)
            throws OutOfStockException, InvalidQuantityException {

        if (qty <= 0) {
            throw new InvalidQuantityException(
                "Quantity must be greater than 0."
            );
        }

        int available = stock.getOrDefault(item, 0);

        if (qty > available) {
            int shortfall = qty - available;

            throw new OutOfStockException(
                "Not enough stock for " + item +
                ". Available: " + available,
                shortfall
            );
        }

        stock.put(item, available - qty);

        System.out.println(
            "Issued " + qty + " " + item + "(s) successfully."
        );
    }
}

public class stockissue {

    public static void main(String[] args) {

        Warehouse warehouse = new Warehouse();

        List<String> items = Arrays.asList(
            "Laptop",
            "Mouse",
            "Keyboard",
            "Laptop"
        );

        List<Integer> quantities = Arrays.asList(2,15, 0, 4 );

        for (int i = 0; i < items.size(); i++) {

            String item = items.get(i);
            int qty = quantities.get(i);

            System.out.println(
                "\nRequest: " + item + " - " + qty
            );

            try {
                warehouse.issue(item, qty);

            } catch (OutOfStockException e) {

                System.out.println("Out of stock: " + e.getMessage());
                System.out.println(
                    "Shortfall = " + e.getShortfall()
                );

            } catch (InvalidQuantityException e) {

                System.out.println(
                    "Invalid quantity: " + e.getMessage()
                );
            }
        }

        System.out.println("\nAll requests processed.");
    }
}