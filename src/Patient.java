import org.jetbrains.annotations.Contract;

import javax.persistence.PostUpdate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Patient {

    private int id;
    private Date dob;
    private String firstName, middleName, lastName, medicalInformation;
    private Appointment appointment;
    private PatientAddress patientAddress;
    private PatientPhone patientPhone;
    private PatientEmailAddress emailAddress;
    private LocalDateTime row_Create, row_LastUpdate;


    public Patient() {
    }

    public Patient(Date dob, String firstName, String lastName, String medicalInformation, Appointment appointment, PatientAddress patientAddress, PatientPhone patientPhone, PatientEmailAddress emailAddress, LocalDateTime row_Create) {
        this.dob = dob;
        this.firstName = firstName;
        this.lastName = lastName;
        this.medicalInformation = medicalInformation;
        this.appointment = appointment;
        this.patientAddress = patientAddress;
        this.patientPhone = patientPhone;
        this.emailAddress = emailAddress;
        this.row_Create = row_Create;
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

    public LocalDateTime getRow_Create() {
        return row_Create;
    }

    public void setRow_Create(LocalDateTime row_Create) {
        this.row_Create = row_Create;
    }

    public LocalDateTime getRow_LastUpdate() {
        return row_LastUpdate;
    }

    private void setRow_LastUpdate(LocalDateTime row_LastUpdate) {
        this.row_LastUpdate = row_LastUpdate;
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

    //on update sets the row LastUpdate to the localtime
    @PostUpdate
    void postUpdate(Object object) {
        setRow_LastUpdate(LocalDateTime.now());
    }
}
