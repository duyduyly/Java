package selection.pagination.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;

@Getter
@Setter
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private Integer code;

    private String message;

    @Builder.Default
    private Object data = Collections.emptyList();

    private Object errors;

    private PaginationModel pagination;
}

