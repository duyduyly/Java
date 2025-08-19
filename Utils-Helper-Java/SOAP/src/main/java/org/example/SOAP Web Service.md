# SOAP Web Service

- [**About SOAP**](#about-soap)
- [**Dependency**](#dependency)
- [**Annotation**](#annotation)
  - [*1.Server Side*](#1-server-side-annotations--provider-of-soap-service-)
  - [*2.Client-Side*](#2-client-side-annotations--consumer-of-soap-service-)
  - [*3.Can Appear Both Sides*](#3-shared-annotations--can-appear-both-sides-)
- [**Detail About Annotation**](#detail)
  - [*1.Server Side*](#server-side-annotations--publishing-soap-services-)
  - [*2.Client-Side*](#client-side-annotations--consuming-soap-services-)
  - [*Other Useful Annotations*](#-other-useful-annotations)
- [**Create SOAP Web Service**](#create-soap-web-service)
  - [*Server*](#server)
  - [*Client*](#client)

#
## About SOAP
- A SOAP Web Service is a type of web service that uses SOAP (Simple Object Access Protocol) to exchange data between applications over a network, usually the Internet. It’s one of the older but still widely used standards for building interoperable, platform-independent, and language-independent services.
- what is SOAP?
  - SOAP is a protocol defined by the W3C (World Wide Web Consortium).
  - It uses XML to structure messages.
  - It defines a strict standard for how messages are formatted, sent, and received.
- SOAP Web Service Architecture
  1. WSDL (Web Services Description Language)
     - An XML-based document that describes what the service does, its methods (operations), input/output parameters, and endpoint URLs.
     - Acts like a "contract" between client and server.
  2. Transport Protocol
      - Most commonly HTTP/HTTPS, but SOAP can also run over SMTP, JMS, TCP, etc.
  3. SOAP Message
     - The actual request and response are XML documents wrapped in a SOAP envelope.
```xml
<soap:Envelope>
<soap:Header>...</soap:Header>
<soap:Body>
<m:GetStudent xmlns:m="http://example.org/student">
<m:studentId>123</m:studentId>
</m:GetStudent>
</soap:Body>
</soap:Envelope>
```
- Features
  - Platform-independent: Java, .NET, Python, etc. can all communicate if they follow the SOAP standard.
  - Strong typing: Input/output data is strictly defined in the WSDL.
  - Built-in error handling: SOAP messages include fault elements for reporting errors.
  - Extensible: Can support security, transactions, and other features via SOAP headers.

- Example Use Cases
  - Banking & financial systems (where strict standards & security are critical).
  - Telecom billing systems.
  - Enterprise-level applications that require guaranteed contracts between services.

- Difference Between SOAP and RESTFUL

| Feature       | SOAP                   | REST                       |
|---------------|------------------------|----------------------------|
| `Protocol`    | Strict protocol        | Architectural style        |
| `Data Format` | XML only               | JSON, XML, others          |
| `Transport`   | HTTP, SMTP, JMS, etc.  | HTTP only                  |
| `Contract`    | WSDL                   | Swagger/OpenAPI (optional) |
| `Security`    | WS-Security (built-in) | HTTPS, OAuth2, JWT         |
| `Speed`       | Slower (heavy)         | Faster (lightweight)       |
| `Use Case`    | Enterprise, Banking    | Web/Mobile APIs            |

## Dependency
- SOAP Dependency
```xml
        <!-- JAX-WS API -->
        <dependency>
            <groupId>jakarta.xml.ws</groupId>
            <artifactId>jakarta.xml.ws-api</artifactId>
            <version>3.0.1</version>
        </dependency>

        <!-- JAX-WS Runtime (Metro implementation) -->
        <dependency>
            <groupId>com.sun.xml.ws</groupId>
            <artifactId>jaxws-rt</artifactId>
            <version>3.0.2</version>
        </dependency>
```

- Validate Field:
```xml
        <dependency>
            <groupId>org.hibernate.validator</groupId>
            <artifactId>hibernate-validator</artifactId>
            <version>8.0.1.Final</version> <!-- or latest -->
        </dependency>

        <dependency>
            <groupId>org.glassfish</groupId>
            <artifactId>jakarta.el</artifactId>
            <version>4.0.2</version> <!-- EL needed for some validation annotations -->
        </dependency>
```

## Annotation
### Overview
#### 1. Server-Side Annotations (Provider of SOAP Service)
| Annotation          | Where to Use        | Purpose                                                                                 |
|---------------------|---------------------|-----------------------------------------------------------------------------------------|
| **`@WebService`**   | Class               | Marks the class as a SOAP service. Defines service name, port name, namespace, etc.     |
| **`@WebMethod`**    | Method              | Marks a method as an operation in the service.                                          |
| **`@WebParam`**     | Method parameter    | Customizes parameter name, namespace, mode (IN/OUT/INOUT), or move to SOAP header.      |
| **`@WebResult`**    | Method return value | Customizes name/namespace of the SOAP response element.                                 |
| **`@OneWay`**       | Method              | Declares a method as one-way (no response, no fault). Useful for fire-and-forget calls. |
| **`@HandlerChain`** | Class               | Attach a handler chain (SOAP message interceptor for logging, auth, etc.).              |
| **`@SOAPBinding`**  | Class or Method     | Defines SOAP binding style (RPC vs Document, Literal vs Encoded). Controls WSDL style.  |
#
#### 2. Client-Side Annotations (Consumer of SOAP Service)
| Annotation                    | Where to Use              | Purpose                                                                        |
|-------------------------------|---------------------------|--------------------------------------------------------------------------------|
| **`@WebServiceClient`**       | Generated Service class   | Defines the client stub factory. Holds WSDL location, namespace, service name. |
| **`@WebEndpoint`**            | Method (in Service class) | Identifies a specific port of the service. Used to get a proxy object (port).  |
| **`@WebFault`**               | Exception class           | Maps a Java exception to a SOAP fault.                                         |
| **`@XmlSeeAlso`** (from JAXB) | Generated client          | Tells JAXB about extra classes used for marshalling/unmarshalling.             |

#
#### 3. Shared Annotations (Can Appear Both Sides)
| Annotation                                                   | Purpose                                                                                                           |
|--------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|
| **`@RequestWrapper`**                                        | Customizes wrapper class for request message (instead of auto-generated).                                         |
| **`@ResponseWrapper`**                                       | Customizes wrapper class for response message.                                                                    |
| **`@XmlRootElement`, `@XmlType`, `@XmlElement`** (from JAXB) | Used for request/response object mapping to XML. These are not strictly JAX-WS, but part of JAXB for XML binding. |



### Detail
### Server-Side Annotations (Publishing SOAP Services)
| Annotation          | Where Used               | Key Variables / Attributes                                                                                                                                                                                                                                                                     | Description                                                    |
|---------------------|--------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------|
| **`@WebService`**   | Class (service endpoint) | - `name`: Name of the service (default = class name). <br> - `serviceName`: Service name in WSDL. <br> - `targetNamespace`: Namespace for WSDL. <br> - `endpointInterface`: Fully qualified name of interface defining SEI (Service Endpoint Interface). <br> - `portName`: Port name in WSDL. | Declares a class as a SOAP web service.                        |
| **`@WebMethod`**    | Method                   | - `operationName`: Name of the operation in WSDL. <br> - `action`: SOAP action (maps to HTTP `SOAPAction` header). <br> - `exclude`: If `true`, method is not exposed as a web service.                                                                                                        | Exposes a Java method as a SOAP operation.                     |
| **`@WebParam`**     | Method parameter         | - `name`: Parameter name in WSDL. <br> - `partName`: Name of the message part in WSDL. <br> - `mode`: `IN` (default), `OUT`, or `INOUT`. <br> - `header`: `true` if parameter should go in SOAP header instead of body.                                                                        | Defines metadata about input/output parameters.                |
| **`@WebResult`**    | Method                   | - `name`: Name of return value in WSDL. <br> - `partName`: Message part name. <br> - `targetNamespace`: Namespace for result. <br> - `header`: `true` if return should go in SOAP header.                                                                                                      | Defines metadata for method return value.                      |
| **`@HandlerChain`** | Class or method          | - `file`: XML file path that defines handler chain. <br> - `name`: Logical name of handler chain.                                                                                                                                                                                              | Attaches SOAP handlers (logging, security, etc.) to endpoint.  |
| **`@SOAPBinding`**  | Class or method          | - `style`: `DOCUMENT` (default) or `RPC`. <br> - `use`: `LITERAL` (default) or `ENCODED`. <br> - `parameterStyle`: `WRAPPED` (default) or `BARE`.                                                                                                                                              | Defines SOAP binding style (document vs RPC, wrapped vs bare). |


#
### Client-Side Annotations (Consuming SOAP Services)
| Annotation              | Where Used                                     | Key Variables / Attributes                                                                                                | Description                                                           |
|-------------------------|------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------|
| **`@WebServiceRef`**    | Field, method, class                           | - `name`: JNDI name of resource. <br> - `type`: Service class type. <br> - `mappedName`: Mapped name in deployment.       | Injects a reference to a web service client.                          |
| **`@WebServiceClient`** | Service class (auto-generated from `wsimport`) | - `name`: Service name in WSDL. <br> - `targetNamespace`: Namespace for service. <br> - `wsdlLocation`: Location of WSDL. | Marks a generated class as a web service client.                      |
| **`@WebEndpoint`**      | Method (inside `@WebServiceClient` class)      | - `name`: Port name in WSDL.                                                                                              | Marks a factory method for a port (proxy to actual service endpoint). |


#
### 🟨 Other Useful Annotations
| Annotation                                       | Where Used         | Description                                                                               |
|--------------------------------------------------|--------------------|-------------------------------------------------------------------------------------------|
| **`@Oneway`**                                    | Method             | Marks a method as one-way (no response expected). Useful for fire-and-forget operations.  |
| **`@XmlElement`, `@XmlType`, `@XmlRootElement`** | Model/Entity class | JAXB annotations, used for XML serialization/deserialization of request/response objects. |


#
## Create SOAP Web Service
### Server
- Order Class:
  - [Address.java](server/model/Address.java)
  - [Student.java](server/model/Student.java)
  - [StudentException.java](server/exception/StudentException.java)
- [StudentService.java](server/web_service/StudentService.java)
  - Define blueprint for SOAP Service
```java
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
            @WebParam(name = "city") String city
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
```

- [StudentServiceImpl.java](server/web_service/StudentServiceImpl.java)
  - Implement service Define Name for service
```java

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
```

- Validate Method:
```java
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD) //importance when validate
public class Student {

    @Min(1)
    @XmlElement(required = true)
    private int id;

    @NotNull // cannot be null
    @Size(min = 2, max = 50) // must be between 2 and 50 chars
    @XmlElement(required = true)
    private String name;
    private Address address;
}
```
```java
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
```

- [StudentPublisher.java](server/StudentPublisher.java)
  - start SOAP Web Service
```java
import jakarta.xml.ws.Endpoint;
import org.example.server.web_service.StudentServiceImpl;

public class StudentPublisher {
    public static void main(String[] args) {
        String url = "http://localhost:8080/studentService";
        Endpoint.publish(url, new StudentServiceImpl());
        System.out.println("SOAP Service running at: " + url + "?wsdl");
    }
}
```
#
### Client
- Same with server:
- Model:
  - To map xml from server
  - [Address.java](client/model/Address.java)
  - [Student.java](client/model/Student.java)
  - can use validate same with ([Student.java](server/model/Student.java)) model in Server

- Service Interface ([StudentService.java](client/service/StudentService.java)):
```java

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
          @WebParam(name = "city") String city
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
```

- **Service Implement**: ([StudentServiceImpl.java](server/web_service/StudentServiceImpl.java))
```java
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
```

- **Test**
```java
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
```

