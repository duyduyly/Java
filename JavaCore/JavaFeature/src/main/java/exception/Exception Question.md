# OCP TEST EXAM QUESTION ABOUT EXCEPTION

## 1. What will be the result of executing the following code?

```java
public class TestException {
  public static void main(String[] args) {
    try {
      methodA();
    } catch (RuntimeException e) {
      System.out.println("Caught RuntimeException");
    } catch (Exception e) {
      System.out.println("Caught Exception");
    }
  }

  static void methodA() throws Exception {
    throw new IllegalArgumentException("Problem in methodA");
  }
}

```
A. Caught RuntimeException

B. Caught Exception

C. Compilation error

D. Program compiles but throws an uncaught exception at runtime

### ✅`Correct Answer: A`

Explanation:
- IllegalArgumentException is a subclass of RuntimeException.
- Even though methodA() is declared to throw Exception, the method actually throws a RuntimeException (unchecked).
- The catch block for RuntimeException catches the exception first.

__result the code:__
```text
Caught RuntimeException
```

-------------------------------------

## 2. Consider the following code:
```java
class MyResource implements AutoCloseable {
    public void close() throws Exception {
        System.out.println("Closing resource");
        throw new Exception("Exception in close");
    }

    public void use() throws Exception {
        System.out.println("Using resource");
        throw new Exception("Exception in use");
    }
}

public class TestResource {
    public static void main(String[] args) {
        try (MyResource res = new MyResource()) {
            res.use();
        } catch (Exception e) {
            System.out.println("Caught: " + e.getMessage());
            for (Throwable t : e.getSuppressed()) {
                System.out.println("Suppressed: " + t);
            }
        }
    }
}
```
What is the output?

A.
```text
Using resource  
Closing resource  
Caught: Exception in close
```

B.
```text
Using resource  
Closing resource  
Caught: Exception in use
Suppressed: java.lang.Exception: Exception in close
```

C.
```text
Using resource  
Closing resource  
Caught: Exception in use
```

D. Compilation error

### ✅ `Correct Answer: B`
💡 Explanation:
- The `use()` method throws an exception: "Exception in use".
- The `close()` method is still called automatically (because of `try-with-resources`), and it also throws an exception: "`Exception in close`".
- Java keeps the first thrown exception ("Exception in `use`") and adds the later one ("Exception in `close`") as a suppressed exception.
- So the main catch block catches "Exception in `use`", and "Exception in `close`" is reported via `e.getSuppressed()`.

-----------------------
## 3. What will be the result of compiling and running the following code?
```java
class SuperClass {
    void method() throws Exception {
        System.out.println("SuperClass method");
    }
}

class SubClass extends SuperClass {
    @Override
    void method() throws RuntimeException {
        System.out.println("SubClass method");
    }
}

public class TestOverride {
    public static void main(String[] args) throws Exception {
        SuperClass obj = new SubClass();
        obj.method();
    }
}
```
A. Compilation error due to incompatible exceptions <br />
B. SuperClass method <br />
C. SubClass method <br />
D. Compilation error because `main` must catch the exception from `method()` <br />

### ✅ `Correct Answer: C`

__💡 Explanation:__
- The superclass method declares throws Exception (a __checked__ exception).
- The subclass method overrides it and declares `throws RuntimeException` (an __unchecked__ exception).
- __This is allowed__ in Java: overridden methods can throw __fewer or narrower exceptions__ (or unchecked ones instead of checked).
- The actual object is a `SubClass`, so `SubClass.method()` is called, and it prints:
```text
sql
```
`SubClass method `

The key concept being tested is exception declarations during method overriding.

------

## 4. What will be the output of the following code?

```java
public class TestCatchOrder {
    public static void main(String[] args) {
        try {
            throw new IllegalArgumentException("Invalid argument");
        } catch (ArithmeticException | NullPointerException e) {
            System.out.println("Caught Arithmetic or NullPointer");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught IllegalArgumentException");
        } catch (Exception e) {
            System.out.println("Caught general Exception");
        }
    }
}
```
A.
```text
Caught Arithmetic or NullPointer
```

