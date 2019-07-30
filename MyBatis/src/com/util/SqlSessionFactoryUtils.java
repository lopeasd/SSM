package com.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description 获取SqlSessionFactory的单例，SqlSession 的实例不是线程安全的，因此是不能被共享的
 * @Author shixiaofei
 * @Date 2019/7/29 15:19
 * @Version 1.0
 */
public class SqlSessionFactoryUtils {

    private static SqlSessionFactory sqlSessionFactory;

    private static InputStream inputStream;

    private SqlSessionFactoryUtils() {

    }

    static {
        try {
            // 创建SqlSessionFactoryBuilder对象
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            //使用mybatis自带的Resources获取资源(也可以使用Class或者ClassLoa的getResourceAsStream方法获取)
            inputStream = Resources.getResourceAsStream("traditionSqlMapConfig.xml");
            // 加载配置文件，创建SqlSessionFactory对象
            sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        } catch (IOException e) {
            if (inputStream == null) System.out.println("配置文件获取不到");
            else e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

}
