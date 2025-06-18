package collections.sort.comparable;

public class Person implements Comparable<Person> {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //sort by age
//    @Override
//    public int compareTo(Person o) {
//        return this.age - o.age;
//    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}' +"\n";
    }

    //sort by name
    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name);
    }
}
