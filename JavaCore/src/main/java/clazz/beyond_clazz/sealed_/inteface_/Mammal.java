package clazz.beyond_clazz.sealed_.inteface_;

public sealed interface Mammal permits Cat, Eats, Dog {
    void breathe();
}
