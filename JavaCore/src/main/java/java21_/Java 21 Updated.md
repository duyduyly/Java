# Java 21 updated
1. [Pattern Matching for Switch ](#pattern-matching-for-switch)(Standard)
2. Record Patterns (Preview)
3. [Sequenced Collections](#sequenced-collections) (Standard)
   - [SequencedCollections](#sequenced-collections)
   - [SequencedSet](#sequenced-set)
   - [SequencedMap<K,V>](#sequencedmapkv)
4. Virtual Threads (Standard)
5. String Templates (Preview)
6. Scoped Values (Preview)

- I just focus into standard feature

### Pattern Matching for Switch

- Type Patterns
- Null Handling
- Guarded Patterns (with when)
- Record Pattern Matching (with switch)
- Exhaustiveness Checks
- Default and Fall-through

📌 Rules & Restrictions

| Rule                | Description                             |
|---------------------|-----------------------------------------|
| ❌ No fall-through   | Each case is independent, must use `->` |
| ✅ `null` safe       | Explicit `case null ->` allowed         |
| ✅ Smart cast        | You don’t need to cast after matching   |
| ✅ Exhaustive checks | When switching over sealed types        |

#
✅ Summary Table

| Feature                   | Supported in Java 21 |
|---------------------------|----------------------|
| Type patterns             | ✅ Yes                |
| Guarded patterns (`when`) | ✅ Yes                |
| Record deconstruction     | ✅ Yes                |
| Sealed class matching     | ✅ Yes                |
| `null` in switch          | ✅ Yes                |
| Fall-through (`:`)        | ❌ Not allowed        |


__Type Patterns and Guarded patterns__
```java
   record StudentRecord(Long id, String name) { };
    public static String checkObType(Object ob) {
        return switch (ob) {
            case String s1 when s1.length() > 5 -> s1+ "'s long String"; //Guarded Patterns (with when)
            case String s2 -> s2+"'s short string";
            case Integer i -> i + "'s an int "; //Type Patterns
            case Double d -> d + "'s an Double ";
            case Long l -> l + "'s an Long ";
            case Float f -> f + "'s an Float ";
            case StudentRecord(Long id, String name) -> "this is Record Student" ; //Record Pattern Matching
            case null -> "It's null!"; //Null Handling
            default -> "Unknown type";
        };
    }

    public static void testObType() {
        System.out.println("Result 1: " + checkObType("ABC"));
        System.out.println("Result 2: " + checkObType(12));
        System.out.println("Result 3: " + checkObType(1D));
        System.out.println("Result 4: " + checkObType(3000000L));
        System.out.println("Result 5: " + checkObType(12f));
        System.out.println("Result 6: " + checkObType(null));
        System.out.println("Result 7: " + checkObType('1'));
        System.out.println("Result 7: " + checkObType(new StudentRecord(1L, "Alan")));
        System.out.println("Result 8: " + checkObType("12421412412"));
    }
```
```text
Result 1: ABC's short string
Result 2: 12's an int 
Result 3: 1.0's an Double 
Result 4: 3000000's an Long 
Result 5: 12.0's an Float 
Result 6: It's null!
Result 7: Unknown type
Result 7: this is Record Student
Result 8: 12421412412's long String
```
#
__Record Class And Sealed Matching__
```java

record StudentRecord(Long id, String name) { };

sealed interface Shape permits Circle, Square { }
record Circle(double radius) implements Shape { }
record Square(double side) implements Shape { }

public static void main(String[] args) {
    Shape shape = new Square(12D);
    System.out.println(exhaustivenessChecks(shape));

    StudentRecord record = new StudentRecord(1L, "Alan");
    System.out.println(checkStudentRecord(record));
    System.out.println(checkStudentRecord(null));
}

public static String exhaustivenessChecks(Shape shape) {
    return switch (shape) { //Exhaustiveness Checks
        case Circle c -> "Circle with r = " + c.radius();
        case Square s -> "Square with side = " + s.side();
    };
}

public static String checkStudentRecord(Object ob) {
    return switch (ob) {
        case StudentRecord(Long id, String name) -> "this is Record Student (" + id + ", " + name + ")";
        case null -> "It's null!";
        default -> "Unknown type";
    };
}
```
```text
Square with side = 12.0
this is Record Student (1, Alan)
It's null!
```

-------------------

## Sequenced Collections
### what is Sequenced Collections?
- In Java 21, a new API was introduced called `Sequenced Collections` (via JEP 431). It adds a unified way to handle ordered collections, such as `lists`, `sets`, and `maps`, with explicit first and last element access.
- A Sequenced Collection is a collection that:
  - `Maintains` a defined `encounter order`.
  - `Allows access` to the first and last elements.
  - Supports reversible views (like reversed iteration).

![Sequenced Collections in Java 21.png](../../../../resources/Sequenced%20Collections%20in%20Java%2021.png)


### New Interfaces in Java 21
| Interface                | Extends                            | Description                             |
|--------------------------|------------------------------------|-----------------------------------------|
| `SequencedCollection<E>` | `Collection<E>`                    | Base interface for ordered collections. |
| `SequencedSet<E>`        | `Set<E>`, `SequencedCollection<E>` | Ordered set, like `LinkedHashSet`.      |
| `SequencedMap<K,V>`      | `Map<K,V>`                         | Ordered map, like `LinkedHashMap`.      |


#
### Implementing Classes:
| Class        | Maintains Order | Implements `SequencedCollection` |
|--------------|-----------------|----------------------------------|
| `ArrayDeque` | ✅ Yes           | ✅ Yes                            |
| `LinkedList` | ✅ Yes           | ✅ Yes                            |
- `ArrayList` does not implement `SequencedCollection` (because no efficient first operations).

#
- `SequencedCollection<E>` is an interface that extends `Collection<E>` and adds methods to:

 | Operation | Description                     |
 |-----------|---------------------------------|
 | Access    | `getFirst()`, `getLast()`       |
 | Insertion | `addFirst(E)`, `addLast(E)`     |
 | Removal   | `removeFirst()`, `removeLast()` |
 | Reversal  | `reversed()`                    |

#
### Key Methods
| Method          | Description                   |
|-----------------|-------------------------------|
| `getFirst()`    | Returns the **first** element |
| `getLast()`     | Returns the **last** element  |
| `addFirst(E e)` | Inserts at the beginning      |
| `addLast(E e)`  | Inserts at the end            |
| `removeFirst()` | Removes the first element     |
| `removeLast()`  | Removes the last element      |
| `reversed()`    | Returns a **reversed view**   |


#
**Who Implements SequencedCollection?**

Only collections that are efficient for both front and back operations:

| Class        | Front Add/Remove | Back Add/Remove | Implements `SequencedCollection` |
|--------------|------------------|-----------------|----------------------------------|
| `ArrayDeque` | ✅ Fast (O(1))    | ✅ Fast (O(1))   | ✅ Yes                            |
| `LinkedList` | ✅ Fast (O(1))    | ✅ Fast (O(1))   | ✅ Yes                            |
| `ArrayList`  | ❌ Slow (O(n))    | ✅ Fast at end   | ❌ No                             |

### ✅ Why Use SequencedCollection Instead of Collection?
- `Collection` is the `base interface` for all Java collections, but it has:
  - ❌ No guaranteed element order
  - ❌ No first/last element access
- if order matters, `SequencedCollection` makes that `explicit and safe`.

| Feature                   | `Collection` | `SequencedCollection` |
|---------------------------|--------------|-----------------------|
| Maintains order           | ❌ No         | ✅ Yes (required)      |
| Access first/last element | ❌ No         | ✅ Yes                 |
| Add/remove at front/back  | ❌ No         | ✅ Yes                 |
| Reversed view             | ❌ No         | ✅ Yes                 |

####  When Should You Use It?
- Use `SequencedCollection` when:
  - You want a `queue`, `stack`, or `double-ended` structure
  - You care about `first`/`last` access
  - You want more `expressive` APIs with better `readability`
  - You’re using `LinkedList`, `ArrayDeque`, or `anything ordered`

--------------------
## Sequenced Set
- Here's everything you need to know about SequencedSet — one of the `new interfaces` introduced in `Java 21` as part of the `Sequenced Collections` feature.
- `SequencedSet<E>` extends `Set<E>` and SequencedCollection<E>, adding ordering capabilities to sets.
- Use SequencedSet when you want:
  - A set that remembers order
  - Easy and safe `first`/`last` access
  - Clean reversal
  - `No duplicates`, but still `ordered behavior`
- It's the `modern`, `powerful` replacement for using `LinkedHashSet` or `TreeSet` in ordered workflows.


### Implementing Classes in Java 21
| Class           | Order Type      | Implements `SequencedSet` |
|-----------------|-----------------|---------------------------|
| `LinkedHashSet` | Insertion order | ✅ Yes                     |
| `TreeSet`       | Sorted order    | ✅ Yes                     |

#
### Important Methods
- Since `SequencedSet` extends `SequencedCollection`, it inherits:

| Method          | Description                                       |
|-----------------|---------------------------------------------------|
| `addFirst(E)`   | Add element at the start (if not already present) |
| `addLast(E)`    | Add element at the end (normal `add()`)           |
| `getFirst()`    | Get the first element                             |
| `getLast()`     | Get the last element                              |
| `removeFirst()` | Remove and return the first element               |
| `removeLast()`  | Remove and return the last element                |
| `reversed()`    | Return a reversed view of the set                 |

### ⚠️ Why HashSet Doesn’t Implement SequencedSet
- `HashSet` has no predictable order.
- It cannot safely implement `getFirst()`, `reversed()`, etc.
- So `SequencedSet` is only implemented by ordered sets like `LinkedHashSet` and `TreeSet`

### Summary
| Interface/Class | Maintains Order | Supports Sequenced Methods | Notes                |
|-----------------|-----------------|----------------------------|----------------------|
| `HashSet`       | ❌ No            | ❌ No                       | Unordered set        |
| `LinkedHashSet` | ✅ Insertion     | ✅ Yes                      | Order-preserving set |
| `TreeSet`       | ✅ Sorted        | ✅ Yes                      | Sorted set           |
| `SequencedSet`  | —               | ✅ Abstract interface       | Added in Java 21     |



----------------

## SequencedMap<K,V>
### Key Methods

| **Method**                               | **Description / Purpose**                                                                                    |
|------------------------------------------|--------------------------------------------------------------------------------------------------------------|
| [putFirst(K key, V value)](#putfirstk-v) | Inserts the entry at the **front** of the map (before all others). If key exists, it is re-ordered to front. |
| [putLast(K key, V value)](#putlastk-v)   | Inserts the entry at the **end** of the map (after all others). If key exists, it is re-ordered to end.      |
| [firstEntry()](#firstentry)              | Returns the **first key-value pair** in the map based on the map's order.                                    |
| [lastEntry()](#lastentry)                | Returns the **last key-value pair** in the map.                                                              |
| [pollFirstEntry()](#pollfirstentry)      | Removes and returns the **first** entry from the map.                                                        |
| [pollLastEntry()](#polllastentry)        | Removes and returns the **last** entry from the map.                                                         |
| [reversed()](#reversed)                  | Returns a **reversed view** of the map (without copying data). First becomes last, and vice versa.           |

```java
   public static void main(String[] args) {
        SequencedMap<String, Integer> map = new LinkedHashMap<>();

        // Use the methods one by one below
    }
```

#
#### putFirst(K, V)
```java
map.putFirst("first", 1);
System.out.println(map); // {first=1}
```

#
#### putLast(K, V)
```java
map.putLast("second", 2);
System.out.println(map); // {first=1, second=2}
```


#
#### firstEntry()
```java
Map.Entry<String, Integer> first = map.firstEntry();
System.out.println("First entry: " + first); // first=1
```

#
#### lastEntry()
```java
Map.Entry<String, Integer> last = map.lastEntry();
System.out.println("Last entry: " + last); // second=2
```


#
#### pollFirstEntry()
```java
Map.Entry<String, Integer> polledFirst = map.pollFirstEntry();
System.out.println("Polled first: " + polledFirst); // first=1
System.out.println(map); // {second=2}
```

#
#### pollLastEntry()
```java
Map.Entry<String, Integer> polledLast = map.pollLastEntry();
System.out.println("Polled last: " + polledLast); // second=2
System.out.println(map); // {}
```

#
#### reversed()
```java
map.putLast("one", 1);
map.putLast("two", 2);
map.putLast("three", 3);

System.out.println("Original: " + map); // {one=1, two=2, three=3}

SequencedMap<String, Integer> reversed = map.reversed();
System.out.println("Reversed: " + reversed); // {three=3, two=2, one=1}
```


### Notice:
- If you use `pushFirst()` for `TreeMap`
```text
Exception in thread "main" java.lang.UnsupportedOperationException
	at java.base/java.util.TreeMap.putFirst(TreeMap.java:321)
	at java21_.Demo.main(Demo.java:31)
```
- the System will throw `exception` because `TreeMap` have Sorted when it's Store

