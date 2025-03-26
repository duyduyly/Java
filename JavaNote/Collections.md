# Collections
## Link
- [Common](#common)
- [List](#list)
- [Set Interface](#set-interface)
- [Queue Interface](#queue-interface)
- [Map Interface](#map-interface)
- [Sorting](#sorting)

## Common
__What is Collection?__

A Collection is a framework that provides a set of classes and interfaces for storing and manipulating a group of objects.

![App Screenshot](https://miro.medium.com/v2/resize:fit:822/1*qgcrVwo8qzF4muOQ-kKB8A.jpeg)

- These interfaces are commonly referred to as collections
  - List, Set, Queue(Deque), Map
- The end we want to work with classes:
  - interfaces List, Set and Queue implement Collection
  - class ArrayList implement List
  - class HashSet and TreeSet implement Set
  - interface deque implements Queue
  - class LinkedList implement Queue and List

![App Screenshot](https://media.geeksforgeeks.org/wp-content/uploads/20200807195934/SortedMap.png)

- interface Map doesn't implement Collection
- classes HashMap and ThreeMap implement Map interface

##
Diamond operator (<>) is used to imply the type of the element in collection
```Java
 List<String> names = new ArrayList<>(); // it's ok
```
- You don't need to define Type for right side, for Collection can automatic understanding
##
But not on the left-hand side!
```Java
List<> names = new ArrayList<String>();// does not compile
```
##
If you use __var__ you __have to specify the type on right-hand side__:
- ArrayList
  - add() ==> return boolean
  - remove() ==> return boolean
  - isEmpty() ==> return boolean
  - size() ==> return size
  - clear() ==> clear all element in ArrayList
  - contains() ==> check element (is exist in ArrayList)

```Java
var names = new ArrayList<String>(); //ok
```
var type just supported from Java 10 and Above

__Some method Support in interfaces__
```java
Collection<String> namesInList = new ArrayList<>();
System.out.println(namesInList.add("Join")) //==> true
System.out.println(namesInList.add("Join"))//==> true

Collection<String> namesInSet = new HashSet<>();
System.out.println(namesInSet.add("Join")) //==> true
System.out.println(namesInSet.add("Join"))//==> false
//because Set doesn't allow duplicates
```
##
Remove method
```Java
Collection<String> names = new ArrayList<>();
names.add("Join");
names.add("Alan");
names.add("Join");
System.out.println(names); //==> [Join, Alan, Join]
System.out.println(names.remove("Join"));//==> true

System.out.println(names); //==> [Alan, Join] only the first match is removed

System.out.println(names.remove("Luka")); // false because Luka doesn't exist in ArrayList
```
##
removeIf() method

```Java
Collection<String> names = new ArrayList<>();
names.add("Join");
names.add("Alan1");
names.add("Alan2");
names.add("Join");

names.removeIf(s -> s.length() > 4); // use predicate as an argument, implemented by lambda expression
System.out.println(names); //==> [Join, Join] // remove all element have length greater than 4
```
##
forEach() method

```Java
Collection<String> names = new ArrayList<>();
names.add("Join");
names.add("Alan1");
names.add("Alan2");
names.add("Join");

names.forEach(name -> System.out.println(name + ", ")); // takes Consumer as an argument, implemented by lambda expression
```

__Result__
```bash
Join, Alan1, Alan2, Join, 
```

#
## List

what is a List?
- an ordered collection which can contain duplicate entries
- items can be reached and inserted using the index(int)
- unlike array, list can change in size after being declared
- there are two classes which implement List interface:
  - arrayList and LinkedList
- ArrayList is better when you read more than you write
- LinkedList inplements both List and Deque

==> For OCA Exam you only need to know ArrayList

__Create a List using factory methods__
1. Arrays.asList(varargs) //fixed size list backed by an array
2. List.of(varargs) // return immutable list
3. List.copyOf(collection) //immutable list with copy of original value
- when you create a List in this way, it's sized is fixed (no adding and removing)

```Java
import java.util.Arrays;
import java.util.List;

public class MyClass {
    public static void main(String[] args) {
        String[] names = new String[]{"Join", "George","Like"};

        List<String> namesAsList = Arrays.asList(names);
        List<String> namesOf = List.of(names);
        List<String> namesCopyOf = List.copyOf(namesAsList);

        names[1] = "Ben";
        System.out.println(namesAsList);
        System.out.println(namesOf);
        System.out.println(namesCopyOf);

    }
}
```
__Result:__
```bash
    [Join, Ben, Like] //because the list is "backed" by the array
    [Join, George, Like] // no change 
    [Join, George, Like] // no change
```

__Backing up works both ways__
```Java
import java.util.Arrays;
import java.util.List;

public class MyClass {
    public static void main(String[] args) {
        String[] names = new String[]{"Join", "George","Luka"};

        List<String> namesAsList = Arrays.asList(names);

        namesAsList.set(2, "Paul");
        System.out.println(namesAsList);
        System.out.println(Arrays.toString(names));
    }
}
```
__Result:__
```bash
[Join, George, Paul]
[Join, George, Paul]
```
- Arrays.asList() does not create a new copy of the array; instead, it returns a fixed-size list backed by the original array.
- This means that when you modify an element in the namesAsList, it directly affects the underlying array (names), and vice versa.

__Create a List with a constructor__

```Java
List<String> myList1 = new ArrayList<>();
//==> create new empty List myList1
List<String> myList2 = new ArrayList<>(myList1);
//==> makes a copy off myList1 and stores it in myList2
```

```Java
ArrayList<String> arrayList1 = new ArrayList<>();
//==> creates new empty ArrayList myList1
ArrayList<String> arrayList2 = new ArrayList<String>(arrayList1);
//=> makes a copy of arrayList1 and stores it in arrayList2
```

```Java
ArrayList<String> arrayList3 = new ArrayList<String>(5);
//==> you have reserved 5 slots, but you can always add more if you want
```

#
__List methods__
- add(E element)
- add(int index, E element)
- get(int index)
- remove(int index)
- remove(E element)
- replaceAll(UnaryOperator<E> op)
- set(int index, E element)
- sort(Comparator<? super E> c)

__Example__
```Java
import java.util.ArrayList;
import java.util.List;

public class MyClass {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Join");
        names.add("George");
        names.add("Paul");
        names.add("Ringo");
    }
}
```

__1.Add__
```Java
        names.add(1, "Alan");
        System.out.println(names);
```
Result:
```bash
[Join, Alan, George, Paul, Ringo] //index 1 changed from George to Alan
```

__2.Set__
```Java
System.out.println("Before: "+names.get(2));
        names.set(2,"Alan2");
        System.out.println("After: "+names.get(2));
```
Result:
```bash
Before: George
After: Alan2
```

__3.Remove__
```Java
        names.remove(1);
        System.out.println("Remove George at index 1: "+names);

        names.remove("Join");
        System.out.println("Remove Join: "+names);
```
Result:
```bash
    Remove George at index 1: [Join, Paul, Ringo]
    Remove Join: [Paul, Ringo]
```

Special case __Remove ArrayList reserved Interger__:
```Java
        List<Integer> numbers = new ArrayList<>();
        numbers.add(2);
        numbers.add(-11);
        numbers.add(7);

        System.out.println(numbers);
        numbers.remove(2);
        System.out.println(numbers);

        numbers.add(7);
        numbers.remove(Integer.valueOf(2));
        System.out.println(numbers);
```
Result:
```bash
[2, -11, 7]
[2, -11]
[-11, 7]
```
__what element will be removed, 2 or 7?__
- since 2 is primitive, remove(int index) will be used
- number 7 will be removed
- if you want to remove element 2, you must use Integer.valueOf(2), for Integer.valueOf() will be converted from primitive type to object
- and remove(E element) will be used

__4.ReplaceAll__
```Java
        names.replaceAll(String::toUpperCase);
        System.out.println(names);

        names.replaceAll(s -> s.toLowerCase(Locale.ROOT));
        System.out.println(names);
```
Result:
```bash
[JOIN, GEORGE, PAUL, RINGO]
[join, george, paul, ringo]
```
#
__Convert List to Array using toArray() methods__
```Java
List<Integer> myList = new ArrayList<>();
myList.add(3);
myList.add(5);
myList.add(7);

Object[] objArray = myList.toArray();// => Array of Object in the List
Integer[] intArray = myList.toArray(new Integer[0]);
//=> array Integer
//=> initial size is 0, but Java will automatically adJust sizes to fit
```
#
## Set Interface

- doesn't allow duplicate entries
- implementations: hashSet, TreeSet
- hashSet store(key, value), elements in hash table
  - key is hashCode(), value is Object
  - doesn't keep the other
  - adding each element takes the same time
- TreeSet stores elements in a sorted tree structure
  - keeps the order
  - adding each element take more time as tree goes trigger

__HashSet Example:__
```java
        Set<String> names = new HashSet<>();
        System.out.println(names.add("John"));
        System.out.println(names.add("George"));
        System.out.println(names.add("John"));
        System.out.println(names.add("Ben"));

        System.out.println(names);
```
_Result_
```bash
true
true
false // false because John existed in Set
true
[George, John, Ben] //arbitrary order!
```

__TreeSet Example:__
```java
        TreeSet<String> names = new TreeSet<>();
        System.out.println(names.add("John"));
        System.out.println(names.add("George"));
        System.out.println(names.add("John"));
        System.out.println(names.add("Ben"));

        System.out.println(names);
```

_Result_
```bash
true
true
false // false because John existed in Set
true
[Ben, George, John] //keeping the order (last added is first on the list)!
```

## Queue Interface
- Implemented by LinkedList class
- adds element in the back, reads from the front
  - FIFO: First In, First Out
- proper methods
  - peek(), offer(E e), Poll()
- methods inherited from Collection
  - element(), add(E e), remove()
- 


```java
        Queue<String> colors = new LinkedList<String>();
        colors.offer("blue"); //--> similar add() method, use to add a element into queue
        colors.offer("green");
        colors.offer("red");
        colors.offer("yellow");
        System.out.println(colors); //print all colors in queue
        System.out.println(colors.peek()); // get First but not poll
        colors.poll(); // poll to push the first element out your queue
        System.out.println(colors);
        System.out.println(colors.peek()); // .peek() similar element() in linkedList Interface
```

_Result:_
```bash
[blue, green, red, yellow]
blue
[green, red, yellow]
green
```

__What happen when you use poll() and peek() with the queue empty?__

```java
        Queue<String> colors = new LinkedList<String>();
        colors.offer("blue"); 
        colors.offer("green");
        colors.offer("red");
        colors.offer("yellow");
        System.out.println(colors.poll());
        System.out.println(colors.poll());
        System.out.println(colors.poll());
        System.out.println(colors.poll());
        System.out.println(colors.poll());
        System.out.println(colors.peek());
```
```bash
blue
green
red
yellow
null
null
```

--> return null

## Deque Interface
- Deque Interface used as a Stack
- implemented by LinkedList and ArrayQueue
- adds element in the front, reads from the back
  - LIFO: Last In, First Out
- proper method
  - peek(), push(), poll()
- method inherited from Collection
  - element(), add(E e), remove()
- avoid use inherit methods, for Inherit method will throw exception when the deque or queue is empty

```java
        Deque<String> colors = new LinkedList<String>();
        colors.push("blue");
        colors.offer("green");
        colors.push("red");
        colors.push("yellow");
        System.out.println(colors);
        System.out.println(colors.peek());
        colors.pop();
        System.out.println(colors.peek());
        colors.pop();
        colors.pop();
        colors.pop();
        System.out.println(colors.peek());
```
```bash
[yellow, red, blue, green]
yellow
red
null
```

# Deque interface as Double-ended Queue
- can use deque as a queue and opposite
- proper method:
  - peedFirst(), offerFirst(E e), poolFirst()
  - peekLast(), offerLast(E e), poolLast()
- methods inherited from Collection
  - getFirst(), addFirst(E e), removeFirst()
  - getLast(), addLast(E e), removeLast()
- avoid use inherit methods, for Inherit method will throw exception when the deque or queue is empty

```java
        Deque<Integer> nums = new ArrayDeque<Integer>();
        nums.addLast(9);
        nums.offerFirst(-11);
        nums.addLast(5);

        System.out.println(nums);
        System.out.println(nums.getFirst());
        System.out.println(nums.peekLast());
        nums.pollFirst();
        System.out.println(nums);
        System.out.println(nums.getFirst());
        System.out.println(nums.peekLast());
```

```bash
[-11, 9, 5] //nums
-11 //nums.getFirst()
5 //nums.peekLast()
[9, 5] //less a element because we polled First Element
9 //First
5 //last
```

## Map Interface

__Map methods__

| Method | Use                                                                                                       |                                                                
| ----------------- |-----------------------------------------------------------------------------------------------------------|
| [Clear](#clear) | Clears all data in the map                                                                                |
| [ContainsKey](#containsKey) | Checks if the key is in the map                                                                           |
| [ContainsValue](#containsValue) | Checks if the value is in the map                                                                         |
| [EntrySet](#entrySet) | Return Set of key/Value pairs                                                                             |
| [ForEach](#forEach) |                                                                                                           |
| [Get](#get) | Return value mapped with key, or null if none exists                                                      |
| [GetOrDefault](#getOrDefault) | Same as get, but returns defaultValue if key doesn't exist                                                |
| [IsEmpty](#isEmpty) | Checks if map is empty                                                                                    |
| [KeySet](#keySet) | Returns Set of all keys                                                                                   |
| [Merge](#merge) | Sets value if key doesn't exits, runs func if key is set to determine new value, removes if value is null |
| [Put](#put) | Adds or replaces k/v pair, returns previous value or null                                                 |
| [PutIfAbsent](#putIfAbsent) | If key not present adds value and returns null (other) |
| [Remove](#remove) | Remove element in the map,Can remove for key, key and value                                         |
| [Replace](#replace) |  Replaces value for given key if key is set, return original value or null if none exist|
| [ReplaceAll](#replaceAll) | Replaces each value with results of function |
| [Size](#size) | Return size of the map |
| [Values](#values) | Return Collection of values |



- the first Create a map with 3 element
```java
    Map<Integer, String> map = new HashMap<>();
    map.put(1, "Alan");
    map.put(2, "Bob");
    map.put(3, "Charlie");
```

## Clear
```java
    System.out.println(map);
    map.clear();
    System.out.println(map);
```
```bash
{1=Alan, 2=Bob, 3=Charlie}
{}
```

## ContainsKey
```java
    System.out.println(map.containsKey(1));
    System.out.println(map.containsKey(4));
```
```bash
true
false
```

## ContainsValue
```java
    System.out.println(map.containsValue("Alan"));
    System.out.println(map.containsValue("Bob Junior"));
```
```bash
true
false
```

## EntrySet
```java
    for (Map.Entry<Integer, String> entry : map.entrySet()) {
        System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
    }
```
```bash
Key: 1, Value: Alan
Key: 2, Value: Bob
Key: 3, Value: Charlie
```

## ForEach
```java
    map.forEach((key, value) -> {
        System.out.println(key + " : " + value);
    });
```
```bash
1 : Alan
2 : Bob
3 : Charlie
```

## Get
```java
    System.out.println(map.get(1));
    System.out.println(map.get(5));
```
```bash
Alan
null
```

## GetOrDefault
```java
    System.out.println(map.getOrDefault(2, "Alan"));
    System.out.println(map.getOrDefault(6, "Charlie Smith"));
```
```bash
Bob
Charlie Smith
```

## IsEmpty
```java
    System.out.println(map.isEmpty());
    map.clear();
    System.out.println(map.isEmpty());
```
```bash
false
true
```

## KeySet
```java
    System.out.println(map.keySet());
```
```bash
[1, 2, 3]
```

## Merge
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
```bash
{banana=1, apple=2}
{greeting=Hello World}
{}
```

## Put
put(K key, V value)
```java
    Map<Integer, String> map = new HashMap<>();
    map.put(1, "Alan");
    map.put(2, "Bob");
    map.put(3, "Charlie");
    System.out.println(map);
```
```bash
{1=Alan, 2=Bob, 3=Charlie}
```

## PutIfAbsent
```java
    map.putIfAbsent(2,"Demo"); // because key 2 existed
    map.putIfAbsent(4,"Demo4");
    System.out.println(map);
```
```bash
  {1=Alan, 2=Bob, 3=Charlie, 4=Demo4}
```

## Remove
```java
    System.out.println(map.remove(1,"Alan2")); // remove fail because value "Alan2" do not exist
    System.out.println(map.remove(1,"Alan")); // remove for key and value
    System.out.println(map.remove(2)); // remove by key
    System.out.println(map);
```
```bash
false
true
Bob
{3=Charlie}
```

## Replace
```java
    System.out.println(map.replace(1, "Alan replace")); // will return old value and return
    System.out.println(map.replace(4, "new")); // return null, and nothing
    System.out.println(map);
```
```bash
Alan
null
{1=Alan replace, 2=Bob, 3=Charlie}
```

## ReplaceAll
replaceAll((key, value) -> newValue)
```java
    map.replaceAll((key, value) -> value.toUpperCase());
    System.out.println(map);
```
```bash
{1=ALAN, 2=BOB, 3=CHARLIE}
```

## Size
```java
    System.out.println(map.size());
```
```bash
3
```

## Values
```java
  System.out.println(map.values());
```
```bash
 [Alan, Bob, Charlie]
```
#
## Sorting
watching again