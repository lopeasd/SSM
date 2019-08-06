package com.review.bean;

/**
 * 级联属性测试
 * @author shixiaofei
 */
public class Car {
    private int price;
    private String address;
    private String name;

    public Car(int price, String address, String name) {
        this.price = price;
        this.address = address;
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Car{" +
                "price=" + price +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

