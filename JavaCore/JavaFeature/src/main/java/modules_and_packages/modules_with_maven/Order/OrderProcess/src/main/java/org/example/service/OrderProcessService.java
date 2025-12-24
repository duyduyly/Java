package modules_and_packages.modules_with_maven.Order.OrderProcess.src.main.java.org.example.service;

public class OrderProcessService {

    private static final NotificationService notificationService = new NotificationService();
    public void process(){
        //implement Process Order
        System.out.println("Process Order");
        notificationService.push();
    }
}
