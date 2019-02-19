import org.jetbrains.annotations.Contract;

import javax.persistence.PostUpdate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class PatientPhone {

    private int phoneNumber;
    private int id;
    private LocalDateTime row_Create, row_LastUpdate;

    public PatientPhone() {
    }

    public PatientPhone(int phoneNumber, LocalDateTime row_Create) {
        this.phoneNumber = phoneNumber;
        this.row_Create = row_Create;
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
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

    //on update sets the row LastUpdate to the localtime
    @PostUpdate
    void postUpdate(Object object) {
        setRow_LastUpdate(LocalDateTime.now());
    }
}
