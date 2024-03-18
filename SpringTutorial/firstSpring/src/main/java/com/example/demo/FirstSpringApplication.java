package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
@SpringBootApplication
public class FirstSpringApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(FirstSpringApplication.class, args);
		/*
		 ApplicationContext is an interface , that provides getBean method for retrieve an instance of a bean.
		 Demo run = context.getBean(Demo.class);, getBean assign object in runtime .
		 run.demo is method of Demo class , Dependency Injection achieved by ApplicationContext and getBean method.
		 */
		Demo run = context.getBean(Demo.class);
		run.demo();//it calls the demo method from Demo class
		
		
	}

}
