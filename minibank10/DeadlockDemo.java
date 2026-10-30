
public class DeadlockDemo {

    // Unsafe transfer: opposite lock ordering can deadlock.
    private static void unsafeTransfer(
            BankAccount from, BankAccount to) {

        synchronized (from) {
            System.out.println(
                Thread.currentThread().getName()
                + " locked account "
                + from.getAccountNumber()
            );

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            synchronized (to) {
                System.out.println("Both locks acquired.");
            }
        }
    }

    public static void runDemo()
            throws InterruptedException {

        System.out.println(
            "\n===== DEADLOCK DEMONSTRATION ====="
        );

        BankAccount a = new BankAccount(101, 5000);
        BankAccount b = new BankAccount(102, 5000);

        Thread t1 = new Thread(
            () -> unsafeTransfer(a, b), "Thread-A"
        );

        Thread t2 = new Thread(
            () -> unsafeTransfer(b, a), "Thread-B"
        );

        // Daemon threads prevent the demonstration
        // from keeping the entire program alive.
        t1.setDaemon(true);
        t2.setDaemon(true);

        t1.start();
        t2.start();

        t1.join(500);
        t2.join(500);

        System.out.println(
            "Thread-A state: " + t1.getState()
        );

        System.out.println(
            "Thread-B state: " + t2.getState()
        );

        System.out.println(
            "\n===== DEADLOCK PREVENTION ====="
        );

        BankAccount c = new BankAccount(201, 5000);
        BankAccount d = new BankAccount(202, 5000);

        Thread safe1 = new Thread(() -> {
            BankAccount.transfer(c, d, 100);
        }, "Safe-Thread-1");

        Thread safe2 = new Thread(() -> {
            BankAccount.transfer(d, c, 200);
        }, "Safe-Thread-2");

        safe1.start();
        safe2.start();

        safe1.join();
        safe2.join();

        System.out.println(
            "Account 201 balance: Rs. "
            + c.getBalance()
        );

        System.out.println(
            "Account 202 balance: Rs. "
            + d.getBalance()
        );

        System.out.println(
            "Safe transfers completed without deadlock."
        );
    }
}
