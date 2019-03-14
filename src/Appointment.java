import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;



@NamedQueries(
        @NamedQuery(
                name = "getAppointmentFromPatientNames",
                query ="select t from Appointment t \n" +
                "join t.patient p \n" +
                "where p.firstName = :firstName AND p.lastName = :lastName"
        )
)


@Entity
@Table(name = "appointment")
public class Appointment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Appointment_Id")
    private Integer id;

    @Column(name = "Date")
    private LocalDateTime appointmentDate;

    @Column(name = "Time")
    private LocalDateTime appointmentTime;

    @OneToOne
    @JoinColumn(name = "Room_Id")
    private Room room;


    @OneToOne
    @JoinColumn(name = "Patient_Id")
    private Patient patient;


    @OneToOne(targetEntity = Staff.class)
    @JoinColumn(name = "Staff_Id")
    private Staff staff;

    @Column(name = "On_Time")
    private Boolean onTime;


    @Column (name = "Row_Create")
    @CreationTimestamp
    @NotNull
    private LocalDateTime createDateTime;

    @Column (name = "Row_LastUpdate")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    public Appointment(){}

    public Appointment(LocalDateTime appointmentDate, LocalDateTime appointmentTime, Room room, Patient patient, Staff staff, Boolean onTime) {
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.room = room;
        this.patient = patient;
        this.staff = staff;
        this.onTime = onTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getOnTime() {
        return onTime;
    }

    public void setOnTime(Boolean onTime) {
        this.onTime = onTime;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appointment)) return false;
        Appointment that = (Appointment) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentDate=" + appointmentDate +
                ", appointmentTime=" + appointmentTime +
                ", room=" + room.getRoomNumber() + " " + room.getRoomName() +
                ", patient=" + patient.getFirstName() + " " + patient.getLastName() +
                ", Nurse=" + staff.getNurse().getFirstName() + " " + staff.getNurse().getLastName() +
                ", Doctor=" + staff.getDoctor().getFirstName() + " " + staff.getDoctor().getLastName() +
                ", onTime=" + onTime +
                '}';
    }
}

