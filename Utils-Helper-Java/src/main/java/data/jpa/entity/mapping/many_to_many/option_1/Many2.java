package data.jpa.entity.mapping.many_to_many.option_1;

import javax.persistence.*;

@Entity
@Table(name = "many2")
public class Many2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
