package org.example.client.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import org.example.client.model.Student;

import java.util.List;

@WebService(
        targetNamespace = "http://localhost:8080/studentService", //Target Namespace Define in WebService in server
        name = "StudentService")
public interface StudentService {
    @WebMethod(operationName = "createStudent")
    @WebResult(name = "student")
    Student createStudent(
            @WebParam(name = "id") int id,
            @WebParam(name = "name") String name,
            @WebParam(name = "address") String xmlAddress
    );

    @WebMethod(operationName = "findById")
    @WebResult(name = "student")
    Student getStudent(
            @WebParam(name = "id") int id
    );

    @WebMethod(operationName = "updateStudent")
    @WebResult(name = "student")
    Student updateStudent(
            @WebParam(name = "id") int id,
            @WebParam(name = "name") String newName
    );

    @WebMethod(operationName = "deleteStudent")
    @WebResult(name = "success")
    boolean deleteStudent(
            @WebParam(name = "id") int id
    );

    @WebMethod(operationName = "getAllStudents")
    @WebResult(name = "students")
    List<Student> getAllStudents();
}
