package com.example.spring_boot_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootDemoApplication
{
	public static void main(String[] args)
	{
		ApplicationContext applicationContext = SpringApplication.run(SpringBootDemoApplication.class, args);

		OrderService orderService = applicationContext.getBean(OrderService.class);

		orderService.sendOrder();
	}
}
