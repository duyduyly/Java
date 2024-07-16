package com.jpa.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    private Object data;

    @JsonProperty("page")
    private PaginationResponse paginationResponse;


    public PageResponse<Object> setPageResponse(Page<T> page) {
        return PageResponse.builder()
                .data(page.getContent())
                .paginationResponse(
                        PaginationResponse.builder()
                                .pageNumber(page.getNumber())
                                .pageSize(page.getSize())
                                .totalPages(page.getTotalPages())
                                .totalElements(page.getTotalElements())
                                .build()
                )
                .build();
    }

    @Data
    @Builder
    static
    class PaginationResponse {

        @JsonProperty("page_number")
        private int pageNumber;

        @JsonProperty("page_size")
        private int pageSize;

        @JsonProperty("total_pages")
        private int totalPages;

        @JsonProperty("total_elements")
        private long totalElements;
    }
}
