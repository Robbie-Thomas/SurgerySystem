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

    public void addPatientAddress(int patientId,String houseName, String street, String city, String postcode, String county, String country, String houseNumber){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer PatientAddressId = null;

        try {
            tx = session.beginTransaction();
            PatientAddress patientAddress = new PatientAddress(patientId, houseName, street, city, postcode, county, country, houseNumber);
            PatientAddressId = (Integer) session.save(patientAddress);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }



    }
