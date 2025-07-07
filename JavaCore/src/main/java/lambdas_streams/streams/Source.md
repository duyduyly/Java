## ✅ Stream Creation Methods Summary

| #  | **Source**        | **Method**                                       | **Finite?** | **Description**                                                                                                                |
|----|-------------------|--------------------------------------------------|-------------|--------------------------------------------------------------------------------------------------------------------------------|
| 1  | Collection        | `collection.stream()`                            | yes         | Create stream from collection  [Example 1](#example-1)                                                                         |
| 2  | Array             | `Arrays.stream(array)`                           | yes         | Create Strean from Arrays [Example 2](#example-2)                                                                              |
| 3  | Values            | `Stream.of(varargs)`                             | yes         | Create stream with elements listed in varargs [Example 3](#example-3)                                                          |
| 4  | Infinite Sequence | `Stream.iterate(seed, unaryOperator)`            | no          | Create stream by using seed for first element and then calls UnaryOperator for each subsequent element [Example 4](#example-4) |
| 5  | Infinite Random   | `Stream.generate(supplier)`                      | no          | Create Stream by calling Supplier for each element upon request [Example 5](#example-5)                                        |
| 6  | File Lines        | `Files.lines(Path)`                              | no          | used to read all lines from a file as a stream of strings, one per line. [Example 6](#example-6)                               |
| 7  | Primitive Streams | `IntStream / LongStream`                         | yes         | Create Stream for Int, Long and double [Example 7](#example-7)                                                                 |
| 8  | Parallel Stream   | `collection.parallelStream()`                    | yes         | Create parallel stram from collection [Example 8](#example-8)                                                                  |
| 9  | Stream Empty      | `Stream.empty()`                                 | yes         | Create Stream with zero element                                                                                                | 
| 10 | Infinite Sequence | `Stream.iterate(seed, predicate, unaryOperator)` | Depend      | same as before, but stops if predicate return false  [Example 9](#example-9)                                                   | 

---

## 🧪 Examples

### Example 1

```java
List<String> names = List.of("Alice", "Bob", "Charlie");
names.stream().forEach(System.out::println);
```

---

### Example 2

```java
String[] items = {"A", "B", "C"};
Arrays.stream(items).forEach(System.out::println);
```

---

### Example 3

```java
Stream.of("Java","Python","C++").forEach(System.out::println);
```

---

### Example 4

```java
Stream.iterate(0,n ->n +2).limit(5).forEach(System.out::println);  // Outputs 0, 2, 4, 6, 8
```

---

### Example 5

```java
    Stream.generate(Math::random).limit(3).forEach(System.out::println);
```

---

### Example 6

```java
    Path path = Paths.get("example.txt");
    try(Stream<String> lines = Files.lines(path)){
        lines.forEach(System.out::println);
    }catch(IOException e){
        e.printStackTrace();
    }
```

> ⚠️ Requires `import java.nio.file.*;` and `import java.io_nio2.*;`

---

### Example 7

```java
    IntStream.range(1,5).forEach(System.out::println); // 1, 2, 3, 4
    DoubleStream.of(1.1,2.2,3.3).forEach(System.out::println);
```

---

### Example 8

```java
    List<Integer> nums = List.of(1, 2, 3, 4, 5);
    nums.parallelStream().map(n ->n *n).forEach(System.out::println);
```

### Example 9

- seed: initial element
- hasNext: condition to continue the stream
- next: how to compute the next element

```java
Stream.iterate(0,n ->n< 20,n ->n +2)
        .

forEach(System.out::println);

//similar 
/*
   Stream.iterate(0, n -> n + 2)
 .limit(10)
 .forEach(System.out::println);
 */
```
