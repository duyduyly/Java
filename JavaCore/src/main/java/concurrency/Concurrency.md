## Concurrency

| [Basic Terminology](#basic-terminology) | [Thread Concurrency](#thread-concurrency) | [Thread life cycle](#threads-life-cycle) | <br/>
| [Creating thread](#creating-thread) | [Thread Methods](#thread-methods)  | [Concurrency Api](#concurrency-api) | <br/>
| [Future instance](#futurev-instance) | [Future Interface Methods](#futurev-interface-methods) | [Callable Interface](#callable-interface) | <br/>
| [Scheduler Task](#scheduling-tasks) | [Scheduling Thread Pool](#scheduling-thread-pool) | [Atomic Class](#atomic-class)

## Basic Terminology
- __Thread__ - `smallest unit of execution` that can be scheduled by the OS
- __Process__ - `group of associated threads` that executed that execute in the same shred env
  - `single-threaded` process(Only one thread)
  - `multiple-threaded` process (more than one threads)
- __Sheared environment__ - threads in same process share the same memory space
  - these threads can communicate directly with one another.
- __Task__ - `single unit of work` performed by the thread
  - usually implemented as `a lambda expression` in Java
  - thread can complete `multiple independent tasks`, but only one at a time
- __Sheared memory__ 
  - `static variable`. `plus instance` and `local variables` passed to a thread
  - (remember, static variables are shared among all instances of a class)
  - `if` one` thread update` the `value` of `static member`, this information becomes `immediately available` for other threads `within the process`.
  - `Notice:` If a variable (`static`, `instance` or `local`) is `used` in `all threads`, it `shouldn't change` value in any thread because it can produce a `wrong result`

----------------------

## Thread Concurrency
- Property executing multiple threads and processes at the same time
- Number of threads can exceed number of available CPU's 
  - in that case OS uses thread scheduler to determine which threads should be currently executing
- __Content switch__ occurs then thread's alloted time is complete, but the thread has not fished processing
  - it's a process of storing thread's current state and later restoring the state
  - it's good thread scheduler minimizes the number of context switching
- __Thread priority__ is a numeric value associated with a thread.
  - used by thread scheduler to determine which thread should be executing

## Thread's Life Cycle
- after a thread is created it exists in one of six states
  - `NEW` - created but not started
  - `RUNNABLE` - running or able to run
  - `TERMINATED` - task completed 
  - `BLOCKED` - waiting to enter synchronized block
  - `WAITING` - waiting indefinitely until notified
  - `TIMED_WAITING` - waiting a specified time

- `NEW` → `RUNNABLE` → `RUNNING` → `BLOCKED/WAITING` → `TERMINATED`

----------------------

## Creating thread

- there are three ways to create a thread
  1. Extend Thread class
  2. Implement Runnable interface
  3. Implement Callable interface(requires ExecutorService)

__Example 1: Extend Thread__
```java
public class MyClass extends Thread { // the first Extend Thread Class

    //Second override run() method to write your implement
    //you call start() method to run the run method
    @Override
    public void run() {
        System.out.println("Thread :" + getName() + " is being executed.");
    }

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.start();

        MyClass myClass2 = new MyClass();
        myClass2.start();

        MyClass myClass3 = new MyClass();
        myClass3.start();

        MyClass myClass4 = new MyClass();
        myClass4.start();

        MyClass myClass5 = new MyClass();
        myClass5.start();
    }
}
```

#
__Example 2: Implement Runnable interface__
```java
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
```
#
__Example 3: Implement Callable interface(requires ExecutorService)__
```java
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MyTask implements Callable<String> {
    private int id;

    public MyTask(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(1000); // simulate delay
        return "Task " + id + " completed by " + Thread.currentThread().getName();
    }
}

public class CallableExample {
    public static void main(String[] args) throws Exception {
        // Create a thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit tasks
        Future<String> future1 = executor.submit(new MyTask(1));
        Future<String> future2 = executor.submit(new MyTask(2));
        Future<String> future3 = executor.submit(new MyTask(3));

        // Retrieve results (blocks until task is done)
        System.out.println(future1.get());
        System.out.println(future2.get());
        System.out.println(future3.get());

        executor.shutdown(); // Always shut down the executor
    }
}
```

## Thread Methods
| Method        | Description                              |
|---------------|------------------------------------------|
| `start()`     | Starts the thread                        |
| `run()`       | Contains the code to execute             |
| `sleep(ms)`   | Pauses thread for given milliseconds     |
| `join()`      | Waits for the thread to finish           |
| `isAlive()`   | Checks if thread is still running        |
| `interrupt()` | Interrupts a thread (usually to stop it) |

__Example:__ [MyClass3.java](thread/MyClass3.java)
```java
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
```
```text
Thread :Thread-0 is being executed.
Thread Sleep 20 seconds
Is Active :true
Add Extra 2 seconds
Thread interrupted
```

----------------
## Concurrency API
- Can be used by importing java.until.concurrent package
- This package includes ExecutorService interface
  - This interface defines services which create and manage threads 
  - Includes features like thread pooling, thread scheduling, etc.

__Example:__ [Concurrency2.java](Concurrency2.java)


## Future<V> instance
- There are two ways you can execute Runnable task
  1. using execute(Runnable task) method
  2. using summit(Runnable task) method
- The difference is that submit() returns a value
  - this value is instance of a special interface called Future<V>
  - this instance can be used to determine the result of the execution

## Future<v> Interface methods
| Method Signature                                | Description                                                                | Example Usage                                      |
|-------------------------------------------------|----------------------------------------------------------------------------|----------------------------------------------------|
| `V get()`                                       | Waits if necessary for the task to complete and returns the result.        | `String result = future.get();`                    |
| `V get(long timeout, TimeUnit unit)`            | Waits for the result, but only up to the specified timeout.                | `String result = future.get(2, TimeUnit.SECONDS);` |
| `boolean isDone()`                              | Returns `true` if the task is completed (either normally or by exception). | `if (future.isDone()) { ... }`                     |
| `boolean isCancelled()`                         | Returns `true` if the task was cancelled before it completed.              | `if (future.isCancelled()) { ... }`                |
| `boolean cancel(boolean mayInterruptIfRunning)` | Attempts to cancel execution.                                              | `future.cancel(true);`                             |

## Callable Interface
- Similar to Runnable, except:
  - method you need to implement is called 'call()':
  - call() method returns a value and can throw a checked exception
- ExecutorService includes overloaded version of the submit() method
  - you can pass callable object to submit() and get Future<T> instance
- when passing runnable, get() returns null if the task is complete
  - with Callable, get() returns the matching generic type

## Scheduling Tasks
| Method                                                                                | Use to                                                                                                                                            |
|---------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------|
| `schedule(Callable<V> callable, long delay, TimeUnit unit)`                           | creates and executes Callable task after given delay                                                                                              |
| ` schedule(Runnable task, long delay, TimeUnit unit)`                                 | creates and executes Runnable task after given delay                                                                                              |
| ` scheduleAtFixedRate(Runnable task, long initDelay, long period, TimeUnit unit)`     | creates and executes Runnable task after initial delay and creating new task every period  value that passes                                      |
| ` scheduleWithFixedDelay(Runnable task, long initDelay, long period, TimeUnit unit)`  | creates and executes Runnable task after initial delay and subsequently with given delay between termination of on and execution of the next one  |

__Example:__
```java
      ScheduledExecutorService service  = Executors.newSingleThreadScheduledExecutor();
        Runnable taskOne = () -> System.out.println("Hello");
        Callable<String> taskTwo = ()-> "Hi!";

        ScheduledFuture<?> future = service.schedule(taskOne, 20, TimeUnit.SECONDS);
        ScheduledFuture<?> futureTwo = service.schedule(taskTwo, 15, TimeUnit.SECONDS);
        
        System.out.println(futureTwo.get());

        service.shutdown();
// taskOne is scheduled 20 seconds in the future 
// taskTwo is scheduled 15 minutes in the future

```

## Scheduling Thread Pool
- thread pool is a group of pre-instantiated reusable threads
  - available to perform a set of arbitrary tasks
  
| Method                                                                    | Use to                                                                                                                                |
|---------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------|
| `ExecutorService newCachedThreadPool()`                                   | creates thread pool that creates new threads as needed, but reuses previously constructed threads when they are available             |
| ` ExecutorService newFixedThreadPool(int noOfThreads)`                    | creates thread pool that reuses fixed number of threads operating off shared unbounded queue                                          |
| ` ScheduledExecutorService newScheduledThreadPool(int noOfThreads)`       | reates thread pool that can schedule commands to run after given delay or execute periodically                                        |

## Atomic Class
**For Example:**
````java
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

        System.out.println(counter);
    }
````
```text
1159966
```

- why counter is not 2_000_000?
- __Complain:__
  - …it’s not a single atomic operation. It actually breaks down into 3 steps:
    1. Read the value of counter from memory.
    2. Increment the value.
    3. Write the new value back to memory.
  - When both threads are doing this at the same time, they may read the same value before either writes it back, so one update gets lost.


__Example of Race Condition__

- Let's say counter = 10, and both threads do this:
  - Thread 1 reads 10, increments to 11.
  - Thread 2 also reads 10, increments to 11.
  - `Both write 11` → but the correct result should’ve been 12.
- This kind of lost update leads to a final result less than 2,000,000.
- ==> `(two thread run parallel and new back in memory 11, so counter less than 2_000_000)`


### `Fix this one `
- you can use Atomic Integer
```java
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
```
```text
Atomic Counter: 2000000
```

- In Java, Atomic classes are part of the java.util.concurrent.atomic package and are designed to safely perform operations on single variables in a multithreaded environment without using synchronization.
### Atomic class Table
| Class                           | Description                                    |
|---------------------------------|------------------------------------------------|
| `AtomicInteger`                 | Atomic operations for `int` values             |
| `AtomicLong`                    | For `long` values                              |
| `AtomicBoolean`                 | For `boolean` values                           |
| `AtomicReference<T>`            | For objects of type `T`                        |
| `AtomicIntegerArray`            | Atomic operations on arrays of `int`           |
| `LongAdder` / `LongAccumulator` | Better than `AtomicLong` under high contention |

### Atomic Methods
| Method                                  | Description                                                                                 | Example & Result                                                  |
|-----------------------------------------|---------------------------------------------------------------------------------------------|-------------------------------------------------------------------|
| `get()`                                 | Returns the current value.                                                                  | `ai.get()` → returns `10` if current value is `10`.               |
| `set(int newValue)`                     | Sets to the given value.                                                                    | `ai.set(5)` → value becomes `5`.                                  |
| `getAndSet(int newValue)`               | Atomically sets to new value and returns old value.                                         | `ai.getAndSet(100)` → returns old (e.g. `5`), now value is `100`. |
| `incrementAndGet()`                     | Atomically increments by 1 and returns the new value.                                       | `ai.incrementAndGet()` on 10 → returns `11`.                      |
| `getAndIncrement()`                     | Returns current value, then increments by 1.                                                | `ai.getAndIncrement()` on 10 → returns `10`, now is `11`.         |
| `decrementAndGet()`                     | Atomically decrements by 1 and returns the new value.                                       | `ai.decrementAndGet()` on 10 → returns `9`.                       |
| `getAndDecrement()`                     | Returns current value, then decrements by 1.                                                | `ai.getAndDecrement()` on 10 → returns `10`, now is `9`.          |
| `addAndGet(int delta)`                  | Atomically adds delta and returns new value.                                                | `ai.addAndGet(5)` on 10 → returns `15`.                           |
| `getAndAdd(int delta)`                  | Returns current value, then adds delta.                                                     | `ai.getAndAdd(5)` on 10 → returns `10`, now is `15`.              |
| `compareAndSet(int expect, int update)` | Atomically sets to `update` if current value is `expect`.                                   | `ai.compareAndSet(10, 20)` → returns `true`, now is `20`.         |
| `weakCompareAndSet(...)`                | Like `compareAndSet`, but may fail spuriously. Use in performance-sensitive low-level code. | Rarely used directly in application code.                         |

Example class: [AtomicExample.java](atomic_class/AtomicExample.java)