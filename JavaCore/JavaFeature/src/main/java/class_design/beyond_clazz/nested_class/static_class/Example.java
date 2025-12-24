package class_design.beyond_clazz.nested_class.static_class;


public class Example {

    class A {
        private String city;
        private String state;
        private String zip;

        public void print() {
            System.out.println("Print A Class");
        }
    }

    static class B {
        private String city;
        private String state;
        private String zip;

        public void print() {
            System.out.println("Print B Class");
        }
    }

}
