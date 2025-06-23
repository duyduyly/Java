package class_design.interface_.private_method;

interface Car {
    private static int calculateSpeed() {
        int speed = 70 * 20;
        return speed;
    }

    public default int getSpeed() {
        return calculateSpeed();
    }

    public default int getRecommendedSpeed() {
        return (int) (calculateSpeed() * 0.8);
    }
}

class MyClass implements Car {

}

public class Main {
    public static void main(String[] args) {
        MyClass obj = new MyClass();
        System.out.println(obj.getSpeed());
        System.out.println(obj.getRecommendedSpeed());
    }
}
