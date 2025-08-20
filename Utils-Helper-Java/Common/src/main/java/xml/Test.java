package xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import xml.model.Address;
import xml.model.Student;
import xml.service.XmlService;

import java.io.StringReader;
import java.io.StringWriter;

public class Test {


    public static void main(String[] args) throws JAXBException {
        String strXml = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <ns2:student id="1" status="true" xmlns:ns2="http://example.com/student">
                    <name>alice@example.com</name>
                    <email>Alice</email>
                    <address id="1">
                        <city>Ha Noi</city>
                    </address>
                    <rawText>&lt;![CDATA[IntelliJ IDEA Community Edition 2022.2.3\\lib\\idea_rt.jar=63574:C:\\Program
                ]]&gt;</rawText>
                </ns2:student>
                """;

        String rawText = """
                IntelliJ IDEA Community Edition 2022.2.3\\lib\\idea_rt.jar=63574:C:\\Program
                """;
        Address address = new Address(1L, "Ha Noi");
        Student student = new Student(1, true, "alice@example.com", "Alice", address, null, rawText);

        XmlService<Student> xmlService = new XmlService<>();
        System.out.println(xmlService.toXml(student));
        System.out.println(xmlService.toObject(strXml, Student.class));
    }
}
