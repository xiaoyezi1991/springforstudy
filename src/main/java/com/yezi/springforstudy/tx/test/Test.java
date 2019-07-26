package com.yezi.springforstudy.tx.test;

import com.yezi.springforstudy.tx.dao.TmTxDao;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:jdbc_tx.xml");
        TmTxDao tmTxDao = (TmTxDao) context.getBean("tmTxDao");

        tmTxDao.withTx();

        // tmTxDao.withTransactionTemplate();

        context.close();

    }

}
