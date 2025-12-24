# Math APIs
## Math API Methods
| [Min and Max](#min-and-max) | [Round](#round) | [Ceil And Floor](#ceil-and-floor) | <br/>
| [Exponents](#exponents-pow) | [Random](#random) |  | <br/>



### min() and max()
- you can compare all types of numbers, but be aware of autocasting
```java
long a = 5; 
int b = 3; 
int c = Math.max(a, b);

int c = Math.max(a, b); //OK => 5

int d = (int) Math.max(a, b); //NOK, DOES NOT COMPILE
```

<br/>

### round()
-  takes decimal number and returns integral number
- if parameter is float, return type will be int
- if parameter is double, return type will be long
```java
double d = 2.56; 
long a = Math.round(d); 
 // => 3

int b = Math.round(d);//OK 
int c = (int) Math.round(d); //NOK, DOES NOT COMPILE
 // => 3
```

<br/>

### ceil() and floor()
- take any number and always returns double
```java
double r = Math.ceil(2.45); 
 // => 3.0 
double p = Math.floor(2.45); 
 // => 2.0 
double q = Math.floor(2.99); 
 // => 2.0
```

### exponents: pow()
-  takes any number, returns double
```java
double p = Math.pow(2, 5); 
 // => 32.0 
double q = Math.pow(25, 0.5); 
 // => 5.0 
```

### random()
```java
double ran = Math.random();
// => random number between 0 and 1.0 (not included)
```

#### Random Number Limit
```java
private final Random rand = new Random();

public int randomIntNum(){
    int min = 50;
    int max = 100;
    return  rand.nextInt(max - min + 1) + min;
}
```
