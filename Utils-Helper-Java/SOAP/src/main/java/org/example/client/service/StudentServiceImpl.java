package org.example.client.service;

import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebServiceClient;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;

@WebServiceClient(
        name = "StudentServiceImpl",
        targetNamespace = "http://localhost:8080/studentService", //Target Namespace Define in WebService in server
        wsdlLocation = "http://localhost:8080/studentService?wsdl" // wsdl link when run server
)
public class StudentServiceImpl extends Service {
    private static final URL WSDL_LOCATION;
    private static final QName SERVICE = new QName(
            "http://localhost:8080/studentService", //Target Namespace Define in WebService in server
            "StudentService" // serviceName in @WebService in server
    );
    private static final QName PORT = new QName(
            "http://localhost:8080/studentService",//Target Namespace Define in WebService in server
            "StudentServicePort" // portName in @WebService in server
    );

    static {
        URL url = null;
        try{
            url=  new URL("http://localhost:8080/studentService?wsdl"); // wsdl link when run server
        } catch (MalformedURLException e) {
            e.getStackTrace();
        }

        WSDL_LOCATION =  url;
    }

    public StudentServiceImpl(){
        super(WSDL_LOCATION, SERVICE);
    }

    public StudentService getStudentServicePort(){
        return super.getPort(PORT, StudentService.class);
    }
}
