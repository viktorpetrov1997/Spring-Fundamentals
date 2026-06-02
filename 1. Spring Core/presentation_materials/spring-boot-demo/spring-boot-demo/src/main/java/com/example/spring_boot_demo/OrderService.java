package com.example.spring_boot_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService
{
    private PaymentService paymentService;

    @Autowired
    public OrderService(PaymentService paymentService)
    {
        this.paymentService = paymentService;
    }

    public void sendOrder()
    {
        System.out.println("Sending order to");
    }
}
