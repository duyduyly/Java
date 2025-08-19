package org.example;

import org.example.service.OrderProcessService;

public class Main {
   private static final OrderProcessService orderProcessService = new OrderProcessService();
    public static void main(String[] args) {
        orderProcessService.processOrder();
    }
}