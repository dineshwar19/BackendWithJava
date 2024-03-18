package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // it is used to indicate that a class is bean.
public class Demo {
	@Autowired //  is used for automatic dependency injection and eliminates the need for manual wiring of beans
	AutowiredClass auto; // just declare the variable for AutowiredClass . not manually assign object , it is done by spring itself by @Autowired 
	public void demo() {
		System.out.println("running..");
		auto.demo1();//it calls the demo1() from AutowiredClass.
	}
}
