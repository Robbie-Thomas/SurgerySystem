import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.jetbrains.annotations.Contract;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@NamedQueries(
        value = {
                @NamedQuery(
                        name = "findPatientByFirstName",
                        query = "select p from Patient p where p.firstName = :name"
                ),
                @NamedQuery(
                        name = "findPatientByLastName",
                        query = "select p from Patient p where p.lastName = :name"
                ),
                @NamedQuery(
                        name = "findPatientByDOB",
                        query = "select current_timestamp from Patient p where  p.dob = :dob"
                ),
                @NamedQuery(
                        name = "findPatientByFullName",
                        query = "select p from Patient p where p.firstName = :firstName AND p.lastName = :lastName"
                ),
                @NamedQuery(
                        name = "deletePatient",
                        query = "delete from Patient p where p.id = :id"
                )


        }
)


@Entity
@Table(name = "Patient")
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "Patient_id")
    private Integer id;

    @Column( name="Date_Of_Birth")
    private LocalDate dob;

    @Column( name="First_Name")
    private String firstName;

    @Column( name="Middle_Name")
    private String middleName;

    @Column( name="Last_Name")
    private String lastName;

    @Column( name="Medical_Information")
    private String medicalInformation;

    @Column( name="Is_Male")
    private boolean isMale;

    @Column (name = "Row_Create")
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column (name = "Row_LastUpdate")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    @OneToMany(
            mappedBy = "patient",
            targetEntity = Patient_Address.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Patient_Address> patientAddresses = new ArrayList<>();
    public void addAddress(Patient_Address patient_address){
        patientAddresses.add(patient_address);
        patient_address.setPatient(this);
    }

    public void removeAddress(Patient_Address patient_address){
        patientAddresses.remove(patient_address);
        patient_address.setPatient(null);
    }


    @OneToMany(
            mappedBy = "patient",
            targetEntity = PatientPhone.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PatientPhone> patientPhones = new ArrayList<>();

    @OneToMany(
            mappedBy = "patient",
            targetEntity = PatientEmailAddress.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PatientEmailAddress> patientEmailAddresses = new ArrayList<PatientEmailAddress>();

    public void addEmail(PatientEmailAddress patientEmailAddress){
        patientEmailAddresses.add(patientEmailAddress);
        patientEmailAddress.setPatient(this);
    }

    public void removeEmail(PatientEmailAddress patientEmailAddress){
        patientEmailAddresses.remove(patientEmailAddress);
        patientEmailAddress.setPatient(null);
    }

    @OneToMany(
            mappedBy = "patient",
            targetEntity = Appointment.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Appointment> appointments = new ArrayList<Appointment>();
    public void addAppointment(Appointment appointment){
        appointments.add(appointment);
        appointment.setPatient(this);
    }

    public void removeAppointment(Appointment appointment){
        appointments.remove(appointment);
        appointment.setPatient(null);
    }


    public Patient() {
    }

    public Patient(LocalDate dob, String firstName, String lastName, String medicalInformation, boolean isMale) {
        this.dob = dob;
        this.firstName = firstName;
        this.lastName = lastName;
        this.medicalInformation = medicalInformation;
        this.isMale = isMale;
    }

    public Patient(LocalDate dob, String firstName, String middleName, String lastName, String medicalInformation, boolean isMale) {
        this.dob = dob;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.medicalInformation = medicalInformation;
        this.isMale = isMale;
    }

    public List<Patient_Address> getPatientAddresses() {
        return patientAddresses;
    }

    public void setPatientAddresses(List<Patient_Address> patientAddresses) {
        this.patientAddresses = patientAddresses;
    }

    public List<PatientPhone> getPatientPhones() {
        return patientPhones;
    }

    public void setPatientPhones(List<PatientPhone> patientPhones) {
        this.patientPhones = patientPhones;
    }

    public List<PatientEmailAddress> getPatientEmailAddresses() {
        return patientEmailAddresses;
    }

    public void setPatientEmailAddresses(List<PatientEmailAddress> patientEmailAddresses) {
        this.patientEmailAddresses = patientEmailAddresses;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointmentset) {
        this.appointments = appointmentset;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
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





    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return id.equals(patient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", dob=" + dob +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", medicalInformation='" + medicalInformation + '\'' +
                ", isMale=" + isMale +
                ", createDateTime=" + createDateTime +
                ", updateDateTime=" + updateDateTime +
                ", patientAddresses=" + patientAddresses +
                ", patientPhones=" + patientPhones +
                ", patientEmailAddresses=" + patientEmailAddresses +
                '}';
    }
}
