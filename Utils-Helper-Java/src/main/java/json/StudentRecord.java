package json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudentRecord {
    private Long id;
    private String name;
    private Integer age;

    public static List<StudentRecord> generate(){
        List<StudentRecord> studentRecords = new ArrayList<>();
        studentRecords.add(new StudentRecord(1L, "Name", 25));
        studentRecords.add(new StudentRecord(2L, "Name2", 21));
        studentRecords.add(new StudentRecord(3L, "Name3", 33));
        return studentRecords;
    }
}
