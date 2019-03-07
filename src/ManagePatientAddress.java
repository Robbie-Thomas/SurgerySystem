import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDateTime;
import java.util.Date;


public class ManagePatientAddress {

    private static SessionFactory factory;

    public static void main(String[] args) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create factory" + ex);
            throw new ExceptionInInitializerError(ex);
        }
        ManagePatientAddress mPa = new ManagePatientAddress();
    }





    }
