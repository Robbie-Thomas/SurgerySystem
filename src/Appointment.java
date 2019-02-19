import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Date;
import java.time.LocalDateTime;


public class Appointment
{
    private Date appointmentDate;
    private LocalDateTime appointmentTime;
    private Patient patient;
    private Staff staff;
    private String roomName;
    private int roomNumber;
    private Date row_Create, row_LastUpdate;

    public Appointment(){}

    public Appointment(Date appointmentDate, LocalDateTime appointmentTime, Patient patient, Staff staff, String roomName, int roomNumber, Date row_Create) {
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.patient = patient;
        this.staff = staff;
        this.roomName = roomName;
        this.roomNumber = roomNumber;
        this.row_Create = row_Create;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
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

    public Date getRow_Create() {
        return row_Create;
    }

    public void setRow_Create(Date row_Create) {
        this.row_Create = row_Create;
    }

    public Date getRow_LastUpdate() {
        return row_LastUpdate;
    }

    public void setRow_LastUpdate(Date row_LastUpdate) {
        this.row_LastUpdate = row_LastUpdate;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

}

