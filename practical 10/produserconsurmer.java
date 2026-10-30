class sharedbuffer {
    private int item;
    private boolean available = false;

    // Producer adds item
    synchronized void produce(int value) {
        try {
            while (available) {
                wait();
            }

            item = value;
            available = true;
            System.out.println("Produced: " + item);

            notify();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    // Consumer removes item
    synchronized void consume() {
        try {
            while (!available) {
                wait();
            }

            System.out.println("Consumed: " + item);
            available = false;

            notify();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

class Producer extends Thread {
    sharedbuffer buffer;

    Producer(sharedbuffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            buffer.produce(i);
        }
    }
}

class Consumer extends Thread {
    sharedbuffer buffer;

    Consumer(sharedbuffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            buffer.consume();
        }
    }
}

public class produserconsurmer {
    public static void main(String[] args) {
        sharedbuffer buffer = new sharedbuffer();

        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);

        producer.start();
        consumer.start();
    }
}