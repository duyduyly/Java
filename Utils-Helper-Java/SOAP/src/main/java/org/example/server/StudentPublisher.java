package org.example.server;

import jakarta.xml.ws.Endpoint;
import org.example.server.web_service.StudentServiceImpl;

public class StudentPublisher {
    public static void main(String[] args) {
        String url = "http://localhost:8080/studentService";
        Endpoint.publish(url, new StudentServiceImpl());
        System.out.println("SOAP Service running at: " + url + "?wsdl");
    }
}
