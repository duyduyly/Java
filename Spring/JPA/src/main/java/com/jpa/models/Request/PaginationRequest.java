package com.jpa.models.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationRequest {

    @JsonProperty("page_index")
    private int pageIndex;

    @JsonProperty("page_size")
    private int pageSize;
}
