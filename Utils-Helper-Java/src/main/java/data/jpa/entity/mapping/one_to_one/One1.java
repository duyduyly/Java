package data.jpa.entity.mapping.one_to_one;

import javax.persistence.*;

@Entity
@Table(name = "one1")
public class One1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "one1")
    private One2 one2;
}
