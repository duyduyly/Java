package com.alan.entity;

import com.alan.Enums.FieldTypeEnums;
import com.alan.converters.FieldTypeConverter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "entity"
//        , uniqueConstraints = {@UniqueConstraint(columnNames = {"reference_no", "deleted_at"})} // use create the multiple Unique field
)
public class Entity extends BaseEntity {//extend base entity to entity have 4 common field, base on @MappedSuperclass to Map 4 field in Entity

    @Column
    @Convert(converter = FieldTypeConverter.class) // @Annotation to convert From String Enum To Int Enums when Data saved in Database
    private FieldTypeEnums fieldType;

    @Column(name = "isField")
    @ColumnDefault("0") // set default field
    private boolean isFiled = false;

}
