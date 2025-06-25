## Using Optional

what is optional?

- optional is a container object that is used to contain values
- think of it as a box, which can be either empty or it can contain something
    - but the optional object itself(the box) is never null
- there are methods to deal with optional values without explicit null check
    - otherwise, we could get NullPointerException if not handle properly
- we have to import java.util.Optional

the method in optional

| Method                             | Parameter | Description                                       |                                                                
|------------------------------------|-----------|---------------------------------------------------|
| [`.of()`](#of)                     | Object    | Add Object non-null into optional                 |
| [`.empty()`](#empty)               |           | create a Optional empty                           |
| [`.ofNullable()`](#ofNullable)     | Object    | Add Object into optional and allow nullable value |
| [`.orElse()`](#orElse)             | Object    | Return or Else Value if Optional is Empty or null |
| [`.orElseGet()`](#orElseGet)       | Object    | Return or Else Value if Optional is Empty or null |
| [`.orElseThrow()`](#orelsethrow)   | Supplier  | Throws exception if value is empty.               |                                       |
| [`.map()`](#map)                   | Function  | Transforms value if present.                      |                                       |
| [`.flatMap()`](#flatmap)           | Function  | Like map but avoids nested Optionals.             |                                       |
| [`.filter()`](#filter)             | Predicate | Returns same Optional if predicate matches.       |                                       |

- import java.util.Optional;

## `.Of()`

```java
    Optional<Integer> op1 = Optional.of(12);
    Optional<Double> op2 = Optional.of(12D);
    Optional<Float> op3 = Optional.of(12f);
    Optional<Long> op4 = Optional.of(12L);
    Optional<String> op5 = Optional.of("12");

```

## `.empty()`

```java
    Optional<Object> empty = Optional.empty();
    System.out.println("Is Empty: " + empty.isEmpty());
    System.out.println("Is Present: " + empty.isPresent());
```

```bash
true
false
```

## `.ofNullable()`

```java
    //Optional<Object> o = Optional.of(null); //not ok, throw NullPointerException
    Optional.ofNullable(null);

```

## `.orElse()`

```java
    //print or else, for op is empty
    Optional<String> op = Optional.empty();
    System.out.println(op.orElse("Else Case"));

    //print op2, for op2 exists value
    Optional<String> op2 = Optional.of("Initialize value");
    System.out.println(op2.orElse("Else Case"));
```

```bash
Else Case
Initialize value
```

## `.orElseGet()`

```java
    Optional<String> op = Optional.empty();
    String username = "adadad";
    op.orElseGet(() -> "https://example.com/avatars/default/" + username + ".png");

```

```bash
https://example.com/avatars/default/adadad.png
```

## `orElseThrow()`

Throws an exception if value is not present.

```java
  String value = Optional.ofNullable(null)
          .orElseThrow(() -> new IllegalArgumentException("No value"));
```

## `map()`

Transforms the value if present.

```java
  Optional<String> name = Optional.of("Charlie");
  Optional<Integer> length = name.map(String::length);
  System.out.println(length.get()); // 7
```
```bash
7
```

## `flatMap()`

Similar to map, but avoids nested Optionals.

```java
  Optional<String> opt = Optional.of("test");
  Optional<String> result = opt.flatMap(val -> Optional.of(val.toUpperCase()));
  System.out.println(result.get()); // TEST
```
```bash
TEST
```

## `filter()`

Returns the same Optional if predicate matches, otherwise empty.

```java
  Optional<String> opt = Optional.of("Java");
  Optional<String> filtered = opt.filter(val -> val.startsWith("J"));
  System.out.println(filtered.isPresent()); // true
  System.out.println(filtered.get());
  
  System.out.println(filtered2.get()); 
  Optional<String> opt2 = Optional.of("Java");
  Optional<String> filtered2 = opt2.filter(val -> val.startsWith("E"));
  System.out.println(filtered2.isPresent()); // true
  System.out.println(filtered2.get());
```
```bash
true
Java

false
Exception in thread "main" java.util.NoSuchElementException: No value present
	at java.base/java.util.Optional.get(Optional.java:143)
	at lambdas_streams.streams.optional.App.main(App.java:27)

```