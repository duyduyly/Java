import clazz.beyond_clazz.enums.Compass2;

import java.util.Random;

public class App {
    public static void main(String[] args) {

        System.out.println(Compass2.SOUTH.getDirection());

    }

    private static void helloWorld(Random rand, int value, int retry) {
        int max = 1000, min = 10;
        int intRan = rand.nextInt(max - min + 1) + min;

        if (value == intRan) {
            System.out.println("Hello world");
            System.out.println("Retry: " + retry);
        } else {
            System.out.println("running...");
            helloWorld(rand, value, retry + 1);
        }
    }

}
