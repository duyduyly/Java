package concurrency.thread;

public class MyClass3 implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread :" + Thread.currentThread().getName() + " is being executed.");
    }

    public static void main(String[] args) throws InterruptedException {
        //because Runnable is Functional interface, so you can use lambda to write implementation.
        Thread thread = new Thread(() -> {
            try {
                System.out.println("Thread :" + Thread.currentThread().getName() + " is being executed.");
                System.out.println("Thread Sleep 20 seconds");
                Thread.sleep(20000);
                System.out.println("Thread woke up after 20 seconds");
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
                //here run, because thread Interrupted on 12s
            }
        });

        thread.start();

        Thread.sleep(10000);

        //check thread isActive
        System.out.println("Is Active :" + thread.isAlive());

        //wait extra more 2 seconds
        thread.join(2000);
        System.out.println("Add Extra 2 seconds");

        //terminal thread (Stop Thread)
        thread.interrupt();
    }
}
