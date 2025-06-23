package old_package.java8.inteface;

public class App {
    public static void main(String[] args) {
        DefaultAndStaticInterface overrideDefaultMethod = new DefaultAndStaticInterface1Impl();
        DefaultAndStaticInterface noOverrideDefaultMethod = new DefaultAndStaticInterface2Impl();


        overrideDefaultMethod.defaultMethod();
        //==> Default Method implemented Custom (overridden)

        noOverrideDefaultMethod.defaultMethod();
        //==> defaultMethod (still Default)
    }
}
