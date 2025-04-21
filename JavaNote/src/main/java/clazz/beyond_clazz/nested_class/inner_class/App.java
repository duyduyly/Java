package clazz.beyond_clazz.nested_class.inner_class;

public class App {
    public static void main(String[] args) {
        Example1 student = Example1.builder().build();
        Example1.Address address = student.new Address();
        Example1.StudentLog studentLog = new Example1.StudentLog();

        studentLog.printTwice("Random Student");


        //initialize all class
        Example2 example2 = new Example2();
        Example2.B b = example2.new B();
        Example2.B.C c = b.new C();

        c.printT();
    }
}
