import model.Account;
import model.SavingsAccount;
import service.BankService;
import exception.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankServiceTest {
    @Test void depositIncreasesBalance() throws Exception {
        Account a=new SavingsAccount("Test User",1000,100);
        a.deposit(500); assertEquals(1500,a.getBalance());
    }
    @Test void withdrawalBeyondAvailableLimitThrows() throws Exception {
        Account a=new SavingsAccount("Test User",1000,500);
        assertThrows(InsufficientFundsException.class,()->a.withdraw(600));
    }
    @Test void concurrentDepositsKeepCorrectBalance() throws Exception {
        Account a=new SavingsAccount("Test User",1000,100);
        Thread[] threads=new Thread[10];
        for(int i=0;i<threads.length;i++){threads[i]=new Thread(()->{for(int j=0;j<1000;j++)try{a.deposit(1);}catch(InvalidAmountException e){throw new RuntimeException(e);}});threads[i].start();}
        for(Thread t:threads)t.join(); assertEquals(11000,a.getBalance());
    }
}
