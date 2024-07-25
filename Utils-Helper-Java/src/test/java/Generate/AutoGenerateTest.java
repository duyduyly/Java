package Generate;

import model.entity.Entity;
import org.junit.Test;

public class AutoGenerateTest {

    private final AutoGenerate autoGenerate;

    public AutoGenerateTest() {
        autoGenerate = new AutoGenerate();
    }

    @Test
    public void generateBuilderEntityForTest() {
        Entity entity = new Entity();
        String Builder = autoGenerate.generateBuilder(entity);
    }
}
