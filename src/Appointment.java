import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 */
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
                        name = "getAppointmentsByDateWeek",
                        query = "select a from Appointment a where a.appointmentDate BETWEEN :date1 AND :date2 "

                ),
                @NamedQuery(
                        name = "getAppointmentsFromDoctorName",
                        query = "select t from Appointment t \n" +
                                "join t.staff s \n" +
                                "where s.doctor.firstName = :firstName AND s.doctor.lastName = :lastName"
                ),
                @NamedQuery(
                        name = "getAppointmentsFromStaffId",
                        query = "select t from Appointment t \n" +
                                "join t.staff s \n" +
                                "where s.id = :staffId"
                ),
                @NamedQuery(
                        name = "getAppointmentsFromNurseName",
                        query = "select t from Appointment t \n" +
                                "join t.staff s \n" +
                                "where s.nurse.firstName = :firstName AND s.nurse.lastName = :lastName"
                ),
                @NamedQuery(
                        name = "updateAppointmentCheckedIn",
                        query = "update Appointment t SET checkedIn = :checkedInBool  \n" +
                                "where t.id = : id"
                ),
                @NamedQuery(
                        name = "updateAppointmentOnTime",
                        query = "update Appointment t SET checkedIn = :OnTimeBool  \n" +
                                "where t.id = : id"
                ),
                @NamedQuery(
                        name = "deleteAppointment",
                        query = "delete Appointment t where t.id = : id"
                ),

        }
)

@Entity
@Table(name = "Appointment")
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


    @ManyToOne
    @JoinColumn(name = "Patient_Id")
    private Patient patient;


    @OneToOne(targetEntity = Staff.class)
    @JoinColumn(name = "Staff_Id")
    private Staff staff;

    @Column(name = "On_Time", columnDefinition = "BOOLEAN")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean onTime;


    @Column(name = "Checked_In", columnDefinition = "BOOLEAN")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean checkedIn;


    @Column (name = "Row_Create")
    @CreationTimestamp
    @NotNull
    private LocalDateTime createDateTime;

    @Column (name = "Row_LastUpdate")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    public Appointment(){}

    /**
     * @param appointmentDate
     * @param appointmentTime
     * @param room
     * @param patient
     * @param staff
     * @param onTime
     * @param checkedIn
     */
    public Appointment(LocalDate appointmentDate, Time appointmentTime, Room room, Patient patient, Staff staff, Boolean onTime, Boolean checkedIn) {
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.room = room;
        this.patient = patient;
        this.staff = staff;
        this.onTime = onTime;
        this.checkedIn = checkedIn;
    }

    /**
     *
     * @return
     */
    public Boolean getCheckedIn() {
        return checkedIn;
    }

    /**
     *
     * @param checkedIn
     */
    public void setCheckedIn(Boolean checkedIn) {
        this.checkedIn = checkedIn;
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
    public Boolean getOnTime() {
        return onTime;
    }

    /**
     *
     * @param onTime
     */
    public void setOnTime(Boolean onTime) {
        this.onTime = onTime;
    }

    /**
     *
     * @return
     */
    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    /**
     *
     * @param appointmentDate
     */
    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    /**
     *
     * @return
     */
    public Time getAppointmentTime() {
        return appointmentTime;
    }

    /**
     *
     * @param appointmentTime
     */
    public void setAppointmentTime(Time appointmentTime) {
        this.appointmentTime = appointmentTime;
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
    public Staff getStaff() {
        return staff;
    }

    /**
     *
     * @param staff
     */
    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    /**
     *
     * @return
     */
    public Room getRoom() {
        return room;
    }

    /**
     *
     * @param room
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     *
     * @return
     */
    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    /**
     *
     * @param createDateTime
     */
    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    /**
     *
     * @return
     */
    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    /**
     *
     * @param updateDateTime
     */
    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appointment)) return false;
        Appointment that = (Appointment) o;
        return id.equals(that.id);
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

