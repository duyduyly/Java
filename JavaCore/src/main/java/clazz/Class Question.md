# OCP Test Exam About Class

### 1. What will be the output of the following code?
```java
class Parent {
    static {
        System.out.println("Parent static block");
    }

    {
        System.out.println("Parent instance block");
    }

    public Parent() {
        System.out.println("Parent constructor");
    }
}

class Child extends Parent {
    static {
        System.out.println("Child static block");
    }

    {
        System.out.println("Child instance block");
    }

    public Child() {
        System.out.println("Child constructor");
    }
}

public class TestClass {
    public static void main(String[] args) {
        new Child();
    }
}
```

A. 
```text
Parent static block  
Child static block  
Parent instance block  
Parent constructor  
Child instance block  
Child constructor

```

B.
```text
Child static block  
Parent static block  
Parent instance block  
Parent constructor  
Child instance block  
Child constructor
```

C.
```text
Parent static block  
Parent instance block  
Parent constructor  
Child static block  
Child instance block  
Child constructor
```

D.
```text
Parent instance block  
Parent constructor  
Child instance block  
Child constructor  
Parent static block  
Child static block
```

[Answer 1](#-answer-1-a)

------------------------------------
### 2. In which of these variable declarations will the variable remain uninitialized unless it is explicitly initialized?

Select the one correct answer.

A. Declaration of an instance variable of type `int` <br/>
B. Declaration of a static variable of type `float` <br/>
C. Declaration of a local variable of type `float` <br/>
D. Declaration of a static variable of type `Object` <br/>
E. Declaration of an instance variable of type `int[]` <br/>

[Answer 2](#-answer-2-c)

<br />

-----------------
###  3. Class and Inheritance

Which of the following statements about class inheritance are correct? <br/>
A. A class can extend more than one class. <br/>
B. A class can implement multiple interfaces.  <br/>
C. An abstract class must have at least one abstract method.  <br/>
D. A concrete class can extend an abstract class.   <br/>

[Answer 3](#-answer-3-b-and-d)

<br />

-----------------
###  4. What is the output of the following code? <br/>
```java
interface Walk {
    default void move() {
        System.out.println("Walking");
    }
}

interface Run extends Walk {
    default void move() {
        System.out.println("Running");
    }
}

class Animal implements Run {}

public class Test {
    public static void main(String[] args) {
        new Animal().move();
    }
}
```
A. Compilation error  <br/>
B. Walking  <br/>
C. Running  <br/>
D. No output  <br/>

[Answer 4](#-answer-4-c)
<br/>

-------------
### 5. Which of the following method declarations __are valid overloads__ of `public void print(int num)`? <br/>
A. `public void print(Integer num)`    <br/>
B. `private void print(int num)`    <br/>
C. `public int print(int num)`    <br/>
D. `public void print(double num)`    <br/>

[Answer 5](#-answer-5-a-and-d)
<br/>

-------------------------

### 6.

```java
sealed interface MyInterface permits MyClass {}
non-sealed class MyClass implements MyInterface{}
```

__Which of the following method can be inserted in to MyInterface?__ <br/>
A.
```java
void int getSpeed(){return 10;}
```

B.
```java
static int getSpeed(){return 10;}
```

C.
```java
default int getSpeed();
```

D.
```java
public default int getSpeed(){return 10;}
```

E.
```java
static abstract int getSpeed();
```

[Answer 6](#-answer-6-b-and-d)

<br/>

-------------------

-------------------
# Answer:

### ✅ Answer 1: `A`

💡 Explanation
- In Java, the order of execution is:
  1. Static blocks (once per class) → Parent → then Child
  2. Instance blocks and constructors (per object) → Parent → then Child </br>

So this is the actual flow:
```text
Parent static block        ← (Parent class loads)
Child static block         ← (Child class loads)

Parent instance block      ← (Creating Parent part of Child)
Parent constructor
Child instance block       ← (Child part now runs)
Child constructor
```

🔥 Key Concepts for OCP:

| Topic                 | What to Remember                                    |
| --------------------- | --------------------------------------------------- |
| Static blocks         | Run once when the class is loaded                   |
| Instance blocks       | Run before the constructor, for each object         |
| Constructor chaining  | Parent constructor runs before child’s              |
| Inheritance & loading | Parent class is loaded and constructed before child |


#

### ✅ Answer 2: `C`
Declaration of a local variable of type float

- A: Instance variable of type int → automatically `initialized` to `0`
- B: Static variable of type float → automatically `initialized` to `0.0f`
- C: Local variable of type float → remains `uninitialized`
- D: Static variable of type Object → automatically `initialized` to `null`
- E: Instance variable of type int[] → automatically `initialized` to `null`

#

### ✅ Answer 3: `B and D`
A is false: Java supports single inheritance. <br/>
C is false: abstract class may have zero abstract methods

#

### ✅ Answer 4: `C`
`Animal` inherits from `Run`, which overrides `move()` from `Walk`

#

### ✅ Answer 5: `A and D`
B is not overload — same signature, only visibility change. <br/>
C is not overload — same signature, only return type differs

#

### ✅ Answer 6: `B and D`
- A: Interface can not have instance method
- B: Static method support to element method in Interface
- C: Wrong because Default method must have body method
- D: Default method support for Interface
- E: In Interface do not use abstract keyword
