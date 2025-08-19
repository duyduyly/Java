# Json

- [**Gson**](#gson)
  - [*Gson Dependency*](#gson-dependency)
  - [*Gson Example*](#gson-example)
- [**Object Mapper**](#objectmapper)
  - [*Jackson Dependency*](#jackson-dependency)
  - [*Object Mapper Example*](#object-mapper-example)
- [**jolt.JsonUtils**](#joltjsonutils)
  - [*Jolt Dependency*](#jolt-dependency)
  - [*sonUtils + Chainr to transform JSON using a spec*](#sonutils--chainr-to-transform-json-using-a-spec)
  - [*Why this is powerful*](#-why-this-is-powerful-)
- [**Validation Json With Javax Validation**](#validation-json-with-javax-validation)
  - [*Validation Dependency*](#validation-dependency)
  - [*Validation Method*](#validate-method)
  - [*Javax Validation Annotation*](#javax-validation-annotation)
    - [*1.Null / Not Null*](#1null--not-null)
    - [*2. Boolean / Truth*](#2-boolean--truth)
    - [*3. Size / Length*](#3-size--length)
    - [*4. Numeric Constraints*](#4-numeric-constraints)
    - [*5. String / Character*](#5-string--character)
    - [*6. Past / Future Dates*](#6-past--future-dates)
    - [*7. Custom Message Example*](#7-custom-message-example)
    - [*8. More*](#8-more)
  - [**Custom Validation Annotation**](#custom-validation-annotation)

## Gson
- Gson is a Java library developed by Google that’s used to convert Java objects to JSON (serialization) and JSON to Java objects (deserialization).
  - It’s lightweight
  - Easy to use
  - And supports complex object structures, generic types, and custom serialization/deserialization.

### Gson Dependency
```pom
<!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>>2.10.1</version>
</dependency>
```

#
### Gson Example
**Example:** ([Test.java](gson/Test.java))
```java
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public record StudentRecord(Long id, String name, int age) {
    };

    public static void main(String[] args) {
        List<StudentRecord> studentRecords = new ArrayList<>();
        studentRecords.add(new StudentRecord(1L, "Name", 25));
        studentRecords.add(new StudentRecord(2L, "Name2", 21));
        studentRecords.add(new StudentRecord(3L, "Name3", 33));

        System.out.println("Serialize Object Json: ");
        System.out.println(serializeObjectJson(studentRecords));
        System.out.println();
        System.out.println(serializeObjectJson(studentRecords.get(0)));

        System.out.println();
        System.out.println("Deserialize Json To Object: ");
        System.out.println(deserializeJsonToObject(jsonString()));

        System.out.println();
        System.out.println("Handle Collection And Generic Type: ");
        System.out.println(handleCollectionsAndGenericType(jsonList()));
    }

    public static String jsonString() {
        return """
                {"id":1,"name":"Name","age":25}
                """;
    }

    public static String jsonList() {
        return """
                [{"id":1,"name":"Name","age":25},{"id":2,"name":"Name2","age":21},{"id":3,"name":"Name3","age":33}]
                """;
    }

    public static String serializeObjectJson(Object myObject) {
        Gson gson = new Gson();
        return gson.toJson(myObject);
    }

    public static Object deserializeJsonToObject(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Object.class);
    }

    public static List<StudentRecord> handleCollectionsAndGenericType(String listJson) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<StudentRecord>>() {
        }.getType();
        return gson.fromJson(listJson, listType);
    }
}
```
```java
Serialize Object Json: 
[{"id":1,"name":"Name","age":25},{"id":2,"name":"Name2","age":21},{"id":3,"name":"Name3","age":33}]
{"id":1,"name":"Name","age":25}

Deserialize Json To Object: 
{id=1.0, name=Name, age=25.0}

Handle Collection And Generic Type: 
[StudentRecord[id=1, name=Name, age=25], StudentRecord[id=2, name=Name2, age=21], StudentRecord[id=3, name=Name3, age=33]]
```

## ObjectMapper
- Jackson is a high-performance Java library for processing JSON, developed by FasterXML.
  It’s more feature-rich than Gson and is widely used in enterprise applications.
- It supports three main processing models:
  - `Data Binding` → Map JSON to Java objects (and vice versa).
  - `Tree Model` → Work with JSON as a tree (JsonNode), useful for partial parsing.
  - `Streaming API` → Low-level read/write of JSON for maximum performance.

### Jackson Dependency
```pom
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.17.0</version>
</dependency>
```

### Object Mapper Example
**Example:** ([Test.java](jackson/Test.java))
```java
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import json.StudentRecord;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

public class Test {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String jsonString() {
        return """
                {"id":1,"name":"Name","age":25}
                """;
    }

    public static String jsonList() {
        return """
                [{"id":1,"name":"Name","age":25},{"id":2,"name":"Name2","age":21},{"id":3,"name":"Name3","age":33}]
                """;
    }

    public static void main(String[] args) {
        List<StudentRecord> studentRecords = StudentRecord.generate();
        System.out.println(studentRecords.getFirst());
        try {
            System.out.println("Serialize: ");
            System.out.println(serialize(studentRecords.getFirst()));
            System.out.println(serialize(studentRecords));

            System.out.println();
            System.out.println("Deserialize: ");
            System.out.println(deserialize(jsonString()));
            System.out.println("Deserialize List: ");
            System.out.println(deserializeJsonList(jsonList()));

            System.out.println();
            System.out.println("Work with JSON as a Tree (JsonNode)");
            workWithJsonAsATree();

            System.out.println();
            System.out.println(" Streaming API (JsonParser & JsonGenerator)");
            writeJsonWithJsonGenerator();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    public static String serialize(StudentRecord studentRecord) throws JsonProcessingException {

        return mapper.writeValueAsString(studentRecord);
    }

    public static String serialize(List<StudentRecord> studentRecords) throws JsonProcessingException {
        return mapper.writeValueAsString(studentRecords);
    }

    public static StudentRecord deserialize(String jsonString) throws JsonProcessingException {
        return mapper.readValue(jsonString, StudentRecord.class);
    }

    public static List<StudentRecord> deserializeJsonList(String jsonList) throws JsonProcessingException {

        // Deserialize JSON array into List<Person>
        return mapper.readValue(jsonList, new TypeReference<List<StudentRecord>>() {
        });
    }

    //Work with JSON as a Tree (JsonNode)
    public static void workWithJsonAsATree() throws JsonProcessingException {
        String json = """
                {
                    "name": "Alice",
                    "age": 30,
                    "address": {
                        "city": "Hanoi",
                        "zip": "10000"
                    }
                }
                """;


        // Parse JSON into a tree
        JsonNode root = mapper.readTree(json);

        // Access values
        String name = root.get("name").asText();
        int age = root.get("age").asInt();
        String city = root.get("address").get("city").asText();

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("City: " + city);
    }

    //Streaming API (JsonParser & JsonGenerator)
    public static void writeJsonWithJsonGenerator() throws IOException {
        JsonFactory factory = new JsonFactory();
        StringWriter writer = new StringWriter();

        JsonGenerator generator = factory.createGenerator(writer);

        generator.writeStartObject(); // {

        generator.writeStringField("name", "Alice");
        generator.writeNumberField("age", 30);

        // Start "address" object
        generator.writeFieldName("address");
        generator.writeStartObject();
        generator.writeStringField("city", "Hanoi");
        generator.writeStringField("zip", "10000");
        generator.writeEndObject(); // end address

        generator.writeEndObject(); // end main object
        generator.close();

        System.out.println(writer.toString());
    }

}
```
## jolt.JsonUtils
- `jolt.JsonUtils` is a utility class from the Jolt library — a Java library used mainly for JSON transformation.
- Jolt is best known for letting you transform JSON structures using a declarative "spec" (a JSON file describing how to map/reshape data)
- The `JsonUtils` class provides basic JSON handling helpers so you don’t have to set up your own parser.

### Jolt Dependency
```pom
<dependency>
    <groupId>com.bazaarvoice.jolt</groupId>
    <artifactId>jolt-core</artifactId>
    <version>0.1.6</version>
</dependency>
<dependency>
    <groupId>com.bazaarvoice.jolt</groupId>
    <artifactId>json-utils</artifactId>
    <version>0.1.6</version>
</dependency>
```

**Main supports of `jolt.JsonUtils:`**

| Method                                | Purpose                                                  |
|---------------------------------------|----------------------------------------------------------|
| `JsonUtils.jsonToObject(String)`      | Parse a JSON string into a Java object (Map, List, etc.) |
| `JsonUtils.jsonToMap(String)`         | Parse JSON into a `Map<String, Object>`                  |
| `JsonUtils.jsonToList(String)`        | Parse JSON array into `List<Object>`                     |
| `JsonUtils.classpathToObject(String)` | Load JSON from a resource in the classpath and parse it  |
| `JsonUtils.filepathToObject(String)`  | Load JSON from a file path and parse it                  |
| `JsonUtils.objectToJson(Object)`      | Convert a Java object to a JSON string                   |
| `JsonUtils.prettyPrintJson(Object)`   | Convert object to pretty-printed JSON string             |

### sonUtils + Chainr to transform JSON using a spec
[input.json](jolt_json/input.json)
```json
{
  "name": "Alice",
  "age": 30,
  "city": "Hanoi"
}
```
[spec.json](jolt_json/spec.json)
```json
[
  {
    "operation": "shift",
    "spec": {
      "name": "fullName",
      "city": "location"
    }
  }
]
```




- `shift` → Moves data from one place to another.
- `name`: "fullName" → Copies name to fullName.
- `city`: "location" → Copies city to location.


**Example:**
```java
import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;

import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        // Load input JSON as Java object (Map)
        Object inputJSON = JsonUtils.filepathToObject("src/main/resources/jolt_json/input.json");

        // Load Jolt spec as Java object (List)
        List<Object> chainrSpecJSON = JsonUtils.filepathToList("src/main/resources/jolt_json/spec.json");

        // Create Chainr transformation from spec
        Chainr chainr = Chainr.fromSpec(chainrSpecJSON);

        // Apply transformation
        Object transformedOutput = chainr.transform(inputJSON);

        // Print pretty JSON result
        System.out.println(JsonUtils.toPrettyJsonString(transformedOutput));
      
        deserializeAndSerialize();
    }

    public static void deserializeAndSerialize() {
        String json = """
                {
                    "name": "Alice",
                    "age": 30
                }
                """;

        // Convert JSON string to Map
        Map<String, Object> data = JsonUtils.jsonToMap(json);
        System.out.println(data.get("name")); // Alice

        // Convert back to JSON string
        String backToJson = JsonUtils.toJsonString(data);
        System.out.println(backToJson);
    }
}
```
```text
{
  "fullName" : "Alice",
  "location" : "Hanoi"
}
Alice
{"name":"Alice","age":30}
```

### 💡 Why this is powerful:
- You don’t hardcode mapping in Java — you just change the `spec.json`.
- Jolt supports more than shift — it has `default`, `remove`, `modify-overwrite-beta`, etc.
- You can build complex JSON reshaping without changing Java code.


--------------------
<br/>


## Validation Json with javax validation
### Validation Dependency
- if you use jakarta, you need add jakarta.el dependency
- You can see Problem, Here: https://stackoverflow.com/questions/24386771/javax-validation-validationexception-hv000183-unable-to-load-javax-el-express
```pom
        <!-- Validation-->
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-validator</artifactId>
            <!-- Version  8.0.1.Final  got jakarta.el.ExpressionFactory Exception-->
            <version>6.0.16.Final</version>
        </dependency>
        <!-- Validation API -->
        <!-- Pay attention: Need attend javax and jakarta with each version of hibernate-->
        <dependency>
            <groupId>javax.validation</groupId>
            <artifactId>validation-api</artifactId>
            <version>2.0.1.Final</version>
        </dependency>
```

### Validate Method
- in method I validate Person Pojo, you can change
- Json Example:
  - [person.json](example/person.json)
  - [person_invalid.json](example/person_invalid.json)

**Custom Exception:** [PersonException.java](validation/PersonException.java)
```java
public class PersonException extends RuntimeException{
  private String message;

  public PersonException(String message){
    super(message);
    this.message = message;
  }
}
```

**Pojo:** [Person.java](validation/Person.java)
```java
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

@Data
public class Person {

    private Long id;

    @NotNull
    private String name;

    @Min(18) @Max(99)
    private Integer age;

    @Email
    private String email;
}
```

**Validate Method:**
```java
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

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
```

- You can see all Test Here: [App.java](validation/App.java)
### Javax Validation Annotation
#### 1.Null / Not Null
- `@Null` → value must be null.
- `@NotNull` → value must not be null.
```java
class ExampleNull {
  @Null
  private String mustBeNull;

  @NotNull
  private String mustNotBeNull;
} 
```

#
#### 2. Boolean / Truth
- `@AssertTrue` → must be true.
- `@AssertFalse` → must be false.
```java
class ExampleBoolean {
  @AssertTrue
  private boolean active;

  @AssertFalse
  private boolean disabled;
} 
```

#
#### 3. Size / Length
- `@Size(min, max)` → string, collection, map, or array must have a size within range.
```java
class ExampleSize {
  @Size(min = 2, max = 10)
  private String username;

  @Size(min = 1, max = 5)
  private List<String> tags;
}
```

#
#### 4. Numeric Constraints
- `@Min(value)` → must be greater than or equal to value.
- `@Max(value)` → must be less than or equal to value.
- `@DecimalMin(value, inclusive = true/false)` → decimal min.
- `@DecimalMax(value, inclusive = true/false)` → decimal max.
- `@Digits(integer, fraction)` → number must have digits restrictions.


```java
class ExampleNumbers {
    @Min(18)
    private int age;

    @Max(100)
    private int score;

    @DecimalMin("0.1")
    private double minValue;

    @DecimalMax(value = "999.99", inclusive = false)
    private double price;

    @Digits(integer = 5, fraction = 2)
    private BigDecimal amount;
}
```

#
#### 5. String / Character
* `@Pattern(regexp)` → must match regex.
* `@Email (Hibernate Validator extension in javax)` → must be valid email.
* `@NotBlank (Hibernate Validator extension)` → must not be null and trimmed length > 0.
* `@NotEmpty (Hibernate Validator extension)` → must not be null or empty.

```java
class ExampleStrings {
    @Pattern(regexp = "[a-zA-Z0-9_]+")
    private String username;

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotEmpty
    private String nickname;
}
```

#
#### 6. Past / Future Dates
* `@Past` → must be a date/time in the past.
*` @PastOrPresent` → must be past or today.
* `@Future` → must be in the future.
* `@FutureOrPresent` → must be future or today.

```java
import java.time.LocalDate;

class ExampleDates {
    @Past
    private LocalDate birthDate;

    @PastOrPresent
    private LocalDate createdAt;

    @Future
    private LocalDate expiryDate;

    @FutureOrPresent
    private LocalDate scheduledDate;
}
```

#
#### 7. Custom Message Example
```java
class ExampleMessage {
    @NotNull(message = "Name cannot be null!")
    private String name;
}
```

#
#### 8. More 
* `@Length(min, max)` → like @Size, but for strings only.
* `@Range(min, max)` → numeric range check.
* `@URL` → must be a valid URL.
* `@CreditCardNumber` → must be valid credit card format.
* `@EAN` → barcode number validation.
* `@ISBN` → valid ISBN.
* `@SafeHtml` (deprecated) → for sanitizing HTML.

```java
import org.hibernate.validator.constraints.*;

class ExampleHibernate {
    @Length(min = 5, max = 20)
    private String description;

    @Range(min = 1, max = 10)
    private int rating;

    @URL
    private String website;

    @CreditCardNumber
    private String creditCard;
}
```

#
### Custom Validation Annotation
- `Step 1` Create custom annotation
- `Step 2` Implement ConstraintValidator
- `Step 3` Use your annotation in POJO
- `Step 4` Run Validation

**Step 1:**
```java
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LowercaseValidator.class) // link to validator
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface LowercaseOnly {

    String message() default "must be lowercase letters only";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
```

**Step 2:**
```java
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LowercaseValidator implements ConstraintValidator<LowercaseOnly, String> {

    @Override
    public void initialize(LowercaseOnly constraintAnnotation) {
        // you can read annotation parameters here if needed
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true; // treat null as valid (use @NotNull if needed)
        return value.matches("[a-z]+");
    }
}
```

**Step 3:**
```java
    @LowercaseOnly
    private String code;
```

**Step 4:**
- Run And check here: [App.java](validation%2FApp.java)
- if Exist Uppercase
```text
age must be less than or equal to 99
```
