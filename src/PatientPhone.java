import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "PatientPhoneNumber")
public class PatientPhone {


    @Id
    @GeneratedValue
    @Column(name = "PatientPhoneNumber_Id")
    @NotNull
    private int id;

    @Column(name = "Phone_Number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "Patient_Id")
    private Patient patient;

    @Column (name = "Row_Create")
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column (name = "Row_LastUpdate")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    public PatientPhone() {
    }

    public PatientPhone(String phoneNumber, LocalDateTime row_Create, Patient patient) {
        this.phoneNumber = phoneNumber;
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    //looks to see if the phone number is unique
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PatientPhone)) return false;
        PatientPhone that = (PatientPhone) o;
        return phoneNumber == that.phoneNumber &&
                id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
