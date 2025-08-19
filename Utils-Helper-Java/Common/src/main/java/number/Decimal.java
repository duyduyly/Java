package number;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
public class Decimal {

    /**
     * Why you must Method of BigDecimal to Divide two Double ?
     * Because when you use Double Divided a normal way then can result will be 0.10000000000001
     * when you divided two Double 1.1 and 10.1 for 100 or any Number you will see
     * Double d1 = (Double) (1.1/100) ; ==> 0.011000000000000001
     * Double d2 = (Double) (10.1/100) ; ==> 0.10099999999999999
     * <p>
     * Method divide of BigDecimal will Auto aground decimal
     */
    public Double divideDecimal(Double a, Double b) {
        BigDecimal result = BigDecimal.valueOf(a).divide(BigDecimal.valueOf(b), RoundingMode.HALF_UP);
        return result.doubleValue();
    }

    /**
     * when 1.1 then it also result 110.00000000000001
     * 1.1 * 100 = 110.00000000000001
     */
    public Double multiplyDecimal(Double a, Double b) {
        BigDecimal result = BigDecimal.valueOf(a).multiply(BigDecimal.valueOf(b));
        return result.doubleValue();
    }

    // Retrieve String number and handle divide
    // RoundingMode.HALF_UP is Rouding of decimal
    //
    public Double divideDecimal(String a, String b) {
        this.checkDoubleType(a, b);
        BigDecimal bigDecimalA = new BigDecimal(a);
        BigDecimal bigDecimalB = new BigDecimal(b);
        BigDecimal result = bigDecimalA.divide(bigDecimalB, RoundingMode.HALF_UP);
        return result.doubleValue();
    }

    public Double divideDecimal(String a, String b, int roundTo) {
        this.checkDoubleType(a, b);
        BigDecimal bigDecimalA = new BigDecimal(a);
        BigDecimal bigDecimalB = new BigDecimal(b);
        BigDecimal result = bigDecimalA.divide(bigDecimalB, roundTo, RoundingMode.HALF_UP);
        return result.doubleValue();
    }

    // retrieve String number and handle multiply
    public Double multiplyDecimal(String a, String b) {
        this.checkDoubleType(a, b);
        BigDecimal bigDecimalA = new BigDecimal(a);
        BigDecimal bigDecimalB = new BigDecimal(b);
        BigDecimal result = bigDecimalA.multiply(bigDecimalB);
        return result.doubleValue();
    }


    public Double parseDouble(Object ob) {
        try {
            if (Objects.isNull(ob)) return 0D;
            String value = String.valueOf(ob);
            value = value.isEmpty() ? "0" : value;
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            log.error("Value: (" + ob.toString() + ") is not Double value", e);
            return 0D;
        }
    }

    public void checkDoubleType(String... valueS) {
        if (Objects.nonNull(valueS)) {
            Arrays.asList(valueS).forEach(value -> {
                try {
                    if (Objects.nonNull(value)) {
                        Double.valueOf(value);
                    }
                } catch (NumberFormatException e) {
                    throw e;
                }
            });

        }
    }

    //compareTo = 0 is Equals
    //compareTo = 1 is a > b
    //compareTo = -1 is b > a
    public static boolean compareStringDecimal(String valueA, String valueB) {
        BigDecimal bigA = new BigDecimal(valueA);
        BigDecimal bigB = new BigDecimal(valueB);
        return bigA.compareTo(bigB) == 0;
    }

}
