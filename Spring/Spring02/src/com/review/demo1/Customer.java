package com.review.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * 懒加载，自动注入复杂类型
 * Autowired默认byType 可以使用Qualifier强制使用名称注入
 * 		required可以设置注入的值是否可以为null
 * Resource 默认byName
 *
 * 测试时，可以更改名字（@Repository(value="customer")），查看两者区别
 *
 * @author shixiaofei
 */
@Lazy
@Repository(value="customer")
@Scope("prototype")
public class Customer {

	@Autowired(required=false)@Qualifier(value="student")
	private Student student;
	@Override
	public String toString() {
		return "Customer [student=" + student + "]";
	}
}
