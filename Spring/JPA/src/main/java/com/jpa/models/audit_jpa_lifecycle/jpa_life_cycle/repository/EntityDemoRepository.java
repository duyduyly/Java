package com.jpa.models.audit_jpa_lifecycle.jpa_life_cycle.repository;

import com.jpa.models.audit_jpa_lifecycle.jpa_life_cycle.model.EntityDemo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityDemoRepository extends JpaRepository<EntityDemo, Long> {
}
