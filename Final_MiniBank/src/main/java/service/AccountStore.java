package service;
import model.Account;
import java.util.*;
public class AccountStore {
    private final HashMap<String,Account> accounts=new HashMap<>();
    private final ArrayList<String> history=new ArrayList<>();
    public synchronized void add(Account a){accounts.put(a.getAccountNumber(),a);}
    public synchronized Account get(String number){return accounts.get(number);}
    public synchronized ArrayList<Account> getAll(){return new ArrayList<>(accounts.values());}
    public synchronized ArrayList<Account> sortedByBalance(){ArrayList<Account> result=getAll();result.sort(Comparator.comparingLong(Account::getBalance));return result;}
    public synchronized void addHistory(String line){history.add(line);}
    public synchronized ArrayList<String> getHistory(){return new ArrayList<>(history);}
}
