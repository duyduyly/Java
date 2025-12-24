package conversion;

public class Conversion {
    public static void main(String[] args) {
        //char to long
        long a = 'a'; // does not need cast

        //byte to short
        byte b = 12;
        short b2 =  b; // does not need cast

        // short to char
        short c1 = 50;
        char c2 = (char) c1; // need cast, Because their ranges are not compatible.


        //int to float
        int d1 = 20;
        float d2 = d1; // does not need cast

        //byte to char, short, int , long
        //==> no need cast because char, short, int and long data type have ranges than bigger byte data type
    }
}
