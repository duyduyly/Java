### Exception Type

We have two kind of exception 
- Checked Exception (Exception) can catch
- Unchecked Exception (Error) can't catch

![App Screenshot](https://data-flair.training/blogs/wp-content/uploads/sites/2/2018/04/hierarchy-of-java-exceptions.webp)

#### Checked Exception 
- Must be declared or handled by the application code where it is thrown
- All checked exception is declared when defining a method
  - using keyword throws
- `Exception is handled using try-catch block`

__Example:__

```java
void readFirstByteFromFile(File fileName){
    FileInputStream file = new FileInputStream(fileName);
    byte x = (byte) file.read();
    System.out.println(x);
}
//this does not compile because there is a possibility that checked exception
//IOException to be thrown by this method
```
__Fix 1__
```java
//declare the exception after signature of method
void readFirstByteFromFile(File fileName)throws IOException {}
```
__Fix 2__
```java
//use try-catch block to catch the exception
void readFirstByteFromFile(File fileName){
        FileInputStream file = null;
        try {
            file = new FileInputStream(fileName);
            byte x = (byte) file.read();
            System.out.println(x);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
```
#
#### Unchecked Exceptions may or may not be handled
```java
private static void printFourthElement(int[] a){
  System.out.println(a[3]);
}
public static void main(String[] args) {
    int[] a = {-7,11,3};
    printFourthElement(a);
}

```
```text
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3
	at App.printFourthElement(App.java:18)
	at App.main(App.java:14)
```

