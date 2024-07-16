package com.alan.entity.mapping.many_to_many.option_2;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Many3_Many4Key implements Serializable {
    @Column(name = "many3_id")
    Long many3Id;

    @Column(name = "many4_id")
    Long many4Id;
}
