import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;


public class SessionManager {

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

        SessionManager sP = new SessionManager();


    }


    //creates a patient with all the fields
    public void addPatient(Date dob, String firstName, String lastName, String medicalInformation, boolean isMale)
        {
            Session session = factory.openSession();
            Transaction tx = null;


        try {
            tx = session.beginTransaction();
            Patient patient = new Patient(dob, firstName, lastName, medicalInformation, isMale);
            session.save(patient);
            tx.commit();
        }catch (HibernateException e)
        {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

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

    //Managing the patient address.

    public void addPatientAddress(Integer patientId, String houseName, String street, String city, String postcode, String county, String country, String houseNumber){
        Session session = factory.openSession();
        Transaction tx = null;


        try {
            tx = session.beginTransaction();
            PatientAddress patientAddress = new PatientAddress(patientId, houseName, street, city, postcode, county, country, houseNumber);
            session.save(patientAddress);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }


    //deletes a patient Address with the equivalent Id as the PatientID
    public void deletePatientAddress(Integer PatientAddress_Id){
        //creates the session
        Session session = factory.openSession();
        //Sets the transaction to null
        Transaction tx = null;

        try {
            //starts the transaction on the hibernate session
            tx = session.beginTransaction();
            //Gets the PatientAddress with the @params patientId
            PatientAddress patientAddress = (PatientAddress)session.get(PatientAddress.class, PatientAddress_Id);
            //Deletes the PatientAddress once found
            session.delete(patientAddress);
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

    public void updatePatientAddressHouseName(Integer PatientAddress_Id, String houseName)
    {
        Session session = factory.openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            PatientAddress patientAddress = (PatientAddress)session.get(PatientAddress.class, PatientAddress_Id);
            patientAddress.setHouseName(houseName);
            tx.commit();
        }catch (HibernateException e)
        {
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void updatePatientAddressHouseNumber(Integer PatientAddress_Id, String houseNumber)
    {
        Session session = factory.openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            PatientAddress patientAddress = (PatientAddress)session.get(PatientAddress.class, PatientAddress_Id);
            patientAddress.setHouseNumber(houseNumber);
            tx.commit();
        }catch (HibernateException e)
        {
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void updatePatientAddressStreet(Integer PatientAddress_Id, String street)
    {
        Session session = factory.openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            PatientAddress patientAddress = (PatientAddress)session.get(PatientAddress.class, PatientAddress_Id);
            patientAddress.setStreet(street);
            tx.commit();
        }catch (HibernateException e)
        {
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void updatePatientAddressCity(Integer PatientAddress_Id, String city)
    {
        Session session = factory.openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            PatientAddress patientAddress = (PatientAddress)session.get(PatientAddress.class, PatientAddress_Id);
            patientAddress.setCity(city);
            tx.commit();
        }catch (HibernateException e)
        {
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void updatePatientAddressPostcode(Integer PatientAddress_Id, String postcode)
    {
        Session session = factory.openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            PatientAddress patientAddress = (PatientAddress)session.get(PatientAddress.class, PatientAddress_Id);
            patientAddress.setPostcode(postcode);
            tx.commit();
        }catch (HibernateException e)
        {
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }


    public void updatePatientAddressCounty(Integer PatientAddress_Id, String county)
    {
        Session session = factory.openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            PatientAddress patientAddress = (PatientAddress)session.get(PatientAddress.class, PatientAddress_Id);
            patientAddress.setCounty(county);
            tx.commit();
        }catch (HibernateException e)
        {
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void updatePatientAddressCountry(Integer PatientAddress_Id, String country)
    {
        Session session = factory.openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            PatientAddress patientAddress = (PatientAddress)session.get(PatientAddress.class, PatientAddress_Id);
            patientAddress.setCountry(country);
            tx.commit();
        }catch (HibernateException e)
        {
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }


    //Patient email Address

    public void addPatientEmailAddress(String Email, Integer patientId)
    {
        Session session = factory.openSession();
        Transaction tx = null;


        try {
            tx = session.beginTransaction();
            PatientEmailAddress patientEmailAddress = new PatientEmailAddress(Email, patientId);
            session.save(patientEmailAddress);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void deletePatientEmailAddress(Integer PatientEmailAddress_Id){
        //creates the session
        Session session = factory.openSession();
        //Sets the transaction to null
        Transaction tx = null;

        try {
            //starts the transaction on the hibernate session
            tx = session.beginTransaction();
            //Gets the PatientEmailAddress with the @params patientId
            PatientEmailAddress patientEmailAddress = (PatientEmailAddress)session.get(PatientEmailAddress.class, PatientEmailAddress_Id);
            //Deletes the PatientAddress once found
            session.delete(patientEmailAddress);
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



    public void updatePatientEmailAddress(Integer PatientEmailAddress_Id, String email)
    {
        Session session = factory.openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            PatientEmailAddress patientEmailAddress = (PatientEmailAddress)session.get(PatientEmailAddress.class, PatientEmailAddress_Id);
            patientEmailAddress.setEmailAddress(email);
            tx.commit();
        }catch (HibernateException e)
        {
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    //Patient Phone number


    public void addPatientPhone(String phoneNumber, Integer patientPhone_Id)
    {
        Session session = factory.openSession();
        Transaction tx = null;


        try {
            tx = session.beginTransaction();
            PatientPhone patientPhone = new PatientPhone(phoneNumber, patientPhone_Id);
            session.save(patientPhone);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void deletePatientPhone(Integer patientPhone_Id){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            PatientPhone patientPhone = (PatientPhone)session.get(PatientPhone.class, patientPhone_Id);
            session.delete(patientPhone);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updatePatientPhoneNumber(Integer patientPhone_Id, String phoneNumber)
    {
        Session session = factory.openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            PatientPhone patientPhone = (PatientPhone)session.get(PatientPhone.class, patientPhone_Id);
            patientPhone.setPhoneNumber(phoneNumber);
            tx.commit();
        }catch (HibernateException e)
        {
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }












}
