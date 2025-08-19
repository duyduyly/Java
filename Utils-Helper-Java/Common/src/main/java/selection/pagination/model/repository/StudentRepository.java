package selection.pagination.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import selection.pagination.model.entity.Student;

public interface StudentRepository extends JpaRepository<Long, Student>, JpaSpecificationExecutor<Student> {
}
