# Work with Xml

- [**Dependency**](#dependency)
- [**Method**](#method)
- [**Understand And Work With Xml**](#understand-and-work-with-xml)
  - [*1.XML Declaration (optional but recommended)*](#1xml-declaration--optional-but-recommended-)
  - [*2.Root Element (mandatory)*](#2root-element--mandatory-)
  - [*3.Elements (Tags)*](#3elements--tags-)
  - [*4. Attributes*](#4-attributes)
  - [*5. Text Content*](#5-text-content)
  - [*6. Comments*](#6-comments)
  - [*7. Nested (Hierarchical) Structure*](#7-nested--hierarchical--structure)
  - [*8. Empty Elements*](#8-empty-elements)
  - [*9. CDATA Sections*](#9-cdata-sections)
  - [*10. Namespaces (for avoiding conflicts)*](#10-namespaces--for-avoiding-conflicts-)


## Dependency
- If using Java 8, JAXB is included in the JDK by default (package javax.xml.bind).
- In Java 11+, you must add the dependency manually.

### 1. jakarta.xml.bind:jakarta.xml.bind-api
- The JAXB API (Jakarta XML Binding API).
- It defines the interfaces and annotations that you use in your code, such as:
  - `@XmlRootElement`, `@XmlElement`, `@XmlAccessorType`
  - `JAXBContext`, `Marshaller`, `Unmarshaller`
- What it supports:
  - Provides the standard contracts for converting Java ↔ XML.
  - This artifact does not contain the actual implementation, only the specification.

#
### 2. org.glassfish.jaxb:jaxb-runtime
- What it is:
  - The reference implementation (RI) of the JAXB API.
  - Maintained under the Eclipse GlassFish project.
- What it supports:
  - Provides the real code that executes marshalling/unmarshalling.
  - Includes runtime classes such as `MarshallerImpl`, `UnmarshallerImpl`.
  - Implements everything defined in `jakarta.xml.bind-api`.

| Dependency                 | Provides                                                          | Needed for                                       |
|----------------------------|-------------------------------------------------------------------|--------------------------------------------------|
| `jakarta.xml.bind-api`     | Interfaces + annotations (`@XmlRootElement`, `JAXBContext`, etc.) | Writing code that uses JAXB                      |
| `jaxb-runtime` (GlassFish) | Actual implementation of the API                                  | Running the marshalling/unmarshalling at runtime |


```xml
<dependency>
    <groupId>jakarta.xml.bind</groupId>
    <artifactId>jakarta.xml.bind-api</artifactId>
    <version>3.0.1</version>
</dependency>
<dependency>
    <groupId>org.glassfish.jaxb</groupId>
    <artifactId>jaxb-runtime</artifactId>
    <version>3.0.1</version>
</dependency>
```

## Method
```java

// From Object To Xml
public static String toXml(Object ob) throws JAXBException{
// Create JAXB Context
    JAXBContext context=JAXBContext.newInstance(ob.getClass());

    // Create Marshaller
    Marshaller marshaller=context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);

    StringWriter strXml=new StringWriter();
    // Convert object to XML and print
    marshaller.marshal(ob,strXml);

    // Or write to file
    // marshaller.marshal(student, new File("student.xml"));

    return strXml.toString();
}
        
//From Str Xml To Object
public T toObject(String strXml, Class<T> obClass) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(obClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (T) unmarshaller.unmarshal(new StringReader(strXml));
}
```

## Understand and Work with xml
- XML (Extensible Markup Language) is a tree-structured text format for representing data.

#
###  1.XML Declaration (optional but recommended)
```java
<?xml version="1.0" encoding="UTF-8"?>
```

### 2.Root Element (mandatory)
- Every XML document must have exactly one root element that encloses all others.
```xml
<student>
    <!-- XML Body-->
</student>
```
- With Java Entity:
```java
@XmlRootElement(name = "student");
public class Student {}
```

#
### 3.Elements (Tags)
- Marked with opening `<tag>` and closing `</tag>`.
- Can contain text, attributes, or other elements.
```xml
<student>
    <name>Alan</name><!-- Element(Tags)-->
</student>
```
```java
    @XmlElement
    private String name;
```

#
### 4. Attributes
- Provide extra information about an element.
- Always inside the opening tag.
```xml
<student id="1" status="active">
    <name>Alice</name>
</student>
```

- `@XmlAttribute`
```java
@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

    @XmlAttribute
    private Integer id;

    @XmlAttribute
    private Boolean status;

    @XmlElement
    private String name;
}
```

#
### 5. Text Content
- The actual data inside an element.
```xml
<email>
    alice@example.com<!-- Text Content-->
</email>
```

### 6. Comments
- For documentation, ignored by parsers.
```xml
<!-- This is a comment -->
```

### 7. Nested (Hierarchical) Structure
- Elements can be inside other elements.
```xml
<student id="1" status="true">
    <name>alice@example.com</name>
    <email>Alice</email>
    <address id="1">
        <city>Ha Noi</city>
    </address>
</student>
```

```java
@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

    @XmlAttribute
    private Integer id;

    @XmlAttribute
    private Boolean status;

    @XmlElement
    private String name;

    @XmlElement
    private String email;

    @XmlElement(name = "address")
    private Address address;
}

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
    @XmlAttribute(name = "id")
    private Long id;

    @XmlElement
    private String city;
}
```

#
### 8. Empty Elements
- Can be self-closing.
```xml
<document>
    <linebreak/>
</document>
```

### 9. CDATA Sections
- Used if you want raw text without being parsed.
- JAXB does not support CDATA directly via annotation.

```xml
<student>
    <rawText>&lt;![CDATA[IntelliJ IDEA Community Edition 2022.2.3\lib\idea_rt.jar=63574:C:\Program]]&gt;</rawText>
</student>
```

### 10. Namespaces (for avoiding conflicts)
- Helps when mixing XML vocabularies.
```xml
<book xmlns:h="http://www.w3.org/TR/html4/">
    <h:title>XML Guide</h:title>
</book>
```

```xml
<ns2:student id="1" status="true" xmlns:ns2="http://example.com/student">
</ns2:student>
```
```java
@XmlRootElement(
        name = "student",
        namespace = "http://example.com/student"
)
public class Student {
}
```