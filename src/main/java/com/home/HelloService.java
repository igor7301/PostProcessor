package com.home;

import org.springframework.stereotype.Component;

@Profiling
public class HelloService implements HelloServiceInterface{

    @RandomInt(min = 1, max = 10)
    private int myRandom;

    public HelloService() {
        System.out.println("constructor JAVA 1");
    }

    public void init() {
        System.out.println(myRandom);
        System.out.println("constructor SPRING 1");

    }

    public void sayHello() {
        System.out.println("hello world");
    }
}
