package com.jpa.repository;

import com.jpa.models.audit_jpa_lifecycle.mockdata.entity.BookEntity;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {
    public static Specification<BookEntity> containsTitle(String title) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }

    public static Specification<BookEntity> containsAuthor(String author) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("author"), "%" + author + "%");
    }
}
