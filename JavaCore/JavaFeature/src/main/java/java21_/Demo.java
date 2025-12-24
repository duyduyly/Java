package java21_;

import java.awt.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SequencedMap;
import java.util.TreeMap;

public class Demo {

    record StudentRecord(Long id, String name) { };

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>(); //random entry when print
        SequencedMap<Integer, String> sequencedMap = new LinkedHashMap<>(); // linked insertion order
        Map<Object, Object> objectObjectHashtable = new LinkedHashMap<>();

        map.put(1, "one");
        map.put(6, "six");
        map.put(4, "four");
        map.put(5, "five");
        map.put(2, "two");
        map.put(3, "three");
        map.put(100, "three");
        map.put(20, "three");
        map.put(40, "three");
        map.put(80, "three");

        sequencedMap.putFirst(1, "one");
        sequencedMap.putFirst(6, "six");
        sequencedMap.putFirst(4, "four");
        sequencedMap.putFirst(5, "five");
        sequencedMap.putFirst(2, "two");
        sequencedMap.putFirst(3, "three");
        sequencedMap.putFirst(100, "three");
        sequencedMap.putFirst(20, "three");
        sequencedMap.putFirst(40, "three");
        sequencedMap.putFirst(80, "three");

        System.out.println("Sequenced Map: "+sequencedMap);
        System.out.println("Map: "+map);
    }

    public static String checkObType(Object ob) {
        return switch (ob) {
            case String s1 when s1.length() > 5 -> s1 + "'s long String";
            case String s2 -> s2 + "'s short string";
            case Integer i -> i + "'s an int ";
            case Double d -> d + "'s an Double ";
            case Long l -> l + "'s an Long ";
            case Float f -> f + "'s an Float ";
            case StudentRecord(Long id, String name) -> "this is Record Student";
            case null -> "It's null!";
            default -> "Unknown type";
        };
    }

    public static void testObType() {
        System.out.println("Result 1: " + checkObType("ABC"));
        System.out.println("Result 2: " + checkObType(12));
        System.out.println("Result 3: " + checkObType(1D));
        System.out.println("Result 4: " + checkObType(3000000L));
        System.out.println("Result 5: " + checkObType(12f));
        System.out.println("Result 6: " + checkObType(null));
        System.out.println("Result 7: " + checkObType('1'));
        System.out.println("Result 7: " + checkObType(new StudentRecord(1L, "Alan")));
        System.out.println("Result 8: " + checkObType("12421412412"));
    }

    sealed interface Shape permits Circle, Square { }
    record Circle(double radius) implements Shape { }
    record Square(double side) implements Shape { }

    public static String exhaustivenessChecks(Shape shape) {
        return switch (shape) {
            case Circle c -> "Circle with r = " + c.radius();
            case Square s -> "Square with side = " + s.side();
            default -> "Unknown type";
        };
    }

    public static String checkStudentRecord(Object ob) {
        return switch (ob) {
            case StudentRecord(Long id, String name) -> "this is Record Student (" + id + ", " + name + ")";
            case null -> "It's null!";
            default -> "Unknown type";
        };
    }

}
