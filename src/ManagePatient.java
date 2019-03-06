import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDateTime;
import java.util.Date;


public class ManagePatient {

    private static SessionFactory factory;
    public static void main(String[] args) {

        try {
            factory = new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex)
        {
            System.err.println("Failed to create factory" + ex);
            throw new ExceptionInInitializerError(ex);
        }

        ManagePatient mP = new ManagePatient();


    }

    public Integer addPatient(Date dob, String firstName, String lastName, String medicalInformation, boolean isMale)
        {
            Session session = factory.openSession();
            Transaction tx = null;
            Integer PatientId = null;


        try {
            tx = session.beginTransaction();
            Patient patient = new Patient(dob, firstName, lastName, medicalInformation, isMale);
            PatientId = (Integer) session.save(patient);
            tx.commit();
        }catch (HibernateException e)
        {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return PatientId;

    }


    public void deletePatient(Integer PatientId){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Patient patient = (Patient)session.get(Patient.class, PatientId);
            session.delete(patient);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


}
