package org.example.client;


import jakarta.xml.bind.JAXBException;
import org.example.client.model.Address;
import org.example.client.model.Student;
import org.example.client.service.StudentService;
import org.example.client.service.StudentServiceImpl;
import org.example.utils.XmlUtils;

import java.util.List;

public class StudentClient {
    private final static XmlUtils<Address> addressUtils = new XmlUtils<>();
    private final static StudentServiceImpl service = new StudentServiceImpl();
    private final static  StudentService port = service.getStudentServicePort();
    private final static String COMMON_ADDRESS = """
            <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
            <address>
                <city>Hà Nội</city>
                <id>1</id>
            </address>
            """;
    public static void main(String[] args) throws JAXBException {
        Address haNoi = new Address(1, "Hà Nội");
        Address daNang = new Address(2, "Đà Nẵng");
        Address hue = new Address(3, "Huế");

        caseNameInvalid();
        caseIdInvalid();
        caseSuccess();

        createStudent(3, "Peter", addressUtils.toXml(haNoi));
        createStudent(4, "Peter", addressUtils.toXml(daNang));
        createStudent(5, "Peter", addressUtils.toXml(hue));

    }

    public static void caseNameInvalid() {
        try {
            Student s1 = port.createStudent(1, "x", COMMON_ADDRESS);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("Case Name Invalid");
        }
    }

    public static void caseIdInvalid() {
        try {
            Student s1 = port.createStudent(-1, "x", COMMON_ADDRESS);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("Case Id Invalid");
        }
    }

    public static void caseSuccess() {

        // Create
        createStudent(1, "Alan", COMMON_ADDRESS);
        createStudent(2, "Bob", COMMON_ADDRESS);

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

    public static void createStudent(int id, String name, String xmlAddress) {
        Student student = port.createStudent(id, name, xmlAddress);
        System.out.println(student.toString() + " Created");
    }
}
