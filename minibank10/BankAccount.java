
public class BankAccount {

    private final int accountNumber;
    private long balance;

    public BankAccount(int accountNumber, long balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public synchronized long getBalance() {
        return balance;
    }

    public synchronized void deposit(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Amount must be positive."
            );
        }

        balance += amount;
    }

    public synchronized void withdraw(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Amount must be positive."
            );
        }

        if (balance < amount) {
            throw new IllegalArgumentException(
                "Insufficient balance."
            );
        }

        balance -= amount;
    }

    // Transfers always lock accounts in ascending order.
    public static void transfer(
            BankAccount from,
            BankAccount to,
            long amount) {

        if (from == to) {
            throw new IllegalArgumentException(
                "Cannot transfer to the same account."
            );
        }

        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Amount must be positive."
            );
        }

        BankAccount first =
            from.accountNumber < to.accountNumber
                ? from : to;

        BankAccount second =
            from.accountNumber < to.accountNumber
                ? to : from;

        synchronized (first) {
            synchronized (second) {
                if (from.balance < amount) {
                    throw new IllegalArgumentException(
                        "Insufficient balance."
                    );
                }

                from.balance -= amount;
                to.balance += amount;
            }
        }
    }
}
