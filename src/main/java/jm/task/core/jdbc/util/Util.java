package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;


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
                settings.put(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQLDialect");
                configuration.setProperties(settings).addAnnotatedClass(User.class);
                sessionFactory = configuration.buildSessionFactory();
                System.out.println("\n\nСессия успешно создана!!!\n");
            } catch (HibernateException e) {
                e.printStackTrace();
                LOGGER.info("Сессия не создана");
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
