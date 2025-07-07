## Conversion

- [Primitive Type Conversion](#1-primitive-type-conversion)
  - [Widening Conversion](#widening-conversion-automatic--implicit)
  - [Narrowing Conversion](#narrowing-conversion-manual--explicit)
- [Reference Type Conversion (Objects)](#2-reference-type-conversion-objects)
  - [Upcasting (Automatic)](#a-upcasting-automatic)
  - [DownCasting (Manual)](#b-downcasting-manual)
- [String Conversion](#3-string-conversion)
  - [Primitive → String](#a-primitive--string)
  - [String → Primitive](#b-string--primitive)
- [Wrapper Class Conversion](#4-wrapper-class-conversion)
- [Summary](#5summary)

### 1. Primitive Type Conversion

#### Widening Conversion (Automatic / Implicit)
- Safe, no data loss. `Smaller type` → `larger type`.

| From  | To                              | Example         |
|-------|---------------------------------|-----------------|
| byte  | short, int, long, float, double | `int i = b;`    |
| short | int, long, float, double        | `long l = s;`   |
| char  | int, long, float, double        | `double d = c;` |
| int   | long, float, double             | `float f = i;`  |
| long  | float, double                   | `double d = l;` |
| float | double                          | `double d = f;` |

#
#### Narrowing Conversion (Manual / Explicit)
- May cause data loss. `Larger type` → `smaller type`.

| From   | To                            | Example              |
|--------|-------------------------------|----------------------|
| double | float, long, int, short, byte | `int i = (int) d;`   |
| int    | short, byte, char             | `byte b = (byte) i;` |

------------------

### 2. Reference Type Conversion (Objects)
#### a. Upcasting (Automatic)
`Subclass` → `Superclass` (safe)
```java
Dog dog = new Dog();
Animal a = dog;  // Upcasting
```
#
#### b. DownCasting (Manual)
`Superclass` → `Subclass` (needs cast)
```java
Animal a = new Dog();
Dog d = (Dog) a;  // DownCasting
```
Note: 
__Unsafe if actual object is not the subclass (throws `ClassCastException`)__

------------------

### 3. String Conversion
#### a) Primitive → String
```java
int i = 123;
String s = String.valueOf(i);  // or "" + i;
```
#
#### b) String → Primitive
Use `parse` methods:
```java
String s = "123";
int i = Integer.parseInt(s);
double d = Double.parseDouble("3.14");
boolean flag = Boolean.parseBoolean("true");
```

Note: __Throws `NumberFormatException` if string is not valid__

------------------

### 4. Wrapper Class Conversion
- Java auto-converts between primitives and wrapper classes:
`Autoboxing` / `Unboxing`

```java
Integer iObj = 10;       // Autoboxing: int → Integer
int i = iObj;            // Unboxing: Integer → int
```

-------------------

### 5.Summary

**Summary Casting:**

| Type                 | Auto | Needs Cast | Notes                     |
|----------------------|------|------------|---------------------------|
| Widening             | ✅    | ❌          | Safe                      |
| Narrowing            | ❌    | ✅          | May lose data             |
| Upcasting            | ✅    | ❌          | Safe, object → parent     |
| Downcasting          | ❌    | ✅          | Risky, use `instanceof`   |
| String <-> Primitive | ❌    | ✅          | Use `parse` and `valueOf` |


**Summary Exception:**

| Cast                           | Exception                                                                     |
|--------------------------------|-------------------------------------------------------------------------------|
| `Larger type` → `smaller type` | May cause data loss.  Example: `int a = 130; byte b = (int) a; result: -126;` |
| `Superclass` → `Subclass`      | May throws `ClassCastException`                                               |
| `String` → `Primitive`         | May  Throws `NumberFormatException`                                           |