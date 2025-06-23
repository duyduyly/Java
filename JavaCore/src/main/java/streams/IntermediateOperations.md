## Intermediate Operations
- Produces a stream as a result
- Can deal with infinite (by returning another infinite stream)
- Can be omitted in a pipeline (unlike source and terminal operations)

## 📋 Intermediate Operations Table

| #  | **Method**            | **Description**                                      | **Jump to Example**                          |
|----|-----------------------|------------------------------------------------------|----------------------------------------------|
| 1  | `filter()`            | Filters elements by predicate                        | [Example 1](#example-1-filter)               |
| 2  | `map()`               | Transforms each element                              | [Example 2](#example-2-map)                  |
| 3  | `flatMap()`           | Flattens nested streams                              | [Example 3](#example-3-flatmap)              |
| 4  | `distinct()`          | Removes duplicate elements                           | [Example 4](#example-4-distinct)             |
| 5  | `sorted()`            | Sorts elements (natural order or comparator)         | [Example 5](#example-5-sorted)               |
| 6  | `peek()`              | Performs an action without modifying the stream      | [Example 6](#example-6-peek)                 |
| 7  | `limit()`             | Truncates stream to max size                         | [Example 7](#example-7-limit)                |
| 8  | `skip()`              | Skips the first N elements                           | [Example 8](#example-8-skip)                 |
| 9  | `mapToInt()`          | Converts objects to IntStream                        | [Example 9](#example-9-maptoint)             |
| 10 | `mapToLong()`         | Converts objects to LongStream                       | [Example 10](#example-10-maptolong)          |
| 11 | `mapToDouble()`       | Converts objects to DoubleStream                     | [Example 11](#example-11-maptodouble)        |
| 12 | `boxed()`             | Boxes primitive stream to object stream              | [Example 12](#example-12-boxed)              |

---

## 🧪 Examples

### Example 1: filter()
```java
Stream.of("a", "bb", "ccc")
      .filter(s -> s.length() > 1)
      .forEach(System.out::println);
```

---

### Example 2: map()
```java
Stream.of("java", "stream")
      .map(String::toUpperCase)
      .forEach(System.out::println);
```

---

### Example 3: flatMap()
```java
Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
      .flatMap(List::stream)
      .forEach(System.out::println);
```

---

### Example 4: distinct()
```java
Stream.of(1, 2, 2, 3)
      .distinct()
      .forEach(System.out::println);
```

---

### Example 5: sorted()
```java
Stream.of(3, 1, 2)
      .sorted()
      .forEach(System.out::println);
```

---

### Example 6: peek()
```java
Stream.of("a", "b", "c")
      .peek(System.out::println)
      .collect(Collectors.toList());
```

---

### Example 7: limit()
```java
Stream.of(1, 2, 3, 4, 5)
      .limit(3)
      .forEach(System.out::println);
```

---

### Example 8: skip()
```java
Stream.of(1, 2, 3, 4, 5)
      .skip(2)
      .forEach(System.out::println);
```

---

### Example 9: mapToInt()
```java
Stream.of("apple", "banana")
      .mapToInt(String::length)
      .forEach(System.out::println);
```

---

### Example 10: mapToLong()
```java
Stream.of("x", "yy")
      .mapToLong(s -> s.length())
      .forEach(System.out::println);
```

---

### Example 11: mapToDouble()
```java
Stream.of(10, 20)
      .mapToDouble(Integer::doubleValue)
      .forEach(System.out::println);
```

---

### Example 12: boxed()
```java
IntStream.range(1, 4)
         .boxed()
         .forEach(System.out::println);
```