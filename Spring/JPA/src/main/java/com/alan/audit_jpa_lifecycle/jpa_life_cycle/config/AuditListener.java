package com.alan.audit_jpa_lifecycle.jpa_life_cycle.config;

import com.alan.audit_jpa_lifecycle.jpa_life_cycle.model.EntityDemo;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
public class AuditListener {

    /*
    *
    *   before persist is called for a new entity – @PrePersist
        after persist is called for a new entity – @PostPersist
        before an entity is removed – @PreRemove
        after an entity has been deleted – @PostRemove
        before the update operation – @PreUpdate
        after an entity is updated – @PostUpdate
        after an entity has been loaded – @PostLoad
        *
        * and embed into entity
    * */

    @PrePersist
    public void PrePersist(EntityDemo entityDemo) {
        entityDemo.setFullName(entityDemo.getFirstName()+entityDemo.getLastName());
        log.info("PrePersist");
    }

    @PostPersist
    public void PostPersist(EntityDemo entityDemo) {
        System.err.println(entityDemo.getFullName());
        log.info("PostPersist");

    }

    @PreRemove
    public void PreRemove(EntityDemo entityDemo) {
        log.info("PreRemove");


    }

    @PostRemove
    public void PostRemove(EntityDemo entityDemo) {
        log.info("PostRemove");


    }

    @PreUpdate
    public void PreUpdate(EntityDemo entityDemo) {
        log.info("PreUpdate");


    }

    @PostUpdate
    public void PostUpdate(EntityDemo entityDemo) {
        log.info("PostUpdate");


    }

    @PostLoad
    public void PostLoad(EntityDemo entityDemo) {
        log.info("PostLoad");


    }
}
