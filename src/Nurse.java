import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.jetbrains.annotations.Contract;

import javax.persistence.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Nurse")
public class Nurse{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Nurse_Id")
    private Integer id;

    @Column(name = "First_Name")
    private String  firstName;

    @Column(name = "Last_Name")
    private String  lastName;

    @Column( name="Is_Male")
    private boolean isMale;

    @Column(name = "Specialist_Area")
    private String  specialistArea;

    @Column(name = "Monday_AM")
    private Boolean MondayAM;

    @Column(name = "Monday_PM")
    private Boolean MondayPM;

    @Column(name = "Tuesday_AM")
    private Boolean TuesdayAM;

    @Column(name = "Tuesday_PM")
    private Boolean TuesdayPM;

    @Column(name = "Wednesday_AM")
    private Boolean WednesdayAM;

    @Column(name = "Wednesday_PM")
    private Boolean WednesdayPM;

    @Column(name = "Thursday_AM")
    private Boolean ThursdayAM;

    @Column(name = "Thursday_PM")
    private Boolean ThursdayPM;

    @Column(name = "Friday_AM")
    private Boolean FridayAM;

    @Column(name= "Friday_PM")
    private Boolean FridayPM;

    @Column(name = "Saturday_AM")
    private Boolean SaturdayAM;

    @Column(name = "Saturday_PM")
    private Boolean SaturdayPM;

    @Column(name= "Sunday_AM")
    private Boolean SundayAM;

    @Column(name = "Sunday_PM")
    private Boolean SundayPM;

    @Column (name = "Row_Create")
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column (name = "Row_LastUpdate")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    public Nurse() {
    }

    /**
     * @param firstName
     * @param lastName
     * @param specialistArea
     * @param isMale
     * @param mondayAM
     * @param mondayPM
     * @param tuesdayAM
     * @param tuesdayPM
     * @param wednesdayAM
     * @param wednesdayPM
     * @param thursdayAM
     * @param thursdayPM
     * @param fridayAM
     * @param fridayPM
     * @param saturdayAM
     * @param saturdayPM
     * @param sundayAM
     * @param sundayPM
     */
    public Nurse(String firstName, String lastName, String specialistArea, Boolean isMale, Boolean mondayAM, Boolean mondayPM, Boolean tuesdayAM, Boolean tuesdayPM, Boolean wednesdayAM, Boolean wednesdayPM, Boolean thursdayAM, Boolean thursdayPM, Boolean fridayAM, Boolean fridayPM, Boolean saturdayAM, Boolean saturdayPM, Boolean sundayAM, Boolean sundayPM) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialistArea = specialistArea;
        this.isMale = isMale;
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
    public boolean isMale() {
        return isMale;
    }

