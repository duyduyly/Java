# Streams And Lambdas

# Question
### 🤔 1. What can be inserted in the following code so that will print [21, 32, 43] ?

```text
List<Integer> ls = Arrays.asList(11, 22, 33);
//INSERT CODE HERE
ls.replaceAll(func);
System.out.print(ls)
```
#
A. `Function<Integer> func = x -> x+10;` <br/>
#
B. `UnaryOperator<Integer> func = x->x+10;` <br/>
#
C. `UnaryOperator<Integer, Integer> func = x ->x+10;` <br/>
#
D. `Consumer<Integer> func = x ->x+10;` <br/>
#
E. `Operator<Integer> func = x->x+10; `
#

--> [Answer](#1--b)

-----------------

#
### 🤔 2. Given:
```text
Stream<String> s1 = Stream.of("1", "2", "3", "4");
Stream<String> s2 = Stream.of("1", "2", "3");
```

__Identify correct statement.__


#
A. `String s = Stream.concat(s1,s2).parallel().distinct().sorted().findFirst().get(); will always assign "1" to s` <br/><br/>
#
B. `Stream.concat(s1, s2).parallel().distinct().sorted().forEach(System.out::print); will always print 12345` <br/><br/>
#
C. `Stream.concat(s1, s2).parallel().distinct().sorted().forEachOrdered(System.out::print); may print the numbers 1 2 3 4 but in unpredictable order.`   <br/><br/>
#
D. `Stream.concat(s1, s2).distinct().sorted().forEach(System.out::print); will always print 1234`  <br/><br/>
#
E. `String s = Stream.concat(s1, s2).parallel().distinct().sorted().findAny().get(); may assign any element to s`
#
--> [Answer](#2-a-d-e)

--------------------

<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>

# Answer
### 1. ✅ B
- A -> it's will not compile, for it's wrong syntax (Function<T,R>)
- C -> not compile, for wrong syntax (UnaryOperator<T>)
- D -> Consumer return void
- E -> Operator is not a valid functional interface

[Question](#-1-what-can-be-inserted-in-the-following-code-so-that-will-print-21-32-43-)

#
### 2. ✅A, D, E

[Question](#-2-given)



