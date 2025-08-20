package org.example.server.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "address")
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class Address {

    @XmlElement
    private int id;

    @XmlElement
    private String city;
}
