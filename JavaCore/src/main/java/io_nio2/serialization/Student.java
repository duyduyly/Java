package io_nio2.serialization;

import lombok.Getter;

import java.io.Serializable;
import java.util.UUID;

@Getter
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private transient String id;
    private String name;
    private int age;

    public Student(String Name, int Age) {
        this.id = UUID.randomUUID().toString();
        this.name = Name;
        this.age = Age;
    }

    @Override
    public String toString() {
        return "Student [ID=" + id + ", Name=" + name + ", Age=" + age + "]";
    }
}
