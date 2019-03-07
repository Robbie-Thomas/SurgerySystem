import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.jetbrains.annotations.Contract;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "PatientEmailAddress")
public class PatientEmailAddress {


    @Id
    @GeneratedValue
    @Column(name = "PatientEmailAddress_Id", unique = true)
    private Integer id;

    @Column(name = "Email_Address", unique = true)
    private String emailAddress;

    @ManyToOne
    @JoinColumn (name = "Patient_Id")
    private int patientId;

    @Column (name = "Row_Create")
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column (name = "Row_LastUpdate")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;


    public PatientEmailAddress() {
    }

    public PatientEmailAddress(String emailAddress, Integer patientId) {
        this.emailAddress = emailAddress;
        this.patientId = patientId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    //Checks to see if an email address with the same email already exists
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PatientEmailAddress)) return false;
        PatientEmailAddress that = (PatientEmailAddress) o;
        return Objects.equals(emailAddress, that.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress);
    }

    //on update sets the row LastUpdate to the localtime

}
