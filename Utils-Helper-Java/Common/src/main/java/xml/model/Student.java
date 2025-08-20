package xml.model;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@XmlRootElement(
        name = "student",
        namespace = "http://example.com/student"
)
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

    private String linebreak;

    @XmlElement
    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String rawText;
}

