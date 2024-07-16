package com.jpa.audit_jpa_lifecycle;

import com.jpa.models.audit_jpa_lifecycle.mockdata.entity.BookEntity;
import com.jpa.repository.BookRepository;
import com.jpa.helper.TestHelper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class AuditServiceTest {

    @Autowired
    private BookRepository bookRepository;

    @AfterEach
    void afterEach() {
        bookRepository.deleteById(1L);
    }

    @Test
    void auditCreatedDate_AndCreatedBy() {
        Faker faker = new Faker();
        BookEntity mockData = BookEntity.builder()
                .id(1L)
                .author(faker.book().author())
                .title(faker.book().title())
                .publishDate(TestHelper.between(new Date("01/01/2000"), new Date()))
                .build();
        bookRepository.save(mockData);

        BookEntity mockData1 = bookRepository.findById(1L).get();
        System.err.println(mockData1.getTitle());
        Assertions.assertNotNull(mockData1.getCreatedBy());
        Assertions.assertNotNull(mockData1.getCreatedAt());
    }


    @Test
    void auditLastModifyAt_And_LastModifyBy() {
        Faker faker = new Faker();
        BookEntity mockData = BookEntity.builder()
                .id(1L)
                .author(faker.book().author())
                .title(faker.book().title())
                .publishDate(TestHelper.between(new Date("01/01/2000"), new Date()))
                .build();
        bookRepository.save(mockData);

        BookEntity beforeChange = bookRepository.findById(1L).get();
        beforeChange.setTitle("ChangeTitle");
        bookRepository.save(beforeChange);
        BookEntity afterChange = bookRepository.findById(1L).get();

        // it will not same because UpdatedBy is random when update
        Assertions.assertNotEquals(beforeChange.getUpdatedAt(), afterChange.getUpdatedAt());
        Assertions.assertNotEquals(beforeChange.getUpdatedBy(), afterChange.getUpdatedBy());
    }
}
