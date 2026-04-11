package io.tennis_scoreboard.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory = null;

    static {
        try {
            Configuration configuration = new Configuration();


            String dbUrl = System.getenv("DB_URL");
            String dbUser = System.getenv("DB_USER");
            String dbPassword = System.getenv("DB_PASSWORD");
            String dbShowSql = System.getenv("DB_SHOW_SQL");
            String dbDdlAuto = System.getenv("DB_DDL_AUTO");


            configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
            configuration.setProperty("hibernate.connection.url", dbUrl);
            configuration.setProperty("hibernate.connection.username", dbUser);
            configuration.setProperty("hibernate.connection.password", dbPassword);
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            configuration.setProperty("hibernate.current_session_context_class", "thread");
            configuration.setProperty("hibernate.format_sql", "true");

            if (dbShowSql != null) {
                configuration.setProperty("hibernate.show_sql", dbShowSql);
            } else {
                configuration.setProperty("hibernate.show_sql", "true");
            }

            if (dbDdlAuto != null) {
                configuration.setProperty("hibernate.hbm2ddl.auto", dbDdlAuto);
            } else {
                configuration.setProperty("hibernate.hbm2ddl.auto", "update");
            }
            configuration.addAnnotatedClass(Match.class);
            configuration.addAnnotatedClass(Player.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}