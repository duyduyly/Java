package class_design.beyond_clazz.nested_class.static_class;


public class App {
    public static void main(String[] args) {
        //B is Static class can access directly to initialize
        Example.B b = new Example.B();
        b.print();


        //must initialize example 1 and then use instant example 1 to initialize A Class
        Example example1 = new Example();
        Example.A a = example1.new A();
        a.print();

    }
}
