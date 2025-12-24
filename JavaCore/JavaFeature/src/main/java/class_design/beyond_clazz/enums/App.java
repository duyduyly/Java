package class_design.beyond_clazz.enums;

public class App {
    public static void main(String[] args) {
        Compass compass = Compass.NORTH;
        compass.printInstruction();
        System.out.println(Compass.EAST.getInstruction());


        Compass2 compass2 = Compass2.SOUTH;
        System.out.println(compass2.getDirection());

    }
}
