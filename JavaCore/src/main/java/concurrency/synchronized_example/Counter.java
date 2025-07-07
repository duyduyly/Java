package concurrency.synchronized_example;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class Counter {
    private final AtomicInteger atomicCount = new AtomicInteger(0);
    private int withoutSynCount = 0; // Not thread-safe
    private int synCount = 0; // syn

    public void increment() {
        withoutSynCount++;// Not thread-safe
    }

    public void incrementAtomic() {
        atomicCount.incrementAndGet();
    }

    public synchronized void incrementSyn() {
        synCount++;
    }

    public int getAtomicCount() {
        return atomicCount.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                System.out.println("Thread 1");
                counter.increment();
                counter.incrementAtomic();
                counter.incrementSyn();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                System.out.println("Thread 2");
                counter.increment();
                counter.incrementAtomic();
                counter.incrementSyn();
            }
        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                System.out.println("Thread 3");
                counter.increment();
                counter.incrementAtomic();
                counter.incrementSyn();
            }
        });

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();


        System.out.println("Final Count (With Atomic): " + counter.getAtomicCount());
        System.out.println("Final Count (without sync): " + counter.getWithoutSynCount());
        System.out.println("Final Count (With sync): " + counter.getSynCount());
    }
}

