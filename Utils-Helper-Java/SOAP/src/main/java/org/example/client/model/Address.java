package org.example.client.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "address")
@ToString
public class Address {
    private int id;
    private String city;
}
