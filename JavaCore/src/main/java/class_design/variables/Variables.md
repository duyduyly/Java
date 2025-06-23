# Variables

## Keyword
| [Local Variable](#local-variable) | [Instance Variable](#instance-variable) |

## Local variable
__Definition:__
A local variable is declared inside a method, constructor, or block and is only accessible within that block.
- Key Characteristics:
  - Exists only during the execution of the method/block.
  - Must be initialized before use.
  - Not accessible from outside the method.
  - Stored in the stack memory.

---------------------------
## Instance Variable
__Definition:__
An instance variable is declared inside a class but outside any method. It is tied to a specific object (instance) of the class.
- Key Characteristics:
  - Each object has its own copy.
  - Gets default value if not initialized (e.g., 0 for int, null for objects).
  - Can have access modifiers (`private`, `public`, etc.).
  - Stored in the heap memory as part of the object.

-----------------------------
```java
public class App{
  public class Variables {

    public static final int DEFAULT_OLD = 0; // Final must be assigned when you initialize
    public static int old; //Do not assign a value, for default value is O
    public static double amount; // Do not assign a value, for default value is OD
    public int old2;

    public static void main(String[] args) {
      Variables var = new Variables();
      var.demo();

      System.out.println("Static Final: " + DEFAULT_OLD + " Final must be assigned when you initialize");
      System.out.println("Int Static : " + old);
      System.out.println("Double Final: " + amount);
      System.out.println("Instance Value: " + var.old2);
    }


    // local variable, just use local
    public void demo() {
      final String name = "adsad";
//        name = "sadas"; // can not change if you use keyword final

      System.out.println("Final Name: " + name);
      String name2 = "sdsd";
      name2 = "asdasdad";

      int old3;//Must initialize value
      System.out.println(old3);

      final int old4;//Must initialize value
      System.out.println(old4);

      System.out.println("Name 2: " + name2);

    }
  }
}
```
- `final` keyword is Constant (Can not change when you initialized)
- `instance variable` and `static variable` when you do not initialize, the compiler will set default value (unless final variable)
- Example:
```java
public class Variables {
  public static final int DEFAULT_OLD = 0; // Final must be assigned when you initialize

  public static void main(String[] args) {
    Variables var = new Variables();
    var.demo();

    System.out.println("Static Final: " + DEFAULT_OLD + " Final must be assigned when you initialize");
  }

  public static int old; //Do not assign a value, for default value is O
    public static double amount; // Do not assign a value, for default value is OD
    public int old2;

    public static void main(String[] args) {
        Variables var = new Variables();

        System.out.println(old);
        System.out.println(amount);
        System.out.println(var.old2);
    }
}
```

- Final variable and Local variable must initialize value
Example:
```java
public class Variables {

    public static final int DEFAULT_OLD = 0; // Final must be assigned when you initialize

  // local variable, just use local
  public void demo() {
    final String name = "adsad";
//        name = "sadas"; // can not change if you use keyword final

    System.out.println("Final Name: " + name);
    String name2 = "sdsd";
    name2 = "asdasdad";

    int old3;//Must initialize value
    System.out.println(old3);

    final int old4;//Must initialize value
    System.out.println(old4);

    System.out.println("Name 2: " + name2);
  }
}
```