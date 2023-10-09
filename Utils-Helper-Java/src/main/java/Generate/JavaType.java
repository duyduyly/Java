package Generate;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Getter
public enum JavaType {
    STRING(String.class.getTypeName()),
    INTEGER(Integer.class.getTypeName()),
    DOUBLE(Double.class.getTypeName()),
    FLOAT(Float.class.getTypeName()),
    ARRAYLIST(ArrayList.class.getTypeName()),
    BIG_DECIMAL(BigDecimal.class.getTypeName()),
    DATE(Date.class.getTypeName()),
    BOOLEAN(Boolean.class.getTypeName()),
    LONG(Long.class.getTypeName());

    private String value;

    JavaType(String value) {
        this.value = value;
    }

    public static JavaType convertStringValueToEnum(String value) {
        return Arrays.asList(JavaType.values()).stream()
                .filter(javaType -> javaType.getValue().equals(value))
                .findFirst().orElse(null);
    }
}
