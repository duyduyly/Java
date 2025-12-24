package exception;

import java.util.Optional;

public class ExceptionUtils {



    public <T> T findOrThrow(Optional<T> optional, RuntimeException ex) {
        return optional.orElseThrow(() -> ex);
    }
}
