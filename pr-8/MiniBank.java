import exception.BankException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

import model.Account;
import model.SavingsAccount;

public class MiniBank {

    public static void main(String[] args)
            throws InterruptedException {

        System.out.println(
                "================================"
        );

        System.out.println(
                "          MiniBank"
        );

        System.out.println(
                "================================"
        );

        // ========================================
        // PRACTICAL 7
        // EXCEPTION HANDLING
        // ========================================

        System.out.println(
                "\n===== PRACTICAL 7 ====="
        );

        Account account1 =
                new SavingsAccount(
                        "Rahul",
                        5000,
                        1000
                );

        Account account2 =
                new SavingsAccount(
                        "Priya",
                        3000,
                        1000
                );

        // ----------------------------------------
        // Invalid deposit
        // ----------------------------------------

        try {

            account1.deposit(-500);

        }
        catch (InvalidAmountException e) {

            System.out.println(
                    "Invalid deposit: "
                            + e.getMessage()
            );
        }

        // ----------------------------------------
        // Insufficient funds
        // ----------------------------------------

        try {

            account1.withdraw(10000);

        }
        catch (InsufficientFundsException e) {

            System.out.println(
                    "Withdrawal failed: "
                            + e.getMessage()
            );

            System.out.println(
                    "Shortfall: Rs. "
                            + e.getShortfall()
            );
        }
        catch (InvalidAmountException e) {

            System.out.println(
                    "Invalid withdrawal: "
                            + e.getMessage()
            );
        }

        // ----------------------------------------
        // Successful withdrawal
        // ----------------------------------------

        try {

            account1.withdraw(1000);

            System.out.println(
                    "Withdrawal successful."
            );

            System.out.println(
                    "Balance: Rs. "
                            + account1.getBalance()
            );

        }
        catch (InsufficientFundsException e) {

            System.out.println(
                    "Withdrawal failed: "
                            + e.getMessage()
            );

        }
        catch (InvalidAmountException e) {

            System.out.println(
                    "Invalid withdrawal: "
                            + e.getMessage()
            );
        }

        // ----------------------------------------
        // Transfer
        // ----------------------------------------

        try {

            account1.transfer(
                    account2,
                    500
            );

            System.out.println(
                    "Transfer successful."
            );

        }
        catch (BankException e) {

            System.out.println(
                    "Transfer failed: "
                            + e.getMessage()
            );
        }
        finally {

            System.out.println(
                    "Transfer operation finished."
            );
        }

        // ----------------------------------------
        // Try-with-resources
        // ----------------------------------------

        System.out.println(
                "\n===== TRY-WITH-RESOURCES ====="
        );

        try (BankResource resource =
                     new BankResource()) {

            resource.use();

        }
        catch (Exception e) {

            System.out.println(
                    "Resource error: "
                            + e.getMessage()
            );
        }

        // ========================================
        // PRACTICAL 8
        // MULTITHREADING
        // ========================================

        System.out.println(
                "\n===== PRACTICAL 8 ====="
        );

        // ----------------------------------------
        // WITHOUT SYNCHRONIZATION
        // ----------------------------------------

        System.out.println(
                "\n===== WITHOUT SYNCHRONIZATION ====="
        );

        Account.setSynchronizedMode(false);

        Account account3 =
                new SavingsAccount(
                        "Rahul",
                        0,
                        0
                );

        int numberOfThreads = 10;
        int times = 1000;
        long amount = 10;

        long expectedBalance =
                numberOfThreads *
                        times *
                        amount;

        Thread[] threads =
                new Thread[numberOfThreads];

        for (int i = 0;
             i < numberOfThreads;
             i++) {

            AccountWorker worker =
                    new AccountWorker(
                            account3,
                            times,
                            amount
                    );

            threads[i] =
                    new Thread(
                            worker,
                            "Worker-" + (i + 1)
                    );

            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println(
                "\nExpected Balance: Rs. "
                        + expectedBalance
        );

        System.out.println(
                "Actual Balance:   Rs. "
                        + account3.getBalance()
        );

        // ----------------------------------------
        // WITH SYNCHRONIZATION
        // ----------------------------------------

        System.out.println(
                "\n===== WITH SYNCHRONIZATION ====="
        );

        Account.setSynchronizedMode(true);

        Account account4 =
                new SavingsAccount(
                        "Priya",
                        0,
                        0
                );

        Thread[] synchronizedThreads =
                new Thread[numberOfThreads];

        for (int i = 0;
             i < numberOfThreads;
             i++) {

            AccountWorker worker =
                    new AccountWorker(
                            account4,
                            times,
                            amount
                    );

            synchronizedThreads[i] =
                    new Thread(
                            worker,
                            "Sync-Worker-" + (i + 1)
                    );

            synchronizedThreads[i].start();
        }

        for (Thread thread :
                synchronizedThreads) {

            thread.join();
        }

        System.out.println(
                "\nExpected Balance: Rs. "
                        + expectedBalance
        );

        System.out.println(
                "Actual Balance:   Rs. "
                        + account4.getBalance()
        );

        System.out.println(
                "\nAll threads completed."
        );
    }
}