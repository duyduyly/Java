# Json

- Gson
- ObjectMapper
- jolt.JsonUtils

## Gson
- Gson is a Java library developed by Google that’s used to convert Java objects to JSON (serialization) and JSON to Java objects (deserialization).
  - It’s lightweight
  - Easy to use
  - And supports complex object structures, generic types, and custom serialization/deserialization.

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

### Dependency
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
[input.json](../../resources/jolt_json/input.json)
```json
{
  "name": "Alice",
  "age": 30,
  "city": "Hanoi"
}
```
[spec.json](../../resources/jolt_json/spec.json)
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

