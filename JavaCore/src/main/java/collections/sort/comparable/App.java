package collections.sort.comparable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<Person>();
        people.add(new Person("Alan", 25));
        people.add(new Person("Peter", 29));
        people.add(new Person("Z", 40));
        people.add(new Person("Hero", 50));

        Collections.sort(people);
        System.out.println(people.toString());
    }
}
