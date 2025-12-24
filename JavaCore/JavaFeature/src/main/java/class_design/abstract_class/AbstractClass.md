## Abstract Class
- classes which can be extended, but cannot be initialized
- marked with abstract keyword
- don't have a body
- the implementation (body) is done in classes which extend an abstract class

<br/>

## Rules for Using Abstract Methods
1. Only instance methods can be marked abstract 
   - not variables, constructors, static methods, etc.
2. Abstract method can only be declared in an abstract class.
3. Non-abstract class which extends abstract class must implement all inherited
   methods.
4. All other rules with overriding methods apply.

<br/>

## Keep in mind...
- abstract classes can have constructors
  - but they can be called only using super() from the child class
- abstract class or method cannot be marked final (wouldn't make any sense)
- abstract method cannot be marked private (obviously)
- static method cannot be overriden
  - therefore, abstract static is not allowed