package collections.sort.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<Person>();
        people.add(new Person("Alan", 25));
        people.add(new Person("Peter", 29));
        people.add(new Person("Z", 40));
        people.add(new Person("Hero", 50));

        System.out.println("Use Lambda: ");
        //sort by age
        Collections.sort(people, (p1, p2) -> p1.getAge() - p2.getAge());
        System.out.println(people);

        System.out.println("--------------------------------");
        //sort by name
        Collections.sort(people, (p1, p2) -> p1.getName().compareTo(p2.getName()));
        System.out.println(people);

        System.out.println("--------------------------------");
        System.out.println("Use comparing: ");
        // to sort by name
        Comparator<Person> c = Comparator.comparing(Person::getName);
        Collections.sort(people, c);
        System.out.println(people);


        System.out.println("--------------------------------");
        System.out.println("Use reversed: ");
        // to sort by name in reversed order
        Comparator<Person> c1 = Comparator.comparing(Person::getName).reversed();
        Collections.sort(people, c1);
        System.out.println(people);

        System.out.println("--------------------------------");
        System.out.println("Use thenComparingInt: ");
        // to sort by name and then by age (if names are the same)
        Comparator<Person> c2 =
                Comparator.comparing(Person::getName).thenComparingInt(Person::getAge);
        Collections.sort(people, c2);
        System.out.println(people);

    }
}
