## Question:
Source from: https://www.myexamcloud.com/blog/ocp-java-17-developer-1z0-829-free-practice-questions.article

<br/>

----------------

### Question 1.
```java
class MyExamCloud{
    
    public static void main(String[] args){
        var x = 1;
        var y = 2;

        System.out.println("" + x + y + " ");
        System.out.println(x + y + " ");
        System.out.println(x + " " + y);
    }
}
```
__which is the output?__ <br/>
A.
```text
3 3 3
```

B. 
```text
12 3 1 2
```


C. 
```text
3 3 12 
```

D.
```text
12 12 3
```

[Answer 1](#-answer-1-__b__)

<br/>

---------------
### Question 2. 

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

[Answer 2](#-answer-2-__b-and-d__)


<br/>

---------------
### Question 3.

Consider following three statements.
1. The variables declared inside a method are called as class variables.
2. The class variables are initialized to its default value.
3. The variable 's' in the declaration 'short s = 10' should be an instance variable.

Which is/are true?

A: Only I. <br/>
B: Only II. <br/>
C: Only III. <br/>
D: Only I and II. <br/>
A: Only I and III. <br/>

[Answer 3](#-answer-3-b)


<br/>

---------------


#

## Answer:
### ✅ Answer 1: __B__
(Plus String from left to right)

### ✅ Answer 2: __B and D__
- A: Interface can not have instance method
- B: Static method support to element method in Interface
- C: Wrong because Default method must have body method
- D: Default method support for Interface
- E: In Interface do not use abstract keyword

### ✅ Answer 3: B
*Complain :*
1. Variables declared inside a method are local variables, not class variables.
   - Class variables are static variables declared at the class level (outside methods) with the static keyword.
2. In Java, class (i.e., static) variables and instance variables are automatically initialized to default values (e.g., 0 for int, null for objects).
3. There is no requirement that s should be an instance variable.
   - It can be a local variable, instance variable, class variable, or even a parameter — depending on where it's declared.
