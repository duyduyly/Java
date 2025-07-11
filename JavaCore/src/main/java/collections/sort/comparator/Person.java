package collections.sort.comparator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public String toString() {
        return "\n"+"{name='" + name + '\'' +","+
                " age=" + age + '}';
    }

}
