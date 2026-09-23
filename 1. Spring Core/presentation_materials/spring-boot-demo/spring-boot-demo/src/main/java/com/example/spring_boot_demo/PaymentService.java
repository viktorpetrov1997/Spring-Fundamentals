package com.example.spring_boot_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService
{
    private final UserService userService;

    @Autowired
    public PaymentService(UserService userService)
    {
        this.userService = userService;
    }

    public void pay()
    {
        System.out.println("PaymentService: Processing payment...");
        userService.showUser();
        System.out.println("PaymentService: Payment successful!");
    }
}
