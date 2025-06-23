import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class App {
    public static void main(String[] args) {

        helloWorld(new Random(), 23, 0);
    }

    private static void printFourthElement(int[] a){
        System.out.println(a[3]);
    }
    void readFirstByteFromFile(File fileName){
        FileInputStream file = null;
        try {
            file = new FileInputStream(fileName);
            byte x = (byte) file.read();
            System.out.println(x);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
