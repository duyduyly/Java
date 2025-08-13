package json.jackson;

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
