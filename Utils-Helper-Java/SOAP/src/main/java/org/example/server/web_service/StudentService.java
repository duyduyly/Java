package org.example.server.web_service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import org.example.server.model.Student;

import java.util.List;

@WebService(
        name = "StudentService",
        targetNamespace = "http://localhost:8080/studentService"
)
@SOAPBinding(style=SOAPBinding.Style.DOCUMENT, use=SOAPBinding.Use.LITERAL)
public interface StudentService {
    @WebMethod(operationName = "createStudent")
    @WebResult(name = "student")
    Student createStudent(
            @WebParam(name = "id") int id,
            @WebParam(name = "name") String name,
            @WebParam(name = "address") String address
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
