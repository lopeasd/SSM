package com.review.bean;

/**
 * setter方式注入
 * @author shixiaofei
 */
public class User{
    private String name;



    public void setName(String name) {
        this.name = name;
    }


    public void method(){
        System.out.println("this is user method...");
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
