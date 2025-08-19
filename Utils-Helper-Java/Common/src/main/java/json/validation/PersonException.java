package json.validation;

public class PersonException extends RuntimeException{
    private String message;

    public PersonException(String message){
        super(message);
        this.message = message;
    }
}
