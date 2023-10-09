package selection.pagination.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationModel {

    @JsonProperty("page")
    private Integer page;

    private Integer total;

    @JsonProperty("items_per_page")
    private Integer itemPerPages;

    @JsonProperty("total_items")
    private Long totalItems;

    private Object data;
}
