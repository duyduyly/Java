package lambdas_streams.lambdas.lamdas;

//an interface which has exactly one abstract method
//function interface can be annotated with @FunctionalInterface
//Java provides many pre-defined functional interface
// Supplier, Consumer, Predicate, Functional, etc.
@FunctionalInterface
public interface MyInterface {
    public int calculate(int a, int b);
}


class Main {
    public static void main(String[] args) {
        MyInterface myInterfaceA = (a, b) -> a + b;
        System.out.println(myInterfaceA.calculate(10, 20));
    }
}
