package streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        Stream<Double> generate = Stream.generate(Math::random);
        Stream<Integer> iterate = Stream.iterate(1, n -> n < 20, n -> n + 1);
        System.out.println(iterate.toList());

        generate.filter(n -> n > 0.5).limit(20).forEach(System.out::println);

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        names.stream()                               // Source
                .filter(name -> name.startsWith("A"))  // Intermediate
                .map(String::toUpperCase)              // Intermediate
                .forEach(System.out::println);         // Terminal

        // if Terminal stream closed, we need open a new stream for this source

        Stream<Double> randomNumber = Stream.generate(Math::random); //source
        randomNumber.findFirst().ifPresent(System.out::println);//stream closed
        //randomNumber.findFirst().get(); // this statement will be error so stream closed


        Optional<String> op = Optional.of("shdsdsd");
        op.ifPresent(t -> System.out.println(t + "sddád"));


    }
}
