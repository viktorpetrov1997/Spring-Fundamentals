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

		UserService userService = applicationContext.getBean(UserService.class);

		System.out.println(userService);
	}
}
