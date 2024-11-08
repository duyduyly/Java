package java8.inteface;

public class DefaultAndStaticInterface1Impl implements DefaultAndStaticInterface {
    @Override
    public String handleFirstName(String string) {
        return "Handle Fist Name";
    }

    @Override
    public void defaultMethod() {
        System.out.println("Default Method implemented Custom");
    }
}
