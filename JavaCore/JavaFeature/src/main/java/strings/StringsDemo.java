package strings;

public class StringsDemo {
    public static void main(String[] args) {
//        equalsAndEqualsIgnoreCase();

//        String name4 = "Alan\\tWalker";
//        System.out.println(name4);
//        System.out.println(name4.translateEscapes());

//        String str = "     John\n    D.\n   Wayne";
//        System.out.println("--");
//        System.out.println(str);
//        System.out.println("--");
//        System.out.println(str.indent(2));
//        System.out.println("--");
//        System.out.println(str.indent(-2));
//        System.out.println("--");
//        System.out.println(str.stripIndent());
//        System.out.println("--");

//        String name = " Alan Walker";
//        System.out.print(name.indent(0));
//        System.out.print(name.indent(10));
//        System.out.print("         Alan Walker".indent(-10));
//
//        System.out.println();
//        System.out.println("Println: ");
//        System.out.println(name.indent(0));
//        System.out.println(name.indent(10));
//        System.out.println("         Alan Walker".indent(-10));


//        System.out.println(name.contains("A"));
//        System.out.println(name.contains("a"));
//        System.out.println(name.contains("walker"));
//        System.out.println(name.contains("Walker"));

//        System.out.println(name.startsWith("Al")); //==> true
//        System.out.println(name.startsWith("al")); //==> false
//        System.out.println(name.endsWith("Walker")); //==> true
//        System.out.println(name.endsWith("walker")); //==> false
//        System.out.println(name.length());
//        System.out.println(name.charAt(2));
//        System.out.println(name.charAt(12));


        //String poolExample
        String name = "Alan"; //value store in String pool, for value create compile time
        String name2 = "Alan   ".trim(); //not store in String pool, for .trim() is evaluated at runtime
        String name3 = new String("Alan"); // keyword new instant object
        String name4 = new String("Alan").intern(); //push Alan into String pool
        String name5 = new String("Alan").intern();
        String name6 = "Al"+"an"; //concatenation is done in the compile-time

        System.out.println(name == name2);
        System.out.println(name == name3);
        System.out.println(name == name4);
        System.out.println(name == name5);
        System.out.println(name == name6);
    }

    public static void concatString(){
        String a = "1";
        String b = "2";
        String a1 = "a";
        int b1 = 2;

        System.out.println("Both is Number: "+a + b); //String is Number
        System.out.println("String plus number: "+a1 + b1); // String plus number
        System.out.println("String plus String: "+ "Alan " + "Walker"); //String plus String

        //notice: need check from left to right
        // if left is String all String
        System.out.println("a" + 1 + 2); //==> a12

        // if left is number and after that is number, can plus and then addition to String
        System.out.println(1 + 2 + "a"); //==> 3a

        //if you use parentheses for two number and first is String
        //plus in parentheses first and then addition with String
        System.out.println("a" + (1+2));

        System.out.println("a"+null);
    }

    public static void equalsAndEqualsIgnoreCase(){
        String name1 = "Alan Walker";
        String name2 = "ALAN WALKER";
        String name3 = "alan walker";

        System.out.println(name1.equals(name2));
        System.out.println(name1.equals(name3));

        System.out.println(name1.equalsIgnoreCase(name2));
        System.out.println(name2.equalsIgnoreCase(name3));
    }
}
