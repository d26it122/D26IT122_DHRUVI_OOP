package model;

import exception.BankException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

import java.util.Objects;

import service.InterestBearing;
import service.Transactable;

public abstract class Account
        implements Transactable, InterestBearing {

    private final String accountNumber;
    private String ownerName;
    private long balance;
    private boolean active;

    private static long accountCounter = 0;

    private static String generateAccountNumber() {
        accountCounter++;
        return String.format("AC%04d", accountCounter);
    }

    public Account(
            String ownerName,
            long openingBalance) {

        this.accountNumber = generateAccountNumber();
        this.ownerName = ownerName;
        this.balance = openingBalance;
        this.active = true;
    }

    public Account(String ownerName) {
        this(ownerName, 0);
    }

    // ----------------------------------------
    // Deposit
    // ----------------------------------------

    @Override
    public void deposit(long amount)
            throws InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Deposit amount must be greater than zero."
            );
        }

        balance += amount;
    }

    // ----------------------------------------
    // Withdraw
    // ----------------------------------------

    @Override
    public boolean withdraw(long amount)
            throws InsufficientFundsException,
            InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Withdrawal amount must be greater than zero."
            );
        }

        if (!canWithdraw(amount)) {

            long shortfall = amount - balance;

            if (shortfall < 0) {
                shortfall = 0;
            }

            throw new InsufficientFundsException(
                    "Insufficient funds for withdrawal.",
                    shortfall
            );
        }

        balance -= amount;

        return true;
    }

    // ----------------------------------------
    // Transfer
    // ----------------------------------------

    public void transfer(
            Account to,
            long amount) throws BankException {

        try {

            if (to == null) {
                throw new BankException(
                        "Destination account does not exist."
                );
            }

            withdraw(amount);
            to.deposit(amount);

        } catch (InsufficientFundsException |
                 InvalidAmountException e) {

            throw e;

        } finally {

            System.out.println(
                    "Transfer operation completed."
            );
        }
    }

    // ----------------------------------------
    // Abstract methods
    // ----------------------------------------

    public abstract double interestRate();

    public abstract boolean canWithdraw(long amount);

    // ----------------------------------------
    // Getters
    // ----------------------------------------

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

    // ----------------------------------------
    // toString
    // ----------------------------------------

    @Override
    public String toString() {

        return "Account{" +
                "accountNumber='" +
                accountNumber + '\'' +
                ", ownerName='" +
                ownerName + '\'' +
                ", balance=" +
                balance +
                '}';
    }

    // ----------------------------------------
    // equals
    // ----------------------------------------

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof Account)) {
            return false;
        }

        Account account = (Account) o;

        return accountNumber.equals(
                account.accountNumber
        );
    }

    // ----------------------------------------
    // hashCode
    // ----------------------------------------

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}