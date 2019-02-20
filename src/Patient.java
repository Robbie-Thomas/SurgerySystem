import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.jetbrains.annotations.Contract;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Patient")
public class Patient {
    @Id @GeneratedValue
    @Column (name = "id")
    private int id;

    @Column( name="DateOfBirth")
    private Date dob;

    @Column( name="First_Name")
    private String firstName;

    @Column( name="Middle_Name")
    private String middleName;

    @Column( name="Last_Name")
    private String lastName;

    @Column( name="Medical_Information")
    private String medicalInformation;

    @Column( name="Appointment_Id")
    private Appointment appointment;

    @Column( name="Address_Id")
    private PatientAddress patientAddress;

    @Column( name="Phone_Id")
    private PatientPhone patientPhone;

    @Column( name="Email_Id")
    private PatientEmailAddress emailAddress;

    @Column (name = "Create_Time")
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column (name = "Update_Time")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;


    public Patient(Date dob, String firstName, String lastName, String medicalInformation, PatientAddress patientAddress, PatientPhone patientPhone, PatientEmailAddress emailAddress) {
    }

    public Patient(Date dob, String firstName, String lastName, String medicalInformation, Appointment appointment, PatientAddress patientAddress, PatientPhone patientPhone, PatientEmailAddress emailAddress) {
        this.dob = dob;
        this.firstName = firstName;
        this.lastName = lastName;
        this.medicalInformation = medicalInformation;
        this.appointment = appointment;
        this.patientAddress = patientAddress;
        this.patientPhone = patientPhone;
        this.emailAddress = emailAddress;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMedicalInformation() {
        return medicalInformation;
    }

    public void setMedicalInformation(String medicalInformation) {
        this.medicalInformation = medicalInformation;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public PatientAddress getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(PatientAddress patientAddress) {
        this.patientAddress = patientAddress;
    }

    public PatientPhone getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(PatientPhone patientPhone) {
        this.patientPhone = patientPhone;
    }

    public PatientEmailAddress getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(PatientEmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }



    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return id == patient.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }



}
