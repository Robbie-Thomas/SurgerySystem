import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.jetbrains.annotations.Contract;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
                )

        }
)


@Entity
@Table(name = "patient")
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
            targetEntity = Address.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Address> addresses = new HashSet<Address>();

    @OneToMany(
            targetEntity = PatientPhone.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<PatientPhone> patientPhones = new HashSet<PatientPhone>();

    @OneToMany(
            targetEntity = PatientEmailAddress.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<PatientEmailAddress> patientEmailAddresses = new HashSet<PatientEmailAddress>();


    public Patient() {
    }

    public Patient(LocalDate dob, String firstName, String lastName, String medicalInformation, boolean isMale) {
        this.dob = dob;
        this.firstName = firstName;
        this.lastName = lastName;
        this.medicalInformation = medicalInformation;
        this.isMale = isMale;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<PatientPhone> getPatientPhones() {
        return patientPhones;
    }

    public void setPatientPhones(Set<PatientPhone> patientPhones) {
        this.patientPhones = patientPhones;
    }

    public Set<PatientEmailAddress> getPatientEmailAddresses() {
        return patientEmailAddresses;
    }

    public void setPatientEmailAddresses(Set<PatientEmailAddress> patientEmailAddresses) {
        this.patientEmailAddresses = patientEmailAddresses;
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
        return id == patient.id;
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
                ", addresses=" + addresses +
                ", patientPhones=" + patientPhones +
                ", patientEmailAddresses=" + patientEmailAddresses +
                '}';
    }
}
