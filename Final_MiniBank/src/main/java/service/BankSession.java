package service;
public class BankSession implements AutoCloseable {
    @Override public void close() { System.out.println("Bank session resource closed safely."); }
}
