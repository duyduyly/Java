package org.example.client;


import org.example.client.model.Student;
import org.example.client.service.StudentService;
import org.example.client.service.StudentServiceImpl;

import java.util.List;

public class StudentClient {
    public static void main(String[] args) {
        caseNameInvalid();
        caseIdInvalid();
        caseSuccess();
    }

    public static void caseNameInvalid() {
        try {
            StudentServiceImpl service = new StudentServiceImpl();
            StudentService port = service.getStudentServicePort();
            Student s1 = port.createStudent(1, "x", "Ha Noi");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("Case Name Invalid");
        }
    }

    public static void caseIdInvalid() {
        try {
            StudentServiceImpl service = new StudentServiceImpl();
            StudentService port = service.getStudentServicePort();
            Student s1 = port.createStudent(-1, "x", "Ha Noi");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("Case Id Invalid");
        }
    }

    public static void caseSuccess() {
        StudentServiceImpl service = new StudentServiceImpl();
        StudentService port = service.getStudentServicePort();
        // Create

        Student s1 = port.createStudent(1, "Alan", "Ha Noi");
        Student s2 = port.createStudent(2, "Bob", "Ha Noi");

        // Read
        Student student = port.getStudent(1);
        System.out.println("FindById(1): " + student);

        // Update
        port.updateStudent(1, "Alice Updated");
        System.out.println("After update: " + port.getStudent(1));

        // Delete
        port.deleteStudent(2);
        System.out.println("Deleted student 2");

        // Get All
        List<Student> all = port.getAllStudents();
        System.out.println("All students: " + all);
        System.out.println("Test Success");
    }
}
