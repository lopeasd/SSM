package com.review.test;

import org.springframework.beans.factory.annotation.Autowired;
import com.review.bean.User;

public class AnnoExample {
    @Autowired
   static User user;

    public static void main(String[] args) {
        user.method();
    }
}
