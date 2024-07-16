package com.alan.audit_jpa_lifecycle.mockdata.repository;

import com.alan.audit_jpa_lifecycle.mockdata.entity.MockData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<MockData, Long> {

}
