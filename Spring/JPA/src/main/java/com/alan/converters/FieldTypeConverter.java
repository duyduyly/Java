package com.alan.converters;



import com.alan.Enums.FieldTypeEnums;

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
