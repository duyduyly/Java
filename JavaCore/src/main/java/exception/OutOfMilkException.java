package exception;

//Custom exception

public class OutOfMilkException extends Exception{
    String message;
    public OutOfMilkException(String message) {
        super(message);
    }
}
