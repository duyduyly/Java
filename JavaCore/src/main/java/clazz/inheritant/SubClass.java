package clazz.inheritant;

public class SubClass extends SupperClass{

    @Override
    public void normalMethod(){
        System.out.println("Normal Class In SubClass");
    }

//    @Override
//    public final void finalMethod() { // can not override
//        System.out.println("final Class In SubClass");
//    }

}
