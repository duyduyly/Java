package data.jpa.entity.audit_jpa_lifecycle.mockdata.entity;

import data.jpa.entity.audit_jpa_lifecycle.jpa_life_cycle.config.AuditListener;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Date;

@Slf4j
@Entity
@EntityListeners(AuditListener.class)
@Table(name = "book")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class MockData extends AuditEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;

    private String title;

    private Date publishDate;



}
