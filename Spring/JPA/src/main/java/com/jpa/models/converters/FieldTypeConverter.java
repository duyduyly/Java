package com.jpa.models.converters;



import com.jpa.models.enums.FieldTypeEnums;

import javax.persistence.AttributeConverter;

public class FieldTypeConverter implements AttributeConverter<FieldTypeEnums, Integer> {
    @Override
    public Integer convertToDatabaseColumn(FieldTypeEnums attribute) {
        return null;
    }

    @Override
    public FieldTypeEnums convertToEntityAttribute(Integer dbData) {
        return null;
    }
}
