package org.example.utils;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;
import java.io.StringWriter;

public class XmlUtils<T> {
    public String toXml(T ob) throws JAXBException {
// Create JAXB Context
        JAXBContext context = JAXBContext.newInstance(ob.getClass());

        // Create Marshaller
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter strXml = new StringWriter();
        // Convert object to XML and print
        marshaller.marshal(ob, strXml);

        // Or write to file
        // marshaller.marshal(student, new File("student.xml"));

        return strXml.toString();
    }

    public T toObject(String strXml, Class<T> obClass) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(obClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (T) unmarshaller.unmarshal(new StringReader(strXml));
    }
}
