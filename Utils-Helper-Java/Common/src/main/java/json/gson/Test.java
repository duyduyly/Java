package json.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import json.StudentRecord;

import java.lang.reflect.Type;
import java.util.List;

public class Test {


    public static void main(String[] args) {
        List<StudentRecord> studentRecords = StudentRecord.generate();

        System.out.println("Serialize Object Json: ");
        System.out.println(serializeObjectJson(studentRecords));
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
