package com.banubalta.util;

import com.banubalta.model.Address;
import com.banubalta.model.Customer;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();




                Properties settings = new Properties();

                // MYSQL 8
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/companydb?useSSL=false");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "123456789");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");

 /*               // PostgreSQL
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/companydb");
                settings.put(Environment.USER, "postgres");
                settings.put(Environment.PASS, "123456789");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL95Dialect");
*/

                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.HBM2DDL_AUTO, "create");
                settings.put(Environment.FORMAT_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                configuration.setProperties(settings);

               configuration.addAnnotatedClass(Customer.class);
               configuration.addAnnotatedClass(Address.class);


                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}