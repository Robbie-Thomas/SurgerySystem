import org.jetbrains.annotations.Contract;

import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class PatientEmailAddress {

    private int id;
    private String emailAddress;
    private LocalDateTime row_Create, row_LastUpdate;

    public PatientEmailAddress() {
    }

    public PatientEmailAddress(String emailAddress, LocalDateTime row_Create) {
        this.row_Create = row_Create;
        this.emailAddress = emailAddress;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @PostUpdate
    void postUpdate(Object object) {
        setRow_LastUpdate(LocalDateTime.now());
    }

    @PrePersist
    void prePersist(Object object) {
        setRow_Create(LocalDateTime.now());
    }
}
