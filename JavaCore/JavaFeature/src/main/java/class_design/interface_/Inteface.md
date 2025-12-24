# Interface

## Keyword:
| [Theory](#theory) | [Interface Fields](#interface-fields) | [Functional Interface](#functional-interfaces) | </br>
| [Default Method](#default-method) | [Static Method](#static-method) | [Private Method](#private-method-java-9) |</br>
| [Sealed Interface](#sealed-interface) | [Marker Interface](#marker-interfaces) | [Interface Inheritance Extending](#interface-inheritance-extending-other-interfaces) | <br/>

| [Interface Note](#interface-note) |

----------
## Theory
- An interface is a completely "abstract class" that is used to group related methods with empty bodies
- The interface in Java is a mechanism to achieve `abstraction`.
- By default, variables in an interface are `public`, `static`, and `final`.
- It is used to achieve abstraction and `multiple inheritance` in Java.
- It supports loose coupling (classes depend on behavior, not implementation).
- In other words, interfaces primarily define methods that other classes must implement.
- An interface in Java defines a set of `behaviours` that a class `can implement`, usually representing an `IS-A relationship`, `but not always in every scenario`.

--------
## Interface Fields
```java
interface Constants {
    int MAX_USERS = 100; // Same as: public static final int MAX_USERS = 100;
}
```

--------
## Functional Interfaces
- use for lambda 
- A functional interface has exactly one abstract method.

__Example 1:__
```java
public static void main(String[] args) {
    // Usage:
    runWithCatch("sdsdsd", true, () -> {
        System.out.println("try body block here~!");
        System.out.println(2 / 0);
    });
}

public static void runWithCatch(String message, boolean canThrow, CheckedRunnable runnable) {
    try {
        runnable.run();
    } catch (Exception e) {
        if (canThrow) {
            throw new RuntimeException(message, e);
        }
        // handle once here
        System.err.println(e.getMessage());
    }
}

@FunctionalInterface
interface CheckedRunnable {
    void run() throws Exception;
}
```
----------------
__Example 2:__
```java

public static void main(String[] args) {
    Calculator plus = Integer::sum;
    Calculator minus = (a , b) -> a-b;
    Calculator multiply = (a , b) -> a*b;
    Calculator divide = (a , b) -> a/b;

    System.out.println("Plus: " +plus.compute(10,2));
    System.out.println("Minus: " +minus.compute(10,2));
    System.out.println("Multiply: " +multiply.compute(10,2));
    System.out.println("Divide: " +divide.compute(10,2));
}

@FunctionalInterface
interface Calculator {
    int compute(int a, int b);
}
```
```text
Plus: 12
Minus: 8
Multiply: 20
Divide: 5
```
- ✅ Used in lambda expressions and streams.

--------
## Default Method
- You can provide a default implementation in an interface
```java
interface Vehicle {
    void start();

    default void honk() {
        System.out.println("Beep!");
    }
}
```

--------
## Static Method
- Interfaces can also have static utility methods.
```java
interface MathUtil {
    static int square(int x) {
        return x * x;
    }
}
```

--------
## Private Method (java-9)
- Help you reuse code inside default or static methods.
```java
interface Logger {
    default void logInfo(String msg) {
        log("INFO: " + msg);
    }

    default void logError(String msg) {
        log("ERROR: " + msg);
    }

    private void log(String msg) {
        System.out.println(msg);
    }
}
```

--------
## Sealed Interface
- Allows controlled inheritance — limit who can implement your interface.
```java
public sealed interface Shape permits Circle, Square {}

final class Circle implements Shape {}
final class Square implements Shape {}
```
- ✅ Use case: modeling finite hierarchies (good for enums on steroids, algebraic data types)

--------
## Marker Interfaces
- Interfaces with no methods, used to mark a class with metadata.
```java
public interface Serializable {}
```

--------
##  Interface Inheritance (Extending Other Interfaces)
- Special thing can extend multiple interface
```java
interface Flyable {
    void fly();
}

interface Bird extends Flyable {
    void buildNest();
}
```
-----------------
## Interface Note:
- Interfaces cannot be final.