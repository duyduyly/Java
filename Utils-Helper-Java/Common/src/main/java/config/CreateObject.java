package config;

import com.github.javafaker.Faker;
import number.Decimal;

public class CreateObject {

    private Faker faker;

    private static Decimal decimal = new Decimal();

    public static Faker faker() {
        return new Faker();
    }

    public static Integer randomIntLimit(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static Long randomLongLimit(long min, long max) {
        return (long) ((Math.random() * (max - min)) + min);
    }

    public static Integer randomInt() {
        return randomIntLimit(0, 1000000);
    }

    public static Double randomDouble() {
        double decimalNumber = decimal.divideDecimal(Double.valueOf(randomIntLimit(0, 999)), 100D);
        double random = randomIntLimit(0, 100000);
        return decimal.multiplyDecimal(random, decimalNumber);
    }

    public static Long randomLong() {
        return randomLongLimit(0L, 999_999_999_999_999_999L);
    }

}
