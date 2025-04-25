package streams.optional;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

public class App {
    public static void main(String[] args) {
        Optional<Double> amount = Optional.of(10.0);

        Optional<Double> wave1 = average(2, 3, 4, 5);
        Optional<Double> wave2 = average();

        Optional<Double> wave3 = average();

        //ifPresent(Consumer c)
        wave1.ifPresent(System.out::println);
        wave2.ifPresent(System.out::println);


        System.out.println("Wave 1 Is present: " + wave1.isPresent());
        System.out.println("Wave 2 Is present: " + wave2.isPresent());

        //use .get() to get Value

        if(wave1.isPresent()) System.out.println("Get Value " + wave1.get());

        try{
            //so Optional isEmpty or null, but you also get it
            //error NoSuchElementException
            wave2.get();
        }catch (NoSuchElementException e){
            System.err.println("Error: "+e.getMessage());
        }

        System.out.println(Double.NaN == 0.00);


    }

    private static Optional<Double> average(int... point) {
        if (point.length == 0) return Optional.empty();
        int sum = Arrays.stream(point).sum();
        return Optional.of((double) sum / point.length);
    }
}
