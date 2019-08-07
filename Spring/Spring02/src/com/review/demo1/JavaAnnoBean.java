package com.review.demo1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaAnnoBean {

    @Bean
    public static JavaConfig1 demo1(){
        return new JavaConfig1();
    }

    @Bean
    public  JavaConfig2  demo2(){
        return new JavaConfig2();
    }

}
