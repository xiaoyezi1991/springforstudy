package com.yezi.springforstudy.ioc;


import com.yezi.springforstudy.ioc.beans.MyBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ioc {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_bean.xml");
        MyBean myBean = (MyBean) applicationContext.getBean("myBean");
        System.out.println(myBean);
        myBean = (MyBean) applicationContext.getBean("myBean1");
        System.out.println(myBean);
    }


}
