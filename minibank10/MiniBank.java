
import exception.BankException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

import model.Account;
import model.SavingsAccount;

public class MiniBank {

    public static void main(String[] args)
            throws InterruptedException {
 // ========================================
        // PRACTICAL 8 - PART B
        // ========================================

        System.out.println(
            "\n===== TRANSACTION PROCESSOR ====="
        );

        BankAccount accountA =
            new BankAccount(301, 10000);

        TransactionProcessor processor =
            new TransactionProcessor();

        // Submit multiple transactions to the pool.
        for (int i = 1; i <= 10; i++) {
            processor.submit(
                new Transaction(accountA, "deposit", 100)
            );
        }

        for (int i = 1; i <= 5; i++) {
            processor.submit(
                new Transaction(accountA, "withdraw", 50)
            );
        }

        processor.stop();

        System.out.println(
            "Final balance: Rs. "
            + accountA.getBalance()
        );

        // Producer-consumer demonstration.
        System.out.println(
            "\n===== PRODUCER-CONSUMER ====="
        );

        TransactionBuffer buffer =
            new TransactionBuffer(3);

        BankAccount accountB =
            new BankAccount(302, 1000);

        Runnable stopSignal = () -> {};

        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    Runnable task = buffer.take();

                    if (task == stopSignal) {
                        break;
                    }

                    task.run();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer");

        consumer.start();

        try {
            for (int i = 1; i <= 5; i++) {
                buffer.put(
                    new Transaction(
                        accountB, "deposit", 100
                    )
                );
            }

            // Tell the consumer to stop after all tasks.
            buffer.put(stopSignal);
            consumer.join();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(
            "Consumer final balance: Rs. "
            + accountB.getBalance()
        );

        // Deadlock and its prevention.
        DeadlockDemo.runDemo();
            }}