package com.yezi.springforstudy.tx.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Map;

public class TmTxDao {
    private Logger logger = LoggerFactory.getLogger(TmTxDao.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private TransactionTemplate transactionTemplate;

    public Map getStudent(int id){
        String sql = "select * from jdbc_student where id = ?";
        Map map = jdbcTemplate.queryForMap(sql, id);
        logger.info("sql ={},id = {}",sql,id);
        System.out.println(map);
        return map;
    }

    public int update(int id,String name){
        String sql = "update jdbc_student set name =? where id =?";
        int count =jdbcTemplate.update(sql,name,id);
        logger.info(String.format("sql: %s",sql));
        logger.info(String.format("para: %d, %s", id, name));
        logger.info(String.format("result:count=%d",count));
        return count;
    }

    public int insert(int id,int age){
        String name ="user" + id;
        String sql = "insert into jdbc_student(id,name,age) values(?,?,?)";
        int count = jdbcTemplate.update(sql, id, name, age);
        logger.info("sql:",sql);
        logger.info(String.format("param: %d,%s,%d",id,name,age));
        logger.info(String.format("result:count=%d",count));
        return count;
    }

    public void withTx(){
        logger.info("开始事务。。。。。");
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(definition);
        try{
            this.getStudent(14);
            this.update(15,"测试");
            this.insert(15,31);
            transactionManager.commit(status);
        }catch (Exception e){
            transactionManager.rollback(status);
        }
        this.getStudent(14);
        this.getStudent(15);
        logger.info("事务结束。。。。");
    }
    public void withoutTx() {
        logger.info("withoutTx start...");
        try{
            this.getStudent(14);
            this.update(15, "user14-tx");
            this.insert(15,31);
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        this.getStudent(14);
        this.getStudent(15);
        logger.info("withoutTx end...");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void withTransactionTemplate() {
        logger.info("withTransactionTemplate start...");
        final TmTxDao txDao = this;
        transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                txDao.getStudent(14);
                txDao.update(14, "user14-tx");
                txDao.insert(15,31);
                return null;
            }
        });
        this.getStudent(14);
        this.getStudent(15);
        logger.info("withTransactionTemplate end...");
    }

}
