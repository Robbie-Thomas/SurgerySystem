import java.util.Date;

public class PatientPhone {

    private int phoneNumber;
    private int id;
    private Date row_Create, row_LastUpdate;

    public PatientPhone() {
    }

    public PatientPhone(int phoneNumber, Date row_Create) {
        this.phoneNumber = phoneNumber;
        this.row_Create = row_Create;
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
}
