package service;
import java.util.LinkedList;
import java.util.Queue;
public class TransactionBuffer {
    private final Queue<Runnable> queue=new LinkedList<>(); private final int capacity;
    public TransactionBuffer(int capacity){if(capacity<1)throw new IllegalArgumentException("Capacity must be positive");this.capacity=capacity;}
    public synchronized void put(Runnable task) throws InterruptedException {while(queue.size()==capacity)wait();queue.add(task);notifyAll();}
    public synchronized Runnable take() throws InterruptedException {while(queue.isEmpty())wait();Runnable task=queue.remove();notifyAll();return task;}
}
