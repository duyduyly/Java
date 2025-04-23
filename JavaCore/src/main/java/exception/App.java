package exception;

import java.util.Optional;

public class App {
    public static ExceptionUtils eu = new ExceptionUtils();

    public static void main(String[] args) {
        Optional<String> ab = Optional.empty();
        String demoThrowException = eu.findOrThrow(ab, new RuntimeException("Demo Throw Exception"));
        System.out.println(demoThrowException);
    }
}
