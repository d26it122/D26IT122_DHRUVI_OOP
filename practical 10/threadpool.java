import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class threadpool {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 10; i++) {

            final int taskId = i;

            executor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(
                        "Task " + taskId + " is running on "
                        + Thread.currentThread().getName()
                    );

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Task " + taskId + " interrupted");
                    }

                    System.out.println(
                        "Task " + taskId + " completed by "
                        + Thread.currentThread().getName()
                    );
                }
            });
        }

        executor.shutdown();

        try {
            if (executor.awaitTermination(30, java.util.concurrent.TimeUnit.SECONDS)) {
                System.out.println("All tasks completed.");
            } else {
                System.out.println("Some tasks are still running.");
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
    }
}