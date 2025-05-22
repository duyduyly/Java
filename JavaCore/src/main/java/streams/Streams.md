# what is the pipeline?

- A stream in java is a sequence of data
- Stream pipeline is set of operation that run on the stream to produce the result
- you can think of a pipeline as of an assembly line in the factory
    - every line has a beginning (source)
    - after that one by one element in the line is processed (intermediate operation)
    - finally, we get the result (terminal operation)
- data in the stream is not generate up front
    - rather, each element is created when needed -> lazy evaluation

# Stream Pipeline

1. Source
    - where the stream comes from
2. Intermediate Operation
    - transforms the stream into another stream
    - there can be as many (or as few) of these operations
    - intermediate operations don't run until the terminal operation runs(lazy)
3. terminal Operation
    - produces a result, and can be used only once
    - after the terminal operation completes, stream is no longer valid

![alt text](https://miro.medium.com/v2/resize:fit:1400/1*wF4lj2i--mkak27fJza3Lg.png)


__Example In Stream:__

```java
    List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
    names.stream()                               // Source
    .filter(name ->name.startsWith("A"))  // Intermediate
        .map(String::toUpperCase)              // Intermediate
    .forEach(System.out::println);         // Terminal
//intermediate operations don't run until the terminal operation runs(lazy)

```

## Stream 

# Java Stream Terminal Operations

| Method             | Description                                                       | Return Type         |
|--------------------|-------------------------------------------------------------------|---------------------|
| `forEach()`        | Performs an action for each element in the stream                 | `void`              |
| `forEachOrdered()` | Like `forEach`, but preserves encounter order in parallel streams | `void`              |
| `toArray()`        | Collects elements into an array                                   | `Object[]` or `T[]` |
| `reduce()`         | Combines elements using an associative accumulation function      | `Optional<T>` / `T` |
| `collect()`        | Performs a mutable reduction (e.g., collecting into a List)       | Collector result    |
| `min()`            | Returns the minimum element by comparator                         | `Optional<T>`       |
| `max()`            | Returns the maximum element by comparator                         | `Optional<T>`       |
| `count()`          | Returns the number of elements in the stream                      | `long`              |
| `anyMatch()`       | Checks if **any** element matches a predicate                     | `boolean`           |
| `allMatch()`       | Checks if **all** elements match a predicate                      | `boolean`           |
| `noneMatch()`      | Checks if **no** elements match a predicate                       | `boolean`           |
| `findFirst()`      | Returns the **first** element (if any)                            | `Optional<T>`       |
| `findAny()`        | Returns **any** element (especially useful for parallel streams)  | `Optional<T>`       |


# Reduce 
```java
        //merge all name
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        //normal way
        String allName = "";
        for (String name : names) allName += name;
        System.out.println(allName);

        //use reduce
        String useReduce = names.stream().reduce("", (s1, s2) -> s1 + s2);
        System.out.println(useReduce);

        //use reduce and conCat reference method
        String useReduceReferenceMethod = names.stream().reduce("", String::concat);
        System.out.println(useReduceReferenceMethod);
```

```bash
AliceBobCharlieDavid
AliceBobCharlieDavid
AliceBobCharlieDavid
```