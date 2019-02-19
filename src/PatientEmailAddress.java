import java.util.Date;

public class PatientEmailAddress {

    private int id;
    private String emailAddress;
    private Date row_Create, row_LastUpdate;

    public PatientEmailAddress() {
    }

    public PatientEmailAddress(String emailAddress, Date row_Create) {
        this.row_Create = row_Create;
        this.emailAddress = emailAddress;
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
}
