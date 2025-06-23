package class_design.beyond_clazz.records;

public class App {

    public static record Example(Long id, String name, int age){};

    public static record Example2(String firstName, String lastName, int id){

        //full
//        public Example2(String firstName, String lastName, int id) {
//            if (id < 10 || id > 1_000_000) throw new IllegalArgumentException();
//            this.firstName = firstName;
//            this.lastName = lastName;
//            this.id = id;
//        }

        //instead of full param in signature, you can write just {}, Compiler will generate and transmit input
        public Example2 {
            if (id < 10 || id > 1_000_000) throw new IllegalArgumentException();
        }
    };

    public static void main(String[] args) {
        var example = new Example(1L, "Alan", 25);
        var example2 = new Example(2L, "Alan02", 25);
        var example3 = new Example(1L, "Alan", 25);

        System.out.println(example.toString());
        System.out.println(example.equals(example2));
        System.out.println(example.equals(example3));


        var example2_2 = new Example2("Author", "Lan", 9);
    }
}
