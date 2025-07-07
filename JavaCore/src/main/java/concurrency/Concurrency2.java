package concurrency;

import com.github.javafaker.Faker;
import concurrency.entity.Package;
import concurrency.entity.PackageEnums;
import concurrency.entity.Snack;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Concurrency2 {


    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
//        runSingleThread();

//        runMultipleThread();

//        runMultipleThread2();

//        timeOutWhenReturnSubmit();

        getIntInThread();


        ScheduledExecutorService service  = Executors.newSingleThreadScheduledExecutor();
        Runnable taskOne = () -> System.out.println("Hello");
        Callable<String> taskTwo = ()-> "Hi!";

        ScheduledFuture<?> future = service.schedule(taskOne, 2, TimeUnit.SECONDS);
        ScheduledFuture<?> futureTwo = service.schedule(taskTwo, 2, TimeUnit.SECONDS);

        System.out.println(futureTwo.get());

        service.shutdown();
    }


    public static void getIntInThread(){
        var service = Executors.newSingleThreadExecutor();
        try {
            Future<Integer> result = service.submit(()->11*12);
            System.out.println(result.get());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            service.shutdown();
        }
    }

    public static void timeOutWhenReturnSubmit(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<?> result = executorService.submit(()-> {
            int count = 0;
            for (int i = 0; i < 10_000_000; i++) count++; });

        try{
            var value = result.get(1, TimeUnit.MILLISECONDS);
            if (Objects.isNull(value)) System.out.println("Task Completed");;
        }catch (TimeoutException e){
            System.out.println("Task didn't complete in time.");
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
    }

    //Single Thread
    public static void runSingleThread() {
        Thread thread = new Thread(() -> {
            sleep();
            System.out.println("Single Thread: " + Thread.currentThread().getName());
        });

        Thread thread2 = new Thread(() -> {
            sleep();
            System.out.println("Single Thread: " + Thread.currentThread().getName());
        });

        Thread thread3 = new Thread(() -> {
            sleep();
            System.out.println("Single Thread: " + Thread.currentThread().getName());
        });

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> submit = executorService.submit(() -> System.out.println());
        executorService.submit(()-> System.out.println("----------------------------"));
        executorService.submit(()-> System.out.println("Run single thread"));
        executorService.execute(thread);
        executorService.execute(thread2);
        executorService.execute(thread3);
        executorService.shutdown();
    }


    //multiple thread
    //pool == 3
    public static void runMultipleThread() {
        Thread thread = new Thread(() -> {
            sleep();
            System.out.println("Multiple Thread: " + Thread.currentThread().getName());
        });

        Thread thread2 = new Thread(() -> {
            sleep();
            System.out.println("Multiple Thread: " + Thread.currentThread().getName());
        });

        Thread thread3 = new Thread(() -> {
            sleep();
            System.out.println("Multiple Thread: " + Thread.currentThread().getName());
        });

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(()-> System.out.println());
        executorService.submit(()-> System.out.println("----------------------------"));
        executorService.submit(()-> System.out.println("Run with 3 pool and 3 threads"));
        executorService.execute(thread);
        executorService.execute(thread2);
        executorService.execute(thread3);
        executorService.shutdown();
    }


    //multiple thread
    //pool == 3
    //start 3 thread and then, the system will start 3 thread
    public static void runMultipleThread2() {
        Thread thread = new Thread(() -> {
            sleep();
            System.out.println("Multiple Thread: " + Thread.currentThread().getName());
        });

        Thread thread2 = new Thread(() -> {
            sleep();
            System.out.println("Multiple Thread: " + Thread.currentThread().getName());
        });

        Thread thread3 = new Thread(() -> {
            sleep();
            System.out.println("Multiple Thread: " + Thread.currentThread().getName());
        });
        Thread thread4 = new Thread(() -> {
            sleep();
            System.out.println("Multiple Thread: " + Thread.currentThread().getName());
        });
        Thread thread5 = new Thread(() -> {
            sleep();
            System.out.println("Multiple Thread: " + Thread.currentThread().getName());
        });
        Thread thread6 = new Thread(() -> {
            sleep();
            System.out.println("Multiple Thread: " + Thread.currentThread().getName());
        });

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(()-> System.out.println());
        executorService.submit(()-> System.out.println("----------------------------"));
        executorService.submit(()-> System.out.println("Run with 3 pool and 6 threads"));
        executorService.execute(thread);
        executorService.execute(thread2);
        executorService.execute(thread3);
        executorService.execute(thread4);
        executorService.execute(thread5);
        executorService.execute(thread6);


        executorService.shutdown();
    }


    public static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
