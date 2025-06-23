## Beyond classes

| [Enum](#enum) | [Sealed Class](#sealed-class) | [Records](#records) | </br>
| [Nested class](#nested-class) |


## Enum
What is the enum?
- Enum (enumeration) is a fixed set of constants 
- Enum provides type-safe checking
  - It's impossible to create an invalid enum value
- Common Examples:
  - Seasons, Compass directions, days of the week, deck of cards, etc...
- Enums can have constructors and instance methods
- Enums can implement abstract methods 
  - Class Compass3 is example for this one

__Simple Enum:__

```java
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public enum Compass {
    NORTH, SOUTH, EAST, WEST
}

@Getter
public enum Compass2 {
    NORTH("North"), SOUTH("South"), EAST("East"), WEST("West");
    private String value;
}

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Compass3 {
    NORTH(1, "North"), SOUTH(2, "South"), EAST(3, "East"), WEST(3, "West");
    private String value;
    private Integer key;
}

public class App {
    public static void main(String[] args) {
        System.out.println(class_design.beyond_clazz.enums.Compass.SOUTH);
        System.out.println(Compass2.SOUTH.getValue());

        System.out.println(Compass3.SOUTH.getKey());
        System.out.println(Compass3.SOUTH.getValue());

        System.out.println(Compass3.SOUTH.getValue().equals("South"));
        System.out.println(Compass3.SOUTH.getKey() == 1);


        //Enum usually use in switch case 
        Compass N = Compass.NORTH;
        String result = switch (N) {
            case NORTH -> {
                System.out.println("You are headed North.");
                yield "You are headed North.";
            }
            case SOUTH -> {
                System.out.println("You are headed South");
                yield "You are headed South";
            }
            default -> "Default Value";
        };

        System.out.println(result);
    }
}
```

## Sealed Class
- Used to restrict which other classes may directly extend your class
- New keywords, `sealed`, `non-sealed`, `permits`
- Sealed classes __must be declared in the same package__ as it's direct subclass
- Every listed subclass must extend sealed class
- every subclass must have one of these class modifier
  - final, sealed, non-sealed

_Example_
```java
    public sealed class Car permit Ford, Renault, Fiat{}
    //each of these classes mus extend car
    //Subclasses have to be made either final, sealed or non-sealed

    public final class Ford extends Car{}
    //can not be further extend 

    public non-sealed class Renault extends Car{}
    //can be extended my any class

    public sealed class Fiat extends Car permits Uno, Punto{}
    //can be extended, but only by classes Uno and Punto

```

_Example about class_design.interface_
`Class dog can not implement Mammal through Interface Eats` **(need check this case again)**
```java
    //Interface Sealed also be made sealed:
    public sealed interface Malmmal permists Cat, Dog, Eats{}

    //Cat, Dog and Eats could be classes and interface
    
    public non-sealed interface Eats extends Mammal{}
    //interface cannot be final!!

    //classes must be implement interface Mammal directly or indirectly
    
    public final class Cat implements Mammal{}
    
    public final class Dog implements Eats{}
    // implement Mammal through Eats
    
```

## Records
What are records?
- encapsulated classes, but without boilerplate code
- the encapsulation is secured
- constructor, getter, toString(), equals() and hashCode() are generated
- record cannot have explicit instance fields 
- record can have static fields and method
- record can have stance methods

__Example:__
```java

public class Example1 {

    private Long id;
    private String name;
    private Address address;
    
    //constructor 
    //getter setter
    //equals() hashcode() toString()
}
//instead of create a class, with too much line
//we just create a record

public class App {

    //create record with a line 
    public static record Example(Long id, String name, int age){};

    //write constructor
    public static record Example2(String firstName, String lastName, int id){

        //full
//        public Example2(String firstName, String lastName, int id) {
//            if (id < 10 || id > 1_000_000) throw new IllegalArgumentException();
//            this.firstName = firstName;
//            this.lastName = lastName;
//            this.id = id;
//        }

        //instead of full param in signature, you can write just {}, Compiler will generate and transmit input
        public Example2 {
            if (id < 10 || id > 1_000_000) throw new IllegalArgumentException();
        }
    }
    
    public static void main(String[] args) {
        var example = new Example(1L, "Alan", 25);
        System.out.println(example.toString());
        var example = new Example(1L, "Alan", 25);
        var example2 = new Example(2L, "Alan02", 25);
        var example3 = new Example(1L, "Alan", 25);

        System.out.println(example.toString());
        System.out.println(example.equals(example2));
        System.out.println(example.equals(example3));


        var example2_2 = new Example2("Author", "Lan", 9);
        
    }
}
```

## Nested class
what is nested class?
==> Nested class is a class defined within another class

Overview:
1. Inner Class
   - Non-static type, defined at the member level of a class
2. Static Nested Class
   - static type, defined at the member level of a class
3. Local class
   - A class defined within a method body
4. Anonymous Class
   - Local class which doesn't have a name

#
__1. Inner class__
- can have access modifier
- can `extend another class` and/or implement interfaces
- can be `marked  abstract or final`
- can access all members of the enclosing class (including private member)
- must create from instance of outer class `intanceOuterClass.new InnerClass()`

_Example_
in `package  class_design.beyond_clazz.nested_class.inner_class`

#
__2. Static Nested Class__
- `can't access instance` variable or methods declared in the outer class
- you don't need an instance of the wider class to access it
- can access directly `OuterClass.new InnerClass()`
- can be marked private or protected

_Example_
in `package`

#
__Local Class__
- nested class defined within the method
  - limited scoped
- don't have access modifier
- can be declared abstract or final
- can access final and effectively final local variables

_Example_
```java
   public class PrintArea{
    private int a = 10;
    public void calculateArea(){
        final int b = 15;
        
        //limited scope in class
        class Computer{
            public void multiply(){
                System.out.println(a*b);//class Computer can access a and b 
            }
        }
        var computer = new Computer(); // goes out of scope when we exist method
        computer.multiply();
    }
}

    public class App{
       public static void main(String[] args) {
          var printArea = new PrintArea();
          printArea.calculateArea();
       }
    }
```
```bash
150
```
#
__4. Anonymous Class__
- special type of local class which doesn't have a name
- must extend an existing class or implement an existing interface

```java
   public class Store{
    abstract class Sale{
       abstract int discount();
    }
   public int newPrice(int oldPrice){
      Sale sale = new Sale(){
        int discount(){return 2;}
   };
      return  oldPrice - sale.discount();
   }
}
```

It works with interface as well
```java
      public class Store {
    interface Sale {
        int discount();
    }

    public int newPrice(int oldPrice) {
        Sale sale = new Sale() {
            int discount() {
                return 2;
            }
        };
        return oldPrice - sale.discount();
    }
}
```

```java
    public class Dog{
    interface Eats{}
    Eats eating = new Eats() {
    };
    //eating is not an instance of the interface (not allowed)
    //but the instance of the anonymous class{}; implementing the interface
}
```



