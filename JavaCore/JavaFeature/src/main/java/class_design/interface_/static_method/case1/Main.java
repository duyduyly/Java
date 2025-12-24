package class_design.interface_.static_method.case1;


interface Vehicle {
    static void maintenance() {  // Static method
        System.out.println("Performing vehicle maintenance.");
    }
}

public class Main {
    public static void main(String[] args) {
        Vehicle.maintenance();  // Calling static method directly
    }
}