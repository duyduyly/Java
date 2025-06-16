# Class

## keyword
| [Inheritance](#1-inheritance) | [Super](#2-super) | [This](#3-this-and-this) | [Class Modifier](#4-class-modifiers) |</br>
| [Object Class](#5-object-class) | [Order of Initialization](#6-order-of-initialization) | [Constructors](#7-constructors) | </br>
| [Static Members](#8-static-members)| [Boxing and Unboxing](#9-boxing-and-unboxing) | [Inheriting Methods](#10-inheriting-methods) |

## 1. Inheritance
- Java Supports single Inheritance
  - class can only one Direct SupperClass
  - (unlike in some other languages, Like C++)
- But Class can implement multiple interfaces

<br />

## 2. Super
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

<br />

## 3. This() and This
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

<br />

## 4. Class Modifiers
- Final 
- abstract
- static
- sealed  (java 17)
- non-sealed (java 17)


<br />

## 5. Object Class
- All Java Classes implicitly inherit from java.lang.Object class
- Object is the only class which doesn't have a parent class
```java
    public class Dog{}
    public class Dog extends java.lang.Object {}
//do not need extends Object Because Default class extended Object
```
- Every class has access to methods defines in Object class
  - e.g. toString(), equals(), hashCode(), etc.

<br />

## 6. Order of Initialization
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

<br />

## 7. Constructors
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

<br />

## 8. Static Members
- In Java, a `static member` belongs to the `class itself`, not to any specific object.
- __That means:__ All instances share the same static member.
- You can access it without creating an object of the class.
- Types of Static Members
  - `Static Variable` (Class Variable)
  - `Static Method`
  - `Static Block`
  - `Static Nested Class`

```java
public class Example{
  static int count; //static variable
  
  public Example() {
    count++;
  }

  //static method
  public static void greet() {
    System.out.println("Hello!");
  }
  
  //static block
  static {
     count = 0;
  }

  //Static Nested Class
  static class Inner {
    public void show() {
      System.out.println("Inside static nested class");
    }
  }
}

public class App{
  public static void main(String[] args) {
     
    //Static variable 
    System.out.println("Before Count: "+Example.count);
    new Example();
    System.out.println("After Count: "+Example.count);
    
    //static method
    Example.greet();

    //Nested static  Class
    Outer.Inner obj = new Outer.Inner();
    obj.show();
    
  }
}
```

<br />

## 9. Boxing and Unboxing
- Boxing: converting a primitive into its wrapper class
  - (putting primitive in the "box")
- Unboxing: converting a wrapper class to a primitive
  - (getting a primitive out of the "box")
```java
// explicit
int a = 3;
Integer b = Integer.valueOf(a);
// int -> Integer (boxing)
int c = b.intValue();
// Integer -> int (unboxing)
```

```java
// implicit
int a = 3;
Integer b = a;
// int -> Integer (autoboxing)
int c = b;
// Integer -> int (unboxing)
```

```java
// Java will also autocast a smaller primitive into the larger one
// BUT Java will not do both automatic operations at the same time!!

int x = 7;
long y = x;
// autocasting, OK

Long z = x;
// autocasting and autoboxing cannot be done at once => NOK!
```

```java
// if you need both autocasting and autoboxing,
// one of these operations should be done by hand (or both)

int x = 7;

// explicit boxing (w/ autocasting)
Long z = Long.valueOf(x);

// explicit casting (w/ autoboxing)
Long z = (long) x;

// explicit everything
Long z = Long.valueOf((long)x);
```
```java
// be careful when working with primitive literals

Long x = 10;
//=> NOK, autocasting and autoboxing is required

Long y = 10L;
//=> OK, only autoboxing is required
```

<br />

## 10. Inheriting Methods
- `subclass` can override an `inherited method`
  -  subclass declares `a new implementation `for an inherited method
  - with` same signature` (name & parameters)
  - and `covariant return type`
-  the property of the object to take many different forms is called `polymorphism`

```java
class Mammal{
    public void speak(){
      System.out.println("Mammal sound");
    }
}

class Dog extends Mammal{
  @Override
  public void speak() {
    System.out.println("Woof!");
    super.speak(); // can use supper to call method of ParentClass
  }
}
```

------------
#### Method Overriding Rules
1. Overriden method must have `the same signature` as superclass method
2. Overriden method must be at least `as accessible` as the original method
3. Overriden method may not declare a checked exception that is
   `new or broader` then the one in the original method
4. Return type of overriden method must be `the same or a subtype`
   of the return type of the original method (covariant return types)


__Covariant return types:__
```java
class Item { 
  protected Number calculatePrice (float price) { 
    return price + price * 0.2; 
  } 
} 

 public class Shoe extends Item {  
  @Override //Double is subtype of Number
  public Double calculatePrice (float shoePrice) { 
    return (shoePrice + shoePrice * 0.2) * 1.05; 
  } 
  public static void main(String[] args) { 
    System.out.println(new Item().calculatePrice(100)); 
    System.out.println(new Shoe().calculatePrice(100)); 
  } 
}
```

__Exceptions:__
```java
// checked exception FileNotFoundException is subclass of IOException 
class A { 
  public void greet() throws IOException { } 
  public void sayHello() { } 
  public void leave() {} throws FileNotFoundException {}  
}

public class B extends A {
  public void greet() throws FileNotFoundException { } //OK
  public void sayHello() throws IOException { } //NOK
  public void leave() throws IOException { } //NOK
}
```
----------
#### Overriding private and static methods
-  if the method is private, it's not visible to other classes
   - the method with the same signature is subclass is independent of that method
   -  this is not overriding, it's just completely different method

- it the method is static, "overriden" method must also be declared static
  - this is not overriding, since every method belongs to its own class
  - this is called hiding the method
- methods marked as final cannot be overriden nor hidden !!

----------
#### Hiding a static method
```java
class A { 
  public static void greet() { System.out.println("Hello."); } 
} 
public class B extends A { 
 
  @Override //putting here would result with compilation error!
  public static void greet() { System.out.println("Good afternoon."); } 
  public static void main(String[] args) { 
    A.greet(); 
    B.greet(); 
  } 
}
```
----------
#### Variables cannot be overriden, only hidden
```java
class Mammal { 
  public String name = "Unknown"; 
} 
public class Dog extends Mammal { 
  public String name = "Rex"; //Dog's name "hides" Mammal's name

  public static void main(String[] args) {   
    Dog d = new Dog(); 
    Mammal m = d;   //the reference is of the type Mammal, pointing to Dog object
    System.out.println(d.name); 
    System.out.println(m.name); 
  }
}
```
