package data.jpa.converters;

import data.jpa.Enums.FieldTypeEnums;

import javax.naming.directory.Attributes;
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
