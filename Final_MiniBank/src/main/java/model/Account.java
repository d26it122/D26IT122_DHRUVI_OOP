package model;

import exception.*;
import model.annotation.*;
import java.io.Serializable;
import java.util.Objects;

public abstract class Account implements Transactable, InterestBearing, Comparable<Account>, Serializable {
    private static final long serialVersionUID = 1L;
    private static long accountCounter = 1;
    @Id private final String accountNumber;
    @MaxLength(60) private String ownerName;
    @Positive private long balance;
    private boolean active;

    protected Account(String ownerName, long openingBalance) throws InvalidAmountException {
        if (openingBalance < 0) throw new InvalidAmountException("Opening balance cannot be negative.");
        this.accountNumber = String.format("AC%04d", accountCounter++);
        this.ownerName = Objects.requireNonNull(ownerName, "Owner name");
        this.balance = openingBalance;
        this.active = true;
    }
    protected Account(String ownerName) throws InvalidAmountException { this(ownerName, 0); }
    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public long getBalance() { return balance; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    protected void setBalanceInternal(long balance) { this.balance = balance; }
    public abstract boolean canWithdraw(long amount);

    @Override public synchronized void deposit(long amount) throws InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException("Deposit amount must be greater than zero.");
        if (!active) throw new InvalidAmountException("Account is inactive.");
        balance = Math.addExact(balance, amount);
    }
    @Override public synchronized boolean withdraw(long amount) throws InvalidAmountException, InsufficientFundsException {
        if (amount <= 0) throw new InvalidAmountException("Withdrawal amount must be greater than zero.");
        if (!active) throw new InvalidAmountException("Account is inactive.");
        if (!canWithdraw(amount)) {
            long shortfall = Math.max(0, amount - balance);
            throw new InsufficientFundsException("Withdrawal not allowed. Available balance: Rs. " + balance, shortfall);
        }
        balance -= amount;
        return true;
    }
    public void transfer(Account to, long amount) throws exception.BankException {
        if (to == null || to == this) throw new exception.InvalidAmountException("A different destination account is required.");
        if (!to.isActive()) throw new exception.InvalidAmountException("Destination account is inactive.");
        Account first = accountNumber.compareTo(to.accountNumber) < 0 ? this : to;
        Account second = first == this ? to : this;
        try {
            synchronized (first) {
                synchronized (second) {
                    withdraw(amount);
                    to.deposit(amount);
                }
            }
        } catch (exception.BankException e) {
            throw e;
        } finally {
            // The JVM releases both monitors when the synchronized blocks exit.
        }
    }
    @Override public int compareTo(Account other) { return accountNumber.compareTo(other.accountNumber); }
    @Override public boolean equals(Object obj) { return this == obj || (obj instanceof Account other && accountNumber.equals(other.accountNumber)); }
    @Override public int hashCode() { return accountNumber.hashCode(); }
    @Override public String toString() { return accountNumber + " | " + ownerName + " | Balance: Rs. " + balance + " | Active: " + active; }
}
