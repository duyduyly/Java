package old_package.java8;

import old_package.model.Student;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Stream {
    /**
     *  In Java 8, the Stream API was introduced as a major feature to enable functional-style operations on collections and data sequences.
     * What is a Stream?
     *  A Stream is a sequence of elements supporting sequential and parallel aggregate operations.
     *  Streams are not data structures and do not store elements; instead, they convey elements from a source (like a collection) through a pipeline of operations.
     *
     * Stream operations can be classified into two categories:
     *
     * @Intermediate Operations:
     * Transform a stream into another stream. Intermediate operations are lazy, meaning they are not executed until a terminal operation is invoked.
     * Examples include:
     * - filter(Predicate)
     * - map(Function)
     * - sorted()
     * - distinct()
     * - limit(n) and skip(n)
     * @Terminal Operations: Produce a result or a side effect and consume the stream.
     * Examples include:
     * - forEach(Consumer)
     * - collect(Collector)
     * - reduce()
     * - count()
     * - findFirst(), findAny()
     * - allMatch(), anyMatch(), noneMatch()
     */

    public static void main(String[] args) {
        Student s1 = new Student();
        List<Student> students = s1.generateStudent(10);
        log("All Student",students);

        List<String> studentFullName = students.stream()
                .filter(student -> student.getAge() > 10)
                .map(Student::getFullName).toList();
        log("Get Full Name Have Age > 10",studentFullName);

        List<String> sortedList = students.stream()
                .sorted((o1,o2) -> Integer.compare(o1.getAge(), o2.getAge()))
                .map(st -> st.getFullName() +" | "+st.getAge()).toList();
        log("Sort List by Age", sortedList);

        List<String> distinct = Arrays.asList("s","s","s","s","s","s");
        log("Distinct List", distinct.stream().distinct().toList());

        List<Student> studentLimit = students.stream().limit(4).skip(2).toList();
        log("Get 4 Student Limit And Skip 2 student first", studentLimit);


        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        int sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);
        log("Use reduce To sum",sum);


        List<Integer> numbers2 = Arrays.asList(1, 2, 3, 4, 5, 6);
        int sum2 = numbers2.parallelStream()
                .reduce(0, Integer::sum);
        log("Use reduce To sum parallelStream",sum2);

        Optional<Student> max = students.stream().max(Comparator.comparing(Student::getAge));
        log("Max Student Age", max.get().getAge());

    }

    private static void log(String title, Object val) {
        System.out.println();
        System.out.println("------------Start " + title + "-----------");
        if(val instanceof List<?>) {
            for (Object ob : (List<?>) val) {
                System.out.println(ob.toString());
            }
        }else {
            System.out.println(val.toString());
        }
        System.out.println("------------End " + title + "-----------");
        System.out.println();
    }

}
