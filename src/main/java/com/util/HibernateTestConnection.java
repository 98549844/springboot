package com.util;


import com.entity.TestEntity;
//import org.hibernate.Query;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class HibernateTestConnection {

    public static void main(String[] args) {
        HibernateTestConnection conn = new HibernateTestConnection();
      //  conn.testConn();
        //conn.saveByHibernate();
//        conn.getDataById();
 //       conn.getResultSetBySQL();
        conn.getResultSetByHQL();
    }

    public void testConn() {
        Session session = null;
        try {

            //1.创建Configuration,该对象用于读取hibernate.cfg.xml,并完成初始化
            Configuration cfg = new Configuration();
            cfg.configure();
            //2.创建SessoinFactory这是一个会话工厂，是一个重量级的对象
            ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
            SessionFactory sf = cfg.buildSessionFactory(sr);

            session = sf.openSession();
            session.isOpen();
            System.out.println("session.isOpen****" + session.isOpen());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void getDataById() {

        Session session = null;
        try {
            //1.创建Configuration,该对象用于读取hibernate.cfg.xml,并完成初始化
            Configuration cfg = new Configuration();
            cfg.configure();
            //2.创建SessoinFactory这是一个会话工厂，是一个重量级的对象
            ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
            //通过Configuration获得一个SessionFactory对象
            SessionFactory sf = cfg.buildSessionFactory(sr);
            //打开一个Session
            session = sf.openSession();
            //开始一个事务
            Transaction tx = session.beginTransaction();
            //param: entity类和id
            TestEntity testEntity1 = (TestEntity) session.get(TestEntity.class, 1);
            TestEntity testEntity4 = (TestEntity) session.load(TestEntity.class, 4);
            //提交事务
            tx.commit();

            System.out.println("testEntity1**" + testEntity1.getUserName());
            System.out.println("testEntity4**" + testEntity4.getUserName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭会话
            session.close();
        }
    }


    public void getResultSetBySQL() {
        Session session = null;
        try {
            Configuration cfg = new Configuration();
            cfg.configure();
            ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
            SessionFactory sf = cfg.buildSessionFactory(sr);
            session = sf.openSession();
            Transaction tx = session.beginTransaction();

            Query query = session.createSQLQuery("select * from test_entity_table").addEntity(TestEntity.class);
            List<TestEntity> testEntityList = query.list();

            tx.commit();
            for (TestEntity t : testEntityList) {
                System.out.println(t.getUserName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void getResultSetByHQL() {
        Session session = null;
        try {
            Configuration cfg = new Configuration();
            cfg.configure();
            ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
            SessionFactory sf = cfg.buildSessionFactory(sr);

            session = sf.openSession();
            Transaction tx = session.beginTransaction();

            Query query = session.createQuery("select f from TestEntity f");
            List<TestEntity> testEntityList = query.list();

            tx.commit();
            for (TestEntity t : testEntityList) {
                System.out.println(t.getUserName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭会话
            session.close();
        }
    }

    public void saveByHibernate() {
        Session session = null;
        try {
            //1.创建Configuration,该对象用于读取hibernate.cfg.xml,并完成初始化
            Configuration cfg = new Configuration();
            cfg.configure();
            //2.创建SessoinFactory这是一个会话工厂，是一个重量级的对象
            ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
            //通过Configuration获得一个SessionFactory对象
            SessionFactory sf = cfg.buildSessionFactory(sr);

            //打开一个Session
            session = sf.openSession();
            //开始一个事务
            Transaction tx = session.beginTransaction();
            //创建一个Student对象
            TestEntity test = new TestEntity();
            test.setUserId(46);
            test.setUserName("ccc");
            test.setEmail("garlam_au@qq.com");
            //通过session的save()方法将Student对象保存到数据库中
            session.save(test);
            //提交事务
            tx.commit();
            //关闭会话
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
