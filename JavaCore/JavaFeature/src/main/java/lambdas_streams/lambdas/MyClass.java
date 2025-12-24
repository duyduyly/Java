package lambdas_streams.lambdas;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class MyClass {

    public static void main(String[] args) {
        System.out.println("Consumer: ");
        Consumer<String> greet1 = s-> System.out.println("Hello,"+s+"!");
        Consumer<String> greet2 = s-> System.out.println("Hello,"+s+"!");
        Consumer<String> greetCombined = greet1.andThen(greet2);

        greetCombined.accept("Hello");

        //another way
        System.out.println();
        greet1.andThen(greet2).accept("Hello");


        System.out.println("Function: ");
        Function<Integer, Integer> square = n -> n*n;
        Function<Integer, Integer> triple = n -> 3*n;
        Function<Integer, Integer> f1 = square.andThen(triple);
        Function<Integer, Integer> f2 = square.compose(triple);


        System.out.println(f1.apply(5)); // 5*5=25,3*25=75
        System.out.println(f2.apply(5)); //(3*n)*(3*n)=(3*5)*(3*5)=225

        System.out.println("Predicate: ");
        Predicate<Integer> gt10 = n -> n > 10;
        Predicate<Integer> lt20 = n -> n < 20;
        Predicate<Integer> p1 = gt10.and(lt20);// gt10 && lt20
        Predicate<Integer> p2 = lt20.or(p1); // gt10 || lt20
        Predicate<Integer> p3 = lt20.negate(); // == !(n<20)

        System.out.println(p1.test(5));
        System.out.println(p2.test(5));
        System.out.println(p3.test(5));
    }
}
