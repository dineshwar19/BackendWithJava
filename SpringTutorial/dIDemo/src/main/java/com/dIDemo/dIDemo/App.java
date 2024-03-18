package com.dIDemo.dIDemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
       ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
       Demo demo = (Demo) context.getBean("demo");
       demo.doSomeThing();
//       demo.setNote(4);
       System.out.println(demo.getNote());
    }
}
