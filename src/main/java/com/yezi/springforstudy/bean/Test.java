package com.yezi.springforstudy.bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(JavaConfig.class);
        UseFunctionService  useFunctionService= context.getBean(UseFunctionService.class);
        useFunctionService.getBean();
        context.close();
    }
}
