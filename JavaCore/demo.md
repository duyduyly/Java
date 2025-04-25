# Java Optional - README

Java's `Optional<T>` is a container object used to represent the presence or absence of a value. It helps avoid `NullPointerException` by providing a functional way to deal with optional values.

---

## 📋 Table of Contents

| Method                          | Description                                       |                                       |
| ------------------------------- | ------------------------------------------------- | ------------------------------------- |
|                                 | [`empty()`](#empty)                               | Returns an empty `Optional` instance. |
| [`of()`](#of)                   | Returns an `Optional` with a non-null value.      |                                       |
| [`ofNullable()`](#ofnullable)   | Returns an `Optional` with a possibly-null value. |                                       |
| [`isPresent()`](#ispresent)     | Returns true if value is present.                 |                                       |
| [`ifPresent()`](#ifpresent)     | Executes code if value is present.                |                                       |
| [`orElse()`](#orelse)           | Returns value or default if empty.                |                                       |
| [`orElseGet()`](#orelseget)     | Returns value or lazy default if empty.           |                                       |
| [`orElseThrow()`](#orelsethrow) | Throws exception if value is empty.               |                                       |
| [`map()`](#map)                 | Transforms value if present.                      |                                       |
| [`flatMap()`](#flatmap)         | Like map but avoids nested Optionals.             |                                       |
| [`filter()`](#filter)           | Returns same Optional if predicate matches.       |                                       |

---

## `empty()`

Returns an empty `Optional` instance.

```java
Optional<String> emptyOpt = Optional.empty();
System.out.println(emptyOpt.isPresent()); // false
```

## `of()`

Returns an `Optional` with a non-null value.

```java
Optional<String> name = Optional.of("Alice");
System.out.println(name.get()); // Alice
```

## `ofNullable()`

Returns an `Optional` describing the value, or empty if null.

```java
String value = null;
Optional<String> opt = Optional.ofNullable(value);
System.out.println(opt.isPresent()); // false
```

## `isPresent()`

Checks if the value is present.

```java
Optional<String> opt = Optional.of("Hi");
System.out.println(opt.isPresent()); // true
```

## `ifPresent()`

Runs a lambda if the value is present.

```java
Optional<String> opt = Optional.of("Welcome");
opt.ifPresent(val -> System.out.println(val.toUpperCase()));
// Output: WELCOME
```

## `orElse()`

Returns the value if present, otherwise returns the default value.

```java
String result = Optional.ofNullable(null).orElse("Default");
System.out.println(result); // Default
```

## `orElseGet()`

Returns the value if present, otherwise calls the Supplier to get default.

```java
String username = "john";
Optional<String> profilePic = Optional.ofNullable(null);
String url = profilePic.orElseGet(() -> generateDefaultAvatar(username));

private static String generateDefaultAvatar(String user) {
    return "https://example.com/avatars/default/" + user + ".png";
}
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

## `flatMap()`

Similar to map, but avoids nested Optionals.

```java
Optional<String> opt = Optional.of("test");
Optional<String> result = opt.flatMap(val -> Optional.of(val.toUpperCase()));
System.out.println(result.get()); // TEST
```

## `filter()`

Returns the same Optional if predicate matches, otherwise empty.

```java
Optional<String> opt = Optional.of("Java");
Optional<String> filtered = opt.filter(val -> val.startsWith("J"));
System.out.println(filtered.isPresent()); // true
```

