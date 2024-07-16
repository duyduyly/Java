package com.jpa.models.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SortRequest {

    private String field;

    @JsonProperty("sort_type")
    private String sortType;
}
