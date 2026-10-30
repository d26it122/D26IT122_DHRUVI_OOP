
import java.util.Scanner;

public class minibank {

    // Record to store bank information
    record BankInfo(String name, String branch) {}

    // Enum for menu options
    enum MenuOption {
        OPEN_ACCOUNT,
        DEPOSIT,
        WITHDRAW,
        TRANSFER,
        EXIT
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Display bank information
        BankInfo bank = new BankInfo("MiniBank", "CHARUSAT Branch");

        System.out.println("==============================");
        System.out.println("       WELCOME TO MINIBANK");
        System.out.println("==============================");
        System.out.println("Bank: " + bank.name());
        System.out.println("Branch: " + bank.branch());

        int choice;

        do {
            System.out.println("\n1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");

            System.out.print("\nEnter your choice: ");

            choice = sc.nextInt();

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            MenuOption option = MenuOption.values()[choice - 1];

            String message = switch (option) {
                case OPEN_ACCOUNT ->
                    "Open Account — to be implemented in a later lab";
                case DEPOSIT ->
                    "Deposit — to be implemented in a later lab";
                case WITHDRAW ->
                    "Withdraw — to be implemented in a later lab";
                case TRANSFER ->
                    "Transfer — to be implemented in a later lab";
                case EXIT ->
                    "Exiting MiniBank...";
            };

            System.out.println("\n" + message);

        } while (choice != 5);

        System.out.println("Thank you for using MiniBank!");

        sc.close();
    }
}
