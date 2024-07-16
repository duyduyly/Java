package data.jpa.entity.mapping.many_to_many.option_1;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "many1")
public class Many1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "many1_many2",
            joinColumns = @JoinColumn(name = "many1_id"),
            inverseJoinColumns = @JoinColumn(name = "many2_id"))
    Set<Many2> many2Set;

}
