package com.example.spring_boot_demo;

import org.springframework.stereotype.Service;

@Service
public class UserService
{
    public void showUser()
    {
        System.out.println("UserService: User is John");
    }
}
