## IO 
__(Working with File)__

### Keyword
| [InputStream](#inputstream) | [Reader And Writer for Text](#readers-and-writers-for-text) | [Abstract Class InputStream](#abstract-class-inputstream) | <br/>
| [Abstract Class Reader](#abstract-class-reader) | [Abstract Class Writer](#abstract-class-writer) | [Console Class](#console-class) | <br/>
| [Serialization](#serialization) |

#

### Using File Classes
- using special class File enables one to create File objects
  -  from which actual physical files on the hard disk can be created
- many of the classes mentioned in previous slides are intended to be wrapped
  - this enables low-level classes to get access to higher-level functionality
  - which results with efficiency (buffering)


### InputStream
- all classes in this lesson are in `java.io.*` package
- Java reads/writes either `characters` or `bytes`
- Classes with Stream in their name read/write binary: (usually used for images, executable files, etc.)
  - FileInputStream
  - FileOutputStream
  - ObjectInputStream
  - ObjectOutputStream

__Read And Write File (Byte)__
```java
    //Convert From Byte[] to String
    public static String read(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    //Get Byte[] from file
    public static byte[] copy(String filePath){
        try (FileInputStream fis = new FileInputStream(filePath)) {
            return fis.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException("Error copy file " + filePath, e);
        }
    }

    //create a new File From Byte[] Content
    public static void paste(String filePath, byte[] bytes){
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException("Error paste file " + filePath, e);
        }
    }
```
--------------
__Read/Write(Char)__
```java
    //read String File
    public static String read(String filePath){
        StringBuilder stringBuilder = new StringBuilder();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            int data;
            while ((data = fis.read()) != -1) {
                stringBuilder.append((char) data);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file " + filePath, e);
        }
        return stringBuilder.toString();
    }

    //Write String File
    public static void write(String filePath, String bodyFile){
      try (FileOutputStream fos = new FileOutputStream(filePath)) {
        byte[] bytes = bodyFile.getBytes();
        fos.write(bytes);
      } catch (IOException e) {
        throw new RuntimeException("Error writing file " + filePath, e);
      }
    }
```
Class here: [FileIOStream.java](io/FileIOStream.java)

#
###  Readers and Writers for text
- Readers and Writers are used to read/write text:
  - FileReader
  - BufferedReader
  - FileWriter
  - BufferedWriter
  - PrintWriter
  - these are used for text files (data which consist of characters or strings)

__Example:__
```java
    public static String read(String filePath) {
        StringBuilder sb = new StringBuilder();
        try (FileReader reader = new FileReader(filePath)) {
            int ch;
            while ((ch = reader.read()) != -1) sb.append((char) ch);  // print each character
        } catch (IOException e) {
            throw new RuntimeException("Error reading file " + filePath, e);
        }
        return sb.toString();
    }

    public static void write(String filePath, String content) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Error writing file " + filePath, e);
        }
    }

    public static String read(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + filePath, e);
        }
    }
    public static void write(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
              writer.write(content);
        } catch (IOException e) {
              throw new RuntimeException("Error writing file: " + filePath, e);
        }
    }
```
Class here: [FileReaderAndWriter.java](io/FileReaderAndWriter.java), [BufferReaderAndWriter.java](io/BufferReaderAndWriter.java)

#
### Abstract class: InputStream
- Low-level class: FileInputStream
  - reads one byte at a time
- High-level class (for efficiency): BufferedInputStream
- High-level class (other): ObjectInputStream
- Wrapping example:
  -  new ObjectInputStream(new FileInputStream("file.dat"));
- Example Here: [ObjectIOStream.java](io/ObjectIOStream.java)

#
### Abstract class: Reader
- Low-level class: FileReader
- High-level class (for efficiency): BufferedReader
- High-level class (other): InputStreamReader
  - a bridge between byte streams and character streams
- Wrapping examples:
  - new BufferedReader(new FileReader("in.txt"));
  - new BufferedReader(new InputStreamReader(System.in));
```java
    public static void toUpperCaseFromConsole(){
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equalsIgnoreCase("Exist")) {
                  break; // stop reading
                }
                System.out.println(line.toUpperCase());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error Writing file: " , e);
        }
}

    public static String read(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + filePath, e);
        }
    }
```

Class here: [BufferReaderAndWriter.java](io/BufferReaderAndWriter.java)

#
### Abstract class: Writer
- Low-level class: FileWriter
- High-level class (for efficiency): BufferedWriter
- High-level classes (other): OutputStreamWriter, PrintWriter
  - a bridge between byte streams and character streams
- Wrapping examples:
  - new BufferedWriter(new FileWriter("out.txt")); 
  - new BufferedWriter(new OutputStreamWriter(System.out));

```java
    public static void write(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Error writing file: " + filePath, e);
        }
    }
```
Class here: [BufferReaderAndWriter.java](io/BufferReaderAndWriter.java)

#
### Console Class
- class java.io.Console is designed for interacting with the user
- this class is a singleton
  - using factory method we create one and only instance of this class
- the constructors of Console class are private
- i.e. created using factory methods, not with new keyword

__Example:__
```java
import java.io.Console;
import java.util.Arrays;

public class MyConsoleExample {
    
    //create a file and cd into folder and run
    public static void main(String[] args) {
        Console console = System.console();

        if (console == null) {
            System.out.println("No console available");
        } else {
            char[] password = console.readPassword("Enter your password: ");
            char[] repeatPassword = "Alan123".toCharArray();

            if (Arrays.equals(password, repeatPassword)) {
                console.writer().println("-".repeat(30));
                console.format("Hello %s!%n", "Alan");
            } else {
                console.writer().println("-".repeat(30));
                console.writer().println("Wrong Password!");
                console.writer().flush();
            }
        }
    }
}
```

__If you run on intelliJ:__
```text
No console available
```

__Run Console:__

- javac <class name>.java //compile .java to .class
- java <Class name> //run main file
```bash
D:\>javaC MyConsoleExample.java
D:\>java MyConsoleExample
```
```text
D:\>java MyConsoleExample
Enter your password:

------------------------------
Hello Alan!

D:\>java MyConsoleExample
Enter your password:

------------------------------
Wrong Password!

```


# 
### Serialization
- Serialization in Java is process of convert from Object to Byte Array to:
  - Write to file (Ex: `.dat`, `ser`)
  - Send through the network
  - Store in memory (cache, session)
  - Transmit among chanel (client to server)
#
#### Serialization and De-serialization
- `serialization` is `the process of saving in-memory` Java `object` in the `physical file`
- de-serialization is the opposite: `reading` from file and `creating` Java object
- to make a class `serializable`:
  -  it must implement the `marker interface Serializable` (marker interface is an interface which has no methods)
  - when serializing the object,` only instance members` are serialized (`not static`)
#
#### serialVersionUID
- a special field in serializable classes which is serialized even though it's static:
```java
private static final long serialVersionUID = 1L;
```
- this field serves as a `unique identifier` for `each class` in (de)serialization process
- during `deserialization` JVM checks if the `serialVersionUID` of the loaded
  class is the same as the `serialVersionUID` of the serialized object
  - if they `match`, it means that the `two versions of the class are compatible`
  - if they `don't match`, the JVM `throws an InvalidClassException`
#
#### Transient Fields
- if you `don't want` a `field` to be `serialized`, you can mark it as `transient`, e.g.
```java
 private transient String myPassword;
```
- when being `deserialized`, `transient field` will `revert` to it's `default` Java `value`
  (null for String, 0 for int, false for boolean, etc.)
- if you have `instance variables` in your `serializable class`, `make sure` that `these
  objects` are also `marked` as `serializable`, e.g.
- if you want to `serialize class` Student which has an `instance variable` of type
  Address, you have to `make Address` class `serializable` as well
- __remember: only non-transient instance members will be serialized!__

#
__Student Class:__
```java
import lombok.Getter;

import java.io.Serializable;
import java.util.UUID;

@Getter
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    //don't want a field to be serialized
    private transient String id;
    private String name;
    private int age;

    public Student(String Name, int Age) {
        this.id = UUID.randomUUID().toString();
        this.name = Name;
        this.age = Age;
    }

    @Override
    public String toString() {
        return "Student [ID=" + id + ", Name=" + name + ", Age=" + age + "]";
    }
}
```

#
__Example 1:__
```java
public static void main(String[] args) {
  Student alan = new Student("Alan", 25);

  //covert from Java Object to Byte Array and id marked transient
  byte[] serialize = SerializationUtils.serialize(alan);
  System.out.println(SerializationUtils.deserialize(serialize));
}
```
```text
Student [ID=null, Name=Alan, Age=25]
```
#
__Example 2:__
```java

    public static void main(String[] args) {
        Student alan = new Student("Alan", 25);
        System.out.println("Initialize Student: " + alan);

        serialize(alan, "src/resource/file/SerializationExample.dat");
        Student deserialize = (Student) deserialize("src/resource/file/SerializationExample.dat");
        System.out.println("Deserialize Student File: "+deserialize.toString());
    }

    public static void serialize(Object ob, String fileName){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(ob);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file", e);
        }
    }

    public static Object deserialize(String fileName){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error reading to file", e);
        }
    }
```

```text
Initialize Student: Student [ID=5f6ef6d9-ccd7-4179-87b2-d2ba81606ab6, Name=Alan, Age=25]
Deserialize Student File: Student [ID=null, Name=Alan, Age=25]
```

Example Class: [SerializationExample.java](serialization/SerializationExample.java)


-------------------
<br/>

## Note:
### System.in
- System.in refers to the standard input stream of a Java program. It is opened when the program is started.
  - in is a variable of java.lang.System class.
  - `public static final InputStream in`
  - The "standard" input stream. This stream is already open and ready to supply input data. Typically this stream corresponds to keyboard input or another input source specified by the host environment or user.
- The `System.in` variable cannot be reassigned to any other stream directly.
  - Although in is a final variable and so, it cannot be reassigned directly. However, System class has a public static void setIn(InputStream in) method that can be used to change in to refer to any other InputStream.
- You can close it by calling `System.in.close();`
- The declared type of System.in is java.io.InputStream. It refers to an object of type java.io.BufferedInputStream.
- An InputStream is meant to read bytes. Whether these bytes are characters or not is irrelevant. A program could potentially interpret the bytes reads through this stream as characters.