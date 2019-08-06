package com.review.bean;

/**
 * 级联属性测试
 * @author shixiaofei
 *
 */
public class Person {

    private String username;
    private int age;
    private Car car;

    public Person(String username, int age, Car car) {
        this.username = username;
        this.age = age;
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    @Override
    public String toString() {
        return "Person{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", car=" + car +
                '}';
    }
}
