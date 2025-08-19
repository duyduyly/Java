package org.example.server.model;

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
