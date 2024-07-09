package com.image.session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateSession {

    private static SessionFactory sessionFactory;

    public static Session getSessionInstance() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        return sessionFactory.openSession();
    }

}