B.
```text
Caught IllegalArgumentException
```

C.
```text
Caught general Exception
``` 


</br>D. Compilation error due to multi-catch order

### ✅ `Correct Answer: B`

__💡 Explanation:__
- The `try` block throws an `IllegalArgumentException`.
- The first `catch` block checks for `ArithmeticException | NullPointerException`, which doesn't match.
- The second `catch` block does match `IllegalArgumentException`, so it's executed.
- So output is:
```text
Caught IllegalArgumentException
```
✅ No error here because the multi-catch is valid and doesn't overlap with `IllegalArgumentException`.

---------------------
About try-finally and Return

## 5. What will be the output of the following code?
```java
public class TestFinally {
    public static void main(String[] args) {
        System.out.println(testMethod());
    }

    static int testMethod() {
        try {
            return 1;
        } finally {
            return 2;
        }
    }
}
```

A. 1 </br>
B. 2 </br>
C. Compilation error </br>
D. Runtime exception </br>

### ✅` Correct Answer: B`

__💡 Explanation:__
- The try block returns 1, but the finally block also has a return 2.
- In Java, the finally block always executes, even if there’s a return in the try.
- If both try and finally have return statements, the finally return overrides the try.</br>
=> `So, 2 is returned.`

--------------------
about Checked vs. Unchecked Exceptions

## 6. What will happen when you try to compile and run the following code?
```java
public class TestChecked {
    public static void main(String[] args) {
        throwException();
    }

    static void throwException() {
        throw new Exception("Checked exception thrown");
    }
}
```
A.Checked exception thrown    </br>
B.Program compiles, but throws an exception at runtime    </br>
C.Compilation error: unhandled exception    </br>
D.Compilation error: method must declare `throws RuntimeException`    </br>

### ✅ Correct Answer: C

__💡 Explanation:__
- `throw new Exception(...)` throws a checked exception.
- Checked exceptions must either be declared in the method signature using `throws` or be caught in a` try-catch` block.
- In `throwException()`, we are throwing a checked exception (`Exception`) but not declaring it with `throws Exception`.
- Hence, compilation error.

✅ If the exception was RuntimeException, it would compile fine (unchecked). </br>
🧠 This is a classic OCP exam trap — make sure you know the difference between checked and unchecked exceptions!


-------------------------------------
About Rethrowing Exceptions</br>
## 7. What will be the result of compiling and running the following code?

```java
public class TestRethrow {
    public static void main(String[] args) {
        try {
            throwException();
        } catch (Exception e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }

    static void throwException() throws Exception {
        try {
            throw new IllegalArgumentException("Invalid");
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
}
```
A. Caught: Invalid         </br>
B. Compilation error: cannot throw IllegalArgumentException         </br>
C. Compilation error: must catch or declare IllegalArgumentException         </br>
D. Compilation error: unhandled exception in catch block         </br>


### ✅ `Correct Answer: A`
__💡 Explanation:__
- `IllegalArgumentException` is an unchecked exception (subclass of `RuntimeException`).
- Even though `throwException()` is declared `throws Exception`, it's actually rethrowing an unchecked exception, which is perfectly fine.
- At runtime, the exception is thrown and caught in the outer `try-catch`, and "`Caught: Invalid`" is printed.
- So it compiles and runs fine ✅.

⚠️ Important OCP Concept: </br>
- Rethrowing an unchecked exception doesn't require you to declare it.
- Declaring `throws Exception` in the method signature is __legal but not required__ for unchecked exceptions.

-------------------------
## 8. What will happen when you try to compile the following code?
```java
public class TestMultiCatch {
    public static void main(String[] args) {
        try {
            System.out.println("Trying something risky...");
        } catch (NullPointerException | ArithmeticException e) {
            System.out.println("Caught a runtime exception");
        } catch (Exception e) {
            System.out.println("Caught a general exception");
        }
    }
}
```

A.Trying something risky...  </br>
B.Code compiles but throws a runtime exception when executed  </br>
C.Compilation error due to incompatible exception types in multi-catch  </br>
D.Compilation error due to unreachable catch block  </br>

