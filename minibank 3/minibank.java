
import java.util.Objects;

// Customer class
class Customer implements Cloneable {

    private String name;
    private String email;
    private String mobile;
    private final String customerId;
    private Address address;

    private static long customerCounter = 100;

    private static String generateCustomerId() {
        customerCounter++;
        return "CUST" + customerCounter;
    }

    public Customer(String name, String email, String mobile, Address address) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    // Public static nested class
    public static class Address {
        private String line;
        private String city;
        private String pincode;

        public Address(String line, String city, String pincode) {
            this.line = line;
            this.city = city;
            this.pincode = pincode;
        }

        public String getLine() {
            return line;
        }

        public String getCity() {
            return city;
        }

        public String getPincode() {
            return pincode;
        }
    }

    // Clone customer
    @Override
    public Customer clone() {
        try {
            return (Customer) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}

// Account class
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

    public Account(String ownerName, long balance) {
        this.accountNumber = generateAccountNumber();
        this.ownerName = ownerName;
        this.balance = balance;
        this.active = true;
    }

    public Account(String ownerName) {
        this(ownerName, 0);
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

    public void deposit(long amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(long amount) {
        if (amount > 0 && active && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    // Convert account object to readable text
    @Override
    public String toString() {
        return "Account Number: " + accountNumber
                + ", Owner: " + ownerName
                + ", Balance: Rs. " + balance;
    }

    // Compare accounts using account number
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Account)) {
            return false;
        }

        Account other = (Account) o;
        return accountNumber.equals(other.accountNumber);
    }

    // Generate hash code using account number
    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}

// Main class
public class minibank {

    public static void main(String[] args) {

        Account a1 = new Account("Aarav", 5000);
        Account a2 = new Account("Diya", 3000);

        // 1. toString()
        System.out.println("Account Details:");
        System.out.println(a1.toString());
        System.out.println(a2); // toString() called automatically

        // 2. equals()
        System.out.println("\nComparing Accounts:");
        System.out.println("Are accounts equal? " + a1.equals(a2));

        // 3. Nested Address class
        Customer.Address address =
                new Customer.Address("Near University Road",
                        "Ahmedabad", "380015");

        Customer c1 = new Customer(
                "Aarav", "aarav@example.com", "9876543210", address);

        System.out.println("\nCustomer Details:");
        System.out.println("ID: " + c1.getCustomerId());
        System.out.println("Name: " + c1.getName());
        System.out.println("City: " + c1.getAddress().getCity());
        System.out.println("Pincode: " + c1.getAddress().getPincode());

        // 4. clone()
        Customer c2 = c1.clone();
        System.out.println("\nCloned Customer:");
        System.out.println("Name: " + c2.getName());
        System.out.println("ID: " + c2.getCustomerId());

        // 5. instanceof
        System.out.println("\nType Checking:");
        System.out.println("a1 is an Account: " + (a1 instanceof Account));
        System.out.println("c1 is a Customer: " + (c1 instanceof Customer));
        //System.out.println("c1 is an Account: " + (c1 instanceof Account));
    }
}
