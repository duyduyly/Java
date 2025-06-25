## IO 
__(Working with File)__

### Keyword
| [InputStream](#inputstream) | [Reader And Writer for Text](#readers-and-writers-for-text) | [Abstract Class InputStream](#abstract-class-inputstream) | <br/>
| [Abstract Class Reader](#abstract-class-reader) | [Abstract Class Writer](#abstract-class-writer) |

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