package org.example.client.model;

import lombok.*;
import org.example.server.model.Address;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Student {
    private int id;
    private String name;

    private Address address;
}
