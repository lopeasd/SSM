package com.review.demo1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 *bean的定义，作用域，初始化执行
 * @author shixiaofei
 */
@Component
@Scope(value = "singleton")
public class Student {
	
	@Value(value="abe")
	private String name;

	/**
	 * 注解初始化方法，创建bean的时候会自动执行此方法。可以配置多个
	 * 相当于xml中的init-method
	 */
	@PostConstruct
	public void init(){
		System.out.println("this is init method from Student ...");
	}

	@PostConstruct
	public void init1(){
		System.out.println("this is init1 method from Student ...");
	}

	/**
	 * 注解销毁方法
	 * 相当于xml中的destroy-method
	 */
	@PreDestroy
	public void destory(){
		System.out.println("this is destory method from Student ...");
	}

	public void destory1(){
		System.out.println("this is destory1 method from Student ...");
	}

	@Override
	public String toString() {
		return "Student [name=" + name + "]";
	}
}
