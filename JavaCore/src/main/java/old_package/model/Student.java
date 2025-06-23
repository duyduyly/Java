package old_package.model;

import com.github.javafaker.Faker;
import old_package.config.JavaConfig;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Student {
    private String name;
    private String lastName;
    private int age;
    private String fullName;

    public List<Student> generateStudent(int total) {
        Faker faker = JavaConfig.faker;
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            Student build = Student.builder()
                    .name(faker.artist().name())
                    .lastName(faker.artist().name())
                    .age(randomAge())
                    .build();
            build.setFullName(build.getName().concat(" ").concat(build.getLastName()));
            students.add(build);
        }
        return students;
    }

    private int randomAge() {
        Random rand = new Random();
        int max = 110, min = 10;
        return rand.nextInt(max - min + 1) + min;
    }
}
