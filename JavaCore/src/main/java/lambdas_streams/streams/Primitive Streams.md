# Primitive Stream

| [Unique Primitive Streams Methods](#unique-primitive-streams-methods) | [Mapping Streams](#mapping-streams) | [Summarizing Statistics](#summarizing-statistics) | <br/>

### **🍀 Why should we use Primitive Stream?**

| Feature                     | `Stream<T>`          | `IntStream` / `LongStream` / `DoubleStream` |
|-----------------------------|----------------------|---------------------------------------------|
| Memory usage                | Higher (boxed types) | Lower (primitive types)                     |
| Speed                       | Slower               | Faster                                      |
| Numeric methods             | Manual               | Built-in                                    |
| Parallel stream performance | Slower               | Optimized                                   |

### 👽 What is Primitive Stream?
- primitive streams (IntStream, LongStream, DoubleStream) are specializations of the general Stream<T> interface and are designed specifically for handling primitive data types efficiently.
- It's more convenient to use primitive streams
- There are three types of primitive streams:
  - `IntStream` - used for `int`, `short`, `byte` and `char`
  - `LongStream` - used for `long`
  - `DoubleStream` - used for `double` and `float`
- Only difference is that `primitive streams` have some `unique methods`

------------------
## Unique Primitive Streams Methods
| Method                                                            | Primitive stream                    | What it does                                                               |
|-------------------------------------------------------------------|-------------------------------------|----------------------------------------------------------------------------|
| [OptionalDouble average() ](#average)                             | IntStream, LongStream, DoubleStream | Arithmetic mean of elements                                                |
| [Stream<T> boxed()](#boxed)                                       | IntStream, LongStream, DoubleStream | Stream<T> where T is wrapper class associated with primitive value         |
| [OptionalInt max()](#max-and-min)                                 | IntStream                           | Maximum element of the stream                                              |
| [OptionalLong max()](#max-and-min)                                | LongStream                          | Maximum element of the stream                                              |
| [OptionalDouble max()](#max-and-min)                              | DoubleStream                        | Maximum element of the stream                                              |
| [OptionalInt min()](#max-and-min)                                 | IntStream                           | Minimum element of the stream                                              |
| [OptionalLong min()](#max-and-min)                                | LongStream                          | Minimum element of the stream                                              |
| [OptionalDouble min()](#max-and-min)                              | DoubleStream                        | Minimum element of the stream                                              |
| [IntStream range(int a, int b)](#range-and-rangeclosed)           | IntStream                           | Returns `IntStream` from a (inclusive) to b (exclusive)                    |
| [LongStream range(long a, long b) ](#range-and-rangeclosed)       | LongStream                          | Returns `LongStream` from a (inclusive) to b (exclusive)                   |
| [IntStream rangeClosed(int a, int b)](#range-and-rangeclosed)     | IntStream                           | Returns `IntStream` from a (inclusive) to b (inclusive)                    |
| [LongStream rangeClosed(long a, long b)](#range-and-rangeclosed)  | LongStream                          | Returns `LongStream` from a (inclusive) to b (inclusive)                   |
| [int sum() ](#sum)                                                | IntStream                           | Returns sum of elements in stream                                          |
| [long sum()](#sum)                                                | LongStream                          | Returns sum of elements in stream                                          |
| [double sum()](#sum)                                              | DoubleStream                        | Returns sum of elements in stream                                          |
| [IntSummaryStatistics summaryStatistics()](#summarystatistics)    | IntStream                           | Returns object containing numerous stream statistics (avg, min, max, etc.) |
| [LongSummaryStatistics summaryStatistics()](#summarystatistics)   | LongStream                          | Returns object containing numerous stream statistics (avg, min, max, etc.) |
| [DoubleSummaryStatistics summaryStatistics()](#summarystatistics) | DoubleStream                        | Returns object containing numerous stream statistics (avg, min, max, etc.) |

#
### average()
````java
OptionalDouble avg = IntStream.of(1, 2, 3, 4, 5).average();
avg.ifPresent(System.out::println); // Output: 3.0
````

#
### boxed()
````java
Stream<Integer> stream = IntStream.of(1, 2, 3).boxed();
stream.forEach(System.out::println); // Output: 1 2 3
````

#
### max() and min()
````java
OptionalInt max = IntStream.of(3, 5, 1).max();
OptionalInt min = IntStream.of(3, 5, 1).min();
System.out.println(max.getAsInt()); // Output: 5
System.out.println(min.getAsInt()); // Output: 1
````

#
### range() and rangeClosed()
````java
IntStream.range(1, 5).forEach(System.out::print);      // Output: 1234
IntStream.rangeClosed(1, 5).forEach(System.out::print); // Output: 12345
````

#
### sum()
````java
int total = IntStream.of(1, 2, 3).sum();
System.out.println(total); // Output: 6
````

#
### summaryStatistics()
````java
IntSummaryStatistics stats = IntStream.of(1, 2, 3, 4).summaryStatistics();
System.out.println(stats.getAverage()); // Output: 2.5
System.out.println(stats.getMax());     // Output: 4
System.out.println(stats.getMin());     // Output: 1
System.out.println(stats.getSum());     // Output: 10
````

------------------

## Mapping Streams
| Source stream class | To create: Stream | To create: DoubleStream | To create: IntStream | To create: LongStream |
|---------------------|-------------------|-------------------------|----------------------|-----------------------|
| Stream<T>           | map()             | mapToDouble()           | mapToInt()           | mapToLong()           |
| DoubleStream        | mapToObj()        | map()                   | mapToInt()           | mapToLong()           |
| IntStream           | mapToObj()        | mapToDouble()           | map()                | mapToLong()           |
| LongStream          | mapToObj()        | mapToDouble()           | mapToInt()           | map()                 |

------------------
#
## Summarizing Statistics
| Method                                      | Primitive stream                    | What it does                                                               |
|---------------------------------------------|-------------------------------------|----------------------------------------------------------------------------|
| IntSummaryStatistics summaryStatistics()    | IntStream                           | Returns object containing numerous stream statistics (avg, min, max, etc.) |
| LongSummaryStatistics summaryStatistics()   | LongStream                          | Returns object containing numerous stream statistics (avg, min, max, etc.) |
| DoubleSummaryStatistics summaryStatistics() | DoubleStream                        | Returns object containing numerous stream statistics (avg, min, max, etc.) |

### 🍉Terminal Methods for Statistics
| Method       | Return Type         | What it does                                                                                             |
|--------------|---------------------|----------------------------------------------------------------------------------------------------------|
| getCount()   | long                | gives number of values                                                                                   |
| getAverage() | double              | returns an average value  or 0 if the stream is empty                                                    |
| getSum()     | double or long      | returns a sum                                                                                            |
| getMin()     | double, int or long | returns the smallest number (if the stream is empty returns the largest numeric value based on the type) |
| getMax()     | double, int or long | returns the largest number (if the stream is empty returns the smallest numeric value based on the type) |

__Example:__
```java
public static void main(String[] args) {
    var intStream = IntStream.of(7, 2, -4, 11, 27);
    IntSummaryStatistics stats = intStream.summaryStatistics();
    System.out.println(stats.getCount());
    System.out.println(stats.getAverage());
    System.out.println(stats.getSum());
    System.out.println(stats.getMin());
    System.out.println(stats.getMax());
}
```
```text
5
8.6
43
-4
27
```

## Primitive note
- It avoids `additional cost associated` with autoboxing/unboxing.
- Remember that `primitive` and `object` versions of data types (i.e. int and Integer, double and Double, etc.) are `not really compatible` with each other in java. They are made compatible through the extra step of` auto-boxing`/`unboxing`. Thus, if you have a stream of primitive ints and if you try to use the object versions of Stream and Function (i.e. Stream<Integer> and Function<Integer, Integer>, you will `incur the cost of boxing and unboxing` the elements