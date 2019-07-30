package com.test;

import com.review.bean.Order;
import com.review.mapper.OrderMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description mapper映射文件测试
 * @Author shixiaofei
 * @Date 2019/7/28 2:09
 * @Version 1.0
 */
public class MapperTest {


    @Test
    public void testGetOrderById() throws IOException {
        // 创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sfb = new SqlSessionFactoryBuilder();
        // 查找配置文件创建输入流
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("mapperSqlMapConfig.xml");

      /*  InputStream inputStream2 = Resources.getResourceAsStream("traditionSqlMapConfig.xml");
        System.out.println(inputStream2);*/

        // 加载配置文件，创建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = sfb.build(inputStream);
        // 创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 执行查询，参数一：要查询的statementId ,参数二：sql语句入参
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        Order order = mapper.getOrderById(7);
        System.out.println(order);
        // 释放资源
        sqlSession.close();
    }

    public void demo(int n){
        int s = (int)Math.sqrt(n);

    }
}
