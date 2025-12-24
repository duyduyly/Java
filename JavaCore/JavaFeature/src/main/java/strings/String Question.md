# String Question
### Question 1.
What will the following code print? <br/>
System.out.println("12345".charAt(6));

A. 5  <br/>
B. null  <br/>
C. -1  <br/>
D. It will throw an `ArrayIndexOutOfBoundsException`.  <br/>
E. It will throw a `StringOutOfBoundsException`.  <br/>
F. It will throw an `IndexOutOfBoundsException`  <br/>


-------------------
<br/> <br/> <br/> <br/> <br/> <br/> <br/> <br/> <br/> <br/> <br/> <br/>

### Answer 1: ✅ F 
- F : Right Because `IndexOutOfBoundsException` is parents class of `StringIndexOutOfBoundsException` and `ArrayIndexOutOfBoundsException`and although in practice, the `charAt()` will throw `StringIndexOutOfBoundsException` but it is not a valid option because the implementation is free to throw some other exception as long as it is an `IndexOutOfBoundsException`.
- Because String length == (6) and index just 5, so this System will throw `StringIndexOutOfBoundsException`
- D : wrong because it's throw when out of index of Array
- E : `StringOutOfBoundsException` wrong because it incorrect name (right Exception is `StringIndexOutOfBoundsException`)
