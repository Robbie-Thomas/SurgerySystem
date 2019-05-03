import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NamedQueries(
        value = {
                @NamedQuery(
                        name = "getStaff",
                        query = "SELECT s from Staff s"
                ),
        }
)

@Entity
@Table(name = "Staff")
public class Staff {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Staff_Id")
    @NotNull
    private Integer id;

    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            targetEntity = Doctor.class
    )
    @JoinColumn(name = "Doctor_Id")
    private Doctor doctor;

    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            targetEntity = Nurse.class
    )
    @JoinColumn(name = "Nurse_Id")
    private Nurse nurse;

    @Column (name = "Row_Create")
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column (name = "Row_LastUpdate")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;


    public Staff(){}

    public Staff(Doctor doctor) {
        this.doctor = doctor;

    }

    public Staff(Nurse nurse){
        this.nurse = nurse;
    }
    public Staff(Doctor doctor, Nurse nurse){
        this.doctor = doctor;
        this.nurse = nurse;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }



    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff)) return false;
        Staff staff = (Staff) o;
        return id.equals(staff.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }




}
