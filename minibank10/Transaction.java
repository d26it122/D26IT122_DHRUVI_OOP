
public class Transaction implements Runnable {

    private final BankAccount account;
    private final String type;
    private final long amount;

    public Transaction(
            BankAccount account,
            String type,
            long amount) {

        this.account = account;
        this.type = type;
        this.amount = amount;
    }

    @Override
    public void run() {
        try {
            if (type.equalsIgnoreCase("deposit")) {
                account.deposit(amount);

                System.out.println(
                    Thread.currentThread().getName()
                    + " deposited Rs. " + amount
                    + " | Account: "
                    + account.getAccountNumber()
                );

            } else if (
                    type.equalsIgnoreCase("withdraw")) {

                account.withdraw(amount);

                System.out.println(
                    Thread.currentThread().getName()
                    + " withdrew Rs. " + amount
                    + " | Account: "
                    + account.getAccountNumber()
                );

            } else {
                System.out.println(
                    "Invalid transaction type: " + type
                );
            }

        } catch (IllegalArgumentException e) {
            System.out.println(
                "Transaction failed: " + e.getMessage()
            );
        }
    }
}
