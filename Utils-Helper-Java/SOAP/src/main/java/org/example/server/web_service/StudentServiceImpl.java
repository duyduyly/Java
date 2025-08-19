package org.example.server.web_service;


import jakarta.jws.WebService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.example.server.model.Address;
import org.example.server.model.Student;

import java.util.*;

@WebService(
        endpointInterface = "org.example.server.web_service.StudentService", //define interface path
        serviceName = "StudentService",
        portName = "StudentServicePort",
        targetNamespace = "http://localhost:8080/studentService"
)
public class StudentServiceImpl implements StudentService {
    private final Map<Integer, Student> store = new HashMap<>();

    @Override
    public Student createStudent(int id, String name, String city) {
        Student student = new Student(id, name, new Address(id, city));
        this.validate(student);
        store.put(id, student);
        System.out.printf("Student %s created \n", name);
        return student;
    }

    @Override
    public Student getStudent(int id) {
        System.out.printf("Get Student Id: %s \n",id);
        return store.get(id);
    }

    @Override
    public Student updateStudent(int id, String newName) {
        Student student = store.get(id);
        if (student != null) {
            student.setName(newName);
            System.out.printf("Student %s Update \n", id);
        }

        return student;
    }

    @Override
    public boolean deleteStudent(int id) {
        System.out.printf("Delete Student id %s \n", id);
        return store.remove(id) != null;
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(store.values());
    }

    private void validate(Student student){
        try(ValidatorFactory factory = Validation.buildDefaultValidatorFactory()){
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Student>> violations = validator.validate(student);

            for (ConstraintViolation<Student> v : violations) {
                System.err.println(v.getPropertyPath() + " " + v.getMessage());
            }

            if(!violations.isEmpty()){
                throw new RuntimeException("Student Invalid");
            }
        }
    }
}
