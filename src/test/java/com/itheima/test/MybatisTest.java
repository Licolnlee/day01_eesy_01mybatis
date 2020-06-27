package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    private InputStream ins;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before//用于在测试方法执行之前执行
    public void init() throws Exception{
        /*create sqlsessionfactory*/
        ins = Resources.getResourceAsStream("SqlMapConfig.xml");
        /*use factory generate sqlsession object*/
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(ins);
        /*user sqlsession to create dao interface proxy object*/
        sqlSession = factory.openSession();
        userDao = sqlSession.getMapper(IUserDao.class);
    }
    @After//用于在测试方法执行之后执行
    public void destory() throws Exception{
        /*释放资源*/
        sqlSession.close();
        ins.close();
    }
    /*test*/
    @Test
    public void testFindAll(){
        /*reading config */
        List<User> users = userDao.findAll();
        /*user proxy object perform function*/
        for (User user : users){
            System.out.println(user);
        }
    }

    /*测试保存操作*/

    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("mybatis saveuser");
        user.setAddress("北京市顺义区");
        user.setSex("男");
        user.setBirthday(new Date());


        userDao.saveUser(user);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(50);
        user.setUsername("mybatis saveuser");
        user.setAddress("北京市顺义区");
        user.setSex("女");
        user.setBirthday(new Date());


        userDao.updateUser(user);
    }

    /*删除用户数据*/
    @Test
    public void testDelete(){

        userDao.deleteUser(50);
    }

    /*查询用户数据*/
    @Test
    public void testFindOne(){

        User user = userDao.findById(48);
        System.out.println(user);
    }

    /*测试模糊查询操作*/
    @Test
    public void testFindName(){

        List<User> users = userDao.findByName("%王%");
        for (User user:users){
            System.out.println(user);
        }
    }

    /*测试查询总记录条数*/
    @Test
    public void testFindTotal(){

        int count = userDao.findTotal();
        System.out.println(count);
    }
}


