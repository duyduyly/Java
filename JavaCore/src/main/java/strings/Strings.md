# Strings

| [String Methods](#string-methods) | [String Builder](#string-builder) | [Comparison Between String and String builder](#comparison-between-string-and-stringbuilder) | <br/>
| [String pool](#string-pool) |


## String methods
- Strings are immutable!
- you have to reassign the new value or create a new String

| Method                                                                                         | Description                                                                                                                                        |
|------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------|
| **[Create and Concatenate a String](#create-and-concatenate-a-string)**                        | Creating strings using literals or constructors, and joining them using `+`, `concat()`, or `StringBuilder`.                                       |
| **[length()](#length)**                                                                        | Returns the number of characters in the string.                                                                                                    |
| **[charAt()](#charat)**                                                                        | Returns the character at the specified index.                                                                                                      |
| **[indexOf()](#indexof)**                                                                      | Returns the index of the first occurrence of a character or substring. Returns `-1` if not found.                                                  |
| **[substring()](#substring)**                                                                  | Extracts a portion of the string from a start index to an end index (exclusive).                                                                   |
| **[toLowerCase() and toUpperCase()](#tolowercase-and-touppercase)**                            | Converts all characters in the string to lowercase or uppercase.                                                                                   |
| **[equals() and equalsIgnoreCase()](#equals-equalsignorecase)**                                | Compares strings for content equality, optionally ignoring case.                                                                                   |
| **[startsWith() and endsWith()](#startswith-endswith)**                                        | Checks if a string starts or ends with a specific prefix or suffix.                                                                                |
| **[contains()](#contains)**                                                                    | Returns `true` if the string contains the specified sequence of characters.                                                                        |
| **[replace()](#replace)**                                                                      | Replaces each occurrence of a character or substring with a new one.                                                                               |
| **[strip(), trim(), stripLeading(), stripTrailing()](#strip-trim-stripleading-striptrailing)** | Removes whitespace: `trim()` removes leading/trailing spaces, `strip()` uses Unicode, `stripLeading()` and `stripTrailing()` remove only one side. |
| **[indent(n)](#indentn)**                                                                      | Adds or removes indentation (spaces) for each line in a multi-line string.                                                                         |
| **[stripIndent()](#stripindent)**                                                              | Removes common leading spaces from every line in a multi-line string.                                                                              |
| **[translateEscapes()](#translateescapes)**                                                    | Converts escape sequences like `\\n`, `\\t` into actual characters.                                                                                |
| **[isEmpty(), isBlank()](#isempty-isblank)**                                                   | `isEmpty()` checks if the string has zero length. `isBlank()` checks if it's empty or only contains whitespace.                                    |
| **[format(), formatted()](#format-formatted)**                                                 | Formats strings using placeholders (`%s`, `%d`, etc.) like `String.format()` or using the instance method `formatted()`.                           |

### Create and Concatenate a String
__Create:__
```java
    String name = "Alan Walker";
    String name = new String("Alan Walker");
```
__Concatenation:__
```java
    public static void main(String[] args) {
    String a = "a";
    String b = "b";

    String c = a + b;
    System.out.println(c);
    
    c = a.concat(b);
    System.out.println(c);
}
```
#
`Notice: You need to remember, we will plus from left to right`
1. if first is String, can all String
```java
System.out.println("a" + 1 + 2);
```
```text
a12
```

2. if first is number and second number,(plus two number), and 3rd is String (before result addition with String)
```java
System.out.println(1 + 2 + "a");
```
```text
3a
```

3. use parentheses for number, plus in parentheses and then outside
```java
System.out.println("a" + (1+2));
```
```text
a3
```

4. String and Null
```java
System.out.println("a"+null);
System.out.println(null+"a");
```
```text
anull
nulla
```

#
### Length()
```java
String name = "Alan Walker";
System.out.println(name.length());
```
```text
11
```

#
### CharAt()
```java

String name = "Alan Walker"; //position three is a
System.out.println(name.charAt(2)); //because index start from 0
```
```text
a
```

__Get Index don't Exist:__
```java
System.out.println(name.charAt(12));
```
```text
Exception in thread "main" java.lang.StringIndexOutOfBoundsException: String index out of range: 12
	at java.base/java.lang.StringLatin1.charAt(StringLatin1.java:48)
	at java.base/java.lang.String.charAt(String.java:1515)
	at strings.StringsDemo.main(StringsDemo.java:10)
```

#
### IndexOf()
```java
String name = "Doctor Dolittle";

System.out.println(name.indexOf('t')); 
// ==> 3 

System.out.println(name.indexOf('t', 5)); 
//=> 11

System.out.println(name.indexOf("cto"));
//=> 2
        
System.out.println(name.indexOf("Do", 4));
// => 7
        
System.out.println(name.indexOf("A"));
//=> -1   // not found
```

#
### substring()
```java
String name = "John Wayne";
System.out.println(name.substring(3));
//=> n Wayne  // from index 3 to the end

System.out.println(name.substring(3, 8));
//=> n Way    // from index 3 to 8 (not included!) 
System.out.println(name.substring(3, 3));
//=>          // empty string 
System.out.println(name.substring(8, 3));
//=> StringIndexOutOfBoundsException
```

#
### toLowerCase() and toUpperCase()
```java
String name = "John Wayne"; 
System.out.println(name.toLowerCase()); 
//=> john wayne 

System.out.println(name.toUpperCase());
//=> JOHN WAYNE
```

#
### equals(), equalsIgnoreCase()

- equals (absolute correct)
- equalsIgnoreCase (skip uppercase with lowercase)
```java
String name1 = "Alan Walker";
String name2 = "ALAN WALKER";
String name3 = "alan walker";

System.out.println(name1.equals(name2));
System.out.println(name1.equals(name3));

System.out.println(name1.equalsIgnoreCase(name2));
System.out.println(name2.equalsIgnoreCase(name3));
```
```text
false
false
true
true
```

#
### startsWith(), endsWith()
```java
        String name = "Alan Walker";

        System.out.println(name.startsWith("Al")); //==> true
        System.out.println(name.startsWith("al")); //==> false
        System.out.println(name.endsWith("Walker")); //==> true
        System.out.println(name.endsWith("walker")); //==> false
//        System.out.println(name.contains("j")); ==> not compile
```

#
### contains()
```java
        String name = "Alan Walker";

        System.out.println(name.contains("A")); //==> true
        System.out.println(name.contains("a")); //==> true
        System.out.println(name.contains("walker")); //==> false
        System.out.println(name.contains("Walker")); //==> true
```

#
### replace()
```java
String str = "abcdeabc";
System.out.println(str.replace('c', 'y'));
//=> abydeaby   // replaces all instances of 'c' with 'y'
System.out.println(str.replace("c", "y"));
//=> abydeaby   // parameters can be both String and char
System.out.println(str.replace("bcd", "xyz"));
//=> axyzeabc
```
#
### strip(), trim(), stripLeading(), stripTrailing()
```java
String str = "    abc  ";
System.out.println("|" + str.strip() + "|");
//=> |abc|
System.out.println("|" + str.trim() + "|");
//=> |abc|    // same as strip, but supports Unicode
System.out.println("|" + str.stripLeading() + "|");
//=> |abc  |
System.out.println("|" + str.stripTrailing() + "|");
//=> |    abc|
```
- whitespaces also includes `\t (tab)`, `\n (new line)`, `\r` (carriage return)
- all escape sequences count as one character in length

#
### indent(n)
- if n = 0 does nothing
- if n > 0 adds the same number of blank spaces to each line
- if n < 0 tries to remove n whitespace characters from the beginning of line
- normalizes existing line breaks
- adds line break at the end if missing

```java
        String name = " Alan Walker";
        System.out.print(name.indent(0));
        System.out.print(name.indent(10));
        System.out.print("         Alan Walker".indent(-10));

        System.out.println();
        System.out.println("Println: ");
        System.out.println(name.indent(0));
        System.out.println(name.indent(10));
        System.out.println("         Alan Walker".indent(-10));
```
```text
 Alan Walker
           Alan Walker
Alan Walker

Println: 
 Alan Walker

           Alan Walker

Alan Walker
```

#
### stripIndent()
- removes all leading incidental whitespace
- normalizes existing line breaks
- does not add line break at the end if missing
```java
       String str = "     John\n    D.\n   Wayne";
        System.out.println("--");
        System.out.println(str);
        System.out.println("--");
        System.out.println(str.indent(2));
        System.out.println("--");
        System.out.println(str.indent(-2));
        System.out.println("--");
        System.out.println(str.stripIndent());
        System.out.println("--");
```
```text
--
     John
    D.
   Wayne
--
       John
      D.
     Wayne

--
   John
  D.
 Wayne

--
  John
 D.
Wayne
--
```

#
### translateEscapes()
```java
        String name4 = "Alan\\tWalker";
        System.out.println(name4);
        System.out.println(name4.translateEscapes());
```
```text
Alan\tWalker
Alan	Walker
```

#
### isEmpty(), isBlank()
```java
System.out.println("".isEmpty()); 
  //=> true 
System.out.println("  ".isEmpty()); 
  //=> false 
System.out.println("".isBlank());  
  //=> true 
System.out.println("  ".isBlank()); 
  //=> true
```

#
### String formating
| String formating symbols |                                           |
|--------------------------|-------------------------------------------|
| `%s`                     | for any type, usually for String          |
| `%d`                     | for integral values (int and long)        |
| `%f`                     | for decimal numbers (float and double)    |
| `%n`                     | inserts a system-dependent line separator |

### format(), formatted()
```java
String name = "John"; 
int numberOfMarbles = 5; 
String printOut1 = name + " has " + numberOfMarbles + " marbles."; 
String printOut2 = String.format("%s has %d marbles.", name, numberOfMarbles); 
String printOut3 = "%s has %d marbles.".formatted(name, numberOfMarbles); 
System.out.println(printOut1); 
System.out.println(printOut2); 
System.out.println(printOut3);
```
```text
John has 5 marbles. 
John has 5 marbles. 
John has 5 marbles.
```
-------------------------

# String builder
- Does not Equal method, so you must convert to String to compare
- StringBuilder is a mutable class which contains a String
- it has many useful methods for manipulating the strings
- StringBuilder name = new StringBuilder("Value");
- Some methods work in the identical way as with a normal String
  
| Method                                           | Description                                                                                   |
|--------------------------------------------------|-----------------------------------------------------------------------------------------------|
| [append()](#append-in-stringbuilder)             | Adds text (string, number, character, etc.) to the end of the current `StringBuilder` object. |
| [insert()](#insert-in-stringbuilder)             | Inserts text at a specified index in the `StringBuilder`.                                     |
| [delete()](#delete-in-stringbuilder)             | Removes a sequence of characters between a start and end index (end exclusive).               |
| [deleteCharAt()](#deletecharat-in-stringbuilder) | Deletes the character at the specified index.                                                 |
| [replace()](#replace-in-stringbuilder)           | Replaces characters between a start and end index with the given string.                      |
| [reverse()](#reverse-in-stringbuilder)           | Reverses the character sequence in the `StringBuilder`.                                       |
| [toString()](#tostring-in-stringbuilder)         | Converts the `StringBuilder` content to a `String` object.                                    |
| [subString()](#substring-in-stringbuilder)       | take a subString by index                                                                     |

#
### append() in StringBuilder
- all arguments are converted to String

```java
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder
            .append(1)
            .append(2L)
            .append(true)
            .append("And");

    System.out.println(stringBuilder);
```
```text
12trueAnd
```


#
### insert() in StringBuilder
```java
StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(1)
                .append(2L)
                .append(true)
                .append("And");

        System.out.println(stringBuilder);

        stringBuilder.insert(2,"C");
        stringBuilder.insert(3,"D");
        System.out.println(stringBuilder);
```
```text
12trueAnd
12CDtrueAnd
```

#
### delete() in StringBuilder
- delete by index from start to end
```java
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder
            .append(1)
            .append(2L)
            .append(true)
            .append("And");
    
    System.out.println(stringBuilder);

    stringBuilder.delete(2,5);
    System.out.println(stringBuilder);
```
```text
12trueAnd
12eAnd
```



#
### deleteCharAt() in StringBuilder
- Delete by index
```java
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(1)
                .append(2L)
                .append(true)
                .append("And");

        System.out.println(stringBuilder);

        stringBuilder.deleteCharAt(2);
        System.out.println(stringBuilder);
```
```text
12trueAnd
12rueAnd
```

#
### replace() in StringBuilder
```java
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(1)
                .append(2L)
                .append(true)
                .append("And");

        System.out.println(stringBuilder);

        stringBuilder.replace(2,4,"Alan");
        System.out.println(stringBuilder);

        //replace with large index, replace goes through the end (no exception)
        stringBuilder.replace(2,200,"Alan");
        System.out.println(stringBuilder);
```
````text
12trueAnd
12AlanueAnd
12Alan
````

#
### reverse() in StringBuilder
```java
    StringBuilder stringBuilder1 = new StringBuilder("Alan");
    stringBuilder1.reverse();
    System.out.println(stringBuilder1);
```
```text
nalA
```

#
### toString() in StringBuilder
```text
 StringBuilder stringBuilder1 = new StringBuilder("Alan");
 String string = stringBuilder1.toString();
```

#
### subString() in StringBuilder
- Return String
```java
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(1)
                .append(2L)
                .append(true)
                .append("And");

        System.out.println(stringBuilder);
        System.out.println(stringBuilder.substring(2,5));
```
```text
12trueAnd
tru
```

##  Comparison between `String` and `StringBuilder`

| Feature           | `String`                                                                            | `StringBuilder`                                                        |
|-------------------|-------------------------------------------------------------------------------------|------------------------------------------------------------------------|
| **Mutability**    | Immutable (cannot be changed after creation)                                        | Mutable (can be modified without creating a new object)                |
| **Performance**   | Slower when performing many string modifications (each change creates a new object) | Faster for multiple modifications (edits the same object in memory)    |
| **Thread Safety** | Thread-safe because it's immutable                                                  | Not thread-safe                                                        |
| **Usage**         | Ideal for constant strings or few modifications                                     | Ideal for frequent modifications (e.g., in loops or string processing) |
| **Methods**       | Methods like `substring()`, `concat()`, `replace()`, etc. return new objects        | Methods like `append()`, `insert()`, `delete()` modify the same object |
| **Memory Usage**  | May use more memory if modified frequently                                          | More memory-efficient for repeated changes                             |
| **To Convert**    | Already a `String`                                                                  | Call `.toString()` to convert to `String`                              |

__Example:__
```java
// String (immutable)
String str = "Hello";
str = str + " World";  // creates a new String

// StringBuilder (mutable)
StringBuilder sb = new StringBuilder("Hello");
sb.append(" World");   // modifies existing object

```

-------------------------------------

## String Pool


### What is String Pool?

--> In Java, the `String Pool` (also known as the String Intern Pool) is a `special memory area` in the Java heap that stores `unique string literals`. It helps `improve memory efficiency` and `performance` when `dealing with many strings.`

-  let's say you create a new string with literal value "Alan"
  - JVM stores it in the memory location known as `String pool` or `intern pool`
- now you create a new `string variable` and assign it a `same literal value`
  - instead of creating a new memory spot for `this literal value`
  - Java will save the memory and look in the `String pool`
  - new variable will point to the existing location in the String pool
- `pool is created at compile-time.`
```java
String name = "Alan";
```

- String at runtime 
```java
String name = "Alan   ".trim(); //trim() is evaluated at run-time

//if you want name save into String pool
//you must add .intern();
//String name = "Alan   ".trim().intern();
```

Example:
```java
        String name = "Alan"; //value store in String pool, for value create compile time
        String name2 = "Alan   ".trim(); //not store in String pool, for .trim() is evaluated at runtime
        String name3 = new String("Alan"); // keyword new instant object
        String name4 = new String("Alan").intern(); //push Alan into String pool
        String name5 = new String("Alan").intern();
        String name6 = "Al"+"an"; //concatenation is done in the compile-time

        System.out.println(name == name2);
        System.out.println(name == name3);
        System.out.println(name == name4);
        System.out.println(name == name5);
        System.out.println(name == name6);
```
```text
false
false
true
true
true
```

⚙️ Why Use a String Pool?
* Saves heap memory
* Improves performance with string comparisons (==)
* Prevents creation of duplicate string objects

🧠 When Is It Created?
* The `string pool` is part of method area (now heap) and is created during class loading (compile-time).
* Managed by the JVM, not the developer.