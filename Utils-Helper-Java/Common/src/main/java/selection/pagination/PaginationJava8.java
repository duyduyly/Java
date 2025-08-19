package selection.pagination;

import com.fasterxml.jackson.core.JsonProcessingException;
import selection.pagination.model.PaginationModel;
import selection.pagination.model.Response;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PaginationJava8 {

    public Response paginateWithJava8(int currentPage, int limit, List<String> data, Sort sort) throws JsonProcessingException {
        int skip = currentPage * limit;
        limit = currentPage == 0 ? limit : limit * currentPage;

        List<String> collect = data.stream()
                .skip(skip)
                .limit(limit)
                .sorted(sort.equals(Sort.ASC) ?
                        Comparator.comparing(Function.identity()) :
                        Comparator.reverseOrder())
                .collect(Collectors.toList());

        PaginationModel pagination = PaginationModel.builder()
                .page(currentPage + 1)
                .itemPerPages(limit)
                .totalItems((long) ((data.size() / limit) + 1))
                .total(data.size())
                .data(collect)
                .build();

        return Response.builder()
                .code(200)
                .message("SUCCESS")
                .data(collect)
                .pagination(pagination)
                .build();
    }

    public enum Sort {
        DESC,
        ASC
    }
}
