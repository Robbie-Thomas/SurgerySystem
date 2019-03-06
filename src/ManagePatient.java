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


    //creates a patient with all the fields
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


    //deletes a patient with the equivalent Id as the PatientID
    public void deletePatient(Integer patientId){
        //creates the session
        Session session = factory.openSession();
        //Sets the transaction to null
        Transaction tx = null;

        try {
            //starts the transaction on the hibernate session
            tx = session.beginTransaction();
            //Gets the Patient with the @params patientId
            Patient patient = (Patient)session.get(Patient.class, patientId);
            //Deletes the patient once found
            session.delete(patient);
            //commits the transaction to the session
            tx.commit();
        } catch (HibernateException e) {
            /*checks to see if the transaction is null, if the transaction tx is not
            it rolls back the transaction */
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            //closing the session
            session.close();
        }
    }


    //Updates a Patients first name
    public void updateFirstName(Integer patientId, String firstName){
        //creates the session
        Session session = factory.openSession();
        //Sets the transaction to null
        Transaction tx = null;
        try {
            //starts the transaction on the hibernate session
            tx = session.beginTransaction();
            //Gets the Patient with the @params patientId
            Patient patient = (Patient)session.get(Patient.class, patientId);
            //Using the method setFirstName sets the patients first name to the String firstName
            //that is required in the method fields
            patient.setFirstName(firstName);
            //commits the transaction to the session
            tx.commit();
        }catch (HibernateException e)
        {
            /*checks to see if the transaction is null, if the transaction tx is not
            it rolls back the transaction */
            if(tx!= null) tx.rollback();
            e.printStackTrace();
        }finally {
            //closing the session
            session.close();
        }

    }

       public void updateLastName(Integer patientId, String lastName){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Patient patient = (Patient)session.get(Patient.class, patientId);
            patient.setLastName(lastName);
            tx.commit();
        }catch (HibernateException e)
        {
            if(tx!= null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

    }

    public void updateMiddleName(Integer patientId, String middleName){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Patient patient = (Patient)session.get(Patient.class, patientId);
            patient.setMiddleName(middleName);
            tx.commit();
        }catch (HibernateException e)
        {
            if(tx!= null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

    }

    public void updateMedicalInformation(Integer patientId, String medicalInfo){
        Session session = factory.openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Patient patient = (Patient)session.get(Patient.class, patientId);
            patient.setMedicalInformation(medicalInfo);
            tx.commit();
        }catch (HibernateException e)
        {
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void updateDateOfBirth(Integer patientId, Date DOB){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Patient patient = (Patient)session.get(Patient.class, patientId);
            patient.setDob(DOB);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public void updateSex(Integer patientId, Boolean isMale){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Patient patient = (Patient)session.get(Patient.class, patientId);
            patient.setMale(isMale);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }


}
