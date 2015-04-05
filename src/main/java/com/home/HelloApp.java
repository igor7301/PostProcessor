package com.home;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        HelloServiceInterface helloService = context.getBean("helloService", HelloServiceInterface.class);
        helloService.sayHello();

    }
}
