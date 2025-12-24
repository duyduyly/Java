package concurrency.atomic_class;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {

    public static int counter = 0;

    public static AtomicInteger atomicCounter = new AtomicInteger(0);

    public static void main(String[] args) {
        countWithPrimitive();
        countWithAtomicClass();


        AtomicInteger ai = new AtomicInteger(10);

        System.out.println(ai.get());               // 10
        ai.set(5);
        System.out.println(ai.getAndSet(100));      // 5, now 100
        System.out.println(ai.incrementAndGet());   // 101
        System.out.println(ai.getAndIncrement());   // 101, now 102
        System.out.println(ai.decrementAndGet());   // 101
        System.out.println(ai.getAndDecrement());   // 101, now 100
        System.out.println(ai.addAndGet(5));        // 105
        System.out.println(ai.getAndAdd(10));       // 105, now 115
        System.out.println(ai.compareAndSet(115, 999)); // true, now 999

    }

    /**
     * …it’s not a single atomic operation. It actually breaks down into 3 steps:
     *      1.Read the value of counter from memory.
     *      2.Increment the value.
     *      3. Write the new value back to memory.
     * When both threads are doing this at the same time, they may read the same value before either writes it back, so one update gets lost.
     * */
    public static void countWithPrimitive() {
        var thread1 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter++;
            }
        });

        var thread2 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter++;
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Primitive Counter: "+counter);
    }

    public static void countWithAtomicClass() {
        var thread1 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                atomicCounter.incrementAndGet();
            }
        });

        var thread2 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                atomicCounter.incrementAndGet();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Atomic Counter: "+atomicCounter);
    }
}
