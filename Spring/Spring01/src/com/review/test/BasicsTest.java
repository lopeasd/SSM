package com.review.test;

import com.review.bean.ComplexObject;
import com.review.bean.ExampleBean;
import com.review.bean.Person;
import com.review.bean.User;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * xml配置方式的测试，ioc容器的创建，setter constructor以及集合属性的配置测试
 *
 * @author shixiaofei
 */
public class BasicsTest {


    /**
     * 创建ioc容器的两种方式
     * applicatoinContext
     */
    @Test
    public void appGetIoc() {
        //创建ioc容器,加载类路径下Spring的配置文件
        // ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        //创建ioc容器,加载磁盘路径下Spring的配置文件，默认在项目路径下(日志文件可以看出)
        ApplicationContext applicationContext1 = new FileSystemXmlApplicationContext("src/applicationContext.xml");

        //获取bean方式1
        User user = (User) applicationContext1.getBean("user");

        //利用
        User bean = applicationContext1.getBean(User.class);
        System.out.println(bean);
        bean.method();
        System.out.println(user);

    }

    /**
     * BeanFactory(XmlBeanFactory已废弃)
     * <p>
     * Spring加载资源并装配对象的过程：
     *  定义好Spring的配置文件
     * 通过Resource对象将Spring配置文件进行抽象，抽象成一个Resource对象。
     * 定义好Bean工厂（各种BeanFactory）
     * 定义好XmlBeanXmlBeanDefinitionReader对象，并将工厂作为参数传递进去供后续回调使用。
     * 通过XmlBeanBefinitionReader对象读取之前抽象出来的Resource对象（包含了XML文件的解析过程）。
     * IOC容器创建完毕，用户可以通过容器获取到所需的对象信息。
     */
    @Test
    public void beanGetIoc() {
        ClassPathResource res = new ClassPathResource("applicationConText.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(res);
        User user = (User) factory.getBean("user");
        System.out.println(user);

    }

    /**
     * FileSystemXmlApplicationContext和ClassPathXmlApplicationContext获取xml文件
     * ①FileSystemXmlApplicationContext
     *     classpath:前缀是不需要的,默认就是指项目的classpath路径下面;
     *     如果要使用绝对路径,需要加上file:前缀表示这是绝对路径;
     * ②ClassPathXmlApplicationContext
     *      没有盘符的是项目工作路径,即项目的根目录; 
     *      有盘符表示的是文件绝对路径.
     */
    @Test
    public void applicationTwo() {
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("src/applicationContext.xml");
        ApplicationContext applicationContext1 = new FileSystemXmlApplicationContext("file:D:\\applicationContext.xml");
        User user = (User) applicationContext.getBean("user");
        User user1 = (User) applicationContext1.getBean("user");
        ApplicationContext applicationContext2 = new ClassPathXmlApplicationContext("applicationConText.xml");
        User user2 = (User) applicationContext2.getBean("user");
        ApplicationContext applicationContext3 = new ClassPathXmlApplicationContext("file:D:\\applicationConText.xml");
        User user3 = (User) applicationContext3.getBean("user");

        System.out.println(user);
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
    }

    /**
     * xml注入方式
     * 1.setter注入
     * 2.构造器注入
     */
    @Test
    public void exampleBean() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        ExampleBean example = (ExampleBean) applicationContext.getBean("exampleBean3");
        System.out.println(example);
    }

    /**
     * 注入属性类型
     */
    @Test
    public void exampleProps() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        ComplexObject complex = (ComplexObject) applicationContext.getBean("complex");
        System.out.println(complex);

    }

    /**
     * p名称空间的使用
     */
    @Test
    public void pnameSpace() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (User) applicationContext.getBean("user2");
        System.out.println(user);
    }

    /**
     * c名称空间的使用，构造器
     */
    @Test
    public void cnameSpace() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        ExampleBean exampleBean = (ExampleBean) applicationContext.getBean("exampleBean4");
        System.out.println(exampleBean);
    }

    /**
     * SqEl表达式测试
     */
    @Test
    public void spelDemo() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (User) applicationContext.getBean("user3");
        System.out.println(user);
    }

    @Test
    public void demo() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);
    }

}
