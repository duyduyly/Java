package com.jpa.models.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter@Getter
@Builder
@NoArgsConstructor@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilterRequest {
    private String title;
    private String author;

    @JsonProperty("pagination_request")
    private PaginationRequest paginationRequest;

    @JsonProperty("sort_request")
    private SortRequest sortRequest;
}
