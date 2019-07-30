package com.test;

import com.review.bean.User;
import com.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.*;

/**
 * @Description 测试传统mybatis操作数据库
 * @Author shixiaofei
 * @Date 2019/7/26 17:15
 * @Version 1.0
 */
public class TraditionTest {


    /**
     * 根据id获取用户
     */
    @Test
    public void testGetUserById() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 执行查询，参数一：要查询的statementId ,参数二：sql语句入参
        User user = sqlSession.selectOne("test.getUserById", 1);
        // 输出查询结果
        System.out.println(user);
        // 释放资源
        sqlSession.close();
    }

    /**
     * 获取所有用户
     */
    @Test
    public void testGetUsers() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> users = sqlSession.selectList("test.getUsers");
        for (User user : users
        ) {
            System.out.println(user);
        }
    }

    /**
     * 根据名字获取用户,使用like模糊查询，用selectList获取多个值
     */
    @Test
    public void testGetUserByName() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> users = sqlSession.selectList("test.getUserByName", "张%");
        for (User user : users
        ) {
            System.out.println(user);
        }
    }

    /**
     * 聚集函数 count(*)查询总记录数
     */
    @Test
    public void testGetCount() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int count = sqlSession.selectOne("test.getCount");
        System.out.println(count);
    }

    /**
     * 插入操作，mybatis默认的SqlSessionFactory是DefaultSqlSessionFactory
     * Date 类型会自动转换
     * 默认开启事务，如果不commit，会默认回滚。不关闭sqlSession会占用资源，导致性能下降
     */
    @Test
    public void testInsertByUser() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setAddress("ccdx");
        user.setUsername("纯悦");
        user.setBirthday(new Date());
        sqlSession.update("test.insertByUser", user);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 插入数据时，返回主键自增的ID值，用user.getId()获取
     * 利用 useGeneratedKeys="true" keyProperty="id" 设置
     */
    @Test
    public void testInsertUserKey() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setUsername("key-1");
        user.setAddress("ccdx");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setId(123);//测试发现没用，但不影响返回值
        sqlSession.update("test.insertUserKey", user);
        System.out.println(user.getId());
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 插入数据，利用LAST_INSERT_ID()函数，返回自增值。
     */
    @Test
    public void testInsertUserKeySelect() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setAddress("jd");
        user.setBirthday(new Date());
        user.setSex("1");
        user.setUsername("keySelect");
        sqlSession.insert("test.insertUserKeySelect", user);
        System.err.println(user.getId());
        sqlSession.commit();
        sqlSession.close();

    }

    /**
     * 动态SQL语句，如果username属性不为空，则查找address为吉林大学和username中含有A的所有用户。
     */
    @Test
    public void testFindUserByName() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setUsername("%A%");
        List<User> users = sqlSession.selectList("test.findUserByName", user);
        for (User user1 : users) {
            System.out.println(user1);
        }
        sqlSession.close();
    }

    /**
     * 动态sql
     * 如果username不为空，且address不为空，查找username和address符合的用户
     * 如果username不为空，address为空，则查找username符合的用户
     * 如果username为空，address不为空，则查找符合addess的用户。
     * 如果username为空，adddress为空，则查找所有的用户
     */
    @Test
    public void testFindUserByWhere() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
//        user.setUsername("%A%");
        List<User> users = sqlSession.selectList("test.findUserByWhere", user);
        for (User user1 : users) {
            System.out.println(user1);
        }
        sqlSession.close();
    }

    /**
     * 动态sql
     * 1.如果设置了username，则查找username符合的用户
     * 2.否则，如果id大于30 ，则查找id大于30的用户
     * 3.否则，username没设置，id不大于30或没设置，则查找所有用户
     */
    @Test
    public void testFindUserBychoose() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
//        user.setUsername("张莉");
//        user.setId(20);
        List<User> users = sqlSession.selectList("test.findUserByChoose", user);
        for (User user1 : users) {
            System.out.println(user1);
        }
        sqlSession.close();
    }

    /**动态sql
     * foreach ，parameterT为list
     */
    @Test
    public void testfindUserEachList() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List list = new ArrayList();
//       listids.add(Arrays.asList(41,42,43,44));
        list.add(41);
        list.add(42);
        list.add(43);
        list.add(44);
        List<User> users = sqlSession.selectList("test.findUserEachList", list);
        for (User user1 : users) {
            System.out.println(user1);
        }
        sqlSession.close();
    }

    /**
     * 动态sql
     * 如果map不为空，则查找id为30,41,45,47的值
     */
    @Test
    public void testfindUserEach() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Map map = new HashMap();
        map.put("ids", Arrays.asList(30, 49, 51, 47));
        List<User> users = sqlSession.selectList("test.findUserEach", map);
        for (User user1 : users) {
            System.out.println(user1);
        }
        sqlSession.close();
    }

    /**
     * 动态sql set标签
     * 如果名字为不为key，则根据id修改名字为对应的值
     */
    @Test
    public void testUpdateUser() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setUsername("测试");
        user.setId(47);
        sqlSession.update("test.updateUser",user);
        sqlSession.commit();
        sqlSession.close();
    }
}


