package service;
import exception.*;
import model.*;
import util.*;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class BankService {
    private final Repository<Account> repository=new Repository<>();
    private final ConcurrentHashMap<String,Account> concurrentAccounts=new ConcurrentHashMap<>();
    private final Path logFile=Path.of("logs","transactions.log");

    public Account openAccount(String owner,long opening,String type) throws BankException {
        if(owner==null||owner.isBlank()||owner.length()>60)throw new InvalidAmountException("Enter a valid account holder name (1-60 characters).");
        Account account=switch(type.toUpperCase()) {
            case "SAVINGS" -> new SavingsAccount(owner,opening,500);
            case "CURRENT" -> new CurrentAccount(owner,opening,5000);
            case "FD" -> new FixedDepositAccount(owner,opening);
            default -> throw new IllegalArgumentException("Choose SAVINGS, CURRENT or FD.");
        };
        repository.save(account); concurrentAccounts.put(account.getAccountNumber(),account);
        log("OPEN_ACCOUNT|"+account.getAccountNumber()+"|"+opening); return account;
    }
    public Account getAccount(String number) throws AccountNotFoundException {Account a=concurrentAccounts.get(number);if(a==null)throw new AccountNotFoundException(number);return a;}
    public void deposit(String number,long amount) throws BankException {Account a=getAccount(number);a.deposit(amount);log("DEPOSIT|"+number+"|"+amount);}
    public void withdraw(String number,long amount) throws BankException {Account a=getAccount(number);a.withdraw(amount);log("WITHDRAW|"+number+"|"+amount);}
    public void transfer(String fromNumber,String toNumber,long amount) throws BankException {
        Account from=getAccount(fromNumber),to=getAccount(toNumber);
        if(from==to)throw new InvalidAmountException("Source and destination accounts must differ.");
        from.transfer(to,amount);
        log("TRANSFER|"+fromNumber+"->"+toNumber+"|"+amount);
    }
    public String statement(String number) throws AccountNotFoundException {return StatementFormatter.buildStatement(getAccount(number));}
    public ArrayList<Account> listAccounts(){return repository.findAll();}
    public ArrayList<Account> sortedAccounts(){ArrayList<Account> list=repository.findAll();list.sort((a,b)->Long.compare(a.getBalance(),b.getBalance()));return list;}
    public void load(Path file) throws IOException,ClassNotFoundException {for(Account a:StatePersister.load(file)){repository.save(a);concurrentAccounts.put(a.getAccountNumber(),a);}}
    public void save(Path file) throws IOException {StatePersister.save(repository.findAll().toArray(new Account[0]),file);}
    public String report() throws IOException {return ReportGenerator.generate(Path.of("logs"),Path.of("data","end-of-day-report.txt"));}
    public void runBatchDeposit(String number,int tasks,long amount) throws AccountNotFoundException {
        Account a=getAccount(number);TransactionProcessor processor=new TransactionProcessor();
        for(int i=0;i<tasks;i++)processor.submit(()->{try{a.deposit(amount);}catch(InvalidAmountException e){throw new RuntimeException(e);}});
        processor.stop();
    }
    private void log(String line){try{TransactionLog.append(logFile,LocalDateTime.now()+"|"+line);}catch(IOException e){System.err.println("Warning: transaction log could not be written: "+e.getMessage());}}
}
