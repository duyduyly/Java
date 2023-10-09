package number;

import org.junit.Assert;
import org.junit.Test;

public class DecimalTest {

    private Decimal decimal;

    public DecimalTest() {
        decimal = new Decimal();
    }

    @Test
    public void divideDecimal() {
        Double a = 1.1 / 100;
        Double b = 10.1 / 100;
        Double c = decimal.divideDecimal(1.1, 100.0);
        Double d = decimal.divideDecimal(10.1, 100.0);

        System.out.println(a);
        System.out.println(b);
        System.out.println("Use Method of BigDecimal: " + c);
        System.out.println("Use Method of BigDecimal: " + d);
    }

    @Test
    public void multiplyDecimal() {
        Double a = 1.1 * 100;
        Double c = decimal.multiplyDecimal(1.1, 100.0);
        System.out.println(a);
        System.out.println("Use Method of BigDecimal: " + c);
    }


    @Test
    public void multiplyDecimal_WithParamIsStringType() {
        String a = "232323.23214241241";
        String b = "232323.23214241241";
        Double result = decimal.multiplyDecimal(a, b);
        System.out.println(result);
    }

    @Test
    public void multiplyDecimal_WithParamIsStringType_And_ValueInvalid() {
        String a = "assas";
        String b = "232323.23214241241";

        Assert.assertThrows(RuntimeException.class, () -> {
            decimal.multiplyDecimal(a, b);
        });
    }

    @Test
    public void divideDecimal_WithParamIsStringType_Success() {
        String a = "232323.23214241241";
        String b = "232323.23214241241";
        Double result = decimal.divideDecimal(a, b);
        System.out.println(result);
    }

    @Test
    public void divideDecimal_WithParamIsStringType_AndRound12Number_Success() {
        String a = "232323.23214241241";
        String b = "23.2323141241";
        int round = 12;
        Double result = decimal.divideDecimal(a, b, round);
        int round2 = 12;
        Double result2 = decimal.divideDecimal(a, b, round2);
        System.out.println(result);
        System.out.println(result2);
    }

    @Test
    public void divideDecimal_WithParamIsStringType_And_ValueInvalid_ThrowRuntimeException() {
        String a = "assas";
        String b = "232323.23214241241";

        Assert.assertThrows(RuntimeException.class, () -> {
            decimal.divideDecimal(a, b);
        });
    }

    @Test
    public void checkType_ValueInvalid() {
        String a = "";
        String b = "232323.23214241241";

        Assert.assertThrows(RuntimeException.class, () -> {
            decimal.checkDoubleType(a, b);
        });
    }

    @Test
    public void checkType_ValueValid() {
        String a = "0";
        String b = "232323.23214241241";
        decimal.checkDoubleType(a, b);
    }

    @Test
    public void parseStringToDouble() {
        Object a = "0";
        Object b = "232323.23214241241";
        Object c = null;
        Object d = "asdasdasdas";

        System.out.println("parseStringToDouble(): ");
        System.out.println(decimal.parseDouble(a));
        System.out.println(decimal.parseDouble(b));
        System.out.println(decimal.parseDouble(c));
        System.out.println(decimal.parseDouble(d));
        System.out.println();
    }


    @Test
    public void compareStringDecimal() {
        String a = "123";
        String b = "123.0000";
        boolean result = Decimal.compareStringDecimal(a, b);
        System.out.println(result);
    }
}
