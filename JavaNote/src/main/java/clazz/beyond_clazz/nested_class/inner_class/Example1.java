package clazz.beyond_clazz.nested_class.inner_class;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Example1 {

    private Long id;
    private String name;
    private Address address;


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    //Cannot use @Builder within 'inner class' that is Non-static
    class Address {
        private String city;
        private String state;
        private String zip;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    //can use builder
    static class Address2 {
        private String city;
        private String state;
        private String zip;
    }

    static class StudentLog{
        public static void printOut(String studentName){
            System.out.println(studentName);
        }

        public void printTwice(String studentName){
            printOut(studentName);
            printOut(studentName);
        }
    }
}
