package exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Optional;

public class App {
    public static ExceptionUtils eu = new ExceptionUtils();

    public static void main(String[] args) {
//        Optional<String> ab = Optional.empty();
//        String demoThrowException = eu.findOrThrow(ab, new RuntimeException("Demo Throw Exception"));
//        System.out.println(demoThrowException);

        // Usage:
        runWithCatch("sdsdsd", true, () -> {
            System.out.println("try body block here~!");
            System.out.println(2 / 0);
        });
    }

    public static void runWithCatch(String message, boolean canThrow, CheckedRunnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            if (canThrow) {
                throw new RuntimeException(message, e);
            }
            // handle once here
            System.err.println(e.getMessage());
        }
    }
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();

    public static ErrorResponse ErrorResponse(String message) {
        return ErrorResponse.builder().status(500).timestamp(LocalDateTime.now()).message(message).build();
    }

    public static String ErrorResponseJson(String message) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(ErrorResponse(message));
    }
}

@FunctionalInterface
interface CheckedRunnable {
    void run() throws Exception;
}





