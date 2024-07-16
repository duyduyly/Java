package com.jpa.models.audit_jpa_lifecycle.mockdata.generateData;

import com.jpa.models.audit_jpa_lifecycle.mockdata.entity.BookEntity;
import com.jpa.repository.BookRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class GenerateData {

    @Autowired
    private BookRepository bookRepository;

    public Date between(Date startInclusive, Date endExclusive) {
        long startMillis = startInclusive.getTime();
        long endMillis = endExclusive.getTime();
        long randomMillisSinceEpoch = ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);

        return new Date(randomMillisSinceEpoch);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void run(){
        Faker faker = new Faker();
        List<BookEntity> bookEntities = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            BookEntity mockData = BookEntity.builder()
                    .author(faker.book().author())
                    .title(faker.book().title())
                    .publishDate(this.between(new Date("01/01/2000"),new Date()))
                    .build();
            bookEntities.add(mockData);
        }
        bookRepository.saveAll(bookEntities);
    }

}
