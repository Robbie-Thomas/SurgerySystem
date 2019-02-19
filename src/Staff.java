import org.jetbrains.annotations.Contract;

import javax.persistence.PostUpdate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Staff {

    private int id;
    private Doctor doctor;
    private Nurse nurse;
    private LocalDateTime row_Create, row_LastUpdate;

    public Staff(){}

    public Staff(Doctor doctor, Nurse nurse, LocalDateTime row_Create) {
        this.doctor = doctor;
        this.nurse = nurse;
        this.row_Create = row_Create;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public LocalDateTime getRow_Create() {
        return row_Create;
    }

    public void setRow_Create(LocalDateTime row_Create) {
        this.row_Create = row_Create;
    }

    public LocalDateTime getRow_LastUpdate() {
        return row_LastUpdate;
    }

    private void setRow_LastUpdate(LocalDateTime row_LastUpdate) {
        this.row_LastUpdate = row_LastUpdate;
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff)) return false;
        Staff staff = (Staff) o;
        return id == staff.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //on update sets the row LastUpdate to the localtime
    @PostUpdate
    void postUpdate(Object object) {
        setRow_LastUpdate(LocalDateTime.now());
    }

}
