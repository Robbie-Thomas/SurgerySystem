import org.jetbrains.annotations.Contract;

import javax.persistence.PostUpdate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Nurse {

    private int id, workday_id;
    private String firstName, lastName, specialistArea;
    private LocalDateTime row_Create, row_LastUpdate;

    public Nurse() {
    }

    public Nurse(int workday_id, String firstName, String lastName, String specialistArea, LocalDateTime row_Create) {

        this.workday_id = workday_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialistArea = specialistArea;
        this.row_Create = row_Create;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorkday_id() {
        return workday_id;
    }

    public void setWorkday_id(int workday_id) {
        this.workday_id = workday_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialistArea() {
        return specialistArea;
    }

    public void setSpecialistArea(String specialistArea) {
        this.specialistArea = specialistArea;
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
        if (!(o instanceof Nurse)) return false;
        Nurse nurse = (Nurse) o;
        return id == nurse.id;
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
