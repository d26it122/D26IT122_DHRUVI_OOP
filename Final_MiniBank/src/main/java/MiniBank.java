
import model.*;
import service.*;
import exception.*;
import util.*;

import java.nio.file.*;
import java.util.*;

import static java.lang.System.out;

public class MiniBank {

    // ==================== CONSTANTS AND OBJECTS ====================

    private static final Path DATA_FILE =
            Path.of("data", "accounts.dat");

    private static final Scanner scanner =
            new Scanner(System.in);

    private static final BankService bank =
            new BankService();


    // ==================== MAIN METHOD ====================

    public static void main(String[] args) {

        out.println("========================================");
        out.println(new BankInfo(
                "MiniBank",
                "CHARUSAT Main Branch"
        ));
        out.println("========================================");

        // Load previously saved accounts
        try {
            bank.load(DATA_FILE);

            out.println(
                    "Loaded saved accounts: "
                    + bank.listAccounts().size()
            );

        } catch (Exception e) {
            out.println(
                    "Starting with an empty account store: "
                    + e.getMessage()
            );
        }

        boolean running = true;

        while (running) {

            printMenu();

            int choice = readInt("Enter choice: ");

            // Practical demonstrations
            if (choice == 9) {

                try {
                    PracticalDemo.run();

                } catch (Exception e) {
                    out.println("Demo error: " + e.getMessage());
                }

                continue;
            }

            // Convert choice into MenuOption
            MenuOption option = switch (choice) {

                case 1 -> MenuOption.OPEN_ACCOUNT;
                case 2 -> MenuOption.DEPOSIT;
                case 3 -> MenuOption.WITHDRAW;
                case 4 -> MenuOption.TRANSFER;
                case 5 -> MenuOption.STATEMENT;
                case 6 -> MenuOption.LIST_ACCOUNTS;
                case 7 -> MenuOption.REPORT;
                case 8 -> MenuOption.EXIT;

                default -> null;
            };

            if (option == null) {
                out.println("Invalid choice. Select 1-9.");
                continue;
            }

            // ==================== MENU OPERATIONS ====================

            try {

                switch (option) {

                    case OPEN_ACCOUNT -> openAccount();

                    case DEPOSIT -> bank.deposit(
                            readText("Account number: "),
                            readLong("Amount: Rs. ")
                    );

                    case WITHDRAW -> bank.withdraw(
                            readText("Account number: "),
                            readLong("Amount: Rs. ")
                    );

                    case TRANSFER -> bank.transfer(
                            readText("From account: "),
                            readText("To account: "),
                            readLong("Amount: Rs. ")
                    );

                    case STATEMENT -> out.println(
                            bank.statement(
                                    readText("Account number: ")
                            )
                    );

                    case LIST_ACCOUNTS -> {

                        if (bank.listAccounts().isEmpty()) {
                            out.println("No accounts found.");
                        } else {
                            bank.sortedAccounts().forEach(out::println);
                        }
                    }

                    case REPORT -> out.println(bank.report());

                    case EXIT -> {

                        bank.save(DATA_FILE);

                        out.println(
                                "Your data has been saved. "
                                + "Thank you for using MiniBank!"
                        );

                        running = false;
                    }
                }

                // Confirmation
                if (running
                        && option != MenuOption.LIST_ACCOUNTS
                        && option != MenuOption.STATEMENT
                        && option != MenuOption.REPORT) {

                    out.println("Operation completed successfully.");
                }

            } catch (InsufficientFundsException e) {

                out.println(
                        "Transaction declined: "
                        + e.getMessage()
                        + " Shortfall: Rs. "
                        + e.getShortfall()
                );

            } catch (BankException e) {

                out.println("Bank error: " + e.getMessage());

            } catch (Exception e) {

                out.println("Error: " + e.getMessage());

            } finally {

                out.println("----------------------------------------");
            }
        }
    }


    // ==================== DISPLAY MENU ====================

    private static void printMenu() {

        out.println("\n========== MINIBANK MENU ==========");
        out.println("1. Open Account");
        out.println("2. Deposit");
        out.println("3. Withdraw");
        out.println("4. Transfer");
        out.println("5. Account Statement");
        out.println("6. List Accounts");
        out.println("7. Bank Report");
        out.println("8. Exit");
        out.println("9. Practical Demonstrations");
        out.println("===================================");
    }


    // ==================== INPUT METHODS ====================

    private static int readInt(String message) {

        out.print(message);

        while (!scanner.hasNextInt()) {

            out.println("Invalid input. Enter a number.");
            scanner.next();
            out.print(message);
        }

        return scanner.nextInt();
    }


    private static long readLong(String message) {

        out.print(message);

        while (!scanner.hasNextLong()) {

            out.println("Invalid amount. Enter a valid number.");
            scanner.next();
            out.print(message);
        }

        return scanner.nextLong();
    }


    private static String readText(String message) {

        out.print(message);

        return scanner.next().trim();
    }


    // ==================== OPEN ACCOUNT ====================

   
private static void openAccount() {

    out.println("\n========== OPEN NEW ACCOUNT ==========");

    String owner = readText("Enter account holder name: ");

    long opening = readLong("Enter opening balance: Rs. ");

    out.println("Account Types:");
    out.println("1. SAVINGS");
    out.println("2. CURRENT");
    out.println("3. FD");

    String type = readText("Enter account type: ");

    try {
        Account account = bank.openAccount(owner, opening, type);

        out.println("\nAccount opened successfully!");
        out.println("Account Number: " + account.getAccountNumber());
        out.println(account);

    } catch (Exception e) {
        out.println("Account could not be opened: " + e.getMessage());
    }
}


}
