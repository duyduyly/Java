package class_design.variables;

public class Variables {

    public static final int DEFAULT_OLD = 0; // Final must be assigned when you initialize
    public static int old; //Do not assign a value, for default value is O
    public static double amount; // Do not assign a value, for default value is OD
    public int old2;

    public static void main(String[] args) {
        Variables var = new Variables();
        var.demo();

        System.out.println("Static Final: " + DEFAULT_OLD + " Final must be assigned when you initialize");
        System.out.println("Int Static : " + old);
        System.out.println("Double Final: " + amount);
        System.out.println("Instance Value: " + var.old2);
    }


    // local variable, just use local
    public void demo() {
        final String name = "adsad";
//        name = "sadas"; // can not change if you use keyword final

        System.out.println("Final Name: " + name);
        String name2 = "sdsd";
        name2 = "asdasdad";

        int old3;//Must initialize value
//        System.out.println(old3);

        final int old4;//Must initialize value
//        System.out.println(old4);

        System.out.println("Name 2: " + name2);

    }

    public static final String sdsd(){

        return "asda";
    }
}
