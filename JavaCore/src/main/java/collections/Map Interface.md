# Map Interface

| [Map Interface](#map-interface) | [HashTable, HashMap and Linked HasMap](#hashtable-hashmap-and-linked-hasmap) | [Tree Map](#treemap) | <br/>
#

- **Key Features:**
    - No Duplicates in Keys: Keys should be unique, but values can be duplicated.
    - Null Handling: It allows one null key in implementations like HashMap and LinkedHashMap, and allows multiple null values in most implementations.
    - Thread-Safe Alternatives: Use ConcurrentHashMap for thread-safe operations. Also, wrap an existing map using Collections.synchronizedMap() for synchronized access

![App Screenshot](https://miro.medium.com/v2/resize:fit:822/1*qgcrVwo8qzF4muOQ-kKB8A.jpeg)
![App Screenshot](https://media.geeksforgeeks.org/wp-content/uploads/20200807195934/SortedMap.png)

__Map methods:__

| Method                          | Use                                                                                                       |                                                                
|---------------------------------|-----------------------------------------------------------------------------------------------------------|
| [Clear](#clear)                 | Clears all data in the map                                                                                |
| [ContainsKey](#containsKey)     | Checks if the key is in the map                                                                           |
| [ContainsValue](#containsValue) | Checks if the value is in the map                                                                         |
| [EntrySet](#entrySet)           | Return Set of key/Value pairs                                                                             |
| [ForEach](#forEach)             |                                                                                                           |
| [Get](#get)                     | Return value mapped with key, or null if none exists                                                      |
| [GetOrDefault](#getOrDefault)   | Same as get, but returns defaultValue if key doesn't exist                                                |
| [IsEmpty](#isEmpty)             | Checks if map is empty                                                                                    |
| [KeySet](#keySet)               | Returns Set of all keys                                                                                   |
| [Merge](#merge)                 | Sets value if key doesn't exits, runs func if key is set to determine new value, removes if value is null |
| [Put](#put)                     | Adds or replaces k/v pair, returns previous value or null                                                 |
| [PutIfAbsent](#putIfAbsent)     | If key not present adds value and returns null (other)                                                    |
| [Remove](#remove)               | Remove element in the map,Can remove for key, key and value                                               |
| [Replace](#replace)             | Replaces value for given key if key is set, return original value or null if none exist                   |
| [ReplaceAll](#replaceAll)       | Replaces each value with results of function                                                              |
| [Size](#size)                   | Return size of the map                                                                                    |
| [Values](#values)               | Return Collection of values                                                                               |



- the first Create a map with 3 element
```java
    Map<Integer, String> map = new HashMap<>();
    map.put(1, "Alan");
    map.put(2, "Bob");
    map.put(3, "Charlie");
```

<br/>

### Clear
```java
    System.out.println(map);
    map.clear();
    System.out.println(map);
```
```text
{1=Alan, 2=Bob, 3=Charlie}
{}
```
<br/>

### ContainsKey
```java
    System.out.println(map.containsKey(1));
    System.out.println(map.containsKey(4));
```
```text
true
false
```
<br/>

### ContainsValue
```java
    System.out.println(map.containsValue("Alan"));
    System.out.println(map.containsValue("Bob Junior"));
```
```text
true
false
```

<br/>

### EntrySet
```java
    for (Map.Entry<Integer, String> entry : map.entrySet()) {
        System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
    }
```
```text
Key: 1, Value: Alan
Key: 2, Value: Bob
Key: 3, Value: Charlie
```

<br/>

### ForEach
```java
    map.forEach((key, value) -> {
        System.out.println(key + " : " + value);
    });
```
```text
1 : Alan
2 : Bob
3 : Charlie
```
<br/>

### Get
```java
    System.out.println(map.get(1));
    System.out.println(map.get(5));
```
```text
Alan
null
```
<br/>

### GetOrDefault
```java
    System.out.println(map.getOrDefault(2, "Alan"));
    System.out.println(map.getOrDefault(6, "Charlie Smith"));
```
```text
Bob
Charlie Smith
```

<br/>

### IsEmpty
```java
    System.out.println(map.isEmpty());
    map.clear();
    System.out.println(map.isEmpty());
```
```text
false
true
```

<br/>

### KeySet
```java
    System.out.println(map.keySet());
```
```text
[1, 2, 3]
```

<br/>

### Merge
V merge(K key, V value, BiFunction<V, V, V> remappingFunction)
```java
    // Example 1: Word Counting
    Map<String, Integer> wordCount = new HashMap<>();

    wordCount.merge("apple", 1, Integer::sum);  // apple = 1
    wordCount.merge("apple", 1, Integer::sum);  // apple = 2
    wordCount.merge("banana", 1, Integer::sum); // banana = 1

    System.out.println(wordCount); // {apple=2, banana=1}

    // Example 2: Custom Merge Logic
    Map<String, String> map = new HashMap<>();
    map.put("greeting", "Hello");
    map.merge("greeting", " World", (oldVal, newVal) -> oldVal + newVal);

    System.out.println(map); // {greeting=Hello World}

    // Example 3: Remove Entry If Needed
    Map<String, Integer> numbers = new HashMap<>();
    numbers.put("x", 5);

    numbers.merge("x", 1, (oldVal, newVal) -> null);  // removes "x"

    System.out.println(numbers); // {}
```
```text
{banana=1, apple=2}
{greeting=Hello World}
{}
```

<br/>

### Put
put(K key, V value)
```java
    Map<Integer, String> map = new HashMap<>();
    map.put(1, "Alan");
    map.put(2, "Bob");
    map.put(3, "Charlie");
    System.out.println(map);
```
```text
{1=Alan, 2=Bob, 3=Charlie}
```

<br/>

### PutIfAbsent
```java
    map.putIfAbsent(2,"Demo"); // because key 2 existed
    map.putIfAbsent(4,"Demo4");
    System.out.println(map);
```
```text
  {1=Alan, 2=Bob, 3=Charlie, 4=Demo4}
```

<br/>

### Remove
```java
    System.out.println(map.remove(1,"Alan2")); // remove fail because value "Alan2" do not exist
    System.out.println(map.remove(1,"Alan")); // remove for key and value
    System.out.println(map.remove(2)); // remove by key
    System.out.println(map);
```
```text
false
true
Bob
{3=Charlie}
```

<br/>

### Replace
```java
    System.out.println(map.replace(1, "Alan replace")); // will return old_package value and return
    System.out.println(map.replace(4, "new")); // return null, and nothing
    System.out.println(map);
```
```text
Alan
null
{1=Alan replace, 2=Bob, 3=Charlie}
```

<br/>

### ReplaceAll
replaceAll((key, value) -> newValue)
```java
    map.replaceAll((key, value) -> value.toUpperCase());
    System.out.println(map);
```
```text
{1=ALAN, 2=BOB, 3=CHARLIE}
```

<br/>

### Size
```java
    System.out.println(map.size());
```
```text
3
```

<br/>

### Values
```java
  System.out.println(map.values());
```
```text
 [Alan, Bob, Charlie]
```
<br/>

#
## HashTable, HashMap and Linked HasMap
| Feature                    | `HashMap`                                                                             | `Hashtable`                                                               | `LinkedHashMap`                                                         | `TreeMap`                                                                       |
|----------------------------|---------------------------------------------------------------------------------------|---------------------------------------------------------------------------|-------------------------------------------------------------------------|---------------------------------------------------------------------------------|
| Thread-safe (synchronized) | ❌ No                                                                                  | ✅ Yes                                                                     | ❌ No                                                                    | ❌ No  (Not synchronized — use Collections.synchronizedSortedMap(...) if needed) |
| Allows `null` key          | ✅ Yes (1 key)                                                                         | ❌ No                                                                      | ✅ Yes                                                                   | ❌ No ( 	Throws NullPointerException if you put a null key)                      |
| Maintains order            | ❌ No (entries may appear random)                                                      | ❌ No                                                                      | ✅ Yes (Maintains insertion order)                                       |                                                                                 |
| Sorted                     | ❌ No                                                                                  | ❌ No                                                                      | ❌ No                                                                    | ✅ Yes (Maintains ascending order of keys by default (uses `Comparable`))        |
| Performance                | ✅ Fast (Faster than Hashtable in single-threaded context)                             | 🚫 Slower (Slower than HashMap due to synchronization)                    | ⚠️ Slightly slower (Slightly slower than HashMap (due to linked list))  |                                                                                 |
| Use in new code            | ✅ Recommended (🔧 Use when you don’t care about order and don’t need synchronization) | ❌ Legacy (Legacy class (from early Java), not recommended in modern code) | ✅ Recommended (Great when you want to maintain order (like for caching) |                                                                                 |

- But have a `Exception` HashMap will maintain order with simple number;
```java
public static void main(String[] args) {
  Map<Integer, String> map = new HashMap<>();
  map.put(1, "one");
  map.put(6, "six");
  map.put(4, "four");
  map.put(5, "five");
  map.put(2, "two");
  map.put(3, "three");

  System.out.println(map);
}
```
```text
{1=one, 2=two, 3=three, 4=four, 5=five, 6=six}
```

- Recommendation:
  - Use HashMap by default.
  - Use LinkedHashMap if you need order.
  - Use ConcurrentHashMap instead of Hashtable for thread-safe access.

#
## TreeMap
- TreeMap is a class in Java’s java.util package that implements the NavigableMap interface and stores entries in sorted order based on the keys.

✅ Features of TreeMap

| Feature                        | Description                                                               |
|--------------------------------|---------------------------------------------------------------------------|
| 🔢 **Sorted by key**           | Maintains **ascending** order of keys by default (uses `Comparable`)      |
| 🔀 **Custom order**            | You can pass a `Comparator` for **custom sorting**                        |
| ❌ **No `null` key**            | Throws `NullPointerException` if you put a `null` key                     |
| ✅ **Allows `null` values**     | You can store `null` as a **value**, just not as a key                    |
| ❌ **Not thread-safe**          | Not synchronized — use `Collections.synchronizedSortedMap(...)` if needed |
| ⏳ **Backed by Red-Black Tree** | Logarithmic time (`O(log n)`) for `put`, `get`, and `remove` operations   |

TreeMap Method

| Method            | Description                                |
|-------------------|--------------------------------------------|
| `firstKey()`      | Returns the smallest key                   |
| `lastKey()`       | Returns the largest key                    |
| `ceilingKey(k)`   | Smallest key ≥ `k`                         |
| `floorKey(k)`     | Largest key ≤ `k`                          |
| `higherKey(k)`    | Smallest key > `k`                         |
| `lowerKey(k)`     | Largest key < `k`                          |
| `subMap(k1, k2)`  | Returns view from `k1` to `k2` (exclusive) |
| `descendingMap()` | Reverses key order                         |

__📌 When to Use TreeMap?__
- Use it when:
  - You need keys to be automatically sorted.
  - You frequently do range queries (like "get all keys between A and Z").
  - You care about the order of keys, unlike HashMap.
---------------------------