package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
//import org.hibernate.boot.registry.StandardServiceRegistry;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class Util {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(AvailableSettings.DRIVER, "com.mysql.cj.jdbc.Driver"); //Environment
                settings.put(AvailableSettings.URL, "jdbc:mysql://localhost:3306/rendb");
                settings.put(AvailableSettings.USER, "root");
                settings.put(AvailableSettings.PASS, "root");
                settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                configuration.setProperties(settings).addAnnotatedClass(User.class);
//                StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory();
                System.out.println("Сессия успешно создана");
            } catch (HibernateException e) {
                e.printStackTrace();
                System.out.println("Сессия не создана");
            }
        }
        return sessionFactory;
    }

    public static void sessionFactoryClosed() {
        sessionFactory.close();
    }


    private Util () {
        throw new UnsupportedOperationException("Чисто служебный конструктор, чтобы закрыть неявный публичный");
    }

}

//                settings.put(Environment.HBM2DDL_AUTO, "update");
//                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
//                settings.put(Environment.SHOW_SQL, "true");
