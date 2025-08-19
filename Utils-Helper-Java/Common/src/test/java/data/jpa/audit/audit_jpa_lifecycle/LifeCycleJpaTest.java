package data.jpa.audit.audit_jpa_lifecycle;


import data.jpa.entity.audit_jpa_lifecycle.jpa_life_cycle.model.EntityDemo;
import data.jpa.entity.audit_jpa_lifecycle.jpa_life_cycle.repository.EntityDemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LifeCycleJpaTest {

    @Autowired
    private EntityDemoRepository entityDemoRepository;


    @Test
    void lifeCycleOfJpa(){

        EntityDemo entityDemo = new EntityDemo();
        entityDemo.setFirstName("Duy ");
        entityDemo.setLastName(" Ly");

        entityDemoRepository.save(entityDemo);

        EntityDemo entityDemo1 = entityDemoRepository.findById(1L).get();
        entityDemo1.setLastName("Hss");

        entityDemoRepository.save(entityDemo1);
        entityDemoRepository.deleteById(1L);


    }
}
