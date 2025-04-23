package collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MyClass {
    public static void main(String[] args) {
        TreeSet<String> names = new TreeSet<>();
        System.out.println(names.add("John"));
        System.out.println(names.add("George"));
        System.out.println(names.add("John"));
        System.out.println(names.add("Ben"));

        System.out.println(names);

        MyClass myClass = new MyClass();


        System.out.println(myClass.testMultipleReturn().getFirst());
        System.out.println(myClass.testMultipleReturn().getSecond());
        System.out.println(myClass.testMultipleReturn().getThird());

    }

    public ThreeReturn<?,?,?> testMultipleReturn(){
        ThreeReturn<Integer, Integer, String> multipleReturn = new ThreeReturn<>();

        multipleReturn.setFirst(1);
        multipleReturn.setSecond(2);
        multipleReturn.setThird("ádasdasdasdas");




        return multipleReturn;
    }

    public boolean check(String strDecimal, Predicate<String> de) {
        de = s -> s.isEmpty();
        de.and(s -> s.isBlank());


        return true;
    }

    private Predicate<String> checkText = s -> !s.isEmpty()
            && !s.isBlank();

    public Double convertStringToDecimal(String strDecimal) {
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setParseBigDecimal(true);
        try {
            return Double.valueOf(strDecimal);
        } catch (Exception e) {
            System.out.println("Convert Error");
            return 0D;
        }
    }

    public boolean checkDecimal(String strDecimal) {
        return checkText.test(strDecimal) && convertStringToDecimal(strDecimal) > 0;
    }


    public static void demo(String str, Consumer<String> cs) {
        cs.accept("Hello World");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class ThreeReturn <T,T2,T3>{
        T first;
        T2 second;
        T3 third;
    }
}
