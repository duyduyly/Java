package concurrency;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(3); // Barrier for 3 parties

        Runnable workerTask = () -> {

            String name = Thread.currentThread().getName();
            System.out.println(name + " is performing the first part of the work.");

            try {
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // code to follow will be performed only after 3 threads finish with the execution of the code above

            System.out.println(name + " is performing the remaining work after the barrier.");
        };

        Thread worker1 = new Thread(workerTask, "Worker 1");
        Thread worker2 = new Thread(workerTask, "Worker 2");
        Thread worker3 = new Thread(workerTask, "Worker 3");
        Thread worker4 = new Thread(workerTask, "Worker 4");

        worker1.start();
        worker2.start();
        worker3.start();
        worker4.start();

    }
}
