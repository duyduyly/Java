package class_design.interface_.default_method.case1;

interface Vehicle {
    void start();  // Abstract method (must be implemented)

    default void stop() {  // Default method with implementation
        System.out.println("Vehicle is stopping...");
    }
}

class Car implements Vehicle {
    public void start() {
        System.out.println("Car is starting...");
    }
}

class Main {
    public static void main(String[] args) {
        Car myCar = new Car();
        myCar.start();
        myCar.stop();  // Calls the default method from Vehicle
    }
}
