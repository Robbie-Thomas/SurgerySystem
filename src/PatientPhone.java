import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.jetbrains.annotations.Contract;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@NamedQueries(
        value = {
                @NamedQuery(
                        name = "findPhoneByName",
                        query = "select t from PatientPhone t \n" +
                                "join t.patient p \n" +
                                "where p.firstName = :firstName AND p.lastName = :lastName"
                )
        }
)

@Entity
@Table(name = "PatientPhoneNumber")
public class PatientPhone {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PatientPhoneNumber_Id")
    private Integer id;

    @Column(name = "Phone_Number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn (name = "Patient_Id", nullable = false)
    private Patient patient;

    @Column (name = "Row_Create")
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column (name = "Row_LastUpdate")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    public PatientPhone() {
    }

    /**
     * @param phoneNumber
     * @param patient
     */
    public PatientPhone(String phoneNumber, Patient patient) {
        this.phoneNumber = phoneNumber;
        this.patient = patient;
    }

    /**
     *
     * @return
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     *
     * @param patient
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     *
     * @return
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @param o
     * @return
     */
    @Contract(value = "null -> false", pure = true)
    @Override
    //looks to see if the phone number is unique
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PatientPhone)) return false;
        PatientPhone that = (PatientPhone) o;
        return phoneNumber == that.phoneNumber &&
                id.equals(that.id);
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
