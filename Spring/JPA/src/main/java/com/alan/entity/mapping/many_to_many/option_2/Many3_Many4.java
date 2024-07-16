package com.alan.entity.mapping.many_to_many.option_2;

import javax.persistence.*;

@Entity
public class Many3_Many4 {

    @EmbeddedId
    Many3_Many4Key id;

    @ManyToOne
    @MapsId("many3Id")
    @JoinColumn(name = "many3_id")
    Many3 many3;

    @ManyToOne
    @MapsId("many4Id")
    @JoinColumn(name = "many4_id")
    Many4 many4;
}
