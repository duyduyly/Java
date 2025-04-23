package java8;

import model.Student;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaExpression {

    /***
     * Predicate<String>  return boolean
     * Consumer<String> return void
     * Function<DataTypeValue, DataType> return DataType
     * Supplier<Double> randomValue return but Not need value
     */

    @FunctionalInterface
    interface Calculator {
        int add(int a, int b);
    }


    public static void main(String[] args) {
        Calculator add = Integer::sum;
        Calculator subtract = (a, b) -> a - b;
        Calculator multiply = (a, b) -> a * b;
        Calculator divide = (a, b) -> a / b;

        System.out.println("Result Add: " + add.add(5, 3));
        System.out.println("Result Subtract: " + subtract.add(5, 3));
        System.out.println("Result Multiply: " + multiply.add(5, 3));
        System.out.println("Result Divide: " + divide.add(5, 3));

        Student student = new Student();
        student.setName("Alan");
        student.setAge(20);
        student.setLastName("Ly");

        Function<Student, String> fullName = s -> s.getName().concat(" ").concat(s.getLastName());
        Predicate<Student> checkOld = s -> s.getAge() > 20;
        System.out.println(fullName.apply(student));
        System.out.println(checkOld.test(student));


        BigDecimal value1 = new BigDecimal(20);
        BigDecimal value2 = null;
        System.out.println(checkAndSetCurrency("USD", value2));
        System.out.println(checkAndSetCurrency("USD", value1));
        System.out.println(checkAndSetCurrency("", value1));
    }

    public static void Predicate(){
        Predicate<Integer> isEven = num -> num % 2 == 0;
        System.out.println(isEven.test(4));  // Output: true
        System.out.println(isEven.test(3));  // Output: false
    }

    public static void Consumer(){
        Consumer<String> printMessage = message -> System.out.println(message);
        printMessage.accept("Hello, World!"); // out put void
    }

    public static void Function(){
        Function<String, Integer> stringLength = str -> str.length();
        System.out.println(stringLength.apply("Hello"));  // Output: 5
    }

    public static void Supplier(){
        Supplier<Double> randomValue = () -> Math.random();
        System.out.println(randomValue.get());  // Output: (a random number between 0.0 and 1.0) , no need param
    }

    public static String checkAndSetCurrency(String current, BigDecimal decimal) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        Function<BigDecimal, String> checkBigDecimal = bigDecimal ->
                Objects.isNull(bigDecimal) ? "0.00" : df.format(bigDecimal);

        if (!current.isEmpty()) {
            checkBigDecimal = checkBigDecimal.andThen(s -> s.concat(" ").concat(current));
        }

        return checkBigDecimal.apply(decimal);
    }

    private static void checkLength(String value, String comapreValue) {
        Predicate<String> isLongerThanFive = s -> s.contains(comapreValue);

        boolean test = isLongerThanFive.test(value);
        if (test) {
            System.out.println("Exist : " + comapreValue);
        } else {
            System.err.println("Does Not Exist: " + comapreValue);
        }
    }

    private static void printUpperCase(String value) {
        Consumer<String> printUpperCase = s -> {
            System.out.println(s.toUpperCase());
        };

        printUpperCase.accept("hello");
    }

    private static String length(String value) {
        Function<String, String> lengthFunction = s -> s.substring(0, s.length() - 3);

        System.out.println(lengthFunction.apply(value));
        return lengthFunction.apply(value);
    }
}
