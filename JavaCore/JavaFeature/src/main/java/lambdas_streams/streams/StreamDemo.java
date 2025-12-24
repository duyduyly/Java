package lambdas_streams.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        Stream<Double> generate = Stream.generate(Math::random);
        Stream<Integer> iterate = Stream.iterate(1, n -> n < 20, n -> n + 1);
        System.out.println(iterate.toList());

        generate.filter(n -> n > 0.5).limit(20).forEach(System.out::println);

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        //merge all name

        //normal way
        String allName = "";
        for (String name : names) allName += name;
        System.out.println(allName);

        //use reduce
        String useReduce = names.stream().reduce("", (s1, s2) -> s1 + s2);
        System.out.println(useReduce);

        //use reduce and conCat reference method
        String useReduceReferenceMethod = names.stream().reduce("", String::concat);
        System.out.println(useReduceReferenceMethod);

        Integer reduce1 = names.stream().reduce(0, (i, s) -> i + s.length(), Integer::sum);
        System.out.println(reduce1);

        //return sum length of each element in array
        String reduce = names.stream().reduce("", (s1, s2) -> s1+"|"+s2.toUpperCase());
        System.out.println(reduce);

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

        String[] myName = new String[]{"L", "U", "K", "E"};

        StringBuilder myNameBuilder = Arrays.stream(myName).collect(
                StringBuilder::new,
                StringBuilder::append,
                StringBuilder::append
        );

        System.out.println(myNameBuilder);


    }
}
