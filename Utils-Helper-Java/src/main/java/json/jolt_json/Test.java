package json.jolt_json;

import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;

import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        // Load input JSON as Java object (Map)
        Object inputJSON = JsonUtils.filepathToObject("src/main/resources/json/jolt_json/input.json");

        // Load Jolt spec as Java object (List)
        List<Object> chainrSpecJSON = JsonUtils.filepathToList("src/main/resources/json/jolt_json/spec.json");

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
