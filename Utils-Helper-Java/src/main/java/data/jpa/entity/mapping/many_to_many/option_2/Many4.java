package data.jpa.entity.mapping.many_to_many.option_2;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "many4")
public class Many4 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "many4")
    Set<Many3_Many4> many3Many4s;
}
