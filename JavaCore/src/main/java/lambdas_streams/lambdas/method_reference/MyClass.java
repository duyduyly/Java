package lambdas_streams.lambdas.method_reference;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@FunctionalInterface
interface Calculator {
    public double path(double t);
}

//you can see, the interface and class is the same parameter, so you can use reference
class Gravity {
    public static double freeFall(double t) {
        final double g = 9.81;
        return 0.5 * g * t * t;
    }
}

public class MyClass {
    public static void main(String[] args) {
        Supplier<LocalDateTime> dtImpl = () -> LocalDateTime.now();
        System.out.println(dtImpl.get());


        //Consumer, BiConsumer
        Consumer<String> logImpl = str -> {
            if (!str.isEmpty()) {
                System.out.println(str);
            }
        };
        logImpl.accept("Hello World");

        BiConsumer<String, Integer> p = (name, age) -> System.out.println(name + " is " + age + " years old");
        p.accept("Peter", 10);


        //Predicate, BiPredicate
        Predicate<String> checkString = (s) -> {
            if (s.isEmpty()) return false;
            if (s.length() > 200) return false;
            return !s.contains("s");
        };
        System.out.println(checkString.test("Peter"));
        System.out.println(checkString.test(""));

        BiPredicate<LocalDateTime, LocalDateTime> checkLocalDate = (d1, d2) -> {
            if (Objects.isNull(d2)) return false;
            return !d1.toLocalDate().isBefore(d2.toLocalDate());
        };
        LocalDateTime lcdTime1 = LocalDateTime.now();
        LocalDateTime lcdTime2 = lcdTime1.plusDays(1);
        System.out.println(checkLocalDate.test(lcdTime1, lcdTime2));
        lcdTime2 = null;
        System.out.println(checkLocalDate.test(lcdTime1, lcdTime2));

        //Function and BiFunction
        Function<String, BigDecimal> checkAndConvertBigDecimal = (s) -> {
            try {
                if (Objects.isNull(s) || s.isEmpty() || s.isBlank()) return BigDecimal.ZERO;
                return new BigDecimal(s);
            } catch (NullPointerException e) {
                System.out.println("Number Parse Is False: " + e.getMessage());
                return BigDecimal.ZERO;
            }
        };

        System.out.println(checkAndConvertBigDecimal.apply(""));
        System.out.println(checkAndConvertBigDecimal.apply("20"));
        System.out.println(checkAndConvertBigDecimal.apply("Peter"));//error

        BiFunction<String, Integer, String> con = (s, i) -> s + i;
        var myCon = con.apply("Join", 25);
        System.out.println(myCon);

        //UnaryOperation, BiNaryOperation
        UnaryOperator<Integer> negative = n -> -n;
        System.out.println(negative.apply(5));

        UnaryOperator<String> shout = String::toUpperCase;
        System.out.println(shout.apply("Join"));

        BinaryOperator<Double> add = (a,b) -> a + b;
        System.out.println(add.apply(3.5, 1.5));

        BinaryOperator<String> con2 = String::concat;
        System.out.println(con2.apply("Join", "Wayne"));
    }
}
