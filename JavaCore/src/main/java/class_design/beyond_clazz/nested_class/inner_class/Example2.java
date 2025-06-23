package class_design.beyond_clazz.nested_class.inner_class;

public class Example2 {
    private int t = 1;
    class B{
        private int t = 2;
        class C{
            private int t = 3;
            public void printT(){
                System.out.println(t);
                System.out.println(this.t);
                System.out.println(B.this.t);
                System.out.println(Example2.this.t);
            }
        }
    }
}
