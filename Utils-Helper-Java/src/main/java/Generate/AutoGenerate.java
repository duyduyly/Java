package Generate;

import config.CreateObject;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Objects;

@Slf4j
public class AutoGenerate {


    public String generateBuilderEntityForTest(Object ob) {
        if (Objects.isNull(ob)) return "";
        StringBuffer result = new StringBuffer();
        result.append(this.getClazzName(ob)).append(".builder()");

        Field[] fields = ob.getClass().getDeclaredFields();
        String key;
        for (Field field : fields) {
            String type = field.getType().getName();
            JavaType javaType = JavaType.convertStringValueToEnum(type);
            key = setValue(javaType);

            result.append(".").append(field.getName()).append("(").append(key).append(")");
        }
        result.append(".build();");

        log.info(result.toString());
        return result.toString();
    }

    private String setValue(JavaType javaType) {
        String key;
        switch (javaType) {
            case LONG:
                key = String.valueOf(CreateObject.randomLong());
                break;
            case BIG_DECIMAL:
                key = "BigDecimal.valueOf(\"" + CreateObject.randomDouble() + "\")";
                break;
            case DATE:
                key = "new Date()";
                break;
            case STRING:
                key = CreateObject.faker().random().hex();
                break;
            case BOOLEAN:
                key = "true";
                break;
            case INTEGER:
                key = String.valueOf(CreateObject.randomInt());
                break;
            case DOUBLE:
                key = String.valueOf(CreateObject.randomDouble());
                break;
            case ARRAYLIST:
                key = "new ArrayList<>()";
                break;
            default:
                key = "0";
                break;
        }
        return key;
    }

    private String getClazzName(Object ob) {
        String[] clazzArr = ob.getClass().getName().split("\\.");
        return clazzArr[clazzArr.length - 1];
    }
}
