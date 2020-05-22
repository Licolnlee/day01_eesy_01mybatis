package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    /*test*/
    public static void main(String[] args) throws Exception {
        /*reading config */
        /*create sqlsessionfactory*/
        InputStream ins = Resources.getResourceAsStream("SqlMapConfig.xml");
        /*use factory generate sqlsession object*/
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(ins);
        /*user sqlsession to create dao interface proxy object*/
        SqlSession session = factory.openSession();
        IUserDao userDao = session.getMapper(IUserDao.class);
        List<User> users = userDao.findAll();
        /*user proxy object perform function*/
        for (User user : users){
            System.out.println(user);
        }
        /*release resources*/
        session.close();
        ins.close();
    }
}
