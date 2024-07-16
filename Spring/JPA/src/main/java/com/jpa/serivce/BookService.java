package com.jpa.serivce;

import com.github.javafaker.Faker;
import com.jpa.models.Request.FilterRequest;
import com.jpa.models.Request.PaginationRequest;
import com.jpa.models.Request.SortRequest;
import com.jpa.repository.BookSpecification;
import com.jpa.models.audit_jpa_lifecycle.mockdata.entity.BookEntity;
import com.jpa.repository.BookRepository;
import com.jpa.models.response.PageResponse;
import com.jpa.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private Faker faker;

    @Autowired
    private RandomUtils randomUtils;

    public PageResponse<Object> getAllBook(FilterRequest filterRequest) {

        int pageIndex = 1;
        int pageSize = 10;
        String author = "";

        if (Objects.nonNull(filterRequest)) {
            if (Objects.nonNull(filterRequest.getAuthor())) {
                author = filterRequest.getAuthor();
            }
            PaginationRequest paginationRequest = filterRequest.getPaginationRequest();
            if (Objects.nonNull(paginationRequest)) {
                if (paginationRequest.getPageIndex() > 0 && paginationRequest.getPageSize() > 0) {
                    pageIndex = paginationRequest.getPageIndex();
                    pageSize = paginationRequest.getPageSize();
                }
            }
        }
        Sort sort = this.sort(filterRequest.getSortRequest());
        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);
        Page<BookEntity> page = author.trim().isEmpty() ?
                bookRepository.findAll(pageable) :
                bookRepository.findByAuthorContaining(author, pageable);
        PageResponse<BookEntity> pageResponse = new PageResponse<>();
        return pageResponse.setPageResponse(page);
    }

    public PageResponse<Object> getAllBookWithSpec(FilterRequest filterRequest) {

        int pageIndex = 1;
        int pageSize = 10;
        String author = "";
        String title = "";

        if (Objects.nonNull(filterRequest)) {
            if (Objects.nonNull(filterRequest.getAuthor())) {
                author = filterRequest.getAuthor();
            }
            if (Objects.nonNull((filterRequest.getTitle()))) {
                title = filterRequest.getTitle();
            }
            PaginationRequest paginationRequest = filterRequest.getPaginationRequest();
            if (Objects.nonNull(paginationRequest)) {
                if (paginationRequest.getPageIndex() > 0 && paginationRequest.getPageSize() > 0) {
                    pageIndex = paginationRequest.getPageIndex();
                    pageSize = paginationRequest.getPageSize();
                }
            }
        }
        Sort sort = this.sort(filterRequest.getSortRequest());
        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);


        Specification<BookEntity> search = this.search(author, title);

        Page<BookEntity> page = bookRepository.findAll(search, pageable);
        PageResponse<BookEntity> pageResponse = new PageResponse<>();
        return pageResponse.setPageResponse(page);
    }

    private Sort sort(SortRequest sortRequest) {
        Sort sort = null;
        String field = "createdBy";
        String sortType = "";
        if (Objects.nonNull(sortRequest)) {
            field = sortRequest.getField();
            sortType = sortRequest.getSortType();
        }
        boolean isDesc = sortType.equalsIgnoreCase("DESC");
        sort = isDesc ? Sort.by(field).descending() : Sort.by(field).ascending();
        return sort;
    }

    private Specification<BookEntity> search(String author, String title) {
        Specification<BookEntity> spec = Specification.where(null);
        if (!author.isEmpty()) {
            spec = spec.and(BookSpecification.containsTitle(author));
        }
        if (!title.isEmpty()) {
            spec = spec.and(BookSpecification.containsAuthor(title));
        }
        return spec;
    }

    public void addAll() {
        List<BookEntity> bookEntities = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            BookEntity mockData = BookEntity.builder()
                    .author(faker.book().author())
                    .title(faker.book().title())
                    .publishDate(randomUtils.between(new Date("01/01/2000"), new Date()))
                    .build();
            bookEntities.add(mockData);
        }
        bookRepository.saveAll(bookEntities);
    }

}
