package clazz.interface_.static_method.case2;

//static method in interface do not override because show method belong interface
interface InterfaceA {
    static void show() {
        System.out.println("InterfaceA's static show()");
    }
}

interface InterfaceB {
    static void show() {
        System.out.println("InterfaceB's static show()");
    }
}

// Class implements both interfaces
class MyClass implements InterfaceA, InterfaceB {
    // No need to implement static methods, as they are not inherited
}

class Main {
    public static void main(String[] args) {
        // Calling static methods on the interface
        InterfaceA.show();  // Calls static method from InterfaceA
        InterfaceB.show();  // Calls static method from InterfaceB
    }
}