### ✅ `Correct Answer: A`
__💡 Explanation:__
- Because the method `do not throw` `exception`
- `NullPointerException` and `ArithmeticException` are both unchecked exceptions and do not have a subclass-superclass relationship, so you can catch them together using multi-catch.
- Since the `try` block doesn't throw any exception, no catch block executes.
- So output is:
```text
Trying something risky...
```
✅ Multi-catch is legal as long as the exceptions are disjoint (no subclass-superclass relationship) and are not duplicated.

🧠 Pro Tip for OCP: </br>
If you do this 👇, it will NOT compile:
```java
catch (IOException | Exception e) { }
```
Because IOException is a subclass of Exception — and Java will say: </br>
`"Alternatives in a multi-catch statement cannot be related by subclassing."`

----------------------------
## 9. What will be the output of the following code?
```java
class MyAutoClose implements AutoCloseable {
    private String name;

    MyAutoClose(String name) {
        this.name = name;
    }

    public void close() throws Exception {
        System.out.println("Closing " + name);
        throw new Exception("Exception from " + name);
    }
}

public class TestSuppressed {
    public static void main(String[] args) {
        try (MyAutoClose res1 = new MyAutoClose("Res1");
             MyAutoClose res2 = new MyAutoClose("Res2")) {
            throw new Exception("Exception in try block");
        } catch (Exception e) {
            System.out.println("Caught: " + e.getMessage());
            for (Throwable t : e.getSuppressed()) {
                System.out.println("Suppressed: " + t.getMessage());
            }
        }
    }
}
```
__What is the output?__

A.
```text
Closing Res2  
Closing Res1  
Caught: Exception in try block  
Suppressed: Exception from Res1  
Suppressed: Exception from Res2
```

B.
```text
Closing Res1  
Closing Res2  
Caught: Exception in try block  
Suppressed: Exception from Res1  
Suppressed: Exception from Res2
```

C.
```text
Caught: Exception from Res2  
Suppressed: Exception in try block  
Suppressed: Exception from Res1
```

D.
```text
Closing Res2  
Closing Res1  
Caught: Exception in try block  
Suppressed: Exception from Res2  
Suppressed: Exception from Res1
```

### ✅ `Correct Answer: A`

__💡 Explanation:__
- In try-with-resources, resources are closed in reverse order of declaration. So:
  - res2.close() is called first → throws "Exception from Res2"
  - then res1.close() → throws "Exception from Res1"
- The try block also throws "Exception in try block"
- Java keeps the main exception from the try, and the others become suppressed

📤 So the output is:
```text
Closing Res2  
Closing Res1  
Caught: Exception in try block  
Suppressed: Exception from Res1  
Suppressed: Exception from Res2
```
🔥 Order of close() is last declared → first </br>
🔥 Suppressed exceptions come from close() methods

-----------------
## 10. What will be the output of the following code?
```java
public class TestFinallyReturn {
    public static void main(String[] args) {
        System.out.println(method());
    }

    static String method() {
        try {
            throw new RuntimeException("Problem in try");
        } catch (RuntimeException e) {
            System.out.println("Caught: " + e.getMessage());
            return "From catch";
        } finally {
            System.out.println("In finally");
            return "From finally";
        }
    }
}
```
A.
```text
Caught: Problem in try  
In finally  
From catch
```

B.
```text
Caught: Problem in try  
In finally  
From finally
```

C.
```text
In finally  
Caught: Problem in try  
From catch
```

D. Compilation error due to return in finally block

### ✅ `Correct Answer: B`

__💡 Explanation:__
- The `try` throws a `RuntimeException`.
- It is caught in the `catch`, which returns `"From catch"`.
- BUT: `finally` always executes, and it also has a return statement.
- When a `finally` block has a `return`, it overrides any return from `try` or `catch`.

🧠 So the actual flow is:
```text
Caught: Problem in try  
In finally  
→ return "From finally"
```

🛑 Pro Tip: Return in finally is legal, but dangerous — it swallows exceptions and overrides all previous returns. That's a common OCP trap!