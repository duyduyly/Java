# 🧠 Java Basic Syntax Guide

This guide covers the essential syntax and structure of Java for beginners. It includes data types, control flow, methods, classes, and basic I/O with short explanations and examples.


| [Hello World](#-1-hello-world) | [Data Types](#-2-data-types) | [Control Flow](#-3-control-flow)|

| [Methods](#-4-methods) | [Classes, Objects, Interface And Abstract Class](#-5-classes-objects-interface-abstract-class)| [Input And Output](#-6-input-and-output)|

| [Arrays](#-7-arrays) | [Comments](#-8-comments) | [Naming Conventions](#-9-naming-conventions) |

| [4 Core principles in OOP](#4-core-principals-in-oop)

---

## 📦 1. Hello World

This is the simplest Java program. Every Java application must have a `main` method.

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

* `public class HelloWorld` defines a class.
* `main` is the entry point of the application.
* `System.out.println` prints text to the console.

---

## 🧮 2. Data Types

Java has primitive and reference data types.

### 2.1 Primitive Types

```java
public class PrimitiveTypesDemo {
    public static void main(String[] args) {
        byte byteVal = 127;
        short shortVal = 32000;
        int intVal = 2147483647;
        long longVal = 9223372036854775807L;

        float floatVal = 3.14f;
        double doubleVal = 3.14159265359;

        boolean isAvailable = true;
        char initial = 'J';

        System.out.println("byte: " + byteVal);
        System.out.println("short: " + shortVal);
        System.out.println("int: " + intVal);
        System.out.println("long: " + longVal);
        System.out.println("float: " + floatVal);
        System.out.println("double: " + doubleVal);
        System.out.println("boolean: " + isAvailable);
        System.out.println("char: " + initial);
    }
}
```

### 2.2 Reference Type

```java
public class ReferenceTypeDemo {
    public static void main(String[] args) {
        String greeting = "Hello, Java!";
        Integer number = 42; // Wrapper class for int

        System.out.println(greeting);
        System.out.println("Wrapper class example: " + number);

        String[] colors = {"Red", "Green", "Blue"};
        for (String color : colors) {
            System.out.println("Color: " + color);
        }
    }
}
```

### 2.3 Var type
- In Java, the var keyword is used for local variable type inference, introduced in Java 10.
- `🤔 When to use var?`
- Use var when:
    - The type is obvious or doesn't add clarity
    - You want to reduce verbosity (especially with generics)
- Avoid var when:
    - It reduces code readability
    - You want to be explicit about the type for maintainability


```java
    // Syntax: var variableName = expression;
    var name = "John";         // Inferred as String
    var age = 30;              // Inferred as int
    var list = new ArrayList<String>(); // Inferred as ArrayList<String>
```

---

## 🔁 3. Control Flow

Control flow statements manage the execution path of your program.

### if–else block

```java
if (age > 18) {
    System.out.println("Adult");
} else {
    System.out.println("Minor");
}
```

### Ternary Operator
```java
    // condition ? expression1 : expression2
    String result = age > 18 ? "Adult" : "Minor"; // replace for if else block
    System.out.println(result);
```


### if–else if–else else block

```java
if (score >= 90) {
    System.out.println("A grade");
} else if (score >= 80) {
    System.out.println("B grade");
} else {
    System.out.println("C or below");
}
```

### switch

```java
int day = 2;
switch (day) {
    case 1: System.out.println("Monday"); break;
    case 2: System.out.println("Tuesday"); break;
    default: System.out.println("Other day");
}
```

### for loop

```java
for (int i = 0; i < 5; i++) {
    System.out.println(i);
}
```

### while loop

```java
int count = 0;
while (count < 5) {
    System.out.println(count);
    count++;
}
```

### do–while loop

```java
int number = 1;
do {
    System.out.println(number);
    number++;
} while (number <= 5);
```

### break and continue

```java
for (int i = 0; i < 10; i++) {
    if (i == 5) break;
    if (i % 2 == 0) continue;
    System.out.println(i);
}
```

---

## 🧰 4. Methods

Methods define reusable blocks of code.

### 4.1 Basic Method

```java
public static int add(int a, int b) {
    return a + b;
}
```

Call method:

```java
int result = add(5, 3);
```

### 4.2 Method with No Return

```java
public static void greet(String name) {
    System.out.println("Hello, " + name);
}
```

### 4.3 Method Overloading

```java
public static int multiply(int a, int b) {
    return a * b;
}

public static double multiply(double a, double b) {
    return a * b;
}
```

### 4.4 Method with Default Values (via Overloading)

```java
public static void printMessage() {
    printMessage("Hello, World!");
}

public static void printMessage(String message) {
    System.out.println(message);
}
```

### 4.5 Method Returning Boolean

```java
public static boolean isEven(int number) {
    return number % 2 == 0;
}
```

### 4.6 Method with Array Parameter

```java
public static int sumArray(int[] numbers) {
    int sum = 0;
    for (int num : numbers) {
        sum += num;
    }
    return sum;
}
```

### 4.7 Recursive Method

```java
public static int factorial(int n) {
    if (n == 0) return 1;
    return n * factorial(n - 1);
}
```

### 4.8 Calling Methods from Main

```java
public static void main(String[] args) {
    greet("Alice");
    System.out.println("5 + 3 = " + add(5, 3));
    System.out.println("Factorial of 5: " + factorial(5));
}
```

### 4.9 Varargs parameters

```java

//returnType methodName(type... variableName)
public class VarargsExample {
    public static void printNumbers(int... numbers) {
        for (int num : numbers) {
            System.out.println(num);
        }
    }

    public static void main(String[] args) {
        printNumbers(1, 2, 3);          // OK
        printNumbers(10);               // OK
        printNumbers();                 // OK (zero arguments)
    }
}
```

---

## 🧱 5. Classes, Objects, Interface, Abstract Class

### Class
```java
public class Person {
    String name;
    int age;

    public void sayHello() {
        System.out.println("Hello, my name is " + name);
    }
}

Person p = new Person();
p.name = "Bob";
p.age = 30;
p.sayHello();
```

### Interface

- Class can implement multiple interface
- Must Implement class 
```java
interface Walkable {
    void walk();
}

interface Swimmable {
    void swim();
}

class Duck implements Walkable, Swimmable {
    public void walk() { System.out.println("Duck walking"); }
    public void swim() { System.out.println("Duck swimming"); }
}
```

### Java 8+ have Static and Default Interface
Static Interface
```java
interface Utility {
    static int square(int x) {
        return x * x;
    }
}
```

### Default Interface
```java
interface Animal {
    default void sleep() {
        System.out.println("Sleeping...");
    }
}
```

### Functional Interface
```java
@FunctionalInterface
interface Greeting {
    void sayHello();
}

public class Main {
    public static void main(String[] args) {
        Greeting g = () -> System.out.println("Hello!");
        g.sayHello(); // Output: Hello!
    }
}
```
### Abstract Class
An abstract class in Java is a class that cannot be instantiated and may contain abstract methods (methods without implementation).

It's a way to provide partial abstraction and common functionality for subclasses.


```java
abstract class Vehicle {
    int speed = 0;

    abstract void start();   // Abstract method

    void stop() {
        System.out.println("Vehicle stopped.");
    }
}

class Car extends Vehicle {
    @Override
    void start() {
        System.out.println("Car started.");
    }
}

public class Main {
    public static void main(String[] args) {
        Vehicle v = new Car();
        v.start();  // Output: Car started.
        v.stop();   // Output: Vehicle stopped.
    }
}

```

---

## 📥 6. Input and Output

```java
import java.util.Scanner;

Scanner scanner = new Scanner(System.in);
System.out.print("Enter your name: ");
String name = scanner.nextLine();
System.out.println("Hello, " + name);
```

---

## 📚 7. Arrays

### Single Array
```java
int[] numbers = {1, 2, 3, 4};
System.out.println(numbers[0]);

for (int num : numbers) {
    System.out.println(num);
}
```

### Multiple Array (Matrix)
```java
int[][] matrix = {
        {1, 2},
        {3, 4}
};
System.out.println(matrix[0][1]); // Output: 2
```

---

## ✅ 8. Comments

```java
// This is a single-line comment
/*
 This is a multi-line comment
*/
/**
 * @Author: note
 * @Param: int a, int b
 * Document Comment
 * */
```

---

## 🧵 9. Naming Conventions

| Element  | Convention         | Example            |
| -------- | ------------------ | ------------------ |
| Class    | PascalCase         | `MyClass`          |
| Variable | camelCase          | `userName`         |
| Constant | UPPER\_SNAKE\_CASE | `MAX_SIZE`         |
| Method   | camelCase          | `calculateTotal()` |

---
🚀

## 4 Core principals in OOP
`Note: (Short about OOP)`
1. `Encapsulation`
   * Definition: Bundling data (variables) and methods that operate on that data into a single unit (class), and restricting direct access to some of the object's components.
   * Goal: Hide internal implementation details and only expose what is necessary.

```java
public class Person {
    private String name; // private variable

    public void setName(String name) {
        this.name = name;  // setter method
    }

    public String getName() {
        return name;       // getter method
    }
}
```

----
2. `Inheritance`
   * Definition: Allows a class to inherit fields and methods from another class using extends keyword.
   * Goal: Promote code reusability.

```java
class Animal {
    void makeSound() {
        System.out.println("Some sound");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Woof!");
    }
}

```

----

3. `Polymorphism`
   * Definition: Ability of an object to take many forms — especially through method overriding or overloading.
   * Types:
     - Compile-time (Method Overloading)
     - Runtime (Method Overriding)
```java
class Animal {
    void makeSound() {
        System.out.println("Animal sound");
    }
}

class Cat extends Animal {
    @Override
    void makeSound() {
        System.out.println("Meow");
    }
}

```

----
4. `Abstraction`
  * Definition: Hiding complex implementation details and showing only the essential features of an object.
  * Achieved using: Abstract classes and Interfaces.

```java
abstract class Shape {
    abstract void draw();  // abstract method
}

class Circle extends Shape {
    @Override
    void draw() {
        System.out.println("Drawing Circle");
    }
}
```

