import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@NamedQueries(
        value = {
                @NamedQuery(
                        name = "getAppointmentsFromPatientNames",
                        query = "select t from Appointment t \n" +
                                "join t.patient p \n" +
                                "where p.firstName = :firstName AND p.lastName = :lastName"
                ),
                @NamedQuery(
                        name = "getAppointmentsByDate",
                        query = "select a from Appointment a where a.appointmentDate = :date"
                ),
                @NamedQuery(
                        name = "getAppointmentsFromDoctorName",
                        query = "select t from Appointment t \n" +
                                "join t.staff s \n" +
                                "where s.doctor.firstName = :firstName AND s.doctor.lastName = :lastName"
                ),
                @NamedQuery(
                        name = "getAppointmentsFromNurseName",
                        query = "select t from Appointment t \n" +
                                "join t.staff s \n" +
                                "where s.nurse.firstName = :firstName AND s.nurse.lastName = :lastName"
                )
        }
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
    private LocalDate appointmentDate;

    @Column(name = "Time")
    private Time appointmentTime;

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

    public Appointment(LocalDate appointmentDate, Time appointmentTime, Room room, Patient patient, Staff staff, Boolean onTime) {
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

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Time getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Time appointmentTime) {
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


}

