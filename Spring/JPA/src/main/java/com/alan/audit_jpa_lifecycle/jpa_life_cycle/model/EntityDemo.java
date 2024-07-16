package com.alan.audit_jpa_lifecycle.jpa_life_cycle.model;

import com.alan.audit_jpa_lifecycle.jpa_life_cycle.config.AuditListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "entity_demo")
@EntityListeners(AuditListener.class)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EntityDemo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
}
