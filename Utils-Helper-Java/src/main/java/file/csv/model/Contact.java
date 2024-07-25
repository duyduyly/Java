package file.csv.model;

import lombok.*;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Contact {
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private String zip;


}
