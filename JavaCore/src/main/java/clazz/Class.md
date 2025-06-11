# Class

## keyword
| [Inheritance](#inheritance) | [Super](#super) | [This](#this-and-this) | [Class Modifier](#class-modifiers) |</br>
| [Object Class](#object-class) | [Order of Initialization](#order-of-initialization) | [Constructors](#constructors) | </br>


## Inheritance
- Java Supports single Inheritance
  - class can only one Direct SupperClass
  - (unlike in some other languages, Like C++)
- But Class can implement multiple interfaces
### Super
- `super()` is used to call the constructor of the `superclass` (parent class).
- If there is no explicit `this()` or `super()` in the first line of the `constructor`
  - the compiler will insert `super()` at the `beginning` of every `constructor`
- Can be called only once
- Must be called in `the first line` of the `constructor`
- And `supper` like this but use to call `method`, `instance variable` or method from `supper class`.

__Example:__
```java
class Animal {
    private String name = "Dog";
    
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    
    Animal() {
        System.out.println("Animal constructor");
    }
    public void sound(){
      System.out.println("Default Sound");
    }
}

class Dog extends Animal {
    Dog() {
        super(); // calls Animal() constructor
        System.out.println("Dog constructor");
    }
    
    public void sound(){
      super.sound(); // call sound method in supper class
      super.setName("Dog"); // call setter method in supper class,
      // besides that you can call instant variable, if instance variable is public or protected
      System.out.println(super.getName());
    }
    
}

public class Test {
    public static void main(String[] args) {
        Dog d = new Dog();
    }
}

```

------------------------
### This() and This
### 🔹`This`:
- `this` is a reference to the `current object`
```java
public class Car {
    String model;

    public Car(String model) {
        this.model = model; // 'this.model' refers to the instance variable
    }

  public Car getCar() {
    return this; // this refers to the Object(Car) to Return
  }

  public void printInfo() {
        //'this' refers to instance variable in class 
    System.out.println(this.model);
  }
}
```

### 🔹`This()`:
- It differs from `this`, which refers to the current object. 
- `this()` is a special syntax used to call another constructor in the same class.

- __Rules for using `this()`:__
  - `this()` can only be called in the first line in the constructor
  -  this() can be called only once
  - you must be careful not to create a "cycle"
    - constructors which call each other ad infinitum
  - `this()` cannot be used in static methods.

__Example:__
```java
public class Student {
    String name;
    int age;

    public Student() {
        this("Unknown", 0); // Calls another constructor
    }

    public Student(String name, int age) {
        this.name = name;   // Refers to the current object's field
        this.age = age;
    }

    public void printInfo() {
        System.out.println(this.name + " is " + this.age + " years old.");
    }
}

```
----------------------
 
## Class Modifiers
- Final 
- abstract
- static
- sealed  (java 17)
- non-sealed (java 17)

## Object Class
- All Java Classes implicitly inherit from java.lang.Object class
- Object is the only class which doesn't have a parent class
```java
    public class Dog{}
    public class Dog extends java.lang.Object {}
//do not need extends Object Because Default class extended Object
```
- Every class has access to methods defines in Object class
  - e.g. toString(), equals(), hashCode(), etc.

## Order of Initialization
```java
    public class Dog{
    
    //1
    static {
        System.out.println("static block");
    }

    //2
    {
        System.out.println("instant block");
    }
    
    //3
    public Dog(){
        System.out.println("Constructor");
    }
    
    //4
    public void bark(){
        System.out.println("The Method Bark");
    }
}
```
- Static block will run First (1)
- and then Instant Block will run
- next, Constructor (3), and Finally is Method when we call it (4)

__Example 1:__
```java
public class ClassStructure {
    
    //(1)
    static {
        System.out.println("Static block");
    }

    //(2)
    {
        System.out.println("Instance block");
    }

    //(3)
    public ClassStructure() {
        System.out.println("Constructor block");
    }

    //(4) when you call it
    public void method(){
        System.out.println("method block");
    }

    public static void main(String[] args) {
        ClassStructure classStructure = new ClassStructure();

        System.out.println("Call Method: ");
        classStructure.method();
    }
}
```
```text
Static block
Instance block
Constructor block
Call Method: 
method block
```

------------------
__Example 2:__
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
```text
Parent static block  
Child static block  
Parent instance block  
Parent constructor  
Child instance block  
Child constructor
```

## Constructors
- Special methods which are called every time you create an instance of an object 
```java
  public class Dog{
    
    // not return type
    // and Name like The class Name
    public Dog(){
      System.out.println("Woof");
    }

    
    //normal Method
    public void bark(){
      System.out.println("Gaw Gaw");
    }
}
```
- name of the constructor must match the name of the class
- Constructors don't have a return type (!!!)
  - if the return type is stated,then it's just a normal method

### A Class Can multiple Constructor
- Constructor overloading:
```java
public class Dog {
    private String name;
    private int age;

    public Dog() {
        System.out.println("Woof!");
    }

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Dog(String name) {
        this.name = name;
    }

    public Dog(int age) {
        this.age = age;
    }

    public Dog(boolean isPuppy, String name) {
        this.age = isPuppy ? 0 : -1;
        this.name = name;
    }
}
```
- if class No Define Constructor
```java
class Dog { 
  public String name; 
  public String age; 
  
  //
} 
class App{
  public static void main(String[] args) {
    Dog dog = new Dog();
  }
}
```
-------
- If the class has no defined constructors the default constructor is created
- That constructor will be called when you make an instance of the class
```java
  // Compiler auto generate
  Dog() { }
```

- Default constructor is created only if no other constructor is present
```java
class Dog { 
  public String name; 
  public String age; 
  public Dog (String name, int age) { 
    this.name = name; 
    this.age = age; 
  }
  // this case default Constructor will not Autogenerate
} 
```

### Constructor access modifiers
* Constructors are usually made `public`
  * But you can also make them `protected`, `default` or `private`
* Private constructors are used if you don't want public no-argument constructor
  to be generated by the compiler


*  In this case, the instance is usually created via some static method, and not
   using the keyword new
* We have seen this behavior in classes used to create Dates and Times, e.g.
```java
LocalDate now = LocalDate.now();
```





