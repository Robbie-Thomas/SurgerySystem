import org.jetbrains.annotations.Contract;

import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.Objects;

public class Nurse{

    private int id;
    private String firstName, lastName, specialistArea;
    private LocalDateTime row_Create, row_LastUpdate;
    private Boolean MondayAM, MondayPM, TuesdayAM, TuesdayPM, WednesdayAM, WednesdayPM, ThursdayAM, ThursdayPM, FridayAM, FridayPM, SaturdayAM, SaturdayPM, SundayAM, SundayPM;

    public Nurse() {
    }

    public Nurse(String firstName, String lastName, String specialistArea, Boolean mondayAM, Boolean mondayPM, Boolean tuesdayAM, Boolean tuesdayPM, Boolean wednesdayAM, Boolean wednesdayPM, Boolean thursdayAM, Boolean thursdayPM, Boolean fridayAM, Boolean fridayPM, Boolean saturdayAM, Boolean saturdayPM, Boolean sundayAM, Boolean sundayPM) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialistArea = specialistArea;
        MondayAM = mondayAM;
        MondayPM = mondayPM;
        TuesdayAM = tuesdayAM;
        TuesdayPM = tuesdayPM;
        WednesdayAM = wednesdayAM;
        WednesdayPM = wednesdayPM;
        ThursdayAM = thursdayAM;
        ThursdayPM = thursdayPM;
        FridayAM = fridayAM;
        FridayPM = fridayPM;
        SaturdayAM = saturdayAM;
        SaturdayPM = saturdayPM;
        SundayAM = sundayAM;
        SundayPM = sundayPM;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Boolean getMondayAM() {
        return MondayAM;
    }

    public void setMondayAM(Boolean mondayAM) {
        MondayAM = mondayAM;
    }

    public Boolean getMondayPM() {
        return MondayPM;
    }

    public void setMondayPM(Boolean mondayPM) {
        MondayPM = mondayPM;
    }

    public Boolean getTuesdayAM() {
        return TuesdayAM;
    }

    public void setTuesdayAM(Boolean tuesdayAM) {
        TuesdayAM = tuesdayAM;
    }

    public Boolean getTuesdayPM() {
        return TuesdayPM;
    }

    public void setTuesdayPM(Boolean tuesdayPM) {
        TuesdayPM = tuesdayPM;
    }

    public Boolean getWednesdayAM() {
        return WednesdayAM;
    }

    public void setWednesdayAM(Boolean wednesdayAM) {
        WednesdayAM = wednesdayAM;
    }

    public Boolean getWednesdayPM() {
        return WednesdayPM;
    }

    public void setWednesdayPM(Boolean wednesdayPM) {
        WednesdayPM = wednesdayPM;
    }

    public Boolean getThursdayAM() {
        return ThursdayAM;
    }

    public void setThursdayAM(Boolean thursdayAM) {
        ThursdayAM = thursdayAM;
    }

    public Boolean getThursdayPM() {
        return ThursdayPM;
    }

    public void setThursdayPM(Boolean thursdayPM) {
        ThursdayPM = thursdayPM;
    }

    public Boolean getFridayAM() {
        return FridayAM;
    }

    public void setFridayAM(Boolean fridayAM) {
        FridayAM = fridayAM;
    }

    public Boolean getFridayPM() {
        return FridayPM;
    }

    public void setFridayPM(Boolean fridayPM) {
        FridayPM = fridayPM;
    }

    public Boolean getSaturdayAM() {
        return SaturdayAM;
    }

    public void setSaturdayAM(Boolean saturdayAM) {
        SaturdayAM = saturdayAM;
    }

    public Boolean getSaturdayPM() {
        return SaturdayPM;
    }

    public void setSaturdayPM(Boolean saturdayPM) {
        SaturdayPM = saturdayPM;
    }

    public Boolean getSundayAM() {
        return SundayAM;
    }

    public void setSundayAM(Boolean sundayAM) {
        SundayAM = sundayAM;
    }

    public Boolean getSundayPM() {
        return SundayPM;
    }

    public void setSundayPM(Boolean sundayPM) {
        SundayPM = sundayPM;
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

    @PrePersist
    void prePersist(Object object) {
        setRow_Create(LocalDateTime.now());
    }

}
