package org.example.service;

public class OrderProcessService {

    private static final NotificationService notificationService = new NotificationService();
    public void process(){
        //implement Process Order
        System.out.println("Process Order");
        notificationService.push();
    }
}
