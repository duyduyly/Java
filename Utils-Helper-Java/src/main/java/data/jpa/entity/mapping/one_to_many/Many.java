package data.jpa.entity.mapping.one_to_many;

import javax.persistence.*;

@Entity
@Table(name = "many")
public class Many {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "one_id", nullable = false)
    private One one;

}
