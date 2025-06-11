package clazz.interface_.default_method.case2;

//Both Interfaces Have the Same Abstract Method
// No conflict occurs because MyClass implements the method explicitly
interface InterfaceA {
    void show(); // Abstract method
}

interface InterfaceB {
    void show(); // Abstract method
}

// Class implements both interfaces
class MyClass implements InterfaceA, InterfaceB {
    public void show() { // Must provide implementation
        System.out.println("Implemented show() method");
    }
}

class Main {
    public static void main(String[] args) {
        MyClass obj = new MyClass();
        obj.show(); // Calls implemented method
    }
}