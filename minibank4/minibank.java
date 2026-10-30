import java.util.regex.Pattern;

class Validator {

    private static final Pattern MOBILE =
            Pattern.compile("^[6-9]\\d{9}$");

    private static final Pattern EMAIL =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private static final Pattern PAN =
            Pattern.compile("^[A-Z]{5}[0-9]{4}[A-Z]$");

    private static final Pattern IFSC =
            Pattern.compile("^[A-Z]{4}0[A-Z0-9]{6}$");

    public static boolean isValidMobile(String mobile) {
        return mobile != null && MOBILE.matcher(mobile).matches();
    }

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL.matcher(email).matches();
    }

    public static boolean isValidPan(String pan) {
        return pan != null && PAN.matcher(pan).matches();
    }

    public static boolean isValidIfsc(String ifsc) {
        return ifsc != null && IFSC.matcher(ifsc).matches();
    }
}

enum TransactionType {
    DEPOSIT,
    WITHDRAW,
    TRANSFER
}

record Command(TransactionType type, String accountNumber, long amount) {
}

class CommandParser {

    public static Command parse(String line) {

        if (line == null || line.trim().isEmpty()) {
            throw new IllegalArgumentException("Command cannot be empty");
        }

        String[] parts = line.trim().split("\\s+");

        if (parts.length != 3) {
            throw new IllegalArgumentException(
                    "Enter command in the format: TYPE ACCOUNT_NUMBER AMOUNT");
        }

        TransactionType type;

        try {
            type = TransactionType.valueOf(parts[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid transaction type");
        }

        String accountNumber = parts[1];
        long amount;

        try {
            amount = Long.parseLong(parts[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Amount must be a whole number");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        return new Command(type, accountNumber, amount);
    }
}

class Account {

    private final String accountNumber;
    private String ownerName;
    private long balance;

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
}

class StatementFormatter {

    public static String buildStatement(Account account) {

        StringBuilder sb = new StringBuilder();

        sb.append("======= MINI BANK STATEMENT =======\n");
        sb.append("Account Number: ")
          .append(account.getAccountNumber()).append("\n");
        sb.append("Owner Name: ")
          .append(account.getOwnerName()).append("\n");
        sb.append("Balance: Rs. ")
          .append(account.getBalance()).append("\n");
        sb.append("===================================");

        return sb.toString();
    }
}
public class minibank {

    public static void main(String[] args) {

        // Test mobile validator
        System.out.println("Mobile Tests:");
        System.out.println(Validator.isValidMobile("9876543210"));
        System.out.println(Validator.isValidMobile("12345"));

        // Test email validator
        System.out.println("\nEmail Tests:");
        System.out.println(Validator.isValidEmail("user@gmail.com"));
        System.out.println(Validator.isValidEmail("user@gmail"));

        // Test PAN validator
        System.out.println("\nPAN Tests:");
        System.out.println(Validator.isValidPan("ABCDE1234F"));
        System.out.println(Validator.isValidPan("abc123"));

        // Test IFSC validator
        System.out.println("\nIFSC Tests:");
        System.out.println(Validator.isValidIfsc("SBIN0001234"));
        System.out.println(Validator.isValidIfsc("SB123"));

        // Parse a command
        System.out.println("\nCommand Parsing:");

        Command command = CommandParser.parse("DEPOSIT AC0001 500");

        System.out.println("Type: " + command.type());
        System.out.println("Account Number: " + command.accountNumber());
        System.out.println("Amount: " + command.amount());

        // Print account statement
        Account account = new Account("AC0001", "Aarav", 5000);

        System.out.println("\n" +
                StatementFormatter.buildStatement(account));
    }
}
