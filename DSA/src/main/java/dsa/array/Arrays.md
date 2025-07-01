# Arrays
- Array indexes start at 0 in most languages.
- Accessing `arr[i]` when it is out of bounds causes an error or crash.
- Fixed size 
- Dynamic array (like Python list, Java ArrayList): Can grow and shrink, but still based on array underneath.
- Arrays use contiguous memory.
- This allows fast access but makes insertion/deletion expensive.
- If there's no enough continuous memory, array allocation may fail.


When you insert or delete at any index other than the end, you have to `shift elements`.
```java
// Insert at index 2
    for (int i = n - 1; i >= 2; i--) {
    arr[i + 1] = arr[i];
    }
    arr[2] = newValue;

```