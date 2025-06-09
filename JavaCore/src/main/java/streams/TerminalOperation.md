## Terminal Stream Operation

| **Method**                                                          | **when applied on infinite stream** | **Return value** | **Reduction** |
|---------------------------------------------------------------------|-------------------------------------|------------------|---------------|
| `count()`  [🧪](#terminal-example-1)                                | Does not terminate                  | long             | yes           |
| `min()`  / `max()` [🧪](#terminal-example-2)                        | Does not terminate                  | Optional<T>      | yes           |
| `findAny()`, `findFirst()` [🧪](#terminal-example-3)                | Terminates                          | Optional<T>      | no            |
| `allMatch()`, `anyMatch()`, `noneMatch()` [🧪](#terminal-example-4) | Sometimes terminates                | boolean          | no            |
| `forEach()` [🧪](#terminal-example-5)                               | Does not terminate                  | void             | no            |
| `reduce()`  [🧪](#terminal-example-6)                               | Does not terminate                  | varies           | yes           |
| `collect()` [🧪](#terminal-example-7)                               | Does not terminate                  | varies           | yes           |

## 🧪

#
### Terminal Example 1

```java
    long count = Stream.of(1, 2, 3, 4).count();
    System.out.println("Count: "+count);
```
#
### Terminal Example 2

```java
    Optional<Integer> min = Stream.of(5, 2, 9).min(Integer::compare);
    min.ifPresent(System.out::println);
```

#
### Terminal Example 3

```java
    Optional<String> first = Stream.of("one", "two", "three").findFirst();
    first.ifPresent(System.out::println);
    
    Optional<String> any = Stream.of("alpha", "beta", "gamma").findAny();
    any.ifPresent(System.out::println);
```
#
### Terminal Example 4

```java
    boolean noneNegative = Stream.of(1, 2, 3).noneMatch(n -> n < 0);
    System.out.println("None negative? " + noneNegative);
    
    boolean allPositive = Stream.of(1, 2, 3).allMatch(n -> n > 0);
    System.out.println("All positive? " + allPositive);
    
    boolean hasEven = Stream.of(1, 3, 5, 6).anyMatch(n -> n % 2 == 0);
    System.out.println("Has even? " + hasEven);
```
#
### Terminal Example 5

```java
    Stream.of("A", "B", "C").forEach(System.out::println);
```
#
### Terminal Example 6

```java
    int sum = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
    System.out.println("Sum: " + sum);
```
#
### Terminal Example 7

```java
    List<String> list = Stream.of("a", "b", "c")
            .collect(Collectors.toList());
    System.out.println(list);
```