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
                .stringField("1231")
                .intField(123)
                .doubleField(123D)
                .build();

        validate._null(entity);

    }

    @Test
    public void _null_Field1IsNull_ThrowException() {
        Entity entity = Entity.builder()
                .stringField(null)
                .intField(123)
                .doubleField(123D)
                .build();

        Assert.assertThrows(Exception.class, () -> {
            validate._null(entity);
        });
    }

}
