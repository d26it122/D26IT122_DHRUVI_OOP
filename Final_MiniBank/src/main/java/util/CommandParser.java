package util;
import model.Command;
import model.TransactionType;
public final class CommandParser {
    private CommandParser() { }
    public static Command parse(String line) {
        if(line==null) throw new IllegalArgumentException("Command cannot be empty.");
        String[] parts=line.trim().split("\\s+");
        if(parts.length!=3) throw new IllegalArgumentException("Use: DEPOSIT|WITHDRAW|TRANSFER ACCOUNT_NUMBER AMOUNT");
        try { return new Command(TransactionType.valueOf(parts[0].toUpperCase()),parts[1],Long.parseLong(parts[2])); }
        catch(RuntimeException e){throw new IllegalArgumentException("Invalid command type or amount.",e);}
    }
}
