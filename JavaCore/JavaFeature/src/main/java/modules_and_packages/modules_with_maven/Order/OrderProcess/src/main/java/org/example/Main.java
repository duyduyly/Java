package modules_and_packages.modules_with_maven.Order.OrderProcess.src.main.java.org.example;

import org.example.service.OrderProcessService;

public class Main {

    private static final OrderProcessService orderProcessService = new OrderProcessService();
    public static void main(String[] args) {
        orderProcessService.process();
    }
}