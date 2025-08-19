package validate;

import Validate.Validate;
import model.entity.Entity;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class ValidateTest {

    private Validate validate;

    public ValidateTest() {
        validate = new Validate();
    }

    @Test
    public void _null_FilledEntity_Success() throws InvocationTargetException, IllegalAccessException {
        Entity entity = Entity.builder()
                .field1("1231")
                .field2(123)
                .field3(123D)
                .build();

        validate._null(entity);

    }

    @Test
    public void _null_Field1IsNull_ThrowException() {
        Entity entity = Entity.builder()
                .field1(null)
                .field2(123)
                .field3(123D)
                .build();

        Assert.assertThrows(Exception.class, () -> {
            validate._null(entity);
        });
    }

}
