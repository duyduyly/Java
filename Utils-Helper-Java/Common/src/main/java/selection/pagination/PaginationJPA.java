package selection.pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import selection.pagination.model.PaginationModel;
import selection.pagination.model.Response;
import selection.pagination.model.entity.Student;
import selection.pagination.model.repository.StudentRepository;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.stream.Collectors;

//need create Spring boot or config JPA to Run
public class PaginationJPA {


    @Autowired
    private StudentRepository studentRepository;

    public Specification<Student> filter(String name, int old, PaginationJava8.Sort sort) {
        return (root, query, builder) -> {
            Predicate predicate;

            if (sort.equals(PaginationJava8.Sort.DESC)) {
                predicate = query.orderBy(builder.desc(root.get("id"))).getRestriction();
            } else {
                predicate = query.orderBy(builder.asc(root.get("id"))).getRestriction();
            }

            if (StringUtils.hasText(name)) {
                Predicate filterByName = builder.like(root.get("name"), "%" + name + "%");
                predicate = builder.and(filterByName);
            }
            if (old != 0) {
                Predicate filterByOld = builder.equal(root.get("old"), old);
                predicate = builder.and(filterByOld);
            }

            return predicate;
        };
    }

    public Response pagination(String name, int old, int page, int itemsPerPage) {
        Pageable pageRequest = PageRequest.of(page - 1, itemsPerPage);

        Page<Student> studentPage = studentRepository.findAll(this.filter(name, old, PaginationJava8.Sort.DESC), pageRequest);
        List<Student> students = studentPage.getContent().stream().map(st -> Student.builder()
                .id(st.getId())
                .name(st.getName())
                .old(st.getOld())
                .clazz(st.getClazz())
                .build()
        ).collect(Collectors.toList());

        return Response.builder()
                .data(students)
                .pagination(PaginationModel.builder()
                        .page(page)
                        .itemPerPages(studentPage.getSize())
                        .total(studentPage.getTotalPages())
                        .totalItems(studentPage.getTotalElements())
                        .build())
                .code(200)
                .message("SUCCESS")
                .build();
    }
}
