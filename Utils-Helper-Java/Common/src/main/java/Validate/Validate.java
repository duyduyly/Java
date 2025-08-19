package Validate;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

@Slf4j
public class Validate {

    public void _null(Object ob) throws InvocationTargetException, IllegalAccessException {
        try {
            Method[] methods = ob.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().startsWith("get")) {
                    if (Objects.isNull(method.invoke(ob))) {
                        String fieldName = method.getName().replace("get", "");
                        log.error(fieldName);
                        throw new RuntimeException(fieldName);
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
