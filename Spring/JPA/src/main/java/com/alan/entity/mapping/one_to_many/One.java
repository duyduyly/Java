package com.alan.entity.mapping.one_to_many;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "one")
public class One {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "one")
    private Set<Many> manies;
}
