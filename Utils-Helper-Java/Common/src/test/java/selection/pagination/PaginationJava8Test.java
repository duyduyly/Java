package selection.pagination;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import selection.pagination.model.Response;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static config.CreateObject.faker;

public class PaginationJava8Test {

    private PaginationJava8 paginationJava8;

    public PaginationJava8Test() {
        paginationJava8 = new PaginationJava8();
    }

    @Test
    public void paginateWithJava8() throws JsonProcessingException {
        List<String> integers = Stream.generate(() -> faker().app().author())
                .limit(200)
                .collect(Collectors.toList());

        Response response = paginationJava8.paginateWithJava8(1, 30, integers, PaginationJava8.Sort.ASC);
        System.out.println(response);
    }
}
