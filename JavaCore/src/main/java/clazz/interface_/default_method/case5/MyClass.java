package clazz.interface_.default_method.case5;

//Since InterfaceB has only an abstract method, MyClass must provide its own implementation.
//must provide implementation method because Interface B show method is abstract method
interface InterfaceA {
     String showA();
     String showB();

     default void show() {
         System.out.println("Show A: "+showA() +" Show B: "+showB());
     }
}


// Class implementing both interfaces
class MyClass implements InterfaceA {
    @Override
    public String showA() {
        return "A";
    }

    @Override
    public String showB() {
        return "B";
    }


}

class Main {
    public static void main(String[] args) {
        MyClass obj = new MyClass();
        obj.show();
    }
}
