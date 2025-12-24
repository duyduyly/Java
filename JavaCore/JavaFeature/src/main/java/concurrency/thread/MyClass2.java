package concurrency.thread;

public class MyClass2 implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread :"+ Thread.currentThread().getName() + " is being executed.");
    }

    public static void main(String[] args) {
        new Thread(new MyClass2()).start();
        new Thread(new MyClass2()).start();

        //because Runnable is Functional interface, so you can use lambda to write implementation.
        new Thread(()->{
            System.out.println("Thread :"+ Thread.currentThread().getName() + " is being executed.");
        }).start();
        new Thread(()->{
            System.out.println("Thread :"+ Thread.currentThread().getName() + " is being executed.");
        }).start();

    }
}
