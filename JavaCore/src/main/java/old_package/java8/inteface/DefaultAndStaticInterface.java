package old_package.java8.inteface;

public interface DefaultAndStaticInterface {

    //Always must write Override Method on Impl class
    String handleFirstName(String string);

    //can override and also can do not need override because we implemented method on an interface
    default void defaultMethod(){
        System.out.println("defaultMethod");
    }

    //Cannot Override and we can call direct DefaultAndStaticInterface.staticInterface()
    static boolean staticInterface() {
        System.out.println("staticInterface");

        return true;
    }
}
