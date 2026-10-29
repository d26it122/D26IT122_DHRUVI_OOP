import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        itemservice service = new itemservice();

        int choice;

        do {
            System.out.println("\n--- Item Management ---");
            System.out.println("1. Add Item");
            System.out.println("2. View Items");
            System.out.println("3. View Count");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter item name: ");
                    String name = sc.nextLine();

                    try {
                        service.addItem(name);
                        System.out.println("Item added successfully");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    for (item item : service.getItems()) {
                        System.out.println(item.getName());
                    }
                    break;

                case 3:
                    System.out.println("Total items: " + service.getCount());
                    break;

                case 4:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid menu choice");
            }

        } while (choice != 4);

        sc.close();
    }
}