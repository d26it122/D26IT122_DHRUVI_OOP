package model;
import exception.InvalidAmountException;
public class SavingsAccount extends Account {
    private static final long serialVersionUID = 1L;
    private final long minBalance;
    public SavingsAccount(String owner, long opening, long minBalance) throws InvalidAmountException { super(owner, opening); if (minBalance < 0) throw new InvalidAmountException("Minimum balance cannot be negative."); this.minBalance = minBalance; }
    public SavingsAccount(String owner, long minBalance) throws InvalidAmountException { this(owner, 0, minBalance); }
    public long getMinBalance() { return minBalance; }
    @Override public double interestRate() { return 4.0; }
    @Override public boolean canWithdraw(long amount) { return getBalance() - amount >= minBalance; }
}
