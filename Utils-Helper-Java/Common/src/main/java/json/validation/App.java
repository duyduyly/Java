package json.validation;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class App {

    private static final String PERSON_JSON_PATH = "src/main/resources/json/example/person.json";
    private static final String PERSON_INVALID_PATH = "src/main/resources/json/example/person_invalid.json";

    public static void main(String[] args) {
        readValidJson();
        readInValidJson();
    }

    public static void readValidJson() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PERSON_JSON_PATH))) {
            String strJson = reader.lines().collect(Collectors.joining());
            ObjectMapper objectMapper = new ObjectMapper();
            Person person = objectMapper.readValue(strJson, Person.class);

            System.out.println("Person: "+person);
            validate(person);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch (PersonException pe){
            System.err.println(pe.getMessage());
        }
    }

    public static void readInValidJson() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PERSON_INVALID_PATH))) {
            String strJson = reader.lines().collect(Collectors.joining());
            ObjectMapper objectMapper = new ObjectMapper();
            Person person = objectMapper.readValue(strJson, Person.class);

            System.out.println("Person: "+person);
            validate(person);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (PersonException pe){
            System.err.println(pe.getMessage());
        }
    }

    public static boolean validate(Person person) {
        // Run validation
        Validator validator = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory()
                .getValidator();

        Set<ConstraintViolation<Person>> violations = validator.validate(person);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<Person> violation : violations) {
                System.err.println(violation.getPropertyPath() + " " + violation.getMessage());
            }
        } else {
            System.out.println("Valid!");
            return true;
        }

        throw new PersonException("Person Json is Invalid!");
    }
}
