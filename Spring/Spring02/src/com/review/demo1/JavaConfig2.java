package com.review.demo1;

import org.springframework.beans.factory.annotation.Value;

public class JavaConfig2 {

    @Value("javaConfig2Name")
    private String name;


    public void configMethod(){
        System.out.println("this is method from JavaConfig2");
    }

    @Override
    public String toString() {
        return "JavaConfig2{" +
                "name='" + name + '\'' +
                '}';
    }
}
