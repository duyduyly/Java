package java8;

import model.Student;

import java.util.List;

public class TestParallelStream {

    //A parallel stream allows us to use multi-core processing by executing the stream operation parallel across multiple CPU cores

    public static void main(String[] args) {
        Student s1 = new Student();
        List<Student> students = s1.generateStudent(10000000);
        logStream("Stream ", students);
        logStreamDotParallelS("Stream Dot ParallelS ", students);
        logParallelStream("Parallel Stream ", students);

    }

    private static void logStream(String title, List<Student> students) {
        Long start = System.currentTimeMillis();
        System.out.println();
        System.out.println("Start: " + title + start);
        List<String> list = students.stream()
                .map(student -> student.getName().concat("1"))
                .filter(name -> name.length() > 3)
                .filter(name -> name.contains("i"))
                .toList();
        System.out.println(list.size());
        Long end = System.currentTimeMillis();
        System.out.println("End: " + title + end);
        System.out.println("Time: " + ((end - start) / 1000D) + "seconds");
        System.out.println();
    }

    //stream().parallel() is more flexible if you want to dynamically choose to go parallel based on runtime conditions.
    private static void logStreamDotParallelS(String title, List<Student> students) {
        Long start = System.currentTimeMillis();
        System.out.println();
        System.out.println("Start: " + title + start);
        List<String> list = students.stream()
                .map(student -> student.getName().concat("1"))
                .parallel()
                .filter(name -> name.length() > 3)
                .filter(name -> name.contains("i"))
                .toList();
        System.out.println(list.size());
        Long end = System.currentTimeMillis();
        System.out.println("End: " + title + end);
        System.out.println("Time: " + ((end - start) / 1000D) + "seconds");
        System.out.println();
    }

    //parallelStream() is more concise when you know you want to perform parallel operations on a Collection.
    private static void logParallelStream(String title, List<Student> students) {
        Long start = System.currentTimeMillis();
        System.out.println();
        System.out.println("Start: " + title + start);
        List<String> list = students.parallelStream()
                .map(student -> student.getName().concat("1"))
                .filter(name -> name.length() > 3)
                .filter(name -> name.contains("i"))
                .toList();
        System.out.println(list.size());
        Long end = System.currentTimeMillis();
        System.out.println("End: " + title + end);
        System.out.println("Time: " + ((end - start) / 1000D) + "seconds");
        System.out.println();
    }

}
