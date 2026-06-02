package com.example.spring_boot_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService
{
    private UserService userService;

    @Autowired
    public PaymentService(UserService userService)
    {
        this.userService = userService;
    }

    public void pay()
    {
        System.out.println("I am paying");
    }
}
