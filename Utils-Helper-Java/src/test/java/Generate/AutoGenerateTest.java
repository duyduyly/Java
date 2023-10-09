package Generate;

import model.entity.Entity;
import org.junit.Test;

public class AutoGenerateTest {

    private AutoGenerate autoGenerate;

    public AutoGenerateTest() {
        autoGenerate = new AutoGenerate();
    }

    @Test
    public void generateBuilderEntityForTest() {
        Entity entity = new Entity();
        autoGenerate.generateBuilderEntityForTest(entity);
    }
}
