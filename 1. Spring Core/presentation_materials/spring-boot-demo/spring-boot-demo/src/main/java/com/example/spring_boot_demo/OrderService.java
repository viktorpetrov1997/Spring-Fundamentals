package com.example.spring_boot_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService
{
    private final PaymentService paymentService;

    @Autowired
    public OrderService(PaymentService paymentService)
    {
        this.paymentService = paymentService;
    }

    public void sendOrder()
    {
        System.out.println("OrderService: Sending order...");
        paymentService.pay();
        System.out.println("OrderService: Order sent!");
    }
}
