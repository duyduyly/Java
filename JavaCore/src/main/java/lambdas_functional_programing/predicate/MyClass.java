package lambdas_functional_programing.predicate;

import java.time.LocalDate;
import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MyClass {

    public static void main(String[] args) {
        Predicate<Integer> isEven = i -> i % 2 == 0;
        Predicate<Integer> isOdd = i -> i % 2 != 0;
        System.out.println(isEven.test(2));
        System.out.println(isOdd.test(2));

        myProbe(10, n -> n % 2 == 0);
        myProbe(10, n -> n > 2);
        myProbe(10, isOdd);

        Function<LocalDate, String> getDay = d -> d.toString();

        LocalDate today = LocalDate.now();
        String apply = getDay.apply(today);
        System.out.println(apply);
    }

    static void myProbe(int n, Predicate<Integer> predicate){
        if (predicate.test(n)){
            System.out.println("the test has passed.");
        }else {
            System.out.println("the test has failed.");
        }
    }
}
