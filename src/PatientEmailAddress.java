import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.jetbrains.annotations.Contract;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@NamedQueries(
        value = {
                @NamedQuery(
                        name = "findEmailByPatients",
                        query = "select p as Patient from Patient p left outer join fetch PatientEmailAddress pe where pe.emailAddress = :email"
                )
                ,
                @NamedQuery(
                        name = "findEmailsByName",
                        query ="select t from PatientEmailAddress t \n" +
                                "join t.patient p \n" +
                                "where p.firstName = :firstName AND p.lastName = :lastName"
                )

        }
)

@Entity
@Table(name = "PatientEmailAddress")
public class PatientEmailAddress {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PatientEmailAddress_Id", unique = true)
    private Integer id;

    @Column(name = "Email_Address", unique = true)
    private String emailAddress;

    @ManyToOne
    @JoinColumn (name = "Patient_Id", nullable = false)
    private Patient patient;

    @Column (name = "Row_Create")
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column (name = "Row_LastUpdate")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;


    public PatientEmailAddress() {
    }

    /**
     * @param emailAddress
     * @param patient
     */
    public PatientEmailAddress(String emailAddress, Patient patient) {
        this.emailAddress = emailAddress;
        this.patient = patient;
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
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     *
     * @param emailAddress
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     *
     * @param o
     * @return
     */
    @Contract(value = "null -> false", pure = true)
    @Override
    //Checks to see if an email address with the same email already exists
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PatientEmailAddress)) return false;
        PatientEmailAddress that = (PatientEmailAddress) o;
        return Objects.equals(emailAddress, that.emailAddress);
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(emailAddress);
    }

    //on update sets the row LastUpdate to the localtime

}
