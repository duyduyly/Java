# OCP Test Exam About Class

1. What will be the output of the following code?
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

### ✅ `Correct Answer: A` </br>

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


------------------------------------

2. 
