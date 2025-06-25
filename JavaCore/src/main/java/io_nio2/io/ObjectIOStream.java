package io_nio2.io;

import io_nio2.Constant;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ObjectIOStream {

    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person("Alan", 25),
                new Person("Peter", 25),
                new Person("Michel", 25),
                new Person("Lan", 25),
                new Person("May", 25),
                new Person("Keran", 25),
                new Person("Trump", 25)
        );

        write("src/resource/file/Output.dat", people);
        List<Person> read = read("src/resource/file/Output.dat");
        read.forEach(System.out::println);
        System.out.println("---------------------------");
        write("src/resource/file/ObjectOutput.dat", people.get(0));
        Person person = readObject("src/resource/file/ObjectOutput.dat");
        System.out.println(person);

    }

    public static void write(String filePath, List<Person> people) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(people);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file", e);
        }
    }

    public static void write(String filePath, Person person) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(person);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file", e);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Person> read(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            if(ois.readObject() instanceof List list) {
                if (!list.isEmpty() && list.get(0) instanceof Person) {
                   return (List<Person>) list;
                }
            }
            throw new RuntimeException("Content invalid");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error reading to file", e);
        }
    }


    public static Person readObject(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
                if (ois.readObject() instanceof Person person) {
                    return person;
                }
            throw new RuntimeException("Content invalid");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error reading to file", e);
        }
    }


    public static class Person implements Serializable {
        private static final long serialVersionUID = 1L;

        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}
