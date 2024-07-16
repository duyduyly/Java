package data.jpa.entity.audit_jpa_lifecycle.jpa_life_cycle.repository;

import data.jpa.entity.audit_jpa_lifecycle.jpa_life_cycle.model.EntityDemo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityDemoRepository extends JpaRepository<EntityDemo, Long> {
}
