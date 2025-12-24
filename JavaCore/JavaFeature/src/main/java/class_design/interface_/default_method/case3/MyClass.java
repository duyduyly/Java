package class_design.interface_.default_method.case3;

//Both Interfaces Have the Same Default Method
//must provide implementation method in class, because Java do not know which default method
interface InterfaceA {
    default void show() {
        System.out.println("InterfaceA's show()");
    }
}

interface InterfaceB {
    default void show() {
        System.out.println("InterfaceB's show()");
    }
}

// Class implementing both interfaces
class MyClass implements InterfaceA, InterfaceB {
    // Must override to resolve conflict
    public void show() {
        System.out.println("MyClass's own implementation");
    }
}

class Main {
    public static void main(String[] args) {
        MyClass obj = new MyClass();
        obj.show();
    }
}
