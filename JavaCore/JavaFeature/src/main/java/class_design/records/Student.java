package class_design.records;

import java.util.Objects;

//the old way of creating encapsulated class
public class Student {

    // 1. declare private final fields
    private final String firstName;
    private final String lastName;
    private final int id;

    // 2. define the constructor
    public Student(String firstName, String lastName, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    // 3. define getters
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    // 4. override toString() method
    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id + '}';
    }

    // 5. override equals() method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && Objects.equals(firstName, student.firstName) &&
                Objects.equals(lastName, student.lastName);
    }

    // 6. override hashCode() method
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, id);
    }
}
