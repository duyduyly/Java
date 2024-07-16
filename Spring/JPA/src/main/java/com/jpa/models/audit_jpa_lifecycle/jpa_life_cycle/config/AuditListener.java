package com.jpa.models.audit_jpa_lifecycle.jpa_life_cycle.config;

import com.jpa.models.audit_jpa_lifecycle.mockdata.entity.BaseEntity;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Date;

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
    public void PrePersist(BaseEntity baseEntity) {
        baseEntity.setCreatedAt(new Date());
        log.info("PrePersist");
    }

    @PostPersist
    public void PostPersist(BaseEntity baseEntity) {
//        System.err.println(entityDemo.getFullName());
        log.info("PostPersist");

    }

    @PreRemove
    public void PreRemove(BaseEntity baseEntity) {
        log.info("PreRemove");


    }

    @PostRemove
    public void PostRemove(BaseEntity baseEntity) {
        log.info("PostRemove");


    }

    @PreUpdate
    public void PreUpdate(BaseEntity baseEntity) {
        baseEntity.setUpdatedAt(new Date());
        log.info("PreUpdate");
    }

    @PostUpdate
    public void PostUpdate(BaseEntity baseEntity) {
        log.info("PostUpdate");


    }

    @PostLoad
    public void PostLoad(BaseEntity baseEntity) {
        log.info("PostLoad");


    }
}
