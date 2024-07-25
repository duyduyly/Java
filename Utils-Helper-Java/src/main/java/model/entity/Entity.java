package model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Entity {
    private String stringField;
    private Integer intField;
    private Double doubleField;
    private Long longField;
    private Date dateFiled;
    private Float floatField;
}
