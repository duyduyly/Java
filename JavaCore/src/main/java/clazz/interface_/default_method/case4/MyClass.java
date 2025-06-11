package clazz.interface_.default_method.case4;

//Since InterfaceB has only an abstract method, MyClass must provide its own implementation.
//must provide implementation method because Interface B show method is abstract method
interface InterfaceA {
    default void show() {
        System.out.println("InterfaceA's show()");
    }
}

interface InterfaceB {
    void show(); // Abstract method
}

// Class implementing both interfaces
class MyClass implements InterfaceA, InterfaceB {
    public void show() { // Must provide implementation
        System.out.println("MyClass's show()");
    }
}

class Main {
    public static void main(String[] args) {
        MyClass obj = new MyClass();
        obj.show();
    }
}
