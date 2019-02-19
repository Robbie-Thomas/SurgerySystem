import java.util.Date;

public class Staff {

    private int id;
    private Doctor doctor;
    private Nurse nurse;
    private Date row_Create, row_LastUpdate;

    public Staff(){}

    public Staff(int id, Doctor doctor, Nurse nurse, Date row_Create) {
        this.id = id;
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
}
