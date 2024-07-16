package data.jpa.entity.mapping.one_to_one;

import javax.persistence.*;

@Entity
@Table(name = "one2")
public class One2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private One1 one1;
}