    /**
     *
     * @param male
     */
    public void setMale(boolean male) {
        isMale = male;
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
    public String getSpecialistArea() {
        return specialistArea;
    }

    /**
     *
     * @param specialistArea
     */
    public void setSpecialistArea(String specialistArea) {
        this.specialistArea = specialistArea;
    }

    /**
     *
     * @return
     */
    public Boolean getMondayAM() {
        return MondayAM;
    }

    /**
     *
     * @param mondayAM
     */
    public void setMondayAM(Boolean mondayAM) {
        MondayAM = mondayAM;
    }

    /**
     *
     * @return
     */
    public Boolean getMondayPM() {
        return MondayPM;
    }

    /**
     *
     * @param mondayPM
     */
    public void setMondayPM(Boolean mondayPM) {
        MondayPM = mondayPM;
    }

    /**
     *
     * @return
     */
    public Boolean getTuesdayAM() {
        return TuesdayAM;
    }

    /**
     *
     * @param tuesdayAM
     */
    public void setTuesdayAM(Boolean tuesdayAM) {
        TuesdayAM = tuesdayAM;
    }

    /**
     *
     * @return
     */
    public Boolean getTuesdayPM() {
        return TuesdayPM;
    }

    /**
     *
     * @param tuesdayPM
     */
    public void setTuesdayPM(Boolean tuesdayPM) {
        TuesdayPM = tuesdayPM;
    }

    /**
     *
     * @return
     */
    public Boolean getWednesdayAM() {
        return WednesdayAM;
    }

    /**
     *
     * @param wednesdayAM
     */
    public void setWednesdayAM(Boolean wednesdayAM) {
        WednesdayAM = wednesdayAM;
    }

    /**
     *
     * @return
     */
    public Boolean getWednesdayPM() {
        return WednesdayPM;
    }

    /**
     *
     * @param wednesdayPM
     */
    public void setWednesdayPM(Boolean wednesdayPM) {
        WednesdayPM = wednesdayPM;
    }

    /**
     *
     * @return
     */
    public Boolean getThursdayAM() {
        return ThursdayAM;
    }

    /**
     *
     * @param thursdayAM
     */
    public void setThursdayAM(Boolean thursdayAM) {
        ThursdayAM = thursdayAM;
    }

    /**
     *
     * @return
     */
    public Boolean getThursdayPM() {
        return ThursdayPM;
    }

    /**
     *
     * @param thursdayPM
     */
    public void setThursdayPM(Boolean thursdayPM) {
        ThursdayPM = thursdayPM;
    }

    /**
     *
     * @return
     */
    public Boolean getFridayAM() {
        return FridayAM;
    }

    /**
     *
     * @param fridayAM
     */
    public void setFridayAM(Boolean fridayAM) {
        FridayAM = fridayAM;
    }

    /**
     *
     * @return
     */
    public Boolean getFridayPM() {
        return FridayPM;
    }

    /**
     *
     * @param fridayPM
     */
    public void setFridayPM(Boolean fridayPM) {
        FridayPM = fridayPM;
    }

    /**
     *
     * @return
     */
    public Boolean getSaturdayAM() {
        return SaturdayAM;
    }

    /**
     *
     * @param saturdayAM
     */
    public void setSaturdayAM(Boolean saturdayAM) {
        SaturdayAM = saturdayAM;
    }

    /**
     *
     * @return
     */
    public Boolean getSaturdayPM() {
        return SaturdayPM;
    }

    /**
     *
     * @param saturdayPM
     */
    public void setSaturdayPM(Boolean saturdayPM) {
        SaturdayPM = saturdayPM;
    }

    /**
     *
     * @return
     */
    public Boolean getSundayAM() {
        return SundayAM;
    }

    /**
     *
     * @param sundayAM
     */
    public void setSundayAM(Boolean sundayAM) {
        SundayAM = sundayAM;
    }

    /**
     *
     * @return
     */
    public Boolean getSundayPM() {
        return SundayPM;
    }

    /**
     *
     * @param sundayPM
     */
    public void setSundayPM(Boolean sundayPM) {
        SundayPM = sundayPM;
    }

    /**
     *
     * @param date
     * @param time
     * @return
     */
    @SuppressWarnings("Duplicates")
    public boolean canWork(Date date, Time time){
        Boolean canWork =false;
        Boolean aM = false;

        if(time.getTime() < 12){ aM = true;}
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E");
        simpleDateFormat.format(date);
        if(aM = true) {
            switch (simpleDateFormat.format(date)) {
                case "Mon": if (getMondayAM() == true) canWork = true;
                    break;
                case "Tue" :if (getTuesdayAM() == true) canWork = true;
                    break;
                case "Wed" :if (getWednesdayAM() == true) canWork = true;
                    break;
                case "Thu" :if (getThursdayAM() == true) canWork = true;
                    break;
                case "Fri" :if (getFridayAM() == true) canWork = true;
                    break;
                case "Sat" :if (getSaturdayAM() == true) canWork = true;
                    break;
                case "Sun" :if (getSundayAM() == true) canWork = true;
                default: return false;
            }
        }
        switch (simpleDateFormat.format(date)) {
            case "Mon": if (getMondayPM() == true) canWork = true;
                break;
            case "Tue" :if (getTuesdayPM() == true) canWork = true;
                break;
            case "Wed" :if (getWednesdayPM() == true) canWork = true;
                break;
            case "Thu" :if (getThursdayPM() == true) canWork = true;
                break;
            case "Fri" :if (getFridayPM() == true) canWork = true;
                break;
            case "Sat" :if (getSaturdayPM() == true) canWork = true;
                break;
            case "Sun" :if (getSundayPM() == true) canWork = true;
            default: return false;
        }
        return canWork;
    }

    /**
     *
     * @param o
     * @return
     */
    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Nurse)) return false;
        Nurse nurse = (Nurse) o;
        return id.equals(nurse.id);
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
