
# Lambda and Functional Programing

an interface which has exactly one abstract method


- [Functional Interface & Lambdas [OCA]](#functional-interface-lambdas)
- [Method References [OCP]](#method-References)
- [Built-in Functional Interface [OCP]](#Built-in-functional-interface)
- [Combining Implementation [OCP]](#Combining-Implementation)
- [Functional Interface for Primatives [OCP]](#functional-interface-for-Primatives)



## Functional Interface & Lambdas [OCA]
- an interface which has exactly one abstract method
- function interface can be annotated with @FunctionalInterface
- Java provides many pre-defined functional interface:
    - Supplier
    - Consumer
    - Predicate
    - Functional, etc...;


**Example: **
```Java
@FunctionalInterface
public interface MyInterface {
    public int calculate(int a, int b);
}


class Main {
    public static void main(String[] args) {
        MyInterface myInterfaceA = (a, b) -> a + b;
        System.out.println(myInterfaceA.calculate(10, 20));
    }
}
```

## Method References [OCP]
Shorter way of writing lambda expression

__Example :__
```Java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

@FunctionalInterface
interface Animal {
    void shout(String s);
}

public class MyClass {

    public static void shout(String t,Animal animal) {
        animal.shout(t);
    }

    public static void main(String[] args) {
        String catCall = "Mew";

        Animal animal = s -> System.out.println(s);
        Animal animal2 = System.out::println; // method reference

        shout(catCall, animal);
        //use method reference
        shout(catCall, animal2);


        Calculator lambda = (a,b) -> Math.min(a,b);
        Calculator methodRef = Math::min; //Compiler will automatic to know and insert parameters into method

        System.out.println(lambda.calculate(-3,1));
        System.out.println(methodRef.calculate(-3,1));
    }
}
```

__Complain:__
- System.out::println method References in lambda
- Instand of writing s two times, we just write once because compiler knows that the abstract method of your functional interface has to take one parameter
- the statement is Shorter
- Compiler can understand number of parameter and automatic insertion into your method.


__Method Reference and context__

__Example:__
```Java
@FunctionalInterface
interface Calculator{ public double path(double t);}

//you can see, the interface and class is the same parameter, so you can use reference
class Gravity{
    public static double freeFall(double t){
        final double g = 9.81;
        return 0.5 * g *  t * t;
    }
}

public class MyClass {
    public static void main(String[] args) {
        Calculator methodRefB = t -> Gravity.freeFall(t); //two method is really same, so the class can become implementation method of calculate interface
        Calculator methodRef = Gravity::freeFall;
        System.out.println(methodRef.path(5));
        System.out.println(methodRefB.path(5));

    }

}
```

- The interface calculator have a method is same signature with __method freeFall__ in Gravity Method, so We use the implementation method in __Gravity class__ and implementation for __path method__ in __interface Calculator__

__Summary:__
| Lambda             | Method Reference                                                                |
| ----------------- | ------------------------------------------------------------------ |
| s -> System.out.println(s) | System.out::println |
| (a,b) -> Math.min(a,b) | Math::min |
| t -> Gravity.freeFall(t) | Gravity::freeFall |
| ()-> s.isEmpty() | s::isEmpty |
| s -> new String(s) | s::new |

- The statement is Shorter
- Compiler can understand number of parameter and automatic insertion into your method.
- when a implemented method can use to implement for interface's method.


## Built-in Functional Interface [OCP]
| Functional Interface             | Method signature|Return Type|                                                                
| ----------------- | -------------------- |-------------------- |
| Supplier<T> | get() | T |
| Consumer<T> | accept(T) | void |
| BiConsumer<T,U> | accept(T,U) | void |
| Predicate<T> | test(T) | boolean |
| BiPredicate<T,U> | test(T,U) | boolean |
| Function<T,R> | apply(T) | R |
| BiFunction<T,U,R> | apply(T,U) | R |
| UnaryOperator<T> | apply(T) | T |
| BinaryOperator<T> | apply(T,T) | T |

__Example:__

```java
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MyClass {
    public static void main(String[] args) {
        Supplier<LocalDateTime> dtImpl = () -> LocalDateTime.now();
        System.out.println(dtImpl.get());


        //Consumer, BiConsumer
        Consumer<String> logImpl = str -> {
            if (!str.isEmpty()) {
                System.out.println(str);
            }
        };
        logImpl.accept("Hello World");

        BiConsumer<String, Integer> p = (name, age) -> System.out.println(name + " is " + age + " years old");
        p.accept("Peter", 10);


        //Predicate, BiPredicate
        Predicate<String> checkString = (s) -> {
            if (s.isEmpty()) return false;
            if (s.length() > 200) return false;
            return !s.contains("s");
        };
        System.out.println(checkString.test("Peter"));
        System.out.println(checkString.test(""));

        BiPredicate<LocalDateTime, LocalDateTime> checkLocalDate = (d1, d2) -> {
            if (Objects.isNull(d2)) return false;
            return !d1.toLocalDate().isBefore(d2.toLocalDate());
        };
        LocalDateTime lcdTime1 = LocalDateTime.now();
        LocalDateTime lcdTime2 = lcdTime1.plusDays(1);
        System.out.println(checkLocalDate.test(lcdTime1, lcdTime2));
        lcdTime2 = null;
        System.out.println(checkLocalDate.test(lcdTime1, lcdTime2));

        //Function and BiFunction
        Function<String, BigDecimal> checkAndConvertBigDecimal = (s) -> {
            try {
                if (Objects.isNull(s) || s.isEmpty() || s.isBlank()) return BigDecimal.ZERO;
                return new BigDecimal(s);
            } catch (Exception e) {
                System.out.println("Number Parse Is False: " + e.getMessage());
                return BigDecimal.ZERO;
            }
        };

        System.out.println(checkAndConvertBigDecimal.apply(""));
        System.out.println(checkAndConvertBigDecimal.apply("20"));
        System.out.println(checkAndConvertBigDecimal.apply("Peter"));//error

        BiFunction<String, Integer, String> con = (s, i) -> s + i;
        var myCon = con.apply("Join", 25);
        System.out.println(myCon);


        //UnaryOperation, BiNaryOperation
        UnaryOperator<Integer> negative = n -> -n;
        System.out.println(negative.apply(5));

        UnaryOperator<String> shout = String::toUpperCase;
        System.out.println(shout.apply("Join"));

        BinaryOperator<Double> add = (a,b) -> a + b;
        System.out.println(add.apply(3.5, 1.5));

        BinaryOperator<String> con2 = String::concat;
        System.out.println(con2.apply("Join", "Wayne"));
    }
}
```


## Combining Implementation [OCP]
__Helper Methods Provided by Fi's__
- Consumer
  - andThen()
- Function
  - andThen()
  - compose
- Predicate
  - and()
  - or()
  - negate()

__Example:__
```Java

import java.util.function.Consumer;

public class MyClass {

    public static void main(String[] args) {
        System.out.println("Consumer: ");
        Consumer<String> greet1 = s -> System.out.println("Hello," + s + "!");
        Consumer<String> greet2 = s -> System.out.println("Hello," + s + "!");
        Consumer<String> greetCombined = greet1.andThen(greet2);

        greetCombined.accept("Hello");

        //another way
        System.out.println();
        greet1.andThen(greet2).accept("Hello");
    }
}
```
__Result:__
```bash
Consumer: 
Hello,Hello!
Hello,Hello!

Hello,Hello!
Hello,Hello!
```



__Example 2:__
```Java

import java.util.function.Function;
public class MyClass {

    public static void main(String[] args) {
        System.out.println("Function: ");
        Function<Integer, Integer> square = n -> n * n;
        Function<Integer, Integer> triple = n -> 3 * n;
        Function<Integer, Integer> f1 = square.andThen(triple);
        Function<Integer, Integer> f2 = square.compose(triple);


        System.out.println(f1.apply(5)); // 5*5=25,3*25=75
        System.out.println(f2.apply(5)); //(3*n)*(3*n)=(3*5)*(3*5)=225
    }
}
```
__Result:__
```bash
Function: 
75
225
```

__Example 3:__
```Java

import java.util.function.Predicate;

public class MyClass {

    public static void main(String[] args) {
        System.out.println("Predicate: ");
        Predicate<Integer> gt10 = n -> n > 10;
        Predicate<Integer> lt20 = n -> n < 20;
        Predicate<Integer> p1 = gt10.and(lt20);// gt10 && lt20
        Predicate<Integer> p2 = lt20.or(p1); // gt10 || lt20
        Predicate<Integer> p3 = lt20.negate(); // == !(n<20)

        System.out.println(p1.test(5));
        System.out.println(p2.test(5));
        System.out.println(p3.test(5));
    }
}
```

__Result:__
```bash
Predicate: 
false
true
false
```

## Functional Interface for Primatives [OCP]

Why should use:
-  Performance Improvement (Avoiding Autoboxing)
  - Primitive functional interfaces operate on primitive types directly (int, long, double, etc.)
- Better Memory Efficiency
  - Since primitive functional interfaces deal with primitive types, they avoid creating extra objects (like Integer or Double), leading to better memory efficiency.
- Direct Mapping to Primitive Operations
  - Primitives in Java are efficient because they are directly mapped to low-level machine instructions.
- Cleaner and More Semantically Correct
-  Less Garbage Collection Pressure
   --> you can refer much more with chat GPT


__Common Functional Interface for Primatives__

| Functional Interface             | Return Type|Abstract Method Signature|                                                                
| ----------------- | -------------------- |-------------------- |
|BooleanSupplier| boolean| getAsBoolean()|
|DoubleSupplier| double| getAsDouble()|
|IntSupplier| int| getAsInt()|
|LongSupplier| long| getAsLong()|
|DoubleConsumer| void| accept(double a)|
|IntConsumer| void| accept(int a)|
|LongConsumer| void| accept(long value)|
|DoublePredicate| boolean| test(double value)|
|IntPredicate| boolean| test(int value)|
|LongPredicate| boolean| test(long value)|

__Common Functional Interface for Primatives 2__

| Functional Interface             | Return Type|Abstract Method Signature|                                                                
| ----------------- | -------------------- |-------------------- |
|DoubleFunction<R>| R| apply(double value)|
|IntFunction<R>| R| apply(int value)|
|LongFunction<R>| R| apply(long value)|
|DoubleUnaryOperator| double| applyAsDouble(double value)|
|IntUnaryOperator| int| applyAsInt(int value)|
|LongUnaryOperator| long| applyAsLong(long value)|
|DoubleBinaryOperator| double| applyAsDouble(double v, double w)|
|IntBinaryOperator| int| applyAsInt(int v, int w)|
|LongBinaryOperator| long| applyAsLong(long v, long w)|


__PRIMITIVE-SPECIFIC FUNCTIONAL INTERFACES 1__

| Functional Interface             | Return Type|Abstract Method Signature|                                                                
| ----------------- | -------------------- |-------------------- |
|ToDoubleFunction<T>| double| applyAsDouble(T t)|
|ToIntFunction<T>| int| applyAsInt(T t)|
|ToLongFunction<T>| long| applyAsLong(T t)|
|ToDoubleBiFunction<T, U>| double| applyAsDouble(T t, U u)|
|ToIntBiFunction<T, U>| int| applyAsInt(T t, U u)|
|ToLongBiFunction<T, U>| long| applyAsLong(T t, U u)|


__PRIMITIVE-SPECIFIC FUNCTIONAL INTERFACES 2__

| Functional Interface             | Return Type|Abstract Method Signature|                                                                
| ----------------- | -------------------- |-------------------- |
|DoubleToIntFunction| int| applyAsInt(double value)|
|DoubleToLongFunction| long| applyAsLong(double value)|
|IntToDoubleFunction| double| applyAsDouble(int value)|
|IntToLongFunction| long| applyAsLong(int value)|
|LongToDoubleFunction| double| applyAsDouble(long value)|
|LongToIntFunction| int| applyAsInt(long value)|
|ObjDoubleConsumer<T>| void| accept(T t, double value)|
|ObjIntConsumer<T>| void| accept(T t, int value)|
|ObjLongConsumer<T>| void| accept(T t, long value)|




