package clazz.inheritant;

public class SupperClass {
    public static void staticMethod() {
        System.out.println("static method");
    }

    public static final void publicFinalMethod() { // declare, for static also can not override
        System.out.println("public final method");
    }

    public final void finalMethod() {// cannot override
        System.out.println("final method");
    }

    public void normalMethod() {
        System.out.println("normal method");
    }
}
