
class Customer {

    private String name;
    private String email;
    private String mobile;
    private final String customerId;

    private static long customerCounter = 100;

    private static String generateCustomerId() {
        customerCounter++;
        return "CUST" + customerCounter;
    }

    public Customer(String name, String email, String mobile) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.customerId = generateCustomerId();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getCustomerId() {
        return customerId;
    }
}

class Account {

    private final String accountNumber;
    private String ownerName;
    private long balance;
    private boolean active;

    private static int accountCounter = 0;

    private static String generateAccountNumber() {
        accountCounter++;
        return String.format("AC%04d", accountCounter);
    }

    // Constructor with opening balance
    public Account(String ownerName, long balance) {
        this.accountNumber = generateAccountNumber();
        this.ownerName = ownerName;
        this.balance = balance;
        this.active = true;
    }

    // Constructor with default balance 0
    public Account(String ownerName) {
        this(ownerName, 0);
    }

    public void deposit(long amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public boolean withdraw(long amount) {
        if (amount > 0 && balance >= amount && active) {
            balance -= amount;
            return true;
        }
        return false;
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

    public boolean isActive() {
        return active;
    }
}

public class minibank {

    public static void main(String[] args) {

        Account[] accounts = new Account[3];

        accounts[0] = new Account("Aarav", 1000);
        accounts[1] = new Account("Vihaan");
        accounts[2] = new Account("Diya", 2000);

        accounts[0].deposit(500);
        accounts[1].deposit(1000);

        boolean result1 = accounts[0].withdraw(200);
        boolean result2 = accounts[1].withdraw(1500);

        System.out.println("Withdrawal from Aarav's account: " + result1);
        System.out.println("Withdrawal from Vihaan's account: " + result2);

        System.out.println("\n--- Account Details ---");

        for (Account account : accounts) {
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Owner: " + account.getOwnerName());
            System.out.println("Balance: Rs. " + account.getBalance());
            System.out.println("Active: " + account.isActive());
            System.out.println();
        }
    }
}
