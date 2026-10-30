
abstract class Account {

    private final String accountNumber;
    private String ownerName;
    protected long balance;

    public Account(String accountNumber, String ownerName, long balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public long getBalance() {
        return balance;
    }

    public abstract double interestRate();

    public abstract boolean canWithdraw(long amount);

    public boolean withdraw(long amount) {
        if (amount > 0 && canWithdraw(amount)) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

// Savings Account
class SavingsAccount extends Account {

    private long minBalance;

    public SavingsAccount(String accountNumber, String ownerName,
                          long balance, long minBalance) {
        super(accountNumber, ownerName, balance);
        this.minBalance = minBalance;
    }

    @Override
    public double interestRate() {
        return 4.0;
    }

    @Override
    public boolean canWithdraw(long amount) {
        return amount > 0 && balance - amount >= minBalance;
    }
}

// Current Account
class CurrentAccount extends Account {

    private long overdraftLimit;

    public CurrentAccount(String accountNumber, String ownerName,
                          long balance, long overdraftLimit) {
        super(accountNumber, ownerName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public double interestRate() {
        return 0.0;
    }

    @Override
    public boolean canWithdraw(long amount) {
        return amount > 0 && balance - amount >= -overdraftLimit;
    }
}

// Fixed Deposit Account
class FixedDepositAccount extends Account {

    public FixedDepositAccount(String accountNumber, String ownerName,
                               long balance) {
        super(accountNumber, ownerName, balance);
    }

    @Override
    public double interestRate() {
        return 7.0;
    }

    @Override
    public boolean canWithdraw(long amount) {
        return false;
    }
}

// Main class
public class minibank {

    public static void main(String[] args) {

        Account[] accounts = {
            new SavingsAccount("AC0001", "Aarav", 10000, 2000),
            new CurrentAccount("AC0002", "Diya", 5000, 3000),
            new FixedDepositAccount("AC0003", "Riya", 20000)
        };

        // Runtime polymorphism
        System.out.println("Account Interest Rates:");

        for (Account account : accounts) {
            System.out.println(
                account.getAccountNumber() + " - "
                + account.getOwnerName() + " - "
                + account.interestRate() + "%"
            );
        }

        // Pattern matching with instanceof (Java 17+)
        System.out.println("\nSpecial Account Check:");

        for (Account account : accounts) {
            if (account instanceof SavingsAccount savings) {
                System.out.println(
                    "Savings Account minimum balance: Rs. "
                    + savings.getBalance()
                );
            }
        }

        // Test withdrawal rules
        System.out.println("\nWithdrawal Tests:");

        System.out.println("Savings withdrawal: "
                + accounts[0].withdraw(3000));

        System.out.println("Current withdrawal: "
                + accounts[1].withdraw(7000));

        System.out.println("Fixed deposit withdrawal: "
                + accounts[2].withdraw(1000));

        System.out.println("\nFinal Balances:");

        for (Account account : accounts) {
            System.out.println(
                account.getAccountNumber() + ": Rs. "
                + account.getBalance()
            );
        }
    }
}
