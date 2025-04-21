package clazz.beyond_clazz.sealed_.inteface_;


//can not implement Mammal through Eats, Just implement directly
public final class Dog implements Eats, Mammal {
    @Override
    public void eat() {

    }

    @Override
    public void breathe() {

    }
}
