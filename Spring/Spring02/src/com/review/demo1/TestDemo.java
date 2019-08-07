package com.review.demo1;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试属性是否注入成功
 * @author shixiaofei
 */
@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestDemo {



    @Resource(name = "customer")
    private Customer customer1;

    @Autowired(required = false)
    private Customer customer2;

    @Autowired
    private Student student;

    @Resource
    private Student student2;

    @Autowired
    private JavaConfig1 javaConfig1;

    @Test
    public void demo(){
        System.out.println(customer1);
        System.out.println(customer2);
        System.out.println("scope=prototype"+(customer1==customer2));

        System.out.println(student);
        System.out.println(student2);
        System.out.println("scope=singleton:"+(student==student2));

        System.out.println(javaConfig1);

    }




}
