### Records

### Keyword
| [Use Record](#use-record) | [Getter and ToString](#getter-and-tostring) | [Equals](#equals) | <br/>
| [Override Constructor](#override-constructor) | [Method](#methods-in-records) |
 
#### What are records?
* `Encapsulated classes`, but without boilerplate code
* The encapsulation is `secured`
* `Constructor`, `getter`, `toString()`, `equals()` and `hashCode()` are generated
* Records cannot have `explicit instance fields`
* Records can have static `fields` and `methods`
* Records can have `instance methods`

__Old way of creating encapsulated class:__
```java
public final class Student {
    // 1. declare private final fields 
    private final String firstName;
    private final String lastName;
    private final int id;
    
    //constructors
    // getter/setter
    
    //toString(), Equals(), hashCode()...
}
```

#
#### Use Record:
```java
public record Student (String firstName, String lastName, int id){
    //we can override auto-generated constructor 
    //this is called "canonical constructor" 

    //there is simpler way => "compact constructor"
    public Student {
        if (id < 10 || id > 1_000_000) throw new IllegalArgumentException();
    }
}

public static void main(String[] args) {
    var theStudent = new Student("Alan", "Ly", 1);
    System.out.println(theStudent.firstName());
    System.out.println(theStudent.lastName());
    System.out.println(theStudent.id());
    System.out.println("ToString: "+ theStudent.toString());

    var anotherStudent = new Student("Alan", "Ly", 1);
    System.out.println("== In records : " + (theStudent == anotherStudent));
    System.out.println("Equal In records: " + (theStudent.equals(anotherStudent)));
}
```

#
#### Getter and ToString():
-  the getter is not like getFirstName(), but firstName()
- toString() is nicely implemented
```java
    public void setStudentRecords(){
    System.out.println(theStudent.firstName());
    System.out.println(theStudent.lastName());
    System.out.println(theStudent.id());
    System.out.println("ToString: "+theStudent);
}
```
```text
Alan
Ly
1
ToString: Student[firstName=Alan, lastName=Ly, id=1]
```

#
#### Equals()
```java
public void equalsInRecord(){
    var theStudent = new Student("Alan", "Ly", 1);
    var anotherStudent = new Student("Alan", "Ly", 1);
    System.out.println("== In Records: "+ (theStudent == anotherStudent));
    System.out.println("== In Records: "+ (theStudent.equals(anotherStudent)));
    //Can Override Equals method like Class
}
```
```text
== In Records: false
Equals In Records: true
```

#
#### Override Constructor:
- We can override auto-generated constructor
- This is called "canonical constructor"

```java
public record Student (String firstName, String lastName, int id) {
    
// Overriding Constructor
//    public Student(String firstName, String lastName, int id) {
//        if (id < 10 || id > 1_000_000) throw new IllegalArgumentException();
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.id = id;
//    }

    //there is simpler way => "compact constructor"
    //notice the syntax: no ()
    // instance fields don't need to be explicitly initialized
    //compact constructor could contain any business logic, e.g. 
    public Student {
        if (id < 10 || id > 1_000_000) throw new IllegalArgumentException();
        firstName = fistName.substring(0,1).toUpperCase
                + firstName.substring(1).toLowerCase;
        lastName = lastName.substring(0,1).toUpperCase
                + lastName.substring(1).toLowerCase;
    }
}
```

#
#### Methods in Records
```java
public static record Student2 (String firstName, String lastName, int old_package, int id) {
    public boolean isMature() {
        return this.old_package > 18;
    }
}
```
