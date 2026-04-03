package io.microservices_java.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory = null;

    static {
        try {
            SessionFactory sessionFactory1 = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Match.class)
                    .addAnnotatedClass(Player.class)
                    .buildSessionFactory();
            sessionFactory = sessionFactory1;

            System.out.println(sessionFactory.toString());
            System.out.println("INIT SESSION FACTORY");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}