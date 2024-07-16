package data.jpa.Enums;

import lombok.Getter;

@Getter
public enum FieldTypeEnums {
    TYPE_ONE(1, "Type one"),
    TYPE_TWO(2, "Type two"),
    TYPE_THREE(3, "Type three");


    private final int status;

    private final String name;

    FieldTypeEnums(int status, String name) {
        this.status = status;
        this.name = name;
    }
}
