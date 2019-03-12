import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class SessionManager {

    private static SessionFactory factory;


    public static void main(String[] args) {

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create factory" + ex);
            throw new ExceptionInInitializerError(ex);
        }

        SessionManager sM = new SessionManager();
        //sM.addPatient(LocalDate.now(),"Terry","Test","Healthy condition", true);
        //sM.addPatient(LocalDate.now(),"Tom","Test","Healthy condition", true);
        //sM.updateFirstName(1,"Tom");
        //sM.addPatientAddress(1,"","55","Lane","Sheffield","S32 8GF","Sheffield","England");
        //sM.addPatientAddress(1,"","57"," Lane","Sheffield","S32 GHF","Sheffield","England");
        //sM.addPatientPhone("0777424242",1);
        //sM.addPatientEmailAddress("email2@dasda.com",1);
        //sM.addPatientEmailAddress("email1@google.com",1);
        //sM.addRoom("","5001");
        //sM.addDoctor("Derick","Peppah","",true,true,true,true,true,true,true,true,true,true,true,false,false,false,false);
        //sM.addNurse("Sarah","Tilter","",false,true,true,true,true,true,true,true,true,true,true,false,false,false,false);
        //sM.addStaffDoctor(1);

        //sM.addAppointment(LocalDateTime.now(),LocalDateTime.now(),1,1,1, true);
        //sM.addAddressToPatient(1,1);
        //System.out.println(sM.getPatient(1).toString());
        //sM.addDoctor("Derick","Peppah","",true,true,true,true,true,true,true,true,true,true,true,false,false,false,false);
        //sM.addNurse("Sarah","Tilter","",false,true,true,true,true,true,true,true,true,true,true,false,false,false,false);

        /*List<Patient> patients = sM.getPatientsWithLastName("Test");
        for(int i =0; patients.size() > i; i++){
            System.out.println(patients.get(i).getFirstName());
        }*/



       //List<Patient> patients = sM.getPatientsWithEmailAddress("dasdas@dasda.com");
       //System.out.println(patients.get(0).getFirstName());

        List<Patient> patientWithEmail = sM.getEmailFromPatientName("Tom", "Test");


    }


    //creates a patient with all the fields
    public void addPatient(LocalDate dob, String firstName, String lastName, String medicalInformation, boolean isMale) {
        Session session = factory.openSession();
        Transaction tx = null;


        try {
            tx = session.beginTransaction();
            Patient patient = new Patient(dob, firstName, lastName, medicalInformation, isMale);
            session.save(patient);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();

    }

    public Patient getPatient(Integer patientId) {
        Patient patient = new Patient();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            patient = session.get(Patient.class, patientId);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return session.get(Patient.class, patientId);
    }


    //deletes a patient with the equivalent Id as the PatientID
    public void deletePatient(Integer patientId) {
        //creates the session
        Session session = factory.openSession();
        //Sets the transaction to null
        Transaction tx = null;

        try {
            //starts the transaction on the hibernate session
            tx = session.beginTransaction();
            //Gets the Patient with the @params patientId
            Patient patient = session.get(Patient.class, patientId);
            //Deletes the patient once found
            session.delete(patient);
            //commits the transaction to the session
            tx.commit();
        } catch (HibernateException e) {
            /*checks to see if the transaction is null, if the transaction tx is not
            it rolls back the transaction */
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }


    //Updates a Patients first name
    public void updateFirstName(Integer patientId, String firstName) {
        //creates the session
        Session session = factory.openSession();
        //Sets the transaction to null
        Transaction tx = null;
        try {
            //starts the transaction on the hibernate session
            tx = session.beginTransaction();
            //Gets the Patient with the @params patientId
            Patient patient = session.get(Patient.class, patientId);
            //Using the method setFirstName sets the patients first name to the String firstName
            //that is required in the method fields
            patient.setFirstName(firstName);
            session.update(patient);
            //commits the transaction to the session
            tx.commit();
        } catch (HibernateException e) {
            /*checks to see if the transaction is null, if the transaction tx is not
            it rolls back the transaction */
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();

    }

    public void updateLastName(Integer patientId, String lastName) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Patient patient = session.get(Patient.class, patientId);
            patient.setLastName(lastName);
            session.update(patient);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();

    }

    public void updateMiddleName(Integer patientId, String middleName) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Patient patient = session.get(Patient.class, patientId);
            patient.setMiddleName(middleName);
            session.update(patient);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();

    }

    public void updateMedicalInformation(Integer patientId, String medicalInfo) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Patient patient = session.get(Patient.class, patientId);
            patient.setMedicalInformation(medicalInfo);
            session.update(patient);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void updateDateOfBirth(Integer patientId, LocalDate DOB) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Patient patient = session.get(Patient.class, patientId);
            patient.setDob(DOB);
            session.update(patient);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void updateSex(Integer patientId, Boolean isMale) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Patient patient = session.get(Patient.class, patientId);
            patient.setMale(isMale);
            session.update(patient);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    //Managing the patient address.

    public void addPatientAddress(Integer patientId, String houseName, String houseNumber, String street, String city, String postcode, String county, String country) {
        Session session = factory.openSession();
        Transaction tx = null;


        try {
            tx = session.beginTransaction();
            Patient patient = session.get(Patient.class, patientId);
            Address address = new Address(patient, houseName, houseNumber, street, city, postcode, county, country);
            session.save(address);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }


    //deletes a patient Address with the equivalent Id as the PatientID
    public void deletePatientAddress(Integer PatientAddress_Id) {
        //creates the session
        Session session = factory.openSession();
        //Sets the transaction to null
        Transaction tx = null;

        try {
            //starts the transaction on the hibernate session
            tx = session.beginTransaction();
            //Gets the Address with the @params patientId
            Address address = session.get(Address.class, PatientAddress_Id);
            //Deletes the Address once found
            session.delete(address);
            //commits the transaction to the session
            tx.commit();
        } catch (HibernateException e) {
            /*checks to see if the transaction is null, if the transaction tx is not
            it rolls back the transaction */
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void updatePatientAddressHouseName(Integer PatientAddress_Id, String houseName) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Address address = session.get(Address.class, PatientAddress_Id);
            address.setHouseName(houseName);
            session.update(address);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void updatePatientAddressHouseNumber(Integer PatientAddress_Id, String houseNumber) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Address address = session.get(Address.class, PatientAddress_Id);
            address.setHouseNumber(houseNumber);
            session.update(address);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void updatePatientAddressStreet(Integer PatientAddress_Id, String street) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Address address = session.get(Address.class, PatientAddress_Id);
            address.setStreet(street);
            session.update(address);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void updatePatientAddressCity(Integer PatientAddress_Id, String city) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Address address = session.get(Address.class, PatientAddress_Id);
            address.setCity(city);
            session.update(address);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void updatePatientAddressPostcode(Integer PatientAddress_Id, String postcode) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Address address = session.get(Address.class, PatientAddress_Id);
            address.setPostcode(postcode);
            session.update(address);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }


    public void updatePatientAddressCounty(Integer PatientAddress_Id, String county) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Address address = session.get(Address.class, PatientAddress_Id);
            address.setCounty(county);
            session.update(address);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void updatePatientAddressCountry(Integer PatientAddress_Id, String country) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Address address = session.get(Address.class, PatientAddress_Id);
            address.setCountry(country);
            session.update(address);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }


    //Patient email Address

    public void addPatientEmailAddress(String Email, Integer patientId) {
        Session session = factory.openSession();
        Transaction tx = null;


        try {
            tx = session.beginTransaction();
            Patient patient = session.get(Patient.class, patientId);
            PatientEmailAddress patientEmailAddress = new PatientEmailAddress(Email, patient);
            session.save(patientEmailAddress);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void deletePatientEmailAddress(Integer PatientEmailAddress_Id) {
        //creates the session
        Session session = factory.openSession();
        //Sets the transaction to null
        Transaction tx = null;

        try {
            //starts the transaction on the hibernate session
            tx = session.beginTransaction();
            //Gets the PatientEmailAddress with the @params patientId
            PatientEmailAddress patientEmailAddress = session.get(PatientEmailAddress.class, PatientEmailAddress_Id);
            //Deletes the Address once found
            session.delete(patientEmailAddress);
            //commits the transaction to the session
            tx.commit();
        } catch (HibernateException e) {
            /*checks to see if the transaction is null, if the transaction tx is not
            it rolls back the transaction */
            if (tx != null) tx.rollback();
            e.printStackTrace();

        }
        session.close();
        //closing the session
    }


    public void updatePatientEmailAddress(Integer PatientEmailAddress_Id, String email) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            PatientEmailAddress patientEmailAddress = session.get(PatientEmailAddress.class, PatientEmailAddress_Id);
            patientEmailAddress.setEmailAddress(email);
            session.update(patientEmailAddress);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    //Patient Phone number


    public void addPatientPhone(String phoneNumber, Integer patient_Id) {
        Session session = factory.openSession();
        Transaction tx = null;


        try {
            tx = session.beginTransaction();
            Patient patient = session.get(Patient.class, patient_Id);
            PatientPhone patientPhone = new PatientPhone(phoneNumber, patient);
            session.save(patientPhone);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void deletePatientPhone(Integer patientPhone_Id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            PatientPhone patientPhone = session.get(PatientPhone.class, patientPhone_Id);
            session.delete(patientPhone);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void updatePatientPhoneNumber(Integer patientPhone_Id, String phoneNumber) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            PatientPhone patientPhone = session.get(PatientPhone.class, patientPhone_Id);
            patientPhone.setPhoneNumber(phoneNumber);
            session.update(patientPhone);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    //Adding phone, email and address to the patient

    public void addAddressToPatient(Integer patientId, Integer patientAddress_Id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Address address = session.load(Address.class, patientAddress_Id);
            Patient patient = session.load(Patient.class, patientId);
            patient.getAddresses().add(address);
            session.update(patient);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void addEmailAddressToPatient(Integer patientId, Integer patientEmailAddress_Id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            PatientEmailAddress patientEmailAddress = session.load(PatientEmailAddress.class, patientEmailAddress_Id);
            Patient patient = session.load(Patient.class, patientId);
            patient.getPatientEmailAddresses().add(patientEmailAddress);
            session.update(patient);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void addPhoneToPatient(Integer patientId, Integer patientPhone_Id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            PatientPhone patientPhone = session.load(PatientPhone.class, patientPhone_Id);
            Patient patient = session.load(Patient.class, patientId);
            patient.getPatientPhones().add(patientPhone);
            session.update(patient);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }


    //Doctor


    public void addDoctor(String firstName, String lastName, String specialistArea, Boolean isMale, Boolean mondayAM, Boolean mondayPM, Boolean tuesdayAM, Boolean tuesdayPM, Boolean wednesdayAM, Boolean wednesdayPM, Boolean thursdayAM, Boolean thursdayPM, Boolean fridayAM, Boolean fridayPM, Boolean saturdayAM, Boolean saturdayPM, Boolean sundayAM, Boolean sundayPM) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Doctor doctor;
            doctor = new Doctor(firstName, lastName, specialistArea, isMale, mondayAM, mondayPM, tuesdayAM, tuesdayPM, wednesdayAM, wednesdayPM, thursdayAM, thursdayPM, fridayAM, fridayPM, saturdayAM, saturdayPM, sundayAM, sundayPM);
            session.save(doctor);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void deleteDoctor(Integer docId) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Doctor doctor = session.get(Doctor.class, docId);
            session.delete(doctor);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void updateDocFirstName(Integer docId, String firstName) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Doctor doctor = session.get(Doctor.class, docId);
            doctor.setFirstName(firstName);
            session.update(doctor);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void updateDocLastName(Integer docId, String lastName) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Doctor doctor = session.get(Doctor.class, docId);
            doctor.setLastName(lastName);
            session.update(doctor);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void updateDocSpecialistArea(Integer docId, String specialistArea) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Doctor doctor = session.get(Doctor.class, docId);
            doctor.setSpecialistArea(specialistArea);
            session.update(doctor);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void updateDocIsMale(Integer docId, boolean isMale) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Doctor doctor = session.get(Doctor.class, docId);
            doctor.setMale(isMale);
            session.update(doctor);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    @SuppressWarnings("Duplicates")
    public void updateDocSchedule(Integer docId, Boolean mondayAM, Boolean mondayPM, Boolean tuesdayAM, Boolean tuesdayPM, Boolean wednesdayAM, Boolean wednesdayPM, Boolean thursdayAM, Boolean thursdayPM, Boolean fridayAM, Boolean fridayPM, Boolean saturdayAM, Boolean saturdayPM, Boolean sundayAM, Boolean sundayPM) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Doctor doctor = session.get(Doctor.class, docId);
            doctor.setMondayAM(mondayAM);
            doctor.setMondayPM(mondayPM);
            doctor.setTuesdayAM(tuesdayAM);
            doctor.setTuesdayPM(tuesdayPM);
            doctor.setWednesdayAM(wednesdayAM);
            doctor.setWednesdayPM(wednesdayPM);
            doctor.setThursdayAM(thursdayAM);
            doctor.setThursdayPM(thursdayPM);
            doctor.setFridayAM(fridayAM);
            doctor.setFridayPM(fridayPM);
            doctor.setSaturdayAM(saturdayAM);
            doctor.setSaturdayPM(saturdayPM);
            doctor.setSundayAM(sundayAM);
            doctor.setSundayPM(sundayPM);
            session.update(doctor);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        }
        session.close();
    }


    // Creating a nurse


    public void addNurse(String firstName, String lastName, String specialistArea, Boolean isMale, Boolean mondayAM, Boolean mondayPM, Boolean tuesdayAM, Boolean tuesdayPM, Boolean wednesdayAM, Boolean wednesdayPM, Boolean thursdayAM, Boolean thursdayPM, Boolean fridayAM, Boolean fridayPM, Boolean saturdayAM, Boolean saturdayPM, Boolean sundayAM, Boolean sundayPM) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Nurse nurse = new Nurse(firstName, lastName, specialistArea, isMale, mondayAM, mondayPM, tuesdayAM, tuesdayPM, wednesdayAM, wednesdayPM, thursdayAM, thursdayPM, fridayAM, fridayPM, saturdayAM, saturdayPM, sundayAM, sundayPM);
            session.save(nurse);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void deleteNurse(Integer nurseId) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Nurse nurse = session.get(Nurse.class, nurseId);
            session.delete(nurse);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    @SuppressWarnings("Duplicates")
    //Need these methods even if shows as duplicate code.
    public void updateNurseFirstName(Integer nurseId, String firstName) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Nurse nurse = session.get(Nurse.class, nurseId);
            nurse.setFirstName(firstName);
            session.update(nurse);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void updateNurseLastName(Integer nurseId, String lastName) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Nurse nurse = session.get(Nurse.class, nurseId);
            nurse.setLastName(lastName);
            session.update(nurse);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void updateNurseSpecialistArea(Integer nurseId, String specialistArea) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Nurse nurse = session.get(Nurse.class, nurseId);
            nurse.setSpecialistArea(specialistArea);
            session.update(nurse);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void updateNurseIsMale(Integer nurseId, boolean isMale) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Nurse nurse = session.get(Nurse.class, nurseId);
            nurse.setMale(isMale);
            session.update(nurse);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    @SuppressWarnings("Duplicates")
    public void updateNurseSchedule(Integer nurseId, Boolean mondayAM, Boolean mondayPM, Boolean tuesdayAM, Boolean tuesdayPM, Boolean wednesdayAM, Boolean wednesdayPM, Boolean thursdayAM, Boolean thursdayPM, Boolean fridayAM, Boolean fridayPM, Boolean saturdayAM, Boolean saturdayPM, Boolean sundayAM, Boolean sundayPM) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Nurse nurse = session.get(Nurse.class, nurseId);
            nurse.setMondayAM(mondayAM);
            nurse.setMondayPM(mondayPM);
            nurse.setTuesdayAM(tuesdayAM);
            nurse.setTuesdayPM(tuesdayPM);
            nurse.setWednesdayAM(wednesdayAM);
            nurse.setWednesdayPM(wednesdayPM);
            nurse.setThursdayAM(thursdayAM);
            nurse.setThursdayPM(thursdayPM);
            nurse.setFridayAM(fridayAM);
            nurse.setFridayPM(fridayPM);
            nurse.setSaturdayAM(saturdayAM);
            nurse.setSaturdayPM(saturdayPM);
            nurse.setSundayAM(sundayAM);
            nurse.setSundayPM(sundayPM);
            session.update(nurse);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        }
        session.close();
    }


    //Room

    public void addRoom(String roomName, String roomNumber) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Room room = new Room(roomName, roomNumber);
            session.save(room);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        }
        session.close();
    }

    public void deleteRoom(Integer roomId) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Room room = session.get(Room.class, roomId);
            session.delete(room);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        }
        session.close();

    }

    public void UpdateRoomNumber(Integer roomId, String roomNumber) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Room room = session.get(Room.class, roomId);
            room.setRoomNumber(roomNumber);
            session.update(room);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        }
        session.close();
    }

    public void UpdateRoomName(Integer roomId, String roomName) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Room room = session.get(Room.class, roomId);
            room.setRoomName(roomName);
            session.update(room);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        }
        session.close();
    }

    //staff

    public void addStaffDoctor(Integer doctorId) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Doctor doctor = session.get(Doctor.class, doctorId);
            Staff staff = new Staff();
            staff.setDoctor(doctor);
            session.save(staff);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        }
        session.close();
    }

    public void addStaffNurse(Integer nurseId) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Nurse nurse = session.get(Nurse.class, nurseId);
            Staff staff = new Staff();
            staff.setNurse(nurse);
            session.save(staff);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        }
        session.close();
    }

    public void deleteStaff(Integer staffId) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Staff staff = session.get(Staff.class, staffId);
            session.delete(staff);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void updateStaffDoc(Integer staffId, Integer doctorId) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Staff staff = session.get(Staff.class, staffId);
            Doctor doctor = session.get(Doctor.class, doctorId);
            staff.setDoctor(doctor);
            session.update(staff);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        }
        session.close();
    }

    public void updateStaffNurse(Integer staffId, Integer nurseId) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Staff staff = session.get(Staff.class, staffId);
            Nurse nurse = session.get(Nurse.class, nurseId);
            staff.setNurse(nurse);
            session.update(staff);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        }
        session.close();
    }

    //Appointment

    public void addAppointment(LocalDateTime appTime, LocalDateTime appDate, Integer patientId, Integer staffId, Integer roomId, boolean onTime) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Patient patient = session.get(Patient.class, patientId);
            Staff staff = session.get(Staff.class, staffId);
            Room room = session.get(Room.class, roomId);
            Appointment appointment = new Appointment(appDate, appTime, room, patient, staff, onTime);
            session.save(appointment);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        }
        session.close();
    }

    public void deleteAppointment(Integer appId) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Appointment appointment = session.get(Appointment.class, appId);
            session.delete(appointment);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void updateAppointmentDate(Integer appId, LocalDateTime date) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Appointment appointment = session.get(Appointment.class, appId);
            appointment.setAppointmentDate(date);
            session.update(appointment);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void updateAppointmentTime(Integer appId, LocalDateTime time) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Appointment appointment = session.get(Appointment.class, appId);
            appointment.setAppointmentTime(time);
            session.update(appointment);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void updateAppointmentStaff(Integer appId, Integer staffId) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Appointment appointment = session.get(Appointment.class, appId);
            Staff staff = session.get(Staff.class, staffId);
            appointment.setStaff(staff);
            session.update(appointment);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void updateAppointmentPatient(Integer appId, Integer patientId) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Appointment appointment = session.get(Appointment.class, appId);
            Patient patient = session.get(Patient.class, patientId);
            appointment.setPatient(patient);
            session.update(appointment);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void updateAppointmentRoom(Integer appId, Integer roomId) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Appointment appointment = session.get(Appointment.class, appId);
            Room room = session.get(Room.class, roomId);
            appointment.setRoom(room);
            session.update(appointment);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void updateAppointmentOnTime(Integer appId, boolean onTime) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Appointment appointment = session.get(Appointment.class, appId);
            appointment.setOnTime(onTime);
            session.update(appointment);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public List getPatientsWithLastName(String lastName) {
        List patients = null;
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            TypedQuery query = session.getNamedQuery("findPatientByLastName");
            query.setParameter("name", lastName);
            patients = query.getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        }return patients;
    }



    public List<Patient> getEmailFromPatientName(String firstName, String lastName) {
        List patientsWithEmail = null;
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            TypedQuery query = session.getNamedQuery("findPatientEmails");
            query.setParameter("firstName", firstName);
            query.setParameter("lastName", lastName);
            patientsWithEmail = query.getResultList();
        }catch (HibernateException e){
            e.printStackTrace();
        }return patientsWithEmail;
    }

    public String patientsName(List<Patient> patients)
    {
        Set patientWithEmail = null;
        String names = "";
        //System.out.println(patientWithEmail.get(0).getPatientEmailAddresses());
        for(int i = 0; patients.size() > i; i++){
            patientWithEmail = patients.get(i).getPatientEmailAddresses();
        }
        Iterator<PatientEmailAddress> it = patientWithEmail.iterator();
        while (it.hasNext())
        {
            
        }
        return "";
    }




}








