package org.example.service;

public class OrderProcessService {

    private final NotificationService notificationService = new NotificationService(); // call notification from service

    public void processOrder(){
        System.out.println("Process Order");
        notificationService.push();
    }
}
