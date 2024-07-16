package com.jpa.repository;

import com.jpa.models.audit_jpa_lifecycle.mockdata.entity.BookEntity;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long>, PagingAndSortingRepository<BookEntity, Long>, JpaSpecificationExecutor<BookEntity> {

    Page<BookEntity> findByAuthorContaining(String author,Pageable pageable);

}
