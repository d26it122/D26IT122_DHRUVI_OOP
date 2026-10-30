import exception.BankException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

import model.Account;
import model.SavingsAccount;
import model.CurrentAccount;
import model.FixedDepositAccount;

import service.Premium;

public class MiniBank {

    // ----------------------------------------
    // AutoCloseable class
    // ----------------------------------------

    static class BankSession implements AutoCloseable {

        public BankSession() {
            System.out.println(
                    "Bank session opened."
            );
        }

        @Override
        public void close() {
            System.out.println(
                    "Bank session closed."
            );
        }
    }

    public static void main(String[] args) {

        System.out.println(
                "================================"
        );
        System.out.println(
                "          MiniBank"
        );
        System.out.println(
                "================================"
        );

        SavingsAccount savings =
                new SavingsAccount(
                        "Rahul",
                        5000,
                        1000
                );

        CurrentAccount current =
                new CurrentAccount(
                        "Priya",
                        3000,
                        2000
                );

        FixedDepositAccount fixedDeposit =
                new FixedDepositAccount(
                        "Amit",
                        10000
                );

        // ----------------------------------------
        // 1. Successful deposit
        // ----------------------------------------

        System.out.println(
                "\n----- Successful Deposit -----"
        );

        try {

            savings.deposit(1000);

            System.out.println(
                    "Deposit successful."
            );

            System.out.println(
                    "New balance: Rs. "
                            + savings.getBalance()
            );

        } catch (InvalidAmountException e) {

            System.out.println(
                    "Deposit failed: "
                            + e.getMessage()
            );
        }

        // ----------------------------------------
        // 2. Invalid deposit
        // ----------------------------------------

        System.out.println(
                "\n----- Invalid Deposit -----"
        );

        try {

            savings.deposit(-500);

        } catch (InvalidAmountException e) {

            System.out.println(
                    "Deposit failed: "
                            + e.getMessage()
            );

        } finally {

            System.out.println(
                    "Deposit operation finished."
            );
        }

        // ----------------------------------------
        // 3. Insufficient funds
        // ----------------------------------------

        System.out.println(
                "\n----- Insufficient Funds -----"
        );

        try {

            savings.withdraw(10000);

        } catch (InsufficientFundsException e) {

            System.out.println(
                    "Withdrawal failed: "
                            + e.getMessage()
            );

            System.out.println(
                    "Shortfall: Rs. "
                            + e.getShortfall()
            );

        } catch (InvalidAmountException e) {

            System.out.println(
                    "Invalid amount: "
                            + e.getMessage()
            );

        } finally {

            System.out.println(
                    "Withdrawal operation finished."
            );
        }

        // ----------------------------------------
        // 4. Invalid withdrawal
        // ----------------------------------------

        System.out.println(
                "\n----- Invalid Withdrawal -----"
        );

        try {

            savings.withdraw(0);

        } catch (InsufficientFundsException e) {

            System.out.println(
                    "Insufficient funds: "
                            + e.getMessage()
            );

        } catch (InvalidAmountException e) {

            System.out.println(
                    "Invalid withdrawal: "
                            + e.getMessage()
            );

        } finally {

            System.out.println(
                    "Withdrawal operation finished."
            );
        }

        // ----------------------------------------
        // 5. Successful transfer
        // ----------------------------------------

        System.out.println(
                "\n----- Transfer -----"
        );

        try {

            savings.transfer(current, 1000);

            System.out.println(
                    "Transfer successful."
            );

            System.out.println(
                    "Rahul balance: Rs. "
                            + savings.getBalance()
            );

            System.out.println(
                    "Priya balance: Rs. "
                            + current.getBalance()
            );

        } catch (BankException e) {

            System.out.println(
                    "Transfer failed: "
                            + e.getMessage()
            );

        } finally {

            System.out.println(
                    "Transfer handling completed."
            );
        }

        // ----------------------------------------
        // 6. Failed transfer
        // ----------------------------------------

        System.out.println(
                "\n----- Failed Transfer -----"
        );

        try {

            savings.transfer(
                    current,
                    100000
            );

        } catch (BankException e) {

            System.out.println(
                    "Transfer failed: "
                            + e.getMessage()
            );

        } finally {

            System.out.println(
                    "Transfer handling completed."
            );
        }

        // ----------------------------------------
        // 7. Try-with-resources
        // ----------------------------------------

        System.out.println(
                "\n----- Try-With-Resources -----"
        );

        try (BankSession session =
                     new BankSession()) {

            System.out.println(
                    "Performing banking operation..."
            );

        } catch (Exception e) {

            System.out.println(
                    "Session error: "
                            + e.getMessage()
            );
        }

        System.out.println(
                "\nMiniBank finished."
        );
    }
}