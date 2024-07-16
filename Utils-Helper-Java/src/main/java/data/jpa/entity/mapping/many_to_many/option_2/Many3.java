package data.jpa.entity.mapping.many_to_many.option_2;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "many3")
public class Many3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "many3")
    Set<Many3_Many4> many3Many4s;
}
