package com.review.demo1;

import org.springframework.beans.factory.annotation.Value;

public class JavaConfig1 {

    @Value("javaConfig1Name")
    private String name;


    public void configMethod(){
        System.out.println("this is method from JavaConfig1");
    }

    @Override
    public String toString() {
        return "JavaConfig1{" +
                "name='" + name + '\'' +
                '}';
    }
}
